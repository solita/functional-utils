package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.emptyList;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import fi.solita.utils.functional.Iterables.RepeatingIterable;
import fi.solita.utils.functional.Iterables.ZippingIterable;

public abstract class Functional extends FunctionalC {

    public static final <T> Iterable<T> subtract(Iterable<? extends T> a, final Collection<? extends T> b) {
        return FunctionalImpl.subtract(a, b);
    }
    
    public static final <T> Iterable<T> remove(T toRemove, Iterable<T> xs) {
        return FunctionalImpl.remove(toRemove, xs);
    }

    public static final <T> Option<T> find(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return FunctionalImpl.find(predicate, xs);
    }

    public static final <T> Iterable<T> filter(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return FunctionalImpl.filter(predicate, xs);
    }
    
    public static final <S, T> Iterable<T> map(Apply<? super S, ? extends T> f, Iterable<S> xs) {
        return FunctionalImpl.map(f, xs);
    }
    
    public static final <S, T1, T2> Iterable<Pair<T1,T2>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, Iterable<S> xs) {
        return FunctionalImpl.map(f1, f2, xs);
    }
    
    public static final <S, T1, T2, T3> Iterable<Tuple3<T1,T2,T3>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, Apply<? super S, T3> f3, Iterable<S> xs) {
        return FunctionalImpl.map(f1, f2, f3, xs);
    }
    
    public static final <S, T1, T2, T3, T4> Iterable<Tuple4<T1,T2,T3,T4>> map(Apply<? super S, T1> f1, Apply<? super S, T2> f2, Apply<? super S, T3> f3, Apply<? super S, T4> f4, Iterable<S> xs) {
        return FunctionalImpl.map(f1, f2, f3, f4, xs);
    }
    
    public static final <F, A, B, FA extends F, FB extends F> FB fmap(Functor<F, A, B, FA, FB> functor, Apply<A,B> f, FA xs) {
        return functor.fmap(f, xs);
    }
    
    public static final <F, A, B, C, D, FAC extends F, FBD extends F> FBD bimap(BiFunctor<F, A, B, C, D, FAC, FBD, ?, ?> p, Apply<A, B> f, Apply<C, D> f2, FAC e) {
        return p.bimap(f, f2, e);
    }
    
    public static final <F, A, B, FAC extends F, FBC extends F> FBC first(BiFunctor<F, A, B, ?, ?, FAC, ?, FBC, ?> p, Apply<A, B> f, FAC e) {
        return p.first(f, e);
    }
    
    public static final <F, C, D, FAC extends F, FAD extends F> FAD second(BiFunctor<F, ?, ?, C, D, FAC, ?, ?, FAD> p, Apply<C, D> f, FAC e) {
        return p.second(f, e);
    }

    public static final <S, T> Iterable<T> flatMap(Apply<? super S, ? extends Iterable<? extends T>> f, Iterable<S> xs) {
        return FunctionalImpl.flatMap(f, xs);
    }

    public static final <T> Iterable<T> flatten(Iterable<? extends Iterable<? extends T>> xs) {
        return FunctionalImpl.flatten(xs);
    }
    
    public static final <T> void foreach(Apply<? super T, Void> procedure, Iterable<T> xs) {
        FunctionalImpl.foreach(procedure, xs);
    }
    
    public static final <T> void foreach(ApplyVoid<? super T> procedure, Iterable<T> xs) {
        FunctionalImpl.foreach(procedure, xs);
    }

    public static final <T> Iterable<List<T>> grouped(long groupSize, Iterable<T> xs) {
        return FunctionalImpl.grouped(groupSize, xs);
    }
    
    public static final <T> Iterable<Iterable<T>> group(Iterable<T> xs) {
        return FunctionalImpl.group(xs);
    }
    
    public static final <T> Iterable<Iterable<T>> group(Apply<Tuple2<T,T>, Boolean> comparator, Iterable<T> xs) {
        return FunctionalImpl.group(comparator, xs);
    }
    
    public static final <T> T head(Iterable<T> xs) {
        return FunctionalImpl.head(xs);
    }
    
    public static final <T> Option<T> headOption(Iterable<T> xs) {
        return FunctionalImpl.headOption(xs);
    }
    
    public static final <T> Iterable<T> tail(Iterable<T> xs) {
        return FunctionalImpl.tail(xs);
    }
    
    public static final <T> T last(Iterable<T> xs) {
        return FunctionalImpl.last(xs);
    }
    
    public static final <T> Option<T> lastOption(Iterable<T> xs) {
        return FunctionalImpl.lastOption(xs);
    }
    
    public static final <T> Iterable<T> init(Iterable<T> xs) {
        return FunctionalImpl.init(xs);
    }
    
    public static final <T> Iterable<T> take(long amount, Iterable<T> xs) {
        return FunctionalImpl.take(amount, xs);
    }

    public static final <T> Iterable<T> drop(long amount, Iterable<T> xs) {
        return FunctionalImpl.drop(amount, xs);
    }
    
    public static final <T> Iterable<T> takeWhile(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return FunctionalImpl.takeWhile(predicate, xs);
    }
    
    public static final <T> Iterable<T> dropWhile(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return FunctionalImpl.dropWhile(predicate, xs);
    }
    
    public static final <T> Pair<Iterable<T>, Iterable<T>> span(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return FunctionalImpl.span(predicate, xs);
    }
    
    public static final boolean isEmpty(Iterable<?> xs) {
        return FunctionalImpl.isEmpty(xs);
    }
    
    public static final long size(Iterable<?> xs) {
        return FunctionalImpl.size(xs);
    }

    public static final <T> boolean contains(T candidate, Iterable<T> xs) {
        return FunctionalImpl.exists(Predicates.equalTo(candidate), xs);
    }

    public static final <T> boolean exists(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return FunctionalImpl.exists(predicate, xs);
    }

    public static final <T> boolean forall(Apply<? super T, Boolean> predicate, Iterable<T> xs) {
        return FunctionalImpl.forall(predicate, xs);
    }
    
    public static final <T> Iterable<T> cons(T x, Iterable<? extends T> xs) {
        return FunctionalImpl.cons(x, xs);
    }
    
    public static final <T> Iterable<T> concat(Iterable<? extends T> a, Iterable<? extends T> b) {
        return FunctionalImpl.concat(a, b);
    }

    public static final <T extends Comparable<? super T>> Iterable<T> sort(Iterable<T> xs) {
        return FunctionalImpl.sort(xs);
    }
    
    public static final <T> Iterable<T> sort(Comparator<? super T> comparator, Iterable<T> xs) {
        return FunctionalImpl.sort(comparator, xs);
    }
    
    public static final <T extends SemiGroup<T>> Option<T> reduce(Iterable<? extends T> xs) {
        return FunctionalImpl.reduce(xs);
    }
    
    public static final <T> T reduce(Monoid<T> m, Iterable<? extends T> xs) {
        return FunctionalImpl.reduce(m, xs);
    }
    
    public static final <T,Z> Z fold(Z zero, Apply<Map.Entry<? extends Z,? extends T>, Z> f, Iterable<? extends T> xs) {
        return FunctionalImpl.fold(zero, f, xs);
    }
    
    public static final <T> Option<T> fold(Apply<Map.Entry<? extends T,? extends T>, T> f, Iterable<? extends T> xs) {
        return FunctionalImpl.fold(f, xs);
    }

    public static <T extends Comparable<T>> Option<T> min(Iterable<T> xs) {
        return FunctionalImpl.min(xs);
    }
    
    public static final <T extends Comparable<T>> Option<T> max(Iterable<T> xs) {
        return FunctionalImpl.max(xs);
    }

    public static final <A,B> Iterable<Tuple2<A, B>> zip(Iterable<A> a, Iterable<B> b) {
        return FunctionalImpl.zip(a, b);
    }
    
    public static final <A,B,C> Iterable<Tuple3<A, B, C>> zip(Iterable<A> a, Iterable<B> b, Iterable<C> c) {
        return FunctionalImpl.zip(a, b, c); 
    }
    
    public static final <A> Iterable<Tuple2<Integer, A>> zipWithIndex(Iterable<A> a) {
        return new ZippingIterable<Integer,A>(range(0), a);
    }

    public static final <T, S extends Enumerable<T> & Bounded<T>> Iterable<T> range(S enumeration) {
        return range(enumeration, enumeration.minBound());
    }
    
    public static final <T> Iterable<T> range(Enumerable<T> enumeration, T from) {
        return FunctionalImpl.range(enumeration, from);
    }
    
    public static final <T> Iterable<T> range(Enumerable<T> enumeration, T from, T toInclusive) {
        return FunctionalImpl.range(enumeration, from, toInclusive);
    }

    public static final <T> Iterable<T> repeat(T value) {
        return new RepeatingIterable<T>(value);
    }

    public static final <T> Iterable<T> repeat(T value, long amount) {
        if (amount <= 0)
            return emptyList();
        return new RepeatingIterable<T>(value, amount);
    }
    
    public static final String mkString(Iterable<Character> xs) {
        return FunctionalImpl.mkString(xs);
    }
    
    public static final String mkString(CharSequence delimiter, Iterable<? extends CharSequence> xs) {
        return FunctionalImpl.mkString(delimiter, xs);
    }

    public static final <T> Iterable<T> reverse(Iterable<T> xs) {
        return FunctionalImpl.reverse(xs);
    }
    
    public static final <T> Iterable<T> distinct(Iterable<T> xs) {
        return FunctionalImpl.distinct(xs);
    }
    
    public static final <T,R> Iterable<R> sequence(T value, Iterable<? extends Apply<? super T,? extends R>> fs) {
        return FunctionalImpl.sequence(value, fs);
    }
    
    public static final <T> Iterable<Iterable<T>> transpose(Iterable<? extends Iterable<T>> xs) {
        return FunctionalImpl.transpose(xs);
    }
    
    public static final CharSequence unlines(Iterable<? extends CharSequence> xs) {
        return FunctionalImpl.unlines(xs);
    }
}
