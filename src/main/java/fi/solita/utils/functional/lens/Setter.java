package fi.solita.utils.functional.lens;

import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Apply2;
import fi.solita.utils.functional.ApplyBi;
import fi.solita.utils.functional.Function;
import fi.solita.utils.functional.Function2;

/**
 * A setter of a specific member.
 */
public class Setter<T,F> implements ApplyBi<T, Apply<F,F>, T> {
    private final Apply2<T,Apply<F,F>,T> f2;
    
    public Setter(Apply2<T, Apply<F, F>, T> f2) {
        this.f2 = f2;
    }

    /**
     * @return a copy of {@code object} with the member of this setter set to {@code newValue}.
     */
    public final T set(T object, F newValue) {
        return object == null ? null : f2.apply(object, Function.<F,F>constant(newValue));
    }
    
    /**
     * @return a copy of {@code object} with the member of this setter modified by {@code f}.
     */
    public final T modify(T object, Apply<F,F> f) {
        return object == null ? null : f2.apply(object, f);
    }
    
    /**
     * @return composition of this and {@code otherSetter}.
     */
    public final <O> Setter<T,O> andThen(final Setter<F,O> otherSetter) {
        final Setter<T,F> self = this;
        return new Setter<T,O>(new Function2<T,Apply<O,O>,T>() {
            public T apply(T tt, final Apply<O,O> fo) {
                return self.modify(tt, new Apply<F,F>() {
                    public F apply(F f) {
                        return otherSetter.modify(f, fo);
                    }
                });
            }
            
            @Override
            public String toString() {
                return f2.toString() + "->" + otherSetter.toString();
            }
        });
    }
    
    @Override
    public String toString() {
        return f2.toString();
    }

    @Override
    public T apply(T t1, Apply<F, F> t2) {
        return f2.apply(t1, t2);
    }
}