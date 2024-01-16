package fi.solita.utils.functional;

import static fi.solita.utils.functional.FunctionalA.concat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Deque;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import fi.solita.utils.functional.Iterables.ForceableIterable;

/**
 * Collection creators and conversions. All functions produce immutable objects unless otherwise indicated.
 */
public abstract class Collections {
    
    private static final SortedMap<Object, Object> EMPTY_SORTED_MAP = java.util.Collections.unmodifiableSortedMap(new TreeMap<Object, Object>());
    private static final SortedSet<Object> EMPTY_SORTED_SET = java.util.Collections.unmodifiableSortedSet(new TreeSet<Object>());

    /**
     * @return some implementation of an empty List.
     */
    public static final <T> List<T> emptyList() {
        return java.util.Collections.emptyList();
    }
    
    /**
     * @return some implementation of an empty Set.
     */
    public static final <T> Set<T> emptySet() {
        return java.util.Collections.emptySet();
    }
    
    /**
     * @return some implementation of an empty SortedSet.
     */
    @SuppressWarnings("unchecked")
    public static final <T> SortedSet<T> emptySortedSet() {
        return (SortedSet<T>) EMPTY_SORTED_SET;
    }
    
    /**
     * @return some implementation of an empty Map.
     */
    public static final <K,V> Map<K,V> emptyMap() {
        return java.util.Collections.emptyMap();
    }
    
    /**
     * @return some implementation of an empty SortedMap.
     */
    @SuppressWarnings("unchecked")
    public static final <K,V> SortedMap<K,V> emptySortedMap() {
        return (SortedMap<K, V>) EMPTY_SORTED_MAP;
    }
    
    /**
     * @return some implementation of an empty Collection.
     */
    public static final <T> Collection<T> emptyCollection() {
        return emptyList();
    }
    
    /**
     * @return some implementation of an empty Queue.
     */
    public static final <T> Queue<T> emptyQueue() {
        return emptyDeque();
    }
    
    /**
     * @return some implementation of an empty Deque.
     */
    public static final <T> Deque<T> emptyDeque() {
        // TODO: make unmodifiable
        return new LinkedList<T>();
    }
    
    /**
     * @return some implementation of an empty <b>mutable</b> List.
     */
    public static final <T> List<T> newMutableList() {
        return new ArrayList<T>();
    }
    
    /**
     * @return some implementation of an empty <b>mutable</b> Set.
     */
    public static final <T> Set<T> newMutableSet() {
        return new HashSet<T>();
    }
    
    /**
     * @return some implementation of an empty <b>mutable</b> SortedSet.
     */
    public static final <T extends Comparable<? super T>> SortedSet<T> newMutableSortedSet() {
        return new TreeSet<T>();
    }
    
    /**
     * @return some implementation of an empty <b>mutable</b> SortedSet, using {@link comparator} for ordering.
     */
    public static final <T> SortedSet<T> newMutableSortedSet(Comparator<? super T> comparator) {
        return new TreeSet<T>(comparator);
    }

    /**
     * @return some implementation of an empty <b>mutable</b> Map.
     */
    public static final <K, V> Map<K, V> newMutableMap() {
        return new HashMap<K, V>();
    }
    
    /**
     * @return some implementation of an empty <b>mutable</b> LinkedMap.
     */
    public static final <K,V> Map<K,V> newMutableLinkedMap() {
        return new LinkedHashMap<K,V>();
    }
    
    /**
     * @return some implementation of an empty <b>mutable</b> SortedMap.
     */
    public static final <K extends Comparable<? super K>,V> SortedMap<K,V> newMutableSortedMap() {
        return new TreeMap<K,V>();
    }
    
    /**
     * @return some implementation of an empty <b>mutable</b> SortedMap, using {@code comparator} for ordering.
     */
    public static final <K,V> SortedMap<K,V> newMutableSortedMap(Comparator<? super K> comparator) {
        return new TreeMap<K,V>(comparator);
    }
    
    /**
     * @return some implementation of an empty <b>mutable</b> Queue.
     */
    public static final <T> Queue<T> newMutableQueue() {
        return new LinkedList<T>();
    }
    
    /**
     * @return some implementation of an empty <b>mutable</b> Deque.
     */
    public static final <T> Deque<T> newMutableDeque() {
        return new LinkedList<T>();
    }
    
    /**
     * @return some implementation of an empty <b>mutable</b> Collection.
     */
    public static final <T> Collection<T> newMutableCollection() {
        return new ArrayList<T>();
    }
    
    /**
     * @return some implementation of an empty <b>mutable</b> List with {@code initialCapacity}.
     */
    public static final <T> List<T> newMutableListOfSize(long initialCapacity) {
        return new ArrayList<T>((int)initialCapacity);
    }

    /**
     * @return some implementation of an empty <b>mutable</b> Set with {@code initialCapacity}.
     */
    public static final <T> Set<T> newMutableSetOfSize(long initialCapacity) {
        return new HashSet<T>((int)initialCapacity);
    }

    /**
     * @return some implementation of an empty <b>mutable</b> Map with {@code initialCapacity}.
     */
    public static final <K,V> Map<K,V> newMutableMapOfSize(long initialCapacity) {
        return new HashMap<K,V>((int)initialCapacity);
    }
    
    /**
     * @return some implementation of an empty <b>mutable</b> LinedMap with {@code initialCapacity}.
     */
    public static final <K,V> Map<K,V> newMutableLinkedMapOfSize(long initialCapacity) {
        return new LinkedHashMap<K,V>((int)initialCapacity);
    }
    
    /**
     * @return some implementation of an empty <b>mutable</b> Collection with {@code initialCapacity}.
     */
    public static final <T> Collection<T> newMutableCollectionOfSize(long initialCapacity) {
        return new ArrayList<T>((int)initialCapacity);
    }
    
