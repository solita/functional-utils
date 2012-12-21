package fi.solita.utils.functional;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;

public abstract class Option<T> implements Iterable<T> {
    private static final NoneImpl<Void> None = new NoneImpl<Void>();

    @SuppressWarnings("unchecked")
    public static <T> Option<T> of(T t) {
        if (t == null) {
            return None();
        } else if (t instanceof Serializable || t.getClass().isPrimitive()) {
            return (Option<T>) new SomeImpl.SerializableSomeImpl<Serializable>((Serializable) t);
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

    public abstract boolean isDefined();

    public static abstract class SerializableOption<T extends Serializable> extends Option<T> {
    }
}

final class NoneImpl<T> extends Option<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    NoneImpl() {
        //
    }
    @Override
    public T get() {
        throw new UnsupportedOperationException("Cannot get from None");
    }
    @Override
    public T getOrElse(T orElse) {
        return orElse;
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

    protected T t;

    // needed for SerializableSome deserialization
    SomeImpl() {
    }

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

    static final class SerializableSomeImpl<T extends Serializable> extends SomeImpl<T> implements Serializable {
        private static final long serialVersionUID = 1L;

        SerializableSomeImpl(T t) {
            super(t);
        }

        private void writeObject(ObjectOutputStream out) throws IOException  {
            out.writeObject(t);
        }

        @SuppressWarnings("unchecked")
        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            t = (T)in.readObject();
        }
    }
}