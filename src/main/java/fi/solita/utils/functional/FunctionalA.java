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

/**
 * Variants of {@link Functional} for Arrays.
 */
public abstract class FunctionalA extends FunctionalM {
    /**
     * @see Functional#subtract
     */
    public static final <T> Iterable<T> subtract(T[] xs, Collection<T> toSubtract) {
        return FunctionalImpl.subtract(newList(xs), toSubtract);
    }
    
    /**
     * @see Functional#subtract
     */
    public static final <T> Iterable<T> subtract(Iterable<T> xs, T[] toSubtract) {
        return FunctionalImpl.subtract(xs, newSet(toSubtract));
    }
    
    /**
     * @see Functional#subtract
     */
    public static final <T> Iterable<T> subtract(T[] xs, T[] toSubtract) {
        return FunctionalImpl.subtract(newList(xs), newSet(toSubtract));
    }
    
    
    
    /**
     * @see Functional#remove
     */
    public static final <T> Iterable<T> remove(T toRemove, T[] xs) {
        return FunctionalImpl.remove(toRemove, newList(xs));
    }
    
    
    
    /**
     * @see Functional#find
     */
    public static final <T> Option<T> find(Apply<? super T, Boolean> predicate, T[] xs) {
        return FunctionalImpl.find(predicate, newList(xs));
    }
    
    
    
    /**
     * @see Functional#filter
     */
    public static final <T> Iterable<T> filter(Apply<? super T, Boolean> predicate, T[] xs) {
        return FunctionalImpl.filter(predicate, newList(xs));
    }
    
    
    
    /**
     * @see Functional#map(Apply, Iterable)
     */
    public static final <S, T> Iterable<T> map(Apply<? super S, ? extends T> f, S[] xs) {
        return FunctionalImpl.map(f, newList(xs));
    }
    
    /**
     * @see Functional#map(ApplyBi, Iterable)
     */
    public static final <S1, S2, T> Iterable<T> map(ApplyBi<? super S1, ? super S2, ? extends T> f, Map.Entry<S1, S2>[] xs) {
        return FunctionalImpl.map(Function.of(f), newList(xs));
    }
    
