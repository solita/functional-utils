package fi.solita.utils.functional;

import static fi.solita.utils.functional.Option.Some;

public abstract class Functors {
    private Functors() {
    }
    
    public static final <A,B> Functor<Iterable<?>, A, B, Iterable<A>, Iterable<B>> iterable() {
        return new Functor<Iterable<?>, A, B, Iterable<A>, Iterable<B>>() {
            @Override
            public Iterable<B> fmap(Apply<? super A, ? extends B> f, Iterable<A> as) {
                return new Iterables.TransformingIterable<A, B>(as, f);
            }
        };
    }
    
    public static final <A,B> Functor<Option<?>, A, B, Option<A>, Option<B>> option() {
        return new Functor<Option<?>, A, B, Option<A>, Option<B>>() {
            @SuppressWarnings("unchecked")
            @Override
            public Option<B> fmap(Apply<? super A, ? extends B> f, Option<A> as) {
                return (Option<B>) (as.isDefined() ? Some(f.apply(as.get())) : Option.<B>None());
            }
        };
    }
    
    public static final <A,B> Functor<Function0<?>, A, B, Function0<A>, Function0<B>> function() {
        return new Functor<Function0<?>, A, B, Function0<A>, Function0<B>>() {
            @Override
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