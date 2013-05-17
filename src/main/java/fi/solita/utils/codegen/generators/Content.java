package fi.solita.utils.codegen.generators;

import static fi.solita.utils.codegen.Helpers.element2NestedClasses;
import static fi.solita.utils.codegen.Helpers.resolveVisibility;
import static fi.solita.utils.codegen.Helpers.typeArgs;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.cons;
import static fi.solita.utils.functional.Functional.contains;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.flatMap;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.Functional.reduce;
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

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

import fi.solita.utils.functional.Collections;
import fi.solita.utils.functional.Function1;
import fi.solita.utils.functional.Function4;
import fi.solita.utils.functional.Monoid;
import fi.solita.utils.functional.Pair;
import fi.solita.utils.functional.Predicate;
import fi.solita.utils.functional.Transformer;
import fi.solita.utils.functional.Transformers;

public abstract class Content {

    public static final Iterable<String> None = None();
    public static final Iterable<String> EmptyLine = Some("");
    
    public static List<String> memberNameAccessor(String name) {
        return newList(
            "public String getName() {",
            "    return \"" + name + "\";",
            "}"
        );
    }
    
    public static Iterable<String> memberInitializer(String classNameGeneric, String name, Class<? extends Member> reflectInvokationClass, Iterable<String> argumentClasses) {
        String declr = Constructor.class.isAssignableFrom(reflectInvokationClass) ? "getDeclaredConstructor(" + mkString(", ", argumentClasses) + ")" :
                       Method.class.isAssignableFrom(reflectInvokationClass) ?      "getDeclaredMethod(" + mkString(", ", cons('"' + name + '"', argumentClasses)) + ")" :
                       Field.class.isAssignableFrom(reflectInvokationClass) ?       "getDeclaredField(\"" + name + "\")" : "";
        String clsName = reflectInvokationClass.getName() + (Constructor.class.isAssignableFrom(reflectInvokationClass) ? "<" + classNameGeneric + ">" : "");
        return concat(
            newList(
                "private transient " + clsName + " $r;",
                "",
                Constructor.class.isAssignableFrom(reflectInvokationClass) ? "@SuppressWarnings(\"unchecked\")" : "",
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
    
    public static Function4<String,Predicate<Element>,List<Function1<TypeElement, Pair<Long,List<String>>>>, TypeElement, Pair<List<Long>,List<String>>> withNestedClasses = new Function4<String, Predicate<Element>, List<Function1<TypeElement, Pair<Long,List<String>>>>, TypeElement, Pair<List<Long>,List<String>>>() {
        @Override
        public Pair<List<Long>,List<String>> apply(String generatedClassNamePattern, Predicate<Element> predicate, List<Function1<TypeElement, Pair<Long,List<String>>>> generators, TypeElement source) {
            if (contains(source.getModifiers(), Modifier.PRIVATE)) {
                // cannot refer to private classes
                return Pair.of(Collections.<Long>emptyList(), Collections.<String>emptyList());
            }
            List<Pair<Long, List<String>>> elemData = newList(sequence(generators, source));
            List<Pair<List<Long>, List<String>>> nestedData = newList(map(filter(element2NestedClasses.apply(source), predicate), withNestedClasses.curried().apply(generatedClassNamePattern).apply(predicate).apply(generators)));
            
            Iterable<Long> contentTimes = map(elemData, Transformers.<Long>left());
            Iterable<List<Long>> nestedTimes = map(nestedData, Transformers.<List<Long>>left());
            Iterable<String> elemContents = flatMap(elemData, Transformers.<List<String>>right());
            Iterable<String> nestedContents = flatMap(nestedData, Transformers.<Iterable<String>>right());
            
            List<Long> totalTimesPerGenerator = newList(map(transpose(cons(contentTimes, nestedTimes)), new Transformer<Iterable<Long>,Long>() {
                @Override
                public Long transform(Iterable<Long> source) {
                    return reduce(source, Monoid.longSum);
                }
            }));
            if (totalTimesPerGenerator.size() != generators.size()) {
                throw new RuntimeException("whoops...");
            }
            
            List<String> allContents = newList(concat(Some(resolveVisibility(source) + " static final class " + generatedClassNamePattern.replace("{}", source.getSimpleName().toString()) + " implements " + Serializable.class.getName() + " {"),
                          elemContents,
                          map(nestedContents, prepend("    ")),
                          Some("}")));
            return Pair.of(totalTimesPerGenerator, allContents);
        }
    };
}
