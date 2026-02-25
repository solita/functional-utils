package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.emptySet;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.FunctionalS.groupIntersecting;
import static fi.solita.utils.functional.FunctionalS.intersection;
import static fi.solita.utils.functional.FunctionalS.product;
import static fi.solita.utils.functional.FunctionalS.range;
import static fi.solita.utils.functional.FunctionalS.sum;
import static fi.solita.utils.functional.FunctionalS.symmetricDifference;
import static fi.solita.utils.functional.FunctionalS.union;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Set;

import org.junit.Test;

public class FunctionalSTest {

    @Test
    public void testSumWithLongIterable() {
        assertThat(sum(newList(1L, 2L, 3L, 4L)), equalTo(10L));
        assertThat(sum(newList(0L)), equalTo(0L));
        assertThat(sum(Collections.<Long>emptyList()), equalTo(0L));
    }

    @Test
    public void testSumWithLongArray() {
        assertThat(sum(1L, 2L, 3L, 4L), equalTo(10L));
        assertThat(sum(0L), equalTo(0L));
        assertThat(sum(new long[]{}), equalTo(0L));
    }

    @Test
    public void testSumWithIntArray() {
        assertThat(sum(1, 2, 3, 4), equalTo(10L));
        assertThat(sum(0), equalTo(0L));
        assertThat(sum(new int[]{}), equalTo(0L));
    }

    @Test
    public void testSumWithShortArray() {
        assertThat(sum((short)1, (short)2, (short)3, (short)4), equalTo(10L));
        assertThat(sum((short)0), equalTo(0L));
        assertThat(sum(new short[]{}), equalTo(0L));
    }

    @Test
    public void testProductWithLongIterable() {
        assertThat(product(newList(2L, 3L, 4L)), equalTo(24L));
        assertThat(product(newList(1L)), equalTo(1L));
        assertThat(product(newList(0L, 5L)), equalTo(0L));
        assertThat(product(Collections.<Long>emptyList()), equalTo(1L));
    }

    @Test
    public void testProductWithLongArray() {
        assertThat(product(2L, 3L, 4L), equalTo(24L));
        assertThat(product(1L), equalTo(1L));
        assertThat(product(0L, 5L), equalTo(0L));
        assertThat(product(new long[]{}), equalTo(1L));
    }

    @Test
    public void testProductWithIntArray() {
        assertThat(product(2, 3, 4), equalTo(24L));
        assertThat(product(1), equalTo(1L));
        assertThat(product(0, 5), equalTo(0L));
        assertThat(product(new int[]{}), equalTo(1L));
    }

    @Test
    public void testProductWithShortArray() {
        assertThat(product((short)2, (short)3, (short)4), equalTo(24L));
        assertThat(product((short)1), equalTo(1L));
        assertThat(product((short)0, (short)5), equalTo(0L));
        assertThat(product(new short[]{}), equalTo(1L));
    }

    @Test
    public void testRangeShortFromInclusive() {
        Iterable<Short> result = range((short)1);
        assertThat(newList(Functional.take(5, result)), equalTo(newList((short)1, (short)2, (short)3, (short)4, (short)5)));
    }

    @Test
    public void testRangeIntFromInclusive() {
        Iterable<Integer> result = range(1);
        assertThat(newList(Functional.take(5, result)), equalTo(newList(1, 2, 3, 4, 5)));
    }

    @Test
    public void testRangeLongFromInclusive() {
        Iterable<Long> result = range(1L);
        assertThat(newList(Functional.take(5, result)), equalTo(newList(1L, 2L, 3L, 4L, 5L)));
    }

    @Test
    public void testRangeShortFromToInclusive() {
        assertThat(newList(range((short)1, (short)5)), equalTo(newList((short)1, (short)2, (short)3, (short)4, (short)5)));
        assertThat(newList(range((short)5, (short)5)), equalTo(newList((short)5)));
        assertTrue(newList(range((short)5, (short)1)).isEmpty());
    }

    @Test
    public void testRangeIntFromToInclusive() {
        assertThat(newList(range(1, 5)), equalTo(newList(1, 2, 3, 4, 5)));
        assertThat(newList(range(5, 5)), equalTo(newList(5)));
        assertTrue(newList(range(5, 1)).isEmpty());
    }

