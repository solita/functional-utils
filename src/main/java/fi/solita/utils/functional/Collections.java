package fi.solita.utils.functional;

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

    public static <T> List<T> newList() {
        return new ArrayList<T>();
    }
    
    public static List<Boolean> newList(boolean[] array) {
        Boolean[] result = new Boolean[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Boolean.valueOf(array[i]);
        }
        return Arrays.asList(result);
    }
    
    public static List<Byte> newList(byte[] array) {
        Byte[] result = new Byte[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Byte.valueOf(array[i]);
        }
        return Arrays.asList(result);
    }
    
    public static List<Character> newList(char[] array) {
        Character[] result = new Character[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Character.valueOf(array[i]);
        }
        return Arrays.asList(result);
    }
    
    public static List<Double> newList(double[] array) {
        Double[] result = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Double.valueOf(array[i]);
        }
        return Arrays.asList(result);
    }
    
    public static List<Float> newList(float[] array) {
        Float[] result = new Float[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Float.valueOf(array[i]);
        }
        return Arrays.asList(result);
    }

    public static List<Integer> newList(int[] array) {
        Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Integer.valueOf(array[i]);
        }
        return Arrays.asList(result);
    }
    
    public static List<Long> newList(long[] array) {
        Long[] result = new Long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Long.valueOf(array[i]);
        }
        return Arrays.asList(result);
    }
    
    public static List<Short> newList(short[] array) {
        Short[] result = new Short[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = Short.valueOf(array[i]);
        }
        return Arrays.asList(result);
    }

    public static <T> List<T> newListOfSize(int initialSize) {
        return new ArrayList<T>(initialSize);
    }

    public static <T> Set<T> newSet() {
        return new HashSet<T>();
    }

    public static <T> Set<T> newSetOfSize(int initialSize) {
        return new HashSet<T>(initialSize);
    }

    public static <K, V> Map<K, V> newMap() {
        return new HashMap<K, V>();
    }

    public static <K,V> Map<K,V> newMapOfSize(int initialSize) {
        return new HashMap<K,V>(initialSize);
    }

    public static <T> List<T> newList(Iterable<T> elements) {
    	List<T> ret = null;
    	for (int size: Iterables.resolveSize(elements)) {
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

    public static <T> List<T> newList(T... elements) {
        return newList(Arrays.asList(elements));
    }

    public static <T> Set<T> newSet(Iterable<T> elements) {
        Set<T> ret = null;
        for (int size: Iterables.resolveSize(elements)) {
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

    public static <T> Set<T> newSet(T... elements) {
        return newSet(Arrays.asList(elements));
    }

    public static <K, V> Map<K, V> newMap(Iterable<? extends Map.Entry<? extends K, ? extends V>> elements) {
        Map<K, V> ret = null;
        for (int size: Iterables.resolveSize(elements)) {
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

    public static <T> T[] newArray(Iterable<T> elements, Class<T> clazz) {
    	List<T> list = newList(elements);
    	@SuppressWarnings("unchecked")
    	T[] ret = (T[])Array.newInstance(clazz, list.size());
    	return list.toArray(ret);
    }
    
    public static Iterable<Character> it(CharSequence charSeq) {
        return new Iterables.CharSequenceIterable(charSeq);
    }
}
