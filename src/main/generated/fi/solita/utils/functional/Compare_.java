package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class Compare_ implements java.io.Serializable {

    public enum Fields {
    }
    
    public static final <T extends java.lang.Comparable<T>> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Ordering<fi.solita.utils.functional.Option<T>>> byOption() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Ordering<fi.solita.utils.functional.Option<T>>>() {
        public fi.solita.utils.functional.Ordering<fi.solita.utils.functional.Option<T>> apply() {
            return fi.solita.utils.functional.Compare.<T>byOption();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<java.util.Comparator<? super T>, fi.solita.utils.functional.Ordering<fi.solita.utils.functional.Option<T>>> byOption1() { return new fi.solita.utils.functional.Function1<java.util.Comparator<? super T>, fi.solita.utils.functional.Ordering<fi.solita.utils.functional.Option<T>>>() {
        public fi.solita.utils.functional.Ordering<fi.solita.utils.functional.Option<T>> apply(java.util.Comparator<? super T> c) {
            return fi.solita.utils.functional.Compare.<T>byOption(c);
        }
    };
    }
    
    public static final <S, T extends java.lang.Comparable<T>> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Function1<? super S,fi.solita.utils.functional.Option<T>>, fi.solita.utils.functional.Ordering<S>> byOption2() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Function1<? super S,fi.solita.utils.functional.Option<T>>, fi.solita.utils.functional.Ordering<S>>() {
        public fi.solita.utils.functional.Ordering<S> apply(fi.solita.utils.functional.Function1<? super S,fi.solita.utils.functional.Option<T>> f) {
            return fi.solita.utils.functional.Compare.<S, T>byOption(f);
        }
    };
    }
    
    public static final <S, T> fi.solita.utils.functional.Function2<fi.solita.utils.functional.Function1<? super S,fi.solita.utils.functional.Option<T>>, java.util.Comparator<? super T>, fi.solita.utils.functional.Ordering<S>> byOption3() { return new fi.solita.utils.functional.Function2<fi.solita.utils.functional.Function1<? super S,fi.solita.utils.functional.Option<T>>, java.util.Comparator<? super T>, fi.solita.utils.functional.Ordering<S>>() {
        public fi.solita.utils.functional.Ordering<S> apply(fi.solita.utils.functional.Function1<? super S,fi.solita.utils.functional.Option<T>> f, java.util.Comparator<? super T> c) {
            return fi.solita.utils.functional.Compare.<S, T>byOption(f, c);
        }
    };
    }
    
    public static final <S extends java.lang.Comparable<S>, T extends java.lang.Iterable<S>> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Ordering<T>> byIterable() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Ordering<T>>() {
        public fi.solita.utils.functional.Ordering<T> apply() {
            return fi.solita.utils.functional.Compare.<S, T>byIterable();
        }
    };
    }
    
    public static final <S, T extends java.lang.Iterable<? extends S>> fi.solita.utils.functional.Function1<java.util.Comparator<S>, fi.solita.utils.functional.Ordering<T>> byIterable1() { return new fi.solita.utils.functional.Function1<java.util.Comparator<S>, fi.solita.utils.functional.Ordering<T>>() {
        public fi.solita.utils.functional.Ordering<T> apply(java.util.Comparator<S> c) {
            return fi.solita.utils.functional.Compare.<S, T>byIterable(c);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Function1<? super T,? extends java.lang.Comparable<?>>, fi.solita.utils.functional.Ordering<T>> by() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Function1<? super T,? extends java.lang.Comparable<?>>, fi.solita.utils.functional.Ordering<T>>() {
        public fi.solita.utils.functional.Ordering<T> apply(fi.solita.utils.functional.Function1<? super T,? extends java.lang.Comparable<?>> f) {
            return fi.solita.utils.functional.Compare.<T>by(f);
        }
    };
    }
    
    public static final <T, S> fi.solita.utils.functional.Function2<fi.solita.utils.functional.Function1<? super S,T>, java.util.Comparator<? super T>, fi.solita.utils.functional.Ordering<S>> by1() { return new fi.solita.utils.functional.Function2<fi.solita.utils.functional.Function1<? super S,T>, java.util.Comparator<? super T>, fi.solita.utils.functional.Ordering<S>>() {
        public fi.solita.utils.functional.Ordering<S> apply(fi.solita.utils.functional.Function1<? super S,T> f, java.util.Comparator<? super T> c) {
            return fi.solita.utils.functional.Compare.<T, S>by(f, c);
        }
    };
    }
    
    public static final <T extends java.lang.Comparable<?>> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Ordering<T>> byNatural() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Ordering<T>>() {
        public fi.solita.utils.functional.Ordering<T> apply() {
            return fi.solita.utils.functional.Compare.<T>byNatural();
        }
    };
    }
    

}
