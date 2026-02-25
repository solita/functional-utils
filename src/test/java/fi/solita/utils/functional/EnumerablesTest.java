package fi.solita.utils.functional;

import static fi.solita.utils.functional.Functional.head;
import static fi.solita.utils.functional.Functional.last;
import static fi.solita.utils.functional.Functional.range;
import static fi.solita.utils.functional.Functional.tail;
import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EnumerablesTest {

    @Test
    public void testAsciiAlpha() {
        Iterable<Character> asciiAlpha = range(Enumerables.asciiAlpha);
        
        assertEquals(Character.valueOf('a'), head(asciiAlpha));
        assertEquals(Character.valueOf('z'), last(asciiAlpha));
        assertEquals(Character.valueOf('b'), head(tail(asciiAlpha)));
    }
    
    @Test
    public void testAsciiAlpha_bounds() {
        assertEquals(Character.valueOf('a'), Enumerables.asciiAlpha.minBound());
        assertEquals(Character.valueOf('z'), Enumerables.asciiAlpha.maxBound());
    }
    
    @Test
    public void testAsciiAlpha_succ() {
        assertEquals(Some('b'), Enumerables.asciiAlpha.succ('a'));
        assertEquals(Some('c'), Enumerables.asciiAlpha.succ('b'));
        assertEquals(Some('z'), Enumerables.asciiAlpha.succ('y'));
        assertEquals(None(), Enumerables.asciiAlpha.succ('z'));
        assertEquals(None(), Enumerables.asciiAlpha.succ(null));
    }
    
    @Test
    public void testAsciiAlpha_pred() {
        assertEquals(None(), Enumerables.asciiAlpha.pred('a'));
        assertEquals(Some('a'), Enumerables.asciiAlpha.pred('b'));
        assertEquals(Some('y'), Enumerables.asciiAlpha.pred('z'));
        assertEquals(None(), Enumerables.asciiAlpha.pred(null));
    }
    
    @Test
    public void testShorts_bounds() {
        assertEquals(Short.valueOf(Short.MIN_VALUE), Enumerables.shorts.minBound());
        assertEquals(Short.valueOf(Short.MAX_VALUE), Enumerables.shorts.maxBound());
    }
    
    @Test
    public void testShorts_succ() {
        assertEquals(Some((short)1), Enumerables.shorts.succ((short)0));
        assertEquals(Some((short)0), Enumerables.shorts.succ((short)-1));
        assertEquals(Some((short)(Short.MAX_VALUE)), Enumerables.shorts.succ((short)(Short.MAX_VALUE - 1)));
        assertEquals(None(), Enumerables.shorts.succ(Short.MAX_VALUE));
        assertEquals(None(), Enumerables.shorts.succ(null));
    }
    
    @Test
    public void testShorts_pred() {
        assertEquals(Some((short)-1), Enumerables.shorts.pred((short)0));
        assertEquals(Some((short)0), Enumerables.shorts.pred((short)1));
        assertEquals(Some((short)(Short.MIN_VALUE)), Enumerables.shorts.pred((short)(Short.MIN_VALUE + 1)));
        assertEquals(None(), Enumerables.shorts.pred(Short.MIN_VALUE));
        assertEquals(None(), Enumerables.shorts.pred(null));
    }
    
    @Test
    public void testShorts_range() {
        Iterable<Short> range = range(Enumerables.shorts, (short)1, (short)5);
        assertEquals(Short.valueOf((short)1), head(range));
        assertEquals(Short.valueOf((short)5), last(range));
    }
    
    @Test
    public void testInts_bounds() {
        assertEquals(Integer.valueOf(Integer.MIN_VALUE), Enumerables.ints.minBound());
        assertEquals(Integer.valueOf(Integer.MAX_VALUE), Enumerables.ints.maxBound());
    }
    
    @Test
    public void testInts_succ() {
        assertEquals(Some(1), Enumerables.ints.succ(0));
        assertEquals(Some(0), Enumerables.ints.succ(-1));
        assertEquals(Some(Integer.MAX_VALUE), Enumerables.ints.succ(Integer.MAX_VALUE - 1));
        assertEquals(None(), Enumerables.ints.succ(Integer.MAX_VALUE));
        assertEquals(None(), Enumerables.ints.succ(null));
    }
    
    @Test
    public void testInts_pred() {
        assertEquals(Some(-1), Enumerables.ints.pred(0));
        assertEquals(Some(0), Enumerables.ints.pred(1));
        assertEquals(Some(Integer.MIN_VALUE), Enumerables.ints.pred(Integer.MIN_VALUE + 1));
        assertEquals(None(), Enumerables.ints.pred(Integer.MIN_VALUE));
        assertEquals(None(), Enumerables.ints.pred(null));
    }
    
    @Test
    public void testInts_range() {
        Iterable<Integer> range = range(Enumerables.ints, 1, 5);
        assertEquals(Integer.valueOf(1), head(range));
        assertEquals(Integer.valueOf(5), last(range));
    }
    
    @Test
    public void testLongs_bounds() {
        assertEquals(Long.valueOf(Long.MIN_VALUE), Enumerables.longs.minBound());
        assertEquals(Long.valueOf(Long.MAX_VALUE), Enumerables.longs.maxBound());
    }
    
    @Test
    public void testLongs_succ() {
        assertEquals(Some(1L), Enumerables.longs.succ(0L));
        assertEquals(Some(0L), Enumerables.longs.succ(-1L));
        assertEquals(Some(Long.MAX_VALUE), Enumerables.longs.succ(Long.MAX_VALUE - 1));
        assertEquals(None(), Enumerables.longs.succ(Long.MAX_VALUE));
        assertEquals(None(), Enumerables.longs.succ(null));
    }
    
    @Test
    public void testLongs_pred() {
        assertEquals(Some(-1L), Enumerables.longs.pred(0L));
        assertEquals(Some(0L), Enumerables.longs.pred(1L));
        assertEquals(Some(Long.MIN_VALUE), Enumerables.longs.pred(Long.MIN_VALUE + 1));
        assertEquals(None(), Enumerables.longs.pred(Long.MIN_VALUE));
        assertEquals(None(), Enumerables.longs.pred(null));
    }
    
    @Test
    public void testLongs_range() {
        Iterable<Long> range = range(Enumerables.longs, 1L, 5L);
        assertEquals(Long.valueOf(1L), head(range));
        assertEquals(Long.valueOf(5L), last(range));
    }
    
    @Test
    public void testBooleans_bounds() {
        assertEquals(Boolean.FALSE, Enumerables.booleans.minBound());
        assertEquals(Boolean.TRUE, Enumerables.booleans.maxBound());
    }
    
    @Test
    public void testBooleans_succ() {
        assertEquals(Some(true), Enumerables.booleans.succ(false));
        assertEquals(None(), Enumerables.booleans.succ(true));
        assertEquals(None(), Enumerables.booleans.succ(null));
    }
    
    @Test
    public void testBooleans_pred() {
        assertEquals(None(), Enumerables.booleans.pred(false));
        assertEquals(Some(false), Enumerables.booleans.pred(true));
        assertEquals(None(), Enumerables.booleans.pred(null));
    }
    
    @Test
    public void testBooleans_range() {
        Iterable<Boolean> range = range(Enumerables.booleans);
        assertEquals(Boolean.FALSE, head(range));
        assertEquals(Boolean.TRUE, last(range));
    }
}
