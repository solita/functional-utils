package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.*;
import static fi.solita.utils.functional.Monoids.*;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

public class MonoidsTest {

    @Test
    public void testOf() {
        Monoid<String> customMonoid = Monoids.of(SemiGroups.stringConcat, Function.of("zero"));
        assertEquals("zero", customMonoid.zero());
        assertEquals("hello world", customMonoid.apply(Pair.of("hello ", "world")));
    }

    @Test
    public void testIntSum() {
        assertEquals(Integer.valueOf(0), intSum.zero());
        assertEquals(Integer.valueOf(5), intSum.apply(Pair.of(2, 3)));
        assertEquals(Integer.valueOf(0), intSum.apply(Pair.of(-5, 5)));
        assertEquals(Integer.valueOf(100), intSum.apply(Pair.of(50, 50)));
    }

    @Test
    public void testIntSum_identityLaw() {
        Integer value = 42;
        assertEquals(value, intSum.apply(Pair.of(value, intSum.zero())));
        assertEquals(value, intSum.apply(Pair.of(intSum.zero(), value)));
    }

    @Test
    public void testIntProduct() {
        assertEquals(Integer.valueOf(1), intProduct.zero());
        assertEquals(Integer.valueOf(6), intProduct.apply(Pair.of(2, 3)));
        assertEquals(Integer.valueOf(0), intProduct.apply(Pair.of(0, 5)));
        assertEquals(Integer.valueOf(100), intProduct.apply(Pair.of(10, 10)));
    }

    @Test
    public void testIntProduct_identityLaw() {
        Integer value = 42;
        assertEquals(value, intProduct.apply(Pair.of(value, intProduct.zero())));
        assertEquals(value, intProduct.apply(Pair.of(intProduct.zero(), value)));
    }

    @Test
    public void testLongSum() {
        assertEquals(Long.valueOf(0L), longSum.zero());
        assertEquals(Long.valueOf(5L), longSum.apply(Pair.of(2L, 3L)));
        assertEquals(Long.valueOf(0L), longSum.apply(Pair.of(-5L, 5L)));
        assertEquals(Long.valueOf(1000000000000L), longSum.apply(Pair.of(500000000000L, 500000000000L)));
    }

    @Test
    public void testLongSum_identityLaw() {
        Long value = 42L;
        assertEquals(value, longSum.apply(Pair.of(value, longSum.zero())));
        assertEquals(value, longSum.apply(Pair.of(longSum.zero(), value)));
    }

    @Test
    public void testLongProduct() {
        assertEquals(Long.valueOf(1L), longProduct.zero());
        assertEquals(Long.valueOf(6L), longProduct.apply(Pair.of(2L, 3L)));
        assertEquals(Long.valueOf(0L), longProduct.apply(Pair.of(0L, 5L)));
        assertEquals(Long.valueOf(100L), longProduct.apply(Pair.of(10L, 10L)));
    }

    @Test
    public void testLongProduct_identityLaw() {
        Long value = 42L;
        assertEquals(value, longProduct.apply(Pair.of(value, longProduct.zero())));
        assertEquals(value, longProduct.apply(Pair.of(longProduct.zero(), value)));
    }

    @Test
    public void testDoubleSum() {
        assertEquals(Double.valueOf(0.0), doubleSum.zero(), 0.001);
        assertEquals(Double.valueOf(5.5), doubleSum.apply(Pair.of(2.2, 3.3)), 0.001);
        assertEquals(Double.valueOf(0.0), doubleSum.apply(Pair.of(-5.5, 5.5)), 0.001);
    }

    @Test
    public void testDoubleSum_identityLaw() {
        Double value = 42.5;
        assertEquals(value, doubleSum.apply(Pair.of(value, doubleSum.zero())), 0.001);
        assertEquals(value, doubleSum.apply(Pair.of(doubleSum.zero(), value)), 0.001);
    }

    @Test
    public void testDoubleProduct() {
        assertEquals(Double.valueOf(1.0), doubleProduct.zero(), 0.001);
        assertEquals(Double.valueOf(6.0), doubleProduct.apply(Pair.of(2.0, 3.0)), 0.001);
        assertEquals(Double.valueOf(0.0), doubleProduct.apply(Pair.of(0.0, 5.0)), 0.001);
    }

    @Test
    public void testDoubleProduct_identityLaw() {
        Double value = 42.5;
        assertEquals(value, doubleProduct.apply(Pair.of(value, doubleProduct.zero())), 0.001);
        assertEquals(value, doubleProduct.apply(Pair.of(doubleProduct.zero(), value)), 0.001);
    }

