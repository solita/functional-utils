package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newMutableList;
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
        assertEquals(Integer.valueOf(42), Match.instance(Integer.class, 42).success.get());
        
        // Test with null
        assertFalse(Match.instance(String.class, (Object)null).isSuccess());
        
        // Test with subclass
        String str = "test";
        assertTrue(Match.instance(String.class, (Object)str).isSuccess());
        assertEquals("test", Match.instance(String.class, (Object)str).success.get());
    }
    
    @Test
    public void singleton() {
        assertFalse(Match.singleton(Collections.<Integer>emptyList()).isSuccess());
        assertFalse(Match.singleton(newList(1,2)).isSuccess());
        
        assertTrue(Match.singleton(newList(1)).isSuccess());
        assertEquals(Integer.valueOf(1), Match.singleton(newList(1)).success.get());
    }
    
    @Test
    public void pair() {
        assertFalse(Match.pair(Collections.<Integer>emptyList()).isSuccess());
        assertFalse(Match.pair(newList(1)).isSuccess());
        assertFalse(Match.pair(newList(1,2,3)).isSuccess());
        
        assertTrue(Match.pair(newList(1,2)).isSuccess());
        assertEquals(Pair.of(1,2), Match.pair(newList(1,2)).success.get());
    }
    
    @Test
    public void iterable() {
         List<Integer> someList = newList(1,2);
         
         List<Character> matching = newMutableList();
         
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
        
        assertTrue(Match.startsWith("foo", "bar").isFailure());
        assertTrue(Match.startsWith("foo", "").isFailure());
        assertTrue(Match.startsWith("foobar", "foo").isFailure());
    }
    
    @Test
    public void endsWith() {
        assertEquals(Try.success(Pair.of("foo", "bar")), Match.endsWith("bar", "foobar"));
        assertEquals(Try.success(Pair.of("", "")),       Match.endsWith("", ""));
        assertEquals(Try.success(Pair.of("bar", "")),    Match.endsWith("", "bar"));
        assertEquals(Try.success(Pair.of("", "foo")),    Match.endsWith("foo", "foo"));
        
        assertTrue(Match.endsWith("foo", "bar").isFailure());
        assertTrue(Match.endsWith("foo", "").isFailure());
        assertTrue(Match.endsWith("foobar", "foo").isFailure());
    }
    
    @Test
    public void stringPair() {
        assertEquals(Try.success(Pair.of("a","b")), Match.pair('_', "a_b"));
        assertEquals(Try.success(Pair.of("","b")),  Match.pair('_', "_b"));
        assertEquals(Try.success(Pair.of("a","")),  Match.pair('_', "a_"));
        assertEquals(Try.success(Pair.of("","")), Match.pair('_', "_"));
        
        assertTrue(Match.pair('_', "foo").isFailure());
        assertTrue(Match.pair('_', "").isFailure());
    }
    
    @Test
    public void groups() {
        assertEquals(Try.success(newList("1","2")), Match.groups(Pattern.compile("(.*)_(.*)"), "1_2"));
        assertEquals(Try.success(newList("foo")), Match.groups(Pattern.compile("(.*)"), "foo"));
        
        assertEquals(Try.success(emptyList()), Match.groups(Pattern.compile(".*"), "foo"));
        assertTrue(Match.groups(Pattern.compile("f(oo)"), "bar").isFailure());
    }
    
    @Test
    public void section_failure() {
        assertTrue(Match.section('{', '}', "").isFailure());
        assertTrue(Match.section('{', '}', "a").isFailure());
        assertTrue(Match.section('{', '}', "{").isFailure());
        assertTrue(Match.section('{', '}', "}").isFailure());
        assertTrue(Match.section('{', '}', "a{").isFailure());
        assertTrue(Match.section('{', '}', "{a").isFailure());
        assertTrue(Match.section('{', '}', "b}").isFailure());
        assertTrue(Match.section('{', '}', "}b").isFailure());
    }
    
    @Test
    public void section_success() {
        assertEquals(Try.success(Tuple.of("", "", "")), Match.section('{', '}', "{}"));
        assertEquals(Try.success(Tuple.of("", " ", "")), Match.section('{', '}', "{ }"));
        assertEquals(Try.success(Tuple.of(" ", "", " ")), Match.section('{', '}', " {} "));
        
        assertEquals(Try.success(Tuple.of(" ", "{", " ")), Match.section('{', '}', " {{} "));
        assertEquals(Try.success(Tuple.of(" ", "", "} ")), Match.section('{', '}', " {}} "));
        assertEquals(Try.success(Tuple.of(" ", "a", " {b}")), Match.section('{', '}', " {a} {b}"));
    }
    
    @Test
    public void nonEmptyString() {
        assertTrue(Match.nonEmptyString("test").isSuccess());
        assertEquals("test", Match.nonEmptyString("test").success.get());
        assertTrue(Match.nonEmptyString(" test ").isSuccess());
        
        assertTrue(Match.nonEmptyString(null).isFailure());
        assertTrue(Match.nonEmptyString("").isFailure());
        assertTrue(Match.nonEmptyString("   ").isFailure());
        assertTrue(Match.nonEmptyString("\t").isFailure());
    }
    
    @Test
    public void hasCauseInHierarchy() {
        IllegalArgumentException rootCause = new IllegalArgumentException("root");
        RuntimeException middleCause = new RuntimeException("middle", rootCause);
        Exception topException = new Exception("top", middleCause);
        
        assertTrue(Match.hasCauseInHierarchy(IllegalArgumentException.class, topException).isSuccess());
        assertEquals(rootCause, Match.hasCauseInHierarchy(IllegalArgumentException.class, topException).success.get());
        
        assertTrue(Match.hasCauseInHierarchy(RuntimeException.class, topException).isSuccess());
        assertEquals(middleCause, Match.hasCauseInHierarchy(RuntimeException.class, topException).success.get());
        
        assertTrue(Match.hasCauseInHierarchy(Exception.class, topException).isSuccess());
        
        assertTrue(Match.hasCauseInHierarchy(NullPointerException.class, topException).isFailure());
    }
    
    @Test
    public void tuple2_withSeparator() {
        assertEquals(Try.success(Tuple.of("a","b")), Match.tuple2('_', "a_b"));
        assertEquals(Try.success(Tuple.of("","b")),  Match.tuple2('_', "_b"));
        assertEquals(Try.success(Tuple.of("a","")),  Match.tuple2('_', "a_"));
        assertEquals(Try.success(Tuple.of("","")), Match.tuple2('_', "_"));
        
        assertTrue(Match.tuple2('_', "foo").isFailure());
        assertTrue(Match.tuple2('_', "").isFailure());
        assertTrue(Match.tuple2('_', "a_b_c").isFailure());
    }
    
    @Test
    public void tuple3_withSeparator() {
        assertEquals(Try.success(Tuple.of("a","b","c")), Match.tuple3('_', "a_b_c"));
        assertEquals(Try.success(Tuple.of("","","")),  Match.tuple3('_', "__"));
        assertEquals(Try.success(Tuple.of("a","","")),  Match.tuple3('_', "a__"));
        assertEquals(Try.success(Tuple.of("","b","")),  Match.tuple3('_', "_b_"));
        
        assertTrue(Match.tuple3('_', "foo").isFailure());
        assertTrue(Match.tuple3('_', "a_b").isFailure());
        assertTrue(Match.tuple3('_', "a_b_c_d").isFailure());
    }
    
    @Test
    public void tuple4_withSeparator() {
        assertEquals(Try.success(Tuple.of("a","b","c","d")), Match.tuple4('_', "a_b_c_d"));
        assertEquals(Try.success(Tuple.of("","","","")),  Match.tuple4('_', "___"));
        
        assertTrue(Match.tuple4('_', "a_b_c").isFailure());
        assertTrue(Match.tuple4('_', "a_b_c_d_e").isFailure());
    }
    
    @Test
    public void tuple5_withSeparator() {
        assertEquals(Try.success(Tuple.of("a","b","c","d","e")), Match.tuple5('_', "a_b_c_d_e"));
        assertEquals(Try.success(Tuple.of("","","","","")),  Match.tuple5('_', "____"));
        
        assertTrue(Match.tuple5('_', "a_b_c_d").isFailure());
        assertTrue(Match.tuple5('_', "a_b_c_d_e_f").isFailure());
    }
    
    @Test
    public void tuple2_fromIterable() {
        assertEquals(Try.success(Tuple.of(1,2)), Match.tuple2(newList(1,2)));
        
        assertTrue(Match.tuple2(newList(1)).isFailure());
        assertTrue(Match.tuple2(newList(1,2,3)).isFailure());
        assertTrue(Match.tuple2(Collections.<Integer>emptyList()).isFailure());
    }
    
    @Test
    public void tuple3_fromIterable() {
        assertEquals(Try.success(Tuple.of(1,2,3)), Match.tuple3(newList(1,2,3)));
        
        assertTrue(Match.tuple3(newList(1,2)).isFailure());
        assertTrue(Match.tuple3(newList(1,2,3,4)).isFailure());
        assertTrue(Match.tuple3(Collections.<Integer>emptyList()).isFailure());
    }
    
    @Test
    public void tuple4_fromIterable() {
        assertEquals(Try.success(Tuple.of(1,2,3,4)), Match.tuple4(newList(1,2,3,4)));
        
        assertTrue(Match.tuple4(newList(1,2,3)).isFailure());
        assertTrue(Match.tuple4(newList(1,2,3,4,5)).isFailure());
        assertTrue(Match.tuple4(Collections.<Integer>emptyList()).isFailure());
    }
    
    @Test
    public void tuple5_fromIterable() {
        assertEquals(Try.success(Tuple.of(1,2,3,4,5)), Match.tuple5(newList(1,2,3,4,5)));
        
        assertTrue(Match.tuple5(newList(1,2,3,4)).isFailure());
        assertTrue(Match.tuple5(newList(1,2,3,4,5,6)).isFailure());
        assertTrue(Match.tuple5(Collections.<Integer>emptyList()).isFailure());
    }
    
    @Test
    public void iterable_singleElement() {
        List<Integer> someList = newList(1);
        
        assertTrue(Match.iterable(1, someList).isSuccess());
        assertTrue(Match.iterable((Object)1, someList).isSuccess());
        assertTrue(Match.iterable(null, someList).isSuccess());
        
        assertTrue(Match.iterable(2, someList).isFailure());
        assertTrue(Match.iterable(1, newList(1,2)).isFailure());
    }
    
    @Test
    public void iterable_threeElements() {
        List<Integer> someList = newList(1,2,3);
        
        assertTrue(Match.iterable(1, 2, 3, someList).isSuccess());
        assertTrue(Match.iterable(null, null, null, someList).isSuccess());
        assertTrue(Match.iterable(1, null, null, someList).isSuccess());
        assertTrue(Match.iterable(null, 2, null, someList).isSuccess());
        
        assertFalse(Match.iterable(2, 2, 3, someList).isSuccess());
        assertFalse(Match.iterable(1, 2, 4, someList).isSuccess());
        assertFalse(Match.iterable(1, 2, 3, newList(1,2)).isSuccess());
        assertFalse(Match.iterable(1, 2, 3, newList(1,2,3,4)).isSuccess());
    }
    
    @Test
    public void groups_multipleGroups() {
        // Test with multiple groups
        assertEquals(Try.success(newList("foo", "bar", "baz")), 
                     Match.groups(Pattern.compile("([a-z]+)_([a-z]+)_([a-z]+)"), "foo_bar_baz"));
        
        // Test with nested groups - (\w+)(\w+) captures all but last char in first group, last char in second
        assertEquals(Try.success(newList("foobar", "fooba", "r")), 
                     Match.groups(Pattern.compile("((\\w+)(\\w+))"), "foobar"));
    }
    
    @Test
    public void section_nestedDelimiters() {
        // Test with multiple opening delimiters before first closing
        assertEquals(Try.success(Tuple.of("", "{{", " ")), Match.section('{', '}', "{{{} "));
        
        // Test matching works with different delimiters
        assertEquals(Try.success(Tuple.of("before", "inside", "after")), 
                     Match.section('[', ']', "before[inside]after"));
    }
    
    @Test
    public void startsWith_edgeCases() {
        // Test with longer strings
        assertEquals(Try.success(Pair.of("hello", " world")), Match.startsWith("hello", "hello world"));
        
        // Test prefix equals entire string
        assertEquals(Try.success(Pair.of("test", "")), Match.startsWith("test", "test"));
    }
    
    @Test
    public void endsWith_edgeCases() {
        // Test with longer strings
        assertEquals(Try.success(Pair.of("hello ", "world")), Match.endsWith("world", "hello world"));
        
        // Test suffix equals entire string
        assertEquals(Try.success(Pair.of("", "test")), Match.endsWith("test", "test"));
    }
    
    @Test
    public void iterable_emptyList() {
        // Test that empty list doesn't match anything
        List<Integer> emptyList = Collections.emptyList();
        assertFalse(Match.iterable(1, emptyList).isSuccess());
        assertFalse(Match.iterable(1, 2, emptyList).isSuccess());
    }
    
    @Test
    public void stringPair_edgeCases() {
        // Test with multiple separators - splits on all occurrences so it fails
        assertTrue(Match.pair('_', "a_b_c").isFailure());
        
        // Test with special characters
        assertEquals(Try.success(Pair.of("hello", "world")), Match.pair(' ', "hello world"));
    }
}