    @Test
    public void testRangeLongFromToInclusive() {
        assertThat(newList(range(1L, 5L)), equalTo(newList(1L, 2L, 3L, 4L, 5L)));
        assertThat(newList(range(5L, 5L)), equalTo(newList(5L)));
        assertTrue(newList(range(5L, 1L)).isEmpty());
    }

    @Test
    public void testUnionTwoSets() {
        Set<Integer> set1 = newSet(1, 2, 3);
        Set<Integer> set2 = newSet(3, 4, 5);
        assertThat(union(set1, set2), equalTo(newSet(1, 2, 3, 4, 5)));
    }

    @Test
    public void testUnionThreeSets() {
        Set<Integer> set1 = newSet(1, 2);
        Set<Integer> set2 = newSet(3, 4);
        Set<Integer> set3 = newSet(5, 6);
        assertThat(union(set1, set2, set3), equalTo(newSet(1, 2, 3, 4, 5, 6)));
    }

    @Test
    public void testUnionFourSets() {
        Set<Integer> set1 = newSet(1, 2);
        Set<Integer> set2 = newSet(3, 4);
        Set<Integer> set3 = newSet(5, 6);
        Set<Integer> set4 = newSet(7, 8);
        assertThat(union(set1, set2, set3, set4), equalTo(newSet(1, 2, 3, 4, 5, 6, 7, 8)));
    }

    @Test
    public void testUnionFiveSets() {
        Set<Integer> set1 = newSet(1);
        Set<Integer> set2 = newSet(2);
        Set<Integer> set3 = newSet(3);
        Set<Integer> set4 = newSet(4);
        Set<Integer> set5 = newSet(5);
        assertThat(union(set1, set2, set3, set4, set5), equalTo(newSet(1, 2, 3, 4, 5)));
    }

    @Test
    public void testUnionSixSets() {
        Set<Integer> set1 = newSet(1);
        Set<Integer> set2 = newSet(2);
        Set<Integer> set3 = newSet(3);
        Set<Integer> set4 = newSet(4);
        Set<Integer> set5 = newSet(5);
        Set<Integer> set6 = newSet(6);
        assertThat(union(set1, set2, set3, set4, set5, set6), equalTo(newSet(1, 2, 3, 4, 5, 6)));
    }

    @Test
    public void testUnionSevenOrMoreSets() {
        Set<Integer> set1 = newSet(1);
        Set<Integer> set2 = newSet(2);
        Set<Integer> set3 = newSet(3);
        Set<Integer> set4 = newSet(4);
        Set<Integer> set5 = newSet(5);
        Set<Integer> set6 = newSet(6);
        Set<Integer> set7 = newSet(7);
        Set<Integer> set8 = newSet(8);
        assertThat(union(set1, set2, set3, set4, set5, set6, set7, set8), equalTo(newSet(1, 2, 3, 4, 5, 6, 7, 8)));
    }

    @Test
    public void testUnionIterable() {
        Set<Integer> set1 = newSet(1, 2);
        Set<Integer> set2 = newSet(3, 4);
        Set<Integer> set3 = newSet(5, 6);
        Iterable<Set<Integer>> sets = newList(set1, set2, set3);
        assertThat(union(sets), equalTo(newSet(1, 2, 3, 4, 5, 6)));
    }

    @Test
    public void testUnionWithEmptySets() {
        Set<Integer> set1 = newSet(1, 2);
        Set<Integer> set2 = emptySet();
        assertThat(union(set1, set2), equalTo(newSet(1, 2)));
    }

    @Test
    public void testIntersectionTwoSets() {
        Set<Integer> set1 = newSet(1, 2, 3, 4);
        Set<Integer> set2 = newSet(3, 4, 5, 6);
        assertThat(intersection(set1, set2), equalTo(newSet(3, 4)));
    }

