package fi.solita.utils.functional;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newMap;
import static fi.solita.utils.functional.Functional.reduce;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class MonoidExamples {

    @SuppressWarnings("unused")
    @Test
    public void examples() {
        List<Integer> ints = newList(1, 2);
        
        // Integers, Booleans and Strings are monoids,
        // but they do not have "default instances of Monoid typeclass" so we
        // must give one as a parameter.
        int three = reduce(ints, Monoids.intSum);
        int two = reduce(ints, Monoids.intProduct);
        boolean notTrue = reduce(newList(true, false), Monoids.booleanConjunction);
        String foobar = reduce(newList("foo", "bar"), Monoids.stringConcat);
        
        // For classes having a default (SemiGroup) instance,
        // no parameter is needed.
        List<Distance> distances = newList(new Distance(1), new Distance(2));
        Option<Distance> reduced = reduce(distances);
        
        Map<String, Integer> first = newMap();
        Map<String, Integer> second = newMap();
        Map<String, Integer> valuesSummed = reduce(newList(first, second), Monoids.<String,Integer>mapCombine(SemiGroups.intSum));
    }
    
    static class Distance implements SemiGroup<Distance> {
        public final int meters;
      
        public Distance(int meters) {
            this.meters = meters;
        }

        @Override
        public Distance apply(final Tuple2<Distance, Distance> t) {
            return new Distance(t._1.meters + t._2.meters);
        }
    }
}
