package fi.solita.utils.functional;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.map;

import java.util.List;
import java.util.Map;

import org.junit.Test;

public class TupleExamples {
    
    @SuppressWarnings("unused")
    @Test
    public void examples() {
        Tuple1<String> tuple1 = Tuple.of("foo");
        Map.Entry<String,String> tuple2extendsMapEntry = Tuple.of("a", "b");
        Tuple2<String,String> pairExtendsTuple2 = Pair.of("left", "right");
        // ... until Tuple22
        
        // a List of common supertype
        List<String> strings = Tuple.asList(Tuple.of("str1", "str2"));
        List<? extends Object> objects = Tuple.asList(Tuple.of(42, "foo"));
        List<? extends Number> numbers = Tuple.asList(Tuple.of(42, 42l));
        
        Object[] values = Tuple.of("str").toArray();
        
        Tuple3<Integer,String,Boolean> tuple = Tuple.of(42, "b", true);
        String valueByFieldAccess = tuple._2;
        
        Pair<Integer,String> pair = Pair.of(42, "right");
        String right = pair.right;
        int left = pair.left;
        
        Tuple2<String, Integer> appendedRight = Tuple.of("str").append(42);
        Tuple3<Boolean, String, Integer> prependedLeft = appendedRight.prepend(true);
        
        Tuple4<Integer, String, Boolean, Object> bigtuple = Tuple.of(42, "str", true, new Object());
        Tuple2<Integer,String> prefix2 = bigtuple.take2();
        Tuple3<Integer, String, Boolean> prefix3 = bigtuple.take3();
        
        List<Pair<String,Integer>> listOfTuples = newList();
        Iterable<Integer> projection = map(listOfTuples, Transformers.<Integer>_2());
    }
}
