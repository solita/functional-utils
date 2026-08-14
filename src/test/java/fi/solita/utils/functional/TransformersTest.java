package fi.solita.utils.functional;

import static fi.solita.utils.functional.Transformers.*;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Option.None;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.junit.Test;

public class TransformersTest {

    @Test
    public void testAppendCharSequence() {
        Apply<CharSequence, String> transformer = append(" world");
        assertEquals("hello world", transformer.apply("hello"));
        assertNull(transformer.apply(null));
    }

    @Test
    public void testAppendGeneric() {
        Apply<String, Pair<String, Integer>> transformer = append(42);
        Pair<String, Integer> result = transformer.apply("test");
        assertEquals("test", result.left());
        assertEquals(Integer.valueOf(42), result.right());
        assertNull(transformer.apply(null));
    }

    @Test
    public void testAppendPair() {
        Map.Entry<String, Integer> entry = Pair.of("key", 10);
        Apply<Map.Entry<String, Integer>, Tuple3<String, Integer, String>> transformer = appendPair("suffix");
        Tuple3<String, Integer, String> result = transformer.apply(entry);
        assertEquals("key", result._1);
        assertEquals(Integer.valueOf(10), result._2);
        assertEquals("suffix", result._3);
        assertNull(transformer.apply(null));
    }

    @Test
    public void testPrependCharSequence() {
        Apply<CharSequence, String> transformer = prepend("hello ");
        assertEquals("hello world", transformer.apply("world"));
    }

    @Test
    public void testPrependGeneric() {
        Apply<Integer, Pair<Boolean, Integer>> transformer = prepend(Boolean.TRUE);
        Pair<Boolean, Integer> result = transformer.apply(42);
        assertEquals(Boolean.TRUE, result.left());
        assertEquals(Integer.valueOf(42), result.right());
        assertNull(transformer.apply(null));
    }

    @Test
    public void testPrependPair() {
        Map.Entry<String, Integer> entry = Pair.of("key", 10);
        Apply<Map.Entry<String, Integer>, Tuple3<String, String, Integer>> transformer = prependPair("prefix");
        Tuple3<String, String, Integer> result = transformer.apply(entry);
        assertEquals("prefix", result._1);
        assertEquals("key", result._2);
        assertEquals(Integer.valueOf(10), result._3);
        assertNull(transformer.apply(null));
    }

    @Test
    public void testReplaceAll() {
        Pattern pattern = Pattern.compile("\\d+");
        Apply<String, String> transformer = replaceAll(pattern, "X");
        assertEquals("foo X bar X", transformer.apply("foo 123 bar 456"));
        assertNull(transformer.apply(null));
    }

    @Test
    public void testRemoveWhitespace() {
        assertEquals("helloworld", removeWhitespace.apply("hello world"));
        assertEquals("test123", removeWhitespace.apply("  test  123  "));
        assertEquals("", removeWhitespace.apply("   "));
        assertNull(removeWhitespace.apply(null));
    }

    @Test
    public void testJoin() {
        Map.Entry<String, Integer> entry = Pair.of("key", 42);
        Apply<Map.Entry<?, ?>, String> transformer = join("=");
        assertEquals("key=42", transformer.apply(entry));
    }

    @Test
    public void testMkString() {
        Tuple3<String, Integer, Boolean> tuple = Tuple.of("a", 1, true);
        Apply<Tuple, String> transformer = mkString(",", new Apply<Object, CharSequence>() {
            @Override
            public CharSequence apply(Object t) {
                return t.toString();
            }
        });
        assertEquals("a,1,true", transformer.apply(tuple));
    }

