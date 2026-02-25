package fi.solita.utils.functional.lens;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Collections.newSortedSet;
import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.junit.Test;

import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.ApplyBi;
import fi.solita.utils.functional.Either;
import fi.solita.utils.functional.Function;
import fi.solita.utils.functional.Function2;
import fi.solita.utils.functional.Monoids;
import fi.solita.utils.functional.Option;
import fi.solita.utils.functional.Pair;
import fi.solita.utils.functional.Tuple;
import fi.solita.utils.functional.Tuple2;
import fi.solita.utils.functional.Tuple3;

public class LensTest {
    
    // Test model classes
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
    
    static class Department {
        final String name;
        final List<Employee> employees;
        
        public Department(String name, List<Employee> employees) {
            this.name = name;
            this.employees = employees;
        }
    }
    
    static class Employee {
        final String name;
        final Option<Integer> salary;
        
        public Employee(String name, Option<Integer> salary) {
            this.name = name;
            this.salary = salary;
        }
    }
    
    // Getters and builders
    static final Apply<Person, String> personName = new Apply<Person, String>() {
        public String apply(Person p) { return p.name; }
    };
    
    static final Apply<Person, Integer> personAge = new Apply<Person, Integer>() {
        public Integer apply(Person p) { return p.age; }
    };
    
    static final Builder<Person> personBuilder = Builder.of(
        Tuple.of(personName, personAge),
        new Function2<String, Integer, Person>() {
            public Person apply(String name, Integer age) {
                return new Person(name, age);
            }
        }
    );
    
    static final Apply<Address, String> addressStreet = new Apply<Address, String>() {
        public String apply(Address a) { return a.street; }
    };
    
    static final Apply<Address, String> addressCity = new Apply<Address, String>() {
        public String apply(Address a) { return a.city; }
    };
    
    static final Builder<Address> addressBuilder = Builder.of(
        Tuple.of(addressStreet, addressCity),
        new Function2<String, String, Address>() {
            public Address apply(String street, String city) {
                return new Address(street, city);
            }
        }
    );
    
    static final Apply<PersonWithAddress, String> personWithAddressName = new Apply<PersonWithAddress, String>() {
        public String apply(PersonWithAddress p) { return p.name; }
    };
    
    static final Apply<PersonWithAddress, Address> personWithAddressAddress = new Apply<PersonWithAddress, Address>() {
        public Address apply(PersonWithAddress p) { return p.address; }
    };
    
    static final Builder<PersonWithAddress> personWithAddressBuilder = Builder.of(
        Tuple.of(personWithAddressName, personWithAddressAddress),
        new Function2<String, Address, PersonWithAddress>() {
            public PersonWithAddress apply(String name, Address address) {
                return new PersonWithAddress(name, address);
            }
        }
    );
    
    static final Apply<Department, String> departmentName = new Apply<Department, String>() {
        public String apply(Department d) { return d.name; }
    };
    
    static final Apply<Department, List<Employee>> departmentEmployees = new Apply<Department, List<Employee>>() {
        public List<Employee> apply(Department d) { return d.employees; }
    };
    
    static final Builder<Department> departmentBuilder = Builder.of(
        Tuple.of(departmentName, departmentEmployees),
        new Function2<String, List<Employee>, Department>() {
            public Department apply(String name, List<Employee> employees) {
                return new Department(name, employees);
            }
        }
    );
    
    static final Apply<Employee, String> employeeName = new Apply<Employee, String>() {
        public String apply(Employee e) { return e.name; }
    };
    
    static final Apply<Employee, Option<Integer>> employeeSalary = new Apply<Employee, Option<Integer>>() {
        public Option<Integer> apply(Employee e) { return e.salary; }
    };
    
    static final Builder<Employee> employeeBuilder = Builder.of(
        Tuple.of(employeeName, employeeSalary),
        new Function2<String, Option<Integer>, Employee>() {
            public Employee apply(String name, Option<Integer> salary) {
                return new Employee(name, salary);
            }
        }
    );
    
