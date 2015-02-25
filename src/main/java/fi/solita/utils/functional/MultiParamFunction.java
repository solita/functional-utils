package fi.solita.utils.functional;

import java.io.Serializable;

public abstract class MultiParamFunction<T, R> implements Apply<T,R>, Serializable {
    public abstract Function1<T, R> tuppled();
    
    public final R apply(T t) {
        return tuppled().apply(t);
    }
}
