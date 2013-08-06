package fi.solita.utils.codegen.generators;

import static fi.solita.utils.codegen.Helpers.boxed;
import static fi.solita.utils.codegen.Helpers.element2Constructors;
import static fi.solita.utils.codegen.Helpers.elementClass;
import static fi.solita.utils.codegen.Helpers.elementGenericQualifiedName;
import static fi.solita.utils.codegen.Helpers.hasRawTypes;
import static fi.solita.utils.codegen.Helpers.isPrivate;
import static fi.solita.utils.codegen.Helpers.padding;
import static fi.solita.utils.codegen.Helpers.parameterTypesAsClasses;
import static fi.solita.utils.codegen.Helpers.paramsWithCast;
import static fi.solita.utils.codegen.Helpers.publicElement;
import static fi.solita.utils.codegen.Helpers.qualifiedName;
import static fi.solita.utils.codegen.Helpers.relevantTypeParams;
import static fi.solita.utils.codegen.Helpers.resolveVisibility;
import static fi.solita.utils.codegen.Helpers.simpleName;
import static fi.solita.utils.codegen.Helpers.throwsCheckedExceptions;
import static fi.solita.utils.codegen.Helpers.typeParameter2String;
import static fi.solita.utils.codegen.generators.Content.EmptyLine;
import static fi.solita.utils.codegen.generators.Content.None;
import static fi.solita.utils.codegen.generators.Content.catchBlock;
import static fi.solita.utils.codegen.generators.Content.memberAccessor;
import static fi.solita.utils.codegen.generators.Content.memberInitializer;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.flatten;
import static fi.solita.utils.functional.Functional.isEmpty;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.Functional.zip;
import static fi.solita.utils.functional.Functional.zipWithIndex;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Transformers.mkString;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

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
            return newList();
        }
        
        Iterable<ExecutableElement> elements = element2Constructors.apply(source);
        if (options.onlyPublicMembers()) {
            elements = filter(elements, publicElement);
        }
        
        Function1<Entry<Integer, ExecutableElement>, Iterable<String>> singleElementTransformer = constructorGen.ap(processingEnv, options);
        Iterable<Iterable<String>> rm = newList(map(newList(zipWithIndex(elements)), singleElementTransformer));
        return flatten(rm);
    }
    
    public static boolean needsToBeFunction(ExecutableElement constructor) {
        return !isEmpty(relevantTypeParams(constructor));
    }
    
    public static Function3<ProcessingEnvironment, Options, Map.Entry<Integer, ExecutableElement>, Iterable<String>> constructorGen = new Function3<ProcessingEnvironment, Options, Map.Entry<Integer, ExecutableElement>, Iterable<String>>() {
        @Override
        public Iterable<String> apply(ProcessingEnvironment processingEnv, Options options, Map.Entry<Integer, ExecutableElement> entry) {
            ExecutableElement constructor = entry.getValue();
            TypeElement enclosingElement = (TypeElement) constructor.getEnclosingElement();
            int index = entry.getKey();

            Iterable<String> relevantTypeParams = map(relevantTypeParams(constructor), typeParameter2String);
            String relevantTypeParamsString = isEmpty(relevantTypeParams) ? "" : "<" + mkString(", ", relevantTypeParams) + ">";
            
            boolean needsToBeFunction = needsToBeFunction(constructor);
            boolean isPrivate = isPrivate(constructor);
            boolean throwsChecked = throwsCheckedExceptions(constructor, processingEnv);
            boolean hasRawTypes = hasRawTypes(constructor);
            
            String returnType = elementGenericQualifiedName(enclosingElement);
            
            int argCount = constructor.getParameters().size();
            List<String> argTypes = newList(map(constructor.getParameters(), qualifiedName.andThen(boxed)));
            List<String> argNames = newList(map(constructor.getParameters(), simpleName));
            List<String> argNamesWithCast = newList(paramsWithCast(constructor, isPrivate));

            String fieldName = "$" + (index == 0 ? "" : index);
            String constructorClass = options.getClassForConstructors(argCount).getName().replace('$', '.');
            String fundef = constructorClass + "<" + mkString(", ", concat(argTypes, newList(returnType))) + ">";
            String declaration = resolveVisibility(constructor) + " static final " + relevantTypeParamsString + " " + fundef + " " + fieldName;
            
            Iterable<String> tryBlock = isPrivate 
                    ? Some("return (" + returnType + ")$getMember().newInstance(" + mkString(", ", argNamesWithCast) + ");")
                    : Some("return new " + returnType + "(" + mkString(", ", argNamesWithCast) + ");");
            
            Iterable<String> tryCatchBlock = isPrivate || throwsChecked
                ? concat(
                    Some("try {"),
                    map(tryBlock, padding),
                    catchBlock,
                    Some("}"))
                : tryBlock;
                    
            Iterable<String> applyBlock = concat(
                Some("public " + returnType + " apply(" + mkString(", ", map(zip(argTypes, argNames), mkString(" "))) + ") {"),
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
                    ? concat(map(memberInitializer(returnType, null, elementClass(constructor), parameterTypesAsClasses(constructor)), padding),
                             EmptyLine)
                    : None,
                options.generateMemberAccessorForConstructors()
                    ? concat(map(memberAccessor(returnType, elementClass(constructor)), padding),
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