package fi.solita.utils.functional;

import fi.solita.utils.functional.Function.GivenEvenLater;
import fi.solita.utils.functional.Function.GivenLater;

public abstract class Function4<T1, T2, T3, T4, R> extends MultiParamFunction<Tuple4<? extends T1, ? extends T2, ? extends T3, ? extends T4>, R> {

    public abstract R apply(T1 t1, T2 t2, T3 t3, T4 t4);

    public final <U> Function4<T1, T2, T3, T4, U> andThen(final Apply<? super R, ? extends U> next) {
        final Function4<T1, T2, T3, T4, R> self = this;
        return new Function4<T1, T2, T3, T4, U>() {
            @Override
            public U apply(T1 t1, T2 t2, T3 t3, T4 t4) {
                return next.apply(self.apply(t1, t2, t3, t4));
            }
        };
    }

    public final Function1<Tuple4<? extends T1, ? extends T2, ? extends T3, ? extends T4>, R> tuppled() {
        return new Function1<Tuple4<? extends T1, ? extends T2, ? extends T3, ? extends T4>, R>() {
            @Override
            public R apply(Tuple4<? extends T1, ? extends T2, ? extends T3, ? extends T4> t) {
                return Function4.this.apply(t._1, t._2, t._3, t._4);
            }
        };
    }
    
    public Function1<T1, Function1<T2, Function1<T3, Function1<T4, R>>>> curried() {
        return new Function1<T1, Function1<T2, Function1<T3, Function1<T4, R>>>>() {
            @Override
            public Function1<T2, Function1<T3, Function1<T4, R>>> apply(final T1 t1) {
                return new Function3<T2, T3, T4, R>() {
                    @Override
                    public R apply(T2 t2, T3 t3, T4 t4) {
                        return Function4.this.apply(t1, t2, t3, t4);
                    }
                }.curried();
            }
        };
    }
    
    public final Function1<T4,R> ap(final T1 t1, final T2 t2, final T3 t3) {
        return ap(t1).ap(t2).ap(t3);
    }
    
    public final Function2<T3,T4,R> ap(final T1 t1, final T2 t2) {
        return ap(t1).ap(t2);
    }
    
