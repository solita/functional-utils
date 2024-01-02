package fi.solita.utils.functional;

import java.io.Serializable;

/**
 * Most basic function (java.util.function.Function<T, R>) from {@code T} to {@code R} 
 */
public interface Apply<T,R> extends Serializable {
    R apply(T t);
}
