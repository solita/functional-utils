package fi.solita.utils.functional;

public abstract class Predicate<T> extends Function1<T, Boolean> {
    
    public static final <T> Predicate<T> of(final Apply<T,Boolean> apply) {
        return new Predicate<T>() {
            @Override
            public boolean accept(T t) {
                return apply.apply(t);
            }
        };
    }
    
    @Override
    public final Boolean apply(T t) {
        return accept(t);
    }
    
    public abstract boolean accept(T candidate);
    
    public final Predicate<T> and(final Apply<? super T, Boolean> other) {
        final Predicate<T> self = this;
        return new Predicate<T>() {
            @Override
            public boolean accept(T candidate) {
                return self.accept(candidate) && other.apply(candidate); 
            }
        };
    }
    
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
