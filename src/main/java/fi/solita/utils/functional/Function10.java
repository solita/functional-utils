package fi.solita.utils.functional;

public abstract class Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> extends MultiParamFunction<Tuple10<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5, ? extends T6, ? extends T7, ? extends T8, ? extends T9, ? extends T10>, R, T1> {

    public abstract R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10);

    @Override
    public final <U> Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, U> andThen(final Apply<? super R, ? extends U> next) {
        final Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> self = this;
        return new Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, U>() {
            @Override
            public U apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10) {
                return next.apply(self.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10));
            }
        };
    }

    @Override
    public final Function1<Tuple10<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5, ? extends T6, ? extends T7, ? extends T8, ? extends T9, ? extends T10>, R> tuppled() {
        return new Function1<Tuple10<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5, ? extends T6, ? extends T7, ? extends T8, ? extends T9, ? extends T10>, R>() {
            @Override
            public R apply(Tuple10<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5, ? extends T6, ? extends T7, ? extends T8, ? extends T9, ? extends T10> t) {
                return Function10.this.apply(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10);
            }
        };
    }
    
    @Override
    public Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, R>>>>>>>>>> curried() {
        return new Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, R>>>>>>>>>>() {
            @Override
            public Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, R>>>>>>>>> apply(final T1 t1) {
                return new Function9<T2, T3, T4, T5, T6, T7, T8, T9, T10, R>() {
                    @Override
                    public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10) {
                        return Function10.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10);
                    }
                }.curried();
            }
        };
    }
    
    public final Function1<T10,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9);
    }
    
    public final Function2<T9,T10,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8);
    }
    
    public final Function3<T8,T9,T10,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7);
    }
    
    public final Function4<T7,T8,T9,T10,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6);
    }
    
    public final Function5<T6,T7,T8,T9,T10,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5);
    }
    
    public final Function6<T5,T6,T7,T8,T9,T10,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4) {
        return ap(t1).ap(t2).ap(t3).ap(t4);
    }
    
    public final Function7<T4,T5,T6,T7,T8,T9,T10,R> ap(final T1 t1, final T2 t2, final T3 t3) {
        return ap(t1).ap(t2).ap(t3);
    }
    
    public final Function8<T3,T4,T5,T6,T7,T8,T9,T10,R> ap(final T1 t1, final T2 t2) {
        return ap(t1).ap(t2);
    }
    
    @Override
    public final Function9<T2,T3,T4,T5,T6,T7,T8,T9,T10,R> ap(final T1 t) {
        return new Function9<T2,T3,T4,T5,T6,T7,T8,T9,T10,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10) {
                return Function10.this.apply(t, t2, t3, t4, t5, t6, t7, t8, t9, t10);
            }
        };
    }
}
