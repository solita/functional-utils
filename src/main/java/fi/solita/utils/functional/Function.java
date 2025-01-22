package fi.solita.utils.functional;

import java.util.Arrays;

public abstract class Function {
    private Function() {
    }

    public static enum GivenLater {
        __
    }
    public static enum GivenEvenLater {
        ___
    }
    public static final GivenLater __ = GivenLater.__;
    public static final GivenEvenLater ___ = GivenEvenLater.___;
    
    /**
     * @return {@code value} as a constant supplier.
     */
    public static final <R> Function0<R> of(final R value) {
        return new Function0<R>() {
            @Override
            public final R apply() {
                return value;
            }
        };
    }

    /**
     * @return a concrete version of {@code apply}.
     */
    public static final <T, R> Function1<T, R> of(final Apply<T, R> apply) {
        return new Function1<T, R>() {
            @Override
            public final R apply(T t) {
                return apply.apply(t);
            }
        };
    }
    
    public static final <T, R> Function1<T, Void> consumer(final ApplyVoid<T> apply) {
        return new Function1<T, Void>() {
            @Override
            public final Void apply(T t) {
                apply.accept(t);
                return null;
            }
        };
    }
    
    /**
     * @return a concrete version of {@code apply}.
     */
    public static final <T1, T2, R> Function2<T1, T2, R> of(final Apply2<T1, T2, R> apply) {
        return new Function2<T1, T2, R>() {
            @Override
            public final R apply(T1 t1, T2 t2) {
                return apply.apply(t1, t2);
            }
        };
    }
    
