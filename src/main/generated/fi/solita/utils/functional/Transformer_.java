package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class Transformer_ implements java.io.Serializable {

    public enum Fields {
    }
    
    public static final <SOURCE, TARGET> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Transformer<SOURCE, TARGET>, fi.solita.utils.functional.Function1<SOURCE, TARGET>>  apply() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Transformer<SOURCE, TARGET>, fi.solita.utils.functional.Function1<SOURCE, TARGET>> () { protected fi.solita.utils.functional.Function1<SOURCE, TARGET> $do(final fi.solita.utils.functional.Transformer<SOURCE, TARGET> $self) { return new fi.solita.utils.functional.Function1<SOURCE, TARGET>() {
        public TARGET apply(SOURCE t) {
            return $self.apply(t);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Transformer.class.getDeclaredMethod("apply", java.lang.Object.class);
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
    
    public static final <SOURCE, TARGET> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Transformer<SOURCE, TARGET>, fi.solita.utils.functional.Function1<SOURCE, TARGET>>  transform() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Transformer<SOURCE, TARGET>, fi.solita.utils.functional.Function1<SOURCE, TARGET>> () { protected fi.solita.utils.functional.Function1<SOURCE, TARGET> $do(final fi.solita.utils.functional.Transformer<SOURCE, TARGET> $self) { return new fi.solita.utils.functional.Function1<SOURCE, TARGET>() {
        public TARGET apply(SOURCE source) {
            return $self.transform(source);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Transformer.class.getDeclaredMethod("transform", java.lang.Object.class);
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
            return "transform";
        }
    
    };
    }
    
    public static final <SOURCE, TARGET, U> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Transformer<SOURCE, TARGET>, fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super TARGET,? extends U>, fi.solita.utils.functional.Transformer<SOURCE,U>>>  andThen() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Transformer<SOURCE, TARGET>, fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super TARGET,? extends U>, fi.solita.utils.functional.Transformer<SOURCE,U>>> () { protected fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super TARGET,? extends U>, fi.solita.utils.functional.Transformer<SOURCE,U>> $do(final fi.solita.utils.functional.Transformer<SOURCE, TARGET> $self) { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Apply<? super TARGET,? extends U>, fi.solita.utils.functional.Transformer<SOURCE,U>>() {
        public fi.solita.utils.functional.Transformer<SOURCE,U> apply(fi.solita.utils.functional.Apply<? super TARGET,? extends U> next) {
            return $self.<U>andThen(next);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Transformer.class.getDeclaredMethod("andThen", fi.solita.utils.functional.Apply.class);
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
