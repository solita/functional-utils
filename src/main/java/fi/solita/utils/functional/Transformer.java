package fi.solita.utils.functional;

public abstract class Transformer<SOURCE, TARGET> extends Function1<SOURCE, TARGET>{
    @Override
    public final TARGET apply(SOURCE t) {
        return transform(t);
    }
    
    public abstract TARGET transform(SOURCE source);
    
    /**
     * Overridden to specialize return type
     */
    @Override
    public final <U> Transformer<SOURCE, U> andThen(final Function1<TARGET,U> next) {
        final Transformer<SOURCE, TARGET> self = this;
        return new Transformer<SOURCE, U>() {
            @Override
            public U transform(SOURCE source) {
                return next.apply(self.transform(source));
            }
        };
    }
}
