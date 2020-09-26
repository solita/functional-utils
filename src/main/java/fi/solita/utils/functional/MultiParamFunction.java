package fi.solita.utils.functional;

import java.io.Serializable;

public abstract class MultiParamFunction<T, R, T1> implements Apply<T,R>, Serializable {
    /**
     * @return this function taking its arguments as a tupple.
     */
    public abstract Apply<T, R> tuppled();
    
    /**
     * @return function composition with {@code next}.
     */
    public abstract <U> Apply<T, U> andThen(final Apply<? super R, ? extends U> next);
    
    /**
     * @return this function curried.
     */
    public abstract Apply<?, ?> curried();
    
    /**
     * partially apply first argument {@code arg1}.
     */
    public abstract Apply<?,R> ap(final T1 arg1);
    
    /**
     * Apply this function to parameters {@code t};
     */
    public final R apply(T t) {
        return tuppled().apply(t);
    }
}
