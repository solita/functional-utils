package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newLinkedMap;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newMap;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Collections.newSortedMap;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * Some Map operations.
 */
public abstract class FunctionalM {
    /**
     * @return value with {@code key} in {@code map}, if any.
     */
    public static final <K, V> Option<V> find(K key, Map<? super K, V> map) {
        return FunctionalImpl.find(key, map);
    }
    
    /**
     * @return elements in {@code map} whose entries satisfy {@code predicate}.
     */
    public static final <T, E> Map<T, E> filter(Apply<Map.Entry<T, E>, Boolean> predicate, Map<T, E> map) {
        return FunctionalImpl.filter(predicate, map);
    }
    
    /**
     * @return elements in {@code map} transformed with {@code f}.
     */
    public static final <K1, V1, K2, V2> Map<K2, V2> map(Apply<? super Map.Entry<K1, V1>, ? extends Map.Entry<? extends K2, ? extends V2>> f, Map<K1, V1> map) {
        return FunctionalImpl.map(f, map);
    }
    
    /**
     * @return elements in {@code map} transformed with {@code f}.
     */
    public static <T,V,R> SortedMap<T, Iterable<R>> map(Apply<V,R> f, SortedMap<T,? extends Iterable<V>> map) {
        return (SortedMap<T, Iterable<R>>) newSortedMap(map.comparator(), Functional.map(FunctionalM.<T,V,R>valueMapper(f), map.entrySet()));
    }
    
    /**
     * @return {@code map} with elements of values transformed with {@code f}.
     */
    public static <T,V,R> Map<T, Iterable<R>> mapValues(Apply<V,R> f, Map<T,? extends Iterable<V>> map) {
        return newLinkedMap(Functional.map(FunctionalM.<T,V,R>valueMapper(f), map.entrySet()));
    }
    
    /**
     * @return {@code map} with elements of values transformed with {@code f}.
     */
    public static <T,V,R> Map<T, List<R>> mapValueList(Apply<V,R> f, Map<T,? extends Iterable<V>> map) {
        return newLinkedMap(Functional.map(FunctionalM.<T,V,R>valueMapperList(f), map.entrySet()));
    }
    
    /**
     * @return {@code map} with elements of values transformed with {@code f}.
     */
    public static <T,V,R> Map<T, Set<R>> mapValueSet(Apply<V,R> f, Map<T,? extends Iterable<V>> map) {
        return newLinkedMap(Functional.map(FunctionalM.<T,V,R>valueMapperSet(f), map.entrySet()));
    }
    
    /**
     * <i>Unsafe!</i> mapping multiple values to same key will lose entries.
     * 
     * @return {@code map} with keys transformed with {@code f}.
     */
    public static <K,V,R> Map<R, V> mapKey(Apply<K,R> f, Map<K,V> map) {
        return newLinkedMap(FunctionalImpl.map(Transformers.<K,V>key().andThen(f), Transformers.<K,V>value(), map.entrySet()));
    }
    
    /**
     * @return {@code map} with values transformed with {@code f}.
     */
    public static <K,V,R> Map<K, R> mapValue(Apply<V,R> f, Map<K,V> map) {
        return newLinkedMap(FunctionalImpl.map(Transformers.<K,V>key(), Transformers.<K,V>value().andThen(f), map.entrySet()));
    }
    
    private static <T,V,R> Transformer<Map.Entry<T, ? extends Iterable<V>>,Map.Entry<T, Iterable<R>>> valueMapper(final Apply<V,R> f) {
        return new Transformer<Map.Entry<T, ? extends Iterable<V>>, Map.Entry<T, Iterable<R>>>() {
            @Override
            public Map.Entry<T, Iterable<R>> transform(Map.Entry<T, ? extends Iterable<V>> e) {
                return Pair.of(e.getKey(), FunctionalImpl.map(f, e.getValue()));
            }
        };
    }
    
    private static <T,V,R> Transformer<Map.Entry<T, ? extends Iterable<V>>,Map.Entry<T, List<R>>> valueMapperList(final Apply<V,R> f) {
        return new Transformer<Map.Entry<T, ? extends Iterable<V>>, Map.Entry<T, List<R>>>() {
            @Override
            public Map.Entry<T, List<R>> transform(Map.Entry<T, ? extends Iterable<V>> e) {
                return Pair.of(e.getKey(), newList(FunctionalImpl.map(f, e.getValue())));
            }
        };
    }
    
    private static <T,V,R> Transformer<Map.Entry<T, ? extends Iterable<V>>,Map.Entry<T, Set<R>>> valueMapperSet(final Apply<V,R> f) {
        return new Transformer<Map.Entry<T, ? extends Iterable<V>>, Map.Entry<T, Set<R>>>() {
            @Override
            public Map.Entry<T, Set<R>> transform(Map.Entry<T, ? extends Iterable<V>> e) {
                return Pair.of(e.getKey(), newSet(FunctionalImpl.map(f, e.getValue())));
            }
        };
    }
    
    /**
     * @return elements in {@code xs} grouped by {@code f}.
     */
    public static final <G, T> Map<G, List<T>> groupBy(Apply<? super T,G> f, Iterable<T> xs) {
        return FunctionalImpl.groupBy(f, xs);
    }
    
    /**
     * <i>Unsafe!</i> New key might already exist in map.
     * 
     * @return {@code map} with additional entry having {@code key} and {@code value}.
     */
    public static final <K, V> Map<K, V> with(K key, V value, Map<? extends K, ? extends V> map) {
        return map == null ? null : newMap(FunctionalImpl.concat(map.entrySet(), newList(Pair.of(key, value))));
    }
}
