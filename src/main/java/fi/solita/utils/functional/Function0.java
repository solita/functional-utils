package fi.solita.utils.functional;

import java.io.Serializable;

public abstract class Function0<R> implements Apply<Tuple0,R>, Serializable {

    public abstract R apply();
    
    public <U> Function0<U> andThen(final Apply<? super R, ? extends U> next) {
        return new Function0<U>() {
            @Override
            public U apply() {
                return next.apply(Function0.this.apply());
            }
        };
    }
    
    @Override
    public final R apply(Tuple0 t) {
        return apply();
    }
}
