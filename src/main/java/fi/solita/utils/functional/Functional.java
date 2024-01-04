package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Option.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import fi.solita.utils.functional.Iterables.RepeatingIterable;

/**
 * @see FunctionalC
 * @see FunctionalS
 * @see FunctionalA
 * @see FunctionalM
 */
public abstract class Functional extends FunctionalC {

    /**
     * @return a new iterable {@code xs} - {@code toSubtract}, i.e. one that contains all elements of {@code xs} that
     * don't exist in {@code toSubtract} by object equality.
     */
    public static final <T> Iterable<T> subtract(Iterable<? extends T> xs, final Collection<? extends T> toSubtract) {
        return FunctionalImpl.subtract(xs, toSubtract);
    }

    /**
     * @return {@code xs} without elements equal to {@code toRemove}.
     */
    public static final <T> Iterable<T> remove(T toRemove, Iterable<T> xs) {
        return FunctionalImpl.remove(toRemove, xs);
    }
    
    /**
     * @return first element in {@code xs} satisfying {@code predicate}, if any.
     */
    public static final <T> Option<T> find(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return FunctionalImpl.find(predicate, xs);
    }

    /**
     * @return all elements in {@code xs} satisfying {@code predicate}.
     */
    public static final <T> Iterable<T> filter(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return FunctionalImpl.filter(predicate, xs);
    }

    /**
     * @return all elements in {@code xs} transformed with {@code f}.
     */
    public static final <S, T> Iterable<T> map(Apply<? super S, ? extends T> f, Iterable<S> xs) {
        return FunctionalImpl.map(f, xs);
    }
    
    /**
     * @return all elements in {@code xs} transformed with {@code f}.
     */
    public static final <S1, S2, T> Iterable<T> map(ApplyBi<? super S1, ? super S2, ? extends T> f, Iterable<? extends Map.Entry<S1, S2>> xs) {
        return FunctionalImpl.map(Function.of(f), xs);
    }
    
    /**
     * @return all elements in {@code xs} transformed with {@code f}.
     */
    public static final <S1, S2, S3, T> Iterable<T> map(Apply3<? super S1, ? super S2, ? super S3, ? extends T> f, Iterable<Tuple3<S1, S2, S3>> xs) {
        return FunctionalImpl.map(Function.of(f), xs);
    }

