package fi.solita.utils.functional;

import java.io.Serializable;

/**
 * Two argument function producing no value. Side-effect.
 */
public interface ApplyBiVoid<T1,T2> extends Serializable {
    void accept(T1 t1, T2 t2);
}
