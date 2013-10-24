package fi.solita.utils.codegen;

import fi.solita.utils.functional.Function1;

public interface MetaProperty<T,R> extends MetaField<T,R> {
    public Function1<R,Void> setter(T $s);
}
