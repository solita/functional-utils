package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.it;
import static fi.solita.utils.functional.Collections.newMap;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Predicates.equalTo;
import static fi.solita.utils.functional.Predicates.not;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import fi.solita.utils.functional.Iterables.ConcatenatingIterable;
import fi.solita.utils.functional.Iterables.FilteringIterable;
import fi.solita.utils.functional.Iterables.FlatteningIterable;
import fi.solita.utils.functional.Iterables.RangeIterable;
import fi.solita.utils.functional.Iterables.TransformingIterable;
import fi.solita.utils.functional.Iterables.TransposingIterable;
import fi.solita.utils.functional.Iterables.ZippingIterable;

final class FunctionalImpl {
    /**
     * Returns a new iterable <code>a - b</code>, i.e. one that contains all elements of <code>a</code> that
     * don't exist in <code>b</code>.
     */
    @SuppressWarnings("unchecked")
    static <T> Iterable<T> subtract(Iterable<? extends T> a, final Collection<? extends T> b) {
        return (Iterable<T>) filter(new Predicate<T>() {
            @Override
            public boolean accept(T object) {
                return !b.contains(object);
            }
        }, a);
    }
    
    static <T> Iterable<T> remove(T toRemove, Iterable<T> xs) {
        return filter(not(equalTo(toRemove)), xs);
    }
    
    static final <T> Option<T> find(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return headOption(filter(predicate, xs));
    }
    
    static final <K, V> Option<V> find(K key, Map<? extends K, V> map) {
        return map == null ? null : Option.of(map.get(key));
    }
    
    static final <T> Iterable<T> filter(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return xs == null ? null : new FilteringIterable<T>(xs, predicate);
    }
    
    static final <T, E> Map<T, E> filter(Apply<Map.Entry<T, E>, Boolean> predicate, Map<T, E> map) {
        return Collections.newMap(filter(predicate, map.entrySet()));
    }
    
    static final <S, T> Iterable<T> map(Apply<? super S, ? extends T> f, Iterable<S> xs) {
        return xs == null ? null : new TransformingIterable<S,T>(xs, f);
    }
    
    static final <S, T1, T2> Iterable<Pair<T1,T2>> map(final Apply<? super S, T1> f1, final Apply<? super S, T2> f2, Iterable<S> xs) {
        return xs == null ? null : new TransformingIterable<S,Pair<T1,T2>>(xs, new Transformer<S, Pair<T1,T2>>() {
            @Override
            public Pair<T1,T2> transform(S source) {
                return Pair.of(f1.apply(source), f2.apply(source));
            }
        });
    }
    
    static final <S, T1, T2, T3> Iterable<Tuple3<T1,T2,T3>> map(final Apply<? super S, T1> f1, final Apply<? super S, T2> f2, final Apply<? super S, T3> f3, Iterable<S> xs) {
        return xs == null ? null : new TransformingIterable<S,Tuple3<T1,T2,T3>>(xs, new Transformer<S, Tuple3<T1,T2,T3>>() {
            @Override
            public Tuple3<T1,T2,T3> transform(S source) {
                return Tuple.of(f1.apply(source), f2.apply(source), f3.apply(source));
            }
        });
    }
    
    static final <S, T1, T2, T3, T4> Iterable<Tuple4<T1,T2,T3,T4>> map(final Apply<? super S, T1> f1, final Apply<? super S, T2> f2, final Apply<? super S, T3> f3, final Apply<? super S, T4> f4, Iterable<S> xs) {
        return xs == null ? null : new TransformingIterable<S,Tuple4<T1,T2,T3,T4>>(xs, new Transformer<S, Tuple4<T1,T2,T3,T4>>() {
            @Override
            public Tuple4<T1,T2,T3,T4> transform(S source) {
                return Tuple.of(f1.apply(source), f2.apply(source), f3.apply(source), f4.apply(source));
            }
        });
    }
    
    static final <K1, V1, K2, V2> Map<K2, V2> map(Apply<? super Map.Entry<K1, V1>, ? extends Map.Entry<? extends K2, ? extends V2>> f, Map<K1, V1> map) {
        return Collections.newMap(map(f, map.entrySet()));
    }
    
    static final <S, T> Iterable<T> flatMap(Apply<? super S, ? extends Iterable<? extends T>> f, Iterable<S> xs) {
        return flatten(map(f, xs));
    }
    
    static <T> Iterable<T> flatten(Iterable<? extends Iterable<? extends T>> xs) {
        return xs == null ? null : new FlatteningIterable<T>(xs);
    }
    
    static final <T> void foreach(Apply<? super T, Void> procedure, Iterable<T> xs) {
        for (T t: xs) {
            procedure.apply(t);
        }
    }
    
