package fi.solita.utils.functional;

import java.util.function.BiFunction;

/**
 * Closed and associative binary operation.
 */
public interface SemiGroup<T> extends BiFunction<T,T,T> {
    
}
