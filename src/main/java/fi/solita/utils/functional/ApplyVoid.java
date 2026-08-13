package fi.solita.utils.functional;

import java.io.Serializable;

/**
 * One argument function producing no value. Side-effect.
 */
public interface ApplyVoid<T> extends java.util.function.Consumer<T>, Serializable {
}
