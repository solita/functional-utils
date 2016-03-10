package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newArray;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newListOfSize;
import static fi.solita.utils.functional.FunctionalA.max;
import static fi.solita.utils.functional.FunctionalA.zip;
import static fi.solita.utils.functional.FunctionalC.drop;
import static fi.solita.utils.functional.FunctionalC.isEmpty;
import static fi.solita.utils.functional.FunctionalC.span;
import static fi.solita.utils.functional.FunctionalC.take;
import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Predicates.equalTo;
import static fi.solita.utils.functional.Predicates.not;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Match {
    @SuppressWarnings("unchecked")
    public static final <S,T extends S> Option<T> instance(Class<T> clazz, S obj) {
        if (clazz.isInstance(obj)) {
            return Some((T)obj);
        } else {
            return None();
        }
    }
    
    @SuppressWarnings("unchecked")
    public static final <T extends Throwable> Option<T> hasCauseInHierarchy(Class<T> cause, Throwable t) {
        if (cause.isInstance(t)) {
            return Some((T) t);
        } else if (t.getCause() != null) {
            return hasCauseInHierarchy(cause, t.getCause());
        }
        return None();
    }
    
    public static final Option<Pair<String,String>> startsWith(String prefix, CharSequence xs) {
        int prefixLength = prefix.length();
        CharSequence left = take(prefixLength, xs).toString();
        if (left.equals(prefix)) {
            return Some(Pair.of(prefix, drop(prefixLength, xs).toString()));
        }
        return Option.None();
    }
    
    public static final Option<Pair<String,String>> endsWith(String suffix, CharSequence xs) {
        int suffixLength = suffix.length();
        int prefixLength = xs.length() - suffixLength;
        CharSequence right = drop(prefixLength, xs).toString();
        if (right.equals(suffix)) {
            return Some(Pair.of(take(prefixLength, xs).toString(), suffix));
        }
        return Option.None();
    }
    
    public static final Option<Tuple2<String,String>> tuple2(char separator, CharSequence xs) {
        List<String> parts = split(separator, xs);
        if (parts.size() == 2) {
            return Some(Tuple.of(parts.get(0), parts.get(1)));
        }
        return None();
    }
    
    public static final Option<Pair<String,String>> pair(char separator, CharSequence xs) {
        List<String> parts = split(separator, xs);
        if (parts.size() == 2) {
            return Some(Pair.of(parts.get(0), parts.get(1)));
        }
        return None();
    }
    
    public static final Option<Tuple3<String,String,String>> tuple3(char separator, CharSequence xs) {
        List<String> parts = split(separator, xs);
        if (parts.size() != 3) {
            return Some(Pair.of(parts.get(0), parts.get(1), parts.get(2)));
        }
        return None();
    }
    
    public static final Option<Tuple4<String,String,String,String>> tuple4(char separator, CharSequence xs) {
        List<String> parts = split(separator, xs);
        if (parts.size() != 4) {
            return Some(Pair.of(parts.get(0), parts.get(1), parts.get(2), parts.get(3)));
        }
        return None();
    }
    
    public static final Option<Tuple5<String,String,String,String,String>> tuple5(char separator, CharSequence xs) {
        List<String> parts = split(separator, xs);
        if (parts.size() != 5) {
            return Some(Pair.of(parts.get(0), parts.get(1), parts.get(2), parts.get(3), parts.get(4)));
        }
        return None();
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
    
    public static final Option<List<String>> groups(Pattern regex, CharSequence xs) {
        Matcher matcher = regex.matcher(xs);
        if (matcher.matches()) {
            List<String> groups = newListOfSize(max(0, matcher.groupCount() - 1));
            for (int i = 1; i <= matcher.groupCount(); ++i) {
                groups.add(matcher.group(i));
            }
            return Some(groups);
        }
        return None();
    }
    
    public static final <T> Option<T> singleton(Iterable<T> ts) {
        List<T> arr = newList(ts);
        if (arr.size() == 1) {
            return Some(arr.get(0));
        }
        return None();
    }
    
    public static final <T> Option<Tuple2<T,T>> tuple2(Iterable<T> ts) {
        List<T> arr = newList(ts);
        if (arr.size() == 2) {
            return Some(Tuple.of(arr.get(0), arr.get(1)));
        }
        return None();
    }
    
    public static final <T> Option<Pair<T,T>> pair(Iterable<T> ts) {
        List<T> arr = newList(ts);
        if (arr.size() == 2) {
            return Some(Pair.of(arr.get(0), arr.get(1)));
        }
        return None();
    }
    
    public static final <T> Option<Tuple3<T,T,T>> tuple3(Iterable<T> ts) {
        List<T> arr = newList(ts);
        if (arr.size() == 3) {
            return Some(Pair.of(arr.get(0), arr.get(1), arr.get(2)));
        }
        return None();
    }
    
    public static final <T> Option<Tuple4<T,T,T,T>> tuple4(Iterable<T> ts) {
        List<T> arr = newList(ts);
        if (arr.size() == 4) {
            return Some(Pair.of(arr.get(0), arr.get(1), arr.get(2), arr.get(3)));
        }
        return None();
    }
    
    public static final <T> Option<Tuple5<T,T,T,T,T>> tuple5(Iterable<T> ts) {
        List<T> arr = newList(ts);
        if (arr.size() == 5) {
            return Some(Pair.of(arr.get(0), arr.get(1), arr.get(2), arr.get(3), arr.get(4)));
        }
        return None();
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Option<T> iterable(T _1, Iterable<? extends T> ts) {
        return ((Option<Tuple1<T>>)(Object)iterable(ts, _1)).map(Transformers.<T>_1());
    }
    
    public static final <T> Option<Pair<T,T>> iterable(T _1, T _2, Iterable<? extends T> ts) {
        return iterable(ts, _1, _2);
    }
    
    public static final <T> Option<Tuple3<T,T,T>> iterable(T _1, T _2, T _3, Iterable<? extends T> ts) {
        return iterable(ts, _1, _2, _3);
    }
    
    @SuppressWarnings("unchecked")
    private static final <T,R extends Tuple> Option<R> iterable(Iterable<T> ts, Object... expected) {
        Object[] actual = newArray(Object.class, ts);
        if (actual.length == expected.length) {
            for (Tuple2<Object, Object> p: zip(expected, actual)) {
                if (p._1 != null && !p._1.equals(p._2)) {
                    return None();
                }
            }
            return (Option<R>) Some(Tuple.of(actual));
        }
        return None();
    }
}
