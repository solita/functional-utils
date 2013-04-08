package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class Function1_ implements java.io.Serializable {

    public enum Fields {
    }
    
    public static final <T> fi.solita.utils.functional.Function0<fi.solita.utils.functional.Function1<T,T>> id() { return new fi.solita.utils.functional.Function0<fi.solita.utils.functional.Function1<T,T>>() {
        public fi.solita.utils.functional.Function1<T,T> apply() {
            return fi.solita.utils.functional.Function1.<T>id();
        }
    };
    }
    
    public static final <T, R> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Function1<T, R>, fi.solita.utils.functional.Function1<T, R>>  apply() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Function1<T, R>, fi.solita.utils.functional.Function1<T, R>> () { protected fi.solita.utils.functional.Function1<T, R> $do(final fi.solita.utils.functional.Function1<T, R> $self) { return new fi.solita.utils.functional.Function1<T, R>() {
        public R apply(T t) {
            return $self.apply(t);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Function1.class.getDeclaredMethod("apply", java.lang.Object.class);
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
    
    public static final <T, R> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Function1<T, R>, fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Function0<R>>>  applyPartial() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Function1<T, R>, fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Function0<R>>> () { protected fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Function0<R>> $do(final fi.solita.utils.functional.Function1<T, R> $self) { return new fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Function0<R>>() {
        public fi.solita.utils.functional.Function0<R> apply(T t) {
            return $self.applyPartial(t);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Function1.class.getDeclaredMethod("applyPartial", java.lang.Object.class);
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
            return "applyPartial";
        }
    
    };
    }
    
    public static final <T, R> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Function1<T, R>, fi.solita.utils.functional.Function1<fi.solita.utils.functional.Tuple1<T>,R>> tuppled() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Function1<T, R>, fi.solita.utils.functional.Function1<fi.solita.utils.functional.Tuple1<T>,R>>() {
        public fi.solita.utils.functional.Function1<fi.solita.utils.functional.Tuple1<T>,R> apply(fi.solita.utils.functional.Function1<T, R> $self) {
            return $self.tuppled();
        }
    };
    }
    
    public static final <T, R, U> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Function1<T, R>, fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super U,? extends T>, fi.solita.utils.functional.Function1<U,R>>>  compose() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Function1<T, R>, fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super U,? extends T>, fi.solita.utils.functional.Function1<U,R>>> () { protected fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super U,? extends T>, fi.solita.utils.functional.Function1<U,R>> $do(final fi.solita.utils.functional.Function1<T, R> $self) { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super U,? extends T>, fi.solita.utils.functional.Function1<U,R>>() {
        public fi.solita.utils.functional.Function1<U,R> apply(fi.solita.utils.functional.Apply<? super U,? extends T> next) {
            return $self.<U>compose(next);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Function1.class.getDeclaredMethod("compose", fi.solita.utils.functional.Apply.class);
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
            return "compose";
        }
    
    };
    }
    
    public static final <T, R, U> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Function1<T, R>, fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super R,? extends U>, fi.solita.utils.functional.Function1<T,U>>>  andThen() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Function1<T, R>, fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super R,? extends U>, fi.solita.utils.functional.Function1<T,U>>> () { protected fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super R,? extends U>, fi.solita.utils.functional.Function1<T,U>> $do(final fi.solita.utils.functional.Function1<T, R> $self) { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super R,? extends U>, fi.solita.utils.functional.Function1<T,U>>() {
        public fi.solita.utils.functional.Function1<T,U> apply(fi.solita.utils.functional.Apply<? super R,? extends U> next) {
            return $self.<U>andThen(next);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Function1.class.getDeclaredMethod("andThen", fi.solita.utils.functional.Apply.class);
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
            return "andThen";
        }
    
    };
    }
    

}
