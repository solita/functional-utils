package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class SomeImpl_ implements java.io.Serializable {

    public enum Fields {
        t,
    }
    
    protected static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.SomeImpl<T>, T> t() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.SomeImpl<T>, T>() {
        private transient java.lang.reflect.Field $r;
        
        
        private java.lang.reflect.Field $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.SomeImpl.class.getDeclaredField("t");
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
            return "t";
        }
    
    
        protected T $do(fi.solita.utils.functional.SomeImpl<T> $self) {
            return $self.t;
        }
    };
    }
    
     static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.SomeImpl<T>> $() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.SomeImpl<T>>() {
        public fi.solita.utils.functional.SomeImpl<T> apply() {
            return new fi.solita.utils.functional.SomeImpl<T>();
        }
    
        private transient java.lang.reflect.Constructor<fi.solita.utils.functional.SomeImpl<T>> $r;
        
        @SuppressWarnings("unchecked")
        private java.lang.reflect.Constructor<fi.solita.utils.functional.SomeImpl<T>> $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.SomeImpl<T>>)(Object)fi.solita.utils.functional.SomeImpl.class.getDeclaredConstructor();
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
    
        public java.lang.reflect.Constructor<fi.solita.utils.functional.SomeImpl<T>> getMember() {
            return $getMember();
        }
    
    };
    }
    
     static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F1<T, fi.solita.utils.functional.SomeImpl<T>> $1() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F1<T, fi.solita.utils.functional.SomeImpl<T>>() {
        public fi.solita.utils.functional.SomeImpl<T> apply(T t) {
            return new fi.solita.utils.functional.SomeImpl<T>(t);
        }
    
        private transient java.lang.reflect.Constructor<fi.solita.utils.functional.SomeImpl<T>> $r;
        
        @SuppressWarnings("unchecked")
        private java.lang.reflect.Constructor<fi.solita.utils.functional.SomeImpl<T>> $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.SomeImpl<T>>)(Object)fi.solita.utils.functional.SomeImpl.class.getDeclaredConstructor(java.lang.Object.class);
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
    
        public java.lang.reflect.Constructor<fi.solita.utils.functional.SomeImpl<T>> getMember() {
            return $getMember();
        }
    
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.SomeImpl<T>, java.util.Iterator<T>> iterator() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.SomeImpl<T>, java.util.Iterator<T>>() {
        public java.util.Iterator<T> apply(fi.solita.utils.functional.SomeImpl<T> $self) {
            return $self.iterator();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.SomeImpl<T>, T> get() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.SomeImpl<T>, T>() {
        public T apply(fi.solita.utils.functional.SomeImpl<T> $self) {
            return $self.get();
        }
    };
    }
    
    public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.SomeImpl<?>, fi.solita.utils.functional.Function1<java.lang.Object, java.lang.Boolean>>  equals() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.SomeImpl<?>, fi.solita.utils.functional.Function1<java.lang.Object, java.lang.Boolean>> () { protected fi.solita.utils.functional.Function1<java.lang.Object, java.lang.Boolean> $do(final fi.solita.utils.functional.SomeImpl<?> $self) { return new fi.solita.utils.functional.Function1<java.lang.Object, java.lang.Boolean>() {
        public java.lang.Boolean apply(java.lang.Object obj) {
            return $self.equals(obj);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.SomeImpl.class.getDeclaredMethod("equals", java.lang.Object.class);
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
            return "equals";
        }
    
    };
    }
    
    public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.SomeImpl<T>, fi.solita.utils.functional.Function1<T, T>>  getOrElse() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.SomeImpl<T>, fi.solita.utils.functional.Function1<T, T>> () { protected fi.solita.utils.functional.Function1<T, T> $do(final fi.solita.utils.functional.SomeImpl<T> $self) { return new fi.solita.utils.functional.Function1<T, T>() {
        public T apply(T orElse) {
            return $self.getOrElse(orElse);
        }
        };}
        private transient java.lang.reflect.Method $r;
        
        
        private java.lang.reflect.Method $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.SomeImpl.class.getDeclaredMethod("getOrElse", java.lang.Object.class);
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
            return "getOrElse";
        }
    
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.SomeImpl<?>, java.lang.Boolean> isDefined() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.SomeImpl<?>, java.lang.Boolean>() {
        public java.lang.Boolean apply(fi.solita.utils.functional.SomeImpl<?> $self) {
            return $self.isDefined();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.SomeImpl<?>, java.lang.Integer> hashCode() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.SomeImpl<?>, java.lang.Integer>() {
        public java.lang.Integer apply(fi.solita.utils.functional.SomeImpl<?> $self) {
            return $self.hashCode();
        }
    };
    }
    
    public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.SomeImpl<?>, java.lang.String> toString() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.SomeImpl<?>, java.lang.String>() {
        public java.lang.String apply(fi.solita.utils.functional.SomeImpl<?> $self) {
            return $self.toString();
        }
    };
    }
    
         static final class SerializableSomeImpl_ implements java.io.Serializable {
        public enum Fields {
        }
        
         static final <T extends java.io.Serializable> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F1<T, fi.solita.utils.functional.SomeImpl.SerializableSomeImpl<T>> $() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F1<T, fi.solita.utils.functional.SomeImpl.SerializableSomeImpl<T>>() {
            public fi.solita.utils.functional.SomeImpl.SerializableSomeImpl<T> apply(T t) {
                return new fi.solita.utils.functional.SomeImpl.SerializableSomeImpl<T>(t);
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.SomeImpl.SerializableSomeImpl<T>> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.SomeImpl.SerializableSomeImpl<T>> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.SomeImpl.SerializableSomeImpl<T>>)(Object)fi.solita.utils.functional.SomeImpl.SerializableSomeImpl.class.getDeclaredConstructor(java.io.Serializable.class);
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.SomeImpl.SerializableSomeImpl<T>> getMember() {
                return $getMember();
            }
        
        };
        }
        
         static final <T extends java.io.Serializable> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.SomeImpl.SerializableSomeImpl<?>, fi.solita.utils.functional.Function1<java.io.ObjectOutputStream, java.lang.Void>>  writeObject() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.SomeImpl.SerializableSomeImpl<?>, fi.solita.utils.functional.Function1<java.io.ObjectOutputStream, java.lang.Void>> () { protected fi.solita.utils.functional.Function1<java.io.ObjectOutputStream, java.lang.Void> $do(final fi.solita.utils.functional.SomeImpl.SerializableSomeImpl<?> $self) { return new fi.solita.utils.functional.Function1<java.io.ObjectOutputStream, java.lang.Void>() {
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
                        $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.SomeImpl.SerializableSomeImpl.class.getDeclaredMethod("writeObject", java.io.ObjectOutputStream.class);
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
        
         static final <T extends java.io.Serializable> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.SomeImpl.SerializableSomeImpl<?>, fi.solita.utils.functional.Function1<java.io.ObjectInputStream, java.lang.Void>>  readObject() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.SomeImpl.SerializableSomeImpl<?>, fi.solita.utils.functional.Function1<java.io.ObjectInputStream, java.lang.Void>> () { protected fi.solita.utils.functional.Function1<java.io.ObjectInputStream, java.lang.Void> $do(final fi.solita.utils.functional.SomeImpl.SerializableSomeImpl<?> $self) { return new fi.solita.utils.functional.Function1<java.io.ObjectInputStream, java.lang.Void>() {
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
                        $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.SomeImpl.SerializableSomeImpl.class.getDeclaredMethod("readObject", java.io.ObjectInputStream.class);
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
