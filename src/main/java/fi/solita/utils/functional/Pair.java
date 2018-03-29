package fi.solita.utils.functional;

import java.util.Map;

public class Pair<LEFT, RIGHT> extends Tuple2<LEFT, RIGHT> {

    public static <LEFT, RIGHT> Pair<LEFT, RIGHT> of(LEFT left, RIGHT right) {
        return new Pair<LEFT, RIGHT>(left, right);
    }
    
    public Pair(LEFT left, RIGHT right) {
        super(left, right);
    }
    
    public final LEFT left() {
        return _1;
    }
    
    public final RIGHT right() {
        return _2;
    }
    
    public final <A,B> Pair<A,B> bimap(Apply<? super LEFT, ? extends A> f, Apply<? super RIGHT, ? extends B> f2) {
        Map.Entry<A, B> ret = BiFunctors.<LEFT,A,RIGHT,B>entry().bimap(f, f2, this);
        return Pair.of(ret.getKey(), ret.getValue());
    }

    public final <A> Pair<A,RIGHT> first(Apply<? super LEFT, ? extends A> f) {
        Map.Entry<A, RIGHT> ret = BiFunctors.<LEFT,A,RIGHT,RIGHT>entry().first(f, this);
        return Pair.of(ret.getKey(), ret.getValue());
    }

    public final <B> Pair<LEFT,B> second(Apply<? super RIGHT, ? extends B> f) {
        Map.Entry<LEFT, B> ret = BiFunctors.<LEFT,LEFT,RIGHT,B>entry().second(f, this);
        return Pair.of(ret.getKey(), ret.getValue());
    }
}
