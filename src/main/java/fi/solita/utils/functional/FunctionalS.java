package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.newArray;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Transformers.int2long;
import static fi.solita.utils.functional.Transformers.short2long;

import java.util.Set;

import fi.solita.utils.functional.Iterables.RangeIterable;

public abstract class FunctionalS extends FunctionalA {
    public static final long sum(Iterable<Long> xs) {
        return FunctionalImpl.sum(xs);
    }
    
    public static final long sum(long... xs) {
        return sum(newList(newArray(xs)));
    }
    
    public static final long sum(int... xs) {
        return sum(map(int2long, newArray(xs)));
    }

    public static final long sum(short... xs) {
        return sum(map(short2long, newArray(xs)));
    }
    
    
    
    public static final long product(Iterable<Long> xs) {
        return FunctionalImpl.product(xs);
    }
    
    public static final long product(long... xs) {
        return product(newList(newArray(xs)));
    }
    
    public static final long product(int... xs) {
        return product(map(Transformers.int2long, newArray(xs)));
    }
    
    public static final long product(short... xs) {
        return product(map(Transformers.short2long, newArray(xs)));
    }
    
    
    
    public static final Iterable<Short> range(short from) {
        return FunctionalImpl.range(Enumerables.shorts, from);
    }
    
    public static final Iterable<Integer> range(int from) {
        return FunctionalImpl.range(Enumerables.ints, from);
    }
    
    public static final Iterable<Long> range(long from) {
        return FunctionalImpl.range(Enumerables.longs, from);
    }
    
    public static final Iterable<Short> range(short from, short toInclusive) {
        if (toInclusive < from)
            return emptyList();
        return new RangeIterable<Short>(Enumerables.shorts, from, toInclusive, toInclusive - from + 1);
    }
    
    public static final Iterable<Integer> range(int from, int toInclusive) {
        if (toInclusive < from)
            return emptyList();
        return new RangeIterable<Integer>(Enumerables.ints, from, toInclusive, toInclusive - from + 1);
    }

    public static final Iterable<Long> range(long from, long toInclusive) {
        if (toInclusive < from)
            return emptyList();
        return new RangeIterable<Long>(Enumerables.longs, from, toInclusive, toInclusive - from + 1);
    }
    
    
    
    public static final <T> Set<T> union(Set<T> e1, Set<T> e2) {
        return FunctionalImpl.reduce(Monoids.<T>setUnion(), newList(e1, e2));
    }
    
    public static final <T> Set<T> union(Set<T> e1, Set<T> e2, Set<T> e3) {
        return FunctionalImpl.reduce(Monoids.<T>setUnion(), newList(e1, e2, e3));
    }
    
    public static final <T> Set<T> union(Set<T> e1, Set<T> e2, Set<T> e3, Set<T> e4) {
        return FunctionalImpl.reduce(Monoids.<T>setUnion(), newList(e1, e2, e3, e4));
    }
    
    public static final <T> Set<T> union(Set<T> e1, Set<T> e2, Set<T> e3, Set<T> e4, Set<T> e5) {
        return FunctionalImpl.reduce(Monoids.<T>setUnion(), newList(e1, e2, e3, e4, e5));
    }
    
    public static final <T> Set<T> union(Set<T> e1, Set<T> e2, Set<T> e3, Set<T> e4, Set<T> e5, Set<T> e6) {
        return FunctionalImpl.reduce(Monoids.<T>setUnion(), newList(e1, e2, e3, e4, e5, e6));
    }
    
    public static final <T> Set<T> union(Set<T> e1, Set<T> e2, Set<T> e3, Set<T> e4, Set<T> e5, Set<T> e6, Set<T>... es) {
        return FunctionalImpl.reduce(Monoids.<T>setUnion(), FunctionalImpl.concat(newList(e1, e2, e3, e4, e5, e6), newList(es)));
    }
    
    
    
    public static final <T> Set<T> intersection(Set<T> e1, Set<T> e2) {
        return FunctionalImpl.reduce(Monoids.<T>setIntersection(), newList(e1, e2));
    }
    
    public static final <T> Set<T> intersection(Set<T> e1, Set<T> e2, Set<T> e3) {
        return FunctionalImpl.reduce(Monoids.<T>setIntersection(), newList(e1, e2, e3));
    }
    
    public static final <T> Set<T> intersection(Set<T> e1, Set<T> e2, Set<T> e3, Set<T> e4) {
        return FunctionalImpl.reduce(Monoids.<T>setIntersection(), newList(e1, e2, e3, e4));
    }
    
    public static final <T> Set<T> intersection(Set<T> e1, Set<T> e2, Set<T> e3, Set<T> e4, Set<T> e5) {
        return FunctionalImpl.reduce(Monoids.<T>setIntersection(), newList(e1, e2, e3, e4, e5));
    }
    
    public static final <T> Set<T> intersection(Set<T> e1, Set<T> e2, Set<T> e3, Set<T> e4, Set<T> e5, Set<T> e6) {
        return FunctionalImpl.reduce(Monoids.<T>setIntersection(), newList(e1, e2, e3, e4, e5, e6));
    }
    
    public static final <T> Set<T> intersection(Set<T> e1, Set<T> e2, Set<T> e3, Set<T> e4, Set<T> e5, Set<T> e6, Set<T>... es) {
        return FunctionalImpl.reduce(Monoids.<T>setIntersection(), FunctionalImpl.concat(newList(e1, e2, e3, e4, e5, e6), newList(es)));
    }
}
