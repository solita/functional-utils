package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Option.Some;

import java.io.Serializable;
import java.util.List;

/**
 * L or M or R
 */
public class Either3<L,M,R> implements Serializable {

    /**
     * @return {@code value} wrapped into left of Either3.
     */
    public static final <L,M,R> Either3<L,M,R> left(L value) {
        return new Either3<L, M, R>(Some(value), Option.<M>None(), Option.<R>None());
    }
    
    /**
     * @return {@code value} wrapped into middle of Either3.
     */
    public static final <L,M,R> Either3<L,M,R> middle(M value) {
        return new Either3<L, M, R>(Option.<L>None(), Some(value), Option.<R>None());
    }

    /**
     * @return {@code value} wrapped into right of Either3.
     */
    public static final <L,M,R> Either3<L,M,R> right(R value) {
        return new Either3<L, M, R>(Option.<L>None(), Option.<M>None(), Some(value));
    }
    
    public static final <T> T get(Either3<? extends T, ? extends T, ? extends T> e) {
        return e.isLeft()   ? e.left.get() :
               e.isMiddle() ? e.middle.get() :
                              e.right.get();
    }
    
    /**
     * @return value of either3 in a list.
     */
    public static final <T> List<T> asList(Either3<? extends T, ? extends T, ? extends T> either) {
        return newList(concat(either.left, either.middle, either.right));
    }
    
    /**
     * @return all left values
     */
    public static final <T> Iterable<T> lefts(Iterable<? extends Either3<T, ?, ?>> xs) {
        return Functional.flatMap(Transformers.<T>either3Left(), xs);
    }
    
    /**
     * @return all middle values
     */
    public static final <T> Iterable<T> middles(Iterable<? extends Either3<?, T, ?>> xs) {
        return Functional.flatMap(Transformers.<T>either3Middle(), xs);
    }
    
    /**
     * @return all right values
     */
    public static final <T> Iterable<T> rights(Iterable<? extends Either3<?, ?, T>> xs) {
        return Functional.flatMap(Transformers.<T>either3Right(), xs);
    }

    protected Either3(Option<L> left, Option<M> middle, Option<R> right) {
        this.left = left;
        this.middle = middle;
        this.right = right;
    }

    /**
     * Left side.
     */
    public final Option<L> left;
    
    /**
     * Middle.
     */
    public final Option<M> middle;
    
    /**
     * Right side.
     */
    public final Option<R> right;
    
    /**
     * @return whether this Either3 has a Left element.
     */
    public final boolean isLeft() {
        return left.isDefined();
    }
    
    /**
     * @return whether this Either3 has a Middle element.
     */
    public final boolean isMiddle() {
        return middle.isDefined();
    }
    
    /**
     * @return whether this Either3 has a Right element.
     */
    public final boolean isRight() {
        return right.isDefined();
    }
    
    /**
     * @return left transformed with {@code fLeft}, middle transformed with {@code fMiddle} or right transformed with {@code fRight}.
     */
    public final <A,B,C> Either3<A,B,C> trimap(Apply<? super L, ? extends A> fLeft, Apply<? super M, ? extends B> fMiddle, Apply<? super R, ? extends C> fRight) {
        return isLeft()   ? Either3.<A,B,C>left(fLeft.apply(left.get())) :
               isMiddle() ? Either3.<A,B,C>middle(fMiddle.apply(middle.get())) :
                            Either3.<A,B,C>right(fRight.apply(right.get()));
    }
    
    /**
     * @return left transformed with {@code fLeft}, middle transformed with {@code fMiddle} or right transformed with {@code fRight}.
     */
    public <T> T fold(Apply<? super L, ? extends T> ifLeft, Apply<? super M, ? extends T> ifMiddle, Apply<? super R, ? extends T> ifRight){
        return isLeft()   ? ifLeft.apply(left.get()) :
               isMiddle() ? ifMiddle.apply(middle.get()) :
                            ifRight.apply(right.get());
    }
    
    /**
     * @return left transformed with {@code f}.
     */
    @SuppressWarnings("unchecked")
    public final <A> Either3<A,M,R> first(Apply<? super L, ? extends A> f) {
        return isLeft() ? Either3.<A,M,R>left(f.apply(left.get())) : (Either3<A,M,R>)this;
    }

    /**
     * @return middle transformed with {@code f}.
     */
    @SuppressWarnings("unchecked")
    public final <B> Either3<L,B,R> second(Apply<? super M, ? extends B> f) {
        return isMiddle() ? Either3.<L,B,R>middle(f.apply(middle.get())) : (Either3<L,B,R>)this;
    }
    
    /**
     * @return right transformed with {@code f}.
     */
    @SuppressWarnings("unchecked")
    public final <C> Either3<L,M,C> third(Apply<? super R, ? extends C> f) {
        return isRight() ? Either3.<L,M,C>right(f.apply(right.get())) : (Either3<L,M,C>)this;
    }
    
    @Override
    public int hashCode() {
        return 31 + left.hashCode() + middle.hashCode() + right.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        return this == obj ||
               obj instanceof Either3 && left.equals(((Either3<?,?,?>)obj).left)
                                      && middle.equals(((Either3<?,?,?>)obj).middle)
                                      && right.equals(((Either3<?,?,?>)obj).right);
    }
    
    @Override
    public String toString() {
        return isLeft()   ? "Either3.left(" + left + ")" :
               isMiddle() ? "Either3.middle(" + middle + ")" :
                            "Either3.right(" + right + ")";
    }
}
