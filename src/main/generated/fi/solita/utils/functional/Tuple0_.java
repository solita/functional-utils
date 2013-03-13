package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class Tuple0_ implements java.io.Serializable {

    public enum Fields {
    }
    
    public static final  fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Tuple0> $ = new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Tuple0>() {
        public fi.solita.utils.functional.Tuple0 apply() {
            return new fi.solita.utils.functional.Tuple0();
        }
    
        private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Tuple0> $r;
        
        @SuppressWarnings("unchecked")
        private java.lang.reflect.Constructor<fi.solita.utils.functional.Tuple0> $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Tuple0>)(Object)fi.solita.utils.functional.Tuple0.class.getDeclaredConstructor();
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
    
        public java.lang.reflect.Constructor<fi.solita.utils.functional.Tuple0> getMember() {
            return $getMember();
        }
    
    };
    
    public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Tuple0, fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Tuple1<T>>>  append() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Tuple0, fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Tuple1<T>>> () { protected fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Tuple1<T>> $do(final fi.solita.utils.functional.Tuple0 $self) { return new fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Tuple1<T>>() {
        public fi.solita.utils.functional.Tuple1<T> apply(T t) {
            return $self.<T>append(t);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Tuple0.class.getDeclaredMethod("append", java.lang.Object.class);
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
            return "append";
        }
    
    };
    }
    
    public static final  fi.solita.utils.functional.Function1<fi.solita.utils.functional.Tuple0, java.lang.Object[]> toArray = new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Tuple0, java.lang.Object[]>() {
        public java.lang.Object[] apply(fi.solita.utils.functional.Tuple0 $self) {
            return $self.toArray();
        }
    };
    
    public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Tuple0, fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Tuple1<T>>>  prepend() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Tuple0, fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Tuple1<T>>> () { protected fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Tuple1<T>> $do(final fi.solita.utils.functional.Tuple0 $self) { return new fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Tuple1<T>>() {
        public fi.solita.utils.functional.Tuple1<T> apply(T t) {
            return $self.<T>prepend(t);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Tuple0.class.getDeclaredMethod("prepend", java.lang.Object.class);
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
            return "prepend";
        }
    
    };
    }
    

}
