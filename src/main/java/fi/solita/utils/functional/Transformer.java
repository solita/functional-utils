package fi.solita.utils.functional;

/**
 * Specialization of {@code Function1<T1,T2>}.
 */
public abstract class Transformer<SOURCE, TARGET> extends Function1<SOURCE, TARGET>{
    @Override
    public final TARGET apply(SOURCE t) {
        return transform(t);
    }
    
    /**
     * @see Transformer#apply
     */
    public abstract TARGET transform(SOURCE source);
    
    /**
     * Overridden to specialize return type
     */
    @Override
    public final <U> Transformer<SOURCE, U> andThen(final Apply<? super TARGET, ? extends U> next) {
        final Transformer<SOURCE, TARGET> self = this;
        return new Transformer<SOURCE, U>() {
            @Override
            public U transform(SOURCE source) {
                return next.apply(self.transform(source));
            }
        };
    }
}