    @Test
    public void testIntersectionThreeSets() {
        Set<Integer> set1 = newSet(1, 2, 3, 4);
        Set<Integer> set2 = newSet(2, 3, 4, 5);
        Set<Integer> set3 = newSet(3, 4, 5, 6);
        assertThat(intersection(set1, set2, set3), equalTo(newSet(3, 4)));
    }

    @Test
    public void testIntersectionFourSets() {
        Set<Integer> set1 = newSet(1, 2, 3, 4, 5);
        Set<Integer> set2 = newSet(2, 3, 4, 5, 6);
        Set<Integer> set3 = newSet(3, 4, 5, 6, 7);
        Set<Integer> set4 = newSet(4, 5, 6, 7, 8);
        assertThat(intersection(set1, set2, set3, set4), equalTo(newSet(4, 5)));
    }

    @Test
    public void testIntersectionFiveSets() {
        Set<Integer> set1 = newSet(1, 2, 3, 4, 5);
        Set<Integer> set2 = newSet(2, 3, 4, 5, 6);
        Set<Integer> set3 = newSet(3, 4, 5, 6, 7);
        Set<Integer> set4 = newSet(4, 5, 6, 7, 8);
        Set<Integer> set5 = newSet(5, 6, 7, 8, 9);
        assertThat(intersection(set1, set2, set3, set4, set5), equalTo(newSet(5)));
    }

    @Test
    public void testIntersectionSixSets() {
        Set<Integer> set1 = newSet(1, 2, 3, 4, 5, 10);
        Set<Integer> set2 = newSet(2, 3, 4, 5, 6, 10);
        Set<Integer> set3 = newSet(3, 4, 5, 6, 7, 10);
        Set<Integer> set4 = newSet(4, 5, 6, 7, 8, 10);
        Set<Integer> set5 = newSet(5, 6, 7, 8, 9, 10);
        Set<Integer> set6 = newSet(5, 10);
        assertThat(intersection(set1, set2, set3, set4, set5, set6), equalTo(newSet(5, 10)));
    }

    @Test
    public void testIntersectionSevenOrMoreSets() {
        Set<Integer> set1 = newSet(1, 2, 3, 4, 5, 10);
        Set<Integer> set2 = newSet(2, 3, 4, 5, 6, 10);
        Set<Integer> set3 = newSet(3, 4, 5, 6, 7, 10);
        Set<Integer> set4 = newSet(4, 5, 6, 7, 8, 10);
        Set<Integer> set5 = newSet(5, 6, 7, 8, 9, 10);
        Set<Integer> set6 = newSet(5, 10);
        Set<Integer> set7 = newSet(10);
        assertThat(intersection(set1, set2, set3, set4, set5, set6, set7), equalTo(newSet(10)));
    }

    @Test
    public void testIntersectionIterable() {
        Set<Integer> set1 = newSet(1, 2, 3, 4);
        Set<Integer> set2 = newSet(2, 3, 4, 5);
        Set<Integer> set3 = newSet(3, 4, 5, 6);
        Iterable<Set<Integer>> sets = newList(set1, set2, set3);
        assertThat(intersection(sets), equalTo(newSet(3, 4)));
    }

    @Test
    public void testIntersectionWithNoCommonElements() {
        Set<Integer> set1 = newSet(1, 2);
        Set<Integer> set2 = newSet(3, 4);
        assertTrue(intersection(set1, set2).isEmpty());
    }

    @Test
    public void testIntersectionIterableWithNull() {
        Set<Integer> result = intersection((Iterable<Set<Integer>>)null);
        assertThat(result, equalTo(null));
    }

    @Test
    public void testIntersectionIterableEmpty() {
        Iterable<Set<Integer>> emptySets = Collections.<Set<Integer>>emptyList();
        assertThat(intersection(emptySets), equalTo(Collections.<Integer>emptySet()));
    }

    @Test
    public void testGroupIntersectingNoIntersection() {
        Set<Integer> set1 = newSet(1, 2);
        Set<Integer> set2 = newSet(3, 4);
        Set<Integer> set3 = newSet(5, 6);
        Iterable<Set<Integer>> result = groupIntersecting(newList(set1, set2, set3));
        List<Set<Integer>> resultList = newList(result);
        assertThat(resultList.size(), equalTo(3));
        assertTrue(resultList.contains(set1));
        assertTrue(resultList.contains(set2));
        assertTrue(resultList.contains(set3));
    }

