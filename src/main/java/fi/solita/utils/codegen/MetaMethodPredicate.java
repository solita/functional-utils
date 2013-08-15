package fi.solita.utils.codegen;

public abstract class MetaMethodPredicate<T> extends fi.solita.utils.functional.Predicate<T> implements MethodMeta_<T, Boolean> {
    @Override
    public String toString() {
        return getClass().getEnclosingClass().getSimpleName() + "." + getName();
    }
}