package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.it;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public abstract class Compare {
    
    public static final <T extends Comparable<? super T>> Comparator<T> naturalNullsLast() {
        return Comparator.nullsLast(Comparator.naturalOrder());
    }
    
    public static final <K extends Comparable<? super K>, V extends Comparable<? super V>> Comparator<Map<K,V>> byMap() {
        return byMap(naturalNullsLast(), naturalNullsLast());
    }
    
    public static final <K, V> Comparator<Map<K,V>> byMap(final Comparator<? super K> keyComparator, final Comparator<? super V> valueComparator) {
        return Comparator.comparing(Map::entrySet, byIterable(byEntry(keyComparator, valueComparator)));
    }
    
    
    
    public static final <L extends Comparable<? super L>, R extends Comparable<? super R>> Comparator<Map.Entry<L,R>> byEntry() {
        return Comparator.comparing( (Map.Entry<L,R> e) -> e.getKey(), naturalNullsLast()).thenComparing(e -> e.getValue(), naturalNullsLast());
    }
    
    public static final <L, R> Comparator<Map.Entry<L,R>> byEntry(final Comparator<? super L> leftComparator, final Comparator<? super R> rightComparator) {
        return Comparator.comparing( (Map.Entry<L,R> e) -> e.getKey(), leftComparator).thenComparing(e -> e.getValue(), rightComparator);
    }
    
    
    
    @SuppressWarnings("unchecked")
    public static final <L extends Comparable<? super L>, R extends Comparable<? super R>> Comparator<Pair<L,R>> byPair() {
        return (Comparator<Pair<L,R>>)(Object)byEntry(naturalNullsLast(), naturalNullsLast());
    }
    
    @SuppressWarnings("unchecked")
    public static final <L, R> Comparator<Pair<L,R>> byPair(final Comparator<? super L> leftComparator, final Comparator<? super R> rightComparator) {
        return (Comparator<Pair<L,R>>)(Object)byEntry(leftComparator, rightComparator);
    }
    
    
    
    public static final <T1, T2, T3> Comparator<Tuple3<T1, T2, T3>> byTuple(final Comparator<? super T1> comparator1, final Comparator<? super T2> comparator2, final Comparator<? super T3> comparator3) {
        return Comparator.<Tuple3<T1, T2, T3>, T1>comparing(t -> t._1, comparator1).thenComparing(t -> t._2, comparator2).thenComparing(t -> t._3, comparator3);
    }
    
    public static final <T1, T2, T3, T4> Comparator<Tuple4<T1, T2, T3, T4>> byTuple(final Comparator<? super T1> comparator1, final Comparator<? super T2> comparator2, final Comparator<? super T3> comparator3, final Comparator<? super T4> comparator4) {
        return Comparator.<Tuple4<T1, T2, T3, T4>,T1>comparing(t -> t._1, comparator1).thenComparing(t -> t._2, comparator2).thenComparing(t -> t._3, comparator3).thenComparing(t -> t._4, comparator4);
    }
    
    
    
    /**
     * @return Comparator for iterables containing elements with natural Comparator.
     */
    public static final <T extends Comparable<? super T>> Comparator<Iterable<T>> byIterable() {
        return byIterable(Compare.<T>naturalNullsLast());
    }
    
    /**
     * @return Comparator for iterables containing elements comparable by {@code elementComparator}.
     */
    public static final <S, T extends Iterable<? extends S>> Comparator<T> byIterable(final Comparator<S> elementComparator) {
        return new Comparator<T>() {
            public final int compare(T o1, T o2) {
                if (o1 == null && o2 == null) {
                    return 0;
                }
                for (Long s1: it(Iterables.resolveSize.apply(o1))) {
                    for (Long s2: it(Iterables.resolveSize.apply(o2))) {
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
     * @return Comparator for an optional element with natural Comparator.
     */
    public static final <T extends Comparable<? super T>> Comparator<Optional<T>> byOptional() {
        return byOptional(naturalNullsLast());
    }
    
    /**
     * @return Comparator for optional type comparable by {@code comparator}.
     */
    public static final <T> Comparator<Optional<T>> byOptional(final Comparator<? super T> comparator) {
        return new Comparator<Optional<T>>() {
            public final int compare(Optional<T> o1, Optional<T> o2) {
                if (o1 == null && o2 == null) {
                    return 0;
                }
                if (!o1.isPresent() && !o2.isPresent()) {
                    return 0;
                } else if (o1.isPresent() && !o2.isPresent()) {
                    return -1;
                } else if (!o1.isPresent() && o2.isPresent()) {
                    return 1;
                } else {
                    return comparator.compare(o1.get(), o2.get());
                }
            }
        };
    }
    
    
    
    public static final <L extends Comparable<? super L>,R extends Comparable<? super R>> Comparator<Either<L,R>> byEither() {
        return Comparator.<Either<L,R>,Optional<L>>comparing(x -> x.left, Compare.<L>byOptional()).thenComparing(x -> x.right, Compare.<R>byOptional());
    }
    
    public static final <L,R> Comparator<Either<L,R>> byEither(final Comparator<? super L> leftComparator, final Comparator<? super R> rightComparator) {
        return Comparator.<Either<L,R>,Optional<L>>comparing(x -> x.left, Compare.byOptional(leftComparator)).thenComparing(x -> x.right, Compare.byOptional(rightComparator));
    }
    
    
    
    public static final <L,M,R> Comparator<Either3<L,M,R>> byEither3(final Comparator<? super L> leftComparator, final Comparator<? super M> middleComparator, final Comparator<? super R> rightComparator) {
        return Comparator.<Either3<L,M,R>,Optional<L>>comparing(x -> x.left, Compare.byOptional(leftComparator))
                         .thenComparing(x -> x.middle, Compare.byOptional(middleComparator))
                         .thenComparing(x -> x.right, Compare.byOptional(rightComparator));
    }
    
    public static final <L extends Comparable<? super L>,M extends Comparable<? super M>,R extends Comparable<? super R>> Comparator<Either3<L,M,R>> byEither3() {
        return Comparator.<Either3<L,M,R>,Optional<L>>comparing(x -> x.left, Compare.byOptional())
                         .thenComparing(x -> x.middle, Compare.byOptional())
                         .thenComparing(x -> x.right, Compare.byOptional());
    }
}
