package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.emptySet;
import static fi.solita.utils.functional.Collections.newArray;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Transformers.int2long;
import static fi.solita.utils.functional.Transformers.short2long;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import fi.solita.utils.functional.Iterables.RangeIterable;

/**
 * Some specialized operations (primitives, Sets...).
 */
public abstract class FunctionalS extends FunctionalA {
    /**
     * {@link Functional#reduce(Monoid, Iterable)} specialized to sum of Longs.
     */
    public static final long sum(Iterable<Long> xs) {
        return FunctionalImpl.sum(xs);
    }
    
    /**
     * {@link Functional#reduce(Monoid, Iterable)} specialized to sum of Longs.
     */
    public static final long sum(long... xs) {
        return sum(newList(newArray(xs)));
    }
    
    /**
     * {@link Functional#reduce(Monoid, Iterable)} specialized to sum of Integers.
     */
    public static final long sum(int... xs) {
        return sum(map(int2long, newArray(xs)));
    }

    /**
     * {@link Functional#reduce(Monoid, Iterable)} specialized to sum of Shorts.
     */
    public static final long sum(short... xs) {
        return sum(map(short2long, newArray(xs)));
    }
    
    
    
    /**
     * {@link Functional#reduce(Monoid, Iterable)} specialized to product of Longs.
     */
    public static final long product(Iterable<Long> xs) {
        return FunctionalImpl.product(xs);
    }
    
    /**
     * {@link Functional#reduce(Monoid, Iterable)} specialized to product of Longs.
     */
    public static final long product(long... xs) {
        return product(newList(newArray(xs)));
    }
    
    /**
     * {@link Functional#reduce(Monoid, Iterable)} specialized to product of Integers.
     */
    public static final long product(int... xs) {
        return product(map(Transformers.int2long, newArray(xs)));
    }
    
    /**
     * {@link Functional#reduce(Monoid, Iterable)} specialized to product of Shorts.
     */
    public static final long product(short... xs) {
        return product(map(Transformers.short2long, newArray(xs)));
    }
    
    
    
    /**
     * {@link Functional#range(Enumerable, Object)} specialized to Shorts.
     */
    public static final Iterable<Short> range(short fromInclusive) {
        return FunctionalImpl.range(Enumerables.shorts, fromInclusive);
    }
    
    /**
     * {@link Functional#range(Enumerable, Object)} specialized to Integers.
     */
    public static final Iterable<Integer> range(int fromInclusive) {
        return FunctionalImpl.range(Enumerables.ints, fromInclusive);
    }
    
    /**
     * {@link Functional#range(Enumerable, Object)} specialized to Longs.
     */
    public static final Iterable<Long> range(long fromInclusive) {
        return FunctionalImpl.range(Enumerables.longs, fromInclusive);
    }
    
    /**
     * {@link Functional#range(Enumerable, Object, Object)} specialized to Shorts.
     */
    public static final Iterable<Short> range(short fromInclusive, short toInclusive) {
        if (toInclusive < fromInclusive)
            return emptyList();
        return new RangeIterable<Short>(Enumerables.shorts, fromInclusive, toInclusive, toInclusive - fromInclusive + 1);
    }
    
    /**
     * {@link Functional#range(Enumerable, Object, Object)} specialized to Integers.
     */
    public static final Iterable<Integer> range(int fromInclusive, int toInclusive) {
        if (toInclusive < fromInclusive)
            return emptyList();
        return new RangeIterable<Integer>(Enumerables.ints, fromInclusive, toInclusive, toInclusive - fromInclusive + 1);
    }

    /**
     * {@link Functional#range(Enumerable, Object, Object)} specialized to Longs.
     */
    public static final Iterable<Long> range(long fromInclusive, long toInclusive) {
        if (toInclusive < fromInclusive)
            return emptyList();
        return new RangeIterable<Long>(Enumerables.longs, fromInclusive, toInclusive, toInclusive - fromInclusive + 1);
    }
    
    
    
    /**
     * @return Set union of {@code e1} and {@code e2}. Preserves iteration order.
     */
    public static final <T> Set<T> union(Set<T> e1, Set<T> e2) {
        return FunctionalImpl.reduce(Monoids.<T>setUnion(), newList(e1, e2));
    }
    
    /**
     * @return Set union of {@code e1}, {@code e2} and {@code e3}. Preserves iteration order.
     */
    public static final <T> Set<T> union(Set<T> e1, Set<T> e2, Set<T> e3) {
        return FunctionalImpl.reduce(Monoids.<T>setUnion(), newList(e1, e2, e3));
    }
    
    /**
     * @return Set union of {@code e1}, {@code e2}, {@code e3} and {@code e4}. Preserves iteration order.
     */
    public static final <T> Set<T> union(Set<T> e1, Set<T> e2, Set<T> e3, Set<T> e4) {
        return FunctionalImpl.reduce(Monoids.<T>setUnion(), newList(e1, e2, e3, e4));
    }
    
    /**
     * @return Set union of {@code e1}, {@code e2}, {@code e3}, {@code e4} and {@code e5}. Preserves iteration order.
     */
    public static final <T> Set<T> union(Set<T> e1, Set<T> e2, Set<T> e3, Set<T> e4, Set<T> e5) {
        return FunctionalImpl.reduce(Monoids.<T>setUnion(), newList(e1, e2, e3, e4, e5));
    }
    
