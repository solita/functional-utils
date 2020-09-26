package fi.solita.utils.functional;

import static fi.solita.utils.functional.Functional.map;

public abstract class Functors {
    private Functors() {
    }
    
    public static final <A,B> Functor<Iterable<?>, A, B, Iterable<A>, Iterable<B>> iterable() {
        return new Functor<Iterable<?>, A, B, Iterable<A>, Iterable<B>>() {
            public Iterable<B> fmap(Apply<? super A, ? extends B> f, Iterable<A> as) {
                return map(f, as);
            }
        };
    }
    
    public static final <A,B> Functor<Option<?>, A, B, Option<A>, Option<B>> option() {
        return new Functor<Option<?>, A, B, Option<A>, Option<B>>() {
            @SuppressWarnings("unchecked")
            public Option<B> fmap(Apply<? super A, ? extends B> f, Option<A> as) {
                return (Option<B>) as.map(f);
            }
        };
    }
    
    public static final <A,B> Functor<Function0<?>, A, B, Function0<A>, Function0<B>> function() {
        return new Functor<Function0<?>, A, B, Function0<A>, Function0<B>>() {
            public Function0<B> fmap(final Apply<? super A, ? extends B> f, final Function0<A> as) {
                return new Function0<B>() {
                    @Override
                    public B apply() {
                        return f.apply(as.apply());
                    }
                };
            }
        };
    }
}