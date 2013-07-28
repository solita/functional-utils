package fi.solita.utils.functional;

public abstract class Bounds {
    private Bounds() {
		}
    
    public static final Bounded<Short> shortBounds = new Bounded<Short>() {
				@Override
				public Short minBound() {
				    return Short.MIN_VALUE;
				}
				
				@Override
				public Short maxBound() {
				    return Short.MAX_VALUE;
				}
		};
		
		public static final Bounded<Integer> intBounds = new Bounded<Integer>() {
				@Override
				public Integer minBound() {
				    return Integer.MIN_VALUE;
				}
				
				@Override
				public Integer maxBound() {
				    return Integer.MAX_VALUE;
				}
		};
		
		public static final Bounded<Long> longBounds = new Bounded<Long>() {
				@Override
				public Long minBound() {
				    return Long.MIN_VALUE;
				}
				
				@Override
				public Long maxBound() {
				    return Long.MAX_VALUE;
				}
		};
		
		public static final Bounded<Boolean> booleans = new Bounded<Boolean>() {
				@Override
				public Boolean minBound() {
				    return false;
				}
				
				@Override
				public Boolean maxBound() {
				    return true;
				}
		};
		
		public static final Bounded<Character> chars = new Bounded<Character>() {
			@Override
			public Character minBound() {
			    return Character.MIN_VALUE;
			}
			
			@Override
			public Character maxBound() {
			    return Character.MAX_VALUE;
			}
	};
		
		public static final Bounded<Character> asciiAlpha = new Bounded<Character>() {
				@Override
				public Character minBound() {
				    return 'a';
				}
				
				@Override
				public Character maxBound() {
				    return 'z';
				}
		};
}
