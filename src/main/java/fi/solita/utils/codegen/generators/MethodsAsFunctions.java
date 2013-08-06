package fi.solita.utils.codegen.generators;

import static fi.solita.utils.codegen.Helpers.allTypeParams;
import static fi.solita.utils.codegen.Helpers.boxed;
import static fi.solita.utils.codegen.Helpers.element2Fields;
import static fi.solita.utils.codegen.Helpers.element2Methods;
import static fi.solita.utils.codegen.Helpers.elementGenericQualifiedName;
import static fi.solita.utils.codegen.Helpers.hasNonQmarkGenerics;
import static fi.solita.utils.codegen.Helpers.hasRawTypes;
import static fi.solita.utils.codegen.Helpers.hasTypeParameters;
import static fi.solita.utils.codegen.Helpers.isInstanceMethod;
import static fi.solita.utils.codegen.Helpers.isPrivate;
import static fi.solita.utils.codegen.Helpers.parameterTypesAsClasses;
import static fi.solita.utils.codegen.Helpers.paramsWithCast;
import static fi.solita.utils.codegen.Helpers.publicElement;
import static fi.solita.utils.codegen.Helpers.qualifiedName;
import static fi.solita.utils.codegen.Helpers.relevantTypeParams;
import static fi.solita.utils.codegen.Helpers.resolveBoxedGenericType;
import static fi.solita.utils.codegen.Helpers.resolveVisibility;
import static fi.solita.utils.codegen.Helpers.returnsVoid;
import static fi.solita.utils.codegen.Helpers.simpleName;
import static fi.solita.utils.codegen.Helpers.throwsCheckedExceptions;
import static fi.solita.utils.codegen.Helpers.typeParameter2String;
import static fi.solita.utils.codegen.Helpers.typeVariableReplacer;
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
import static fi.solita.utils.functional.Functional.contains;
import static fi.solita.utils.functional.Functional.exists;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.flatMap;
import static fi.solita.utils.functional.Functional.flatten;
import static fi.solita.utils.functional.Functional.groupBy;
import static fi.solita.utils.functional.Functional.intersection;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.Functional.repeat;
import static fi.solita.utils.functional.Functional.subtract;
import static fi.solita.utils.functional.Functional.zip;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Transformers.mkString;
import static fi.solita.utils.functional.Transformers.prepend;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.TypeParameterElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;

import fi.solita.utils.codegen.Helpers;
import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Function1;
import fi.solita.utils.functional.Function3;
import fi.solita.utils.functional.Functional;
import fi.solita.utils.functional.Predicate;
import fi.solita.utils.functional.Transformer;
import fi.solita.utils.functional.Tuple2;

public class MethodsAsFunctions extends Generator<MethodsAsFunctions.Options> {

    @SuppressWarnings("rawtypes")
    public static interface Options extends GeneratorOptions {
        Class<? extends Apply> getClassForMethods(int argCount);
        Class<? extends Apply> getPredicateClassForMethods();
        List<String> getAdditionalBodyLinesForMethods(ExecutableElement element);
        boolean generateMemberInitializerForMethods();
        boolean generateMemberAccessorForMethods();
        boolean generateMemberNameAccessorForMethods();
        boolean onlyPublicMembers();
    }
    
    public static final MethodsAsFunctions instance = new MethodsAsFunctions();
    
    @Override
    public Iterable<String> apply(ProcessingEnvironment processingEnv, Options options, TypeElement source) {
        Iterable<ExecutableElement> elements = element2Methods.apply(source);
        if (options.onlyPublicMembers()) {
            elements = filter(elements, publicElement);
        }
      
        Iterable<List<ExecutableElement>> elementsByName = groupBy(elements, simpleName).values();
        Function1<Entry<Integer, ExecutableElement>, Iterable<String>> singleElementTransformer = executableElementGen.ap(processingEnv, options);
        
        Iterable<Tuple2<Integer, ExecutableElement>> fm = newList(flatMap(elementsByName, zipWithIndex));
        Iterable<Iterable<String>> rm = newList(map(fm, singleElementTransformer));
        return flatten(rm);
    }
    