    // Basic lens tests
    @Test
    public void testLensOfWithBuilder() {
        Person person = new Person("John", 30);
        Lens<Person, String> nameLens = Lens.of(personName, personBuilder);
        
        assertEquals("John", nameLens.get(person));
        assertEquals("John", nameLens.apply(person));
    }
    
    @Test
    public void testLensOfWithSetter() {
        Person person = new Person("John", 30);
        Lens<Person, String> nameLens = Lens.of(personName, new ApplyBi<Person, Apply<String, String>, Person>() {
            public Person apply(Person p, Apply<String, String> f) {
                return new Person(f.apply(p.name), p.age);
            }
        });
        
        assertEquals("John", nameLens.get(person));
    }
    
    @Test
    public void testLensGet() {
        Person person = new Person("Jane", 25);
        Lens<Person, String> nameLens = Lens.of(personName, personBuilder);
        
        assertEquals("Jane", nameLens.get(person));
        
        Lens<Person, Integer> ageLens = Lens.of(personAge, personBuilder);
        assertEquals(Integer.valueOf(25), ageLens.get(person));
    }
    
    @Test
    public void testLensGetWithNull() {
        Lens<Person, String> nameLens = Lens.of(personName, personBuilder);
        assertNull(nameLens.get(null));
    }
    
    @Test
    public void testLensSet() {
        Person person = new Person("John", 30);
        Lens<Person, String> nameLens = Lens.of(personName, personBuilder);
        
        Person updated = nameLens.set(person, "Jane");
        
        assertEquals("Jane", updated.name);
        assertEquals(30, updated.age);
        assertEquals("John", person.name); // Original unchanged
    }
    
    @Test
    public void testLensModify() {
        Person person = new Person("John", 30);
        Lens<Person, String> nameLens = Lens.of(personName, personBuilder);
        
        Person updated = nameLens.modify(person, new Apply<String, String>() {
            public String apply(String name) {
                return name.toUpperCase();
            }
        });
        
        assertEquals("JOHN", updated.name);
        assertEquals(30, updated.age);
        assertEquals("John", person.name); // Original unchanged
    }
    
    @Test
    public void testLensModifyAge() {
        Person person = new Person("John", 30);
        Lens<Person, Integer> ageLens = Lens.of(personAge, personBuilder);
        
        Person updated = ageLens.modify(person, new Apply<Integer, Integer>() {
            public Integer apply(Integer age) {
                return age + 1;
            }
        });
        
        assertEquals("John", updated.name);
        assertEquals(31, updated.age);
    }
    
    @Test
    public void testLensSetWithNull() {
        Lens<Person, String> nameLens = Lens.of(personName, personBuilder);
        Person result = nameLens.set(null, "Jane");
        assertNull(result);
    }
    
    @Test
    public void testLensModifyWithNull() {
        Lens<Person, String> nameLens = Lens.of(personName, personBuilder);
        Person result = nameLens.modify(null, Function.<String>id());
        assertNull(result);
    }
    
    // Lens composition tests
    @Test
    public void testLensAndThen() {
        Address address = new Address("Main St", "Springfield");
        PersonWithAddress person = new PersonWithAddress("John", address);
        
        Lens<PersonWithAddress, Address> addressLens = Lens.of(personWithAddressAddress, personWithAddressBuilder);
        Lens<Address, String> cityLens = Lens.of(addressCity, addressBuilder);
        Lens<PersonWithAddress, String> composedLens = addressLens.andThen(cityLens);
        
        assertEquals("Springfield", composedLens.get(person));
    }
    
    @Test
    public void testLensAndThenSet() {
        Address address = new Address("Main St", "Springfield");
        PersonWithAddress person = new PersonWithAddress("John", address);
        
        Lens<PersonWithAddress, Address> addressLens = Lens.of(personWithAddressAddress, personWithAddressBuilder);
        Lens<Address, String> cityLens = Lens.of(addressCity, addressBuilder);
        Lens<PersonWithAddress, String> composedLens = addressLens.andThen(cityLens);
        
        PersonWithAddress updated = composedLens.set(person, "Boston");
        
        assertEquals("Boston", updated.address.city);
        assertEquals("Main St", updated.address.street);
        assertEquals("John", updated.name);
        assertEquals("Springfield", person.address.city); // Original unchanged
    }
    
