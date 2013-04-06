package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newList;
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
import fi.solita.utils.functional.Iterables.RangeIterable;
import fi.solita.utils.functional.Iterables.RepeatingIterable;
import fi.solita.utils.functional.Iterables.TransformingIterable;
import fi.solita.utils.functional.Iterables.ZippingIterable;

public abstract class Functional {

    /**
     * Returns a new iterable <code>a - b</code>, i.e. one that contains all elements of <code>a</code> that
     * don't exist in <code>b</code>.
     */
    public static <T> Iterable<T> subtract(Iterable<T> a, final Collection<T> b) {
        return filter(a, new Predicate<T>() {
            @Override
            public boolean accept(T object) {
                return !b.contains(object);
            }
        });
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

    public static <T, E> Map<T, E> filter(Map<T, E> map, Apply<Map.Entry<? super T, ? super E>, Boolean> filter) {
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

    public static <S, T> Iterable<T> flatMap(Iterable<S> elements, Apply<? super S, ? extends Iterable<T>> transformer) {
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
        return flatten(map(elements, Function1.<Iterable<? extends T>>id()));
    }

    public static <T> Iterable<T> flatten(Iterable<? extends Iterable<? extends T>> elements) {
        return new ConcatenatingIterable<T>(elements);
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

        List<List<T>> target = Collections.newList();
        Iterator<T> it = elements.iterator();
        while (it.hasNext()) {
            List<T> group = Collections.newListOfSize(size);
            for (@SuppressWarnings("unused") int i: range(1, size)) {
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
    	  return head(Arrays.asList(elements));
    }

    public static <T> T head(Iterable<T> elements) {
    	return elements.iterator().next();
    }
    
    public static <T> Option<T> headOption(T[] elements) {
    	  return headOption(Arrays.asList(elements));
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
    	  return last(Arrays.asList(elements));
    }

    public static <T> T last(Iterable<T> elements) {
        Iterator<T> it = elements.iterator();
        T ret = it.next();
        while (it.hasNext()) {
            ret = it.next();
        }
        return ret;
    }
    
    public static <T> Option<T> lastOption(T[] elements) {
    	  return lastOption(Arrays.asList(elements));
    }

    public static <T> Option<T> lastOption(Iterable<T> elements) {
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
        if (amount < 0) {
            throw new IllegalArgumentException("amount must be gte 0");
        }
        return new Iterable<T>() {
            @Override
            public Iterator<T> iterator() {
                Iterator<T> it = elements.iterator();
                int left = amount;
                while (left > 0 && it.hasNext()) {
                    it.next();
                    left--;
                }
                return it;
            }
        };
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
                    private Iterator<T> source = elements.iterator();
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
                    private Iterator<T> source = elements.iterator();
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
        return Iterables.resolveSize(elements).getOrElse(Collections.newList(elements).size());
    }

    public static <T> boolean contains(T[] elements, T element) {
        return contains(Arrays.asList(elements), element);
    }

    public static <T,E> boolean contains(Iterable<T> elements, T element) {
        return exists(elements, Predicates.equalTo(element));
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
    	return isEmpty(filter(elements, Predicates.not(filter)));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Iterable<T> cons(T element, Iterable<? extends T> elements) {
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

    public static <T> Iterable<T> concat(Iterable<? extends T> elements1, Iterable<? extends T> elements2) {
    	return new ConcatenatingIterable<T>(Collections.newList(elements1, elements2));
    }

    public static <T> Iterable<T> concat(final Iterable<? extends T> elements1, final Iterable<? extends T> elements2, final Iterable<? extends T> elements3) {
        return concat(elements1, concat(elements2, elements3));
    }
    
    public static <T> Iterable<T> concat(final Iterable<? extends T> elements1, final Iterable<? extends T> elements2, final Iterable<? extends T> elements3, final Iterable<? extends T> elements4) {
        return concat(elements1, concat(elements2, elements3, elements4));
    }
    
    public static <T> Iterable<T> concat(final Iterable<? extends T> elements1, final Iterable<? extends T> elements2, final Iterable<? extends T> elements3, final Iterable<? extends T> elements4, final Iterable<? extends T> elements5, final Iterable<? extends T>... rest) {
        return concat(elements1, concat(elements2, concat(elements3, elements4, elements5, flatten(rest))));
    }
    
    public static <T extends Comparable<T>> Iterable<T> sort(final T[] elements) {
    	  return sort(Arrays.asList(elements));
    }
    
    public static <T extends Comparable<T>> Iterable<T> sort(final Iterable<T> elements) {
        if (isEmpty(elements)) {
            return newSet();
        }
        return sort(elements, Ordering.Natural());
    }
    
    public static <T> Iterable<T> sort(T[] elements, Comparator<? super T> comparator) {
    	  return sort(Arrays.asList(elements), comparator);
    }

    public static <T> Iterable<T> sort(final Iterable<T> elements, final Comparator<? super T> comparator) {
        return new Iterables.SortingIterable<T>(elements, comparator);
    }
    
    public static <T extends SemiGroup<T>> T reduce(T e1) {
        return e1;
    }
    
    public static <T extends SemiGroup<T>> T reduce(T e1, T e2) {
        return reduce(newList(e1, e2)).get();
    }
    
    public static <T extends SemiGroup<T>> T reduce(T e1, T e2, T e3) {
        return reduce(newList(e1, e2, e3)).get();
    }
    
    public static <T extends SemiGroup<T>> T reduce(T e1, T e2, T e3, T e4) {
        return reduce(newList(e1, e2, e3, e4)).get();
    }
    
    public static <T extends SemiGroup<T>> T reduce(T e1, T e2, T e3, T e4, T... elements) {
        return reduce(concat(newList(e1, e2, e3, e4), elements)).get();
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
        if (isEmpty(elements)) {
            return None();
        }
        T ret = head(elements);
        for (T t : drop(elements, 1)) {
            ret = f.apply(Tuple.of(ret, t));
        }
        return Some(ret);
    }
    
    public static long sum(int e1) {
        return sum(Arrays.asList(e1));
    }
    
    public static long sum(int e1, int e2) {
        return sum(Arrays.asList(e1, e2));
    }
    
    public static long sum(int e1, int e2, int e3) {
        return sum(Arrays.asList(e1, e2, e3));
    }
    
    public static long sum(int e1, int e2, int e3, Integer... rest) {
        return sum(concat(Arrays.asList(e1, e2, e3), rest));
    }

    public static long sum(Iterable<Integer> elements) {
        return reduce(map(elements, Transformers.int2long), Monoid.longSum);
    }

    public static long product(int e1) {
        return product(Arrays.asList(e1));
    }
    
    public static long product(int e1, int e2) {
        return product(Arrays.asList(e1, e2));
    }
    
    public static long product(int e1, int e2, int e3) {
        return product(Arrays.asList(e1, e2, e3));
    }
    
    public static long product(int e1, int e2, int e3, Integer... rest) {
        return product(concat(Arrays.asList(e1, e2, e3), rest));
    }
    
    public static long product(Iterable<Integer> elements) {
        return reduce(map(elements, Transformers.int2long), Monoid.longProduct);
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> T min(T e1, T... elements) {
        return min(concat(Arrays.asList(e1), elements)).get();
    }

    public static <T extends Comparable<T>> Option<T> min(Iterable<T> elements) {
        return headOption(sort(elements));
    }
    
    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> T max(T e1, T... elements) {
        return max(concat(Arrays.asList(e1), elements)).get();
    }

    public static <T extends Comparable<T>> Option<T> max(Iterable<T> elements) {
        return headOption(sort(elements, Ordering.Natural().reverse()));
    }

    public static <A,B> Iterable<Map.Entry<A, B>> zip(A[] a, B[] b) {
        return zip(Arrays.asList(a), Arrays.asList(b));
    }

    public static <A,B> Iterable<Map.Entry<A, B>> zip(A[] a, Iterable<B> b) {
        return zip(Arrays.asList(a), b);
    }

    public static <A,B> Iterable<Map.Entry<A, B>> zip(Iterable<A> a, B[] b) {
        return zip(a, Arrays.asList(b));
    }

    public static <A,B> Iterable<Map.Entry<A, B>> zip(Iterable<A> a, Iterable<B> b) {
        return new ZippingIterable<A,B>(a, b);
    }

    public static <A> Iterable<Map.Entry<Integer, A>> zipWithIndex(Iterable<A> a) {
        return new ZippingIterable<Integer,A>(new RangeIterable(0), a);
    }

    public static <A> Iterable<Map.Entry<Integer, A>> zipWithIndex(A[] a) {
        return new ZippingIterable<Integer,A>(new RangeIterable(0), Arrays.asList(a));
    }

    public static Iterable<Integer> range(int from) {
        return new RangeIterable(from);
    }

    public static Iterable<Integer> range(int from, int toInclusive) {
        return new RangeIterable(from, toInclusive);
    }

    public static <T> Iterable<T> repeat(T value) {
        return new RepeatingIterable<T>(value);
    }

    public static <T> Iterable<T> repeat(T value, int amount) {
        return new RepeatingIterable<T>(value, amount);
    }
    
    public static String mkString(Iterable<Character> elements) {
        return mkString("", map(elements, Transformers.toString));
    }

    public static String mkString(String delim, Iterable<String> elements) {
        StringBuilder sb = new StringBuilder();
        for (String s: elements) {
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
    
    private static final String LINE_SEP = System.getProperty("line.separator");
    
    public static final String unlines(Iterable<String> elements) {
        return mkString(LINE_SEP, elements);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Set<T> union(Set<T> e1, Set<T> e2) {
        return reduce(newList(e1, e2), (Monoid<Set<T>>)(Object)Monoid.setUnion);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Set<T> union(Set<T> e1, Set<T> e2, Set<T>... e) {
        return reduce(concat(newList(e1, e2), e), (Monoid<Set<T>>)(Object)Monoid.setUnion);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Set<T> intersection(Set<T> e1, Set<T> e2) {
        return reduce(newList(e1, e2), (Monoid<Set<T>>)(Object)Monoid.setIntersection);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Set<T> intersection(Set<T> e1, Set<T> e2, Set<T>... e) {
        return reduce(concat(newList(e1, e2), e), (Monoid<Set<T>>)(Object)Monoid.setIntersection);
    }
}
