package fi.solita.utils.functional;

import java.io.Serializable;

public abstract class MultiParamFunction<T, R, T1> implements java.util.function.Function<T, R>, Serializable {
    /**
     * @return this function taking its arguments as a tupple.
     */
    public abstract java.util.function.Function<T, R> tuppled();
    
    /**
     * @return function composition with {@code next}.
     */
    public abstract <U> java.util.function.Function<T, U> andThen(final java.util.function.Function<? super R, ? extends U> next);
    
    /**
     * @return this function curried.
     */
    public abstract java.util.function.Function<?, ?> curried();
    
    /**
     * partially apply first argument {@code arg1}.
     */
    public abstract java.util.function.Function<?,R> ap(final T1 arg1);
    
    /**
     * Function this function to parameters {@code t};
     */
    public final R apply(T t) {
        return tuppled().apply(t);
    }
}
