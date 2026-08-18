package fi.solita.utils.functional;

/**
 * Specialization of {@code Function1<T, Boolean>}.
 */
@Deprecated
public interface Pred<T> extends java.util.function.Function<T, Boolean>, java.util.function.Predicate<T> {
    default Boolean apply(T t) {
        return test(t);
    }
}
