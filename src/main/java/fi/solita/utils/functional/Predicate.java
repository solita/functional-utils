package fi.solita.utils.functional;

/**
 * Specialization of {@code Function1<T, Boolean>}.
 */
public abstract class Predicate<T> extends Function1<T, Boolean> {
    
    /**
     * @return concrete {@code Predicate} for abstract function {@code apply}.
     */
    public static final <T> Predicate<T> of(final Apply<T,Boolean> apply) {
        return new Predicate<T>() {
            @Override
            public boolean accept(T t) {
                return apply.apply(t);
            }
        };
    }
    
    @Override
    public Boolean apply(T t) {
        return accept(t);
    }
    
    /**
     * @return whether this predicate accepts {@code candidate}.
     */
    public abstract boolean accept(T candidate);
    
    /**
     * @return conjunction of {@code this} and {@code other}.
     */
    public final Predicate<T> and(final Apply<? super T, Boolean> other) {
        final Predicate<T> self = this;
        return new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return self.accept(candidate) && other.apply(candidate); 
            }
        };
    }
    
    /**
     * @return disjunction of {@code this} and {@code other}.
     */
    public final Predicate<T> or(final Apply<? super T, Boolean> other) {
        final Predicate<T> self = this;
        return new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return self.accept(candidate) || other.apply(candidate); 
            }
        };
    }
}
