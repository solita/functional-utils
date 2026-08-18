package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newLinkedMap;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newMap;
import static fi.solita.utils.functional.Collections.newMultimap;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Collections.newSortedMap;
import static fi.solita.utils.functional.Functional.repeat;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;

/**
 * Some Map operations.
 */
public abstract class FunctionalM {
    /**
     * @return value with {@code key} in {@code map}, if any.
     */
    public static final <K, V> Optional<V> find(K key, Map<? super K, ? extends V> map) {
        return FunctionalImpl.find(key, map);
    }
    
    /**
     * @return values in {@code map} whose keys satisfy {@code predicate}.
     */
    public static final <K, V> Iterable<V> findBy(java.util.function.Predicate<? super K> predicate, Map<K, ? extends V> map) {
        return FunctionalImpl.map(Map.Entry::getValue, FunctionalImpl.filter(x -> predicate.test(x.getKey()), map.entrySet()));
    }
    
    /**
     * @return elements in {@code map} whose entries satisfy {@code predicate}.
     */
    public static final <T, E> Map<T, E> filter(BiPredicate<? super T, ? super E> predicate, Map<T, E> map) {
        return FunctionalImpl.filter(predicate, map);
    }
    
    /**
     * @return elements in {@code map} transformed with {@code f}.
     */
    public static final <K1, V1, K2, V2> Map<K2, V2> map(SemiGroup<V2> valueCombiner, BiFunction<? super K1, ? super V1, ? extends Map.Entry<K2,V2>> f, Map<K1, V1> map) {
        return FunctionalImpl.map(valueCombiner, f, map);
    }
    
    /**
     * @return elements in {@code map} with keys transformed with {@code f1} and values transformed with {@code f2}.
     */
    public static final <K1, V1, K2, V2> Map<K2, V2> map(SemiGroup<V2> valueCombiner, final java.util.function.Function<K1, K2> f1, final java.util.function.Function<V1, V2> f2, Map<K1, V1> map) {
        return FunctionalImpl.map(valueCombiner, (a,b) -> Pair.of(f1.apply(a), f2.apply(b)), map);
    }
    
    /**
     * @return elements in {@code map} transformed with {@code f}.
     */
    public static <T,V,R> SortedMap<T, Iterable<R>> map(java.util.function.Function<? super V,? extends R> f, SortedMap<T,? extends Iterable<V>> map) {
        return map == null ? null : (SortedMap<T, Iterable<R>>) newSortedMap(SemiGroups.<Iterable<R>>fail(), map.comparator(), FunctionalImpl.map(FunctionalM.<T,V,R>valueMapper(f), map.entrySet()));
    }
    
    /**
     * @return {@code map} with elements of values transformed with {@code f}.
     */
    public static <T,V,R> Map<T, Iterable<R>> mapValues(java.util.function.Function<? super V,? extends R> f, Map<T,? extends Iterable<V>> map) {
        return map == null ? null : newLinkedMap(SemiGroups.<Iterable<R>>fail(), FunctionalImpl.map(FunctionalM.<T,V,R>valueMapper(f), map.entrySet()));
    }
    
    /**
     * @return {@code map} with elements of values transformed with {@code f}.
     */
    public static <T,V,R> Map<T, List<R>> mapValueList(java.util.function.Function<? super V,? extends R> f, Map<T,? extends Iterable<V>> map) {
        return map == null ? null : newLinkedMap(SemiGroups.<List<R>>fail(), FunctionalImpl.map(FunctionalM.<T,V,R>valueMapperList(f), map.entrySet()));
    }
    
    /**
     * @return {@code map} with elements of values transformed with {@code f}.
     */
    public static <T,V,R> Map<T, Set<R>> mapValueSet(java.util.function.Function<? super V,? extends R> f, Map<T,? extends Iterable<V>> map) {
        return map == null ? null : newLinkedMap(SemiGroups.<Set<R>>fail(), FunctionalImpl.map(FunctionalM.<T,V,R>valueMapperSet(f), map.entrySet()));
    }
    
    /**
     * @return {@code map} with keys transformed with {@code f}.
     */
    public static <K,V,R> Map<R, V> mapKey(SemiGroup<V> valueCombiner, java.util.function.Function<? super K,? extends R> f, Map<K,V> map) {
        return map == null ? null : newLinkedMap(valueCombiner, FunctionalImpl.map(x -> f.apply(x.getKey()), x -> x.getValue(), map.entrySet()));
    }
    
    /**
     * @return {@code map} with values transformed with {@code f}.
     */
    public static <K,V,R> Map<K, R> mapValue(java.util.function.Function<? super V,? extends R> f, Map<K,V> map) {
        return map == null ? null : newLinkedMap(SemiGroups.<R>fail(), FunctionalImpl.map(Map.Entry::getKey, x -> f.apply(x.getValue()), map.entrySet()));
    }
    
    public static <K,V,C extends Iterable<V>> Iterable<Pair<K, V>> flatten(Map<K,C> map) {
        return map == null ? null : FunctionalImpl.flatMap(new java.util.function.Function<Map.Entry<K,C>,Iterable<Pair<K,V>>>() {
            @Override
            public Iterable<Pair<K, V>> apply(final Map.Entry<K, C> t) {
                return FunctionalImpl.zip(repeat(t.getKey()), t.getValue());
            }
        }, map.entrySet());
    }
    
