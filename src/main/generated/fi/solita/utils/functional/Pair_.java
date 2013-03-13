package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class Pair_ implements java.io.Serializable {

    public enum Fields {
        left,
        right,
    }
    
    public static final <LEFT, RIGHT> fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Pair<LEFT, RIGHT>, LEFT> left() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Pair<LEFT, RIGHT>, LEFT>() {
        private transient java.lang.reflect.Field $r;
        
        
        private java.lang.reflect.Field $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.Pair.class.getDeclaredField("left");
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
            return "left";
        }
    
    
        protected LEFT $do(fi.solita.utils.functional.Pair<LEFT, RIGHT> $self) {
            return $self.left;
        }
    };
    }
    
    public static final <LEFT, RIGHT> fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Pair<LEFT, RIGHT>, RIGHT> right() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Pair<LEFT, RIGHT>, RIGHT>() {
        private transient java.lang.reflect.Field $r;
        
        
        private java.lang.reflect.Field $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.Pair.class.getDeclaredField("right");
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
            return "right";
        }
    
    
        protected RIGHT $do(fi.solita.utils.functional.Pair<LEFT, RIGHT> $self) {
            return $self.right;
        }
    };
    }
    
     static final <LEFT, RIGHT> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Pair<LEFT, RIGHT>> $() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Pair<LEFT, RIGHT>>() {
        public fi.solita.utils.functional.Pair<LEFT, RIGHT> apply() {
            return new fi.solita.utils.functional.Pair<LEFT, RIGHT>();
        }
    
        private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Pair<LEFT, RIGHT>> $r;
        
        @SuppressWarnings("unchecked")
        private java.lang.reflect.Constructor<fi.solita.utils.functional.Pair<LEFT, RIGHT>> $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Pair<LEFT, RIGHT>>)(Object)fi.solita.utils.functional.Pair.class.getDeclaredConstructor();
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
    
        public java.lang.reflect.Constructor<fi.solita.utils.functional.Pair<LEFT, RIGHT>> getMember() {
            return $getMember();
        }
    
    };
    }
    
    public static final <LEFT, RIGHT> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F2<LEFT, RIGHT, fi.solita.utils.functional.Pair<LEFT, RIGHT>> $1() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F2<LEFT, RIGHT, fi.solita.utils.functional.Pair<LEFT, RIGHT>>() {
        public fi.solita.utils.functional.Pair<LEFT, RIGHT> apply(LEFT left, RIGHT right) {
            return new fi.solita.utils.functional.Pair<LEFT, RIGHT>(left, right);
        }
    
        private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Pair<LEFT, RIGHT>> $r;
        
        @SuppressWarnings("unchecked")
        private java.lang.reflect.Constructor<fi.solita.utils.functional.Pair<LEFT, RIGHT>> $getMember() {
            if ($r == null) {
                try {
                    $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Pair<LEFT, RIGHT>>)(Object)fi.solita.utils.functional.Pair.class.getDeclaredConstructor(java.lang.Object.class, java.lang.Object.class);
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
    
        public java.lang.reflect.Constructor<fi.solita.utils.functional.Pair<LEFT, RIGHT>> getMember() {
            return $getMember();
        }
    
    };
    }
    
    public static final <LEFT, RIGHT> fi.solita.utils.functional.Function2<LEFT, RIGHT, fi.solita.utils.functional.Pair<LEFT,RIGHT>> of() { return new fi.solita.utils.functional.Function2<LEFT, RIGHT, fi.solita.utils.functional.Pair<LEFT,RIGHT>>() {
        public fi.solita.utils.functional.Pair<LEFT,RIGHT> apply(LEFT left, RIGHT right) {
            return fi.solita.utils.functional.Pair.<LEFT, RIGHT>of(left, right);
        }
    };
    }
    
    public static final <LEFT extends java.io.Serializable, RIGHT extends java.io.Serializable, R extends fi.solita.utils.functional.Pair<LEFT, RIGHT> & java.io.Serializable> fi.solita.utils.functional.Function2<LEFT, RIGHT, R> of1() { return new fi.solita.utils.functional.Function2<LEFT, RIGHT, R>() {
        public R apply(LEFT left, RIGHT right) {
            return fi.solita.utils.functional.Pair.<LEFT, RIGHT, R>of(left, right);
        }
    };
    }
    
        public static final class SerializablePair_ implements java.io.Serializable {
        public enum Fields {
        }
        
         static final <LEFT extends java.io.Serializable, RIGHT extends java.io.Serializable> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Pair.SerializablePair<LEFT, RIGHT>> $() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Pair.SerializablePair<LEFT, RIGHT>>() {
            public fi.solita.utils.functional.Pair.SerializablePair<LEFT, RIGHT> apply() {
                try {
                    return (fi.solita.utils.functional.Pair.SerializablePair<LEFT, RIGHT>)$getMember().newInstance();
                } catch (RuntimeException $e) {
                    throw $e;
                } catch (Error $e) {
                    throw $e;
                } catch (Throwable $e) {
                    throw new RuntimeException($e);
                }
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Pair.SerializablePair<LEFT, RIGHT>> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Pair.SerializablePair<LEFT, RIGHT>> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Pair.SerializablePair<LEFT, RIGHT>>)(Object)fi.solita.utils.functional.Pair.SerializablePair.class.getDeclaredConstructor();
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Pair.SerializablePair<LEFT, RIGHT>> getMember() {
                return $getMember();
            }
        
        };
        }
        
         static final <LEFT extends java.io.Serializable, RIGHT extends java.io.Serializable> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F2<LEFT, RIGHT, fi.solita.utils.functional.Pair.SerializablePair<LEFT, RIGHT>> $1() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F2<LEFT, RIGHT, fi.solita.utils.functional.Pair.SerializablePair<LEFT, RIGHT>>() {
            public fi.solita.utils.functional.Pair.SerializablePair<LEFT, RIGHT> apply(LEFT left, RIGHT right) {
                try {
                    return (fi.solita.utils.functional.Pair.SerializablePair<LEFT, RIGHT>)$getMember().newInstance((Object)left, (Object)right);
                } catch (RuntimeException $e) {
                    throw $e;
                } catch (Error $e) {
                    throw $e;
                } catch (Throwable $e) {
                    throw new RuntimeException($e);
                }
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Pair.SerializablePair<LEFT, RIGHT>> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Pair.SerializablePair<LEFT, RIGHT>> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Pair.SerializablePair<LEFT, RIGHT>>)(Object)fi.solita.utils.functional.Pair.SerializablePair.class.getDeclaredConstructor(java.io.Serializable.class, java.io.Serializable.class);
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Pair.SerializablePair<LEFT, RIGHT>> getMember() {
                return $getMember();
            }
        
        };
        }
        
         static final <LEFT extends java.io.Serializable, RIGHT extends java.io.Serializable> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Pair.SerializablePair<?, ?>, fi.solita.utils.functional.Function1<java.io.ObjectOutputStream, java.lang.Void>>  writeObject() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Pair.SerializablePair<?, ?>, fi.solita.utils.functional.Function1<java.io.ObjectOutputStream, java.lang.Void>> () { protected fi.solita.utils.functional.Function1<java.io.ObjectOutputStream, java.lang.Void> $do(final fi.solita.utils.functional.Pair.SerializablePair<?, ?> $self) { return new fi.solita.utils.functional.Function1<java.io.ObjectOutputStream, java.lang.Void>() {
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
                        $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Pair.SerializablePair.class.getDeclaredMethod("writeObject", java.io.ObjectOutputStream.class);
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
        
         static final <LEFT extends java.io.Serializable, RIGHT extends java.io.Serializable> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Pair.SerializablePair<?, ?>, fi.solita.utils.functional.Function1<java.io.ObjectInputStream, java.lang.Void>>  readObject() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Pair.SerializablePair<?, ?>, fi.solita.utils.functional.Function1<java.io.ObjectInputStream, java.lang.Void>> () { protected fi.solita.utils.functional.Function1<java.io.ObjectInputStream, java.lang.Void> $do(final fi.solita.utils.functional.Pair.SerializablePair<?, ?> $self) { return new fi.solita.utils.functional.Function1<java.io.ObjectInputStream, java.lang.Void>() {
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
                        $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Pair.SerializablePair.class.getDeclaredMethod("readObject", java.io.ObjectInputStream.class);
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
