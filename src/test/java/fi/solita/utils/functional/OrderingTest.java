package fi.solita.utils.functional;

import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;

import org.junit.Test;

public class OrderingTest {

    private static class Person {
        final String name;
        final int age;
        
        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }

    @Test
    public void of_wrapsComparator() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        
        Ordering<Integer> ordering = Ordering.of(comparator);
        
        assertTrue(ordering.compare(1, 2) < 0);
        assertTrue(ordering.compare(2, 1) > 0);
        assertEquals(0, ordering.compare(5, 5));
    }
    
    @Test
    public void of_returnsOrderingIfAlreadyOrdering() {
        Ordering<Integer> original = Ordering.Natural();
        Ordering<Integer> wrapped = Ordering.of(original);
        
        assertTrue(original == wrapped);
    }
    
    @Test
    public void of_handlesNullsInComparison() {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };
        
        Ordering<Integer> ordering = Ordering.of(comparator);
        
        assertEquals(0, ordering.compare(null, null));
    }

    @Test
    public void Natural_comparesComparableObjects() {
        Ordering<Integer> ordering = Ordering.Natural();
        
        assertTrue(ordering.compare(1, 2) < 0);
        assertTrue(ordering.compare(2, 1) > 0);
        assertEquals(0, ordering.compare(5, 5));
    }
    
    @Test
    public void Natural_comparesStrings() {
        Ordering<String> ordering = Ordering.Natural();
        
        assertTrue(ordering.compare("a", "b") < 0);
        assertTrue(ordering.compare("z", "a") > 0);
        assertEquals(0, ordering.compare("hello", "hello"));
    }
    
    @Test
    public void Natural_handlesNulls() {
        Ordering<Integer> ordering = Ordering.Natural();
        
        assertEquals(0, ordering.compare(null, null));
    }

    @Test
    public void reverse_reversesOrdering() {
        Ordering<Integer> natural = Ordering.Natural();
        Ordering<Integer> reversed = natural.reverse();
        
        assertTrue(reversed.compare(1, 2) > 0);
        assertTrue(reversed.compare(2, 1) < 0);
        assertEquals(0, reversed.compare(5, 5));
    }
    
    @Test
    public void reverse_reversesStringOrdering() {
        Ordering<String> natural = Ordering.Natural();
        Ordering<String> reversed = natural.reverse();
        
        assertTrue(reversed.compare("a", "b") > 0);
        assertTrue(reversed.compare("z", "a") < 0);
        assertEquals(0, reversed.compare("hello", "hello"));
    }

    @Test
    public void then_chainsComparators() {
        Ordering<Person> ordering = Compare.by(new Apply<Person, String>() {
            @Override
            public String apply(Person t) {
                return t.name;
            }
        }).then(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o1.age, o2.age);
            }
        });
        
        Person alice25 = new Person("Alice", 25);
        Person alice30 = new Person("Alice", 30);
        Person bob25 = new Person("Bob", 25);
        
        assertTrue(ordering.compare(alice25, alice30) < 0);
        assertTrue(ordering.compare(alice30, alice25) > 0);
        assertTrue(ordering.compare(alice25, bob25) < 0);
        assertEquals(0, ordering.compare(alice25, alice25));
    }
    
    @Test
    public void then_combinesWithZeroOrdering() {
        Ordering<Integer> natural = Ordering.Natural();
        Ordering<Integer> zero = natural.zero();
        Ordering<Integer> combined = zero.then(natural);
        
        assertTrue(combined.compare(1, 2) < 0);
        assertTrue(combined.compare(2, 1) > 0);
        assertEquals(0, combined.compare(5, 5));
    }

    @Test
    public void thenBy_chainsWithNaturalOrdering() {
        Ordering<Person> ordering = Compare.by(new Apply<Person, String>() {
            @Override
            public String apply(Person t) {
                return t.name;
            }
        }).thenBy(new Apply<Person, Integer>() {
            @Override
            public Integer apply(Person t) {
                return t.age;
            }
        });
        
        Person alice25 = new Person("Alice", 25);
        Person alice30 = new Person("Alice", 30);
        Person bob25 = new Person("Bob", 25);
        
        assertTrue(ordering.compare(alice25, alice30) < 0);
        assertTrue(ordering.compare(alice30, alice25) > 0);
        assertTrue(ordering.compare(alice25, bob25) < 0);
    }

    @Test
    public void thenByOption_handlesOptions() {
        Ordering<Person> ordering = Compare.by(new Apply<Person, String>() {
            @Override
            public String apply(Person t) {
                return t.name;
            }
        }).thenByOption(new Apply<Person, Option<Integer>>() {
            @Override
            public Option<Integer> apply(Person t) {
                return t.age > 0 ? Some(t.age) : Option.<Integer>None();
            }
        });
        
        Person alice25 = new Person("Alice", 25);
        Person alice30 = new Person("Alice", 30);
        
        assertTrue(ordering.compare(alice25, alice30) < 0);
        assertTrue(ordering.compare(alice30, alice25) > 0);
    }

    @Test
    public void thenBy_withCustomComparator() {
        Comparator<Integer> reverseIntComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        };
        
        Ordering<Person> ordering = Compare.by(new Apply<Person, String>() {
            @Override
            public String apply(Person t) {
                return t.name;
            }
        }).thenBy(new Apply<Person, Integer>() {
            @Override
            public Integer apply(Person t) {
                return t.age;
            }
        }, reverseIntComparator);
        
        Person alice25 = new Person("Alice", 25);
        Person alice30 = new Person("Alice", 30);
        
        assertTrue(ordering.compare(alice25, alice30) > 0);
        assertTrue(ordering.compare(alice30, alice25) < 0);
    }

    @Test
    public void thenByOption_withCustomComparator() {
        Comparator<Integer> reverseIntComparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        };
        
        Ordering<Person> ordering = Compare.by(new Apply<Person, String>() {
            @Override
            public String apply(Person t) {
                return t.name;
            }
        }).thenByOption(new Apply<Person, Option<Integer>>() {
            @Override
            public Option<Integer> apply(Person t) {
                return Some(t.age);
            }
        }, reverseIntComparator);
        
        Person alice25 = new Person("Alice", 25);
        Person alice30 = new Person("Alice", 30);
        
        assertTrue(ordering.compare(alice25, alice30) > 0);
        assertTrue(ordering.compare(alice30, alice25) < 0);
    }

    @Test
    public void apply_combinesOrderings() {
        Ordering<Integer> natural = Ordering.Natural();
        Ordering<Integer> zero = natural.zero();
        
        Ordering<Integer> combined = natural.apply(Pair.of(zero, natural));
        
        assertTrue(combined.compare(1, 2) < 0);
        assertTrue(combined.compare(2, 1) > 0);
        assertEquals(0, combined.compare(5, 5));
    }

    @Test
    public void zero_returnsIdentityOrdering() {
        Ordering<Integer> natural = Ordering.Natural();
        Ordering<Integer> zero = natural.zero();
        
        assertEquals(0, zero.compare(1, 2));
        assertEquals(0, zero.compare(2, 1));
        assertEquals(0, zero.compare(5, 5));
    }
    
    @Test
    public void zero_isIdentityForThen() {
        Ordering<Integer> natural = Ordering.Natural();
        Ordering<Integer> zero = natural.zero();
        
        Ordering<Integer> leftIdentity = zero.then(natural);
        Ordering<Integer> rightIdentity = natural.then(zero);
        
        assertTrue(leftIdentity.compare(1, 2) < 0);
        assertTrue(rightIdentity.compare(1, 2) < 0);
    }

    @Test
    public void complexChaining_combinesMultipleOperations() {
        Ordering<Person> ordering = Compare.by(new Apply<Person, String>() {
            @Override
            public String apply(Person t) {
                return t.name;
            }
        })
        .reverse()
        .thenBy(new Apply<Person, Integer>() {
            @Override
            public Integer apply(Person t) {
                return t.age;
            }
        });
        
        Person alice25 = new Person("Alice", 25);
        Person alice30 = new Person("Alice", 30);
        Person bob25 = new Person("Bob", 25);
        
        assertTrue(ordering.compare(alice25, bob25) > 0);
        assertTrue(ordering.compare(bob25, alice25) < 0);
        assertTrue(ordering.compare(alice25, alice30) < 0);
    }
}
