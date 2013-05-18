package fi.solita.utils.codegen.generators;

import static fi.solita.utils.codegen.Helpers.element2Fields;
import static fi.solita.utils.codegen.Helpers.elementClass;
import static fi.solita.utils.codegen.Helpers.elementGenericQualifiedName;
import static fi.solita.utils.codegen.Helpers.hasNonQmarkGenerics;
import static fi.solita.utils.codegen.Helpers.isPrivate;
import static fi.solita.utils.codegen.Helpers.resolveBoxedGenericType;
import static fi.solita.utils.codegen.Helpers.resolveVisibility;
import static fi.solita.utils.codegen.Helpers.simpleName;
import static fi.solita.utils.codegen.Helpers.staticElements;
import static fi.solita.utils.codegen.Helpers.typeParameter2String;
import static fi.solita.utils.codegen.generators.Content.EmptyLine;
import static fi.solita.utils.codegen.generators.Content.None;
import static fi.solita.utils.codegen.generators.Content.catchBlock;
import static fi.solita.utils.codegen.generators.Content.memberAccessor;
import static fi.solita.utils.codegen.generators.Content.memberInitializer;
import static fi.solita.utils.codegen.generators.Content.memberNameAccessor;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.flatMap;
import static fi.solita.utils.functional.Functional.isEmpty;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.Functional.subtract;
import static fi.solita.utils.functional.Functional.zip;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Predicates.not;
import static fi.solita.utils.functional.Transformers.prepend;

import java.util.List;
import java.util.regex.Pattern;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;

import fi.solita.utils.codegen.Helpers;
import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Collections;
import fi.solita.utils.functional.Function1;
import fi.solita.utils.functional.Function2;
import fi.solita.utils.functional.Predicate;
import fi.solita.utils.functional.Transformer;
import fi.solita.utils.functional.Tuple2;

public class InstanceFieldsAsFunctions extends Function2<InstanceFieldsAsFunctions.Options, TypeElement, Iterable<String>> {
    
    public static final Function1<Options, Function1<TypeElement, Iterable<String>>> instance = new InstanceFieldsAsFunctions().curried();
    
    @SuppressWarnings("rawtypes")
    public static interface Options {
        Class<? extends Apply> getClassForInstanceFields();
        Class<? extends Apply> getPredicateClassForInstanceFields();
        List<String> getAdditionalBodyLinesForInstanceFields();
        boolean generateMemberAccessorForFields();
        boolean generateMemberInitializerForFields();
        boolean generateMemberNameAccessorForFields();
        boolean makeFieldsPublic();
        boolean onlyPublicMembers();
    }
    
    @Override
    public Iterable<String> apply(Options options, TypeElement source) {
        Iterable<VariableElement> elements = element2Fields.apply(source);
        if (options.onlyPublicMembers()) {
            elements = filter(elements, new Predicate<Element>() {
                @Override
                public boolean accept(Element candidate) {
                    return candidate.getModifiers().contains(Modifier.PUBLIC);
                }
            });
        }
      
        return flatMap(filter(elements, not(staticElements)), variableElementGen.curried().apply(options));
    }
    
