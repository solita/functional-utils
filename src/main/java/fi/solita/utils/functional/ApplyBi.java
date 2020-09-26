package fi.solita.utils.functional;

import java.io.Serializable;

/**
 * Two argument function from {@code T1} and {@code T2} to {@code R}
 */
public interface ApplyBi<T1,T2,R> extends Serializable {
    R apply(T1 t1, T2 t2);
}
