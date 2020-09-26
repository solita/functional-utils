package fi.solita.utils.functional;

import java.io.Serializable;

/**
 * Function taking no arguments, only producing a value.
 */
public interface ApplyZero<T> extends Serializable {
    T get();
}
