package fi.solita.utils.functional;

import java.io.Serializable;

/**
 * java.util.function.Function but for 5 arguments
 */
public interface Apply5<T1,T2,T3,T4,T5,R> extends Serializable {
    R apply(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5);
}
