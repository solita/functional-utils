package fi.solita.utils.functional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;

public class CompareTest {
    
    @Test
    public void byIterable_comparesLists() {
        Comparator<Iterable<Integer>> ordering = Compare.<Integer>byIterable();
        
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(1, 2, 4);
        List<Integer> list3 = Arrays.asList(1, 2, 3);
        
        assertTrue(ordering.compare(list1, list2) < 0);
        assertTrue(ordering.compare(list2, list1) > 0);
        assertEquals(0, ordering.compare(list1, list3));
    }
    
    @Test
    public void byIterable_comparesDifferentLengths() {
        Comparator<Iterable<Integer>> ordering = Compare.<Integer>byIterable();
        
        List<Integer> shorter = Arrays.asList(1, 2);
        List<Integer> longer = Arrays.asList(1, 2, 3);
        
        assertTrue(ordering.compare(shorter, longer) < 0);
        assertTrue(ordering.compare(longer, shorter) > 0);
    }
    
    @Test
    public void byIterable_withCustomComparator() {
        Comparator<Integer> reverseOrdering = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        };
        
        Comparator<List<Integer>> ordering = Compare.byIterable(reverseOrdering);
        
        List<Integer> list1 = Arrays.asList(3, 2, 1);
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        
        assertTrue(ordering.compare(list1, list2) < 0);
    }
    
    @Test
    public void byIterable_handlesNulls() {
        Comparator<Iterable<Integer>> ordering = Compare.<Integer>byIterable();
        
        assertEquals(0, ordering.compare(null, null));
    }
    
    @Test
    public void byOptional_comparesOptions() {
        Comparator<Optional<Integer>> ordering = Compare.byOptional();
        
        Optional<Integer> none = Optional.empty();
        Optional<Integer> some1 = Optional.of(1);
        Optional<Integer> some2 = Optional.of(2);
        Optional<Integer> some1b = Optional.of(1);
        
        assertEquals(0, ordering.compare(none, none));
        assertTrue(ordering.compare(none, some1) > 0);
        assertTrue(ordering.compare(some1, none) < 0);
        assertTrue(ordering.compare(some1, some2) < 0);
        assertTrue(ordering.compare(some2, some1) > 0);
        assertEquals(0, ordering.compare(some1, some1b));
    }
    
    @Test
    public void byOptional_withCustomComparator() {
        Comparator<Integer> reverseOrdering = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        };
        
        Comparator<Optional<Integer>> ordering = Compare.byOptional(reverseOrdering);
        
        Optional<Integer> some1 = Optional.of(1);
        Optional<Integer> some2 = Optional.of(2);
        
        assertTrue(ordering.compare(some1, some2) > 0);
        assertTrue(ordering.compare(some2, some1) < 0);
    }
    
    @Test
    public void byOptional_handlesNulls() {
        Comparator<Optional<Integer>> ordering = Compare.byOptional();
        
        assertEquals(0, ordering.compare(null, null));
    }
    
    @Test
    public void byOptional_withTransformerFunction() {
        class Person {
            Optional<String> nickname;
            
            Person(Optional<String> nickname) {
                this.nickname = nickname;
            }
        }
        
        java.util.function.Function<Person, Optional<String>> getNickname = new java.util.function.Function<Person, Optional<String>>() {
            @Override
            public Optional<String> apply(Person p) {
                return p.nickname;
            }
        };
        
        Comparator<Person> ordering = Compare.byOptional(getNickname);
        
        Person person1 = new Person(Optional.empty());
        Person person2 = new Person(Optional.of("Al"));
        Person person3 = new Person(Optional.of("Bo"));
        
        assertTrue(ordering.compare(person1, person2) > 0);
        assertTrue(ordering.compare(person2, person3) < 0);
    }
    
    @Test
    public void byMap_comparesMapsByEntries() {
        Comparator<Map<String, Integer>> ordering = Compare.byMap();
        
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("a", 1);
        map1.put("b", 2);
        
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("a", 1);
        map2.put("b", 2);
        
        Map<String, Integer> map3 = new HashMap<>();
        map3.put("a", 1);
        map3.put("b", 3);
        
        assertEquals(0, ordering.compare(map1, map2));
        assertTrue(ordering.compare(map1, map3) != 0);
    }
    
    @Test
    public void byMap_withCustomComparators() {
        Comparator<String> stringOrdering = Comparator.naturalOrder();
        Comparator<Integer> intOrdering = Comparator.naturalOrder();
        
        Comparator<Map<String, Integer>> ordering = Compare.byMap(stringOrdering, intOrdering);
        
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("a", 1);
        
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("b", 1);
        
        assertTrue(ordering.compare(map1, map2) < 0);
    }
    
    @Test
    public void byPair_comparesPairs() {
        Comparator<Pair<Integer, String>> ordering = Compare.byPair(
            Comparator.naturalOrder(),
            Comparator.naturalOrder()
        );
        
        Pair<Integer, String> pair1 = Pair.of(1, "a");
        Pair<Integer, String> pair2 = Pair.of(1, "b");
        Pair<Integer, String> pair3 = Pair.of(2, "a");
        Pair<Integer, String> pair4 = Pair.of(1, "a");
        
        assertTrue(ordering.compare(pair1, pair2) < 0);
        assertTrue(ordering.compare(pair1, pair3) < 0);
        assertEquals(0, ordering.compare(pair1, pair4));
    }
    
    @Test
    public void byTuple_comparesTuple3() {
        Comparator<Tuple3<Integer, String, Double>> ordering = Compare.byTuple(
            Comparator.naturalOrder(),
            Comparator.naturalOrder(),
            Comparator.naturalOrder()
        );
        
        Tuple3<Integer, String, Double> tuple1 = Tuple.of(1, "a", 1.0);
        Tuple3<Integer, String, Double> tuple2 = Tuple.of(1, "a", 2.0);
        Tuple3<Integer, String, Double> tuple3 = Tuple.of(1, "b", 1.0);
        Tuple3<Integer, String, Double> tuple4 = Tuple.of(1, "a", 1.0);
        
        assertTrue(ordering.compare(tuple1, tuple2) < 0);
        assertTrue(ordering.compare(tuple1, tuple3) < 0);
        assertEquals(0, ordering.compare(tuple1, tuple4));
    }
    
    @Test
    public void byTuple_comparesTuple4() {
        Comparator<Tuple4<Integer, String, Double, Boolean>> ordering = Compare.byTuple(
            Comparator.naturalOrder(),
            Comparator.naturalOrder(),
            Comparator.naturalOrder(),
            new Comparator<Boolean>() {
                @Override
                public int compare(Boolean o1, Boolean o2) {
                    return o1.compareTo(o2);
                }
            }
        );
        
        Tuple4<Integer, String, Double, Boolean> tuple1 = Tuple.of(1, "a", 1.0, true);
        Tuple4<Integer, String, Double, Boolean> tuple2 = Tuple.of(1, "a", 1.0, false);
        Tuple4<Integer, String, Double, Boolean> tuple3 = Tuple.of(1, "a", 1.0, true);
        
        assertTrue(ordering.compare(tuple1, tuple2) > 0);
        assertEquals(0, ordering.compare(tuple1, tuple3));
    }
    
    @Test
    public void byEither_comparesEithers() {
        Comparator<Either<Integer, String>> ordering = Compare.byEither();
        
        Either<Integer, String> left1 = Either.left(1);
        Either<Integer, String> left2 = Either.left(2);
        Either<Integer, String> right1 = Either.right("a");
        Either<Integer, String> right2 = Either.right("b");
        
        assertTrue(ordering.compare(left1, left2) < 0);
        assertTrue(ordering.compare(right1, right2) < 0);
        assertTrue(ordering.compare(left1, right1) < 0);
    }
    
    @Test
    public void byEither3_comparesEither3() {
        Comparator<Either3<Integer, String, Double>> ordering = Compare.byEither3();
        
        Either3<Integer, String, Double> left = Either3.left(1);
        Either3<Integer, String, Double> middle = Either3.middle("a");
        Either3<Integer, String, Double> right = Either3.right(1.0);
        
        assertTrue(ordering.compare(left, middle) < 0);
        assertTrue(ordering.compare(middle, right) < 0);
        assertTrue(ordering.compare(left, right) < 0);
    }
    
    @Test
    public void byKey_comparesMapEntries() {
        Comparator<Map.Entry<Integer, String>> ordering = Comparator.comparing(Map.Entry::getKey);
        
        Pair<Integer, String> entry1 = Pair.of(1, "z");
        Pair<Integer, String> entry2 = Pair.of(2, "a");
        
        assertTrue(ordering.compare(entry1, entry2) < 0);
    }
    
    @Test
    public void byValue_comparesMapEntries() {
        Comparator<Map.Entry<Integer, String>> ordering = Comparator.comparing(Map.Entry::getValue);
        
        Pair<Integer, String> entry1 = Pair.of(5, "a");
        Pair<Integer, String> entry2 = Pair.of(1, "z");
        
        assertTrue(ordering.compare(entry1, entry2) < 0);
    }
    
    @Test
    public void by_1_comparesTuplesByFirstElement() {
        Tuple2<Integer, String> tuple1 = Tuple.of(1, "z");
        Tuple2<Integer, String> tuple2 = Tuple.of(2, "a");
        
        assertTrue(Comparator.<Tuple2<Integer,String>,Integer>comparing(t -> t._1).compare(tuple1, tuple2) < 0);
    }
    
    @Test
    public void by_2_comparesTuplesBySecondElement() {
        Tuple2<Integer, String> tuple1 = Tuple.of(5, "a");
        Tuple2<Integer, String> tuple2 = Tuple.of(1, "z");
        
        assertTrue(Comparator.<Tuple2<Integer,String>,String>comparing(t -> t._2).compare(tuple1, tuple2) < 0);
    }
    
    @Test
    public void by_3_comparesTuplesByThirdElement() {
        Tuple3<Integer, String, Double> tuple1 = Tuple.of(5, "z", 1.0);
        Tuple3<Integer, String, Double> tuple2 = Tuple.of(1, "a", 2.0);
        
        assertTrue(Comparator.<Tuple3<Integer,String,Double>,Double>comparing(t -> t._3).compare(tuple1, tuple2) < 0);
    }
    
    @Test
    public void by_4_comparesTuplesByFourthElement() {
        Tuple4<Integer, String, Double, Boolean> tuple1 = Tuple.of(5, "z", 2.0, false);
        Tuple4<Integer, String, Double, Boolean> tuple2 = Tuple.of(1, "a", 1.0, true);
        
        assertTrue(Comparator.<Tuple4<Integer,String,Double,Boolean>,Boolean>comparing(t -> t._4).compare(tuple1, tuple2) < 0);
    }
    
    @Test
    public void ordering_then_chainsComparators() {
        class Person {
            String firstName;
            String lastName;
            
            Person(String firstName, String lastName) {
                this.firstName = firstName;
                this.lastName = lastName;
            }
        }
        
        java.util.function.Function<Person, String> getFirstName = new java.util.function.Function<Person, String>() {
            @Override
            public String apply(Person p) {
                return p.firstName;
            }
        };
        
        java.util.function.Function<Person, String> getLastName = new java.util.function.Function<Person, String>() {
            @Override
            public String apply(Person p) {
                return p.lastName;
            }
        };
        
        Comparator<Person> ordering = Comparator.comparing(getFirstName).thenComparing(getLastName);
        
        Person person1 = new Person("Alice", "Smith");
        Person person2 = new Person("Alice", "Jones");
        Person person3 = new Person("Bob", "Smith");
        
        assertTrue(ordering.compare(person1, person2) > 0);
        assertTrue(ordering.compare(person1, person3) < 0);
    }
    
    @Test
    public void ordering_reverse_reversesOrder() {
        Comparator<Integer> natural = Comparator.naturalOrder();
        Comparator<Integer> reversed = natural.reversed();
        
        assertTrue(natural.compare(1, 2) < 0);
        assertTrue(reversed.compare(1, 2) > 0);
    }
}
