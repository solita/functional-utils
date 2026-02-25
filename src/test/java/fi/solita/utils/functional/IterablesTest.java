package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.it;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.distinct;
import static fi.solita.utils.functional.Functional.drop;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.flatten;
import static fi.solita.utils.functional.Functional.group;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.repeat;
import static fi.solita.utils.functional.Functional.reverse;
import static fi.solita.utils.functional.Functional.sort;
import static fi.solita.utils.functional.Functional.take;
import static fi.solita.utils.functional.Functional.transpose;
import static fi.solita.utils.functional.Functional.zip;
import static fi.solita.utils.functional.FunctionalA.concat;
import static fi.solita.utils.functional.FunctionalS.range;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

import fi.solita.utils.functional.Iterables.ConcatenatingIterable;
import fi.solita.utils.functional.Iterables.TransformingIterable;

public class IterablesTest {

    @Test
    public void testResolveSizeForCollection() {
        List<Integer> list = newList(1, 2, 3);
        Option<Long> size = Iterables.resolveSize.apply(list);
        assertTrue(size.isDefined());
        assertEquals(3L, size.get().longValue());
    }

    @Test
    public void testResolveSizeForOption() {
        Option<Integer> some = Option.Some(42);
        Option<Integer> none = Option.None();
        
        Option<Long> sizeOfSome = Iterables.resolveSize.apply(some);
        assertTrue(sizeOfSome.isDefined());
        assertEquals(1L, sizeOfSome.get().longValue());
        
        Option<Long> sizeOfNone = Iterables.resolveSize.apply(none);
        assertTrue(sizeOfNone.isDefined());
        assertEquals(0L, sizeOfNone.get().longValue());
    }

    @Test
    public void testRangeIterable() {
        Iterable<Integer> range = range(1, 5);
        List<Integer> result = newList(range);
        assertEquals(newList(1, 2, 3, 4, 5), result);
    }

    @Test
    public void testRangeIterableWithKnownSize() {
        Iterable<Integer> range = range(1, 5);
        Option<Long> size = Iterables.resolveSize.apply(range);
        assertTrue(size.isDefined());
        assertEquals(5L, size.get().longValue());
    }

    @Test
    public void testRepeatingIterableInfinite() {
        Iterable<String> repeat = repeat("hello");
        List<String> result = newList(take(3, repeat));
        assertEquals(newList("hello", "hello", "hello"), result);
    }

    @Test
    public void testRepeatingIterableFinite() {
        Iterable<String> repeat = repeat("hello", 3);
        List<String> result = newList(repeat);
        assertEquals(newList("hello", "hello", "hello"), result);
    }

    @Test
    public void testRepeatingIterableWithSize() {
        Iterable<String> repeat = repeat("hello", 5);
        Option<Long> size = Iterables.resolveSize.apply(repeat);
        assertTrue(size.isDefined());
        assertEquals(5L, size.get().longValue());
    }

    @Test
    public void testTransposingIterable() {
        Iterable<? extends Iterable<Integer>> lists = newList(
            newList(1, 2, 3),
            newList(4, 5, 6),
            newList(7, 8, 9)
        );
        Iterable<Iterable<Integer>> transposed = transpose(lists);
        List<List<Integer>> result = newList(map(new Transformer<Iterable<Integer>, List<Integer>>() {
            @Override
            public List<Integer> transform(Iterable<Integer> source) {
                return newList(source);
            }
        }, transposed));
        
        assertEquals(3, result.size());
        assertEquals(newList(1, 4, 7), result.get(0));
        assertEquals(newList(2, 5, 8), result.get(1));
        assertEquals(newList(3, 6, 9), result.get(2));
    }

