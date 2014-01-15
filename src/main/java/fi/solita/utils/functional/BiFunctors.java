package fi.solita.utils.functional;

import java.util.Map;

public abstract class BiFunctors {
    private BiFunctors() {
    }

    public static final <A, B, C, D> BiFunctor<Map.Entry<?, ?>, A, B, C, D, Map.Entry<A, C>, Map.Entry<B, D>, Map.Entry<B, C>, Map.Entry<A, B>> entry() {
        return new BiFunctor<Map.Entry<?, ?>, A, B, C, D, Map.Entry<A, C>, Map.Entry<B, D>, Map.Entry<B, C>, Map.Entry<A, B>>() {
            @Override
            public Map.Entry<B, D> bimap(Apply<A, B> f1, Apply<C, D> f2, Map.Entry<A, C> e) {
                return Pair.of(f1.apply(e.getKey()), f2.apply(e.getValue()));
            }

            @Override
            public Map.Entry<B, C> first(Apply<A, B> f, Map.Entry<A, C> e) {
                return Pair.of(f.apply(e.getKey()), e.getValue());
            }

            @Override
            public Map.Entry<A, C> second(Apply<B, C> f, Map.Entry<A, B> e) {
                return Pair.of(e.getKey(), f.apply(e.getValue()));
            }
        };
    }
}