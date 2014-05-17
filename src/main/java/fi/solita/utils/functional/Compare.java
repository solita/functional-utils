package fi.solita.utils.functional;

import java.util.Comparator;
import java.util.Iterator;

public abstract class Compare {
    
    public static final <T extends Comparable<T>> Ordering<Iterable<T>> byIterable() {
        return byIterable(Ordering.<T>Natural());
    }
    
    public static final <S, T extends Iterable<? extends S>> Ordering<T> byIterable(final Comparator<S> c) {
        return new Ordering<T>() {
            @Override
            public int compare(T o1, T o2) {
                for (Long s1: Iterables.resolveSize.apply(o1)) {
                    for (Long s2: Iterables.resolveSize.apply(o2)) {
                        int s = s1.compareTo(s2);
                        if (s != 0) {
                            return s;
                        }
                    }
                }
                Iterator<? extends S> it1 = o1.iterator();
                Iterator<? extends S> it2 = o2.iterator();
                while (it1.hasNext() && it2.hasNext()) {
                    int i = c.compare(it1.next(), it2.next());
                    if (i != 0) {
                        return i;
                    }
                }
                return it1.hasNext() ? 1 : it2.hasNext() ? -1 : 0;
            }
        }; 
    }
    
    public static final <T extends Comparable<?>> Ordering<T> byNatural() {
        return Ordering.Natural();
    }
    
    @SuppressWarnings("unchecked")
    public static final <T> Ordering<T> by(final Apply<? super T, ? extends Comparable<?>> f) {
        return by(f, (Comparator<Object>)(Object)Ordering.Natural());
    }
    
    public static final <T,S> Ordering<S> by(final Apply<? super S, T> f, final Comparator<? super T> c) {
        return new Ordering<S>() {
            @Override
            public int compare(S o1, S o2) {
                return c.compare(f.apply(o1), f.apply(o2));
            }
        };
    }
    
    public static final <S, T extends Comparable<T>> Ordering<S> byOption(final Apply<? super S, Option<T>> f) {
        return byOption(f, Ordering.Natural());
    }
    
    public static final <S, T> Ordering<S> byOption(final Apply<? super S, Option<T>> f, final Comparator<? super T> c) {
        return by(f, Compare.<T>byOption(c));
    }
    
    public static final <T extends Comparable<T>> Ordering<Option<T>> byOption() {
        return byOption(Ordering.<T>Natural());
    }
    
    public static final <T> Ordering<Option<T>> byOption(final Comparator<? super T> c) {
        return new Ordering<Option<T>>() {
            @Override
            public int compare(Option<T> o1, Option<T> o2) {
                if (!o1.isDefined() && !o2.isDefined()) {
                    return 0;
                } else if (o1.isDefined() && !o2.isDefined()) {
                    return -1;
                } else if (!o1.isDefined() && o2.isDefined()) {
                    return 1;
                } else {
                    return c.compare(o1.get(), o2.get());
                }
            }
        };
    }
    
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._1<? extends Comparable<?>>> by_1 = (Ordering<Tuple._1<? extends Comparable<?>>>)(Object)by(Tuple_._1_.<Comparable<?>>get_1());
    
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._2<? extends Comparable<?>>> by_2 = (Ordering<Tuple._2<? extends Comparable<?>>>)(Object)by(Tuple_._2_.<Comparable<?>>get_2());

    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._3<? extends Comparable<?>>> by_3 = (Ordering<Tuple._3<? extends Comparable<?>>>)(Object)by(Tuple_._3_.<Comparable<?>>get_3());
    
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._4<? extends Comparable<?>>> by_4 = (Ordering<Tuple._4<? extends Comparable<?>>>)(Object)by(Tuple_._4_.<Comparable<?>>get_4());
    
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._5<? extends Comparable<?>>> by_5 = (Ordering<Tuple._5<? extends Comparable<?>>>)(Object)by(Tuple_._5_.<Comparable<?>>get_5());
    
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._6<? extends Comparable<?>>> by_6 = (Ordering<Tuple._6<? extends Comparable<?>>>)(Object)by(Tuple_._6_.<Comparable<?>>get_6());
    
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._7<? extends Comparable<?>>> by_7 = (Ordering<Tuple._7<? extends Comparable<?>>>)(Object)by(Tuple_._7_.<Comparable<?>>get_7());
    
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._8<? extends Comparable<?>>> by_8 = (Ordering<Tuple._8<? extends Comparable<?>>>)(Object)by(Tuple_._8_.<Comparable<?>>get_8());
    
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._9<? extends Comparable<?>>> by_9 = (Ordering<Tuple._9<? extends Comparable<?>>>)(Object)by(Tuple_._9_.<Comparable<?>>get_9());
    
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._10<? extends Comparable<?>>> by_10 = (Ordering<Tuple._10<? extends Comparable<?>>>)(Object)by(Tuple_._10_.<Comparable<?>>get_10());
    
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._11<? extends Comparable<?>>> by_11 = (Ordering<Tuple._11<? extends Comparable<?>>>)(Object)by(Tuple_._11_.<Comparable<?>>get_11());
    
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._12<? extends Comparable<?>>> by_12 = (Ordering<Tuple._12<? extends Comparable<?>>>)(Object)by(Tuple_._12_.<Comparable<?>>get_12());
    
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._13<? extends Comparable<?>>> by_13 = (Ordering<Tuple._13<? extends Comparable<?>>>)(Object)by(Tuple_._13_.<Comparable<?>>get_13());
    
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._14<? extends Comparable<?>>> by_14 = (Ordering<Tuple._14<? extends Comparable<?>>>)(Object)by(Tuple_._14_.<Comparable<?>>get_14());
    
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._15<? extends Comparable<?>>> by_15 = (Ordering<Tuple._15<? extends Comparable<?>>>)(Object)by(Tuple_._15_.<Comparable<?>>get_15());
    
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._16<? extends Comparable<?>>> by_16 = (Ordering<Tuple._16<? extends Comparable<?>>>)(Object)by(Tuple_._16_.<Comparable<?>>get_16());
    
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._17<? extends Comparable<?>>> by_17 = (Ordering<Tuple._17<? extends Comparable<?>>>)(Object)by(Tuple_._17_.<Comparable<?>>get_17());
    
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._18<? extends Comparable<?>>> by_18 = (Ordering<Tuple._18<? extends Comparable<?>>>)(Object)by(Tuple_._18_.<Comparable<?>>get_18());
    
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._19<? extends Comparable<?>>> by_19 = (Ordering<Tuple._19<? extends Comparable<?>>>)(Object)by(Tuple_._19_.<Comparable<?>>get_19());
    
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._20<? extends Comparable<?>>> by_20 = (Ordering<Tuple._20<? extends Comparable<?>>>)(Object)by(Tuple_._20_.<Comparable<?>>get_20());
    
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._21<? extends Comparable<?>>> by_21 = (Ordering<Tuple._21<? extends Comparable<?>>>)(Object)by(Tuple_._21_.<Comparable<?>>get_21());
    
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._22<? extends Comparable<?>>> by_22 = (Ordering<Tuple._22<? extends Comparable<?>>>)(Object)by(Tuple_._22_.<Comparable<?>>get_22());

}
