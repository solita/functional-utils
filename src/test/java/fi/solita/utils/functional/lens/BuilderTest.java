package fi.solita.utils.functional.lens;

import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;

import org.junit.Test;

import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.ApplyZero;
import fi.solita.utils.functional.Function2;
import fi.solita.utils.functional.Function3;
import fi.solita.utils.functional.Option;
import fi.solita.utils.functional.Tuple;
import fi.solita.utils.functional.Tuple1;
import fi.solita.utils.functional.Tuple2;
import fi.solita.utils.functional.Tuple3;
import fi.solita.utils.functional.lens.Builder.IncompleteException;

public class BuilderTest {
    
    // Test classes
    static class Person {
        final String name;
        final int age;
        
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
    
    static class Person_ {
        static final Apply<Person, String> name = new Apply<Person, String>() {
            public String apply(Person p) { return p.name; }
        };
        static final Apply<Person, Integer> age = new Apply<Person, Integer>() {
            public Integer apply(Person p) { return p.age; }
        };
        static final Tuple2<Apply<Person, String>, Apply<Person, Integer>> $Fields() {
            return Tuple.of(name, age);
        }
        static final Function2<String, Integer, Person> $ = new Function2<String, Integer, Person>() {
            public Person apply(String name, Integer age) {
                return new Person(name, age);
            }
        };
    }
    
    static class Employee {
        final String name;
        final Option<Integer> salary;
        final String department;
        
        public Employee(String name, Option<Integer> salary, String department) {
            this.name = name;
            this.salary = salary;
            this.department = department;
        }
    }
    