    @Test
    public void testPrefix() {
        Apply<String, String> transformer = prefix(3);
        assertEquals("hel", transformer.apply("hello"));
        assertEquals("hi", transformer.apply("hi"));
        assertEquals("", transformer.apply(""));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPrefixInvalidArgument() {
        prefix(0);
    }

    @Test
    public void testFlip() {
        Map.Entry<String, Integer> entry = Pair.of("key", 42);
        Apply<Map.Entry<String, Integer>, Map.Entry<Integer, String>> transformer = flip();
        Map.Entry<Integer, String> result = transformer.apply(entry);
        assertEquals(Integer.valueOf(42), result.getKey());
        assertEquals("key", result.getValue());
    }

    @Test
    public void testMod() {
        Apply<Integer, Integer> transformer = mod(10);
        assertEquals(Integer.valueOf(3), transformer.apply(13));
        assertEquals(Integer.valueOf(0), transformer.apply(20));
    }

    @Test
    public void testNegate() {
        assertEquals(Long.valueOf(-5), negate.apply(5L));
        assertEquals(Long.valueOf(5), negate.apply(-5L));
    }

    @Test
    public void testNegateInt() {
        assertEquals(Integer.valueOf(-5), negateInt.apply(5));
        assertEquals(Integer.valueOf(5), negateInt.apply(-5));
    }

    @Test
    public void testNegateShort() {
        assertEquals(Short.valueOf((short) -5), negateShort.apply((short) 5));
        assertEquals(Short.valueOf((short) 5), negateShort.apply((short) -5));
    }

    @Test
    public void testShort2Long() {
        assertEquals(Long.valueOf(42), short2long.apply((short) 42));
    }

    @Test
    public void testInt2Long() {
        assertEquals(Long.valueOf(42), int2long.apply(42));
    }

    @Test
    public void testToString() {
        assertEquals("42", Transformers.toString.apply(42));
        assertEquals("test", Transformers.toString.apply("test"));
        assertNull(Transformers.toString.apply(null));
    }

    @Test
    public void testLeftAndKey() {
        Map.Entry<String, Integer> entry = Pair.of("key", 42);
        Apply<Map.Entry<? extends String, ?>, String> leftTransformer = left();
        assertEquals("key", leftTransformer.apply(entry));
        
        Apply<Map.Entry<String, Integer>, String> keyTransformer = key();
        assertEquals("key", keyTransformer.apply(entry));
    }

    @Test
    public void testRightAndValue() {
        Map.Entry<String, Integer> entry = Pair.of("key", 42);
        Apply<Map.Entry<?, ? extends Integer>, Integer> rightTransformer = right();
        assertEquals(Integer.valueOf(42), rightTransformer.apply(entry));
        
        Apply<Map.Entry<String, Integer>, Integer> valueTransformer = value();
        assertEquals(Integer.valueOf(42), valueTransformer.apply(entry));
    }

    @Test
    public void testFirst() {
        Map.Entry<String, Integer> entry = Pair.of("key", 42);
        Apply<Map.Entry<String, Integer>, Map.Entry<Integer, Integer>> transformer = 
            first(new Apply<String, Integer>() {
                @Override
                public Integer apply(String t) {
                    return t.length();
                }
            });
        Map.Entry<Integer, Integer> result = transformer.apply(entry);
        assertEquals(Integer.valueOf(3), result.getKey());
        assertEquals(Integer.valueOf(42), result.getValue());
    }

    @Test
    public void testSecond() {
        Map.Entry<String, Integer> entry = Pair.of("key", 42);
        Apply<Map.Entry<String, Integer>, Map.Entry<String, String>> transformer = 
            second(new Apply<Integer, String>() {
                @Override
                public String apply(Integer t) {
                    return t.toString();
                }
            });
        Map.Entry<String, String> result = transformer.apply(entry);
        assertEquals("key", result.getKey());
        assertEquals("42", result.getValue());
    }

    @Test
    public void testEitherLeft() {
        Either<String, Integer> left = Either.left("value");
        Apply<Either<String, ?>, Option<String>> transformer = eitherLeft();
        assertEquals(Some("value"), transformer.apply(left));
        
        Either<String, Integer> right = Either.right(42);
        assertEquals(None(), transformer.apply(right));
    }

    @Test
    public void testEitherRight() {
        Either<String, Integer> right = Either.right(42);
        Apply<Either<?, Integer>, Option<Integer>> transformer = eitherRight();
        assertEquals(Some(42), transformer.apply(right));
        
        Either<String, Integer> left = Either.left("value");
        assertEquals(None(), transformer.apply(left));
    }

    @Test
    public void testEither3Left() {
        Either3<String, Integer, Boolean> left = Either3.left("value");
        Apply<Either3<String, ?, ?>, Option<String>> transformer = either3Left();
        assertEquals(Some("value"), transformer.apply(left));
        
        Either3<String, Integer, Boolean> middle = Either3.middle(42);
        assertEquals(None(), transformer.apply(middle));
    }

    @Test
    public void testEither3Middle() {
        Either3<String, Integer, Boolean> middle = Either3.middle(42);
        Apply<Either3<?, Integer, ?>, Option<Integer>> transformer = either3Middle();
        assertEquals(Some(42), transformer.apply(middle));
        
        Either3<String, Integer, Boolean> left = Either3.left("value");
        assertEquals(None(), transformer.apply(left));
    }

    @Test
    public void testEither3Right() {
        Either3<String, Integer, Boolean> right = Either3.right(true);
        Apply<Either3<?, ?, Boolean>, Option<Boolean>> transformer = either3Right();
        assertEquals(Some(true), transformer.apply(right));
        
        Either3<String, Integer, Boolean> middle = Either3.middle(42);
        assertEquals(None(), transformer.apply(middle));
    }

    @Test
    public void testMapEntrySet() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        
        Apply<Map<String, Integer>, Set<Map.Entry<String, Integer>>> transformer = mapEntrySet();
        Set<Map.Entry<String, Integer>> result = transformer.apply(map);
        assertEquals(2, result.size());
    }

