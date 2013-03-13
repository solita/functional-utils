package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class FunctionalTests_ implements java.io.Serializable {

    public enum Fields {
    }
    
    public static final  fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.FunctionalTests> $ = new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.FunctionalTests>() {
        public fi.solita.utils.functional.FunctionalTests apply() {
            return new fi.solita.utils.functional.FunctionalTests();
        }
    
        private transient java.lang.reflect.Constructor<fi.solita.utils.functional.FunctionalTests> $r;
        
        @SuppressWarnings("unchecked")
        private java.lang.reflect.Constructor<fi.solita.utils.functional.FunctionalTests> $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.FunctionalTests>)(Object)fi.solita.utils.functional.FunctionalTests.class.getDeclaredConstructor();
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
    
        public java.lang.reflect.Constructor<fi.solita.utils.functional.FunctionalTests> getMember() {
            return $getMember();
        }
    
    };
    
    public static final  fi.solita.utils.functional.Function1<fi.solita.utils.functional.FunctionalTests, java.lang.Void> testTake = new fi.solita.utils.functional.Function1<fi.solita.utils.functional.FunctionalTests, java.lang.Void>() {
        public java.lang.Void apply(fi.solita.utils.functional.FunctionalTests $self) {
            $self.testTake();
            return null;
        }
    };
    
    public static final  fi.solita.utils.functional.Function1<fi.solita.utils.functional.FunctionalTests, java.lang.Void> testReverse = new fi.solita.utils.functional.Function1<fi.solita.utils.functional.FunctionalTests, java.lang.Void>() {
        public java.lang.Void apply(fi.solita.utils.functional.FunctionalTests $self) {
            $self.testReverse();
            return null;
        }
    };
    
    public static final  fi.solita.utils.functional.Function1<fi.solita.utils.functional.FunctionalTests, java.lang.Void> testDrop = new fi.solita.utils.functional.Function1<fi.solita.utils.functional.FunctionalTests, java.lang.Void>() {
        public java.lang.Void apply(fi.solita.utils.functional.FunctionalTests $self) {
            $self.testDrop();
            return null;
        }
    };
    

}
