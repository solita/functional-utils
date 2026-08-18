package fi.solita.utils.functional;

import java.io.Serializable;

/**
 * Most basic function (java.util.function.Function<T, R>) from {@code T} to {@code R} 
 */
@Deprecated
public interface Apply<T,R> extends java.util.function.Function<T,R>, Serializable {
}
