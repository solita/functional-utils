package fi.solita.utils.codegen.generators;

import static fi.solita.utils.codegen.Helpers.element2NestedClasses;
import static fi.solita.utils.codegen.Helpers.isPrimitive;
import static fi.solita.utils.codegen.Helpers.isPrivate;
import static fi.solita.utils.codegen.Helpers.padding;
import static fi.solita.utils.codegen.Helpers.resolveVisibility;
import static fi.solita.utils.codegen.Helpers.typeArgs;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.cons;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.flatMap;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.Functional.repeat;
import static fi.solita.utils.functional.Functional.sequence;
import static fi.solita.utils.functional.Functional.transpose;
import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Transformers.prepend;

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

import fi.solita.utils.codegen.Helpers;
import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Collections;
import fi.solita.utils.functional.Function4;
import fi.solita.utils.functional.Pair;
import fi.solita.utils.functional.Predicate;
import fi.solita.utils.functional.Transformer;

public abstract class Content {

    public static final Iterable<String> None = None();
    public static final Iterable<String> EmptyLine = Some("");
    
    public static final List<String> memberNameAccessor(String name) {
        return newList(
            "public String getName() {",
            "    return \"" + name + "\";",
            "}"
        );
    }
    
    private static final Pattern arrayClass = Pattern.compile("(\\[\\])*\\.class"); 
    private static final Pattern typeArgArray = Pattern.compile("[^.\\[\\]]+((\\[\\])+).class");
    
    public static Iterable<String> memberInitializer(String classNameGeneric, String name, Class<? extends Member> reflectInvokationClass, Iterable<String> argumentClasses) {
        Iterable<String> typeargsReplacedWithObject = map(argumentClasses, new Transformer<String,String>() {
            @Override
            public String transform(String source) {
                if (isPrimitive(arrayClass.matcher(source).replaceAll(""))) {
                    // just a primitive
                    return source;
                } else {
                    Matcher m = typeArgArray.matcher(source);
                    if (m.matches()) {
                        return "java.lang.Object[].class";
                    }
                    return source;
                }
            }
        });
        boolean isConstructor = Constructor.class.isAssignableFrom(reflectInvokationClass);
        String declr = isConstructor ? "getDeclaredConstructor(" + mkString(", ", typeargsReplacedWithObject) + ")" :
                       Method.class.isAssignableFrom(reflectInvokationClass) ?      "getDeclaredMethod(" + mkString(", ", cons('"' + name + '"', typeargsReplacedWithObject)) + ")" :
                       Field.class.isAssignableFrom(reflectInvokationClass) ?       "getDeclaredField(\"" + name + "\")" : "";
        String clsName = reflectInvokationClass.getName() + (isConstructor ? "<" + classNameGeneric + ">" : "");
        return concat(
            newList(
                "private transient " + clsName + " $r;",
                "",
                isConstructor ? "@SuppressWarnings(\"unchecked\")" : "",
                "private " + clsName + " $getMember() {",
                "    if ($r == null) {",
                "        try {",
                "            $r = (" + clsName + ")(Object)" + typeArgs.matcher(classNameGeneric).replaceAll("") + ".class." + declr + ";",
                "            $r.setAccessible(true);"
                ),
            map(catchBlock, prepend("        ")),
            newList(
                "        }",
                "    }",
                "    return $r;",
                "}"
            )
        );
    }
    
    public static List<String> memberAccessor(String classNameGeneric, Class<? extends Member> reflectInvokationClass) {
        return newList(
            "public " + reflectInvokationClass.getName() + (Constructor.class.isAssignableFrom(reflectInvokationClass) ? "<" + classNameGeneric + ">" : "") + " getMember() {",
            "    return $getMember();",
            "}"
        );
    }
    
    public static List<String> catchBlock = newList(
        "} catch (RuntimeException $e) {",
        "    throw $e;",
        "} catch (Error $e) {",
        "    throw $e;",
        "} catch (Throwable $e) {",
        "    throw new RuntimeException($e);"
    );
    
    public static Function4<String,Predicate<Element>,List<Apply<TypeElement, Pair<Long,List<String>>>>, TypeElement, Pair<List<Long>,List<String>>> withNestedClasses = new Function4<String, Predicate<Element>, List<Apply<TypeElement, Pair<Long,List<String>>>>, TypeElement, Pair<List<Long>,List<String>>>() {
        @Override
        public Pair<List<Long>,List<String>> apply(String generatedClassNamePattern, Predicate<Element> predicate, List<Apply<TypeElement, Pair<Long,List<String>>>> generators, TypeElement source) {
            if (isPrivate(source)) {
                // cannot refer to private classes
                return Pair.of(newList(repeat(0l, generators.size())), Collections.<String>emptyList());
            }
            List<Pair<Long, List<String>>> elemData = newList(sequence(generators, source));
            List<Pair<List<Long>, List<String>>> nestedData = newList(map(filter(element2NestedClasses.apply(source), predicate), withNestedClasses.ap(generatedClassNamePattern, predicate, generators)));
            
            Iterable<Long> generatorTimesForContent = map(elemData, Helpers.<Long>left());
            Iterable<List<Long>> generatorTimesForNestedClasses = map(nestedData, Helpers.<List<Long>>left());
            Iterable<String> elemContents = flatMap(elemData, Helpers.<List<String>>right());
            Iterable<String> nestedContents = flatMap(nestedData, Helpers.<List<String>>right());
            
            List<Long> totalTimesPerGenerator = newList(map(transpose(cons(generatorTimesForContent, generatorTimesForNestedClasses)), Helpers.iterableSum));
            
            List<String> allContents = newList(concat(
                          Some(resolveVisibility(source) + " static final class " + generatedClassNamePattern.replace("{}", source.getSimpleName().toString()) + " implements " + Serializable.class.getName() + " {"),
                          elemContents,
                          map(nestedContents, padding),
                          Some("}")));
            return Pair.of(totalTimesPerGenerator, allContents);
        }
    };
}
