package fi.solita.utils.functional;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;

public abstract class Option<T> implements Iterable<T>, Serializable {
    private static final NoneImpl<Void> NoneInstance = new NoneImpl<Void>();

    public static final <T> Option<T> of(T t) {
        if (t == null) {
            return None();
        } else {
            return new SomeImpl<T>(t);
        }
    }

    public static final <T> Option<T> Some(T t) {
        return new SomeImpl<T>(t);
    }

    @SuppressWarnings("unchecked")
    public static final <T> Option<T> None() {
        return (Option<T>) NoneInstance;
    }

    Option() {
    }

    public abstract T get();

    public abstract T getOrElse(T orElse);
    
    public abstract <R> Option<R> map(Apply<? super T, R> f);

    public abstract <R> R cata(Apply<? super T, ? extends R> ifSome, Function0<? extends R> ifNone);
    
    public abstract <R> R fold(Apply<? super T, ? extends R> ifSome, R ifNone);
    
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
    public final <R> R cata(Apply<? super T, ? extends R> ifSome, Function0<? extends R> ifNone) {
        return ifNone.apply();
    }
    
    @Override
    public final <R> R fold(Apply<? super T, ? extends R> ifSome, R ifNone) {
        return ifNone;
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
    public final <R> R cata(Apply<? super T, ? extends R> ifSome, Function0<? extends R> ifNone) {
        return ifSome.apply(t);
    }
    
    @Override
    public final <R> R fold(Apply<? super T, ? extends R> ifSome, R ifNone) {
        return ifSome.apply(t);
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