    public static <K,V1,V2,C extends Iterable<? extends Map.Entry<V1,V2>>> Iterable<Tuple3<K,V1,V2>> flatten2(Map<K,C> map) {
        return map == null ? null : FunctionalImpl.flatMap(new java.util.function.Function<Map.Entry<K,C>,Iterable<Tuple3<K,V1,V2>>>() {
            @Override
            public Iterable<Tuple3<K,V1,V2>> apply(final Map.Entry<K, C> t) {
                return FunctionalImpl.map(e -> Tuple.of(t.getKey(), e.getKey(), e.getValue()), (Iterable<? extends Map.Entry<V1,V2>>)t.getValue());
            }
        }, map.entrySet());
    }
    
    public static <K,V1,V2,V3,C extends Iterable<Tuple3<V1,V2,V3>>> Iterable<Tuple4<K,V1,V2,V3>> flatten3(Map<K,C> map) {
        return map == null ? null : FunctionalImpl.flatMap(new java.util.function.Function<Map.Entry<K,C>,Iterable<Tuple4<K,V1,V2,V3>>>() {
            @Override
            public Iterable<Tuple4<K,V1,V2,V3>> apply(final Map.Entry<K, C> t) {
                return FunctionalImpl.map(new java.util.function.Function<Tuple3<V1,V2,V3>, Tuple4<K,V1,V2,V3>>() {
                    @Override
                    public Tuple4<K, V1, V2, V3> apply(Tuple3<V1, V2, V3> tt) {
                        return tt.prepend(t.getKey());
                    }
                }, t.getValue());
            }
        }, map.entrySet());
    }
    
    private static <T,V,R> java.util.function.Function<Map.Entry<T, ? extends Iterable<V>>,Map.Entry<T, Iterable<R>>> valueMapper(final java.util.function.Function<? super V,? extends R> f) {
        return new java.util.function.Function<Map.Entry<T, ? extends Iterable<V>>, Map.Entry<T, Iterable<R>>>() {
            @Override
            public Map.Entry<T, Iterable<R>> apply(Map.Entry<T, ? extends Iterable<V>> e) {
                return Pair.of(e.getKey(), FunctionalImpl.map(f, e.getValue()));
            }
        };
    }
    
    private static <T,V,R> java.util.function.Function<Map.Entry<T, ? extends Iterable<V>>,Map.Entry<T, List<R>>> valueMapperList(final java.util.function.Function<? super V,? extends R> f) {
        return new java.util.function.Function<Map.Entry<T, ? extends Iterable<V>>, Map.Entry<T, List<R>>>() {
            @Override
            public Map.Entry<T, List<R>> apply(Map.Entry<T, ? extends Iterable<V>> e) {
                return Pair.of(e.getKey(), newList(FunctionalImpl.map(f, e.getValue())));
            }
        };
    }
    
    private static <T,V,R> java.util.function.Function<Map.Entry<T, ? extends Iterable<V>>,Map.Entry<T, Set<R>>> valueMapperSet(final java.util.function.Function<? super V,? extends R> f) {
        return new java.util.function.Function<Map.Entry<T, ? extends Iterable<V>>, Map.Entry<T, Set<R>>>() {
            @Override
            public Map.Entry<T, Set<R>> apply(Map.Entry<T, ? extends Iterable<V>> e) {
                return Pair.of(e.getKey(), newSet(FunctionalImpl.map(f, e.getValue())));
            }
        };
    }
    
    /**
     * @return elements in {@code xs} grouped by {@code f}.
     */
    public static final <G, T> Map<G, List<T>> groupBy(java.util.function.Function<? super T,? extends G> f, Iterable<? extends T> xs) {
        return FunctionalImpl.groupBy(f, xs);
    }
    
    public static final <G,V,T> Map<G, List<V>> groupBy(java.util.function.Function<? super T,? extends G> key, java.util.function.Function<? super T,? extends V> value, Iterable<? extends T> xs) {
        return newMultimap(FunctionalImpl.map(Pair.fanout(key, value), xs));
    }
    
    /**
     * @return tuples in {@code xs} grouped by first value of the tuple.
     */
    public static final <G, R, T extends Tuple._1<G> & Tuple.Tailable<R>> Map<G, List<R>> groupByFirst(Iterable<? extends T> xs) {
        return newMultimap(FunctionalImpl.map(new java.util.function.Function<T, Map.Entry<G,R>>() {
            public Map.Entry<G, R> apply(T t) {
                return Pair.of(t.get_1(), t.drop1());
            }
        }, xs));
    }
    
    /**
     * @return tuples in {@code xs} grouped by first value of the tuple, duplicates handled with {@code valueCombiner}.
     */
    public static final <G, R, T extends Tuple._1<G> & Tuple.Tailable<R>> Map<G, R> groupByFirst(SemiGroup<R> valueCombiner, Iterable<? extends T> xs) {
        return newMap(valueCombiner, FunctionalImpl.map(new java.util.function.Function<T, Map.Entry<G,R>>() {
            public Map.Entry<G, R> apply(T t) {
                return Pair.of(t.get_1(), t.drop1());
            }
        }, xs));
    }
    
    /**
     * @return {@code map} with additional entry having {@code key} and {@code value}.
     */
    public static final <K, V> Map<K, V> with(SemiGroup<V> valueCombiner, K key, V value, Map<? extends K, ? extends V> map) {
        return map == null ? null : newMap(valueCombiner, FunctionalImpl.concat(map.entrySet(), newList(Pair.of(key, value))));
    }
}
