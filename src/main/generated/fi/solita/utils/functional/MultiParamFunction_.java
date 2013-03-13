package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class MultiParamFunction_ implements java.io.Serializable {

    public enum Fields {
    }
    
    public static final <T extends fi.solita.utils.functional.Tuple, R> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.MultiParamFunction<T, R>, fi.solita.utils.functional.Function1<T, R>>  apply() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.MultiParamFunction<T, R>, fi.solita.utils.functional.Function1<T, R>> () { protected fi.solita.utils.functional.Function1<T, R> $do(final fi.solita.utils.functional.MultiParamFunction<T, R> $self) { return new fi.solita.utils.functional.Function1<T, R>() {
        public R apply(T t) {
            return $self.apply(t);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.MultiParamFunction.class.getDeclaredMethod("apply", fi.solita.utils.functional.Tuple.class);
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
    
    public static final <T extends fi.solita.utils.functional.Tuple, R> fi.solita.utils.functional.Function1<fi.solita.utils.functional.MultiParamFunction<T, R>, fi.solita.utils.functional.Function1<T,R>> tuppled() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.MultiParamFunction<T, R>, fi.solita.utils.functional.Function1<T,R>>() {
        public fi.solita.utils.functional.Function1<T,R> apply(fi.solita.utils.functional.MultiParamFunction<T, R> $self) {
            return $self.tuppled();
        }
    };
    }
    

}
