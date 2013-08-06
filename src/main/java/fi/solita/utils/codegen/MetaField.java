package fi.solita.utils.codegen;

import fi.solita.utils.functional.Function1;

public abstract class MetaField<T, R> extends Function1<T, R> implements FieldMeta_<T, R> {

    public static abstract class Predicate<T, B> extends fi.solita.utils.functional.Predicate<T> implements FieldMeta_<T, Boolean> {
        @Override
        public String toString() {
            return getClass().getEnclosingClass().getSimpleName() + "." + getName();
        }
    }
    
    public static abstract class Property<T, R> extends Function1<T, R> implements FieldMeta_<T, R>, PropertyMeta_<T, R> {
        @Override
        public String toString() {
            return getClass().getEnclosingClass().getSimpleName() + "." + getName();
        }
    }
    
    public static abstract class PredicateProperty<T, B> extends fi.solita.utils.functional.Predicate<T> implements FieldMeta_<T, Boolean>, PropertyMeta_<T, Boolean> {
        @Override
        public String toString() {
            return getClass().getEnclosingClass().getSimpleName() + "." + getName();
        }
    }
}