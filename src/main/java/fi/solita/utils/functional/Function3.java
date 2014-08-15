package fi.solita.utils.functional;

import static fi.solita.utils.functional.Function._;
import fi.solita.utils.functional.Function.GivenEvenLater;
import fi.solita.utils.functional.Function.GivenLater;

public abstract class Function3<T1, T2, T3, R> extends MultiParamFunction<Tuple3<T1,T2,T3>, R> {

    public abstract R apply(T1 t1, T2 t2, T3 t3);
    
    public final <U> Function3<T1, T2, T3, U> andThen(final Apply<? super R, ? extends U> next) {
        final Function3<T1, T2, T3, R> self = this;
        return new Function3<T1, T2, T3, U>() {
            @Override
            public U apply(T1 t1, T2 t2, T3 t3) {
                return next.apply(self.apply(t1, t2, t3));
            }
        };
    }
    
    public final Function1<Tuple3<T1, T2, T3>, R> tuppled() { 
        return new Function1<Tuple3<T1, T2, T3>, R>() {
            @Override
            public R apply(Tuple3<T1, T2, T3> t) {
                return Function3.this.apply(t._1, t._2, t._3);
            }
        };
    }
    
    public Function1<T1, Function1<T2, Function1<T3, R>>> curried() {
        return new Function1<T1, Function1<T2, Function1<T3, R>>>() {
            @Override
            public Function1<T2, Function1<T3, R>> apply(final T1 t1) {
                return new Function2<T2, T3, R>() {
                    @Override
                    public R apply(T2 t2, T3 t3) {
                        return Function3.this.apply(t1, t2, t3);
                    }
                }.curried();
            }
        };
    }
    
    public final Function1<T3,R> ap(final T1 t1, final T2 t2) {
        return ap(t1).ap(t2);
    }
    
    public final Function2<T2,T3,R> ap(final T1 t1) {
        return new Function2<T2,T3,R>() {
            @Override
            public R apply(T2 t2, T3 t3) {
                return Function3.this.apply(t1, t2, t3);
            }
        };
    }
    
    static final <T1,T2,T3,R> Function3<T1,T2,T3,R> partial(final Apply<? extends Tuple,R> f, final Object... paramsAndPlaceholders) {
        return new Function3<T1,T2,T3,R>() {
            @SuppressWarnings("unchecked")
            @Override
            public R apply(T1 t1, T2 t2, T3 t3) {
                return PartialApplicationHelper.doApply((Apply<Tuple,R>)f, paramsAndPlaceholders, t1, t2, t3);
            }
        };
    }
    
    static final <T1,T2,T3,R,FR extends Apply<?,R>> Function3<T1,T2,T3,FR> split(final Apply<? extends Tuple,R> f, final Object... placeholders) {
        return new Function3<T1,T2,T3,FR>() {
            @Override
            public FR apply(T1 t1, T2 t2, T3 t3) {
                return PartialApplicationHelper.makeSecondFunc(f, placeholders, t1, t2, t3);
            }
        };
    }
    
    public final Function1<T1,R> apply(GivenLater _0, final T2 t2, final T3 t3) {
        return new Function1<T1,R>() {
            @Override
            public R apply(T1 t1) {
                return Function3.this.apply(t1, t2, t3);
            }
        };
    }

    public final Function1<T2,R> apply(final T1 t1, GivenLater _1, final T3 t3) {
        return new Function1<T2,R>() {
            @Override
            public R apply(T2 t2) {
                return Function3.this.apply(t1, t2, t3);
            }
        };
    }

    public final Function2<T1,T2,R> apply(GivenLater _0, GivenLater _1, final T3 t3) {
        return new Function2<T1,T2,R>() {
            @Override
            public R apply(T1 t1, T2 t2) {
                return Function3.this.apply(t1, t2, t3);
            }
        };
    }

    public final Function2<T2,T3,R> apply(final T1 t1, GivenLater _1, GivenLater _2) {
        return new Function2<T2,T3,R>() {
            @Override
            public R apply(T2 t2, T3 t3) {
                return Function3.this.apply(t1, t2, t3);
            }
        };
    }

    public final Function2<T1,T3,R> apply(GivenLater _0, final T2 t2, GivenLater _2) {
        return new Function2<T1,T3,R>() {
            @Override
            public R apply(T1 t1, T3 t3) {
                return Function3.this.apply(t1, t2, t3);
            }
        };
    }

    public final Function1<T3,R> apply(final T1 t1, final T2 t2, GivenLater _2) {
        return new Function1<T3,R>() {
            @Override
            public R apply(T3 t3) {
                return Function3.this.apply(t1, t2, t3);
            }
        };
    }
    
    public final Function1<T1,Function2<T2,T3,R>> apply(GivenLater t1, final GivenEvenLater t2, final GivenEvenLater t3) {
        return new Function1<T1, Function2<T2,T3,R>>() {
            @Override
            public Function2<T2, T3, R> apply(T1 t) {
                return Function3.this.apply(t, _, _);
            }
        };
    }

    public final Function1<T2,Function2<T1,T3,R>> apply(final GivenEvenLater t1, GivenLater _1, final GivenEvenLater t3) {
        return new Function1<T2,Function2<T1,T3,R>>() {
            @Override
            public Function2<T1, T3, R> apply(T2 t2) {
                return Function3.this.apply(_, t2, _);
            }
        };
    }

    public final Function2<T1,T2,Function1<T3,R>> apply(GivenLater _0, GivenLater _1, final GivenEvenLater t3) {
        return new Function2<T1,T2,Function1<T3,R>>() {
            @Override
            public Function1<T3, R> apply(T1 t1, T2 t2) {
                return Function3.this.apply(t1, t2, _);
            }
        };
    }

    public final Function2<T2,T3,Function1<T1,R>> apply(final GivenEvenLater t1, GivenLater _1, GivenLater _2) {
        return new Function2<T2,T3,Function1<T1,R>>() {
            @Override
            public Function1<T1, R> apply(T2 t2, T3 t3) {
                return Function3.this.apply(_, t2, t3);
            }
        };
    }

    public final Function2<T1,T3,Function1<T2,R>> apply(GivenLater _0, final GivenEvenLater t2, GivenLater _2) {
        return new Function2<T1,T3,Function1<T2,R>>() {
            @Override
            public Function1<T2, R> apply(T1 t1, T3 t3) {
                return Function3.this.apply(t1, _, t3);
            }
        };
    }

    public final Function1<T3,Function2<T1,T2,R>> apply(final GivenEvenLater t1, final GivenEvenLater t2, GivenLater _2) {
        return new Function1<T3,Function2<T1,T2,R>>() {
            @Override
            public Function2<T1, T2, R> apply(T3 t3) {
                return Function3.this.apply(_, _, t3);
            }
        };
    }
}
