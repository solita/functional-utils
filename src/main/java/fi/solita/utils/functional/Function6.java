package fi.solita.utils.functional;

public abstract class Function6<T1, T2, T3, T4, T5, T6, R> extends MultiParamFunction<Tuple6<T1, T2, T3, T4, T5, T6>, R> {

    public abstract R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6);

    public final <U> Function6<T1, T2, T3, T4, T5, T6, U> andThen(final Apply<? super R, ? extends U> next) {
        final Function6<T1, T2, T3, T4, T5, T6, R> self = this;
        return new Function6<T1, T2, T3, T4, T5, T6, U>() {
            @Override
            public U apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
                return next.apply(self.apply(t1, t2, t3, t4, t5, t6));
            }
        };
    }

    public final Function1<Tuple6<T1, T2, T3, T4, T5, T6>, R> tuppled() {
        return new Function1<Tuple6<T1, T2, T3, T4, T5, T6>, R>() {
            @Override
            public R apply(Tuple6<T1, T2, T3, T4, T5, T6> t) {
                return Function6.this.apply(t._1, t._2, t._3, t._4, t._5, t._6);
            }
        };
    }
    
    public Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, R>>>>>> curried() {
        return new Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, R>>>>>>() {
            @Override
            public Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, R>>>>> apply(final T1 t1) {
                return new Function5<T2, T3, T4, T5, T6, R>() {
                    @Override
                    public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
                        return Function6.this.apply(t1, t2, t3, t4, t5, t6);
                    }
                }.curried();
            }
        };
    }
    
    public final Function1<T6,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5);
    }
    
    public final Function2<T5,T6,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4) {
        return ap(t1).ap(t2).ap(t3).ap(t4);
    }
    
    public final Function3<T4,T5,T6,R> ap(final T1 t1, final T2 t2, final T3 t3) {
        return ap(t1).ap(t2).ap(t3);
    }
    
    public final Function4<T3,T4,T5,T6,R> ap(final T1 t1, final T2 t2) {
        return ap(t1).ap(t2);
    }
    
    public final Function5<T2,T3,T4,T5,T6,R> ap(final T1 t) {
        return new Function5<T2,T3,T4,T5,T6,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
                return Function6.this.apply(t, t2, t3, t4, t5, t6);
            }
        };
    }
}
