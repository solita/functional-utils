package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newArray;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newMap;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.cons;
import static fi.solita.utils.functional.Functional.contains;
import static fi.solita.utils.functional.Functional.drop;
import static fi.solita.utils.functional.Functional.dropWhile;
import static fi.solita.utils.functional.Functional.exists;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.find;
import static fi.solita.utils.functional.Functional.flatMap;
import static fi.solita.utils.functional.Functional.flatten;
import static fi.solita.utils.functional.Functional.forall;
import static fi.solita.utils.functional.Functional.foreach;
import static fi.solita.utils.functional.Functional.head;
import static fi.solita.utils.functional.Functional.headOption;
import static fi.solita.utils.functional.Functional.init;
import static fi.solita.utils.functional.Functional.isEmpty;
import static fi.solita.utils.functional.Functional.last;
import static fi.solita.utils.functional.Functional.lastOption;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.size;
import static fi.solita.utils.functional.Functional.span;
import static fi.solita.utils.functional.Functional.tail;
import static fi.solita.utils.functional.Functional.take;
import static fi.solita.utils.functional.Functional.takeWhile;
import static fi.solita.utils.functional.FunctionalExamples_.*;
import static fi.solita.utils.functional.Transformers.*;

import java.util.Arrays;
import java.util.Map;

public class FunctionalExamples {

    static Iterable<Integer> someIterableOrArray = newList(1, 2);
    static Map<Integer,String> someMap = newMap(Pair.of(42, "foo"));
    
    static boolean evenKey(Map.Entry<Integer, String> e) {
        return e.getKey() % 2 == 0;
    }
    
    static void someProcedure(Integer i) {
    }
    
    public void examples() {
        // head
        Integer head = head(someIterableOrArray);
        
        // headOption
        Option<Integer> headOption = headOption(someIterableOrArray);
        
        // last
        Integer last = last(someIterableOrArray);
        
        // lastOption
        Option<Integer> lastOption = lastOption(someIterableOrArray);
        
        // tail
        Iterable<Integer> tail = tail(someIterableOrArray);
        
        // init
        Iterable<Integer> init = init(someIterableOrArray);
        
        // take
        Iterable<Integer> take = take(1, someIterableOrArray);
        
        // takeWhile
        Iterable<Integer> takeWhile = takeWhile(someIterableOrArray, Predicates.odd);
        
        // drop
        Iterable<Integer> drop = drop(1, someIterableOrArray);
        
        // dropWhile
        Iterable<Integer> dropWhile = dropWhile(someIterableOrArray, Predicates.odd);
        
        // span
        Pair<Iterable<Integer>, Iterable<Integer>> span = span(someIterableOrArray, Predicates.odd);
        
        // isEmpty
        boolean isEmpty = isEmpty(someIterableOrArray);
        
        // containment
        boolean contains = contains(someIterableOrArray, 1);
        
        // size
        int size = size(someIterableOrArray);
        
        // cons
        Iterable<Integer> cons = cons(0, someIterableOrArray);
        
        // concat
        Iterable<Integer> concat = concat(someIterableOrArray, someIterableOrArray);
        
        // find
        Option<Integer> find = find(someIterableOrArray, Predicates.even);
        Option<String> findFromMap = find(42, someMap);
        
        // filter
        Iterable<Integer> filter = filter(someIterableOrArray, Predicates.even);
        Map<Integer, String> filterFromMap = filter(someMap, evenKey);
        
        // exists
        boolean exists = exists(someIterableOrArray, Predicates.even);
        
        // forall
        boolean forAll = forall(someIterableOrArray, Predicates.even);
        
        // foreach
        foreach(someIterableOrArray, someProcedure);
        
        // map
        Iterable<Integer> map = map(someIterableOrArray, negate);
        Map<Integer, String> mapOfMap = map(someMap, Function.<Map.Entry<Integer, String>>id());
        Iterable<Pair<Integer, Integer>> mapMultiple = map(someIterableOrArray, negate, negate.andThen(negate));
        
        // flatMap
        Iterable<Integer> flatMap = flatMap(someIterableOrArray, Collections_.<Integer>newList9());
        
        // flatten
        Iterable<Integer> flatten = flatten(Arrays.asList(someIterableOrArray));
        Iterable<Integer> flattenArrays = flatten(new Integer[][]{new Integer[]{1}, new Integer[]{2}});
        
        // fold
        // reduce
        
        // union
        // intersection
        // subtract
        
        // min
        // max
        // sum
        // product
        
        // sort
        // reverse
        // repeat
        // range
        // sequence
        
        // zip
        // zipWithIndex
        // transpose
        // groupBy
        // grouped
        
        // mkString
        // unlines
    }
}