    public static final <T1, T2, T3, R> Function3<T1, T2, T3, R> of(final Apply3<T1, T2, T3, R> apply) {
        return new Function3<T1, T2, T3, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3) {
                return apply.apply(t1, t2, t3);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, R> Function4<T1, T2, T3, T4, R> of(final Apply4<T1, T2, T3, T4, R> apply) {
        return new Function4<T1, T2, T3, T4, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4) {
                return apply.apply(t1, t2, t3, t4);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, R> Function5<T1, T2, T3, T4, T5, R> of(final Apply5<T1, T2, T3, T4, T5, R> apply) {
        return new Function5<T1, T2, T3, T4, T5, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
                return apply.apply(t1, t2, t3, t4, t5);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, R> Function6<T1, T2, T3, T4, T5, T6, R> of(final Apply6<T1, T2, T3, T4, T5, T6, R> apply) {
        return new Function6<T1, T2, T3, T4, T5, T6, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
                return apply.apply(t1, t2, t3, t4, t5, t6);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, R> Function7<T1, T2, T3, T4, T5, T6, T7, R> of(final Apply7<T1, T2, T3, T4, T5, T6, T7, R> apply) {
        return new Function7<T1, T2, T3, T4, T5, T6, T7, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, R> Function8<T1, T2, T3, T4, T5, T6, T7, T8, R> of(final Apply8<T1, T2, T3, T4, T5, T6, T7, T8, R> apply) {
        return new Function8<T1, T2, T3, T4, T5, T6, T7, T8, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, R> Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> of(final Apply9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R> apply) {
        return new Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> of(final Apply10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R> apply) {
        return new Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R> Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R> of(final Apply11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R> apply) {
        return new Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R> Function12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R> of(final Apply12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R> apply) {
        return new Function12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R> Function13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R> of(final Apply13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R> apply) {
        return new Function13<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R> Function14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R> of(final Apply14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R> apply) {
        return new Function14<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R> Function15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R> of(final Apply15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R> apply) {
        return new Function15<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R> Function16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R> of(final Apply16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R> apply) {
        return new Function16<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R> Function17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R> of(final Apply17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R> apply) {
        return new Function17<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R> Function18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R> of(final Apply18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R> apply) {
        return new Function18<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R> Function19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R> of(final Apply19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R> apply) {
        return new Function19<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R> Function20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R> of(final Apply20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R> apply) {
        return new Function20<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R> Function21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R> of(final Apply21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R> apply) {
        return new Function21<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R> Function22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R> of(final Apply22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R> apply) {
        return new Function22<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, R> Function23<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, R> of(final Apply23<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, R> apply) {
        return new Function23<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, R> Function24<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, R> of(final Apply24<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, R> apply) {
        return new Function24<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, R> Function25<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, R> of(final Apply25<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, R> apply) {
        return new Function25<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, R> Function26<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, R> of(final Apply26<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, R> apply) {
        return new Function26<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, R> Function27<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, R> of(final Apply27<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, R> apply) {
        return new Function27<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, R> Function28<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, R> of(final Apply28<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, R> apply) {
        return new Function28<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, R> Function29<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, R> of(final Apply29<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, R> apply) {
        return new Function29<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, R> Function30<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, R> of(final Apply30<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, R> apply) {
        return new Function30<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, R> Function31<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, R> of(final Apply31<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, R> apply) {
        return new Function31<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, R> Function32<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, R> of(final Apply32<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, R> apply) {
        return new Function32<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, R> Function33<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, R> of(final Apply33<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, R> apply) {
        return new Function33<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, R> Function34<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, R> of(final Apply34<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, R> apply) {
        return new Function34<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, R> Function35<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, R> of(final Apply35<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, R> apply) {
        return new Function35<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34, T35 t35) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34, t35);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, R> Function36<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, R> of(final Apply36<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, R> apply) {
        return new Function36<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34, T35 t35, T36 t36) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34, t35, t36);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, R> Function37<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, R> of(final Apply37<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, R> apply) {
        return new Function37<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34, T35 t35, T36 t36, T37 t37) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34, t35, t36, t37);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, R> Function38<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, R> of(final Apply38<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, R> apply) {
        return new Function38<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34, T35 t35, T36 t36, T37 t37, T38 t38) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34, t35, t36, t37, t38);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, R> Function39<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, R> of(final Apply39<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, R> apply) {
        return new Function39<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34, T35 t35, T36 t36, T37 t37, T38 t38, T39 t39) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34, t35, t36, t37, t38, t39);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, R> Function40<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, R> of(final Apply40<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, R> apply) {
        return new Function40<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34, T35 t35, T36 t36, T37 t37, T38 t38, T39 t39, T40 t40) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34, t35, t36, t37, t38, t39, t40);
            }
        };
    }
    
    public static final <T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, R> Function41<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, R> of(final Apply41<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, R> apply) {
        return new Function41<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, R>() {
            @Override
            public final R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34, T35 t35, T36 t36, T37 t37, T38 t38, T39 t39, T40 t40, T41 t41) {
                return apply.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34, t35, t36, t37, t38, t39, t40, t41);
            }
        };
    }
    
    public static final <T1, T2> Function2<T1, T2, Void> consumer(final ApplyBiVoid<T1, T2> apply) {
        return new Function2<T1, T2, Void>() {
            @Override
            public final Void apply(T1 t1, T2 t2) {
                apply.accept(t1, t2);
                return null;
            }
        };
    }
    
    /**
     * @return a wrapper for {@code supplier}, which memorizes the value thus invoking {@code supplier} only once.
     */
    public static final <R> Function0<R> memoize(final ApplyZero<R> supplier) {
        return new Function0<R>() {
            private R r;
            @Override
            public final R apply() {
                if (r == null) {
                    r = supplier.get();
                }
                return r;
            }
        };
    }

    private static final Function1<?, ?> ID = new Function1<Object, Object>() {
        @Override
        public final Object apply(Object t) {
            return t;
        }
    };

    /**
     * @return identity function.
     */
    @SuppressWarnings("unchecked")
    public static final <T> Function1<T, T> id() {
        return (Function1<T, T>) ID;
    }
    
    /**
     * @return a constant function ignoring its argument and returning always {@code value}.
     */
    public static final <T,R> Function1<T,R> constant(final R value) {
        return new Function1<T, R>() {
            @Override
            public final R apply(T ignored) {
                return value;
            }
        };
    }

    /**
     * @return a function which always throws {@code throwable} on invokation.
     */
    public static final <R, E extends Throwable> Function0.Ex1<R,E> throwing(final E throwable) {
        return new Function0.Ex1<R,E>() {
            @Override
            public final R apply() throws E {
                throw throwable;
            }
        };
    }

    /**
     * @return {@code f} with its arguments flipped.
     */
    public static final <A, B, C> Function1<B, Function1<A, C>> flip(final Apply<A, ? extends Apply<B, C>> f) {
        return new Function1<B, Function1<A, C>>() {
            @Override
            public final Function1<A, C> apply(final B g) {
                return new Function1<A, C>() {
                    @Override
                    public final C apply(A t) {
                        return f.apply(t).apply(g);
                    }
                };
            }
        };
    }

    /**
     * @return 2-parameter version of {@code f}.
     */
    public static final <A, B, C> Function2<A, B, C> uncurried(final Apply<A, ? extends Apply<B, C>> f) {
        return new Function2<A, B, C>() {
            @Override
            public final C apply(A t1, B t2) {
                return f.apply(t1).apply(t2);
            }
        };
    }

    /**
     * @return 3-parameter version of {@code f}.
     */
    public static final <A, B, C, D> Function3<A, B, C, D> uncurried2(final Apply<A, ? extends Apply<B, ? extends Apply<C, D>>> f) {
        return new Function3<A, B, C, D>() {
            @Override
            public final D apply(A t1, B t2, C t3) {
                return f.apply(t1).apply(t2).apply(t3);
            }
        };
    }

    /**
     * @return 4-parameter version of {@code f}.
     */
    public static final <A, B, C, D, E> Function4<A, B, C, D, E> uncurried3(final Apply<A, ? extends Apply<B, ? extends Apply<C, ? extends Apply<D, E>>>> f) {
        return new Function4<A, B, C, D, E>() {
            @Override
            public final E apply(A t1, B t2, C t3, D t4) {
                return f.apply(t1).apply(t2).apply(t3).apply(t4);
            }
        };
    }
    
    /**
     * Partially apply {@code arg1} to {@code f}
     */
    public static final <T1,T2,R> Function1<T2,R> ap(final Apply2<T1,T2,R> f, final T1 arg1) {
        return new Function1<T2,R>() {
            @Override
            public final R apply(T2 arg2) {
                return f.apply(arg1, arg2);
            }
        };
    }
    
    /**
     * Partially apply {@code arg1} to {@code f}
     */
    public static final <T1,T2,T3,R> Function2<T2,T3,R> ap(final Apply3<T1,T2,T3,R> f, final T1 arg1) {
        return new Function2<T2,T3,R>() {
            @Override
            public final R apply(T2 arg2, T3 arg3) {
                return f.apply(arg1, arg2, arg3);
            }
        };
    }
    
    /**
     * Partially apply {@code arg1} and {@code arg2} to {@code f}
     */
    public static final <T1,T2,T3,R> Function1<T3,R> ap(final Apply3<T1,T2,T3,R> f, final T1 arg1, final T2 arg2) {
        return new Function1<T3,R>() {
            @Override
            public final R apply(T3 arg3) {
                return f.apply(arg1, arg2, arg3);
            }
        };
    }
    
    /**
     * Partially apply {@code arg1} to {@code f}
     */
    public static final <T1,T2,T3,T4,R> Function3<T2,T3,T4,R> ap(final Apply4<T1,T2,T3,T4,R> f, final T1 arg1) {
        return new Function3<T2,T3,T4,R>() {
            @Override
            public final R apply(T2 arg2, T3 arg3, T4 arg4) {
                return f.apply(arg1, arg2, arg3, arg4);
            }
        };
    }
    
    /**
     * Partially apply {@code arg1} and {@code arg2} to {@code f}
     */
    public static final <T1,T2,T3,T4,R> Function2<T3,T4,R> ap(final Apply4<T1,T2,T3,T4,R> f, final T1 arg1, final T2 arg2) {
        return new Function2<T3,T4,R>() {
            @Override
            public final R apply(T3 arg3, T4 arg4) {
                return f.apply(arg1, arg2, arg3, arg4);
            }
        };
    }
    
    /**
     * Partially apply {@code arg1}, {@code arg2} and {@code arg3} to {@code f}
     */
    public static final <T1,T2,T3,T4,R> Function1<T4,R> ap(final Apply4<T1,T2,T3,T4,R> f, final T1 arg1, final T2 arg2, final T3 arg3) {
        return new Function1<T4,R>() {
            @Override
            public final R apply(T4 arg4) {
                return f.apply(arg1, arg2, arg3, arg4);
            }
        };
    }
    
    /**
     * Partially apply {@code arg1} to {@code f}
     */
    public static final <T1,T2,T3,T4,T5,R> Function4<T2,T3,T4,T5,R> ap(final Apply5<T1,T2,T3,T4,T5,R> f, final T1 arg1) {
        return new Function4<T2,T3,T4,T5,R>() {
            @Override
            public final R apply(T2 arg2, T3 arg3, T4 arg4, T5 arg5) {
                return f.apply(arg1, arg2, arg3, arg4, arg5);
            }
        };
    }
    
    /**
     * Partially apply {@code arg1} and {@code arg2} to {@code f}
     */
    public static final <T1,T2,T3,T4,T5,R> Function3<T3,T4,T5,R> ap(final Apply5<T1,T2,T3,T4,T5,R> f, final T1 arg1, final T2 arg2) {
        return new Function3<T3,T4,T5,R>() {
            @Override
            public final R apply(T3 arg3, T4 arg4, T5 arg5) {
                return f.apply(arg1, arg2, arg3, arg4, arg5);
            }
        };
    }
    
    /**
     * Partially apply {@code arg1}, {@code arg2} and {@code arg3} to {@code f}
     */
    public static final <T1,T2,T3,T4,T5,R> Function2<T4,T5,R> ap(final Apply5<T1,T2,T3,T4,T5,R> f, final T1 arg1, final T2 arg2, final T3 arg3) {
        return new Function2<T4,T5,R>() {
            @Override
            public final R apply(T4 arg4, T5 arg5) {
                return f.apply(arg1, arg2, arg3, arg4, arg5);
            }
        };
    }
    
    /**
     * Partially apply {@code arg1}, {@code arg2}, {@code arg3} and {@code arg4} to {@code f}
     */
    public static final <T1,T2,T3,T4,T5,R> Function1<T5,R> ap(final Apply5<T1,T2,T3,T4,T5,R> f, final T1 arg1, final T2 arg2, final T3 arg3, final T4 arg4) {
        return new Function1<T5,R>() {
            @Override
            public final R apply(T5 arg5) {
                return f.apply(arg1, arg2, arg3, arg4, arg5);
            }
        };
    }
    
    /**
     * Partially apply {@code arg1} to {@code f}
     */
    public static final <T1,T2,T3,T4,T5,T6,R> Function5<T2,T3,T4,T5,T6,R> ap(final Apply6<T1,T2,T3,T4,T5,T6,R> f, final T1 arg1) {
        return new Function5<T2,T3,T4,T5,T6,R>() {
            @Override
            public final R apply(T2 arg2, T3 arg3, T4 arg4, T5 arg5, T6 arg6) {
                return f.apply(arg1, arg2, arg3, arg4, arg5, arg6);
            }
        };
    }
    
    /**
     * Partially apply {@code arg1} and {@code arg2} to {@code f}
     */
    public static final <T1,T2,T3,T4,T5,T6,R> Function4<T3,T4,T5,T6,R> ap(final Apply6<T1,T2,T3,T4,T5,T6,R> f, final T1 arg1, final T2 arg2) {
        return new Function4<T3,T4,T5,T6,R>() {
            @Override
            public final R apply(T3 arg3, T4 arg4, T5 arg5, T6 arg6) {
                return f.apply(arg1, arg2, arg3, arg4, arg5, arg6);
            }
        };
    }
    
    /**
     * Partially apply {@code arg1}, {@code arg2} and {@code arg3} to {@code f}
     */
    public static final <T1,T2,T3,T4,T5,T6,R> Function3<T4,T5,T6,R> ap(final Apply6<T1,T2,T3,T4,T5,T6,R> f, final T1 arg1, final T2 arg2, final T3 arg3) {
        return new Function3<T4,T5,T6,R>() {
            @Override
            public final R apply(T4 arg4, T5 arg5, T6 arg6) {
                return f.apply(arg1, arg2, arg3, arg4, arg5, arg6);
            }
        };
    }
    
    /**
     * Partially apply {@code arg1}, {@code arg2}, {@code arg3} and {@code arg4} to {@code f}
     */
    public static final <T1,T2,T3,T4,T5,T6,R> Function2<T5,T6,R> ap(final Apply6<T1,T2,T3,T4,T5,T6,R> f, final T1 arg1, final T2 arg2, final T3 arg3, final T4 arg4) {
        return new Function2<T5,T6,R>() {
            @Override
            public final R apply(T5 arg5, T6 arg6) {
                return f.apply(arg1, arg2, arg3, arg4, arg5, arg6);
            }
        };
    }
    
    /**
     * Partially apply {@code arg1}, {@code arg2}, {@code arg3}, {@code arg4} and {@code arg5} to {@code f}
     */
    public static final <T1,T2,T3,T4,T5,T6,R> Function1<T6,R> ap(final Apply6<T1,T2,T3,T4,T5,T6,R> f, final T1 arg1, final T2 arg2, final T3 arg3, final T4 arg4, final T5 arg5) {
        return new Function1<T6,R>() {
            @Override
            public final R apply(T6 arg6) {
                return f.apply(arg1, arg2, arg3, arg4, arg5, arg6);
            }
        };
    }
}

class PartialApplicationHelper {
    static final Object[] replacePlaceholdersWithParams(Object[] paramsAndPlaceholders, Object... actualParamsForPlaceholders) {
        Object[] ret = Arrays.copyOf(paramsAndPlaceholders, paramsAndPlaceholders.length);
        int t = 0;
        for (int i = 0; i < ret.length; ++i) {
            if (ret[i] == Function.__) {
                ret[i] = actualParamsForPlaceholders[t];
                t++;
                if (t == actualParamsForPlaceholders.length) {
                    break;
                }
            }
        }
        return ret;
    }
    
    static final <R> R doApply(Apply<Tuple,R> f, Object[] paramsAndPlaceholders, Object... params) {
        return f.apply(Tuple.of(replacePlaceholdersWithParams(paramsAndPlaceholders, params)));
    }
    
    private static final Tuple replacePlaceholders(Object[] args, Object[] t, Object... p) {
        Object[] ret = new Object[args.length];
        int ti = 0;
        int pi = 0;
        for (int i = 0; i < ret.length; ++i) {
            if (args[i] == Function.__) {
                ret[i] = t[ti];
                ti++;
            } else {
                ret[i] = p[pi];
                pi++;
            }
        }
        return Tuple.of(ret);
    }
    
    @SuppressWarnings("unchecked")
    static final <R,FR extends Apply<?,R>> FR makeSecondFunc(final Apply<? extends Tuple,R> f, final Object[] args, final Object... t) {
        final Apply<Tuple,R> ff = ((Apply<Tuple,R>)f);
        switch (args.length - t.length) {
            case 1: return (FR) new Function1<Object,R>() {
                @Override
                public final R apply(Object p1) {
                    return ff.apply(replacePlaceholders(args, t, p1));
                }
            };
            case 2: return (FR) new Function2<Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2));
                }
            };
            case 3: return (FR) new Function3<Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3));
                }
            };
            case 4: return (FR) new Function4<Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4));
                }
            };
            case 5: return (FR) new Function5<Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5));
                }
            };
            case 6: return (FR) new Function6<Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6));
                }
            };
            case 7: return (FR) new Function7<Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7));
                }
            };
            case 8: return (FR) new Function8<Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8));
                }
            };
            case 9: return (FR) new Function9<Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9));
                }
            };
            case 10: return (FR) new Function10<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));
                }
            };
            case 11: return (FR) new Function11<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
                }
            };
            case 12: return (FR) new Function12<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12));
                }
            };
            case 13: return (FR) new Function13<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13));
                }
            };
            case 14: return (FR) new Function14<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14));
                }
            };
            case 15: return (FR) new Function15<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15));
                }
            };
            case 16: return (FR) new Function16<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16));
                }
            };
            case 17: return (FR) new Function17<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17));
                }
            };
            case 18: return (FR) new Function18<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18));
                }
            };
            case 19: return (FR) new Function19<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19));
                }
            };
            case 20: return (FR) new Function20<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20));
                }
            };
            case 21: return (FR) new Function21<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21));
                }
            };
            case 22: return (FR) new Function22<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22));
                }
            };
            case 23: return (FR) new Function23<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23));
                }
            };
            case 24: return (FR) new Function24<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23, Object p24) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24));
                }
            };
            case 25: return (FR) new Function25<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23, Object p24, Object p25) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25));
                }
            };
            case 26: return (FR) new Function26<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23, Object p24, Object p25, Object p26) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26));
                }
            };
            case 27: return (FR) new Function27<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23, Object p24, Object p25, Object p26, Object p27) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27));
                }
            };
            case 28: return (FR) new Function28<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23, Object p24, Object p25, Object p26, Object p27, Object p28) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28));
                }
            };
            case 29: return (FR) new Function29<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23, Object p24, Object p25, Object p26, Object p27, Object p28, Object p29) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29));
                }
            };
            case 30: return (FR) new Function30<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23, Object p24, Object p25, Object p26, Object p27, Object p28, Object p29, Object p30) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30));
                }
            };
            case 31: return (FR) new Function31<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23, Object p24, Object p25, Object p26, Object p27, Object p28, Object p29, Object p30, Object p31) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31));
                }
            };
            case 32: return (FR) new Function32<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23, Object p24, Object p25, Object p26, Object p27, Object p28, Object p29, Object p30, Object p31, Object p32) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32));
                }
            };
            case 33: return (FR) new Function33<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23, Object p24, Object p25, Object p26, Object p27, Object p28, Object p29, Object p30, Object p31, Object p32, Object p33) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33));
                }
            };
            case 34: return (FR) new Function34<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23, Object p24, Object p25, Object p26, Object p27, Object p28, Object p29, Object p30, Object p31, Object p32, Object p33, Object p34) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34));
                }
            };
            case 35: return (FR) new Function35<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23, Object p24, Object p25, Object p26, Object p27, Object p28, Object p29, Object p30, Object p31, Object p32, Object p33, Object p34, Object p35) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35));
                }
            };
            case 36: return (FR) new Function36<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23, Object p24, Object p25, Object p26, Object p27, Object p28, Object p29, Object p30, Object p31, Object p32, Object p33, Object p34, Object p35, Object p36) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36));
                }
            };
            case 37: return (FR) new Function37<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23, Object p24, Object p25, Object p26, Object p27, Object p28, Object p29, Object p30, Object p31, Object p32, Object p33, Object p34, Object p35, Object p36, Object p37) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37));
                }
            };
            case 38: return (FR) new Function38<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23, Object p24, Object p25, Object p26, Object p27, Object p28, Object p29, Object p30, Object p31, Object p32, Object p33, Object p34, Object p35, Object p36, Object p37, Object p38) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38));
                }
            };
            case 39: return (FR) new Function39<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23, Object p24, Object p25, Object p26, Object p27, Object p28, Object p29, Object p30, Object p31, Object p32, Object p33, Object p34, Object p35, Object p36, Object p37, Object p38, Object p39) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39));
                }
            };
            case 40: return (FR) new Function40<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23, Object p24, Object p25, Object p26, Object p27, Object p28, Object p29, Object p30, Object p31, Object p32, Object p33, Object p34, Object p35, Object p36, Object p37, Object p38, Object p39, Object p40) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40));
                }
            };
            case 41: return (FR) new Function41<Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,Object,R>() {
                @Override
                public final R apply(Object p1, Object p2, Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9, Object p10, Object p11, Object p12, Object p13, Object p14, Object p15, Object p16, Object p17, Object p18, Object p19, Object p20, Object p21, Object p22, Object p23, Object p24, Object p25, Object p26, Object p27, Object p28, Object p29, Object p30, Object p31, Object p32, Object p33, Object p34, Object p35, Object p36, Object p37, Object p38, Object p39, Object p40, Object p41) {
                    return ff.apply(replacePlaceholders(args, t, p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20, p21, p22, p23, p24, p25, p26, p27, p28, p29, p30, p31, p32, p33, p34, p35, p36, p37, p38, p39, p40, p41));
                }
            };
        }
        throw new UnsupportedOperationException("Not implemented");
    }
}
