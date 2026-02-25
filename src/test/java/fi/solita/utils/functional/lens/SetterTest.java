package fi.solita.utils.functional.lens;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Collections.newSortedSet;
import static fi.solita.utils.functional.Option.Some;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.junit.Test;

import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Function;
import fi.solita.utils.functional.Function2;
import fi.solita.utils.functional.Option;

public class SetterTest {
    
    // Test classes
    static class Person {
        final String name;
        final int age;
        
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
    
    static class Address {
        final String street;
        final String city;
        
        public Address(String street, String city) {
            this.street = street;
            this.city = city;
        }
    }
    
    static class PersonWithAddress {
        final String name;
        final Address address;
        
        public PersonWithAddress(String name, Address address) {
            this.name = name;
            this.address = address;
        }
    }
    
    static class Container<T> {
        final T value;
        
        public Container(T value) {
            this.value = value;
        }
    }
    
    static class CollectionHolder {
        final List<String> items;
        final Set<Integer> numbers;
        final SortedSet<String> sortedItems;
        final Option<String> optionalValue;
        final Collection<String> collection;
        
        public CollectionHolder(List<String> items, Set<Integer> numbers, 
                               SortedSet<String> sortedItems, Option<String> optionalValue,
                               Collection<String> collection) {
            this.items = items;
            this.numbers = numbers;
            this.sortedItems = sortedItems;
            this.optionalValue = optionalValue;
            this.collection = collection;
        }
    }
    
    // Setters for Person
    static final Setter<Person, String> nameSetter = Setter.of(new Function2<Person, Apply<String, String>, Person>() {
        @Override
        public Person apply(Person person, Apply<String, String> f) {
            return new Person(f.apply(person.name), person.age);
        }
    });
    
    static final Setter<Person, Integer> ageSetter = Setter.of(new Function2<Person, Apply<Integer, Integer>, Person>() {
        @Override
        public Person apply(Person person, Apply<Integer, Integer> f) {
            return new Person(person.name, f.apply(person.age));
        }
    });
    
    // Setters for Address
    static final Setter<Address, String> streetSetter = Setter.of(new Function2<Address, Apply<String, String>, Address>() {
        @Override
        public Address apply(Address address, Apply<String, String> f) {
            return new Address(f.apply(address.street), address.city);
        }
    });
    
    static final Setter<Address, String> citySetter = Setter.of(new Function2<Address, Apply<String, String>, Address>() {
        @Override
        public Address apply(Address address, Apply<String, String> f) {
            return new Address(address.street, f.apply(address.city));
        }
    });
    
    // Setters for PersonWithAddress
    static final Setter<PersonWithAddress, Address> addressSetter = Setter.of(new Function2<PersonWithAddress, Apply<Address, Address>, PersonWithAddress>() {
        @Override
        public PersonWithAddress apply(PersonWithAddress person, Apply<Address, Address> f) {
            return new PersonWithAddress(person.name, f.apply(person.address));
        }
    });
    
    // Setter for Container
    static final <T> Setter<Container<T>, T> valueSetter() {
        return Setter.of(new Function2<Container<T>, Apply<T, T>, Container<T>>() {
            @Override
            public Container<T> apply(Container<T> container, Apply<T, T> f) {
                return new Container<T>(f.apply(container.value));
            }
        });
    }
    
    // Setters for CollectionHolder
    static final Setter<CollectionHolder, List<String>> itemsSetter = Setter.of(new Function2<CollectionHolder, Apply<List<String>, List<String>>, CollectionHolder>() {
        @Override
        public CollectionHolder apply(CollectionHolder holder, Apply<List<String>, List<String>> f) {
            return new CollectionHolder(f.apply(holder.items), holder.numbers, holder.sortedItems, holder.optionalValue, holder.collection);
        }
    });
    
    static final Setter<CollectionHolder, Set<Integer>> numbersSetter = Setter.of(new Function2<CollectionHolder, Apply<Set<Integer>, Set<Integer>>, CollectionHolder>() {
        @Override
        public CollectionHolder apply(CollectionHolder holder, Apply<Set<Integer>, Set<Integer>> f) {
            return new CollectionHolder(holder.items, f.apply(holder.numbers), holder.sortedItems, holder.optionalValue, holder.collection);
        }
    });
    
    static final Setter<CollectionHolder, SortedSet<String>> sortedItemsSetter = Setter.of(new Function2<CollectionHolder, Apply<SortedSet<String>, SortedSet<String>>, CollectionHolder>() {
        @Override
        public CollectionHolder apply(CollectionHolder holder, Apply<SortedSet<String>, SortedSet<String>> f) {
            return new CollectionHolder(holder.items, holder.numbers, f.apply(holder.sortedItems), holder.optionalValue, holder.collection);
        }
    });
    
