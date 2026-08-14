package fi.solita.utils.functional;

public abstract class Predicates {

    public static final <T extends Comparable<T>> Predicate<T> greaterThan(final T value) {
        return new Predicate<T>() {
            @Override
            public final boolean accept(T candidate) {
                return candidate.compareTo(value) > 0;
            }
        };
    }
    
    public static final <T extends Comparable<T>> Predicate<T> lessThan(final T value) {
        return new Predicate<T>() {
            @Override
            public final boolean accept(T candidate) {
                return candidate.compareTo(value) < 0;
            }
        };
    }
    
    public static final <T extends Comparable<T>> Predicate<T> greaterThanOrEqualTo(final T value) {
        return new Predicate<T>() {
            @Override
            public final boolean accept(T candidate) {
                return candidate.compareTo(value) >= 0;
            }
        };
    }
    
    public static final <T extends Comparable<T>> Predicate<T> lessThanOrEqualTo(final T value) {
        return new Predicate<T>() {
            @Override
            public final boolean accept(T candidate) {
                return candidate.compareTo(value) <= 0;
            }
        };
    }
    
    public static final <T extends Comparable<T>> Predicate<T> between(final T beginInclusive, final T endInclusive) {
        return new Predicate<T>() {
            Predicate<T> pred = greaterThanOrEqualTo(beginInclusive).and(lessThanOrEqualTo(endInclusive));
            
            @Override
            public final boolean accept(T candidate) {
                return pred.accept(candidate);
            }
        };
    }
}
