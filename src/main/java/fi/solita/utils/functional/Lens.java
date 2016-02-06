package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.reduce;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public final class Lens<T,F> {
    private final Apply<? super T, F> getter;
    private final Function2<T, Apply<F,F>, T> setter;
    private final Builder<T> builder;
    
    private static final <A,B> Lens<A,B> each(final Lens<A,? extends Iterable<B>> lens, final Monoid<B> g, final Apply<Iterable<B>,? extends Iterable<B>> h) {
        return new Lens<A,B>(new Apply<A,B>() {
            public B apply(A a) {
                return reduce(g, lens.get(a));
            }
        }, new Function2<A,Apply<B,B>,A>() {
            @SuppressWarnings("unchecked")
            public A apply(A a, Apply<B,B> f) {
                Iterable<B> bs = lens.getter.apply(a);
                return lens.builder.init(a)
                              .with((Apply<A,Iterable<B>>)lens.getter, h.apply(map(f, bs)))
                              .build();
            }
        },
        lens.builder);
    }
    
    public static final <A,B> Lens<A,B> each(final Lens<A,Iterable<B>> lens, final Monoid<B> g) {
        return each(lens, g, new Apply<Iterable<B>, Iterable<B>>() {
            public Iterable<B> apply(Iterable<B> t) {
                return newList(t);
            }
        });
    }
    
    public static final <A,B> Lens<A,B> eachCollection(final Lens<A,Collection<B>> lens, final Monoid<B> g) {
        return each(lens, g, new Apply<Iterable<B>, Collection<B>>() {
            public Collection<B> apply(Iterable<B> t) {
                return newList(t);
            }
        });
    }
    
    public static final <A,B> Lens<A,B> eachList(final Lens<A,List<B>> lens, final Monoid<B> g) {
        return each(lens, g, new Apply<Iterable<B>, List<B>>() {
            public List<B> apply(Iterable<B> t) {
                return newList(t);
            }
        });
    }
    
    public static final <A,B> Lens<A,B> eachSet(final Lens<A,Set<B>> lens, final Monoid<B> g) {
        return each(lens, g, new Apply<Iterable<B>, Set<B>>() {
            public Set<B> apply(Iterable<B> t) {
                return newSet(t);
            }
        });
    }
    
    public static final <A,B> Lens<A,B> option(final Lens<A,Option<B>> lens, final Function0<B> orElse) {
        return new Lens<A,B>(new Apply<A,B>() {
            public B apply(A a) {
                return lens.get(a).cata(Function.<B>id(), orElse);
            }
        }, new Function2<A,Apply<B,B>,A>() {
            public A apply(A a, Apply<B,B> f) {
                Option<B> bs = lens.getter.apply(a);
                return lens.builder.init(a)
                              .with(lens.getter, bs.map(f))
                              .build();
            }
        },
        lens.builder);
    }

    private Lens(Apply<? super T, F> getter, Function2<T, Apply<F,F>, T> setter, Builder<T> builder) {
        this.getter = getter;
        this.setter = setter;
        this.builder = builder;
    }

    public static final <T,F> Lens<T,F> of(Apply<? super T, F> getter, Builder<T> builder) {
        return new Lens<T,F>(getter, setter(getter, builder), builder);
    }

    public final F get(T t) {
        return getter.apply(t);
    }

    public final T set(T t, F newValue) {
        return setter.apply(t, Function.<F,F>constant(newValue));
    }
    
    public final T over(T t, Apply<F,F> f) {
        return setter.apply(t, f);
    }
    
    public final <O> Lens<T,O> andThen(final Lens<F,O> otherLens) {
        return new Lens<T,O>(new Apply<T,O>() {
            public O apply(T t) {
                return otherLens.get(getter.apply(t));
            }
        }, new Function2<T,Apply<O,O>,T>() {
            public T apply(T tt, Apply<O,O> f) {
                F otherObject = otherLens.builder.init(getter.apply(tt)).build();
                return builder.init(tt)
                              .with(getter, otherLens.over(otherObject, f))
                              .build();
            }
        },
        builder);
    }

    private static final <T,F> Function2<T,Apply<F,F>,T> setter(final Apply<? super T,F> field, final Builder<T> builder) {
        return new Function2<T,Apply<F,F>, T>() {
            @Override
            public T apply(T t1, Apply<F, F> t2) {
                return builder.init(t1)
                        .with(field, t2.apply(field.apply(t1)))
                        .build();
            }
        };
    }
}