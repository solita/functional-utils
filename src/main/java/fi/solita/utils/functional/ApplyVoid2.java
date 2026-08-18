package fi.solita.utils.functional;

import java.io.Serializable;

/**
 * Two argument function producing no value. Side-effect.
 */
@Deprecated
public interface ApplyVoid2<T1,T2> extends java.util.function.BiConsumer<T1, T2>, Serializable {
}
