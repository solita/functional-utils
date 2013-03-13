package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class Ordering_ implements java.io.Serializable {

    public enum Fields {
    }
    
    public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Ordering<T>, fi.solita.utils.functional.Function1<fi.solita.utils.functional.Tuple2<fi.solita.utils.functional.Ordering<T>,fi.solita.utils.functional.Ordering<T>>, fi.solita.utils.functional.Ordering<T>>>  apply() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Ordering<T>, fi.solita.utils.functional.Function1<fi.solita.utils.functional.Tuple2<fi.solita.utils.functional.Ordering<T>,fi.solita.utils.functional.Ordering<T>>, fi.solita.utils.functional.Ordering<T>>> () { protected fi.solita.utils.functional.Function1<fi.solita.utils.functional.Tuple2<fi.solita.utils.functional.Ordering<T>,fi.solita.utils.functional.Ordering<T>>, fi.solita.utils.functional.Ordering<T>> $do(final fi.solita.utils.functional.Ordering<T> $self) { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Tuple2<fi.solita.utils.functional.Ordering<T>,fi.solita.utils.functional.Ordering<T>>, fi.solita.utils.functional.Ordering<T>>() {
        public fi.solita.utils.functional.Ordering<T> apply(fi.solita.utils.functional.Tuple2<fi.solita.utils.functional.Ordering<T>,fi.solita.utils.functional.Ordering<T>> t) {
            return $self.apply(t);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Ordering.class.getDeclaredMethod("apply", fi.solita.utils.functional.Tuple2.class);
                    $r.setAccessible(true);
                } catch (RuntimeException $e) {
                    throw $e;
                } catch (Error $e) {
                    throw $e;
                } catch (Throwable $e) {
                    throw new RuntimeException($e);
                }
            }
            return $r;
        }
    
        public java.lang.reflect.Method getMember() {
            return $getMember();
        }
    
        public String getName() {
            return "apply";
        }
    
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<java.util.Comparator<? super T>, fi.solita.utils.functional.Ordering<T>> of() { return new fi.solita.utils.functional.Function1<java.util.Comparator<? super T>, fi.solita.utils.functional.Ordering<T>>() {
        public fi.solita.utils.functional.Ordering<T> apply(java.util.Comparator<? super T> c) {
            return fi.solita.utils.functional.Ordering.<T>of(c);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Ordering<T>, fi.solita.utils.functional.Ordering<T>> reverse() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Ordering<T>, fi.solita.utils.functional.Ordering<T>>() {
        public fi.solita.utils.functional.Ordering<T> apply(fi.solita.utils.functional.Ordering<T> $self) {
            return $self.reverse();
        }
    };
    }
    
    public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Ordering<T>, fi.solita.utils.functional.Function1<fi.solita.utils.functional.Ordering<? super T>, fi.solita.utils.functional.Ordering<T>>>  then() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Ordering<T>, fi.solita.utils.functional.Function1<fi.solita.utils.functional.Ordering<? super T>, fi.solita.utils.functional.Ordering<T>>> () { protected fi.solita.utils.functional.Function1<fi.solita.utils.functional.Ordering<? super T>, fi.solita.utils.functional.Ordering<T>> $do(final fi.solita.utils.functional.Ordering<T> $self) { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Ordering<? super T>, fi.solita.utils.functional.Ordering<T>>() {
        public fi.solita.utils.functional.Ordering<T> apply(fi.solita.utils.functional.Ordering<? super T> next) {
            return $self.then(next);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Ordering.class.getDeclaredMethod("then", fi.solita.utils.functional.Ordering.class);
                    $r.setAccessible(true);
                } catch (RuntimeException $e) {
                    throw $e;
                } catch (Error $e) {
                    throw $e;
                } catch (Throwable $e) {
                    throw new RuntimeException($e);
                }
            }
            return $r;
        }
    
        public java.lang.reflect.Method getMember() {
            return $getMember();
        }
    
        public String getName() {
            return "then";
        }
    
    };
    }
    
    public static final <T extends java.lang.Comparable<?>> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Ordering<T>> Natural1() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Ordering<T>>() {
        public fi.solita.utils.functional.Ordering<T> apply() {
            return fi.solita.utils.functional.Ordering.<T>Natural();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Ordering<T>, fi.solita.utils.functional.Ordering<T>> zero() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Ordering<T>, fi.solita.utils.functional.Ordering<T>>() {
        public fi.solita.utils.functional.Ordering<T> apply(fi.solita.utils.functional.Ordering<T> $self) {
            return $self.zero();
        }
    };
    }
    

}
