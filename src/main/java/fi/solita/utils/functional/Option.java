package fi.solita.utils.functional;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;

public abstract class Option<T> implements Iterable<T>, Serializable {
    private static final NoneImpl<Void> NoneInstance = new NoneImpl<Void>();

    /**
     * Interface between nulls and optionals.
     * 
     * @return {@code valueOrNull} wrapped inside an Option, either as a {@code Some} or a {@code None} depending whether it was a {@code null}.
     */
    public static final <T> Option<T> of(T valueOrNull) {
        if (valueOrNull == null) {
            return None();
        } else {
            return new SomeImpl<T>(valueOrNull);
        }
    }

    /**
     * @return {@code Option} containing {@code value}.
     */
    public static final <T> Option<T> Some(T value) {
        return new SomeImpl<T>(value);
    }

    /**
     * @return undefined {@code Option}.
     */
    @SuppressWarnings("unchecked")
    public static final <T> Option<T> None() {
        return (Option<T>) NoneInstance;
    }

    Option() {
    }

    /**
     * <i>Unsafe!</i> Will throw an exception if not defined.
     * 
     * @return value from inside this {@code Option}.
     */
    public abstract T get();

    /**
     * @return value from inside this {@code Option} or {@code orElse} if not defined.
     */
    public abstract T getOrElse(T orElse);
    
    /**
     * @return {@code Option} where the value, if any, is transformed with {@code f}.
     */
    public abstract <R> Option<R> map(Apply<? super T, R> f);
    
    /**
     * @return same {@code Option} if the value satisfies {@code predicate}, otherwise a None.
     */
    public abstract Option<T> filter(Apply<? super T, Boolean> predicate);
    
    /**
     * @return {@code Option} where the value, if any, is transformed with {@code f} and a layer of Option removed.
     */
    public abstract <R> Option<R> flatMap(Apply<? super T, Option<R>> f);

    /**
     * @return collapsed value whether this was defined or not.
     */
    public abstract <R> R cata(Apply<? super T, ? extends R> ifSome, ApplyZero<? extends R> ifNone);
    
    /**
     * @return collapsed value whether this was defined or not.
     */
    public abstract <R> R fold(Apply<? super T, ? extends R> ifSome, R ifNone);

    public abstract T orElse(ApplyZero<? extends T> ifNone);
    
    /**
     * @return whether this {@code Option} contains a value or not.
     */
    public abstract boolean isDefined();
}

final class NoneImpl<T> extends Option<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public final T get() {
        throw new UnsupportedOperationException("Cannot get from None");
    }
    
    @Override
    public final T getOrElse(T orElse) {
        return orElse;
    }
    
    @Override
    public final <R> Option<R> map(Apply<? super T, R> f) {
        return None();
    }
    
    @Override
    public Option<T> filter(Apply<? super T, Boolean> f) {
        return this;
    }
    
    @Override
    public <R> Option<R> flatMap(Apply<? super T, Option<R>> f) {
        return None();
    }
    
    @Override
    public final <R> R cata(Apply<? super T, ? extends R> ifSome, ApplyZero<? extends R> ifNone) {
        return ifNone.get();
    }
    
    @Override
    public final <R> R fold(Apply<? super T, ? extends R> ifSome, R ifNone) {
        return ifNone;
    }

    @Override
    public T orElse(ApplyZero<? extends T> ifNone) {
        return ifNone.get();
    }
    
    @Override
    public final boolean isDefined() {
        return false;
    }
    
    public final Iterator<T> iterator() {
        return Collections.<T>emptyList().iterator();
    }

    @Override
    public final String toString() {
        return "None";
    }
}

final class SomeImpl<T> extends Option<T> {

    protected final T t;

    SomeImpl(T t) {
        this.t = t;
    }

    @Override
    public final T get() {
        return t;
    }

    @Override
    public final T getOrElse(T orElse) {
        return get();
    }
    
    @Override
    public final <R> Option<R> map(Apply<? super T, R> f) {
        return Option.of(f.apply(t));
    }
    
    @Override
    public Option<T> filter(Apply<? super T, Boolean> f) {
        return f.apply(t) ? this : Option.<T>None();
    }
    
    @Override
    public <R> Option<R> flatMap(Apply<? super T, Option<R>> f) {
        return f.apply(t);
    }
    
    @Override
    public final <R> R cata(Apply<? super T, ? extends R> ifSome, ApplyZero<? extends R> ifNone) {
        return ifSome.apply(t);
    }
    
    @Override
    public final <R> R fold(Apply<? super T, ? extends R> ifSome, R ifNone) {
        return ifSome.apply(t);
    }

    @Override
    public T orElse(ApplyZero<? extends T> ifNone) {
        return t;
    }

    @Override
    public final boolean isDefined() {
        return true;
    }

    public final Iterator<T> iterator() {
        return Collections.singleton(t).iterator();
    }

    @Override
    public final int hashCode() {
        return 31 + t.hashCode();
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof SomeImpl))
            return false;
        SomeImpl<?> other = (SomeImpl<?>) obj;
        return t.equals(other.t);
    }

    @Override
    public final String toString() {
        return "Some(" + t + ")";
    }
}
