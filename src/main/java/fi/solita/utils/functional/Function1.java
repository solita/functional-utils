package fi.solita.utils.functional;

import java.io.Serializable;

public abstract class Function1<T, R> implements Apply<T,R>, Serializable {
    
    public abstract R apply(T t);

    public <U> Function1<T, U> andThen(final Apply<? super R, ? extends U> next) {
        return new Function1<T, U>() {
            @Override
            public final U apply(T source) {
                return next.apply(Function1.this.apply(source));
            }
        };
    }
    
    public final <U> Function1<U, R> compose(final Apply<? super U, ? extends T> next) {
        return new Function1<U, R>() {
            @Override
            public final R apply(U source) {
                return Function1.this.apply(next.apply(source));
            }
        };
    }

    public final Function1<Tuple1<T>, R> tuppled() {
        return new Function1<Tuple1<T>, R>() {
            @Override
            public final R apply(Tuple1<T> source) {
                return Function1.this.apply(source._1);
            }
        };
    }
    
    public final Function0<R> ap(final T t) {
        return new Function0<R>() {
            @Override
            public final R apply() {
                return Function1.this.apply(t);
            }
        };
    }
    
    static final <T1,R> Function1<T1,R> partial(final Apply<? extends Tuple,R> f, final Object... paramsAndPlaceholders) {
        return new Function1<T1,R>() {
            @SuppressWarnings("unchecked")
            @Override
            public final R apply(T1 t1) {
                return PartialApplicationHelper.doApply((Apply<Tuple,R>)f, paramsAndPlaceholders, t1);
            }
        };
    }
    
    static final <T1,R,FR extends Apply<?,R>> Function1<T1,FR> split(final Apply<? extends Tuple,R> f, final Object... placeholders) {
        return new Function1<T1,FR>() {
            @Override
            public final FR apply(T1 t1) {
                return PartialApplicationHelper.makeSecondFunc(f, placeholders, t1);
            }
        };
    }
    
    public static abstract class Ex1<T,R,E1 extends Throwable> implements ApplyEx.Ex1<T, R, E1>, Serializable {
        public abstract R apply(T t) throws E1;
        
        public final Ex1<Tuple1<T>, R, E1> tuppled() {
            return new Ex1<Tuple1<T>, R, E1>() {
                @Override
                public final R apply(Tuple1<T> source) throws E1 {
                    return Ex1.this.apply(source._1);
                }
            };
        }
        
        public final Function0.Ex1<R, E1> ap(final T t) {
            return new Function0.Ex1<R, E1>() {
                @Override
                public final R apply() throws E1 {
                    return Ex1.this.apply(t);
                }
            };
        }
        
        public final <U> Ex1<T,U,E1> andThen(final Apply<? super R, ? extends U> next) {
            return new Ex1<T,U,E1>() {
                @Override
                public final U apply(T t) throws E1 {
                    return next.apply(Ex1.this.apply(t));
                }
            };
        }
        
        public final <U,EE1 extends Throwable> Ex2<T,U,E1,EE1> andThen(final ApplyEx.Ex1<? super R, ? extends U, ? extends EE1> next) {
            return new Ex2<T,U,E1,EE1>() {
                @Override
                public final U apply(T t) throws E1, EE1 {
                    return next.apply(Ex1.this.apply(t));
                }
            };
        }
        
        public final <U,EE1 extends Throwable,EE2 extends Throwable> Ex3<T,U,E1,EE1,EE2> andThen(final ApplyEx.Ex2<? super R, ? extends U, ? extends EE1, ? extends EE2> next) {
            return new Ex3<T,U,E1,EE1,EE2>() {
                @Override
                public final U apply(T t) throws E1, EE1, EE2 {
                    return next.apply(Ex1.this.apply(t));
                }
            };
        }
        
        public final <U,EE1 extends Throwable,EE2 extends Throwable, EE3 extends Throwable> Ex4<T,U,E1,EE1,EE2,EE3> andThen(final ApplyEx.Ex3<? super R, ? extends U, ? extends EE1, ? extends EE2, ? extends EE3> next) {
            return new Ex4<T,U,E1,EE1,EE2,EE3>() {
                @Override
                public final U apply(T t) throws E1, EE1, EE2, EE3 {
                    return next.apply(Ex1.this.apply(t));
                }
            };
        }
    }
    
    public static abstract class Ex2<T, R, E1 extends Throwable, E2 extends Throwable> implements ApplyEx.Ex2<T, R, E1, E2>, Serializable {
        public abstract R apply(T t) throws E1, E2;
        
