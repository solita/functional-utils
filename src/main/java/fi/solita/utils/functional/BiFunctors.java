package fi.solita.utils.functional;

import java.util.Map;
import java.util.Map.Entry;

public abstract class BiFunctors {
    private BiFunctors() {
    }

    public static final <A, B, C, D> BiFunctor<Map.Entry<?, ?>, A, B, C, D, Map.Entry<A, C>, Map.Entry<B, D>, Map.Entry<B, C>, Map.Entry<A,D>> entry() {
        return new BiFunctor<Map.Entry<?, ?>, A, B, C, D, Map.Entry<A, C>, Map.Entry<B,D>, Map.Entry<B, C>, Map.Entry<A,D>>() {
            @SuppressWarnings("unchecked")
            public Map.Entry<B,D> bimap(Apply<? super A, ? extends B> f1, Apply<? super C, ? extends D> f2, Map.Entry<A, C> e) {
                return (Entry<B, D>) Pair.of(f1.apply(e.getKey()), f2.apply(e.getValue()));
            }

            @SuppressWarnings("unchecked")
            public Map.Entry<B, C> first(Apply<? super A, ? extends B> f, Map.Entry<A, C> e) {
                return (Entry<B, C>) Pair.of(f.apply(e.getKey()), e.getValue());
            }

            @SuppressWarnings("unchecked")
            public Map.Entry<A, D> second(Apply<? super C, ? extends D> f, Map.Entry<A, C> e) {
                return (Entry<A, D>) Pair.of(e.getKey(), f.apply(e.getValue()));
            }
        };
    }
    
    public static final <A, B, C, D> BiFunctor<Either<?, ?>, A, B, C, D, Either<A, C>, Either<B, D>, Either<B, C>, Either<A,D>> either() {
        return new BiFunctor<Either<?, ?>, A, B, C, D, Either<A, C>, Either<B, D>, Either<B, C>, Either<A, D>>() {
            public Either<B, D> bimap(Apply<? super A, ? extends B> f1, Apply<? super C, ? extends D> f2, Either<A, C> e) {
                return e.isLeft() ? Either.<B,D>left(f1.apply(e.left.get())) : Either.<B,D>right(f2.apply(e.right.get()));
            }

            public Either<B, C> first(Apply<? super A, ? extends B> f, Either<A, C> e) {
                return e.isLeft() ? Either.<B,C>left(f.apply(e.left.get())) : Either.<B,C>right(e.right.get());
            }

            public Either<A, D> second(Apply<? super C, ? extends D> f, Either<A, C> e) {
                return e.isLeft() ? Either.<A,D>left(e.left.get()) : Either.<A,D>right(f.apply(e.right.get()));
            }
        };
    }
}