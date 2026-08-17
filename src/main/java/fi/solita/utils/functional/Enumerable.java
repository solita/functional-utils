package fi.solita.utils.functional;

import java.util.Optional;

/**
 * Enumerable thing, that is, something which has a known predecessor and/or a known successor.
 */
public interface Enumerable<T> {
    /**
     * @return successor for {@code value}, if any.
     */
    public Optional<T> succ(T value);
    
    /**
     * @return predecessor for {@code value}, if any.
     */
    public Optional<T> pred(T value);
}