    @Test
    public void testStringConcat() {
        assertEquals("", stringConcat.zero());
        assertEquals("hello world", stringConcat.apply(Pair.of("hello ", "world")));
        assertEquals("foobar", stringConcat.apply(Pair.of("foo", "bar")));
        assertEquals("test", stringConcat.apply(Pair.of("", "test")));
        assertEquals("test", stringConcat.apply(Pair.of("test", "")));
    }

    @Test
    public void testStringConcat_identityLaw() {
        String value = "test";
        assertEquals(value, stringConcat.apply(Pair.of(value, stringConcat.zero())));
        assertEquals(value, stringConcat.apply(Pair.of(stringConcat.zero(), value)));
    }

    @Test
    public void testBooleanConjunction() {
        assertEquals(Boolean.TRUE, booleanConjunction.zero());
        assertEquals(Boolean.TRUE, booleanConjunction.apply(Pair.of(true, true)));
        assertEquals(Boolean.FALSE, booleanConjunction.apply(Pair.of(true, false)));
        assertEquals(Boolean.FALSE, booleanConjunction.apply(Pair.of(false, true)));
        assertEquals(Boolean.FALSE, booleanConjunction.apply(Pair.of(false, false)));
    }

    @Test
    public void testBooleanConjunction_identityLaw() {
        assertEquals(Boolean.TRUE, booleanConjunction.apply(Pair.of(true, booleanConjunction.zero())));
        assertEquals(Boolean.FALSE, booleanConjunction.apply(Pair.of(false, booleanConjunction.zero())));
    }

    @Test
    public void testBooleanDisjunction() {
        assertEquals(Boolean.FALSE, booleanDisjunction.zero());
        assertEquals(Boolean.TRUE, booleanDisjunction.apply(Pair.of(true, true)));
        assertEquals(Boolean.TRUE, booleanDisjunction.apply(Pair.of(true, false)));
        assertEquals(Boolean.TRUE, booleanDisjunction.apply(Pair.of(false, true)));
        assertEquals(Boolean.FALSE, booleanDisjunction.apply(Pair.of(false, false)));
    }

    @Test
    public void testBooleanDisjunction_identityLaw() {
        assertEquals(Boolean.TRUE, booleanDisjunction.apply(Pair.of(true, booleanDisjunction.zero())));
        assertEquals(Boolean.FALSE, booleanDisjunction.apply(Pair.of(false, booleanDisjunction.zero())));
    }

    @Test
    public void testEndo() {
        Monoid<Apply<String, String>> endoMonoid = Monoids.<String>endo();
        
        // Zero is identity function
        Apply<String, String> identity = endoMonoid.zero();
        assertEquals("test", identity.apply("test"));
        
        // Compose functions
        Apply<String, String> toUpper = new Apply<String, String>() {
            public String apply(String t) {
                return t.toUpperCase();
            }
        };
        Apply<String, String> addSuffix = new Apply<String, String>() {
            public String apply(String t) {
                return t + "!";
            }
        };
        
        Apply<String, String> composed = endoMonoid.apply(Pair.of(toUpper, addSuffix));
        assertEquals("HELLO!", composed.apply("hello"));
    }

    @Test
    public void testEndo_identityLaw() {
        Monoid<Apply<Integer, Integer>> endoMonoid = Monoids.<Integer>endo();
        
        Apply<Integer, Integer> addOne = new Apply<Integer, Integer>() {
            public Integer apply(Integer t) {
                return t + 1;
            }
        };
        
        Apply<Integer, Integer> leftIdentity = endoMonoid.apply(Pair.of(addOne, endoMonoid.zero()));
        Apply<Integer, Integer> rightIdentity = endoMonoid.apply(Pair.of(endoMonoid.zero(), addOne));
        
        assertEquals(Integer.valueOf(43), leftIdentity.apply(42));
        assertEquals(Integer.valueOf(43), rightIdentity.apply(42));
    }

    @Test
    public void testSetUnion() {
        Monoid<Set<String>> setMonoid = Monoids.<String>setUnion();
        
        // Zero is empty set
        assertTrue(setMonoid.zero().isEmpty());
        
        // Union of sets - newSet creates a LinkedHashSet which preserves insertion order
        Set<String> set1 = newSet(newList("a", "b"));
        Set<String> set2 = newSet(newList("b", "c"));
        Set<String> result = setMonoid.apply(Pair.of(set1, set2));
        
        assertEquals(3, result.size());
        assertTrue(result.contains("a"));
        assertTrue(result.contains("b"));
        assertTrue(result.contains("c"));
        
        // Preserves iteration order
        Iterator<String> it = result.iterator();
        assertEquals("a", it.next());
        assertEquals("b", it.next());
        assertEquals("c", it.next());
    }