    @Test
    public void testLensAndThenModify() {
        Address address = new Address("Main St", "Springfield");
        PersonWithAddress person = new PersonWithAddress("John", address);
        
        Lens<PersonWithAddress, Address> addressLens = Lens.of(personWithAddressAddress, personWithAddressBuilder);
        Lens<Address, String> streetLens = Lens.of(addressStreet, addressBuilder);
        Lens<PersonWithAddress, String> composedLens = addressLens.andThen(streetLens);
        
        PersonWithAddress updated = composedLens.modify(person, new Apply<String, String>() {
            public String apply(String street) {
                return street + " Apt 1";
            }
        });
        
        assertEquals("Main St Apt 1", updated.address.street);
        assertEquals("Springfield", updated.address.city);
    }
    
    @Test
    public void testLensAndThenWithNull() {
        Lens<PersonWithAddress, Address> addressLens = Lens.of(personWithAddressAddress, personWithAddressBuilder);
        Lens<Address, String> cityLens = Lens.of(addressCity, addressBuilder);
        Lens<PersonWithAddress, String> composedLens = addressLens.andThen(cityLens);
        
        assertNull(composedLens.get(null));
        assertNull(composedLens.set(null, "Boston"));
    }
    
    // Collection lens tests
    @Test
    public void testEachList() {
        Department dept = new Department("Sales", newList(
            new Employee("John", Some(50000)),
            new Employee("Jane", Some(60000))
        ));
        
        Lens<Department, List<Employee>> employeesLens = Lens.of(departmentEmployees, departmentBuilder);
        Lens<Employee, String> nameLens = Lens.of(employeeName, employeeBuilder);
        Lens<Department, Iterable<String>> namesLens = Lens.eachList(employeesLens, nameLens);
        
        List<String> names = newList(namesLens.get(dept));
        assertEquals(newList("John", "Jane"), names);
    }
    
    @Test
    public void testEachListModify() {
        Department dept = new Department("Sales", newList(
            new Employee("John", Some(50000)),
            new Employee("Jane", Some(60000))
        ));
        
        Lens<Department, List<Employee>> employeesLens = Lens.of(departmentEmployees, departmentBuilder);
        Lens<Employee, String> nameLens = Lens.of(employeeName, employeeBuilder);
        Lens<Department, Iterable<String>> namesLens = Lens.eachList(employeesLens, nameLens);
        
        // Modify each name by converting to uppercase
        Department updated = namesLens.modify(dept, new Apply<Iterable<String>, Iterable<String>>() {
            public Iterable<String> apply(Iterable<String> names) {
                // The names iterable contains a single name that needs to be transformed
                String name = newList(names).get(0);
                return newList(name.toUpperCase());
            }
        });
        
        assertEquals(newList("JOHN", "JANE"), newList(namesLens.get(updated)));
    }
    
    @Test
    public void testEachListWithMonoid() {
        Department dept = new Department("Sales", newList(
            new Employee("John", Some(50000)),
            new Employee("Jane", Some(60000))
        ));
        
        Lens<Department, List<Employee>> employeesLens = Lens.of(departmentEmployees, departmentBuilder);
        Lens<Employee, String> salaryLens = Lens.of(employeeName, employeeBuilder);
        Lens<Department, String> namesLens = Lens.eachList(Monoids.stringConcat, employeesLens, salaryLens);
        
        // Concatenates all employee names
        assertEquals("JohnJane", namesLens.get(dept));
    }
    
