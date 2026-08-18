package fi.solita.utils.functional;
import static fi.solita.utils.functional.Collections.newMutableList;
import static fi.solita.utils.functional.Functional.sort;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import fi.solita.utils.functional.Tuple._1;

@SuppressWarnings("unused")
public class CompareExamples {
  
    static class Employee {
        public int salary;
        public Optional<String> name;
    }
    
    static java.util.function.Function<Employee,Integer> salary = new java.util.function.Function<Employee,Integer>() {
        @Override
        public Integer apply(Employee source) {
            return source.salary;
        }
    };
    static java.util.function.Function<Employee,Optional<String>> name = new java.util.function.Function<Employee,Optional<String>>() {
        @Override
        public Optional<String> apply(Employee source) {
            return source.name;
        }
    };
    
    @Test
    public void examples() {
        Comparator<Comparable<?>> byNaturalComparator = (Comparator<Comparable<?>>) Comparator.naturalOrder();
        Comparator<Employee> byComparable = Comparator.comparing(salary);
        Comparator<Employee> byExplicitComparator = Comparator.comparing(salary, Comparator.naturalOrder());
        
        Comparator<_1<? extends Comparable>> by_1 = Comparator.comparing(Tuple._1::get_1);

        List<Tuple2<String, Employee>> listOfTuples = newMutableList();
        sort(by_1, listOfTuples);
        // Employee does not implement comparable, but we can first map to _2 and then to salary
        sort(Comparator.comparing(t -> salary.apply(t._2)), listOfTuples);
        
        // sorted by the contents of an iterable
        sort(Compare.<String>byIterable(), Collections.<List<String>>newMutableList());

        // sorted by the contents of an Option
        sort(Compare.<String>byOptional(), Collections.<Optional<String>>newMutableList());
        
        // sorted by a function to an Option
        sort(Compare.byOptional(name), Collections.<Employee>newMutableList());
        
        // and the same with explicit comparators
        sort(Compare.byIterable(Comparator.comparing(salary)), Collections.<List<Employee>>newMutableList());
        sort(Compare.byOptional(Comparator.comparing(salary)), Collections.<Optional<Employee>>newMutableList());
        sort(Compare.byOptional(name, Comparator.naturalOrder()), Collections.<Employee>newMutableList());
    }
}
