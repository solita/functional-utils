package fi.solita.utils.functional;

import java.util.Comparator;
import java.util.Map;

/**
 * Extension to Comparator.
 */
public abstract class Ordering<T> implements Comparator<T>, Monoid<Ordering<T>> {
    
    /**
     * @return {@code comparator} converted to an {@code Ordering}.
     */
    public static final <T> Ordering<T> of(final Comparator<? super T> comparator) {
        if (comparator instanceof Ordering) {
            @SuppressWarnings("unchecked")
            Ordering<T> ret = (Ordering<T>) comparator;
            return ret;
        }
        return new Ordering<T>() {
            public int compare(T o1, T o2) {
                if (o1 == null && o2 == null) {
                    return 0;
                }
                return comparator.compare(o1, o2);
            }
        };
    }
    
    @SuppressWarnings("rawtypes")
    private static final Ordering Natural = new Ordering<Comparable>() {
        @SuppressWarnings("unchecked")
        public int compare(Comparable o1, Comparable o2) {
            if (o1 == null && o2 == null) {
                return 0;
            }
            return o1.compareTo(o2);
        }
    }; 
    
    /**
     * @return natural ordering.
     */
    @SuppressWarnings("unchecked")
    public static final <T extends Comparable<?>> Ordering<T> Natural() {
        return Natural;
    }
    
    /**
     * @return reversed ordering.
     */
    public final Ordering<T> reverse() {
        return of(java.util.Collections.reverseOrder(this));
    }
    
    public final Ordering<T> apply(Map.Entry<? extends Ordering<T>,? extends Ordering<T>> t) {
        return of(Monoids.<T>comparatorConcat().apply(t));
    }

    public final Ordering<T> zero() {
        return of(Monoids.comparatorConcat().zero());
    }
    
    /**
     * @return composition of {@code this} and {@code next}.
     */
    public final Ordering<T> then(Comparator<? super T> next) {
        return apply(Pair.of(this, Ordering.of(next)));
    }
    
    /**
     * @return composition of {@code this} and transformation to an element with natural ordering.
     */
    public final Ordering<T> thenBy(final Apply<? super T, ? extends Comparable<?>> next) {
        return then(Compare.by(next));
    }
    
    /**
     * @return composition of {@code this} and transformation to an element with natural ordering.
     */
    public final <S extends Comparable<? super S>> Ordering<T> thenByOption(final Apply<? super T, ? extends Option<S>> next) {
        return then(Compare.byOption(next));
    }
    
    /**
     * @return composition of {@code this} and {@code next} by transforming to a type comparable by {@code targetComparator}.
     */
    public final <S> Ordering<T> thenBy(final Apply<? super T, S> next, final Comparator<? super S> targetComparator) {
        return then(Compare.by(next, targetComparator));
    }
    
    /**
     * @return composition of {@code this} and {@code next} by transforming to a type comparable by {@code targetComparator}.
     */
    public final <S> Ordering<T> thenByOption(final Apply<? super T, ? extends Option<S>> next, final Comparator<? super S> targetComparator) {
        return then(Compare.byOption(next, targetComparator));
    }
}