    @Test
    public void testEachSet() {
        class Container {
            final Set<String> items;
            Container(Set<String> items) { this.items = items; }
        }
        
        final Apply<Container, Set<String>> containerItems = new Apply<Container, Set<String>>() {
            public Set<String> apply(Container c) { return c.items; }
        };
        
        Lens<Container, Set<String>> itemsLens = Lens.of(containerItems, new ApplyBi<Container, Apply<Set<String>,Set<String>>, Container>() {
            public Container apply(Container c, Apply<Set<String>, Set<String>> f) {
                return new Container(f.apply(c.items));
            }
        });
        
        Container container = new Container(newSet("a", "b", "c"));
        Lens<Container, Iterable<String>> eachLens = Lens.eachSet(itemsLens, Lens.<String>id());
        
        Set<String> result = newSet(eachLens.get(container));
        assertEquals(newSet("a", "b", "c"), result);
    }
    
    @Test
    public void testEachSortedSet() {
        class Container {
            final SortedSet<String> items;
            Container(SortedSet<String> items) { this.items = items; }
        }
        
        final Apply<Container, SortedSet<String>> containerItems = new Apply<Container, SortedSet<String>>() {
            public SortedSet<String> apply(Container c) { return c.items; }
        };
        
        Lens<Container, SortedSet<String>> itemsLens = Lens.of(containerItems, new ApplyBi<Container, Apply<SortedSet<String>,SortedSet<String>>, Container>() {
            public Container apply(Container c, Apply<SortedSet<String>, SortedSet<String>> f) {
                return new Container(f.apply(c.items));
            }
        });
        
        Comparator<String> stringComparator = new Comparator<String>() {
            public int compare(String s1, String s2) {
                return s1.compareTo(s2);
            }
        };
        Container container = new Container(newSortedSet(stringComparator, newList("c", "a", "b")));
        Lens<Container, Iterable<String>> eachLens = Lens.eachSortedSet(itemsLens, Lens.<String>id());
        
        List<String> result = newList(eachLens.get(container));
        assertEquals(newList("a", "b", "c"), result);
    }
    
    @Test
    public void testEachCollection() {
        class Container {
            final Collection<Integer> numbers;
            Container(Collection<Integer> numbers) { this.numbers = numbers; }
        }
        
        final Apply<Container, Collection<Integer>> containerNumbers = new Apply<Container, Collection<Integer>>() {
            public Collection<Integer> apply(Container c) { return c.numbers; }
        };
        
        Lens<Container, Collection<Integer>> numbersLens = Lens.of(containerNumbers, new ApplyBi<Container, Apply<Collection<Integer>,Collection<Integer>>, Container>() {
            public Container apply(Container c, Apply<Collection<Integer>, Collection<Integer>> f) {
                return new Container(f.apply(c.numbers));
            }
        });
        
        Container container = new Container(newList(1, 2, 3));
        Lens<Container, Iterable<Integer>> eachLens = Lens.eachCollection(numbersLens, Lens.<Integer>id());
        
        List<Integer> result = newList(eachLens.get(container));
        assertEquals(newList(1, 2, 3), result);
    }
    
    // Option and Either lens tests
    @Test
    public void testEachOption() {
        class Container {
            final Option<String> value;
            Container(Option<String> value) { this.value = value; }
        }
        
        final Apply<Container, Option<String>> containerValue = new Apply<Container, Option<String>>() {
            public Option<String> apply(Container c) { return c.value; }
        };
        
        Lens<Container, Option<String>> valueLens = Lens.of(containerValue, new ApplyBi<Container, Apply<Option<String>,Option<String>>, Container>() {
            public Container apply(Container c, Apply<Option<String>, Option<String>> f) {
                return new Container(f.apply(c.value));
            }
        });
        
        Container container = new Container(Some("hello"));
        Lens<Container, Option<String>> eachLens = Lens.eachOption(valueLens, Lens.<String>id());
        
        assertEquals(Some("hello"), eachLens.get(container));
    }
    
