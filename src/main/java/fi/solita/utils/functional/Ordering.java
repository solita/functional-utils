package fi.solita.utils.functional;

import static fi.solita.utils.functional.Functional.reduce;

import java.util.Comparator;
import java.util.Map;

public abstract class Ordering<T> implements Comparator<T>, Monoid<Ordering<T>> {
    
    public static final <T> Ordering<T> of(final Comparator<? super T> c) {
        if (c instanceof Ordering) {
            @SuppressWarnings("unchecked")
            Ordering<T> ret = (Ordering<T>) c;
            return ret;
        }
        return new Ordering<T>() {
            public int compare(T o1, T o2) {
                return c.compare(o1, o2);
            }
            
        };
    }
    
    @SuppressWarnings("rawtypes")
    private static final Ordering Natural = new Ordering<Comparable>() {
        @SuppressWarnings("unchecked")
        public int compare(Comparable o1, Comparable o2) {
            return o1.compareTo(o2);
        }
    }; 
    @SuppressWarnings("unchecked")
    public static final <T extends Comparable<?>> Ordering<T> Natural() {
        return Natural;
    }
    
    public final Ordering<T> reverse() {
        return of(java.util.Collections.reverseOrder(this));
    }
    
    public final Ordering<T> apply(Map.Entry<? extends Ordering<T>,? extends Ordering<T>> t) {
        return of(Monoids.<T>comparatorConcat().apply(t.getKey(), t.getValue()));
    }

    public final Ordering<T> zero() {
        return of(Monoids.comparatorConcat().zero());
    }
    
    public final Ordering<T> then(Comparator<? super T> next) {
        return reduce(this, of(next));
    }
}