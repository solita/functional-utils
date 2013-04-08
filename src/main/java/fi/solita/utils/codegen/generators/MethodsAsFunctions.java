package fi.solita.utils.codegen.generators;

import static fi.solita.utils.codegen.Helpers.boxed;
import static fi.solita.utils.codegen.Helpers.element2Fields;
import static fi.solita.utils.codegen.Helpers.element2Methods;
import static fi.solita.utils.codegen.Helpers.elementClass;
import static fi.solita.utils.codegen.Helpers.elementGenericQualifiedName;
import static fi.solita.utils.codegen.Helpers.hasNonQmarkGenerics;
import static fi.solita.utils.codegen.Helpers.hasRawTypes;
import static fi.solita.utils.codegen.Helpers.hasTypeParameters;
import static fi.solita.utils.codegen.Helpers.isInstanceMethod;
import static fi.solita.utils.codegen.Helpers.isPrivate;
import static fi.solita.utils.codegen.Helpers.parameterTypesAsClasses;
import static fi.solita.utils.codegen.Helpers.paramsWithCast;
import static fi.solita.utils.codegen.Helpers.qualifiedName;
import static fi.solita.utils.codegen.Helpers.relevantTypeParams;
import static fi.solita.utils.codegen.Helpers.resolveBoxedGenericType;
import static fi.solita.utils.codegen.Helpers.resolveVisibility;
import static fi.solita.utils.codegen.Helpers.returnsVoid;
import static fi.solita.utils.codegen.Helpers.simpleName;
import static fi.solita.utils.codegen.Helpers.throwsCheckedExceptions;
import static fi.solita.utils.codegen.Helpers.typeParameter2String;
import static fi.solita.utils.codegen.generators.Content.EmptyLine;
import static fi.solita.utils.codegen.generators.Content.None;
import static fi.solita.utils.codegen.generators.Content.catchBlock;
import static fi.solita.utils.codegen.generators.Content.memberAccessor;
import static fi.solita.utils.codegen.generators.Content.memberInitializer;
import static fi.solita.utils.codegen.generators.Content.memberNameAccessor;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.cons;
import static fi.solita.utils.functional.Functional.exists;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.flatMap;
import static fi.solita.utils.functional.Functional.groupBy;
import static fi.solita.utils.functional.Functional.intersection;
import static fi.solita.utils.functional.Functional.isEmpty;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.Functional.repeat;
import static fi.solita.utils.functional.Functional.zip;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Transformers.mkString;
import static fi.solita.utils.functional.Transformers.prepend;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;

import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Function0;
import fi.solita.utils.functional.Function1;
import fi.solita.utils.functional.Function3;
import fi.solita.utils.functional.Functional;
import fi.solita.utils.functional.Predicate;
import fi.solita.utils.functional.Tuple2;

public class MethodsAsFunctions extends Function3<ProcessingEnvironment, MethodsAsFunctions.Options, TypeElement, Iterable<String>> {

    @SuppressWarnings("rawtypes")
    public static interface Options {
        Class<? extends Apply> getClassForMethods(int argCount);
        Class<? extends Apply> getPredicateClassForMethods();
        List<String> getAdditionalBodyLinesForMethods(ExecutableElement element);
        boolean generateMemberInitializerForMethods();
        boolean generateMemberAccessorForMethods();
        boolean generateMemberNameAccessorForMethods();
        boolean onlyPublicMembers();
    }
    
    public static Function1<ProcessingEnvironment, Function1<Options, Function1<TypeElement, Iterable<String>>>> instance = new MethodsAsFunctions().curried();
    
