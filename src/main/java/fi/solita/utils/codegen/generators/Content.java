package fi.solita.utils.codegen.generators;

import static fi.solita.utils.codegen.Helpers.element2NestedClasses;
import static fi.solita.utils.codegen.Helpers.importType;
import static fi.solita.utils.codegen.Helpers.importTypes;
import static fi.solita.utils.codegen.Helpers.isPrimitive;
import static fi.solita.utils.codegen.Helpers.isPrivate;
import static fi.solita.utils.codegen.Helpers.padding;
import static fi.solita.utils.codegen.Helpers.publicElement;
import static fi.solita.utils.codegen.Helpers.removeGenericPart;
import static fi.solita.utils.codegen.Helpers.resolveVisibility;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.cons;
import static fi.solita.utils.functional.Functional.isEmpty;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.Functional.repeat;
import static fi.solita.utils.functional.Functional.transpose;
import static fi.solita.utils.functional.FunctionalA.concat;
import static fi.solita.utils.functional.FunctionalA.filter;
import static fi.solita.utils.functional.FunctionalImpl.flatMap;
import static fi.solita.utils.functional.FunctionalImpl.map;
import static fi.solita.utils.functional.FunctionalImpl.sequence;
import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Predicates.equalTo;
import static fi.solita.utils.functional.Predicates.not;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import fi.solita.utils.codegen.CommonMetadataProcessor;
import fi.solita.utils.codegen.Helpers;
import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Collections;
import fi.solita.utils.functional.Function5;
import fi.solita.utils.functional.Pair;
import fi.solita.utils.functional.Predicate;
import fi.solita.utils.functional.Transformer;

public abstract class Content {

    public static final Iterable<String> None = None();
    public static final Iterable<String> EmptyLine = Some("");
    
    public static final List<String> memberNameAccessor(String name) {
        return newList(
            "public " + importType(String.class) + " getName() {",
            "  return \"" + name + "\";",
            "}"
        );
    }
    
    private static final Pattern arrayClass = Pattern.compile("(\\[\\])*"); 
    private static final Pattern typeArgArray = Pattern.compile("[^.\\[\\]]+((\\[\\])+)");
    
    public static final Iterable<String> reflectionInvokationArgs(Iterable<String> argumentClasses) {
        return map(argumentClasses, new Transformer<String,String>() {
            @Override
            public String transform(String source) {
                if (isPrimitive(arrayClass.matcher(source).replaceAll(""))) {
                    // just a primitive
                    return source + ".class";
                } else {
                    Matcher m = typeArgArray.matcher(source);
                    if (m.matches()) {
                        return importType(Object.class) + "[].class";
                    }
                    return importTypes(source) + ".class";
                }
            }
        });
        
    }
    
    public static final Iterable<String> memberInitializer(String classNameGeneric, String name, Class<? extends Member> reflectInvokationClass, Iterable<String> argumentClasses) {
        Iterable<String> reflectionInvokationArgs = reflectionInvokationArgs(argumentClasses);
        boolean isConstructor = Constructor.class.isAssignableFrom(reflectInvokationClass);
        String declr = isConstructor ? "getDeclaredConstructor(" + mkString(", ", reflectionInvokationArgs) + ")" :
                       Method.class.isAssignableFrom(reflectInvokationClass) ?      "getDeclaredMethod(" + mkString(", ", cons('"' + name + '"', reflectionInvokationArgs)) + ")" :
                       Field.class.isAssignableFrom(reflectInvokationClass) ?       "getDeclaredField(\"" + name + "\")" : "";
        String clsName = importType(reflectInvokationClass) + (isConstructor ? "<" + importTypes(classNameGeneric) + ">" : "");
        return concat(
            newList(
                "private transient " + clsName + " $r;",
                "",
                isConstructor ? "@SuppressWarnings(\"unchecked\")" : "",
                "private " + clsName + " $getMember() {",
                "  if ($r == null) {",
                "    try {",
                "      $r = (" + clsName + ")(" + importType(Object.class) + ")" + importTypes(removeGenericPart.apply(classNameGeneric)) + ".class." + declr + ";",
                "      $r.setAccessible(true);"
                ),
            map(catchBlock, padding.andThen(padding)),
            newList(
                "    }",
                "  }",
                "  return $r;",
                "}"
            )
        );
    }
    
