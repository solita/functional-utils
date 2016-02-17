package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.sequence;
import static fi.solita.utils.functional.Option.Some;
import static org.junit.Assert.*;

import java.util.List;

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
}
