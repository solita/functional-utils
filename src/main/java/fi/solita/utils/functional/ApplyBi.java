package fi.solita.utils.functional;

import java.io.Serializable;

public interface ApplyBi<T1,T2,R> extends Serializable {
    R apply(T1 t1, T2 t2);
}
