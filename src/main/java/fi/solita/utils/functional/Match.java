package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newArray;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newListOfSize;
import static fi.solita.utils.functional.Functional.zipWithIndex;
import static fi.solita.utils.functional.FunctionalA.max;
import static fi.solita.utils.functional.FunctionalA.zip;
import static fi.solita.utils.functional.FunctionalC.drop;
import static fi.solita.utils.functional.FunctionalC.isEmpty;
import static fi.solita.utils.functional.FunctionalC.span;
import static fi.solita.utils.functional.FunctionalC.take;
import static fi.solita.utils.functional.Predicates.equalTo;
import static fi.solita.utils.functional.Predicates.not;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Match {
    public static final Try<String,String> nonEmptyString(String obj) {
        if (obj == null || obj.trim().isEmpty()) {
            return Try.failure("String was null or empty: " + obj);
        }
        return Try.success(obj);
    }
    
    @SuppressWarnings("unchecked")
    public static final <S,T extends S> Try<String,T> instance(Class<T> clazz, S obj) {
        if (clazz.isInstance(obj)) {
            return Try.success((T)obj);
        } else {
            return Try.failure("Not an instance of " + clazz + ": " + obj);
        }
    }
    
    @SuppressWarnings("unchecked")
    public static final <T extends Throwable> Try<String,T> hasCauseInHierarchy(Class<T> cause, Throwable t) {
        if (cause.isInstance(t)) {
            return Try.success((T) t);
        } else if (t.getCause() != null) {
            return hasCauseInHierarchy(cause, t.getCause());
        }
        return Try.failure("Cause of type " + cause + " not found from " + t);
    }
    
    public static final Try<String,Pair<String,String>> startsWith(String prefix, CharSequence xs) {
        int prefixLength = prefix.length();
        CharSequence left = take(prefixLength, xs).toString();
        if (left.equals(prefix)) {
            return Try.success(Pair.of(prefix, drop(prefixLength, xs).toString()));
        }
        return Try.failure(prefix + " is not a prefix of " + xs);
    }
    
    public static final Try<String,Pair<String,String>> endsWith(String suffix, CharSequence xs) {
        int suffixLength = suffix.length();
        int prefixLength = xs.length() - suffixLength;
        CharSequence right = drop(prefixLength, xs).toString();
        if (right.equals(suffix)) {
            return Try.success(Pair.of(take(prefixLength, xs).toString(), suffix));
        }
        return Try.failure(suffix + " is not a suffix of " + xs);
    }
    
    public static final Try<String,Tuple2<String,String>> tuple2(char separator, CharSequence xs) {
        List<String> parts = split(separator, xs);
        if (parts.size() == 2) {
            return Try.success(Tuple.of(parts.get(0), parts.get(1)));
        }
        return err(separator, xs, 2);
    }

    public static final Try<String,Pair<String,String>> pair(char separator, CharSequence xs) {
        List<String> parts = split(separator, xs);
        if (parts.size() == 2) {
            return Try.success(Pair.of(parts.get(0), parts.get(1)));
        }
        return err(separator, xs, 2);
    }
    
    public static final Try<String,Tuple3<String,String,String>> tuple3(char separator, CharSequence xs) {
        List<String> parts = split(separator, xs);
        if (parts.size() == 3) {
            return Try.success(Tuple.of(parts.get(0), parts.get(1), parts.get(2)));
        }
        return err(separator, xs, 3);
    }
    
    public static final Try<String,Tuple4<String,String,String,String>> tuple4(char separator, CharSequence xs) {
        List<String> parts = split(separator, xs);
        if (parts.size() == 4) {
            return Try.success(Tuple.of(parts.get(0), parts.get(1), parts.get(2), parts.get(3)));
        }
        return err(separator, xs, 4);
    }
    
    public static final Try<String,Tuple5<String,String,String,String,String>> tuple5(char separator, CharSequence xs) {
        List<String> parts = split(separator, xs);
        if (parts.size() == 5) {
            return Try.success(Tuple.of(parts.get(0), parts.get(1), parts.get(2), parts.get(3), parts.get(4)));
        }
        return err(separator, xs, 5);
    }
    
    private static <T extends Tuple> Try<String, T> err(char separator, CharSequence xs, int parts) {
        return Try.failure(separator + " does not separate " + xs + " to exactly " + parts + "  parts");
    }

    private static final List<String> split(char separator, CharSequence xs) {
        Predicate<Character> predicate = not(equalTo(separator));
        List<String> parts = newList();
        
        boolean cont = !isEmpty(xs);
        while (cont) {
            Pair<CharSequence,CharSequence> p = span(predicate, xs);
            parts.add(p.left.toString());
            xs = drop(1, p.right);
            cont = !isEmpty(xs) || !isEmpty(p.right);
        }
        return parts;
    }
    
    public static final Try<String,List<String>> groups(Pattern regex, CharSequence xs) {
        Matcher matcher = regex.matcher(xs);
        if (matcher.matches()) {
            List<String> groups = newListOfSize(max(0, matcher.groupCount() - 1));
            for (int i = 1; i <= matcher.groupCount(); ++i) {
                groups.add(matcher.group(i));
            }
            return Try.success(groups);
        }
        return Try.failure(xs + " does not match " + regex);
    }
    
    public static final <T> Try<String,T> singleton(Iterable<T> ts) {
        List<T> arr = newList(ts);
        if (arr.size() == 1) {
            return Try.success(arr.get(0));
        }
        return Try.failure("Not a singleton (was size " + arr.size() + "): " + ts);
    }
    
    public static final <T> Try<String,Tuple2<T,T>> tuple2(Iterable<T> ts) {
        List<T> arr = newList(ts);
        if (arr.size() == 2) {
            return Try.success(Tuple.of(arr.get(0), arr.get(1)));
        }
        return err(ts, arr, 2);
    }

    public static final <T> Try<String,Pair<T,T>> pair(Iterable<T> ts) {
        List<T> arr = newList(ts);
        if (arr.size() == 2) {
            return Try.success(Pair.of(arr.get(0), arr.get(1)));
        }
        return err(ts, arr, 2);
    }
    
    public static final <T> Try<String,Tuple3<T,T,T>> tuple3(Iterable<T> ts) {
        List<T> arr = newList(ts);
        if (arr.size() == 3) {
            return Try.success(Pair.of(arr.get(0), arr.get(1), arr.get(2)));
        }
        return err(ts, arr, 3);
    }
    
    public static final <T> Try<String,Tuple4<T,T,T,T>> tuple4(Iterable<T> ts) {
        List<T> arr = newList(ts);
        if (arr.size() == 4) {
            return Try.success(Pair.of(arr.get(0), arr.get(1), arr.get(2), arr.get(3)));
        }
        return err(ts, arr, 4);
    }
    
    public static final <T> Try<String,Tuple5<T,T,T,T,T>> tuple5(Iterable<T> ts) {
        List<T> arr = newList(ts);
        if (arr.size() == 5) {
            return Try.success(Pair.of(arr.get(0), arr.get(1), arr.get(2), arr.get(3), arr.get(4)));
        }
        return err(ts, arr, 5);
    }
    
    private static <T extends Tuple> Try<String, T> err(Iterable<?> ts, List<?> arr, int size) {
        return Try.failure("Not of size " + size + " (was size " + arr.size() + "): " + ts);
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Try<String,T> iterable(T _1, Iterable<? extends T> ts) {
        return ((Try<String,Tuple1<T>>)(Object)iterable(ts, _1)).map(Transformers.<T>_1());
    }
    
    public static final <T> Try<String,Pair<T,T>> iterable(T _1, T _2, Iterable<? extends T> ts) {
        return iterable(ts, _1, _2);
    }
    
    public static final <T> Try<String,Tuple3<T,T,T>> iterable(T _1, T _2, T _3, Iterable<? extends T> ts) {
        return iterable(ts, _1, _2, _3);
    }
    
    @SuppressWarnings("unchecked")
    private static final <T,R extends Tuple> Try<String,R> iterable(Iterable<T> ts, Object... expected) {
        Object[] actual = newArray(Object.class, ts);
        if (actual.length == expected.length) {
            for (Tuple2<Integer, Tuple2<Object, Object>> t: zipWithIndex(zip(expected, actual))) {
                int i = t._1;
                Tuple2<Object,Object> p = t._2;
                if (p._1 != null && !p._1.equals(p._2)) {
                    return Try.failure("Object at index " + i + " (" + p._2 + ") did not match expected value: " + p._1);
                }
            }
            return Try.success((R)Tuple.of(actual));
        }
        return Try.failure("Amount of expected values (" + expected.length + ") did not match the length of iterable (" + actual.length + "): " + ts);
    }
}
