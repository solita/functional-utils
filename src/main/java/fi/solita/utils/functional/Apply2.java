package fi.solita.utils.functional;

import java.io.Serializable;

/**
 * java.util.function.Function but for 2 arguments
 */
@Deprecated
public interface Apply2<T1,T2,R> extends java.util.function.BiFunction<T1, T2, R>, Serializable {
}
