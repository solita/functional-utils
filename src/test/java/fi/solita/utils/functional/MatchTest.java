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
        for (String m: Match.instance(String.class, (Object)42)) {
            fail("Should not have matched");
        }
        
        for (Integer m: Match.instance(Integer.class, 42)) {
            return;
        }
        fail("Should have matched");
    }
    
    @Test
    public void singleton() {
        for (Integer m: Match.singleton(Collections.<Integer>emptyList())) {
            fail("Should not have matched");
        }
        for (Integer m: Match.singleton(newList(1,2))) {
            fail("Should not have matched");
        }
        
        for (Integer m: Match.singleton(newList(1))) {
            return;
        }
        fail("Should have matched");
    }
    
    @Test
    public void pair() {
        for (Pair<Integer, Integer> m: Match.pair(Collections.<Integer>emptyList())) {
            fail("Should not have matched");
        }
        for (Pair<Integer, Integer> m: Match.pair(newList(1))) {
            fail("Should not have matched");
        }
        for (Pair<Integer, Integer> m: Match.pair(newList(1,2,3))) {
            fail("Should not have matched");
        }
        
        for (Pair<Integer, Integer> m: Match.pair(newList(1,2))) {
            return;
        }
        fail("Should have matched");
    }
    
    @Test
    public void iterable() {
         List<Integer> someList = newList(1,2);
         
         List<Character> matching = newList();
         
         for (Pair<Integer,Integer> m: Match.iterable(null, null, someList)) {
             matching.add('a');
         }
         for (Pair<Integer,Integer> m: Match.iterable(1, null, someList)) {
             matching.add('b');
         }
         for (Pair<Integer,Integer> m: Match.iterable(1, 2, someList)) {
             matching.add('c');
         }
         for (Pair<Object,Object> m: Match.iterable((Object)1, null, someList)) {
             matching.add('d');
         }
         
         for (Pair<Integer,Integer> m: Match.iterable(2, null, someList)) {
             matching.add('e');
         }
         for (Integer m: Match.iterable(1, someList)) {
             matching.add('f');
         }
         for (Tuple3<Integer,Integer,Integer> m: Match.iterable(1, 2, 3, someList)) {
             matching.add('g');
         }
         for (Object m: Match.iterable(1, new Object(), someList)) {
             matching.add('h');
         }
         
         assertEquals(newList('a', 'b', 'c', 'd'), matching);
    }
    
    @Test
    public void startsWith() {
        assertEquals(Some(Pair.of("foo", "bar")), Match.startsWith("foo", "foobar"));
        assertEquals(Some(Pair.of("", "")),       Match.startsWith("", ""));
        assertEquals(Some(Pair.of("", "bar")),    Match.startsWith("", "bar"));
        assertEquals(Some(Pair.of("foo", "")),    Match.startsWith("foo", "foo"));
        
        assertEquals(None(), Match.startsWith("foo", "bar"));
        assertEquals(None(), Match.startsWith("foo", ""));
        assertEquals(None(), Match.startsWith("foobar", "foo"));
    }
    
    @Test
    public void endsWith() {
        assertEquals(Some(Pair.of("foo", "bar")), Match.endsWith("bar", "foobar"));
        assertEquals(Some(Pair.of("", "")),       Match.endsWith("", ""));
        assertEquals(Some(Pair.of("bar", "")),    Match.endsWith("", "bar"));
        assertEquals(Some(Pair.of("", "foo")),    Match.endsWith("foo", "foo"));
        
        assertEquals(None(), Match.endsWith("foo", "bar"));
        assertEquals(None(), Match.endsWith("foo", ""));
        assertEquals(None(), Match.endsWith("foobar", "foo"));
    }
    
    @Test
    public void stringPair() {
        assertEquals(Some(Pair.of("a","b")), Match.pair('_', "a_b"));
        assertEquals(Some(Pair.of("","b")),  Match.pair('_', "_b"));
        assertEquals(Some(Pair.of("a","")),  Match.pair('_', "a_"));
        assertEquals(Some(Pair.of("","")), Match.pair('_', "_"));
        
        assertEquals(None(), Match.pair('_', "foo"));
        assertEquals(None(), Match.pair('_', ""));
    }
    
    @Test
    public void groups() {
        assertEquals(Some(newList("1","2")), Match.groups(Pattern.compile("(.*)_(.*)"), "1_2"));
        assertEquals(Some(newList("foo")), Match.groups(Pattern.compile("(.*)"), "foo"));
        
        assertEquals(Some(emptyList()), Match.groups(Pattern.compile(".*"), "foo"));
        assertEquals(None(), Match.groups(Pattern.compile("f(oo)"), "bar"));
    }
}
