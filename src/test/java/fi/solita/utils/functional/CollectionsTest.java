package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class CollectionsTest {

    @Test
    public void testEmptyList() {
        List<String> list = emptyList();
        assertNotNull(list);
        assertTrue(list.isEmpty());
        assertEquals(0, list.size());
    }

    @Test
    public void testEmptySet() {
        Set<String> set = emptySet();
        assertNotNull(set);
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
    }

    @Test
    public void testEmptySortedSet() {
        SortedSet<String> set = emptySortedSet();
        assertNotNull(set);
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
    }

    @Test
    public void testEmptyMap() {
        Map<String, Integer> map = emptyMap();
        assertNotNull(map);
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }

    @Test
    public void testEmptySortedMap() {
        SortedMap<String, Integer> map = emptySortedMap();
        assertNotNull(map);
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }

    @Test
    public void testEmptyCollection() {
        Collection<String> collection = emptyCollection();
        assertNotNull(collection);
        assertTrue(collection.isEmpty());
        assertEquals(0, collection.size());
    }

    @Test
    public void testEmptyQueue() {
        Queue<String> queue = emptyQueue();
        assertNotNull(queue);
        assertTrue(queue.isEmpty());
        assertEquals(0, queue.size());
    }

    @Test
    public void testEmptyDeque() {
        Deque<String> deque = emptyDeque();
        assertNotNull(deque);
        assertTrue(deque.isEmpty());
        assertEquals(0, deque.size());
    }

    @Test
    public void testNewMutableList() {
        List<String> list = newMutableList();
        assertNotNull(list);
        assertTrue(list.isEmpty());
        list.add("test");
        assertEquals(1, list.size());
        assertEquals("test", list.get(0));
    }

    @Test
    public void testNewMutableSet() {
        Set<String> set = newMutableSet();
        assertNotNull(set);
        assertTrue(set.isEmpty());
        set.add("test");
        assertEquals(1, set.size());
        assertTrue(set.contains("test"));
    }

    @Test
    public void testNewMutableSortedSet() {
        SortedSet<Integer> set = newMutableSortedSet();
        assertNotNull(set);
        assertTrue(set.isEmpty());
        set.add(3);
        set.add(1);
        set.add(2);
        assertEquals(3, set.size());
        assertEquals(Integer.valueOf(1), set.first());
        assertEquals(Integer.valueOf(3), set.last());
    }

    @Test
    public void testNewMutableSortedSetWithComparator() {
        Comparator<Integer> reverseOrder = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        };
        SortedSet<Integer> set = newMutableSortedSet(reverseOrder);
        assertNotNull(set);
        set.add(1);
        set.add(2);
        set.add(3);
        assertEquals(Integer.valueOf(3), set.first());
        assertEquals(Integer.valueOf(1), set.last());
    }

    @Test
    public void testNewMutableMap() {
        Map<String, Integer> map = newMutableMap();
        assertNotNull(map);
        assertTrue(map.isEmpty());
        map.put("test", 42);
        assertEquals(1, map.size());
        assertEquals(Integer.valueOf(42), map.get("test"));
    }

    @Test
    public void testNewMutableLinkedMap() {
        Map<String, Integer> map = newMutableLinkedMap();
        assertNotNull(map);
        assertTrue(map.isEmpty());
        map.put("first", 1);
        map.put("second", 2);
        map.put("third", 3);
        Iterator<String> keys = map.keySet().iterator();
        assertEquals("first", keys.next());
        assertEquals("second", keys.next());
        assertEquals("third", keys.next());
    }

    @Test
    public void testNewMutableSortedMap() {
        SortedMap<Integer, String> map = newMutableSortedMap();
        assertNotNull(map);
        assertTrue(map.isEmpty());
        map.put(3, "three");
        map.put(1, "one");
        map.put(2, "two");
        assertEquals(Integer.valueOf(1), map.firstKey());
        assertEquals(Integer.valueOf(3), map.lastKey());
    }

    @Test
    public void testNewMutableSortedMapWithComparator() {
        Comparator<Integer> reverseOrder = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        };
        SortedMap<Integer, String> map = newMutableSortedMap(reverseOrder);
        map.put(1, "one");
        map.put(2, "two");
        map.put(3, "three");
        assertEquals(Integer.valueOf(3), map.firstKey());
        assertEquals(Integer.valueOf(1), map.lastKey());
    }

    @Test
    public void testNewMutableQueue() {
        Queue<String> queue = newMutableQueue();
        assertNotNull(queue);
        assertTrue(queue.isEmpty());
        queue.offer("test");
        assertEquals(1, queue.size());
        assertEquals("test", queue.peek());
    }

    @Test
    public void testNewMutableDeque() {
        Deque<String> deque = newMutableDeque();
        assertNotNull(deque);
        assertTrue(deque.isEmpty());
        deque.addFirst("first");
        deque.addLast("last");
        assertEquals(2, deque.size());
        assertEquals("first", deque.peekFirst());
        assertEquals("last", deque.peekLast());
    }

    @Test
    public void testNewMutableCollection() {
        Collection<String> collection = newMutableCollection();
        assertNotNull(collection);
        assertTrue(collection.isEmpty());
        collection.add("test");
        assertEquals(1, collection.size());
    }

    @Test
    public void testNewMutableListOfSize() {
        List<String> list = newMutableListOfSize(100);
        assertNotNull(list);
        assertTrue(list.isEmpty());
        for (int i = 0; i < 100; i++) {
            list.add("item" + i);
        }
        assertEquals(100, list.size());
    }

    @Test
    public void testNewMutableSetOfSize() {
        Set<String> set = newMutableSetOfSize(100);
        assertNotNull(set);
        assertTrue(set.isEmpty());
        for (int i = 0; i < 100; i++) {
            set.add("item" + i);
        }
        assertEquals(100, set.size());
    }

    @Test
    public void testNewMutableMapOfSize() {
        Map<String, Integer> map = newMutableMapOfSize(100);
        assertNotNull(map);
        assertTrue(map.isEmpty());
        for (int i = 0; i < 100; i++) {
            map.put("key" + i, i);
        }
        assertEquals(100, map.size());
    }

    @Test
    public void testNewMutableLinkedMapOfSize() {
        Map<String, Integer> map = newMutableLinkedMapOfSize(100);
        assertNotNull(map);
        assertTrue(map.isEmpty());
        for (int i = 0; i < 100; i++) {
            map.put("key" + i, i);
        }
        assertEquals(100, map.size());
    }

    @Test
    public void testNewMutableCollectionOfSize() {
        Collection<String> collection = newMutableCollectionOfSize(100);
        assertNotNull(collection);
        assertTrue(collection.isEmpty());
        for (int i = 0; i < 100; i++) {
            collection.add("item" + i);
        }
        assertEquals(100, collection.size());
    }

    @Test
    public void testNewListFromBooleanArray() {
        boolean[] array = new boolean[]{true, false, true};
        List<Boolean> list = newList(array);
        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals(Boolean.TRUE, list.get(0));
        assertEquals(Boolean.FALSE, list.get(1));
        assertEquals(Boolean.TRUE, list.get(2));
    }

    @Test
    public void testNewListFromByteArray() {
        byte[] array = new byte[]{1, 2, 3};
        List<Byte> list = newList(array);
        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals(Byte.valueOf((byte)1), list.get(0));
        assertEquals(Byte.valueOf((byte)2), list.get(1));
        assertEquals(Byte.valueOf((byte)3), list.get(2));
    }

    @Test
    public void testNewListFromCharArray() {
        char[] array = new char[]{'a', 'b', 'c'};
        List<Character> list = newList(array);
        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals(Character.valueOf('a'), list.get(0));
        assertEquals(Character.valueOf('b'), list.get(1));
        assertEquals(Character.valueOf('c'), list.get(2));
    }

    @Test
    public void testNewListFromDoubleArray() {
        double[] array = new double[]{1.0, 2.0, 3.0};
        List<Double> list = newList(array);
        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals(Double.valueOf(1.0), list.get(0));
        assertEquals(Double.valueOf(2.0), list.get(1));
        assertEquals(Double.valueOf(3.0), list.get(2));
    }

    @Test
    public void testNewListFromFloatArray() {
        float[] array = new float[]{1.0f, 2.0f, 3.0f};
        List<Float> list = newList(array);
        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals(Float.valueOf(1.0f), list.get(0));
        assertEquals(Float.valueOf(2.0f), list.get(1));
        assertEquals(Float.valueOf(3.0f), list.get(2));
    }

    @Test
    public void testNewListFromIntArray() {
        int[] array = new int[]{1, 2, 3};
        List<Integer> list = newList(array);
        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(2), list.get(1));
        assertEquals(Integer.valueOf(3), list.get(2));
    }

    @Test
    public void testNewListFromLongArray() {
        long[] array = new long[]{1L, 2L, 3L};
        List<Long> list = newList(array);
        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals(Long.valueOf(1L), list.get(0));
        assertEquals(Long.valueOf(2L), list.get(1));
        assertEquals(Long.valueOf(3L), list.get(2));
    }

    @Test
    public void testNewListFromShortArray() {
        short[] array = new short[]{1, 2, 3};
        List<Short> list = newList(array);
        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals(Short.valueOf((short)1), list.get(0));
        assertEquals(Short.valueOf((short)2), list.get(1));
        assertEquals(Short.valueOf((short)3), list.get(2));
    }

    @Test
    public void testNewListFromObjectArray() {
        String[] array = new String[]{"a", "b", "c"};
        List<String> list = newList(array);
        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
    }

    @Test
    public void testNewListFromNullArray() {
        String[] array = null;
        List<String> list = newList(array);
        assertNull(list);
    }

    @Test
    public void testNewListFromEnumeration() {
        Vector<String> vector = new Vector<String>();
        vector.add("a");
        vector.add("b");
        vector.add("c");
        List<String> list = newList(vector.elements());
        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
    }

    @Test
    public void testNewListFromNullEnumeration() {
        Enumeration<String> enumeration = null;
        List<String> list = newList(enumeration);
        assertNull(list);
    }

    @Test
    public void testNewListWithOneElement() {
        List<String> list = newList("a");
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals("a", list.get(0));
    }

    @Test
    public void testNewListWithTwoElements() {
        List<String> list = newList("a", "b");
        assertNotNull(list);
        assertEquals(2, list.size());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
    }

    @Test
    public void testNewListWithThreeElements() {
        List<String> list = newList("a", "b", "c");
        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
    }

    @Test
    public void testNewListWithFourElements() {
        List<String> list = newList("a", "b", "c", "d");
        assertNotNull(list);
        assertEquals(4, list.size());
        assertEquals("a", list.get(0));
        assertEquals("d", list.get(3));
    }

    @Test
    public void testNewListWithFiveElements() {
        List<String> list = newList("a", "b", "c", "d", "e");
        assertNotNull(list);
        assertEquals(5, list.size());
        assertEquals("e", list.get(4));
    }

    @Test
    public void testNewSetFromIntArray() {
        int[] array = new int[]{1, 2, 3, 2, 1};
        Set<Integer> set = newSet(array);
        assertNotNull(set);
        assertEquals(3, set.size());
        assertTrue(set.contains(1));
        assertTrue(set.contains(2));
        assertTrue(set.contains(3));
    }

    @Test
    public void testNewSetFromObjectArray() {
        String[] array = new String[]{"a", "b", "c", "b", "a"};
        Set<String> set = newSet(array);
        assertNotNull(set);
        assertEquals(3, set.size());
        assertTrue(set.contains("a"));
        assertTrue(set.contains("b"));
        assertTrue(set.contains("c"));
    }

    @Test
    public void testNewSetWithOneElement() {
        Set<String> set = newSet("a");
        assertNotNull(set);
        assertEquals(1, set.size());
        assertTrue(set.contains("a"));
    }

    @Test
    public void testNewSetWithTwoElements() {
        Set<String> set = newSet("a", "b");
        assertNotNull(set);
        assertEquals(2, set.size());
        assertTrue(set.contains("a"));
        assertTrue(set.contains("b"));
    }

    @Test
    public void testNewSetWithThreeElements() {
        Set<String> set = newSet("a", "b", "c");
        assertNotNull(set);
        assertEquals(3, set.size());
        assertTrue(set.contains("a"));
        assertTrue(set.contains("b"));
        assertTrue(set.contains("c"));
    }

    @Test
    public void testNewSetWithDuplicates() {
        Set<String> set = newSet("a", "b", "a");
        assertNotNull(set);
        assertEquals(2, set.size());
        assertTrue(set.contains("a"));
        assertTrue(set.contains("b"));
    }

    @Test
    public void testNewMapWithSingleEntry() {
        Map<String, Integer> map = newMap(Pair.of("key", 42));
        assertNotNull(map);
        assertEquals(1, map.size());
        assertEquals(Integer.valueOf(42), map.get("key"));
    }

    @Test
    public void testNewMapWithTwoEntries() {
        Map<String, Integer> map = newMap(Pair.of("first", 1), Pair.of("second", 2));
        assertNotNull(map);
        assertEquals(2, map.size());
        assertEquals(Integer.valueOf(1), map.get("first"));
        assertEquals(Integer.valueOf(2), map.get("second"));
    }

    @Test
    public void testNewMapWithThreeEntries() {
        Map<String, Integer> map = newMap(Pair.of("first", 1), Pair.of("second", 2), Pair.of("third", 3));
        assertNotNull(map);
        assertEquals(3, map.size());
        assertEquals(Integer.valueOf(1), map.get("first"));
        assertEquals(Integer.valueOf(2), map.get("second"));
        assertEquals(Integer.valueOf(3), map.get("third"));
    }

    @Test
    public void testNewArrayFromBooleanArray() {
        boolean[] primitiveArray = new boolean[]{true, false, true};
        Boolean[] objectArray = newArray(primitiveArray);
        assertNotNull(objectArray);
        assertEquals(3, objectArray.length);
        assertEquals(Boolean.TRUE, objectArray[0]);
        assertEquals(Boolean.FALSE, objectArray[1]);
        assertEquals(Boolean.TRUE, objectArray[2]);
    }

    @Test
    public void testNewArrayFromIntArray() {
        int[] primitiveArray = new int[]{1, 2, 3};
        Integer[] objectArray = newArray(primitiveArray);
        assertNotNull(objectArray);
        assertEquals(3, objectArray.length);
        assertEquals(Integer.valueOf(1), objectArray[0]);
        assertEquals(Integer.valueOf(2), objectArray[1]);
        assertEquals(Integer.valueOf(3), objectArray[2]);
    }

    @Test
    public void testNewArrayFromObjectArray() {
        Integer[] objectArray = new Integer[]{1, 2, 3};
        int[] primitiveArray = newArray(objectArray);
        assertNotNull(primitiveArray);
        assertEquals(3, primitiveArray.length);
        assertEquals(1, primitiveArray[0]);
        assertEquals(2, primitiveArray[1]);
        assertEquals(3, primitiveArray[2]);
    }

    @Test
    public void testNewArrayFromVarargs() {
        Character[] array = newArray('a', 'b', 'c');
        assertNotNull(array);
        assertEquals(3, array.length);
        assertEquals(Character.valueOf('a'), array[0]);
        assertEquals(Character.valueOf('b'), array[1]);
        assertEquals(Character.valueOf('c'), array[2]);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testImmutableList() {
        List<String> list = newList("a", "b", "c");
        list.add("d");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testImmutableSet() {
        Set<String> set = newSet("a", "b", "c");
        set.add("d");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testImmutableMap() {
        Map<String, Integer> map = newMap(Pair.of("key", 42));
        map.put("another", 24);
    }

    @Test
    public void testListIsOrdered() {
        List<Integer> list = newList(3, 1, 2);
        assertEquals(Integer.valueOf(3), list.get(0));
        assertEquals(Integer.valueOf(1), list.get(1));
        assertEquals(Integer.valueOf(2), list.get(2));
    }

    @Test
    public void testEmptyListsAreEqual() {
        List<String> list1 = emptyList();
        List<String> list2 = emptyList();
        assertEquals(list1, list2);
    }

    @Test
    public void testEmptySetsAreEqual() {
        Set<String> set1 = emptySet();
        Set<String> set2 = emptySet();
        assertEquals(set1, set2);
    }

    @Test
    public void testEmptyMapsAreEqual() {
        Map<String, Integer> map1 = emptyMap();
        Map<String, Integer> map2 = emptyMap();
        assertEquals(map1, map2);
    }

    @Test
    public void testNewListFromIterable() {
        Iterable<String> iterable = Arrays.asList("a", "b", "c");
        List<String> list = newList(iterable);
        assertNotNull(list);
        assertEquals(3, list.size());
        assertEquals("a", list.get(0));
        assertEquals("b", list.get(1));
        assertEquals("c", list.get(2));
    }

    @Test
    public void testNewListFromNullIterable() {
        Iterable<String> iterable = null;
        List<String> list = newList(iterable);
        assertNull(list);
    }

    @Test
    public void testNewListFromEmptyIterable() {
        Iterable<String> iterable = Arrays.asList();
        List<String> list = newList(iterable);
        assertNotNull(list);
        assertTrue(list.isEmpty());
    }

    @Test
    public void testNewSetFromIterable() {
        Iterable<String> iterable = Arrays.asList("a", "b", "c", "b");
        Set<String> set = newSet(iterable);
        assertNotNull(set);
        assertEquals(3, set.size());
        assertTrue(set.contains("a"));
        assertTrue(set.contains("b"));
        assertTrue(set.contains("c"));
    }

    @Test
    public void testNewSetFromNullIterable() {
        Iterable<String> iterable = null;
        Set<String> set = newSet(iterable);
        assertNull(set);
    }

    @Test
    public void testNewSetFromEnumeration() {
        Vector<String> vector = new Vector<String>();
        vector.add("a");
        vector.add("b");
        vector.add("c");
        Set<String> set = newSet(vector.elements());
        assertNotNull(set);
        assertEquals(3, set.size());
        assertTrue(set.contains("a"));
        assertTrue(set.contains("b"));
        assertTrue(set.contains("c"));
    }

    @Test
    public void testNewSetFromNullEnumeration() {
        Enumeration<String> enumeration = null;
        Set<String> set = newSet(enumeration);
        assertNull(set);
    }

    @Test
    public void testNewMapFromIterable() {
        List<Pair<String, Integer>> entries = Arrays.asList(
            Pair.of("first", 1),
            Pair.of("second", 2),
            Pair.of("third", 3)
        );
        Map<String, Integer> map = newMap(SemiGroups.<Integer>fail(), entries);
        assertNotNull(map);
        assertEquals(3, map.size());
        assertEquals(Integer.valueOf(1), map.get("first"));
        assertEquals(Integer.valueOf(2), map.get("second"));
        assertEquals(Integer.valueOf(3), map.get("third"));
    }

    @Test
    public void testNewMapFromNullIterable() {
        Iterable<Map.Entry<String, Integer>> entries = null;
        Map<String, Integer> map = newMap(SemiGroups.<Integer>fail(), entries);
        assertNull(map);
    }

    @Test
    public void testNewMultimap() {
        List<Pair<String, Integer>> entries = Arrays.asList(
            Pair.of("a", 1),
            Pair.of("a", 2),
            Pair.of("b", 3),
            Pair.of("a", 4)
        );
        Map<String, List<Integer>> multimap = newMultimap(entries);
        assertNotNull(multimap);
        assertEquals(2, multimap.size());
        assertEquals(3, multimap.get("a").size());
        assertEquals(1, multimap.get("b").size());
        assertTrue(multimap.get("a").contains(1));
        assertTrue(multimap.get("a").contains(2));
        assertTrue(multimap.get("a").contains(4));
        assertTrue(multimap.get("b").contains(3));
    }

    @Test
    public void testNewMultimapFromNull() {
        Iterable<Map.Entry<String, Integer>> entries = null;
        Map<String, List<Integer>> multimap = newMultimap(entries);
        assertNull(multimap);
    }

    @Test
    public void testItFromCharSequence() {
        Iterable<Character> iterable = it("abc");
        assertNotNull(iterable);
        Iterator<Character> iterator = iterable.iterator();
        assertEquals(Character.valueOf('a'), iterator.next());
        assertEquals(Character.valueOf('b'), iterator.next());
        assertEquals(Character.valueOf('c'), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testItFromNullCharSequence() {
        CharSequence charSeq = null;
        Iterable<Character> iterable = it(charSeq);
        assertNull(iterable);
    }

    @Test
    public void testItFromSingleValue() {
        List<String> iterable = newList("test");
        assertNotNull(iterable);
        Iterator<String> iterator = iterable.iterator();
        assertEquals("test", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testItFromNullValue() {
        CharSequence value = null;
        Iterable<Character> iterable = it(value);
        assertNull(iterable);
    }

    @Test
    public void testNewArrayFromByteArrayConversion() {
        byte[] primitiveArray = new byte[]{1, 2, 3};
        Byte[] objectArray = newArray(primitiveArray);
        assertNotNull(objectArray);
        assertEquals(3, objectArray.length);
        assertEquals(Byte.valueOf((byte)1), objectArray[0]);
        
        byte[] convertedBack = newArray(objectArray);
        assertNotNull(convertedBack);
        assertEquals(3, convertedBack.length);
        assertEquals(1, convertedBack[0]);
    }

    @Test
    public void testNewArrayFromCharArrayConversion() {
        char[] primitiveArray = new char[]{'a', 'b', 'c'};
        Character[] objectArray = newArray(primitiveArray);
        assertNotNull(objectArray);
        assertEquals(3, objectArray.length);
        
        char[] convertedBack = newArray(objectArray);
        assertNotNull(convertedBack);
        assertEquals(3, convertedBack.length);
        assertEquals('a', convertedBack[0]);
    }

    @Test
    public void testNewArrayFromDoubleArrayConversion() {
        double[] primitiveArray = new double[]{1.0, 2.0, 3.0};
        Double[] objectArray = newArray(primitiveArray);
        assertNotNull(objectArray);
        assertEquals(3, objectArray.length);
        
        double[] convertedBack = newArray(objectArray);
        assertNotNull(convertedBack);
        assertEquals(3, convertedBack.length);
        assertEquals(1.0, convertedBack[0], 0.0001);
    }

    @Test
    public void testNewArrayFromFloatArrayConversion() {
        float[] primitiveArray = new float[]{1.0f, 2.0f, 3.0f};
        Float[] objectArray = newArray(primitiveArray);
        assertNotNull(objectArray);
        assertEquals(3, objectArray.length);
        
        float[] convertedBack = newArray(objectArray);
        assertNotNull(convertedBack);
        assertEquals(3, convertedBack.length);
        assertTrue(Math.abs(1.0f - convertedBack[0]) < 0.0001f);
    }

    @Test
    public void testNewArrayFromLongArrayConversion() {
        long[] primitiveArray = new long[]{1L, 2L, 3L};
        Long[] objectArray = newArray(primitiveArray);
        assertNotNull(objectArray);
        assertEquals(3, objectArray.length);
        
        long[] convertedBack = newArray(objectArray);
        assertNotNull(convertedBack);
        assertEquals(3, convertedBack.length);
        assertEquals(1L, convertedBack[0]);
    }

    @Test
    public void testNewArrayFromShortArrayConversion() {
        short[] primitiveArray = new short[]{1, 2, 3};
        Short[] objectArray = newArray(primitiveArray);
        assertNotNull(objectArray);
        assertEquals(3, objectArray.length);
        
        short[] convertedBack = newArray(objectArray);
        assertNotNull(convertedBack);
        assertEquals(3, convertedBack.length);
        assertEquals(1, convertedBack[0]);
    }

    @Test
    public void testNewArrayFromBooleanArrayConversion() {
        boolean[] primitiveArray = new boolean[]{true, false, true};
        Boolean[] objectArray = newArray(primitiveArray);
        assertNotNull(objectArray);
        assertEquals(3, objectArray.length);
        
        boolean[] convertedBack = newArray(objectArray);
        assertNotNull(convertedBack);
        assertEquals(3, convertedBack.length);
        assertEquals(true, convertedBack[0]);
        assertEquals(false, convertedBack[1]);
    }

    @Test
    public void testNewArrayFromNullPrimitiveArray() {
        int[] primitiveArray = null;
        Integer[] objectArray = newArray(primitiveArray);
        assertNull(objectArray);
    }

    @Test
    public void testNewArrayFromNullObjectArray() {
        Integer[] objectArray = null;
        int[] primitiveArray = newArray(objectArray);
        assertNull(primitiveArray);
    }

    @Test
    public void testNewArrayWithClass() {
        List<String> list = Arrays.asList("a", "b", "c");
        String[] array = newArray(String.class, list);
        assertNotNull(array);
        assertEquals(3, array.length);
        assertEquals("a", array[0]);
        assertEquals("b", array[1]);
        assertEquals("c", array[2]);
    }

    @Test
    public void testNewArrayWithClassFromNull() {
        Iterable<String> iterable = null;
        String[] array = newArray(String.class, iterable);
        assertNull(array);
    }

    @Test
    public void testNewSortedSet() {
        Iterable<Integer> iterable = Arrays.asList(3, 1, 2);
        SortedSet<Integer> set = newSortedSet(iterable);
        assertNotNull(set);
        assertEquals(3, set.size());
        assertEquals(Integer.valueOf(1), set.first());
        assertEquals(Integer.valueOf(3), set.last());
    }

    @Test
    public void testNewSortedSetWithComparator() {
        Comparator<Integer> reverseOrder = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        };
        Iterable<Integer> iterable = Arrays.asList(1, 2, 3);
        SortedSet<Integer> set = newSortedSet(reverseOrder, iterable);
        assertNotNull(set);
        assertEquals(3, set.size());
        assertEquals(Integer.valueOf(3), set.first());
        assertEquals(Integer.valueOf(1), set.last());
    }

    @Test
    public void testNewSortedSetFromNull() {
        Iterable<Integer> iterable = null;
        SortedSet<Integer> set = newSortedSet(iterable);
        assertNull(set);
    }

    @Test
    public void testNewSetFromBooleanArray() {
        boolean[] array = new boolean[]{true, false, true};
        Set<Boolean> set = newSet(array);
        assertNotNull(set);
        assertEquals(2, set.size());
        assertTrue(set.contains(true));
        assertTrue(set.contains(false));
    }

    @Test
    public void testNewSetFromByteArray() {
        byte[] array = new byte[]{1, 2, 3, 2};
        Set<Byte> set = newSet(array);
        assertNotNull(set);
        assertEquals(3, set.size());
    }

    @Test
    public void testNewSetFromCharArray() {
        char[] array = new char[]{'a', 'b', 'c', 'a'};
        Set<Character> set = newSet(array);
        assertNotNull(set);
        assertEquals(3, set.size());
    }

    @Test
    public void testNewSetFromDoubleArray() {
        double[] array = new double[]{1.0, 2.0, 3.0, 1.0};
        Set<Double> set = newSet(array);
        assertNotNull(set);
        assertEquals(3, set.size());
    }

    @Test
    public void testNewSetFromFloatArray() {
        float[] array = new float[]{1.0f, 2.0f, 3.0f, 1.0f};
        Set<Float> set = newSet(array);
        assertNotNull(set);
        assertEquals(3, set.size());
    }

    @Test
    public void testNewSetFromLongArray() {
        long[] array = new long[]{1L, 2L, 3L, 1L};
        Set<Long> set = newSet(array);
        assertNotNull(set);
        assertEquals(3, set.size());
    }

    @Test
    public void testNewSetFromShortArray() {
        short[] array = new short[]{1, 2, 3, 1};
        Set<Short> set = newSet(array);
        assertNotNull(set);
        assertEquals(3, set.size());
    }

    @Test
    public void testNewSetFromNullObjectArray() {
        String[] array = null;
        Set<String> set = newSet(array);
        assertNull(set);
    }

    @Test
    public void testNewListWithSixElements() {
        List<String> list = newList("a", "b", "c", "d", "e", "f");
        assertNotNull(list);
        assertEquals(6, list.size());
        assertEquals("f", list.get(5));
    }

    @Test
    public void testNewSetWithFourElements() {
        Set<String> set = newSet("a", "b", "c", "d");
        assertNotNull(set);
        assertEquals(4, set.size());
        assertTrue(set.contains("d"));
    }

    @Test
    public void testNewSetWithFiveElements() {
        Set<String> set = newSet("a", "b", "c", "d", "e");
        assertNotNull(set);
        assertEquals(5, set.size());
        assertTrue(set.contains("e"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testImmutableMapFromIterable() {
        List<Pair<String, Integer>> entries = Arrays.asList(Pair.of("key", 42));
        Map<String, Integer> map = newMap(SemiGroups.<Integer>fail(), entries);
        map.put("another", 24);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testImmutableMultimap() {
        List<Pair<String, Integer>> entries = Arrays.asList(Pair.of("key", 42));
        Map<String, List<Integer>> multimap = newMultimap(entries);
        multimap.put("another", newList(24));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testImmutableListFromIterable() {
        Iterable<String> iterable = Arrays.asList("a", "b", "c");
        List<String> list = newList(iterable);
        list.add("d");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testImmutableSetFromIterable() {
        Iterable<String> iterable = Arrays.asList("a", "b", "c");
        Set<String> set = newSet(iterable);
        set.add("d");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testImmutableSortedSet() {
        Iterable<Integer> iterable = Arrays.asList(1, 2, 3);
        SortedSet<Integer> set = newSortedSet(iterable);
        set.add(4);
    }

    @Test
    public void testNewMapWithValueFunction() {
        Set<String> keys = newSet("a", "b", "c");
        Map<String, Integer> map = newMap(new Apply<String, Integer>() {
            public Integer apply(String t) {
                return t.length();
            }
        }, keys);
        assertNotNull(map);
        assertEquals(3, map.size());
        assertEquals(Integer.valueOf(1), map.get("a"));
        assertEquals(Integer.valueOf(1), map.get("b"));
        assertEquals(Integer.valueOf(1), map.get("c"));
    }

    @Test
    public void testNewLinkedMapWithValueCombiner() {
        List<Pair<String, Integer>> entries = Arrays.asList(
            Pair.of("a", 1),
            Pair.of("b", 2),
            Pair.of("a", 3)
        );
        Map<String, Integer> map = newLinkedMap(SemiGroups.<Integer>first(), entries);
        assertNotNull(map);
        assertEquals(2, map.size());
        assertEquals(Integer.valueOf(1), map.get("a")); // first value wins
        assertEquals(Integer.valueOf(2), map.get("b"));
        
        // Check insertion order is preserved
        Iterator<String> keys = map.keySet().iterator();
        assertEquals("a", keys.next());
        assertEquals("b", keys.next());
    }

    @Test
    public void testNewLinkedMapWithLastCombiner() {
        List<Pair<String, Integer>> entries = Arrays.asList(
            Pair.of("a", 1),
            Pair.of("b", 2),
            Pair.of("a", 3)
        );
        Map<String, Integer> map = newLinkedMap(SemiGroups.<Integer>last(), entries);
        assertNotNull(map);
        assertEquals(2, map.size());
        assertEquals(Integer.valueOf(3), map.get("a")); // last value wins
        assertEquals(Integer.valueOf(2), map.get("b"));
    }

    @Test
    public void testNewLinkedMapFromNull() {
        Iterable<Map.Entry<String, Integer>> entries = null;
        Map<String, Integer> map = newLinkedMap(SemiGroups.<Integer>fail(), entries);
        assertNull(map);
    }

    @Test
    public void testNewSortedMapWithValueFunction() {
        Set<String> keys = newSet("a", "bb", "ccc");
        SortedMap<String, Integer> map = newSortedMap(new Apply<String, Integer>() {
            public Integer apply(String t) {
                return t.length();
            }
        }, keys);
        assertNotNull(map);
        assertEquals(3, map.size());
        assertEquals(Integer.valueOf(1), map.get("a"));
        assertEquals(Integer.valueOf(2), map.get("bb"));
        assertEquals(Integer.valueOf(3), map.get("ccc"));
        assertEquals("a", map.firstKey());
        assertEquals("ccc", map.lastKey());
    }

    @Test
    public void testNewSortedMapWithComparatorAndValueFunction() {
        Comparator<String> reverseOrder = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        };
        Set<String> keys = newSet("a", "b", "c");
        SortedMap<String, Integer> map = newSortedMap(reverseOrder, new Apply<String, Integer>() {
            public Integer apply(String t) {
                return t.length();
            }
        }, keys);
        assertNotNull(map);
        assertEquals(3, map.size());
        assertEquals("c", map.firstKey());
        assertEquals("a", map.lastKey());
    }

    @Test
    public void testNewSortedMapFromIterable() {
        List<Pair<Integer, String>> entries = Arrays.asList(
            Pair.of(3, "three"),
            Pair.of(1, "one"),
            Pair.of(2, "two")
        );
        SortedMap<Integer, String> map = newSortedMap(SemiGroups.<String>fail(), entries);
        assertNotNull(map);
        assertEquals(3, map.size());
        assertEquals(Integer.valueOf(1), map.firstKey());
        assertEquals(Integer.valueOf(3), map.lastKey());
        assertEquals("one", map.get(1));
        assertEquals("two", map.get(2));
        assertEquals("three", map.get(3));
    }

    @Test
    public void testNewSortedMapWithComparatorFromIterable() {
        Comparator<Integer> reverseOrder = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        };
        List<Pair<Integer, String>> entries = Arrays.asList(
            Pair.of(1, "one"),
            Pair.of(2, "two"),
            Pair.of(3, "three")
        );
        SortedMap<Integer, String> map = newSortedMap(SemiGroups.<String>fail(), reverseOrder, entries);
        assertNotNull(map);
        assertEquals(3, map.size());
        assertEquals(Integer.valueOf(3), map.firstKey());
        assertEquals(Integer.valueOf(1), map.lastKey());
    }

    @Test
    public void testNewSortedMapWithFirstCombiner() {
        List<Pair<Integer, String>> entries = Arrays.asList(
            Pair.of(1, "one"),
            Pair.of(2, "two"),
            Pair.of(1, "ONE")
        );
        SortedMap<Integer, String> map = newSortedMap(SemiGroups.<String>first(), entries);
        assertNotNull(map);
        assertEquals(2, map.size());
        assertEquals("one", map.get(1)); // first value wins
    }

    @Test
    public void testNewSortedMapWithLastCombiner() {
        List<Pair<Integer, String>> entries = Arrays.asList(
            Pair.of(1, "one"),
            Pair.of(2, "two"),
            Pair.of(1, "ONE")
        );
        SortedMap<Integer, String> map = newSortedMap(SemiGroups.<String>last(), entries);
        assertNotNull(map);
        assertEquals(2, map.size());
        assertEquals("ONE", map.get(1)); // last value wins
    }

    @Test
    public void testNewSortedMapFromNull() {
        Iterable<Map.Entry<Integer, String>> entries = null;
        SortedMap<Integer, String> map = newSortedMap(SemiGroups.<String>fail(), entries);
        assertNull(map);
    }

    @Test
    public void testNewSortedMapWithComparatorFromNull() {
        Comparator<Integer> comparator = Ordering.Natural();
        Iterable<Map.Entry<Integer, String>> entries = null;
        SortedMap<Integer, String> map = newSortedMap(SemiGroups.<String>fail(), comparator, entries);
        assertNull(map);
    }

    @Test
    public void testNewMapWithFourEntries() {
        Map<String, Integer> map = newMap(
            Pair.of("first", 1),
            Pair.of("second", 2),
            Pair.of("third", 3),
            Pair.of("fourth", 4)
        );
        assertNotNull(map);
        assertEquals(4, map.size());
        assertEquals(Integer.valueOf(4), map.get("fourth"));
    }

    @Test
    public void testNewMapWithFiveEntries() {
        Map<String, Integer> map = newMap(
            Pair.of("first", 1),
            Pair.of("second", 2),
            Pair.of("third", 3),
            Pair.of("fourth", 4),
            Pair.of("fifth", 5)
        );
        assertNotNull(map);
        assertEquals(5, map.size());
        assertEquals(Integer.valueOf(5), map.get("fifth"));
    }

    @Test
    public void testNewMapWithSixEntries() {
        Map<String, Integer> map = newMap(
            Pair.of("a", 1),
            Pair.of("b", 2),
            Pair.of("c", 3),
            Pair.of("d", 4),
            Pair.of("e", 5),
            Pair.of("f", 6)
        );
        assertNotNull(map);
        assertEquals(6, map.size());
        assertEquals(Integer.valueOf(6), map.get("f"));
    }

    @Test
    public void testItFromIterableOfCharacters() {
        Iterable<Character> chars = Arrays.asList('a', 'b', 'c');
        CharSequence seq = Collections.it(chars);
        assertNotNull(seq);
        assertEquals(3, seq.length());
        assertEquals('a', seq.charAt(0));
        assertEquals('b', seq.charAt(1));
        assertEquals('c', seq.charAt(2));
        assertEquals("abc", seq.toString());
    }

    @Test
    public void testItFromIterableOfCharactersNull() {
        Iterable<Character> chars = null;
        CharSequence seq = Collections.it(chars);
        assertNull(seq);
    }

    @Test
    public void testItFromSingleValueString() {
        String value = "test";
        Iterable<Character> iterable = Collections.it(value);
        assertNotNull(iterable);
        Iterator<Character> iterator = iterable.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Character.valueOf('t'), iterator.next());
        assertTrue(iterator.hasNext());
    }

    @Test
    public void testItFromSingleValueInteger() {
        Integer value = 42;
        Iterable<Integer> iterable = Collections.<Integer>it(value);
        assertNotNull(iterable);
        Iterator<Integer> iterator = iterable.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(Integer.valueOf(42), iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testItFromNullValueReturnsNull() {
        String value = null;
        Iterable<Character> iterable = Collections.it(value);
        assertNull(iterable);
    }

    @Test
    public void testLazily() {
        final List<String> list = newMutableList();
        list.add("a");
        list.add("b");
        
        Iterable<String> lazy = lazily(new ApplyZero<Iterable<String>>() {
            public Iterable<String> get() {
                return list;
            }
        });
        
        assertNotNull(lazy);
        
        // Modify the list before iteration
        list.add("c");
        
        // The lazy iterable should see the modified list
        Iterator<String> iterator = lazy.iterator();
        assertEquals("a", iterator.next());
        assertEquals("b", iterator.next());
        assertEquals("c", iterator.next());
        assertFalse(iterator.hasNext());
    }

    @Test
    public void testLazilyMultipleIterations() {
        final int[] counter = new int[]{0};
        
        Iterable<Integer> lazy = lazily(new ApplyZero<Iterable<Integer>>() {
            public Iterable<Integer> get() {
                counter[0]++;
                return Arrays.asList(1, 2, 3);
            }
        });
        
        // First iteration
        Iterator<Integer> iter1 = lazy.iterator();
        assertEquals(1, counter[0]);
        assertEquals(Integer.valueOf(1), iter1.next());
        
        // Second iteration - should call producer again
        Iterator<Integer> iter2 = lazy.iterator();
        assertEquals(2, counter[0]);
        assertEquals(Integer.valueOf(1), iter2.next());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testImmutableSortedSetWithComparator() {
        Comparator<Integer> comparator = Ordering.Natural();
        Iterable<Integer> iterable = Arrays.asList(1, 2, 3);
        SortedSet<Integer> set = newSortedSet(comparator, iterable);
        set.add(4);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testImmutableSortedMap() {
        List<Pair<Integer, String>> entries = Arrays.asList(Pair.of(1, "one"));
        SortedMap<Integer, String> map = newSortedMap(SemiGroups.<String>fail(), entries);
        map.put(2, "two");
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testImmutableLinkedMap() {
        List<Pair<String, Integer>> entries = Arrays.asList(Pair.of("key", 42));
        Map<String, Integer> map = newLinkedMap(SemiGroups.<Integer>fail(), entries);
        map.put("another", 24);
    }

    @Test
    public void testNewMapWithValueFunctionAndSingletonSet() {
        Set<String> keys = newSet("a");
        Map<String, Integer> map = newMap(new Apply<String, Integer>() {
            public Integer apply(String t) {
                return t.length();
            }
        }, keys);
        assertNotNull(map);
        assertEquals(1, map.size());
        assertEquals(Integer.valueOf(1), map.get("a"));
    }

    @Test
    public void testNewSortedMapFromEmptyIterable() {
        List<Map.Entry<Integer, String>> entries = Arrays.asList();
        SortedMap<Integer, String> map = newSortedMap(SemiGroups.<String>fail(), entries);
        assertNotNull(map);
        assertTrue(map.isEmpty());
    }

    @Test
    public void testNewLinkedMapFromEmptyIterable() {
        List<Map.Entry<String, Integer>> entries = Arrays.asList();
        Map<String, Integer> map = newLinkedMap(SemiGroups.<Integer>fail(), entries);
        assertNotNull(map);
        assertTrue(map.isEmpty());
    }

    @Test
    public void testNewSortedMapWithComparatorFromEmptyIterable() {
        Comparator<Integer> comparator = Ordering.Natural();
        List<Map.Entry<Integer, String>> entries = Arrays.asList();
        SortedMap<Integer, String> map = newSortedMap(SemiGroups.<String>fail(), comparator, entries);
        assertNotNull(map);
        assertTrue(map.isEmpty());
    }

    @Test
    public void testCharSequenceIterableRoundTrip() {
        String original = "hello";
        Iterable<Character> chars = it(original);
        CharSequence rebuilt = Collections.it(chars);
        assertEquals(original, rebuilt.toString());
        assertEquals(original.length(), rebuilt.length());
        for (int i = 0; i < original.length(); i++) {
            assertEquals(original.charAt(i), rebuilt.charAt(i));
        }
    }

    @Test
    public void testNewSortedSetWithSingleElement() {
        Iterable<Integer> iterable = Arrays.asList(42);
        SortedSet<Integer> set = newSortedSet(iterable);
        assertNotNull(set);
        assertEquals(1, set.size());
        assertEquals(Integer.valueOf(42), set.first());
        assertEquals(Integer.valueOf(42), set.last());
    }

    @Test
    public void testNewSortedSetWithComparatorAndSingleElement() {
        Comparator<Integer> comparator = Ordering.Natural();
        Iterable<Integer> iterable = Arrays.asList(42);
        SortedSet<Integer> set = newSortedSet(comparator, iterable);
        assertNotNull(set);
        assertEquals(1, set.size());
        assertEquals(Integer.valueOf(42), set.first());
    }
}
