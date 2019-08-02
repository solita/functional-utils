package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newList;
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
        return FunctionalImpl.subtract(newList(a), b);
    }
    
    public static final <T> Iterable<T> subtract(Iterable<T> a, T[] b) {
        return FunctionalImpl.subtract(a, newSet(b));
    }
    
    public static final <T> Iterable<T> subtract(T[] a, T[] b) {
        return FunctionalImpl.subtract(newList(a), newSet(b));
    }
    
    
    
    public static final <T> Iterable<T> remove(T toRemove, T[] xs) {
        return FunctionalImpl.remove(toRemove, newList(xs));
    }
    
    
    
    public static final <T> Option<T> find(Apply<? super T, Boolean> predicate, T[] xs) {
        return FunctionalImpl.find(predicate, newList(xs));
    }
    
    
    
    public static final <T> Iterable<T> filter(Apply<? super T, Boolean> predicate, T[] xs) {
        return FunctionalImpl.filter(predicate, newList(xs));
    }
    
    
    
    public static final <S, T> Iterable<T> map(Apply<? super S, ? extends T> f, S[] xs) {
        return FunctionalImpl.map(f, newList(xs));
    }
    
    public static final <S, T1, T2> Iterable<Pair<T1,T2>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, S[] elements) {
        return FunctionalImpl.map(f1, f2, newList(elements));
    }
    
    public static final <S, T1, T2, T3> Iterable<Tuple3<T1,T2,T3>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, Apply<? super S, T3> f3, S[] xs) {
        return FunctionalImpl.map(f1, f2, f3, newList(xs));
    }
    
    public static final <S, T1, T2, T3, T4> Iterable<Tuple4<T1,T2,T3,T4>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, Apply<? super S, T3> f3, Apply<? super S, T4> f4, S[] xs) {
        return FunctionalImpl.map(f1, f2, f3, f4, newList(xs));
    }

    
    
    public static final <S, T> Iterable<T> flatMap(Apply<? super S, ? extends Iterable<T>> f, S[] xs) {
        return FunctionalImpl.flatMap(f, newList(xs));
    }
    
    
    
    public static final <T> Iterable<T> flatten(T[][] xs) {
        return FunctionalImpl.flatten(map(new Transformer<T[], Iterable<T>>() {
            @Override
            public final Iterable<T> transform(T[] source) {
                return newList(source);
            }
        }, xs));
    }
    
    public static final <T> Iterable<T> flatten(T[] x, T[]... xs) {
        return FunctionalImpl.flatten(FunctionalImpl.map(new Transformer<T[], Iterable<T>>() {
            @Override
            public final Iterable<T> transform(T[] source) {
                return newList(source);
            }
        }, cons(x, xs)));
    }
    
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2) {
        return FunctionalImpl.flatten(newList(x1, x2));
    }
    
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3) {
        return FunctionalImpl.flatten(newList(x1, x2, x3));
    }
    
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4) {
        return FunctionalImpl.flatten(newList(x1, x2, x3, x4));
    }
    
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5) {
        return FunctionalImpl.flatten(newList(x1, x2, x3, x4, x5));
    }
    
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6) {
        return FunctionalImpl.flatten(newList(x1, x2, x3, x4, x5, x6));
    }
    
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6, Iterable<? extends T>... xs) {
        return FunctionalImpl.flatten(concat(newList(x1, x2, x3, x4, x5, x6), xs));
    }
    
    
    
    public static final <T> void foreach(Apply<? super T, Void> procedure, T[] xs) {
        FunctionalImpl.foreach(procedure, newList(xs));
    }
    
    public static final <T> void foreach(ApplyVoid<? super T> procedure, T[] xs) {
        FunctionalImpl.foreach(procedure, newList(xs));
    }
    
    
    
    public static final <T> Iterable<List<T>> grouped(long groupSize, T[] xs) {
        return FunctionalImpl.grouped(groupSize, Arrays.asList(xs));
    }
    
    
    
    public static final <T> Iterable<Iterable<T>> group(T[] xs) {
        return FunctionalImpl.group(newList(xs));
    }
    
    public static final <T> Iterable<Iterable<T>> group(Apply<Map.Entry<T,T>, Boolean> comparator, T[] xs) {
        return FunctionalImpl.group(comparator, newList(xs));
    }
    
    
    
    public static final <G, T> Map<G, List<T>> groupBy(Apply<? super T,G> f, T[] xs) {
        return FunctionalImpl.groupBy(f, newList(xs));
    }
    
    
    
    public static final <T> T head(T[] xs) {
        return xs == null ? null : xs[0];
    }
    
    
    
    public static final <T> Option<T> headOption(T[] xs) {
        return xs == null ? null : xs.length == 0 ? Option.<T>None() : Some(xs[0]);
    }
    
    
    
    public static final <T> Iterable<T> tail(T[] xs) {
        return FunctionalImpl.tail(newList(xs));
    }
    
    
    
    public static final <T> T last(T[] xs) {
        return xs == null ? null : xs[xs.length-1];
    }
    
    
    
    public static final <T> Option<T> lastOption(T[] xs) {
        return xs == null ? null : xs.length == 0 ? Option.<T>None() : Some(xs[xs.length-1]);
    }
    
    
    
    public static final <T> Iterable<T> init(T[] xs) {
        return FunctionalImpl.init(newList(xs));
    }
    
    
    
    public static final <T> Iterable<T> take(long amount, T[] xs) {
        return FunctionalImpl.take(amount, newList(xs));
    }
    
    
    
    public static final <T> Iterable<T> drop(long amount, T[] xs) {
        return FunctionalImpl.drop(amount, newList(xs));
    }
    
    
    
    public static final <T> Iterable<T> takeWhile(Apply<? super T, Boolean> predicate, T[] xs) {
        return FunctionalImpl.takeWhile(predicate, newList(xs));
    }
    
    
    
    public static final <T> Iterable<T> dropWhile(Apply<? super T, Boolean> predicate, T[] xs) {
        return FunctionalImpl.dropWhile(predicate, newList(xs));
    }
    
    
    
    public static final <T> Pair<Iterable<T>, Iterable<T>> span(Apply<? super T, Boolean> predicate, T[] xs) {
        return FunctionalImpl.span(predicate, newList(xs));
    }

    
    
    public static final <T> Pair<Iterable<T>, Iterable<T>> partition(Apply<? super T, Boolean> predicate, T[] xs) {
        return FunctionalImpl.partition(predicate, newList(xs));
    }
    
    
    
    public static final <T> Iterable<T> every(int nth, T[] xs) {
        return FunctionalImpl.every(nth, newList(xs));
    }
    
    
    
    public static final <T> boolean isEmpty(T[] xs) {
        return xs.length == 0;
    }
    
    
    
    public static final <T> int size(T[] xs) {
        return xs.length;
    }
    
    
    
    public static final <T> boolean contains(T candidate, T[] xs) {
        return FunctionalImpl.exists(Predicates.equalTo(candidate), newList(xs));
    }
    
    
    
    public static final <T> boolean exists(Apply<T, Boolean> predicate, T[] xs) {
        return FunctionalImpl.exists(predicate, newList(xs));
    }
    
    
    
    public static final <T> boolean forall(Apply<? super T, Boolean> predicate, T[] xs) {
        return FunctionalImpl.forall(predicate, newList(xs));
    }
    
    
    
    public static final <T> Iterable<T> cons(T x, T[] xs) {
        return FunctionalImpl.cons(x, newList(xs));
    }
    
    
    
    public static final <T> Iterable<T> concat(T[] a, Iterable<? extends T> b) {
        return FunctionalImpl.concat(newList(a), b);
    }

    public static final <T> Iterable<T> concat(Iterable<? extends T> a, T[] b) {
        return FunctionalImpl.concat(a, newList(b));
    }

    public static final <T> Iterable<T> concat(T[] a, T[] b) {
        return FunctionalImpl.concat(newList(a), newList(b));
    }

    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3) {
        return x1 == null && x2 == null && x3 == null ? null : new ConcatenatingIterable<T>(FunctionalImpl.filter(Predicates.not(Predicates.isNull()), newList(x1, x2, x3)));
    }
    
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4) {
        return x1 == null && x2 == null && x3 == null && x4 == null ? null : new ConcatenatingIterable<T>(FunctionalImpl.filter(Predicates.not(Predicates.isNull()), newList(x1, x2, x3, x4)));
    }
    
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5) {
        return x1 == null && x2 == null && x3 == null && x4 == null && x5 == null ? null : new ConcatenatingIterable<T>(FunctionalImpl.filter(Predicates.not(Predicates.isNull()), newList(x1, x2, x3, x4, x5)));
    }
    
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6) {
        return x1 == null && x2 == null && x3 == null && x4 == null && x5 == null && x6 == null ? null : new ConcatenatingIterable<T>(FunctionalImpl.filter(Predicates.not(Predicates.isNull()), newList(x1, x2, x3, x4, x5, x6)));
    }
    
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6, Iterable<? extends T>... xs) {
        return x1 == null && x2 == null && x3 == null && x4 == null && x5 == null && x6 == null && xs == null ? null : new ConcatenatingIterable<T>(concat(FunctionalImpl.filter(Predicates.not(Predicates.isNull()), newList(x1, x2, x3, x4, x5, x6)), xs));
    }
    
    
    
    public static final <T extends Comparable<T>> Iterable<T> sort(T[] xs) {
        return FunctionalImpl.sort(newList(xs));
    }
    
    public static final <T> Iterable<T> sort(Comparator<? super T> comparator, T[] xs) {
        return FunctionalImpl.sort(comparator, newList(xs));
    }
    
    
    
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2) {
        return FunctionalImpl.reduce(newList(x1, x2)).get();
    }
    
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2, T x3) {
        return FunctionalImpl.reduce(newList(x1, x2, x3)).get();
    }
    
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2, T x3, T x4) {
        return FunctionalImpl.reduce(newList(x1, x2, x3, x4)).get();
    }
    
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2, T x3, T x4, T x5) {
        return FunctionalImpl.reduce(newList(x1, x2, x3, x4, x5)).get();
    }
    
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2, T x3, T x4, T x5, T x6) {
        return FunctionalImpl.reduce(newList(x1, x2, x3, x4, x5, x6)).get();
    }
    
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2, T x3, T x4, T x5, T x6, T... xs) {
        return FunctionalImpl.reduce(concat(newList(x1, x2, x3, x4, x5, x6), xs)).get();
    }
    
    public static final <T extends SemiGroup<T>> Option<T> reduce(T[] xs) {
        return FunctionalImpl.reduce(newList(xs));
    }
    
    public static final <T> T reduce(Monoid<T> m, T[] xs) {
        return FunctionalImpl.reduce(m, newList(xs));
    }
    
    
    
    public static final <T,Z> Z fold(Z zero, Apply<Map.Entry<? extends Z,? extends T>, Z> f, T[] xs) {
        return FunctionalImpl.fold(zero, f, newList(xs));
    }
    
    /**
     * @return <i>None</i> if <i>elements</i> is empty
     */
    public static final <T> Option<T> fold(Apply<Map.Entry<? extends T,? extends T>, T> f, T[] xs) {
        return FunctionalImpl.fold(f, newList(xs));
    }
    
    
    
    public static final <T extends Comparable<T>> T min(T x, T y) {
        return x.compareTo(y) <= 0 ? x : y;
    }
    
    public static final <T extends Comparable<T>> T min(T x, T... xs) {
        return x == null || xs == null ? null : FunctionalImpl.min(cons(x, xs)).get();
    }

    public static final <T extends Comparable<T>> Option<T> min(T[] xs) {
        return FunctionalImpl.min(newList(xs));
    }
    
    
    
    public static final <T extends Comparable<T>> T max(T x, T y) {
        return x.compareTo(y) >= 0 ? x : y;
    }
    
    public static final <T extends Comparable<T>> T max(T x, T... xs) {
        return x == null || xs == null ? null : FunctionalImpl.max(cons(x, xs)).get();
    }
    
    public static final <T extends Comparable<T>> Option<T> max(T[] xs) {
        return FunctionalImpl.max(newList(xs));
    }
    
    
    
    public static final <A,B> Iterable<Pair<A, B>> zip(A[] a, B[] b) {
        return FunctionalImpl.zip(newList(a), newList(b));
    }

    public static final <A,B> Iterable<Pair<A, B>> zip(A[] a, Iterable<B> b) {
        return FunctionalImpl.zip(newList(a), b);
    }

    public static final <A,B> Iterable<Pair<A, B>> zip(Iterable<A> a, B[] b) {
        return FunctionalImpl.zip(a, newList(b));
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, B[] b, C[] c) {
        return FunctionalImpl.zip(newList(a), newList(b), newList(c));
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, Iterable<B> b, Iterable<C> c) {
        return FunctionalImpl.zip(newList(a), b, c);
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, B[] b, Iterable<C> c) {
        return FunctionalImpl.zip(a, newList(b), c);
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, Iterable<B> b, C[] c) {
        return FunctionalImpl.zip(a, b, newList(c));
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, B[] b, Iterable<C> c) {
        return FunctionalImpl.zip(newList(a), newList(b), c);
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, B[] b, C[] c) {
        return FunctionalImpl.zip(a, newList(b), newList(c));
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, Iterable<B> b, C[] c) {
        return FunctionalImpl.zip(newList(a), b, newList(c));
    }
    
    public static final <A,B,C,D> Iterable<Tuple4<A, B, C, D>> zip(A[] a, B[] b, C[] c, D[] d) {
        return FunctionalImpl.zip(newList(a), newList(b), newList(c), newList(d));
    }
    
    public static final <A,B,C,D> Iterable<Tuple4<A, B, C, D>> zip(A[] a, Iterable<B> b, Iterable<C> c, Iterable<D> d) {
        return FunctionalImpl.zip(newList(a), b, c, d);
    }
    
    public static final <A,B,C,D> Iterable<Tuple4<A, B, C, D>> zip(Iterable<A> a, B[] b, Iterable<C> c, Iterable<D> d) {
        return FunctionalImpl.zip(a, newList(b), c, d);
    }
    
    public static final <A,B,C,D> Iterable<Tuple4<A, B, C, D>> zip(Iterable<A> a, Iterable<B> b, C[] c, Iterable<D> d) {
        return FunctionalImpl.zip(a, b, newList(c), d);
    }
    
    public static final <A,B,C,D> Iterable<Tuple4<A, B, C, D>> zip(Iterable<A> a, Iterable<B> b, Iterable<C> c, D[] d) {
        return FunctionalImpl.zip(a, b, c, newList(d));
    }
    
    public static final <A,B,C,D> Iterable<Tuple4<A, B, C, D>> zip(A[] a, B[] b, Iterable<C> c, Iterable<D> d) {
        return FunctionalImpl.zip(newList(a), newList(b), c, d);
    }
    
    public static final <A,B,C,D> Iterable<Tuple4<A, B, C, D>> zip(Iterable<A> a, B[] b, C[] c, Iterable<D> d) {
        return FunctionalImpl.zip(a, newList(b), newList(c), d);
    }
    
    public static final <A,B,C,D> Iterable<Tuple4<A, B, C, D>> zip(Iterable<A> a, Iterable<B> b, C[] c, D[] d) {
        return FunctionalImpl.zip(a, b, newList(c), newList(d));
    }
    
    
    
    public static final <A> Iterable<Pair<Integer, A>> zipWithIndex(A[] a) {
        return a == null ? null : new ZippingIterable<Integer,A>(FunctionalImpl.range(Enumerables.ints, 0), newList(a));
    }
    
    public static final <T> Iterable<List<T>> rangify(Enumerable<T> enumeration, T[] xs) {
        return FunctionalImpl.rangify(enumeration, newList(xs));
    }
    
    
    
    public static final CharSequence mkString(Character[] xs) {
        return FunctionalImpl.mkString(newList(xs));
    }
    
    public static final CharSequence mkString(CharSequence delimiter, CharSequence[] xs) {
        return FunctionalImpl.mkString(delimiter, newList(xs));
    }
    
    
    
    public static final <T> Iterable<T> reverse(T[] xs) {
        return FunctionalImpl.reverse(newList(xs));
    }
    
    
    
    public static final <T> Iterable<T> distinct(T[] xs) {
        return FunctionalImpl.distinct(newList(xs));
    }
    
    
    
    public static final <T,R> Iterable<R> sequence(T value, Apply<? super T,? extends R>[] fs) {
        return FunctionalImpl.sequence(value, newList(fs));
    }
    
    
    
    public static final <T> Iterable<Iterable<T>> transpose(Iterable<T>[] xs) {
        return FunctionalImpl.transpose(newList(xs));
    }
    
    public static final CharSequence unlines(CharSequence[] xs) {
        return FunctionalImpl.unlines(newList(xs));
    }
}