    @SuppressWarnings("unchecked")
    static final <T> void foreach(ApplyVoid<? super T> procedure, Iterable<T> xs) {
        foreach((Apply<? super T, Void>)procedure, xs);
    }
    
    /**
     * Non-lazy
     */
    static final <T> Iterable<List<T>> grouped(long groupSize, Iterable<T> xs) {
        if (groupSize <= 0) {
            throw new IllegalArgumentException("size must be positive");
        }
        if (xs == null) {
            return null;
        }

        Option<Long> estimatedSize = Iterables.resolveSize.apply(xs);
        List<List<T>> target = estimatedSize.isDefined() ? Collections.<List<T>>newListOfSize(estimatedSize.get() / groupSize) : Collections.<List<T>>newList();
        Iterator<T> it = xs.iterator();
        while (it.hasNext()) {
            List<T> group = Collections.newListOfSize((int)groupSize);
            for (int i = 0; i < groupSize; ++i) {
                if (it.hasNext()) {
                    group.add(it.next());
                }
            }
            if (!group.isEmpty()) {
                target.add(group);
            }
        }
        return target;
    }
    
    @SuppressWarnings("unchecked")
    static <T> Iterable<Iterable<T>> group(Iterable<T> xs) {
        return group((Predicate<Tuple2<T,T>>)(Object)tuple2elementsEqual, xs);
    }
    
    private static final Predicate<Tuple2<Object,Object>> tuple2elementsEqual = new Predicate<Tuple2<Object,Object>>() {
        @Override
        public boolean accept(Tuple2<Object, Object> candidate) {
            return candidate._1.equals(candidate._2);
        }
    };
    
    static final <T> Iterable<Iterable<T>> group(Apply<Tuple2<T,T>, Boolean> comparator, Iterable<T> xs) {
        return xs == null ? null : new Iterables.GroupingIterable<T>(xs, comparator);
    }
    
    /**
     * Non-lazy
     */
    static <G, T> Map<G, List<T>> groupBy(Apply<? super T,G> f, Iterable<T> xs) {
        if (xs == null) {
            return null;
        }
        Map<G, List<T>> target = newMap();
        for (T t: xs) {
            G g = f.apply(t);
            Option<List<T>> groupOption = find(g, target);
            List<T> group;
            if (groupOption.isDefined()) {
                group = groupOption.get();
            } else {
                group = Collections.newList();
                target.put(g, group);
            }
            group.add(t);
        }
        return target;
    }
    
    static <T> T head(Iterable<T> xs) {
        return xs == null ? null : xs.iterator().next();
    }
    
    static <T> Option<T> headOption(Iterable<T> xs) {
        if (xs == null) {
            return null;
        }
        Iterator<T> it = xs.iterator();
        if (it.hasNext()) {
            return Some(it.next());
        } else {
            return None();
        }
    }
    
    static <T> Iterable<T> tail(Iterable<T> xs) {
        return drop(1, xs);
    }
    
    static <T> T last(Iterable<T> xs) {
        return xs == null ? null : lastOption(xs).get();
    }
    
    static <T> Option<T> lastOption(Iterable<T> xs) {
        if (xs == null) {
            return null;
        }
        if (xs instanceof List) {
            if (((List<T>) xs).isEmpty()) {
                return None();
            }
            return Some(((List<T>) xs).get(((List<T>) xs).size()-1));
        } else {
            Iterator<T> it = xs.iterator();
            if (it.hasNext()) {
                T ret = it.next();
                while (it.hasNext()) {
                    ret = it.next();
                }
                return Some(ret);
            } else {
                return None();
            }
        }
    }
    
    static <T> Iterable<T> init(Iterable<T> xs) {
        return xs == null ? null : take(size(xs)-1, xs);
    }
    
    static <T> Iterable<T> take(long amount, Iterable<T> xs) {
        return xs == null ? null : new Iterables.TakingIterable<T>(xs, amount);
    }

    static <T> Iterable<T> drop(long amount, Iterable<T> xs) {
        return xs == null ? null : new Iterables.DroppingIterable<T>(xs, amount);
    }
    
