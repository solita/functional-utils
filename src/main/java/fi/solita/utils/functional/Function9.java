package fi.solita.utils.functional;


public abstract class Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> extends MultiParamFunction<Tuple9<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5, ? extends T6, ? extends T7, ? extends T8, ? extends T9>, R> {

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

    public final Function1<Tuple9<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5, ? extends T6, ? extends T7, ? extends T8, ? extends T9>, R> tuppled() {
        return new Function1<Tuple9<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5, ? extends T6, ? extends T7, ? extends T8, ? extends T9>, R>() {
            @Override
            public R apply(Tuple9<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5, ? extends T6, ? extends T7, ? extends T8, ? extends T9> t) {
                return Function9.this.apply(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9);
            }
        };
    }
    
    public Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, R>>>>>>>>> curried() {
        return new Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, R>>>>>>>>>() {
            @Override
            public Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, R>>>>>>>> apply(final T1 t1) {
                return new Function8<T2, T3, T4, T5, T6, T7, T8, T9, R>() {
                    @Override
                    public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                        return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
                    }
                }.curried();
            }
        };
    }
    
    public final Function1<T9,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8);
    }
    
    public final Function2<T8,T9,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7);
    }
    
    public final Function3<T7,T8,T9,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6);
    }
    
    public final Function4<T6,T7,T8,T9,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5);
    }
    
    public final Function5<T5,T6,T7,T8,T9,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4) {
        return ap(t1).ap(t2).ap(t3).ap(t4);
    }
    
    public final Function6<T4,T5,T6,T7,T8,T9,R> ap(final T1 t1, final T2 t2, final T3 t3) {
        return ap(t1).ap(t2).ap(t3);
    }
    
    public final Function7<T3,T4,T5,T6,T7,T8,T9,R> ap(final T1 t1, final T2 t2) {
        return ap(t1).ap(t2);
    }
    
    public final Function8<T2,T3,T4,T5,T6,T7,T8,T9,R> ap(final T1 t) {
        return new Function8<T2,T3,T4,T5,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }
    
    static final <T1,T2,T3,T4,T5,T6,T7,T8,T9,R> Function9<T1,T2,T3,T4,T5,T6,T7,T8,T9,R> partial(final Apply<? extends Tuple,R> f, final Object... paramsAndPlaceholders) {
        return new Function9<T1,T2,T3,T4,T5,T6,T7,T8,T9,R>() {
            @SuppressWarnings("unchecked")
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return PartialApplicationHelper.doApply((Apply<Tuple,R>)f, paramsAndPlaceholders, t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }
    
    static final <T1,T2,T3,T4,T5,T6,T7,T8,T9,R,FR extends Apply<?,R>> Function9<T1,T2,T3,T4,T5,T6,T7,T8,T9,FR> split(final Apply<? extends Tuple,R> f, final Object... placeholders) {
        return new Function9<T1,T2,T3,T4,T5,T6,T7,T8,T9,FR>() {
            @Override
            public FR apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return PartialApplicationHelper.makeSecondFunc(f, placeholders, t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }
}
