package fi.solita.utils.functional;

/**
 * Enumerable thing, that is, something which has a known predecessor and/or a known successor.
 */
public interface Enumerable<T> {
    /**
     * @return successor for {@code value}, if any.
     */
    public Option<T> succ(T value);
    
    /**
     * @return predecessor for {@code value}, if any.
     */
    public Option<T> pred(T value);
}
