package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class Tuple1_ implements java.io.Serializable {

    public enum Fields {
        _1,
    }
    
    public static final <T1> fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Tuple1<T1>, T1> _1() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Tuple1<T1>, T1>() {
        private transient java.lang.reflect.Field $r;
        
        
        private java.lang.reflect.Field $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.Tuple1.class.getDeclaredField("_1");
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
            return "_1";
        }
    
    
        protected T1 $do(fi.solita.utils.functional.Tuple1<T1> $self) {
            return $self._1;
        }
    };
    }
    
     static final <T1> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Tuple1<T1>> $() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Tuple1<T1>>() {
        public fi.solita.utils.functional.Tuple1<T1> apply() {
            return new fi.solita.utils.functional.Tuple1<T1>();
        }
    
        private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Tuple1<T1>> $r;
        
        @SuppressWarnings("unchecked")
        private java.lang.reflect.Constructor<fi.solita.utils.functional.Tuple1<T1>> $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Tuple1<T1>>)(Object)fi.solita.utils.functional.Tuple1.class.getDeclaredConstructor();
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
    
        public java.lang.reflect.Constructor<fi.solita.utils.functional.Tuple1<T1>> getMember() {
            return $getMember();
        }
    
    };
    }
    
    public static final <T1> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F1<T1, fi.solita.utils.functional.Tuple1<T1>> $1() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F1<T1, fi.solita.utils.functional.Tuple1<T1>>() {
        public fi.solita.utils.functional.Tuple1<T1> apply(T1 t1) {
            return new fi.solita.utils.functional.Tuple1<T1>(t1);
        }
    
        private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Tuple1<T1>> $r;
        
        @SuppressWarnings("unchecked")
        private java.lang.reflect.Constructor<fi.solita.utils.functional.Tuple1<T1>> $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Tuple1<T1>>)(Object)fi.solita.utils.functional.Tuple1.class.getDeclaredConstructor(java.lang.Object.class);
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
    
        public java.lang.reflect.Constructor<fi.solita.utils.functional.Tuple1<T1>> getMember() {
            return $getMember();
        }
    
    };
    }
    
    public static final <T1, T> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Tuple1<T1>, fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Tuple2<T1,T>>>  append() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Tuple1<T1>, fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Tuple2<T1,T>>> () { protected fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Tuple2<T1,T>> $do(final fi.solita.utils.functional.Tuple1<T1> $self) { return new fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Tuple2<T1,T>>() {
        public fi.solita.utils.functional.Tuple2<T1,T> apply(T t) {
            return $self.<T>append(t);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Tuple1.class.getDeclaredMethod("append", java.lang.Object.class);
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
            return "append";
        }
    
    };
    }
    
    public static final <T1> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Tuple1<?>, java.lang.Object[]> toArray() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Tuple1<?>, java.lang.Object[]>() {
        public java.lang.Object[] apply(fi.solita.utils.functional.Tuple1<?> $self) {
            return $self.toArray();
        }
    };
    }
    
    public static final <T1> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Tuple1<T1>, T1> get_1() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Tuple1<T1>, T1>() {
        public T1 apply(fi.solita.utils.functional.Tuple1<T1> $self) {
            return $self.get_1();
        }
    };
    }
    
    public static final <T1, T> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Tuple1<T1>, fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Tuple2<T,T1>>>  prepend() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Tuple1<T1>, fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Tuple2<T,T1>>> () { protected fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Tuple2<T,T1>> $do(final fi.solita.utils.functional.Tuple1<T1> $self) { return new fi.solita.utils.functional.Function1<T, fi.solita.utils.functional.Tuple2<T,T1>>() {
        public fi.solita.utils.functional.Tuple2<T,T1> apply(T t) {
            return $self.<T>prepend(t);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Tuple1.class.getDeclaredMethod("prepend", java.lang.Object.class);
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
            return "prepend";
        }
    
    };
    }
    
         static final class SerializableTuple_ implements java.io.Serializable {
        public enum Fields {
        }
        
         static final <T1 extends java.io.Serializable> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Tuple1.SerializableTuple<T1>> $() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Tuple1.SerializableTuple<T1>>() {
            public fi.solita.utils.functional.Tuple1.SerializableTuple<T1> apply() {
                return new fi.solita.utils.functional.Tuple1.SerializableTuple<T1>();
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Tuple1.SerializableTuple<T1>> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Tuple1.SerializableTuple<T1>> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Tuple1.SerializableTuple<T1>>)(Object)fi.solita.utils.functional.Tuple1.SerializableTuple.class.getDeclaredConstructor();
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Tuple1.SerializableTuple<T1>> getMember() {
                return $getMember();
            }
        
        };
        }
        
         static final <T1 extends java.io.Serializable> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F1<T1, fi.solita.utils.functional.Tuple1.SerializableTuple<T1>> $1() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F1<T1, fi.solita.utils.functional.Tuple1.SerializableTuple<T1>>() {
            public fi.solita.utils.functional.Tuple1.SerializableTuple<T1> apply(T1 t1) {
                return new fi.solita.utils.functional.Tuple1.SerializableTuple<T1>(t1);
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Tuple1.SerializableTuple<T1>> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Tuple1.SerializableTuple<T1>> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Tuple1.SerializableTuple<T1>>)(Object)fi.solita.utils.functional.Tuple1.SerializableTuple.class.getDeclaredConstructor(java.io.Serializable.class);
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Tuple1.SerializableTuple<T1>> getMember() {
                return $getMember();
            }
        
        };
        }
        
         static final <T1 extends java.io.Serializable> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Tuple1.SerializableTuple<?>, fi.solita.utils.functional.Function1<java.io.ObjectOutputStream, java.lang.Void>>  writeObject() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Tuple1.SerializableTuple<?>, fi.solita.utils.functional.Function1<java.io.ObjectOutputStream, java.lang.Void>> () { protected fi.solita.utils.functional.Function1<java.io.ObjectOutputStream, java.lang.Void> $do(final fi.solita.utils.functional.Tuple1.SerializableTuple<?> $self) { return new fi.solita.utils.functional.Function1<java.io.ObjectOutputStream, java.lang.Void>() {
            public java.lang.Void apply(java.io.ObjectOutputStream out) {
                try {
                    $getMember().invoke($self, (Object)out);
                    return null;
                } catch (RuntimeException $e) {
                    throw $e;
                } catch (Error $e) {
                    throw $e;
                } catch (Throwable $e) {
                    throw new RuntimeException($e);
                }
            }
            };}
            private transient java.lang.reflect.Method $r;
            
            
            private java.lang.reflect.Method $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Tuple1.SerializableTuple.class.getDeclaredMethod("writeObject", java.io.ObjectOutputStream.class);
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
                return "writeObject";
            }
        
        };
        }
        
         static final <T1 extends java.io.Serializable> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Tuple1.SerializableTuple<?>, fi.solita.utils.functional.Function1<java.io.ObjectInputStream, java.lang.Void>>  readObject() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Tuple1.SerializableTuple<?>, fi.solita.utils.functional.Function1<java.io.ObjectInputStream, java.lang.Void>> () { protected fi.solita.utils.functional.Function1<java.io.ObjectInputStream, java.lang.Void> $do(final fi.solita.utils.functional.Tuple1.SerializableTuple<?> $self) { return new fi.solita.utils.functional.Function1<java.io.ObjectInputStream, java.lang.Void>() {
            public java.lang.Void apply(java.io.ObjectInputStream in) {
                try {
                    $getMember().invoke($self, (Object)in);
                    return null;
                } catch (RuntimeException $e) {
                    throw $e;
                } catch (Error $e) {
                    throw $e;
                } catch (Throwable $e) {
                    throw new RuntimeException($e);
                }
            }
            };}
            private transient java.lang.reflect.Method $r;
            
            
            private java.lang.reflect.Method $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Tuple1.SerializableTuple.class.getDeclaredMethod("readObject", java.io.ObjectInputStream.class);
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
                return "readObject";
            }
        
        };
        }
        
        }

}