    @Test
    public void testEachOptionWithNone() {
        class Container {
            final Option<String> value;
            Container(Option<String> value) { this.value = value; }
        }
        
        final Apply<Container, Option<String>> containerValue = new Apply<Container, Option<String>>() {
            public Option<String> apply(Container c) { return c.value; }
        };
        
        Lens<Container, Option<String>> valueLens = Lens.of(containerValue, new ApplyBi<Container, Apply<Option<String>,Option<String>>, Container>() {
            public Container apply(Container c, Apply<Option<String>, Option<String>> f) {
                return new Container(f.apply(c.value));
            }
        });
        
        Container container = new Container(None());
        Lens<Container, Option<String>> eachLens = Lens.eachOption(valueLens, Lens.<String>id());
        
        assertEquals(None(), eachLens.get(container));
    }
    
    @Test
    public void testLeftLens() {
        Either<String, Integer> either = Either.left("error");
        
        Lens<Either<String, Integer>, Option<String>> leftLens = Lens.left();
        
        assertEquals(Some("error"), leftLens.get(either));
    }
    
    @Test
    public void testLeftLensWithRight() {
        Either<String, Integer> either = Either.right(42);
        
        Lens<Either<String, Integer>, Option<String>> leftLens = Lens.left();
        
        assertEquals(None(), leftLens.get(either));
    }
    
    @Test
    public void testLeftLensModify() {
        Either<String, Integer> either = Either.left("error");
        
        Lens<Either<String, Integer>, Option<String>> leftLens = Lens.left();
        
        Either<String, Integer> updated = leftLens.modify(either, new Apply<Option<String>, Option<String>>() {
            public Option<String> apply(Option<String> opt) {
                return opt.map(new Apply<String, String>() {
                    public String apply(String s) {
                        return s.toUpperCase();
                    }
                });
            }
        });
        
        assertEquals(Some("ERROR"), Lens.<String, Integer>left().get(updated));
    }
    
    @Test
    public void testRightLens() {
        Either<String, Integer> either = Either.right(42);
        
        Lens<Either<String, Integer>, Option<Integer>> rightLens = Lens.right();
        
        assertEquals(Some(42), rightLens.get(either));
    }
    
    @Test
    public void testRightLensWithLeft() {
        Either<String, Integer> either = Either.left("error");
        
        Lens<Either<String, Integer>, Option<Integer>> rightLens = Lens.right();
        
        assertEquals(None(), rightLens.get(either));
    }
    
    @Test
    public void testRightLensModify() {
        Either<String, Integer> either = Either.right(42);
        
        Lens<Either<String, Integer>, Option<Integer>> rightLens = Lens.right();
        
        Either<String, Integer> updated = rightLens.modify(either, new Apply<Option<Integer>, Option<Integer>>() {
            public Option<Integer> apply(Option<Integer> opt) {
                return opt.map(new Apply<Integer, Integer>() {
                    public Integer apply(Integer i) {
                        return i * 2;
                    }
                });
            }
        });
        
        assertEquals(Some(84), Lens.<String, Integer>right().get(updated));
    }
    
    // Tuple and Pair lens tests
    @Test
    public void testTuple2Lenses() {
        Tuple2<String, Integer> tuple = Tuple.of("hello", 42);
        
        Lens<Tuple._1<String>, String> _1 = Lens._1();
        Lens<Tuple._2<Integer>, Integer> _2 = Lens._2();
        
        assertEquals("hello", _1.get(tuple));
        assertEquals(Integer.valueOf(42), _2.get(tuple));
        
        Tuple2<String, Integer> updated1 = (Tuple2<String, Integer>) _1.set(tuple, "world");
        assertEquals("world", updated1.get_1());
        assertEquals(Integer.valueOf(42), updated1.get_2());
        
        Tuple2<String, Integer> updated2 = (Tuple2<String, Integer>) _2.set(tuple, 100);
        assertEquals("hello", updated2.get_1());
        assertEquals(Integer.valueOf(100), updated2.get_2());
    }
    
