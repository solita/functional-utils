package fi.solita.utils.functional;

public interface Bounded<T> {
    public T minBound();
    public T maxBound();
}