    public static Function2<Options, VariableElement, Iterable<String>> variableElementGen = new Function2<InstanceFieldsAsFunctions.Options, VariableElement, Iterable<String>>() {
        @Override
        public Iterable<String> apply(Options options, VariableElement field) {
            TypeElement enclosingElement = (TypeElement) field.getEnclosingElement();
            
            String returnType = resolveBoxedGenericType(field.asType());
            
            Iterable<String> allTypeParams = map(enclosingElement.getTypeParameters(), typeParameter2String);
            List<String> allTypeParamsWithoutConstraints = newList(map(enclosingElement.getTypeParameters(), simpleName));
            final String rett = ("." + returnType + ".");
            List<Tuple2<String, String>> relevantTypeParamPairs = newList(filter(zip(allTypeParams, allTypeParamsWithoutConstraints), new Predicate<Tuple2<String,String>>() {
                @Override
                public boolean accept(Tuple2<String,String> candidate) {
                    return rett.matches(".*[^a-zA-Z0-9_]" + Pattern.quote(candidate._2) + "[^a-zA-Z0-9_].*");
                }
            }));
            
            List<String> relevantTypeParams = newList(map(relevantTypeParamPairs, Helpers.<String>left()));
            List<String> relevantTypeParamsWithoutConstraints = newList(map(relevantTypeParamPairs, Helpers.<String>right()));

            final List<String> toReplace = newList(subtract(allTypeParamsWithoutConstraints, relevantTypeParamsWithoutConstraints));
            Transformer<String,String> doReplace = new Transformer<String,String>() {
                @Override
                public String transform(String candidate) {
                    for (String r: toReplace) {
                        candidate = candidate.replaceAll("([^a-zA-Z0-9_])([?]\\s*(?:extends|super)\\s+)?" + Pattern.quote(r) + "([^a-zA-Z0-9_])", "$1?$3");
                    }
                    return candidate;
                }
            };
            
            boolean isPrivate = isPrivate(field);
            boolean needsToBeFunction = !relevantTypeParams.isEmpty();
            
            String relevantTypeParamsString = isEmpty(relevantTypeParams) ? "" : "<" + mkString(", ", map(relevantTypeParams, doReplace)) + ">";
            returnType = doReplace.apply(returnType);
            
            String enclosingElementGenericQualifiedName = doReplace.apply(elementGenericQualifiedName(enclosingElement));
            
            String modifiers = (options.makeFieldsPublic() ? "public" : resolveVisibility(field)) + " static final";
            String fieldName = field.getSimpleName().toString();
            
            String returnClause = "return " + (isPrivate ? "(" + returnType + ")" : "");
            
            Class<?> fieldClass = returnType.equals(Boolean.class.getName()) ? options.getPredicateClassForInstanceFields() : options.getClassForInstanceFields();
            String fundef = fieldClass.getName().replace('$', '.') + "<" + enclosingElementGenericQualifiedName + ", " + returnType + ">";
            String declaration = modifiers + " " + (needsToBeFunction ? relevantTypeParamsString + " ": "") + fundef + " " + fieldName;

            Iterable<String> tryBlock = isPrivate
                ? Some(returnClause + "$getMember().get($self);")
                : Some(returnClause + "$self." + fieldName + ";");
            
            Iterable<String> tryCatchBlock = isPrivate
                ? concat(
                    Some("try {"),
                    map(tryBlock, prepend("    ")),
                    catchBlock,
                    Some("}"))
                : tryBlock;
                        
            Iterable<String> applyBlock = concat(
                Some("protected " + returnType + " $do(" + enclosingElementGenericQualifiedName + " $self) {"),
                map(tryCatchBlock, prepend("    ")),
                Some("}")
            );
                        
            @SuppressWarnings("unchecked")
            Iterable<String> res = concat(
                needsToBeFunction
                    ? Some(declaration + "() { return new " + fundef + "() {")
                    : Some(declaration + " = new " + fundef + "() {"),
                isPrivate || options.generateMemberInitializerForFields()
                    ? concat(map(memberInitializer(enclosingElementGenericQualifiedName, fieldName, elementClass(field), Collections.<String>newList()), prepend("    ")),
                             EmptyLine)
                    : None,
                options.generateMemberAccessorForFields()
                    ? concat(map(memberAccessor(enclosingElementGenericQualifiedName, elementClass(field)), prepend("    ")),
                             EmptyLine)
                    : None,
                options.generateMemberNameAccessorForFields()
                    ? concat(map(memberNameAccessor(fieldName), prepend("    ")),
                             EmptyLine)
                    : None,
                map(options.getAdditionalBodyLinesForInstanceFields(), prepend("    ")),
                EmptyLine,
                isPrivate && (hasNonQmarkGenerics(returnType) || field.asType().getKind() == TypeKind.TYPEVAR)
                    ? Some("    @SuppressWarnings(\"unchecked\")")
                    : None,
                map(applyBlock, prepend("    ")),
                Some("};"),
                needsToBeFunction
                    ? Some("}")
                    : None,
                EmptyLine
            );
            return res;
        }
    };
}