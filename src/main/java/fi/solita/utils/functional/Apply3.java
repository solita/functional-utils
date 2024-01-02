package fi.solita.utils.functional;

import java.io.Serializable;

/**
 * java.util.function.Function but for 3 arguments
 */
public interface Apply3<T1,T2,T3,R> extends Serializable {
    R apply(T1 t1, T2 t2, T3 t3);
}