    /**
     * @return some implementation of a list containing elements {@code array}.
     */
    public static final List<Boolean> newList(boolean[] array) {
        return newList(newArray(array));
    }
    
    /**
     * @return some implementation of a list containing elements {@code array}.
     */
    public static final List<Byte> newList(byte[] array) {
        return newList(newArray(array));
    }
    
    /**
     * @return some implementation of a list containing elements {@code array}.
     */
    public static final List<Character> newList(char[] array) {
        return newList(newArray(array));
    }
    
    /**
     * @return some implementation of a list containing elements {@code array}.
     */
    public static final List<Double> newList(double[] array) {
        return newList(newArray(array));
    }
    
    /**
     * @return some implementation of a list containing elements {@code array}.
     */
    public static final List<Float> newList(float[] array) {
        return newList(newArray(array));
    }

    /**
     * @return some implementation of a list containing elements {@code array}.
     */
    public static final List<Integer> newList(int[] array) {
        return newList(newArray(array));
    }
    
    /**
     * @return some implementation of a list containing elements {@code array}.
     */
    public static final List<Long> newList(long[] array) {
        return newList(newArray(array));
    }
    
    /**
     * @return some implementation of a list containing elements {@code array}.
     */
    public static final List<Short> newList(short[] array) {
        return newList(newArray(array));
    }
    
    /**
     * @return some implementation of a list containing elements {@code elements}.
     */
    public static final <T> List<T> newList(T[] elements) {
        return elements == null ? null : newList(Arrays.asList(elements));
    }
    
    /**
     * @return some implementation of a list containing elements {@code elements}.
     */
    public static final <T> List<T> newList(Enumeration<T> elements) {
        return elements == null ? null : java.util.Collections.unmodifiableList(java.util.Collections.list(elements));
    }
    
