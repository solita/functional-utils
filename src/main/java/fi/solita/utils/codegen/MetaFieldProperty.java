package fi.solita.utils.codegen;

import fi.solita.utils.functional.Function1;

public abstract class MetaFieldProperty<T, R> extends Function1<T, R> implements FieldMeta_<T, R>, PropertyMeta_<T, R> {
    @Override
    public String toString() {
        return getClass().getEnclosingClass().getSimpleName() + "." + getName();
    }
}