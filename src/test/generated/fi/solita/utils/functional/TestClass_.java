package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class TestClass_ implements java.io.Serializable {

    public enum Fields {
        id,
    }
    
     static final fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.TestClass, java.lang.Double> id = new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.TestClass, java.lang.Double>() {
        private transient java.lang.reflect.Field $r;
        
        
        private java.lang.reflect.Field $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.TestClass.class.getDeclaredField("id");
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
    
        public java.lang.reflect.Field getMember() {
            return $getMember();
        }
    
        public String getName() {
            return "id";
        }
    
    
        protected java.lang.Double $do(fi.solita.utils.functional.TestClass $self) {
            return $self.id;
        }
    };
    
     static final  fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.TestClass> $ = new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.TestClass>() {
        public fi.solita.utils.functional.TestClass apply() {
            return new fi.solita.utils.functional.TestClass();
        }
    
        private transient java.lang.reflect.Constructor<fi.solita.utils.functional.TestClass> $r;
        
        @SuppressWarnings("unchecked")
        private java.lang.reflect.Constructor<fi.solita.utils.functional.TestClass> $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.TestClass>)(Object)fi.solita.utils.functional.TestClass.class.getDeclaredConstructor();
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
    
        public java.lang.reflect.Constructor<fi.solita.utils.functional.TestClass> getMember() {
            return $getMember();
        }
    
    };
    

}
