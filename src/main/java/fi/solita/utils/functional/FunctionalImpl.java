package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.it;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newMutableLinkedMap;
import static fi.solita.utils.functional.Collections.newMutableMap;
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
import java.util.Map.Entry;
import java.util.NoSuchElementException;

import fi.solita.utils.functional.Iterables.ConcatenatingIterable;
import fi.solita.utils.functional.Iterables.FilteringIterable;
import fi.solita.utils.functional.Iterables.FlatteningIterable;
import fi.solita.utils.functional.Iterables.RangeIterable;
import fi.solita.utils.functional.Iterables.TransformingIterable;
import fi.solita.utils.functional.Iterables.TransposingIterable;
import fi.solita.utils.functional.Iterables.ZippingIterable;

final class FunctionalImpl {
    @SuppressWarnings("unchecked")
    static final <T> Iterable<T> subtract(Iterable<? extends T> xs, final Collection<? extends T> toSubtract) {
        return  (Iterable<T>) (toSubtract == null ? xs : filter(new Predicate<T>() {
            @Override
            public final boolean accept(T object) {
                return !toSubtract.contains(object);
            }
        }, xs));
    }
    
    static final <T> Iterable<T> remove(T toRemove, Iterable<T> xs) {
        return filter(not(equalTo(toRemove)), xs);
    }
    
    static final <T> Option<T> find(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return headOption(filter(predicate, xs));
    }
    
    static final <K, V> Option<V> find(K key, Map<? super K, V> map) {
        return map == null ? null : Option.of(map.get(key));
    }
    
    static final <T> Iterable<T> filter(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return xs == null ? null : new FilteringIterable<T>(xs, predicate);
    }
    
    static final <T, E> Map<T, E> filter(Apply<Map.Entry<T, E>, Boolean> predicate, Map<T, E> map) {
        // to preserve iteration order
        return Collections.newLinkedMap(SemiGroups.<E>fail(), filter(predicate, map.entrySet()));
    }
    
    static final <S, T> Iterable<T> map(Apply<? super S, ? extends T> f, Iterable<S> xs) {
        return xs == null ? null : new TransformingIterable<S,T>(xs, f);
    }
    
    static final <S, T1, T2> Iterable<Pair<T1,T2>> map(final Apply<? super S, T1> f1, final Apply<? super S, T2> f2, Iterable<S> xs) {
        return xs == null ? null : new TransformingIterable<S,Pair<T1,T2>>(xs, new Transformer<S, Pair<T1,T2>>() {
            @Override
            public final Pair<T1,T2> transform(S source) {
                return Pair.of(f1.apply(source), f2.apply(source));
            }
        });
    }
    
    static final <S, T1, T2, T3> Iterable<Tuple3<T1,T2,T3>> map(final Apply<? super S, T1> f1, final Apply<? super S, T2> f2, final Apply<? super S, T3> f3, Iterable<S> xs) {
        return xs == null ? null : new TransformingIterable<S,Tuple3<T1,T2,T3>>(xs, new Transformer<S, Tuple3<T1,T2,T3>>() {
            @Override
            public final Tuple3<T1,T2,T3> transform(S source) {
                return Tuple.of(f1.apply(source), f2.apply(source), f3.apply(source));
            }
        });
    }
    
    static final <S, T1, T2, T3, T4> Iterable<Tuple4<T1,T2,T3,T4>> map(final Apply<? super S, T1> f1, final Apply<? super S, T2> f2, final Apply<? super S, T3> f3, final Apply<? super S, T4> f4, Iterable<S> xs) {
        return xs == null ? null : new TransformingIterable<S,Tuple4<T1,T2,T3,T4>>(xs, new Transformer<S, Tuple4<T1,T2,T3,T4>>() {
            @Override
            public final Tuple4<T1,T2,T3,T4> transform(S source) {
                return Tuple.of(f1.apply(source), f2.apply(source), f3.apply(source), f4.apply(source));
            }
        });
    }
    
