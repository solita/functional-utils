package fi.solita.utils.functional;

public abstract class Function {
    private Function() {
    }
    
    public static final <R> Function0<R> of(final R r) {
        return new Function0<R>() {
            @Override
            public R apply() {
                return r;
            }
        };
    }
    
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
    
    public static final <R> Function0<R> throwing(final RuntimeException e) {
        return new Function0<R>() {
            @Override
            public R apply() {
                throw e;
            }
        };
    }
    
    public static final <A,B,C> Function1<B,Function1<A,C>> flip(final Apply<A,? extends Apply<B,C>> f) {
        return new Function1<B,Function1<A,C>>() {
            @Override
            public Function1<A, C> apply(final B g) {
                return new Function1<A,C>() {
                    @Override
                    public C apply(A t) {
                        return f.apply(t).apply(g);
                    }
                };
            }
        };
    }
}
