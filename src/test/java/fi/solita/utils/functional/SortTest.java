package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.range;
import static fi.solita.utils.functional.Functional.sort;
import static fi.solita.utils.functional.Functional.take;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

class TestClass {
    double id = Math.random();
}

public class SortTest {
    
    Function1<TestClass,Double> accessor = new Function1<TestClass,Double>() {
        @Override
        public Double apply(TestClass t) {
            return t.id;
        }
    };

    @Test
    public void testSort() {
        for (int a: range(1, 6)) {
            int n = (int)Math.pow(10, a);
            System.out.println("Taking " + n + " first elements");
            for (int i: range(1, 6)) {
                runSortOnce((int)Math.pow(10, i), n);
            }
            System.out.println();
        }
    }
    
    public void runSortOnce(int size, int firstToTake) {
        System.out.println("Testing for " + size + " elements");
        List<TestClass> data = newList(map(range(1, size), new Transformer<Integer, TestClass>() {
            @Override
            public TestClass transform(Integer source) {
                return new TestClass();
            }
        }));
        
        long start = System.nanoTime();
        List<TestClass> regular = new ArrayList<TestClass>(data);
        java.util.Collections.sort(regular, Compare.by(accessor));
        regular = regular.subList(0, Math.min(firstToTake, regular.size()));
        long end = System.nanoTime();
        
        System.out.println("Regular sort took " + ((end-start)/1000/1000) + " ms");
        
        start = System.nanoTime();
        List<TestClass> lazy = newList(take(sort(data, Compare.by(accessor)), firstToTake));
        end = System.nanoTime();
        
        System.out.println("Lazy sort took    " + ((end-start)/1000/1000) + " ms");
        
        assertEquals(regular, lazy);
    }
}