    @Test
    public void testGroupIntersectingWithIntersection() {
        Set<Integer> set1 = newSet(1, 2, 3);
        Set<Integer> set2 = newSet(3, 4, 5);
        Set<Integer> set3 = newSet(5, 6, 7);
        Set<Integer> set4 = newSet(8, 9);
        Iterable<Set<Integer>> result = groupIntersecting(newList(set1, set2, set3, set4));
        List<Set<Integer>> resultList = newList(result);
        assertThat(resultList.size(), equalTo(2));
        assertTrue(resultList.contains(newSet(1, 2, 3, 4, 5, 6, 7)));
        assertTrue(resultList.contains(set4));
    }

    @Test
    public void testGroupIntersectingAllIntersect() {
        Set<Integer> set1 = newSet(1, 2, 3);
        Set<Integer> set2 = newSet(2, 3, 4);
        Set<Integer> set3 = newSet(3, 4, 5);
        Iterable<Set<Integer>> result = groupIntersecting(newList(set1, set2, set3));
        List<Set<Integer>> resultList = newList(result);
        assertThat(resultList.size(), equalTo(1));
        assertThat(resultList.get(0), equalTo(newSet(1, 2, 3, 4, 5)));
    }

    @Test
    public void testGroupIntersectingMultipleGroups() {
        Set<Integer> set1 = newSet(1, 2);
        Set<Integer> set2 = newSet(2, 3);
        Set<Integer> set3 = newSet(4, 5);
        Set<Integer> set4 = newSet(5, 6);
        Iterable<Set<Integer>> result = groupIntersecting(newList(set1, set2, set3, set4));
        List<Set<Integer>> resultList = newList(result);
        assertThat(resultList.size(), equalTo(2));
        assertTrue(resultList.contains(newSet(1, 2, 3)));
        assertTrue(resultList.contains(newSet(4, 5, 6)));
    }

    @Test
    public void testGroupIntersectingNull() {
        assertThat(groupIntersecting(null), equalTo(null));
    }

    @Test
    public void testGroupIntersectingEmpty() {
        Iterable<Set<Integer>> emptySets = Collections.<Set<Integer>>emptySet();
        assertThat(newSet(groupIntersecting(emptySets)), equalTo(Collections.<Set<Integer>>emptySet()));
    }

    @Test
    public void testSymmetricDifference() {
        Set<Integer> set1 = newSet(1, 2, 3, 4);
        Set<Integer> set2 = newSet(3, 4, 5, 6);
        Pair<Set<Integer>, Set<Integer>> result = symmetricDifference(set1, set2);
        assertThat(result.left(), equalTo(newSet(1, 2)));
        assertThat(result.right(), equalTo(newSet(5, 6)));
    }

    @Test
    public void testSymmetricDifferenceNoOverlap() {
        Set<Integer> set1 = newSet(1, 2);
        Set<Integer> set2 = newSet(3, 4);
        Pair<Set<Integer>, Set<Integer>> result = symmetricDifference(set1, set2);
        assertThat(result.left(), equalTo(newSet(1, 2)));
        assertThat(result.right(), equalTo(newSet(3, 4)));
    }

    @Test
    public void testSymmetricDifferenceCompleteOverlap() {
        Set<Integer> set1 = newSet(1, 2, 3);
        Set<Integer> set2 = newSet(1, 2, 3);
        Pair<Set<Integer>, Set<Integer>> result = symmetricDifference(set1, set2);
        assertTrue(result.left().isEmpty());
        assertTrue(result.right().isEmpty());
    }

    @Test
    public void testSymmetricDifferenceWithEmptySets() {
        Set<Integer> set1 = newSet(1, 2, 3);
        Set<Integer> set2 = emptySet();
        Pair<Set<Integer>, Set<Integer>> result = symmetricDifference(set1, set2);
        assertThat(result.left(), equalTo(newSet(1, 2, 3)));
        assertTrue(result.right().isEmpty());
    }
}
