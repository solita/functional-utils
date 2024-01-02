package fi.solita.utils.functional;

/**
 * Two argument function from {@code T1} and {@code T2} to {@code R}
 */
public interface ApplyBi<T1,T2,R> extends Apply2<T1,T2,R> {
    R apply(T1 t1, T2 t2);
}
