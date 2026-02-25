package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.FunctionalC.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class FunctionalCTest {

    @Test
    public void testSubtract_charSequence() {
        CharSequence xs = "hello";
        CharSequence toSubtract = "lo";
        
        CharSequence result = subtract(xs, toSubtract);
        
        assertThat(result.toString(), equalTo("he"));
    }
    
    @Test
    public void testSubtract_string() {
        String xs = "hello";
        String toSubtract = "lo";
        
        String result = subtract(xs, toSubtract);
        
        assertThat(result, equalTo("he"));
    }
    
    @Test
    public void testSubtract_null() {
        assertNull(subtract((String)null, "lo"));
        assertNull(subtract("hello", (String)null));
    }
    
    @Test
    public void testRemove_charSequence() {
        CharSequence xs = "hello";
        
        CharSequence result = remove('l', xs);
        
        assertThat(result.toString(), equalTo("heo"));
    }
    
    @Test
    public void testRemove_string() {
        String xs = "hello";
        
        String result = remove('l', xs);
        
        assertThat(result, equalTo("heo"));
    }
    
    @Test
    public void testRemove_null() {
        assertNull(remove('x', (String)null));
    }
    
    @Test
    public void testFind_found() {
        CharSequence xs = "hello";
        
        Option<Character> result = find(new Apply<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return c == 'l';
            }
        }, xs);
        
        assertTrue(result.isDefined());
        assertThat(result.get(), equalTo('l'));
    }
    
    @Test
    public void testFind_notFound() {
        CharSequence xs = "hello";
        
        Option<Character> result = find(new Apply<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return c == 'x';
            }
        }, xs);
        
        assertFalse(result.isDefined());
    }
    
    @Test
    public void testFilter_charSequence() {
        CharSequence xs = "hello";
        
        CharSequence result = filter(new Apply<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return c != 'l';
            }
        }, xs);
        
        assertThat(result.toString(), equalTo("heo"));
    }
    
    @Test
    public void testFilter_string() {
        String xs = "hello";
        
        String result = filter(new Apply<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return c != 'l';
            }
        }, xs);
        
        assertThat(result, equalTo("heo"));
    }
    
    @Test
    public void testFilter_null() {
        assertNull(filter(new Apply<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return true;
            }
        }, (String)null));
    }
    
    @Test
    public void testMap() {
        CharSequence xs = "abc";
        
        Iterable<Integer> result = map(new Apply<Character, Integer>() {
            @Override
            public Integer apply(Character c) {
                return (int)c;
            }
        }, xs);
        
        assertThat(newList(result), equalTo(newList(97, 98, 99)));
    }
    
    @Test
    public void testMap_twoFunctions() {
        CharSequence xs = "abc";
        
        Iterable<Pair<Character, Integer>> result = map(
            new Apply<Character, Character>() {
                @Override
                public Character apply(Character c) {
                    return Character.toUpperCase(c);
                }
            },
            new Apply<Character, Integer>() {
                @Override
                public Integer apply(Character c) {
                    return (int)c;
                }
            },
            xs
        );
        
        List<Pair<Character, Integer>> resultList = newList(result);
        assertThat(resultList.get(0), equalTo(Pair.of('A', 97)));
        assertThat(resultList.get(1), equalTo(Pair.of('B', 98)));
        assertThat(resultList.get(2), equalTo(Pair.of('C', 99)));
    }
    
    @Test
    public void testMap_threeFunctions() {
        CharSequence xs = "abc";
        
        Iterable<Tuple3<Character, Integer, Boolean>> result = map(
            new Apply<Character, Character>() {
                @Override
                public Character apply(Character c) {
                    return Character.toUpperCase(c);
                }
            },
            new Apply<Character, Integer>() {
                @Override
                public Integer apply(Character c) {
                    return (int)c;
                }
            },
            new Apply<Character, Boolean>() {
                @Override
                public Boolean apply(Character c) {
                    return Character.isLowerCase(c);
                }
            },
            xs
        );
        
        List<Tuple3<Character, Integer, Boolean>> resultList = newList(result);
        assertThat(resultList.get(0), equalTo(Tuple.of('A', 97, true)));
        assertThat(resultList.get(1), equalTo(Tuple.of('B', 98, true)));
        assertThat(resultList.get(2), equalTo(Tuple.of('C', 99, true)));
    }
    
    @Test
    public void testMap_fourFunctions() {
        CharSequence xs = "ab";
        
        Iterable<Tuple4<Character, Integer, Boolean, String>> result = map(
            new Apply<Character, Character>() {
                @Override
                public Character apply(Character c) {
                    return Character.toUpperCase(c);
                }
            },
            new Apply<Character, Integer>() {
                @Override
                public Integer apply(Character c) {
                    return (int)c;
                }
            },
            new Apply<Character, Boolean>() {
                @Override
                public Boolean apply(Character c) {
                    return Character.isLowerCase(c);
                }
            },
            new Apply<Character, String>() {
                @Override
                public String apply(Character c) {
                    return String.valueOf(c);
                }
            },
            xs
        );
        
        List<Tuple4<Character, Integer, Boolean, String>> resultList = newList(result);
        assertThat(resultList.get(0), equalTo(Tuple.of('A', 97, true, "a")));
        assertThat(resultList.get(1), equalTo(Tuple.of('B', 98, true, "b")));
    }
    
    @Test
    public void testFlatMap() {
        CharSequence xs = "ab";
        
        Iterable<Character> result = flatMap(new Apply<Character, Iterable<Character>>() {
            @Override
            public Iterable<Character> apply(Character c) {
                return newList(c, Character.toUpperCase(c));
            }
        }, xs);
        
        assertThat(newList(result), equalTo(newList('a', 'A', 'b', 'B')));
    }
    
    @Test
    public void testForeach() {
        final StringBuilder sb = new StringBuilder();
        
        foreach(new Apply<Character, Void>() {
            @Override
            public Void apply(Character c) {
                sb.append(c);
                return null;
            }
        }, "hello");
        
        assertThat(sb.toString(), equalTo("hello"));
    }
    
    @Test
    public void testForeach_applyVoid() {
        final StringBuilder sb = new StringBuilder();
        
        foreach(new ApplyVoid<Character>() {
            @Override
            public void accept(Character c) {
                sb.append(c);
            }
        }, "hello");
        
        assertThat(sb.toString(), equalTo("hello"));
    }
    
    @Test
    public void testGrouped_charSequence() {
        CharSequence xs = "abcdefg";
        
        Iterable<CharSequence> result = grouped(2, xs);
        
        List<CharSequence> resultList = newList(result);
        assertThat(resultList.size(), equalTo(4));
        assertThat(resultList.get(0).toString(), equalTo("ab"));
        assertThat(resultList.get(1).toString(), equalTo("cd"));
        assertThat(resultList.get(2).toString(), equalTo("ef"));
        assertThat(resultList.get(3).toString(), equalTo("g"));
    }
    
    @Test
    public void testGrouped_string() {
        String xs = "abcdefg";
        
        Iterable<String> result = grouped(3, xs);
        
        List<String> resultList = newList(result);
        assertThat(resultList.size(), equalTo(3));
        assertThat(resultList.get(0), equalTo("abc"));
        assertThat(resultList.get(1), equalTo("def"));
        assertThat(resultList.get(2), equalTo("g"));
    }
    
    @Test
    public void testGroup_charSequence() {
        CharSequence xs = "aabbcca";
        
        Iterable<CharSequence> result = group(xs);
        
        List<CharSequence> resultList = newList(result);
        assertThat(resultList.size(), equalTo(4));
        assertThat(resultList.get(0).toString(), equalTo("aa"));
        assertThat(resultList.get(1).toString(), equalTo("bb"));
        assertThat(resultList.get(2).toString(), equalTo("cc"));
        assertThat(resultList.get(3).toString(), equalTo("a"));
    }
    
    @Test
    public void testGroup_string() {
        String xs = "aabbcca";
        
        Iterable<String> result = group(xs);
        
        List<String> resultList = newList(result);
        assertThat(resultList.size(), equalTo(4));
        assertThat(resultList.get(0), equalTo("aa"));
        assertThat(resultList.get(1), equalTo("bb"));
        assertThat(resultList.get(2), equalTo("cc"));
        assertThat(resultList.get(3), equalTo("a"));
    }
    
    @Test
    public void testGroup_withComparator() {
        ApplyBi<Character, Character, Boolean> comparator = new ApplyBi<Character, Character, Boolean>() {
            @Override
            public Boolean apply(Character a, Character b) {
                return Character.toLowerCase(a) == Character.toLowerCase(b);
            }
        };
        
        Iterable<String> result = group(comparator, "aAbbBcc");
        
        List<String> resultList = newList(result);
        assertThat(resultList.size(), equalTo(3));
        assertThat(resultList.get(0), equalTo("aA"));
        assertThat(resultList.get(1), equalTo("bbB"));
        assertThat(resultList.get(2), equalTo("cc"));
    }
    
    @Test
    public void testHead() {
        assertThat(head("hello"), equalTo('h'));
    }
    
    @Test
    public void testHead_null() {
        assertNull(head((CharSequence)null));
    }
    
    @Test
    public void testHeadOption() {
        Option<Character> result = headOption("hello");
        assertTrue(result.isDefined());
        assertThat(result.get(), equalTo('h'));
    }
    
    @Test
    public void testHeadOption_empty() {
        Option<Character> result = headOption("");
        assertFalse(result.isDefined());
    }
    
    @Test
    public void testHeadOption_null() {
        assertNull(headOption((CharSequence)null));
    }
    
    @Test
    public void testTail_charSequence() {
        CharSequence result = tail((CharSequence)"hello");
        assertThat(result.toString(), equalTo("ello"));
    }
    
    @Test
    public void testTail_string() {
        String result = tail("hello");
        assertThat(result, equalTo("ello"));
    }
    
    @Test
    public void testTail_null() {
        assertNull(tail((String)null));
    }
    
    @Test
    public void testLast() {
        assertThat(last("hello"), equalTo('o'));
    }
    
    @Test
    public void testLast_null() {
        assertNull(last((CharSequence)null));
    }
    
    @Test
    public void testLastOption() {
        Option<Character> result = lastOption("hello");
        assertTrue(result.isDefined());
        assertThat(result.get(), equalTo('o'));
    }
    
    @Test
    public void testLastOption_empty() {
        Option<Character> result = lastOption("");
        assertFalse(result.isDefined());
    }
    
    @Test
    public void testLastOption_null() {
        assertNull(lastOption((CharSequence)null));
    }
    
    @Test
    public void testInit_charSequence() {
        CharSequence result = init((CharSequence)"hello");
        assertThat(result.toString(), equalTo("hell"));
    }
    
    @Test
    public void testInit_string() {
        String result = init("hello");
        assertThat(result, equalTo("hell"));
    }
    
    @Test
    public void testInit_null() {
        assertNull(init((String)null));
    }
    
    @Test
    public void testTake_charSequence() {
        CharSequence result = take(3, (CharSequence)"hello");
        assertThat(result.toString(), equalTo("hel"));
    }
    
    @Test
    public void testTake_string() {
        String result = take(3, "hello");
        assertThat(result, equalTo("hel"));
    }
    
    @Test
    public void testTake_moreThanLength() {
        String result = take(10, "hello");
        assertThat(result, equalTo("hello"));
    }
    
    @Test
    public void testTake_null() {
        assertNull(take(3, (String)null));
    }
    
    @Test
    public void testDrop_charSequence() {
        CharSequence result = drop(2, (CharSequence)"hello");
        assertThat(result.toString(), equalTo("llo"));
    }
    
    @Test
    public void testDrop_string() {
        String result = drop(2, "hello");
        assertThat(result, equalTo("llo"));
    }
    
    @Test
    public void testDrop_moreThanLength() {
        String result = drop(10, "hello");
        assertThat(result, equalTo(""));
    }
    
    @Test
    public void testDrop_null() {
        assertNull(drop(2, (String)null));
    }
    
    @Test
    public void testTakeLast_charSequence() {
        CharSequence result = takeLast(3, (CharSequence)"hello");
        assertThat(result.toString(), equalTo("llo"));
    }
    
    @Test
    public void testTakeLast_string() {
        String result = takeLast(3, "hello");
        assertThat(result, equalTo("llo"));
    }
    
    @Test
    public void testDropLast_charSequence() {
        CharSequence result = dropLast(2, (CharSequence)"hello");
        assertThat(result.toString(), equalTo("hel"));
    }
    
    @Test
    public void testDropLast_string() {
        String result = dropLast(2, "hello");
        assertThat(result, equalTo("hel"));
    }
    
    @Test
    public void testTakeWhile_charSequence() {
        CharSequence result = takeWhile(new Apply<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return c != 'l';
            }
        }, (CharSequence)"hello");
        
        assertThat(result.toString(), equalTo("he"));
    }
    
    @Test
    public void testTakeWhile_string() {
        String result = takeWhile(new Apply<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return c != 'l';
            }
        }, "hello");
        
        assertThat(result, equalTo("he"));
    }
    
    @Test
    public void testTakeWhile_null() {
        assertNull(takeWhile(new Apply<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return true;
            }
        }, (String)null));
    }
    
    @Test
    public void testDropWhile_charSequence() {
        CharSequence result = dropWhile(new Apply<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return c != 'l';
            }
        }, (CharSequence)"hello");
        
        assertThat(result.toString(), equalTo("llo"));
    }
    
    @Test
    public void testDropWhile_string() {
        String result = dropWhile(new Apply<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return c != 'l';
            }
        }, "hello");
        
        assertThat(result, equalTo("llo"));
    }
    
    @Test
    public void testDropWhile_null() {
        assertNull(dropWhile(new Apply<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return true;
            }
        }, (String)null));
    }
    
    @Test
    public void testSpan_charSequence() {
        Pair<CharSequence, CharSequence> result = span(new Apply<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return c != 'l';
            }
        }, (CharSequence)"hello");
        
        assertThat(result.left().toString(), equalTo("he"));
        assertThat(result.right().toString(), equalTo("llo"));
    }
    
    @Test
    public void testSpan_string() {
        Pair<String, String> result = span(new Apply<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return c != 'l';
            }
        }, "hello");
        
        assertThat(result.left(), equalTo("he"));
        assertThat(result.right(), equalTo("llo"));
    }
    
    @Test
    public void testSpan_null() {
        assertNull(span(new Apply<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return true;
            }
        }, (String)null));
    }
    
    @Test
    public void testPartition_charSequence() {
        Pair<CharSequence, CharSequence> result = partition(new Apply<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return c == 'l';
            }
        }, (CharSequence)"hello");
        
        assertThat(result.left().toString(), equalTo("ll"));
        assertThat(result.right().toString(), equalTo("heo"));
    }
    
    @Test
    public void testPartition_string() {
        Pair<String, String> result = partition(new Apply<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return c == 'l';
            }
        }, "hello");
        
        assertThat(result.left(), equalTo("ll"));
        assertThat(result.right(), equalTo("heo"));
    }
    
    @Test
    public void testSplit_charSequence_atIndex() {
        Pair<CharSequence, CharSequence> result = split(2, (CharSequence)"hello");
        
        assertThat(result.left().toString(), equalTo("he"));
        assertThat(result.right().toString(), equalTo("llo"));
    }
    
    @Test
    public void testSplit_charSequence_headAndTail() {
        Pair<Character, CharSequence> result = split((CharSequence)"hello");
        
        assertThat(result.left(), equalTo('h'));
        assertThat(result.right().toString(), equalTo("ello"));
    }
    
    @Test
    public void testSplit_string_atIndex() {
        Pair<String, String> result = split(2, "hello");
        
        assertThat(result.left(), equalTo("he"));
        assertThat(result.right(), equalTo("llo"));
    }
    
    @Test
    public void testSplit_string_headAndTail() {
        Pair<Character, String> result = split("hello");
        
        assertThat(result.left(), equalTo('h'));
        assertThat(result.right(), equalTo("ello"));
    }
    
    @Test
    public void testSplit_null() {
        assertNull(split(2, (String)null));
        assertNull(split((String)null));
    }
    
    @Test
    public void testEvery_charSequence() {
        CharSequence result = every(2, (CharSequence)"hello");
        assertThat(result.toString(), equalTo("hlo"));
    }
    
    @Test
    public void testEvery_string() {
        String result = every(2, "hello");
        assertThat(result, equalTo("hlo"));
    }
    
    @Test
    public void testEvery_null() {
        assertNull(every(2, (String)null));
    }
    
    @Test
    public void testIsEmpty_string() {
        assertTrue(isEmpty(""));
        assertFalse(isEmpty("hello"));
    }
    
    @Test
    public void testIsEmpty_charSequence() {
        assertTrue(isEmpty((CharSequence)""));
        assertFalse(isEmpty((CharSequence)"hello"));
    }
    
    @Test
    public void testSize() {
        assertThat(size("hello"), equalTo(5L));
        assertThat(size(""), equalTo(0L));
    }
    
    @Test
    public void testContains_charSequence() {
        assertTrue(contains('l', (CharSequence)"hello"));
        assertFalse(contains('x', (CharSequence)"hello"));
    }
    
    @Test
    public void testContains_string() {
        assertTrue(contains('l', "hello"));
        assertFalse(contains('x', "hello"));
    }
    
    @Test
    public void testExists() {
        assertTrue(exists(new Apply<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return c == 'l';
            }
        }, "hello"));
        
        assertFalse(exists(new Apply<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return c == 'x';
            }
        }, "hello"));
    }
    
    @Test
    public void testForall() {
        assertTrue(forall(new Apply<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return Character.isLowerCase(c);
            }
        }, "hello"));
        
        assertFalse(forall(new Apply<Character, Boolean>() {
            @Override
            public Boolean apply(Character c) {
                return c == 'h';
            }
        }, "hello"));
    }
    
    @Test
    public void testCons_charSequence() {
        CharSequence result = cons('h', (CharSequence)"ello");
        assertThat(result.toString(), equalTo("hello"));
    }
    
    @Test
    public void testCons_string() {
        String result = cons('h', "ello");
        assertThat(result, equalTo("hello"));
    }
    
    @Test
    public void testCons_null() {
        assertNull(cons('h', (String)null));
    }
    
    @Test
    public void testAppend_charSequence() {
        CharSequence result = append('o', (CharSequence)"hell");
        assertThat(result.toString(), equalTo("hello"));
    }
    
    @Test
    public void testAppend_string() {
        String result = append('o', "hell");
        assertThat(result, equalTo("hello"));
    }
    
    @Test
    public void testAppend_null() {
        assertNull(append('o', (String)null));
    }
    
    @Test
    public void testConcat_charSequence() {
        CharSequence result = concat((CharSequence)"hel", (CharSequence)"lo");
        assertThat(result.toString(), equalTo("hello"));
    }
    
    @Test
    public void testConcat_string() {
        String result = concat("hel", "lo");
        assertThat(result, equalTo("hello"));
    }
    
    @Test
    public void testConcat_nullHandling() {
        assertNull(concat((String)null, (String)null));
        assertThat(concat((String)null, "hello"), equalTo("hello"));
        assertThat(concat("hello", (String)null), equalTo("hello"));
    }
    
    @Test
    public void testSort_charSequence() {
        CharSequence result = sort((CharSequence)"dcba");
        assertThat(result.toString(), equalTo("abcd"));
    }
    
    @Test
    public void testSort_string() {
        String result = sort("dcba");
        assertThat(result, equalTo("abcd"));
    }
    
    @Test
    public void testSort_withComparator() {
        Comparator<Character> reverseComparator = new Comparator<Character>() {
            @Override
            public int compare(Character a, Character b) {
                return b.compareTo(a);
            }
        };
        
        String result = sort(reverseComparator, "abcd");
        assertThat(result, equalTo("dcba"));
    }
    
    @Test
    public void testSort_withComparator_charSequence() {
        Comparator<Character> reverseComparator = new Comparator<Character>() {
            @Override
            public int compare(Character a, Character b) {
                return b.compareTo(a);
            }
        };
        
        CharSequence result = sort(reverseComparator, (CharSequence)"abcd");
        assertThat(result.toString(), equalTo("dcba"));
    }
    
    @Test
    public void testSort_null() {
        assertNull(sort((String)null));
    }
    
    @Test
    public void testFold_withZero() {
        Integer result = fold(0, new Apply<Map.Entry<? extends Integer, ? extends Character>, Integer>() {
            @Override
            public Integer apply(Map.Entry<? extends Integer, ? extends Character> entry) {
                return entry.getKey() + 1;
            }
        }, "hello");
        
        assertThat(result, equalTo(5));
    }
    
    @Test
    public void testFold_withoutZero() {
        Option<Character> result = fold(new Apply<Map.Entry<? extends Character, ? extends Character>, Character>() {
            @Override
            public Character apply(Map.Entry<? extends Character, ? extends Character> entry) {
                return entry.getValue();
            }
        }, "hello");
        
        assertTrue(result.isDefined());
    }
    
    @Test
    public void testMin() {
        Option<Character> result = min("dcba");
        assertTrue(result.isDefined());
        assertThat(result.get(), equalTo('a'));
    }
    
    @Test
    public void testMax() {
        Option<Character> result = max("dcba");
        assertTrue(result.isDefined());
        assertThat(result.get(), equalTo('d'));
    }
    
    @Test
    public void testZip_two() {
        Iterable<Pair<Character, Character>> result = zip("abc", "def");
        
        List<Pair<Character, Character>> resultList = newList(result);
        assertThat(resultList.size(), equalTo(3));
        assertThat(resultList.get(0), equalTo(Pair.of('a', 'd')));
        assertThat(resultList.get(1), equalTo(Pair.of('b', 'e')));
        assertThat(resultList.get(2), equalTo(Pair.of('c', 'f')));
    }
    
    @Test
    public void testZip_three() {
        Iterable<Tuple3<Character, Character, Character>> result = zip("abc", "def", "ghi");
        
        List<Tuple3<Character, Character, Character>> resultList = newList(result);
        assertThat(resultList.size(), equalTo(3));
        assertThat(resultList.get(0), equalTo(Tuple.of('a', 'd', 'g')));
        assertThat(resultList.get(1), equalTo(Tuple.of('b', 'e', 'h')));
        assertThat(resultList.get(2), equalTo(Tuple.of('c', 'f', 'i')));
    }
    
    @Test
    public void testZip_four() {
        Iterable<Tuple4<Character, Character, Character, Character>> result = zip("abc", "def", "ghi", "jkl");
        
        List<Tuple4<Character, Character, Character, Character>> resultList = newList(result);
        assertThat(resultList.size(), equalTo(3));
        assertThat(resultList.get(0), equalTo(Tuple.of('a', 'd', 'g', 'j')));
        assertThat(resultList.get(1), equalTo(Tuple.of('b', 'e', 'h', 'k')));
        assertThat(resultList.get(2), equalTo(Tuple.of('c', 'f', 'i', 'l')));
    }
    
    @Test
    public void testZipWithIndex() {
        Iterable<Pair<Integer, Character>> result = zipWithIndex("abc");
        
        List<Pair<Integer, Character>> resultList = newList(result);
        assertThat(resultList.size(), equalTo(3));
        assertThat(resultList.get(0), equalTo(Pair.of(0, 'a')));
        assertThat(resultList.get(1), equalTo(Pair.of(1, 'b')));
        assertThat(resultList.get(2), equalTo(Pair.of(2, 'c')));
    }
    
    @Test
    public void testUnzipC() {
        List<Pair<Character, Character>> pairs = newList(Pair.of('a', 'd'), Pair.of('b', 'e'), Pair.of('c', 'f'));
        
        Pair<CharSequence, CharSequence> result = unzipC(pairs);
        
        assertThat(result.left().toString(), equalTo("abc"));
        assertThat(result.right().toString(), equalTo("def"));
    }
    
    @Test
    public void testUnzipC_null() {
        assertNull(unzipC(null));
    }
    
    @Test
    public void testUnzip3C() {
        List<Tuple3<Character, Character, Character>> tuples = newList(
            Tuple.of('a', 'd', 'g'),
            Tuple.of('b', 'e', 'h'),
            Tuple.of('c', 'f', 'i')
        );
        
        Tuple3<CharSequence, CharSequence, CharSequence> result = unzip3C(tuples);
        
        assertThat(result._1.toString(), equalTo("abc"));
        assertThat(result._2.toString(), equalTo("def"));
        assertThat(result._3.toString(), equalTo("ghi"));
    }
    
    @Test
    public void testUnzip3C_null() {
        assertNull(unzip3C(null));
    }
    
    @Test
    public void testRepeat_infinite() {
        CharSequence result = repeat('a');
        
        // Take 5 characters from the infinite sequence and verify
        CharSequence taken = take(5, result);
        
        // Convert CharSequence to iterable and then to list
        List<Character> chars = newList(Collections.it(taken));
        assertThat(chars, equalTo(newList('a', 'a', 'a', 'a', 'a')));
    }
    
    @Test
    public void testRepeat_withAmount() {
        CharSequence result = repeat('a', 3);
        assertThat(result.toString(), equalTo("aaa"));
    }
    
    @Test
    public void testRepeat_zeroAmount() {
        CharSequence result = repeat('a', 0);
        assertThat(result.toString(), equalTo(""));
    }
    
    @Test
    public void testRepeat_negativeAmount() {
        CharSequence result = repeat('a', -1);
        assertThat(result.toString(), equalTo(""));
    }
    
    @Test
    public void testPadLeft() {
        CharSequence result = padLeft(5, '0', (CharSequence)"42");
        assertThat(result.toString(), equalTo("00042"));
    }
    
    @Test
    public void testPadLeft_alreadyLongEnough() {
        CharSequence result = padLeft(3, '0', (CharSequence)"hello");
        assertThat(result.toString(), equalTo("hello"));
    }
    
    @Test
    public void testPadLeft_null() {
        assertNull(padLeft(5, '0', (CharSequence)null));
    }
    
    @Test
    public void testPadRight() {
        CharSequence result = padRight(5, '0', (CharSequence)"42");
        assertThat(result.toString(), equalTo("42000"));
    }
    
    @Test
    public void testPadRight_alreadyLongEnough() {
        CharSequence result = padRight(3, '0', (CharSequence)"hello");
        assertThat(result.toString(), equalTo("hello"));
    }
    
    @Test
    public void testPadRight_null() {
        assertNull(padRight(5, '0', (CharSequence)null));
    }
    
    @Test
    public void testMkString_charSequence() {
        String result = mkString((CharSequence)"hello");
        assertThat(result, equalTo("hello"));
    }
    
    @Test
    public void testMkString_null() {
        assertNull(mkString((CharSequence)null));
    }
    
    @Test
    public void testMkString_withDelimiter() {
        String result = mkString(",", "abc");
        assertThat(result, equalTo("a,b,c"));
    }
    
    @Test
    public void testReverse_charSequence() {
        CharSequence result = reverse((CharSequence)"hello");
        assertThat(result.toString(), equalTo("olleh"));
    }
    
    @Test
    public void testReverse_string() {
        String result = reverse("hello");
        assertThat(result, equalTo("olleh"));
    }
    
    @Test
    public void testReverse_null() {
        assertNull(reverse((String)null));
    }
    
    @Test
    public void testDistinct() {
        CharSequence result = distinct((CharSequence)"hello");
        assertThat(result.toString(), equalTo("helo"));
    }
    
    @Test
    public void testRangify() {
        CharSequence xs = "abcdefhij";
        
        Iterable<List<Character>> result = rangify(Enumerables.asciiAlpha, xs);
        
        List<List<Character>> resultList = newList(result);
        assertThat(resultList.size(), equalTo(2));
        // rangify returns [first, last] for each consecutive range
        assertThat(resultList.get(0), equalTo(newList('a', 'f')));
        assertThat(resultList.get(1), equalTo(newList('h', 'j')));
    }
    
    @Test
    public void testUnlines() {
        List<String> lines = newList("hello", "world");
        CharSequence result = unlines(lines);
        assertThat(result.toString(), equalTo("hello\nworld"));
    }
}
