package fi.solita.utils.functional;

import java.io.Serializable;

public interface ApplyVoid<T> extends Serializable {
    void accept(T t);
}
