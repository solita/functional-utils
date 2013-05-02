package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class CompareExamples_ implements java.io.Serializable {

    public enum Fields {
    }
    
    public static final  fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.CompareExamples> $ = new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.CompareExamples>() {
        public fi.solita.utils.functional.CompareExamples apply() {
            return new fi.solita.utils.functional.CompareExamples();
        }
    
        private transient java.lang.reflect.Constructor<fi.solita.utils.functional.CompareExamples> $r;
        
        @SuppressWarnings("unchecked")
        private java.lang.reflect.Constructor<fi.solita.utils.functional.CompareExamples> $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.CompareExamples>)(Object)fi.solita.utils.functional.CompareExamples.class.getDeclaredConstructor();
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
    
        public java.lang.reflect.Constructor<fi.solita.utils.functional.CompareExamples> getMember() {
            return $getMember();
        }
    
    };
    
    public static final  fi.solita.utils.functional.Function1<fi.solita.utils.functional.CompareExamples, java.lang.Void> examples = new fi.solita.utils.functional.Function1<fi.solita.utils.functional.CompareExamples, java.lang.Void>() {
        public java.lang.Void apply(fi.solita.utils.functional.CompareExamples $self) {
            $self.examples();
            return null;
        }
    };
    
         static final class Employee_ implements java.io.Serializable {
        public enum Fields {
            name,
            salary,
        }
        
        public static final fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.CompareExamples.Employee, fi.solita.utils.functional.Option<java.lang.String>> name = new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.CompareExamples.Employee, fi.solita.utils.functional.Option<java.lang.String>>() {
            private transient java.lang.reflect.Field $r;
            
            
            private java.lang.reflect.Field $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.CompareExamples.Employee.class.getDeclaredField("name");
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
                return "name";
            }
        
        
            protected fi.solita.utils.functional.Option<java.lang.String> $do(fi.solita.utils.functional.CompareExamples.Employee $self) {
                return $self.name;
            }
        };
        
        public static final fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.CompareExamples.Employee, java.lang.Integer> salary = new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.CompareExamples.Employee, java.lang.Integer>() {
            private transient java.lang.reflect.Field $r;
            
            
            private java.lang.reflect.Field $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.CompareExamples.Employee.class.getDeclaredField("salary");
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
                return "salary";
            }
        
        
            protected java.lang.Integer $do(fi.solita.utils.functional.CompareExamples.Employee $self) {
                return $self.salary;
            }
        };
        
         static final  fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.CompareExamples.Employee> $ = new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.CompareExamples.Employee>() {
            public fi.solita.utils.functional.CompareExamples.Employee apply() {
                return new fi.solita.utils.functional.CompareExamples.Employee();
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.CompareExamples.Employee> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.CompareExamples.Employee> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.CompareExamples.Employee>)(Object)fi.solita.utils.functional.CompareExamples.Employee.class.getDeclaredConstructor();
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.CompareExamples.Employee> getMember() {
                return $getMember();
            }
        
        };
        
        }

}