    public static Function3<ProcessingEnvironment, Options, Map.Entry<Integer, ExecutableElement>, Iterable<String>> executableElementGen = new Function3<ProcessingEnvironment, Options, Map.Entry<Integer, ExecutableElement>, Iterable<String>>() {
        @Override
        public Iterable<String> apply(ProcessingEnvironment processingEnv, Options options, Map.Entry<Integer, ExecutableElement> entry) {
            ExecutableElement method = entry.getValue();
            TypeElement enclosingElement = (TypeElement) method.getEnclosingElement();
            String enclosingElementQualifiedName = qualifiedName.apply(enclosingElement);
            
            int index = entry.getKey() + (contains(map(element2Fields.apply(enclosingElement), simpleName), simpleName.apply(method)) ? 1 : 0);
            
            List<? extends TypeParameterElement> allTypeParamsForMethod = newList(allTypeParams(method));
            List<? extends TypeParameterElement> relevantTypeParamsForMethod = newList(relevantTypeParams(method));
            final List<String> relevantTypeParams = newList(map(relevantTypeParamsForMethod, typeParameter2String));
            final Set<String> relevantTypeParamsWithoutConstraints = newSet(map(relevantTypeParamsForMethod, simpleName));
            final List<String> allTypeParamsWithoutConstraints = newList(map(allTypeParamsForMethod, simpleName));
            List<? extends VariableElement> methodParameters = method.getParameters();
            
            Transformer<String, String> doReplace = typeVariableReplacer(newList(subtract(allTypeParamsWithoutConstraints, relevantTypeParamsWithoutConstraints)));
            
            String modifiers = resolveVisibility(method) + " static final";
            String methodName = method.getSimpleName().toString();
            String returnType = doReplace.transform(resolveBoxedGenericType(method.getReturnType()));
            List<String> argumentTypes = newList(map(methodParameters, qualifiedName.andThen(boxed)));
            List<? extends TypeParameterElement> enclosingElementTypeParameters = enclosingElement.getTypeParameters();
            
            boolean needsToBeFunction = !relevantTypeParams.isEmpty();
            final boolean isPrivate = isPrivate(method);
            boolean isInstanceMethod = isInstanceMethod(method);
            boolean zeroArgInstanceMethod = isInstanceMethod && methodParameters.isEmpty(); // handle no-arg methods like static functions
            boolean handleAsInstanceMethod = isInstanceMethod && !zeroArgInstanceMethod;
            boolean returnsVoid = returnsVoid(method);
            Set<String> returnAndArgumentTypes = newSet(cons(returnType, argumentTypes));
            boolean needsTypeArguments = !intersection(relevantTypeParamsWithoutConstraints, returnAndArgumentTypes).isEmpty() || exists(returnAndArgumentTypes, hasTypeParameters);
            
            boolean throwsChecked = throwsCheckedExceptions(method, processingEnv);
            boolean hasRawTypes = hasRawTypes(method);
            
            int argCount = zeroArgInstanceMethod ? 1 : methodParameters.size(); // no-arg instance methods need $self parameter
            
            String instanceName = isInstanceMethod ? "$self" : enclosingElementQualifiedName;
            
            String enclosingElementGenericQualifiedName = doReplace.transform(needsTypeArguments
                    ? elementGenericQualifiedName(enclosingElement)
                    : enclosingElementQualifiedName + (enclosingElementTypeParameters.isEmpty() ? "" : "<" + mkString(", ", repeat("?", enclosingElementTypeParameters.size())) + ">"));
            
            argumentTypes = newList(map(argumentTypes, doReplace));
            String relevantTypeParamsString = relevantTypeParams.isEmpty() ? "" : "<" + mkString(", ", map(relevantTypeParams, doReplace)) + ">";
            
            List<String> argTypes = zeroArgInstanceMethod ? newList(enclosingElementGenericQualifiedName) : argumentTypes;
            Iterable<String> argNames = zeroArgInstanceMethod ? newList("$self") : map(methodParameters, simpleName);
            List<String> argNamesWithCast = newList(paramsWithCast(method, isPrivate));
            
            String returnClause = returnsVoid ? "" : "return " + (isPrivate ? "(" + returnType + ")" : "");

            Class<?> methodClass = !handleAsInstanceMethod && (returnType.equals(Boolean.class.getName()) || returnType.equals(boolean.class.getName())) && argCount == 1 ? options.getPredicateClassForMethods() : options.getClassForMethods(argCount);
            String fundef = methodClass.getName().replace('$', '.') + "<" + mkString(", ", concat(argTypes, newList(returnType))) + "> ";
            String outerFundef = handleAsInstanceMethod ? Function1.class.getName() + "<" + enclosingElementGenericQualifiedName + ", " + fundef + ">" : fundef;
            
            String declaration = modifiers + " " + relevantTypeParamsString + " " + outerFundef + " " + methodName + (index == 0 ? "" : index);
            
            String implementedMethod = Predicate.class.isAssignableFrom(methodClass) ? "boolean accept" : returnType + " apply";
            Iterable<String> tryBlock = concat(
                isPrivate
                    ? Some(returnClause + "$getMember().invoke(" + mkString(", ", cons(isInstanceMethod ? "$self" : "null", argNamesWithCast)) + ");")
                    : Some(returnClause + instanceName + "." + (method.getTypeParameters().isEmpty() ? "" : "<" + mkString(", ", map(method.getTypeParameters(), simpleName)) + ">") + methodName + "(" + mkString(", ", argNamesWithCast) + ");"),
                returnsVoid
                    ? Some("return null;")
                    : None
            );
            Iterable<String> tryCatchBlock = isPrivate || throwsChecked
                ? concat(
                    Some("try {"),
                    map(tryBlock, Helpers.padding),
                    catchBlock,
                    Some("}"))
                : tryBlock;
            Iterable<String> applyBlock = concat(
                Some("    public final " + implementedMethod + "(" + mkString(", ", map(zip(map(argTypes, prepend("final ")), argNames), mkString(" "))) + ") { "),
                map(tryCatchBlock, Helpers.padding),
                Some("    }")
            );
            @SuppressWarnings("unchecked")
            Iterable<String> contentBlock = concat(
                map(applyBlock, Helpers.padding),
                options.generateMemberInitializerForMethods() || isPrivate
                    ? concat(map(memberInitializer(enclosingElementGenericQualifiedName, methodName, Method.class, parameterTypesAsClasses(method)), Helpers.padding),
                             EmptyLine)
                    : None,
                options.generateMemberAccessorForMethods()
                    ? concat(map(memberAccessor(enclosingElementGenericQualifiedName, Method.class), Helpers.padding),
                             EmptyLine)
                    : None,
                options.generateMemberNameAccessorForMethods()
                    ? concat(map(memberNameAccessor(methodName), Helpers.padding),
                             EmptyLine)
                    : None,
                map(options.getAdditionalBodyLinesForMethods(method), Helpers.padding)
            );
            @SuppressWarnings("unchecked")
            Iterable<String> res = concat(
                hasRawTypes
                    ? Some("@SuppressWarnings(\"rawtypes\")")
                    : None,
                Some(declaration + (needsToBeFunction ? "() { return" : " =") + " new " + outerFundef + "() {"),
                isPrivate && (method.getReturnType().getKind() == TypeKind.TYPEVAR || hasNonQmarkGenerics(returnType))
                    ? Some("    @SuppressWarnings(\"unchecked\")")
                    : None,
                handleAsInstanceMethod
                    ? withOuter(contentBlock, fundef, enclosingElementGenericQualifiedName)
                    : contentBlock,
                Some("};"),
                needsToBeFunction
                    ? Some("}")
                    : None,
                EmptyLine
            );
            return res;
        }
    };
    
    private static final Iterable<String> withOuter(Iterable<String> inner, String fundef, String enclosingElementGenericQualifiedName) {
        return concat(
            Some("public " + fundef + " apply(final " + enclosingElementGenericQualifiedName + " $self) {"),
            Some("return new " + fundef + "() {"),
            inner,
            Some("};}")
        );
    }
    
    public static final Function1<Iterable<ExecutableElement>, Iterable<Tuple2<Integer,ExecutableElement>>> zipWithIndex = new Function1<Iterable<ExecutableElement>, Iterable<Tuple2<Integer,ExecutableElement>>>() {
        @Override
        public Iterable<Tuple2<Integer, ExecutableElement>> apply(Iterable<ExecutableElement> t) {
            return Functional.zipWithIndex(t);
        }
    };
}