    /**
     * @return some implementation of a list containing element {@code e1}.
     */
    public static final <T> List<T> newList(T e1) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1} and {@code e2}.
     */
    public static final <T> List<T> newList(T e1, T e2) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2} and {@code e3}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3} and {@code e4}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4} and {@code e5}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5} and {@code e6}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6} and {@code e7}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7} and {@code e8}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8} and {@code e9}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9} and {@code e10}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10} and {@code e11}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11} and {@code e12}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12} and {@code e13}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13} and {@code e14}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14} and {@code e15}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15} and {@code e16}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16} and {@code e17}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17} and {@code e18}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18} and {@code e19}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19} and {@code e20}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20} and {@code e21}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21} and {@code e22}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22} and {@code e23}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23} and {@code e24}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24} and {@code e25}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25} and {@code e26}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26} and {@code e27}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27} and {@code e28}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28} and {@code e29}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29} and {@code e30}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29, T e30) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30} and {@code e31}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29, T e30, T e31) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31} and {@code e32}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29, T e30, T e31, T e32) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31}, {@code e32} and {@code e33}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29, T e30, T e31, T e32, T e33) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e33));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31}, {@code e32}, {@code e33} and {@code e34}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29, T e30, T e31, T e32, T e33, T e34) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e33, e34));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31}, {@code e32}, {@code e33}, {@code e34} and {@code e35}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29, T e30, T e31, T e32, T e33, T e34, T e35) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e33, e34, e35));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31}, {@code e32}, {@code e33}, {@code e34}, {@code e35}, {@code e36}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29, T e30, T e31, T e32, T e33, T e34, T e35, T e36) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e33, e34, e35, e36));
    }

    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31}, {@code e32}, {@code e33}, {@code e34}, {@code e35}, {@code e36} and {@code e37}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29, T e30, T e31, T e32, T e33, T e34, T e35, T e36, T e37) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e33, e34, e35, e36, e37));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31}, {@code e32}, {@code e33}, {@code e34}, {@code e35}, {@code e36}, {@code e37} and {@code e38}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29, T e30, T e31, T e32, T e33, T e34, T e35, T e36, T e37, T e38) {
        return java.util.Collections.unmodifiableList(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e33, e34, e35, e36, e37, e38));
    }
    
    /**
     * @return some implementation of a list containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31}, {@code e32}, {@code e33}, {@code e34}, {@code e35}, {@code e36}, {@code e37}, {@code e38}, {@code e39} and {@code elements}.
     */
    public static final <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29, T e30, T e31, T e32, T e33, T e34, T e35, T e36, T e37, T e38, T e39, T... elements) {
        return newList(concat(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e33, e34, e35, e36, e37, e38, e39), elements));
    }
    
    /**
     * @return some implementation of a set containing elements {@code array}.
     */
    public static final Set<Boolean> newSet(boolean[] array) {
        return newSet(newArray(array));
    }
    
    /**
     * @return some implementation of a set containing elements {@code array}.
     */
    public static final Set<Byte> newSet(byte[] array) {
        return newSet(newArray(array));
    }
    
    /**
     * @return some implementation of a set containing elements {@code array}.
     */
    public static final Set<Character> newSet(char[] array) {
        return newSet(newArray(array));
    }
    
    /**
     * @return some implementation of a set containing elements {@code array}.
     */
    public static final Set<Double> newSet(double[] array) {
        return newSet(newArray(array));
    }
    
    /**
     * @return some implementation of a set containing elements {@code array}.
     */
    public static final Set<Float> newSet(float[] array) {
        return newSet(newArray(array));
    }

    /**
     * @return some implementation of a set containing elements {@code array}.
     */
    public static final Set<Integer> newSet(int[] array) {
        return newSet(newArray(array));
    }
    
    /**
     * @return some implementation of a set containing elements {@code array}.
     */
    public static final Set<Long> newSet(long[] array) {
        return newSet(newArray(array));
    }
    
    /**
     * @return some implementation of a set containing elements {@code array}.
     */
    public static final Set<Short> newSet(short[] array) {
        return newSet(newArray(array));
    }
    
    /**
     * @return some implementation of a set containing elements {@code elements}.
     */
    public static final <T> Set<T> newSet(T[] elements) {
        return elements == null ? null : newSet(Arrays.asList(elements));
    }
    
    /**
     * @return some implementation of a set containing elements {@code elements}.
     */
    public static final <T> Set<T> newSet(Enumeration<T> elements) {
        return elements == null ? null : newSet(java.util.Collections.list(elements));
    }
    
    /**
     * @return some implementation of a set containing element {@code e1}.
     */
    public static final <T> Set<T> newSet(T e1) {
        return java.util.Collections.singleton(e1);
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1} and {@code e2}.
     */
    public static final <T> Set<T> newSet(T e1, T e2) {
        return newSet(Arrays.asList(e1, e2));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2} and {@code e3}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3) {
        return newSet(Arrays.asList(e1, e2, e3));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3} and {@code e4}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4) {
        return newSet(Arrays.asList(e1, e2, e3, e4));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4} and {@code e5}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5} and {@code e6}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6} and {@code e7}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7} and {@code e8}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8} and {@code e9}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9} and {@code e10}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10} and {@code e11}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11} and {@code e12}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12} and {@code e13}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13} and {@code e14}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14} and {@code e15}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15} and {@code e16}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16} and {@code e17}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17} and {@code e18}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18} and {@code e19}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19} and {@code e20}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20} and {@code e21}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21} and {@code e22}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22} and {@code e23}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23} and {@code e24}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24} and {@code e25}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25} and {@code e26}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26} and {@code e27}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27} and {@code e28}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28} and {@code e29}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29} and {@code e30}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29, T e30) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30} and {@code e31}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29, T e30, T e31) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31} and {@code e32}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29, T e30, T e31, T e32) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31}, {@code e32} and {@code e33}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29, T e30, T e31, T e32, T e33) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e33));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31}, {@code e32}, {@code e33} and {@code e34}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29, T e30, T e31, T e32, T e33, T e34) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e33, e34));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31}, {@code e32}, {@code e33}, {@code e34} and {@code e35}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29, T e30, T e31, T e32, T e33, T e34, T e35) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e33, e34, e35));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31}, {@code e32}, {@code e33}, {@code e34}, {@code e35} and {@code e36}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29, T e30, T e31, T e32, T e33, T e34, T e35, T e36) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e33, e34, e35, e36));
    }

    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31}, {@code e32}, {@code e33}, {@code e34}, {@code e35}, {@code e36} and {@code e37}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29, T e30, T e31, T e32, T e33, T e34, T e35, T e36, T e37) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e33, e34, e35, e36, e37));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31}, {@code e32}, {@code e33}, {@code e34}, {@code e35}, {@code e36}, {@code e37} and {@code e38}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29, T e30, T e31, T e32, T e33, T e34, T e35, T e36, T e37, T e38) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e33, e34, e35, e36, e37));
    }
    
    /**
     * @return some implementation of a set containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31}, {@code e32}, {@code e33}, {@code e34}, {@code e35}, {@code e36}, {@code e37}, {@code e38}, {@code e39} and {@code elements}.
     */
    public static final <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T e7, T e8, T e9, T e10, T e11, T e12, T e13, T e14, T e15, T e16, T e17, T e18, T e19, T e20, T e21, T e22, T e23, T e24, T e25, T e26, T e27, T e28, T e29, T e30, T e31, T e32, T e33, T e34, T e35, T e36, T e37, T e38, T e39, T... elements) {
        return newSet(concat(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e33, e34, e35, e36, e37, e38, e39), elements));
    }
    
    private static final Class<?> unmodifiableListClass = java.util.Collections.unmodifiableList(emptyList()).getClass();
    private static final Class<?> unmodifiableSetClass = java.util.Collections.unmodifiableSet(emptySet()).getClass();
    private static final Class<?> unmodifiableSortedSetClass = java.util.Collections.unmodifiableSortedSet(emptySortedSet()).getClass();

    /**
     * @return some implementation of a List containing {@code elements}.
     */
    public static final <T> List<T> newList(Iterable<T> elements) {
        if (elements == null) {
            return null;
        }
        if (unmodifiableListClass.isInstance(elements)) {
            return (List<T>)elements;
        }
        if (elements instanceof ForceableIterable) {
            ((ForceableIterable) elements).completeIterationNeeded();
        }
        List<T> ret = null;
        for (long size: Iterables.resolveSize.apply(elements)) {
            ret = newMutableListOfSize(size);
        }
        if (ret == null) {
            ret = newMutableList();
        }

        if (elements instanceof Collection) {
            ret.addAll((Collection<? extends T>) elements);
        } else {
            for (T t: elements) {
                ret.add(t);
            }
        }
        if (ret instanceof ArrayList) {
            ((ArrayList<?>) ret).trimToSize();
        }
        return ret.isEmpty() ? Collections.<T>emptyList() : java.util.Collections.unmodifiableList(ret);
    }

    /**
     * @return some implementation of a Set containing {@code elements}.
     */
    public static final <T> Set<T> newSet(Iterable<T> elements) {
        if (elements == null) {
            return null;
        }
        if (unmodifiableSetClass.isInstance(elements)) {
            return (Set<T>)elements;
        }
        if (elements instanceof ForceableIterable) {
            ((ForceableIterable) elements).completeIterationNeeded();
        }
        Set<T> ret = null;
        for (long size: Iterables.resolveSize.apply(elements)) {
            ret = newMutableSetOfSize(size);
        }
        if (ret == null) {
            ret = newMutableSet();
        }
        if (elements instanceof Collection) {
            ret.addAll((Collection<? extends T>) elements);
        } else {
            for (T t: elements) {
                ret.add(t);
            }
        }
        return ret.isEmpty() ? Collections.<T>emptySet() : java.util.Collections.unmodifiableSet(ret);
    }
    
    /**
     * @return some implementation of a SortedSt containing {@code elements}.
     */
    public static final <T extends Comparable<? super T>> SortedSet<T> newSortedSet(Iterable<T> elements) {
        if (elements == null) {
            return null;
        }
        if (unmodifiableSortedSetClass.isInstance(elements) && ((SortedSet<?>) elements).comparator() == null) {
            return (SortedSet<T>)elements;
        }
        if (elements instanceof ForceableIterable) {
            ((ForceableIterable) elements).completeIterationNeeded();
        }
        SortedSet<T> ret = newMutableSortedSet();
        if (elements instanceof Collection) {
            ret.addAll((Collection<? extends T>) elements);
        } else {
            for (T t: elements) {
                ret.add(t);
            }
        }
        return ret.isEmpty() ? Collections.<T>emptySortedSet() : java.util.Collections.unmodifiableSortedSet(ret);
    }
    
    /**
     * @return some implementation of a SortedSet containing {@code elements}, using {@code comparator} for ordering.
     */
    public static final <T> SortedSet<T> newSortedSet(Comparator<? super T> comparator, Iterable<T> elements) {
        if (elements == null) {
            return null;
        }
        if (unmodifiableSortedSetClass.isInstance(elements) && comparator.equals(((SortedSet<?>) elements).comparator())) {
            return (SortedSet<T>)elements;
        }
        if (elements instanceof ForceableIterable) {
            ((ForceableIterable) elements).completeIterationNeeded();
        }
        SortedSet<T> ret = newMutableSortedSet(comparator);
        if (elements instanceof Collection) {
            ret.addAll((Collection<? extends T>) elements);
        } else {
            for (T t: elements) {
                ret.add(t);
            }
        }
        return ret.isEmpty() ? Collections.<T>emptySortedSet() : java.util.Collections.unmodifiableSortedSet(ret);
    }
    
    /**
     * @return some implementation of a Map containing {@code elements} as keys and values acquired with {@code fValue}.
     */
    public static final <T, V> Map<T, V> newMap(Apply<? super T,V> fValue, Set<T> xs) {
        // cannot be duplicate keys, since xs is a Set
        return newMap(SemiGroups.<V>fail(), Functional.map(Function.<T>id(), fValue, xs));
    }
    
    /**
     * Use {@link SemiGroups#first()}, {@link SemiGroups#last()} or {@link SemiGroups#fail()} for handling duplicate elements.
     * 
     * @return some implementation of a Map containing {@code elements}.
     */
    public static final <K, V> Map<K, V> newMap(SemiGroup<V> valueCombiner, Iterable<? extends Map.Entry<? extends K, ? extends V>> elements) {
        if (elements == null) {
            return null;
        }
        if (elements instanceof ForceableIterable) {
            ((ForceableIterable) elements).completeIterationNeeded();
        }
        Map<K, V> ret = null;
        for (long size: Iterables.resolveSize.apply(elements)) {
            ret = newMutableMapOfSize(size);
        }
        if (ret == null) {
            ret = newMutableMap();
        }
        for (Map.Entry<? extends K, ? extends V> e: elements) {
            V v = ret.get(e.getKey());
            v = v == null ? e.getValue() : valueCombiner.apply(Pair.of(v, e.getValue()));
            ret.put(e.getKey(), v);
        }
        return ret.isEmpty() ? Collections.<K,V>emptyMap() : java.util.Collections.unmodifiableMap(ret);
    }

    /**
     * Use {@link SemiGroups#first()}, {@link SemiGroups#last()} or {@link SemiGroups#fail()} for handling duplicate elements.
     * 
     * @return some implementation of a LinkedMap containing {@code elements}.
     */
    public static final <K, V> Map<K, V> newLinkedMap(SemiGroup<V> valueCombiner, Iterable<? extends Map.Entry<? extends K, ? extends V>> elements) {
        if (elements == null) {
            return null;
        }
        if (elements instanceof ForceableIterable) {
            ((ForceableIterable) elements).completeIterationNeeded();
        }
        Map<K, V> ret = null;
        for (long size: Iterables.resolveSize.apply(elements)) {
            ret = newMutableLinkedMapOfSize(size);
        }
        if (ret == null) {
            ret = newMutableLinkedMap();
        }
        for (Map.Entry<? extends K, ? extends V> e: elements) {
            V v = ret.get(e.getKey());
            v = v == null ? e.getValue() : valueCombiner.apply(Pair.of(v, e.getValue()));
            ret.put(e.getKey(), v);
        }
        return ret.isEmpty() ? Collections.<K,V>emptyMap() : java.util.Collections.unmodifiableMap(ret);
    }
    
    /**
     * @return some implementation of a SortedMap containing {@code elements} as keys and values acquired with {@code fValue}.
     */
    public static final <T extends Comparable<? super T>, V> SortedMap<T, V> newSortedMap(Apply<? super T,V> fValue, Set<T> elements) {
        // cannot be duplicate keys, since xs is a Set
        return newSortedMap(SemiGroups.<V>fail(), Functional.map(Function.<T>id(), fValue, elements));
    }
    
    /**
     * @return some implementation of a SortedMap containing {@code elements} as keys and values acquired with {@code fValue}, using {@code comparator} for ordering.
     */
    public static final <T, V> SortedMap<T, V> newSortedMap(Comparator<? super T> comparator, Apply<? super T,V> fValue, Set<T> elements) {
        // cannot be duplicate keys, since xs is a Set
        return newSortedMap(SemiGroups.<V>fail(), comparator, Functional.map(Function.<T>id(), fValue, elements));
    }
    
    /**
     * Use {@link SemiGroups#first()}, {@link SemiGroups#last()} or {@link SemiGroups#fail()} for handling duplicate elements.
     * 
     * @return some implementation of a SortedMap containing {@code elements}.
     */
    public static final <K extends Comparable<? super K>, V> SortedMap<K, V> newSortedMap(SemiGroup<V> valueCombiner, Iterable<? extends Map.Entry<? extends K, ? extends V>> elements) {
        if (elements == null) {
            return null;
        }
        if (elements instanceof ForceableIterable) {
            ((ForceableIterable) elements).completeIterationNeeded();
        }
        SortedMap<K, V> ret = newMutableSortedMap();
        for (Map.Entry<? extends K, ? extends V> e: elements) {
            V v = ret.get(e.getKey());
            v = v == null ? e.getValue() : valueCombiner.apply(Pair.of(v, e.getValue()));
            ret.put(e.getKey(), v);
        }
        return ret.isEmpty() ? Collections.<K,V>emptySortedMap() : java.util.Collections.unmodifiableSortedMap(ret);
    }
    
    /**
     * Use {@link SemiGroups#first()}, {@link SemiGroups#last()} or {@link SemiGroups#fail()} for handling duplicate elements.
     * 
     * @return some implementation of a SortedMap containing {@code elements}, using {@code comparator} for ordering.
     */
    public static final <K, V> SortedMap<K, V> newSortedMap(SemiGroup<V> valueCombiner, Comparator<? super K> comparator, Iterable<? extends Map.Entry<? extends K, ? extends V>> elements) {
        if (elements == null) {
            return null;
        }
        if (elements instanceof ForceableIterable) {
            ((ForceableIterable) elements).completeIterationNeeded();
        }
        SortedMap<K, V> ret = newMutableSortedMap(comparator);
        for (Map.Entry<? extends K, ? extends V> e: elements) {
            V v = ret.get(e.getKey());
            v = v == null ? e.getValue() : valueCombiner.apply(Pair.of(v, e.getValue()));
            ret.put(e.getKey(), v);
        }
        return ret.isEmpty() ? Collections.<K,V>emptySortedMap() : java.util.Collections.unmodifiableSortedMap(ret);
    }
    
    /**
     * @return some implementation of a Map containing {@code e1}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1));
    }

    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1} and {@code e2}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2} and {@code e3}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3} and {@code e4}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4} and {@code e5}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5} and {@code e6}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6} and {@code e7}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7} and {@code e8}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8} and {@code e9}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9} and {@code e10}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10} and {@code e11}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11} and {@code e12}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12} and {@code e13}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13} and {@code e14}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14} and {@code e15}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15} and {@code e16}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16} and {@code e17}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17} and {@code e18}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18} and {@code e19}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18, Map.Entry<? extends K, ? extends V> e19) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19} and {@code e20}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18, Map.Entry<? extends K, ? extends V> e19, Map.Entry<? extends K, ? extends V> e20) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20} and {@code e21}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18, Map.Entry<? extends K, ? extends V> e19, Map.Entry<? extends K, ? extends V> e20, Map.Entry<? extends K, ? extends V> e21) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21} and {@code e22}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18, Map.Entry<? extends K, ? extends V> e19, Map.Entry<? extends K, ? extends V> e20, Map.Entry<? extends K, ? extends V> e21, Map.Entry<? extends K, ? extends V> e22) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22} and {@code e23}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18, Map.Entry<? extends K, ? extends V> e19, Map.Entry<? extends K, ? extends V> e20, Map.Entry<? extends K, ? extends V> e21, Map.Entry<? extends K, ? extends V> e22, Map.Entry<? extends K, ? extends V> e23) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23} and {@code e24}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18, Map.Entry<? extends K, ? extends V> e19, Map.Entry<? extends K, ? extends V> e20, Map.Entry<? extends K, ? extends V> e21, Map.Entry<? extends K, ? extends V> e22, Map.Entry<? extends K, ? extends V> e23, Map.Entry<? extends K, ? extends V> e24) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24} and {@code e25}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18, Map.Entry<? extends K, ? extends V> e19, Map.Entry<? extends K, ? extends V> e20, Map.Entry<? extends K, ? extends V> e21, Map.Entry<? extends K, ? extends V> e22, Map.Entry<? extends K, ? extends V> e23, Map.Entry<? extends K, ? extends V> e24, Map.Entry<? extends K, ? extends V> e25) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25} and {@code e26}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18, Map.Entry<? extends K, ? extends V> e19, Map.Entry<? extends K, ? extends V> e20, Map.Entry<? extends K, ? extends V> e21, Map.Entry<? extends K, ? extends V> e22, Map.Entry<? extends K, ? extends V> e23, Map.Entry<? extends K, ? extends V> e24, Map.Entry<? extends K, ? extends V> e25, Map.Entry<? extends K, ? extends V> e26) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26} and {@code e27}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18, Map.Entry<? extends K, ? extends V> e19, Map.Entry<? extends K, ? extends V> e20, Map.Entry<? extends K, ? extends V> e21, Map.Entry<? extends K, ? extends V> e22, Map.Entry<? extends K, ? extends V> e23, Map.Entry<? extends K, ? extends V> e24, Map.Entry<? extends K, ? extends V> e25, Map.Entry<? extends K, ? extends V> e26, Map.Entry<? extends K, ? extends V> e27) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27} and {@code e28}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18, Map.Entry<? extends K, ? extends V> e19, Map.Entry<? extends K, ? extends V> e20, Map.Entry<? extends K, ? extends V> e21, Map.Entry<? extends K, ? extends V> e22, Map.Entry<? extends K, ? extends V> e23, Map.Entry<? extends K, ? extends V> e24, Map.Entry<? extends K, ? extends V> e25, Map.Entry<? extends K, ? extends V> e26, Map.Entry<? extends K, ? extends V> e27, Map.Entry<? extends K, ? extends V> e28) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28} and {@code e29}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18, Map.Entry<? extends K, ? extends V> e19, Map.Entry<? extends K, ? extends V> e20, Map.Entry<? extends K, ? extends V> e21, Map.Entry<? extends K, ? extends V> e22, Map.Entry<? extends K, ? extends V> e23, Map.Entry<? extends K, ? extends V> e24, Map.Entry<? extends K, ? extends V> e25, Map.Entry<? extends K, ? extends V> e26, Map.Entry<? extends K, ? extends V> e27, Map.Entry<? extends K, ? extends V> e28, Map.Entry<? extends K, ? extends V> e29) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29} and {@code e30}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18, Map.Entry<? extends K, ? extends V> e19, Map.Entry<? extends K, ? extends V> e20, Map.Entry<? extends K, ? extends V> e21, Map.Entry<? extends K, ? extends V> e22, Map.Entry<? extends K, ? extends V> e23, Map.Entry<? extends K, ? extends V> e24, Map.Entry<? extends K, ? extends V> e25, Map.Entry<? extends K, ? extends V> e26, Map.Entry<? extends K, ? extends V> e27, Map.Entry<? extends K, ? extends V> e28, Map.Entry<? extends K, ? extends V> e29, Map.Entry<? extends K, ? extends V> e30) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30} and {@code e31}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18, Map.Entry<? extends K, ? extends V> e19, Map.Entry<? extends K, ? extends V> e20, Map.Entry<? extends K, ? extends V> e21, Map.Entry<? extends K, ? extends V> e22, Map.Entry<? extends K, ? extends V> e23, Map.Entry<? extends K, ? extends V> e24, Map.Entry<? extends K, ? extends V> e25, Map.Entry<? extends K, ? extends V> e26, Map.Entry<? extends K, ? extends V> e27, Map.Entry<? extends K, ? extends V> e28, Map.Entry<? extends K, ? extends V> e29, Map.Entry<? extends K, ? extends V> e30, Map.Entry<? extends K, ? extends V> e31) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31} and {@code e32}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18, Map.Entry<? extends K, ? extends V> e19, Map.Entry<? extends K, ? extends V> e20, Map.Entry<? extends K, ? extends V> e21, Map.Entry<? extends K, ? extends V> e22, Map.Entry<? extends K, ? extends V> e23, Map.Entry<? extends K, ? extends V> e24, Map.Entry<? extends K, ? extends V> e25, Map.Entry<? extends K, ? extends V> e26, Map.Entry<? extends K, ? extends V> e27, Map.Entry<? extends K, ? extends V> e28, Map.Entry<? extends K, ? extends V> e29, Map.Entry<? extends K, ? extends V> e30, Map.Entry<? extends K, ? extends V> e31, Map.Entry<? extends K, ? extends V> e32) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31}, {@code e32} and {@code e33}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18, Map.Entry<? extends K, ? extends V> e19, Map.Entry<? extends K, ? extends V> e20, Map.Entry<? extends K, ? extends V> e21, Map.Entry<? extends K, ? extends V> e22, Map.Entry<? extends K, ? extends V> e23, Map.Entry<? extends K, ? extends V> e24, Map.Entry<? extends K, ? extends V> e25, Map.Entry<? extends K, ? extends V> e26, Map.Entry<? extends K, ? extends V> e27, Map.Entry<? extends K, ? extends V> e28, Map.Entry<? extends K, ? extends V> e29, Map.Entry<? extends K, ? extends V> e30, Map.Entry<? extends K, ? extends V> e31, Map.Entry<? extends K, ? extends V> e32, Map.Entry<? extends K, ? extends V> e33) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e33));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31}, {@code e32}, {@code e33} and {@code e34}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18, Map.Entry<? extends K, ? extends V> e19, Map.Entry<? extends K, ? extends V> e20, Map.Entry<? extends K, ? extends V> e21, Map.Entry<? extends K, ? extends V> e22, Map.Entry<? extends K, ? extends V> e23, Map.Entry<? extends K, ? extends V> e24, Map.Entry<? extends K, ? extends V> e25, Map.Entry<? extends K, ? extends V> e26, Map.Entry<? extends K, ? extends V> e27, Map.Entry<? extends K, ? extends V> e28, Map.Entry<? extends K, ? extends V> e29, Map.Entry<? extends K, ? extends V> e30, Map.Entry<? extends K, ? extends V> e31, Map.Entry<? extends K, ? extends V> e32, Map.Entry<? extends K, ? extends V> e33, Map.Entry<? extends K, ? extends V> e34) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e33, e34));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31}, {@code e32}, {@code e33}, {@code e34} and {@code e35}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18, Map.Entry<? extends K, ? extends V> e19, Map.Entry<? extends K, ? extends V> e20, Map.Entry<? extends K, ? extends V> e21, Map.Entry<? extends K, ? extends V> e22, Map.Entry<? extends K, ? extends V> e23, Map.Entry<? extends K, ? extends V> e24, Map.Entry<? extends K, ? extends V> e25, Map.Entry<? extends K, ? extends V> e26, Map.Entry<? extends K, ? extends V> e27, Map.Entry<? extends K, ? extends V> e28, Map.Entry<? extends K, ? extends V> e29, Map.Entry<? extends K, ? extends V> e30, Map.Entry<? extends K, ? extends V> e31, Map.Entry<? extends K, ? extends V> e32, Map.Entry<? extends K, ? extends V> e33, Map.Entry<? extends K, ? extends V> e34, Map.Entry<? extends K, ? extends V> e35) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e33, e34, e35));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     * 
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31}, {@code e32}, {@code e33}, {@code e34}, {@code e35} and {@code e36}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18, Map.Entry<? extends K, ? extends V> e19, Map.Entry<? extends K, ? extends V> e20, Map.Entry<? extends K, ? extends V> e21, Map.Entry<? extends K, ? extends V> e22, Map.Entry<? extends K, ? extends V> e23, Map.Entry<? extends K, ? extends V> e24, Map.Entry<? extends K, ? extends V> e25, Map.Entry<? extends K, ? extends V> e26, Map.Entry<? extends K, ? extends V> e27, Map.Entry<? extends K, ? extends V> e28, Map.Entry<? extends K, ? extends V> e29, Map.Entry<? extends K, ? extends V> e30, Map.Entry<? extends K, ? extends V> e31, Map.Entry<? extends K, ? extends V> e32, Map.Entry<? extends K, ? extends V> e33, Map.Entry<? extends K, ? extends V> e34, Map.Entry<? extends K, ? extends V> e35, Map.Entry<? extends K, ? extends V> e36) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e33, e34, e35, e36));
    }

    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     *
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31}, {@code e32}, {@code e33}, {@code e34}, {@code e35}, {@code e36} and {@code e37}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18, Map.Entry<? extends K, ? extends V> e19, Map.Entry<? extends K, ? extends V> e20, Map.Entry<? extends K, ? extends V> e21, Map.Entry<? extends K, ? extends V> e22, Map.Entry<? extends K, ? extends V> e23, Map.Entry<? extends K, ? extends V> e24, Map.Entry<? extends K, ? extends V> e25, Map.Entry<? extends K, ? extends V> e26, Map.Entry<? extends K, ? extends V> e27, Map.Entry<? extends K, ? extends V> e28, Map.Entry<? extends K, ? extends V> e29, Map.Entry<? extends K, ? extends V> e30, Map.Entry<? extends K, ? extends V> e31, Map.Entry<? extends K, ? extends V> e32, Map.Entry<? extends K, ? extends V> e33, Map.Entry<? extends K, ? extends V> e34, Map.Entry<? extends K, ? extends V> e35, Map.Entry<? extends K, ? extends V> e36, Map.Entry<? extends K, ? extends V> e37) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e33, e34, e35, e36, e37));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     *
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31}, {@code e32}, {@code e33}, {@code e34}, {@code e35}, {@code e36}, {@code e37} and {@code e38}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18, Map.Entry<? extends K, ? extends V> e19, Map.Entry<? extends K, ? extends V> e20, Map.Entry<? extends K, ? extends V> e21, Map.Entry<? extends K, ? extends V> e22, Map.Entry<? extends K, ? extends V> e23, Map.Entry<? extends K, ? extends V> e24, Map.Entry<? extends K, ? extends V> e25, Map.Entry<? extends K, ? extends V> e26, Map.Entry<? extends K, ? extends V> e27, Map.Entry<? extends K, ? extends V> e28, Map.Entry<? extends K, ? extends V> e29, Map.Entry<? extends K, ? extends V> e30, Map.Entry<? extends K, ? extends V> e31, Map.Entry<? extends K, ? extends V> e32, Map.Entry<? extends K, ? extends V> e33, Map.Entry<? extends K, ? extends V> e34, Map.Entry<? extends K, ? extends V> e35, Map.Entry<? extends K, ? extends V> e36, Map.Entry<? extends K, ? extends V> e37, Map.Entry<? extends K, ? extends V> e38) {
        return newMap(SemiGroups.<V>fail(), Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e33, e34, e35, e36, e37, e38));
    }
    
    /**
     * <i>Unsafe!</i> Fails if given duplicate keys.
     *
     * @return some implementation of a Map containing elements {@code e1}, {@code e2}, {@code e3}, {@code e4}, {@code e5}, {@code e6}, {@code e7}, {@code e8}, {@code e9}, {@code e10}, {@code e11}, {@code e12}, {@code e13}, {@code e14}, {@code e15}, {@code e16}, {@code e17}, {@code e18}, {@code e19}, {@code e20}, {@code e21}, {@code e22}, {@code e23}, {@code e24}, {@code e25}, {@code e26}, {@code e27}, {@code e28}, {@code e29}, {@code e30}, {@code e31}, {@code e32}, {@code e33}, {@code e34}, {@code e35}, {@code e36}, {@code e37}, {@code e38}, {@code e39} and {@code elements}.
     */
    public static final <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2, Map.Entry<? extends K, ? extends V> e3, Map.Entry<? extends K, ? extends V> e4, Map.Entry<? extends K, ? extends V> e5, Map.Entry<? extends K, ? extends V> e6, Map.Entry<? extends K, ? extends V> e7, Map.Entry<? extends K, ? extends V> e8, Map.Entry<? extends K, ? extends V> e9, Map.Entry<? extends K, ? extends V> e10, Map.Entry<? extends K, ? extends V> e11, Map.Entry<? extends K, ? extends V> e12, Map.Entry<? extends K, ? extends V> e13, Map.Entry<? extends K, ? extends V> e14, Map.Entry<? extends K, ? extends V> e15, Map.Entry<? extends K, ? extends V> e16, Map.Entry<? extends K, ? extends V> e17, Map.Entry<? extends K, ? extends V> e18, Map.Entry<? extends K, ? extends V> e19, Map.Entry<? extends K, ? extends V> e20, Map.Entry<? extends K, ? extends V> e21, Map.Entry<? extends K, ? extends V> e22, Map.Entry<? extends K, ? extends V> e23, Map.Entry<? extends K, ? extends V> e24, Map.Entry<? extends K, ? extends V> e25, Map.Entry<? extends K, ? extends V> e26, Map.Entry<? extends K, ? extends V> e27, Map.Entry<? extends K, ? extends V> e28, Map.Entry<? extends K, ? extends V> e29, Map.Entry<? extends K, ? extends V> e30, Map.Entry<? extends K, ? extends V> e31, Map.Entry<? extends K, ? extends V> e32, Map.Entry<? extends K, ? extends V> e33, Map.Entry<? extends K, ? extends V> e34, Map.Entry<? extends K, ? extends V> e35, Map.Entry<? extends K, ? extends V> e36, Map.Entry<? extends K, ? extends V> e37, Map.Entry<? extends K, ? extends V> e38, Map.Entry<? extends K, ? extends V> e39, Map.Entry<? extends K, ? extends V>... elements) {
        return newMap(SemiGroups.<V>fail(), concat(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10, e11, e12, e13, e14, e15, e16, e17, e18, e19, e20, e21, e22, e23, e24, e25, e26, e27, e28, e29, e30, e31, e32, e33, e34, e35, e36, e37, e38, e39), elements));
    }
    
    /**
     * @return some implementation of a Map containing {@code elements}. One key can map to multiple values.
     */
    public static final <K, V> Map<K, List<V>> newMultimap(Iterable<? extends Map.Entry<? extends K, ? extends V>> elements) {
        if (elements == null) {
            return null;
        }
        if (elements instanceof ForceableIterable) {
            ((ForceableIterable) elements).completeIterationNeeded();
        }
        Map<K, List<V>> ret = null;
        for (long size: Iterables.resolveSize.apply(elements)) {
            ret = newMutableMapOfSize((int)(size*1.5));
        }
        if (ret == null) {
            ret = newMutableMap();
        }
        for (Map.Entry<? extends K, ? extends V> e: elements) {
            List<V> values = ret.get(e.getKey());
            if (values == null) {
                values = newMutableListOfSize(5);
                ret.put(e.getKey(), values);
            }
            values.add(e.getValue());
        }
        return java.util.Collections.unmodifiableMap(ret);
    }
    
    /**
     * @return a <b>mutable</b> array containing elements {@code array}.
     */
    public static final Boolean[] newArray(boolean... array) {
        if (array == null) {
            return null;
        }
        Boolean[] result = new Boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    /**
     * @return a <b>mutable</b> array containing elements {@code array}.
     */
    public static final Byte[] newArray(byte... array) {
        if (array == null) {
            return null;
        }
        Byte[] result = new Byte[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    /**
     * @return a <b>mutable</b> array containing elements {@code array}.
     */
    public static final Character[] newArray(char... array) {
        if (array == null) {
            return null;
        }
        Character[] result = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    /**
     * @return a <b>mutable</b> array containing elements {@code array}.
     */
    public static final Double[] newArray(double... array) {
        if (array == null) {
            return null;
        }
        Double[] result = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    /**
     * @return a <b>mutable</b> array containing elements {@code array}.
     */
    public static final Float[] newArray(float... array) {
        if (array == null) {
            return null;
        }
        Float[] result = new Float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * @return a <b>mutable</b> array containing elements {@code array}.
     */
    public static final Integer[] newArray(int... array) {
        if (array == null) {
            return null;
        }
        Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    /**
     * @return a <b>mutable</b> array containing elements {@code array}.
     */
    public static final Long[] newArray(long... array) {
        if (array == null) {
            return null;
        }
        Long[] result = new Long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    /**
     * @return a <b>mutable</b> array containing elements {@code array}.
     */
    public static final Short[] newArray(short... array) {
        if (array == null) {
            return null;
        }
        Short[] result = new Short[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    /**
     * @return a <b>mutable</b> array containing elements {@code array}.
     */
    public static final boolean[] newArray(Boolean[] array) {
        if (array == null) {
            return null;
        }
        boolean[] result = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    /**
     * @return a <b>mutable</b> array containing elements {@code array}.
     */
    public static final byte[] newArray(Byte[] array) {
        if (array == null) {
            return null;
        }
        byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    /**
     * @return a <b>mutable</b> array containing elements {@code array}.
     */
    public static final char[] newArray(Character[] array) {
        if (array == null) {
            return null;
        }
        char[] result = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    /**
     * @return a <b>mutable</b> array containing elements {@code array}.
     */
    public static final double[] newArray(Double[] array) {
        if (array == null) {
            return null;
        }
        double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    /**
     * @return a <b>mutable</b> array containing elements {@code array}.
     */
    public static final float[] newArray(Float[] array) {
        if (array == null) {
            return null;
        }
        float[] result = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * @return a <b>mutable</b> array containing elements {@code array}.
     */
    public static final int[] newArray(Integer[] array) {
        if (array == null) {
            return null;
        }
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    /**
     * @return a <b>mutable</b> array containing elements {@code array}.
     */
    public static final long[] newArray(Long[] array) {
        if (array == null) {
            return null;
        }
        long[] result = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    /**
     * @return a <b>mutable</b> array containing elements {@code array}.
     */
    public static final short[] newArray(Short[] array) {
        if (array == null) {
            return null;
        }
        short[] result = new short[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    /**
     * @return a <b>mutable</b> array containing elements {@code elements} of type {@code clazz}.
     */
    public static final <T> T[] newArray(Class<T> clazz, Iterable<? extends T> elements) {
        if (elements == null) {
            return null;
        }
        List<? extends T> list = newList(elements);
        @SuppressWarnings("unchecked")
        T[] ret = (T[])Array.newInstance(clazz, list.size());
        return list.toArray(ret);
    }
    
    /**
     * @return lazy iterable of characters in {@code charSeq}.
     */
    public static final Iterable<Character> it(CharSequence charSeq) {
        return charSeq == null ? null : new Iterables.CharSequenceIterable(charSeq);
    }
    
    /**
     * @return CharSequence of characters in {@code xs}.
     */
    public static final CharSequence it(Iterable<Character> xs) {
        return xs == null ? null : new Iterables.MemoizingCharSequenceIterable(xs);
    }
    
    /**
     * @return a lazy iterable for an iterable produced by {@code producer}.
     */
    public static final <T> Iterable<T> lazily(final ApplyZero<Iterable<T>> producer) {
        return new Iterable<T>() {
            public Iterator<T> iterator() {
                return producer.get().iterator();
            }
        };
    }
}
