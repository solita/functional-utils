package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.FunctionalA.append;
import static fi.solita.utils.functional.FunctionalA.concat;
import static fi.solita.utils.functional.FunctionalA.cons;
import static fi.solita.utils.functional.FunctionalA.contains;
import static fi.solita.utils.functional.FunctionalA.distinct;
import static fi.solita.utils.functional.FunctionalA.drop;
import static fi.solita.utils.functional.FunctionalA.dropWhile;
import static fi.solita.utils.functional.FunctionalA.every;
import static fi.solita.utils.functional.FunctionalA.exists;
import static fi.solita.utils.functional.FunctionalA.filter;
import static fi.solita.utils.functional.FunctionalA.find;
import static fi.solita.utils.functional.FunctionalA.flatMap;
import static fi.solita.utils.functional.FunctionalA.flatten;
import static fi.solita.utils.functional.FunctionalA.fold;
import static fi.solita.utils.functional.FunctionalA.forall;
import static fi.solita.utils.functional.FunctionalA.foreach;
import static fi.solita.utils.functional.FunctionalA.group;
import static fi.solita.utils.functional.FunctionalA.groupBy;
import static fi.solita.utils.functional.FunctionalA.grouped;
import static fi.solita.utils.functional.FunctionalA.head;
import static fi.solita.utils.functional.FunctionalA.headOption;
import static fi.solita.utils.functional.FunctionalA.init;
import static fi.solita.utils.functional.FunctionalA.isEmpty;
import static fi.solita.utils.functional.FunctionalA.last;
import static fi.solita.utils.functional.FunctionalA.lastOption;
import static fi.solita.utils.functional.FunctionalA.map;
import static fi.solita.utils.functional.FunctionalA.max;
import static fi.solita.utils.functional.FunctionalA.maxBy;
import static fi.solita.utils.functional.FunctionalA.min;
import static fi.solita.utils.functional.FunctionalA.minBy;
import static fi.solita.utils.functional.FunctionalA.mkString;
import static fi.solita.utils.functional.FunctionalA.ordered;
import static fi.solita.utils.functional.FunctionalA.partition;
import static fi.solita.utils.functional.FunctionalA.reduce;
import static fi.solita.utils.functional.FunctionalA.remove;
import static fi.solita.utils.functional.FunctionalA.reverse;
import static fi.solita.utils.functional.FunctionalA.sequence;
import static fi.solita.utils.functional.FunctionalA.size;
import static fi.solita.utils.functional.FunctionalA.sort;
import static fi.solita.utils.functional.FunctionalA.span;
import static fi.solita.utils.functional.FunctionalA.split;
import static fi.solita.utils.functional.FunctionalA.subtract;
import static fi.solita.utils.functional.FunctionalA.tail;
import static fi.solita.utils.functional.FunctionalA.take;
import static fi.solita.utils.functional.FunctionalA.takeWhile;
import static fi.solita.utils.functional.FunctionalA.transpose;
import static fi.solita.utils.functional.FunctionalA.unlines;
import static fi.solita.utils.functional.FunctionalA.unzip;
import static fi.solita.utils.functional.FunctionalA.unzip3;
import static fi.solita.utils.functional.FunctionalA.zip;
import static fi.solita.utils.functional.FunctionalA.zipWithIndex;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class FunctionalATest {

    @Test
    public void testSubtract_arrayAndCollection() {
        Integer[] xs = {1, 2, 3, 4, 5};
        List<Integer> toSubtract = newList(2, 4);
        
        Iterable<Integer> result = subtract(xs, toSubtract);
        
        assertThat(newList(result), equalTo(newList(1, 3, 5)));
    }
    
    @Test
    public void testSubtract_iterableAndArray() {
        List<Integer> xs = newList(1, 2, 3, 4, 5);
        Integer[] toSubtract = {2, 4};
        
        Iterable<Integer> result = subtract(xs, toSubtract);
        
        assertThat(newList(result), equalTo(newList(1, 3, 5)));
    }
    
    @Test
    public void testSubtract_arrayAndArray() {
        Integer[] xs = {1, 2, 3, 4, 5};
        Integer[] toSubtract = {2, 4};
        
        Iterable<Integer> result = subtract(xs, toSubtract);
        
        assertThat(newList(result), equalTo(newList(1, 3, 5)));
    }
    
    @Test
    public void testRemove() {
        Integer[] xs = {1, 2, 3, 2, 4};
        
        Iterable<Integer> result = remove(2, xs);
        
        assertThat(newList(result), equalTo(newList(1, 3, 4)));
    }
    
    @Test
    public void testFind_found() {
        Integer[] xs = {1, 2, 3, 4, 5};
        
        Option<Integer> result = find(new Apply<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer x) {
                return x > 3;
            }
        }, xs);
        
        assertTrue(result.isDefined());
        assertThat(result.get(), equalTo(4));
    }
    
    @Test
    public void testFind_notFound() {
        Integer[] xs = {1, 2, 3};
        
        Option<Integer> result = find(new Apply<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer x) {
                return x > 10;
            }
        }, xs);
        
        assertFalse(result.isDefined());
    }
    
    @Test
    public void testFilter() {
        Integer[] xs = {1, 2, 3, 4, 5, 6};
        
        Iterable<Integer> result = filter(new Apply<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer x) {
                return x % 2 == 0;
            }
        }, xs);
        
        assertThat(newList(result), equalTo(newList(2, 4, 6)));
    }
    
    @Test
    public void testMap() {
        Integer[] xs = {1, 2, 3};
        
        Iterable<Integer> result = map(new Apply<Integer, Integer>() {
            @Override
            public Integer apply(Integer x) {
                return x * 2;
            }
        }, xs);
        
        assertThat(newList(result), equalTo(newList(2, 4, 6)));
    }
    
    @Test
    public void testMap_twoFunctions() {
        String[] xs = {"a", "bb", "ccc"};
        
        Iterable<Pair<Integer, String>> result = map(
            new Apply<String, Integer>() {
                @Override
                public Integer apply(String s) {
                    return s.length();
                }
            },
            new Apply<String, String>() {
                @Override
                public String apply(String s) {
                    return s.toUpperCase();
                }
            },
            xs
        );
        
        List<Pair<Integer, String>> resultList = newList(result);
        assertThat(resultList.get(0), equalTo(Pair.of(1, "A")));
        assertThat(resultList.get(1), equalTo(Pair.of(2, "BB")));
        assertThat(resultList.get(2), equalTo(Pair.of(3, "CCC")));
    }
    
    @Test
    public void testFlatMap() {
        Integer[] xs = {1, 2, 3};
        
        Iterable<Integer> result = flatMap(new Apply<Integer, Iterable<Integer>>() {
            @Override
            public Iterable<Integer> apply(Integer x) {
                return newList(x, x * 10);
            }
        }, xs);
        
        assertThat(newList(result), equalTo(newList(1, 10, 2, 20, 3, 30)));
    }
    
    @Test
    public void testFlatten_arrays() {
        Integer[][] xs = {{1, 2}, {3, 4}, {5}};
        
        Iterable<Integer> result = flatten(xs);
        
        assertThat(newList(result), equalTo(newList(1, 2, 3, 4, 5)));
    }
    
    @Test
    public void testFlatten_varargs() {
        Integer[] x1 = {1, 2};
        Integer[] x2 = {3, 4};
        Integer[] x3 = {5};
        
        Iterable<Integer> result = flatten(x1, x2, x3);
        
        assertThat(newList(result), equalTo(newList(1, 2, 3, 4, 5)));
    }
    
    @Test
    public void testForeach() {
        Integer[] xs = {1, 2, 3};
        final List<Integer> collected = Collections.newMutableList();
        
        foreach(new Apply<Integer, Void>() {
            @Override
            public Void apply(Integer x) {
                collected.add(x * 2);
                return null;
            }
        }, xs);
        
        assertThat(collected, equalTo(newList(2, 4, 6)));
    }
    
    @Test
    public void testGrouped() {
        Integer[] xs = {1, 2, 3, 4, 5, 6, 7};
        
        Iterable<List<Integer>> result = grouped(3, xs);
        
        List<List<Integer>> resultList = newList(result);
        assertThat(resultList.get(0), equalTo(newList(1, 2, 3)));
        assertThat(resultList.get(1), equalTo(newList(4, 5, 6)));
        assertThat(resultList.get(2), equalTo(newList(7)));
    }
    
    @Test
    public void testGroup() {
        Integer[] xs = {1, 1, 2, 2, 2, 3};
        
        Iterable<Iterable<Integer>> result = group(xs);
        
        List<List<Integer>> resultList = newList(Functional.map(new Transformer<Iterable<Integer>, List<Integer>>() {
            @Override
            public List<Integer> transform(Iterable<Integer> source) {
                return newList(source);
            }
        }, result));
        
        assertThat(resultList.get(0), equalTo(newList(1, 1)));
        assertThat(resultList.get(1), equalTo(newList(2, 2, 2)));
        assertThat(resultList.get(2), equalTo(newList(3)));
    }
    
    @Test
    public void testGroupBy() {
        String[] xs = {"a", "bb", "c", "dd", "eee"};
        
        Map<Integer, List<String>> result = groupBy(new Apply<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        }, xs);
        
        assertThat(result.get(1), equalTo(newList("a", "c")));
        assertThat(result.get(2), equalTo(newList("bb", "dd")));
        assertThat(result.get(3), equalTo(newList("eee")));
    }
    
    @Test
    public void testHead() {
        Integer[] xs = {1, 2, 3};
        
        assertThat(head(xs), equalTo(1));
    }
    
    @Test
    public void testHead_null() {
        Integer[] xs = null;
        
        assertNull(head(xs));
    }
    
    @Test
    public void testHeadOption() {
        Integer[] xs = {1, 2, 3};
        
        Option<Integer> result = headOption(xs);
        
        assertTrue(result.isDefined());
        assertThat(result.get(), equalTo(1));
    }
    
    @Test
    public void testHeadOption_empty() {
        Integer[] xs = {};
        
        Option<Integer> result = headOption(xs);
        
        assertFalse(result.isDefined());
    }
    
    @Test
    public void testTail() {
        Integer[] xs = {1, 2, 3};
        
        Iterable<Integer> result = tail(xs);
        
        assertThat(newList(result), equalTo(newList(2, 3)));
    }
    
    @Test
    public void testLast() {
        Integer[] xs = {1, 2, 3};
        
        assertThat(last(xs), equalTo(3));
    }
    
    @Test
    public void testLast_null() {
        Integer[] xs = null;
        
        assertNull(last(xs));
    }
    
    @Test
    public void testLastOption() {
        Integer[] xs = {1, 2, 3};
        
        Option<Integer> result = lastOption(xs);
        
        assertTrue(result.isDefined());
        assertThat(result.get(), equalTo(3));
    }
    
    @Test
    public void testLastOption_empty() {
        Integer[] xs = {};
        
        Option<Integer> result = lastOption(xs);
        
        assertFalse(result.isDefined());
    }
    
    @Test
    public void testInit() {
        Integer[] xs = {1, 2, 3};
        
        Iterable<Integer> result = init(xs);
        
        assertThat(newList(result), equalTo(newList(1, 2)));
    }
    
    @Test
    public void testTake() {
        Integer[] xs = {1, 2, 3, 4, 5};
        
        Iterable<Integer> result = take(3, xs);
        
        assertThat(newList(result), equalTo(newList(1, 2, 3)));
    }
    
    @Test
    public void testDrop() {
        Integer[] xs = {1, 2, 3, 4, 5};
        
        Iterable<Integer> result = drop(2, xs);
        
        assertThat(newList(result), equalTo(newList(3, 4, 5)));
    }
    
    @Test
    public void testTakeWhile() {
        Integer[] xs = {1, 2, 3, 4, 5};
        
        Iterable<Integer> result = takeWhile(new Apply<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer x) {
                return x < 4;
            }
        }, xs);
        
        assertThat(newList(result), equalTo(newList(1, 2, 3)));
    }
    
    @Test
    public void testDropWhile() {
        Integer[] xs = {1, 2, 3, 4, 5};
        
        Iterable<Integer> result = dropWhile(new Apply<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer x) {
                return x < 4;
            }
        }, xs);
        
        assertThat(newList(result), equalTo(newList(4, 5)));
    }
    
    @Test
    public void testSpan() {
        Integer[] xs = {1, 2, 3, 4, 5};
        
        Pair<Iterable<Integer>, Iterable<Integer>> result = span(new Apply<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer x) {
                return x < 4;
            }
        }, xs);
        
        assertThat(newList(result.left()), equalTo(newList(1, 2, 3)));
        assertThat(newList(result.right()), equalTo(newList(4, 5)));
    }
    
    @Test
    public void testPartition() {
        Integer[] xs = {1, 2, 3, 4, 5, 6};
        
        Pair<Iterable<Integer>, Iterable<String>> result = partition(new Apply<Integer, Either<Integer, String>>() {
            @Override
            public Either<Integer, String> apply(Integer x) {
                if (x % 2 == 0) {
                    return Either.left(x);
                } else {
                    return Either.right("odd");
                }
            }
        }, xs);
        
        assertThat(newList(result.left()), equalTo(newList(2, 4, 6)));
        assertThat(newList(result.right()), equalTo(newList("odd", "odd", "odd")));
    }
    
    @Test
    public void testSplit_byIndex() {
        Integer[] xs = {1, 2, 3, 4, 5};
        
        Pair<Iterable<Integer>, Iterable<Integer>> result = split(2, xs);
        
        assertThat(newList(result.left()), equalTo(newList(1, 2)));
        assertThat(newList(result.right()), equalTo(newList(3, 4, 5)));
    }
    
    @Test
    public void testSplit_headTail() {
        Integer[] xs = {1, 2, 3};
        
        Pair<Integer, Iterable<Integer>> result = split(xs);
        
        assertThat(result.left(), equalTo(1));
        assertThat(newList(result.right()), equalTo(newList(2, 3)));
    }
    
    @Test
    public void testEvery() {
        Integer[] xs = {1, 2, 3, 4, 5, 6, 7, 8};
        
        Iterable<Integer> result = every(3, xs);
        
        assertThat(newList(result), equalTo(newList(1, 4, 7)));
    }
    
    @Test
    public void testIsEmpty() {
        assertTrue(isEmpty(new Integer[]{}));
        assertFalse(isEmpty(new Integer[]{1}));
    }
    
    @Test
    public void testSize() {
        assertThat(size(new Integer[]{1, 2, 3}), equalTo(3));
        assertThat(size(new Integer[]{}), equalTo(0));
    }
    
    @Test
    public void testContains() {
        Integer[] xs = {1, 2, 3};
        
        assertTrue(contains(2, xs));
        assertFalse(contains(5, xs));
    }
    
    @Test
    public void testExists() {
        Integer[] xs = {1, 2, 3, 4, 5};
        
        assertTrue(exists(new Apply<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer x) {
                return x > 3;
            }
        }, xs));
        
        assertFalse(exists(new Apply<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer x) {
                return x > 10;
            }
        }, xs));
    }
    
    @Test
    public void testForall() {
        Integer[] xs = {2, 4, 6};
        
        assertTrue(forall(new Apply<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer x) {
                return x % 2 == 0;
            }
        }, xs));
        
        assertFalse(forall(new Apply<Integer, Boolean>() {
            @Override
            public Boolean apply(Integer x) {
                return x > 3;
            }
        }, xs));
    }
    
    @Test
    public void testCons() {
        Integer[] xs = {2, 3};
        
        Iterable<Integer> result = cons(1, xs);
        
        assertThat(newList(result), equalTo(newList(1, 2, 3)));
    }
    
    @Test
    public void testAppend() {
        Integer[] xs = {1, 2};
        
        Iterable<Integer> result = append(3, xs);
        
        assertThat(newList(result), equalTo(newList(1, 2, 3)));
    }
    
    @Test
    public void testConcat_arrayArray() {
        Integer[] a = {1, 2};
        Integer[] b = {3, 4};
        
        Iterable<Integer> result = concat(a, b);
        
        assertThat(newList(result), equalTo(newList(1, 2, 3, 4)));
    }
    
    @Test
    public void testConcat_arrayIterable() {
        Integer[] a = {1, 2};
        List<Integer> b = newList(3, 4);
        
        Iterable<Integer> result = concat(a, b);
        
        assertThat(newList(result), equalTo(newList(1, 2, 3, 4)));
    }
    
    @Test
    public void testConcat_iterableArray() {
        List<Integer> a = newList(1, 2);
        Integer[] b = {3, 4};
        
        Iterable<Integer> result = concat(a, b);
        
        assertThat(newList(result), equalTo(newList(1, 2, 3, 4)));
    }
    
    @Test
    public void testConcat_threeArrays() {
        Integer[] a = {1};
        Integer[] b = {2};
        Integer[] c = {3};
        
        Iterable<Integer> result = concat(a, b, c);
        
        assertThat(newList(result), equalTo(newList(1, 2, 3)));
    }
    
    @Test
    public void testSort() {
        Integer[] xs = {3, 1, 4, 1, 5, 9, 2, 6};
        
        Iterable<Integer> result = sort(xs);
        
        assertThat(newList(result), equalTo(newList(1, 1, 2, 3, 4, 5, 6, 9)));
    }
    
    @Test
    public void testSort_withComparator() {
        Integer[] xs = {1, 2, 3, 4, 5};
        
        Iterable<Integer> result = sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b.compareTo(a); // reverse order
            }
        }, xs);
        
        assertThat(newList(result), equalTo(newList(5, 4, 3, 2, 1)));
    }
    
    @Test
    public void testReduce_semiGroup() {
        SemiGroupInteger[] xs = {
            new SemiGroupInteger(1),
            new SemiGroupInteger(2),
            new SemiGroupInteger(3),
            new SemiGroupInteger(4)
        };
        
        Option<SemiGroupInteger> result = reduce(xs);
        
        assertTrue(result.isDefined());
        assertThat(result.get().value, equalTo(10));
    }
    
    // Helper class for testing SemiGroup reduce
    private static class SemiGroupInteger implements SemiGroup<SemiGroupInteger> {
        public final int value;
        
        public SemiGroupInteger(int value) {
            this.value = value;
        }
        
        @Override
        public SemiGroupInteger apply(Map.Entry<? extends SemiGroupInteger, ? extends SemiGroupInteger> t) {
            return new SemiGroupInteger(t.getKey().value + t.getValue().value);
        }
    }
    
    @Test
    public void testReduce_varargs() {
        SemiGroupInteger result = reduce(
            new SemiGroupInteger(1), 
            new SemiGroupInteger(2), 
            new SemiGroupInteger(3), 
            new SemiGroupInteger(4)
        );
        
        assertThat(result.value, equalTo(10));
    }
    
    @Test
    public void testFold() {
        Integer[] xs = {1, 2, 3, 4};
        
        ApplyBi<Integer, Integer, Integer> sum = new ApplyBi<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer acc, Integer val) {
                return acc + val;
            }
        };
        
        Integer result = fold(0, Function.of(sum), xs);
        
        assertThat(result, equalTo(10));
    }
    
    @Test
    public void testMin() {
        Integer[] xs = {3, 1, 4, 1, 5};
        
        Option<Integer> result = min(xs);
        
        assertTrue(result.isDefined());
        assertThat(result.get(), equalTo(1));
    }
    
    @Test
    public void testMin_varargs() {
        Integer result = min(3, 1, 4, 1, 5);
        
        assertThat(result, equalTo(1));
    }
    
    @Test
    public void testMinBy() {
        String[] xs = {"aaa", "b", "cc"};
        
        Option<String> result = minBy(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return Integer.compare(a.length(), b.length());
            }
        }, xs);
        
        assertTrue(result.isDefined());
        assertThat(result.get(), equalTo("b"));
    }
    
    @Test
    public void testMax() {
        Integer[] xs = {3, 1, 4, 1, 5};
        
        Option<Integer> result = max(xs);
        
        assertTrue(result.isDefined());
        assertThat(result.get(), equalTo(5));
    }
    
    @Test
    public void testMax_varargs() {
        Integer result = max(3, 1, 4, 1, 5);
        
        assertThat(result, equalTo(5));
    }
    
    @Test
    public void testMaxBy() {
        String[] xs = {"aaa", "b", "cc"};
        
        Option<String> result = maxBy(new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return Integer.compare(a.length(), b.length());
            }
        }, xs);
        
        assertTrue(result.isDefined());
        assertThat(result.get(), equalTo("aaa"));
    }
    
    @Test
    public void testOrdered() {
        assertTrue(ordered(1, 2, 3, 4));
        assertFalse(ordered(1, 3, 2, 4));
    }
    
    @Test
    public void testOrdered_withComparator() {
        Comparator<Integer> reverseOrder = new Comparator<Integer>() {
            @Override
            public int compare(Integer a, Integer b) {
                return b.compareTo(a);
            }
        };
        
        assertTrue(ordered(reverseOrder, 4, 3, 2, 1));
        assertFalse(ordered(reverseOrder, 1, 2, 3, 4));
    }
    
    @Test
    public void testZip_arrays() {
        Integer[] a = {1, 2, 3};
        String[] b = {"a", "b", "c"};
        
        Iterable<Pair<Integer, String>> result = zip(a, b);
        
        List<Pair<Integer, String>> resultList = newList(result);
        assertThat(resultList.get(0), equalTo(Pair.of(1, "a")));
        assertThat(resultList.get(1), equalTo(Pair.of(2, "b")));
        assertThat(resultList.get(2), equalTo(Pair.of(3, "c")));
    }
    
    @Test
    public void testZip_arrayIterable() {
        Integer[] a = {1, 2, 3};
        List<String> b = newList("a", "b", "c");
        
        Iterable<Pair<Integer, String>> result = zip(a, b);
        
        List<Pair<Integer, String>> resultList = newList(result);
        assertThat(resultList.get(0), equalTo(Pair.of(1, "a")));
    }
    
    @Test
    public void testZip_threeArrays() {
        Integer[] a = {1, 2};
        String[] b = {"a", "b"};
        Boolean[] c = {true, false};
        
        Iterable<Tuple3<Integer, String, Boolean>> result = zip(a, b, c);
        
        List<Tuple3<Integer, String, Boolean>> resultList = newList(result);
        assertThat(resultList.get(0), equalTo(Tuple.of(1, "a", true)));
        assertThat(resultList.get(1), equalTo(Tuple.of(2, "b", false)));
    }
    
    @Test
    public void testZipWithIndex() {
        String[] xs = {"a", "b", "c"};
        
        Iterable<Pair<Integer, String>> result = zipWithIndex(xs);
        
        List<Pair<Integer, String>> resultList = newList(result);
        assertThat(resultList.get(0), equalTo(Pair.of(0, "a")));
        assertThat(resultList.get(1), equalTo(Pair.of(1, "b")));
        assertThat(resultList.get(2), equalTo(Pair.of(2, "c")));
    }
    
    @Test
    public void testUnzip() {
        @SuppressWarnings("unchecked")
        Pair<Integer, String>[] xs = new Pair[]{
            Pair.of(1, "a"),
            Pair.of(2, "b"),
            Pair.of(3, "c")
        };
        
        Pair<Iterable<Integer>, Iterable<String>> result = unzip(xs);
        
        assertThat(newList(result.left()), equalTo(newList(1, 2, 3)));
        assertThat(newList(result.right()), equalTo(newList("a", "b", "c")));
    }
    
    @Test
    public void testUnzip3() {
        @SuppressWarnings("unchecked")
        Tuple3<Integer, String, Boolean>[] xs = new Tuple3[]{
            Tuple.of(1, "a", true),
            Tuple.of(2, "b", false)
        };
        
        Tuple3<Iterable<Integer>, Iterable<String>, Iterable<Boolean>> result = unzip3(xs);
        
        assertThat(newList(result._1), equalTo(newList(1, 2)));
        assertThat(newList(result._2), equalTo(newList("a", "b")));
        assertThat(newList(result._3), equalTo(newList(true, false)));
    }
    
    @Test
    public void testMkString_characters() {
        Character[] xs = {'a', 'b', 'c'};
        
        CharSequence result = mkString(xs);
        
        assertThat(result.toString(), equalTo("abc"));
    }
    
    @Test
    public void testMkString_withDelimiter() {
        CharSequence[] xs = {"a", "b", "c"};
        
        CharSequence result = mkString(", ", xs);
        
        assertThat(result.toString(), equalTo("a, b, c"));
    }
    
    @Test
    public void testReverse() {
        Integer[] xs = {1, 2, 3, 4};
        
        Iterable<Integer> result = reverse(xs);
        
        assertThat(newList(result), equalTo(newList(4, 3, 2, 1)));
    }
    
    @Test
    public void testDistinct() {
        Integer[] xs = {1, 2, 2, 3, 1, 4};
        
        Iterable<Integer> result = distinct(xs);
        
        assertThat(newList(result), equalTo(newList(1, 2, 3, 4)));
    }
    
    @Test
    public void testSequence() {
        @SuppressWarnings("unchecked")
        Apply<Integer, String>[] fs = new Apply[]{
            new Apply<Integer, String>() {
                @Override
                public String apply(Integer x) {
                    return "value:" + x;
                }
            },
            new Apply<Integer, String>() {
                @Override
                public String apply(Integer x) {
                    return "double:" + (x * 2);
                }
            }
        };
        
        Iterable<String> result = sequence(5, fs);
        
        assertThat(newList(result), equalTo(newList("value:5", "double:10")));
    }
    
    @Test
    public void testTranspose() {
        @SuppressWarnings("unchecked")
        Iterable<Integer>[] xs = new Iterable[]{
            newList(1, 2, 3),
            newList(4, 5, 6)
        };
        
        Iterable<Iterable<Integer>> result = transpose(xs);
        
        List<List<Integer>> resultList = newList(Functional.map(new Transformer<Iterable<Integer>, List<Integer>>() {
            @Override
            public List<Integer> transform(Iterable<Integer> source) {
                return newList(source);
            }
        }, result));
        
        assertThat(resultList.get(0), equalTo(newList(1, 4)));
        assertThat(resultList.get(1), equalTo(newList(2, 5)));
        assertThat(resultList.get(2), equalTo(newList(3, 6)));
    }
    
    @Test
    public void testUnlines() {
        CharSequence[] xs = {"line1", "line2", "line3"};
        
        CharSequence result = unlines(xs);
        
        assertThat(result.toString(), equalTo("line1\nline2\nline3"));
    }
}
