package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newMap;
import static fi.solita.utils.functional.Collections.newSortedMap;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public abstract class FunctionalM {
    public static final <K, V> Option<V> find(K key, Map<? super K, V> map) {
        return FunctionalImpl.find(key, map);
    }
    
    public static final <T, E> Map<T, E> filter(Apply<Map.Entry<T, E>, Boolean> predicate, Map<T, E> map) {
        return FunctionalImpl.filter(predicate, map);
    }
    
    public static final <K1, V1, K2, V2> Map<K2, V2> map(Apply<? super Map.Entry<K1, V1>, ? extends Map.Entry<? extends K2, ? extends V2>> f, Map<K1, V1> map) {
        return FunctionalImpl.map(f, map);
    }
    
    public static <T,V,R> SortedMap<T, Iterable<R>> customMap(Apply<V,R> f, SortedMap<T,? extends Iterable<V>> m) {
        return (SortedMap<T, Iterable<R>>) newSortedMap(m.comparator(), Functional.map(FunctionalM.<T,V,R>valueMapper(f), m.entrySet()));
    }
    
    private static <T,V,R> Transformer<Map.Entry<T, ? extends Iterable<V>>,Map.Entry<T, Iterable<R>>> valueMapper(final Apply<V,R> f) {
        return new Transformer<Map.Entry<T, ? extends Iterable<V>>, Map.Entry<T, Iterable<R>>>() {
            @Override
            public Map.Entry<T, Iterable<R>> transform(Map.Entry<T, ? extends Iterable<V>> e) {
                return Pair.of(e.getKey(), Functional.map(f, e.getValue()));
            }
        };
    }
    
    public static final <G, T> Map<G, List<T>> groupBy(Apply<? super T,G> f, Iterable<T> xs) {
        return FunctionalImpl.groupBy(f, xs);
    }
    
    public static final <K, V> Map<K, V> with(K key, V value, Map<? extends K, ? extends V> map) {
        return map == null ? null : newMap(FunctionalImpl.concat(map.entrySet(), newList(Pair.of(key, value))));
    }
}
