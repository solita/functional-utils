package fi.solita.utils.functional.lens;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Collections.newSortedSet;
import static fi.solita.utils.functional.Functional.headOption;
import static fi.solita.utils.functional.Functional.map;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Apply2;
import fi.solita.utils.functional.ApplyBi;
import fi.solita.utils.functional.Function;
import fi.solita.utils.functional.Function2;
import fi.solita.utils.functional.Option;

/**
 * A setter of a specific member.
 */
public class Setter<T,F> implements ApplyBi<T, Apply<F,F>, T> {
    private final Apply2<T,Apply<F,F>,T> f2;
    
    public Setter(Apply2<T, Apply<F, F>, T> f2) {
        this.f2 = f2;
    }
    
    /**
     * @return a setter {@code setter}.
     */
    public static final <T,F> Setter<T,F> of(final ApplyBi<T, Apply<F,F>, T> setter) {
        return new Setter<T,F>(new Function2<T,Apply<F,F>,T>() {
            @Override
            public T apply(T t1, Apply<F, F> t2) {
                return setter.apply(t1, t2);
            }
        });
    }
    
    /**
     * @return a setter targetting each member of the set behind {@code setter}.
     */
    public static final <D,E,S> Setter<D,S> eachOption(final Setter<D,Option<E>> setter, final Setter<E,S> setter2) {
        return eachIterable(setter, setter2, new Apply<Iterable<E>, Option<E>>() {
            public Option<E> apply(Iterable<E> t) {
                return headOption(t);
            }
        });
    }
    
    /**
     * @return a setter targetting each member of the collection behind {@code setter}.
     */
    public static final <D,E,S> Setter<D,S> eachCollection(final Setter<D,Collection<E>> setter, final Setter<E,S> setter2) {
        return eachIterable(setter, setter2, new Apply<Iterable<E>, Collection<E>>() {
            public Collection<E> apply(Iterable<E> t) {
                return newList(t);
            }
        });
    }
    
    /**
     * @return a setter targetting each member of the list behind {@code setter}.
     */
    public static final <D,E,S> Setter<D,S> eachList(final Setter<D,List<E>> setter, final Setter<E,S> setter2) {
        return eachIterable(setter, setter2, new Apply<Iterable<E>, List<E>>() {
            public List<E> apply(Iterable<E> t) {
                return newList(t);
            }
        });
    }
    
    /**
     * @return a setter targetting each member of the set behind {@code setter}.
     */
    public static final <D,E,S> Setter<D,S> eachSet(final Setter<D,Set<E>> setter, final Setter<E,S> setter2) {
        return eachIterable(setter, setter2, new Apply<Iterable<E>, Set<E>>() {
            public Set<E> apply(Iterable<E> t) {
                return newSet(t);
            }
        });
    }
    
    /**
     * @return a setter targetting each member of the sortedset behind {@code setter}.
     */
    public static final <D,E,S> Setter<D,S> eachSortedSet(final Setter<D,SortedSet<E>> setter, final Setter<E,S> setter2) {
        return new Setter<D,S>(new Function2<D, Apply<S,S>, D>() {
            @Override
            public D apply(D d, final Apply<S, S> f) {
                return setter.modify(d, new Apply<SortedSet<E>,SortedSet<E>>() {
                    public SortedSet<E> apply(SortedSet<E> c) {
                        return c == null ? null : newSortedSet(c.comparator(), map(new Apply<E,E>() {
                            public E apply(E e) {
                                return setter2.modify(e, f);
                            }
                        }, c));
                    };
                });
            }
        });
    }
    
    /**
     * @return a setter targetting each member of the iterable behind {@code setter}.
     */
    public static final <D,E,S> Setter<D,S> eachIterable(final Setter<D,Iterable<E>> setter, final Setter<E,S> setter2) {
        return eachIterable(setter, setter2, Function.<Iterable<E>>id());
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
    
    private static final <D,E,S,C extends Iterable<E>> Setter<D,S> eachIterable(final Setter<D,C> lens, final Setter<E,S> lens2, final Apply<Iterable<E>,C> wrap) {
        return new Setter<D,S>(new Function2<D, Apply<S,S>, D>() {
            @Override
            public D apply(D d, final Apply<S, S> f) {
                return lens.modify(d, new Apply<C,C>() {
                    public C apply(C c) {
                        return wrap.apply(map(new Apply<E,E>() {
                            public E apply(E e) {
                                return lens2.modify(e, f);
                            }
                        }, c));
                    };
                });
            }
        });
    }
}