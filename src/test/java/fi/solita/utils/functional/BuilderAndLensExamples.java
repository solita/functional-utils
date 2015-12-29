package fi.solita.utils.functional;

import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Member;

import org.junit.Test;

class Department {
    final String name;

    public static final Builder<Department> builder = Builder.of(Department_.$Fields(), Department_.$);
    
    public Department(String name) {
        this.name = name;
    }
}

class Employee {
    final String name;
    final Option<Integer> salary;
    final Department department;
    
    public static final Builder<Employee> builder = Builder.of(Employee_.$Fields(), Employee_.$);

    public Employee(String name, Option<Integer> salary, Department department) {
        this.name = name;
        this.salary = salary;
        this.department = department;
    }
}

/** Generated in practice by meta-utils */
class Department_ {
    public static final Tuple1<Apply<Department,String>> $Fields() {
        return Tuple.of(name);
    }
    public static final Apply<String, Department> $ = new Apply<String, Department>() {
        public Department apply(String t) {
            return new Department(t);
        }
    };
    public static final Apply<Department, String> name = new Apply<Department, String>() {
        public String apply(Department t) {
            return t.name;
        }
    };
}

/** Generated in practice by meta-utils */
class Employee_ {
    public static final Tuple3<Apply<Employee,String>,Apply<Employee,Option<Integer>>,Apply<Employee,Department>> $Fields() {
        return Tuple.of(name, salary, department);
    }
    public static final Function3<String, Option<Integer>, Department, Employee> $ = new Function3<String,Option<Integer>,Department,Employee>() {
        public Employee apply(String t1, Option<Integer> t2, Department t3) {
            return new Employee(t1, t2, t3);
        }
    };
    public static final Apply<Employee, String> name = new Apply<Employee, String>() {
        public String apply(Employee t) {
            return t.name;
        }
    };
    public static final Apply<Employee, Option<Integer>> salary = new Apply<Employee, Option<Integer>>() {
        public Option<Integer> apply(Employee t) {
            return t.salary;
        }
        @SuppressWarnings("unused")
        public Member getMember() {
            try {
                return Employee.class.getDeclaredField("salary");
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }
    };
    public static final Apply<Employee, Department> department = new Apply<Employee, Department>() {
        public Department apply(Employee t) {
            return t.department;
        }
    };
}

public class BuilderAndLensExamples {

    @Test
    public void builder() {
        Employee employee = Builder.of(Employee_.$Fields(), Employee_.$)
            .with(Employee_.name, "John")
            .without(Employee_.salary)
            .with(Employee_.department, new Department("Sales"))
            .build();
        assertEquals("John", employee.name);
        assertEquals(None(), employee.salary);
        assertEquals("Sales", employee.department.name);
    }
    
    @Test
    public void lens() {
        Employee employee = new Employee("John", Some(42), new Department("Sales"));
        Lens<Employee,String> nameLens = Lens.of(Employee_.name, Employee.builder);

        assertEquals("John", employee.name);
        assertEquals("John", nameLens.get(employee));

        Employee newEmployee = nameLens.set(employee, "Jane");
        assertEquals("Jane", newEmployee.name);
    }

    @Test
    public void deepLens() {
        Employee employee = new Employee("John", Some(42), new Department("Sales"));

        Lens<Employee, Department> employeeDepartmentLens = Lens.of(Employee_.department, Employee.builder);
        Lens<Department, String> departmentNameLens = Lens.of(Department_.name, Department.builder);
        Lens<Employee, String> employeeDepartmentNameLens = employeeDepartmentLens.andThen(departmentNameLens);

        assertEquals("Sales", employeeDepartmentNameLens.get(employee));

        Employee newEmployee = employeeDepartmentNameLens.set(employee, "IT");
        assertEquals("IT", newEmployee.department.name);
    }
}
