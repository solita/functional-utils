package fi.solita.utils.codegen.generators;

import static fi.solita.utils.codegen.Helpers.boxedQualifiedName;
import static fi.solita.utils.codegen.Helpers.element2Constructors;
import static fi.solita.utils.codegen.Helpers.elementGenericQualifiedName;
import static fi.solita.utils.codegen.Helpers.hasRawTypes;
import static fi.solita.utils.codegen.Helpers.importType;
import static fi.solita.utils.codegen.Helpers.importTypes;
import static fi.solita.utils.codegen.Helpers.isPrivate;
import static fi.solita.utils.codegen.Helpers.joinWithSpace;
import static fi.solita.utils.codegen.Helpers.padding;
import static fi.solita.utils.codegen.Helpers.parameterTypesAsClasses;
import static fi.solita.utils.codegen.Helpers.paramsWithCast;
import static fi.solita.utils.codegen.Helpers.publicElement;
import static fi.solita.utils.codegen.Helpers.relevantTypeParams;
import static fi.solita.utils.codegen.Helpers.resolveVisibility;
import static fi.solita.utils.codegen.Helpers.simpleName;
import static fi.solita.utils.codegen.Helpers.typeParameter2String;
import static fi.solita.utils.codegen.generators.Content.EmptyLine;
import static fi.solita.utils.codegen.generators.Content.None;
import static fi.solita.utils.codegen.generators.Content.catchBlock;
import static fi.solita.utils.codegen.generators.Content.memberAccessor;
import static fi.solita.utils.codegen.generators.Content.memberInitializer;
import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.flatten;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.Functional.zip;
import static fi.solita.utils.functional.Functional.zipWithIndex;
import static fi.solita.utils.functional.Option.Some;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;

import fi.solita.utils.codegen.Helpers;
import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Function1;
import fi.solita.utils.functional.Function3;

public class ConstructorsAsFunctions extends Generator<ConstructorsAsFunctions.Options> {
    
    public static interface Options extends GeneratorOptions {
        @SuppressWarnings("rawtypes")
        Class<? extends Apply> getClassForConstructors(int argCount);
        List<String> getAdditionalBodyLinesForConstructors(ExecutableElement element);
        boolean generateMemberInitializerForConstructors();
        boolean generateMemberAccessorForConstructors();
        boolean onlyPublicMembers();
    }
    
    public static ConstructorsAsFunctions instance = new ConstructorsAsFunctions();
    
    @Override
    public Iterable<String> apply(ProcessingEnvironment processingEnv, Options options, TypeElement source) {
        if (source.getModifiers().contains(Modifier.ABSTRACT)) {
            return emptyList();
        }

        Iterable<ExecutableElement> elements = element2Constructors.apply(source);
        if (options.onlyPublicMembers()) {
            elements = filter(elements, publicElement);
        }
        
        Function1<Entry<Integer, ExecutableElement>, Iterable<String>> singleElementTransformer = constructorGen.ap(new Helpers.EnvDependent(processingEnv), options);
        Iterable<Iterable<String>> rm = newList(map(newList(zipWithIndex(elements)), singleElementTransformer));
        return flatten(rm);
    }
    
    public static Function3<Helpers.EnvDependent, Options, Map.Entry<Integer, ExecutableElement>, Iterable<String>> constructorGen = new Function3<Helpers.EnvDependent, Options, Map.Entry<Integer, ExecutableElement>, Iterable<String>>() {
        @Override
        public Iterable<String> apply(Helpers.EnvDependent helper, Options options, Map.Entry<Integer, ExecutableElement> entry) {
            ExecutableElement constructor = entry.getValue();
            TypeElement enclosingElement = (TypeElement) constructor.getEnclosingElement();
            int index = entry.getKey();

            List<? extends TypeParameterElement> relevantTypeParams = newList(relevantTypeParams(constructor));
            String relevantTypeParamsString = relevantTypeParams.isEmpty() ? "" : "<" + importTypes(mkString(", ", map(relevantTypeParams, typeParameter2String))) + ">";
            List<? extends VariableElement> parameters = constructor.getParameters();
            boolean needsToBeFunction = !relevantTypeParams.isEmpty();
            
            boolean isPrivate = isPrivate(constructor);
            boolean throwsChecked = helper.throwsCheckedExceptions(constructor);
            boolean hasRawTypes = hasRawTypes(constructor);
            
            String returnType = elementGenericQualifiedName(enclosingElement);
            String returnTypeImported = importTypes(elementGenericQualifiedName(enclosingElement));
            
            int argCount = constructor.getParameters().size();
            List<String> argTypes = newList(map(parameters, boxedQualifiedName));
            List<String> argNames = newList(map(parameters, simpleName));
            List<String> argNamesWithCast = newList(paramsWithCast(parameters, isPrivate));

            String fieldName = "$" + (index == 0 ? "" : index);
            Class<?> constructorClass = options.getClassForConstructors(argCount);
            String fundef = importType(constructorClass) + "<" + importTypes(mkString(", ", concat(argTypes, newList(returnType)))) + ">";
            String declaration = resolveVisibility(constructor) + "static final " + relevantTypeParamsString + " " + fundef + " " + fieldName;
            
            Iterable<String> tryBlock = isPrivate 
                    ? Some("return (" + returnTypeImported + ")$getMember().newInstance(" + mkString(", ", argNamesWithCast) + ");")
                    : Some("return new " + returnTypeImported + "(" + mkString(", ", argNamesWithCast) + ");");
            
            Iterable<String> tryCatchBlock = isPrivate || throwsChecked
                ? concat(
                    Some("try {"),
                    map(tryBlock, padding),
                    catchBlock,
                    Some("}"))
                : tryBlock;
                    
            Iterable<String> applyBlock = concat(
                Some("public " + returnTypeImported + " apply(" + mkString(", ", map(zip(argTypes, argNames), joinWithSpace)) + ") {"),
                map(tryCatchBlock, padding),
                Some("}")
            );
            
            Iterable<String> toString = newList(
                "@Override",
                "public String toString() {",
                "    return \"" + fieldName + "\";",
                "}"
            );
                
            @SuppressWarnings("unchecked")
            Iterable<String> res = concat(
                hasRawTypes
                    ? Some("@SuppressWarnings(\"rawtypes\")")
                    : None,
                Some(declaration + (needsToBeFunction ? "() { return" : " =") + " new " + fundef + "() {"),
                map(applyBlock, padding),
                EmptyLine,
                isPrivate || options.generateMemberInitializerForConstructors()
                    ? concat(map(memberInitializer(returnType, null, Constructor.class, parameterTypesAsClasses(constructor, relevantTypeParams)), padding),
                             EmptyLine)
                    : None,
                options.generateMemberAccessorForConstructors()
                    ? concat(map(memberAccessor(returnType, Constructor.class), padding),
                             EmptyLine)
                    : None,
                map(options.getAdditionalBodyLinesForConstructors(constructor), padding),
                map(toString, padding),
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