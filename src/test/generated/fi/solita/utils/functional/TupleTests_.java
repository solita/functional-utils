package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class TupleTests_ implements java.io.Serializable {

    public enum Fields {
    }
    
    public static final  fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.TupleTests> $ = new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.TupleTests>() {
        public fi.solita.utils.functional.TupleTests apply() {
            return new fi.solita.utils.functional.TupleTests();
        }
    
        private transient java.lang.reflect.Constructor<fi.solita.utils.functional.TupleTests> $r;
        
        @SuppressWarnings("unchecked")
        private java.lang.reflect.Constructor<fi.solita.utils.functional.TupleTests> $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.TupleTests>)(Object)fi.solita.utils.functional.TupleTests.class.getDeclaredConstructor();
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
    
        public java.lang.reflect.Constructor<fi.solita.utils.functional.TupleTests> getMember() {
            return $getMember();
        }
    
    };
    
    public static final  fi.solita.utils.functional.Function1<fi.solita.utils.functional.TupleTests, java.lang.Void> testPrefix = new fi.solita.utils.functional.Function1<fi.solita.utils.functional.TupleTests, java.lang.Void>() {
        public java.lang.Void apply(fi.solita.utils.functional.TupleTests $self) {
            $self.testPrefix();
            return null;
        }
    };
    

}
