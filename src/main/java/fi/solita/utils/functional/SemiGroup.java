package fi.solita.utils.functional;

import java.util.Map;

/**
 * Closed and associative binary operation.
 */
public interface SemiGroup<T> extends Apply<Map.Entry<? extends T,? extends T>, T> {
    
}
