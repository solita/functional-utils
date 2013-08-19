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

import fi.solita.utils.functional.Iterables.*;

public abstract class Functional {

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
    
    public static <T> Iterable<T> subtract(T[] a, final Collection<T> b) {
        return subtract(Arrays.asList(a), b);
    }
    
    public static <T> Iterable<T> subtract(Iterable<T> a, final T[] b) {
        return subtract(a, newSet(b));
    }
    
    public static <T> Iterable<T> subtract(T[] a, final T[] b) {
        return subtract(Arrays.asList(a), newSet(b));
    }

    public static <K, V> Option<V> find(Map<? extends K, V> map, K key) {
        return Option.of(map.get(key));
    }

    public static <T> Option<T> find(T[] elements, Apply<? super T, Boolean> filter) {
        return find(Arrays.asList(elements), filter);
    }

    public static <T> Option<T> find(Iterable<T> elements, Apply<? super T, Boolean> filter) {
        return headOption(filter(elements, filter));
    }

    public static <T> Iterable<T> filter(T[] elements, Apply<? super T, Boolean> filter) {
        return filter(Arrays.asList(elements), filter);
    }

    public static <T> Iterable<T> filter(Iterable<T> elements, Apply<? super T, Boolean> filter) {
        return new FilteringIterable<T>(elements, filter);
    }

    public static <T, E> Map<T, E> filter(Map<T, E> map, Apply<Map.Entry<T, E>, Boolean> filter) {
        return Collections.newMap(filter(map.entrySet(), filter));
    }

    public static <S, T> Iterable<T> map(S[] elements, Apply<? super S, ? extends T> transformer) {
        return map(Arrays.asList(elements), transformer);
    }

    public static <S, T> Iterable<T> map(Iterable<S> elements, Apply<? super S, ? extends T> transformer) {
        return new TransformingIterable<S,T>(elements, transformer);
    }

    public static <K1, V1, K2, V2> Map<K2, V2> map(Map<K1, V1> source, Apply<Map.Entry<K1, V1>, Map.Entry<K2, V2>> transformer) {
        return Collections.newMap(map(source.entrySet(), transformer));
    }

    public static <S, T> Iterable<T> flatMap(S[] elements, Apply<? super S, ? extends Iterable<T>> transformer) {
        return flatMap(Arrays.asList(elements), transformer);
    }

    public static <S, T> Iterable<T> flatMap(Iterable<S> elements, Apply<? super S, ? extends Iterable<? extends T>> transformer) {
        return flatten(map(elements, transformer));
    }

    public static <T> Iterable<T> flatten(T[][] elements) {
        return flatten(map(elements, new Transformer<T[], Iterable<T>>() {
            @Override
            public Iterable<T> transform(T[] source) {
                return Arrays.asList(source);
            }
        }));
    }
    
    public static <T> Iterable<T> flatten(Iterable<? extends T>[] elements) {
        return flatten(Arrays.asList(elements));
    }

    public static <T> Iterable<T> flatten(Iterable<? extends Iterable<? extends T>> elements) {
        return new FlatteningIterable<T>(elements);
    }
    
    public static <T> void foreach(T[] elements, Apply<? super T, Void> procedure) {
        foreach(Arrays.asList(elements), procedure);
    }
    
    public static <T> void foreach(Iterable<T> elements, Apply<? super T, Void> procedure) {
        for (T t: elements) {
            procedure.apply(t);
        }
    }

    /**
     * Non-lazy
     */
    public static <T> Iterable<List<T>> grouped(T[] elements, int size) {
        return grouped(Arrays.asList(elements), size);
    }

    /**
     * Non-lazy
     */
    public static <T> Iterable<List<T>> grouped(Iterable<T> elements, int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("size must be positive");
        }

