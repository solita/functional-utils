package fi.solita.utils.functional;

import java.io.Serializable;

/**
 * java.util.function.Function but for 2 arguments
 */
public interface Apply2<T1,T2,R> extends Serializable {
    R apply(T1 t1, T2 t2);
}
