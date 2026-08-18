package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.it;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newMutableLinkedMap;
import static fi.solita.utils.functional.Collections.newMutableMap;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;

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
        return  (Iterable<T>) (toSubtract == null ? xs : filter(x -> !toSubtract.contains(x), xs));
    }
    
    static final <T> Iterable<T> remove(T toRemove, Iterable<? extends T> xs) {
        return filter(x -> !toRemove.equals(x), xs);
    }
    
    static final <T> Optional<T> find(java.util.function.Predicate<? super T> predicate, Iterable<? extends T> xs) {
        return headOptional(filter(predicate, xs));
    }
    
    static final <K, V> Optional<V> find(K key, Map<? super K, ? extends V> map) {
        return map == null ? null : Optional.ofNullable(map.get(key));
    }
    
    static final <T> Iterable<T> filter(java.util.function.Predicate<? super T> predicate, Iterable<? extends T> xs) {
        return xs == null ? null : new FilteringIterable<T>(xs, predicate);
    }
    
    static final <T, E> Map<T, E> filter(BiPredicate<? super T, ? super E> predicate, Map<T, E> map) {
        // to preserve iteration order
        return Collections.newLinkedMap(SemiGroups.<E>fail(), filter(e -> predicate.test(e.getKey(), e.getValue()), map.entrySet()));
    }
    
    static final <S, T> Iterable<T> map(java.util.function.Function<? super S, ? extends T> f, Iterable<? extends S> xs) {
        return xs == null ? null : new TransformingIterable<S,T>(xs, f);
    }
    
    static final <S, T1, T2> Iterable<Pair<T1,T2>> map(final java.util.function.Function<? super S, ? extends T1> f1, final java.util.function.Function<? super S, ? extends T2> f2, Iterable<? extends S> xs) {
        return xs == null ? null : new TransformingIterable<S,Pair<T1,T2>>(xs, source -> Pair.of(f1.apply(source), f2.apply(source)));
    }
    
    static final <S, T1, T2, T3> Iterable<Tuple3<T1,T2,T3>> map(final java.util.function.Function<? super S, ? extends T1> f1, final java.util.function.Function<? super S, ? extends T2> f2, final java.util.function.Function<? super S, ? extends T3> f3, Iterable<? extends S> xs) {
        return xs == null ? null : new TransformingIterable<S,Tuple3<T1,T2,T3>>(xs, source -> Tuple.of(f1.apply(source), f2.apply(source), f3.apply(source)));
    }
    
    static final <S, T1, T2, T3, T4> Iterable<Tuple4<T1,T2,T3,T4>> map(final java.util.function.Function<? super S, ? extends T1> f1, final java.util.function.Function<? super S, ? extends T2> f2, final java.util.function.Function<? super S, ? extends T3> f3, final java.util.function.Function<? super S, ? extends T4> f4, Iterable<? extends S> xs) {
        return xs == null ? null : new TransformingIterable<S,Tuple4<T1,T2,T3,T4>>(xs, source -> Tuple.of(f1.apply(source), f2.apply(source), f3.apply(source), f4.apply(source)));
    }
    
    static final <K1, V1, K2, V2> Map<K2, V2> map(SemiGroup<V2> valueCombiner, BiFunction<? super K1, ? super V1, ? extends Map.Entry<K2,V2>> f, Map<K1, V1> map) {
        // to preserve iteration order
        Map<K2, V2> ret = newMutableLinkedMap();
        for (Map.Entry<K1, V1> k: map.entrySet()) {
            Map.Entry<? extends K2, ? extends V2> e = f.apply(k.getKey(), k.getValue());
            V2 v = ret.get(e.getKey());
            v = v == null ? e.getValue() : valueCombiner.apply(v, e.getValue());
            ret.put(e.getKey(), v);
        }
        return ret;
    }
    
    static final <S, T> Iterable<T> flatMap(java.util.function.Function<? super S, ? extends Iterable<? extends T>> f, Iterable<? extends S> xs) {
        return flatten(map(f, xs));
    }
    
    static final <T> Iterable<T> flatten(Iterable<? extends Iterable<? extends T>> xs) {
        return xs == null ? null : new FlatteningIterable<T>(xs);
    }
    
    static final <T> void foreach(java.util.function.Function<? super T, Void> procedure, Iterable<? extends T> xs) {
        for (T t: xs) {
            procedure.apply(t);
        }
    }
    
    static final <T> void foreach(Consumer<? super T> procedure, Iterable<? extends T> xs) {
        for (T t: xs) {
            procedure.accept(t);
        }
    }
    
    /**
     * Non-lazy
     */
    static final <T> Iterable<List<T>> grouped(long groupSize, Iterable<? extends T> xs) {
        if (groupSize <= 0) {
            throw new IllegalArgumentException("size must be positive");
        }
        if (xs == null) {
            return null;
        }

        Optional<Long> estimatedSize = Iterables.resolveSize.apply(xs);
        List<List<T>> target = estimatedSize.isPresent() ? Collections.<List<T>>newMutableListOfSize(estimatedSize.get() / groupSize) : Collections.<List<T>>newMutableList();
        Iterator<? extends T> it = xs.iterator();
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
    
    static final <T> Iterable<Iterable<T>> group(Iterable<? extends T> xs) {
        return group((a,b) -> a.equals(b), xs);
    }
    
    static final <T> Iterable<Iterable<T>> group(BiPredicate<? super T, ? super T> comparator, Iterable<? extends T> xs) {
        return xs == null ? null : new Iterables.GroupingIterable<T>(xs, comparator);
    }
    
    /**
     * Non-lazy
     */
    static final <G, T> Map<G, List<T>> groupBy(java.util.function.Function<? super T,? extends G> f, Iterable<? extends T> xs) {
        if (xs == null) {
            return null;
        }
        Map<G, List<T>> target = newMutableMap();
        for (T t: xs) {
            G g = f.apply(t);
            Optional<List<T>> groupOption = find(g, target);
            List<T> group;
            if (groupOption.isPresent()) {
                group = groupOption.get();
            } else {
                group = Collections.newMutableList();
                target.put(g, group);
            }
            group.add(t);
        }
        return target;
    }
    
    static final <T> T head(Iterable<? extends T> xs) {
        return xs == null ? null : xs.iterator().next();
    }
    
    static final <T> Optional<T> headOptional(Iterable<? extends T> xs) {
        if (xs == null) {
            return null;
        }
        Iterator<? extends T> it = xs.iterator();
        if (it.hasNext()) {
            return Optional.of(it.next());
        } else {
            return Optional.empty();
        }
    }
    
    static final <T> Iterable<T> tail(Iterable<? extends T> xs) {
        return drop(1, xs);
    }
    
    static final <T> T last(Iterable<? extends T> xs) {
        return xs == null ? null : lastOptional(xs).get();
    }
    
    static final <T> Optional<T> lastOptional(Iterable<? extends T> xs) {
        if (xs == null) {
            return null;
        }
        if (xs instanceof Collection && ((Collection<? extends T>) xs).isEmpty()) {
            return Optional.empty();
        }
        
        if (xs instanceof List) {
            return Optional.of(((List<? extends T>) xs).get(((List<? extends T>) xs).size()-1));
        } else if (xs instanceof NavigableSet) {
            return Optional.of(((NavigableSet<? extends T>) xs).last());
        } else {
            Iterator<? extends T> it = xs.iterator();
            if (it.hasNext()) {
                T ret = it.next();
                while (it.hasNext()) {
                    ret = it.next();
                }
                return Optional.of(ret);
            } else {
                return Optional.empty();
            }
        }
    }
    
    static final <T> Iterable<T> init(Iterable<? extends T> xs) {
        // TODO: make lazier to not need the actual size
        return xs == null ? null : take(size(xs)-1, xs);
    }
    
    static final <T> Iterable<T> take(long amount, Iterable<? extends T> xs) {
        return xs == null ? null : new Iterables.TakingIterable<T>(xs, amount);
    }

    static final <T> Iterable<T> drop(long amount, Iterable<? extends T> xs) {
        return xs == null ? null : new Iterables.DroppingIterable<T>(xs, amount);
    }
    
    static final <T> Iterable<T> takeWhile(final java.util.function.Predicate<? super T> predicate, final Iterable<? extends T> xs) {
        return xs == null ? null : new Iterable<T>() {
            public final Iterator<T> iterator() {
                return new Iterator<T>() {
                    private Optional<T> next;
                    private Iterator<? extends T> source = xs.iterator();
                    {
                        readNext();
                    }

                    public final boolean hasNext() {
                        return next.isPresent();
                    }

                    private final void readNext() {
                        if (!source.hasNext()) {
                            next = Optional.empty();
                        } else {
                            T n = source.next();
                            if (predicate.test(n)) {
                                next = Optional.of(n);
                            } else {
                                next = Optional.empty();
                            }
                        }
                    }

                    public final T next() {
                        if (!next.isPresent()) {
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
    
    static final <T> Iterable<T> dropWhile(final java.util.function.Predicate<? super T> predicate, final Iterable<? extends T> xs) {
        return xs == null ? null : new Iterable<T>() {
            public final Iterator<T> iterator() {
                return new Iterator<T>() {
                    private boolean dropping = true;
                    private Iterator<? extends T> source = xs.iterator();
                    private Optional<T> next;
                    {
                        readNext();
                    }

                    public final boolean hasNext() {
                        return next.isPresent();
                    }

                    private final void readNext() {
                        next = source.hasNext() ? Optional.of(source.next()) : Optional.empty();
                        while (dropping && next.isPresent() && predicate.test(next.get())) {
                            next = source.hasNext() ? Optional.of(source.next()) : Optional.empty();
                        }
                        dropping = false;
                    }

                    public final T next() {
                        if (!next.isPresent()) {
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
    
    static final <T> Pair<Iterable<T>, Iterable<T>> span(java.util.function.Predicate<? super T> predicate, Iterable<? extends T> xs) {
        // TODO: a more efficient implementation
        return xs == null ? null : Pair.of(takeWhile(predicate, xs), dropWhile(predicate, xs));
    }
    
    static final <T,L,R> Pair<Iterable<L>, Iterable<R>> partition(java.util.function.Function<? super T, Either<L,R>> f, Iterable<? extends T> xs) {
        // TODO: a more efficient implementation
        Iterable<Either<L, R>> ys = map(f, xs);
        return xs == null ? null : Pair.of(map(x -> x.left.get(), filter(Either::isLeft, ys)), map(x -> x.right.get(), filter(Either::isRight, ys)));
    }
    
    public static <T> Pair<Iterable<T>, Iterable<T>> split(int amount, Iterable<? extends T> xs) {
        // TODO: a more efficient implementation
        return xs == null ? null : Pair.of(take(amount, xs), drop(amount, xs));
    }
    
    public static <T> Pair<T, Iterable<T>> split(Iterable<? extends T> xs) {
        Pair<Iterable<T>, Iterable<T>> s = split(1, xs);
        return s.first(Functional::head);
    }
    
    public static final <T> Iterable<T> every(int nth, Iterable<? extends T> xs) {
        // TODO: optimize...
        return nth < 0 ? Collections.<T>emptyList() : nth == 0 ? it(headOptional(xs)) : Functional.map(Pair::right, Functional.filter(t -> t._1 % nth == 0, Functional.zipWithIndex(xs)));
    }
    
    static final boolean isEmpty(Iterable<?> xs) {
        Optional<Long> size = Iterables.resolveSize.apply(xs);
        if (size.isPresent()) {
            return size.get() == 0;
        }
        return !xs.iterator().hasNext();
    }
    
    static final long size(Iterable<?> xs) {
        for (long size: it(Iterables.resolveSize.apply(xs))) {
            return size;
        }
        int s = 0;
        for (@SuppressWarnings("unused") Object o: xs) {
            s++;
        }
        return s;
    }
    
    static final <T> boolean exists(java.util.function.Predicate<? super T> predicate, Iterable<? extends T> xs) {
        return !isEmpty(filter(predicate, xs));
    }
    
    static final <T> boolean forall(java.util.function.Predicate<? super T> predicate, Iterable<? extends T> xs) {
        return !exists(x -> !predicate.test(x), xs);
    }
    
    static final <T extends Comparable<? super T>> Iterable<T> sort(Iterable<? extends T> xs) {
        return sort(Comparator.naturalOrder(), xs);
    }
    
    static final <T> Iterable<T> sort(Comparator<? super T> comparator, Iterable<? extends T> xs) {
        if (xs == null) {
            return null;
        }
        Optional<Long> size = Iterables.resolveSize.apply(xs);
        if (size.isPresent() && size.get() == 0) {
            return emptyList();
        }
        return new Iterables.SortingIterable<T>(xs, comparator);
    }
    
    static final <T extends SemiGroup<T>> Optional<T> reduce(Iterable<? extends T> xs) {
        if (xs == null) {
            return null;
        }
        if (isEmpty(xs)) {
            return Optional.empty();
        }
        return fold(head(xs), xs);
    }
    
    static final <T> T reduce(Monoid<T> monoid, Iterable<? extends T> xs) {
        return fold(monoid, xs).orElse(monoid.zero());
    }
    
    static final <T,Z> Z fold(Z zero, BiFunction<? super Z, ? super T, ? extends Z> f, Iterable<? extends T> xs) {
        if (xs == null) {
            return null;
        }
        Z ret = zero;
        for (T t : xs) {
            ret = f.apply(ret, t);
        }
        return ret;
    }
    
    /**
     * @return <i>Optional.empty</i> if <i>elements</i> is empty
     */
    static final <T> Optional<T> fold(BiFunction<? super T, ? super T, ? extends T> f, Iterable<? extends T> xs) {
        if (xs == null) {
            return null;
        }
        T ret = null;
        for (T t : xs) {
            if (ret == null) {
                ret = t;
            } else {
                ret = f.apply(ret, t);
            }
        }
        return Optional.ofNullable(ret);
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
    static final <T> Iterable<T> append(T x, Iterable<? extends T> xs) {
        return x == null && xs == null ? null : x == null ? (Iterable<T>)xs : xs == null ? newList(x) : concat(xs, Arrays.asList(x));
    }
    
    @SuppressWarnings("unchecked")
    static final <T> Iterable<T> concat(Iterable<? extends T> a, Iterable<? extends T> b) {
        return a == null && b == null ? null : a == null ? (Iterable<T>)b : b == null ? (Iterable<T>)a : new ConcatenatingIterable<T>(Arrays.asList(a, b));
    }
    
    @SuppressWarnings("rawtypes")
    private static final BiFunction<Comparable,Comparable,Comparable> smaller = new BiFunction<Comparable, Comparable, Comparable>() {
        @SuppressWarnings("unchecked")
        @Override
        public Comparable apply(Comparable t1, Comparable t2) {
            return t1.compareTo(t2) > 0 ? t2 : t1;
        }
    };
    
    @SuppressWarnings("rawtypes")
    private static final BiFunction<Comparable,Comparable,Comparable> bigger = new BiFunction<Comparable, Comparable, Comparable>() {
        @SuppressWarnings("unchecked")
        @Override
        public Comparable apply(Comparable t1, Comparable t2) {
            return t1.compareTo(t2) < 0 ? t2 : t1;
        }
    };

    @SuppressWarnings("unchecked")
    static final <T extends Comparable<? super T>> Optional<T> min(Iterable<? extends T> xs) {
        if (xs == null) {
            return null;
        }
        xs = newList(xs);
        if (isEmpty(xs)) {
            return Optional.empty();
        }
        return fold((BiFunction<? super T,? super T,? extends T>)(Object)smaller, xs);
    }
    
    @SuppressWarnings("unchecked")
    static final <T extends Comparable<? super T>> Optional<T> max(Iterable<? extends T> xs) {
        if (xs == null) {
            return null;
        }
        xs = newList(xs);
        if (isEmpty(xs)) {
            return Optional.empty();
        }
        return fold((BiFunction<? super T,? super T,? extends T>)(Object)bigger, xs);
    }
    
    static final <T> Optional<T> minBy(final Comparator<? super T> comparator, Iterable<? extends T> xs) {
        if (xs == null) {
            return null;
        }
        xs = newList(xs);
        if (isEmpty(xs)) {
            return Optional.empty();
        }
        return fold(new BiFunction<T, T, T>() {
            @Override
            public T apply(T t1, T t2) {
                return comparator.compare(t1, t2) > 0 ? t2 : t1;
           }
       }, xs);
    }
    
    static final <T> Optional<T> maxBy(final Comparator<? super T> comparator, Iterable<? extends T> xs) {
        if (xs == null) {
            return null;
        }
        xs = newList(xs);
        if (isEmpty(xs)) {
            return Optional.empty();
        }
        return fold(new BiFunction<T, T, T>() {
             @Override
            public T apply(T t1, T t2) {
                return comparator.compare(t1, t2) < 0 ? t2 : t1;
            }
        }, xs);
    }
    
    static final <T> boolean ordered(final Comparator<? super T> comparator, Iterable<? extends T> xs) {
        return isEmpty(xs) || fold(headOptional(xs),
                                   (Optional<T> a, T b) -> a.isPresent() && comparator.compare(a.get(), b) <= 0 ? Optional.of(b) : Optional.empty(),
                                   tail(xs)).isPresent();
    }
    
    static final <A,B> Iterable<Pair<A, B>> zip(Iterable<? extends A> a, Iterable<? extends B> b) {
        return a == null || b == null ? null : new ZippingIterable<A,B>(a, b);
    }
    
    @SuppressWarnings("unchecked")
    static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<? extends A> a, Iterable<? extends B> b, Iterable<? extends C> c) {
        return map((java.util.function.Function<Pair<Pair<A, B>, C>, Tuple3<A, B, C>>)(Object)zip3Transformer, zip(zip(a, b), c));
    }
    
    @SuppressWarnings("unchecked")
    static final <A,B,C,D> Iterable<Tuple4<A, B, C, D>> zip(Iterable<? extends A> a, Iterable<? extends B> b, Iterable<? extends C> c, Iterable<? extends D> d) {
        return map((java.util.function.Function<Pair<Pair<A, B>, Pair<C,D>>, Tuple4<A, B, C, D>>)(Object)zip4Transformer, zip(zip(a, b), zip(c,d)));
    }
    
    static final <A> Iterable<Pair<Integer, A>> zipWithIndex(Iterable<? extends A> xs) {
        return new ZippingIterable<Integer, A>(range(Enumerables.ints, 0), xs);
    }
    
    private static final java.util.function.Function<Tuple2<Tuple2<Object,Object>,Object>,Tuple3<Object,Object,Object>> zip3Transformer = new java.util.function.Function<Tuple2<Tuple2<Object, Object>, Object>, Tuple3<Object, Object, Object>>() {
        @Override
        public final Tuple3<Object, Object, Object> apply(Tuple2<Tuple2<Object, Object>, Object> source) {
            return source._1.append(source._2);
        }
    };
    
    private static final java.util.function.Function<Tuple2<Tuple2<Object,Object>,Tuple2<Object,Object>>,Tuple4<Object,Object,Object,Object>> zip4Transformer = new java.util.function.Function<Tuple2<Tuple2<Object, Object>, Tuple2<Object,Object>>, Tuple4<Object, Object, Object, Object>>() {
        @Override
        public final Tuple4<Object, Object, Object, Object> apply(Tuple2<Tuple2<Object, Object>, Tuple2<Object, Object>> source) {
            return source._1.join(source._2);
        }
    };
    
    static final <A, B> Pair<Iterable<A>, Iterable<B>> unzip(Iterable<? extends Pair<A,B>> xs) {
        if (xs == null) {
            return null;
        }
        // TODO: a more efficient implementation
        return Pair.of(map(Pair::left, xs), map(Pair::right, xs));
    }
    
    static final <A, B, C> Tuple3<Iterable<A>, Iterable<B>, Iterable<C>> unzip3(Iterable<? extends Tuple3<A,B,C>> xs) {
        if (xs == null) {
            return null;
        }
        // TODO: a more efficient implementation
        return Tuple.of(map(t -> t._1, xs), map(t -> t._2, xs), map(t -> t._3, xs));
    }
    
    static final <T> Iterable<T> range(Enumerable<T> enumeration, T from) {
        return enumeration == null ? null : new RangeIterable<T>(enumeration, from, Optional.empty());
    }
    
    static final <T> Iterable<T> range(Enumerable<T> enumeration, T from, T toInclusive) {
        return enumeration == null ? null : new RangeIterable<T>(enumeration, from, Optional.of(toInclusive));
    }
    
    @SuppressWarnings("unchecked")
    static final <T> Iterable<List<T>> rangify(final Enumerable<T> enumeration, Iterable<? extends T> xs) {
        Iterable<Iterable<T>> ret = FunctionalImpl.group(new BiPredicate<T,T>() {
            public boolean test(T candidate1, T candidate2) {
                Optional<T> succ = enumeration.succ(candidate1);
                return succ.isPresent() && succ.get().equals(candidate2);
            };
        }, xs);
        return (Iterable<List<T>>)(Object)map(minAndMax, ret);
    }
    
    static final java.util.function.Function<Iterable<?>, List<?>> minAndMax = new java.util.function.Function<Iterable<?>,List<?>>() {
        public List<?> apply(Iterable<?> source) {
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
    
    static final <T> Iterable<T> reverse(Iterable<? extends T> xs) {
        return xs == null ? null : new Iterables.ReversingIterable<T>(xs);
    }
    
    static final <T> Iterable<T> distinct(Iterable<? extends T> xs) {
        return xs == null ? null : new Iterables.DistinctIterable<T>(xs);
    }
    
    static final <T,R> Iterable<R> sequence(final T value, Iterable<? extends java.util.function.Function<? super T,? extends R>> fs) {
        return map(source -> source.apply(value), fs);
    }
    
    static final <P,T,R> BiFunction<P,T,T> combineEndo(final Iterable<? extends java.util.function.Function<? super P,? extends java.util.function.Function<T,T>>> fs) {
        return new BiFunction<P, T, T>() {
            @Override
            public T apply(P p, T t) {
                return FunctionalImpl.reduce(Monoids.<T>endo(), FunctionalImpl.sequence(p, fs)).apply(t);
            }
        };
    }
    
    static final <T> Iterable<Iterable<T>> transpose(Iterable<? extends Iterable<T>> xs) {
        return xs == null ? null : new TransposingIterable<T>(xs);
    }
    
    static final CharSequence unlines(Iterable<? extends CharSequence> xs) {
        if (xs == null) {
            return null;
        }
        Iterable<String> lineSeparators = Functional.repeat(System.getProperty("line.separator"));
        Optional<? extends CharSequence> first = headOptional(xs);
        if (!first.isPresent()) {
            return "";
        }
        Iterable<CharSequence> rest = map(source -> Functional.concat(source._1, source._2), zip(lineSeparators, tail(xs)));
        return it(flatten(map(FunctionalC.charSeq2iterable, cons(first.get(), rest))));
    }
}