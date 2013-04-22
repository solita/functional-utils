package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class Predicates_ implements java.io.Serializable {

    public enum Fields {
    }
    
    public static final  fi.solita.utils.functional.Function1<java.lang.String, fi.solita.utils.functional.Predicate<java.lang.String>> startsWith = new fi.solita.utils.functional.Function1<java.lang.String, fi.solita.utils.functional.Predicate<java.lang.String>>() {
        public fi.solita.utils.functional.Predicate<java.lang.String> apply(java.lang.String prefix) {
            return fi.solita.utils.functional.Predicates.startsWith(prefix);
        }
    };
    
    public static final  fi.solita.utils.functional.Function0<fi.solita.utils.functional.Predicate<fi.solita.utils.functional.Option<?>>> defined = new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Predicate<fi.solita.utils.functional.Option<?>>>() {
        public fi.solita.utils.functional.Predicate<fi.solita.utils.functional.Option<?>> apply() {
            return fi.solita.utils.functional.Predicates.defined();
        }
    };
    
    public static final <T> fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Predicate<T>> equalTo() { return new fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Predicate<T>>() {
        public fi.solita.utils.functional.Predicate<T> apply(T element) {
            return fi.solita.utils.functional.Predicates.<T>equalTo(element);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Predicate<T>> nulls() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Predicate<T>>() {
        public fi.solita.utils.functional.Predicate<T> apply() {
            return fi.solita.utils.functional.Predicates.<T>nulls();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<java.lang.Class<? extends T>, fi.solita.utils.functional.Predicate<T>> instanceOf() { return new fi.solita.utils.functional.Function1<java.lang.Class<? extends T>, fi.solita.utils.functional.Predicate<T>>() {
        public fi.solita.utils.functional.Predicate<T> apply(java.lang.Class<? extends T> clazz) {
            return fi.solita.utils.functional.Predicates.<T>instanceOf(clazz);
        }
    };
    }
    
    public static final  fi.solita.utils.functional.Function1<java.lang.CharSequence, fi.solita.utils.functional.Predicate<java.lang.String>> contains = new fi.solita.utils.functional.Function1<java.lang.CharSequence, fi.solita.utils.functional.Predicate<java.lang.String>>() {
        public fi.solita.utils.functional.Predicate<java.lang.String> apply(java.lang.CharSequence infix) {
            return fi.solita.utils.functional.Predicates.contains(infix);
        }
    };
    
    public static final <T extends java.lang.reflect.AnnotatedElement> fi.solita.utils.functional.Function1<java.lang.Class<? extends java.lang.annotation.Annotation>, fi.solita.utils.functional.Predicate<T>> hasAnnotation() { return new fi.solita.utils.functional.Function1<java.lang.Class<? extends java.lang.annotation.Annotation>, fi.solita.utils.functional.Predicate<T>>() {
        public fi.solita.utils.functional.Predicate<T> apply(java.lang.Class<? extends java.lang.annotation.Annotation> annotation) {
            return fi.solita.utils.functional.Predicates.<T>hasAnnotation(annotation);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<T,java.lang.Boolean>, fi.solita.utils.functional.Predicate<T>> not() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<T,java.lang.Boolean>, fi.solita.utils.functional.Predicate<T>>() {
        public fi.solita.utils.functional.Predicate<T> apply(fi.solita.utils.functional.Apply<T,java.lang.Boolean> filter) {
            return fi.solita.utils.functional.Predicates.<T>not(filter);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<java.lang.Class<T>, fi.solita.utils.functional.Predicate<java.lang.Class<?>>> isInstance() { return new fi.solita.utils.functional.Function1<java.lang.Class<T>, fi.solita.utils.functional.Predicate<java.lang.Class<?>>>() {
        public fi.solita.utils.functional.Predicate<java.lang.Class<?>> apply(java.lang.Class<T> clazz) {
            return fi.solita.utils.functional.Predicates.<T>isInstance(clazz);
        }
    };
    }
    
    public static final  fi.solita.utils.functional.Function1<java.util.regex.Pattern, fi.solita.utils.functional.Predicate<java.lang.String>> matches = new fi.solita.utils.functional.Function1<java.util.regex.Pattern, fi.solita.utils.functional.Predicate<java.lang.String>>() {
        public fi.solita.utils.functional.Predicate<java.lang.String> apply(java.util.regex.Pattern pattern) {
            return fi.solita.utils.functional.Predicates.matches(pattern);
        }
    };
    
    public static final <T extends java.lang.Comparable<T>> fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Predicate<T>> lessThan() { return new fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Predicate<T>>() {
        public fi.solita.utils.functional.Predicate<T> apply(T value) {
            return fi.solita.utils.functional.Predicates.<T>lessThan(value);
        }
    };
    }
    
    public static final <T extends java.lang.Comparable<T>> fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Predicate<T>> greaterThanOrEqualTo() { return new fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Predicate<T>>() {
        public fi.solita.utils.functional.Predicate<T> apply(T value) {
            return fi.solita.utils.functional.Predicates.<T>greaterThanOrEqualTo(value);
        }
    };
    }
    
    public static final <T extends java.lang.Comparable<T>> fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Predicate<T>> greaterThan() { return new fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Predicate<T>>() {
        public fi.solita.utils.functional.Predicate<T> apply(T value) {
            return fi.solita.utils.functional.Predicates.<T>greaterThan(value);
        }
    };
    }
    
    public static final <E, T extends java.lang.Iterable<E>> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Predicate<T>> empty1() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Predicate<T>>() {
        public fi.solita.utils.functional.Predicate<T> apply() {
            return fi.solita.utils.functional.Predicates.<E, T>empty();
        }
    };
    }
    
    public static final <T extends java.lang.Comparable<T>> fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Predicate<T>> lessThanOrEqualTo() { return new fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Predicate<T>>() {
        public fi.solita.utils.functional.Predicate<T> apply(T value) {
            return fi.solita.utils.functional.Predicates.<T>lessThanOrEqualTo(value);
        }
    };
    }
    
    public static final  fi.solita.utils.functional.Function1<java.lang.String, fi.solita.utils.functional.Predicate<java.lang.String>> endsWith = new fi.solita.utils.functional.Function1<java.lang.String, fi.solita.utils.functional.Predicate<java.lang.String>>() {
        public fi.solita.utils.functional.Predicate<java.lang.String> apply(java.lang.String prefix) {
            return fi.solita.utils.functional.Predicates.endsWith(prefix);
        }
    };
    
    public static final  fi.solita.utils.functional.Function1<java.util.regex.Pattern, fi.solita.utils.functional.Predicate<java.lang.String>> lookingAt = new fi.solita.utils.functional.Function1<java.util.regex.Pattern, fi.solita.utils.functional.Predicate<java.lang.String>>() {
        public fi.solita.utils.functional.Predicate<java.lang.String> apply(java.util.regex.Pattern pattern) {
            return fi.solita.utils.functional.Predicates.lookingAt(pattern);
        }
    };
    

}
