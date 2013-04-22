package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class SortTest_ implements java.io.Serializable {

    public enum Fields {
        accessor,
    }
    
     static final fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.SortTest, fi.solita.utils.functional.Function1<fi.solita.utils.functional.TestClass,java.lang.Double>> accessor = new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.SortTest, fi.solita.utils.functional.Function1<fi.solita.utils.functional.TestClass,java.lang.Double>>() {
        private transient java.lang.reflect.Field $r;
        
        
        private java.lang.reflect.Field $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.SortTest.class.getDeclaredField("accessor");
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
            return "accessor";
        }
    
    
        protected fi.solita.utils.functional.Function1<fi.solita.utils.functional.TestClass,java.lang.Double> $do(fi.solita.utils.functional.SortTest $self) {
            return $self.accessor;
        }
    };
    
    public static final  fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.SortTest> $ = new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.SortTest>() {
        public fi.solita.utils.functional.SortTest apply() {
            return new fi.solita.utils.functional.SortTest();
        }
    
        private transient java.lang.reflect.Constructor<fi.solita.utils.functional.SortTest> $r;
        
        @SuppressWarnings("unchecked")
        private java.lang.reflect.Constructor<fi.solita.utils.functional.SortTest> $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.SortTest>)(Object)fi.solita.utils.functional.SortTest.class.getDeclaredConstructor();
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
    
        public java.lang.reflect.Constructor<fi.solita.utils.functional.SortTest> getMember() {
            return $getMember();
        }
    
    };
    
    public static final  fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.SortTest, fi.solita.utils.functional.Function2<java.lang.Integer, java.lang.Integer, java.lang.Void>>  runSortOnce = new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.SortTest, fi.solita.utils.functional.Function2<java.lang.Integer, java.lang.Integer, java.lang.Void>> () { protected fi.solita.utils.functional.Function2<java.lang.Integer, java.lang.Integer, java.lang.Void> $do(final fi.solita.utils.functional.SortTest $self) { return new fi.solita.utils.functional.Function2<java.lang.Integer, java.lang.Integer, java.lang.Void>() {
        public java.lang.Void apply(java.lang.Integer size, java.lang.Integer firstToTake) {
            $self.runSortOnce((int)size, (int)firstToTake);
            return null;
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.SortTest.class.getDeclaredMethod("runSortOnce", int.class, int.class);
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
            return "runSortOnce";
        }
    
    };
    
    public static final  fi.solita.utils.functional.Function1<fi.solita.utils.functional.SortTest, java.lang.Void> testSort = new fi.solita.utils.functional.Function1<fi.solita.utils.functional.SortTest, java.lang.Void>() {
        public java.lang.Void apply(fi.solita.utils.functional.SortTest $self) {
            $self.testSort();
            return null;
        }
    };
    

}
