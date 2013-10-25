package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.emptyList;
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
import fi.solita.utils.functional.Iterables.RepeatingIterable;
import fi.solita.utils.functional.Iterables.TransformingIterable;
import fi.solita.utils.functional.Iterables.TransposingIterable;
import fi.solita.utils.functional.Iterables.ZippingIterable;

public abstract class Functional {

    /**
     * Returns a new iterable <code>a - b</code>, i.e. one that contains all elements of <code>a</code> that
     * don't exist in <code>b</code>.
     */
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> subtract(Iterable<? extends T> a, final Collection<? extends T> b) {
        return (Iterable<T>) filter(a, new Predicate<T>() {
            @Override
            public boolean accept(T object) {
                return !b.contains(object);
            }
        });
    }
    
    public static final <T> Iterable<T> subtract(T[] a, Collection<T> b) {
        return subtract(Arrays.asList(a), b);
    }
    
    public static final <T> Iterable<T> subtract(Iterable<T> a, T... b) {
        return subtract(a, newSet(b));
    }
    
    public static final <T> Iterable<T> subtract(T[] a, T... b) {
        return subtract(Arrays.asList(a), newSet(b));
    }
    
    

    public static final <K, V> Option<V> find(K key, Map<? extends K, V> map) {
        return Option.of(map.get(key));
    }

    public static final <T> Option<T> find(T[] xs, Apply<? super T, Boolean> predicate) {
        return find(Arrays.asList(xs), predicate);
    }
    
    public static final <T> Option<T> find(Apply<? super T, Boolean> predicate, T... xs) {
        return find(Arrays.asList(xs), predicate);
    }

    public static final <T> Option<T> find(Iterable<T> xs, Apply<? super T, Boolean> predicate) {
        return headOption(filter(xs, predicate));
    }
    
    public static final <T> Option<T> find(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return headOption(filter(xs, predicate));
    }

    
    
    public static final <T> Iterable<T> filter(T[] xs, Apply<? super T, Boolean> predicate) {
        return filter(Arrays.asList(xs), predicate);
    }
    
    public static final <T> Iterable<T> filter(Apply<? super T, Boolean> predicate, T... xs) {
        return filter(Arrays.asList(xs), predicate);
    }

    public static final <T> Iterable<T> filter(Iterable<T> xs, Apply<? super T, Boolean> predicate) {
        return new FilteringIterable<T>(xs, predicate);
    }
    
    public static final <T> Iterable<T> filter(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return new FilteringIterable<T>(xs, predicate);
    }

    public static final <T, E> Map<T, E> filter(Map<T, E> map, Apply<Map.Entry<T, E>, Boolean> predicate) {
        return Collections.newMap(filter(map.entrySet(), predicate));
    }
    
    public static final <T, E> Map<T, E> filter(Apply<Map.Entry<T, E>, Boolean> predicate, Map<T, E> map) {
        return Collections.newMap(filter(map.entrySet(), predicate));
    }

    public static final <S, T> Iterable<T> map(S[] xs, Apply<? super S, ? extends T> f) {
        return map(Arrays.asList(xs), f);
    }
    
    public static final <S, T> Iterable<T> map(Apply<? super S, ? extends T> f, S... xs) {
        return map(Arrays.asList(xs), f);
    }
    
    public static final <S, T1, T2> Iterable<Pair<T1,T2>> map(S[] xs, Apply<? super S, T1> f1, Apply<? super S, T2> f2) {
        return map(Arrays.asList(xs), f1, f2);
    }
    
