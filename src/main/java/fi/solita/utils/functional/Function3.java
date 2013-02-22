package fi.solita.utils.functional;

public abstract class Function3<T1, T2, T3, R> extends MultiParamFunction<Tuple3<T1,T2,T3>, R> {

    public abstract R apply(T1 t1, T2 t2, T3 t3);
    
    public final <U> Function3<T1, T2, T3, U> andThen(final Apply<? super R, ? extends U> next) {
        final Function3<T1, T2, T3, R> self = this;
        return new Function3<T1, T2, T3, U>() {
            @Override
            public U apply(T1 t1, T2 t2, T3 t3) {
                return next.apply(self.apply(t1, t2, t3));
            }
        };
    }
    
    public final Function1<Tuple3<T1, T2, T3>, R> tuppled() { 
        return new Function1<Tuple3<T1, T2, T3>, R>() {
            @Override
            public R apply(Tuple3<T1, T2, T3> t) {
                return Function3.this.apply(t._1, t._2, t._3);
            }
        };
    }
    
    public Function1<T1, Function1<T2, Function1<T3, R>>> curried() {
        return new Function1<T1, Function1<T2, Function1<T3, R>>>() {
            @Override
            public Function1<T2, Function1<T3, R>> apply(final T1 t1) {
                return new Function2<T2, T3, R>() {
                    @Override
                    public R apply(T2 t2, T3 t3) {
                        return Function3.this.apply(t1, t2, t3);
                    }
                }.curried();
            }
        };
    }
}
