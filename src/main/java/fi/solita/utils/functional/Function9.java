package fi.solita.utils.functional;

import fi.solita.utils.codegen.NoMetadataGeneration;
import fi.solita.utils.functional.Function.GivenLater;

@NoMetadataGeneration
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
    
    public final Function5<T3,T4,T5,T7,T8,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function5<T3,T4,T5,T7,T8,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T5 t5, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function8<T2,T3,T4,T5,T6,T7,T8,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function8<T2,T3,T4,T5,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T3,T4,T5,T6,T7,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function5<T3,T4,T5,T6,T7,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T2,T8,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function4<T1,T2,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T4,T5,T6,T8,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function5<T4,T5,T6,T8,T9,R>() {
            @Override
            public R apply(T4 t4, T5 t5, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T3,T4,T6,T8,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function5<T3,T4,T6,T8,T9,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T3,T6,T7,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function3<T3,T6,T7,R>() {
            @Override
            public R apply(T3 t3, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T4,T5,T8,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function5<T1,T2,T4,T5,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T5 t5, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T8,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function3<T1,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T3,T4,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function3<T3,T4,T9,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T3,T6,T7,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function6<T1,T2,T3,T6,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T2,T4,T5,T6,T7,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function6<T2,T4,T5,T6,T7,T9,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T5 t5, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T4,T6,T7,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function5<T2,T4,T6,T7,T9,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T3,T5,T7,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function4<T2,T3,T5,T7,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T5 t5, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T4,T6,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function5<T1,T2,T4,T6,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T3,T5,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function4<T2,T3,T5,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T5 t5, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T5,T6,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function2<T5,T6,R>() {
            @Override
            public R apply(T5 t5, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function1<T4,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function1<T4,R>() {
            @Override
            public R apply(T4 t4) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T4,T7,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function3<T1,T4,T7,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T4,T7,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function4<T1,T4,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T5,T6,T7,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function5<T1,T2,T5,T6,T7,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T5 t5, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T3,T5,T6,T7,T8,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function6<T1,T3,T5,T6,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T5 t5, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T5,T7,T8,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function6<T1,T2,T5,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T5 t5, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T3,T4,T6,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function3<T3,T4,T6,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T4,T5,T6,T7,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function4<T4,T5,T6,T7,R>() {
            @Override
            public R apply(T4 t4, T5 t5, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T3,T4,T5,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function4<T1,T3,T4,T5,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T5 t5) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T3,T5,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function5<T1,T2,T3,T5,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T5 t5, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T3,T5,T6,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function5<T1,T3,T5,T6,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T5 t5, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T3,T8,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function5<T1,T2,T3,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T3,T4,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function3<T1,T3,T4,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T3,T4,T5,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function5<T2,T3,T4,T5,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T4,T8,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function3<T4,T8,T9,R>() {
            @Override
            public R apply(T4 t4, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T4,T6,T7,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function5<T1,T2,T4,T6,T7,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T1,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function2<T1,T9,R>() {
            @Override
            public R apply(T1 t1, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T3,T6,T8,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function5<T1,T3,T6,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T2,T5,T7,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function4<T1,T2,T5,T7,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T5 t5, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T7,T8,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function2<T7,T8,R>() {
            @Override
            public R apply(T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T4,T5,T7,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function4<T1,T4,T5,T7,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T5 t5, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function1<T2,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function1<T2,R>() {
            @Override
            public R apply(T2 t2) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T3,T5,T8,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function5<T1,T3,T5,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T5 t5, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T3,T4,T5,T6,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function4<T3,T4,T5,T6,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T5 t5, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T8,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function2<T8,T9,R>() {
            @Override
            public R apply(T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T2,T3,T4,T6,T8,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function6<T2,T3,T4,T6,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T4,T6,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function3<T1,T4,T6,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T2,T4,T5,T6,T7,T8,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function6<T2,T4,T5,T6,T7,T8,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T2,T3,T4,T5,T6,T8,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function7<T2,T3,T4,T5,T6,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T3,T5,T8,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function4<T2,T3,T5,T8,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T5 t5, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T4,T5,T6,T7,T8,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function7<T1,T4,T5,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T3,T5,T6,T7,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function6<T1,T2,T3,T5,T6,T7,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T5 t5, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T3,T8,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function3<T3,T8,T9,R>() {
            @Override
            public R apply(T3 t3, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T2,T4,T6,T7,T8,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function6<T2,T4,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T4,T6,T8,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function5<T1,T2,T4,T6,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T3,T4,T5,T8,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function6<T1,T2,T3,T4,T5,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T4,T5,T6,T7,T8,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function5<T4,T5,T6,T7,T8,R>() {
            @Override
            public R apply(T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T6,T7,T8,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function5<T1,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T4,T5,T6,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function4<T2,T4,T5,T6,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T5 t5, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T2,T8,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function3<T2,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T2,T5,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function4<T1,T2,T5,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T5 t5, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T3,T4,T5,T7,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function4<T3,T4,T5,T7,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T5 t5, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T3,T5,T7,T8,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function5<T3,T5,T7,T8,T9,R>() {
            @Override
            public R apply(T3 t3, T5 t5, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T3,T4,T6,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function6<T1,T2,T3,T4,T6,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T2,T3,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function4<T1,T2,T3,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T4,T5,T8,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function3<T4,T5,T8,R>() {
            @Override
            public R apply(T4 t4, T5 t5, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T3,T5,T6,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function6<T1,T2,T3,T5,T6,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T5 t5, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T5,T6,T7,T8,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function5<T5,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T2,T3,T5,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function4<T1,T2,T3,T5,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T5 t5) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T5,T8,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function4<T2,T5,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T5 t5, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T4,T5,T6,T8,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function5<T1,T4,T5,T6,T8,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T5 t5, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T2,T3,T4,T5,T6,T7,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function7<T1,T2,T3,T4,T5,T6,T7,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T3,T4,T5,T7,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function5<T3,T4,T5,T7,T9,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T5 t5, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T2,T3,T5,T6,T7,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function6<T2,T3,T5,T6,T7,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T5 t5, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T5,T6,T7,T8,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function5<T2,T5,T6,T7,T8,R>() {
            @Override
            public R apply(T2 t2, T5 t5, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T2,T3,T6,T7,T8,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function6<T2,T3,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T3,T7,T8,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function6<T1,T2,T3,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T3,T6,T8,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function4<T1,T3,T6,T8,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T3,T7,T8,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function4<T3,T7,T8,T9,R>() {
            @Override
            public R apply(T3 t3, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T5,T8,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function4<T1,T5,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T5 t5, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function8<T1,T2,T3,T4,T5,T6,T7,T8,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function8<T1,T2,T3,T4,T5,T6,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T2,T6,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function2<T2,T6,R>() {
            @Override
            public R apply(T2 t2, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T3,T8,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function2<T3,T8,R>() {
            @Override
            public R apply(T3 t3, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T2,T3,T5,T6,T8,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function7<T1,T2,T3,T5,T6,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T5 t5, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T5,T7,T8,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function5<T1,T5,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T5 t5, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T4,T7,T8,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function6<T1,T2,T4,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T3,T8,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function3<T1,T3,T8,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T5,T7,T8,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function4<T1,T5,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T5 t5, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T3,T4,T7,T8,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function5<T1,T3,T4,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T3,T4,T7,T8,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function6<T1,T2,T3,T4,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T3,T4,T6,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function5<T1,T3,T4,T6,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T2,T6,T7,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function4<T1,T2,T6,T7,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T3,T4,T8,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function5<T1,T3,T4,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T5,T6,T7,T8,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function5<T1,T5,T6,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T5 t5, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T3,T5,T7,T8,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function5<T2,T3,T5,T7,T8,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T5 t5, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T3,T4,T5,T8,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function5<T1,T3,T4,T5,T8,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T5 t5, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T7,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function2<T7,T9,R>() {
            @Override
            public R apply(T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T5,T7,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function4<T1,T5,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T5 t5, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T5,T7,T8,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function4<T5,T7,T8,T9,R>() {
            @Override
            public R apply(T5 t5, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T3,T4,T5,T6,T8,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function6<T3,T4,T5,T6,T8,T9,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T5 t5, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T5,T6,T7,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function4<T1,T5,T6,T7,R>() {
            @Override
            public R apply(T1 t1, T5 t5, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T3,T5,T6,T7,T8,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function6<T3,T5,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T3 t3, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T2,T8,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function2<T2,T8,R>() {
            @Override
            public R apply(T2 t2, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T4,T5,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function3<T1,T4,T5,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T5 t5) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T4,T5,T6,T7,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function5<T1,T4,T5,T6,T7,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T5 t5, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T5,T6,T8,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function6<T1,T2,T5,T6,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T5 t5, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T3,T7,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function5<T1,T2,T3,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T3,T5,T6,T7,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function6<T1,T3,T5,T6,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T5 t5, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T4,T5,T7,T8,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function5<T2,T4,T5,T7,T8,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T5 t5, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T3,T4,T5,T7,T8,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function6<T3,T4,T5,T7,T8,T9,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T5 t5, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T4,T6,T7,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function6<T1,T2,T4,T6,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T2,T7,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function2<T2,T7,R>() {
            @Override
            public R apply(T2 t2, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T2,T3,T4,T5,T6,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function6<T2,T3,T4,T5,T6,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T3,T6,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function4<T2,T3,T6,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T4,T5,T8,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function5<T2,T4,T5,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T5 t5, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T3,T4,T8,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function4<T2,T3,T4,T8,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T3,T5,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function3<T3,T5,T9,R>() {
            @Override
            public R apply(T3 t3, T5 t5, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T3,T7,T8,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function5<T1,T2,T3,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T2,T3,T4,T5,T6,T7,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function6<T2,T3,T4,T5,T6,T7,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T5,T6,T7,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function4<T5,T6,T7,T9,R>() {
            @Override
            public R apply(T5 t5, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T3,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function2<T3,T9,R>() {
            @Override
            public R apply(T3 t3, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T5,T7,T8,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function5<T1,T2,T5,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T5 t5, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T3,T6,T7,T8,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function6<T1,T3,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T1,T7,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function2<T1,T7,R>() {
            @Override
            public R apply(T1 t1, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T4,T7,T8,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function5<T1,T4,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T3,T4,T7,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function6<T1,T2,T3,T4,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T5,T6,T7,T8,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function6<T1,T5,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T6,T7,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function5<T1,T2,T6,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T4,T5,T6,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function5<T2,T4,T5,T6,T9,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T5 t5, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T3,T4,T5,T6,T7,T8,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function7<T1,T3,T4,T5,T6,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T2,T5,T8,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function4<T1,T2,T5,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T5 t5, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T4,T5,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function3<T4,T5,T9,R>() {
            @Override
            public R apply(T4 t4, T5 t5, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T3,T4,T5,T6,T7,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function7<T1,T3,T4,T5,T6,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T4,T5,T7,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function4<T2,T4,T5,T7,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T5 t5, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T4,T5,T7,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function3<T4,T5,T7,R>() {
            @Override
            public R apply(T4 t4, T5 t5, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T2,T3,T4,T5,T8,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function7<T1,T2,T3,T4,T5,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T3,T4,T5,T8,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function5<T2,T3,T4,T5,T8,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T6,T7,T8,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function4<T1,T6,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T2,T3,T4,T5,T6,T7,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function7<T2,T3,T4,T5,T6,T7,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T4,T5,T8,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function4<T1,T4,T5,T8,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T5 t5, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T4,T7,T8,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function4<T2,T4,T7,T8,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T2,T3,T4,T5,T7,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function7<T1,T2,T3,T4,T5,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T2,T3,T4,T7,T8,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function7<T1,T2,T3,T4,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T1,T3,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function2<T1,T3,R>() {
            @Override
            public R apply(T1 t1, T3 t3) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T2,T5,T7,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function3<T2,T5,T7,R>() {
            @Override
            public R apply(T2 t2, T5 t5, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T3,T5,T8,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function5<T2,T3,T5,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T5 t5, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T2,T3,T4,T5,T7,T8,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function7<T2,T3,T4,T5,T7,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T2,T4,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function3<T2,T4,T9,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T3,T6,T8,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function4<T3,T6,T8,T9,R>() {
            @Override
            public R apply(T3 t3, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T5,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function2<T5,T9,R>() {
            @Override
            public R apply(T5 t5, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T2,T7,T8,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function3<T2,T7,T8,R>() {
            @Override
            public R apply(T2 t2, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T3,T5,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function4<T1,T3,T5,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T5 t5, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T2,T5,T8,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function3<T2,T5,T8,R>() {
            @Override
            public R apply(T2 t2, T5 t5, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T2,T5,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function2<T2,T5,R>() {
            @Override
            public R apply(T2 t2, T5 t5) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T3,T5,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function3<T1,T3,T5,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T5 t5) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T2,T4,T5,T6,T8,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function7<T1,T2,T4,T5,T6,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T5 t5, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T3,T4,T6,T8,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function5<T1,T3,T4,T6,T8,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T4,T6,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function2<T4,T6,R>() {
            @Override
            public R apply(T4 t4, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T3,T4,T5,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function5<T1,T2,T3,T4,T5,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T4,T7,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function2<T4,T7,R>() {
            @Override
            public R apply(T4 t4, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T4,T6,T7,T8,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function5<T4,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T4 t4, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T4,T5,T6,T8,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function5<T2,T4,T5,T6,T8,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T5 t5, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T4,T5,T6,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function5<T1,T2,T4,T5,T6,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T5 t5, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T3,T4,T6,T8,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function6<T1,T3,T4,T6,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T4,T6,T7,T8,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function4<T4,T6,T7,T8,R>() {
            @Override
            public R apply(T4 t4, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T2,T6,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function4<T1,T2,T6,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T4,T6,T7,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function4<T1,T4,T6,T7,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T2,T4,T5,T7,T8,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function7<T1,T2,T4,T5,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T5 t5, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T2,T7,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function3<T2,T7,T9,R>() {
            @Override
            public R apply(T2 t2, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T2,T4,T5,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function4<T1,T2,T4,T5,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T5 t5) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T3,T4,T8,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function4<T1,T3,T4,T8,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T3,T4,T7,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function3<T3,T4,T7,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T6,T7,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function4<T1,T6,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T2,T3,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function3<T2,T3,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T3,T5,T7,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function6<T1,T2,T3,T5,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T5 t5, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T4,T5,T7,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function6<T1,T2,T4,T5,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T5 t5, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T6,T8,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function3<T6,T8,T9,R>() {
            @Override
            public R apply(T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T3,T4,T6,T8,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function4<T3,T4,T6,T8,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T4,T5,T7,T8,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function6<T1,T2,T4,T5,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T5 t5, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T5,T6,T8,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function5<T1,T2,T5,T6,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T5 t5, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T4,T6,T7,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function3<T4,T6,T7,R>() {
            @Override
            public R apply(T4 t4, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T6,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function3<T1,T6,T9,R>() {
            @Override
            public R apply(T1 t1, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T3,T7,T8,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function5<T1,T3,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T3,T4,T5,T6,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function5<T1,T3,T4,T5,T6,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T5 t5, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T3,T4,T6,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function4<T2,T3,T4,T6,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T2,T5,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function3<T1,T2,T5,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T5 t5) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T2,T3,T4,T6,T7,T8,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function7<T2,T3,T4,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T4,T7,T8,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function5<T1,T2,T4,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T4,T6,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function3<T4,T6,T9,R>() {
            @Override
            public R apply(T4 t4, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T3,T7,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function4<T1,T3,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T3,T4,T5,T6,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function6<T1,T3,T4,T5,T6,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T5 t5, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T3,T7,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function3<T1,T3,T7,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T2,T4,T8,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function4<T1,T2,T4,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T3,T6,T8,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function6<T1,T2,T3,T6,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T4,T5,T6,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function5<T1,T4,T5,T6,T9,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T5 t5, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T2,T3,T5,T7,T8,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function6<T2,T3,T5,T7,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T5 t5, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T2,T4,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function4<T1,T2,T4,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T2,T4,T7,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function4<T1,T2,T4,T7,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T5,T8,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function3<T5,T8,T9,R>() {
            @Override
            public R apply(T5 t5, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T3,T6,T7,T8,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function6<T1,T2,T3,T6,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T5,T7,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function2<T5,T7,R>() {
            @Override
            public R apply(T5 t5, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T3,T6,T8,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function4<T2,T3,T6,T8,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T3,T5,T6,T7,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function5<T2,T3,T5,T6,T7,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T5 t5, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T3,T5,T7,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function4<T3,T5,T7,T9,R>() {
            @Override
            public R apply(T3 t3, T5 t5, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T3,T6,T7,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function5<T1,T3,T6,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T3,T4,T7,T8,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function5<T2,T3,T4,T7,T8,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T2,T3,T6,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function3<T2,T3,T6,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T3,T5,T6,T7,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function5<T1,T3,T5,T6,T7,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T5 t5, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T2,T5,T6,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function4<T1,T2,T5,T6,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T5 t5, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T3,T5,T6,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function4<T3,T5,T6,T9,R>() {
            @Override
            public R apply(T3 t3, T5 t5, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T3,T4,T5,T7,T8,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function6<T1,T3,T4,T5,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T5 t5, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T5,T6,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function3<T1,T5,T6,R>() {
            @Override
            public R apply(T1 t1, T5 t5, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T3,T5,T6,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function4<T2,T3,T5,T6,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T5 t5, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T3,T5,T7,T8,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function6<T1,T2,T3,T5,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T5 t5, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T4,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function3<T1,T4,T9,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T1,T4,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function2<T1,T4,R>() {
            @Override
            public R apply(T1 t1, T4 t4) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T4,T8,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function4<T1,T4,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T3,T6,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function3<T1,T3,T6,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T2,T3,T7,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function4<T1,T2,T3,T7,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T6,T7,T8,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function4<T2,T6,T7,T8,R>() {
            @Override
            public R apply(T2 t2, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T6,T7,T8,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function3<T6,T7,T8,R>() {
            @Override
            public R apply(T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T6,T7,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function4<T2,T6,T7,T9,R>() {
            @Override
            public R apply(T2 t2, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T3,T4,T5,T6,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function6<T1,T2,T3,T4,T5,T6,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T5,T6,T8,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function4<T2,T5,T6,T8,R>() {
            @Override
            public R apply(T2 t2, T5 t5, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T2,T4,T5,T6,T7,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function7<T1,T2,T4,T5,T6,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T5 t5, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T3,T5,T8,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function6<T1,T2,T3,T5,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T5 t5, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T4,T6,T8,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function4<T2,T4,T6,T8,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T2,T6,T8,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function3<T2,T6,T8,R>() {
            @Override
            public R apply(T2 t2, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T2,T3,T6,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function4<T1,T2,T3,T6,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T3,T4,T6,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function5<T2,T3,T4,T6,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T4,T7,T8,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function5<T2,T4,T7,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T3,T4,T7,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function4<T2,T3,T4,T7,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T3,T6,T7,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function5<T2,T3,T6,T7,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T6,T7,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function3<T1,T6,T7,R>() {
            @Override
            public R apply(T1 t1, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T3,T4,T5,T7,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function5<T2,T3,T4,T5,T7,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T2,T3,T4,T5,T6,T8,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function7<T1,T2,T3,T4,T5,T6,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T3,T5,T6,T8,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function6<T1,T2,T3,T5,T6,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T5 t5, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T3,T5,T6,T8,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function5<T3,T5,T6,T8,T9,R>() {
            @Override
            public R apply(T3 t3, T5 t5, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T5,T7,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function4<T2,T5,T7,T9,R>() {
            @Override
            public R apply(T2 t2, T5 t5, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T4,T6,T7,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function4<T2,T4,T6,T7,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T5,T6,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function4<T2,T5,T6,T9,R>() {
            @Override
            public R apply(T2 t2, T5 t5, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T3,T4,T7,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function5<T1,T2,T3,T4,T7,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T3,T4,T5,T6,T8,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function5<T3,T4,T5,T6,T8,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T5 t5, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function8<T1,T2,T3,T4,T5,T7,T8,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function8<T1,T2,T3,T4,T5,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T2,T3,T4,T5,T6,T8,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function6<T2,T3,T4,T5,T6,T8,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T3,T4,T8,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function4<T3,T4,T8,T9,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T2,T5,T6,T7,T8,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function6<T2,T5,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T3,T4,T6,T7,T8,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function7<T1,T3,T4,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T4,T8,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function3<T1,T4,T8,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T4,T5,T8,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function5<T1,T4,T5,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T5 t5, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T2,T3,T5,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function3<T2,T3,T5,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T5 t5) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T2,T3,T4,T5,T8,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function6<T2,T3,T4,T5,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T3,T6,T7,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function4<T3,T6,T7,T9,R>() {
            @Override
            public R apply(T3 t3, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T2,T4,T6,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function3<T2,T4,T6,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T4,T6,T7,T8,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function5<T2,T4,T6,T7,T8,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T2,T7,T8,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function4<T1,T2,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T3,T5,T6,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function4<T1,T3,T5,T6,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T5 t5, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T5,T7,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function3<T1,T5,T7,R>() {
            @Override
            public R apply(T1 t1, T5 t5, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function1<T1,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function1<T1,R>() {
            @Override
            public R apply(T1 t1) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T5,T6,T8,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function5<T1,T5,T6,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T5 t5, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T3,T6,T7,T8,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function5<T2,T3,T6,T7,T8,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T5,T6,T7,T8,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function4<T5,T6,T7,T8,R>() {
            @Override
            public R apply(T5 t5, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T4,T6,T7,T8,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function6<T1,T4,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T3,T5,T8,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function4<T3,T5,T8,T9,R>() {
            @Override
            public R apply(T3 t3, T5 t5, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T3,T4,T5,T7,T8,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function7<T1,T3,T4,T5,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T5 t5, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T3,T4,T5,T6,T7,T8,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function6<T3,T4,T5,T6,T7,T8,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T3,T6,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function5<T1,T2,T3,T6,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T2,T3,T5,T6,T7,T8,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function7<T1,T2,T3,T5,T6,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T5 t5, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T3,T4,T6,T7,T8,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function6<T1,T3,T4,T6,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T2,T7,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function4<T1,T2,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T3,T7,T8,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function5<T2,T3,T7,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T2,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function2<T2,T9,R>() {
            @Override
            public R apply(T2 t2, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T3,T5,T7,T8,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function4<T3,T5,T7,T8,R>() {
            @Override
            public R apply(T3 t3, T5 t5, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T5,T6,T7,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function5<T1,T5,T6,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T5 t5, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T4,T5,T8,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function4<T2,T4,T5,T8,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T5 t5, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T3,T5,T6,T7,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function4<T3,T5,T6,T7,R>() {
            @Override
            public R apply(T3 t3, T5 t5, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T4,T6,T8,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function6<T1,T2,T4,T6,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T2,T3,T4,T5,T6,T7,T8,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function7<T2,T3,T4,T5,T6,T7,T8,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T1,T8,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function2<T1,T8,R>() {
            @Override
            public R apply(T1 t1, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T3,T5,T6,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function5<T1,T2,T3,T5,T6,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T5 t5, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T2,T3,T4,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function4<T1,T2,T3,T4,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T3,T5,T7,T8,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function5<T1,T3,T5,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T5 t5, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T3,T4,T5,T6,T7,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function6<T3,T4,T5,T6,T7,T9,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T2,T4,T5,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function3<T2,T4,T5,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T5 t5) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T5,T6,T7,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function6<T1,T2,T5,T6,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T5 t5, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T4,T6,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function4<T2,T4,T6,T9,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T3,T4,T6,T7,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function5<T3,T4,T6,T7,T9,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T3,T4,T6,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function4<T3,T4,T6,T9,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T3,T4,T6,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function5<T1,T2,T3,T4,T6,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T2,T4,T5,T6,T8,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function6<T2,T4,T5,T6,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T5 t5, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T4,T6,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function4<T1,T4,T6,T9,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T3,T7,T8,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function4<T2,T3,T7,T8,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T4,T6,T7,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function4<T4,T6,T7,T9,R>() {
            @Override
            public R apply(T4 t4, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T4,T5,T7,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function5<T1,T2,T4,T5,T7,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T5 t5, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T3,T4,T5,T6,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function5<T3,T4,T5,T6,T9,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T5 t5, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T4,T5,T6,T7,T8,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function6<T1,T4,T5,T6,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T3,T5,T7,T8,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function6<T1,T3,T5,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T5 t5, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T3,T6,T7,T8,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function5<T1,T3,T6,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T3,T6,T7,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function4<T1,T3,T6,T7,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T3,T4,T7,T8,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function5<T3,T4,T7,T8,T9,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T3,T4,T5,T6,T7,T8,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function7<T3,T4,T5,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T3,T4,T6,T8,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function6<T1,T2,T3,T4,T6,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T2,T4,T5,T6,T7,T8,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function7<T2,T4,T5,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T5,T8,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function3<T1,T5,T8,R>() {
            @Override
            public R apply(T1 t1, T5 t5, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T3,T4,T8,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function3<T3,T4,T8,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T3,T5,T6,T8,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function4<T3,T5,T6,T8,R>() {
            @Override
            public R apply(T3 t3, T5 t5, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function8<T1,T2,T3,T4,T5,T6,T7,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function8<T1,T2,T3,T4,T5,T6,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T2,T3,T5,T6,T7,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function7<T1,T2,T3,T5,T6,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T5 t5, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T2,T4,T5,T6,T7,T8,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function7<T1,T2,T4,T5,T6,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T6,T7,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function3<T6,T7,T9,R>() {
            @Override
            public R apply(T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T3,T4,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function4<T2,T3,T4,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T2,T8,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function3<T1,T2,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T3,T8,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function4<T1,T3,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T3,T5,T6,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function3<T3,T5,T6,R>() {
            @Override
            public R apply(T3 t3, T5 t5, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T3,T4,T5,T8,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function4<T3,T4,T5,T8,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T5 t5, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T3,T5,T7,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function4<T1,T3,T5,T7,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T5 t5, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T5,T8,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function5<T1,T2,T5,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T5 t5, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T2,T3,T4,T6,T7,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function6<T2,T3,T4,T6,T7,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T3,T5,T8,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function5<T1,T2,T3,T5,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T5 t5, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T5,T6,T7,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function5<T2,T5,T6,T7,T9,R>() {
            @Override
            public R apply(T2 t2, T5 t5, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T4,T5,T7,T8,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function6<T1,T4,T5,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T5 t5, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T5,T6,T7,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function3<T5,T6,T7,R>() {
            @Override
            public R apply(T5 t5, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T5,T6,T8,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function3<T5,T6,T8,R>() {
            @Override
            public R apply(T5 t5, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function8<T1,T2,T3,T4,T5,T6,T8,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function8<T1,T2,T3,T4,T5,T6,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T3,T4,T7,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function4<T1,T3,T4,T7,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T3,T7,T8,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function3<T3,T7,T8,R>() {
            @Override
            public R apply(T3 t3, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T2,T7,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function3<T1,T2,T7,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T7,T8,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function3<T7,T8,T9,R>() {
            @Override
            public R apply(T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T3,T6,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function2<T3,T6,R>() {
            @Override
            public R apply(T3 t3, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function1<T6,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function1<T6,R>() {
            @Override
            public R apply(T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T4,T5,T6,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function4<T4,T5,T6,T9,R>() {
            @Override
            public R apply(T4 t4, T5 t5, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T4,T5,T7,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function4<T4,T5,T7,T9,R>() {
            @Override
            public R apply(T4 t4, T5 t5, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T3,T4,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function2<T3,T4,R>() {
            @Override
            public R apply(T3 t3, T4 t4) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T3,T4,T7,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function5<T2,T3,T4,T7,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T2,T3,T8,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function4<T1,T2,T3,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T2,T3,T5,T6,T7,T8,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function7<T2,T3,T5,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T3,T7,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function4<T2,T3,T7,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T2,T5,T6,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function3<T2,T5,T6,R>() {
            @Override
            public R apply(T2 t2, T5 t5, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T4,T5,T6,T7,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function6<T1,T4,T5,T6,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T5 t5, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T2,T5,T6,T7,T8,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function7<T1,T2,T5,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T3,T5,T8,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function3<T3,T5,T8,R>() {
            @Override
            public R apply(T3 t3, T5 t5, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T4,T6,T8,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function4<T1,T4,T6,T8,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T2,T5,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function3<T2,T5,T9,R>() {
            @Override
            public R apply(T2 t2, T5 t5, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T3,T5,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function2<T3,T5,R>() {
            @Override
            public R apply(T3 t3, T5 t5) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T3,T4,T8,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function5<T2,T3,T4,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T7,T8,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function4<T1,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T5,T7,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function3<T5,T7,T9,R>() {
            @Override
            public R apply(T5 t5, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function1<T9,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function1<T9,R>() {
            @Override
            public R apply(T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function8<T1,T2,T4,T5,T6,T7,T8,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function8<T1,T2,T4,T5,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T2,T3,T4,T6,T8,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function7<T1,T2,T3,T4,T6,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T3,T4,T5,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function3<T3,T4,T5,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T5 t5) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T4,T7,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function4<T2,T4,T7,T9,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T3,T4,T6,T7,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function5<T1,T3,T4,T6,T7,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T4,T5,T6,T7,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function6<T1,T2,T4,T5,T6,T7,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T5 t5, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T7,T8,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function4<T2,T7,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T2,T3,T4,T6,T7,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function7<T1,T2,T3,T4,T6,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T6,T8,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function2<T6,T8,R>() {
            @Override
            public R apply(T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T1,T2,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function2<T1,T2,R>() {
            @Override
            public R apply(T1 t1, T2 t2) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T2,T3,T5,T6,T8,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function6<T2,T3,T5,T6,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T5 t5, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T3,T5,T6,T8,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function5<T1,T3,T5,T6,T8,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T5 t5, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T3,T4,T6,T8,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function5<T2,T3,T4,T6,T8,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T6,T7,T8,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function5<T1,T2,T6,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T3,T6,T8,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function3<T3,T6,T8,R>() {
            @Override
            public R apply(T3 t3, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T4,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function2<T4,T9,R>() {
            @Override
            public R apply(T4 t4, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T2,T3,T8,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function3<T2,T3,T8,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T6,T7,T8,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function6<T1,T2,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T3,T4,T6,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function4<T1,T3,T4,T6,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T3,T4,T5,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function6<T1,T2,T3,T4,T5,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T3,T6,T7,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function5<T1,T2,T3,T6,T7,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T4,T7,T8,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function3<T4,T7,T8,R>() {
            @Override
            public R apply(T4 t4, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T3,T4,T5,T8,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function5<T3,T4,T5,T8,T9,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T5 t5, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T3,T4,T7,T8,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function6<T1,T3,T4,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T4,T7,T8,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function4<T1,T4,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T3,T6,T7,T8,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function5<T3,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T3 t3, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T4,T7,T8,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function4<T4,T7,T8,T9,R>() {
            @Override
            public R apply(T4 t4, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T3,T4,T5,T6,T7,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function6<T1,T3,T4,T5,T6,T7,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T4,T5,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function4<T1,T4,T5,T9,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T5 t5, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T2,T3,T4,T6,T7,T8,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function7<T1,T2,T3,T4,T6,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T3,T7,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function3<T3,T7,T9,R>() {
            @Override
            public R apply(T3 t3, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T4,T5,T6,T7,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function5<T2,T4,T5,T6,T7,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T5 t5, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T2,T3,T4,T6,T7,T8,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function6<T2,T3,T4,T6,T7,T8,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T5,T7,T8,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function3<T5,T7,T8,R>() {
            @Override
            public R apply(T5 t5, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T5,T6,T7,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function4<T2,T5,T6,T7,R>() {
            @Override
            public R apply(T2 t2, T5 t5, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T4,T5,T6,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function3<T4,T5,T6,R>() {
            @Override
            public R apply(T4 t4, T5 t5, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T3,T5,T6,T7,T8,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function5<T3,T5,T6,T7,T8,R>() {
            @Override
            public R apply(T3 t3, T5 t5, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T2,T4,T8,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function3<T2,T4,T8,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T6,T8,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function4<T1,T6,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T3,T4,T5,T7,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function6<T1,T2,T3,T4,T5,T7,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T4,T5,T6,T8,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function6<T1,T4,T5,T6,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T5 t5, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T3,T4,T5,T7,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function5<T1,T3,T4,T5,T7,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T5 t5, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T5,T6,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function5<T1,T2,T5,T6,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T5 t5, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T3,T4,T7,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function5<T1,T3,T4,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T2,T3,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function3<T1,T2,T3,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T4,T5,T7,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function5<T1,T4,T5,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T5 t5, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T3,T4,T5,T6,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function5<T2,T3,T4,T5,T6,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T2,T6,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function3<T1,T2,T6,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T3,T4,T8,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function5<T1,T2,T3,T4,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T4,T5,T8,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function4<T4,T5,T8,T9,R>() {
            @Override
            public R apply(T4 t4, T5 t5, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T4,T6,T7,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function5<T1,T4,T6,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function1<T8,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function1<T8,R>() {
            @Override
            public R apply(T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T4,T5,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function4<T2,T4,T5,T9,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T5 t5, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T6,T8,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function3<T1,T6,T8,R>() {
            @Override
            public R apply(T1 t1, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T3,T5,T6,T8,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function6<T1,T3,T5,T6,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T5 t5, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T3,T5,T7,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function5<T1,T2,T3,T5,T7,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T5 t5, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T5,T6,T8,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function5<T2,T5,T6,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T5 t5, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T1,T6,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function2<T1,T6,R>() {
            @Override
            public R apply(T1 t1, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T3,T6,T8,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function5<T1,T2,T3,T6,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T3,T7,T8,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function4<T1,T3,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T4,T5,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function5<T1,T2,T4,T5,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T5 t5, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T1,T5,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function2<T1,T5,R>() {
            @Override
            public R apply(T1 t1, T5 t5) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T4,T5,T7,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function5<T2,T4,T5,T7,T9,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T5 t5, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T3,T5,T7,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function3<T3,T5,T7,R>() {
            @Override
            public R apply(T3 t3, T5 t5, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T4,T5,T6,T7,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function5<T4,T5,T6,T7,T9,R>() {
            @Override
            public R apply(T4 t4, T5 t5, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T5,T7,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function5<T1,T2,T5,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T5 t5, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T4,T5,T6,T8,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function6<T1,T2,T4,T5,T6,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T5 t5, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T3,T4,T6,T7,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function6<T1,T2,T3,T4,T6,T7,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T2,T3,T6,T7,T8,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function7<T1,T2,T3,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T2,T4,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function3<T1,T2,T4,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function1<T5,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function1<T5,R>() {
            @Override
            public R apply(T5 t5) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T5,T7,T8,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function5<T2,T5,T7,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T5 t5, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T3,T4,T5,T8,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function6<T1,T3,T4,T5,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T5 t5, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T7,T8,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function3<T1,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T3,T4,T8,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function6<T1,T2,T3,T4,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T2,T3,T5,T7,T8,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function7<T1,T2,T3,T5,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T5 t5, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T3,T5,T6,T7,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function5<T3,T5,T6,T7,T9,R>() {
            @Override
            public R apply(T3 t3, T5 t5, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T2,T4,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function2<T2,T4,R>() {
            @Override
            public R apply(T2 t2, T4 t4) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T4,T8,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function5<T1,T2,T4,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T2,T3,T4,T5,T6,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function7<T1,T2,T3,T4,T5,T6,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T2,T4,T5,T7,T8,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function6<T2,T4,T5,T7,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T5 t5, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T3,T4,T5,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function4<T2,T3,T4,T5,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T2,T3,T4,T5,T7,T8,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function6<T2,T3,T4,T5,T7,T8,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T4,T5,T7,T8,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function5<T4,T5,T7,T8,T9,R>() {
            @Override
            public R apply(T4 t4, T5 t5, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function8<T1,T2,T3,T5,T6,T7,T8,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function8<T1,T2,T3,T5,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T2,T4,T6,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function4<T1,T2,T4,T6,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T2,T3,T4,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function3<T2,T3,T4,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T3,T6,T8,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function5<T2,T3,T6,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T4,T8,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function4<T2,T4,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T4,T5,T6,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function6<T1,T2,T4,T5,T6,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T5 t5, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T3,T4,T6,T7,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function6<T1,T3,T4,T6,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T6 t6, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T5,T6,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function3<T5,T6,T9,R>() {
            @Override
            public R apply(T5 t5, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T5,T7,T8,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function4<T2,T5,T7,T8,R>() {
            @Override
            public R apply(T2 t2, T5 t5, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T3,T7,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function2<T3,T7,R>() {
            @Override
            public R apply(T3 t3, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T2,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function3<T1,T2,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T2,T3,T4,T5,T7,T8,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function7<T1,T2,T3,T4,T5,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T7,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function3<T1,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T4,T6,T7,T8,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function5<T1,T4,T6,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T3,T4,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function4<T1,T3,T4,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T3,T6,T7,T8,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function4<T3,T6,T7,T8,R>() {
            @Override
            public R apply(T3 t3, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T3,T5,T7,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function5<T2,T3,T5,T7,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T5 t5, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T5,T6,T8,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function4<T1,T5,T6,T8,R>() {
            @Override
            public R apply(T1 t1, T5 t5, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T5,T6,T7,T8,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function6<T1,T2,T5,T6,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T5 t5, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T3,T4,T6,T7,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function4<T3,T4,T6,T7,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T2,T3,T4,T7,T8,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function6<T2,T3,T4,T7,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T3,T4,T6,T7,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function5<T2,T3,T4,T6,T7,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T4,T6,T8,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function4<T4,T6,T8,T9,R>() {
            @Override
            public R apply(T4 t4, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function1<T7,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function1<T7,R>() {
            @Override
            public R apply(T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T4,T5,T6,T7,T8,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function6<T4,T5,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T3,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function3<T1,T3,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T3,T4,T5,T7,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function6<T1,T3,T4,T5,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T5 t5, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function8<T1,T2,T3,T4,T6,T7,T8,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function8<T1,T2,T3,T4,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T3,T4,T5,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function5<T1,T3,T4,T5,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T5 t5, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T2,T6,T8,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function4<T1,T2,T6,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T3,T4,T5,T6,T8,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function7<T1,T3,T4,T5,T6,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T5 t5, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T5,T6,T8,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function4<T5,T6,T8,T9,R>() {
            @Override
            public R apply(T5 t5, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T3,T5,T7,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function5<T1,T3,T5,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T5 t5, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T4,T7,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function3<T4,T7,T9,R>() {
            @Override
            public R apply(T4 t4, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T2,T3,T7,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function3<T2,T3,T7,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T3,T6,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function3<T3,T6,T9,R>() {
            @Override
            public R apply(T3 t3, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T3,T6,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function4<T1,T3,T6,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T3,T5,T8,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function4<T1,T3,T5,T8,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T5 t5, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T6,T7,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function2<T6,T7,R>() {
            @Override
            public R apply(T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T3,T5,T6,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function5<T2,T3,T5,T6,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T5 t5, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T4,T5,T7,T8,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function5<T1,T4,T5,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T5 t5, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T7,T8,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function5<T1,T2,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T2,T6,T7,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function3<T2,T6,T7,R>() {
            @Override
            public R apply(T2 t2, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T2,T3,T4,T5,T7,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function6<T2,T3,T4,T5,T7,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T3,T4,T7,T8,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function4<T3,T4,T7,T8,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T2,T6,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function3<T2,T6,T9,R>() {
            @Override
            public R apply(T2 t2, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T3,T4,T6,T7,T8,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function6<T3,T4,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T4,T6,T7,T8,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function6<T1,T2,T4,T6,T7,T8,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T3,T4,T5,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function4<T3,T4,T5,T9,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T5 t5, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T1,T5,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function3<T1,T5,T9,R>() {
            @Override
            public R apply(T1 t1, T5 t5, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T6,T7,T8,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function4<T6,T7,T8,T9,R>() {
            @Override
            public R apply(T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T3,T5,T6,T8,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function5<T2,T3,T5,T6,T8,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T5 t5, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T5,T8,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function2<T5,T8,R>() {
            @Override
            public R apply(T5 t5, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T2,T4,T6,T7,T8,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function7<T1,T2,T4,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T3,T6,T7,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function4<T2,T3,T6,T7,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T6 t6, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T5,T6,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, final T4 t4, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function4<T1,T5,T6,T9,R>() {
            @Override
            public R apply(T1 t1, T5 t5, T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T3,T8,T9,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function4<T2,T3,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T1,T4,T5,T6,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, final T8 t8, final T9 t9) {
        return new Function4<T1,T4,T5,T6,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T5 t5, T6 t6) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function1<T3,R> apply(final T1 t1, final T2 t2, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function1<T3,R>() {
            @Override
            public R apply(T3 t3) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T4,T5,T7,T8,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function4<T4,T5,T7,T8,R>() {
            @Override
            public R apply(T4 t4, T5 t5, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T3,T4,T6,T7,T8,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function5<T3,T4,T6,T7,T8,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T6,T7,T8,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function5<T2,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T2,T4,T5,T8,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function6<T1,T2,T4,T5,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T5 t5, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T2,T6,T8,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function4<T2,T6,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T2,T3,T5,T6,T7,T8,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, final T9 t9) {
        return new Function6<T2,T3,T5,T6,T7,T8,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T5 t5, T6 t6, T7 t7, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T4,T5,T6,T8,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function4<T4,T5,T6,T8,R>() {
            @Override
            public R apply(T4 t4, T5 t5, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function7<T1,T3,T5,T6,T7,T8,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, final T4 t4, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function7<T1,T3,T5,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T4,T5,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, GivenLater _4, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function2<T4,T5,R>() {
            @Override
            public R apply(T4 t4, T5 t5) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T4,T7,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function5<T1,T2,T4,T7,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T4 t4, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T4,T6,T8,T9,R> apply(GivenLater _0, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function5<T1,T4,T6,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T4 t4, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function4<T3,T4,T7,T9,R> apply(final T1 t1, final T2 t2, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, GivenLater _8) {
        return new Function4<T3,T4,T7,T9,R>() {
            @Override
            public R apply(T3 t3, T4 t4, T7 t7, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T4,T8,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function2<T4,T8,R>() {
            @Override
            public R apply(T4 t4, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T2,T4,T6,T8,T9,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function5<T2,T4,T6,T8,T9,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T6,T8,T9,R> apply(GivenLater _0, GivenLater _1, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, GivenLater _8) {
        return new Function5<T1,T2,T6,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T6 t6, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function8<T1,T3,T4,T5,T6,T7,T8,T9,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, GivenLater _6, GivenLater _7, GivenLater _8) {
        return new Function8<T1,T3,T4,T5,T6,T7,T8,T9,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T2,T4,T7,R> apply(final T1 t1, GivenLater _1, final T3 t3, GivenLater _3, final T5 t5, final T6 t6, GivenLater _6, final T8 t8, final T9 t9) {
        return new Function3<T2,T4,T7,R>() {
            @Override
            public R apply(T2 t2, T4 t4, T7 t7) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function3<T4,T6,T8,R> apply(final T1 t1, final T2 t2, final T3 t3, GivenLater _3, final T5 t5, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function3<T4,T6,T8,R>() {
            @Override
            public R apply(T4 t4, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function6<T1,T3,T4,T5,T6,T8,R> apply(GivenLater _0, final T2 t2, GivenLater _2, GivenLater _3, GivenLater _4, GivenLater _5, final T7 t7, GivenLater _7, final T9 t9) {
        return new Function6<T1,T3,T4,T5,T6,T8,R>() {
            @Override
            public R apply(T1 t1, T3 t3, T4 t4, T5 t5, T6 t6, T8 t8) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T2,T3,R> apply(final T1 t1, GivenLater _1, GivenLater _2, final T4 t4, final T5 t5, final T6 t6, final T7 t7, final T8 t8, final T9 t9) {
        return new Function2<T2,T3,R>() {
            @Override
            public R apply(T2 t2, T3 t3) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function5<T1,T2,T3,T4,T9,R> apply(GivenLater _0, GivenLater _1, GivenLater _2, GivenLater _3, final T5 t5, final T6 t6, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function5<T1,T2,T3,T4,T9,R>() {
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }

    public final Function2<T6,T9,R> apply(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5, GivenLater _5, final T7 t7, final T8 t8, GivenLater _8) {
        return new Function2<T6,T9,R>() {
            @Override
            public R apply(T6 t6, T9 t9) {
                return Function9.this.apply(t1, t2, t3, t4, t5, t6, t7, t8, t9);
            }
        };
    }
}
