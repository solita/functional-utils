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
import static fi.solita.utils.functional.Functional.flatten;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.Functional.sequence;
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

import fi.solita.utils.functional.Function1;
import fi.solita.utils.functional.Function4;
import fi.solita.utils.functional.Predicate;

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
    
    public static Function4<String,Predicate<Element>,List<Function1<TypeElement, Iterable<String>>>, TypeElement, Iterable<String>> withNestedClasses = new Function4<String, Predicate<Element>, List<Function1<TypeElement, Iterable<String>>>, TypeElement, Iterable<String>>() {
        @Override
        public Iterable<String> apply(String generatedClassNamePattern, Predicate<Element> predicate, List<Function1<TypeElement, Iterable<String>>> generators, TypeElement source) {
            if (contains(source.getModifiers(), Modifier.PRIVATE)) {
                // cannot refer to private classes
                return None;
            }
            String visibility = resolveVisibility(source);
            Iterable<String> elemData = flatten(sequence(generators, source));
            Iterable<String> nestedData = flatMap(filter(element2NestedClasses.apply(source), predicate), withNestedClasses.curried().apply(generatedClassNamePattern).apply(predicate).apply(generators));
            return concat(Some(visibility + " static final class " + generatedClassNamePattern.replace("{}", source.getSimpleName().toString()) + " implements " + Serializable.class.getName() + " {"),
                          elemData,
                          map(nestedData, prepend("    ")),
                          Some("}"));
        }
    };
}
