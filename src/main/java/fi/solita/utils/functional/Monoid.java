package fi.solita.utils.functional;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

public interface Monoid<T> extends SemiGroup<T> {
    T zero();
    
    public static class BooleanDisjunction extends SemiGroup.BooleanDisjunction implements Monoid<Boolean> {
        @Override
        public Boolean zero() {
            return false;
        }
    }

    public static class BooleanConjunction extends SemiGroup.BooleanConjunction implements Monoid<Boolean> {
        @Override
        public Boolean zero() {
            return true;
        }
    }

    public static class StringConcat extends SemiGroup.StringConcat implements Monoid<String> {
        @Override
        public String zero() {
            return "";
        }
    }

    public static class LongProduct extends SemiGroup.LongProduct implements Monoid<Long> {
        @Override
        public Long zero() {
            return 1l;
        }
    }

    public static class LongSum extends SemiGroup.LongSum implements Monoid<Long> {
        @Override
        public Long zero() {
            return 0l;
        }
    }

    public static class IntProduct extends SemiGroup.IntProduct implements Monoid<Integer> {
        @Override
        public Integer zero() {
            return 1;
        }
    }

    public static class IntSum extends SemiGroup.IntSum implements Monoid<Integer> {
        @Override
        public Integer zero() {
            return 0;
        }
    }
    
    public static class SetUnion<T> extends SemiGroup.SetUnion<T> implements Monoid<Set<T>> {
        @Override
        public Set<T> zero() {
            return java.util.Collections.emptySet();
        }
    }
    
    public static class SetIntersection<T> extends SemiGroup.SetIntersection<T> implements Monoid<Set<T>> {
        private static final Set<?> AllContainingSet = new AllContainingSet<Object>();
        static final class AllContainingSet<T> implements Set<T> {
            @Override
            public int size() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return true;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return true;
            }

            @Override
            public Iterator<T> iterator() {
                throw new UnsupportedOperationException();
            }

            @Override
            public Object[] toArray() {
                throw new UnsupportedOperationException();
            }

            @Override
            public <S> S[] toArray(S[] a) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean add(T e) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean remove(Object o) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean addAll(Collection<? extends T> c) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                throw new UnsupportedOperationException();
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                throw new UnsupportedOperationException();
            }

            @Override
            public void clear() {
                throw new UnsupportedOperationException();
            }
        }

        @SuppressWarnings("unchecked")
        @Override
        public Set<T> zero() {
            return (Set<T>) AllContainingSet;
        }
    }
    
    public static class ComparatorConcat<T> extends SemiGroup.ComparatorConcat<T> implements Monoid<Comparator<T>> {
        @SuppressWarnings("unchecked")
        public <C extends Comparator<?>> Monoid<C> of() {
            return (Monoid<C>)(Object)this;
        }
        
        @Override
        public Comparator<T> zero() {
            return new Comparator<T>() {
                @Override
                public int compare(T o1, T o2) {
                    return 0;
                }
            };
        }
    }

    public static final Monoid<Integer> intSum = new IntSum();

    public static final Monoid<Integer> intProduct = new IntProduct();

    public static final Monoid<Long> longSum = new LongSum();

    public static final Monoid<Long> longProduct = new LongProduct();

    public static final Monoid<String> stringConcat = new StringConcat();

    public static final Monoid<Boolean> booleanConjunction = new BooleanConjunction();

    public static final Monoid<Boolean> booleanDisjunction = new BooleanDisjunction();
    
    public static final Monoid<Set<Object>> setUnion = new SetUnion<Object>();
    
    public static final Monoid<Set<Object>> setIntersection = new SetIntersection<Object>();
    
    public static final ComparatorConcat<Object> comparatorConcat = new ComparatorConcat<Object>();
}