    static final Setter<CollectionHolder, Option<String>> optionalValueSetter = Setter.of(new Function2<CollectionHolder, Apply<Option<String>, Option<String>>, CollectionHolder>() {
        @Override
        public CollectionHolder apply(CollectionHolder holder, Apply<Option<String>, Option<String>> f) {
            return new CollectionHolder(holder.items, holder.numbers, holder.sortedItems, f.apply(holder.optionalValue), holder.collection);
        }
    });
    
    static final Setter<CollectionHolder, Iterable<String>> iterableItemsSetter = Setter.of(new Function2<CollectionHolder, Apply<Iterable<String>, Iterable<String>>, CollectionHolder>() {
        @Override
        public CollectionHolder apply(CollectionHolder holder, Apply<Iterable<String>, Iterable<String>> f) {
            Iterable<String> transformed = f.apply(holder.items);
            return new CollectionHolder(newList(transformed), holder.numbers, holder.sortedItems, holder.optionalValue, holder.collection);
        }
    });
    
    static final Setter<CollectionHolder, Collection<String>> collectionSetter = Setter.of(new Function2<CollectionHolder, Apply<Collection<String>, Collection<String>>, CollectionHolder>() {
        @Override
        public CollectionHolder apply(CollectionHolder holder, Apply<Collection<String>, Collection<String>> f) {
            return new CollectionHolder(holder.items, holder.numbers, holder.sortedItems, holder.optionalValue, f.apply(holder.collection));
        }
    });
    
    // Setter for individual string elements
    static final Setter<String, String> stringToUpperSetter = Setter.of(new Function2<String, Apply<String, String>, String>() {
        @Override
        public String apply(String s, Apply<String, String> f) {
            return f.apply(s);
        }
    });
    
    // Setter for individual integer elements
    static final Setter<Integer, Integer> intSetter = Setter.of(new Function2<Integer, Apply<Integer, Integer>, Integer>() {
        @Override
        public Integer apply(Integer i, Apply<Integer, Integer> f) {
            return f.apply(i);
        }
    });
    
    @Test
    public void testSet_BasicValue() {
        Person person = new Person("Alice", 30);
        
        Person modified = nameSetter.set(person, "Bob");
        
        assertEquals("Bob", modified.name);
        assertEquals(30, modified.age);
        // Original unchanged
        assertEquals("Alice", person.name);
    }
    
    @Test
    public void testSet_NullObject_ReturnsNull() {
        Person modified = nameSetter.set(null, "Bob");
        
        assertNull(modified);
    }
    
    @Test
    public void testModify_WithFunction() {
        Person person = new Person("Alice", 30);
        
        Person modified = nameSetter.modify(person, new Apply<String, String>() {
            @Override
            public String apply(String name) {
                return name.toUpperCase();
            }
        });
        
        assertEquals("ALICE", modified.name);
        assertEquals(30, modified.age);
    }
    
    @Test
    public void testModify_NullObject_ReturnsNull() {
        Person modified = nameSetter.modify(null, new Apply<String, String>() {
            @Override
            public String apply(String name) {
                return name.toUpperCase();
            }
        });
        
        assertNull(modified);
    }
    
    @Test
    public void testModify_IncrementAge() {
        Person person = new Person("Alice", 30);
        
        Person modified = ageSetter.modify(person, new Apply<Integer, Integer>() {
            @Override
            public Integer apply(Integer age) {
                return age + 1;
            }
        });
        
        assertEquals("Alice", modified.name);
        assertEquals(31, modified.age);
    }
    
    @Test
    public void testAndThen_ComposeTwoSetters() {
        Address address = new Address("Main St", "Boston");
        PersonWithAddress person = new PersonWithAddress("Alice", address);
        
        Setter<PersonWithAddress, String> composedSetter = addressSetter.andThen(citySetter);
        
        PersonWithAddress modified = composedSetter.set(person, "New York");
        
        assertEquals("Alice", modified.name);
        assertEquals("Main St", modified.address.street);
        assertEquals("New York", modified.address.city);
    }
    
    @Test
    public void testAndThen_MultipleCompositions() {
        Address address = new Address("Main St", "Boston");
        PersonWithAddress person = new PersonWithAddress("Alice", address);
        
        Setter<PersonWithAddress, String> composedSetter = addressSetter.andThen(streetSetter);
        
        PersonWithAddress modified = composedSetter.modify(person, new Apply<String, String>() {
            @Override
            public String apply(String street) {
                return street + " Apt 5";
            }
        });
        
        assertEquals("Alice", modified.name);
        assertEquals("Main St Apt 5", modified.address.street);
        assertEquals("Boston", modified.address.city);
    }
    