    /**
     * @see Functional#map(Apply, Apply, Iterable)
     */
    public static final <S, T1, T2> Iterable<Pair<T1,T2>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, S[] elements) {
        return FunctionalImpl.map(f1, f2, newList(elements));
    }
    
    /**
     * @see Functional#map(Apply, Apply, Apply, Iterable)
     */
    public static final <S, T1, T2, T3> Iterable<Tuple3<T1,T2,T3>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, Apply<? super S, T3> f3, S[] xs) {
        return FunctionalImpl.map(f1, f2, f3, newList(xs));
    }
    
    /**
     * @see Functional#map(Apply, Apply, Apply, Apply, Iterable)
     */
    public static final <S, T1, T2, T3, T4> Iterable<Tuple4<T1,T2,T3,T4>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, Apply<? super S, T3> f3, Apply<? super S, T4> f4, S[] xs) {
        return FunctionalImpl.map(f1, f2, f3, f4, newList(xs));
    }

    
    
    /**
     * @see Functional#flatMap
     */
    public static final <S, T> Iterable<T> flatMap(Apply<? super S, ? extends Iterable<T>> f, S[] xs) {
        return FunctionalImpl.flatMap(f, newList(xs));
    }
    
    
    
    /**
     * @see Functional#flatten
     */
    public static final <T> Iterable<T> flatten(T[][] xs) {
        return FunctionalImpl.flatten(map(new Transformer<T[], Iterable<T>>() {
            @Override
            public final Iterable<T> transform(T[] source) {
                return newList(source);
            }
        }, xs));
    }
    
    /**
     * @see Functional#flatten
     */
    public static final <T> Iterable<T> flatten(T[] x, T[]... xs) {
        return FunctionalImpl.flatten(FunctionalImpl.map(new Transformer<T[], Iterable<T>>() {
            @Override
            public final Iterable<T> transform(T[] source) {
                return newList(source);
            }
        }, cons(x, xs)));
    }
    
    /**
     * @see Functional#flatten
     */
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2) {
        return FunctionalImpl.flatten(newList(x1, x2));
    }
    
    /**
     * @see Functional#flatten
     */
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3) {
        return FunctionalImpl.flatten(newList(x1, x2, x3));
    }
    
    /**
     * @see Functional#flatten
     */
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4) {
        return FunctionalImpl.flatten(newList(x1, x2, x3, x4));
    }
    
    /**
     * @see Functional#flatten
     */
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5) {
        return FunctionalImpl.flatten(newList(x1, x2, x3, x4, x5));
    }
    
    /**
     * @see Functional#flatten
     */
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6) {
        return FunctionalImpl.flatten(newList(x1, x2, x3, x4, x5, x6));
    }
    
    /**
     * @see Functional#flatten
     */
    public static final <T> Iterable<T> flatten(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6, Iterable<? extends T>... xs) {
        return FunctionalImpl.flatten(concat(newList(x1, x2, x3, x4, x5, x6), xs));
    }
    
    
    
    /**
     * @see Functional#foreach
     */
    public static final <T> void foreach(Apply<? super T, Void> procedure, T[] xs) {
        FunctionalImpl.foreach(procedure, newList(xs));
    }
    
    /**
     * @see Functional#foreach
     */
    public static final <T> void foreach(ApplyVoid<? super T> procedure, T[] xs) {
        FunctionalImpl.foreach(procedure, newList(xs));
    }
    
    
    
    /**
     * @see Functional#grouped
     */
    public static final <T> Iterable<List<T>> grouped(long groupSize, T[] xs) {
        return FunctionalImpl.grouped(groupSize, Arrays.asList(xs));
    }
    
    
    
    /**
     * @see Functional#group(Iterable)
     */
    public static final <T> Iterable<Iterable<T>> group(T[] xs) {
        return FunctionalImpl.group(newList(xs));
    }
    
    /**
     * @see Functional#group(Apply, Iterable)
     */
    public static final <T> Iterable<Iterable<T>> group(ApplyBi<T,T,Boolean> comparator, T[] xs) {
        return FunctionalImpl.group(comparator, newList(xs));
    }
    
    
    
    /**
     * @see Functional#groupBy
     */
    public static final <G, T> Map<G, List<T>> groupBy(Apply<? super T,G> f, T[] xs) {
        return FunctionalImpl.groupBy(f, newList(xs));
    }
    
    
    
    /**
     * @see Functional#head
     */
    public static final <T> T head(T[] xs) {
        return xs == null ? null : xs[0];
    }
    
    
    
    /**
     * @see Functional#headOption
     */
    public static final <T> Option<T> headOption(T[] xs) {
        return xs == null ? null : xs.length == 0 ? Option.<T>None() : Some(xs[0]);
    }
    
    
    
    /**
     * @see Functional#tail
     */
    public static final <T> Iterable<T> tail(T[] xs) {
        return FunctionalImpl.tail(newList(xs));
    }
    
    
    
    /**
     * @see Functional#last
     */
    public static final <T> T last(T[] xs) {
        return xs == null ? null : xs[xs.length-1];
    }
    
    
    
    /**
     * @see Functional#lastOption
     */
    public static final <T> Option<T> lastOption(T[] xs) {
        return xs == null ? null : xs.length == 0 ? Option.<T>None() : Some(xs[xs.length-1]);
    }
    
    
    
    /**
     * @see Functional#init
     */
    public static final <T> Iterable<T> init(T[] xs) {
        return FunctionalImpl.init(newList(xs));
    }
    
    
    
    /**
     * @see Functional#take
     */
    public static final <T> Iterable<T> take(long amount, T[] xs) {
        return FunctionalImpl.take(amount, newList(xs));
    }
    
    
    
    /**
     * @see Functional#drop
     */
    public static final <T> Iterable<T> drop(long amount, T[] xs) {
        return FunctionalImpl.drop(amount, newList(xs));
    }
    
    
    
    /**
     * @see Functional#takeWhile
     */
    public static final <T> Iterable<T> takeWhile(Apply<? super T, Boolean> predicate, T[] xs) {
        return FunctionalImpl.takeWhile(predicate, newList(xs));
    }
    
    
    
    /**
     * @see Functional#dropWhile
     */
    public static final <T> Iterable<T> dropWhile(Apply<? super T, Boolean> predicate, T[] xs) {
        return FunctionalImpl.dropWhile(predicate, newList(xs));
    }
    
    
    
    /**
     * @see Functional#span
     */
    public static final <T> Pair<Iterable<T>, Iterable<T>> span(Apply<? super T, Boolean> predicate, T[] xs) {
        return FunctionalImpl.span(predicate, newList(xs));
    }

    
    
    /**
     * @see Functional#partition
     */
    public static final <T,L,R> Pair<Iterable<L>, Iterable<R>> partition(Apply<? super T, Either<L,R>> f, T[] xs) {
        return FunctionalImpl.partition(f, newList(xs));
    }
    
    
    
    /**
     * @see Functional#split(int, Iterable)
     */
    public static final <T> Pair<Iterable<T>,Iterable<T>> split(int i, T[] xs) {
        return FunctionalImpl.split(i, newList(xs));
    }
    
    /**
     * @see Functional#split(Iterable)
     */
    public static <T> Pair<T, Iterable<T>> split(T[] xs) {
        return xs == null ? null : Pair.of(xs[0], tail(xs));
    }
    
    
    
    /**
     * @see Functional#every
     */
    public static final <T> Iterable<T> every(int nth, T[] xs) {
        return FunctionalImpl.every(nth, newList(xs));
    }
    
    
    
    /**
     * @see Functional#isEmpty
     */
    public static final <T> boolean isEmpty(T[] xs) {
        return xs.length == 0;
    }
    
    
    
    /**
     * @see Functional#size
     */
    public static final <T> int size(T[] xs) {
        return xs.length;
    }
    
    
    
    /**
     * @see Functional#contains
     */
    public static final <T> boolean contains(T candidate, T[] xs) {
        return FunctionalImpl.exists(Predicates.equalTo(candidate), newList(xs));
    }
    
    
    
    /**
     * @see Functional#exists
     */
    public static final <T> boolean exists(Apply<T, Boolean> predicate, T[] xs) {
        return FunctionalImpl.exists(predicate, newList(xs));
    }
    
    
    
    /**
     * @see Functional#forall
     */
    public static final <T> boolean forall(Apply<? super T, Boolean> predicate, T[] xs) {
        return FunctionalImpl.forall(predicate, newList(xs));
    }
    
    
    
    /**
     * @see Functional#cons
     */
    public static final <T> Iterable<T> cons(T x, T[] xs) {
        return FunctionalImpl.cons(x, newList(xs));
    }
    
    
    
    /**
     * @see Functional#append
     */
    public static final <T> Iterable<T> append(T x, T[] xs) {
        return FunctionalImpl.append(x, newList(xs));
    }
    
    
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(T[] a, Iterable<? extends T> b) {
        return FunctionalImpl.concat(newList(a), b);
    }

    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(Iterable<? extends T> a, T[] b) {
        return FunctionalImpl.concat(a, newList(b));
    }

    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(T[] a, T[] b) {
        return FunctionalImpl.concat(newList(a), newList(b));
    }
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(T[] a, T[] b, T[] c) {
        return concat(newList(a), newList(b), newList(c));
    }
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(T[] a, T[] b, T[] c, T[] d) {
        return concat(newList(a), newList(b), newList(c), newList(d));
    }
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(T[] a, T[] b, T[] c, T[] d, T[] e) {
        return concat(newList(a), newList(b), newList(c), newList(d), newList(e));
    }
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(T[] a, T[] b, T[] c, T[] d, T[] e, T[] f) {
        return concat(newList(a), newList(b), newList(c), newList(d), newList(e), newList(f));
    }
    
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(T[] a, T[] b, T[] c, T[] d, T[] e, T[] f, T[] g) {
        return concat(newList(a), newList(b), newList(c), newList(d), newList(e), newList(f), newList(g));
    }
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(T[] a, T[] b, T[] c, T[] d, T[] e, T[] f, T[] g, T[] h) {
        return concat(newList(a), newList(b), newList(c), newList(d), newList(e), newList(f), newList(g), newList(h));
    }
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(T[] a, T[] b, T[] c, T[] d, T[] e, T[] f, T[] g, T[] h, T[] i) {
        return concat(newList(a), newList(b), newList(c), newList(d), newList(e), newList(f), newList(g), newList(h), newList(i));
    }
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(T[] a, T[] b, T[] c, T[] d, T[] e, T[] f, T[] g, T[] h, T[] i, T[] j) {
        return concat(newList(a), newList(b), newList(c), newList(d), newList(e), newList(f), newList(g), newList(h), newList(i), newList(j));
    }
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(T[] a, T[] b, T[] c, T[] d, T[] e, T[] f, T[] g, T[] h, T[] i, T[] j, T[] k) {
        return concat(newList(a), newList(b), newList(c), newList(d), newList(e), newList(f), newList(g), newList(h), newList(i), newList(j), newList(k));
    }

    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3) {
        return x1 == null && x2 == null && x3 == null ? null : new ConcatenatingIterable<T>(FunctionalImpl.filter(Predicates.not(Predicates.isNull()), newList(x1, x2, x3)));
    }
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4) {
        return x1 == null && x2 == null && x3 == null && x4 == null ? null : new ConcatenatingIterable<T>(FunctionalImpl.filter(Predicates.not(Predicates.isNull()), newList(x1, x2, x3, x4)));
    }
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5) {
        return x1 == null && x2 == null && x3 == null && x4 == null && x5 == null ? null : new ConcatenatingIterable<T>(FunctionalImpl.filter(Predicates.not(Predicates.isNull()), newList(x1, x2, x3, x4, x5)));
    }
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6) {
        return x1 == null && x2 == null && x3 == null && x4 == null && x5 == null && x6 == null ? null : new ConcatenatingIterable<T>(FunctionalImpl.filter(Predicates.not(Predicates.isNull()), newList(x1, x2, x3, x4, x5, x6)));
    }
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6, Iterable<? extends T> x7) {
        return x1 == null && x2 == null && x3 == null && x4 == null && x5 == null && x6 == null && x7 == null ? null : new ConcatenatingIterable<T>(FunctionalImpl.filter(Predicates.not(Predicates.isNull()), newList(x1, x2, x3, x4, x5, x6, x7)));
    }
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6, Iterable<? extends T> x7, Iterable<? extends T> x8) {
        return x1 == null && x2 == null && x3 == null && x4 == null && x5 == null && x6 == null && x7 == null && x8 == null ? null : new ConcatenatingIterable<T>(FunctionalImpl.filter(Predicates.not(Predicates.isNull()), newList(x1, x2, x3, x4, x5, x6, x7, x8)));
    }
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6, Iterable<? extends T> x7, Iterable<? extends T> x8, Iterable<? extends T> x9) {
        return x1 == null && x2 == null && x3 == null && x4 == null && x5 == null && x6 == null && x7 == null && x8 == null && x9 == null ? null : new ConcatenatingIterable<T>(FunctionalImpl.filter(Predicates.not(Predicates.isNull()), newList(x1, x2, x3, x4, x5, x6, x7, x8, x9)));
    }
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6, Iterable<? extends T> x7, Iterable<? extends T> x8, Iterable<? extends T> x9, Iterable<? extends T> x10) {
        return x1 == null && x2 == null && x3 == null && x4 == null && x5 == null && x6 == null && x7 == null && x8 == null && x9 == null && x10 == null ? null : new ConcatenatingIterable<T>(FunctionalImpl.filter(Predicates.not(Predicates.isNull()), newList(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10)));
    }
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6, Iterable<? extends T> x7, Iterable<? extends T> x8, Iterable<? extends T> x9, Iterable<? extends T> x10, Iterable<? extends T> x11) {
        return x1 == null && x2 == null && x3 == null && x4 == null && x5 == null && x6 == null && x7 == null && x8 == null && x9 == null && x10 == null && x11 == null ? null : new ConcatenatingIterable<T>(FunctionalImpl.filter(Predicates.not(Predicates.isNull()), newList(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11)));
    }
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6, Iterable<? extends T> x7, Iterable<? extends T> x8, Iterable<? extends T> x9, Iterable<? extends T> x10, Iterable<? extends T> x11, Iterable<? extends T> x12) {
        return x1 == null && x2 == null && x3 == null && x4 == null && x5 == null && x6 == null && x7 == null && x8 == null && x9 == null && x10 == null && x11 == null && x12 == null ? null : new ConcatenatingIterable<T>(FunctionalImpl.filter(Predicates.not(Predicates.isNull()), newList(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12)));
    }
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6, Iterable<? extends T> x7, Iterable<? extends T> x8, Iterable<? extends T> x9, Iterable<? extends T> x10, Iterable<? extends T> x11, Iterable<? extends T> x12, Iterable<? extends T> x13) {
        return x1 == null && x2 == null && x3 == null && x4 == null && x5 == null && x6 == null && x7 == null && x8 == null && x9 == null && x10 == null && x11 == null && x12 == null && x13 == null ? null : new ConcatenatingIterable<T>(FunctionalImpl.filter(Predicates.not(Predicates.isNull()), newList(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13)));
    }
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6, Iterable<? extends T> x7, Iterable<? extends T> x8, Iterable<? extends T> x9, Iterable<? extends T> x10, Iterable<? extends T> x11, Iterable<? extends T> x12, Iterable<? extends T> x13, Iterable<? extends T> x14) {
        return x1 == null && x2 == null && x3 == null && x4 == null && x5 == null && x6 == null && x7 == null && x8 == null && x9 == null && x10 == null && x11 == null && x12 == null && x13 == null && x14 == null ? null : new ConcatenatingIterable<T>(FunctionalImpl.filter(Predicates.not(Predicates.isNull()), newList(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14)));
    }
    
    /**
     * @see Functional#concat
     */
    public static final <T> Iterable<T> concat(Iterable<? extends T> x1, Iterable<? extends T> x2, Iterable<? extends T> x3, Iterable<? extends T> x4, Iterable<? extends T> x5, Iterable<? extends T> x6, Iterable<? extends T> x7, Iterable<? extends T> x8, Iterable<? extends T> x9, Iterable<? extends T> x10, Iterable<? extends T> x11, Iterable<? extends T> x12, Iterable<? extends T> x13, Iterable<? extends T> x14, Iterable<? extends T>... xs) {
        Iterable<Iterable<? extends T>> a = FunctionalImpl.filter(Predicates.not(Predicates.isNull()), newList(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11, x12, x13, x14));
        Iterable<Iterable<? extends T>> b = filter(Predicates.not(Predicates.isNull()), xs);
        return x1 == null && x2 == null && x3 == null && x4 == null && x5 == null && x6 == null && x7 == null && x8 == null && x9 == null && x10 == null && x11 == null && x12 == null && x13 == null && x14 == null && xs == null ? null : new ConcatenatingIterable<T>(FunctionalImpl.concat(a, b));
    }
    
    
    
    /**
     * @see Functional#sort(Iterable)
     */
    public static final <T extends Comparable<T>> Iterable<T> sort(T[] xs) {
        return FunctionalImpl.sort(newList(xs));
    }
    
    /**
     * @see Functional#sort(Comparator, Iterable)
     */
    public static final <T> Iterable<T> sort(Comparator<? super T> comparator, T[] xs) {
        return FunctionalImpl.sort(comparator, newList(xs));
    }
    
    
    
    /**
     * @see Functional#reduce(Iterable)
     */
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2) {
        return FunctionalImpl.reduce(newList(x1, x2)).get();
    }
    
    /**
     * @see Functional#reduce(Iterable)
     */
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2, T x3) {
        return FunctionalImpl.reduce(newList(x1, x2, x3)).get();
    }
    
    /**
     * @see Functional#reduce(Iterable)
     */
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2, T x3, T x4) {
        return FunctionalImpl.reduce(newList(x1, x2, x3, x4)).get();
    }
    
    /**
     * @see Functional#reduce(Iterable)
     */
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2, T x3, T x4, T x5) {
        return FunctionalImpl.reduce(newList(x1, x2, x3, x4, x5)).get();
    }
    
    /**
     * @see Functional#reduce(Iterable)
     */
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2, T x3, T x4, T x5, T x6) {
        return FunctionalImpl.reduce(newList(x1, x2, x3, x4, x5, x6)).get();
    }
    
    /**
     * @see Functional#reduce(Iterable)
     */
    public static final <T extends SemiGroup<T>> T reduce(T x1, T x2, T x3, T x4, T x5, T x6, T... xs) {
        return FunctionalImpl.reduce(concat(newList(x1, x2, x3, x4, x5, x6), xs)).get();
    }
    
    /**
     * @see Functional#reduce(Iterable)
     */
    public static final <T extends SemiGroup<T>> Option<T> reduce(T[] xs) {
        return FunctionalImpl.reduce(newList(xs));
    }
    
    /**
     * @see Functional#reduce(Monoid, Iterable)
     */
    public static final <T> T reduce(Monoid<T> m, T[] xs) {
        return FunctionalImpl.reduce(m, newList(xs));
    }
    
    
    
    /**
     * @see Functional#fold(Object, Apply, Iterable)
     */
    public static final <T,Z> Z fold(Z zero, Apply<Map.Entry<? extends Z,? extends T>, Z> f, T[] xs) {
        return FunctionalImpl.fold(zero, f, newList(xs));
    }
    
    /**
     * @see Functional#fold(Apply, Iterable)
     */
    public static final <T> Option<T> fold(Apply<Map.Entry<? extends T,? extends T>, T> f, T[] xs) {
        return FunctionalImpl.fold(f, newList(xs));
    }
    
    
    
    /**
     * @see Functional#min
     */
    public static final <T extends Comparable<T>> Option<T> min(T[] xs) {
        return FunctionalImpl.min(newList(xs));
    }
    
    /**
     * @see Functional#min
     */
    public static final <T extends Comparable<T>> T min(T x, T... xs) {
        return x == null || xs == null ? null : FunctionalImpl.min(cons(x, xs)).get();
    }
    
    /**
     * @see Functional#minBy
     */
    public static final <T> Option<T> minBy(Comparator<? super T> comparator, T[] xs) {
        return FunctionalImpl.minBy(comparator, newList(xs));
    }
    
    /**
     * @see Functional#minBy
     */
    public static final <T> T minBy(Comparator<? super T> comparator, T x, T... xs) {
        return x == null || xs == null ? null : FunctionalImpl.minBy(comparator, cons(x, xs)).get();
    }
    
    
    
    /**
     * @see Functional#max
     */
    public static final <T extends Comparable<T>> Option<T> max(T[] xs) {
        return FunctionalImpl.max(newList(xs));
    }
    
    /**
     * @see Functional#max
     */
    public static final <T extends Comparable<T>> T max(T x, T... xs) {
        return x == null || xs == null ? null : FunctionalImpl.max(cons(x, xs)).get();
    }
    
    /**
     * @see Functional#maxBy
     */
    public static final <T> Option<T> maxBy(Comparator<? super T> comparator, T[] xs) {
        return FunctionalImpl.maxBy(comparator, newList(xs));
    }
    
    /**
     * @see Functional#maxBy
     */
    public static final <T> T maxBy(Comparator<? super T> comparator, T x, T... xs) {
        return x == null || xs == null ? null : FunctionalImpl.maxBy(comparator, cons(x, xs)).get();
    }
    
    
    
    /**
     * @see Functional#ordered
     */
    public static final <T> boolean ordered(Comparator<? super T> comparator, T x, T... xs) {
        return FunctionalImpl.ordered(comparator, cons(x, xs));
    }
    
    /**
     * @see Functional#ordered
     */
    public static final <T extends Comparable<T>> boolean ordered(T x, T... xs) {
        return FunctionalImpl.ordered(Compare.byNatural(), cons(x, xs));
    }
    
    
    
    /**
     * @see Functional#zip(Iterable, Iterable)
     */
    public static final <A,B> Iterable<Pair<A, B>> zip(A[] a, B[] b) {
        return FunctionalImpl.zip(newList(a), newList(b));
    }

    /**
     * @see Functional#zip(Iterable, Iterable)
     */
    public static final <A,B> Iterable<Pair<A, B>> zip(A[] a, Iterable<B> b) {
        return FunctionalImpl.zip(newList(a), b);
    }

    /**
     * @see Functional#zip(Iterable, Iterable)
     */
    public static final <A,B> Iterable<Pair<A, B>> zip(Iterable<A> a, B[] b) {
        return FunctionalImpl.zip(a, newList(b));
    }
    
    /**
     * @see Functional#zip(Iterable, Iterable, Iterable)
     */
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, B[] b, C[] c) {
        return FunctionalImpl.zip(newList(a), newList(b), newList(c));
    }
    
    /**
     * @see Functional#zip(Iterable, Iterable, Iterable)
     */
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, Iterable<B> b, Iterable<C> c) {
        return FunctionalImpl.zip(newList(a), b, c);
    }
    
    /**
     * @see Functional#zip(Iterable, Iterable, Iterable)
     */
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, B[] b, Iterable<C> c) {
        return FunctionalImpl.zip(a, newList(b), c);
    }
    
    /**
     * @see Functional#zip(Iterable, Iterable, Iterable)
     */
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, Iterable<B> b, C[] c) {
        return FunctionalImpl.zip(a, b, newList(c));
    }
    
    /**
     * @see Functional#zip(Iterable, Iterable, Iterable)
     */
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, B[] b, Iterable<C> c) {
        return FunctionalImpl.zip(newList(a), newList(b), c);
    }
    
    /**
     * @see Functional#zip(Iterable, Iterable, Iterable)
     */
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, B[] b, C[] c) {
        return FunctionalImpl.zip(a, newList(b), newList(c));
    }
    
    /**
     * @see Functional#zip(Iterable, Iterable, Iterable)
     */
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(A[] a, Iterable<B> b, C[] c) {
        return FunctionalImpl.zip(newList(a), b, newList(c));
    }
    
    /**
     * @see Functional#zip(Iterable, Iterable, Iterable, Iterable)
     */
    public static final <A,B,C,D> Iterable<Tuple4<A, B, C, D>> zip(A[] a, B[] b, C[] c, D[] d) {
        return FunctionalImpl.zip(newList(a), newList(b), newList(c), newList(d));
    }
    
    /**
     * @see Functional#zip(Iterable, Iterable, Iterable, Iterable)
     */
    public static final <A,B,C,D> Iterable<Tuple4<A, B, C, D>> zip(A[] a, Iterable<B> b, Iterable<C> c, Iterable<D> d) {
        return FunctionalImpl.zip(newList(a), b, c, d);
    }
    
    /**
     * @see Functional#zip(Iterable, Iterable, Iterable, Iterable)
     */
    public static final <A,B,C,D> Iterable<Tuple4<A, B, C, D>> zip(Iterable<A> a, B[] b, Iterable<C> c, Iterable<D> d) {
        return FunctionalImpl.zip(a, newList(b), c, d);
    }
    
    /**
     * @see Functional#zip(Iterable, Iterable, Iterable, Iterable)
     */
    public static final <A,B,C,D> Iterable<Tuple4<A, B, C, D>> zip(Iterable<A> a, Iterable<B> b, C[] c, Iterable<D> d) {
        return FunctionalImpl.zip(a, b, newList(c), d);
    }
    
    /**
     * @see Functional#zip(Iterable, Iterable, Iterable, Iterable)
     */
    public static final <A,B,C,D> Iterable<Tuple4<A, B, C, D>> zip(Iterable<A> a, Iterable<B> b, Iterable<C> c, D[] d) {
        return FunctionalImpl.zip(a, b, c, newList(d));
    }
    
    /**
     * @see Functional#zip(Iterable, Iterable, Iterable, Iterable)
     */
    public static final <A,B,C,D> Iterable<Tuple4<A, B, C, D>> zip(A[] a, B[] b, Iterable<C> c, Iterable<D> d) {
        return FunctionalImpl.zip(newList(a), newList(b), c, d);
    }
    
    /**
     * @see Functional#zip(Iterable, Iterable, Iterable, Iterable)
     */
    public static final <A,B,C,D> Iterable<Tuple4<A, B, C, D>> zip(Iterable<A> a, B[] b, C[] c, Iterable<D> d) {
        return FunctionalImpl.zip(a, newList(b), newList(c), d);
    }
    
    /**
     * @see Functional#zip(Iterable, Iterable, Iterable, Iterable)
     */
    public static final <A,B,C,D> Iterable<Tuple4<A, B, C, D>> zip(Iterable<A> a, Iterable<B> b, C[] c, D[] d) {
        return FunctionalImpl.zip(a, b, newList(c), newList(d));
    }
    
    
    
    /**
     * @see Functional#zipWithIndex
     */
    public static final <A> Iterable<Pair<Integer, A>> zipWithIndex(A[] xs) {
        return Functional.zipWithIndex(newList(xs));
    }
    
    /**
     * @see Functional#unzip
     */
    public static final <A, B> Pair<Iterable<A>, Iterable<B>> unzip(Pair<A,B>[] xs) {
        return FunctionalImpl.unzip(newList(xs));
    }
    
    /**
     * @see Functional#unzip3
     */
    public static final <A, B, C> Tuple3<Iterable<A>, Iterable<B>, Iterable<C>> unzip3(Tuple3<A,B,C>[] xs) {
        return FunctionalImpl.unzip3(newList(xs));
    }
    
    
    
    /**
     * @see Functional#rangify
     */
    public static final <T> Iterable<List<T>> rangify(Enumerable<T> enumeration, T[] xs) {
        return FunctionalImpl.rangify(enumeration, newList(xs));
    }
    
    
    
    /**
     * @see Functional#mkString(Iterable)
     */
    public static final CharSequence mkString(Character[] xs) {
        return FunctionalImpl.mkString(newList(xs));
    }
    
    /**
     * @see Functional#mkString(CharSequence, Iterable)
     */
    public static final CharSequence mkString(CharSequence delimiter, CharSequence[] xs) {
        return FunctionalImpl.mkString(delimiter, newList(xs));
    }
    
    
    
    /**
     * @see Functional#reverse
     */
    public static final <T> Iterable<T> reverse(T[] xs) {
        return FunctionalImpl.reverse(newList(xs));
    }
    
    
    
    /**
     * @see Functional#distinct
     */
    public static final <T> Iterable<T> distinct(T[] xs) {
        return FunctionalImpl.distinct(newList(xs));
    }
    
    
    
    /**
     * @see Functional#sequence
     */
    public static final <T,R> Iterable<R> sequence(T value, Apply<? super T,? extends R>[] fs) {
        return FunctionalImpl.sequence(value, newList(fs));
    }
    
    
    
    /**
     * @see Functional#combineEndo
     */
    public static final <P,T,R> ApplyBi<P,T,T> combineEndo(final Apply<P,? extends Apply<T,T>> f1) {
        return FunctionalImpl.combineEndo(newList(f1));
    }
    
    /**
     * @see Functional#combineEndo
     */
    public static final <P,T,R> ApplyBi<P,T,T> combineEndo(final Apply<P,? extends Apply<T,T>> f1, final Apply<P,? extends Apply<T,T>> f2) {
        return FunctionalImpl.combineEndo(newList(f1, f2));
    }
    
    /**
     * @see Functional#combineEndo
     */
    public static final <P,T,R> ApplyBi<P,T,T> combineEndo(final Apply<P,? extends Apply<T,T>> f1, final Apply<P,? extends Apply<T,T>> f2, final Apply<P,? extends Apply<T,T>> f3) {
        return FunctionalImpl.combineEndo(newList(f1, f2, f3));
    }
    
    /**
     * @see Functional#combineEndo
     */
    public static final <P,T,R> ApplyBi<P,T,T> combineEndo(final Apply<P,? extends Apply<T,T>> f1, final Apply<P,? extends Apply<T,T>> f2, final Apply<P,? extends Apply<T,T>> f3, final Apply<P,? extends Apply<T,T>> f4) {
        return FunctionalImpl.combineEndo(newList(f1, f2, f3, f4));
    }
    
    /**
     * @see Functional#combineEndo
     */
    public static final <P,T,R> ApplyBi<P,T,T> combineEndo(final Apply<P,? extends Apply<T,T>> f1, final Apply<P,? extends Apply<T,T>> f2, final Apply<P,? extends Apply<T,T>> f3, final Apply<P,? extends Apply<T,T>> f4, final Apply<P,? extends Apply<T,T>> f5) {
        return FunctionalImpl.combineEndo(newList(f1, f2, f3, f4, f5));
    }
    
    /**
     * @see Functional#combineEndo
     */
    public static final <P,T,R> ApplyBi<P,T,T> combineEndo(final Apply<P,? extends Apply<T,T>> f1, final Apply<P,? extends Apply<T,T>> f2, final Apply<P,? extends Apply<T,T>> f3, final Apply<P,? extends Apply<T,T>> f4, final Apply<P,? extends Apply<T,T>> f5, final Apply<P,? extends Apply<T,T>> f6) {
        return FunctionalImpl.combineEndo(newList(f1, f2, f3, f4, f5, f6));
    }
    
    /**
     * @see Functional#combineEndo
     */
    public static final <P,T,R> ApplyBi<P,T,T> combineEndo(final Apply<P,? extends Apply<T,T>> f1, final Apply<P,? extends Apply<T,T>> f2, final Apply<P,? extends Apply<T,T>> f3, final Apply<P,? extends Apply<T,T>> f4, final Apply<P,? extends Apply<T,T>> f5, final Apply<P,? extends Apply<T,T>> f6, final Apply<P,? extends Apply<T,T>> f7) {
        return FunctionalImpl.combineEndo(newList(f1, f2, f3, f4, f5, f6, f7));
    }
    
    /**
     * @see Functional#combineEndo
     */
    public static final <P,T,R> ApplyBi<P,T,T> combineEndo(final Apply<P,? extends Apply<T,T>> f1, final Apply<P,? extends Apply<T,T>> f2, final Apply<P,? extends Apply<T,T>> f3, final Apply<P,? extends Apply<T,T>> f4, final Apply<P,? extends Apply<T,T>> f5, final Apply<P,? extends Apply<T,T>> f6, final Apply<P,? extends Apply<T,T>> f7, final Apply<P,? extends Apply<T,T>>... fs) {
        return FunctionalImpl.combineEndo(concat(newList(f1, f2, f3, f4, f5, f6, f7), fs));
    }
    
    
    
    /**
     * @see Functional#transpose
     */
    public static final <T> Iterable<Iterable<T>> transpose(Iterable<T>[] xs) {
        return FunctionalImpl.transpose(newList(xs));
    }
    
    
    
    /**
     * @see Functional#unlines
     */
    public static final CharSequence unlines(CharSequence[] xs) {
        return FunctionalImpl.unlines(newList(xs));
    }
}