    @Test
    public void testSome() {
        Apply<String, Option<String>> transformer = some();
        Option<String> result = transformer.apply("test");
        assertEquals(Some("test"), result);
        assertTrue(result.isDefined());
        assertEquals("test", result.get());
    }

    @Test
    public void testGet() {
        Apply<Option<String>, String> transformer = get();
        assertEquals("test", transformer.apply(Some("test")));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testGetOnNone() {
        Apply<Option<String>, String> transformer = get();
        transformer.apply(Option.<String>None());
    }

    @Test
    public void testGetOrElse() {
        Apply<Option<String>, String> transformer = getOrElse("default");
        assertEquals("test", transformer.apply(Some("test")));
        assertEquals("default", transformer.apply(Option.<String>None()));
    }

    @Test
    public void testIt() {
        String str = "abc";
        Iterable<Character> result = it.apply(str);
        List<Character> chars = newList(result);
        assertEquals(3, chars.size());
        assertEquals(Character.valueOf('a'), chars.get(0));
        assertEquals(Character.valueOf('b'), chars.get(1));
        assertEquals(Character.valueOf('c'), chars.get(2));
    }

    @Test
    public void testHead() {
        List<String> list = Arrays.asList("a", "b", "c");
        Apply<Iterable<String>, String> transformer = head();
        assertEquals("a", transformer.apply(list));
    }

    @Test
    public void testTail() {
        List<String> list = Arrays.asList("a", "b", "c");
        Apply<Iterable<String>, Iterable<String>> transformer = tail();
        List<String> result = newList(transformer.apply(list));
        assertEquals(2, result.size());
        assertEquals("b", result.get(0));
        assertEquals("c", result.get(1));
    }

    @Test
    public void testTailStr() {
        assertEquals("bc", tailStr.apply("abc"));
        assertEquals("", tailStr.apply("a"));
    }

    @Test
    public void testMap() {
        List<String> list = Arrays.asList("a", "bb", "ccc");
        Apply<Iterable<? extends String>, Iterable<Integer>> transformer = 
            map(new Apply<String, Integer>() {
                @Override
                public Integer apply(String t) {
                    return t.length();
                }
            });
        List<Integer> result = newList(transformer.apply(list));
        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    public void testMapOption() {
        Apply<Option<? extends String>, Option<Integer>> transformer = 
            mapOption(new Apply<String, Integer>() {
                @Override
                public Integer apply(String t) {
                    return t.length();
                }
            });
        assertEquals(Some(5), transformer.apply(Some("hello")));
        assertEquals(Option.<Integer>None(), transformer.apply(Option.<String>None()));
    }

    @Test
    public void testFlatMap() {
        List<String> list = Arrays.asList("a", "bb");
        Apply<Iterable<? extends String>, Iterable<Character>> transformer = 
            flatMap(new Apply<String, Iterable<Character>>() {
                @Override
                public Iterable<Character> apply(String t) {
                    return Collections.it(t);
                }
            });
        List<Character> result = newList(transformer.apply(list));
        assertEquals(3, result.size());
        assertEquals(Character.valueOf('a'), result.get(0));
        assertEquals(Character.valueOf('b'), result.get(1));
        assertEquals(Character.valueOf('b'), result.get(2));
    }

    @Test
    public void testFlatten() {
        List<List<String>> nested = Arrays.asList(
            Arrays.asList("a", "b"),
            Arrays.asList("c", "d")
        );
        Apply<Iterable<? extends List<String>>, Iterable<String>> transformer = flatten();
        List<String> result = newList(transformer.apply(nested));
        assertEquals(Arrays.asList("a", "b", "c", "d"), result);
    }

    @Test
    public void testNewList() {
        Apply<String, List<String>> transformer = Transformers.<String>newList();
        List<String> result = transformer.apply("test");
        assertEquals(1, result.size());
        assertEquals("test", result.get(0));
    }

    @Test
    public void testNewSet() {
        Apply<String, Set<String>> transformer = newSet();
        Set<String> result = transformer.apply("test");
        assertEquals(1, result.size());
        assertTrue(result.contains("test"));
    }

    @Test
    public void testNewMap() {
        Apply<Tuple2<String, Integer>, Map<String, Integer>> transformer = newMap();
        Map<String, Integer> result = transformer.apply(Tuple.of("key", 42));
        assertEquals(1, result.size());
        assertEquals(Integer.valueOf(42), result.get("key"));
    }

    @Test
    public void testSize() {
        List<String> list = Arrays.asList("a", "b", "c");
        assertEquals(Long.valueOf(3), size.apply(list));
        assertEquals(Long.valueOf(0), size.apply(Arrays.asList()));
    }

    @Test
    public void testTake2() {
        Tuple3<String, Integer, Boolean> tuple = Tuple.of("a", 42, true);
        Apply<Tuple3<String, Integer, Boolean>, Tuple2<String, Integer>> transformer = take2();
        Tuple2<String, Integer> result = transformer.apply(tuple);
        assertEquals("a", result._1);
        assertEquals(Integer.valueOf(42), result._2);
    }

    @Test
    public void testTake3() {
        Tuple4<String, Integer, Boolean, Double> tuple = Tuple.of("a", 42, true, 3.14);
        Apply<Tuple4<String, Integer, Boolean, Double>, Tuple3<String, Integer, Boolean>> transformer = take3();
        Tuple3<String, Integer, Boolean> result = transformer.apply(tuple);
        assertEquals("a", result._1);
        assertEquals(Integer.valueOf(42), result._2);
        assertEquals(Boolean.TRUE, result._3);
    }

    @Test
    public void testTupleAccessors() {
        Tuple3<String, Integer, Boolean> tuple = Tuple.of("a", 42, true);
        
        Apply<Tuple._1<? extends String>, String> t1 = _1();
        assertEquals("a", t1.apply(tuple));
        
        Apply<Tuple._2<? extends Integer>, Integer> t2 = _2();
        assertEquals(Integer.valueOf(42), t2.apply(tuple));
        
        Apply<Tuple._3<? extends Boolean>, Boolean> t3 = _3();
        assertEquals(Boolean.TRUE, t3.apply(tuple));
    }

    @Test
    public void testTupleAccessorsHigherPositions() {
        Tuple10<String, Integer, Boolean, Double, Long, Short, Byte, Float, Character, String> tuple = 
            Tuple.of("a", 42, true, 3.14, 100L, (short)5, (byte)1, 2.5f, 'x', "end");
        
        Apply<Tuple._4<? extends Double>, Double> t4 = _4();
        assertEquals(Double.valueOf(3.14), t4.apply(tuple));
        
        Apply<Tuple._5<? extends Long>, Long> t5 = _5();
        assertEquals(Long.valueOf(100L), t5.apply(tuple));
        
        Apply<Tuple._10<? extends String>, String> t10 = _10();
        assertEquals("end", t10.apply(tuple));
    }

    @Test
    public void testTupleAccessors11To20() {
        Tuple20<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer,
                Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> tuple = 
            Tuple.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
        
        Apply<Tuple._11<? extends Integer>, Integer> t11 = _11();
        assertEquals(Integer.valueOf(11), t11.apply(tuple));
        
        Apply<Tuple._15<? extends Integer>, Integer> t15 = _15();
        assertEquals(Integer.valueOf(15), t15.apply(tuple));
        
        Apply<Tuple._20<? extends Integer>, Integer> t20 = _20();
        assertEquals(Integer.valueOf(20), t20.apply(tuple));
    }

    @Test
    public void testTupleAccessors21To30() {
        Tuple30<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer,
                Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer,
                Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> tuple = 
            Tuple.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                     21, 22, 23, 24, 25, 26, 27, 28, 29, 30);
        
        Apply<Tuple._21<? extends Integer>, Integer> t21 = _21();
        assertEquals(Integer.valueOf(21), t21.apply(tuple));
        
        Apply<Tuple._25<? extends Integer>, Integer> t25 = _25();
        assertEquals(Integer.valueOf(25), t25.apply(tuple));
        
        Apply<Tuple._30<? extends Integer>, Integer> t30 = _30();
        assertEquals(Integer.valueOf(30), t30.apply(tuple));
    }

    @Test
    public void testTupleAccessors31To44() {
        Tuple44<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer,
                Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer,
                Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer,
                Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer,
                Integer, Integer, Integer, Integer> tuple = 
            Tuple.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
                     21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
                     41, 42, 43, 44);
        
        Apply<Tuple._31<? extends Integer>, Integer> t31 = _31();
        assertEquals(Integer.valueOf(31), t31.apply(tuple));
        
        Apply<Tuple._35<? extends Integer>, Integer> t35 = _35();
        assertEquals(Integer.valueOf(35), t35.apply(tuple));
        
        Apply<Tuple._40<? extends Integer>, Integer> t40 = _40();
        assertEquals(Integer.valueOf(40), t40.apply(tuple));
        
        Apply<Tuple._44<? extends Integer>, Integer> t44 = _44();
        assertEquals(Integer.valueOf(44), t44.apply(tuple));
    }

