package fi.solita.utils.functional;


public interface Monoid<T> extends SemiGroup<T> {
    T zero();
}
