package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class Apply_ implements java.io.Serializable {

    public enum Fields {
    }
    
    public static final <T, R> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Apply<T, R>, fi.solita.utils.functional.Function1<T, R>>  apply() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Apply<T, R>, fi.solita.utils.functional.Function1<T, R>> () { protected fi.solita.utils.functional.Function1<T, R> $do(final fi.solita.utils.functional.Apply<T, R> $self) { return new fi.solita.utils.functional.Function1<T, R>() {
        public R apply(T t) {
            return $self.apply(t);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Apply.class.getDeclaredMethod("apply", java.lang.Object.class);
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
    

}
