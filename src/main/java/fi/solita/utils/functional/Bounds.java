package fi.solita.utils.functional;

public abstract class Bounds {
    private Bounds() {
    }
    
    public static final Bounded<Short> shortBounds = new Bounded<Short>() {
        public Short minBound() {
            return Short.MIN_VALUE;
        }
        
        public Short maxBound() {
            return Short.MAX_VALUE;
        }
    };
    
    public static final Bounded<Integer> intBounds = new Bounded<Integer>() {
        public Integer minBound() {
            return Integer.MIN_VALUE;
        }
        
        public Integer maxBound() {
            return Integer.MAX_VALUE;
        }
    };
    
    public static final Bounded<Long> longBounds = new Bounded<Long>() {
        public Long minBound() {
            return Long.MIN_VALUE;
        }
        
        public Long maxBound() {
            return Long.MAX_VALUE;
        }
    };
    
    public static final Bounded<Boolean> booleans = new Bounded<Boolean>() {
        public Boolean minBound() {
            return false;
        }
        
        public Boolean maxBound() {
            return true;
        }
    };
    
    public static final Bounded<Character> chars = new Bounded<Character>() {
        public Character minBound() {
            return Character.MIN_VALUE;
        }
        
        public Character maxBound() {
            return Character.MAX_VALUE;
        }
    };
    
    public static final Bounded<Character> asciiAlpha = new Bounded<Character>() {
        public Character minBound() {
            return 'a';
        }
        
        public Character maxBound() {
            return 'z';
        }
    };
}
