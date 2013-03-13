package fi.solita.utils.functional;


@javax.annotation.Generated("fi.solita.utils.codegen.CommonMetadataProcessor")
public final class Iterables_ implements java.io.Serializable {

    public enum Fields {
    }
    
     static final  fi.solita.utils.functional.Function1<java.lang.Iterable<?>, fi.solita.utils.functional.Option<java.lang.Integer>> resolveSize = new fi.solita.utils.functional.Function1<java.lang.Iterable<?>, fi.solita.utils.functional.Option<java.lang.Integer>>() {
        public fi.solita.utils.functional.Option<java.lang.Integer> apply(java.lang.Iterable<?> iterable) {
            return fi.solita.utils.functional.Iterables.resolveSize(iterable);
        }
    };
    
         static final class RangeIterable_ implements java.io.Serializable {
        public enum Fields {
            from,
            toInclusive,
        }
        
         static final fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.RangeIterable, java.lang.Integer> from = new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.RangeIterable, java.lang.Integer>() {
            private transient java.lang.reflect.Field $r;
            
            
            private java.lang.reflect.Field $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.Iterables.RangeIterable.class.getDeclaredField("from");
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
                return "from";
            }
        
        
            protected java.lang.Integer $do(fi.solita.utils.functional.Iterables.RangeIterable $self) {
                try {
                    return (java.lang.Integer)$getMember().get($self);
                } catch (RuntimeException $e) {
                    throw $e;
                } catch (Error $e) {
                    throw $e;
                } catch (Throwable $e) {
                    throw new RuntimeException($e);
                }
            }
        };
        