    static final <T> Iterable<T> takeWhile(final Apply<? super T, Boolean> predicate, final Iterable<T> xs) {
        return xs == null ? null : new Iterable<T>() {
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    private Option<T> next;
                    private Iterator<T> source = xs.iterator();
                    {
                        readNext();
                    }

                    public boolean hasNext() {
                        return next.isDefined();
                    }

                    private void readNext() {
                        if (!source.hasNext()) {
                            next = None();
                        } else {
                            T n = source.next();
                            if (predicate.apply(n)) {
                                next = Some(n);
                            } else {
                                next = None();
                            }
                        }
                    }

                    public T next() {
                        if (!next.isDefined()) {
                            throw new NoSuchElementException();
                        }
                        T ret = next.get();
                        readNext();
                        return ret;
                    }

                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
    
    static final <T> Iterable<T> dropWhile(final Apply<? super T, Boolean> predicate, final Iterable<T> xs) {
        return xs == null ? null : new Iterable<T>() {
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    private boolean dropping = true;
                    private Iterator<T> source = xs.iterator();
                    private Option<T> next;
                    {
                        readNext();
                    }

                    public boolean hasNext() {
                        return next.isDefined();
                    }

                    private void readNext() {
                        next = source.hasNext() ? Some(source.next()) : Option.<T>None();
                        while (dropping && next.isDefined() && predicate.apply(next.get())) {
                            next = source.hasNext() ? Some(source.next()) : Option.<T>None();
                        }
                        dropping = false;
                    }

                    public T next() {
                        if (!next.isDefined()) {
                            throw new NoSuchElementException();
                        }
                        T ret = next.get();
                        readNext();
                        return ret;
                    }

                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
    
    static final <T> Pair<Iterable<T>, Iterable<T>> span(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        // TODO: a more efficient implementation
        return xs == null ? null : Pair.of(takeWhile(predicate, xs), dropWhile(predicate, xs));
    }
    
    static boolean isEmpty(Iterable<?> xs) {
        return !xs.iterator().hasNext();
    }
    
    static long size(Iterable<?> xs) {
        for (long size: Iterables.resolveSize.apply(xs)) {
            return size;
        }
        int s = 0;
        for (@SuppressWarnings("unused") Object o: xs) {
            s++;
        }
        return s;
    }
    
    static final <T> boolean exists(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return !isEmpty(filter(predicate, xs));
    }
    
    static final <T> boolean forall(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return !exists(Predicates.not(predicate), xs);
    }
    
    static <T extends Comparable<? super T>> Iterable<T> sort(Iterable<T> xs) {
        return sort(Ordering.Natural(), xs);
    }
    
    static final <T> Iterable<T> sort(Comparator<? super T> comparator, Iterable<T> xs) {
        if (xs == null) {
            return null;
        }
        if (isEmpty(xs)) {
            return emptyList();
        }
        return new Iterables.SortingIterable<T>(xs, comparator);
    }
    
    static <T extends SemiGroup<T>> Option<T> reduce(Iterable<? extends T> xs) {
        if (xs == null) {
            return null;
        }
        if (isEmpty(xs)) {
            return None();
        }
        return fold(head(xs), xs);
    }
    
    static final <T> T reduce(Monoid<T> m, Iterable<? extends T> xs) {
        return xs == null ? null : fold(m, cons(m.zero(), xs)).get();
    }
    
    static final <T,Z> Z fold(Z zero, Apply<Map.Entry<? extends Z,? extends T>, Z> f, Iterable<? extends T> xs) {
        if (xs == null) {
            return null;
        }
        Z ret = zero;
        for (T t : xs) {
            ret = f.apply(Tuple.of(ret, t));
        }
        return ret;
    }
    
    /**
     * @return <i>None</i> if <i>elements</i> is empty
     */
    static final <T> Option<T> fold(Apply<Map.Entry<? extends T,? extends T>, T> f, Iterable<? extends T> xs) {
        if (xs == null) {
            return null;
        }
        Option<? extends T> h = headOption(xs);
        if (h.isDefined()) {
            T ret = h.get();
            for (T t : drop(1, xs)) {
                ret = f.apply(Tuple.of(ret, t));
            }
            return Some(ret);
        }
        return None();
    }
    
    static long sum(Iterable<Long> xs) {
        return reduce(Monoids.longSum, xs);
    }
    
    static long product(Iterable<Long> xs) {
        return reduce(Monoids.longProduct, xs);
    }
    
    @SuppressWarnings("unchecked")
    static <T> Iterable<T> cons(T x, Iterable<? extends T> xs) {
        return concat(Arrays.asList(x), xs);
    }
    
    @SuppressWarnings("unchecked")
    static <T> Iterable<T> concat(Iterable<? extends T> a, Iterable<? extends T> b) {
        return a == null || b == null ? null : new ConcatenatingIterable<T>(Arrays.asList(a, b));
    }
    
    @SuppressWarnings("unchecked")
    static <T extends Comparable<T>> Option<T> min(Iterable<T> xs) {
        if (xs == null) {
            return null;
        }
        if (isEmpty(xs)) {
            return None();
        }
        return Some(fold(head(xs), (Apply<Map.Entry<? extends T,? extends T>,T>)(Object)smaller, tail(xs)));
    }
    
    @SuppressWarnings("rawtypes")
    private static final Function2<Comparable,Comparable,Comparable> smaller = new Function2<Comparable, Comparable, Comparable>() {
        @SuppressWarnings("unchecked")
        @Override
        public Comparable apply(Comparable t1, Comparable t2) {
            return t1.compareTo(t2) > 0 ? t2 : t1;
        }
    };
    
    @SuppressWarnings("rawtypes")
    private static final Function2<Comparable,Comparable,Comparable> bigger = new Function2<Comparable, Comparable, Comparable>() {
        @SuppressWarnings("unchecked")
        @Override
        public Comparable apply(Comparable t1, Comparable t2) {
            return t1.compareTo(t2) < 0 ? t2 : t1;
        }
    };
    
    @SuppressWarnings("unchecked")
    static <T extends Comparable<T>> Option<T> max(Iterable<T> xs) {
        if (xs == null) {
            return null;
        }
        if (isEmpty(xs)) {
            return None();
        }
        return Some(fold(head(xs), (Apply<Map.Entry<? extends T,? extends T>,T>)(Object)bigger, tail(xs)));
    }
    
    static <A,B> Iterable<Tuple2<A, B>> zip(Iterable<A> a, Iterable<B> b) {
        return a == null || b == null ? null : new ZippingIterable<A,B>(a, b);
    }
    
    @SuppressWarnings("unchecked")
    static <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, Iterable<B> b, Iterable<C> c) {
        return map((Transformer<Tuple2<Tuple2<A, B>, C>, Tuple3<A, B, C>>)(Object)zip3Transformer, zip(zip(a, b), c));
    }
    
    private static Transformer<Tuple2<Tuple2<Object,Object>,Object>,Tuple3<Object,Object,Object>> zip3Transformer = new Transformer<Tuple2<Tuple2<Object, Object>, Object>, Tuple3<Object, Object, Object>>() {
        @Override
        public Tuple3<Object, Object, Object> transform(Tuple2<Tuple2<Object, Object>, Object> source) {
            return source._1.append(source._2);
        }
    };
    
    static <T> Iterable<T> range(Enumerable<T> enumeration, T from) {
        return enumeration == null ? null : new RangeIterable<T>(enumeration, from, Option.<T>None());
    }
    
    static <T> Iterable<T> range(Enumerable<T> enumeration, T from, T toInclusive) {
        return enumeration == null ? null : new RangeIterable<T>(enumeration, from, Some(toInclusive));
    }
    
    static String mkString(Iterable<Character> xs) {
        if (xs == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (char c: xs) {
            sb.append(c);
        }
        return sb.toString();
    }
    
    static String mkString(CharSequence delimiter, Iterable<? extends CharSequence> xs) {
        if (xs == null) {
            return null;
        }
        if (isEmpty(xs)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(head(xs));
        for (CharSequence s: tail(xs)) {
            sb.append(delimiter);
            sb.append(s);
        }
        return sb.toString();
    }
    
    static <T> Iterable<T> reverse(Iterable<T> xs) {
        return xs == null ? null : new Iterables.ReversingIterable<T>(xs);
    }
    
    static <T> Iterable<T> distinct(Iterable<T> xs) {
        return filter(new DistinctPredicate<T>(), xs);
    }
    
    protected static final class DistinctPredicate<T> extends Predicate<T> {
        private final Set<T> visited = newSet();
        @Override
        public boolean accept(T candidate) {
            boolean ret = !visited.contains(candidate);
            visited.add(candidate);
            return ret;
        }
    };
    
    static final <T,R> Iterable<R> sequence(final T value, Iterable<? extends Apply<? super T,? extends R>> fs) {
        return map(new Transformer<Apply<? super T,? extends R>, R>() {
            @Override
            public R transform(Apply<? super T,? extends R> source) {
                return source.apply(value);
            }
        }, fs);
    }
    
    static <T> Iterable<Iterable<T>> transpose(Iterable<? extends Iterable<T>> xs) {
        return xs == null ? null : new TransposingIterable<T>(xs);
    }
    
    static CharSequence unlines(Iterable<? extends CharSequence> xs) {
        if (xs == null) {
            return null;
        }
        Iterable<String> lineSeparators = Functional.repeat(System.getProperty("line.separator"));
        CharSequence first = head(xs);
        Iterable<CharSequence> rest = map(new Transformer<Tuple2<String,? extends CharSequence>,CharSequence>() {
            @Override
            public CharSequence transform(Tuple2<String, ? extends CharSequence> source) {
                return Functional.concat(source._1, source._2);
            }
        }, zip(lineSeparators, tail(xs)));
        return it(flatten(map(FunctionalC.charSeq2iterable, cons(first, rest))));
    }
}