    @Test
    public void testZipTo() {
        List<String> list = Arrays.asList("a", "b", "c");
        Function2<Iterable<String>, Integer, Iterable<Pair<String, Integer>>> zipToFunc = zipTo();
        Iterable<Pair<String, Integer>> result = zipToFunc.apply(list, 42);
        
        List<Pair<String, Integer>> resultList = newList(result);
        assertEquals(3, resultList.size());
        assertEquals("a", resultList.get(0).left());
        assertEquals(Integer.valueOf(42), resultList.get(0).right());
        assertEquals("b", resultList.get(1).left());
        assertEquals(Integer.valueOf(42), resultList.get(1).right());
        assertEquals("c", resultList.get(2).left());
        assertEquals(Integer.valueOf(42), resultList.get(2).right());
    }

    @Test
    public void testZipToPair() {
        List<Pair<String, Integer>> list = Arrays.asList(
            Pair.of("a", 1),
            Pair.of("b", 2)
        );
        Function2<Iterable<? extends Map.Entry<String, Integer>>, String, Iterable<Tuple3<String, Integer, String>>> zipToPairFunc = zipToPair();
        Iterable<Tuple3<String, Integer, String>> result = zipToPairFunc.apply(list, "suffix");
        
        List<Tuple3<String, Integer, String>> resultList = newList(result);
        assertEquals(2, resultList.size());
        assertEquals("a", resultList.get(0)._1);
        assertEquals(Integer.valueOf(1), resultList.get(0)._2);
        assertEquals("suffix", resultList.get(0)._3);
        assertEquals("b", resultList.get(1)._1);
        assertEquals(Integer.valueOf(2), resultList.get(1)._2);
        assertEquals("suffix", resultList.get(1)._3);
    }

