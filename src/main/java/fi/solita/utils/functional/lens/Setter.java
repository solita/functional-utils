package fi.solita.utils.functional.lens;

import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Function;
import fi.solita.utils.functional.Function2;

public class Setter<T,F> {
    private final Function2<T,Apply<F,F>,T> f2;
    
    public Setter(Function2<T, Apply<F, F>, T> f2) {
        this.f2 = f2;
    }

    public final T set(T t, F newValue) {
        return f2.apply(t, Function.<F,F>constant(newValue));
    }
    
    public final T modify(T t, Apply<F,F> f) {
        return f2.apply(t, f);
    }
    
    public final <O> Setter<T,O> andThen(final Setter<F,O> otherLens) {
        final Setter<T,F> self = this;
        return new Setter<T,O>(new Function2<T,Apply<O,O>,T>() {
            public T apply(T tt, final Apply<O,O> fo) {
                return self.modify(tt, new Apply<F,F>() {
                    public F apply(F f) {
                        return otherLens.modify(f, fo);
                    }
                });
            }
        });
    }
}