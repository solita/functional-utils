package fi.solita.utils.codegen.generators;

import static fi.solita.utils.codegen.Helpers.element2Fields;
import static fi.solita.utils.codegen.Helpers.elementGenericQualifiedName;
import static fi.solita.utils.codegen.Helpers.hasNonQmarkGenerics;
import static fi.solita.utils.codegen.Helpers.importType;
import static fi.solita.utils.codegen.Helpers.importTypes;
import static fi.solita.utils.codegen.Helpers.isFinal;
import static fi.solita.utils.codegen.Helpers.isPrivate;
import static fi.solita.utils.codegen.Helpers.padding;
import static fi.solita.utils.codegen.Helpers.publicElement;
import static fi.solita.utils.codegen.Helpers.qualifiedName;
import static fi.solita.utils.codegen.Helpers.resolveBoxedGenericType;
import static fi.solita.utils.codegen.Helpers.resolveVisibility;
import static fi.solita.utils.codegen.Helpers.simpleName;
import static fi.solita.utils.codegen.Helpers.staticElements;
import static fi.solita.utils.codegen.Helpers.typeParameter2String;
import static fi.solita.utils.codegen.generators.Content.EmptyLine;
import static fi.solita.utils.codegen.generators.Content.None;
import static fi.solita.utils.codegen.generators.Content.catchBlock;
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

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;

import fi.solita.utils.codegen.Helpers;
import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Function1;
import fi.solita.utils.functional.Function2;
import fi.solita.utils.functional.Predicate;
import fi.solita.utils.functional.Transformer;
import fi.solita.utils.functional.Tuple2;

public class InstanceFieldsAsFunctions extends Generator<InstanceFieldsAsFunctions.Options> {
    
    public static final InstanceFieldsAsFunctions instance = new InstanceFieldsAsFunctions();
    
    @SuppressWarnings("rawtypes")
    public static interface Options extends GeneratorOptions {
        Class<? extends Apply> getClassForInstanceFields(boolean isFinal);
        Class<? extends Apply> getPredicateClassForInstanceFields(boolean isFinal);
        List<String> getAdditionalBodyLinesForInstanceFields();
        boolean generateMemberAccessorForFields();
        boolean generateMemberInitializerForFields();
        boolean generateMemberNameAccessorForFields();
        boolean makeFieldsPublic();
        boolean onlyPublicMembers();
    }
    
    @Override
    public Iterable<String> apply(ProcessingEnvironment processingEnv, Options options, TypeElement source) {
        Iterable<VariableElement> elements = element2Fields.apply(source);
        if (options.onlyPublicMembers()) {
            elements = filter(elements, publicElement);
        }
      
        return flatMap(filter(elements, not(staticElements)), variableElementGen.ap(options));
    }
    
    private static final Transformer<String,Pattern> toReplacePattern = new Transformer<String,Pattern>() {
        @Override
        public Pattern transform(String obj) {
            return Pattern.compile("([^a-zA-Z0-9_])([?]\\s*(?:extends|super)\\s+)?" + Pattern.quote(obj.toString()) + "([^a-zA-Z0-9_])");
        }
    };
    
