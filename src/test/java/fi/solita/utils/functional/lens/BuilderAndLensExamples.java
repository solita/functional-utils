package fi.solita.utils.functional.lens;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Functional.flatMap;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Member;
import java.util.List;

import org.junit.Test;

import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Collections;
import fi.solita.utils.functional.Function2;
import fi.solita.utils.functional.Function3;
import fi.solita.utils.functional.Option;
import fi.solita.utils.functional.Tuple;
import fi.solita.utils.functional.Tuple2;
import fi.solita.utils.functional.Tuple3;

class Department {
    final String name;
    final List<Employee> employees;

    public static final Builder<Department> builder = Builder.of(Department_.$Fields(), Department_.$);
    
    public Department(String name, List<Employee> employees) {
        this.name = name;
        this.employees = employees;
    }
    
    public Department(String name) {
        this.name = name;
        this.employees = Collections.emptyList();
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
    public static final Tuple2<Apply<Department,String>,Apply<Department,List<Employee>>> $Fields() {
        return Tuple.of(name, employees);
    }
    public static final Function2<String, List<Employee>, Department> $ = new Function2<String, List<Employee>, Department>() {
        public Department apply(String t, List<Employee> e) {
            return new Department(t, e);
        }
    };
    public static final Apply<Department, String> name = new Apply<Department, String>() {
        public String apply(Department t) {
            return t.name;
        }
    };
    public static final Apply<Department, List<Employee>> employees = new Apply<Department, List<Employee>>() {
        public List<Employee> apply(Department t) {
            return t.employees;
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
        Lens<Employee,String> name_ = Lens.of(Employee_.name, Employee.builder);

        assertEquals("John", employee.name);
        assertEquals("John", name_.get(employee));

        Employee newEmployee = name_.set(employee, "Jane");
        assertEquals("Jane", newEmployee.name);
    }

    @Test
    public void deepLens() {
        Employee employee = new Employee("John", Some(42), new Department("Sales"));

        Lens<Employee, Department> employeeDepartment_ = Lens.of(Employee_.department, Employee.builder);
        Lens<Department, String> departmentName_ = Lens.of(Department_.name, Department.builder);
        Lens<Employee, String> employeeDepartmentName_ = employeeDepartment_.andThen(departmentName_);

        assertEquals("Sales", employeeDepartmentName_.get(employee));

        Employee newEmployee = employeeDepartmentName_.set(employee, "IT");
        assertEquals("IT", newEmployee.department.name);
    }
    
    @Test
    public void listLens() {
        Department department = new Department("Sales", newList(new Employee("John", Some(42), new Department("IT"))));

        Lens<Department, List<Employee>> departmentEmployees_ = Lens.of(Department_.employees, Department.builder);
        Setter<Employee, Option<Integer>> employeeSalary_ = Lens.of(Employee_.salary, Employee.builder);

        assertEquals(newSet(42), newSet(flatMap(Employee_.salary, departmentEmployees_.get(department))));

        
        Setter<Department,Option<Integer>> departmentSalaries_ = Setter.eachList(departmentEmployees_, employeeSalary_);
        assertEquals(newSet(69), newSet(flatMap(Employee_.salary, departmentSalaries_.set(department, Some(69)).employees)));
        
        
        Lens<Department, String> departmentName_ = Lens.of(Department_.name, Department.builder);
        Setter<Employee, Department> employeeDepartment_ = Lens.of(Employee_.department, Employee.builder);
        
        Setter<Department,Department> departmentEmployeeDepartments_ = Setter.eachList(departmentEmployees_, employeeDepartment_);
        Setter<Department,String> departmentEmployeeDepartmentsName_ = departmentEmployeeDepartments_.andThen(departmentName_);
        
        assertEquals(newSet("IT2"), newSet(map(Department_.name, map(Employee_.department, departmentEmployeeDepartmentsName_.set(department, "IT2").employees))));
    }
}
