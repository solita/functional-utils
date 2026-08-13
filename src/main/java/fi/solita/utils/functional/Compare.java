package fi.solita.utils.functional;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

public abstract class Compare {
    
    /**
     * @return natural ordering.
     */
    public static final <T extends Comparable<?>> Ordering<T> byNatural() {
        return Ordering.Natural();
    }
    
    /**
     * @return ordering for iterables containing elements with natural ordering.
     */
    public static final <T extends Comparable<? super T>> Ordering<Iterable<T>> byIterable() {
        return byIterable(Ordering.<T>Natural());
    }
    
    /**
     * @return ordering for an optional element with natural ordering.
     */
    public static final <T extends Comparable<? super T>> Ordering<Option<T>> byOption() {
        return byOption(Ordering.<T>Natural());
    }
    
    public static final <K extends Comparable<? super K>, V extends Comparable<? super V>> Ordering<Map<K,V>> byMap() {
        return byMap(Ordering.<K>Natural(), Ordering.<V>Natural());
    }
    
    public static final <K, V> Ordering<Map<K,V>> byMap(final Comparator<? super K> keyComparator, final Comparator<? super V> valueComparator) {
        return by(Map::entrySet, byIterable(byEntry(keyComparator, valueComparator)));
    }
    
    public static final <L, R> Ordering<Map.Entry<L,R>> byEntry(final Comparator<? super L> leftComparator, final Comparator<? super R> rightComparator) {
        return Compare.by( (Map.Entry<L,R> e) -> e.getKey(), leftComparator).then(by(e -> e.getValue(), rightComparator));
    }
    
    @SuppressWarnings("unchecked")
    public static final <L, R> Ordering<Pair<L,R>> byPair(final Comparator<? super L> leftComparator, final Comparator<? super R> rightComparator) {
        return (Ordering<Pair<L,R>>)(Object)byEntry(leftComparator, rightComparator);
    }
    
    public static final <T1, T2, T3> Ordering<Tuple3<T1, T2, T3>> byTuple(final Comparator<? super T1> comparator1, final Comparator<? super T2> comparator2, final Comparator<? super T3> comparator3) {
        return Compare.<T1,Tuple3<T1, T2, T3>>by(t -> t._1, comparator1).thenBy(t -> t._2, comparator2).then(by(t -> t._3, comparator3));
    }
    
    public static final <T1, T2, T3, T4> Ordering<Tuple4<T1, T2, T3, T4>> byTuple(final Comparator<? super T1> comparator1, final Comparator<? super T2> comparator2, final Comparator<? super T3> comparator3, final Comparator<? super T4> comparator4) {
        return Compare.<T1,Tuple4<T1, T2, T3, T4>>by(t -> t._1, comparator1).thenBy(t -> t._2, comparator2).then(by(t -> t._3, comparator3)).then(by(t -> t._4, comparator4));
    }
    
    /**
     * @return ordering for iterables containing elements comparable by {@code elementComparator}.
     */
    public static final <S, T extends Iterable<? extends S>> Ordering<T> byIterable(final Comparator<S> elementComparator) {
        return new Ordering<T>() {
            public final int compare(T o1, T o2) {
                if (o1 == null && o2 == null) {
                    return 0;
                }
                for (Long s1: Iterables.resolveSize.apply(o1)) {
                    for (Long s2: Iterables.resolveSize.apply(o2)) {
                        int s = s1.compareTo(s2);
                        if (s != 0) {
                            return s;
                        }
                    }
                }
                Iterator<? extends S> it1 = o1.iterator();
                Iterator<? extends S> it2 = o2.iterator();
                while (it1.hasNext() && it2.hasNext()) {
                    int i = elementComparator.compare(it1.next(), it2.next());
                    if (i != 0) {
                        return i;
                    }
                }
                return it1.hasNext() ? 1 : it2.hasNext() ? -1 : 0;
            }
        }; 
    }
    
    /**
     * @return ordering for elements by transforming to an element with natural ordering.
     */
    @SuppressWarnings("unchecked")
    public static final <T> Ordering<T> by(final Apply<? super T, ? extends Comparable<?>> f) {
        return by(f, (Comparator<Object>)(Object)Ordering.Natural());
    }
    
    /**
     * @return ordering for elements by transforming to a type comparable by {@code targetComparator}.
     */
    public static final <T,S> Ordering<S> by(final Apply<? super S, T> f, final Comparator<? super T> targetComparator) {
        return new Ordering<S>() {
            public final int compare(S o1, S o2) {
                if (o1 == null && o2 == null) {
                    return 0;
                }
                return targetComparator.compare(f.apply(o1), f.apply(o2));
            }
        };
    }
    
    /**
     * @return ordering for elements by transforming to an optional element with natural ordering. 
     */
    public static final <S, T extends Comparable<? super T>> Ordering<S> byOption(final Apply<? super S, ? extends Option<T>> f) {
        return byOption(f, Ordering.Natural());
    }
    
    /**
     * @return ordering for elements by transforming to an optional type comparable by {@code targetComparator}.
     */
    public static final <S, T> Ordering<S> byOption(final Apply<? super S, ? extends Option<T>> f, final Comparator<? super T> targetComparator) {
        return by(f, Compare.<T>byOption(targetComparator));
    }
    
    /**
     * @return ordering for optional type comparable by {@code comparator}.
     */
    public static final <T> Ordering<Option<T>> byOption(final Comparator<? super T> comparator) {
        return new Ordering<Option<T>>() {
            public final int compare(Option<T> o1, Option<T> o2) {
                if (o1 == null && o2 == null) {
                    return 0;
                }
                if (!o1.isDefined() && !o2.isDefined()) {
                    return 0;
                } else if (o1.isDefined() && !o2.isDefined()) {
                    return -1;
                } else if (!o1.isDefined() && o2.isDefined()) {
                    return 1;
                } else {
                    return comparator.compare(o1.get(), o2.get());
                }
            }
        };
    }
    
    public static final <L extends Comparable<? super L>,R extends Comparable<? super R>> Ordering<Either<L,R>> byEither() {
        return byOption( (Either<L,R> x) -> x.left).then(byOption(x -> x.right));
    }
    
    public static final <L extends Comparable<? super L>,M extends Comparable<? super M>,R extends Comparable<? super R>> Ordering<Either3<L,M,R>> byEither3() {
        return byOption( (Either3<L,M,R> x) -> x.left).then(
               byOption( (Either3<L,M,R> x) -> x.middle).then(
               byOption(x -> x.right)));
    }
    
    public static final <K extends Comparable<? super K>,V> Ordering<Map.Entry<K,V>> byKey() {
        return by(Map.Entry::getKey);
    }
    
    public static final <K,V extends Comparable<? super V>> Ordering<Map.Entry<K,V>> byValue() {
        return by(Map.Entry::getValue);
    }
}