        public final Ex2<Tuple1<T>, R, E1, E2> tuppled() {
            return new Ex2<Tuple1<T>, R, E1, E2>() {
                @Override
                public final R apply(Tuple1<T> source) throws E1, E2 {
                    return Ex2.this.apply(source._1);
                }
            };
        }
        
        public final Function0.Ex2<R, E1, E2> ap(final T t) {
            return new Function0.Ex2<R, E1, E2>() {
                @Override
                public final R apply() throws E1, E2 {
                    return Ex2.this.apply(t);
                }
            };
        }
        
        public final <U> Ex2<T,U,E1,E2> andThen(final Apply<? super R, ? extends U> next) {
            return new Ex2<T,U,E1,E2>() {
                @Override
                public final U apply(T t) throws E1, E2 {
                    return next.apply(Ex2.this.apply(t));
                }
            };
        }
        
        public final <U,EE1 extends Throwable> Ex3<T,U,E1,E2,EE1> andThen(final ApplyEx.Ex1<? super R, ? extends U, ? extends EE1> next) {
            return new Ex3<T,U,E1,E2,EE1>() {
                @Override
                public final U apply(T t) throws E1, E2, EE1 {
                    return next.apply(Ex2.this.apply(t));
                }
            };
        }
        
        public final <U,EE1 extends Throwable,EE2 extends Throwable> Ex4<T,U,E1,E2,EE1,EE2> andThen(final ApplyEx.Ex2<? super R, ? extends U, ? extends EE1, ? extends EE2> next) {
            return new Ex4<T,U,E1,E2,EE1,EE2>() {
                @Override
                public final U apply(T t) throws E1, E2, EE1, EE2 {
                    return next.apply(Ex2.this.apply(t));
                }
            };
        }
    }
    
    public static abstract class Ex3<T, R, E1 extends Throwable, E2 extends Throwable, E3 extends Throwable> implements ApplyEx.Ex3<T, R, E1, E2, E3>, Serializable {
        public abstract R apply(T t) throws E1, E2, E3;
        
        public final Ex3<Tuple1<T>, R, E1, E2, E3> tuppled() {
            return new Ex3<Tuple1<T>, R, E1, E2, E3>() {
                @Override
                public final R apply(Tuple1<T> source) throws E1, E2, E3 {
                    return Ex3.this.apply(source._1);
                }
            };
        }
        
        public final Function0.Ex3<R, E1, E2, E3> ap(final T t) {
            return new Function0.Ex3<R, E1, E2, E3>() {
                @Override
                public final R apply() throws E1, E2, E3 {
                    return Ex3.this.apply(t);
                }
            };
        }
        
        public final <U> Ex3<T,U,E1,E2,E3> andThen(final Apply<? super R, ? extends U> next) {
            return new Ex3<T,U,E1,E2,E3>() {
                @Override
                public final U apply(T t) throws E1, E2, E3 {
                    return next.apply(Ex3.this.apply(t));
                }
            };
        }
        
        public final <U,EE1 extends Throwable> Ex4<T,U,E1,E2,E3,EE1> andThen(final ApplyEx.Ex1<? super R, ? extends U, ? extends EE1> next) {
            return new Ex4<T,U,E1,E2,E3,EE1>() {
                @Override
                public final U apply(T t) throws E1, E2, E3, EE1 {
                    return next.apply(Ex3.this.apply(t));
                }
            };
        }
    }
    
    public static abstract class Ex4<T, R, E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable> implements ApplyEx.Ex4<T, R, E1, E2, E3, E4>, Serializable {
        public abstract R apply(T t) throws E1, E2, E3, E4;
        
        public final Ex4<Tuple1<T>, R, E1, E2, E3, E4> tuppled() {
            return new Ex4<Tuple1<T>, R, E1, E2, E3, E4>() {
                @Override
                public final R apply(Tuple1<T> source) throws E1, E2, E3, E4 {
                    return Ex4.this.apply(source._1);
                }
            };
        }
        
        public final Function0.Ex4<R, E1, E2, E3, E4> ap(final T t) {
            return new Function0.Ex4<R, E1, E2, E3, E4>() {
                @Override
                public final R apply() throws E1, E2, E3, E4 {
                    return Ex4.this.apply(t);
                }
            };
        }
        
        public final <U> Ex4<T,U,E1,E2,E3,E4> andThen(final Apply<? super R, ? extends U> next) {
            return new Ex4<T,U,E1,E2,E3,E4>() {
                @Override
                public final U apply(T t) throws E1, E2, E3, E4 {
                    return next.apply(Ex4.this.apply(t));
                }
            };
        }
    }
}
