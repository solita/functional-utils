package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newMap;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Functional.*;
import static fi.solita.utils.functional.FunctionalExamples_.*;
import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Predicates.*;
import static fi.solita.utils.functional.Transformers.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class FunctionalExamples {
    
    static <T> void assertEquals(T expected, T actual) {
        Assert.assertEquals(expected, actual);
    }

    static Iterable<Integer> someIterableOrArray = newList(1, 2, 3);
    static Map<Integer,String> someMap = newMap(Pair.of(42, "foo"));
    
    static boolean evenKey(Map.Entry<Integer, String> e) {
        return e.getKey() % 2 == 0;
    }
    
    static int intPlus(int s1, int s2) {
        return s1 + s2;
    }
    
    static String strConcat(String s1, int s2) {
        return s1 + s2;
    }
    
    static void someProcedure(Integer i) {
    }
    
    static List<Integer> repeatTwice(int i) {
        return newList(i, i);
    }
    
    @Test
    public void examples() {
        // head
        int head = head(someIterableOrArray);
        assertEquals(1, head);
        
        // headOption
        Option<Integer> headOption = headOption(someIterableOrArray);
        assertEquals(Some(1), headOption);
        assertEquals(None(), headOption(emptyList()));
        
        // last
        int last = last(someIterableOrArray);
        assertEquals(3, last);
        
        // lastOption
        Option<Integer> lastOption = lastOption(someIterableOrArray);
        assertEquals(Some(3), lastOption);
        assertEquals(None(), lastOption(emptyList()));
        
        // tail
        Iterable<Integer> tail = tail(someIterableOrArray);
        assertEquals(newList(2,3), newList(tail));
        
        // init
        Iterable<Integer> init = init(someIterableOrArray);
        assertEquals(newList(1,2), newList(init));
        
        // take
        Iterable<Integer> take = take(2, someIterableOrArray);
        assertEquals(newList(1,2), newList(take));
        
        // takeWhile
        Iterable<Integer> takeWhile = takeWhile(odd, someIterableOrArray);
        assertEquals(newList(1), newList(takeWhile));
        
        // drop
        Iterable<Integer> drop = drop(1, someIterableOrArray);
        assertEquals(newList(2,3), newList(drop));
        
        // dropWhile
        Iterable<Integer> dropWhile = dropWhile(odd, someIterableOrArray);
        assertEquals(newList(2,3), newList(dropWhile));
        
        // span
        Pair<Iterable<Integer>, Iterable<Integer>> span = span(odd, someIterableOrArray);
        assertEquals(Pair.of(newList(1),         newList(2,3)),
                     Pair.of(newList(span.left), newList(span.right)));
        
        // isEmpty
        boolean isEmpty = isEmpty(someIterableOrArray);
        assertEquals(false, isEmpty);
        
        // containment
        boolean contains = contains(1, someIterableOrArray);
        assertEquals(true, contains);
        
        // size
        long size = size(someIterableOrArray);
        assertEquals(3l, size);
        
        // cons
        Iterable<Integer> cons = cons(0, someIterableOrArray);
        assertEquals(newList(0,1,2,3), newList(cons));
        
        // concat
        Iterable<Integer> concat = concat(someIterableOrArray, someIterableOrArray);
        assertEquals(newList(1,2,3,1,2,3), newList(concat));
        
        // find
        Option<Integer> find = find(even, someIterableOrArray);
        Option<String> findFromMap = find(42, someMap);
        assertEquals(Some(2), find);
        assertEquals(Some("foo"), findFromMap);
        assertEquals(None(), find(even, Collections.<Integer>emptyList()));
        
        // filter
        Iterable<Integer> filter = filter(odd, someIterableOrArray);
        Map<Integer, String> filterFromMap = filter(evenKey, someMap);
        assertEquals(newList(1,3), newList(filter));
        assertEquals(newMap(Pair.of(42, "foo")), filterFromMap);
        
        // exists
        boolean exists = exists(even, someIterableOrArray);
        assertEquals(true, exists);
        
        // forall
        boolean forall = forall(even, someIterableOrArray);
        assertEquals(false, forall);
        
        // foreach
        foreach(someProcedure, someIterableOrArray);
        
        // map
        Iterable<Integer> map = map(negateInt, someIterableOrArray);
        Map<Integer, String> mapOfMap = map(Function.<Map.Entry<Integer, String>>id(), someMap);
        Iterable<Pair<Integer, Integer>> mapMultiple = map(negateInt, negateInt.andThen(negateInt), someIterableOrArray);
        assertEquals(newList(-1,-2,-3), newList(map));
        assertEquals(newMap(Pair.of(42, "foo")), mapOfMap);
        assertEquals(newList(Pair.of(-1,1), Pair.of(-2,2), Pair.of(-3,3)), newList(mapMultiple));
        
        // flatMap
        Iterable<Integer> flatMap = flatMap(repeatTwice, someIterableOrArray);
        assertEquals(newList(1,1,2,2,3,3), newList(flatMap));
        
        // flatten
        Iterable<Integer> flatten = flatten(map(repeatTwice, someIterableOrArray));
        Iterable<Integer> flattenArrays = flatten(new Integer[][]{new Integer[]{1}, new Integer[]{2}});
        assertEquals(newList(1,1,2,2,3,3), newList(flatten));
        assertEquals(newList(1,2), newList(flattenArrays));
        
        // fold
        Option<Integer> fold = fold(intPlus, someIterableOrArray);
        String foldFromZero = fold("->", strConcat, someIterableOrArray);
        assertEquals(Some(6), fold);
        assertEquals("->123", foldFromZero);
        
        // reduce
        long reduce = reduce(Monoids.longSum, map(Transformers.int2long, someIterableOrArray));
        assertEquals(6l, reduce);
        
        // union
        Set<Integer> union = union(newSet(1,2), newSet(2,3));
        assertEquals(newSet(1,2,3), union);
        
        // intersection
        Set<Integer> intersection = intersection(newSet(1,2), newSet(2,3));
        assertEquals(newSet(2), intersection);
        
        // subtract
        Iterable<Integer> subtract = subtract(someIterableOrArray, 2);
        assertEquals(newList(1,3), newList(subtract));
        
        // min
        Option<Integer> min = min(someIterableOrArray);
        assertEquals(Some(1), min);
        assertEquals(None(), min(Collections.<Integer>emptyList()));
        
        // max
        Option<Integer> max = max(someIterableOrArray);
        assertEquals(Some(3), max);
        assertEquals(None(), max(Collections.<Integer>emptyList()));
        
        // sum
        long sum = sum(1,2,3);
        assertEquals(6l, sum);
        
        // product
        long product = product(1,2,3);
        assertEquals(6l, product);
        
        // sort
        Iterable<Integer> sort = sort(someIterableOrArray);
        Iterable<Integer> sortBy = sort(Compare.byNatural().reverse(), someIterableOrArray);
        assertEquals(newList(1,2,3), newList(sort));
        assertEquals(newList(3,2,1), newList(sortBy));
        
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
