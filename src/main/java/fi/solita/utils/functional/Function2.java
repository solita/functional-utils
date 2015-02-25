package fi.solita.utils.functional;

import java.util.Map;

import fi.solita.utils.functional.Function.GivenEvenLater;
import fi.solita.utils.functional.Function.GivenLater;

public abstract class Function2<T1, T2, R> extends MultiParamFunction<Map.Entry<T1,T2>, R> {

    public abstract R apply(T1 t1, T2 t2);
    
    public final <U> Function2<T1, T2, U> andThen(final Apply<? super R, ? extends U> next) {
        final Function2<T1, T2, R> self = this;
        return new Function2<T1, T2, U>() {
            @Override
            public U apply(T1 t1, T2 t2) {
                return next.apply(self.apply(t1, t2));
            }
        };
    }
    
    @Override
    public final Function1<Map.Entry<T1, T2>, R> tuppled() {
        return new Function1<Map.Entry<T1, T2>, R>() {
            @Override
            public R apply(Map.Entry<T1, T2> t) {
                return Function2.this.apply(t.getKey(), t.getValue());
            }
        };
    }
    
    public final Function1<T1, Function1<T2, R>> curried() {
        return new Function1<T1, Function1<T2,R>>() {
            @Override
            public Function1<T2, R> apply(final T1 t1) {
                return new Function1<T2, R>() {
                    @Override
                    public R apply(T2 t2) {
                        return Function2.this.apply(t1, t2);
                    }
                };
            }
        };
    }
    
    public final Function1<T2,R> ap(final T1 t) {
        return new Function1<T2,R>() {
            @Override
            public R apply(T2 t2) {
                return Function2.this.apply(t, t2);
            }
        };
    }
    
    static final <T1,T2,R> Function2<T1,T2,R> partial(final Apply<? extends Tuple,R> f, final Object... paramsAndPlaceholders) {
        return new Function2<T1,T2,R>() {
            @SuppressWarnings("unchecked")
            @Override
            public R apply(T1 t1, T2 t2) {
                return PartialApplicationHelper.doApply((Apply<Tuple,R>)f, paramsAndPlaceholders, t1, t2);
            }
        };
    }
    
    static final <T1,T2,R,FR extends Apply<?,R>> Function2<T1,T2,FR> split(final Apply<? extends Tuple,R> f, final Object... placeholders) {
        return new Function2<T1,T2,FR>() {
            @Override
            public FR apply(T1 t1, T2 t2) {
                return PartialApplicationHelper.makeSecondFunc(f, placeholders, t1, t2);
            }
        };
    }
    
    public final Function1<T2,R> apply(final T1 t1, GivenLater _2) {
        return new Function1<T2,R>() {
            @Override
            public R apply(T2 t2) {
                return Function2.this.apply(t1, t2);
            }
        };
    }

    public final Function1<T1,R> apply(GivenLater _1, final T2 t2) {
        return new Function1<T1,R>() {
            @Override
            public R apply(T1 t1) {
                return Function2.this.apply(t1, t2);
            }
        };
    }
    
    public final Function1<T1,Function1<T2,R>> apply(GivenLater _1, GivenEvenLater _2) {
        return new Function1<T1, Function1<T2,R>>() {
            @Override
            public Function1<T2, R> apply(T1 t) {
                return apply(t);
            }
        };
    }
    
    public final Function1<T2,Function1<T1,R>> apply(GivenEvenLater _1, GivenLater _2) {
        return new Function1<T2, Function1<T1,R>>() {
            @Override
            public Function1<T1, R> apply(T2 t) {
                return apply(t);
            }
        };
    }
}
