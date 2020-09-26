package fi.solita.utils.functional;

import java.io.Serializable;

/**
 * Most basic function from {@code T} to {@code R} 
 */
public interface Apply<T,R> extends Serializable {
    R apply(T t);
}
