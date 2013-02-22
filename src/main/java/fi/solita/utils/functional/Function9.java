package fi.solita.utils.functional;

public abstract class Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> extends MultiParamFunction<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>, R> {

    public abstract R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9);

    public final <U> Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, U> andThen(final Apply<? super R, ? extends U> next) {
        final Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> self = this;
        return new Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, U>() {
            @Override
            public U apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return next.apply(self.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9));
            }
        };
    }

    public final Function1<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>, R> tuppled() {
        return new Function1<Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9>, R>() {
            @Override
            public R apply(Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> t) {
                return Function9.this.apply(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9);
            }
        };
    }
}
