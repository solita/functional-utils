package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newMap;
import static fi.solita.utils.functional.Collections.newSet;
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
import static fi.solita.utils.functional.Functional.fold;
import static fi.solita.utils.functional.Functional.forall;
import static fi.solita.utils.functional.Functional.foreach;
import static fi.solita.utils.functional.Functional.head;
import static fi.solita.utils.functional.Functional.headOption;
import static fi.solita.utils.functional.Functional.init;
import static fi.solita.utils.functional.Functional.isEmpty;
import static fi.solita.utils.functional.Functional.last;
import static fi.solita.utils.functional.Functional.lastOption;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.max;
import static fi.solita.utils.functional.Functional.min;
import static fi.solita.utils.functional.Functional.reduce;
import static fi.solita.utils.functional.Functional.size;
import static fi.solita.utils.functional.Functional.sort;
import static fi.solita.utils.functional.Functional.span;
import static fi.solita.utils.functional.Functional.subtract;
import static fi.solita.utils.functional.Functional.tail;
import static fi.solita.utils.functional.Functional.take;
import static fi.solita.utils.functional.Functional.takeWhile;
import static fi.solita.utils.functional.FunctionalA.flatten;
import static fi.solita.utils.functional.FunctionalM.filter;
import static fi.solita.utils.functional.FunctionalM.find;
import static fi.solita.utils.functional.FunctionalM.map;
import static fi.solita.utils.functional.FunctionalS.intersection;
import static fi.solita.utils.functional.FunctionalS.product;
import static fi.solita.utils.functional.FunctionalS.sum;
import static fi.solita.utils.functional.FunctionalS.union;
import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Predicates.even;
import static fi.solita.utils.functional.Predicates.odd;
import static fi.solita.utils.functional.Transformers.negateInt;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class FunctionalExamples {
    
    static <T> void assertEquals(T expected, T actual) {
        Assert.assertEquals(expected, actual);
    }

    static Iterable<Integer> someIterableOrArray = newList(1, 2, 3);
    static Map<Integer,String> someMap = newMap(Pair.of(42, "foo"));
    
    Apply<Map.Entry<Integer,String>,Boolean> evenKey = new Transformer<Map.Entry<Integer,String>,Boolean>() {
        @Override
        public Boolean transform(Entry<Integer, String> source) {
            return source.getKey() % 2 == 0;
        }
    };
    
    Apply<Integer,Void> someProcedure = new Transformer<Integer,Void>() {
        @Override
        public Void transform(Integer source) {
            return null;
        }
    };
    
    Apply<Integer,List<Integer>> repeatTwice = new Transformer<Integer,List<Integer>>() {
        @Override
        public List<Integer> transform(Integer source) {
            return newList(source, source);
        }
    };
    
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
                     Pair.of(newList(span.left()), newList(span.right())));
        
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
        Map<Integer, String> mapOfMap = map(SemiGroups.<String>fail(), Function.<Map.Entry<Integer, String>>id(), someMap);
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
        Option<Integer> fold = fold(SemiGroups.intSum, someIterableOrArray);
        String foldFromZero = fold("->", SemiGroups.stringConcat, map(Transformers.toString, someIterableOrArray));
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
        Iterable<Integer> subtract = subtract(someIterableOrArray, newList(2));
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
        // distinct
        
        // zip
        // zipWithIndex
        // transpose
        // groupBy
        // grouped
        
        // mkString
        // unlines
    }
}