    public static final <S, T1, T2> Iterable<Pair<T1,T2>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, S... elements) {
        return map(Arrays.asList(elements), f1, f2);
    }
    
    public static final <S, T1, T2, T3> Iterable<Tuple3<T1,T2,T3>> map(S[] xs, Apply<? super S, T1> f1, Apply<? super S, T2> f2, Apply<? super S, T3> f3) {
        return map(Arrays.asList(xs), f1, f2, f3);
    }
    
    public static final <S, T1, T2, T3> Iterable<Tuple3<T1,T2,T3>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, Apply<? super S, T3> f3, S... xs) {
        return map(Arrays.asList(xs), f1, f2, f3);
    }
    
    public static final <S, T1, T2, T3, T4> Iterable<Tuple4<T1,T2,T3,T4>> map(S[] xs, Apply<? super S, T1> f1, Apply<? super S, T2> f2, Apply<? super S, T3> f3, Apply<? super S, T4> f4) {
        return map(Arrays.asList(xs), f1, f2, f3, f4);
    }
    
    public static final <S, T1, T2, T3, T4> Iterable<Tuple4<T1,T2,T3,T4>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, Apply<? super S, T3> f3, Apply<? super S, T4> f4, S... xs) {
        return map(Arrays.asList(xs), f1, f2, f3, f4);
    }

    public static final <S, T> Iterable<T> map(Iterable<S> xs, Apply<? super S, ? extends T> f) {
        return new TransformingIterable<S,T>(xs, f);
    }
    
    public static final <S, T> Iterable<T> map(Apply<? super S, ? extends T> f, Iterable<S> xs) {
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
    
    public static final <S, T1, T2> Iterable<Pair<T1,T2>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, Iterable<S> xs) {
        return map(xs, f1, f2);
    }
    
    public static final <S, T1, T2, T3> Iterable<Tuple3<T1,T2,T3>> map(Iterable<S> xs, final Apply<? super S, T1> f1, final Apply<? super S, T2> f2, final Apply<? super S, T3> f3) {
        return new TransformingIterable<S,Tuple3<T1,T2,T3>>(xs, new Transformer<S, Tuple3<T1,T2,T3>>() {
            @Override
            public Tuple3<T1,T2,T3> transform(S source) {
                return Tuple.of(f1.apply(source), f2.apply(source), f3.apply(source));
            }
        });
    }
    
    public static final <S, T1, T2, T3> Iterable<Tuple3<T1,T2,T3>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, Apply<? super S, T3> f3, Iterable<S> xs) {
        return map(xs, f1, f2, f3);
    }
    
    public static final <S, T1, T2, T3, T4> Iterable<Tuple4<T1,T2,T3,T4>> map(Iterable<S> xs, final Apply<? super S, T1> f1, final Apply<? super S, T2> f2, final Apply<? super S, T3> f3, final Apply<? super S, T4> f4) {
        return new TransformingIterable<S,Tuple4<T1,T2,T3,T4>>(xs, new Transformer<S, Tuple4<T1,T2,T3,T4>>() {
            @Override
            public Tuple4<T1,T2,T3,T4> transform(S source) {
                return Tuple.of(f1.apply(source), f2.apply(source), f3.apply(source), f4.apply(source));
            }
        });
    }
    
    public static final <S, T1, T2, T3, T4> Iterable<Tuple4<T1,T2,T3,T4>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, Apply<? super S, T3> f3, Apply<? super S, T4> f4, Iterable<S> xs) {
        return map(xs, f1, f2, f3, f4);
    }

    public static final <K1, V1, K2, V2> Map<K2, V2> map(Map<K1, V1> map, Apply<Map.Entry<K1, V1>, Map.Entry<K2, V2>> f) {
        return Collections.newMap(map(map.entrySet(), f));
    }
    
    public static final <K1, V1, K2, V2> Map<K2, V2> map(Apply<Map.Entry<K1, V1>, Map.Entry<K2, V2>> f, Map<K1, V1> map) {
        return Collections.newMap(map(map.entrySet(), f));
    }

    public static final <S, T> Iterable<T> flatMap(S[] xs, Apply<? super S, ? extends Iterable<T>> f) {
        return flatMap(Arrays.asList(xs), f);
    }
    
    public static final <S, T> Iterable<T> flatMap(Apply<? super S, ? extends Iterable<T>> f, S... xs) {
        return flatMap(Arrays.asList(xs), f);
    }

    public static final <S, T> Iterable<T> flatMap(Iterable<S> xs, Apply<? super S, ? extends Iterable<? extends T>> f) {
        return flatten(map(xs, f));
    }
    
    public static final <S, T> Iterable<T> flatMap(Apply<? super S, ? extends Iterable<? extends T>> f, Iterable<S> xs) {
        return flatten(map(xs, f));
    }

    public static final <T> Iterable<T> flatten(T[]... xs) {
        return flatten(map(xs, new Transformer<T[], Iterable<T>>() {
            @Override
            public Iterable<T> transform(T[] source) {
                return Arrays.asList(source);
            }
        }));
    }
    
    public static final <T> Iterable<T> flatten(Iterable<? extends T>... xs) {
        return flatten(Arrays.asList(xs));
    }

    public static final <T> Iterable<T> flatten(Iterable<? extends Iterable<? extends T>> xs) {
        return new FlatteningIterable<T>(xs);
    }
    
    public static final <T> void foreach(T[] xs, Apply<? super T, ?> procedure) {
        foreach(Arrays.asList(xs), procedure);
    }
    
    public static final <T> void foreach(Apply<? super T, ?> procedure, T... xs) {
        foreach(Arrays.asList(xs), procedure);
    }
    
    public static final <T> void foreach(Iterable<T> xs, Apply<? super T, ?> procedure) {
        for (T t: xs) {
            procedure.apply(t);
        }
    }
    
    public static final <T> void foreach(Apply<? super T, ?> procedure, Iterable<T> xs) {
        foreach(xs, procedure);
    }

    /**
     * Non-lazy
     */
    public static final <T> Iterable<List<T>> grouped(int groupSize, T... xs) {
        return grouped(groupSize, Arrays.asList(xs));
    }

    /**
     * Non-lazy
     */
    public static final <T> Iterable<List<T>> grouped(int groupSize, Iterable<T> xs) {
        if (groupSize <= 0) {
            throw new IllegalArgumentException("size must be positive");
        }

        Option<Integer> estimatedSize = Iterables.resolveSize.apply(xs);
        List<List<T>> target = estimatedSize.isDefined() ? Collections.<List<T>>newListOfSize(estimatedSize.get() / groupSize) : Collections.<List<T>>newList();
        Iterator<T> it = xs.iterator();
        while (it.hasNext()) {
            List<T> group = Collections.newListOfSize(groupSize);
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
    
    public static final <T> Iterable<Iterable<T>> grouped(T... xs) {
        return grouped(Arrays.asList(xs));
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<Iterable<T>> grouped(Iterable<T> xs) {
        return grouped(xs, (Predicate<Tuple2<T,T>>)(Object)tuple2elementsEqual);
    }
    
    private static final Predicate<Tuple2<Object,Object>> tuple2elementsEqual = new Predicate<Tuple2<Object,Object>>() {
        @Override
        public boolean accept(Tuple2<Object, Object> candidate) {
            return candidate._1.equals(candidate._2);
        }
    };
    
    public static final <T> Iterable<Iterable<T>> grouped(T[] xs, Apply<Tuple2<T,T>, Boolean> comparator) {
        return grouped(Arrays.asList(xs), comparator);
    }
    
    public static final <T> Iterable<Iterable<T>> grouped(Apply<Tuple2<T,T>, Boolean> comparator, T... xs) {
        return grouped(Arrays.asList(xs), comparator);
    }
    
    public static final <T> Iterable<Iterable<T>> grouped(Iterable<T> xs, Apply<Tuple2<T,T>, Boolean> comparator) {
        return new Iterables.GroupingIterable<T>(xs, comparator);
    }
    
    public static final <T> Iterable<Iterable<T>> grouped(Apply<Tuple2<T,T>, Boolean> comparator, Iterable<T> xs) {
        return new Iterables.GroupingIterable<T>(xs, comparator);
    }
    
    /**
     * Non-lazy
     */
    public static final <G, T> Map<G, List<T>> groupBy(T[] xs, Apply<? super T,G> f) {
          return groupBy(Arrays.asList(xs), f);
    }
    
    public static final <G, T> Map<G, List<T>> groupBy(Apply<? super T,G> f, T... xs) {
        return groupBy(Arrays.asList(xs), f);
    }
    
    /**
     * Non-lazy
     */
    public static final <G, T> Map<G, List<T>> groupBy(Iterable<T> xs, Apply<? super T,G> f) {
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
    
    public static final <G, T> Map<G, List<T>> groupBy(Apply<? super T,G> f, Iterable<T> xs) {
        return groupBy(xs, f);
    }
    
    public static final <T> T head(T... xs) {
        return xs[0];
    }

    public static final <T> T head(Iterable<T> xs) {
        return xs.iterator().next();
    }
    
    public static final <T> Option<T> headOption(T... xs) {
          return xs.length == 0 ? Option.<T>None() : Some(xs[0]);
    }

    public static final <T> Option<T> headOption(Iterable<T> xs) {
        Iterator<T> it = xs.iterator();
        if (it.hasNext()) {
            return Some(it.next());
        } else {
            return None();
        }
    }
    
    public static final <T> Iterable<T> tail(T... xs) {
          return tail(Arrays.asList(xs));
    }

    public static final <T> Iterable<T> tail(Iterable<T> xs) {
        return drop(1, xs);
    }
    
    public static final <T> T last(T... xs) {
          return xs[xs.length-1];
    }

    public static final <T> T last(Iterable<T> xs) {
        return lastOption(xs).get();
    }
    
    public static final <T> Option<T> lastOption(T... xs) {
          return xs.length == 0 ? Option.<T>None() : Some(xs[xs.length-1]);
    }

    public static final <T> Option<T> lastOption(Iterable<T> xs) {
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
    
    public static final <T> Iterable<T> init(T... xs) {
        return init(Arrays.asList(xs));
    }

    public static final <T> Iterable<T> init(Iterable<T> xs) {
        return take(size(xs)-1, xs);
    }
    
    public static final <T> Iterable<T> take(int amount, T... xs) {
        return new Iterables.TakingIterable<T>(Arrays.asList(xs), amount);
    }

    public static final <T> Iterable<T> take(int amount, Iterable<T> xs) {
        return new Iterables.TakingIterable<T>(xs, amount);
    }
    
    public static final <T> Iterable<T> drop(int amount, T... xs) {
        return new Iterables.DroppingIterable<T>(Arrays.asList(xs), amount);
    }

    public static final <T> Iterable<T> drop(int amount, Iterable<T> xs) {
        return new Iterables.DroppingIterable<T>(xs, amount);
    }
    
    public static final <T> Iterable<T> takeWhile(T[] xs, Apply<? super T, Boolean> predicate) {
        return takeWhile(Arrays.asList(xs), predicate);
    }
    
    public static final <T> Iterable<T> takeWhile(Apply<? super T, Boolean> predicate, T... xs) {
        return takeWhile(Arrays.asList(xs), predicate);
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
    
    public static final <T> Iterable<T> takeWhile(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return takeWhile(xs, predicate);
    }
    
    public static final <T> Iterable<T> dropWhile(T[] xs, Apply<? super T, Boolean> predicate) {
          return dropWhile(Arrays.asList(xs), predicate);
    }
    
    public static final <T> Iterable<T> dropWhile(Apply<? super T, Boolean> predicate, T... xs) {
        return dropWhile(Arrays.asList(xs), predicate);
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
    
    public static final <T> Iterable<T> dropWhile(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return dropWhile(xs, predicate);
    }
    
    public static final <T> Pair<Iterable<T>, Iterable<T>> span(Iterable<T> xs, Apply<? super T, Boolean> predicate) {
        // TODO: a more efficient implementation
        return Pair.of(takeWhile(xs, predicate), dropWhile(xs, predicate));
    }
    
    public static final <T> Pair<Iterable<T>, Iterable<T>> span(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return span(xs, predicate);
    }
    
    public static final <T> boolean isEmpty(T[] xs) {
        return xs.length == 0;
    }

    public static final boolean isEmpty(Iterable<?> xs) {
        return !xs.iterator().hasNext();
    }
    
    public static final <T> int size(T[] xs) {
        return xs.length;
    }

    public static final int size(Iterable<?> xs) {
        for (int size: Iterables.resolveSize.apply(xs)) {
            return size;
        }
        int s = 0;
        for (@SuppressWarnings("unused") Object o: xs) {
            s++;
        }
        return s;
    }

    public static final <T> boolean contains(T candidate, T... xs) {
        return contains(Arrays.asList(xs), candidate);
    }

    public static final <T> boolean contains(T candidate, Iterable<T> xs) {
        return exists(xs, Predicates.equalTo(candidate));
    }

    public static final <T> boolean exists(T[] xs, Apply<T, Boolean> predicate) {
        return exists(Arrays.asList(xs), predicate);
    }
    
    public static final <T> boolean exists(Apply<T, Boolean> predicate, T... xs) {
        return exists(Arrays.asList(xs), predicate);
    }

    public static final <T> boolean exists(Iterable<T> xs, Apply<? super T, Boolean> predicate) {
        return !isEmpty(filter(xs, predicate));
    }
    
    public static final <T> boolean exists(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return exists(xs, predicate);
    }

    public static final <T> boolean forall(T[] xs, Apply<? super T, Boolean> predicate) {
        return forall(Arrays.asList(xs), predicate);
    }
    
    public static final <T> boolean forall(Apply<? super T, Boolean> predicate, T... xs) {
        return forall(Arrays.asList(xs), predicate);
    }

    public static final <T> boolean forall(Iterable<T> xs, Apply<? super T, Boolean> predicate) {
        return !exists(xs, Predicates.not(predicate));
    }
    
    public static final <T> boolean forall(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return !exists(xs, Predicates.not(predicate));
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> cons(T x, Iterable<? extends T> xs) {
        return concat(Arrays.asList(x), xs);
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> cons(T x, T... xs) {
        return concat(Arrays.asList(x), xs);
    }

    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> concat(T[] a, Iterable<? extends T> b) {
        return concat(Arrays.asList(a), b);
    }

    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> concat(Iterable<? extends T> a, T... b) {
        return concat(a, Arrays.asList(b));
    }

    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> concat(T[] a, T... b) {
        return concat(Arrays.asList(a), Arrays.asList(b));
    }

    public static final <T> Iterable<T> concat(Iterable<? extends T> a, Iterable<? extends T>... b) {
        return new ConcatenatingIterable<T>(cons(a, b));
    }

    public static final <T extends Comparable<T>> Iterable<T> sort(T... xs) {
          return sort(Arrays.asList(xs));
    }
    
    public static final <T extends Comparable<T>> Iterable<T> sort(Iterable<T> xs) {
        return sort(xs, Ordering.Natural());
    }
    
    public static final <T> Iterable<T> sort(T[] xs, Comparator<? super T> comparator) {
          return sort(Arrays.asList(xs), comparator);
    }
    
    public static final <T> Iterable<T> sort(Comparator<? super T> comparator, T... xs) {
        return sort(Arrays.asList(xs), comparator);
    }

    public static final <T> Iterable<T> sort(Iterable<T> xs, Comparator<? super T> comparator) {
        if (isEmpty(xs)) {
            return emptyList();
        }
        return new Iterables.SortingIterable<T>(xs, comparator);
    }
    
    public static final <T> Iterable<T> sort(Comparator<? super T> comparator, Iterable<T> xs) {
        return sort(xs, comparator);
    }
    
    public static final <T extends SemiGroup<T>> T reduce(T x, T... xs) {
        return reduce(cons(x, xs)).get();
    }
    
    public static final <T extends SemiGroup<T>>  Option<T> reduce(T[] xs) {
        return reduce(Arrays.asList(xs));
    }

    public static final <T extends SemiGroup<T>> Option<T> reduce(Iterable<? extends T> xs) {
        if (isEmpty(xs)) {
            return None();
        }
        return fold(xs, head(xs));
    }
    
    public static final <T> T reduce(T[] xs, Monoid<T> m) {
        return reduce(Arrays.asList(xs), m);
    }
    
    public static final <T> T reduce(Monoid<T> m, T[] xs) {
        return reduce(Arrays.asList(xs), m);
    }
  
    public static final <T> T reduce(Iterable<? extends T> xs, Monoid<T> m) {
        return fold(cons(m.zero(), xs), m).get();
    }
    
    public static final <T> T reduce(Monoid<T> m, Iterable<? extends T> xs) {
        return fold(cons(m.zero(), xs), m).get();
    }
    
    public static final <T,Z> Z fold(Z zero, T[] xs, Apply<Tuple2<Z,T>, Z> f) {
        return fold(zero, Arrays.asList(xs), f);
    }
    
    public static final <T,Z> Z fold(Z zero, Apply<Tuple2<Z,T>, Z> f, T... xs) {
        return fold(zero, Arrays.asList(xs), f);
    }
    
    public static final <T,Z> Z fold(Z zero, Iterable<? extends T> xs, Apply<Tuple2<Z,T>, Z> f) {
        Z ret = zero;
        for (T t : xs) {
            ret = f.apply(Tuple.of(ret, t));
        }
        return ret;
    }
    
    public static final <T,Z> Z fold(Z zero, Apply<Tuple2<Z,T>, Z> f, Iterable<? extends T> xs) {
        return fold(zero, xs, f);
    }
    
    /**
     * @return <i>None</i> if <i>elements</i> is empty
     */
    public static final <T> Option<T> fold(T[] xs, Apply<Tuple2<T,T>, T> f) {
        return fold(Arrays.asList(xs), f);
    }
    
    public static final <T> Option<T> fold(Apply<Tuple2<T,T>, T> f, T... xs) {
        return fold(Arrays.asList(xs), f);
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
    
    public static final <T> Option<T> fold(Apply<Tuple2<T,T>, T> f, Iterable<? extends T> xs) {
        return fold(xs, f);
    }
    
    public static final long sum(Integer... xs) {
        return sum(Arrays.asList(xs));
    }

    public static final long sum(Iterable<Integer> xs) {
        return reduce(map(xs, int2long), Monoids.longSum);
    }

    public static final long product(Integer... xs) {
        return product(Arrays.asList(xs));
    }
    
    public static final long product(Iterable<Integer> xs) {
        return reduce(map(xs, int2long), Monoids.longProduct);
    }
    
    public static final <T extends Comparable<T>> T min(T x, T... xs) {
        return min(cons(x, xs)).get();
    }

    public static final <T extends Comparable<T>> Option<T> min(T[] xs) {
        return min(Arrays.asList(xs));
    }
    
    @SuppressWarnings("unchecked")
    public static final <T extends Comparable<T>> Option<T> min(Iterable<T> xs) {
        if (isEmpty(xs)) {
            return None();
        }
        return Some(fold(head(xs), (Apply<Tuple2<T,T>,T>)(Object)smaller, tail(xs)));
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
    
    public static final <T extends Comparable<T>> T max(T x, T... xs) {
        return max(cons(x, xs)).get();
    }
    
    public static final <T extends Comparable<T>> Option<T> max(T[] xs) {
        return max(Arrays.asList(xs));
    }

    @SuppressWarnings("unchecked")
    public static final <T extends Comparable<T>> Option<T> max(Iterable<T> xs) {
        if (isEmpty(xs)) {
            return None();
        }
        return Some(fold(head(xs), (Apply<Tuple2<T,T>,T>)(Object)bigger, tail(xs)));
    }

    public static final <A,B> Iterable<Tuple2<A, B>> zip(A[] a, B... b) {
        return zip(Arrays.asList(a), Arrays.asList(b));
    }

    public static final <A,B> Iterable<Tuple2<A, B>> zip(A[] a, Iterable<B> b) {
        return zip(Arrays.asList(a), b);
    }

    public static final <A,B> Iterable<Tuple2<A, B>> zip(Iterable<A> a, B... b) {
        return zip(a, Arrays.asList(b));
    }

    public static final <A,B> Iterable<Tuple2<A, B>> zip(Iterable<A> a, Iterable<B> b) {
        return new ZippingIterable<A,B>(a, b);
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, B[] b, C... c) {
        return zip(Arrays.asList(a), Arrays.asList(b), Arrays.asList(c));
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, Iterable<B> b, Iterable<C> c) {
        return zip(Arrays.asList(a), b, c);
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, B[] b, Iterable<C> c) {
        return zip(a, Arrays.asList(b), c);
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, Iterable<B> b, C... c) {
        return zip(a, b, Arrays.asList(c));
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, B[] b, Iterable<C> c) {
        return zip(Arrays.asList(a), Arrays.asList(b), c);
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, B[] b, C... c) {
        return zip(a, Arrays.asList(b), Arrays.asList(c));
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, Iterable<B> b, C... c) {
        return zip(Arrays.asList(a), b, Arrays.asList(c));
    }
   
    @SuppressWarnings("unchecked")
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, Iterable<B> b, Iterable<C> c) {
        return map(zip(zip(a, b), c), ( Transformer<Tuple2<Tuple2<A, B>, C>, Tuple3<A, B, C>>)(Object)zip3Transformer);
    }
    
    private static Transformer<Tuple2<Tuple2<Object,Object>,Object>,Tuple3<Object,Object,Object>> zip3Transformer = new Transformer<Tuple2<Tuple2<Object, Object>, Object>, Tuple3<Object, Object, Object>>() {
        @Override
        public Tuple3<Object, Object, Object> transform(Tuple2<Tuple2<Object, Object>, Object> source) {
            return source._1.append(source._2);
        }
    };

    public static final <A> Iterable<Tuple2<Integer, A>> zipWithIndex(Iterable<A> a) {
        return new ZippingIterable<Integer,A>(range(0), a);
    }

    public static final <A> Iterable<Tuple2<Integer, A>> zipWithIndex(A... a) {
        return new ZippingIterable<Integer,A>(range(0), Arrays.asList(a));
    }

    public static final Iterable<Integer> range(int from) {
        return range(Enumerables.ints, from);
    }

    public static final Iterable<Integer> range(int from, int toInclusive) {
        if (toInclusive < from)
            return emptyList();
        return new RangeIterable<Integer>(Enumerables.ints, from, toInclusive, toInclusive - from + 1);
    }
    
    public static final <T, S extends Enumerable<T> & Bounded<T>> Iterable<T> range(S enumeration) {
        return range(enumeration, enumeration.minBound());
    }
    
    public static final <T> Iterable<T> range(Enumerable<T> enumeration, T from) {
        return new RangeIterable<T>(enumeration, from, Option.<T>None());
    }
    
    public static final <T> Iterable<T> range(Enumerable<T> enumeration, T from, T toInclusive) {
        return new RangeIterable<T>(enumeration, from, Some(toInclusive));
    }

    public static final <T> Iterable<T> repeat(T value) {
        return new RepeatingIterable<T>(value);
    }

    public static final <T> Iterable<T> repeat(T value, int amount) {
        if (amount <= 0)
            return emptyList();
        return new RepeatingIterable<T>(value, amount);
    }
    
    public static final String mkString(Character[] xs) {
        return mkString(Arrays.asList(xs));
    }
    
    public static final String mkString(Iterable<Character> xs) {
        StringBuilder sb = new StringBuilder();
        for (char c: xs) {
            sb.append(c);
        }
        return sb.toString();
    }
    
    public static final String mkString(CharSequence delimiter, CharSequence... xs) {
        return mkString(delimiter, Arrays.asList(xs));
    }

    public static final String mkString(CharSequence delimiter, Iterable<? extends CharSequence> xs) {
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
    
    public static final <T> Iterable<T> reverse(T[] xs) {
        return reverse(Arrays.asList(xs));
    }

    public static final <T> Iterable<T> reverse(Iterable<T> xs) {
        return new Iterables.ReversingIterable<T>(xs);
    }
    
    public static final <T,R> Iterable<R> sequence(Apply<? super T,? extends R>[] fs, T value) {
        return sequence(Arrays.asList(fs), value);
    }
    
    public static final <T,R> Iterable<R> sequence(T value, Apply<? super T,? extends R>[] fs) {
        return sequence(Arrays.asList(fs), value);
    }

    public static final <T,R> Iterable<R> sequence(Iterable<? extends Apply<? super T,? extends R>> fs, final T value) {
        return map(fs, new Transformer<Apply<? super T,? extends R>, R>() {
            @Override
            public R transform(Apply<? super T,? extends R> source) {
                return source.apply(value);
            }
        });
    }
    
    public static final <T,R> Iterable<R> sequence(T value, Iterable<? extends Apply<? super T,? extends R>> fs) {
        return sequence(fs, value);
    }
    
    public static final <T> Iterable<Iterable<T>> transpose(Iterable<T>... xs) {
        return transpose(Arrays.asList(xs));
    }
    
    public static final <T> Iterable<Iterable<T>> transpose(Iterable<? extends Iterable<T>> xs) {
        return new TransposingIterable<T>(xs);
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<Iterable<T>> transpose2(Iterable<T[]> xs) {
        return transpose(map(xs, (Transformer<T[], Iterable<T>>)(Object)arrayToIterable));
    }
    
    private static final Transformer<Object[], Iterable<Object>> arrayToIterable = new Transformer<Object[], Iterable<Object>>() {
        @Override
        public Iterable<Object> transform(Object[] source) {
            return Arrays.asList(source);
        }
    };
    
    private static final Transformer<Integer,Long> int2long = new Transformer<Integer,Long>() {
        @Override
        public Long transform(Integer source) {
            return source.longValue();
        }
    };
    
    private static final String LINE_SEP = System.getProperty("line.separator");
    
    public static final String unlines(CharSequence[] xs) {
        return unlines(Arrays.asList(xs));
    }
    
    public static final String unlines(Iterable<? extends CharSequence> xs) {
        return mkString(LINE_SEP, xs);
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Set<T> union(Set<T> e1, Set<T> e2, Set<T>... e) {
        return reduce(concat(Arrays.asList(e1, e2), Arrays.asList(e)), Monoids.<T>setUnion());
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Set<T> intersection(Set<T> e1, Set<T> e2, Set<T>... e) {
        return reduce(concat(Arrays.asList(e1, e2), Arrays.asList(e)), Monoids.<T>setIntersection());
    }
}