    @Test
    public void testModNegative() {
        Apply<Integer, Integer> transformer = mod(7);
        // Java's % operator: -3 % 7 = -3 (not 4 as in mathematical modulo)
        assertEquals(Integer.valueOf(-3), transformer.apply(-3));
        assertEquals(Integer.valueOf(0), transformer.apply(-7));
    }

    @Test
    public void testAppendMultipleOperations() {
        Apply<CharSequence, String> append1 = append(" world");
        Apply<CharSequence, String> append2 = append("!");
        
        assertEquals("hello world", append1.apply("hello"));
        assertEquals("hello world!", append2.apply(append1.apply("hello")));
    }

    @Test
    public void testPrependMultipleOperations() {
        Apply<CharSequence, String> prepend1 = prepend("hello ");
        Apply<CharSequence, String> prepend2 = prepend("well ");
        
        assertEquals("hello world", prepend1.apply("world"));
        String intermediate = prepend1.apply("world");
        assertEquals("well hello world", prepend2.apply(intermediate));
    }

    @Test
    public void testReplaceAllMultiplePatterns() {
        Pattern pattern1 = Pattern.compile("\\d+");
        Pattern pattern2 = Pattern.compile("[a-z]+");
        
        Apply<String, String> trans1 = replaceAll(pattern1, "NUM");
        assertEquals("test NUM and NUM", trans1.apply("test 123 and 456"));
        
        Apply<String, String> trans2 = replaceAll(pattern2, "WORD");
        assertEquals("WORD 123 WORD 456", trans2.apply("test 123 and 456"));
    }

