package fi.solita.utils.functional;

import java.io.Serializable;

public abstract class Function1<T, R> implements Apply<T,R>, Serializable {

    public static final <T> Function1<T, T> id() {
        return new Function1<T,T>() {
            @Override
            public T apply(T t) {
                return t;
            }
        };
    }

    public abstract R apply(T t);

    public <U> Function1<T, U> andThen(final Apply<? super R, ? extends U> next) {
        final Function1<T, R> self = this;
        return new Function1<T, U>() {
            @Override
            public U apply(T source) {
                return next.apply(self.apply(source));
            }
        };
    }
    
    public <U> Function1<U, R> compose(final Apply<? super U, ? extends T> next) {
        final Function1<T, R> self = this;
        return new Function1<U, R>() {
            @Override
            public R apply(U source) {
                return self.apply(next.apply(source));
            }
        };
    }

    public final Function1<Tuple1<T>, R> tuppled() {
        return new Function1<Tuple1<T>, R>() {
            @Override
            public R apply(Tuple1<T> source) {
                return Function1.this.apply(source._1);
            }
        };
    }
    
    public final Function0<R> applyPartial(final T t) {
        return new Function0<R>() {
            @Override
            public R apply() {
                return Function1.this.apply(t);
            }
        };
    }
}
