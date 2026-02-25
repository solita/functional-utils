package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.*;
import static fi.solita.utils.functional.SemiGroups.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class SemiGroupsTest {

    @Test
    public void testIntSum() {
        assertEquals(Integer.valueOf(5), intSum.apply(Pair.of(2, 3)));
        assertEquals(Integer.valueOf(0), intSum.apply(Pair.of(-5, 5)));
        assertEquals(Integer.valueOf(100), intSum.apply(Pair.of(50, 50)));
    }

    @Test
    public void testIntProduct() {
        assertEquals(Integer.valueOf(6), intProduct.apply(Pair.of(2, 3)));
        assertEquals(Integer.valueOf(0), intProduct.apply(Pair.of(0, 5)));
        assertEquals(Integer.valueOf(100), intProduct.apply(Pair.of(10, 10)));
    }

    @Test
    public void testLongSum() {
        assertEquals(Long.valueOf(5L), longSum.apply(Pair.of(2L, 3L)));
        assertEquals(Long.valueOf(0L), longSum.apply(Pair.of(-5L, 5L)));
        assertEquals(Long.valueOf(1000000000000L), longSum.apply(Pair.of(500000000000L, 500000000000L)));
    }

    @Test
    public void testLongProduct() {
        assertEquals(Long.valueOf(6L), longProduct.apply(Pair.of(2L, 3L)));
        assertEquals(Long.valueOf(0L), longProduct.apply(Pair.of(0L, 5L)));
        assertEquals(Long.valueOf(100L), longProduct.apply(Pair.of(10L, 10L)));
    }

    @Test
    public void testDoubleSum() {
        assertEquals(Double.valueOf(5.5), doubleSum.apply(Pair.of(2.2, 3.3)), 0.001);
        assertEquals(Double.valueOf(0.0), doubleSum.apply(Pair.of(-5.5, 5.5)), 0.001);
    }

    @Test
    public void testDoubleProduct() {
        assertEquals(Double.valueOf(6.0), doubleProduct.apply(Pair.of(2.0, 3.0)), 0.001);
        assertEquals(Double.valueOf(0.0), doubleProduct.apply(Pair.of(0.0, 5.0)), 0.001);
    }

    @Test
    public void testStringConcat() {
        assertEquals("hello world", stringConcat.apply(Pair.of("hello ", "world")));
        assertEquals("foobar", stringConcat.apply(Pair.of("foo", "bar")));
        assertEquals("test", stringConcat.apply(Pair.of("", "test")));
        assertEquals("test", stringConcat.apply(Pair.of("test", "")));
    }

    @Test
    public void testBooleanConjunction() {
        assertEquals(Boolean.TRUE, booleanConjunction.apply(Pair.of(true, true)));
        assertEquals(Boolean.FALSE, booleanConjunction.apply(Pair.of(true, false)));
        assertEquals(Boolean.FALSE, booleanConjunction.apply(Pair.of(false, true)));
        assertEquals(Boolean.FALSE, booleanConjunction.apply(Pair.of(false, false)));
    }

    @Test
    public void testBooleanDisjunction() {
        assertEquals(Boolean.TRUE, booleanDisjunction.apply(Pair.of(true, true)));
        assertEquals(Boolean.TRUE, booleanDisjunction.apply(Pair.of(true, false)));
        assertEquals(Boolean.TRUE, booleanDisjunction.apply(Pair.of(false, true)));
        assertEquals(Boolean.FALSE, booleanDisjunction.apply(Pair.of(false, false)));
    }

    @Test
    public void testFirst() {
        SemiGroup<String> first = first();
        assertEquals("first", first.apply(Pair.of("first", "second")));
        assertEquals("a", first.apply(Pair.of("a", "b")));
    }

    @Test
    public void testLast() {
        SemiGroup<String> last = last();
        assertEquals("second", last.apply(Pair.of("first", "second")));
        assertEquals("b", last.apply(Pair.of("a", "b")));
    }

    @Test(expected = RuntimeException.class)
    public void testFail() {
        SemiGroup<String> fail = SemiGroups.<String>fail();
        fail.apply(Pair.of("a", "b"));
    }

    @Test
    public void testFailUnequal_equal() {
        SemiGroup<String> failUnequal = failUnequal();
        assertEquals("test", failUnequal.apply(Pair.of("test", "test")));
    }

    @Test(expected = RuntimeException.class)
    public void testFailUnequal_notEqual() {
        SemiGroup<String> failUnequal = failUnequal();
        failUnequal.apply(Pair.of("a", "b"));
    }

    @Test
    public void testMax() {
        SemiGroup<Integer> max = max();
        assertEquals(Integer.valueOf(5), max.apply(Pair.of(3, 5)));
        assertEquals(Integer.valueOf(10), max.apply(Pair.of(10, 2)));
        assertEquals(Integer.valueOf(7), max.apply(Pair.of(7, 7)));
    }

    @Test
    public void testMin() {
        SemiGroup<Integer> min = min();
        assertEquals(Integer.valueOf(3), min.apply(Pair.of(3, 5)));
        assertEquals(Integer.valueOf(2), min.apply(Pair.of(10, 2)));
        assertEquals(Integer.valueOf(7), min.apply(Pair.of(7, 7)));
    }

    @Test
    public void testMaxBy() {
        Comparator<String> lengthComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        };
        SemiGroup<String> maxByLength = maxBy(lengthComparator);
        assertEquals("longer", maxByLength.apply(Pair.of("short", "longer")));
        assertEquals("longer", maxByLength.apply(Pair.of("longer", "short")));
    }

    @Test
    public void testMinBy() {
        Comparator<String> lengthComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        };
        SemiGroup<String> minByLength = minBy(lengthComparator);
        assertEquals("short", minByLength.apply(Pair.of("short", "longer")));
        assertEquals("short", minByLength.apply(Pair.of("longer", "short")));
    }

    @Test
    public void testSetUnion() {
        SemiGroup<Set<String>> union = setUnion();
        Set<String> set1 = newSet(newList("a", "b", "c"));
        Set<String> set2 = newSet(newList("c", "d", "e"));
        Set<String> result = union.apply(Pair.of(set1, set2));
        
        assertEquals(5, result.size());
        assertTrue(result.contains("a"));
        assertTrue(result.contains("b"));
        assertTrue(result.contains("c"));
        assertTrue(result.contains("d"));
        assertTrue(result.contains("e"));
    }

    @Test
    public void testSetUnion_withEmptySets() {
        SemiGroup<Set<String>> union = setUnion();
        Set<String> set1 = newSet(newList("a", "b"));
        Set<String> emptySet = Collections.emptySet();
        
        Set<String> result1 = union.apply(Pair.of(set1, emptySet));
        assertEquals(2, result1.size());
        
        Set<String> result2 = union.apply(Pair.of(emptySet, set1));
        assertEquals(2, result2.size());
    }

    @Test
    public void testSetIntersection() {
        SemiGroup<Set<String>> intersection = setIntersection();
        Set<String> set1 = newSet(newList("a", "b", "c"));
        Set<String> set2 = newSet(newList("b", "c", "d"));
        Set<String> result = intersection.apply(Pair.of(set1, set2));
        
        assertEquals(2, result.size());
        assertTrue(result.contains("b"));
        assertTrue(result.contains("c"));
    }

    @Test
    public void testSetIntersection_noCommonElements() {
        SemiGroup<Set<String>> intersection = setIntersection();
        Set<String> set1 = newSet(newList("a", "b"));
        Set<String> set2 = newSet(newList("c", "d"));
        Set<String> result = intersection.apply(Pair.of(set1, set2));
        
        assertTrue(result.isEmpty());
    }

    @Test
    public void testSetIntersection_withEmptySets() {
        SemiGroup<Set<String>> intersection = setIntersection();
        Set<String> set1 = newSet(newList("a", "b"));
        Set<String> emptySet = Collections.emptySet();
        
        Set<String> result = intersection.apply(Pair.of(set1, emptySet));
        assertTrue(result.isEmpty());
    }

    @Test
    public void testListConcat() {
        SemiGroup<List<String>> concat = listConcat();
        List<String> list1 = newList("a", "b");
        List<String> list2 = newList("c", "d");
        List<String> result = concat.apply(Pair.of(list1, list2));
        
        assertEquals(4, result.size());
        assertEquals("a", result.get(0));
        assertEquals("b", result.get(1));
        assertEquals("c", result.get(2));
        assertEquals("d", result.get(3));
    }

    @Test
    public void testCollectionConcat() {
        SemiGroup<Collection<String>> concat = collectionConcat();
        Collection<String> coll1 = newList("a", "b");
        Collection<String> coll2 = newList("c", "d");
        Collection<String> result = concat.apply(Pair.of(coll1, coll2));
        
        assertEquals(4, result.size());
    }

    @Test
    public void testIterableConcat() {
        SemiGroup<Iterable<String>> concat = iterableConcat();
        Iterable<String> iter1 = newList("a", "b");
        Iterable<String> iter2 = newList("c", "d");
        Iterable<String> result = concat.apply(Pair.of(iter1, iter2));
        
        List<String> resultList = newList(result);
        assertEquals(4, resultList.size());
        assertEquals("a", resultList.get(0));
        assertEquals("d", resultList.get(3));
    }

    @Test
    public void testComparatorConcat() {
        SemiGroup<Comparator<String>> concat = comparatorConcat();
        
        Comparator<String> lengthComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        };
        
        Comparator<String> lexicographicComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        
        Comparator<String> combined = concat.apply(Pair.of(lengthComparator, lexicographicComparator));
        
        // Same length, so falls back to lexicographic
        assertTrue(combined.compare("abc", "def") < 0);
        // Different length, uses length comparator
        assertTrue(combined.compare("ab", "def") < 0);
    }

    @Test
    public void testMapCombine() {
        SemiGroup<Map<String, Integer>> mapCombine = mapCombine(intSum);
        
        Map<String, Integer> map1 = newMutableMap();
        map1.put("a", 1);
        map1.put("b", 2);
        
        Map<String, Integer> map2 = newMutableMap();
        map2.put("b", 3);
        map2.put("c", 4);
        
        Map<String, Integer> result = mapCombine.apply(Pair.of(map1, map2));
        
        assertEquals(3, result.size());
        assertEquals(Integer.valueOf(1), result.get("a"));
        assertEquals(Integer.valueOf(5), result.get("b")); // 2 + 3
        assertEquals(Integer.valueOf(4), result.get("c"));
    }

    @Test
    public void testMapCombine_withEmptyMaps() {
        SemiGroup<Map<String, Integer>> mapCombine = mapCombine(intSum);
        
        Map<String, Integer> map1 = newMutableMap();
        map1.put("a", 1);
        
        Map<String, Integer> emptyMap = Collections.emptyMap();
        
        Map<String, Integer> result1 = mapCombine.apply(Pair.of(map1, emptyMap));
        assertEquals(1, result1.size());
        assertEquals(Integer.valueOf(1), result1.get("a"));
        
        Map<String, Integer> result2 = mapCombine.apply(Pair.of(emptyMap, map1));
        assertEquals(1, result2.size());
        assertEquals(Integer.valueOf(1), result2.get("a"));
    }

    @Test
    public void testProduct2() {
        SemiGroup<Tuple2<Integer, String>> product = product(intSum, stringConcat);
        
        Tuple2<Integer, String> tuple1 = Tuple.of(1, "hello ");
        Tuple2<Integer, String> tuple2 = Tuple.of(2, "world");
        
        Tuple2<Integer, String> result = product.apply(Pair.of(tuple1, tuple2));
        
        assertEquals(Integer.valueOf(3), result._1);
        assertEquals("hello world", result._2);
    }

    @Test
    public void testProduct3() {
        SemiGroup<Tuple3<Integer, String, Long>> product = product(intSum, stringConcat, longSum);
        
        Tuple3<Integer, String, Long> tuple1 = Tuple.of(1, "hello ", 10L);
        Tuple3<Integer, String, Long> tuple2 = Tuple.of(2, "world", 20L);
        
        Tuple3<Integer, String, Long> result = product.apply(Pair.of(tuple1, tuple2));
        
        assertEquals(Integer.valueOf(3), result._1);
        assertEquals("hello world", result._2);
        assertEquals(Long.valueOf(30L), result._3);
    }

    @Test
    public void testProduct4() {
        SemiGroup<Tuple4<Integer, String, Long, Boolean>> product = 
            product(intSum, stringConcat, longSum, booleanConjunction);
        
        Tuple4<Integer, String, Long, Boolean> tuple1 = Tuple.of(1, "hello ", 10L, true);
        Tuple4<Integer, String, Long, Boolean> tuple2 = Tuple.of(2, "world", 20L, true);
        
        Tuple4<Integer, String, Long, Boolean> result = product.apply(Pair.of(tuple1, tuple2));
        
        assertEquals(Integer.valueOf(3), result._1);
        assertEquals("hello world", result._2);
        assertEquals(Long.valueOf(30L), result._3);
        assertEquals(Boolean.TRUE, result._4);
    }

    @Test
    public void testEndo() {
        SemiGroup<Apply<Integer, Integer>> endo = endo();
        
        Apply<Integer, Integer> addOne = new Apply<Integer, Integer>() {
            @Override
            public Integer apply(Integer t) {
                return t + 1;
            }
        };
        
        Apply<Integer, Integer> multiplyByTwo = new Apply<Integer, Integer>() {
            @Override
            public Integer apply(Integer t) {
                return t * 2;
            }
        };
        
        Apply<Integer, Integer> composed = endo.apply(Pair.of(addOne, multiplyByTwo));
        
        // Should first add one, then multiply by two: (5 + 1) * 2 = 12
        assertEquals(Integer.valueOf(12), composed.apply(5));
    }

    @Test
    public void testAssociativity_intSum() {
        // Test that (a + b) + c = a + (b + c)
        Integer a = 1, b = 2, c = 3;
        
        Integer left = intSum.apply(Pair.of(intSum.apply(Pair.of(a, b)), c));
        Integer right = intSum.apply(Pair.of(a, intSum.apply(Pair.of(b, c))));
        
        assertEquals(left, right);
    }

    @Test
    public void testAssociativity_stringConcat() {
        // Test that (a + b) + c = a + (b + c)
        String a = "foo", b = "bar", c = "baz";
        
        String left = stringConcat.apply(Pair.of(stringConcat.apply(Pair.of(a, b)), c));
        String right = stringConcat.apply(Pair.of(a, stringConcat.apply(Pair.of(b, c))));
        
        assertEquals(left, right);
    }

    @Test
    public void testAssociativity_listConcat() {
        // Test that (a + b) + c = a + (b + c)
        List<String> a = newList("1");
        List<String> b = newList("2");
        List<String> c = newList("3");
        
        SemiGroup<List<String>> concat = listConcat();
        List<String> left = concat.apply(Pair.of(concat.apply(Pair.of(a, b)), c));
        List<String> right = concat.apply(Pair.of(a, concat.apply(Pair.of(b, c))));
        
        assertEquals(left, right);
    }

    @Test
    public void testSetUnion_withNullSets() {
        SemiGroup<Set<String>> union = setUnion();
        Set<String> set1 = newSet(newList("a", "b"));
        
        Set<String> result1 = union.apply(Pair.of((Set<String>)null, set1));
        assertEquals(2, result1.size());
        
        Set<String> result2 = union.apply(Pair.of(set1, (Set<String>)null));
        assertEquals(2, result2.size());
    }

    @Test
    public void testSetIntersection_withNullSets() {
        SemiGroup<Set<String>> intersection = setIntersection();
        Set<String> set1 = newSet(newList("a", "b"));
        
        Set<String> result1 = intersection.apply(Pair.of((Set<String>)null, set1));
        assertNull(result1);
        
        Set<String> result2 = intersection.apply(Pair.of(set1, (Set<String>)null));
        assertNull(result2);
    }

    @Test
    public void testMapCombine_withNullMaps() {
        SemiGroup<Map<String, Integer>> mapCombine = mapCombine(intSum);
        
        Map<String, Integer> map1 = newMutableMap();
        map1.put("a", 1);
        
        Map<String, Integer> result1 = mapCombine.apply(Pair.of((Map<String, Integer>)null, map1));
        assertEquals(1, result1.size());
        
        Map<String, Integer> result2 = mapCombine.apply(Pair.of(map1, (Map<String, Integer>)null));
        assertEquals(1, result2.size());
    }

    @Test
    public void testComparatorConcat_withNullValues() {
        SemiGroup<Comparator<String>> concat = comparatorConcat();
        
        Comparator<String> lengthComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        };
        
        Comparator<String> lexicographicComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        
        Comparator<String> combined = concat.apply(Pair.of(lengthComparator, lexicographicComparator));
        
        // Both null should return 0
        assertEquals(0, combined.compare(null, null));
    }

    @Test
    public void testIntSum_negativeNumbers() {
        assertEquals(Integer.valueOf(-8), intSum.apply(Pair.of(-5, -3)));
        assertEquals(Integer.valueOf(-2), intSum.apply(Pair.of(3, -5)));
    }

    @Test
    public void testIntProduct_negativeNumbers() {
        assertEquals(Integer.valueOf(15), intProduct.apply(Pair.of(-5, -3)));
        assertEquals(Integer.valueOf(-15), intProduct.apply(Pair.of(3, -5)));
    }

    @Test
    public void testLongSum_negativeNumbers() {
        assertEquals(Long.valueOf(-8L), longSum.apply(Pair.of(-5L, -3L)));
        assertEquals(Long.valueOf(-2L), longSum.apply(Pair.of(3L, -5L)));
    }

    @Test
    public void testLongProduct_negativeNumbers() {
        assertEquals(Long.valueOf(15L), longProduct.apply(Pair.of(-5L, -3L)));
        assertEquals(Long.valueOf(-15L), longProduct.apply(Pair.of(3L, -5L)));
    }

    @Test
    public void testDoubleSum_negativeNumbers() {
        assertEquals(Double.valueOf(-8.8), doubleSum.apply(Pair.of(-5.5, -3.3)), 0.001);
        assertEquals(Double.valueOf(-2.3), doubleSum.apply(Pair.of(3.2, -5.5)), 0.001);
    }

    @Test
    public void testDoubleProduct_negativeNumbers() {
        assertEquals(Double.valueOf(10.0), doubleProduct.apply(Pair.of(-5.0, -2.0)), 0.001);
        assertEquals(Double.valueOf(-10.0), doubleProduct.apply(Pair.of(2.0, -5.0)), 0.001);
    }

    @Test
    public void testStringConcat_emptyStrings() {
        assertEquals("", stringConcat.apply(Pair.of("", "")));
    }

    @Test
    public void testMax_withStrings() {
        SemiGroup<String> max = max();
        assertEquals("zebra", max.apply(Pair.of("apple", "zebra")));
        assertEquals("zebra", max.apply(Pair.of("zebra", "apple")));
    }

    @Test
    public void testMin_withStrings() {
        SemiGroup<String> min = min();
        assertEquals("apple", min.apply(Pair.of("apple", "zebra")));
        assertEquals("apple", min.apply(Pair.of("zebra", "apple")));
    }

    @Test
    public void testMaxBy_sameLength() {
        Comparator<String> lengthComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        };
        SemiGroup<String> maxByLength = maxBy(lengthComparator);
        // When lengths are equal (compareTo returns 0), it returns t1
        String result = maxByLength.apply(Pair.of("abc", "def"));
        assertEquals("abc", result);
    }

    @Test
    public void testMinBy_sameLength() {
        Comparator<String> lengthComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        };
        SemiGroup<String> minByLength = minBy(lengthComparator);
        // When lengths are equal (compareTo returns 0), it returns t1
        String result = minByLength.apply(Pair.of("abc", "def"));
        assertEquals("abc", result);
    }

    @Test
    public void testListConcat_withEmptyLists() {
        SemiGroup<List<String>> concat = listConcat();
        List<String> list1 = newList("a", "b");
        List<String> emptyList = emptyList();
        
        List<String> result1 = concat.apply(Pair.of(list1, emptyList));
        assertEquals(2, result1.size());
        
        List<String> result2 = concat.apply(Pair.of(emptyList, list1));
        assertEquals(2, result2.size());
    }

    @Test
    public void testCollectionConcat_withEmptyCollections() {
        SemiGroup<Collection<String>> concat = collectionConcat();
        Collection<String> coll1 = newList("a", "b");
        Collection<String> emptyColl = emptyList();
        
        Collection<String> result1 = concat.apply(Pair.of(coll1, emptyColl));
        assertEquals(2, result1.size());
        
        Collection<String> result2 = concat.apply(Pair.of(emptyColl, coll1));
        assertEquals(2, result2.size());
    }

    @Test
    public void testIterableConcat_withEmptyIterables() {
        SemiGroup<Iterable<String>> concat = iterableConcat();
        Iterable<String> iter1 = newList("a", "b");
        Iterable<String> emptyIter = emptyList();
        
        Iterable<String> result1 = concat.apply(Pair.of(iter1, emptyIter));
        List<String> resultList1 = newList(result1);
        assertEquals(2, resultList1.size());
        
        Iterable<String> result2 = concat.apply(Pair.of(emptyIter, iter1));
        List<String> resultList2 = newList(result2);
        assertEquals(2, resultList2.size());
    }

    @Test
    public void testEndo_multipleCompositions() {
        SemiGroup<Apply<Integer, Integer>> endo = endo();
        
        Apply<Integer, Integer> addOne = new Apply<Integer, Integer>() {
            @Override
            public Integer apply(Integer t) {
                return t + 1;
            }
        };
        
        Apply<Integer, Integer> multiplyByTwo = new Apply<Integer, Integer>() {
            @Override
            public Integer apply(Integer t) {
                return t * 2;
            }
        };
        
        Apply<Integer, Integer> subtractThree = new Apply<Integer, Integer>() {
            @Override
            public Integer apply(Integer t) {
                return t - 3;
            }
        };
        
        // Compose all three: ((5 + 1) * 2) - 3 = 9
        Apply<Integer, Integer> composed = endo.apply(Pair.of(endo.apply(Pair.of(addOne, multiplyByTwo)), subtractThree));
        assertEquals(Integer.valueOf(9), composed.apply(5));
    }

    @Test
    public void testAssociativity_setUnion() {
        // Test that (a ∪ b) ∪ c = a ∪ (b ∪ c)
        Set<String> a = newSet(newList("a", "b"));
        Set<String> b = newSet(newList("c", "d"));
        Set<String> c = newSet(newList("e", "f"));
        
        SemiGroup<Set<String>> union = setUnion();
        Set<String> left = union.apply(Pair.of(union.apply(Pair.of(a, b)), c));
        Set<String> right = union.apply(Pair.of(a, union.apply(Pair.of(b, c))));
        
        assertEquals(left, right);
    }

    @Test
    public void testAssociativity_mapCombine() {
        // Test that (a + b) + c = a + (b + c)
        Map<String, Integer> a = newMutableMap();
        a.put("x", 1);
        Map<String, Integer> b = newMutableMap();
        b.put("x", 2);
        Map<String, Integer> c = newMutableMap();
        c.put("x", 3);
        
        SemiGroup<Map<String, Integer>> mapCombine = mapCombine(intSum);
        Map<String, Integer> left = mapCombine.apply(Pair.of(mapCombine.apply(Pair.of(a, b)), c));
        Map<String, Integer> right = mapCombine.apply(Pair.of(a, mapCombine.apply(Pair.of(b, c))));
        
        assertEquals(left.get("x"), right.get("x"));
    }

    @Test
    public void testMapCombine_disjointKeys() {
        SemiGroup<Map<String, Integer>> mapCombine = mapCombine(intSum);
        
        Map<String, Integer> map1 = newMutableMap();
        map1.put("a", 1);
        map1.put("b", 2);
        
        Map<String, Integer> map2 = newMutableMap();
        map2.put("c", 3);
        map2.put("d", 4);
        
        Map<String, Integer> result = mapCombine.apply(Pair.of(map1, map2));
        
        assertEquals(4, result.size());
        assertEquals(Integer.valueOf(1), result.get("a"));
        assertEquals(Integer.valueOf(2), result.get("b"));
        assertEquals(Integer.valueOf(3), result.get("c"));
        assertEquals(Integer.valueOf(4), result.get("d"));
    }

    @Test
    public void testSetIntersection_sameSet() {
        SemiGroup<Set<String>> intersection = setIntersection();
        Set<String> set1 = newSet(newList("a", "b", "c"));
        Set<String> result = intersection.apply(Pair.of(set1, set1));
        
        assertEquals(3, result.size());
        assertTrue(result.contains("a"));
        assertTrue(result.contains("b"));
        assertTrue(result.contains("c"));
    }

    @Test
    public void testSetUnion_sameSet() {
        SemiGroup<Set<String>> union = setUnion();
        Set<String> set1 = newSet(newList("a", "b", "c"));
        Set<String> result = union.apply(Pair.of(set1, set1));
        
        assertEquals(3, result.size());
        assertTrue(result.contains("a"));
        assertTrue(result.contains("b"));
        assertTrue(result.contains("c"));
    }
}
