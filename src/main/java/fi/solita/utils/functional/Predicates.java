package fi.solita.utils.functional;

public abstract class Predicates {

    public static final <T extends Comparable<T>> java.util.function.Predicate<T> greaterThan(final T value) {
        return x -> x.compareTo(value) > 0;
    }
    
    public static final <T extends Comparable<T>> java.util.function.Predicate<T> lessThan(final T value) {
        return x -> x.compareTo(value) < 0;
    }
    
    public static final <T extends Comparable<T>> java.util.function.Predicate<T> greaterThanOrEqualTo(final T value) {
        return x -> x.compareTo(value) >= 0;
    }
    
    public static final <T extends Comparable<T>> java.util.function.Predicate<T> lessThanOrEqualTo(final T value) {
        return x -> x.compareTo(value) <= 0;
    }
    
    public static final <T extends Comparable<T>> java.util.function.Predicate<T> between(final T beginInclusive, final T endInclusive) {
        java.util.function.Predicate<T> pred = greaterThanOrEqualTo(beginInclusive).and(lessThanOrEqualTo(endInclusive));
        return x -> pred.test(x);
    }
}