    @Test
    public void testSetUnion_identityLaw() {
        Monoid<Set<String>> setMonoid = Monoids.<String>setUnion();
        Set<String> set = newSet(newList("a", "b"));
        
        Set<String> leftIdentity = setMonoid.apply(Pair.of(set, setMonoid.zero()));
        Set<String> rightIdentity = setMonoid.apply(Pair.of(setMonoid.zero(), set));
        
        assertEquals(set, leftIdentity);
        assertEquals(set, rightIdentity);
    }

    @Test
    public void testComparatorConcat() {
        Monoid<Comparator<Person>> compMonoid = Monoids.<Person>comparatorConcat();
        
        // Zero comparator always returns 0
        Comparator<Person> zero = compMonoid.zero();
        assertEquals(0, zero.compare(new Person("Alice", 30), new Person("Bob", 25)));
        
        // Combine comparators
        Comparator<Person> byName = new Comparator<Person>() {
            public int compare(Person o1, Person o2) {
                return o1.name.compareTo(o2.name);
            }
        };
        Comparator<Person> byAge = new Comparator<Person>() {
            public int compare(Person o1, Person o2) {
                return Integer.compare(o1.age, o2.age);
            }
        };
        
        // First by name, then by age if names are equal
        Comparator<Person> combined = compMonoid.apply(Pair.of(byName, byAge));
        
        assertTrue(combined.compare(new Person("Alice", 30), new Person("Bob", 25)) < 0);
        assertTrue(combined.compare(new Person("Alice", 25), new Person("Alice", 30)) < 0);
        assertEquals(0, combined.compare(new Person("Alice", 30), new Person("Alice", 30)));
    }

    @Test
    public void testIterableConcat() {
        Monoid<Iterable<String>> iterMonoid = Monoids.<String>iterableConcat();
        
        // Zero is empty list
        assertFalse(iterMonoid.zero().iterator().hasNext());
        
        // Concatenate iterables
        Iterable<String> iter1 = newList("a", "b");
        Iterable<String> iter2 = newList("c", "d");
        Iterable<String> result = iterMonoid.apply(Pair.of(iter1, iter2));
        
        assertEquals(newList("a", "b", "c", "d"), newList(result));
    }

    @Test
    public void testIterableConcat_identityLaw() {
        Monoid<Iterable<String>> iterMonoid = Monoids.<String>iterableConcat();
        Iterable<String> iter = newList("a", "b");
        
        assertEquals(newList(iter), newList(iterMonoid.apply(Pair.of(iter, iterMonoid.zero()))));
        assertEquals(newList(iter), newList(iterMonoid.apply(Pair.of(iterMonoid.zero(), iter))));
    }

    @Test
    public void testCollectionConcat() {
        Monoid<Collection<String>> collMonoid = Monoids.<String>collectionConcat();
        
        // Zero is empty list
        assertTrue(collMonoid.zero().isEmpty());
        
        // Concatenate collections
        Collection<String> coll1 = newList("a", "b");
        Collection<String> coll2 = newList("c", "d");
        Collection<String> result = collMonoid.apply(Pair.of(coll1, coll2));
        
        assertEquals(4, result.size());
        assertEquals(newList("a", "b", "c", "d"), newList(result));
    }

    @Test
    public void testCollectionConcat_emptyCollections() {
        Monoid<Collection<String>> collMonoid = Monoids.<String>collectionConcat();
        
        Collection<String> coll1 = newList("a", "b");
        Collection<String> empty = emptyList();
        
        // Empty on right
        Collection<String> result1 = collMonoid.apply(Pair.of(coll1, empty));
        assertEquals(coll1, result1);
        
        // Empty on left
        Collection<String> result2 = collMonoid.apply(Pair.of(empty, coll1));
        assertEquals(coll1, result2);
        
        // Both empty
        Collection<String> result3 = collMonoid.apply(Pair.of(empty, empty));
        assertTrue(result3.isEmpty());
    }

    @Test
    public void testListConcat() {
        Monoid<List<String>> listMonoid = Monoids.<String>listConcat();
        
        // Zero is empty list
        assertTrue(listMonoid.zero().isEmpty());
        
        // Concatenate lists
        List<String> list1 = newList("a", "b");
        List<String> list2 = newList("c", "d");
        List<String> result = listMonoid.apply(Pair.of(list1, list2));
        
        assertEquals(4, result.size());
        assertEquals(newList("a", "b", "c", "d"), result);
    }

