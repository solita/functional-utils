package fi.solita.utils.functional;

public abstract class Function43<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43, R> extends MultiParamFunction<Tuple43<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5, ? extends T6, ? extends T7, ? extends T8, ? extends T9, ? extends T10, ? extends T11, ? extends T12, ? extends T13, ? extends T14, ? extends T15, ? extends T16, ? extends T17, ? extends T18, ? extends T19, ? extends T20, ? extends T21, ? extends T22, ? extends T23, ? extends T24, ? extends T25, ? extends T26, ? extends T27, ? extends T28, ? extends T29, ? extends T30, ? extends T31, ? extends T32, ? extends T33, ? extends T34, ? extends T35, ? extends T36, ? extends T37, ? extends T38, ? extends T39, ? extends T40, ? extends T41, ? extends T42, ? extends T43>, R, T1> {

    public abstract R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34, T35 t35, T36 t36, T37 t37, T38 t38, T39 t39, T40 t40, T41 t41, T42 t42, T43 t43);

    @Override
    public final <U> Function43<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43, U> andThen(final Apply<? super R, ? extends U> next) {
        final Function43<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43, R> self = this;
        return new Function43<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43, U>() {
            @Override
            public U apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34, T35 t35, T36 t36, T37 t37, T38 t38, T39 t39, T40 t40, T41 t41, T42 t42, T43 t43) {
                return next.apply(self.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34, t35, t36, t37, t38, t39, t40, t41, t42, t43));
            }
        };
    }

    @Override
    public final Function1<Tuple43<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5, ? extends T6, ? extends T7, ? extends T8, ? extends T9, ? extends T10, ? extends T11, ? extends T12, ? extends T13, ? extends T14, ? extends T15, ? extends T16, ? extends T17, ? extends T18, ? extends T19, ? extends T20, ? extends T21, ? extends T22, ? extends T23, ? extends T24, ? extends T25, ? extends T26, ? extends T27, ? extends T28, ? extends T29, ? extends T30, ? extends T31, ? extends T32, ? extends T33, ? extends T34, ? extends T35, ? extends T36, ? extends T37, ? extends T38, ? extends T39, ? extends T40, ? extends T41, ? extends T42, ? extends T43>, R> tuppled() {
        return new Function1<Tuple43<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5, ? extends T6, ? extends T7, ? extends T8, ? extends T9, ? extends T10, ? extends T11, ? extends T12, ? extends T13, ? extends T14, ? extends T15, ? extends T16, ? extends T17, ? extends T18, ? extends T19, ? extends T20, ? extends T21, ? extends T22, ? extends T23, ? extends T24, ? extends T25, ? extends T26, ? extends T27, ? extends T28, ? extends T29, ? extends T30, ? extends T31, ? extends T32, ? extends T33, ? extends T34, ? extends T35, ? extends T36, ? extends T37, ? extends T38, ? extends T39, ? extends T40, ? extends T41, ? extends T42, ? extends T43>, R>() {
            @Override
            public R apply(Tuple43<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5, ? extends T6, ? extends T7, ? extends T8, ? extends T9, ? extends T10, ? extends T11, ? extends T12, ? extends T13, ? extends T14, ? extends T15, ? extends T16, ? extends T17, ? extends T18, ? extends T19, ? extends T20, ? extends T21, ? extends T22, ? extends T23, ? extends T24, ? extends T25, ? extends T26, ? extends T27, ? extends T28, ? extends T29, ? extends T30, ? extends T31, ? extends T32, ? extends T33, ? extends T34, ? extends T35, ? extends T36, ? extends T37, ? extends T38, ? extends T39, ? extends T40, ? extends T41, ? extends T42, ? extends T43> t) {
                return Function43.this.apply(t._1, t._2, t._3, t._4, t._5, t._6, t._7, t._8, t._9, t._10, t._11, t._12, t._13, t._14, t._15, t._16, t._17, t._18, t._19, t._20, t._21, t._22, t._23, t._24, t._25, t._26, t._27, t._28, t._29, t._30, t._31, t._32, t._33, t._34, t._35, t._36, t._37, t._38, t._39, t._40, t._41, t._42, t._43);
            }
        };
    }
    
    @Override
    public Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, Function1<T12, Function1<T13, Function1<T14, Function1<T15, Function1<T16, Function1<T17, Function1<T18, Function1<T19, Function1<T20, Function1<T21, Function1<T22, Function1<T23, Function1<T24, Function1<T25, Function1<T26, Function1<T27, Function1<T28, Function1<T29, Function1<T30, Function1<T31, Function1<T32, Function1<T33, Function1<T34, Function1<T35, Function1<T36, Function1<T37, Function1<T38, Function1<T39, Function1<T40, Function1<T41, Function1<T42, Function1<T43, R>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> curried() {
        return new Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, Function1<T12, Function1<T13, Function1<T14, Function1<T15, Function1<T16, Function1<T17, Function1<T18, Function1<T19, Function1<T20, Function1<T21, Function1<T22, Function1<T23, Function1<T24, Function1<T25, Function1<T26, Function1<T27, Function1<T28, Function1<T29, Function1<T30, Function1<T31, Function1<T32, Function1<T33, Function1<T34, Function1<T35, Function1<T36, Function1<T37, Function1<T38, Function1<T39, Function1<T40, Function1<T41, Function1<T42, Function1<T43, R>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>() {
            @Override
            public Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, Function1<T7, Function1<T8, Function1<T9, Function1<T10, Function1<T11, Function1<T12, Function1<T13, Function1<T14, Function1<T15, Function1<T16, Function1<T17, Function1<T18, Function1<T19, Function1<T20, Function1<T21, Function1<T22, Function1<T23, Function1<T24, Function1<T25, Function1<T26, Function1<T27, Function1<T28, Function1<T29, Function1<T30, Function1<T31, Function1<T32, Function1<T33, Function1<T34, Function1<T35, Function1<T36, Function1<T37, Function1<T38, Function1<T39, Function1<T40, Function1<T41, Function1<T42, Function1<T43, R>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> apply(final T1 t1) {
                return new Function42<T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, T14, T15, T16, T17, T18, T19, T20, T21, T22, T23, T24, T25, T26, T27, T28, T29, T30, T31, T32, T33, T34, T35, T36, T37, T38, T39, T40, T41, T42, T43, R>() {
                    @Override
                    public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34, T35 t35, T36 t36, T37 t37, T38 t38, T39 t39, T40 t40, T41 t41, T42 t42, T43 t43) {
                        return Function43.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34, t35, t36, t37, t38, t39, t40, t41, t42, t43);
                    }
                }.curried();
            }
        };
    }

    public final Function1<T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34, T35 t35, T36 t36, T37 t37, T38 t38, T39 t39, T40 t40, T41 t41, T42 t42) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21).ap(t22).ap(t23).ap(t24).ap(t25).ap(t26).ap(t27).ap(t28).ap(t29).ap(t30).ap(t31).ap(t32).ap(t33).ap(t34).ap(t35).ap(t36).ap(t37).ap(t38).ap(t39).ap(t40).ap(t41).ap(t42);
    }

    public final Function2<T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34, T35 t35, T36 t36, T37 t37, T38 t38, T39 t39, T40 t40, T41 t41) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21).ap(t22).ap(t23).ap(t24).ap(t25).ap(t26).ap(t27).ap(t28).ap(t29).ap(t30).ap(t31).ap(t32).ap(t33).ap(t34).ap(t35).ap(t36).ap(t37).ap(t38).ap(t39).ap(t40).ap(t41);
    }
    
    public final Function3<T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34, T35 t35, T36 t36, T37 t37, T38 t38, T39 t39, T40 t40) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21).ap(t22).ap(t23).ap(t24).ap(t25).ap(t26).ap(t27).ap(t28).ap(t29).ap(t30).ap(t31).ap(t32).ap(t33).ap(t34).ap(t35).ap(t36).ap(t37).ap(t38).ap(t39).ap(t40);
    }
    
    public final Function4<T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34, T35 t35, T36 t36, T37 t37, T38 t38, T39 t39) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21).ap(t22).ap(t23).ap(t24).ap(t25).ap(t26).ap(t27).ap(t28).ap(t29).ap(t30).ap(t31).ap(t32).ap(t33).ap(t34).ap(t35).ap(t36).ap(t37).ap(t38).ap(t39);
    }
    
    public final Function5<T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34, T35 t35, T36 t36, T37 t37, T38 t38) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21).ap(t22).ap(t23).ap(t24).ap(t25).ap(t26).ap(t27).ap(t28).ap(t29).ap(t30).ap(t31).ap(t32).ap(t33).ap(t34).ap(t35).ap(t36).ap(t37).ap(t38);
    }
    
    public final Function6<T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34, T35 t35, T36 t36, T37 t37) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21).ap(t22).ap(t23).ap(t24).ap(t25).ap(t26).ap(t27).ap(t28).ap(t29).ap(t30).ap(t31).ap(t32).ap(t33).ap(t34).ap(t35).ap(t36).ap(t37);
    }
    
    public final Function7<T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34, T35 t35, T36 t36) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21).ap(t22).ap(t23).ap(t24).ap(t25).ap(t26).ap(t27).ap(t28).ap(t29).ap(t30).ap(t31).ap(t32).ap(t33).ap(t34).ap(t35).ap(t36);
    }

    public final Function8<T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34, T35 t35) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21).ap(t22).ap(t23).ap(t24).ap(t25).ap(t26).ap(t27).ap(t28).ap(t29).ap(t30).ap(t31).ap(t32).ap(t33).ap(t34).ap(t35);
    }
    
    public final Function9<T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21).ap(t22).ap(t23).ap(t24).ap(t25).ap(t26).ap(t27).ap(t28).ap(t29).ap(t30).ap(t31).ap(t32).ap(t33).ap(t34);
    }
    
    public final Function10<T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21).ap(t22).ap(t23).ap(t24).ap(t25).ap(t26).ap(t27).ap(t28).ap(t29).ap(t30).ap(t31).ap(t32).ap(t33);
    }
    
    public final Function11<T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21).ap(t22).ap(t23).ap(t24).ap(t25).ap(t26).ap(t27).ap(t28).ap(t29).ap(t30).ap(t31).ap(t32);
    }
    
    public final Function12<T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21).ap(t22).ap(t23).ap(t24).ap(t25).ap(t26).ap(t27).ap(t28).ap(t29).ap(t30).ap(t31);
    }
    
    public final Function13<T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21).ap(t22).ap(t23).ap(t24).ap(t25).ap(t26).ap(t27).ap(t28).ap(t29).ap(t30);
    }
    
    public final Function14<T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21).ap(t22).ap(t23).ap(t24).ap(t25).ap(t26).ap(t27).ap(t28).ap(t29);
    }
    
    public final Function15<T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21).ap(t22).ap(t23).ap(t24).ap(t25).ap(t26).ap(t27).ap(t28);
    }
    
    public final Function16<T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21).ap(t22).ap(t23).ap(t24).ap(t25).ap(t26).ap(t27);
    }
    
    public final Function17<T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21).ap(t22).ap(t23).ap(t24).ap(t25).ap(t26);
    }
    
    public final Function18<T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21).ap(t22).ap(t23).ap(t24).ap(t25);
    }
    
    public final Function19<T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21).ap(t22).ap(t23).ap(t24);
    }
    
    public final Function20<T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21).ap(t22).ap(t23);
    }
    
    public final Function21<T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21).ap(t22);
    }
    
    public final Function22<T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20).ap(t21);
    }
    
    public final Function23<T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19).ap(t20);
    }
    
    public final Function24<T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18).ap(t19);
    }
    
    public final Function25<T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17).ap(t18);
    }
    
    public final Function26<T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16, T17 t17) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16).ap(t17);
    }
    
    public final Function27<T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15, T16 t16) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15).ap(t16);
    }
    
    public final Function28<T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14, T15 t15) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14).ap(t15);
    }
    
    public final Function29<T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13, T14 t14) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13).ap(t14);
    }
    
    public final Function30<T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12, final T13 t13) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12).ap(t13);
    }
   
    public final Function31<T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11, final T12 t12) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11).ap(t12);
    }
    
    public final Function32<T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10, final T11 t11) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10).ap(t11);
    }
   
    public final Function33<T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9, final T10 t10) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9).ap(t10);
    }
    
    public final Function34<T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8).ap(t9);
    }
    
    public final Function35<T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7).ap(t8);
    }
    
    public final Function36<T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6).ap(t7);
    }
    
    public final Function37<T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5).ap(t6);
    }
    
    public final Function38<T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5);
    }
    
    public final Function39<T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4) {
        return ap(t1).ap(t2).ap(t3).ap(t4);
    }
    
    public final Function40<T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2, final T3 t3) {
        return ap(t1).ap(t2).ap(t3);
    }
    
    public final Function41<T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t1, final T2 t2) {
        return ap(t1).ap(t2);
    }
    
    @Override
    public final Function42<T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> ap(final T1 t) {
        return new Function42<T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34, T35 t35, T36 t36, T37 t37, T38 t38, T39 t39, T40 t40, T41 t41, T42 t42, T43 t43) {
                return Function43.this.apply(t, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34, t35, t36, t37, t38, t39, t40, t41, t42, t43);
            }
        };
    }
    
    static final <T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> Function43<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R> partial(final Apply<? extends Tuple,R> f, final Object... paramsAndPlaceholders) {
        return new Function43<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R>() {
            @SuppressWarnings("unchecked")
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34, T35 t35, T36 t36, T37 t37, T38 t38, T39 t39, T40 t40, T41 t41, T42 t42, T43 t43) {
                return PartialApplicationHelper.doApply((Apply<Tuple,R>)f, paramsAndPlaceholders, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34, t35, t36, t37, t38, t39, t40, t41, t42, t43);
            }
        };
    }
    
    static final <T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,R,FR extends Apply<?,R>> Function43<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,FR> split(final Apply<? extends Tuple,R> f, final Object... placeholders) {
        return new Function43<T1,T2,T3,T4,T5,T6,T7,T8,T9,T10,T11,T12,T13,T14,T15,T16,T17,T18,T19,T20,T21,T22,T23,T24,T25,T26,T27,T28,T29,T30,T31,T32,T33,T34,T35,T36,T37,T38,T39,T40,T41,T42,T43,FR>() {
            @Override
            public FR apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9, T10 t10, T11 t11, T12 t12, T13 t13, T14 t14, T15 t15, T16 t16, T17 t17, T18 t18, T19 t19, T20 t20, T21 t21, T22 t22, T23 t23, T24 t24, T25 t25, T26 t26, T27 t27, T28 t28, T29 t29, T30 t30, T31 t31, T32 t32, T33 t33, T34 t34, T35 t35, T36 t36, T37 t37, T38 t38, T39 t39, T40 t40, T41 t41, T42 t42, T43 t43) {
                return PartialApplicationHelper.makeSecondFunc(f, placeholders, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t24, t25, t26, t27, t28, t29, t30, t31, t32, t33, t34, t35, t36, t37, t38, t39, t40, t41, t42, t43);
            }
        };
    }
}
