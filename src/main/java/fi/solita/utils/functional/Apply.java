package fi.solita.utils.functional;

import java.io.Serializable;

public interface Apply<T,R> extends Serializable {
    R apply(T t);
}
