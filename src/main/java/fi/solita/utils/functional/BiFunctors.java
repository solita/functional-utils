package fi.solita.utils.functional;

import java.util.Map;

public abstract class BiFunctors {
    private BiFunctors() {
    }

    public static final <A, B, C, D> BiFunctor<Map.Entry<?, ?>, A, B, C, D, Map.Entry<A, C>, Map.Entry<? extends B, ? extends D>, Map.Entry<? extends B, C>, Map.Entry<? extends A,? extends D>> entry() {
        return new BiFunctor<Map.Entry<?, ?>, A, B, C, D, Map.Entry<A, C>, Map.Entry<? extends B, ? extends D>, Map.Entry<? extends B, C>, Map.Entry<? extends A,? extends D>>() {
            @Override
            public Map.Entry<? extends B, ? extends D> bimap(Apply<? super A, ? extends B> f1, Apply<? super C, ? extends D> f2, Map.Entry<A, C> e) {
                return Pair.of(f1.apply(e.getKey()), f2.apply(e.getValue()));
            }

            @Override
            public Map.Entry<? extends B, C> first(Apply<? super A, ? extends B> f, Map.Entry<A, C> e) {
                return Pair.of(f.apply(e.getKey()), e.getValue());
            }

            @Override
            public Map.Entry<A, ? extends D> second(Apply<? super C, ? extends D> f, Map.Entry<A, C> e) {
                return Pair.of(e.getKey(), f.apply(e.getValue()));
            }
        };
    }
}