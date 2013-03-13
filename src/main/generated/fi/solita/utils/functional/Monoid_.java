package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class Monoid_ implements java.io.Serializable {

    public enum Fields {
    }
    
    public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid<T>, T> zero() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid<T>, T>() {
        public T apply(fi.solita.utils.functional.Monoid<T> $self) {
            return $self.zero();
        }
    };
    }
    
        public static final class BooleanDisjunction_ implements java.io.Serializable {
        public enum Fields {
        }
        
        public static final  fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.BooleanDisjunction> $ = new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.BooleanDisjunction>() {
            public fi.solita.utils.functional.Monoid.BooleanDisjunction apply() {
                return new fi.solita.utils.functional.Monoid.BooleanDisjunction();
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.BooleanDisjunction> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.BooleanDisjunction> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.BooleanDisjunction>)(Object)fi.solita.utils.functional.Monoid.BooleanDisjunction.class.getDeclaredConstructor();
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.BooleanDisjunction> getMember() {
                return $getMember();
            }
        
        };
        
        public static final  fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.BooleanDisjunction, java.lang.Boolean> zero = new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.BooleanDisjunction, java.lang.Boolean>() {
            public java.lang.Boolean apply(fi.solita.utils.functional.Monoid.BooleanDisjunction $self) {
                return $self.zero();
            }
        };
        
        }
        public static final class BooleanConjunction_ implements java.io.Serializable {
        public enum Fields {
        }
        
        public static final  fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.BooleanConjunction> $ = new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.BooleanConjunction>() {
            public fi.solita.utils.functional.Monoid.BooleanConjunction apply() {
                return new fi.solita.utils.functional.Monoid.BooleanConjunction();
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.BooleanConjunction> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.BooleanConjunction> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.BooleanConjunction>)(Object)fi.solita.utils.functional.Monoid.BooleanConjunction.class.getDeclaredConstructor();
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.BooleanConjunction> getMember() {
                return $getMember();
            }
        
        };
        
        public static final  fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.BooleanConjunction, java.lang.Boolean> zero = new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.BooleanConjunction, java.lang.Boolean>() {
            public java.lang.Boolean apply(fi.solita.utils.functional.Monoid.BooleanConjunction $self) {
                return $self.zero();
            }
        };
        
        }
        public static final class StringConcat_ implements java.io.Serializable {
        public enum Fields {
        }
        
        public static final  fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.StringConcat> $ = new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.StringConcat>() {
            public fi.solita.utils.functional.Monoid.StringConcat apply() {
                return new fi.solita.utils.functional.Monoid.StringConcat();
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.StringConcat> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.StringConcat> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.StringConcat>)(Object)fi.solita.utils.functional.Monoid.StringConcat.class.getDeclaredConstructor();
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.StringConcat> getMember() {
                return $getMember();
            }
        
        };
        
        public static final  fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.StringConcat, java.lang.String> zero = new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.StringConcat, java.lang.String>() {
            public java.lang.String apply(fi.solita.utils.functional.Monoid.StringConcat $self) {
                return $self.zero();
            }
        };
        
        }
        public static final class LongProduct_ implements java.io.Serializable {
        public enum Fields {
        }
        
        public static final  fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.LongProduct> $ = new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.LongProduct>() {
            public fi.solita.utils.functional.Monoid.LongProduct apply() {
                return new fi.solita.utils.functional.Monoid.LongProduct();
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.LongProduct> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.LongProduct> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.LongProduct>)(Object)fi.solita.utils.functional.Monoid.LongProduct.class.getDeclaredConstructor();
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.LongProduct> getMember() {
                return $getMember();
            }
        
        };
        
        public static final  fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.LongProduct, java.lang.Long> zero = new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.LongProduct, java.lang.Long>() {
            public java.lang.Long apply(fi.solita.utils.functional.Monoid.LongProduct $self) {
                return $self.zero();
            }
        };
        
        }
        public static final class LongSum_ implements java.io.Serializable {
        public enum Fields {
        }
        
        public static final  fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.LongSum> $ = new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.LongSum>() {
            public fi.solita.utils.functional.Monoid.LongSum apply() {
                return new fi.solita.utils.functional.Monoid.LongSum();
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.LongSum> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.LongSum> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.LongSum>)(Object)fi.solita.utils.functional.Monoid.LongSum.class.getDeclaredConstructor();
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.LongSum> getMember() {
                return $getMember();
            }
        
        };
        
        public static final  fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.LongSum, java.lang.Long> zero = new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.LongSum, java.lang.Long>() {
            public java.lang.Long apply(fi.solita.utils.functional.Monoid.LongSum $self) {
                return $self.zero();
            }
        };
        
        }
        public static final class IntProduct_ implements java.io.Serializable {
        public enum Fields {
        }
        
        public static final  fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.IntProduct> $ = new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.IntProduct>() {
            public fi.solita.utils.functional.Monoid.IntProduct apply() {
                return new fi.solita.utils.functional.Monoid.IntProduct();
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.IntProduct> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.IntProduct> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.IntProduct>)(Object)fi.solita.utils.functional.Monoid.IntProduct.class.getDeclaredConstructor();
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.IntProduct> getMember() {
                return $getMember();
            }
        
        };
        
        public static final  fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.IntProduct, java.lang.Integer> zero = new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.IntProduct, java.lang.Integer>() {
            public java.lang.Integer apply(fi.solita.utils.functional.Monoid.IntProduct $self) {
                return $self.zero();
            }
        };
        
        }
        public static final class IntSum_ implements java.io.Serializable {
        public enum Fields {
        }
        
        public static final  fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.IntSum> $ = new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.IntSum>() {
            public fi.solita.utils.functional.Monoid.IntSum apply() {
                return new fi.solita.utils.functional.Monoid.IntSum();
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.IntSum> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.IntSum> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.IntSum>)(Object)fi.solita.utils.functional.Monoid.IntSum.class.getDeclaredConstructor();
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.IntSum> getMember() {
                return $getMember();
            }
        
        };
        
        public static final  fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.IntSum, java.lang.Integer> zero = new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.IntSum, java.lang.Integer>() {
            public java.lang.Integer apply(fi.solita.utils.functional.Monoid.IntSum $self) {
                return $self.zero();
            }
        };
        
        }
        public static final class SetUnion_ implements java.io.Serializable {
        public enum Fields {
        }
        
        public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.SetUnion<T>> $() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.SetUnion<T>>() {
            public fi.solita.utils.functional.Monoid.SetUnion<T> apply() {
                return new fi.solita.utils.functional.Monoid.SetUnion<T>();
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.SetUnion<T>> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.SetUnion<T>> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.SetUnion<T>>)(Object)fi.solita.utils.functional.Monoid.SetUnion.class.getDeclaredConstructor();
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.SetUnion<T>> getMember() {
                return $getMember();
            }
        
        };
        }
        
        public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.SetUnion<T>, java.util.Set<T>> zero() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.SetUnion<T>, java.util.Set<T>>() {
            public java.util.Set<T> apply(fi.solita.utils.functional.Monoid.SetUnion<T> $self) {
                return $self.zero();
            }
        };
        }
        
        }
        public static final class SetIntersection_ implements java.io.Serializable {
        public enum Fields {
        }
        
        public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.SetIntersection<T>> $() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.SetIntersection<T>>() {
            public fi.solita.utils.functional.Monoid.SetIntersection<T> apply() {
                return new fi.solita.utils.functional.Monoid.SetIntersection<T>();
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.SetIntersection<T>> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.SetIntersection<T>> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.SetIntersection<T>>)(Object)fi.solita.utils.functional.Monoid.SetIntersection.class.getDeclaredConstructor();
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.SetIntersection<T>> getMember() {
                return $getMember();
            }
        
        };
        }
        
        public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.SetIntersection<T>, java.util.Set<T>> zero() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.SetIntersection<T>, java.util.Set<T>>() {
            public java.util.Set<T> apply(fi.solita.utils.functional.Monoid.SetIntersection<T> $self) {
                return $self.zero();
            }
        };
        }
        
             static final class AllContainingSet_ implements java.io.Serializable {
            public enum Fields {
            }
            
             static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<T>> $() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<T>>() {
                public fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<T> apply() {
                    return new fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<T>();
                }
            
                private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<T>> $r;
                
                @SuppressWarnings("unchecked")
                private java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<T>> $getMember() {
                    if ($r == null) {
                        try {
                            $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<T>>)(Object)fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet.class.getDeclaredConstructor();
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
            
                public java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<T>> getMember() {
                    return $getMember();
                }
            
            };
            }
            
            public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?>, java.lang.Void> clear() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?>, java.lang.Void>() {
                public java.lang.Void apply(fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?> $self) {
                    $self.clear();
                    return null;
                }
            };
            }
            
            public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?>, java.lang.Boolean> isEmpty() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?>, java.lang.Boolean>() {
                public java.lang.Boolean apply(fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?> $self) {
                    return $self.isEmpty();
                }
            };
            }
            
            public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?>, java.lang.Object[]> toArray() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?>, java.lang.Object[]>() {
                public java.lang.Object[] apply(fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?> $self) {
                    return $self.toArray();
                }
            };
            }
            
            public static final <T, S> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?>, fi.solita.utils.functional.Function1<S[], S[]>>  toArray1() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?>, fi.solita.utils.functional.Function1<S[], S[]>> () { protected fi.solita.utils.functional.Function1<S[], S[]> $do(final fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?> $self) { return new fi.solita.utils.functional.Function1<S[], S[]>() {
                public S[] apply(S[] a) {
                    return $self.<S>toArray(a);
                }
                };}
                private transient java.lang.reflect.Method $r;
                
                
                private java.lang.reflect.Method $getMember() {
                    if ($r == null) {
                        try {
                            $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet.class.getDeclaredMethod("toArray", java.lang.Object.class);
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
                    return "toArray";
                }
            
            };
            }
            
            public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<T>, java.util.Iterator<T>> iterator() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<T>, java.util.Iterator<T>>() {
                public java.util.Iterator<T> apply(fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<T> $self) {
                    return $self.iterator();
                }
            };
            }
            
            public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<T>, fi.solita.utils.functional.Function1<java.util.Collection<? extends T>, java.lang.Boolean>>  addAll() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<T>, fi.solita.utils.functional.Function1<java.util.Collection<? extends T>, java.lang.Boolean>> () { protected fi.solita.utils.functional.Function1<java.util.Collection<? extends T>, java.lang.Boolean> $do(final fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<T> $self) { return new fi.solita.utils.functional.Function1<java.util.Collection<? extends T>, java.lang.Boolean>() {
                public java.lang.Boolean apply(java.util.Collection<? extends T> c) {
                    return $self.addAll(c);
                }
                };}
                private transient java.lang.reflect.Method $r;
                
                
                private java.lang.reflect.Method $getMember() {
                    if ($r == null) {
                        try {
                            $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet.class.getDeclaredMethod("addAll", java.util.Collection.class);
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
                    return "addAll";
                }
            
            };
            }
            
            public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?>, fi.solita.utils.functional.Function1<java.lang.Object, java.lang.Boolean>>  remove() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?>, fi.solita.utils.functional.Function1<java.lang.Object, java.lang.Boolean>> () { protected fi.solita.utils.functional.Function1<java.lang.Object, java.lang.Boolean> $do(final fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?> $self) { return new fi.solita.utils.functional.Function1<java.lang.Object, java.lang.Boolean>() {
                public java.lang.Boolean apply(java.lang.Object o) {
                    return $self.remove(o);
                }
                };}
                private transient java.lang.reflect.Method $r;
                
                
                private java.lang.reflect.Method $getMember() {
                    if ($r == null) {
                        try {
                            $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet.class.getDeclaredMethod("remove", java.lang.Object.class);
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
                    return "remove";
                }
            
            };
            }
            
            public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?>, fi.solita.utils.functional.Function1<java.util.Collection<?>, java.lang.Boolean>>  containsAll() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?>, fi.solita.utils.functional.Function1<java.util.Collection<?>, java.lang.Boolean>> () { protected fi.solita.utils.functional.Function1<java.util.Collection<?>, java.lang.Boolean> $do(final fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?> $self) { return new fi.solita.utils.functional.Function1<java.util.Collection<?>, java.lang.Boolean>() {
                public java.lang.Boolean apply(java.util.Collection<?> c) {
                    return $self.containsAll(c);
                }
                };}
                private transient java.lang.reflect.Method $r;
                
                
                private java.lang.reflect.Method $getMember() {
                    if ($r == null) {
                        try {
                            $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet.class.getDeclaredMethod("containsAll", java.util.Collection.class);
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
                    return "containsAll";
                }
            
            };
            }
            
            public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?>, fi.solita.utils.functional.Function1<java.util.Collection<?>, java.lang.Boolean>>  retainAll() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?>, fi.solita.utils.functional.Function1<java.util.Collection<?>, java.lang.Boolean>> () { protected fi.solita.utils.functional.Function1<java.util.Collection<?>, java.lang.Boolean> $do(final fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?> $self) { return new fi.solita.utils.functional.Function1<java.util.Collection<?>, java.lang.Boolean>() {
                public java.lang.Boolean apply(java.util.Collection<?> c) {
                    return $self.retainAll(c);
                }
                };}
                private transient java.lang.reflect.Method $r;
                
                
                private java.lang.reflect.Method $getMember() {
                    if ($r == null) {
                        try {
                            $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet.class.getDeclaredMethod("retainAll", java.util.Collection.class);
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
                    return "retainAll";
                }
            
            };
            }
            
            public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?>, fi.solita.utils.functional.Function1<java.lang.Object, java.lang.Boolean>>  contains() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?>, fi.solita.utils.functional.Function1<java.lang.Object, java.lang.Boolean>> () { protected fi.solita.utils.functional.Function1<java.lang.Object, java.lang.Boolean> $do(final fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?> $self) { return new fi.solita.utils.functional.Function1<java.lang.Object, java.lang.Boolean>() {
                public java.lang.Boolean apply(java.lang.Object o) {
                    return $self.contains(o);
                }
                };}
                private transient java.lang.reflect.Method $r;
                
                
                private java.lang.reflect.Method $getMember() {
                    if ($r == null) {
                        try {
                            $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet.class.getDeclaredMethod("contains", java.lang.Object.class);
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
                    return "contains";
                }
            
            };
            }
            
            public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<T>, fi.solita.utils.functional.Function1<T, java.lang.Boolean>>  add() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<T>, fi.solita.utils.functional.Function1<T, java.lang.Boolean>> () { protected fi.solita.utils.functional.Function1<T, java.lang.Boolean> $do(final fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<T> $self) { return new fi.solita.utils.functional.Function1<T, java.lang.Boolean>() {
                public java.lang.Boolean apply(T e) {
                    return $self.add(e);
                }
                };}
                private transient java.lang.reflect.Method $r;
                
                
                private java.lang.reflect.Method $getMember() {
                    if ($r == null) {
                        try {
                            $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet.class.getDeclaredMethod("add", java.lang.Object.class);
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
                    return "add";
                }
            
            };
            }
            
            public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?>, fi.solita.utils.functional.Function1<java.util.Collection<?>, java.lang.Boolean>>  removeAll() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMethodMeta_<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?>, fi.solita.utils.functional.Function1<java.util.Collection<?>, java.lang.Boolean>> () { protected fi.solita.utils.functional.Function1<java.util.Collection<?>, java.lang.Boolean> $do(final fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?> $self) { return new fi.solita.utils.functional.Function1<java.util.Collection<?>, java.lang.Boolean>() {
                public java.lang.Boolean apply(java.util.Collection<?> c) {
                    return $self.removeAll(c);
                }
                };}
                private transient java.lang.reflect.Method $r;
                
                
                private java.lang.reflect.Method $getMember() {
                    if ($r == null) {
                        try {
                            $r = (java.lang.reflect.Method)(Object)fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet.class.getDeclaredMethod("removeAll", java.util.Collection.class);
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
                    return "removeAll";
                }
            
            };
            }
            
            public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?>, java.lang.Integer> size() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?>, java.lang.Integer>() {
                public java.lang.Integer apply(fi.solita.utils.functional.Monoid.SetIntersection.AllContainingSet<?> $self) {
                    return $self.size();
                }
            };
            }
            
            }
        }
        public static final class ComparatorConcat_ implements java.io.Serializable {
        public enum Fields {
        }
        
        public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.ComparatorConcat<T>> $() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F0<fi.solita.utils.functional.Monoid.ComparatorConcat<T>>() {
            public fi.solita.utils.functional.Monoid.ComparatorConcat<T> apply() {
                return new fi.solita.utils.functional.Monoid.ComparatorConcat<T>();
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.ComparatorConcat<T>> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.ComparatorConcat<T>> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.ComparatorConcat<T>>)(Object)fi.solita.utils.functional.Monoid.ComparatorConcat.class.getDeclaredConstructor();
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Monoid.ComparatorConcat<T>> getMember() {
                return $getMember();
            }
        
        };
        }
        
        public static final <T, C extends java.util.Comparator<?>> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.ComparatorConcat<T>, fi.solita.utils.functional.Monoid<C>> of() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.ComparatorConcat<T>, fi.solita.utils.functional.Monoid<C>>() {
            public fi.solita.utils.functional.Monoid<C> apply(fi.solita.utils.functional.Monoid.ComparatorConcat<T> $self) {
                return $self.<C>of();
            }
        };
        }
        
        public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.ComparatorConcat<T>, java.util.Comparator<T>> zero() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Monoid.ComparatorConcat<T>, java.util.Comparator<T>>() {
            public java.util.Comparator<T> apply(fi.solita.utils.functional.Monoid.ComparatorConcat<T> $self) {
                return $self.zero();
            }
        };
        }
        
        }

}
