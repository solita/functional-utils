package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class Function3_ implements java.io.Serializable {

    public enum Fields {
    }
    
    public static final <T1, T2, T3, R> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Function3<T1, T2, T3, R>, fi.solita.utils.functional.Function3<T1, T2, T3, R>>  apply() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Function3<T1, T2, T3, R>, fi.solita.utils.functional.Function3<T1, T2, T3, R>> () { protected fi.solita.utils.functional.Function3<T1, T2, T3, R> $do(final fi.solita.utils.functional.Function3<T1, T2, T3, R> $self) { return new fi.solita.utils.functional.Function3<T1, T2, T3, R>() {
        public R apply(T1 t1, T2 t2, T3 t3) {
            return $self.apply(t1, t2, t3);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Function3.class.getDeclaredMethod("apply", java.lang.Object.class, java.lang.Object.class, java.lang.Object.class);
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
    
    public static final <T1, T2, T3, R> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Function3<T1, T2, T3, R>, fi.solita.utils.functional.Function1<fi.solita.utils.functional.Tuple3<T1,T2,T3>,R>> tuppled() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Function3<T1, T2, T3, R>, fi.solita.utils.functional.Function1<fi.solita.utils.functional.Tuple3<T1,T2,T3>,R>>() {
        public fi.solita.utils.functional.Function1<fi.solita.utils.functional.Tuple3<T1,T2,T3>,R> apply(fi.solita.utils.functional.Function3<T1, T2, T3, R> $self) {
            return $self.tuppled();
        }
    };
    }
    
    public static final <T1, T2, T3, R, U> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Function3<T1, T2, T3, R>, fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super R,? extends U>, fi.solita.utils.functional.Function3<T1,T2,T3,U>>>  andThen() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Function3<T1, T2, T3, R>, fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super R,? extends U>, fi.solita.utils.functional.Function3<T1,T2,T3,U>>> () { protected fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super R,? extends U>, fi.solita.utils.functional.Function3<T1,T2,T3,U>> $do(final fi.solita.utils.functional.Function3<T1, T2, T3, R> $self) { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super R,? extends U>, fi.solita.utils.functional.Function3<T1,T2,T3,U>>() {
        public fi.solita.utils.functional.Function3<T1,T2,T3,U> apply(fi.solita.utils.functional.Apply<? super R,? extends U> next) {
            return $self.<U>andThen(next);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Function3.class.getDeclaredMethod("andThen", fi.solita.utils.functional.Apply.class);
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
    
    public static final <T1, T2, T3, R> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Function3<T1, T2, T3, R>, fi.solita.utils.functional.Function1<T1,fi.solita.utils.functional.Function1<T2,fi.solita.utils.functional.Function1<T3,R>>>> curried() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Function3<T1, T2, T3, R>, fi.solita.utils.functional.Function1<T1,fi.solita.utils.functional.Function1<T2,fi.solita.utils.functional.Function1<T3,R>>>>() {
        public fi.solita.utils.functional.Function1<T1,fi.solita.utils.functional.Function1<T2,fi.solita.utils.functional.Function1<T3,R>>> apply(fi.solita.utils.functional.Function3<T1, T2, T3, R> $self) {
            return $self.curried();
        }
    };
    }
    

}
