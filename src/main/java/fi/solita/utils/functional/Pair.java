package fi.solita.utils.functional;

import java.util.Map;

/**
 * Specialized Tuple2<LEFT, RIGHT>.
 */
public class Pair<LEFT, RIGHT> extends Tuple2<LEFT, RIGHT> {

    /**
     * @return a pair of {@code left} and {@code right}.
     */
    public static <LEFT, RIGHT> Pair<LEFT, RIGHT> of(LEFT left, RIGHT right) {
        return new Pair<LEFT, RIGHT>(left, right);
    }
    
    public static <T, LEFT, RIGHT> java.util.function.Function<T, Pair<LEFT, RIGHT>> fanout(final java.util.function.Function<? super T, ? extends LEFT> l, final java.util.function.Function<? super T, ? extends RIGHT> r) {
        return new Function1<T, Pair<LEFT,RIGHT>>() {
            @Override
            public Pair<LEFT, RIGHT> apply(T t) {
                return Pair.of(l.apply(t), r.apply(t));
            }
        };
    }
    
    public Pair(LEFT left, RIGHT right) {
        super(left, right);
    }
    
    /**
     * @return left value.
     */
    public final LEFT left() {
        return _1;
    }
    
    /**
     * @return right value.
     */
    public final RIGHT right() {
        return _2;
    }
    
    @Override
    public final <A,B> Pair<A,B> bimap(java.util.function.Function<? super LEFT, ? extends A> fLeft, java.util.function.Function<? super RIGHT, ? extends B> fRight) {
        Map.Entry<A, B> ret = BiFunctors.<LEFT,A,RIGHT,B>entry().bimap(fLeft, fRight, this);
        return Pair.of(ret.getKey(), ret.getValue());
    }

    @Override
    public final <A> Pair<A,RIGHT> first(java.util.function.Function<? super LEFT, ? extends A> f) {
        Map.Entry<A, RIGHT> ret = BiFunctors.<LEFT,A,RIGHT,RIGHT>entry().first(f, this);
        return Pair.of(ret.getKey(), ret.getValue());
    }

    @Override
    public final <B> Pair<LEFT,B> second(java.util.function.Function<? super RIGHT, ? extends B> f) {
        Map.Entry<LEFT, B> ret = BiFunctors.<LEFT,LEFT,RIGHT,B>entry().second(f, this);
        return Pair.of(ret.getKey(), ret.getValue());
    }
}
