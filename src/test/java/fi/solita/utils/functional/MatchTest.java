package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.sequence;
import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;
import static org.junit.Assert.*;

import java.util.List;
import java.util.regex.Pattern;

import org.junit.Test;

@SuppressWarnings("unused")
public class MatchTest {
    
    @Test
    public void instance() {
        assertFalse(Match.instance(String.class, (Object)42).isSuccess());
        assertTrue(Match.instance(Integer.class, 42).isSuccess());
    }
    
    @Test
    public void singleton() {
        assertFalse(Match.singleton(Collections.<Integer>emptyList()).isSuccess());
        assertFalse(Match.singleton(newList(1,2)).isSuccess());
        
        assertTrue(Match.singleton(newList(1)).isSuccess());
    }
    
    @Test
    public void pair() {
        assertFalse(Match.pair(Collections.<Integer>emptyList()).isSuccess());
        assertFalse(Match.pair(newList(1)).isSuccess());
        assertFalse(Match.pair(newList(1,2,3)).isSuccess());
        
        assertTrue(Match.pair(newList(1,2)).isSuccess());
    }
    
    @Test
    public void iterable() {
         List<Integer> someList = newList(1,2);
         
         List<Character> matching = newList();
         
         assertTrue(Match.iterable(null, null, someList).isSuccess());
         assertTrue(Match.iterable(1, null, someList).isSuccess());
         assertTrue(Match.iterable(1, 2, someList).isSuccess());
         assertTrue(Match.iterable((Object)1, null, someList).isSuccess());
         
         assertFalse(Match.iterable(2, null, someList).isSuccess());
         assertFalse(Match.iterable(1, someList).isSuccess());
         assertFalse(Match.iterable(1, 2, 3, someList).isSuccess());
         assertFalse(Match.iterable(1, new Object(), someList).isSuccess());
    }
    
    @Test
    public void startsWith() {
        assertEquals(Try.success(Pair.of("foo", "bar")), Match.startsWith("foo", "foobar"));
        assertEquals(Try.success(Pair.of("", "")),       Match.startsWith("", ""));
        assertEquals(Try.success(Pair.of("", "bar")),    Match.startsWith("", "bar"));
        assertEquals(Try.success(Pair.of("foo", "")),    Match.startsWith("foo", "foo"));
        
        assertTrue(Match.startsWith("foo", "bar").failure.isDefined());
        assertTrue(Match.startsWith("foo", "").failure.isDefined());
        assertTrue(Match.startsWith("foobar", "foo").failure.isDefined());
    }
    
    @Test
    public void endsWith() {
        assertEquals(Try.success(Pair.of("foo", "bar")), Match.endsWith("bar", "foobar"));
        assertEquals(Try.success(Pair.of("", "")),       Match.endsWith("", ""));
        assertEquals(Try.success(Pair.of("bar", "")),    Match.endsWith("", "bar"));
        assertEquals(Try.success(Pair.of("", "foo")),    Match.endsWith("foo", "foo"));
        
        assertTrue(Match.endsWith("foo", "bar").failure.isDefined());
        assertTrue(Match.endsWith("foo", "").failure.isDefined());
        assertTrue(Match.endsWith("foobar", "foo").failure.isDefined());
    }
    
    @Test
    public void stringPair() {
        assertEquals(Try.success(Pair.of("a","b")), Match.pair('_', "a_b"));
        assertEquals(Try.success(Pair.of("","b")),  Match.pair('_', "_b"));
        assertEquals(Try.success(Pair.of("a","")),  Match.pair('_', "a_"));
        assertEquals(Try.success(Pair.of("","")), Match.pair('_', "_"));
        
        assertTrue(Match.pair('_', "foo").failure.isDefined());
        assertTrue(Match.pair('_', "").failure.isDefined());
    }
    
    @Test
    public void groups() {
        assertEquals(Try.success(newList("1","2")), Match.groups(Pattern.compile("(.*)_(.*)"), "1_2"));
        assertEquals(Try.success(newList("foo")), Match.groups(Pattern.compile("(.*)"), "foo"));
        
        assertEquals(Try.success(emptyList()), Match.groups(Pattern.compile(".*"), "foo"));
        assertTrue(Match.groups(Pattern.compile("f(oo)"), "bar").failure.isDefined());
    }
}
