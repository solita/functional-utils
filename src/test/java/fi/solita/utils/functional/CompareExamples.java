package fi.solita.utils.functional;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.sort;

import java.util.List;

import org.junit.Test;

import fi.solita.utils.functional.CompareExamples_.Employee_;
import fi.solita.utils.functional.Tuple._1;

@SuppressWarnings("unused")
public class CompareExamples {
  
    static class Employee {
        public int salary;
        public Option<String> name;
    }
    
    @Test
    public void examples() {
        Ordering<Comparable<?>> byNaturalOrdering = Compare.byNatural();
        Ordering<Employee> byComparable = Compare.by(Employee_.salary);
        Ordering<Employee> byExplicitComparator = Compare.by(Employee_.salary, Ordering.Natural());
        
        Ordering<_1<? extends Comparable<?>>> by_1 = Compare.by_1;

        List<Tuple2<String, Employee>> listOfTuples = newList();
        sort(listOfTuples, by_1);
        // Employee does not implement comparable, but we can first map to _2 and then to salary
        sort(listOfTuples, Compare.by(Tuple2_.<Employee>_2().andThen(Employee_.salary)));
        
        // sorted by the contents of an iterable
        sort(Collections.<List<String>>newList(), Compare.<String>byIterable());

        // sorted by the contents of an Option
        sort(Collections.<Option<String>>newList(), Compare.<String>byOption());
        
        // sorted by a function to an Option
        sort(Collections.<Employee>newList(), Compare.byOption(Employee_.name));
        
        // and the same with explicit comparators
        sort(Collections.<List<Employee>>newList(), Compare.byIterable(Compare.by(Employee_.salary)));
        sort(Collections.<Option<Employee>>newList(), Compare.byOption(Compare.by(Employee_.salary)));
        sort(Collections.<Employee>newList(), Compare.byOption(Employee_.name, Ordering.Natural()));
    }
}
