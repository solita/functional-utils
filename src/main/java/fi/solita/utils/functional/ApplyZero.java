package fi.solita.utils.functional;

import java.io.Serializable;

/**
 * Function taking no arguments, only producing a value.
 */
@Deprecated
public interface ApplyZero<T> extends java.util.function.Supplier<T>, Serializable {
}
