package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class Predicate_ implements java.io.Serializable {

    public enum Fields {
    }
    
    public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Predicate<T>, fi.solita.utils.functional.Function1<T, java.lang.Boolean>>  apply() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Predicate<T>, fi.solita.utils.functional.Function1<T, java.lang.Boolean>> () { protected fi.solita.utils.functional.Function1<T, java.lang.Boolean> $do(final fi.solita.utils.functional.Predicate<T> $self) { return new fi.solita.utils.functional.Function1<T, java.lang.Boolean>() {
        public java.lang.Boolean apply(T t) {
            return $self.apply(t);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Predicate.class.getDeclaredMethod("apply", java.lang.Object.class);
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
    
    public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Predicate<T>, fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, fi.solita.utils.functional.Predicate<T>>>  or() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Predicate<T>, fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, fi.solita.utils.functional.Predicate<T>>> () { protected fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, fi.solita.utils.functional.Predicate<T>> $do(final fi.solita.utils.functional.Predicate<T> $self) { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, fi.solita.utils.functional.Predicate<T>>() {
        public fi.solita.utils.functional.Predicate<T> apply(fi.solita.utils.functional.Apply<? super T,java.lang.Boolean> other) {
            return $self.or(other);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Predicate.class.getDeclaredMethod("or", fi.solita.utils.functional.Apply.class);
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
            return "or";
        }
    
    };
    }
    
    public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Predicate<T>, fi.solita.utils.functional.Function1<T, java.lang.Boolean>>  accept() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Predicate<T>, fi.solita.utils.functional.Function1<T, java.lang.Boolean>> () { protected fi.solita.utils.functional.Function1<T, java.lang.Boolean> $do(final fi.solita.utils.functional.Predicate<T> $self) { return new fi.solita.utils.functional.Function1<T, java.lang.Boolean>() {
        public java.lang.Boolean apply(T candidate) {
            return $self.accept(candidate);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Predicate.class.getDeclaredMethod("accept", java.lang.Object.class);
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
            return "accept";
        }
    
    };
    }
    
    public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Predicate<T>, fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, fi.solita.utils.functional.Predicate<T>>>  and() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Predicate<T>, fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, fi.solita.utils.functional.Predicate<T>>> () { protected fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, fi.solita.utils.functional.Predicate<T>> $do(final fi.solita.utils.functional.Predicate<T> $self) { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, fi.solita.utils.functional.Predicate<T>>() {
        public fi.solita.utils.functional.Predicate<T> apply(fi.solita.utils.functional.Apply<? super T,java.lang.Boolean> other) {
            return $self.and(other);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Predicate.class.getDeclaredMethod("and", fi.solita.utils.functional.Apply.class);
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
            return "and";
        }
    
    };
    }
    

}
