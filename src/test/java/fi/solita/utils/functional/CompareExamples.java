package fi.solita.utils.functional;
import static fi.solita.utils.functional.Collections.newMutableList;
import static fi.solita.utils.functional.Functional.sort;

import java.util.List;

import org.junit.Test;

import fi.solita.utils.functional.Tuple._1;

@SuppressWarnings("unused")
public class CompareExamples {
  
    static class Employee {
        public int salary;
        public Option<String> name;
    }
    
    static Apply<Employee,Integer> salary = new Transformer<Employee,Integer>() {
        @Override
        public Integer transform(Employee source) {
            return source.salary;
        }
    };
    static Apply<Employee,Option<String>> name = new Transformer<Employee,Option<String>>() {
        @Override
        public Option<String> transform(Employee source) {
            return source.name;
        }
    };
    
    @Test
    public void examples() {
        Ordering<Comparable<?>> byNaturalOrdering = Compare.byNatural();
        Ordering<Employee> byComparable = Compare.by(salary);
        Ordering<Employee> byExplicitComparator = Compare.by(salary, Ordering.Natural());
        
        Ordering<_1<? extends Comparable<?>>> by_1 = Compare.by_1;

        List<Tuple2<String, Employee>> listOfTuples = newMutableList();
        sort(by_1, listOfTuples);
        // Employee does not implement comparable, but we can first map to _2 and then to salary
        sort(Compare.by(Transformers.<Employee>_2().andThen(salary)), listOfTuples);
        
        // sorted by the contents of an iterable
        sort(Compare.<String>byIterable(), Collections.<List<String>>newMutableList());

        // sorted by the contents of an Option
        sort(Compare.<String>byOption(), Collections.<Option<String>>newMutableList());
        
        // sorted by a function to an Option
        sort(Compare.byOption(name), Collections.<Employee>newMutableList());
        
        // and the same with explicit comparators
        sort(Compare.byIterable(Compare.by(salary)), Collections.<List<Employee>>newMutableList());
        sort(Compare.byOption(Compare.by(salary)), Collections.<Option<Employee>>newMutableList());
        sort(Compare.byOption(name, Ordering.Natural()), Collections.<Employee>newMutableList());
    }
}
