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
        return FunctionalImpl.subtract(Arrays.asList(a), b);
    }
    
    public static final <T> Iterable<T> subtract(Iterable<T> a, T[] b) {
        return FunctionalImpl.subtract(a, newSet(b));
    }
    
    public static final <T> Iterable<T> subtract(T[] a, T[] b) {
        return FunctionalImpl.subtract(Arrays.asList(a), newSet(b));
    }
    
    
    
    public static final <T> Option<T> find(Apply<? super T, Boolean> predicate, T[] xs) {
        return FunctionalImpl.find(predicate, Arrays.asList(xs));
    }
    
    
    
    public static final <T> Iterable<T> filter(Apply<? super T, Boolean> predicate, T[] xs) {
        return FunctionalImpl.filter(predicate, Arrays.asList(xs));
    }
    
    
    
    public static final <S, T> Iterable<T> map(Apply<? super S, ? extends T> f, S[] xs) {
        return FunctionalImpl.map(f, Arrays.asList(xs));
    }
    
    public static final <S, T1, T2> Iterable<Pair<T1,T2>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, S[] elements) {
        return FunctionalImpl.map(f1, f2, Arrays.asList(elements));
    }
    
    public static final <S, T1, T2, T3> Iterable<Tuple3<T1,T2,T3>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, Apply<? super S, T3> f3, S[] xs) {
        return FunctionalImpl.map(f1, f2, f3, Arrays.asList(xs));
    }
    
    public static final <S, T1, T2, T3, T4> Iterable<Tuple4<T1,T2,T3,T4>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, Apply<? super S, T3> f3, Apply<? super S, T4> f4, S[] xs) {
        return FunctionalImpl.map(f1, f2, f3, f4, Arrays.asList(xs));
    }

    
    
    public static final <S, T> Iterable<T> flatMap(Apply<? super S, ? extends Iterable<T>> f, S[] xs) {
        return FunctionalImpl.flatMap(f, Arrays.asList(xs));
    }
    
    
    
    public static final <T> Iterable<T> flatten(T[][] xs) {
        return FunctionalImpl.flatten(map(new Transformer<T[], Iterable<T>>() {
            @Override
            public Iterable<T> transform(T[] source) {
                return Arrays.asList(source);
            }
        }, xs));
    }
    
    public static final <T> Iterable<T> flatten(T[] x, T[]... xs) {
        return FunctionalImpl.flatten(FunctionalImpl.map(new Transformer<T[], Iterable<T>>() {
            @Override
            public Iterable<T> transform(T[] source) {
                return Arrays.asList(source);
            }
        }, cons(x, xs)));
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2) {
        return FunctionalImpl.flatten(Arrays.asList(x1, x2));
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3) {
        return FunctionalImpl.flatten(Arrays.asList(x1, x2, x3));
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4) {
        return FunctionalImpl.flatten(Arrays.asList(x1, x2, x3, x4));
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5) {
        return FunctionalImpl.flatten(Arrays.asList(x1, x2, x3, x4, x5));
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6) {
        return FunctionalImpl.flatten(Arrays.asList(x1, x2, x3, x4, x5, x6));
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6, Iterable<? extends T>... xs) {
        return FunctionalImpl.flatten(concat(Arrays.asList(x1, x2, x3, x4, x5, x6), xs));
    }
    
    
    
    public static final <T> void foreach(Apply<? super T, Void> procedure, T[] xs) {
        FunctionalImpl.foreach(procedure, Arrays.asList(xs));
    }
    
    public static final <T> void foreach(ApplyVoid<? super T> procedure, T[] xs) {
        FunctionalImpl.foreach(procedure, Arrays.asList(xs));
    }
    
    
    
    public static final <T> Iterable<List<T>> grouped(long groupSize, T[] xs) {
        return FunctionalImpl.grouped(groupSize, Arrays.asList(xs));
    }
    
    
    
    public static final <T> Iterable<Iterable<T>> group(T[] xs) {
        return FunctionalImpl.group(Arrays.asList(xs));
    }
    
    public static final <T> Iterable<Iterable<T>> group(Apply<Tuple2<T,T>, Boolean> comparator, T[] xs) {
        return FunctionalImpl.group(comparator, Arrays.asList(xs));
    }
    
    
    
    public static final <G, T> Map<G, List<T>> groupBy(Apply<? super T,G> f, T[] xs) {
        return FunctionalImpl.groupBy(f, Arrays.asList(xs));
    }
    
    
    
    public static final <T> T head(T[] xs) {
        return xs[0];
    }
    
    
    
    public static final <T> Option<T> headOption(T[] xs) {
        return xs.length == 0 ? Option.<T>None() : Some(xs[0]);
    }
    
    
    
    public static final <T> Iterable<T> tail(T[] xs) {
        return FunctionalImpl.tail(Arrays.asList(xs));
    }
    
    
    
    public static final <T> T last(T[] xs) {
        return xs[xs.length-1];
    }
    
    
    
    public static final <T> Option<T> lastOption(T[] xs) {
        return xs.length == 0 ? Option.<T>None() : Some(xs[xs.length-1]);
    }
    
    
    
    public static final <T> Iterable<T> init(T[] xs) {
        return FunctionalImpl.init(Arrays.asList(xs));
    }
    
    
    
    public static final <T> Iterable<T> take(long amount, T[] xs) {
        return new Iterables.TakingIterable<T>(Arrays.asList(xs), amount);
    }
    
    
    
    public static final <T> Iterable<T> drop(long amount, T[] xs) {
        return new Iterables.DroppingIterable<T>(Arrays.asList(xs), amount);
    }
    
    
    
    public static final <T> Iterable<T> takeWhile(Apply<? super T, Boolean> predicate, T[] xs) {
        return FunctionalImpl.takeWhile(predicate, Arrays.asList(xs));
    }
    
    
    
    public static final <T> Iterable<T> dropWhile(Apply<? super T, Boolean> predicate, T[] xs) {
        return FunctionalImpl.dropWhile(predicate, Arrays.asList(xs));
    }
    
    
    
    public static final <T> boolean isEmpty(T[] xs) {
        return xs.length == 0;
    }
    
    
    
    public static final <T> int size(T[] xs) {
        return xs.length;
    }
    
    
    
    public static final <T> boolean contains(T candidate, T[] xs) {
        return FunctionalImpl.exists(Predicates.equalTo(candidate), Arrays.asList(xs));
    }
    
    
    
    public static final <T> boolean exists(Apply<T, Boolean> predicate, T[] xs) {
        return FunctionalImpl.exists(predicate, Arrays.asList(xs));
    }
    
    
    
    public static final <T> boolean forall(Apply<? super T, Boolean> predicate, T[] xs) {
        return FunctionalImpl.forall(predicate, Arrays.asList(xs));
    }
    
    
    
    @SuppressWarnings("unchecked")
    public static final <T> Iterable<T> cons(T x, T[] xs) {
        return concat(Arrays.asList(x), xs);
    }
    
    
    
    public static final <T> Iterable<T> concat(T[] a, Iterable<? extends T> b) {
        return FunctionalImpl.concat(Arrays.asList(a), b);
    }

    public static final <T> Iterable<T> concat(Iterable<? extends T> a, T[] b) {
        return FunctionalImpl.concat(a, Arrays.asList(b));
    }

    public static final <T> Iterable<T> concat(T[] a, T[] b) {
        return FunctionalImpl.concat(Arrays.asList(a), Arrays.asList(b));
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
        return FunctionalImpl.sort(Arrays.asList(xs));
    }
    
    public static final <T> Iterable<T> sort(Comparator<? super T> comparator, T[] xs) {
        return FunctionalImpl.sort(comparator, Arrays.asList(xs));
    }
    
    
    
    @SuppressWarnings("unchecked")
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2) {
        return FunctionalImpl.reduce(Arrays.asList(x1, x2)).get();
    }
    
    @SuppressWarnings("unchecked")
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2, T x3) {
        return FunctionalImpl.reduce(Arrays.asList(x1, x2, x3)).get();
    }
    
    @SuppressWarnings("unchecked")
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2, T x3, T x4) {
        return FunctionalImpl.reduce(Arrays.asList(x1, x2, x3, x4)).get();
    }
    
    @SuppressWarnings("unchecked")
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2, T x3, T x4, T x5) {
        return FunctionalImpl.reduce(Arrays.asList(x1, x2, x3, x4, x5)).get();
    }
    
    @SuppressWarnings("unchecked")
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2, T x3, T x4, T x5, T x6) {
        return FunctionalImpl.reduce(Arrays.asList(x1, x2, x3, x4, x5, x6)).get();
    }
    
    @SuppressWarnings("unchecked")
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2, T x3, T x4, T x5, T x6, T... xs) {
        return FunctionalImpl.reduce(concat(Arrays.asList(x1, x2, x3, x4, x5, x6), xs)).get();
    }
    
    public static final <T extends SemiGroup<T>> Option<T> reduce(T[] xs) {
        return FunctionalImpl.reduce(Arrays.asList(xs));
    }
    
    public static final <T> T reduce(Monoid<T> m, T[] xs) {
        return FunctionalImpl.reduce(m, Arrays.asList(xs));
    }
    
    
    
    public static final <T,Z> Z fold(Z zero, Apply<Map.Entry<? extends Z,? extends T>, Z> f, T[] xs) {
        return FunctionalImpl.fold(zero, f, Arrays.asList(xs));
    }
    
    /**
     * @return <i>None</i> if <i>elements</i> is empty
     */
    public static final <T> Option<T> fold(Apply<Map.Entry<? extends T,? extends T>, T> f, T[] xs) {
        return FunctionalImpl.fold(f, Arrays.asList(xs));
    }
    
    
    
    public static final <T extends Comparable<T>> T min(T x, T... xs) {
        return FunctionalImpl.min(cons(x, xs)).get();
    }

    public static final <T extends Comparable<T>> Option<T> min(T[] xs) {
        return FunctionalImpl.min(Arrays.asList(xs));
    }
    
    
    
    public static final <T extends Comparable<T>> T max(T x, T... xs) {
        return FunctionalImpl.max(cons(x, xs)).get();
    }
    
    public static final <T extends Comparable<T>> Option<T> max(T[] xs) {
        return FunctionalImpl.max(Arrays.asList(xs));
    }
    
    
    
    public static final <A,B> Iterable<Tuple2<A, B>> zip(A[] a, B[] b) {
        return FunctionalImpl.zip(Arrays.asList(a), Arrays.asList(b));
    }

    public static final <A,B> Iterable<Tuple2<A, B>> zip(A[] a, Iterable<B> b) {
        return FunctionalImpl.zip(Arrays.asList(a), b);
    }

    public static final <A,B> Iterable<Tuple2<A, B>> zip(Iterable<A> a, B[] b) {
        return FunctionalImpl.zip(a, Arrays.asList(b));
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, B[] b, C[] c) {
        return FunctionalImpl.zip(Arrays.asList(a), Arrays.asList(b), Arrays.asList(c));
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, Iterable<B> b, Iterable<C> c) {
        return FunctionalImpl.zip(Arrays.asList(a), b, c);
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, B[] b, Iterable<C> c) {
        return FunctionalImpl.zip(a, Arrays.asList(b), c);
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, Iterable<B> b, C[] c) {
        return FunctionalImpl.zip(a, b, Arrays.asList(c));
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, B[] b, Iterable<C> c) {
        return FunctionalImpl.zip(Arrays.asList(a), Arrays.asList(b), c);
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, B[] b, C[] c) {
        return FunctionalImpl.zip(a, Arrays.asList(b), Arrays.asList(c));
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, Iterable<B> b, C[] c) {
        return FunctionalImpl.zip(Arrays.asList(a), b, Arrays.asList(c));
    }
    
    
    
    public static final <A> Iterable<Tuple2<Integer, A>> zipWithIndex(A[] a) {
        return new ZippingIterable<Integer,A>(FunctionalImpl.range(Enumerables.ints, 0), Arrays.asList(a));
    }
    
    
    
    public static final CharSequence mkString(Character[] xs) {
        return FunctionalImpl.mkString(Arrays.asList(xs));
    }
    
    public static final CharSequence mkString(CharSequence delimiter, CharSequence[] xs) {
        return FunctionalImpl.mkString(delimiter, Arrays.asList(xs));
    }
    
    
    
    public static final <T> Iterable<T> reverse(T[] xs) {
        return FunctionalImpl.reverse(Arrays.asList(xs));
    }
    
    
    
    public static <T> Iterable<T> distinct(T[] xs) {
        return FunctionalImpl.distinct(Arrays.asList(xs));
    }
    
    
    
    public static final <T,R> Iterable<R> sequence(T value, Apply<? super T,? extends R>[] fs) {
        return FunctionalImpl.sequence(value, Arrays.asList(fs));
    }
    
    
    
    public static final <T> Iterable<Iterable<T>> transpose(Iterable<T>[] xs) {
        return FunctionalImpl.transpose(Arrays.asList(xs));
    }
    
    public static final CharSequence unlines(CharSequence[] xs) {
        return FunctionalImpl.unlines(Arrays.asList(xs));
    }
}

