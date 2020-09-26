package fi.solita.utils.functional;

import fi.solita.utils.functional.Function.GivenEvenLater;
import fi.solita.utils.functional.Function.GivenLater;

public abstract class Function5<T1, T2, T3, T4, T5, R> extends MultiParamFunction<Tuple5<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5>, R, T1> {

    public abstract R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5);

    @Override
    public final <U> Function5<T1, T2, T3, T4, T5, U> andThen(final Apply<? super R, ? extends U> next) {
        final Function5<T1, T2, T3, T4, T5, R> self = this;
        return new Function5<T1, T2, T3, T4, T5, U>() {
            @Override
            public U apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
                return next.apply(self.apply(t1, t2, t3, t4, t5));
            }
        };
    }

    @Override
    public final Function1<Tuple5<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5>, R> tuppled() {
        return new Function1<Tuple5<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5>, R>() {
            @Override
            public R apply(Tuple5<? extends T1, ? extends T2, ? extends T3, ? extends T4, ? extends T5> t) {
                return Function5.this.apply(t._1, t._2, t._3, t._4, t._5);
            }
        };
    }
    
    @Override
    public Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, R>>>>> curried() {
        return new Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, R>>>>>() {
            @Override
            public Function1<T2, Function1<T3, Function1<T4, Function1<T5, R>>>> apply(final T1 t1) {
                return new Function4<T2, T3, T4, T5, R>() {
                    @Override
                    public R apply(T2 t2, T3 t3, T4 t4, T5 t5) {
                        return Function5.this.apply(t1, t2, t3, t4, t5);
                    }
                }.curried();
            }
        };
    }
    
    public final Function1<T5,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4) {
        return ap(t1).ap(t2).ap(t3).ap(t4);
    }
    
    public final Function2<T4,T5,R> ap(final T1 t1, final T2 t2, final T3 t3) {
        return ap(t1).ap(t2).ap(t3);
    }
    
    public final Function3<T3,T4,T5,R> ap(final T1 t1, final T2 t2) {
        return ap(t1).ap(t2);
    }
    
    @Override
    public final Function4<T2,T3,T4,T5,R> ap(final T1 t) {
        return new Function4<T2,T3,T4,T5,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5) {
                return Function5.this.apply(t, t2, t3, t4, t5);
            }
        };
    }
    
    static final <T1,T2,T3,T4,T5,R> Function5<T1,T2,T3,T4,T5,R> partial(final Apply<? extends Tuple,R> f, final Object... paramsAndPlaceholders) {
        return new Function5<T1,T2,T3,T4,T5,R>() {
            @SuppressWarnings("unchecked")
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
                return PartialApplicationHelper.doApply((Apply<Tuple,R>)f, paramsAndPlaceholders, t1, t2, t3, t4, t5);
            }
        };
    }
    
    static final <T1,T2,T3,T4,T5,R,FR extends Apply<?,R>> Function5<T1,T2,T3,T4,T5,FR> split(final Apply<? extends Tuple,R> f, final Object... placeholders) {
        return new Function5<T1,T2,T3,T4,T5,FR>() {
            @Override
            public FR apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
                return PartialApplicationHelper.makeSecondFunc(f, placeholders, t1, t2, t3, t4, t5);
            }
        };
    }
    
    public final Function3<T3,T4,T5,R> apply(T1 t1, T2 t2, GivenLater t3, GivenLater t4, GivenLater t5) {
        return Function3.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function3<T3,T4,T5,Function2<T1,T2,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenLater t3, GivenLater t4, GivenLater t5) {
        return Function3.split(this, t1, t2, t3, t4, t5);
    }

    public final Function4<T1,T2,T3,T4,R> apply(GivenLater t1, GivenLater t2, GivenLater t3, GivenLater t4, T5 t5) {
        return Function4.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function4<T1,T2,T3,T4,Function1<T5,R>> apply(GivenLater t1, GivenLater t2, GivenLater t3, GivenLater t4, GivenEvenLater t5) {
        return Function4.split(this, t1, t2, t3, t4, t5);
    }

    public final Function2<T1,T2,R> apply(GivenLater t1, GivenLater t2, T3 t3, T4 t4, T5 t5) {
        return Function2.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function2<T1,T2,Function3<T3,T4,T5,R>> apply(GivenLater t1, GivenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenEvenLater t5) {
        return Function2.split(this, t1, t2, t3, t4, t5);
    }

    public final Function4<T2,T3,T4,T5,R> apply(T1 t1, GivenLater t2, GivenLater t3, GivenLater t4, GivenLater t5) {
        return Function4.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function4<T2,T3,T4,T5,Function1<T1,R>> apply(GivenEvenLater t1, GivenLater t2, GivenLater t3, GivenLater t4, GivenLater t5) {
        return Function4.split(this, t1, t2, t3, t4, t5);
    }

    public final Function2<T1,T3,R> apply(GivenLater t1, T2 t2, GivenLater t3, T4 t4, T5 t5) {
        return Function2.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function2<T1,T3,Function3<T2,T4,T5,R>> apply(GivenLater t1, GivenEvenLater t2, GivenLater t3, GivenEvenLater t4, GivenEvenLater t5) {
        return Function2.split(this, t1, t2, t3, t4, t5);
    }

    public final Function3<T1,T2,T3,R> apply(GivenLater t1, GivenLater t2, GivenLater t3, T4 t4, T5 t5) {
        return Function3.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function3<T1,T2,T3,Function2<T4,T5,R>> apply(GivenLater t1, GivenLater t2, GivenLater t3, GivenEvenLater t4, GivenEvenLater t5) {
        return Function3.split(this, t1, t2, t3, t4, t5);
    }

    public final Function2<T4,T5,R> apply(T1 t1, T2 t2, T3 t3, GivenLater t4, GivenLater t5) {
        return Function2.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function2<T4,T5,Function3<T1,T2,T3,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenLater t4, GivenLater t5) {
        return Function2.split(this, t1, t2, t3, t4, t5);
    }

    public final Function2<T1,T4,R> apply(GivenLater t1, T2 t2, T3 t3, GivenLater t4, T5 t5) {
        return Function2.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function2<T1,T4,Function3<T2,T3,T5,R>> apply(GivenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenLater t4, GivenEvenLater t5) {
        return Function2.split(this, t1, t2, t3, t4, t5);
    }

    public final Function3<T2,T3,T5,R> apply(T1 t1, GivenLater t2, GivenLater t3, T4 t4, GivenLater t5) {
        return Function3.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function3<T2,T3,T5,Function2<T1,T4,R>> apply(GivenEvenLater t1, GivenLater t2, GivenLater t3, GivenEvenLater t4, GivenLater t5) {
        return Function3.split(this, t1, t2, t3, t4, t5);
    }

    public final Function2<T2,T3,R> apply(T1 t1, GivenLater t2, GivenLater t3, T4 t4, T5 t5) {
        return Function2.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function2<T2,T3,Function3<T1,T4,T5,R>> apply(GivenEvenLater t1, GivenLater t2, GivenLater t3, GivenEvenLater t4, GivenEvenLater t5) {
        return Function2.split(this, t1, t2, t3, t4, t5);
    }

    public final Function2<T3,T4,R> apply(T1 t1, T2 t2, GivenLater t3, GivenLater t4, T5 t5) {
        return Function2.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function2<T3,T4,Function3<T1,T2,T5,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenLater t3, GivenLater t4, GivenEvenLater t5) {
        return Function2.split(this, t1, t2, t3, t4, t5);
    }

    public final Function1<T1,R> apply(GivenLater t1, T2 t2, T3 t3, T4 t4, T5 t5) {
        return Function1.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function1<T1,Function4<T2,T3,T4,T5,R>> apply(GivenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenEvenLater t5) {
        return Function1.split(this, t1, t2, t3, t4, t5);
    }

    public final Function3<T1,T4,T5,R> apply(GivenLater t1, T2 t2, T3 t3, GivenLater t4, GivenLater t5) {
        return Function3.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function3<T1,T4,T5,Function2<T2,T3,R>> apply(GivenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenLater t4, GivenLater t5) {
        return Function3.split(this, t1, t2, t3, t4, t5);
    }

    public final Function2<T2,T5,R> apply(T1 t1, GivenLater t2, T3 t3, T4 t4, GivenLater t5) {
        return Function2.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function2<T2,T5,Function3<T1,T3,T4,R>> apply(GivenEvenLater t1, GivenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenLater t5) {
        return Function2.split(this, t1, t2, t3, t4, t5);
    }

    public final Function3<T2,T4,T5,R> apply(T1 t1, GivenLater t2, T3 t3, GivenLater t4, GivenLater t5) {
        return Function3.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function3<T2,T4,T5,Function2<T1,T3,R>> apply(GivenEvenLater t1, GivenLater t2, GivenEvenLater t3, GivenLater t4, GivenLater t5) {
        return Function3.split(this, t1, t2, t3, t4, t5);
    }

    public final Function1<T5,R> apply(T1 t1, T2 t2, T3 t3, T4 t4, GivenLater t5) {
        return Function1.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function1<T5,Function4<T1,T2,T3,T4,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenLater t5) {
        return Function1.split(this, t1, t2, t3, t4, t5);
    }

    public final Function3<T1,T3,T5,R> apply(GivenLater t1, T2 t2, GivenLater t3, T4 t4, GivenLater t5) {
        return Function3.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function3<T1,T3,T5,Function2<T2,T4,R>> apply(GivenLater t1, GivenEvenLater t2, GivenLater t3, GivenEvenLater t4, GivenLater t5) {
        return Function3.split(this, t1, t2, t3, t4, t5);
    }

    public final Function4<T1,T3,T4,T5,R> apply(GivenLater t1, T2 t2, GivenLater t3, GivenLater t4, GivenLater t5) {
        return Function4.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function4<T1,T3,T4,T5,Function1<T2,R>> apply(GivenLater t1, GivenEvenLater t2, GivenLater t3, GivenLater t4, GivenLater t5) {
        return Function4.split(this, t1, t2, t3, t4, t5);
    }

    public final Function3<T1,T3,T4,R> apply(GivenLater t1, T2 t2, GivenLater t3, GivenLater t4, T5 t5) {
        return Function3.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function3<T1,T3,T4,Function2<T2,T5,R>> apply(GivenLater t1, GivenEvenLater t2, GivenLater t3, GivenLater t4, GivenEvenLater t5) {
        return Function3.split(this, t1, t2, t3, t4, t5);
    }

    public final Function1<T2,R> apply(T1 t1, GivenLater t2, T3 t3, T4 t4, T5 t5) {
        return Function1.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function1<T2,Function4<T1,T3,T4,T5,R>> apply(GivenEvenLater t1, GivenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenEvenLater t5) {
        return Function1.split(this, t1, t2, t3, t4, t5);
    }

    public final Function3<T1,T2,T5,R> apply(GivenLater t1, GivenLater t2, T3 t3, T4 t4, GivenLater t5) {
        return Function3.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function3<T1,T2,T5,Function2<T3,T4,R>> apply(GivenLater t1, GivenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenLater t5) {
        return Function3.split(this, t1, t2, t3, t4, t5);
    }

    public final Function2<T2,T4,R> apply(T1 t1, GivenLater t2, T3 t3, GivenLater t4, T5 t5) {
        return Function2.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function2<T2,T4,Function3<T1,T3,T5,R>> apply(GivenEvenLater t1, GivenLater t2, GivenEvenLater t3, GivenLater t4, GivenEvenLater t5) {
        return Function2.split(this, t1, t2, t3, t4, t5);
    }

    public final Function4<T1,T2,T4,T5,R> apply(GivenLater t1, GivenLater t2, T3 t3, GivenLater t4, GivenLater t5) {
        return Function4.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function4<T1,T2,T4,T5,Function1<T3,R>> apply(GivenLater t1, GivenLater t2, GivenEvenLater t3, GivenLater t4, GivenLater t5) {
        return Function4.split(this, t1, t2, t3, t4, t5);
    }

    public final Function1<T3,R> apply(T1 t1, T2 t2, GivenLater t3, T4 t4, T5 t5) {
        return Function1.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function1<T3,Function4<T1,T2,T4,T5,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenLater t3, GivenEvenLater t4, GivenEvenLater t5) {
        return Function1.split(this, t1, t2, t3, t4, t5);
    }

    public final Function2<T3,T5,R> apply(T1 t1, T2 t2, GivenLater t3, T4 t4, GivenLater t5) {
        return Function2.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function2<T3,T5,Function3<T1,T2,T4,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenLater t3, GivenEvenLater t4, GivenLater t5) {
        return Function2.split(this, t1, t2, t3, t4, t5);
    }

    public final Function1<T4,R> apply(T1 t1, T2 t2, T3 t3, GivenLater t4, T5 t5) {
        return Function1.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function1<T4,Function4<T1,T2,T3,T5,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenLater t4, GivenEvenLater t5) {
        return Function1.split(this, t1, t2, t3, t4, t5);
    }

    public final Function2<T1,T5,R> apply(GivenLater t1, T2 t2, T3 t3, T4 t4, GivenLater t5) {
        return Function2.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function2<T1,T5,Function3<T2,T3,T4,R>> apply(GivenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenLater t5) {
        return Function2.split(this, t1, t2, t3, t4, t5);
    }

    public final Function3<T2,T3,T4,R> apply(T1 t1, GivenLater t2, GivenLater t3, GivenLater t4, T5 t5) {
        return Function3.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function3<T2,T3,T4,Function2<T1,T5,R>> apply(GivenEvenLater t1, GivenLater t2, GivenLater t3, GivenLater t4, GivenEvenLater t5) {
        return Function3.split(this, t1, t2, t3, t4, t5);
    }

    public final Function4<T1,T2,T3,T5,R> apply(GivenLater t1, GivenLater t2, GivenLater t3, T4 t4, GivenLater t5) {
        return Function4.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function4<T1,T2,T3,T5,Function1<T4,R>> apply(GivenLater t1, GivenLater t2, GivenLater t3, GivenEvenLater t4, GivenLater t5) {
        return Function4.split(this, t1, t2, t3, t4, t5);
    }

    public final Function3<T1,T2,T4,R> apply(GivenLater t1, GivenLater t2, T3 t3, GivenLater t4, T5 t5) {
        return Function3.partial(this, t1, t2, t3, t4, t5);
    }

    public final Function3<T1,T2,T4,Function2<T3,T5,R>> apply(GivenLater t1, GivenLater t2, GivenEvenLater t3, GivenLater t4, GivenEvenLater t5) {
        return Function3.split(this, t1, t2, t3, t4, t5);
    }
}