    @Test
    public void testListConcat_emptyLists() {
        Monoid<List<String>> listMonoid = Monoids.<String>listConcat();
        
        List<String> list1 = newList("a", "b");
        List<String> empty = emptyList();
        
        // Empty on right
        List<String> result1 = listMonoid.apply(Pair.of(list1, empty));
        assertEquals(list1, result1);
        
        // Empty on left
        List<String> result2 = listMonoid.apply(Pair.of(empty, list1));
        assertEquals(list1, result2);
        
        // Both empty
        List<String> result3 = listMonoid.apply(Pair.of(empty, empty));
        assertTrue(result3.isEmpty());
    }

    @Test
    public void testMapCombine() {
        Monoid<Map<String, Integer>> mapMonoid = Monoids.mapCombine(SemiGroups.intSum);
        
        // Zero is empty map
        assertTrue(mapMonoid.zero().isEmpty());
        
        // Combine maps with overlapping keys
        Map<String, Integer> map1 = newMap(Pair.of("a", 1), Pair.of("b", 2));
        Map<String, Integer> map2 = newMap(Pair.of("b", 3), Pair.of("c", 4));
        Map<String, Integer> result = mapMonoid.apply(Pair.of(map1, map2));
        
        assertEquals(3, result.size());
        assertEquals(Integer.valueOf(1), result.get("a"));
        assertEquals(Integer.valueOf(5), result.get("b")); // 2 + 3
        assertEquals(Integer.valueOf(4), result.get("c"));
    }

    @Test
    public void testMapCombine_identityLaw() {
        Monoid<Map<String, Integer>> mapMonoid = Monoids.mapCombine(SemiGroups.intSum);
        Map<String, Integer> map = newMap(Pair.of("a", 1), Pair.of("b", 2));
        
        Map<String, Integer> leftIdentity = mapMonoid.apply(Pair.of(map, mapMonoid.zero()));
        Map<String, Integer> rightIdentity = mapMonoid.apply(Pair.of(mapMonoid.zero(), map));
        
        assertEquals(map, leftIdentity);
        assertEquals(map, rightIdentity);
    }

    @Test
    public void testProduct_tuple2() {
        Monoid<Tuple2<Integer, String>> productMonoid = Monoids.product(intSum, stringConcat);
        
        // Zero
        Tuple2<Integer, String> zero = productMonoid.zero();
        assertEquals(Integer.valueOf(0), zero._1);
        assertEquals("", zero._2);
        
        // Combine
        Tuple2<Integer, String> t1 = Tuple.of(1, "hello");
        Tuple2<Integer, String> t2 = Tuple.of(2, " world");
        Tuple2<Integer, String> result = productMonoid.apply(Pair.of(t1, t2));
        
        assertEquals(Integer.valueOf(3), result._1);
        assertEquals("hello world", result._2);
    }

    @Test
    public void testProduct_tuple3() {
        Monoid<Tuple3<Integer, String, Boolean>> productMonoid = 
            Monoids.product(intSum, stringConcat, booleanConjunction);
        
        // Zero
        Tuple3<Integer, String, Boolean> zero = productMonoid.zero();
        assertEquals(Integer.valueOf(0), zero._1);
        assertEquals("", zero._2);
        assertEquals(Boolean.TRUE, zero._3);
        
        // Combine
        Tuple3<Integer, String, Boolean> t1 = Tuple.of(1, "hello", true);
        Tuple3<Integer, String, Boolean> t2 = Tuple.of(2, " world", true);
        Tuple3<Integer, String, Boolean> result = productMonoid.apply(Pair.of(t1, t2));
        
        assertEquals(Integer.valueOf(3), result._1);
        assertEquals("hello world", result._2);
        assertEquals(Boolean.TRUE, result._3);
    }

    @Test
    public void testProduct_tuple4() {
        Monoid<Tuple4<Integer, String, Boolean, Long>> productMonoid = 
            Monoids.product(intSum, stringConcat, booleanConjunction, longProduct);
        
        // Zero
        Tuple4<Integer, String, Boolean, Long> zero = productMonoid.zero();
        assertEquals(Integer.valueOf(0), zero._1);
        assertEquals("", zero._2);
        assertEquals(Boolean.TRUE, zero._3);
        assertEquals(Long.valueOf(1L), zero._4);
        
        // Combine
        Tuple4<Integer, String, Boolean, Long> t1 = Tuple.of(1, "hello", true, 2L);
        Tuple4<Integer, String, Boolean, Long> t2 = Tuple.of(2, " world", true, 3L);
        Tuple4<Integer, String, Boolean, Long> result = productMonoid.apply(Pair.of(t1, t2));
        
        assertEquals(Integer.valueOf(3), result._1);
        assertEquals("hello world", result._2);
        assertEquals(Boolean.TRUE, result._3);
        assertEquals(Long.valueOf(6L), result._4);
    }

    // Helper class for comparator tests
    static class Person {
        final String name;
        final int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
