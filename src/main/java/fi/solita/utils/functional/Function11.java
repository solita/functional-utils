package fi.solita.utils.functional;

public abstract class Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R> extends MultiParamFunction<Tuple11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>, R> {

    public abstract R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11);

    public final <U> Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, U> andThen(final Apply<? super R, ? extends U> next) {
        final Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R> self = this;
        return new Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, U>() {
            @Override
            public U apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11) {
                return next.apply(self.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11));
            }
        };
    }

    public final Function1<Tuple11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>, R> tuppled() {
        return new Function1<Tuple11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11>, R>() {
            @Override
            public R apply(Tuple11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> t) {
                return Function11.this.apply(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11);
            }
        };
    }
    
    public Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, R>>>>>>>>>>> curried() {
        return new Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, R>>>>>>>>>>>() {
            @Override
            public Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, R>>>>>>>>>> apply(final T1 t1) {
                return new Function10<T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R>() {
                    @Override
                    public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11) {
                        return Function11.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11);
                    }
                }.curried();
            }
        };
    }
}
