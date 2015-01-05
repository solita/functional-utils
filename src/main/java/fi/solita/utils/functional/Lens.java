package fi.solita.utils.functional;

public final class Lens<T,F> {
    private final Apply<T, F> getter;
    private final Apply<Tuple2<T, F>, T> setter;
    private final Builder<T> builder;

    private Lens(Apply<T, F> getter, Apply<Tuple2<T, F>, T> setter, Builder<T> builder) {
        this.getter = getter;
        this.setter = setter;
        this.builder = builder;
    }

    public static final <T,F> Lens<T,F> of(Apply<T, F> getter, Builder<T> builder) {
        return new Lens<T,F>(getter, setter(getter, builder), builder);
    }

    public final F get(T t) {
        return getter.apply(t);
    }

    public final T set(T t, F newValue) {
        return setter.apply(Tuple.of(t, newValue));
    }
    
    public final <O> Lens<T,O> andThen(final Lens<F,O> otherLens) {
        return new Lens<T,O>(new Apply<T,O>() {
            public O apply(T t) {
                return otherLens.get(getter.apply(t));
            }
        }, new Function2<T,O,T>() {
            public T apply(T tt, O otherValue) {
                F otherObject = otherLens.builder.init(getter.apply(tt)).build();
                return builder.init(tt)
                              .with(getter, otherLens.set(otherObject, otherValue))
                              .build();
            }
        },
        builder);
    }

    private static final <T,F> Apply<Tuple2<T,F>,T> setter(final Apply<T,F> field, final Builder<T> builder) {
        return new Apply<Tuple2<T,F>, T>() {
            public T apply(Tuple2<T, F> t) {
                return builder.init(t._1)
                              .with(field, t._2)
                              .build();
            }
        };
    }
}