    @Test
    public void testOf_CreateSetterFromApplyBi() {
        Setter<Person, String> setter = Setter.of(nameSetter);
        
        Person person = new Person("Alice", 30);
        Person modified = setter.set(person, "Bob");
        
        assertEquals("Bob", modified.name);
        assertEquals(30, modified.age);
    }
    
    @Test
    public void testEachList_ModifyAllElements() {
        List<String> items = newList("a", "b", "c");
        CollectionHolder holder = new CollectionHolder(items, Collections.<Integer>emptySet(), Collections.<String>emptySortedSet(), Option.<String>None(), Collections.<String>emptyList());
        
        Setter<CollectionHolder, String> eachSetter = Setter.eachList(itemsSetter, stringToUpperSetter);
        
        CollectionHolder modified = eachSetter.modify(holder, new Apply<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        });
        
        assertEquals(newList("A", "B", "C"), modified.items);
    }
    
    @Test
    public void testEachList_EmptyList() {
        CollectionHolder holder = new CollectionHolder(Collections.<String>emptyList(), Collections.<Integer>emptySet(), Collections.<String>emptySortedSet(), Option.<String>None(), Collections.<String>emptyList());
        
        Setter<CollectionHolder, String> eachSetter = Setter.eachList(itemsSetter, stringToUpperSetter);
        
        CollectionHolder modified = eachSetter.modify(holder, new Apply<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        });
        
