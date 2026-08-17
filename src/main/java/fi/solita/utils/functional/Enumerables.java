package fi.solita.utils.functional;

import java.util.Optional;

public abstract class Enumerables {
    private Enumerables() {
    }
    
    public static abstract class BoundedEnumerable<T> implements Enumerable<T>, Bounded<T> {
        private final T maxBound;
        private final T minBound;

        public BoundedEnumerable(Bounded<T> bounds) {
          this.maxBound = bounds.maxBound();
          this.minBound = bounds.minBound();
        }
    
        public Optional<T> succ(T t) {
                if (t == null || t.equals(maxBound)) return Optional.empty(); else return Optional.of(doSucc(t));
        }

        public Optional<T> pred(T t) {
              if (t == null || t.equals(minBound)) return Optional.empty(); else return Optional.of(doPred(t));
        }
        
        public T minBound() {
              return minBound;
        }
        
        public T maxBound() {
              return maxBound;
        }
      
        protected abstract T doSucc(T t);
        protected abstract T doPred(T t);
    }
    
    public static final BoundedEnumerable<Short> shorts = new BoundedEnumerable<Short>(Bounds.shortBounds) {
        @Override
        protected Short doSucc(Short t) {
              return (short)(t+1);
        }

        @Override
        protected Short doPred(Short t) {
            return (short)(t-1);
        }
    };
    
    public static final BoundedEnumerable<Integer> ints = new BoundedEnumerable<Integer>(Bounds.intBounds) {
        @Override
        protected Integer doSucc(Integer t) {
              return t+1;
        }

        @Override
        protected Integer doPred(Integer t) {
            return t-1;
        }
    };
  
    public static final BoundedEnumerable<Long> longs = new BoundedEnumerable<Long>(Bounds.longBounds) {
        @Override
        protected Long doSucc(Long t) {
            return t + 1;
        }

        @Override
        protected Long doPred(Long t) {
            return t - 1;
        }
    };
    
    public static final BoundedEnumerable<Character> asciiAlpha = new BoundedEnumerable<Character>(Bounds.asciiAlpha) {
        @Override
        protected Character doSucc(Character t) {
              return (char)((int)t + 1);
        }

        @Override
        protected Character doPred(Character t) {
              return (char)((int)t - 1);
        }
    };
      
    public static final BoundedEnumerable<Boolean> booleans = new BoundedEnumerable<Boolean>(Bounds.booleans) {
        @Override
        protected Boolean doSucc(Boolean t) {
              return true;
        }

        @Override
        protected Boolean doPred(Boolean t) {
              return false;
        }
    };
}