    static final <K1, V1, K2, V2> Map<K2, V2> map(SemiGroup<V2> valueCombiner, Apply<? super Map.Entry<K1, V1>, ? extends Map.Entry<? extends K2, ? extends V2>> f, Map<K1, V1> map) {
        // to preserve iteration order
        Map<K2, V2> ret = newMutableLinkedMap();
        for (Map.Entry<K1, V1> k: map.entrySet()) {
            Entry<? extends K2, ? extends V2> e = f.apply(k);
            V2 v = ret.get(e.getKey());
            v = v == null ? e.getValue() : valueCombiner.apply(Pair.of(v, e.getValue()));
            ret.put(e.getKey(), v);
        }
        return ret;
    }
    
    static final <S, T> Iterable<T> flatMap(Apply<? super S, ? extends Iterable<? extends T>> f, Iterable<S> xs) {
        return flatten(map(f, xs));
    }
    
    static final <T> Iterable<T> flatten(Iterable<? extends Iterable<? extends T>> xs) {
        return xs == null ? null : new FlatteningIterable<T>(xs);
    }
    
    static final <T> void foreach(Apply<? super T, Void> procedure, Iterable<T> xs) {
        for (T t: xs) {
            procedure.apply(t);
        }
    }
    
    static final <T> void foreach(ApplyVoid<? super T> procedure, Iterable<T> xs) {
        for (T t: xs) {
            procedure.accept(t);
        }
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
        List<List<T>> target = estimatedSize.isDefined() ? Collections.<List<T>>newMutableListOfSize(estimatedSize.get() / groupSize) : Collections.<List<T>>newMutableList();
        Iterator<T> it = xs.iterator();
        while (it.hasNext()) {
            List<T> group = Collections.newMutableListOfSize((int)groupSize);
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
    static final <T> Iterable<Iterable<T>> group(Iterable<T> xs) {
        return group((Predicate<Map.Entry<T,T>>)(Object)tuple2elementsEqual, xs);
    }
    
    private static final Predicate<Tuple2<Object,Object>> tuple2elementsEqual = new Predicate<Tuple2<Object,Object>>() {
        @Override
        public final boolean accept(Tuple2<Object, Object> candidate) {
            return candidate._1.equals(candidate._2);
        }
    };
    
    static final <T> Iterable<Iterable<T>> group(Apply<Map.Entry<T,T>, Boolean> comparator, Iterable<T> xs) {
        return xs == null ? null : new Iterables.GroupingIterable<T>(xs, comparator);
    }
    
    /**
     * Non-lazy
     */
    static final <G, T> Map<G, List<T>> groupBy(Apply<? super T,G> f, Iterable<T> xs) {
        if (xs == null) {
            return null;
        }
        Map<G, List<T>> target = newMutableMap();
        for (T t: xs) {
            G g = f.apply(t);
            Option<List<T>> groupOption = find(g, target);
            List<T> group;
            if (groupOption.isDefined()) {
                group = groupOption.get();
            } else {
                group = Collections.newMutableList();
                target.put(g, group);
            }
            group.add(t);
        }
        return target;
    }
    
    static final <T> T head(Iterable<T> xs) {
        return xs == null ? null : xs.iterator().next();
    }
    
    static final <T> Option<T> headOption(Iterable<T> xs) {
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
    
    static final <T> Iterable<T> tail(Iterable<T> xs) {
        return drop(1, xs);
    }
    
    static final <T> T last(Iterable<T> xs) {
        return xs == null ? null : lastOption(xs).get();
    }
    
    static final <T> Option<T> lastOption(Iterable<T> xs) {
        if (xs == null) {
            return null;
        }
        if (xs instanceof Collection && ((Collection<T>) xs).isEmpty()) {
            return None();
        }
        
        if (xs instanceof List) {
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
    
    static final <T> Iterable<T> init(Iterable<T> xs) {
        // TODO: make lazier to not need the actual size
        return xs == null ? null : take(size(xs)-1, xs);
    }
    
    static final <T> Iterable<T> take(long amount, Iterable<T> xs) {
        return xs == null ? null : new Iterables.TakingIterable<T>(xs, amount);
    }

    static final <T> Iterable<T> drop(long amount, Iterable<T> xs) {
        return xs == null ? null : new Iterables.DroppingIterable<T>(xs, amount);
    }
    
    static final <T> Iterable<T> takeWhile(final Apply<? super T, Boolean> predicate, final Iterable<T> xs) {
        return xs == null ? null : new Iterable<T>() {
            public final Iterator<T> iterator() {
                return new Iterator<T>() {
                    private Option<T> next;
                    private Iterator<T> source = xs.iterator();
                    {
                        readNext();
                    }

                    public final boolean hasNext() {
                        return next.isDefined();
                    }

                    private final void readNext() {
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

                    public final T next() {
                        if (!next.isDefined()) {
                            throw new NoSuchElementException();
                        }
                        T ret = next.get();
                        readNext();
                        return ret;
                    }

                    public final void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
    
    static final <T> Iterable<T> dropWhile(final Apply<? super T, Boolean> predicate, final Iterable<T> xs) {
        return xs == null ? null : new Iterable<T>() {
            public final Iterator<T> iterator() {
                return new Iterator<T>() {
                    private boolean dropping = true;
                    private Iterator<T> source = xs.iterator();
                    private Option<T> next;
                    {
                        readNext();
                    }

                    public final boolean hasNext() {
                        return next.isDefined();
                    }

                    private final void readNext() {
                        next = source.hasNext() ? Some(source.next()) : Option.<T>None();
                        while (dropping && next.isDefined() && predicate.apply(next.get())) {
                            next = source.hasNext() ? Some(source.next()) : Option.<T>None();
                        }
                        dropping = false;
                    }

                    public final T next() {
                        if (!next.isDefined()) {
                            throw new NoSuchElementException();
                        }
                        T ret = next.get();
                        readNext();
                        return ret;
                    }

                    public final void remove() {
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
    
    static final <T,L,R> Pair<Iterable<L>, Iterable<R>> partition(Apply<? super T, Either<L,R>> f, Iterable<T> xs) {
        // TODO: a more efficient implementation
        Iterable<Either<L, R>> ys = map(f, xs);
        return xs == null ? null : Pair.of(map(Transformers.<L>eitherLeft().andThen(Transformers.<L>get()), filter(Predicates.isLeft, ys)), map(Transformers.<R>eitherRight().andThen(Transformers.<R>get()), filter(Predicates.isRight, ys)));
    }
    
    public static <T> Pair<Iterable<T>, Iterable<T>> split(int amount, Iterable<T> xs) {
        // TODO: a more efficient implementation
        return xs == null ? null : Pair.of(take(amount, xs), drop(amount, xs));
    }
    
    public static <T> Pair<T, Iterable<T>> split(Iterable<T> xs) {
        return split(1, xs).first(Transformers.<T>head());
    }
    
    public static final <T> Iterable<T> every(int nth, Iterable<T> xs) {
        // TODO: optimize...
        return Functional.map(Transformers.<T>right(), Functional.filter(Transformers.<Integer>_1().andThen(Predicates.divisible(nth)), Functional.zipWithIndex(xs)));
    }
    
    static final boolean isEmpty(Iterable<?> xs) {
        Option<Long> size = Iterables.resolveSize.apply(xs);
        if (size.isDefined()) {
            return size.get() == 0;
        }
        return !xs.iterator().hasNext();
    }
    
    static final long size(Iterable<?> xs) {
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
    
    static final <T extends Comparable<? super T>> Iterable<T> sort(Iterable<T> xs) {
        return sort(Ordering.Natural(), xs);
    }
    
    static final <T> Iterable<T> sort(Comparator<? super T> comparator, Iterable<T> xs) {
        if (xs == null) {
            return null;
        }
        Option<Long> size = Iterables.resolveSize.apply(xs);
        if (size.isDefined() && size.get() == 0) {
            return emptyList();
        }
        return new Iterables.SortingIterable<T>(xs, comparator);
    }
    
    static final <T extends SemiGroup<T>> Option<T> reduce(Iterable<? extends T> xs) {
        if (xs == null) {
            return null;
        }
        if (isEmpty(xs)) {
            return None();
        }
        return fold(head(xs), xs);
    }
    
    static final <T> T reduce(Monoid<T> monoid, Iterable<? extends T> xs) {
        return xs == null ? null : fold(monoid, cons(monoid.zero(), xs)).get();
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
        T ret = null;
        for (T t : xs) {
            if (ret == null) {
                ret = t;
            } else {
                ret = f.apply(Tuple.of(ret, t));
            }
        }
        return Option.of(ret);
    }
    
    static final long sum(Iterable<Long> xs) {
        return reduce(Monoids.longSum, xs);
    }
    
    static final long product(Iterable<Long> xs) {
        return reduce(Monoids.longProduct, xs);
    }
    
    @SuppressWarnings("unchecked")
    static final <T> Iterable<T> cons(T x, Iterable<? extends T> xs) {
        return x == null && xs == null ? null : x == null ? (Iterable<T>)xs : xs == null ? newList(x) : concat(Arrays.asList(x), xs);
    }
    
    @SuppressWarnings("unchecked")
    static final <T> Iterable<T> concat(Iterable<? extends T> a, Iterable<? extends T> b) {
        return a == null && b == null ? null : a == null ? (Iterable<T>)b : b == null ? (Iterable<T>)a : new ConcatenatingIterable<T>(Arrays.asList(a, b));
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
    static final <T extends Comparable<? super T>> Option<T> min(Iterable<T> xs) {
        if (xs == null) {
            return null;
        }
        xs = newList(xs);
        if (isEmpty(xs)) {
            return None();
        }
        return fold((Apply<Map.Entry<? extends T,? extends T>,T>)(Object)smaller, xs);
    }
    
    @SuppressWarnings("unchecked")
    static final <T extends Comparable<? super T>> Option<T> max(Iterable<T> xs) {
        if (xs == null) {
            return null;
        }
        xs = newList(xs);
        if (isEmpty(xs)) {
            return None();
        }
        return fold((Apply<Map.Entry<? extends T,? extends T>,T>)(Object)bigger, xs);
    }
    
    static final <T> Option<T> minBy(final Comparator<? super T> comparator, Iterable<T> xs) {
        if (xs == null) {
            return null;
        }
        xs = newList(xs);
        if (isEmpty(xs)) {
            return None();
        }
        return fold(new Function2<T, T, T>() {
             @Override
            public T apply(T t1, T t2) {
                return comparator.compare(t1, t2) > 0 ? t2 : t1;
            }
        }, xs);
    }
    
    static final <T> Option<T> maxBy(final Comparator<? super T> comparator, Iterable<T> xs) {
        if (xs == null) {
            return null;
        }
        xs = newList(xs);
        if (isEmpty(xs)) {
            return None();
        }
        return fold(new Function2<T, T, T>() {
             @Override
            public T apply(T t1, T t2) {
                return comparator.compare(t1, t2) < 0 ? t2 : t1;
            }
        }, xs);
    }
    
    static final <A,B> Iterable<Pair<A, B>> zip(Iterable<A> a, Iterable<B> b) {
        return a == null || b == null ? null : new ZippingIterable<A,B>(a, b);
    }
    
    @SuppressWarnings("unchecked")
    static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, Iterable<B> b, Iterable<C> c) {
        return map((Transformer<Pair<Pair<A, B>, C>, Tuple3<A, B, C>>)(Object)zip3Transformer, zip(zip(a, b), c));
    }
    
    @SuppressWarnings("unchecked")
    static final <A,B,C,D> Iterable<Tuple4<A, B, C, D>> zip(Iterable<A> a, Iterable<B> b, Iterable<C> c, Iterable<D> d) {
        return map((Transformer<Pair<Pair<A, B>, Pair<C,D>>, Tuple4<A, B, C, D>>)(Object)zip4Transformer, zip(zip(a, b), zip(c,d)));
    }
    
    static final <A> Iterable<Pair<Integer, A>> zipWithIndex(Iterable<A> xs) {
        return new ZippingIterable<Integer, A>(range(Enumerables.ints, 0), xs);
    }
    
    private static final Transformer<Tuple2<Tuple2<Object,Object>,Object>,Tuple3<Object,Object,Object>> zip3Transformer = new Transformer<Tuple2<Tuple2<Object, Object>, Object>, Tuple3<Object, Object, Object>>() {
        @Override
        public final Tuple3<Object, Object, Object> transform(Tuple2<Tuple2<Object, Object>, Object> source) {
            return source._1.append(source._2);
        }
    };
    
    private static final Transformer<Tuple2<Tuple2<Object,Object>,Tuple2<Object,Object>>,Tuple4<Object,Object,Object,Object>> zip4Transformer = new Transformer<Tuple2<Tuple2<Object, Object>, Tuple2<Object,Object>>, Tuple4<Object, Object, Object, Object>>() {
        @Override
        public final Tuple4<Object, Object, Object, Object> transform(Tuple2<Tuple2<Object, Object>, Tuple2<Object, Object>> source) {
            return source._1.join(source._2);
        }
    };
    
    static final <A, B> Pair<Iterable<A>, Iterable<B>> unzip(Iterable<Pair<A,B>> xs) {
        if (xs == null) {
            return null;
        }
        // TODO: a more efficient implementation
        return Pair.of(map(Transformers.<A>left(), xs), map(Transformers.<B>right(), xs));
    }
    
    static final <A, B, C> Tuple3<Iterable<A>, Iterable<B>, Iterable<C>> unzip3(Iterable<Tuple3<A,B,C>> xs) {
        if (xs == null) {
            return null;
        }
        // TODO: a more efficient implementation
        return Tuple.of(map(Transformers.<A>_1(), xs), map(Transformers.<B>_2(), xs), map(Transformers.<C>_3(), xs));
    }
    
    static final <T> Iterable<T> range(Enumerable<T> enumeration, T from) {
        return enumeration == null ? null : new RangeIterable<T>(enumeration, from, Option.<T>None());
    }
    
    static final <T> Iterable<T> range(Enumerable<T> enumeration, T from, T toInclusive) {
        return enumeration == null ? null : new RangeIterable<T>(enumeration, from, Some(toInclusive));
    }
    
    @SuppressWarnings("unchecked")
    static final <T> Iterable<List<T>> rangify(final Enumerable<T> enumeration, Iterable<T> xs) {
        Iterable<Iterable<T>> ret = FunctionalImpl.group(new Predicate<Map.Entry<T,T>>() {
            public boolean accept(Map.Entry<T,T> candidate) {
                Option<T> succ = enumeration.succ(candidate.getKey());
                return succ.isDefined() && succ.get().equals(candidate.getValue());
            };
        }, xs);
        return (Iterable<List<T>>)(Object)map(minAndMax, ret);
    }
    
    static final Transformer<Iterable<?>, List<?>> minAndMax = new Transformer<Iterable<?>,List<?>>() {
        public List<?> transform(Iterable<?> source) {
            List<?> ts = newList(source);
            return ts.size() == 1 ? ts : newList(head(ts), last(ts));
        };
    };
    
    static final String mkString(Iterable<Character> xs) {
        if (xs == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (char c: xs) {
            sb.append(c);
        }
        return sb.toString();
    }
    
    static final String mkString(CharSequence delimiter, Iterable<? extends CharSequence> xs) {
        if (xs == null) {
            return null;
        }
        xs = newList(xs);
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
    
    static final <T> Iterable<T> reverse(Iterable<T> xs) {
        return xs == null ? null : new Iterables.ReversingIterable<T>(xs);
    }
    
    static final <T> Iterable<T> distinct(Iterable<T> xs) {
        return xs == null ? null : new Iterables.DistinctIterable<T>(xs);
    }
    
    static final <T,R> Iterable<R> sequence(final T value, Iterable<? extends Apply<? super T,? extends R>> fs) {
        return map(new Transformer<Apply<? super T,? extends R>, R>() {
            @Override
            public final R transform(Apply<? super T,? extends R> source) {
                return source.apply(value);
            }
        }, fs);
    }
    
    static final <T> Iterable<Iterable<T>> transpose(Iterable<? extends Iterable<T>> xs) {
        return xs == null ? null : new TransposingIterable<T>(xs);
    }
    
    static final CharSequence unlines(Iterable<? extends CharSequence> xs) {
        if (xs == null) {
            return null;
        }
        Iterable<String> lineSeparators = Functional.repeat(System.getProperty("line.separator"));
        Option<? extends CharSequence> first = headOption(xs);
        if (!first.isDefined()) {
            return "";
        }
        Iterable<CharSequence> rest = map(new Transformer<Tuple2<String,? extends CharSequence>,CharSequence>() {
            @Override
            public final CharSequence transform(Tuple2<String, ? extends CharSequence> source) {
                return Functional.concat(source._1, source._2);
            }
        }, zip(lineSeparators, tail(xs)));
        return it(flatten(map(FunctionalC.charSeq2iterable, cons(first.get(), rest))));
    }
}