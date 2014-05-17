package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Option.Some;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import fi.solita.utils.functional.Iterables.ConcatenatingIterable;
import fi.solita.utils.functional.Iterables.ZippingIterable;

public abstract class FunctionalA extends FunctionalM {
    public static final <T> Iterable<T> subtract(T[] a, Collection<T> b) {
        return subtract(Arrays.asList(a), b);
    }
    
    public static final <T> Iterable<T> subtract(Iterable<T> a, T[] b) {
        return subtract(a, newSet(b));
    }
    
    public static final <T> Iterable<T> subtract(T[] a, T[] b) {
        return subtract(Arrays.asList(a), newSet(b));
    }
    
    
    
    public static final <T> Option<T> find(T[] xs, Apply<? super T, Boolean> predicate) {
        return find(Arrays.asList(xs), predicate);
    }
    
    public static final <T> Option<T> find(Apply<? super T, Boolean> predicate, T[] xs) {
        return find(Arrays.asList(xs), predicate);
    }
    
    
    
    public static final <T> Iterable<T> filter(T[] xs, Apply<? super T, Boolean> predicate) {
        return filter(Arrays.asList(xs), predicate);
    }
    
    public static final <T> Iterable<T> filter(Apply<? super T, Boolean> predicate, T[] xs) {
        return filter(Arrays.asList(xs), predicate);
    }
    
    
    
    public static final <S, T> Iterable<T> map(S[] xs, Apply<? super S, ? extends T> f) {
        return map(Arrays.asList(xs), f);
    }
    
    public static final <S, T> Iterable<T> map(Apply<? super S, ? extends T> f, S[] xs) {
        return map(Arrays.asList(xs), f);
    }
    
    public static final <S, T1, T2> Iterable<Pair<T1,T2>> map(S[] xs, Apply<? super S, T1> f1, Apply<? super S, T2> f2) {
        return map(Arrays.asList(xs), f1, f2);
    }
    
