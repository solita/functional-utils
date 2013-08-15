package fi.solita.utils.codegen;

public abstract class MetaFieldPredicate<T> extends fi.solita.utils.functional.Predicate<T> implements FieldMeta_<T, Boolean> {
    @Override
    public String toString() {
        return getClass().getEnclosingClass().getSimpleName() + "." + getName();
    }
}