    @Test
    public void testTuple3Lenses() {
        Tuple3<String, Integer, Boolean> tuple = Tuple.of("hello", 42, true);
        
        Lens<Tuple._1<String>, String> _1 = Lens._1();
        Lens<Tuple._2<Integer>, Integer> _2 = Lens._2();
        Lens<Tuple._3<Boolean>, Boolean> _3 = Lens._3();
        
        assertEquals("hello", _1.get(tuple));
        assertEquals(Integer.valueOf(42), _2.get(tuple));
        assertEquals(Boolean.TRUE, _3.get(tuple));
        
        Tuple3<String, Integer, Boolean> updated = (Tuple3<String, Integer, Boolean>) _2.modify(tuple, new Apply<Integer, Integer>() {
            public Integer apply(Integer i) {
                return i * 2;
            }
        });
        
        assertEquals("hello", updated.get_1());
        assertEquals(Integer.valueOf(84), updated.get_2());
        assertEquals(Boolean.TRUE, updated.get_3());
    }
    
    @Test
    public void testPairLenses() {
        Pair<String, Integer> pair = Pair.of("left", 42);
        
        Lens<Pair<String, Integer>, String> leftLens = Lens.pairLeft();
        Lens<Pair<String, Integer>, Integer> rightLens = Lens.pairRight();
        
        assertEquals("left", leftLens.get(pair));
        assertEquals(Integer.valueOf(42), rightLens.get(pair));
        
        Pair<String, Integer> updatedLeft = leftLens.set(pair, "new left");
        assertEquals("new left", updatedLeft.left());
        assertEquals(Integer.valueOf(42), updatedLeft.right());
        
        Pair<String, Integer> updatedRight = rightLens.set(pair, 100);
        assertEquals("left", updatedRight.left());
        assertEquals(Integer.valueOf(100), updatedRight.right());
    }
    
    // Map lens tests
    @Test
    public void testMapValueLens() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        
        Lens<Map<String, Integer>, Integer> aLens = Lens.mapValue("a");
        
        assertEquals(Integer.valueOf(1), aLens.get(map));
        
        Map<String, Integer> updated = aLens.set(map, 10);
        assertEquals(Integer.valueOf(10), updated.get("a"));
        assertEquals(Integer.valueOf(2), updated.get("b"));
        assertEquals(Integer.valueOf(3), updated.get("c"));
        
        // Original unchanged
        assertEquals(Integer.valueOf(1), map.get("a"));
    }
    
    @Test
    public void testMapValueLensModify() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("x", 5);
        map.put("y", 10);
        
        Lens<Map<String, Integer>, Integer> xLens = Lens.mapValue("x");
        
        Map<String, Integer> updated = xLens.modify(map, new Apply<Integer, Integer>() {
            public Integer apply(Integer value) {
                return value * 2;
            }
        });
        
        assertEquals(Integer.valueOf(10), updated.get("x"));
        assertEquals(Integer.valueOf(10), updated.get("y"));
    }
    
    @Test
    public void testMapValueLensWithMissingKey() {
        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("a", 1);
        
        Lens<Map<String, Integer>, Integer> bLens = Lens.mapValue("b");
        
        assertNull(bLens.get(map));
        
        // Setting a missing key should not modify the map
        Map<String, Integer> updated = bLens.set(map, 2);
        assertEquals(1, updated.size());
        assertEquals(Integer.valueOf(1), updated.get("a"));
    }
    
    // Identity lens test
    @Test
    public void testIdentityLens() {
        String value = "hello";
        
        Lens<String, String> idLens = Lens.id();
        
        assertEquals("hello", idLens.get(value));
        
        String updated = idLens.set(value, "world");
        assertEquals("world", updated);
        
        String modified = idLens.modify(value, new Apply<String, String>() {
            public String apply(String s) {
                return s.toUpperCase();
            }
        });
        
        assertEquals("HELLO", modified);
    }
    
    @Test
    public void testIdentityLensWithComplexType() {
        Person person = new Person("John", 30);
        
        Lens<Person, Person> idLens = Lens.id();
        
        assertEquals(person, idLens.get(person));
        
        Person newPerson = new Person("Jane", 25);
        Person updated = idLens.set(person, newPerson);
        
        assertEquals("Jane", updated.name);
        assertEquals(25, updated.age);
    }

}
