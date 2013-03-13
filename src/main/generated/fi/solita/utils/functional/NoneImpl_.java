package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class NoneImpl_ implements java.io.Serializable {

    public enum Fields {
    }
    
     static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.NoneImpl<T>> $() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.NoneImpl<T>>() {
        public fi.solita.utils.functional.NoneImpl<T> apply() {
            return new fi.solita.utils.functional.NoneImpl<T>();
        }
    
        private transient java.lang.reflect.Constructor<fi.solita.utils.functional.NoneImpl<T>> $r;
        
        @SuppressWarnings("unchecked")
        private java.lang.reflect.Constructor<fi.solita.utils.functional.NoneImpl<T>> $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.NoneImpl<T>>)(Object)fi.solita.utils.functional.NoneImpl.class.getDeclaredConstructor();
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
    
        public java.lang.reflect.Constructor<fi.solita.utils.functional.NoneImpl<T>> getMember() {
            return $getMember();
        }
    
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.NoneImpl<T>, java.util.Iterator<T>> iterator() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.NoneImpl<T>, java.util.Iterator<T>>() {
        public java.util.Iterator<T> apply(fi.solita.utils.functional.NoneImpl<T> $self) {
            return $self.iterator();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.NoneImpl<T>, T> get() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.NoneImpl<T>, T>() {
        public T apply(fi.solita.utils.functional.NoneImpl<T> $self) {
            return $self.get();
        }
    };
    }
    
    public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.NoneImpl<T>, fi.solita.utils.functional.Function1<T, T>>  getOrElse() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.NoneImpl<T>, fi.solita.utils.functional.Function1<T, T>> () { protected fi.solita.utils.functional.Function1<T, T> $do(final fi.solita.utils.functional.NoneImpl<T> $self) { return new fi.solita.utils.functional.Function1<T, T>() {
        public T apply(T orElse) {
            return $self.getOrElse(orElse);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.NoneImpl.class.getDeclaredMethod("getOrElse", java.lang.Object.class);
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
    
    public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.NoneImpl<?>, java.lang.Boolean> isDefined() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.NoneImpl<?>, java.lang.Boolean>() {
        public java.lang.Boolean apply(fi.solita.utils.functional.NoneImpl<?> $self) {
            return $self.isDefined();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.NoneImpl<?>, java.lang.String> toString() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.NoneImpl<?>, java.lang.String>() {
        public java.lang.String apply(fi.solita.utils.functional.NoneImpl<?> $self) {
            return $self.toString();
        }
    };
    }
    

}
