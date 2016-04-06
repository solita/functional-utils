package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Option.Some;

import java.io.Serializable;
import java.util.List;

public class Either<L,R> implements Serializable {

    public static final <L,R> Either<L,R> left(L left) {
        return new Either<L, R>(Some(left), Option.<R>None());
    }

    public static final <L,R> Either<L,R> right(R right) {
        return new Either<L, R>(Option.<L>None(), Some(right));
    }
    
    public static final <T> T get(Either<? extends T, ? extends T> e) {
        return e.isLeft() ? e.left.get() : e.right.get();
    }
    
    public static final <T> List<T> asList(Either<? extends T, ? extends T> either) {
        return newList(concat(either.left, either.right));
    }

    protected Either(Option<L> left, Option<R> right) {
        this.left = left;
        this.right = right;
    }

    public final Option<L> left;
    public final Option<R> right;
    
    public final boolean isLeft() {
        return left.isDefined();
    }
    
    public final boolean isRight() {
        return right.isDefined();
    }
    
    public final <A,B> Either<A,B> bimap(Apply<? super L, ? extends A> f, Apply<? super R, ? extends B> f2) {
        return BiFunctors.<L,A,R,B>either().bimap(f, f2, this);
    }

    public final <A> Either<A,R> first(Apply<? super L, ? extends A> f) {
        return BiFunctors.<L,A,R,R>either().first(f, this);
    }

    public final <B> Either<L,B> second(Apply<? super R, ? extends B> f) {
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
