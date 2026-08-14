package fi.solita.utils.functional;

import java.io.Serializable;

public abstract class MultiParamFunction<T, R, T1> implements Apply<T, R>, Serializable {
    /**
     * @return this function taking its arguments as a tupple.
     */
    public abstract Apply<T, R> tuppled();
    
    /**
     * @return this function curried.
     */
    public abstract Apply<?, ?> curried();
    
    /**
     * Apply this function to parameters {@code t};
     */
    public final R apply(T t) {
        return tuppled().apply(t);
    }
}
