package fi.solita.utils.functional;

public abstract class Function2<T1, T2, R> extends MultiParamFunction<Tuple2<T1,T2>, R> {

    public abstract R apply(T1 t1, T2 t2);
    
    public final <U> Function2<T1, T2, U> andThen(final Apply<? super R, ? extends U> next) {
        final Function2<T1, T2, R> self = this;
        return new Function2<T1, T2, U>() {
            @Override
            public U apply(T1 t1, T2 t2) {
                return next.apply(self.apply(t1, t2));
            }
        };
    }
    
    @Override
    public final Function1<Tuple2<T1, T2>, R> tuppled() {
        return new Function1<Tuple2<T1, T2>, R>() {
            @Override
            public R apply(Tuple2<T1, T2> t) {
                return Function2.this.apply(t._1, t._2);
            }
        };
    }
    
    public final Function1<T1, Function1<T2, R>> curried() {
        return new Function1<T1, Function1<T2,R>>() {
            @Override
            public Function1<T2, R> apply(final T1 t1) {
                return new Function1<T2, R>() {
                    @Override
                    public R apply(T2 t2) {
                        return Function2.this.apply(t1, t2);
                    }
                };
            }
        };
    }
    
    public final Function1<T2,R> ap(final T1 t) {
        return new Function1<T2,R>() {
            @Override
            public R apply(T2 t2) {
                return Function2.this.apply(t, t2);
            }
        };
    }
}