    @Override
    public Iterable<String> apply(ProcessingEnvironment processingEnv, Options options, TypeElement source) {
        Iterable<ExecutableElement> elements = element2Methods.apply(source);
        if (options.onlyPublicMembers()) {
            elements = filter(elements, new Predicate<Element>() {
                @Override
                public boolean accept(Element candidate) {
                    return candidate.getModifiers().contains(Modifier.PUBLIC);
                }
            });
        }
      
        Iterable<List<ExecutableElement>> elementsByName = groupBy(elements, simpleName).values();
        Function1<Entry<Integer, ExecutableElement>, Iterable<String>> singleElementTransformer = executableElementGen.curried().apply(processingEnv).apply(options);
        
        return flatMap(flatMap(elementsByName, zipWithIndex), singleElementTransformer);
    }
    
    public static Function3<ProcessingEnvironment, Options, Map.Entry<Integer, ExecutableElement>, Iterable<String>> executableElementGen = new Function3<ProcessingEnvironment, Options, Map.Entry<Integer, ExecutableElement>, Iterable<String>>() {
        @Override
        public Iterable<String> apply(ProcessingEnvironment processingEnv, Options options, Map.Entry<Integer, ExecutableElement> entry) {
            ExecutableElement method = entry.getValue();
            TypeElement enclosingElement = (TypeElement) method.getEnclosingElement();
            
            List<String> fieldNames = newList(map(element2Fields.apply(enclosingElement), simpleName));
            
            int index = entry.getKey() + (fieldNames.contains(simpleName.apply(method)) ? 1 : 0);
            
            List<String> relevantTypeParams = newList(map(relevantTypeParams(method), typeParameter2String));
            List<String> relevantTypeParamsWithoutConstraints = newList(map(relevantTypeParams(method), simpleName));
            String relevantTypeParamsString = relevantTypeParams.isEmpty() ? "" : "<" + mkString(", ", relevantTypeParams) + ">";
            String methodTypeParamsWithoutConstraintsString = method.getTypeParameters().isEmpty() ? "" : "<" + mkString(", ", map(method.getTypeParameters(), simpleName)) + ">";
            
            String modifiers = resolveVisibility(method) + " static final";
            String methodName = method.getSimpleName().toString();
            String returnType = resolveBoxedGenericType(method.getReturnType());
            List<String> argumentTypes = newList(map(method.getParameters(), qualifiedName.andThen(boxed)));
            
            boolean needsToBeFunction = !isEmpty(relevantTypeParams);
            final boolean isPrivate = isPrivate(method);
            boolean isInstanceMethod = isInstanceMethod(method);
            boolean zeroArgInstanceMethod = isInstanceMethod && method.getParameters().isEmpty(); // handle no-arg methods like static functions
            boolean handleAsInstanceMethod = isInstanceMethod && !zeroArgInstanceMethod;
            boolean returnsVoid = returnsVoid(method);
            boolean needsTypeArguments = !intersection(newSet(relevantTypeParamsWithoutConstraints), newSet(cons(returnType, argumentTypes))).isEmpty() || exists(cons(returnType, argumentTypes), hasTypeParameters);
            
            boolean throwsChecked = throwsCheckedExceptions(method, processingEnv);
            boolean hasRawTypes = hasRawTypes(method);
            
            int argCount = zeroArgInstanceMethod ? 1 : method.getParameters().size(); // no-arg instance methods need $self parameter
            
            String instanceName = isInstanceMethod ? "$self" : qualifiedName.apply(enclosingElement);
            
            String enclosingElementGenericQualifiedName = needsTypeArguments
                    ? elementGenericQualifiedName(enclosingElement)
                    : qualifiedName.apply(enclosingElement) + (enclosingElement.getTypeParameters().isEmpty() ? "" : "<" + mkString(", ", repeat("?", enclosingElement.getTypeParameters().size())) + ">");
                    
            List<String> argTypes = zeroArgInstanceMethod ? newList(enclosingElementGenericQualifiedName) : argumentTypes;
            List<String> argNames = zeroArgInstanceMethod ? newList("$self")                              : newList(map(method.getParameters(), simpleName));
            List<String> argNamesWithCast = newList(paramsWithCast(method, isPrivate));
            
            String returnClause = returnsVoid ? "" : "return " + (isPrivate ? "(" + returnType + ")" : "");

            Class<?> fieldClass = !handleAsInstanceMethod && returnType.equals(Boolean.class.getName()) && argCount == 1 ? options.getPredicateClassForMethods() : options.getClassForMethods(argCount);
            String fundef = (Function0.class.getPackage().getName() + ".Function" + argCount) + "<" + mkString(", ", concat(argTypes, newList(returnType))) + ">";
            String instanceMethodFundef = fieldClass.getName().replace('$', '.') + "<" + enclosingElementGenericQualifiedName + ", " + fundef + "> ";
            String declaration = modifiers + " " + relevantTypeParamsString + " " + (handleAsInstanceMethod ? instanceMethodFundef : fundef) + " " + methodName + (index == 0 ? "" : index);
            
            String fundefPrefix = !handleAsInstanceMethod ? "" : instanceMethodFundef + "() { protected " + fundef + " $do(final " + enclosingElementGenericQualifiedName + " $self) { return new ";
            
            Iterable<String> tryBlock = concat(
                isPrivate
                    ? Some(returnClause + "$getMember().invoke(" + mkString(", ", cons(isInstanceMethod ? "$self" : "null", argNamesWithCast)) + ");")
                    : Some(returnClause + instanceName + "." + methodTypeParamsWithoutConstraintsString + methodName + "(" + mkString(", ", argNamesWithCast) + ");"),
                returnsVoid
                    ? Some("return null;")
                    : None
            );
                
            Iterable<String> tryCatchBlock = isPrivate || throwsChecked
                ? concat(
                    Some("try {"),
                    map(tryBlock, prepend("    ")),
                    catchBlock,
                    Some("}"))
                : tryBlock;
                    
            Iterable<String> applyBlock = concat(
                Some("public " + returnType + " apply(" + mkString(", ", map(zip(argTypes, argNames), mkString(" "))) + ") {"),
                map(tryCatchBlock, prepend("    ")),
                Some("}")
            );
            
            @SuppressWarnings("unchecked")
            Iterable<String> res = concat(
                hasRawTypes
                    ? Some("@SuppressWarnings(\"rawtypes\")")
                    : None,
                Some(declaration + (needsToBeFunction ? "() { return" : " =") + " new " + fundefPrefix + fundef + "() {"),
                isPrivate && (hasNonQmarkGenerics(returnType) || method.getReturnType().getKind() == TypeKind.TYPEVAR)
                    ? Some("    @SuppressWarnings(\"unchecked\")")
                    : None,
                map(applyBlock, prepend("    ")),
                handleAsInstanceMethod
                    ? Some("    };}")
                    : None,
                handleAsInstanceMethod && options.generateMemberInitializerForMethods() || isPrivate
                    ? concat(map(memberInitializer(enclosingElementGenericQualifiedName, methodName, elementClass(method), parameterTypesAsClasses(method)), prepend("    ")),
                             EmptyLine)
                    : None,
                handleAsInstanceMethod && options.generateMemberAccessorForMethods()
                    ? concat(map(memberAccessor(enclosingElementGenericQualifiedName, elementClass(method)), prepend("    ")),
                             EmptyLine)
                    : None,
                handleAsInstanceMethod && options.generateMemberNameAccessorForMethods()
                    ? concat(map(memberNameAccessor(methodName), prepend("    ")),
                             EmptyLine)
                    : None,
                map(options.getAdditionalBodyLinesForMethods(method), prepend("    ")),
                Some("};"),
                needsToBeFunction
                    ? Some("}")
                    : None,
                EmptyLine
            );
            return res;
        }
    };
    
    public static Function1<Iterable<ExecutableElement>, Iterable<Tuple2<Integer,ExecutableElement>>> zipWithIndex = new Function1<Iterable<ExecutableElement>, Iterable<Tuple2<Integer,ExecutableElement>>>() {
        @Override
        public Iterable<Tuple2<Integer, ExecutableElement>> apply(Iterable<ExecutableElement> t) {
            return Functional.zipWithIndex(t);
        }
    };
}