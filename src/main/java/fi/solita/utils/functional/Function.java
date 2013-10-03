package fi.solita.utils.functional;

public abstract class Function {
    private Function() {
    }
    
    public static enum GivenLater {}
    public static final GivenLater _ = null;
    
    public static final <T,R> Function1<T,R> of(final Apply<T,R> apply) {
        return new Function1<T, R>() {
            @Override
            public R apply(T t) {
                return apply.apply(t);
            }
        };
    }
    
    private static final Function1<?,?> ID = new Function1<Object,Object>() {
        @Override
        public Object apply(Object t) {
            return t;
        }
    };

    @SuppressWarnings("unchecked")
    public static final <T> Function1<T, T> id() {
        return (Function1<T, T>) ID;
    }
    
    public static final <A,B,C> Function1<B,Function1<A,C>> flip(final Apply<A,? extends Apply<B,C>> f) {
        return new Function1<B,Function1<A,C>>() {
            @Override
            public Function1<A, C> apply(final B g) {
                return new Function1<A,C>() {
                    @Override
                    public C apply(A t) {
                        return f.apply(t).apply(g);
                    }
                };
            }
        };
    }
    
    public static final <A,B,C> Function2<A,B,C> uncurried(final Apply<A,? extends Apply<B,C>> f) {
    	  return new Function2<A, B, C>() {
				  @Override
				  public C apply(A t1, B t2) {
				      return f.apply(t1).apply(t2);
				  }
				};
    }
    
    public static final <A,B,C,D> Function3<A,B,C,D> uncurried2(final Apply<A,? extends Apply<B,? extends Apply<C,D>>> f) {
  	    return new Function3<A, B, C, D>() {
				    @Override
				    public D apply(A t1, B t2, C t3) {
				        return f.apply(t1).apply(t2).apply(t3);
				    }
				};
    }
    
    public static final <A,B,C,D,E> Function4<A,B,C,D,E> uncurried3(final Apply<A,? extends Apply<B,? extends Apply<C,? extends Apply<D,E>>>> f) {
    	  return new Function4<A, B, C, D, E>() {
			      @Override
			      public E apply(A t1, B t2, C t3, D t4) {
				        return f.apply(t1).apply(t2).apply(t3).apply(t4);
				    }
				};
    }
}
