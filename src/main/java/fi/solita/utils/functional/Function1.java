package fi.solita.utils.functional;

public abstract class Function1<T, R> {

    public abstract R apply(T t);
    
    public <U> Function1<T, U> andThen(final Function1<R, U> next) {
        final Function1<T, R> self = this;
        return new Function1<T, U>() {
            @Override
            public U apply(T source) {
                return next.apply(self.apply(source));
            }
        };
    }
    
    public Function1<Tuple1<T>, R> tuppled() {
        final Function1<T, R> self = this;
        return new Function1<Tuple1<T>, R>() {
            @Override
            public R apply(Tuple1<T> source) {
                return self.apply(source._1);
            }
        };
    }
}
