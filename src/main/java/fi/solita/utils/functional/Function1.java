package fi.solita.utils.functional;

import java.io.Serializable;

public abstract class Function1<T, R> implements Apply<T,R>, Serializable {
    
    public static final <T,R> Function1<T,R> of(final Apply<T,R> apply) {
        return new Function1<T, R>() {
            @Override
            public R apply(T t) {
                return apply.apply(t);
            }
        };
    }
    
    private static final Function1<?,?> ID = new Function1<Object,Object>() {
        @Override
        public Object apply(Object t) {
            return t;
        }
    };

    @SuppressWarnings("unchecked")
		public static final <T> Function1<T, T> id() {
        return (Function1<T, T>) ID;
    }

    public abstract R apply(T t);

    public <U> Function1<T, U> andThen(final Apply<? super R, ? extends U> next) {
        return new Function1<T, U>() {
            @Override
            public U apply(T source) {
                return next.apply(Function1.this.apply(source));
            }
        };
    }
    
    public <U> Function1<U, R> compose(final Apply<? super U, ? extends T> next) {
        return new Function1<U, R>() {
            @Override
            public R apply(U source) {
                return Function1.this.apply(next.apply(source));
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
    
    public final Function0<R> ap(final T t) {
        return new Function0<R>() {
            @Override
            public R apply() {
                return Function1.this.apply(t);
            }
        };
    }
}
