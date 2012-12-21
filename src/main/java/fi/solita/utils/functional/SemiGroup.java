package fi.solita.utils.functional;


public abstract class SemiGroup<T> extends Function2<T, T, T> {
    public abstract T apply(T first, T second);

    public static final SemiGroup<Integer> IntSum = new SemiGroup<Integer>() {
        @Override
        public Integer apply(Integer first, Integer second) {
            return first + second;
        }
    };

    public static final SemiGroup<Integer> IntProduct = new SemiGroup<Integer>() {
        @Override
        public Integer apply(Integer first, Integer second) {
            return first * second;
        }
    };

    public static final SemiGroup<Long> LongSum = new SemiGroup<Long>() {
        @Override
        public Long apply(Long first, Long second) {
            return first + second;
        }
    };

    public static final SemiGroup<Long> LongProduct = new SemiGroup<Long>() {
        @Override
        public Long apply(Long first, Long second) {
            return first * second;
        }
    };

    public static final SemiGroup<String> StringConcat = new SemiGroup<String>() {
        @Override
        public String apply(String first, String second) {
            return first + second;
        }
    };

    public static final SemiGroup<Boolean> BooleanConjunction = new SemiGroup<Boolean>() {
        @Override
        public Boolean apply(Boolean first, Boolean second) {
            return first && second;
        }
    };

    public static final SemiGroup<Boolean> BooleanDisjunction = new SemiGroup<Boolean>() {
        @Override
        public Boolean apply(Boolean first, Boolean second) {
            return first || second;
        }
    };

}
