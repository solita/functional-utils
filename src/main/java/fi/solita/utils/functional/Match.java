package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newList;
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
    
    public static final <T> Option<Pair<T, T>> pair(Iterable<T> ts) {
        List<T> arr = newList(ts);
        if (arr.size() == 2) {
            return Some(Pair.of(arr.get(0), arr.get(1)));
        }
        return None();
    }
    
    public static final <T> Option<Tuple3<T, T, T>> tuple3(Iterable<T> ts) {
        List<T> arr = newList(ts);
        if (arr.size() == 3) {
            return Some(Pair.of(arr.get(0), arr.get(1), arr.get(2)));
        }
        return None();
    }
}