        Option<Integer> estimatedSize = Iterables.resolveSize.apply(elements);
        List<List<T>> target = estimatedSize.isDefined() ? Collections.<List<T>>newListOfSize(estimatedSize.get() / size) : Collections.<List<T>>newList();
        Iterator<T> it = elements.iterator();
        while (it.hasNext()) {
            List<T> group = Collections.newListOfSize(size);
            for (int i = 0; i < size; ++i) {
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
    
    /**
     * Non-lazy
     */
    public static <G, T> Map<G, List<T>> groupBy(T[] elements, Apply<? super T,G> f) {
          return groupBy(Arrays.asList(elements), f);
    }
    
    /**
     * Non-lazy
     */
    public static <G, T> Map<G, List<T>> groupBy(Iterable<T> elements, Apply<? super T,G> f) {
        Map<G, List<T>> target = newMap();
        for (T t: elements) {
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
    
    public static <T> T head(T[] elements) {
        return elements[0];
    }

    public static <T> T head(Iterable<T> elements) {
        return elements.iterator().next();
    }
    
    public static <T> Option<T> headOption(T[] elements) {
          return elements.length == 0 ? Option.<T>None() : Some(elements[0]);
    }

    public static <T> Option<T> headOption(Iterable<T> elements) {
        Iterator<T> it = elements.iterator();
        if (it.hasNext()) {
            return Some(it.next());
        } else {
            return None();
        }
    }
    
    public static <T> Iterable<T> tail(T[] elements) {
          return tail(Arrays.asList(elements));
    }

    public static <T> Iterable<T> tail(Iterable<T> elements) {
        return drop(elements, 1);
    }
    
    public static <T> T last(T[] elements) {
          return elements[elements.length-1];
    }

    public static <T> T last(Iterable<T> elements) {
        return lastOption(elements).get();
    }
    
    public static <T> Option<T> lastOption(T[] elements) {
          return elements.length == 0 ? Option.<T>None() : Some(elements[elements.length-1]);
    }

    public static <T> Option<T> lastOption(Iterable<T> elements) {
        if (elements instanceof List) {
            if (((List<T>) elements).isEmpty()) {
                return None();
            }
            return Some(((List<T>) elements).get(((List<T>) elements).size()-1));
        } else {
            Iterator<T> it = elements.iterator();
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
    
    public static <T> Iterable<T> init(T[] elements) {
        return init(Arrays.asList(elements));
    }

    public static <T> Iterable<T> init(Iterable<T> elements) {
        return take(elements, size(elements)-1);
    }
    
    public static <T> Iterable<T> take(T[] elements, int amount) {
        return take(Arrays.asList(elements), amount);
    }

    public static <T> Iterable<T> take(final Iterable<T> elements, final int amount) {
        return new Iterables.TakingIterable<T>(elements, amount);
    }
    
    public static <T> Iterable<T> drop(T[] elements, int amount) {
        return drop(Arrays.asList(elements), amount);
    }

    public static <T> Iterable<T> drop(final Iterable<T> elements, final int amount) {
        return new Iterables.DroppingIterable<T>(elements, amount);
    }
    
    public static <T> Iterable<T> takeWhile(T[] elements, Apply<? super T, Boolean> predicate) {
        return takeWhile(Arrays.asList(elements), predicate);
    }

    public static <T> Iterable<T> takeWhile(final Iterable<T> elements, final Apply<? super T, Boolean> predicate) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    private Option<T> next;
                    private final Iterator<T> source = elements.iterator();
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
    
    public static <T> Iterable<T> dropWhile(T[] elements, Apply<? super T, Boolean> predicate) {
          return dropWhile(Arrays.asList(elements), predicate);
    }

    public static <T> Iterable<T> dropWhile(final Iterable<T> elements, final Apply<? super T, Boolean> predicate) {
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                return new Iterator<T>() {
                    private boolean dropping = true;
                    private final Iterator<T> source = elements.iterator();
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
    
    public static <T> Pair<Iterable<T>, Iterable<T>> span(final Iterable<T> elements, final Apply<? super T, Boolean> predicate) {
        // TODO: a more efficient implementation
        return Pair.of(takeWhile(elements, predicate), dropWhile(elements, predicate));
    }

    public static boolean isEmpty(Iterable<?> elements) {
        return !elements.iterator().hasNext();
    }

    public static int size(Iterable<?> elements) {
        if (elements instanceof Collection) {
            return ((Collection<?>) elements).size();
        } else {
            int s = 0;
            Iterator<?> it = elements.iterator();
            while (it.hasNext()) {
                s++;
                it.next();
            }
            return s;
        }
    }

    public static <T> boolean contains(T[] elements, T element) {
        return contains(Arrays.asList(elements), element);
    }

    public static <T,E> boolean contains(Iterable<T> elements, final T element) {
        return exists(elements, new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return candidate.equals(element);
            }
        });
    }

    public static <T> boolean exists(T[] elements, Apply<T, Boolean> filter) {
        return exists(Arrays.asList(elements), filter);
    }

    public static <T> boolean exists(Iterable<T> elements, Apply<? super T, Boolean> filter) {
        return !isEmpty(filter(elements, filter));
    }

    public static <T> boolean forAll(T[] elements, Apply<? super T, Boolean> filter) {
        return forAll(Arrays.asList(elements), filter);
    }

    public static <T> boolean forAll(Iterable<T> elements, Apply<? super T, Boolean> filter) {
        return !exists(elements, Predicates.not(filter));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Iterable<T> cons(T element, Iterable<? extends T> elements) {
        return concat(Arrays.asList(element), elements);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Iterable<T> cons(T element, T[] elements) {
        return concat(Arrays.asList(element), elements);
    }

    public static <T> Iterable<T> concat(T[] elements1, Iterable<? extends T> elements2) {
        return concat(Arrays.asList(elements1), elements2);
    }

    public static <T> Iterable<T> concat(Iterable<? extends T> elements1, T[] elements2) {
        return concat(elements1, Arrays.asList(elements2));
    }

    public static <T> Iterable<T> concat(T[] elements1, T[] elements2) {
        return concat(Arrays.asList(elements1), Arrays.asList(elements2));
    }

    @SuppressWarnings("unchecked")
    public static <T> Iterable<T> concat(Iterable<? extends T> elements1, Iterable<? extends T> elements2) {
        return new ConcatenatingIterable<T>(Arrays.asList(elements1, elements2));
    }

    @SuppressWarnings("unchecked")
    public static <T> Iterable<T> concat(Iterable<? extends T> elements1, Iterable<? extends T> elements2, Iterable<? extends T> elements3) {
        return new ConcatenatingIterable<T>(Arrays.asList(elements1, elements2, elements3));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Iterable<T> concat(Iterable<? extends T> elements1, Iterable<? extends T> elements2, Iterable<? extends T> elements3, Iterable<? extends T> elements4) {
        return new ConcatenatingIterable<T>(Arrays.asList(elements1, elements2, elements3, elements4));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Iterable<T> concat(Iterable<? extends T> elements1, final Iterable<? extends T> elements2, Iterable<? extends T> elements3, Iterable<? extends T> elements4, Iterable<? extends T> elements5, Iterable<? extends T>... rest) {
        return new ConcatenatingIterable<T>(concat(Arrays.asList(elements1, elements2, elements3, elements4, elements5), rest));
    }
    
    public static <T extends Comparable<T>> Iterable<T> sort(T[] elements) {
          return sort(Arrays.asList(elements));
    }
    
    public static <T extends Comparable<T>> Iterable<T> sort(Iterable<T> elements) {
        return sort(elements, Ordering.Natural());
    }
    
    public static <T> Iterable<T> sort(T[] elements, Comparator<? super T> comparator) {
          return sort(Arrays.asList(elements), comparator);
    }

    public static <T> Iterable<T> sort(Iterable<T> elements, Comparator<? super T> comparator) {
        if (isEmpty(elements)) {
            return emptyList();
        }
        return new Iterables.SortingIterable<T>(elements, comparator);
    }
    
    public static <T extends SemiGroup<T>> T reduce(T e1) {
        return e1;
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends SemiGroup<T>> T reduce(T e1, T e2) {
        return reduce(Arrays.asList(e1, e2)).get();
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends SemiGroup<T>> T reduce(T e1, T e2, T e3) {
        return reduce(Arrays.asList(e1, e2, e3)).get();
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends SemiGroup<T>> T reduce(T e1, T e2, T e3, T e4) {
        return reduce(Arrays.asList(e1, e2, e3, e4)).get();
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends SemiGroup<T>> T reduce(T e1, T e2, T e3, T e4, T... elements) {
        return reduce(concat(Arrays.asList(e1, e2, e3, e4), elements)).get();
    }
    
    public static <T extends SemiGroup<T>>  Option<T> reduce(T[] elements) {
        return reduce(Arrays.asList(elements));
    }

    public static <T extends SemiGroup<T>> Option<T> reduce(Iterable<? extends T> elements) {
        if (isEmpty(elements)) {
            return None();
        }
        return fold(elements, head(elements));
    }
    
    public static <T> T reduce(T[] elements, Monoid<T> m) {
        return reduce(Arrays.asList(elements), m);
    }
  
    public static <T> T reduce(Iterable<? extends T> elements, Monoid<T> m) {
        return fold(cons(m.zero(), elements), m).get();
    }
    
    /**
     * @return <i>None</i> if <i>elements</i> is empty
     */
    public static <T> Option<T> fold(T[] elements, Apply<Tuple2<T,T>, T> f) {
        return fold(Arrays.asList(elements), f);
    }

    /**
     * @return <i>None</i> if <i>elements</i> is empty
     */
    public static <T> Option<T> fold(Iterable<? extends T> elements, Apply<Tuple2<T,T>, T> f) {
        Option<? extends T> h = headOption(elements);
        if (h.isDefined()) {
            T ret = h.get();
            for (T t : drop(elements, 1)) {
                ret = f.apply(Tuple.of(ret, t));
            }
            return Some(ret);
        }
        return None();
    }
    
    public static long sum(Integer... elements) {
        return sum(Arrays.asList(elements));
    }

    public static long sum(Iterable<Integer> elements) {
        return reduce(map(elements, int2long), Monoids.longSum);
    }

    public static long product(Integer... elements) {
        return product(Arrays.asList(elements));
    }
    
    public static long product(Iterable<Integer> elements) {
        return reduce(map(elements, int2long), Monoids.longProduct);
    }
    
    public static <T extends Comparable<T>> T min(T e1, T... elements) {
        return min(cons(e1, elements)).get();
    }

    public static <T extends Comparable<T>> Option<T> min(T[] elements) {
        return min(Arrays.asList(elements));
    }
    
    public static <T extends Comparable<T>> Option<T> min(Iterable<T> elements) {
        return headOption(sort(elements));
    }
    
    public static <T extends Comparable<T>> T max(T e1, T... elements) {
        return max(cons(e1, elements)).get();
    }
    
    public static <T extends Comparable<T>> Option<T> max(T[] elements) {
        return max(Arrays.asList(elements));
    }

    public static <T extends Comparable<T>> Option<T> max(Iterable<T> elements) {
        return headOption(sort(elements, Ordering.Natural().reverse()));
    }

    public static <A,B> Iterable<Tuple2<A, B>> zip(A[] a, B[] b) {
        return zip(Arrays.asList(a), Arrays.asList(b));
    }

    public static <A,B> Iterable<Tuple2<A, B>> zip(A[] a, Iterable<B> b) {
        return zip(Arrays.asList(a), b);
    }

    public static <A,B> Iterable<Tuple2<A, B>> zip(Iterable<A> a, B[] b) {
        return zip(a, Arrays.asList(b));
    }

    public static <A,B> Iterable<Tuple2<A, B>> zip(Iterable<A> a, Iterable<B> b) {
        return new ZippingIterable<A,B>(a, b);
    }
    
    public static <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, B[] b, C[] c) {
        return zip(Arrays.asList(a), Arrays.asList(b), Arrays.asList(c));
    }
    
    public static <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, Iterable<B> b, Iterable<C> c) {
        return zip(Arrays.asList(a), b, c);
    }
    
    public static <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, B[] b, Iterable<C> c) {
        return zip(a, Arrays.asList(b), c);
    }
    
    public static <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, Iterable<B> b, C[] c) {
        return zip(a, b, Arrays.asList(c));
    }
    
    public static <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, B[] b, Iterable<C> c) {
        return zip(Arrays.asList(a), Arrays.asList(b), c);
    }
    
    public static <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, B[] b, C[] c) {
        return zip(a, Arrays.asList(b), Arrays.asList(c));
    }
    
    public static <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, Iterable<B> b, C[] c) {
        return zip(Arrays.asList(a), b, Arrays.asList(c));
    }
   
    @SuppressWarnings("unchecked")
    public static <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, Iterable<B> b, Iterable<C> c) {
        return map(zip(zip(a, b), c), ( Transformer<Tuple2<Tuple2<A, B>, C>, Tuple3<A, B, C>>)(Object)zip3Transformer);
    }
    
    private static final Transformer<Tuple2<Tuple2<Object,Object>,Object>,Tuple3<Object,Object,Object>> zip3Transformer = new Transformer<Tuple2<Tuple2<Object, Object>, Object>, Tuple3<Object, Object, Object>>() {
        @Override
        public Tuple3<Object, Object, Object> transform(Tuple2<Tuple2<Object, Object>, Object> source) {
            return source._1.append(source._2);
        }
    };

    public static <A> Iterable<Tuple2<Integer, A>> zipWithIndex(Iterable<A> a) {
        return new ZippingIterable<Integer,A>(range(0), a);
    }

    public static <A> Iterable<Tuple2<Integer, A>> zipWithIndex(A[] a) {
        return new ZippingIterable<Integer,A>(range(0), Arrays.asList(a));
    }

    public static Iterable<Integer> range(int from) {
        return range(Enumerables.ints, from);
    }

    public static Iterable<Integer> range(int from, int toInclusive) {
        return new RangeIterable<Integer>(Enumerables.ints, from, toInclusive, toInclusive - from + 1);
    }
    
    public static <T, S extends Enumerable<T> & Bounded<T>> Iterable<T> range(S enumeration) {
        return range(enumeration, enumeration.minBound());
    }
    
    public static <T> Iterable<T> range(Enumerable<T> enumeration, T from) {
        return new RangeIterable<T>(enumeration, from, Option.<T>None());
    }
    
    public static <T> Iterable<T> range(Enumerable<T> enumeration, T from, T toInclusive) {
        return new RangeIterable<T>(enumeration, from, Some(toInclusive));
    }

    public static <T> Iterable<T> repeat(T value) {
        return new RepeatingIterable<T>(value);
    }

    public static <T> Iterable<T> repeat(T value, int amount) {
        return new RepeatingIterable<T>(value, amount);
    }
    
    public static String mkString(Iterable<Character> elements) {
        StringBuilder sb = new StringBuilder();
        for (char c: elements) {
            sb.append(c);
        }
        return sb.toString();
    }
    
    public static String mkString(CharSequence delim, CharSequence[] elements) {
        return mkString(delim, Arrays.asList(elements));
    }

    public static String mkString(CharSequence delim, Iterable<? extends CharSequence> elements) {
        StringBuilder sb = new StringBuilder();
        for (CharSequence s: elements) {
            if (sb.length() > 0) {
                sb.append(delim);
            }
            sb.append(s);
        }
        return sb.toString();
    }

    public static <T> Iterable<T> reverse(Iterable<T> elements) {
        return new Iterables.ReversingIterable<T>(elements);
    }

    public static <T,R> Iterable<R> sequence(Iterable<? extends Apply<? super T,? extends R>> elements, final T t) {
        return map(elements, new Transformer<Apply<? super T,? extends R>, R>() {
            @Override
            public R transform(Apply<? super T,? extends R> source) {
                return source.apply(t);
            }
        });
    }
    
    public static <T> Iterable<Iterable<T>> transpose(Iterable<? extends Iterable<T>> elements) {
        return new TransposingIterable<T>(elements);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Iterable<Iterable<T>> transpose2(Iterable<T[]> elements) {
        return transpose(map(elements, (Transformer<T[], Iterable<T>>)(Object)arrayToIterable));
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
            return (long)source;
        }
    };
    
    private static final String LINE_SEP = System.getProperty("line.separator");
    
    public static final String unlines(Iterable<? extends CharSequence> elements) {
        return mkString(LINE_SEP, elements);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Set<T> union(Set<T> e1, Set<T> e2) {
        return reduce(Arrays.asList(e1, e2), Monoids.<T>setUnion());
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Set<T> union(Set<T> e1, Set<T> e2, Set<T>... e) {
        return reduce(concat(Arrays.asList(e1, e2), e), Monoids.<T>setUnion());
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Set<T> intersection(Set<T> e1, Set<T> e2) {
        return reduce(Arrays.asList(e1, e2), Monoids.<T>setIntersection());
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Set<T> intersection(Set<T> e1, Set<T> e2, Set<T>... e) {
        return reduce(concat(Arrays.asList(e1, e2), e), Monoids.<T>setIntersection());
    }
}
