package fi.solita.utils.functional;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;

public abstract class Compare {
    
    /**
     * @return natural ordering.
     */
    public static final <T extends Comparable<?>> Ordering<T> byNatural() {
        return Ordering.Natural();
    }
    
    /**
     * @return ordering for iterables containing elements with natural ordering.
     */
    public static final <T extends Comparable<T>> Ordering<Iterable<T>> byIterable() {
        return byIterable(Ordering.<T>Natural());
    }
    
    /**
     * @return ordering for an optional element with natural ordering.
     */
    public static final <T extends Comparable<T>> Ordering<Option<T>> byOption() {
        return byOption(Ordering.<T>Natural());
    }
    
    public static final <K extends Comparable<K>, V extends Comparable<V>> Ordering<Map<K,V>> byMap() {
        return byMap(Ordering.<K>Natural(), Ordering.<V>Natural());
    }
    
    public static final <K, V> Ordering<Map<K,V>> byMap(final Comparator<K> keyComparator, final Comparator<V> valueComparator) {
        return by(Transformers.<K,V>mapEntrySet(), byIterable(by(Transformers.<K,V>key(), keyComparator).then(by(Transformers.<K,V>value(), valueComparator))));
    }
    
    /**
     * @return ordering for iterables containing elements comparable by {@code elementComparator}.
     */
    public static final <S, T extends Iterable<? extends S>> Ordering<T> byIterable(final Comparator<S> elementComparator) {
        return new Ordering<T>() {
            public final int compare(T o1, T o2) {
                if (o1 == null && o2 == null) {
                    return 0;
                }
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
                    int i = elementComparator.compare(it1.next(), it2.next());
                    if (i != 0) {
                        return i;
                    }
                }
                return it1.hasNext() ? 1 : it2.hasNext() ? -1 : 0;
            }
        }; 
    }
    
    /**
     * @return ordering for elements by transforming to an element with natural ordering.
     */
    @SuppressWarnings("unchecked")
    public static final <T> Ordering<T> by(final Apply<? super T, ? extends Comparable<?>> f) {
        return by(f, (Comparator<Object>)(Object)Ordering.Natural());
    }
    
    /**
     * @return ordering for elements by transforming to a type comparable by {@code targetComparator}.
     */
    public static final <T,S> Ordering<S> by(final Apply<? super S, T> f, final Comparator<? super T> targetComparator) {
        return new Ordering<S>() {
            public final int compare(S o1, S o2) {
                if (o1 == null && o2 == null) {
                    return 0;
                }
                return targetComparator.compare(f.apply(o1), f.apply(o2));
            }
        };
    }
    
    /**
     * @return ordering for elements by transforming to an optional element with natural ordering. 
     */
    public static final <S, T extends Comparable<T>> Ordering<S> byOption(final Apply<? super S, ? extends Option<T>> f) {
        return byOption(f, Ordering.Natural());
    }
    
    /**
     * @return ordering for elements by transforming to an optional type comparable by {@code targetComparator}.
     */
    public static final <S, T> Ordering<S> byOption(final Apply<? super S, ? extends Option<T>> f, final Comparator<? super T> targetComparator) {
        return by(f, Compare.<T>byOption(targetComparator));
    }
    
    /**
     * @return ordering for optional type comparable by {@code comparator}.
     */
    public static final <T> Ordering<Option<T>> byOption(final Comparator<? super T> comparator) {
        return new Ordering<Option<T>>() {
            public final int compare(Option<T> o1, Option<T> o2) {
                if (o1 == null && o2 == null) {
                    return 0;
                }
                if (!o1.isDefined() && !o2.isDefined()) {
                    return 0;
                } else if (o1.isDefined() && !o2.isDefined()) {
                    return -1;
                } else if (!o1.isDefined() && o2.isDefined()) {
                    return 1;
                } else {
                    return comparator.compare(o1.get(), o2.get());
                }
            }
        };
    }
    
    public static final <K extends Comparable<K>,V> Ordering<Map.Entry<K,V>> byKey() {
        return by(Transformers.<K,V>key());
    }
    
    public static final <K,V extends Comparable<K>> Ordering<Map.Entry<K,V>> byValue() {
        return by(Transformers.<K,V>value());
    }
    
    /**
     * Ordering for 1. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._1<? extends Comparable<?>>> by_1 = (Ordering<Tuple._1<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_1());
    
    /**
     * Ordering for 2. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._2<? extends Comparable<?>>> by_2 = (Ordering<Tuple._2<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_2());

    /**
     * Ordering for 3. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._3<? extends Comparable<?>>> by_3 = (Ordering<Tuple._3<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_3());
    
    /**
     * Ordering for 4. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._4<? extends Comparable<?>>> by_4 = (Ordering<Tuple._4<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_4());
    
    /**
     * Ordering for 5. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._5<? extends Comparable<?>>> by_5 = (Ordering<Tuple._5<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_5());
    
    /**
     * Ordering for 6. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._6<? extends Comparable<?>>> by_6 = (Ordering<Tuple._6<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_6());
    
    /**
     * Ordering for 7. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._7<? extends Comparable<?>>> by_7 = (Ordering<Tuple._7<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_7());
    
    /**
     * Ordering for 8. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._8<? extends Comparable<?>>> by_8 = (Ordering<Tuple._8<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_8());
    
    /**
     * Ordering for 9. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._9<? extends Comparable<?>>> by_9 = (Ordering<Tuple._9<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_9());
    
    /**
     * Ordering for 10. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._10<? extends Comparable<?>>> by_10 = (Ordering<Tuple._10<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_10());
    
    /**
     * Ordering for 11. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._11<? extends Comparable<?>>> by_11 = (Ordering<Tuple._11<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_11());
    
    /**
     * Ordering for 12. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._12<? extends Comparable<?>>> by_12 = (Ordering<Tuple._12<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_12());
    
    /**
     * Ordering for 13. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._13<? extends Comparable<?>>> by_13 = (Ordering<Tuple._13<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_13());
    
    /**
     * Ordering for 14. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._14<? extends Comparable<?>>> by_14 = (Ordering<Tuple._14<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_14());
    
    /**
     * Ordering for 15. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._15<? extends Comparable<?>>> by_15 = (Ordering<Tuple._15<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_15());
    
    /**
     * Ordering for 16. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._16<? extends Comparable<?>>> by_16 = (Ordering<Tuple._16<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_16());
    
    /**
     * Ordering for 17. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._17<? extends Comparable<?>>> by_17 = (Ordering<Tuple._17<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_17());
    
    /**
     * Ordering for 18. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._18<? extends Comparable<?>>> by_18 = (Ordering<Tuple._18<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_18());
    
    /**
     * Ordering for 19. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._19<? extends Comparable<?>>> by_19 = (Ordering<Tuple._19<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_19());
    
    /**
     * Ordering for 20. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._20<? extends Comparable<?>>> by_20 = (Ordering<Tuple._20<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_20());
    
    /**
     * Ordering for 21. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._21<? extends Comparable<?>>> by_21 = (Ordering<Tuple._21<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_21());
    
    /**
     * Ordering for 22. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._22<? extends Comparable<?>>> by_22 = (Ordering<Tuple._22<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_22());

    /**
     * Ordering for 23. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._23<? extends Comparable<?>>> by_23 = (Ordering<Tuple._23<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_23());
    
    /**
     * Ordering for 24. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._24<? extends Comparable<?>>> by_24 = (Ordering<Tuple._24<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_24());
    
    /**
     * Ordering for 25. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._25<? extends Comparable<?>>> by_25 = (Ordering<Tuple._25<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_25());
    
    /**
     * Ordering for 26. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._26<? extends Comparable<?>>> by_26 = (Ordering<Tuple._26<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_26());
    
    /**
     * Ordering for 27. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._27<? extends Comparable<?>>> by_27 = (Ordering<Tuple._27<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_27());
    
    /**
     * Ordering for 28. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._28<? extends Comparable<?>>> by_28 = (Ordering<Tuple._28<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_28());
    
    /**
     * Ordering for 29. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._29<? extends Comparable<?>>> by_29 = (Ordering<Tuple._29<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_29());
    
    /**
     * Ordering for 30. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._30<? extends Comparable<?>>> by_30 = (Ordering<Tuple._30<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_30());
    
    /**
     * Ordering for 31. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._31<? extends Comparable<?>>> by_31 = (Ordering<Tuple._31<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_31());
    
    /**
     * Ordering for 32. element of a tuple.
     */
    @SuppressWarnings("unchecked")
    public static final Ordering<Tuple._32<? extends Comparable<?>>> by_32 = (Ordering<Tuple._32<? extends Comparable<?>>>)(Object)by(Transformers.<Comparable<?>>_32());
}