    /**
     * @return Set union of {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5} and {@code e6}. Preserves iteration order.
     */
    public static final <T> Set<T> union(Set<T> e1, Set<T> e2, Set<T> e3, Set<T> e4, Set<T> e5, Set<T> e6) {
        return FunctionalImpl.reduce(Monoids.<T>setUnion(), newList(e1, e2, e3, e4, e5, e6));
    }
    
    /**
     * @return Set union of {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6} and all in {@code es}. Preserves iteration order.
     */
    public static final <T> Set<T> union(Set<T> e1, Set<T> e2, Set<T> e3, Set<T> e4, Set<T> e5, Set<T> e6, Set<T>... es) {
        return FunctionalImpl.reduce(Monoids.<T>setUnion(), FunctionalImpl.concat(newList(e1, e2, e3, e4, e5, e6), newList(es)));
    }
    
    /**
     * @return Set union of Sets in {@code es}. Preserves iteration order.
     */
    public static final <T> Set<T> union(Iterable<? extends Set<T>> es) {
        return FunctionalImpl.reduce(Monoids.<T>setUnion(), es);
    }
    
    
    
    /**
     * @return Set intersection of {@code e1} and {@code e2}.
     */
    public static final <T> Set<T> intersection(Set<T> e1, Set<T> e2) {
        return FunctionalImpl.fold(SemiGroups.<T>setIntersection(), newList(e1, e2)).get();
    }
    
    /**
     * @return Set intersection of {@code e1}, {@code e2} and {@code e3}.
     */
    public static final <T> Set<T> intersection(Set<T> e1, Set<T> e2, Set<T> e3) {
        return FunctionalImpl.fold(SemiGroups.<T>setIntersection(), newList(e1, e2, e3)).get();
    }
    
    /**
     * @return Set intersection of {@code e1}, {@code e2}, {@code e3} and {@code e4}.
     */
    public static final <T> Set<T> intersection(Set<T> e1, Set<T> e2, Set<T> e3, Set<T> e4) {
        return FunctionalImpl.fold(SemiGroups.<T>setIntersection(), newList(e1, e2, e3, e4)).get();
    }
    
    /**
     * @return Set intersection of {@code e1}, {@code e2}, {@code e3}, {@code e4} and {@code e5}.
     */
    public static final <T> Set<T> intersection(Set<T> e1, Set<T> e2, Set<T> e3, Set<T> e4, Set<T> e5) {
        return FunctionalImpl.fold(SemiGroups.<T>setIntersection(), newList(e1, e2, e3, e4, e5)).get();
    }
    
    /**
     * @return Set intersection of {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5} and {@code e6}.
     */
    public static final <T> Set<T> intersection(Set<T> e1, Set<T> e2, Set<T> e3, Set<T> e4, Set<T> e5, Set<T> e6) {
        return FunctionalImpl.fold(SemiGroups.<T>setIntersection(), newList(e1, e2, e3, e4, e5, e6)).get();
    }
    
    /**
     * @return Set intersection of {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6} and all in {@code es}.
     */
    public static final <T> Set<T> intersection(Set<T> e1, Set<T> e2, Set<T> e3, Set<T> e4, Set<T> e5, Set<T> e6, Set<T>... es) {
        return FunctionalImpl.fold(SemiGroups.<T>setIntersection(), FunctionalImpl.concat(newList(e1, e2, e3, e4, e5, e6), newList(es))).get();
    }
    
    /**
     * @return Set intersection of Sets in {@code es}.
     */
    public static final <T> Set<T> intersection(Iterable<? extends Set<T>> es) {
        return FunctionalImpl.fold(Collections.<T>emptySet(), SemiGroups.<T>setIntersection(), es);
    }
    
    
    /**
     * @return Intersecting groups of {@code sets}
     */
    public static final <T> Iterable<Set<T>> groupIntersecting(Iterable<Set<T>> sets) {
        if (sets == null) {
            return null;
        }
        if (Functional.isEmpty(sets)) {
            return emptySet();
        }
        final Set<T> first = Functional.head(sets);
        Pair<Iterable<Set<T>>,Iterable<Set<T>>> intersectsAndNot = Functional.partition(new Transformer<Set<T>, Either<Set<T>,Set<T>>>() {
            public Either<Set<T>,Set<T>> transform(Set<T> source) {
                return intersection(source, first).isEmpty() ? Either.<Set<T>,Set<T>>right(source) : Either.<Set<T>,Set<T>>left(source);
            };
        }, sets);
        Set<T> intersectingHead = newSet(Functional.flatten(intersectsAndNot.left()));
        List<Set<T>> notIntersectingHead = newList(intersectsAndNot.right());
        if (notIntersectingHead.isEmpty()) {
            // all intersect
            return Arrays.asList(intersectingHead);
        }
        if (intersectingHead.equals(first)) {
            // head doesn't expand anymore -> continue with the rest
            return Functional.cons(intersectingHead, groupIntersecting(notIntersectingHead));
        }
        return groupIntersecting(Functional.cons(intersectingHead, notIntersectingHead));
    }
}
