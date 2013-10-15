package fi.solita.utils.functional;

import fi.solita.utils.codegen.NoMetadataGeneration;
import fi.solita.utils.functional.Function.GivenEvenLater;
import fi.solita.utils.functional.Function.GivenLater;

@NoMetadataGeneration
public abstract class Function6<T1, T2, T3, T4, T5, T6, R> extends MultiParamFunction<Tuple6<T1, T2, T3, T4, T5, T6>, R> {

    public abstract R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6);

    public final <U> Function6<T1, T2, T3, T4, T5, T6, U> andThen(final Apply<? super R, ? extends U> next) {
        final Function6<T1, T2, T3, T4, T5, T6, R> self = this;
        return new Function6<T1, T2, T3, T4, T5, T6, U>() {
            @Override
            public U apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
                return next.apply(self.apply(t1, t2, t3, t4, t5, t6));
            }
        };
    }

    public final Function1<Tuple6<T1, T2, T3, T4, T5, T6>, R> tuppled() {
        return new Function1<Tuple6<T1, T2, T3, T4, T5, T6>, R>() {
            @Override
            public R apply(Tuple6<T1, T2, T3, T4, T5, T6> t) {
                return Function6.this.apply(t._1, t._2, t._3, t._4, t._5, t._6);
            }
        };
    }
    
    public Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, R>>>>>> curried() {
        return new Function1<T1, Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, R>>>>>>() {
            @Override
            public Function1<T2, Function1<T3, Function1<T4, Function1<T5, Function1<T6, R>>>>> apply(final T1 t1) {
                return new Function5<T2, T3, T4, T5, T6, R>() {
                    @Override
                    public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
                        return Function6.this.apply(t1, t2, t3, t4, t5, t6);
                    }
                }.curried();
            }
        };
    }
    
    public final Function1<T6,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4, final T5 t5) {
        return ap(t1).ap(t2).ap(t3).ap(t4).ap(t5);
    }
    
    public final Function2<T5,T6,R> ap(final T1 t1, final T2 t2, final T3 t3, final T4 t4) {
        return ap(t1).ap(t2).ap(t3).ap(t4);
    }
    
    public final Function3<T4,T5,T6,R> ap(final T1 t1, final T2 t2, final T3 t3) {
        return ap(t1).ap(t2).ap(t3);
    }
    
    public final Function4<T3,T4,T5,T6,R> ap(final T1 t1, final T2 t2) {
        return ap(t1).ap(t2);
    }
    
    public final Function5<T2,T3,T4,T5,T6,R> ap(final T1 t) {
        return new Function5<T2,T3,T4,T5,T6,R>() {
            @Override
            public R apply(T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
                return Function6.this.apply(t, t2, t3, t4, t5, t6);
            }
        };
    }
    
    static final <T1,T2,T3,T4,T5,T6,R> Function6<T1,T2,T3,T4,T5,T6,R> partial(final Apply<? extends Tuple,R> f, final Object... paramsAndPlaceholders) {
        return new Function6<T1,T2,T3,T4,T5,T6,R>() {
            @SuppressWarnings("unchecked")
            @Override
            public R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
                return PartialApplicationHelper.doApply((Apply<Tuple,R>)f, paramsAndPlaceholders, t1, t2, t3, t4, t5, t6);
            }
        };
    }
    
    static final <T1,T2,T3,T4,T5,T6,R,FR extends Apply<?,R>> Function6<T1,T2,T3,T4,T5,T6,FR> split(final Apply<? extends Tuple,R> f, final Object... placeholders) {
        return new Function6<T1,T2,T3,T4,T5,T6,FR>() {
            @Override
            public FR apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
                return PartialApplicationHelper.makeSecondFunc(f, placeholders, t1, t2, t3, t4, t5, t6);
            }
        };
    }
    
    public final Function2<T1,T3,R> apply(GivenLater t1, T2 t2, GivenLater t3, T4 t4, T5 t5, T6 t6) {
        return Function2.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T1,T3,Function4<T2,T4,T5,T6,R>> apply(GivenLater t1, GivenEvenLater t2, GivenLater t3, GivenEvenLater t4, GivenEvenLater t5, GivenEvenLater t6) {
        return Function2.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function5<T2,T3,T4,T5,T6,R> apply(T1 t1, GivenLater t2, GivenLater t3, GivenLater t4, GivenLater t5, GivenLater t6) {
        return Function5.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function5<T2,T3,T4,T5,T6,Function1<T1,R>> apply(GivenEvenLater t1, GivenLater t2, GivenLater t3, GivenLater t4, GivenLater t5, GivenLater t6) {
        return Function5.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T1,T2,T4,T6,R> apply(GivenLater t1, GivenLater t2, T3 t3, GivenLater t4, T5 t5, GivenLater t6) {
        return Function4.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T1,T2,T4,T6,Function2<T3,T5,R>> apply(GivenLater t1, GivenLater t2, GivenEvenLater t3, GivenLater t4, GivenEvenLater t5, GivenLater t6) {
        return Function4.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function1<T5,R> apply(T1 t1, T2 t2, T3 t3, T4 t4, GivenLater t5, T6 t6) {
        return Function1.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function1<T5,Function5<T1,T2,T3,T4,T6,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenLater t5, GivenEvenLater t6) {
        return Function1.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T1,T2,T3,T6,R> apply(GivenLater t1, GivenLater t2, GivenLater t3, T4 t4, T5 t5, GivenLater t6) {
        return Function4.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T1,T2,T3,T6,Function2<T4,T5,R>> apply(GivenLater t1, GivenLater t2, GivenLater t3, GivenEvenLater t4, GivenEvenLater t5, GivenLater t6) {
        return Function4.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function5<T1,T2,T4,T5,T6,R> apply(GivenLater t1, GivenLater t2, T3 t3, GivenLater t4, GivenLater t5, GivenLater t6) {
        return Function5.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function5<T1,T2,T4,T5,T6,Function1<T3,R>> apply(GivenLater t1, GivenLater t2, GivenEvenLater t3, GivenLater t4, GivenLater t5, GivenLater t6) {
        return Function5.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T2,T4,T5,R> apply(T1 t1, GivenLater t2, T3 t3, GivenLater t4, GivenLater t5, T6 t6) {
        return Function3.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T2,T4,T5,Function3<T1,T3,T6,R>> apply(GivenEvenLater t1, GivenLater t2, GivenEvenLater t3, GivenLater t4, GivenLater t5, GivenEvenLater t6) {
        return Function3.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T2,T4,T5,T6,R> apply(T1 t1, GivenLater t2, T3 t3, GivenLater t4, GivenLater t5, GivenLater t6) {
        return Function4.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T2,T4,T5,T6,Function2<T1,T3,R>> apply(GivenEvenLater t1, GivenLater t2, GivenEvenLater t3, GivenLater t4, GivenLater t5, GivenLater t6) {
        return Function4.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function5<T1,T2,T3,T4,T5,R> apply(GivenLater t1, GivenLater t2, GivenLater t3, GivenLater t4, GivenLater t5, T6 t6) {
        return Function5.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function5<T1,T2,T3,T4,T5,Function1<T6,R>> apply(GivenLater t1, GivenLater t2, GivenLater t3, GivenLater t4, GivenLater t5, GivenEvenLater t6) {
        return Function5.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T1,T3,T4,R> apply(GivenLater t1, T2 t2, GivenLater t3, GivenLater t4, T5 t5, T6 t6) {
        return Function3.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T1,T3,T4,Function3<T2,T5,T6,R>> apply(GivenLater t1, GivenEvenLater t2, GivenLater t3, GivenLater t4, GivenEvenLater t5, GivenEvenLater t6) {
        return Function3.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T2,T3,T5,R> apply(T1 t1, GivenLater t2, GivenLater t3, T4 t4, GivenLater t5, T6 t6) {
        return Function3.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T2,T3,T5,Function3<T1,T4,T6,R>> apply(GivenEvenLater t1, GivenLater t2, GivenLater t3, GivenEvenLater t4, GivenLater t5, GivenEvenLater t6) {
        return Function3.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T2,T4,R> apply(T1 t1, GivenLater t2, T3 t3, GivenLater t4, T5 t5, T6 t6) {
        return Function2.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T2,T4,Function4<T1,T3,T5,T6,R>> apply(GivenEvenLater t1, GivenLater t2, GivenEvenLater t3, GivenLater t4, GivenEvenLater t5, GivenEvenLater t6) {
        return Function2.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T2,T5,T6,R> apply(T1 t1, GivenLater t2, T3 t3, T4 t4, GivenLater t5, GivenLater t6) {
        return Function3.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T2,T5,T6,Function3<T1,T3,T4,R>> apply(GivenEvenLater t1, GivenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenLater t5, GivenLater t6) {
        return Function3.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T2,T3,R> apply(T1 t1, GivenLater t2, GivenLater t3, T4 t4, T5 t5, T6 t6) {
        return Function2.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T2,T3,Function4<T1,T4,T5,T6,R>> apply(GivenEvenLater t1, GivenLater t2, GivenLater t3, GivenEvenLater t4, GivenEvenLater t5, GivenEvenLater t6) {
        return Function2.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function1<T4,R> apply(T1 t1, T2 t2, T3 t3, GivenLater t4, T5 t5, T6 t6) {
        return Function1.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function1<T4,Function5<T1,T2,T3,T5,T6,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenLater t4, GivenEvenLater t5, GivenEvenLater t6) {
        return Function1.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T1,T3,T6,R> apply(GivenLater t1, T2 t2, GivenLater t3, T4 t4, T5 t5, GivenLater t6) {
        return Function3.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T1,T3,T6,Function3<T2,T4,T5,R>> apply(GivenLater t1, GivenEvenLater t2, GivenLater t3, GivenEvenLater t4, GivenEvenLater t5, GivenLater t6) {
        return Function3.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T1,T2,T5,R> apply(GivenLater t1, GivenLater t2, T3 t3, T4 t4, GivenLater t5, T6 t6) {
        return Function3.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T1,T2,T5,Function3<T3,T4,T6,R>> apply(GivenLater t1, GivenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenLater t5, GivenEvenLater t6) {
        return Function3.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T5,T6,R> apply(T1 t1, T2 t2, T3 t3, T4 t4, GivenLater t5, GivenLater t6) {
        return Function2.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T5,T6,Function4<T1,T2,T3,T4,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenLater t5, GivenLater t6) {
        return Function2.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T1,T2,T3,T5,R> apply(GivenLater t1, GivenLater t2, GivenLater t3, T4 t4, GivenLater t5, T6 t6) {
        return Function4.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T1,T2,T3,T5,Function2<T4,T6,R>> apply(GivenLater t1, GivenLater t2, GivenLater t3, GivenEvenLater t4, GivenLater t5, GivenEvenLater t6) {
        return Function4.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T3,T4,T5,R> apply(T1 t1, T2 t2, GivenLater t3, GivenLater t4, GivenLater t5, T6 t6) {
        return Function3.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T3,T4,T5,Function3<T1,T2,T6,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenLater t3, GivenLater t4, GivenLater t5, GivenEvenLater t6) {
        return Function3.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T3,T4,R> apply(T1 t1, T2 t2, GivenLater t3, GivenLater t4, T5 t5, T6 t6) {
        return Function2.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T3,T4,Function4<T1,T2,T5,T6,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenLater t3, GivenLater t4, GivenEvenLater t5, GivenEvenLater t6) {
        return Function2.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T3,T5,T6,R> apply(T1 t1, T2 t2, GivenLater t3, T4 t4, GivenLater t5, GivenLater t6) {
        return Function3.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T3,T5,T6,Function3<T1,T2,T4,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenLater t3, GivenEvenLater t4, GivenLater t5, GivenLater t6) {
        return Function3.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T1,T5,R> apply(GivenLater t1, T2 t2, T3 t3, T4 t4, GivenLater t5, T6 t6) {
        return Function2.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T1,T5,Function4<T2,T3,T4,T6,R>> apply(GivenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenLater t5, GivenEvenLater t6) {
        return Function2.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T1,T4,T5,R> apply(GivenLater t1, T2 t2, T3 t3, GivenLater t4, GivenLater t5, T6 t6) {
        return Function3.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T1,T4,T5,Function3<T2,T3,T6,R>> apply(GivenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenLater t4, GivenLater t5, GivenEvenLater t6) {
        return Function3.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T1,T2,T3,R> apply(GivenLater t1, GivenLater t2, GivenLater t3, T4 t4, T5 t5, T6 t6) {
        return Function3.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T1,T2,T3,Function3<T4,T5,T6,R>> apply(GivenLater t1, GivenLater t2, GivenLater t3, GivenEvenLater t4, GivenEvenLater t5, GivenEvenLater t6) {
        return Function3.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T1,T2,T6,R> apply(GivenLater t1, GivenLater t2, T3 t3, T4 t4, T5 t5, GivenLater t6) {
        return Function3.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T1,T2,T6,Function3<T3,T4,T5,R>> apply(GivenLater t1, GivenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenEvenLater t5, GivenLater t6) {
        return Function3.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T3,T4,T6,R> apply(T1 t1, T2 t2, GivenLater t3, GivenLater t4, T5 t5, GivenLater t6) {
        return Function3.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T3,T4,T6,Function3<T1,T2,T5,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenLater t3, GivenLater t4, GivenEvenLater t5, GivenLater t6) {
        return Function3.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T1,T2,T4,T5,R> apply(GivenLater t1, GivenLater t2, T3 t3, GivenLater t4, GivenLater t5, T6 t6) {
        return Function4.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T1,T2,T4,T5,Function2<T3,T6,R>> apply(GivenLater t1, GivenLater t2, GivenEvenLater t3, GivenLater t4, GivenLater t5, GivenEvenLater t6) {
        return Function4.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function1<T2,R> apply(T1 t1, GivenLater t2, T3 t3, T4 t4, T5 t5, T6 t6) {
        return Function1.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function1<T2,Function5<T1,T3,T4,T5,T6,R>> apply(GivenEvenLater t1, GivenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenEvenLater t5, GivenEvenLater t6) {
        return Function1.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T4,T5,R> apply(T1 t1, T2 t2, T3 t3, GivenLater t4, GivenLater t5, T6 t6) {
        return Function2.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T4,T5,Function4<T1,T2,T3,T6,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenLater t4, GivenLater t5, GivenEvenLater t6) {
        return Function2.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T1,T6,R> apply(GivenLater t1, T2 t2, T3 t3, T4 t4, T5 t5, GivenLater t6) {
        return Function2.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T1,T6,Function4<T2,T3,T4,T5,R>> apply(GivenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenEvenLater t5, GivenLater t6) {
        return Function2.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function1<T3,R> apply(T1 t1, T2 t2, GivenLater t3, T4 t4, T5 t5, T6 t6) {
        return Function1.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function1<T3,Function5<T1,T2,T4,T5,T6,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenLater t3, GivenEvenLater t4, GivenEvenLater t5, GivenEvenLater t6) {
        return Function1.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T1,T4,R> apply(GivenLater t1, T2 t2, T3 t3, GivenLater t4, T5 t5, T6 t6) {
        return Function2.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T1,T4,Function4<T2,T3,T5,T6,R>> apply(GivenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenLater t4, GivenEvenLater t5, GivenEvenLater t6) {
        return Function2.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T1,T3,T4,T5,R> apply(GivenLater t1, T2 t2, GivenLater t3, GivenLater t4, GivenLater t5, T6 t6) {
        return Function4.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T1,T3,T4,T5,Function2<T2,T6,R>> apply(GivenLater t1, GivenEvenLater t2, GivenLater t3, GivenLater t4, GivenLater t5, GivenEvenLater t6) {
        return Function4.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T1,T2,R> apply(GivenLater t1, GivenLater t2, T3 t3, T4 t4, T5 t5, T6 t6) {
        return Function2.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T1,T2,Function4<T3,T4,T5,T6,R>> apply(GivenLater t1, GivenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenEvenLater t5, GivenEvenLater t6) {
        return Function2.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T1,T3,T5,T6,R> apply(GivenLater t1, T2 t2, GivenLater t3, T4 t4, GivenLater t5, GivenLater t6) {
        return Function4.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T1,T3,T5,T6,Function2<T2,T4,R>> apply(GivenLater t1, GivenEvenLater t2, GivenLater t3, GivenEvenLater t4, GivenLater t5, GivenLater t6) {
        return Function4.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T2,T3,T4,T5,R> apply(T1 t1, GivenLater t2, GivenLater t3, GivenLater t4, GivenLater t5, T6 t6) {
        return Function4.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T2,T3,T4,T5,Function2<T1,T6,R>> apply(GivenEvenLater t1, GivenLater t2, GivenLater t3, GivenLater t4, GivenLater t5, GivenEvenLater t6) {
        return Function4.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T1,T3,T4,T6,R> apply(GivenLater t1, T2 t2, GivenLater t3, GivenLater t4, T5 t5, GivenLater t6) {
        return Function4.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T1,T3,T4,T6,Function2<T2,T5,R>> apply(GivenLater t1, GivenEvenLater t2, GivenLater t3, GivenLater t4, GivenEvenLater t5, GivenLater t6) {
        return Function4.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T2,T3,T5,T6,R> apply(T1 t1, GivenLater t2, GivenLater t3, T4 t4, GivenLater t5, GivenLater t6) {
        return Function4.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T2,T3,T5,T6,Function2<T1,T4,R>> apply(GivenEvenLater t1, GivenLater t2, GivenLater t3, GivenEvenLater t4, GivenLater t5, GivenLater t6) {
        return Function4.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T2,T4,T6,R> apply(T1 t1, GivenLater t2, T3 t3, GivenLater t4, T5 t5, GivenLater t6) {
        return Function3.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T2,T4,T6,Function3<T1,T3,T5,R>> apply(GivenEvenLater t1, GivenLater t2, GivenEvenLater t3, GivenLater t4, GivenEvenLater t5, GivenLater t6) {
        return Function3.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T3,T5,R> apply(T1 t1, T2 t2, GivenLater t3, T4 t4, GivenLater t5, T6 t6) {
        return Function2.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T3,T5,Function4<T1,T2,T4,T6,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenLater t3, GivenEvenLater t4, GivenLater t5, GivenEvenLater t6) {
        return Function2.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T1,T2,T5,T6,R> apply(GivenLater t1, GivenLater t2, T3 t3, T4 t4, GivenLater t5, GivenLater t6) {
        return Function4.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T1,T2,T5,T6,Function2<T3,T4,R>> apply(GivenLater t1, GivenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenLater t5, GivenLater t6) {
        return Function4.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function5<T1,T2,T3,T4,T6,R> apply(GivenLater t1, GivenLater t2, GivenLater t3, GivenLater t4, T5 t5, GivenLater t6) {
        return Function5.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function5<T1,T2,T3,T4,T6,Function1<T5,R>> apply(GivenLater t1, GivenLater t2, GivenLater t3, GivenLater t4, GivenEvenLater t5, GivenLater t6) {
        return Function5.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function5<T1,T3,T4,T5,T6,R> apply(GivenLater t1, T2 t2, GivenLater t3, GivenLater t4, GivenLater t5, GivenLater t6) {
        return Function5.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function5<T1,T3,T4,T5,T6,Function1<T2,R>> apply(GivenLater t1, GivenEvenLater t2, GivenLater t3, GivenLater t4, GivenLater t5, GivenLater t6) {
        return Function5.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T1,T2,T3,T4,R> apply(GivenLater t1, GivenLater t2, GivenLater t3, GivenLater t4, T5 t5, T6 t6) {
        return Function4.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T1,T2,T3,T4,Function2<T5,T6,R>> apply(GivenLater t1, GivenLater t2, GivenLater t3, GivenLater t4, GivenEvenLater t5, GivenEvenLater t6) {
        return Function4.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function1<T1,R> apply(GivenLater t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6) {
        return Function1.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function1<T1,Function5<T2,T3,T4,T5,T6,R>> apply(GivenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenEvenLater t5, GivenEvenLater t6) {
        return Function1.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T1,T4,T6,R> apply(GivenLater t1, T2 t2, T3 t3, GivenLater t4, T5 t5, GivenLater t6) {
        return Function3.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T1,T4,T6,Function3<T2,T3,T5,R>> apply(GivenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenLater t4, GivenEvenLater t5, GivenLater t6) {
        return Function3.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T3,T6,R> apply(T1 t1, T2 t2, GivenLater t3, T4 t4, T5 t5, GivenLater t6) {
        return Function2.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T3,T6,Function4<T1,T2,T4,T5,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenLater t3, GivenEvenLater t4, GivenEvenLater t5, GivenLater t6) {
        return Function2.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T1,T4,T5,T6,R> apply(GivenLater t1, T2 t2, T3 t3, GivenLater t4, GivenLater t5, GivenLater t6) {
        return Function4.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T1,T4,T5,T6,Function2<T2,T3,R>> apply(GivenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenLater t4, GivenLater t5, GivenLater t6) {
        return Function4.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T1,T5,T6,R> apply(GivenLater t1, T2 t2, T3 t3, T4 t4, GivenLater t5, GivenLater t6) {
        return Function3.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T1,T5,T6,Function3<T2,T3,T4,R>> apply(GivenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenLater t5, GivenLater t6) {
        return Function3.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function1<T6,R> apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, GivenLater t6) {
        return Function1.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function1<T6,Function5<T1,T2,T3,T4,T5,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenEvenLater t5, GivenLater t6) {
        return Function1.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function5<T1,T2,T3,T5,T6,R> apply(GivenLater t1, GivenLater t2, GivenLater t3, T4 t4, GivenLater t5, GivenLater t6) {
        return Function5.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function5<T1,T2,T3,T5,T6,Function1<T4,R>> apply(GivenLater t1, GivenLater t2, GivenLater t3, GivenEvenLater t4, GivenLater t5, GivenLater t6) {
        return Function5.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T1,T3,T5,R> apply(GivenLater t1, T2 t2, GivenLater t3, T4 t4, GivenLater t5, T6 t6) {
        return Function3.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T1,T3,T5,Function3<T2,T4,T6,R>> apply(GivenLater t1, GivenEvenLater t2, GivenLater t3, GivenEvenLater t4, GivenLater t5, GivenEvenLater t6) {
        return Function3.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T2,T3,T4,T6,R> apply(T1 t1, GivenLater t2, GivenLater t3, GivenLater t4, T5 t5, GivenLater t6) {
        return Function4.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T2,T3,T4,T6,Function2<T1,T5,R>> apply(GivenEvenLater t1, GivenLater t2, GivenLater t3, GivenLater t4, GivenEvenLater t5, GivenLater t6) {
        return Function4.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T4,T6,R> apply(T1 t1, T2 t2, T3 t3, GivenLater t4, T5 t5, GivenLater t6) {
        return Function2.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T4,T6,Function4<T1,T2,T3,T5,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenLater t4, GivenEvenLater t5, GivenLater t6) {
        return Function2.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T2,T5,R> apply(T1 t1, GivenLater t2, T3 t3, T4 t4, GivenLater t5, T6 t6) {
        return Function2.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T2,T5,Function4<T1,T3,T4,T6,R>> apply(GivenEvenLater t1, GivenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenLater t5, GivenEvenLater t6) {
        return Function2.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T1,T2,T4,R> apply(GivenLater t1, GivenLater t2, T3 t3, GivenLater t4, T5 t5, T6 t6) {
        return Function3.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T1,T2,T4,Function3<T3,T5,T6,R>> apply(GivenLater t1, GivenLater t2, GivenEvenLater t3, GivenLater t4, GivenEvenLater t5, GivenEvenLater t6) {
        return Function3.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T2,T6,R> apply(T1 t1, GivenLater t2, T3 t3, T4 t4, T5 t5, GivenLater t6) {
        return Function2.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function2<T2,T6,Function4<T1,T3,T4,T5,R>> apply(GivenEvenLater t1, GivenLater t2, GivenEvenLater t3, GivenEvenLater t4, GivenEvenLater t5, GivenLater t6) {
        return Function2.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T4,T5,T6,R> apply(T1 t1, T2 t2, T3 t3, GivenLater t4, GivenLater t5, GivenLater t6) {
        return Function3.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T4,T5,T6,Function3<T1,T2,T3,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenEvenLater t3, GivenLater t4, GivenLater t5, GivenLater t6) {
        return Function3.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T2,T3,T6,R> apply(T1 t1, GivenLater t2, GivenLater t3, T4 t4, T5 t5, GivenLater t6) {
        return Function3.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T2,T3,T6,Function3<T1,T4,T5,R>> apply(GivenEvenLater t1, GivenLater t2, GivenLater t3, GivenEvenLater t4, GivenEvenLater t5, GivenLater t6) {
        return Function3.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T2,T3,T4,R> apply(T1 t1, GivenLater t2, GivenLater t3, GivenLater t4, T5 t5, T6 t6) {
        return Function3.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function3<T2,T3,T4,Function3<T1,T5,T6,R>> apply(GivenEvenLater t1, GivenLater t2, GivenLater t3, GivenLater t4, GivenEvenLater t5, GivenEvenLater t6) {
        return Function3.split(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T3,T4,T5,T6,R> apply(T1 t1, T2 t2, GivenLater t3, GivenLater t4, GivenLater t5, GivenLater t6) {
        return Function4.partial(this, t1, t2, t3, t4, t5, t6);
    }

    public final Function4<T3,T4,T5,T6,Function2<T1,T2,R>> apply(GivenEvenLater t1, GivenEvenLater t2, GivenLater t3, GivenLater t4, GivenLater t5, GivenLater t6) {
        return Function4.split(this, t1, t2, t3, t4, t5, t6);
    }
}
