package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class FunctionExamples_ implements java.io.Serializable {

    public enum Fields {
    }
    
    public static final  fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.FunctionExamples> $ = new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.FunctionExamples>() {
        public fi.solita.utils.functional.FunctionExamples apply() {
            return new fi.solita.utils.functional.FunctionExamples();
        }
    
        private transient java.lang.reflect.Constructor<fi.solita.utils.functional.FunctionExamples> $r;
        
        @SuppressWarnings("unchecked")
        private java.lang.reflect.Constructor<fi.solita.utils.functional.FunctionExamples> $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.FunctionExamples>)(Object)fi.solita.utils.functional.FunctionExamples.class.getDeclaredConstructor();
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
    
        public java.lang.reflect.Constructor<fi.solita.utils.functional.FunctionExamples> getMember() {
            return $getMember();
        }
    
    };
    
    public static final  fi.solita.utils.functional.Function1<fi.solita.utils.functional.FunctionExamples, java.lang.Void> examples = new fi.solita.utils.functional.Function1<fi.solita.utils.functional.FunctionExamples, java.lang.Void>() {
        public java.lang.Void apply(fi.solita.utils.functional.FunctionExamples $self) {
            $self.examples();
            return null;
        }
    };
    

}
