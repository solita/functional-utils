package fi.solita.utils.functional;

import static fi.solita.utils.functional.FunctionalA.concat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Collections {
    
    public static <T> List<T> emptyList() {
        return java.util.Collections.emptyList();
    }
    
    public static <T> Set<T> emptySet() {
        return java.util.Collections.emptySet();
    }
    
    public static <K,V> Map<K,V> emptyMap() {
        return java.util.Collections.emptyMap();
    }

    public static <T> List<T> newList() {
        return new ArrayList<T>();
    }
    
    public static <T> Set<T> newSet() {
        return new HashSet<T>();
    }

    public static <K, V> Map<K, V> newMap() {
        return new HashMap<K, V>();
    }
    
    public static <T> List<T> newListOfSize(long initialSize) {
        return new ArrayList<T>((int)initialSize);
    }

    public static <T> Set<T> newSetOfSize(long initialSize) {
        return new HashSet<T>((int)initialSize);
    }

    public static <K,V> Map<K,V> newMapOfSize(long initialSize) {
        return new HashMap<K,V>((int)initialSize);
    }
    
    public static List<Boolean> newList(boolean[] array) {
        return newList(newArray(array));
    }
    
    public static List<Byte> newList(byte[] array) {
        return newList(newArray(array));
    }
    
    public static List<Character> newList(char[] array) {
        return newList(newArray(array));
    }
    
    public static List<Double> newList(double[] array) {
        return newList(newArray(array));
    }
    
    public static List<Float> newList(float[] array) {
        return newList(newArray(array));
    }

    public static List<Integer> newList(int[] array) {
        return newList(newArray(array));
    }
    
    public static List<Long> newList(long[] array) {
        return newList(newArray(array));
    }
    
    public static List<Short> newList(short[] array) {
        return newList(newArray(array));
    }
    
    public static <T> List<T> newList(T[] elements) {
        return newList(Arrays.asList(elements));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> List<T> newList(T e1) {
        return Arrays.asList(e1);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> List<T> newList(T e1, T e2) {
        return Arrays.asList(e1, e2);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> List<T> newList(T e1, T e2, T e3) {
        return Arrays.asList(e1, e2, e3);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> List<T> newList(T e1, T e2, T e3, T e4) {
        return Arrays.asList(e1, e2, e3, e4);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> List<T> newList(T e1, T e2, T e3, T e4, T e5) {
        return Arrays.asList(e1, e2, e3, e4, e5);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6) {
        return Arrays.asList(e1, e2, e3, e4, e5, e6);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> List<T> newList(T e1, T e2, T e3, T e4, T e5, T e6, T... elements) {
        return newList(concat(Arrays.asList(e1, e2, e3, e4, e5, e6), elements));
    }
    
    public static Set<Boolean> newSet(boolean[] array) {
        return newSet(newArray(array));
    }
    
    public static Set<Byte> newSet(byte[] array) {
        return newSet(newArray(array));
    }
    
    public static Set<Character> newSet(char[] array) {
        return newSet(newArray(array));
    }
    
    public static Set<Double> newSet(double[] array) {
        return newSet(newArray(array));
    }
    
    public static Set<Float> newSet(float[] array) {
        return newSet(newArray(array));
    }

    public static Set<Integer> newSet(int[] array) {
        return newSet(newArray(array));
    }
    
    public static Set<Long> newSet(long[] array) {
        return newSet(newArray(array));
    }
    
    public static Set<Short> newSet(short[] array) {
        return newSet(newArray(array));
    }
    
    public static <T> Set<T> newSet(T[] elements) {
        return newSet(Arrays.asList(elements));
    }
    
    public static <T> Set<T> newSet(T e1) {
        return java.util.Collections.singleton(e1);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Set<T> newSet(T e1, T e2) {
        return newSet(Arrays.asList(e1, e2));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Set<T> newSet(T e1, T e2, T e3) {
        return newSet(Arrays.asList(e1, e2, e3));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Set<T> newSet(T e1, T e2, T e3, T e4) {
        return newSet(Arrays.asList(e1, e2, e3, e4));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6) {
        return newSet(Arrays.asList(e1, e2, e3, e4, e5, e6));
    }
    
    @SuppressWarnings("unchecked")
    public static <T> Set<T> newSet(T e1, T e2, T e3, T e4, T e5, T e6, T... elements) {
        return newSet(concat(Arrays.asList(e1, e2, e3, e4, e5, e6), elements));
    }
    
    public static <T> List<T> newList(Iterable<T> elements) {
        List<T> ret = null;
        for (long size: Iterables.resolveSize.apply(elements)) {
            ret = newListOfSize(size);
        }
        if (ret == null) {
            ret = newList();
        }

        if (elements instanceof Collection) {
            ret.addAll((Collection<? extends T>) elements);
        } else {
            for (T t: elements) {
                ret.add(t);
            }
        }
        return java.util.Collections.unmodifiableList(ret);
    }

    public static <T> Set<T> newSet(Iterable<T> elements) {
        Set<T> ret = null;
        for (long size: Iterables.resolveSize.apply(elements)) {
            ret = newSetOfSize((int)(size*1.5));
        }
        if (ret == null) {
            ret = newSet();
        }
        if (elements instanceof Collection) {
            ret.addAll((Collection<? extends T>) elements);
        } else {
            for (T t: elements) {
                ret.add(t);
            }
        }
        return java.util.Collections.unmodifiableSet(ret);
    }

    public static <K, V> Map<K, V> newMap(Iterable<? extends Map.Entry<? extends K, ? extends V>> elements) {
        Map<K, V> ret = null;
        for (long size: Iterables.resolveSize.apply(elements)) {
            ret = newMapOfSize((int)(size*1.5));
        }
        if (ret == null) {
            ret = newMap();
        }
        for (Map.Entry<? extends K, ? extends V> e: elements) {
            ret.put(e.getKey(), e.getValue());
        }
        return java.util.Collections.unmodifiableMap(ret);
    }
    
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1) {
        return newMap(Arrays.asList(e1));
    }

    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V> e1, Map.Entry<? extends K, ? extends V> e2) {
        return newMap(Arrays.asList(e1, e2));
    }

    public static <K, V> Map<K, V> newMap(Map.Entry<? extends K, ? extends V>... elements) {
        return newMap(Arrays.asList(elements));
    }
    
    public static <K, V> Map<K, List<V>> newMultimap(Iterable<? extends Map.Entry<? extends K, ? extends V>> elements) {
        Map<K, List<V>> ret = null;
        for (long size: Iterables.resolveSize.apply(elements)) {
            ret = newMapOfSize((int)(size*1.5));
        }
        if (ret == null) {
            ret = newMap();
        }
        for (Map.Entry<? extends K, ? extends V> e: elements) {
            List<V> values = ret.get(e.getKey());
            if (values == null) {
                values = newList();
                ret.put(e.getKey(), values);
            }
            values.add(e.getValue());
        }
        return java.util.Collections.unmodifiableMap(ret);
    }
    
    public static Boolean[] newArray(boolean... array) {
        Boolean[] result = new Boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    public static Byte[] newArray(byte... array) {
        Byte[] result = new Byte[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    public static Character[] newArray(char... array) {
        Character[] result = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    public static Double[] newArray(double... array) {
        Double[] result = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    public static Float[] newArray(float... array) {
        Float[] result = new Float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    public static Integer[] newArray(int... array) {
        Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    public static Long[] newArray(long... array) {
        Long[] result = new Long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    public static Short[] newArray(short... array) {
        Short[] result = new Short[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    public static boolean[] newArray(Boolean[] array) {
        boolean[] result = new boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    public static byte[] newArray(Byte[] array) {
        byte[] result = new byte[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    public static char[] newArray(Character[] array) {
        char[] result = new char[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    public static double[] newArray(Double[] array) {
        double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    public static float[] newArray(Float[] array) {
        float[] result = new float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    public static int[] newArray(Integer[] array) {
        int[] result = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    public static long[] newArray(Long[] array) {
        long[] result = new long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    public static short[] newArray(Short[] array) {
        short[] result = new short[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }

    public static <T> T[] newArray(Class<T> clazz, Iterable<T> elements) {
        List<T> list = newList(elements);
        @SuppressWarnings("unchecked")
        T[] ret = (T[])Array.newInstance(clazz, list.size());
        return list.toArray(ret);
    }
    
    public static Iterable<Character> it(CharSequence charSeq) {
        return new Iterables.CharSequenceIterable(charSeq);
    }
    
    public static CharSequence it(Iterable<Character> xs) {
        return new Iterables.MemoizingCharSequenceIterable(xs);
    }
}
