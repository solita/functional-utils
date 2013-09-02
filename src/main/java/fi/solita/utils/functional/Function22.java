package fi.solita.utils.functional;

public abstract class Function22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R> extends MultiParamFunction<Tuple22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22>, R> {

    public abstract R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22);

    public final <U> Function22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, U> andThen(final Apply<? super R, ? extends U> next) {
        final Function22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R> self = this;
        return new Function22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, U>() {
            @Override
            public U apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22) {
                return next.apply(self.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22));
            }
        };
    }

    public final Function1<Tuple22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22>, R> tuppled() {
        return new Function1<Tuple22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22>, R>() {
            @Override
            public R apply(Tuple22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22> t) {
                return Function22.this.apply(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22);
            }
        };
    }
    
    public Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, Function1<T12, Function1<T13, Function1<T14, Function1<T15, Function1<T16, Function1<T17, Function1<T18, Function1<T19, Function1<T20, Function1<T21, Function1<T22, R>>>>>>>>>>>>>>>>>>>>>> curried() {
        return new Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, Function1<T12, Function1<T13, Function1<T14, Function1<T15, Function1<T16, Function1<T17, Function1<T18, Function1<T19, Function1<T20, Function1<T21, Function1<T22, R>>>>>>>>>>>>>>>>>>>>>>() {
            @Override
            public Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, Function1<T12, Function1<T13, Function1<T14, Function1<T15, Function1<T16, Function1<T17, Function1<T18, Function1<T19, Function1<T20, Function1<T21, Function1<T22, R>>>>>>>>>>>>>>>>>>>>> apply(final T1 t1) {
                return new Function21<T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R>() {
                    @Override
                    public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22) {
                        return Function22.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22);
                    }
                }.curried();
            }
        };
    }
    
    public final Function1<T22,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21);
    }
    
    public final Function2<T21,T22,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20);
    }
    
    public final Function3<T20,T21,T22,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19);
    }
    
    public final Function4<T19,T20,T21,T22,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18);
    }
    
    public final Function5<T18,T19,T20,T21,T22,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17);
    }
    
    public final Function6<T17,T18,T19,T20,T21,T22,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16);
    }
    
    public final Function7<T16,T17,T18,T19,T20,T21,T22,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15);
    }
    
    public final Function8<T15,T16,T17,T18,T19,T20,T21,T22,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14);
    }
    
    public final Function9<T14,T15,T16,T17,T18,T19,T20,T21,T22,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13);
    }
    
    public final Function10<T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12);
    }
    
    public final Function11<T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11);
    }
   
    public final Function12<T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10);
    }
    
    public final Function13<T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9);
    }
    
    public final Function14<T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8);
    }
    
    public final Function15<T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7);
    }
    
    public final Function16<T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6);
    }
    
    public final Function17<T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5);
    }
    
    public final Function18<T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4) {
        return ap(t1).ap(t2).ap(t3).ap(t4);
    }
    
    public final Function19<T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> ap(final T1 t1, final T2 t2, final T3 t3) {
        return ap(t1).ap(t2).ap(t3);
    }
    
    public final Function20<T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> ap(final T1 t1, final T2 t2) {
        return ap(t1).ap(t2);
    }
    
    public final Function21<T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R> ap(final T1 t) {
        return new Function21<T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22) {
                return Function22.this.apply(t, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22);
            }
        };
    }
}
