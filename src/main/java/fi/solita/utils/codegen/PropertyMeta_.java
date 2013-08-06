package fi.solita.utils.codegen;

import java.io.Serializable;

import fi.solita.utils.functional.Function1;

public interface PropertyMeta_<T,R> extends Meta_<T,R>, Serializable {
    public Function1<R,Void> setter(T $s);
}