         static final fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.RangeIterable, java.lang.Integer> toInclusive = new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.RangeIterable, java.lang.Integer>() {
            private transient java.lang.reflect.Field $r;
            
            
            private java.lang.reflect.Field $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.Iterables.RangeIterable.class.getDeclaredField("toInclusive");
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
                return "toInclusive";
            }
        
        
            protected java.lang.Integer $do(fi.solita.utils.functional.Iterables.RangeIterable $self) {
                try {
                    return (java.lang.Integer)$getMember().get($self);
                } catch (RuntimeException $e) {
                    throw $e;
                } catch (Error $e) {
                    throw $e;
                } catch (Throwable $e) {
                    throw new RuntimeException($e);
                }
            }
        };
        
        public static final  fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F1<java.lang.Integer, fi.solita.utils.functional.Iterables.RangeIterable> $ = new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F1<java.lang.Integer, fi.solita.utils.functional.Iterables.RangeIterable>() {
            public fi.solita.utils.functional.Iterables.RangeIterable apply(java.lang.Integer from) {
                return new fi.solita.utils.functional.Iterables.RangeIterable(from);
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.RangeIterable> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.RangeIterable> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.RangeIterable>)(Object)fi.solita.utils.functional.Iterables.RangeIterable.class.getDeclaredConstructor(int.class);
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.RangeIterable> getMember() {
                return $getMember();
            }
        
        };
        
        public static final  fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F2<java.lang.Integer, java.lang.Integer, fi.solita.utils.functional.Iterables.RangeIterable> $1 = new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F2<java.lang.Integer, java.lang.Integer, fi.solita.utils.functional.Iterables.RangeIterable>() {
            public fi.solita.utils.functional.Iterables.RangeIterable apply(java.lang.Integer from, java.lang.Integer toInclusive) {
                return new fi.solita.utils.functional.Iterables.RangeIterable(from, toInclusive);
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.RangeIterable> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.RangeIterable> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.RangeIterable>)(Object)fi.solita.utils.functional.Iterables.RangeIterable.class.getDeclaredConstructor(int.class, int.class);
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.RangeIterable> getMember() {
                return $getMember();
            }
        
        };
        
        public static final  fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.RangeIterable, java.util.Iterator<java.lang.Integer>> iterator = new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.RangeIterable, java.util.Iterator<java.lang.Integer>>() {
            public java.util.Iterator<java.lang.Integer> apply(fi.solita.utils.functional.Iterables.RangeIterable $self) {
                return $self.iterator();
            }
        };
        
        public static final  fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.RangeIterable, fi.solita.utils.functional.Option<java.lang.Integer>> size = new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.RangeIterable, fi.solita.utils.functional.Option<java.lang.Integer>>() {
            public fi.solita.utils.functional.Option<java.lang.Integer> apply(fi.solita.utils.functional.Iterables.RangeIterable $self) {
                return $self.size();
            }
        };
        
        }
         static final class RepeatingIterable_ implements java.io.Serializable {
        public enum Fields {
            amount,
            value,
        }
        
         static final fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.RepeatingIterable<?>, fi.solita.utils.functional.Option<java.lang.Integer>> amount = new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.RepeatingIterable<?>, fi.solita.utils.functional.Option<java.lang.Integer>>() {
            private transient java.lang.reflect.Field $r;
            
            
            private java.lang.reflect.Field $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.Iterables.RepeatingIterable.class.getDeclaredField("amount");
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
                return "amount";
            }
        
        
            @SuppressWarnings("unchecked")
            protected fi.solita.utils.functional.Option<java.lang.Integer> $do(fi.solita.utils.functional.Iterables.RepeatingIterable<?> $self) {
                try {
                    return (fi.solita.utils.functional.Option<java.lang.Integer>)$getMember().get($self);
                } catch (RuntimeException $e) {
                    throw $e;
                } catch (Error $e) {
                    throw $e;
                } catch (Throwable $e) {
                    throw new RuntimeException($e);
                }
            }
        };
        
         static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.RepeatingIterable<T>, T> value() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.RepeatingIterable<T>, T>() {
            private transient java.lang.reflect.Field $r;
            
            
            private java.lang.reflect.Field $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.Iterables.RepeatingIterable.class.getDeclaredField("value");
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
                return "value";
            }
        
        
            @SuppressWarnings("unchecked")
            protected T $do(fi.solita.utils.functional.Iterables.RepeatingIterable<T> $self) {
                try {
                    return (T)$getMember().get($self);
                } catch (RuntimeException $e) {
                    throw $e;
                } catch (Error $e) {
                    throw $e;
                } catch (Throwable $e) {
                    throw new RuntimeException($e);
                }
            }
        };
        }
        
        public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F1<T, fi.solita.utils.functional.Iterables.RepeatingIterable<T>> $() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F1<T, fi.solita.utils.functional.Iterables.RepeatingIterable<T>>() {
            public fi.solita.utils.functional.Iterables.RepeatingIterable<T> apply(T value) {
                return new fi.solita.utils.functional.Iterables.RepeatingIterable<T>(value);
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.RepeatingIterable<T>> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.RepeatingIterable<T>> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.RepeatingIterable<T>>)(Object)fi.solita.utils.functional.Iterables.RepeatingIterable.class.getDeclaredConstructor(java.lang.Object.class);
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.RepeatingIterable<T>> getMember() {
                return $getMember();
            }
        
        };
        }
        
        public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F2<T, java.lang.Integer, fi.solita.utils.functional.Iterables.RepeatingIterable<T>> $1() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F2<T, java.lang.Integer, fi.solita.utils.functional.Iterables.RepeatingIterable<T>>() {
            public fi.solita.utils.functional.Iterables.RepeatingIterable<T> apply(T value, java.lang.Integer amount) {
                return new fi.solita.utils.functional.Iterables.RepeatingIterable<T>(value, amount);
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.RepeatingIterable<T>> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.RepeatingIterable<T>> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.RepeatingIterable<T>>)(Object)fi.solita.utils.functional.Iterables.RepeatingIterable.class.getDeclaredConstructor(java.lang.Object.class, int.class);
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.RepeatingIterable<T>> getMember() {
                return $getMember();
            }
        
        };
        }
        
        public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.RepeatingIterable<T>, java.util.Iterator<T>> iterator() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.RepeatingIterable<T>, java.util.Iterator<T>>() {
            public java.util.Iterator<T> apply(fi.solita.utils.functional.Iterables.RepeatingIterable<T> $self) {
                return $self.iterator();
            }
        };
        }
        
        public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.RepeatingIterable<?>, fi.solita.utils.functional.Option<java.lang.Integer>> size() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.RepeatingIterable<?>, fi.solita.utils.functional.Option<java.lang.Integer>>() {
            public fi.solita.utils.functional.Option<java.lang.Integer> apply(fi.solita.utils.functional.Iterables.RepeatingIterable<?> $self) {
                return $self.size();
            }
        };
        }
        
        }
         static final class PossiblySizeAwareIterable_ implements java.io.Serializable {
        public enum Fields {
        }
        
        public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.PossiblySizeAwareIterable<?>, java.lang.String> toString() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.PossiblySizeAwareIterable<?>, java.lang.String>() {
            public java.lang.String apply(fi.solita.utils.functional.Iterables.PossiblySizeAwareIterable<?> $self) {
                return $self.toString();
            }
        };
        }
        
        public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.PossiblySizeAwareIterable<?>, fi.solita.utils.functional.Option<java.lang.Integer>> size() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.PossiblySizeAwareIterable<?>, fi.solita.utils.functional.Option<java.lang.Integer>>() {
            public fi.solita.utils.functional.Option<java.lang.Integer> apply(fi.solita.utils.functional.Iterables.PossiblySizeAwareIterable<?> $self) {
                return $self.size();
            }
        };
        }
        
        }
         static final class ZippingIterable_ implements java.io.Serializable {
        public enum Fields {
            elements1,
            elements2,
        }
        
         static final <A, B> fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.ZippingIterable<A, B>, java.lang.Iterable<A>> elements1() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.ZippingIterable<A, B>, java.lang.Iterable<A>>() {
            private transient java.lang.reflect.Field $r;
            
            
            private java.lang.reflect.Field $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.Iterables.ZippingIterable.class.getDeclaredField("elements1");
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
                return "elements1";
            }
        
        
            @SuppressWarnings("unchecked")
            protected java.lang.Iterable<A> $do(fi.solita.utils.functional.Iterables.ZippingIterable<A, B> $self) {
                try {
                    return (java.lang.Iterable<A>)$getMember().get($self);
                } catch (RuntimeException $e) {
                    throw $e;
                } catch (Error $e) {
                    throw $e;
                } catch (Throwable $e) {
                    throw new RuntimeException($e);
                }
            }
        };
        }
        
         static final <A, B> fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.ZippingIterable<A, B>, java.lang.Iterable<B>> elements2() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.ZippingIterable<A, B>, java.lang.Iterable<B>>() {
            private transient java.lang.reflect.Field $r;
            
            
            private java.lang.reflect.Field $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.Iterables.ZippingIterable.class.getDeclaredField("elements2");
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
                return "elements2";
            }
        
        
            @SuppressWarnings("unchecked")
            protected java.lang.Iterable<B> $do(fi.solita.utils.functional.Iterables.ZippingIterable<A, B> $self) {
                try {
                    return (java.lang.Iterable<B>)$getMember().get($self);
                } catch (RuntimeException $e) {
                    throw $e;
                } catch (Error $e) {
                    throw $e;
                } catch (Throwable $e) {
                    throw new RuntimeException($e);
                }
            }
        };
        }
        
        public static final <A, B> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F2<java.lang.Iterable<A>, java.lang.Iterable<B>, fi.solita.utils.functional.Iterables.ZippingIterable<A, B>> $() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F2<java.lang.Iterable<A>, java.lang.Iterable<B>, fi.solita.utils.functional.Iterables.ZippingIterable<A, B>>() {
            public fi.solita.utils.functional.Iterables.ZippingIterable<A, B> apply(java.lang.Iterable<A> elements1, java.lang.Iterable<B> elements2) {
                return new fi.solita.utils.functional.Iterables.ZippingIterable<A, B>(elements1, elements2);
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.ZippingIterable<A, B>> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.ZippingIterable<A, B>> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.ZippingIterable<A, B>>)(Object)fi.solita.utils.functional.Iterables.ZippingIterable.class.getDeclaredConstructor(java.lang.Iterable.class, java.lang.Iterable.class);
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.ZippingIterable<A, B>> getMember() {
                return $getMember();
            }
        
        };
        }
        
        public static final <A, B> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.ZippingIterable<A, B>, java.util.Iterator<java.util.Map.Entry<A,B>>> iterator() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.ZippingIterable<A, B>, java.util.Iterator<java.util.Map.Entry<A,B>>>() {
            public java.util.Iterator<java.util.Map.Entry<A,B>> apply(fi.solita.utils.functional.Iterables.ZippingIterable<A, B> $self) {
                return $self.iterator();
            }
        };
        }
        
        public static final <A, B> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.ZippingIterable<?, ?>, fi.solita.utils.functional.Option<java.lang.Integer>> size() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.ZippingIterable<?, ?>, fi.solita.utils.functional.Option<java.lang.Integer>>() {
            public fi.solita.utils.functional.Option<java.lang.Integer> apply(fi.solita.utils.functional.Iterables.ZippingIterable<?, ?> $self) {
                return $self.size();
            }
        };
        }
        
        }
         static final class ConcatenatingIterable_ implements java.io.Serializable {
        public enum Fields {
            elements,
        }
        
         static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.ConcatenatingIterable<T>, java.lang.Iterable<? extends java.lang.Iterable<? extends T>>> elements() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.ConcatenatingIterable<T>, java.lang.Iterable<? extends java.lang.Iterable<? extends T>>>() {
            private transient java.lang.reflect.Field $r;
            
            
            private java.lang.reflect.Field $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.Iterables.ConcatenatingIterable.class.getDeclaredField("elements");
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
                return "elements";
            }
        
        
            @SuppressWarnings("unchecked")
            protected java.lang.Iterable<? extends java.lang.Iterable<? extends T>> $do(fi.solita.utils.functional.Iterables.ConcatenatingIterable<T> $self) {
                try {
                    return (java.lang.Iterable<? extends java.lang.Iterable<? extends T>>)$getMember().get($self);
                } catch (RuntimeException $e) {
                    throw $e;
                } catch (Error $e) {
                    throw $e;
                } catch (Throwable $e) {
                    throw new RuntimeException($e);
                }
            }
        };
        }
        
        public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F1<java.lang.Iterable<? extends java.lang.Iterable<? extends T>>, fi.solita.utils.functional.Iterables.ConcatenatingIterable<T>> $() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F1<java.lang.Iterable<? extends java.lang.Iterable<? extends T>>, fi.solita.utils.functional.Iterables.ConcatenatingIterable<T>>() {
            public fi.solita.utils.functional.Iterables.ConcatenatingIterable<T> apply(java.lang.Iterable<? extends java.lang.Iterable<? extends T>> elements) {
                return new fi.solita.utils.functional.Iterables.ConcatenatingIterable<T>(elements);
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.ConcatenatingIterable<T>> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.ConcatenatingIterable<T>> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.ConcatenatingIterable<T>>)(Object)fi.solita.utils.functional.Iterables.ConcatenatingIterable.class.getDeclaredConstructor(java.lang.Iterable.class);
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.ConcatenatingIterable<T>> getMember() {
                return $getMember();
            }
        
        };
        }
        
        public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.ConcatenatingIterable<T>, java.util.Iterator<T>> iterator() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.ConcatenatingIterable<T>, java.util.Iterator<T>>() {
            public java.util.Iterator<T> apply(fi.solita.utils.functional.Iterables.ConcatenatingIterable<T> $self) {
                return $self.iterator();
            }
        };
        }
        
        public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.ConcatenatingIterable<?>, fi.solita.utils.functional.Option<java.lang.Integer>> size() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.ConcatenatingIterable<?>, fi.solita.utils.functional.Option<java.lang.Integer>>() {
            public fi.solita.utils.functional.Option<java.lang.Integer> apply(fi.solita.utils.functional.Iterables.ConcatenatingIterable<?> $self) {
                return $self.size();
            }
        };
        }
        
        }
         static final class FilteringIterable_ implements java.io.Serializable {
        public enum Fields {
            filter,
            iterable,
        }
        
         static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.FilteringIterable<T>, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>> filter() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.FilteringIterable<T>, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>>() {
            private transient java.lang.reflect.Field $r;
            
            
            private java.lang.reflect.Field $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.Iterables.FilteringIterable.class.getDeclaredField("filter");
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
                return "filter";
            }
        
        
            @SuppressWarnings("unchecked")
            protected fi.solita.utils.functional.Apply<? super T,java.lang.Boolean> $do(fi.solita.utils.functional.Iterables.FilteringIterable<T> $self) {
                try {
                    return (fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>)$getMember().get($self);
                } catch (RuntimeException $e) {
                    throw $e;
                } catch (Error $e) {
                    throw $e;
                } catch (Throwable $e) {
                    throw new RuntimeException($e);
                }
            }
        };
        }
        
         static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.FilteringIterable<T>, java.lang.Iterable<T>> iterable() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.FilteringIterable<T>, java.lang.Iterable<T>>() {
            private transient java.lang.reflect.Field $r;
            
            
            private java.lang.reflect.Field $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.Iterables.FilteringIterable.class.getDeclaredField("iterable");
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
                return "iterable";
            }
        
        
            @SuppressWarnings("unchecked")
            protected java.lang.Iterable<T> $do(fi.solita.utils.functional.Iterables.FilteringIterable<T> $self) {
                try {
                    return (java.lang.Iterable<T>)$getMember().get($self);
                } catch (RuntimeException $e) {
                    throw $e;
                } catch (Error $e) {
                    throw $e;
                } catch (Throwable $e) {
                    throw new RuntimeException($e);
                }
            }
        };
        }
        
        public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F2<java.lang.Iterable<T>, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, fi.solita.utils.functional.Iterables.FilteringIterable<T>> $() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F2<java.lang.Iterable<T>, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean>, fi.solita.utils.functional.Iterables.FilteringIterable<T>>() {
            public fi.solita.utils.functional.Iterables.FilteringIterable<T> apply(java.lang.Iterable<T> iterable, fi.solita.utils.functional.Apply<? super T,java.lang.Boolean> filter) {
                return new fi.solita.utils.functional.Iterables.FilteringIterable<T>(iterable, filter);
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.FilteringIterable<T>> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.FilteringIterable<T>> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.FilteringIterable<T>>)(Object)fi.solita.utils.functional.Iterables.FilteringIterable.class.getDeclaredConstructor(java.lang.Iterable.class, fi.solita.utils.functional.Apply.class);
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.FilteringIterable<T>> getMember() {
                return $getMember();
            }
        
        };
        }
        
        public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.FilteringIterable<T>, java.util.Iterator<T>> iterator() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.FilteringIterable<T>, java.util.Iterator<T>>() {
            public java.util.Iterator<T> apply(fi.solita.utils.functional.Iterables.FilteringIterable<T> $self) {
                return $self.iterator();
            }
        };
        }
        
        public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.FilteringIterable<?>, fi.solita.utils.functional.Option<java.lang.Integer>> size() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.FilteringIterable<?>, fi.solita.utils.functional.Option<java.lang.Integer>>() {
            public fi.solita.utils.functional.Option<java.lang.Integer> apply(fi.solita.utils.functional.Iterables.FilteringIterable<?> $self) {
                return $self.size();
            }
        };
        }
        
        }
         static final class TransformingIterable_ implements java.io.Serializable {
        public enum Fields {
            iterable,
            transformer,
        }
        
         static final <S, T> fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.TransformingIterable<S, T>, java.lang.Iterable<S>> iterable() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.TransformingIterable<S, T>, java.lang.Iterable<S>>() {
            private transient java.lang.reflect.Field $r;
            
            
            private java.lang.reflect.Field $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.Iterables.TransformingIterable.class.getDeclaredField("iterable");
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
                return "iterable";
            }
        
        
            @SuppressWarnings("unchecked")
            protected java.lang.Iterable<S> $do(fi.solita.utils.functional.Iterables.TransformingIterable<S, T> $self) {
                try {
                    return (java.lang.Iterable<S>)$getMember().get($self);
                } catch (RuntimeException $e) {
                    throw $e;
                } catch (Error $e) {
                    throw $e;
                } catch (Throwable $e) {
                    throw new RuntimeException($e);
                }
            }
        };
        }
        
         static final <S, T> fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.TransformingIterable<S, T>, fi.solita.utils.functional.Apply<? super S,? extends T>> transformer() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.TransformingIterable<S, T>, fi.solita.utils.functional.Apply<? super S,? extends T>>() {
            private transient java.lang.reflect.Field $r;
            
            
            private java.lang.reflect.Field $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.Iterables.TransformingIterable.class.getDeclaredField("transformer");
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
                return "transformer";
            }
        
        
            @SuppressWarnings("unchecked")
            protected fi.solita.utils.functional.Apply<? super S,? extends T> $do(fi.solita.utils.functional.Iterables.TransformingIterable<S, T> $self) {
                try {
                    return (fi.solita.utils.functional.Apply<? super S,? extends T>)$getMember().get($self);
                } catch (RuntimeException $e) {
                    throw $e;
                } catch (Error $e) {
                    throw $e;
                } catch (Throwable $e) {
                    throw new RuntimeException($e);
                }
            }
        };
        }
        
        public static final <S, T> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F2<java.lang.Iterable<S>, fi.solita.utils.functional.Apply<? super S,? extends T>, fi.solita.utils.functional.Iterables.TransformingIterable<S, T>> $() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F2<java.lang.Iterable<S>, fi.solita.utils.functional.Apply<? super S,? extends T>, fi.solita.utils.functional.Iterables.TransformingIterable<S, T>>() {
            public fi.solita.utils.functional.Iterables.TransformingIterable<S, T> apply(java.lang.Iterable<S> iterable, fi.solita.utils.functional.Apply<? super S,? extends T> transformer) {
                return new fi.solita.utils.functional.Iterables.TransformingIterable<S, T>(iterable, transformer);
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.TransformingIterable<S, T>> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.TransformingIterable<S, T>> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.TransformingIterable<S, T>>)(Object)fi.solita.utils.functional.Iterables.TransformingIterable.class.getDeclaredConstructor(java.lang.Iterable.class, fi.solita.utils.functional.Apply.class);
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.TransformingIterable<S, T>> getMember() {
                return $getMember();
            }
        
        };
        }
        
        public static final <S, T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.TransformingIterable<S, T>, java.util.Iterator<T>> iterator() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.TransformingIterable<S, T>, java.util.Iterator<T>>() {
            public java.util.Iterator<T> apply(fi.solita.utils.functional.Iterables.TransformingIterable<S, T> $self) {
                return $self.iterator();
            }
        };
        }
        
        public static final <S, T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.TransformingIterable<?, ?>, fi.solita.utils.functional.Option<java.lang.Integer>> size() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.TransformingIterable<?, ?>, fi.solita.utils.functional.Option<java.lang.Integer>>() {
            public fi.solita.utils.functional.Option<java.lang.Integer> apply(fi.solita.utils.functional.Iterables.TransformingIterable<?, ?> $self) {
                return $self.size();
            }
        };
        }
        
        }
         static final class ReversingIterable_ implements java.io.Serializable {
        public enum Fields {
            iterable,
        }
        
         static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.ReversingIterable<T>, java.lang.Iterable<T>> iterable() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.ReversingIterable<T>, java.lang.Iterable<T>>() {
            private transient java.lang.reflect.Field $r;
            
            
            private java.lang.reflect.Field $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.Iterables.ReversingIterable.class.getDeclaredField("iterable");
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
                return "iterable";
            }
        
        
            @SuppressWarnings("unchecked")
            protected java.lang.Iterable<T> $do(fi.solita.utils.functional.Iterables.ReversingIterable<T> $self) {
                try {
                    return (java.lang.Iterable<T>)$getMember().get($self);
                } catch (RuntimeException $e) {
                    throw $e;
                } catch (Error $e) {
                    throw $e;
                } catch (Throwable $e) {
                    throw new RuntimeException($e);
                }
            }
        };
        }
        
        public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F1<java.lang.Iterable<T>, fi.solita.utils.functional.Iterables.ReversingIterable<T>> $() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F1<java.lang.Iterable<T>, fi.solita.utils.functional.Iterables.ReversingIterable<T>>() {
            public fi.solita.utils.functional.Iterables.ReversingIterable<T> apply(java.lang.Iterable<T> iterable) {
                return new fi.solita.utils.functional.Iterables.ReversingIterable<T>(iterable);
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.ReversingIterable<T>> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.ReversingIterable<T>> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.ReversingIterable<T>>)(Object)fi.solita.utils.functional.Iterables.ReversingIterable.class.getDeclaredConstructor(java.lang.Iterable.class);
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.ReversingIterable<T>> getMember() {
                return $getMember();
            }
        
        };
        }
        
        public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.ReversingIterable<T>, java.util.Iterator<T>> iterator() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.ReversingIterable<T>, java.util.Iterator<T>>() {
            public java.util.Iterator<T> apply(fi.solita.utils.functional.Iterables.ReversingIterable<T> $self) {
                return $self.iterator();
            }
        };
        }
        
        public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.ReversingIterable<?>, java.lang.String> toString() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.ReversingIterable<?>, java.lang.String>() {
            public java.lang.String apply(fi.solita.utils.functional.Iterables.ReversingIterable<?> $self) {
                return $self.toString();
            }
        };
        }
        
        public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.ReversingIterable<?>, fi.solita.utils.functional.Option<java.lang.Integer>> size() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.ReversingIterable<?>, fi.solita.utils.functional.Option<java.lang.Integer>>() {
            public fi.solita.utils.functional.Option<java.lang.Integer> apply(fi.solita.utils.functional.Iterables.ReversingIterable<?> $self) {
                return $self.size();
            }
        };
        }
        
        }
         static final class CharSequenceIterable_ implements java.io.Serializable {
        public enum Fields {
            charSeq,
        }
        
         static final fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.CharSequenceIterable, java.lang.CharSequence> charSeq = new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.CharSequenceIterable, java.lang.CharSequence>() {
            private transient java.lang.reflect.Field $r;
            
            
            private java.lang.reflect.Field $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.Iterables.CharSequenceIterable.class.getDeclaredField("charSeq");
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
                return "charSeq";
            }
        
        
            protected java.lang.CharSequence $do(fi.solita.utils.functional.Iterables.CharSequenceIterable $self) {
                try {
                    return (java.lang.CharSequence)$getMember().get($self);
                } catch (RuntimeException $e) {
                    throw $e;
                } catch (Error $e) {
                    throw $e;
                } catch (Throwable $e) {
                    throw new RuntimeException($e);
                }
            }
        };
        
        public static final  fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F1<java.lang.CharSequence, fi.solita.utils.functional.Iterables.CharSequenceIterable> $ = new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F1<java.lang.CharSequence, fi.solita.utils.functional.Iterables.CharSequenceIterable>() {
            public fi.solita.utils.functional.Iterables.CharSequenceIterable apply(java.lang.CharSequence charSeq) {
                return new fi.solita.utils.functional.Iterables.CharSequenceIterable(charSeq);
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.CharSequenceIterable> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.CharSequenceIterable> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.CharSequenceIterable>)(Object)fi.solita.utils.functional.Iterables.CharSequenceIterable.class.getDeclaredConstructor(java.lang.CharSequence.class);
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.CharSequenceIterable> getMember() {
                return $getMember();
            }
        
        };
        
        public static final  fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.CharSequenceIterable, java.util.Iterator<java.lang.Character>> iterator = new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.CharSequenceIterable, java.util.Iterator<java.lang.Character>>() {
            public java.util.Iterator<java.lang.Character> apply(fi.solita.utils.functional.Iterables.CharSequenceIterable $self) {
                return $self.iterator();
            }
        };
        
        public static final  fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.CharSequenceIterable, fi.solita.utils.functional.Option<java.lang.Integer>> size = new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.CharSequenceIterable, fi.solita.utils.functional.Option<java.lang.Integer>>() {
            public fi.solita.utils.functional.Option<java.lang.Integer> apply(fi.solita.utils.functional.Iterables.CharSequenceIterable $self) {
                return $self.size();
            }
        };
        
        }
         static final class SortingIterable_ implements java.io.Serializable {
        public enum Fields {
            comparator,
            iterable,
        }
        
         static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.SortingIterable<T>, java.util.Comparator<? super T>> comparator() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.SortingIterable<T>, java.util.Comparator<? super T>>() {
            private transient java.lang.reflect.Field $r;
            
            
            private java.lang.reflect.Field $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.Iterables.SortingIterable.class.getDeclaredField("comparator");
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
                return "comparator";
            }
        
        
            @SuppressWarnings("unchecked")
            protected java.util.Comparator<? super T> $do(fi.solita.utils.functional.Iterables.SortingIterable<T> $self) {
                try {
                    return (java.util.Comparator<? super T>)$getMember().get($self);
                } catch (RuntimeException $e) {
                    throw $e;
                } catch (Error $e) {
                    throw $e;
                } catch (Throwable $e) {
                    throw new RuntimeException($e);
                }
            }
        };
        }
        
         static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.SortingIterable<T>, java.lang.Iterable<T>> iterable() { return new fi.solita.utils.codegen.DefaultMeta.DefaultMeta_<fi.solita.utils.functional.Iterables.SortingIterable<T>, java.lang.Iterable<T>>() {
            private transient java.lang.reflect.Field $r;
            
            
            private java.lang.reflect.Field $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Field)(Object)fi.solita.utils.functional.Iterables.SortingIterable.class.getDeclaredField("iterable");
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
                return "iterable";
            }
        
        
            @SuppressWarnings("unchecked")
            protected java.lang.Iterable<T> $do(fi.solita.utils.functional.Iterables.SortingIterable<T> $self) {
                try {
                    return (java.lang.Iterable<T>)$getMember().get($self);
                } catch (RuntimeException $e) {
                    throw $e;
                } catch (Error $e) {
                    throw $e;
                } catch (Throwable $e) {
                    throw new RuntimeException($e);
                }
            }
        };
        }
        
        public static final <T> fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F2<java.lang.Iterable<T>, java.util.Comparator<? super T>, fi.solita.utils.functional.Iterables.SortingIterable<T>> $() { return new fi.solita.utils.codegen.DefaultMeta.DefaultConstructorMeta_.F2<java.lang.Iterable<T>, java.util.Comparator<? super T>, fi.solita.utils.functional.Iterables.SortingIterable<T>>() {
            public fi.solita.utils.functional.Iterables.SortingIterable<T> apply(java.lang.Iterable<T> iterable, java.util.Comparator<? super T> comparator) {
                return new fi.solita.utils.functional.Iterables.SortingIterable<T>(iterable, comparator);
            }
        
            private transient java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.SortingIterable<T>> $r;
            
            @SuppressWarnings("unchecked")
            private java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.SortingIterable<T>> $getMember() {
                if ($r == null) {
                    try {
                        $r = (java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.SortingIterable<T>>)(Object)fi.solita.utils.functional.Iterables.SortingIterable.class.getDeclaredConstructor(java.lang.Iterable.class, java.util.Comparator.class);
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
        
            public java.lang.reflect.Constructor<fi.solita.utils.functional.Iterables.SortingIterable<T>> getMember() {
                return $getMember();
            }
        
        };
        }
        
        public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.SortingIterable<T>, java.util.Iterator<T>> iterator() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.SortingIterable<T>, java.util.Iterator<T>>() {
            public java.util.Iterator<T> apply(fi.solita.utils.functional.Iterables.SortingIterable<T> $self) {
                return $self.iterator();
            }
        };
        }
        
        public static final <T> fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.SortingIterable<?>, fi.solita.utils.functional.Option<java.lang.Integer>> size() { return new fi.solita.utils.functional.Function1<fi.solita.utils.functional.Iterables.SortingIterable<?>, fi.solita.utils.functional.Option<java.lang.Integer>>() {
            public fi.solita.utils.functional.Option<java.lang.Integer> apply(fi.solita.utils.functional.Iterables.SortingIterable<?> $self) {
                return $self.size();
            }
        };
        }
        
        }

}
