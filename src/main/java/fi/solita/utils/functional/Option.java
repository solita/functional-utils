package fi.solita.utils.functional;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;

public abstract class Option<T> implements Iterable<T>, Serializable {
    private static final NoneImpl<Void> None = new NoneImpl<Void>();

    public static <T> Option<T> of(T t) {
        if (t == null) {
            return None();
        } else {
            return new SomeImpl<T>(t);
        }
    }

    public static <T> Option<T> Some(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Passed null to Some");
        }
        return of(t);
    }

    @SuppressWarnings("unchecked")
    public static <T> Option<T> None() {
        return (Option<T>) None;
    }

    Option() {
    }

    public abstract T get();

    public abstract T getOrElse(T orElse);
    
    public abstract <R> Option<R> map(Apply<? super T, R> f);

    public abstract <R> R cata(Apply<? super T,R> ifSome, Function0<R> ifNone);
    
    public abstract <R> R fold(Apply<? super T,R> ifSome, R ifNone);
    
    public abstract boolean isDefined();
}

final class NoneImpl<T> extends Option<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Override
    public T get() {
        throw new UnsupportedOperationException("Cannot get from None");
    }
    
    @Override
    public T getOrElse(T orElse) {
        return orElse;
    }
    
    @Override
    public <R> Option<R> map(Apply<? super T, R> f) {
        return None();
    }
    
    @Override
    public <R> R cata(Apply<? super T, R> ifSome, Function0<R> ifNone) {
        return ifNone.apply();
    }
    
    @Override
    public <R> R fold(Apply<? super T, R> ifSome, R ifNone) {
        return ifNone;
    }
    
    @Override
    public boolean isDefined() {
        return false;
    }
    
    @Override
    public Iterator<T> iterator() {
        return Collections.<T>emptyList().iterator();
    }

    @Override
    public String toString() {
        return "None";
    }
}

class SomeImpl<T> extends Option<T> {

    protected final T t;

    SomeImpl(T t) {
        if (t == null) {
            throw new IllegalArgumentException("Passed null to Some");
        }
        this.t = t;
    }

    @Override
    public T get() {
        return t;
    }

    @Override
    public T getOrElse(T orElse) {
        return get();
    }
    
    @Override
    public <R> Option<R> map(Apply<? super T, R> f) {
        return Some(f.apply(t));
    }
    
    @Override
    public <R> R cata(Apply<? super T, R> ifSome, Function0<R> ifNone) {
        return ifSome.apply(t);
    }
    
    @Override
    public <R> R fold(Apply<? super T, R> ifSome, R ifNone) {
        return ifSome.apply(t);
    }

    @Override
    public boolean isDefined() {
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return Collections.singleton(t).iterator();
    }

    @Override
    public int hashCode() {
        return 31 + t.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
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
    public String toString() {
        return "Some(" + t + ")";
    }
}