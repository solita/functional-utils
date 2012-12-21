package fi.solita.utils.functional;

public abstract class Function0<R> {

    public abstract R apply();
    
    public <U> Function0<U> andThen(final Function1<R, U> next) {
        final Function0<R> self = this;
        return new Function0<U>() {
            @Override
            public U apply() {
                return next.apply(self.apply());
            }
        };
    }
}
