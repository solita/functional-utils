package fi.solita.utils.functional;

import java.io.Serializable;

/**
 * Five argument function producing no value. Side-effect.
 */
public interface ApplyVoid5<T1,T2,T3,T4,T5> extends Serializable {
    void accept(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5);
}
