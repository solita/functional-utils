package fi.solita.utils.functional;

import java.io.Serializable;

/**
 * One argument function producing no value. Side-effect.
 */
public interface ApplyVoid<T> extends Serializable {
    void accept(T t);
}
