package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.it;
import static fi.solita.utils.functional.Collections.newMap;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;

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

public abstract class FunctionalImpl {
    /**
     * Returns a new iterable <code>a - b</code>, i.e. one that contains all elements of <code>a</code> that
     * don't exist in <code>b</code>.
     */
    @SuppressWarnings("unchecked")
    public static <T> Iterable<T> subtract(Iterable<? extends T> a, final Collection<? extends T> b) {
        return (Iterable<T>) filter(a, new Predicate<T>() {
            @Override
            public boolean accept(T object) {
                return !b.contains(object);
            }
        });
    }
    
    public static final <T> Option<T> find(Iterable<T> xs, Apply<? super T, Boolean> predicate) {
        return headOption(filter(xs, predicate));
    }
    
    public static final <K, V> Option<V> find(Map<? extends K, V> map, K key) {
        return Option.of(map.get(key));
    }
    
    public static final <T> Iterable<T> filter(Iterable<T> xs, Apply<? super T, Boolean> predicate) {
        return new FilteringIterable<T>(xs, predicate);
    }
    
    public static final <T, E> Map<T, E> filter(Map<T, E> map, Apply<Map.Entry<T, E>, Boolean> predicate) {
        return Collections.newMap(filter(map.entrySet(), predicate));
    }
    
    public static final <S, T> Iterable<T> map(Iterable<S> xs, Apply<? super S, ? extends T> f) {
        return new TransformingIterable<S,T>(xs, f);
    }
    
    public static final <S, T1, T2> Iterable<Pair<T1,T2>> map(Iterable<S> xs, final Apply<? super S, T1> f1, final Apply<? super S, T2> f2) {
        return new TransformingIterable<S,Pair<T1,T2>>(xs, new Transformer<S, Pair<T1,T2>>() {
            @Override
            public Pair<T1,T2> transform(S source) {
                return Pair.of(f1.apply(source), f2.apply(source));
            }
        });
    }
    
    public static final <S, T1, T2, T3> Iterable<Tuple3<T1,T2,T3>> map(Iterable<S> xs, final Apply<? super S, T1> f1, final Apply<? super S, T2> f2, final Apply<? super S, T3> f3) {
        return new TransformingIterable<S,Tuple3<T1,T2,T3>>(xs, new Transformer<S, Tuple3<T1,T2,T3>>() {
            @Override
            public Tuple3<T1,T2,T3> transform(S source) {
                return Tuple.of(f1.apply(source), f2.apply(source), f3.apply(source));
            }
        });
    }
    
    public static final <S, T1, T2, T3, T4> Iterable<Tuple4<T1,T2,T3,T4>> map(Iterable<S> xs, final Apply<? super S, T1> f1, final Apply<? super S, T2> f2, final Apply<? super S, T3> f3, final Apply<? super S, T4> f4) {
        return new TransformingIterable<S,Tuple4<T1,T2,T3,T4>>(xs, new Transformer<S, Tuple4<T1,T2,T3,T4>>() {
            @Override
            public Tuple4<T1,T2,T3,T4> transform(S source) {
                return Tuple.of(f1.apply(source), f2.apply(source), f3.apply(source), f4.apply(source));
            }
        });
    }
    
    public static final <K1, V1, K2, V2> Map<K2, V2> map(Map<K1, V1> map, Apply<? super Map.Entry<K1, V1>, ? extends Map.Entry<? extends K2, ? extends V2>> f) {
        return Collections.newMap(map(map.entrySet(), f));
    }
    
    public static final <S, T> Iterable<T> flatMap(Iterable<S> xs, Apply<? super S, ? extends Iterable<? extends T>> f) {
        return flatten(map(xs, f));
    }
    
    public static <T> Iterable<T> flatten(Iterable<? extends Iterable<? extends T>> xs) {
        return new FlatteningIterable<T>(xs);
    }
    
