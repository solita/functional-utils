package fi.solita.utils.functional;
import static fi.solita.utils.functional.Collections.newList;

import org.junit.Test;

public class MatchExamples {
    
    @SuppressWarnings("unused")
    @Test
    public void examples() {
        for (Integer m: Match.instance(Integer.class, (Object)42)) {
            // executed only if object is an Integer 
        }
        
        for (Integer m: Match.singleton(newList(1))) {
            // executed only if the given iterable contains exactly one item
        }
        
        for (Pair<Integer, Integer> m: Match.pair(newList(1,2))) {
            // executed only if the given iterable contains exactly two items
        }
        
        for (Pair<Integer,Integer> m: Match.iterable(1, null, newList(1,2))) {
            // executed only if the iterable (last parameter) contains exactly as many
            // elements as given as previous parameters, and all non-null values
            // are equal to the corresponding element in the iterable.
            //
            // So, you can use nulls to mean "this element can be anything"
        }
    }
}
