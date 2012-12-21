package fi.solita.utils.functional;

public abstract class Monoid<T> extends SemiGroup<T> {
	public abstract T zero();
    
	public static final Monoid<Integer> IntSum = new Monoid<Integer>() {
	    @Override
        public Integer apply(Integer first, Integer second) {
            return SemiGroup.IntSum.apply(first, second);
        }

	    @Override
        public Integer zero() {
            return 0;
        }
	};

	public static final Monoid<Integer> IntProduct = new Monoid<Integer>() {
        @Override
        public Integer apply(Integer first, Integer second) {
            return SemiGroup.IntProduct.apply(first, second);
        }

        @Override
        public Integer zero() {
            return 1;
        }
    };

    public static final Monoid<Long> LongSum = new Monoid<Long>() {
        @Override
        public Long apply(Long first, Long second) {
            return SemiGroup.LongSum.apply(first, second);
        }

        @Override
        public Long zero() {
            return 0l;
        }
    };

    public static final Monoid<Long> LongProduct = new Monoid<Long>() {
        @Override
        public Long apply(Long first, Long second) {
            return SemiGroup.LongProduct.apply(first, second);
        }

        @Override
        public Long zero() {
            return 1l;
        }
    };

	public static final Monoid<String> StringConcat = new Monoid<String>() {
        @Override
        public String apply(String first, String second) {
            return SemiGroup.StringConcat.apply(first, second);
        }

        @Override
        public String zero() {
            return "";
        }
	};

	public static final Monoid<Boolean> BooleanConjunction = new Monoid<Boolean>() {
        @Override
        public Boolean apply(Boolean first, Boolean second) {
            return SemiGroup.BooleanConjunction.apply(first, second);
        }

        @Override
        public Boolean zero() {
            return true;
        }
    };

    public static final Monoid<Boolean> BooleanDisjunction = new Monoid<Boolean>() {
        @Override
        public Boolean apply(Boolean first, Boolean second) {
            return SemiGroup.BooleanDisjunction.apply(first, second);
        }

        @Override
        public Boolean zero() {
            return false;
        }
    };
}