    @Test
    public void testTransposingIterableWithDifferentSizes() {
        Iterable<? extends Iterable<Integer>> lists = newList(
            newList(1, 2, 3),
            newList(4, 5)
        );
        Iterable<Iterable<Integer>> transposed = transpose(lists);
        List<List<Integer>> result = newList(map(new Transformer<Iterable<Integer>, List<Integer>>() {
            @Override
            public List<Integer> transform(Iterable<Integer> source) {
                return newList(source);
            }
        }, transposed));
        
        // Transpose should stop at the shortest list
        assertEquals(2, result.size());
        assertEquals(newList(1, 4), result.get(0));
        assertEquals(newList(2, 5), result.get(1));
    }

    @Test
    public void testZippingIterable() {
        Iterable<Integer> list1 = newList(1, 2, 3);
        Iterable<String> list2 = newList("a", "b", "c");
        
        Iterable<Pair<Integer, String>> zipped = zip(list1, list2);
        List<Pair<Integer, String>> result = newList(zipped);
        
        assertEquals(3, result.size());
        assertEquals(Pair.of(1, "a"), result.get(0));
        assertEquals(Pair.of(2, "b"), result.get(1));
        assertEquals(Pair.of(3, "c"), result.get(2));
    }

    @Test
    public void testZippingIterableWithDifferentSizes() {
        Iterable<Integer> list1 = newList(1, 2, 3);
        Iterable<String> list2 = newList("a", "b");
        
        Iterable<Pair<Integer, String>> zipped = zip(list1, list2);
        List<Pair<Integer, String>> result = newList(zipped);
        
        assertEquals(2, result.size());
        assertEquals(Pair.of(1, "a"), result.get(0));
        assertEquals(Pair.of(2, "b"), result.get(1));
    }

    @Test
    public void testZippingIterableSize() {
        Iterable<Integer> list1 = newList(1, 2, 3);
        Iterable<String> list2 = newList("a", "b", "c", "d");
        
        Iterable<Pair<Integer, String>> zipped = zip(list1, list2);
        Option<Long> size = Iterables.resolveSize.apply(zipped);
        
        assertTrue(size.isDefined());
        assertEquals(3L, size.get().longValue());
    }

    @Test
    public void testConcatenatingIterable() {
        Iterable<Integer> list1 = newList(1, 2);
        Iterable<Integer> list2 = newList(3, 4);
        Iterable<Integer> list3 = newList(5);
        
        Iterable<Integer> concatenated = concat(list1, list2, list3);
        List<Integer> result = newList(concatenated);
        
        assertEquals(newList(1, 2, 3, 4, 5), result);
    }

