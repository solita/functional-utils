package fi.solita.utils.functional;

import java.io.Serializable;

/**
 * Three argument function producing no value. Side-effect.
 */
public interface ApplyVoid3<T1,T2, T3> extends Serializable {
    void accept(T1 t1, T2 t2, T3 t3);
}
