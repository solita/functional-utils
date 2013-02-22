package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.filter;

import java.util.Comparator;
import java.util.Set;

public interface SemiGroup<T> extends Apply<Tuple2<T,T>, T> {
    public static class BooleanDisjunction extends Function2<Boolean,Boolean,Boolean> implements SemiGroup<Boolean> {
        @Override
        public Boolean apply(Boolean first, Boolean second) {
            return first || second;
        }
    }

    public static class BooleanConjunction extends Function2<Boolean,Boolean,Boolean> implements SemiGroup<Boolean> {
        @Override
        public Boolean apply(Boolean first, Boolean second) {
            return first && second;
        }
    }

    public static class StringConcat extends Function2<String,String,String> implements SemiGroup<String> {
        @Override
        public String apply(String first, String second) {
            return first + second;
        }
    }

    public static class LongProduct extends Function2<Long,Long,Long> implements SemiGroup<Long> {
        @Override
        public Long apply(Long first, Long second) {
            return first * second;
        }
    }

    public static class LongSum extends Function2<Long,Long,Long> implements SemiGroup<Long> {
        @Override
        public Long apply(Long first, Long second) {
            return first + second;
        }
    }

    public static class IntProduct extends Function2<Integer,Integer,Integer> implements SemiGroup<Integer> {
        @Override
        public Integer apply(Integer first, Integer second) {
            return first * second;
        }
    }

    public static class IntSum extends Function2<Integer,Integer,Integer> implements SemiGroup<Integer> {
        @Override
        public Integer apply(Integer first, Integer second) {
            return first + second;
        }
    }
    
    public static class SetUnion<T> extends Function2<Set<T>,Set<T>,Set<T>> implements SemiGroup<Set<T>> {
        @Override
        public Set<T> apply(Set<T> first, Set<T> second) {
            if (first instanceof Monoid.SetIntersection.AllContainingSet) {
                return second;
            }
            if (second instanceof Monoid.SetIntersection.AllContainingSet) {
                return first;
            }
            return newSet(concat(first, second));
        }
    }
    
    public static class SetIntersection<T> extends Function2<Set<T>,Set<T>,Set<T>> implements SemiGroup<Set<T>> {
        @Override
        public Set<T> apply(Set<T> first, final Set<T> second) {
            if (first instanceof Monoid.SetIntersection.AllContainingSet) {
                return second;
            }
            if (second instanceof Monoid.SetIntersection.AllContainingSet) {
                return first;
            }
            return newSet(filter(first, new Predicate<T>() {
                @Override
                public boolean accept(T candidate) {
                    return second.contains(candidate);
                }
            }));
        }
    }
    
    public static class ComparatorConcat<T> extends Function2<Comparator<T>,Comparator<T>,Comparator<T>> implements SemiGroup<Comparator<T>> {
        @Override
        public Comparator<T> apply(final Comparator<T> first, final Comparator<T> second) {
            return new Comparator<T>() {
                @Override
                public int compare(T o1, T o2) {
                    int c = first.compare(o1, o2);
                    return c != 0 ? c : second.compare(o1, o2);
                }
            };
        }
    }

    public static final SemiGroup<Integer> IntSum = Monoid.IntSum;

    public static final SemiGroup<Integer> IntProduct = Monoid.IntProduct;

    public static final SemiGroup<Long> LongSum = Monoid.LongSum;

    public static final SemiGroup<Long> LongProduct = Monoid.LongProduct;

    public static final SemiGroup<String> StringConcat = Monoid.StringConcat;

    public static final SemiGroup<Boolean> BooleanConjunction = Monoid.BooleanConjunction;

    public static final SemiGroup<Boolean> BooleanDisjunction = Monoid.BooleanDisjunction;
    
    public static final SemiGroup<Set<Object>> SetUnion = Monoid.SetUnion;
    
    public static final SemiGroup<Set<Object>> SetIntersection = Monoid.SetIntersection;
    
    public static final SemiGroup<Comparator<Object>> ComparatorConcat = Monoid.ComparatorConcat;
}