    public static final <S, T1, T2> Iterable<Pair<T1,T2>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, S[] elements) {
        return map(Arrays.asList(elements), f1, f2);
    }
    
    public static final <S, T1, T2, T3> Iterable<Tuple3<T1,T2,T3>> map(S[] xs, Apply<? super S, T1> f1, Apply<? super S, T2> f2, Apply<? super S, T3> f3) {
        return map(Arrays.asList(xs), f1, f2, f3);
    }
    
    public static final <S, T1, T2, T3> Iterable<Tuple3<T1,T2,T3>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, Apply<? super S, T3> f3, S[] xs) {
        return map(Arrays.asList(xs), f1, f2, f3);
    }
    
    public static final <S, T1, T2, T3, T4> Iterable<Tuple4<T1,T2,T3,T4>> map(S[] xs, Apply<? super S, T1> f1, Apply<? super S, T2> f2, Apply<? super S, T3> f3, Apply<? super S, T4> f4) {
        return map(Arrays.asList(xs), f1, f2, f3, f4);
    }
    
    public static final <S, T1, T2, T3, T4> Iterable<Tuple4<T1,T2,T3,T4>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, Apply<? super S, T3> f3, Apply<? super S, T4> f4, S[] xs) {
        return map(Arrays.asList(xs), f1, f2, f3, f4);
    }

    
    
    public static final <S, T> Iterable<T> flatMap(S[] xs, Apply<? super S, ? extends Iterable<T>> f) {
        return flatMap(Arrays.asList(xs), f);
    }
    
    public static final <S, T> Iterable<T> flatMap(Apply<? super S, ? extends Iterable<T>> f, S[] xs) {
        return flatMap(Arrays.asList(xs), f);
    }
    
    
    
    public static final <T> Iterable<T> flatten(T[][] xs) {
        return flatten(map(xs, new Transformer<T[], Iterable<T>>() {
            @Override
            public Iterable<T> transform(T[] source) {
                return Arrays.asList(source);
            }
        }));
    }
    
    public static final <T> Iterable<T> flatten(T[] x, T[]... xs) {
        return flatten(map(cons(x, xs), new Transformer<T[], Iterable<T>>() {
            @Override
            public Iterable<T> transform(T[] source) {
                return Arrays.asList(source);
            }
        }));
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2) {
        return flatten(Arrays.asList(x1, x2));
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3) {
        return flatten(Arrays.asList(x1, x2, x3));
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4) {
        return flatten(Arrays.asList(x1, x2, x3, x4));
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5) {
        return flatten(Arrays.asList(x1, x2, x3, x4, x5));
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6) {
        return flatten(Arrays.asList(x1, x2, x3, x4, x5, x6));
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6, Iterable<? extends T>... xs) {
        return flatten(concat(Arrays.asList(x1, x2, x3, x4, x5, x6), xs));
    }
    
    
    
    public static final <T> void foreach(T[] xs, Apply<? super T, Void> procedure) {
        foreach(Arrays.asList(xs), procedure);
    }
    
    public static final <T> void foreach(T[] xs, ApplyVoid<? super T> procedure) {
        foreach(Arrays.asList(xs), procedure);
    }
    
    public static final <T> void foreach(Apply<? super T, Void> procedure, T[] xs) {
        foreach(Arrays.asList(xs), procedure);
    }
    
    public static final <T> void foreach(ApplyVoid<? super T> procedure, T[] xs) {
        foreach(Arrays.asList(xs), procedure);
    }
    
    
    
    public static final <T> Iterable<List<T>> grouped(long groupSize, T[] xs) {
        return grouped(Arrays.asList(xs), groupSize);
    }
    
    
    
    public static final <T> Iterable<Iterable<T>> group(T[] xs) {
        return group(Arrays.asList(xs));
    }
    
    public static final <T> Iterable<Iterable<T>> group(T[] xs, Apply<Tuple2<T,T>, Boolean> comparator) {
        return group(Arrays.asList(xs), comparator);
    }
    
    public static final <T> Iterable<Iterable<T>> group(Apply<Tuple2<T,T>, Boolean> comparator, T[] xs) {
        return group(Arrays.asList(xs), comparator);
    }
    
    
    
    public static final <G, T> Map<G, List<T>> groupBy(T[] xs, Apply<? super T,G> f) {
          return groupBy(Arrays.asList(xs), f);
    }
    
    public static final <G, T> Map<G, List<T>> groupBy(Apply<? super T,G> f, T[] xs) {
        return groupBy(Arrays.asList(xs), f);
    }
    
    
    
    public static final <T> T head(T[] xs) {
        return xs[0];
    }
    
    
    
    public static final <T> Option<T> headOption(T[] xs) {
        return xs.length == 0 ? Option.<T>None() : Some(xs[0]);
    }
    
    
    
    public static final <T> Iterable<T> tail(T[] xs) {
        return tail(Arrays.asList(xs));
    }
    
    
    
    public static final <T> T last(T[] xs) {
        return xs[xs.length-1];
    }
    
    
    
    public static final <T> Option<T> lastOption(T[] xs) {
        return xs.length == 0 ? Option.<T>None() : Some(xs[xs.length-1]);
    }
    
    
    
    public static final <T> Iterable<T> init(T[] xs) {
        return init(Arrays.asList(xs));
    }
    
    
    
    public static final <T> Iterable<T> take(long amount, T[] xs) {
        return new Iterables.TakingIterable<T>(Arrays.asList(xs), amount);
    }
    
    
    
    public static final <T> Iterable<T> drop(long amount, T[] xs) {
        return new Iterables.DroppingIterable<T>(Arrays.asList(xs), amount);
    }
    
    
    
    public static final <T> Iterable<T> takeWhile(T[] xs, Apply<? super T, Boolean> predicate) {
        return takeWhile(Arrays.asList(xs), predicate);
    }
    
    public static final <T> Iterable<T> takeWhile(Apply<? super T, Boolean> predicate, T[] xs) {
        return takeWhile(Arrays.asList(xs), predicate);
    }
    
    
    
    public static final <T> Iterable<T> dropWhile(T[] xs, Apply<? super T, Boolean> predicate) {
        return dropWhile(Arrays.asList(xs), predicate);
    }
  
    public static final <T> Iterable<T> dropWhile(Apply<? super T, Boolean> predicate, T[] xs) {
        return dropWhile(Arrays.asList(xs), predicate);
    }
    
    
    
    public static final <T> boolean isEmpty(T[] xs) {
        return xs.length == 0;
    }
    
    
    
    public static final <T> int size(T[] xs) {
        return xs.length;
    }
    
    
    
    public static final <T> boolean contains(T candidate, T[] xs) {
        return FunctionalImpl.exists(Arrays.asList(xs), Predicates.equalTo(candidate));
    }
    
    
    
    public static final <T> boolean exists(T[] xs, Apply<T, Boolean> predicate) {
        return exists(Arrays.asList(xs), predicate);
    }
    
    public static final <T> boolean exists(Apply<T, Boolean> predicate, T[] xs) {
        return exists(Arrays.asList(xs), predicate);
    }
    
    
    
    public static final <T> boolean forall(T[] xs, Apply<? super T, Boolean> predicate) {
        return forall(Arrays.asList(xs), predicate);
    }
    
    public static final <T> boolean forall(Apply<? super T, Boolean> predicate, T[] xs) {
        return forall(Arrays.asList(xs), predicate);
    }
    
    
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> cons(T x, T[] xs) {
        return concat(Arrays.asList(x), xs);
    }
    
    
    
    public static final <T> Iterable<T> concat(T[] a, Iterable<? extends T> b) {
        return concat(Arrays.asList(a), b);
    }

    public static final <T> Iterable<T> concat(Iterable<? extends T> a, T[] b) {
        return concat(a, Arrays.asList(b));
    }

    public static final <T> Iterable<T> concat(T[] a, T[] b) {
        return concat(Arrays.asList(a), Arrays.asList(b));
    }

    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3) {
        return new ConcatenatingIterable<T>(Arrays.asList(x1, x2, x3));
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4) {
        return new ConcatenatingIterable<T>(Arrays.asList(x1, x2, x3, x4));
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5) {
        return new ConcatenatingIterable<T>(Arrays.asList(x1, x2, x3, x4, x5));
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6) {
        return new ConcatenatingIterable<T>(Arrays.asList(x1, x2, x3, x4, x5, x6));
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6, Iterable<? extends T>... xs) {
        return new ConcatenatingIterable<T>(concat(Arrays.asList(x1, x2, x3, x4, x5, x6), xs));
    }
    
    
    
    public static final <T extends Comparable<T>> Iterable<T> sort(T[] xs) {
        return sort(Arrays.asList(xs));
    }
    
    public static final <T> Iterable<T> sort(T[] xs, Comparator<? super T> comparator) {
        return sort(Arrays.asList(xs), comparator);
    }
  
    public static final <T> Iterable<T> sort(Comparator<? super T> comparator, T[] xs) {
        return sort(Arrays.asList(xs), comparator);
    }
    
    
    
    @SuppressWarnings("unchecked")
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2) {
        return reduce(Arrays.asList(x1, x2)).get();
    }
    
    @SuppressWarnings("unchecked")
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2, T x3) {
        return reduce(Arrays.asList(x1, x2, x3)).get();
    }
    
    @SuppressWarnings("unchecked")
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2, T x3, T x4) {
        return reduce(Arrays.asList(x1, x2, x3, x4)).get();
    }
    
    @SuppressWarnings("unchecked")
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2, T x3, T x4, T x5) {
        return reduce(Arrays.asList(x1, x2, x3, x4, x5)).get();
    }
    
    @SuppressWarnings("unchecked")
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2, T x3, T x4, T x5, T x6) {
        return reduce(Arrays.asList(x1, x2, x3, x4, x5, x6)).get();
    }
    
    @SuppressWarnings("unchecked")
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2, T x3, T x4, T x5, T x6, T... xs) {
        return reduce(concat(Arrays.asList(x1, x2, x3, x4, x5, x6), xs)).get();
    }
    
    public static final <T extends SemiGroup<T>> Option<T> reduce(T[] xs) {
        return reduce(Arrays.asList(xs));
    }
    
    public static final <T> T reduce(T[] xs, Monoid<T> m) {
        return reduce(Arrays.asList(xs), m);
    }
    
    public static final <T> T reduce(Monoid<T> m, T[] xs) {
        return reduce(Arrays.asList(xs), m);
    }
    
    
    
    public static final <T,Z> Z fold(Z zero, T[] xs, Apply<Tuple2<Z,T>, Z> f) {
        return fold(zero, Arrays.asList(xs), f);
    }
    
    public static final <T,Z> Z fold(Z zero, Apply<Tuple2<Z,T>, Z> f, T[] xs) {
        return fold(zero, Arrays.asList(xs), f);
    }
    
    /**
     * @return <i>None</i> if <i>elements</i> is empty
     */
    public static final <T> Option<T> fold(T[] xs, Apply<Tuple2<T,T>, T> f) {
        return fold(Arrays.asList(xs), f);
    }
    
    public static final <T> Option<T> fold(Apply<Tuple2<T,T>, T> f, T[] xs) {
        return fold(Arrays.asList(xs), f);
    }
    
    
    
    public static final <T extends Comparable<T>> T min(T x, T... xs) {
        return min(cons(x, xs)).get();
    }

    public static final <T extends Comparable<T>> Option<T> min(T[] xs) {
        return min(Arrays.asList(xs));
    }
    
    
    
    public static final <T extends Comparable<T>> T max(T x, T... xs) {
        return max(cons(x, xs)).get();
    }
    
    public static final <T extends Comparable<T>> Option<T> max(T[] xs) {
        return max(Arrays.asList(xs));
    }
    
    
    
    public static final <A,B> Iterable<Tuple2<A, B>> zip(A[] a, B[] b) {
        return zip(Arrays.asList(a), Arrays.asList(b));
    }

    public static final <A,B> Iterable<Tuple2<A, B>> zip(A[] a, Iterable<B> b) {
        return zip(Arrays.asList(a), b);
    }

    public static final <A,B> Iterable<Tuple2<A, B>> zip(Iterable<A> a, B[] b) {
        return zip(a, Arrays.asList(b));
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, B[] b, C[] c) {
        return zip(Arrays.asList(a), Arrays.asList(b), Arrays.asList(c));
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, Iterable<B> b, Iterable<C> c) {
        return zip(Arrays.asList(a), b, c);
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, B[] b, Iterable<C> c) {
        return zip(a, Arrays.asList(b), c);
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, Iterable<B> b, C[] c) {
        return zip(a, b, Arrays.asList(c));
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, B[] b, Iterable<C> c) {
        return zip(Arrays.asList(a), Arrays.asList(b), c);
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, B[] b, C[] c) {
        return zip(a, Arrays.asList(b), Arrays.asList(c));
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, Iterable<B> b, C[] c) {
        return zip(Arrays.asList(a), b, Arrays.asList(c));
    }
    
    
    
    public static final <A> Iterable<Tuple2<Integer, A>> zipWithIndex(A[] a) {
        return new ZippingIterable<Integer,A>(range(Enumerables.ints, 0), Arrays.asList(a));
    }
    
    
    
    public static final CharSequence mkString(Character[] xs) {
        return mkString(Arrays.asList(xs));
    }
    
    public static final CharSequence mkString(CharSequence delimiter, CharSequence[] xs) {
        return mkString(delimiter, Arrays.asList(xs));
    }
    
    
    
    public static final <T> Iterable<T> reverse(T[] xs) {
        return reverse(Arrays.asList(xs));
    }
    
    
    
    public static <T> Iterable<T> distinct(T[] xs) {
        return distinct(Arrays.asList(xs));
    }
    
    
    
    public static final <T,R> Iterable<R> sequence(Apply<? super T,? extends R>[] fs, T value) {
        return sequence(Arrays.asList(fs), value);
    }
    
    public static final <T,R> Iterable<R> sequence(T value, Apply<? super T,? extends R>[] fs) {
        return sequence(Arrays.asList(fs), value);
    }
    
    
    
    public static final <T> Iterable<Iterable<T>> transpose(Iterable<T>[] xs) {
        return transpose(Arrays.asList(xs));
    }
    
    public static final CharSequence unlines(CharSequence[] xs) {
        return unlines(Arrays.asList(xs));
    }
}

