package fi.solita.utils.functional;

public interface ApplyBi<T1,T2,R> {
    R apply(T1 t1, T2 t2);
}