    /**
     * @return all elements in {@code xs} transformed with {@code f1} and {@code f2} respectively.
     */
    public static final <S, T1, T2> Iterable<Pair<T1, T2>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, Iterable<S> xs) {
        return FunctionalImpl.map(f1, f2, xs);
    }

    /**
     * @return all elements in {@code xs} transformed with {@code f1}, {@code f2} and {@code f3} respectively.
     */
    public static final <S, T1, T2, T3> Iterable<Tuple3<T1, T2, T3>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, Apply<? super S, T3> f3, Iterable<S> xs) {
        return FunctionalImpl.map(f1, f2, f3, xs);
    }

    /**
     * @return all elements in {@code xs} transformed with {@code f1}, {@code f2}, {@code f3} and {@code f4} respectively.
     */
    public static final <S, T1, T2, T3, T4> Iterable<Tuple4<T1, T2, T3, T4>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, Apply<? super S, T3> f3, Apply<? super S, T4> f4, Iterable<S> xs) {
        return FunctionalImpl.map(f1, f2, f3, f4, xs);
    }

    /**
     * @param functor Functor definition corresponding to {@code xs}.
     * @return functor {@code xs} transformed with {@code f}.
     */
    public static final <F, A, B, FA extends F, FB extends F> FB fmap(Functor<F, A, B, FA, FB> functor, Apply<A, B> f, FA xs) {
        return functor.fmap(f, xs);
    }

    /**
     * Maps both values of a bifunctor.
     * 
     * @param bifunctor Bifunctor definition corresponding to {@code xs}.
     * @return bifuntor {@code xs} transformed with {@code f1} and {@code f2} respectively.
     */
    public static final <F, A, B, C, D, FAC extends F, FBD extends F> FBD bimap(BiFunctor<F, A, B, C, D, FAC, FBD, ?, ?> bifunctor, Apply<A, B> f, Apply<C, D> f2, FAC xs) {
        return bifunctor.bimap(f, f2, xs);
    }

    /**
     * Maps the first value of a bifunctor.
     * 
     * @param bifunctor Bifunctor definition corresponding to {@code xs}.
     * @return bifunctor {@code xs} transformed with {@code f} over its first value.
     */
    public static final <F, A, B, FAC extends F, FBC extends F> FBC first(BiFunctor<F, A, B, ?, ?, FAC, ?, FBC, ?> bifunctor, Apply<A, B> f, FAC xs) {
        return bifunctor.first(f, xs);
    }

    /**
     * Maps the second value of a bifunctor.
     * 
     * @param bifunctor Bifunctor definition corresponding to {@code xs}.
     * @return bifunctor {@code xs} transformed with {@code f} over its second value.
     */
    public static final <F, C, D, FAC extends F, FAD extends F> FAD second(BiFunctor<F, ?, ?, C, D, FAC, ?, ?, FAD> bifunctor, Apply<C, D> f, FAC xs) {
        return bifunctor.second(f, xs);
    }

    /**
     * @return elements of {@code xs} transformed with {@code f} flattening the resulting iterable. 
     */
    public static final <S, T> Iterable<T> flatMap(Apply<? super S, ? extends Iterable<? extends T>> f, Iterable<S> xs) {
        return FunctionalImpl.flatMap(f, xs);
    }

    /**
     * @return elements of {@code xs} with one level of iteration removed.
     */
    public static final <T> Iterable<T> flatten(Iterable<? extends Iterable<? extends T>> xs) {
        return FunctionalImpl.flatten(xs);
    }

    /**
     * Applies side-effectful {@code procedure} to each element in {@code xs}.
     */
    public static final <T> void foreach(Apply<? super T, Void> procedure, Iterable<T> xs) {
        FunctionalImpl.foreach(procedure, xs);
    }

    /**
     * Applies side-effectful {@code procedure} to each element in {@code xs}.
     */
    public static final <T> void foreach(ApplyVoid<? super T> procedure, Iterable<T> xs) {
        FunctionalImpl.foreach(procedure, xs);
    }

    /**
     * @return elements in {@code xs} grouped to lists of size {@code groupSize}. The last list may be shorter.
     */
    public static final <T> Iterable<List<T>> grouped(long groupSize, Iterable<T> xs) {
        return FunctionalImpl.grouped(groupSize, xs);
    }

    /**
     * For example: <code>group([1,2,2,3,2]) == [[1],[2,2],[3],[2]]</code>.
     * 
     * @return equal (by object equality) consecutive elements in {@code xs} grouped together.
     */
    public static final <T> Iterable<Iterable<T>> group(Iterable<T> xs) {
        return FunctionalImpl.group(xs);
    }

    /**
     * For example: <code>group(Object::equals, [1,2,2,3,2]) == [[1],[2,2],[3],[2]]</code>.
     * 
     * @return equal (by {@code comparator}) consecutive elements in {@code xs} grouped together.
     */
    public static final <T> Iterable<Iterable<T>> group(Apply<Map.Entry<T, T>, Boolean> comparator, Iterable<T> xs) {
        return FunctionalImpl.group(comparator, xs);
    }

    /**
     * <i>Unsafe!</i>
     * 
     * @return first element in {@code xs}. Throws an exception if {@code xs} is empty.
     */
    public static final <T> T head(Iterable<T> xs) {
        return FunctionalImpl.head(xs);
    }

    /**
     * @return first element in {@code xs}, if any.
     */
    public static final <T> Option<T> headOption(Iterable<T> xs) {
        return FunctionalImpl.headOption(xs);
    }

    /**
     * For example: <code>tail([1,2,3]) == [2,3]</code>.
     * 
     * <p><i>Unsafe!</i>
     * 
     * @return all but first element in {@code xs}. Throws an exception if {@code xs} is empty.
     */
    public static final <T> Iterable<T> tail(Iterable<T> xs) {
        return FunctionalImpl.tail(xs);
    }
    
    /**
     * <i>Unsafe!</i>
     * 
     * @return last element in {@code xs}. Throws an exception if {@code xs} is empty.
     */
    public static final <T> T last(Iterable<T> xs) {
        return FunctionalImpl.last(xs);
    }

    /**
     * @return last element in {@code xs}, if any.
     */
    public static final <T> Option<T> lastOption(Iterable<T> xs) {
        return FunctionalImpl.lastOption(xs);
    }

    /**
     * For example: <code>init([1,2,3]) == [1,2]</code>.
     * 
     * <p><i>Unsafe!</i>
     * 
     * @return all but last element in {@code xs}. Throws an exception if {@code xs} is empty.
     */
    public static final <T> Iterable<T> init(Iterable<T> xs) {
        return FunctionalImpl.init(xs);
    }

    /**
     * For example:
     * <br><code>take(2, [1,2,3]) == [1,2]</code>.
     * <br><code>take(2, [1]) == [1]</code>.
     * 
     * @return at most {@code amount} first elements in {@code xs}.
     */
    public static final <T> Iterable<T> take(long amount, Iterable<T> xs) {
        return FunctionalImpl.take(amount, xs);
    }

    /**
     * For example:
     * <br><code>drop(2, [1,2,3]) == [3]</code>.
     * <br><code>drop(2, [1]) == []</code>.
     * 
     * @return elements in {@code xs} except the first {@code amount}.
     */
    public static final <T> Iterable<T> drop(long amount, Iterable<T> xs) {
        return FunctionalImpl.drop(amount, xs);
    }
    
    /**
     * @return at most {@code amount} last elements in {@code xs}.
     */
    public static final <T> Iterable<T> takeLast(long amount, Iterable<T> xs) {
        return reverse(take(4, reverse(xs)));
    }
    
    /**
     * @return elements in {@code xs} except the last {@code amount}.
     */
    public static final <T> Iterable<T> dropLast(long amount, Iterable<T> xs) {
        return reverse(drop(4, reverse(xs)));
    }

    /**
     * For example: <code>takeWhile(even, [2,4,6,7,8]) == [2,4,6]</code>.
     * 
     * @return longest prefix of {@code xs} where all elements satisfy {@code predicate}.
     */
    public static final <T> Iterable<T> takeWhile(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return FunctionalImpl.takeWhile(predicate, xs);
    }

    /**
     * For example: <code>dropWhile(even, [2,4,6,7,8]) == [7,8]</code>.
     * 
     * @return elements in {@code xs} since the first one not satisfying {@code predicate}.
     */
    public static final <T> Iterable<T> dropWhile(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return FunctionalImpl.dropWhile(predicate, xs);
    }

    /**
     * {@link #takeWhile} and {@link #dropWhile} combined.
     * 
     * @return two iterables where the first is the longest prefix of {@code xs} where all elements satisfy {@code predicate}, and the second is the rest of the elements in {@code xs}.
     */
    public static final <T> Pair<Iterable<T>, Iterable<T>> span(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return FunctionalImpl.span(predicate, xs);
    }
    
    /**
     * @return elements in {@code xs} divided into two groups by {@code f}.
     */
    public static final <T,L,R> Pair<Iterable<L>, Iterable<R>> partition(Apply<? super T, Either<L,R>> f, Iterable<T> xs) {
        return FunctionalImpl.partition(f, xs);
    }
    
    /**
     * {@link #take} and {@link #drop} combined.
     * 
     * @return two iterables where the first one is at most {@code amount} first elements in {@code xs}, and the second is the rest of the elements in {@code xs}.
     */
    public static final <T> Pair<Iterable<T>,Iterable<T>> split(int amount, Iterable<T> xs) {
        return FunctionalImpl.split(amount, xs);
    }
    
    /**
     * <i>Unsafe!</i>
     * 
     * @return first element in {@code xs}, and the rest.
     */
    public static <T> Pair<T, Iterable<T>> split(Iterable<T> xs) {
        return FunctionalImpl.split(xs);
    }

    /**
     * @return every {@code nth} element in {@code xs}.
     */
    public static final <T> Iterable<T> every(int nth, Iterable<T> xs) {
        return FunctionalImpl.every(nth, xs);
    }

    /**
     * @return whether {@code xs} is empty or not.
     */
    public static final boolean isEmpty(Iterable<?> xs) {
        return FunctionalImpl.isEmpty(xs);
    }

    /**
     * @return length of {@code xs}.
     */
    public static final long size(Iterable<?> xs) {
        return FunctionalImpl.size(xs);
    }

    /**
     * @return whether {@code xs} contains {@code candidate}, by object equality.
     */
    public static final <T> boolean contains(T candidate, Iterable<T> xs) {
        return FunctionalImpl.exists(Predicates.equalTo(candidate), xs);
    }

    /**
     * @return whether there exists an element in {@code xs} satisfying {@code predicate}.
     */
    public static final <T> boolean exists(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return FunctionalImpl.exists(predicate, xs);
    }

    /**
     * @return whether all elements in {@code xs} satisfy {@code predicate}.
     */
    public static final <T> boolean forall(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return FunctionalImpl.forall(predicate, xs);
    }

    /**
     * @return {@code x} prepended in front of {@code xs}.
     */
    public static final <T> Iterable<T> cons(T x, Iterable<? extends T> xs) {
        return FunctionalImpl.cons(x, xs);
    }

    /**
     * @return elements in {@code a} and then elements in {@code b}.
     */
    public static final <T> Iterable<T> concat(Iterable<? extends T> a, Iterable<? extends T> b) {
        return FunctionalImpl.concat(a, b);
    }

    /**
     * @return elements in {@code xs} sorted by natural ordering.
     */
    public static final <T extends Comparable<? super T>> Iterable<T> sort(Iterable<T> xs) {
        return FunctionalImpl.sort(xs);
    }

    /**
     * @return elements in {@code xs} sorted by {@code comparator}.
     */
    public static final <T> Iterable<T> sort(Comparator<? super T> comparator, Iterable<T> xs) {
        return FunctionalImpl.sort(comparator, xs);
    }

    /**
     * @return elements in {@code xs}, if any, folded together by natural semigrouping.
     */
    public static final <T extends SemiGroup<T>> Option<T> reduce(Iterable<? extends T> xs) {
        return FunctionalImpl.reduce(xs);
    }

    /**
     * @return elements in {@code xs} folded together by {@code monoid}. {@link Monoid#zero} for an empty {@code xs}.
     */
    public static final <T> T reduce(Monoid<T> monoid, Iterable<? extends T> xs) {
        return FunctionalImpl.reduce(monoid, xs);
    }
    
    /**
     * @return elements in {@code xs}, if any, folded together by bifunction {@code f}.
     */
    public static final <T> Option<T> fold(Apply<Map.Entry<? extends T, ? extends T>, T> f, Iterable<? extends T> xs) {
        return FunctionalImpl.fold(f, xs);
    }

    /**
     * @return elements in {@code xs} folded together by bifunction {@code f}. {@code zero} for an empty {@code xs}.
     */
    public static final <T, Z> Z fold(Z zero, Apply<Map.Entry<? extends Z, ? extends T>, Z> f, Iterable<? extends T> xs) {
        return FunctionalImpl.fold(zero, f, xs);
    }

    /**
     * @return smallest value in {@code xs}, if any, determined by natural ordering.
     */
    public static <T extends Comparable<? super T>> Option<T> min(Iterable<T> xs) {
        return FunctionalImpl.min(xs);
    }
    
    /**
     * @return smaller of {@code x} and {@code y} by natural ordering.
     */
    public static final <T extends Comparable<T>> T min(T x, T y) {
        return x.compareTo(y) <= 0 ? x : y;
    }
    
    /**
     * @return smaller of {@code x} and {@code y} by {@code comparator}.
     */
    public static final <T> T minBy(Comparator<? super T> comparator, T x, T y) {
        return comparator.compare(x, y) <= 0 ? x : y;
    }
    
    /**
     * @return smallest of {@code xs} by {@code comparator}.
     */
    public static final <T> Option<T> minBy(Comparator<? super T> comparator, Iterable<T> xs) {
        return FunctionalImpl.minBy(comparator, xs);
    }

    /**
     * @return largest value in {@code xs}, if any, determined by natural ordering.
     */
    public static final <T extends Comparable<? super T>> Option<T> max(Iterable<T> xs) {
        return FunctionalImpl.max(xs);
    }
    
    /**
     * @return larger of {@code x} and {@code y}.
     */
    public static final <T extends Comparable<T>> T max(T x, T y) {
        return x.compareTo(y) >= 0 ? x : y;
    }
    
    /**
     * @return larger of {@code x} and {@code y} by {@code comparator}.
     */
    public static final <T> T maxBy(Comparator<? super T> comparator, T x, T y) {
        return comparator.compare(x, y) >= 0 ? x : y;
    }
    
    /**
     * @return largest of {@code xs} by {@code comparator}.
     */
    public static final <T> Option<T> maxBy(Comparator<? super T> comparator, Iterable<T> xs) {
        return FunctionalImpl.maxBy(comparator, xs);
    }
    
    
    /**
     * @return {@code value} restricted between inclusive of {@code minBound} and {@code maxBound}.
     */
    public static final <T extends Comparable<T>> T clamp(T minBound, T maxBound, T value) {
        return min(maxBound, max(value, minBound));
    }

    /**
     * Forgets remaining elements in longer list, if the inputs are of different size, that is:<br>
     * <code>length(zip(a,b)) == min(length(a), length(b))</code>.
     * 
     * <p><i>Unsafe!</i>
     * 
     * @return elements in {@code a} zipped pairwise together with elements in {@code b}.
     */
    public static final <A, B> Iterable<Pair<A, B>> zip(Iterable<A> a, Iterable<B> b) {
        return FunctionalImpl.zip(a, b);
    }

    /**
     * Forgets remaining elements in longer lists, if the inputs are of different size, that is:<br>
     * <code>length(zip(a,b,c)) == min(length(a), length(b), length(c))</code>.
     * 
     * <p><i>Unsafe!</i>
     * 
     * @return elements in {@code a}, {@code b} and {@code c} zipped together.
     */
    public static final <A, B, C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, Iterable<B> b, Iterable<C> c) {
        return FunctionalImpl.zip(a, b, c);
    }
    
    /**
     * Forgets remaining elements in longer lists, if the inputs are of different size, that is:<br>
     * <code>length(zip(a,b,c,d)) == min(length(a), length(b), length(c), length(d))</code>.
     * 
     * <p><i>Unsafe!</i>
     * 
     * @return elements in {@code a}, {@code b}, {@code c} and {@code d} zipped together.
     */
    public static final <A, B, C, D> Iterable<Tuple4<A, B, C, D>> zip(Iterable<A> a, Iterable<B> b, Iterable<C> c, Iterable<D> d) {
        return FunctionalImpl.zip(a, b, c, d);
    }

    /**
     * @return elements in {@code xs} together with their index, starting from 0.
     */
    public static final <A> Iterable<Pair<Integer, A>> zipWithIndex(Iterable<A> xs) {
        return FunctionalImpl.zipWithIndex(xs);
    }
    
    public static <A,B> Iterable<Pair<A,B>> zipTo(Iterable<A> as, B b) {
        return zip(as, repeat(b));
    }
    
    public static <A,B,C> Iterable<Tuple3<A,B,C>> zipToPair(Iterable<? extends Map.Entry<A,B>> as, C c) {
        return map(Transformers.<A,B,C>appendPair(c), as);
    }
    
    
    public static final <A, B> Pair<Iterable<A>, Iterable<B>> unzip(Iterable<Pair<A,B>> xs) {
        return FunctionalImpl.unzip(xs);
    }
    
    public static final <A, B, C> Tuple3<Iterable<A>, Iterable<B>, Iterable<C>> unzip3(Iterable<Tuple3<A,B,C>> xs) {
        return FunctionalImpl.unzip3(xs);
    }

    /**
     * @return all elements in bounded {@code enumeration}.
     */
    public static final <T, S extends Enumerable<T> & Bounded<T>> Iterable<T> range(S enumeration) {
        return enumeration == null ? null : range(enumeration, enumeration.minBound());
    }

    /**
     * @return elements in {@code enumeration} starting from {@code fromInclusive}.
     */
    public static final <T> Iterable<T> range(Enumerable<T> enumeration, T fromInclusive) {
        return FunctionalImpl.range(enumeration, fromInclusive);
    }

    /**
     * @return elements in {@code enumeration} starting from {@code fromInclusive} up to and including {@code toInclusive}.
     */
    public static final <T> Iterable<T> range(Enumerable<T> enumeration, T fromInclusive, T toInclusive) {
        return FunctionalImpl.range(enumeration, fromInclusive, toInclusive);
    }

    /**
     * For example: <code>rangify(ints, [1,2,3,5,7,8]) == [[1,3],[5],[7,8]]</code>.
     * 
     * @return elements in {@code xs} grouped in consecutive ranges determined by {@code enumeration}.
     */
    public static final <T> Iterable<List<T>> rangify(Enumerable<T> enumeration, Iterable<T> xs) {
        return FunctionalImpl.rangify(enumeration, xs);
    }

    /**
     * @return infinite sequence of {@code value}.
     */
    public static final <T> Iterable<T> repeat(T value) {
        return value == null ? null : new RepeatingIterable<T>(value);
    }

    /**
     * @return {@code value} repeated {@code amount} times.
     */
    public static final <T> Iterable<T> repeat(T value, long amount) {
        if (amount <= 0) {
            return emptyList();
        }
        return value == null ? null : new RepeatingIterable<T>(value, amount);
    }
    
    /**
     * @return {@code xs} padded from left with {@code value} up to length {@toLength}
     */
    public static final <T> Iterable<T> padLeft(int toLength, T value, Iterable<T> xs) {
        return xs == null ? null : concat(repeat(value, toLength-size(xs)), xs);
    }
    
    /**
     * @return {@code xs} padded from right with {@code value} up to length {@toLength}
     */
    public static final <T> Iterable<T> padRight(int toLength, T value, Iterable<T> xs) {
        return xs == null ? null : concat(xs, repeat(value, toLength-size(xs)));
    }

    /**
     * @return characters in {@code xs} concatenated together.
     */
    public static final String mkString(Iterable<Character> xs) {
        return FunctionalImpl.mkString(xs);
    }

    /**
     * @return elements in {@code xs} concatenated together separated by {@code delimiter}.
     */
    public static final String mkString(CharSequence delimiter, Iterable<? extends CharSequence> xs) {
        return FunctionalImpl.mkString(delimiter, xs);
    }

    /**
     * @return elements in {@code xs} in reversed order.
     */
    public static final <T> Iterable<T> reverse(Iterable<T> xs) {
        return FunctionalImpl.reverse(xs);
    }

    /**
     * @return elements in {@code xs} in the same order but all duplicates removed.
     */
    public static final <T> Iterable<T> distinct(Iterable<T> xs) {
        return FunctionalImpl.distinct(xs);
    }

    /**
     * @return results of applying {@code value} to each function in {@code fs}.
     */
    public static final <T, R> Iterable<R> sequence(T value, Iterable<? extends Apply<? super T, ? extends R>> fs) {
        return FunctionalImpl.sequence(value, fs);
    }
    
    /**
     * @return propagate an argument to all given endofunctions and combining them
     */
    public static final <P,T,R> ApplyBi<P,T,T> combineEndo(final Iterable<? extends Apply<P,? extends Apply<T,T>>> fs) {
        return FunctionalImpl.combineEndo(fs);
    }

    /**
     * Matrix transposition.
     * 
     * Forgets remaining elements in longer list, if the inputs are of different size, that is:<br>
     * <code>transpose([1,2],[3]) == [[1],[3]]</code>.
     * 
     * <p><i>Unsafe!</i>
     * 
     * @return matrix {@code xs} transposed.
     */
    public static final <T> Iterable<Iterable<T>> transpose(Iterable<? extends Iterable<T>> xs) {
        return FunctionalImpl.transpose(xs);
    }
    
    
    /**
     * Applicative for optionals.
     * 
     * @return {@code Some(f(a,b))} if {@code a} and {@code b} are defined. {@code None()} otherwise.
     */
    public static final <A,B,T> Option<T> map2(Option<A> a, Option<B> b, ApplyBi<A,B,T> f) {
        for (A aa: a) {
            for (B bb: b) {
                return Some(f.apply(aa, bb));
            }
        }
        return None();
    }
    
    /**
     * Applicative for optionals.
     * 
     * @return {@code Some(f(a,b,c))} if {@code a} and {@code b} and {@code c} are defined. {@code None()} otherwise.
     */
    public static final <A,B,C,T> Option<T> map3(Option<A> a, Option<B> b, Option<C> c, Apply3<A,B,C,T> f) {
        for (A aa: a) {
            for (B bb: b) {
                for (C cc: c) {
                    return Some(f.apply(aa, bb, cc));
                }
            }
        }
        return None();
    }
    
    /**
     * Applicative for optionals.
     * 
     * @return {@code Some(f(a,b,c,d))} if {@code a} and {@code b} and {@code c} and {@code d} are defined. {@code None()} otherwise.
     */
    public static final <A,B,C,D,T> Option<T> map4(Option<A> a, Option<B> b, Option<C> c, Option<D> d, Apply4<A,B,C,D,T> f) {
        for (A aa: a) {
            for (B bb: b) {
                for (C cc: c) {
                    for (D dd: d) {
                        return Some(f.apply(aa, bb, cc, dd));
                    }
                }
            }
        }
        return None();
    }
}
