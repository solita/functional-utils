package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newArray;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.FunctionalA.zip;
import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;

import java.util.List;

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
    
    public static final <T> Option<T> singleton(Iterable<T> ts) {
        List<T> arr = newList(ts);
        if (arr.size() == 1) {
            return Some(arr.get(0));
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