    @Test
    public void testConcatenatingIterableSize() {
        Iterable<Integer> list1 = newList(1, 2);
        Iterable<Integer> list2 = newList(3, 4, 5);
        
        Iterable<Integer> concatenated = new ConcatenatingIterable<>(newList(list1, list2));
        Option<Long> size = Iterables.resolveSize.apply(concatenated);
        
        assertTrue(size.isDefined());
        assertEquals(5L, size.get().longValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConcatenatingIterableThrowsOnNull() {
        new ConcatenatingIterable<Integer>(null);
    }

    @Test
    public void testConcatenatingIterableFlattenesNestedConcats() {
        Iterable<Integer> list1 = newList(1, 2);
        Iterable<Integer> list2 = newList(3, 4);
        Iterable<Integer> concat1 = new ConcatenatingIterable<>(newList(list1, list2));
        
        Iterable<Integer> list3 = newList(5, 6);
        Iterable<Integer> concat2 = new ConcatenatingIterable<>(newList(concat1, list3));
        
        List<Integer> result = newList(concat2);
        assertEquals(newList(1, 2, 3, 4, 5, 6), result);
    }

    @Test
    public void testFlatteningIterable() {
        @SuppressWarnings("unchecked")
        Iterable<? extends Iterable<Integer>> lists = (Iterable<? extends Iterable<Integer>>) (Iterable<?>) newList(
            newList(1, 2),
            null,
            newList(3, 4),
            null,
            newList(5)
        );
        
        Iterable<Integer> flattened = flatten(lists);
        List<Integer> result = newList(flattened);
        
        assertEquals(newList(1, 2, 3, 4, 5), result);
    }

    @Test
    public void testDistinctIterable() {
        Iterable<Integer> list = newList(1, 2, 2, 3, 1, 4, 3, 5);
        Iterable<Integer> distinctList = distinct(list);
        List<Integer> result = newList(distinctList);
        
        assertEquals(newList(1, 2, 3, 4, 5), result);
    }

    @Test
    public void testDistinctIterablePreservesOrder() {
        Iterable<String> list = newList("a", "b", "c", "b", "d", "a");
        Iterable<String> distinctList = distinct(list);
        List<String> result = newList(distinctList);
        
        assertEquals(newList("a", "b", "c", "d"), result);
    }

    @Test
    public void testFilteringIterable() {
        Iterable<Integer> list = newList(1, 2, 3, 4, 5, 6);
        Iterable<Integer> filtered = filter(new Predicate<Integer>() {
            @Override
            public boolean accept(Integer candidate) {
                return candidate % 2 == 0;
            }
        }, list);
        
        List<Integer> result = newList(filtered);
        assertEquals(newList(2, 4, 6), result);
    }

    @Test
    public void testFilteringIterableEmptyResult() {
        Iterable<Integer> list = newList(1, 3, 5);
        Iterable<Integer> filtered = filter(new Predicate<Integer>() {
            @Override
            public boolean accept(Integer candidate) {
                return candidate % 2 == 0;
            }
        }, list);
        
        List<Integer> result = newList(filtered);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testTransformingIterable() {
        Iterable<Integer> list = newList(1, 2, 3);
        Iterable<String> transformed = map(new Transformer<Integer, String>() {
            @Override
            public String transform(Integer source) {
                return "num" + source;
            }
        }, list);
        
        List<String> result = newList(transformed);
        assertEquals(newList("num1", "num2", "num3"), result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTransformingIterableThrowsOnNullIterable() {
        new TransformingIterable<Integer, String>(null, new Transformer<Integer, String>() {
            @Override
            public String transform(Integer source) {
                return source.toString();
            }
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testTransformingIterableThrowsOnNullTransformer() {
        new TransformingIterable<Integer, String>(newList(1, 2, 3), null);
    }

    @Test
    public void testTransformingIterablePreservesSize() {
        Iterable<Integer> list = newList(1, 2, 3);
        Iterable<String> transformed = map(new Transformer<Integer, String>() {
            @Override
            public String transform(Integer source) {
                return source.toString();
            }
        }, list);
        
        Option<Long> size = Iterables.resolveSize.apply(transformed);
        assertTrue(size.isDefined());
        assertEquals(3L, size.get().longValue());
    }

    @Test
    public void testReversingIterable() {
        Iterable<Integer> list = newList(1, 2, 3, 4, 5);
        Iterable<Integer> reversed = reverse(list);
        List<Integer> result = newList(reversed);
        
        assertEquals(newList(5, 4, 3, 2, 1), result);
    }

    @Test
    public void testReversingIterablePreservesSize() {
        Iterable<Integer> list = newList(1, 2, 3);
        Iterable<Integer> reversed = reverse(list);
        
        Option<Long> size = Iterables.resolveSize.apply(reversed);
        assertTrue(size.isDefined());
        assertEquals(3L, size.get().longValue());
    }

    @Test
    public void testCharSequenceIterable() {
        String str = "hello";
        Iterable<Character> chars = it(str);
        List<Character> result = newList(chars);
        
        assertEquals(5, result.size());
        assertEquals(Character.valueOf('h'), result.get(0));
        assertEquals(Character.valueOf('e'), result.get(1));
        assertEquals(Character.valueOf('l'), result.get(2));
        assertEquals(Character.valueOf('l'), result.get(3));
        assertEquals(Character.valueOf('o'), result.get(4));
    }

    @Test
    public void testCharSequenceIterableSize() {
        String str = "test";
        Iterable<Character> chars = it(str);
        Option<Long> size = Iterables.resolveSize.apply(chars);
        
        assertTrue(size.isDefined());
        assertEquals(4L, size.get().longValue());
    }

    @Test
    public void testSortingIterable() {
        Iterable<Integer> list = newList(3, 1, 4, 1, 5, 9, 2, 6);
        Iterable<Integer> sorted = sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        }, list);
        
        List<Integer> result = newList(sorted);
        assertEquals(newList(1, 1, 2, 3, 4, 5, 6, 9), result);
    }

    @Test
    public void testSortingIterableDescending() {
        Iterable<Integer> list = newList(3, 1, 4, 5, 2);
        Iterable<Integer> sorted = sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        }, list);
        
        List<Integer> result = newList(sorted);
        assertEquals(newList(5, 4, 3, 2, 1), result);
    }

    @Test
    public void testSortingIterablePreservesSize() {
        Iterable<Integer> list = newList(3, 1, 2);
        Iterable<Integer> sorted = sort(Compare.byNatural(), list);
        
        Option<Long> size = Iterables.resolveSize.apply(sorted);
        assertTrue(size.isDefined());
        assertEquals(3L, size.get().longValue());
    }

    @Test
    public void testTakingIterable() {
        Iterable<Integer> list = newList(1, 2, 3, 4, 5);
        Iterable<Integer> taken = take(3, list);
        List<Integer> result = newList(taken);
        
        assertEquals(newList(1, 2, 3), result);
    }

    @Test
    public void testTakingIterableMoreThanAvailable() {
        Iterable<Integer> list = newList(1, 2, 3);
        Iterable<Integer> taken = take(10, list);
        List<Integer> result = newList(taken);
        
        assertEquals(newList(1, 2, 3), result);
    }

    @Test
    public void testTakingIterableNegativeAmount() {
        Iterable<Integer> list = newList(1, 2, 3);
        Iterable<Integer> taken = take(-5, list);
        List<Integer> result = newList(taken);
        
        assertTrue(result.isEmpty());
    }

    @Test
    public void testTakingIterableSize() {
        Iterable<Integer> list = newList(1, 2, 3, 4, 5);
        Iterable<Integer> taken = take(3, list);
        
        Option<Long> size = Iterables.resolveSize.apply(taken);
        assertTrue(size.isDefined());
        assertEquals(3L, size.get().longValue());
    }

    @Test
    public void testDroppingIterable() {
        Iterable<Integer> list = newList(1, 2, 3, 4, 5);
        Iterable<Integer> dropped = drop(2, list);
        List<Integer> result = newList(dropped);
        
        assertEquals(newList(3, 4, 5), result);
    }

    @Test
    public void testDroppingIterableMoreThanAvailable() {
        Iterable<Integer> list = newList(1, 2, 3);
        Iterable<Integer> dropped = drop(10, list);
        List<Integer> result = newList(dropped);
        
        assertTrue(result.isEmpty());
    }

    @Test
    public void testDroppingIterableNegativeAmount() {
        Iterable<Integer> list = newList(1, 2, 3);
        Iterable<Integer> dropped = drop(-5, list);
        List<Integer> result = newList(dropped);
        
        assertEquals(newList(1, 2, 3), result);
    }

    @Test
    public void testDroppingIterableSize() {
        Iterable<Integer> list = newList(1, 2, 3, 4, 5);
        Iterable<Integer> dropped = drop(2, list);
        
        Option<Long> size = Iterables.resolveSize.apply(dropped);
        assertTrue(size.isDefined());
        assertEquals(3L, size.get().longValue());
    }

    @Test
    public void testGroupingIterable() {
        Iterable<Integer> list = newList(1, 1, 2, 2, 2, 3, 4, 4);
        Iterable<Iterable<Integer>> grouped = group(list);
        List<List<Integer>> result = newList(map(new Transformer<Iterable<Integer>, List<Integer>>() {
            @Override
            public List<Integer> transform(Iterable<Integer> source) {
                return newList(source);
            }
        }, grouped));
        
        assertEquals(4, result.size());
        assertEquals(newList(1, 1), result.get(0));
        assertEquals(newList(2, 2, 2), result.get(1));
        assertEquals(newList(3), result.get(2));
        assertEquals(newList(4, 4), result.get(3));
    }

    @Test
    public void testGroupingIterableWithCustomComparator() {
        Iterable<Integer> list = newList(1, 2, 3, 6, 7, 10);
        // Group by whether difference is 1
        Iterable<Iterable<Integer>> grouped = group(new ApplyBi<Integer, Integer, Boolean>() {
            @Override
            public Boolean apply(Integer t1, Integer t2) {
                return t2 - t1 == 1;
            }
        }, list);
        List<List<Integer>> result = newList(map(new Transformer<Iterable<Integer>, List<Integer>>() {
            @Override
            public List<Integer> transform(Iterable<Integer> source) {
                return newList(source);
            }
        }, grouped));
        
        assertEquals(3, result.size());
        assertEquals(newList(1, 2, 3), result.get(0));
        assertEquals(newList(6, 7), result.get(1));
        assertEquals(newList(10), result.get(2));
    }

    @Test
    public void testIteratorRemoveThrowsUnsupportedOperation() {
        Iterable<Integer> range = range(1, 3);
        Iterator<Integer> it = range.iterator();
        it.next();
        
        try {
            it.remove();
            fail("Expected UnsupportedOperationException");
        } catch (UnsupportedOperationException e) {
            // Expected
        }
    }

    @Test(expected = NoSuchElementException.class)
    public void testRangeIteratorThrowsNoSuchElementException() {
        Iterable<Integer> range = range(1, 2);
        Iterator<Integer> it = range.iterator();
        it.next();
        it.next();
        it.next(); // Should throw
    }

    @Test(expected = NoSuchElementException.class)
    public void testRepeatingIteratorThrowsNoSuchElementException() {
        Iterable<String> repeat = repeat("test", 2);
        Iterator<String> it = repeat.iterator();
        it.next();
        it.next();
        it.next(); // Should throw
    }

    @Test(expected = NoSuchElementException.class)
    public void testTransposingIteratorThrowsNoSuchElementException() {
        Iterable<? extends Iterable<Integer>> lists = newList(
            newList(1, 2),
            newList(3, 4)
        );
        Iterable<Iterable<Integer>> transposed = transpose(lists);
        Iterator<Iterable<Integer>> it = transposed.iterator();
        it.next();
        it.next();
        it.next(); // Should throw
    }

    @Test(expected = NoSuchElementException.class)
    public void testTakingIteratorThrowsNoSuchElementException() {
        Iterable<Integer> list = newList(1, 2);
        Iterable<Integer> taken = take(2, list);
        Iterator<Integer> it = taken.iterator();
        it.next();
        it.next();
        it.next(); // Should throw
    }

    @Test(expected = NoSuchElementException.class)
    public void testFilteringIteratorThrowsNoSuchElementException() {
        Iterable<Integer> list = newList(1, 2, 3);
        Iterable<Integer> filtered = filter(Predicates.equalTo(1), list);
        Iterator<Integer> it = filtered.iterator();
        it.next();
        it.next(); // Should throw
    }

    @Test(expected = NoSuchElementException.class)
    public void testGroupingIteratorThrowsNoSuchElementException() {
        Iterable<Integer> list = newList(1, 2);
        Iterable<Iterable<Integer>> grouped = group(list);
        Iterator<Iterable<Integer>> it = grouped.iterator();
        it.next();
        it.next();
        it.next(); // Should throw
    }
}