    public static final <T> void foreach(Iterable<T> xs, Apply<? super T, Void> procedure) {
        for (T t: xs) {
            procedure.apply(t);
        }
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> void foreach(Iterable<T> xs, ApplyVoid<? super T> procedure) {
        foreach(xs, (Apply<? super T, Void>)procedure);
    }
    
    /**
     * Non-lazy
     */
    public static final <T> Iterable<List<T>> grouped(Iterable<T> xs, long groupSize) {
        if (groupSize <= 0) {
            throw new IllegalArgumentException("size must be positive");
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
    public static <T> Iterable<Iterable<T>> group(Iterable<T> xs) {
        return group(xs, (Predicate<Tuple2<T,T>>)(Object)tuple2elementsEqual);
    }
    
    private static final Predicate<Tuple2<Object,Object>> tuple2elementsEqual = new Predicate<Tuple2<Object,Object>>() {
        @Override
        public boolean accept(Tuple2<Object, Object> candidate) {
            return candidate._1.equals(candidate._2);
        }
    };
    
    public static final <T> Iterable<Iterable<T>> group(Iterable<T> xs, Apply<Tuple2<T,T>, Boolean> comparator) {
        return new Iterables.GroupingIterable<T>(xs, comparator);
    }
    
    /**
     * Non-lazy
     */
    public static <G, T> Map<G, List<T>> groupBy(Iterable<T> xs, Apply<? super T,G> f) {
        Map<G, List<T>> target = newMap();
        for (T t: xs) {
            G g = f.apply(t);
            Option<List<T>> groupOption = find(target, g);
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
    
    public static <T> T head(Iterable<T> xs) {
        return xs.iterator().next();
    }
    
    public static <T> Option<T> headOption(Iterable<T> xs) {
        Iterator<T> it = xs.iterator();
        if (it.hasNext()) {
            return Some(it.next());
        } else {
            return None();
        }
    }
    
    public static <T> Iterable<T> tail(Iterable<T> xs) {
        return drop(1, xs);
    }
    
    public static <T> T last(Iterable<T> xs) {
        return lastOption(xs).get();
    }
    
    public static <T> Option<T> lastOption(Iterable<T> xs) {
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
    
    public static <T> Iterable<T> init(Iterable<T> xs) {
        return take(size(xs)-1, xs);
    }
    
    public static <T> Iterable<T> take(long amount, Iterable<T> xs) {
        return new Iterables.TakingIterable<T>(xs, amount);
    }

    public static <T> Iterable<T> drop(long amount, Iterable<T> xs) {
        return new Iterables.DroppingIterable<T>(xs, amount);
    }
    
    public static final <T> Iterable<T> takeWhile(final Iterable<T> xs, final Apply<? super T, Boolean> predicate) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    private Option<T> next;
                    private Iterator<T> source = xs.iterator();
                    {
                        readNext();
                    }

                    @Override
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

                    @Override
                    public T next() {
                        if (!next.isDefined()) {
                            throw new NoSuchElementException();
                        }
                        T ret = next.get();
                        readNext();
                        return ret;
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
    
    public static final <T> Iterable<T> dropWhile(final Iterable<T> xs, final Apply<? super T, Boolean> predicate) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    private boolean dropping = true;
                    private Iterator<T> source = xs.iterator();
                    private Option<T> next;
                    {
                        readNext();
                    }

                    @Override
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

                    @Override
                    public T next() {
                        if (!next.isDefined()) {
                            throw new NoSuchElementException();
                        }
                        T ret = next.get();
                        readNext();
                        return ret;
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
    
    public static final <T> Pair<Iterable<T>, Iterable<T>> span(Iterable<T> xs, Apply<? super T, Boolean> predicate) {
        // TODO: a more efficient implementation
        return Pair.of(takeWhile(xs, predicate), dropWhile(xs, predicate));
    }
    
    public static boolean isEmpty(Iterable<?> xs) {
        return !xs.iterator().hasNext();
    }
    
    public static long size(Iterable<?> xs) {
        for (long size: Iterables.resolveSize.apply(xs)) {
            return size;
        }
        int s = 0;
        for (@SuppressWarnings("unused") Object o: xs) {
            s++;
        }
        return s;
    }
    
    public static final <T> boolean exists(Iterable<T> xs, Apply<? super T, Boolean> predicate) {
        return !isEmpty(filter(xs, predicate));
    }
    
    public static final <T> boolean forall(Iterable<T> xs, Apply<? super T, Boolean> predicate) {
        return !exists(xs, Predicates.not(predicate));
    }
    
    public static <T extends Comparable<T>> Iterable<T> sort(Iterable<T> xs) {
        return sort(xs, Ordering.Natural());
    }
    
    public static final <T> Iterable<T> sort(Iterable<T> xs, Comparator<? super T> comparator) {
        if (isEmpty(xs)) {
            return emptyList();
        }
        return new Iterables.SortingIterable<T>(xs, comparator);
    }
    
    public static <T extends SemiGroup<T>> Option<T> reduce(Iterable<? extends T> xs) {
        if (isEmpty(xs)) {
            return None();
        }
        return fold(xs, head(xs));
    }
    
    public static final <T> T reduce(Iterable<? extends T> xs, Monoid<T> m) {
        return fold(cons(m.zero(), xs), m).get();
    }
    
    public static final <T,Z> Z fold(Z zero, Iterable<? extends T> xs, Apply<Tuple2<Z,T>, Z> f) {
        Z ret = zero;
        for (T t : xs) {
            ret = f.apply(Tuple.of(ret, t));
        }
        return ret;
    }
    
    /**
     * @return <i>None</i> if <i>elements</i> is empty
     */
    public static final <T> Option<T> fold(Iterable<? extends T> xs, Apply<Tuple2<T,T>, T> f) {
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
    
    public static long sum(Iterable<Long> xs) {
        return reduce(xs, Monoids.longSum);
    }
    
    public static long product(Iterable<Long> xs) {
        return reduce(xs, Monoids.longProduct);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Iterable<T> cons(T x, Iterable<? extends T> xs) {
        return concat(Arrays.asList(x), xs);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Iterable<T> concat(Iterable<? extends T> a, Iterable<? extends T> b) {
        return new ConcatenatingIterable<T>(Arrays.asList(a, b));
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> Option<T> min(Iterable<T> xs) {
        if (isEmpty(xs)) {
            return None();
        }
        return Some(fold(head(xs), tail(xs), (Apply<Tuple2<T,T>,T>)(Object)smaller));
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
    public static <T extends Comparable<T>> Option<T> max(Iterable<T> xs) {
        if (isEmpty(xs)) {
            return None();
        }
        return Some(fold(head(xs), tail(xs), (Apply<Tuple2<T,T>,T>)(Object)bigger));
    }
    
    public static <A,B> Iterable<Tuple2<A, B>> zip(Iterable<A> a, Iterable<B> b) {
        return new ZippingIterable<A,B>(a, b);
    }
    
    @SuppressWarnings("unchecked")
    public static <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, Iterable<B> b, Iterable<C> c) {
        return map(zip(zip(a, b), c), ( Transformer<Tuple2<Tuple2<A, B>, C>, Tuple3<A, B, C>>)(Object)zip3Transformer);
    }
    
    private static Transformer<Tuple2<Tuple2<Object,Object>,Object>,Tuple3<Object,Object,Object>> zip3Transformer = new Transformer<Tuple2<Tuple2<Object, Object>, Object>, Tuple3<Object, Object, Object>>() {
        @Override
        public Tuple3<Object, Object, Object> transform(Tuple2<Tuple2<Object, Object>, Object> source) {
            return source._1.append(source._2);
        }
    };
    
    public static <T> Iterable<T> range(Enumerable<T> enumeration, T from) {
        return new RangeIterable<T>(enumeration, from, Option.<T>None());
    }
    
    public static <T> Iterable<T> range(Enumerable<T> enumeration, T from, T toInclusive) {
        return new RangeIterable<T>(enumeration, from, Some(toInclusive));
    }
    
    public static String mkString(Iterable<Character> xs) {
        StringBuilder sb = new StringBuilder();
        for (char c: xs) {
            sb.append(c);
        }
        return sb.toString();
    }
    
    public static String mkString(CharSequence delimiter, Iterable<? extends CharSequence> xs) {
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
    
    public static <T> Iterable<T> reverse(Iterable<T> xs) {
        return new Iterables.ReversingIterable<T>(xs);
    }
    
    public static <T> Iterable<T> distinct(Iterable<T> xs) {
        return filter(xs, new DistinctPredicate<T>());
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
    
    public static final <T,R> Iterable<R> sequence(Iterable<? extends Apply<? super T,? extends R>> fs, final T value) {
        return map(fs, new Transformer<Apply<? super T,? extends R>, R>() {
            @Override
            public R transform(Apply<? super T,? extends R> source) {
                return source.apply(value);
            }
        });
    }
    
    public static <T> Iterable<Iterable<T>> transpose(Iterable<? extends Iterable<T>> xs) {
        return new TransposingIterable<T>(xs);
    }
    
    public static CharSequence unlines(Iterable<? extends CharSequence> xs) {
        Iterable<String> lineSeparators = Functional.repeat(System.getProperty("line.separator"));
        CharSequence first = head(xs);
        Iterable<CharSequence> rest = map(zip(lineSeparators, tail(xs)), new Transformer<Tuple2<String,? extends CharSequence>,CharSequence>() {
            @Override
            public CharSequence transform(Tuple2<String, ? extends CharSequence> source) {
                return Functional.concat(source._1, source._2);
            }
        });
        return it(flatten(map(cons(first, rest), FunctionalC.charSeq2iterable)));
    }
}