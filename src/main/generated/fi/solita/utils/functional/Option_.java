package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class Option_ implements java.io.Serializable {

    public enum Fields {
    }
    
    public static final <T> fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Option<T>> Some() { return new fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Option<T>>() {
        public fi.solita.utils.functional.Option<T> apply(T t) {
            return fi.solita.utils.functional.Option.<T>Some(t);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Option<T>> of() { return new fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Option<T>>() {
        public fi.solita.utils.functional.Option<T> apply(T t) {
            return fi.solita.utils.functional.Option.<T>of(t);
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Option<T>, T> get() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Option<T>, T>() {
        public T apply(fi.solita.utils.functional.Option<T> $self) {
            return $self.get();
        }
    };
    }
    
    public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Option<T>, fi.solita.utils.functional.Function1<T, T>>  getOrElse() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Option<T>, fi.solita.utils.functional.Function1<T, T>> () { protected fi.solita.utils.functional.Function1<T, T> $do(final fi.solita.utils.functional.Option<T> $self) { return new fi.solita.utils.functional.Function1<T, T>() {
        public T apply(T orElse) {
            return $self.getOrElse(orElse);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Option.class.getDeclaredMethod("getOrElse", java.lang.Object.class);
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
            return "getOrElse";
        }
    
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Option<?>, java.lang.Boolean> isDefined() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Option<?>, java.lang.Boolean>() {
        public java.lang.Boolean apply(fi.solita.utils.functional.Option<?> $self) {
            return $self.isDefined();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Option<T>> None1() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Option<T>>() {
        public fi.solita.utils.functional.Option<T> apply() {
            return fi.solita.utils.functional.Option.<T>None();
        }
    };
    }
    
        public static final class SerializableOption_ implements java.io.Serializable {
        public enum Fields {
        }
        
        }

}
