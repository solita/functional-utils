package fi.solita.utils.functional;

import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class CompareTest {
    
    @Test
    public void byNatural_comparesIntegers() {
        Ordering<Integer> ordering = Compare.byNatural();
        
        assertTrue(ordering.compare(1, 2) < 0);
        assertTrue(ordering.compare(2, 1) > 0);
        assertEquals(0, ordering.compare(5, 5));
    }
    
    @Test
    public void byNatural_comparesStrings() {
        Ordering<String> ordering = Compare.byNatural();
        
        assertTrue(ordering.compare("a", "b") < 0);
        assertTrue(ordering.compare("z", "a") > 0);
        assertEquals(0, ordering.compare("hello", "hello"));
    }
    
    @Test
    public void byNatural_handlesNulls() {
        Ordering<Integer> ordering = Compare.byNatural();
        
        assertEquals(0, ordering.compare(null, null));
    }
    
    @Test
    public void byIterable_comparesLists() {
        Ordering<Iterable<Integer>> ordering = Compare.<Integer>byIterable();
        
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(1, 2, 4);
        List<Integer> list3 = Arrays.asList(1, 2, 3);
        
        assertTrue(ordering.compare(list1, list2) < 0);
        assertTrue(ordering.compare(list2, list1) > 0);
        assertEquals(0, ordering.compare(list1, list3));
    }
    
    @Test
    public void byIterable_comparesDifferentLengths() {
        Ordering<Iterable<Integer>> ordering = Compare.<Integer>byIterable();
        
        List<Integer> shorter = Arrays.asList(1, 2);
        List<Integer> longer = Arrays.asList(1, 2, 3);
        
        assertTrue(ordering.compare(shorter, longer) < 0);
        assertTrue(ordering.compare(longer, shorter) > 0);
    }
    
    @Test
    public void byIterable_withCustomComparator() {
        Ordering<Integer> reverseOrdering = new Ordering<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        };
        
        Ordering<List<Integer>> ordering = Compare.byIterable(reverseOrdering);
        
        List<Integer> list1 = Arrays.asList(3, 2, 1);
        List<Integer> list2 = Arrays.asList(1, 2, 3);
        
        assertTrue(ordering.compare(list1, list2) < 0);
    }
    
    @Test
    public void byIterable_handlesNulls() {
        Ordering<Iterable<Integer>> ordering = Compare.<Integer>byIterable();
        
        assertEquals(0, ordering.compare(null, null));
    }
    
    @Test
    public void byOption_comparesOptions() {
        Ordering<Option<Integer>> ordering = Compare.byOption();
        
        Option<Integer> none = None();
        Option<Integer> some1 = Some(1);
        Option<Integer> some2 = Some(2);
        Option<Integer> some1b = Some(1);
        
        assertEquals(0, ordering.compare(none, none));
        assertTrue(ordering.compare(none, some1) > 0);
        assertTrue(ordering.compare(some1, none) < 0);
        assertTrue(ordering.compare(some1, some2) < 0);
        assertTrue(ordering.compare(some2, some1) > 0);
        assertEquals(0, ordering.compare(some1, some1b));
    }
    
    @Test
    public void byOption_withCustomComparator() {
        Ordering<Integer> reverseOrdering = new Ordering<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        };
        
        Ordering<Option<Integer>> ordering = Compare.byOption(reverseOrdering);
        
        Option<Integer> some1 = Some(1);
        Option<Integer> some2 = Some(2);
        
        assertTrue(ordering.compare(some1, some2) > 0);
        assertTrue(ordering.compare(some2, some1) < 0);
    }
    
    @Test
    public void byOption_handlesNulls() {
        Ordering<Option<Integer>> ordering = Compare.byOption();
        
        assertEquals(0, ordering.compare(null, null));
    }
    
    @Test
    public void by_withTransformerFunction() {
        class Person {
            String name;
            int age;
            
            Person(String name, int age) {
                this.name = name;
                this.age = age;
            }
        }
        
        Apply<Person, Integer> getAge = new Apply<Person, Integer>() {
            @Override
            public Integer apply(Person p) {
                return p.age;
            }
        };
        
        Ordering<Person> ordering = Compare.by(getAge);
        
        Person person1 = new Person("Alice", 25);
        Person person2 = new Person("Bob", 30);
        Person person3 = new Person("Charlie", 25);
        
        assertTrue(ordering.compare(person1, person2) < 0);
        assertTrue(ordering.compare(person2, person1) > 0);
        assertEquals(0, ordering.compare(person1, person3));
    }
    
    @Test
    public void by_withExplicitComparator() {
        class Person {
            String name;
            
            Person(String name) {
                this.name = name;
            }
        }
        
        Apply<Person, String> getName = new Apply<Person, String>() {
            @Override
            public String apply(Person p) {
                return p.name;
            }
        };
        
        Ordering<Person> ordering = Compare.by(getName, Ordering.Natural());
        
        Person person1 = new Person("Alice");
        Person person2 = new Person("Bob");
        
        assertTrue(ordering.compare(person1, person2) < 0);
    }
    
    @Test
    public void by_handlesNulls() {
        Apply<Integer, Integer> identity = new Apply<Integer, Integer>() {
            @Override
            public Integer apply(Integer i) {
                return i;
            }
        };
        
        Ordering<Integer> ordering = Compare.by(identity);
        
        assertEquals(0, ordering.compare(null, null));
    }
    
    @Test
    public void byOption_withTransformerFunction() {
        class Person {
            Option<String> nickname;
            
            Person(Option<String> nickname) {
                this.nickname = nickname;
            }
        }
        
        Apply<Person, Option<String>> getNickname = new Apply<Person, Option<String>>() {
            @Override
            public Option<String> apply(Person p) {
                return p.nickname;
            }
        };
        
        Ordering<Person> ordering = Compare.byOption(getNickname);
        
        Person person1 = new Person(Option.<String>None());
        Person person2 = new Person(Some("Al"));
        Person person3 = new Person(Some("Bo"));
        
        assertTrue(ordering.compare(person1, person2) > 0);
        assertTrue(ordering.compare(person2, person3) < 0);
    }
    
    @Test
    public void byMap_comparesMapsByEntries() {
        Ordering<Map<String, Integer>> ordering = Compare.byMap();
        
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
        Ordering<String> stringOrdering = Compare.byNatural();
        Ordering<Integer> intOrdering = Compare.byNatural();
        
        Ordering<Map<String, Integer>> ordering = Compare.byMap(stringOrdering, intOrdering);
        
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("a", 1);
        
        Map<String, Integer> map2 = new HashMap<>();
        map2.put("b", 1);
        
        assertTrue(ordering.compare(map1, map2) < 0);
    }
    
    @Test
    public void byPair_comparesPairs() {
        Ordering<Pair<Integer, String>> ordering = Compare.byPair(
            Ordering.Natural(), 
            Ordering.Natural()
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
        Ordering<Tuple3<Integer, String, Double>> ordering = Compare.byTuple(
            Ordering.Natural(),
            Ordering.Natural(),
            Ordering.Natural()
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
        Ordering<Tuple4<Integer, String, Double, Boolean>> ordering = Compare.byTuple(
            Ordering.Natural(),
            Ordering.Natural(),
            Ordering.Natural(),
            new Ordering<Boolean>() {
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
        Ordering<Either<Integer, String>> ordering = Compare.byEither();
        
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
        Ordering<Either3<Integer, String, Double>> ordering = Compare.byEither3();
        
        Either3<Integer, String, Double> left = Either3.left(1);
        Either3<Integer, String, Double> middle = Either3.middle("a");
        Either3<Integer, String, Double> right = Either3.right(1.0);
        
        assertTrue(ordering.compare(left, middle) < 0);
        assertTrue(ordering.compare(middle, right) < 0);
        assertTrue(ordering.compare(left, right) < 0);
    }
    
    @Test
    public void byKey_comparesMapEntries() {
        Ordering<Map.Entry<Integer, String>> ordering = Compare.byKey();
        
        Pair<Integer, String> entry1 = Pair.of(1, "z");
        Pair<Integer, String> entry2 = Pair.of(2, "a");
        
        assertTrue(ordering.compare(entry1, entry2) < 0);
    }
    
    @Test
    public void byValue_comparesMapEntries() {
        Ordering<Map.Entry<Integer, String>> ordering = Compare.byValue();
        
        Pair<Integer, String> entry1 = Pair.of(5, "a");
        Pair<Integer, String> entry2 = Pair.of(1, "z");
        
        assertTrue(ordering.compare(entry1, entry2) < 0);
    }
    
    @Test
    public void by_1_comparesTuplesByFirstElement() {
        Tuple2<Integer, String> tuple1 = Tuple.of(1, "z");
        Tuple2<Integer, String> tuple2 = Tuple.of(2, "a");
        
        assertTrue(Compare.by_1.compare(tuple1, tuple2) < 0);
    }
    
    @Test
    public void by_2_comparesTuplesBySecondElement() {
        Tuple2<Integer, String> tuple1 = Tuple.of(5, "a");
        Tuple2<Integer, String> tuple2 = Tuple.of(1, "z");
        
        assertTrue(Compare.by_2.compare(tuple1, tuple2) < 0);
    }
    
    @Test
    public void by_3_comparesTuplesByThirdElement() {
        Tuple3<Integer, String, Double> tuple1 = Tuple.of(5, "z", 1.0);
        Tuple3<Integer, String, Double> tuple2 = Tuple.of(1, "a", 2.0);
        
        assertTrue(Compare.by_3.compare(tuple1, tuple2) < 0);
    }
    
    @Test
    public void by_4_comparesTuplesByFourthElement() {
        Tuple4<Integer, String, Double, Boolean> tuple1 = Tuple.of(5, "z", 2.0, false);
        Tuple4<Integer, String, Double, Boolean> tuple2 = Tuple.of(1, "a", 1.0, true);
        
        assertTrue(Compare.by_4.compare(tuple1, tuple2) < 0);
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
        
        Apply<Person, String> getFirstName = new Apply<Person, String>() {
            @Override
            public String apply(Person p) {
                return p.firstName;
            }
        };
        
        Apply<Person, String> getLastName = new Apply<Person, String>() {
            @Override
            public String apply(Person p) {
                return p.lastName;
            }
        };
        
        Ordering<Person> ordering = Compare.by(getFirstName).then(Compare.by(getLastName));
        
        Person person1 = new Person("Alice", "Smith");
        Person person2 = new Person("Alice", "Jones");
        Person person3 = new Person("Bob", "Smith");
        
        assertTrue(ordering.compare(person1, person2) > 0);
        assertTrue(ordering.compare(person1, person3) < 0);
    }
    
    @Test
    public void ordering_reverse_reversesOrder() {
        Ordering<Integer> natural = Compare.byNatural();
        Ordering<Integer> reversed = natural.reverse();
        
        assertTrue(natural.compare(1, 2) < 0);
        assertTrue(reversed.compare(1, 2) > 0);
    }
}
