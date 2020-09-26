package fi.solita.utils.functional;

/**
 * A thing having known lower bound and upper bound.
 */
public interface Bounded<T> {
    public T minBound();
    public T maxBound();
}