    public final Function3<T2,T3,T4,R> ap(final T1 t) {
        return new Function3<T2,T3,T4,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4) {
                return Function4.this.apply(t, t2, t3, t4);
            }
        };
    }
    
    static final <T1,T2,T3,T4,R> Function4<T1,T2,T3,T4,R> partial(final Apply<? extends Tuple,R> f, final Object... paramsAndPlaceholders) {
        return new Function4<T1,T2,T3,T4,R>() {
            @SuppressWarnings("unchecked")
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4) {
                return PartialApplicationHelper.doApply((Apply<Tuple,R>)f, paramsAndPlaceholders, t1, t2, t3, t4);
            }
        };
    }
    
    static final <T1,T2,T3,T4,R,FR extends Apply<?,R>> Function4<T1,T2,T3,T4,FR> split(final Apply<? extends Tuple,R> f, final Object... placeholders) {
        return new Function4<T1,T2,T3,T4,FR>() {
            @Override
            public FR apply(T1 t1, T2 t2, T3 t3, T4 t4) {
                return PartialApplicationHelper.makeSecondFunc(f, placeholders, t1, t2, t3, t4);
            }
        };
    }
    
    public final Function2<T1,T3,R> apply(GivenLater t1, T2 t2, GivenLater t3, T4 t4) {
        return Function2.partial(this, t1, t2, t3, t4);
    }

    public final Function2<T1,T3,Function2<T2,T4,R>> apply(GivenLater t1, GivenEvenLater t2, GivenLater t3, GivenEvenLater t4) {
        return Function2.split(this, t1, t2, t3, t4);
    }

    public final Function3<T1,T3,T4,R> apply(GivenLater t1, T2 t2, GivenLater t3, GivenLater t4) {
        return Function3.partial(this, t1, t2, t3, t4);
    }

    public final Function3<T1,T3,T4,Function1<T2,R>> apply(GivenLater t1, GivenEvenLater t2, GivenLater t3, GivenLater t4) {
        return Function3.split(this, t1, t2, t3, t4);
    }

    public final Function1<T2,R> apply(T1 t1, GivenLater t2, T3 t3, T4 t4) {
        return Function1.partial(this, t1, t2, t3, t4);
    }

    public final Function1<T2,Function3<T1,T3,T4,R>> apply(GivenEvenLater t1, GivenLater t2, GivenEvenLater t3, GivenEvenLater t4) {
        return Function1.split(this, t1, t2, t3, t4);
    }

    public final Function3<T2,T3,T4,R> apply(T1 t1, GivenLater t2, GivenLater t3, GivenLater t4) {
        return Function3.partial(this, t1, t2, t3, t4);
    }

    public final Function3<T2,T3,T4,Function1<T1,R>> apply(GivenEvenLater t1, GivenLater t2, GivenLater t3, GivenLater t4) {
        return Function3.split(this, t1, t2, t3, t4);
    }

    public final Function1<T1,R> apply(GivenLater t1, T2 t2, T3 t3, T4 t4) {
        return Function1.partial(this, t1, t2, t3, t4);
    }

    public final Function1<T1,Function3<T2,T3,T4,R>> apply(GivenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenEvenLater t4) {
        return Function1.split(this, t1, t2, t3, t4);
    }

    public final Function3<T1,T2,T4,R> apply(GivenLater t1, GivenLater t2, T3 t3, GivenLater t4) {
        return Function3.partial(this, t1, t2, t3, t4);
    }

    public final Function3<T1,T2,T4,Function1<T3,R>> apply(GivenLater t1, GivenLater t2, GivenEvenLater t3, GivenLater t4) {
        return Function3.split(this, t1, t2, t3, t4);
    }

    public final Function2<T2,T3,R> apply(T1 t1, GivenLater t2, GivenLater t3, T4 t4) {
        return Function2.partial(this, t1, t2, t3, t4);
    }

    public final Function2<T2,T3,Function2<T1,T4,R>> apply(GivenEvenLater t1, GivenLater t2, GivenLater t3, GivenEvenLater t4) {
        return Function2.split(this, t1, t2, t3, t4);
    }

    public final Function3<T1,T2,T3,R> apply(GivenLater t1, GivenLater t2, GivenLater t3, T4 t4) {
        return Function3.partial(this, t1, t2, t3, t4);
    }

    public final Function3<T1,T2,T3,Function1<T4,R>> apply(GivenLater t1, GivenLater t2, GivenLater t3, GivenEvenLater t4) {
        return Function3.split(this, t1, t2, t3, t4);
    }

    public final Function2<T2,T4,R> apply(T1 t1, GivenLater t2, T3 t3, GivenLater t4) {
        return Function2.partial(this, t1, t2, t3, t4);
    }

    public final Function2<T2,T4,Function2<T1,T3,R>> apply(GivenEvenLater t1, GivenLater t2, GivenEvenLater t3, GivenLater t4) {
        return Function2.split(this, t1, t2, t3, t4);
    }

    public final Function1<T3,R> apply(T1 t1, T2 t2, GivenLater t3, T4 t4) {
        return Function1.partial(this, t1, t2, t3, t4);
    }

    public final Function1<T3,Function3<T1,T2,T4,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenLater t3, GivenEvenLater t4) {
        return Function1.split(this, t1, t2, t3, t4);
    }

    public final Function2<T3,T4,R> apply(T1 t1, T2 t2, GivenLater t3, GivenLater t4) {
        return Function2.partial(this, t1, t2, t3, t4);
    }

    public final Function2<T3,T4,Function2<T1,T2,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenLater t3, GivenLater t4) {
        return Function2.split(this, t1, t2, t3, t4);
    }

    public final Function2<T1,T4,R> apply(GivenLater t1, T2 t2, T3 t3, GivenLater t4) {
        return Function2.partial(this, t1, t2, t3, t4);
    }

    public final Function2<T1,T4,Function2<T2,T3,R>> apply(GivenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenLater t4) {
        return Function2.split(this, t1, t2, t3, t4);
    }

    public final Function1<T4,R> apply(T1 t1, T2 t2, T3 t3, GivenLater t4) {
        return Function1.partial(this, t1, t2, t3, t4);
    }

    public final Function1<T4,Function3<T1,T2,T3,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenLater t4) {
        return Function1.split(this, t1, t2, t3, t4);
    }

    public final Function2<T1,T2,R> apply(GivenLater t1, GivenLater t2, T3 t3, T4 t4) {
        return Function2.partial(this, t1, t2, t3, t4);
    }

    public final Function2<T1,T2,Function2<T3,T4,R>> apply(GivenLater t1, GivenLater t2, GivenEvenLater t3, GivenEvenLater t4) {
        return Function2.split(this, t1, t2, t3, t4);
    }
}