    @Test
    public void testEmptyStringOperations() {
        assertEquals("", removeWhitespace.apply(""));
        // tailStr throws exception on empty string, so we test only with non-empty
        assertEquals("", prefix(5).apply(""));
    }

    @Test
    public void testNegateZero() {
        assertEquals(Long.valueOf(0), negate.apply(0L));
        assertEquals(Integer.valueOf(0), negateInt.apply(0));
        assertEquals(Short.valueOf((short) 0), negateShort.apply((short) 0));
    }

    @Test
    public void testConversionTransformers() {
        assertEquals(Long.valueOf(0), short2long.apply((short) 0));
        assertEquals(Long.valueOf(-100), short2long.apply((short) -100));
        assertEquals(Long.valueOf(Short.MAX_VALUE), short2long.apply(Short.MAX_VALUE));
        
        assertEquals(Long.valueOf(0), int2long.apply(0));
        assertEquals(Long.valueOf(-1000), int2long.apply(-1000));
        assertEquals(Long.valueOf(Integer.MAX_VALUE), int2long.apply(Integer.MAX_VALUE));
    }

    @Test
    public void testSomeWithNull() {
        Apply<String, Option<String>> transformer = some();
        Option<String> result = transformer.apply(null);
        assertTrue(result.isDefined());
        assertNull(result.get());
    }

    @Test
    public void testMapWithEmptyIterable() {
        List<String> emptyList = Arrays.asList();
        Apply<Iterable<? extends String>, Iterable<Integer>> transformer = 
            map(new Apply<String, Integer>() {
                @Override
                public Integer apply(String t) {
                    return t.length();
                }
            });
        List<Integer> result = newList(transformer.apply(emptyList));
        assertEquals(0, result.size());
    }

    @Test
    public void testFlatMapWithEmptyIterable() {
        List<String> emptyList = Arrays.asList();
        Apply<Iterable<? extends String>, Iterable<Character>> transformer = 
            flatMap(new Apply<String, Iterable<Character>>() {
                @Override
                public Iterable<Character> apply(String t) {
                    return Collections.it(t);
                }
            });
        List<Character> result = newList(transformer.apply(emptyList));
        assertEquals(0, result.size());
    }

    @Test
    public void testFlattenWithEmptyIterables() {
        List<List<String>> nested = Arrays.asList(
            Collections.<String>emptyList(),
            Arrays.asList("a"),
            Collections.<String>emptyList()
        );
        Apply<Iterable<? extends List<String>>, Iterable<String>> transformer = flatten();
        List<String> result = newList(transformer.apply(nested));
        assertEquals(Arrays.asList("a"), result);
    }
}