    public static final List<String> memberAccessor(String classNameGeneric, Class<? extends Member> reflectInvokationClass) {
        return newList(
            "public " + importType(reflectInvokationClass) + (Constructor.class.isAssignableFrom(reflectInvokationClass) ? "<" + importTypes(classNameGeneric) + ">" : "") + " getMember() {",
            "  return $getMember();",
            "}"
        );
    }
    
    public static final RuntimeException wrap(Throwable e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException)e;
        } else if (e instanceof Error) {
            throw (Error)e;
        } else {
            return new RuntimeException(e);
        }
    }
    
    public static final List<String> catchBlock = newList(
        "} catch (" + importType(Throwable.class) + " $e) {",
        "  throw " + importType(Content.class) + ".wrap($e);"
    );
    
    public static final Function5<CommonMetadataProcessor.CombinedGeneratorOptions,String,Predicate<Element>,List<Apply<TypeElement, Pair<Long,List<String>>>>, TypeElement, Pair<List<Long>,List<String>>> withNestedClasses = new Function5<CommonMetadataProcessor.CombinedGeneratorOptions, String, Predicate<Element>, List<Apply<TypeElement, Pair<Long,List<String>>>>, TypeElement, Pair<List<Long>,List<String>>>() {
        @Override
        public Pair<List<Long>,List<String>> apply(CommonMetadataProcessor.CombinedGeneratorOptions options, String generatedClassNamePattern, Predicate<Element> predicate, List<Apply<TypeElement, Pair<Long,List<String>>>> generators, TypeElement source) {
            if (isPrivate(source)) {
                // cannot refer to private classes
                return Pair.of(newList(repeat(0l, generators.size())), Collections.<String>emptyList());
            }
            List<Pair<Long, List<String>>> elemData = newList(sequence(generators, source));
            
            Iterable<TypeElement> nestedToProcess = element2NestedClasses.apply(source);
            if (options.onlyPublicMembers()) {
                nestedToProcess = filter(nestedToProcess, publicElement);
            }
            List<Pair<List<Long>, List<String>>> nestedData = newList(map(filter(nestedToProcess, predicate), withNestedClasses.ap(options, generatedClassNamePattern, predicate, generators)));
            
            Iterable<Long> generatorTimesForContent = map(elemData, Helpers.<Long>left());
            Iterable<List<Long>> generatorTimesForNestedClasses = map(nestedData, Helpers.<List<Long>>left());
            Iterable<String> elemContents = flatMap(elemData, Helpers.<List<String>>right());
            Iterable<String> nestedContents = flatMap(nestedData, Helpers.<List<String>>right());
            
            List<Long> totalTimesPerGenerator = newList(map(transpose(cons(generatorTimesForContent, generatorTimesForNestedClasses)), Helpers.iterableSum));
            
            Iterable<String> warnings = source.getAnnotation(SuppressWarnings.class) == null
                ? Collections.<String>emptyList()
                : filter(source.getAnnotation(SuppressWarnings.class).value(), not(equalTo("unused")));
            
            List<String> allContents = newList(concat(
                          isEmpty(warnings)
                              ? None
                              : Some("@SuppressWarnings({\"" + mkString("\",\"", warnings) + "\"})"),
                          source.getAnnotation(Deprecated.class) == null
                              ? None
                              : Some("@Deprecated"),
                          Some(resolveVisibility(source) + "static final class " + generatedClassNamePattern.replace("{}", source.getSimpleName().toString()) + " implements " + Serializable.class.getName() + " {"),
                          elemContents,
                          map(nestedContents, padding),
                          Some("}")));
            return Pair.of(totalTimesPerGenerator, allContents);
        }
    };
}
