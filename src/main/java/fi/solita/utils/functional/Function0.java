package fi.solita.utils.functional;

import java.io.Serializable;

import fi.solita.utils.codegen.NoMetadataGeneration;

@NoMetadataGeneration
public abstract class Function0<R> implements Apply<Tuple0,R>, Serializable {

    public abstract R apply();
    
    public final <U> Function0<U> andThen(final Apply<? super R, ? extends U> next) {
        return new Function0<U>() {
            @Override
            public U apply() {
                return next.apply(Function0.this.apply());
            }
        };
    }
    
    @Override
    public final R apply(Tuple0 t) {
        return apply();
    }
    
    public static abstract class Ex1<R,E1 extends Throwable> implements ApplyEx.Ex1<Tuple0, R, E1>, Serializable {
        public abstract R apply() throws E1;
        
        @Override
        public final R apply(Tuple0 t) throws E1 {
            return apply();
        }
        
        public final <U> Function0.Ex1<U,E1> andThen(final Apply<? super R, ? extends U> next) {
            return new Ex1<U,E1>() {
                @Override
                public U apply() throws E1 {
                    return next.apply(Ex1.this.apply());
                }
            };
        }
        
        public final <U,EE1 extends Throwable> Function0.Ex2<U,E1,EE1> andThen(final ApplyEx.Ex1<? super R, ? extends U, ? extends EE1> next) {
            return new Ex2<U,E1,EE1>() {
                @Override
                public U apply() throws E1, EE1 {
                    return next.apply(Ex1.this.apply());
                }
            };
        }
        
        public final <U,EE1 extends Throwable,EE2 extends Throwable> Function0.Ex3<U,E1,EE1,EE2> andThen(final ApplyEx.Ex2<? super R, ? extends U, ? extends EE1, ? extends EE2> next) {
            return new Ex3<U,E1,EE1,EE2>() {
                @Override
                public U apply() throws E1, EE1, EE2 {
                    return next.apply(Ex1.this.apply());
                }
            };
        }
        
        public final <U,EE1 extends Throwable,EE2 extends Throwable, EE3 extends Throwable> Function0.Ex4<U,E1,EE1,EE2,EE3> andThen(final ApplyEx.Ex3<? super R, ? extends U, ? extends EE1, ? extends EE2, ? extends EE3> next) {
            return new Ex4<U,E1,EE1,EE2,EE3>() {
                @Override
                public U apply() throws E1, EE1, EE2, EE3 {
                    return next.apply(Ex1.this.apply());
                }
            };
        }
    }
    
    public static abstract class Ex2<R,E1 extends Throwable, E2 extends Throwable> implements ApplyEx.Ex2<Tuple0, R, E1, E2>, Serializable {
        public abstract R apply() throws E1, E2;
        
        @Override
        public final R apply(Tuple0 t) throws E1, E2 {
            return apply();
        }
        
        public final <U> Function0.Ex2<U,E1,E2> andThen(final Apply<? super R, ? extends U> next) {
            return new Ex2<U,E1,E2>() {
                @Override
                public U apply() throws E1, E2 {
                    return next.apply(Ex2.this.apply());
                }
            };
        }
        
        public final <U,EE1 extends Throwable> Function0.Ex3<U,E1,E2,EE1> andThen(final ApplyEx.Ex1<? super R, ? extends U, ? extends EE1> next) {
            return new Ex3<U,E1,E2,EE1>() {
                @Override
                public U apply() throws E1, E2, EE1 {
                    return next.apply(Ex2.this.apply());
                }
            };
        }
        
        public final <U,EE1 extends Throwable,EE2 extends Throwable> Function0.Ex4<U,E1,E2,EE1,EE2> andThen(final ApplyEx.Ex2<? super R, ? extends U, ? extends EE1, ? extends EE2> next) {
            return new Ex4<U,E1,E2,EE1,EE2>() {
                @Override
                public U apply() throws E1, E2, EE1, EE2 {
                    return next.apply(Ex2.this.apply());
                }
            };
        }
    }
    
    public static abstract class Ex3<R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable> implements ApplyEx.Ex3<Tuple0, R, E1, E2, E3>, Serializable {
        public abstract R apply() throws E1, E2, E3;
        
        @Override
        public final R apply(Tuple0 t) throws E1, E2, E3 {
            return apply();
        }
        
        public final <U> Function0.Ex3<U,E1,E2,E3> andThen(final Apply<? super R, ? extends U> next) {
            return new Ex3<U,E1,E2,E3>() {
                @Override
                public U apply() throws E1, E2, E3 {
                    return next.apply(Ex3.this.apply());
                }
            };
        }
        
        public final <U,EE1 extends Throwable> Function0.Ex4<U,E1,E2,E3,EE1> andThen(final ApplyEx.Ex1<? super R, ? extends U, ? extends EE1> next) {
            return new Ex4<U,E1,E2,E3,EE1>() {
                @Override
                public U apply() throws E1, E2, E3, EE1 {
                    return next.apply(Ex3.this.apply());
                }
            };
        }
    }
    
    public static abstract class Ex4<R,E1 extends Throwable, E2 extends Throwable, E3 extends Throwable, E4 extends Throwable> implements ApplyEx.Ex4<Tuple0, R, E1, E2, E3, E4>, Serializable {
        public abstract R apply() throws E1, E2, E3, E4;
        
        @Override
        public final R apply(Tuple0 t) throws E1, E2, E3, E4 {
            return apply();
        }
        
        public final <U> Function0.Ex4<U,E1,E2,E3,E4> andThen(final Apply<? super R, ? extends U> next) {
            return new Ex4<U,E1,E2,E3,E4>() {
                @Override
                public U apply() throws E1, E2, E3, E4 {
                    return next.apply(Ex4.this.apply());
                }
            };
        }
    }
}