    public static Function2<Options, VariableElement, Iterable<String>> variableElementGen = new Function2<InstanceFieldsAsFunctions.Options, VariableElement, Iterable<String>>() {
        @Override
        public Iterable<String> apply(Options options, VariableElement field) {
            TypeElement enclosingElement = (TypeElement) field.getEnclosingElement();
            String enclosingElementQualifiedName = qualifiedName.apply(enclosingElement);
            String enclosingElementQualifiedNameImported = importTypes(enclosingElementQualifiedName);
            
            String returnType = resolveBoxedGenericType(field.asType());
            
            Iterable<String> allTypeParams = map(enclosingElement.getTypeParameters(), typeParameter2String);
            List<String> allTypeParamsWithoutConstraints = newList(map(enclosingElement.getTypeParameters(), simpleName));
            final String rett = ("." + returnType + ".");
            List<Tuple2<String, String>> relevantTypeParamPairs = newList(filter(zip(allTypeParams, allTypeParamsWithoutConstraints), new Predicate<Tuple2<String,String>>() {
                @Override
                public boolean accept(Tuple2<String,String> candidate) {
                    return rett.matches(".*[^a-zA-Z0-9_]" + Pattern.quote(candidate._2.toString()) + "[^a-zA-Z0-9_].*");
                }
            }));
            
            List<String> relevantTypeParams = newList(map(relevantTypeParamPairs, Helpers.<String>left()));
            List<String> relevantTypeParamsWithoutConstraints = newList(map(relevantTypeParamPairs, Helpers.<String>right()));

            final List<String> toReplace = newList(subtract(allTypeParamsWithoutConstraints, relevantTypeParamsWithoutConstraints));
            final List<Pattern> toReplacePatterns = newList(map(toReplace, toReplacePattern));
            Transformer<String,String> doReplace = new Transformer<String,String>() {
                @Override
                public String transform(String candidate) {
                    for (Pattern p: toReplacePatterns) {
                        candidate = p.matcher(candidate).replaceAll("$1?$3");
                    }
                    return candidate;
                }
            };
            
            boolean isPrivate = isPrivate(field);
            boolean isFinal = isFinal(field);
            boolean needsToBeFunction = !relevantTypeParams.isEmpty();
            
            String relevantTypeParamsString = isEmpty(relevantTypeParams) ? "" : "<" + mkString(", ", map(relevantTypeParams, doReplace)) + ">";
            returnType = doReplace.apply(returnType);
            String returnTypeImported = importTypes(returnType);
            
            String enclosingElementGenericQualifiedName = doReplace.apply(elementGenericQualifiedName(enclosingElement));
            String enclosingElementGenericQualifiedNameImported = importTypes(enclosingElementGenericQualifiedName);
            
            String visibility = options.makeFieldsPublic() ? "public " : resolveVisibility(field);
            String modifiers = visibility + "static final";
            String fieldName = field.getSimpleName().toString();
            
            String returnClause = "return " + (isPrivate ? "(" + (needsToBeFunction ? "Object" : returnTypeImported) + ")" : "");
            
            boolean usePredicate = returnType.equals(Boolean.class.getName()) || returnType.equals(boolean.class.getName());
            Class<?> fieldClass = usePredicate ? options.getPredicateClassForInstanceFields(isFinal) : options.getClassForInstanceFields(isFinal);
            String fieldClassImported = importType(fieldClass);
            String fundef = fieldClassImported + "<" + enclosingElementGenericQualifiedNameImported + (usePredicate ? "" : ", " + returnTypeImported) + ">";
            String privateFundef = fieldClassImported + "<" + enclosingElementQualifiedNameImported + (usePredicate ? "" : ",Object") + ">";
            String declaration = modifiers + " " + (needsToBeFunction ? relevantTypeParamsString + " ": "") + fundef + " " + fieldName;
            String privateDeclaration = "private static final " + fieldClassImported + " $" + fieldName;
            String setterFundef = importType(Function1.class) + "<" + (needsToBeFunction ? "Object" : returnTypeImported) + ",Void>";
            String implementedMethod = Predicate.class.isAssignableFrom(fieldClass) ? "boolean accept" : (needsToBeFunction ? "Object" : returnTypeImported) + " apply";

            Iterable<String> tryBlock = isPrivate
                ? Some(returnClause + "getMember().get($self);")
                : Some(returnClause + "$self." + fieldName + ";");
            
            Iterable<String> tryCatchBlock = isPrivate
                ? concat(
                    Some("try {"),
                    map(tryBlock, padding),
                    catchBlock,
                    Some("}"))
                : tryBlock;
                        
            Iterable<String> applyBlock = concat(
                Some("public final " + implementedMethod + "(final " + (needsToBeFunction ? enclosingElementQualifiedNameImported : enclosingElementGenericQualifiedNameImported) + " $self) {"),
                map(tryCatchBlock, padding),
                Some("}")
            );
            
            Iterable<String> setTryBlock = isPrivate
                    ? Some("getMember().set($self, $newValue);")
                    : Some("$self." + fieldName + " = $newValue;");
            
            Iterable<String> setTryCatchBlock = isPrivate
                    ? concat(
                        Some("try {"),
                        map(setTryBlock, padding),
                        catchBlock,
                        Some("}"))
                    : setTryBlock;
            
            Iterable<String> setBlock = concat(
                    needsToBeFunction
                        ? Some("public final " + setterFundef + " setter(final " + (needsToBeFunction ? enclosingElementQualifiedNameImported : enclosingElementGenericQualifiedNameImported) + " $self) { return new " + setterFundef + "() { public Void apply(final Object $newValue) {")
                        : Some("public final " + relevantTypeParamsString + " " + setterFundef + " setter(final " + (needsToBeFunction ? enclosingElementQualifiedNameImported : enclosingElementGenericQualifiedNameImported) + " $self) { return new " + setterFundef + "() { public Void apply(final " + returnType + " $newValue) {"),
                    map(setTryCatchBlock, padding),
                    Some("    return null;"),
                    Some("}};}")
                );
            
            String initParams = "(" + enclosingElementQualifiedNameImported + ".class, \"" + fieldName + "\")";
                        
            @SuppressWarnings("unchecked")
            Iterable<String> res = concat(
                needsToBeFunction
                    ? newList(enclosingElementQualifiedNameImported.equals(enclosingElementGenericQualifiedNameImported) ? "" : "@SuppressWarnings(\"rawtypes\")",
                              privateDeclaration + " = new " + privateFundef + initParams + " {")
                    : Some(declaration + " = new " + fundef + initParams + " {"),
                map(options.getAdditionalBodyLinesForInstanceFields(), padding),
                EmptyLine,
                isPrivate && !needsToBeFunction && (hasNonQmarkGenerics(returnType) || field.asType().getKind() == TypeKind.TYPEVAR)
                    ? Some("    @SuppressWarnings(\"unchecked\")")
                    : None,
                map(applyBlock, padding),
                isFinal ? None : map(setBlock, padding),
                Some("};"),
                needsToBeFunction
                    ? newList("@SuppressWarnings(\"unchecked\")",
                              declaration + "() { return (" + fundef + ")$" + fieldName + "; }")
                    : None,
                EmptyLine
            );
            return res;
        }
    };
}