        assertTrue(modified.items.isEmpty());
    }
    
    @Test
    public void testEachSet_ModifyAllElements() {
        Set<Integer> numbers = newSet(1, 2, 3);
        CollectionHolder holder = new CollectionHolder(Collections.<String>emptyList(), numbers, Collections.<String>emptySortedSet(), Option.<String>None(), Collections.<String>emptyList());
        
        Setter<CollectionHolder, Integer> eachSetter = Setter.eachSet(numbersSetter, intSetter);
        
        CollectionHolder modified = eachSetter.modify(holder, new Apply<Integer, Integer>() {
            @Override
            public Integer apply(Integer i) {
                return i * 2;
            }
        });
        
        assertEquals(newSet(2, 4, 6), modified.numbers);
    }
    
    @Test
    public void testEachSet_EmptySet() {
        CollectionHolder holder = new CollectionHolder(Collections.<String>emptyList(), Collections.<Integer>emptySet(), Collections.<String>emptySortedSet(), Option.<String>None(), Collections.<String>emptyList());
        
        Setter<CollectionHolder, Integer> eachSetter = Setter.eachSet(numbersSetter, intSetter);
        
        CollectionHolder modified = eachSetter.modify(holder, new Apply<Integer, Integer>() {
            @Override
            public Integer apply(Integer i) {
                return i * 2;
            }
        });
        
        assertTrue(modified.numbers.isEmpty());
    }
    
    @Test
    public void testEachSortedSet_ModifyAllElements() {
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        };
        SortedSet<String> sortedItems = newSortedSet(comparator, newList("a", "b", "c"));
        CollectionHolder holder = new CollectionHolder(Collections.<String>emptyList(), Collections.<Integer>emptySet(), sortedItems, Option.<String>None(), Collections.<String>emptyList());
        
        Setter<CollectionHolder, String> eachSetter = Setter.eachSortedSet(sortedItemsSetter, stringToUpperSetter);
        
        CollectionHolder modified = eachSetter.modify(holder, new Apply<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        });
        
        assertEquals(newList("A", "B", "C"), newList(modified.sortedItems));
    }
    
    @Test
    public void testEachSortedSet_PreservesComparator() {
        final Comparator<String> reverseComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1); // Reverse order
            }
        };
        SortedSet<String> sortedItems = newSortedSet(reverseComparator, newList("a", "b", "c"));
        CollectionHolder holder = new CollectionHolder(Collections.<String>emptyList(), Collections.<Integer>emptySet(), sortedItems, Option.<String>None(), Collections.<String>emptyList());
        
        Setter<CollectionHolder, String> eachSetter = Setter.eachSortedSet(sortedItemsSetter, stringToUpperSetter);
        
        CollectionHolder modified = eachSetter.modify(holder, new Apply<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        });
        
        // Should preserve reverse order
        assertEquals(newList("C", "B", "A"), newList(modified.sortedItems));
        assertEquals(reverseComparator, modified.sortedItems.comparator());
    }
    
    @Test
    public void testEachSortedSet_NullValue() {
        CollectionHolder holder = new CollectionHolder(Collections.<String>emptyList(), Collections.<Integer>emptySet(), null, Option.<String>None(), Collections.<String>emptyList());
        
        Setter<CollectionHolder, String> eachSetter = Setter.eachSortedSet(sortedItemsSetter, stringToUpperSetter);
        
        CollectionHolder modified = eachSetter.modify(holder, new Apply<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        });
        
        assertNull(modified.sortedItems);
    }
    
    @Test
    public void testEachCollection_ModifyAllElements() {
        Collection<String> collection = newList("x", "y", "z");
        CollectionHolder holder = new CollectionHolder(Collections.<String>emptyList(), Collections.<Integer>emptySet(), Collections.<String>emptySortedSet(), Option.<String>None(), collection);
        
        Setter<CollectionHolder, String> eachSetter = Setter.eachCollection(collectionSetter, stringToUpperSetter);
        
        CollectionHolder modified = eachSetter.modify(holder, new Apply<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        });
        
        assertEquals(newList("X", "Y", "Z"), newList(modified.collection));
    }
    
    @Test
    public void testEachIterable_ModifyAllElements() {
        List<String> items = newList("a", "b", "c");
        CollectionHolder holder = new CollectionHolder(items, Collections.<Integer>emptySet(), Collections.<String>emptySortedSet(), Option.<String>None(), Collections.<String>emptyList());
        
        Setter<CollectionHolder, String> eachSetter = Setter.eachIterable(iterableItemsSetter, stringToUpperSetter);
        
        CollectionHolder modified = eachSetter.modify(holder, new Apply<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        });
        
        assertEquals(newList("A", "B", "C"), newList(modified.items));
    }
    
    @Test
    public void testEachOption_SomeValue() {
        CollectionHolder holder = new CollectionHolder(Collections.<String>emptyList(), Collections.<Integer>emptySet(), Collections.<String>emptySortedSet(), Some("value"), Collections.<String>emptyList());
        
        Setter<CollectionHolder, String> eachSetter = Setter.eachOption(optionalValueSetter, stringToUpperSetter);
        
        CollectionHolder modified = eachSetter.modify(holder, new Apply<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        });
        
        assertEquals(Some("VALUE"), modified.optionalValue);
    }
    
    @Test
    public void testEachOption_NoneValue() {
        CollectionHolder holder = new CollectionHolder(Collections.<String>emptyList(), Collections.<Integer>emptySet(), Collections.<String>emptySortedSet(), Option.<String>None(), Collections.<String>emptyList());
        
        Setter<CollectionHolder, String> eachSetter = Setter.eachOption(optionalValueSetter, stringToUpperSetter);
        
        CollectionHolder modified = eachSetter.modify(holder, new Apply<String, String>() {
            @Override
            public String apply(String s) {
                return s.toUpperCase();
            }
        });
        
        assertEquals(Option.<String>None(), modified.optionalValue);
    }
    
    @Test
    public void testApply_DelegateToF2() {
        Person person = new Person("Alice", 30);
        
        Person modified = nameSetter.apply(person, Function.<String, String>constant("Bob"));
        
        assertEquals("Bob", modified.name);
        assertEquals(30, modified.age);
    }
    
    @Test
    public void testChainedModifications() {
        Person person = new Person("Alice", 30);
        
        // Chain multiple modifications
        Person modified = ageSetter.modify(
            nameSetter.modify(person, new Apply<String, String>() {
                @Override
                public String apply(String name) {
                    return name.toUpperCase();
                }
            }), 
            new Apply<Integer, Integer>() {
                @Override
                public Integer apply(Integer age) {
                    return age + 5;
                }
            }
        );
        
        assertEquals("ALICE", modified.name);
        assertEquals(35, modified.age);
    }
    
    @Test
    public void testGenericSetter() {
        Container<String> container = new Container<String>("hello");
        Setter<Container<String>, String> setter = valueSetter();
        
        Container<String> modified = setter.set(container, "world");
        
        assertEquals("world", modified.value);
    }
    
    @Test
    public void testSetterImmutability() {
        Person person = new Person("Alice", 30);
        
        // Modify through setter
        Person modified = nameSetter.set(person, "Bob");
        
        // Original should be unchanged
        assertEquals("Alice", person.name);
        assertEquals(30, person.age);
        
        // Modified should have new value
        assertEquals("Bob", modified.name);
        assertEquals(30, modified.age);
    }
}
