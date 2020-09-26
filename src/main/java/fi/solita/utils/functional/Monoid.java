package fi.solita.utils.functional;

/**
 * Semigroup with an identity element.
 */
public interface Monoid<T> extends SemiGroup<T> {
    T zero();
}