    static class Employee_ {
        static final Apply<Employee, String> name = new Apply<Employee, String>() {
            public String apply(Employee e) { return e.name; }
        };
        static final Apply<Employee, Option<Integer>> salary = new Apply<Employee, Option<Integer>>() {
            public Option<Integer> apply(Employee e) { return e.salary; }
            @SuppressWarnings("unused")
            public java.lang.reflect.Member getMember() {
                try {
                    return Employee.class.getDeclaredField("salary");
                } catch (NoSuchFieldException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
        static final Apply<Employee, String> department = new Apply<Employee, String>() {
            public String apply(Employee e) { return e.department; }
        };
        static final Tuple3<Apply<Employee, String>, Apply<Employee, Option<Integer>>, Apply<Employee, String>> $Fields() {
            return Tuple.of(name, salary, department);
        }
        static final Function3<String, Option<Integer>, String, Employee> $ = new Function3<String, Option<Integer>, String, Employee>() {
            public Employee apply(String name, Option<Integer> salary, String dept) {
                return new Employee(name, salary, dept);
            }
        };
    }
    
    static class Simple {
        final String value;
        public Simple(String value) { this.value = value; }
    }
    
    static class Simple_ {
        static final Apply<Simple, String> value = new Apply<Simple, String>() {
            public String apply(Simple s) { return s.value; }
        };
        static final Tuple1<Apply<Simple, String>> $Fields() {
            return Tuple.of(value);
        }
        static final Apply<String, Simple> $ = new Apply<String, Simple>() {
            public Simple apply(String value) {
                return new Simple(value);
            }
        };
    }
    
    static class NoArgs {
        public NoArgs() {}
    }
    
    @Test
    public void testBuilder_Tuple2_BasicBuild() {
        Builder<Person> builder = Builder.of(Person_.$Fields(), Person_.$);
        
        Person person = builder
            .with(Person_.name, "Alice")
            .with(Person_.age, 30)
            .build();
        
        assertEquals("Alice", person.name);
        assertEquals(30, person.age);
    }
    
    @Test
    public void testBuilder_Tuple3_BasicBuild() {
        Builder<Employee> builder = Builder.of(Employee_.$Fields(), Employee_.$);
        
        Employee employee = builder
            .with(Employee_.name, "Bob")
            .with(Employee_.salary, Some(50000))
            .with(Employee_.department, "IT")
            .build();
        
        assertEquals("Bob", employee.name);
        assertEquals(Some(50000), employee.salary);
        assertEquals("IT", employee.department);
    }
    
    @Test
    public void testBuilder_Tuple1_BasicBuild() {
        Builder<Simple> builder = Builder.of(Simple_.$Fields(), Simple_.$);
        
        Simple simple = builder.with(Simple_.value, "test").build();
        
        assertEquals("test", simple.value);
    }
    
    @Test
    public void testBuilder_Tuple0_NoArgs() {
        Builder<NoArgs> builder = Builder.of(Tuple.of(), new ApplyZero<NoArgs>() {
            public NoArgs get() { return new NoArgs(); }
        });
        
        NoArgs obj = builder.build();
        assertNotNull(obj);
    }
    
    @Test(expected = IncompleteException.class)
    public void testBuilder_Incomplete_ThrowsException() {
        Builder<Person> builder = Builder.of(Person_.$Fields(), Person_.$);
        
        builder.with(Person_.name, "Charlie").build();
    }
    
    @Test
    public void testBuilder_BuildAllowIncomplete_WithNull() {
        // Test with a class that has Integer (object) instead of int (primitive)
        // so null doesn't cause NullPointerException
        Builder<Employee> builder = Builder.of(Employee_.$Fields(), Employee_.$);
        
        Employee employee = builder.with(Employee_.name, "Charlie").buildAllowIncomplete();
        
        assertEquals("Charlie", employee.name);
        // department should be null
        assertNull(employee.department);
    }
    
    @Test
    public void testBuilder_WithoutOption() {
        Builder<Employee> builder = Builder.of(Employee_.$Fields(), Employee_.$);
        
        Employee employee = builder
            .with(Employee_.name, "David")
            .without(Employee_.salary)
            .with(Employee_.department, "Sales")
            .build();
        
        assertEquals("David", employee.name);
        assertEquals(None(), employee.salary);
        assertEquals("Sales", employee.department);
    }
    
    @Test
    public void testBuilder_OptionField_WithExplicitValue() {
        Builder<Employee> builder = Builder.of(Employee_.$Fields(), Employee_.$);
        
        // When Option field is explicitly set with None
        Employee employee = builder
            .with(Employee_.name, "Eve")
            .with(Employee_.salary, Option.<Integer>None())
            .with(Employee_.department, "HR")
            .build();
        
        assertEquals("Eve", employee.name);
        assertEquals(Option.<Integer>None(), employee.salary);
        assertEquals("HR", employee.department);
    }
    
    @Test
    public void testBuilder_Init_CopiesValues() {
        Person original = new Person("Frank", 40);
        
        Builder<Person> builder = Builder.of(Person_.$Fields(), Person_.$);
        Builder<Person> initialized = builder.init(original);
        
        Person copy = initialized.build();
        
        assertEquals("Frank", copy.name);
        assertEquals(40, copy.age);
    }
    
    @Test
    public void testBuilder_Init_ThenModify() {
        Person original = new Person("Grace", 25);
        
        Builder<Person> builder = Builder.of(Person_.$Fields(), Person_.$);
        Builder<Person> initialized = builder.init(original);
        
        Person modified = initialized.with(Person_.age, 26).build();
        
        assertEquals("Grace", modified.name);
        assertEquals(26, modified.age);
        assertEquals(25, original.age); // Original unchanged
    }
    
    @Test
    public void testBuilder_GetMembers() {
        Builder<Person> builder = Builder.of(Person_.$Fields(), Person_.$);
        
        Collection<? extends Apply<? super Person, ? extends Object>> members = builder.getMembers();
        
        assertEquals(2, members.size());
        assertTrue(members.contains(Person_.name));
        assertTrue(members.contains(Person_.age));
    }
    
    @Test
    public void testBuilder_ResultType() {
        Builder<Employee> builder = Builder.of(Employee_.$Fields(), Employee_.$);
        
        Class<Employee> resultType = builder.resultType();
        
        assertEquals(Employee.class, resultType);
    }
    
    @Test
    public void testBuilder_ToLens() {
        Builder<Person> builder = Builder.of(Person_.$Fields(), Person_.$);
        Lens<Person, String> nameLens = builder.toLens(Person_.name);
        
        Person person = new Person("Helen", 35);
        
        assertEquals("Helen", nameLens.get(person));
        
        Person modified = nameLens.set(person, "Holly");
        assertEquals("Holly", modified.name);
        assertEquals(35, modified.age);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBuilder_WithInvalidMember_ThrowsException() {
        Builder<Person> builder = Builder.of(Person_.$Fields(), Person_.$);
        
        Apply<Person, String> unknownMember = new Apply<Person, String>() {
            public String apply(Person p) { return "unknown"; }
        };
        
        builder.with(unknownMember, "value");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBuilder_WithoutInvalidMember_ThrowsException() {
        Builder<Employee> builder = Builder.of(Employee_.$Fields(), Employee_.$);
        
        Apply<Employee, Option<?>> unknownMember = new Apply<Employee, Option<?>>() {
            public Option<?> apply(Employee e) { return None(); }
        };
        
        builder.without(unknownMember);
    }
    
    @Test
    public void testBuilder_MultipleWith_LastValueWins() {
        Builder<Person> builder = Builder.of(Person_.$Fields(), Person_.$);
        
        Person person = builder
            .with(Person_.name, "First")
            .with(Person_.age, 20)
            .with(Person_.name, "Second")
            .build();
        
        assertEquals("Second", person.name);
        assertEquals(20, person.age);
    }
    
    @Test
    public void testBuilder_Immutable_OriginalUnchanged() {
        Builder<Person> builder1 = Builder.of(Person_.$Fields(), Person_.$);
        Builder<Person> builder2 = builder1.with(Person_.name, "Ivy");
        Builder<Person> builder3 = builder2.with(Person_.age, 28);
        
        // builder1 should still be empty/incomplete
        try {
            builder1.build();
            fail("Expected IncompleteException");
        } catch (IncompleteException e) {
            // Expected
        }
        
        // builder2 should only have name set
        try {
            builder2.build();
            fail("Expected IncompleteException");
        } catch (IncompleteException e) {
            // Expected
        }
        
        // builder3 should have both
        Person person = builder3.build();
        assertEquals("Ivy", person.name);
        assertEquals(28, person.age);
    }
    
    @Test
    public void testBuilder_ChainedOperations() {
        Builder<Person> builder = Builder.of(Person_.$Fields(), Person_.$);
        
        // Test chaining multiple operations
        Person person = builder
            .with(Person_.name, "Jack")
            .with(Person_.age, 45)
            .init(new Person("Jill", 50))
            .with(Person_.age, 55)
            .build();
        
        assertEquals("Jill", person.name);
        assertEquals(55, person.age);
    }
}