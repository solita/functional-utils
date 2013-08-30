package fi.solita.utils.functional;

public abstract class Function7<T1, T2, T3, T4, T5, T6, T7, R> extends MultiParamFunction<Tuple7<T1, T2, T3, T4, T5, T6, T7>, R> {

    public abstract R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7);

    public final <U> Function7<T1, T2, T3, T4, T5, T6, T7, U> andThen(final Apply<? super R, ? extends U> next) {
        final Function7<T1, T2, T3, T4, T5, T6, T7, R> self = this;
        return new Function7<T1, T2, T3, T4, T5, T6, T7, U>() {
            @Override
            public U apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
                return next.apply(self.apply(t1, t2, t3, t4, t5, t6, t7));
            }
        };
    }

    public final Function1<Tuple7<T1, T2, T3, T4, T5, T6, T7>, R> tuppled() {
        return new Function1<Tuple7<T1, T2, T3, T4, T5, T6, T7>, R>() {
            @Override
            public R apply(Tuple7<T1, T2, T3, T4, T5, T6, T7> t) {
                return Function7.this.apply(t._1, t._2, t._3, t._4, t._5, t._6, t._7);
            }
        };
    }
    
    public Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, R>>>>>>> curried() {
        return new Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, R>>>>>>>() {
            @Override
            public Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, R>>>>>> apply(final T1 t1) {
                return new Function6<T2, T3, T4, T5, T6, T7, R>() {
                    @Override
                    public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
                        return Function7.this.apply(t1, t2, t3, t4, t5, t6, t7);
                    }
                }.curried();
            }
        };
    }
    
    public final Function1<T7,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6);
    }
    
    public final Function2<T6,T7,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5);
    }
    
    public final Function3<T5,T6,T7,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4) {
        return ap(t1).ap(t2).ap(t3).ap(t4);
    }
    
    public final Function4<T4,T5,T6,T7,R> ap(final T1 t1, final T2 t2, final T3 t3) {
        return ap(t1).ap(t2).ap(t3);
    }
    
    public final Function5<T3,T4,T5,T6,T7,R> ap(final T1 t1, final T2 t2) {
        return ap(t1).ap(t2);
    }
    
    public final Function6<T2,T3,T4,T5,T6,T7,R> ap(final T1 t) {
        return new Function6<T2,T3,T4,T5,T6,T7,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
                return Function7.this.apply(t, t2, t3, t4, t5, t6, t7);
            }
        };
    }
}
