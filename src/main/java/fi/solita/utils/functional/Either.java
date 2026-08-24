package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.it;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.concat;

import java.util.List;
import java.util.Optional;

/**
 * Simple binary sum type. Either "left" or "right".
 */
public class Either<L,R> {

    /**
     * @return {@code value} wrapped into left side of Either.
     */
    public static final <L,R> Either<L,R> left(L value) {
        return new Either<L, R>(Optional.of(value), Optional.empty());
    }

    /**
     * @return {@code value} wrapped into right side of Either.
     */
    public static final <L,R> Either<L,R> right(R value) {
        return new Either<L, R>(Optional.empty(), Optional.of(value));
    }
    
    /**
     * @return value from either the left side or the right.
     */
    public static final <T> T get(Either<? extends T, ? extends T> e) {
        return e.isLeft() ? e.left.get() : e.right.get();
    }
    
    /**
     * @return value of either in a list.
     */
    public static final <T> List<T> asList(Either<? extends T, ? extends T> either) {
        return newList(concat(it(either.left), it(either.right)));
    }
    
    /**
     * @return all left values
     */
    public static final <T> Iterable<T> lefts(Iterable<? extends Either<T, ?>> xs) {
        return Functional.concatMap(x -> x.left, xs);
    }
    
    /**
     * @return all right values
     */
    public static final <T> Iterable<T> rights(Iterable<? extends Either<?, T>> xs) {
        return Functional.concatMap(x -> x.right, xs);
    }

    protected Either(Optional<L> left, Optional<R> right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Left side.
     */
    public final Optional<L> left;
    
    /**
     * Right side.
     */
    public final Optional<R> right;
    
    /**
     * @return whether this Either has a Left element.
     */
    public final boolean isLeft() {
        return left.isPresent();
    }
    
    /**
     * @return whether this Either has a Right element.
     */
    public final boolean isRight() {
        return right.isPresent();
    }
    
    /**
     * @return left side transformed with {@code fLeft} or right side transformed with {@code fRight}.
     */
    public final <A,B> Either<A,B> bimap(java.util.function.Function<? super L, ? extends A> fLeft, java.util.function.Function<? super R, ? extends B> fRight) {
        return BiFunctors.<L,A,R,B>either().bimap(fLeft, fRight, this);
    }
    
    /**
     * @return left side transformed with {@code fLeft} or right side transformed with {@code fRight}.
     */
    public <T> T fold(java.util.function.Function<? super L, ? extends T> ifLeft, java.util.function.Function<? super R, ? extends T> ifRight){
        return isLeft() ? ifLeft.apply(left.get()) : ifRight.apply(right.get());
    }
    
    @SuppressWarnings("unchecked")
    public <T> Either<L,T> map(java.util.function.Function<? super R, ? extends T> f){
        return isLeft() ? (Either<L,T>)this : Either.<L,T>right(f.apply(right.get()));
    }
    
    @SuppressWarnings("unchecked")
    public <T> Either<L,T> flatMap(java.util.function.Function<? super R, Either<L,? extends T>> f){
        return isLeft() ? (Either<L,T>)this : (Either<L,T>)f.apply(right.get());
    }

    /**
     * @return left side transformed with {@code f}.
     */
    public final <A> Either<A,R> first(java.util.function.Function<? super L, ? extends A> f) {
        return BiFunctors.<L,A,R,R>either().first(f, this);
    }

    /**
     * @return right side transformed with {@code f}.
     */
    public final <B> Either<L,B> second(java.util.function.Function<? super R, ? extends B> f) {
        return BiFunctors.<L,L,R,B>either().second(f, this);
    }
    
    @Override
    public int hashCode() {
        return 31 + left.hashCode() + right.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        return this == obj ||
               obj instanceof Either && left.equals(((Either<?,?>)obj).left) && right.equals(((Either<?,?>)obj).right);
    }
    
    @Override
    public String toString() {
        return isLeft() ? "Either.left(" + left + ")" : "Either.right(" + right + ")";
    }
}
