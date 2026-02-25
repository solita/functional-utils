package fi.solita.utils.functional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;


public class TupleTest {

    @Test
    public void testPrefix() {
        Tuple4<String, Integer, Long, Double> tuple4 = Tuple.of("1", 2, 3l, 4.0);
        
        Tuple2<String, Integer> tuple2 = tuple4.take2();
        Tuple3<String, Integer, Long> tuple3 = tuple4.take3();
        
        assertEquals("1", tuple2._1);
        assertEquals((Integer)2, tuple2._2);
        
        assertEquals("1", tuple3._1);
        assertEquals((Integer)2, tuple3._2);
        assertEquals((Long)3l, tuple3._3);
    }
    
    @Test
    public void testTupleOfFactoryMethods() {
        // Test Tuple0
        Tuple0 tuple0 = Tuple.of();
        assertEquals(0, tuple0.toArray().length);
        
        // Test Tuple1
        Tuple1<String> tuple1 = Tuple.of("a");
        assertEquals("a", tuple1._1);
        
        // Test Tuple2
        Tuple2<String, Integer> tuple2 = Tuple.of("a", 1);
        assertEquals("a", tuple2._1);
        assertEquals((Integer)1, tuple2._2);
        
        // Test Tuple3
        Tuple3<String, Integer, Long> tuple3 = Tuple.of("a", 1, 2L);
        assertEquals("a", tuple3._1);
        assertEquals((Integer)1, tuple3._2);
        assertEquals((Long)2L, tuple3._3);
        
        // Test Tuple4
        Tuple4<String, Integer, Long, Double> tuple4 = Tuple.of("a", 1, 2L, 3.0);
        assertEquals("a", tuple4._1);
        assertEquals((Integer)1, tuple4._2);
        assertEquals((Long)2L, tuple4._3);
        assertEquals((Double)3.0, tuple4._4);
        
        // Test Tuple5
        Tuple5<String, Integer, Long, Double, Boolean> tuple5 = Tuple.of("a", 1, 2L, 3.0, true);
        assertEquals("a", tuple5._1);
        assertEquals((Integer)1, tuple5._2);
        assertEquals((Long)2L, tuple5._3);
        assertEquals((Double)3.0, tuple5._4);
        assertEquals(Boolean.TRUE, tuple5._5);
    }
    
    @Test
    public void testTupleVarargsOf() {
        // Test with different argument counts
        Tuple tuple0 = Tuple.of(new Object[]{});
        assertTrue(tuple0 instanceof Tuple0);
        
        Tuple tuple1 = Tuple.of(new Object[]{"a"});
        assertTrue(tuple1 instanceof Tuple1);
        assertEquals("a", ((Tuple1<?>)tuple1)._1);
        
        Tuple tuple2 = Tuple.of(new Object[]{"a", 1});
        assertTrue(tuple2 instanceof Tuple2);
        assertEquals("a", ((Tuple2<?,?>)tuple2)._1);
        assertEquals(1, ((Tuple2<?,?>)tuple2)._2);
        
        Tuple tuple3 = Tuple.of(new Object[]{"a", 1, 2L});
        assertTrue(tuple3 instanceof Tuple3);
        assertEquals("a", ((Tuple3<?,?,?>)tuple3)._1);
        assertEquals(1, ((Tuple3<?,?,?>)tuple3)._2);
        assertEquals(2L, ((Tuple3<?,?,?>)tuple3)._3);
    }
    
    @Test
    public void testAsList() {
        // Test Tuple0
        List<Object> list0 = Tuple.asList(Tuple.of());
        assertEquals(0, list0.size());
        
        // Test Tuple1
        List<String> list1 = Tuple.asList(Tuple.of("a"));
        assertEquals(1, list1.size());
        assertEquals("a", list1.get(0));
        
        // Test Tuple2
        List<?> list2 = Tuple.asList(Tuple.of("a", 1));
        assertEquals(2, list2.size());
        assertEquals("a", list2.get(0));
        assertEquals(1, list2.get(1));
        
        // Test Tuple3
        List<?> list3 = Tuple.asList(Tuple.of("a", 1, 2L));
        assertEquals(3, list3.size());
        assertEquals("a", list3.get(0));
        assertEquals(1, list3.get(1));
        assertEquals(2L, list3.get(2));
        
        // Test Tuple4
        List<?> list4 = Tuple.asList(Tuple.of("a", 1, 2L, 3.0));
        assertEquals(4, list4.size());
        assertEquals("a", list4.get(0));
        assertEquals(1, list4.get(1));
        assertEquals(2L, list4.get(2));
        assertEquals(3.0, list4.get(3));
    }
    
    @Test
    public void testAppend() {
        Tuple2<String, Integer> tuple2 = Tuple.of("a", 1);
        Tuple3<String, Integer, Long> tuple3 = tuple2.append(2L);
        
        assertEquals("a", tuple3._1);
        assertEquals((Integer)1, tuple3._2);
        assertEquals((Long)2L, tuple3._3);
        
        Tuple4<String, Integer, Long, Double> tuple4 = tuple3.append(3.0);
        assertEquals("a", tuple4._1);
        assertEquals((Integer)1, tuple4._2);
        assertEquals((Long)2L, tuple4._3);
        assertEquals((Double)3.0, tuple4._4);
    }
    
    @Test
    public void testPrepend() {
        Tuple2<String, Integer> tuple2 = Tuple.of("a", 1);
        Tuple3<Long, String, Integer> tuple3 = tuple2.prepend(2L);
        
        assertEquals((Long)2L, tuple3._1);
        assertEquals("a", tuple3._2);
        assertEquals((Integer)1, tuple3._3);
        
        Tuple4<Double, Long, String, Integer> tuple4 = tuple3.prepend(3.0);
        assertEquals((Double)3.0, tuple4._1);
        assertEquals((Long)2L, tuple4._2);
        assertEquals("a", tuple4._3);
        assertEquals((Integer)1, tuple4._4);
    }
    
    @Test
    public void testJoin() {
        Tuple2<String, Integer> tuple2a = Tuple.of("a", 1);
        Tuple2<Long, Double> tuple2b = Tuple.of(2L, 3.0);
        
        Tuple4<String, Integer, Long, Double> tuple4 = tuple2a.join(tuple2b);
        
        assertEquals("a", tuple4._1);
        assertEquals((Integer)1, tuple4._2);
        assertEquals((Long)2L, tuple4._3);
        assertEquals((Double)3.0, tuple4._4);
        
        // Test joining with Tuple1
        Tuple1<Boolean> tuple1 = Tuple.of(true);
        Tuple3<String, Integer, Boolean> tuple3 = tuple2a.join(tuple1);
        
        assertEquals("a", tuple3._1);
        assertEquals((Integer)1, tuple3._2);
        assertEquals(Boolean.TRUE, tuple3._3);
    }
    
    @Test
    public void testDrop() {
        Tuple4<String, Integer, Long, Double> tuple4 = Tuple.of("a", 1, 2L, 3.0);
        
        // Test drop1
        Tuple3<Integer, Long, Double> tuple3 = tuple4.drop1();
        assertEquals((Integer)1, tuple3._1);
        assertEquals((Long)2L, tuple3._2);
        assertEquals((Double)3.0, tuple3._3);
        
        // Test drop2
        Tuple2<Long, Double> tuple2 = tuple4.drop2();
        assertEquals((Long)2L, tuple2._1);
        assertEquals((Double)3.0, tuple2._2);
        
        // Test drop3
        Tuple1<Double> tuple1 = tuple4.drop3();
        assertEquals((Double)3.0, tuple1._1);
    }
    
    @Test
    public void testTake() {
        Tuple4<String, Integer, Long, Double> tuple4 = Tuple.of("a", 1, 2L, 3.0);
        
        // Test take1
        Tuple1<String> tuple1 = tuple4.take1();
        assertEquals("a", tuple1._1);
        
        // Test take2
        Tuple2<String, Integer> tuple2 = tuple4.take2();
        assertEquals("a", tuple2._1);
        assertEquals((Integer)1, tuple2._2);
        
        // Test take3
        Tuple3<String, Integer, Long> tuple3 = tuple4.take3();
        assertEquals("a", tuple3._1);
        assertEquals((Integer)1, tuple3._2);
        assertEquals((Long)2L, tuple3._3);
    }
    
    @Test
    public void testEquals() {
        Tuple2<String, Integer> tuple1 = Tuple.of("a", 1);
        Tuple2<String, Integer> tuple2 = Tuple.of("a", 1);
        Tuple2<String, Integer> tuple3 = Tuple.of("b", 1);
        Tuple2<String, Integer> tuple4 = Tuple.of("a", 2);
        
        assertEquals(tuple1, tuple2);
        assertFalse(tuple1.equals(tuple3));
        assertFalse(tuple1.equals(tuple4));
        assertFalse(tuple3.equals(tuple4));
        
        // Test with null values
        Tuple2<String, Integer> tupleWithNull1 = Tuple.of(null, 1);
        Tuple2<String, Integer> tupleWithNull2 = Tuple.of(null, 1);
        Tuple2<String, Integer> tupleWithNull3 = Tuple.of("a", null);
        
        assertEquals(tupleWithNull1, tupleWithNull2);
        assertFalse(tupleWithNull1.equals(tupleWithNull3));
        assertFalse(tuple1.equals(tupleWithNull1));
    }
    
    @Test
    public void testHashCode() {
        Tuple2<String, Integer> tuple1 = Tuple.of("a", 1);
        Tuple2<String, Integer> tuple2 = Tuple.of("a", 1);
        Tuple2<String, Integer> tuple3 = Tuple.of("b", 1);
        
        assertEquals(tuple1.hashCode(), tuple2.hashCode());
        assertFalse(tuple1.hashCode() == tuple3.hashCode());
        
        // Test with null values
        Tuple2<String, Integer> tupleWithNull = Tuple.of(null, 1);
        // Just verify it doesn't throw an exception
        tupleWithNull.hashCode();
    }
    
    @Test
    public void testToString() {
        Tuple2<String, Integer> tuple2 = Tuple.of("a", 1);
        assertEquals("(a,1)", tuple2.toString());
        
        Tuple3<String, Integer, Long> tuple3 = Tuple.of("a", 1, 2L);
        assertEquals("(a,1,2)", tuple3.toString());
        
        Tuple4<String, Integer, Long, Double> tuple4 = Tuple.of("a", 1, 2L, 3.0);
        assertEquals("(a,1,2,3.0)", tuple4.toString());
        
        // Test with null
        Tuple2<String, Integer> tupleWithNull = Tuple.of(null, 1);
        assertEquals("(null,1)", tupleWithNull.toString());
    }
    
    @Test
    public void testBimap() {
        Tuple2<String, Integer> tuple = Tuple.of("hello", 5);
        
        Tuple2<Integer, String> mapped = tuple.bimap(
            new Apply<String, Integer>() {
                @Override
                public Integer apply(String s) {
                    return s.length();
                }
            },
            new Apply<Integer, String>() {
                @Override
                public String apply(Integer i) {
                    return i.toString();
                }
            }
        );
        
        assertEquals((Integer)5, mapped._1);
        assertEquals("5", mapped._2);
    }
    
    @Test
    public void testFirst() {
        Tuple2<String, Integer> tuple = Tuple.of("hello", 5);
        
        Tuple2<Integer, Integer> result = tuple.first(new Apply<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        });
        
        assertEquals((Integer)5, result._1);
        assertEquals((Integer)5, result._2);
    }
    
    @Test
    public void testSecond() {
        Tuple2<String, Integer> tuple = Tuple.of("hello", 5);
        
        Tuple2<String, String> result = tuple.second(new Apply<Integer, String>() {
            @Override
            public String apply(Integer i) {
                return i.toString();
            }
        });
        
        assertEquals("hello", result._1);
        assertEquals("5", result._2);
    }
    
    @Test
    public void testToArray() {
        Tuple4<String, Integer, Long, Double> tuple4 = Tuple.of("a", 1, 2L, 3.0);
        Object[] array = tuple4.toArray();
        
        assertEquals(4, array.length);
        assertEquals("a", array[0]);
        assertEquals(1, array[1]);
        assertEquals(2L, array[2]);
        assertEquals(3.0, array[3]);
    }
    
    @Test
    public void testLargerTupleFactory() {
        // Test Tuple6
        Tuple6<String, Integer, Long, Double, Boolean, Character> tuple6 = 
            Tuple.of("a", 1, 2L, 3.0, true, 'x');
        assertEquals("a", tuple6._1);
        assertEquals((Integer)1, tuple6._2);
        assertEquals((Long)2L, tuple6._3);
        assertEquals((Double)3.0, tuple6._4);
        assertEquals(Boolean.TRUE, tuple6._5);
        assertEquals((Character)'x', tuple6._6);
        
        // Test Tuple7
        Tuple7<String, Integer, Long, Double, Boolean, Character, Float> tuple7 = 
            Tuple.of("a", 1, 2L, 3.0, true, 'x', 7.0f);
        assertEquals("a", tuple7._1);
        assertEquals((Float)7.0f, tuple7._7);
        
        // Test Tuple8
        Tuple8<String, Integer, Long, Double, Boolean, Character, Float, Byte> tuple8 = 
            Tuple.of("a", 1, 2L, 3.0, true, 'x', 7.0f, (byte)8);
        assertEquals("a", tuple8._1);
        assertEquals((Byte)(byte)8, tuple8._8);
    }
    
    @Test
    public void testAccessorMethods() {
        Tuple4<String, Integer, Long, Double> tuple4 = Tuple.of("a", 1, 2L, 3.0);
        
        assertEquals("a", tuple4.get_1());
        assertEquals((Integer)1, tuple4.get_2());
        assertEquals((Long)2L, tuple4.get_3());
        assertEquals((Double)3.0, tuple4.get_4());
    }
    
    @Test
    public void testTuple5Operations() {
        Tuple5<String, Integer, Long, Double, Boolean> tuple5 = 
            Tuple.of("a", 1, 2L, 3.0, true);
        
        // Test take operations
        Tuple1<String> tuple1 = tuple5.take1();
        assertEquals("a", tuple1._1);
        
        Tuple2<String, Integer> tuple2 = tuple5.take2();
        assertEquals("a", tuple2._1);
        assertEquals((Integer)1, tuple2._2);
        
        Tuple3<String, Integer, Long> tuple3 = tuple5.take3();
        assertEquals("a", tuple3._1);
        assertEquals((Integer)1, tuple3._2);
        assertEquals((Long)2L, tuple3._3);
        
        Tuple4<String, Integer, Long, Double> tuple4 = tuple5.take4();
        assertEquals("a", tuple4._1);
        assertEquals((Integer)1, tuple4._2);
        assertEquals((Long)2L, tuple4._3);
        assertEquals((Double)3.0, tuple4._4);
        
        // Test drop operations
        Tuple4<Integer, Long, Double, Boolean> dropped1 = tuple5.drop1();
        assertEquals((Integer)1, dropped1._1);
        assertEquals(Boolean.TRUE, dropped1._4);
        
        Tuple3<Long, Double, Boolean> dropped2 = tuple5.drop2();
        assertEquals((Long)2L, dropped2._1);
        assertEquals(Boolean.TRUE, dropped2._3);
        
        Tuple2<Double, Boolean> dropped3 = tuple5.drop3();
        assertEquals((Double)3.0, dropped3._1);
        assertEquals(Boolean.TRUE, dropped3._2);
        
        Tuple1<Boolean> dropped4 = tuple5.drop4();
        assertEquals(Boolean.TRUE, dropped4._1);
    }
    
    @Test
    public void testTuple0Operations() {
        Tuple0 tuple0 = Tuple.of();
        
        // Test toArray
        Object[] array = tuple0.toArray();
        assertEquals(0, array.length);
        
        // Test toString
        assertEquals("()", tuple0.toString());
        
        // Test equals
        Tuple0 tuple0b = Tuple.of();
        assertEquals(tuple0, tuple0b);
        
        // Test hashCode
        assertEquals(tuple0.hashCode(), tuple0b.hashCode());
    }
    
    @Test
    public void testTuple1Operations() {
        Tuple1<String> tuple1 = Tuple.of("test");
        
        // Test toArray
        Object[] array = tuple1.toArray();
        assertEquals(1, array.length);
        assertEquals("test", array[0]);
        
        // Test append
        Tuple2<String, Integer> tuple2 = tuple1.append(42);
        assertEquals("test", tuple2._1);
        assertEquals((Integer)42, tuple2._2);
        
        // Test prepend
        Tuple2<Integer, String> tuple2b = tuple1.prepend(42);
        assertEquals((Integer)42, tuple2b._1);
        assertEquals("test", tuple2b._2);
        
        // Test accessor
        assertEquals("test", tuple1.get_1());
    }
    
    @Test
    public void testComplexJoinOperations() {
        Tuple3<String, Integer, Long> tuple3a = Tuple.of("a", 1, 2L);
        Tuple3<Double, Boolean, Character> tuple3b = Tuple.of(3.0, true, 'x');
        
        Tuple6<String, Integer, Long, Double, Boolean, Character> tuple6 = tuple3a.join(tuple3b);
        
        assertEquals("a", tuple6._1);
        assertEquals((Integer)1, tuple6._2);
        assertEquals((Long)2L, tuple6._3);
        assertEquals((Double)3.0, tuple6._4);
        assertEquals(Boolean.TRUE, tuple6._5);
        assertEquals((Character)'x', tuple6._6);
    }
    
    @Test
    public void testVarargsOfWithLargerTuples() {
        // Test with 5 elements
        Tuple tuple5 = Tuple.of(new Object[]{"a", 1, 2L, 3.0, true});
        assertTrue(tuple5 instanceof Tuple5);
        assertEquals("a", ((Tuple5<?,?,?,?,?>)tuple5)._1);
        assertEquals(true, ((Tuple5<?,?,?,?,?>)tuple5)._5);
        
        // Test with 6 elements
        Tuple tuple6 = Tuple.of(new Object[]{"a", 1, 2L, 3.0, true, 'x'});
        assertTrue(tuple6 instanceof Tuple6);
        assertEquals("a", ((Tuple6<?,?,?,?,?,?>)tuple6)._1);
        assertEquals('x', ((Tuple6<?,?,?,?,?,?>)tuple6)._6);
    }
    
    @Test
    public void testAsListWithLargerTuples() {
        // Test Tuple5
        List<?> list5 = Tuple.asList(Tuple.of("a", 1, 2L, 3.0, true));
        assertEquals(5, list5.size());
        assertEquals("a", list5.get(0));
        assertEquals(true, list5.get(4));
        
        // Test Tuple6
        List<?> list6 = Tuple.asList(Tuple.of("a", 1, 2L, 3.0, true, 'x'));
        assertEquals(6, list6.size());
        assertEquals("a", list6.get(0));
        assertEquals('x', list6.get(5));
    }
    
    @Test
    public void testEqualsWithDifferentTypes() {
        Tuple2<String, Integer> tuple2 = Tuple.of("a", 1);
        Tuple3<String, Integer, Long> tuple3 = Tuple.of("a", 1, 2L);
        
        // Different tuple sizes should not be equal
        assertFalse(tuple2.equals(tuple3));
        assertFalse(tuple3.equals(tuple2));
    }
    
    @Test
    public void testToStringWithEmptyTuple() {
        Tuple0 tuple0 = Tuple.of();
        assertEquals("()", tuple0.toString());
        
        Tuple1<String> tuple1 = Tuple.of("single");
        assertEquals("(single)", tuple1.toString());
    }
    
    @Test
    public void testTuple9And10FactoryMethods() {
        // Test Tuple9
        Tuple9<String, Integer, Long, Double, Boolean, Character, Float, Byte, Short> tuple9 = 
            Tuple.of("a", 1, 2L, 3.0, true, 'x', 7.0f, (byte)8, (short)9);
        assertEquals("a", tuple9._1);
        assertEquals((Integer)1, tuple9._2);
        assertEquals((Short)(short)9, tuple9._9);
        
        // Test Tuple10
        Tuple10<String, Integer, Long, Double, Boolean, Character, Float, Byte, Short, String> tuple10 = 
            Tuple.of("a", 1, 2L, 3.0, true, 'x', 7.0f, (byte)8, (short)9, "ten");
        assertEquals("a", tuple10._1);
        assertEquals("ten", tuple10._10);
    }
    
    @Test
    public void testTuple9And10Operations() {
        // Test Tuple9 operations
        Tuple9<String, Integer, Long, Double, Boolean, Character, Float, Byte, Short> tuple9 = 
            Tuple.of("a", 1, 2L, 3.0, true, 'x', 7.0f, (byte)8, (short)9);
        
        // Test take operations
        Tuple5<String, Integer, Long, Double, Boolean> tuple5 = tuple9.take5();
        assertEquals("a", tuple5._1);
        assertEquals(Boolean.TRUE, tuple5._5);
        
        // Test drop operations
        Tuple5<Boolean, Character, Float, Byte, Short> dropped4 = tuple9.drop4();
        assertEquals(Boolean.TRUE, dropped4._1);
        assertEquals((Short)(short)9, dropped4._5);
        
        // Test Tuple10 accessor methods
        Tuple10<String, Integer, Long, Double, Boolean, Character, Float, Byte, Short, String> tuple10 = 
            Tuple.of("a", 1, 2L, 3.0, true, 'x', 7.0f, (byte)8, (short)9, "ten");
        
        assertEquals("a", tuple10.get_1());
        assertEquals("ten", tuple10.get_10());
    }
    
    @Test
    public void testAsListWithTuple9And10() {
        // Test Tuple9
        List<?> list9 = Tuple.asList(Tuple.of("a", 1, 2L, 3.0, true, 'x', 7.0f, (byte)8, (short)9));
        assertEquals(9, list9.size());
        assertEquals("a", list9.get(0));
        assertEquals((short)9, list9.get(8));
        
        // Test Tuple10
        List<?> list10 = Tuple.asList(Tuple.of("a", 1, 2L, 3.0, true, 'x', 7.0f, (byte)8, (short)9, "ten"));
        assertEquals(10, list10.size());
        assertEquals("a", list10.get(0));
        assertEquals("ten", list10.get(9));
    }
    
    @Test
    public void testVarargsWithTuple9And10() {
        // Test with 9 elements
        Tuple tuple9 = Tuple.of(new Object[]{"a", 1, 2L, 3.0, true, 'x', 7.0f, (byte)8, (short)9});
        assertTrue(tuple9 instanceof Tuple9);
        assertEquals("a", ((Tuple9<?,?,?,?,?,?,?,?,?>)tuple9)._1);
        assertEquals((short)9, ((Tuple9<?,?,?,?,?,?,?,?,?>)tuple9)._9);
        
        // Test with 10 elements
        Tuple tuple10 = Tuple.of(new Object[]{"a", 1, 2L, 3.0, true, 'x', 7.0f, (byte)8, (short)9, "ten"});
        assertTrue(tuple10 instanceof Tuple10);
        assertEquals("a", ((Tuple10<?,?,?,?,?,?,?,?,?,?>)tuple10)._1);
        assertEquals("ten", ((Tuple10<?,?,?,?,?,?,?,?,?,?>)tuple10)._10);
    }
    
    @Test
    public void testTupleChainOperations() {
        // Test chaining append operations
        Tuple1<String> tuple1 = Tuple.of("a");
        Tuple5<String, Integer, Long, Double, Boolean> tuple5 = 
            tuple1.append(1).append(2L).append(3.0).append(true);
        
        assertEquals("a", tuple5._1);
        assertEquals((Integer)1, tuple5._2);
        assertEquals(Boolean.TRUE, tuple5._5);
        
        // Test chaining prepend operations
        Tuple1<Boolean> base = Tuple.of(true);
        Tuple5<Double, Long, Integer, String, Boolean> reversed = 
            base.prepend("a").prepend(1).prepend(2L).prepend(3.0);
        
        assertEquals((Double)3.0, reversed._1);
        assertEquals(Boolean.TRUE, reversed._5);
    }
    
    @Test
    public void testEqualityWithNullsInLargerTuples() {
        Tuple5<String, Integer, Long, Double, Boolean> tuple5a = Tuple.of("a", null, 2L, null, true);
        Tuple5<String, Integer, Long, Double, Boolean> tuple5b = Tuple.of("a", null, 2L, null, true);
        Tuple5<String, Integer, Long, Double, Boolean> tuple5c = Tuple.of("a", 1, 2L, null, true);
        
        assertEquals(tuple5a, tuple5b);
        assertFalse(tuple5a.equals(tuple5c));
    }
    
    @Test
    public void testToArrayWithLargerTuples() {
        Tuple9<String, Integer, Long, Double, Boolean, Character, Float, Byte, Short> tuple9 = 
            Tuple.of("a", 1, 2L, 3.0, true, 'x', 7.0f, (byte)8, (short)9);
        Object[] array = tuple9.toArray();
        
        assertEquals(9, array.length);
        assertEquals("a", array[0]);
        assertEquals((short)9, array[8]);
    }
    
    @Test
    public void testMapEntryCompatibility() {
        // Tuple2 implements Map.Entry
        Tuple2<String, Integer> tuple2 = Tuple.of("key", 42);
        
        assertEquals("key", tuple2.getKey());
        assertEquals((Integer)42, tuple2.getValue());
    }
    
    @Test
    public void testLargerTuples11to16() {
        // Test Tuple11
        Tuple11<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> tuple11 = 
            Tuple.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
        assertEquals((Integer)1, tuple11._1);
        assertEquals((Integer)11, tuple11._11);
        assertEquals((Integer)11, tuple11.get_11());
        
        // Test Tuple12
        Tuple12<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> tuple12 = 
            Tuple.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12);
        assertEquals((Integer)12, tuple12._12);
        
        // Test Tuple15
        Tuple15<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> tuple15 = 
            Tuple.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15);
        assertEquals((Integer)15, tuple15._15);
        
        // Test Tuple16
        Tuple16<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> tuple16 = 
            Tuple.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16);
        assertEquals((Integer)16, tuple16._16);
    }
    
    @Test
    public void testAsListWithLargerTuples11to16() {
        // Test Tuple11
        List<Integer> list11 = Tuple.asList(Tuple.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11));
        assertEquals(11, list11.size());
        assertEquals((Integer)1, list11.get(0));
        assertEquals((Integer)11, list11.get(10));
        
        // Test Tuple15
        List<Integer> list15 = Tuple.asList(Tuple.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15));
        assertEquals(15, list15.size());
        assertEquals((Integer)15, list15.get(14));
    }
    
    @Test
    public void testVarargsWithLargerTuples() {
        // Test with 11 elements
        Tuple tuple11 = Tuple.of(new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11});
        assertTrue(tuple11 instanceof Tuple11);
        
        // Test with 15 elements
        Tuple tuple15 = Tuple.of(new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        assertTrue(tuple15 instanceof Tuple15);
        
        // Test with 20 elements
        Tuple tuple20 = Tuple.of(new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20});
        assertTrue(tuple20 instanceof Tuple20);
    }
    
    @Test
    public void testJoinWithVariousSizes() {
        // Test joining Tuple4 and Tuple4 to get Tuple8
        Tuple4<Integer, Integer, Integer, Integer> tuple4a = Tuple.of(1, 2, 3, 4);
        Tuple4<Integer, Integer, Integer, Integer> tuple4b = Tuple.of(5, 6, 7, 8);
        Tuple8<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> tuple8 = tuple4a.join(tuple4b);
        
        assertEquals((Integer)1, tuple8._1);
        assertEquals((Integer)8, tuple8._8);
        
        // Test joining Tuple5 and Tuple5 to get Tuple10
        Tuple5<Integer, Integer, Integer, Integer, Integer> tuple5a = Tuple.of(1, 2, 3, 4, 5);
        Tuple5<Integer, Integer, Integer, Integer, Integer> tuple5b = Tuple.of(6, 7, 8, 9, 10);
        Tuple10<Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer, Integer> tuple10 = tuple5a.join(tuple5b);
        
        assertEquals((Integer)1, tuple10._1);
        assertEquals((Integer)10, tuple10._10);
    }
    
    @Test
    public void testEqualsWithNull() {
        Tuple0 tuple0a = Tuple.of();
        Tuple0 tuple0b = Tuple.of();
        assertEquals(tuple0a, tuple0b);
        
        // Test equals with null object
        assertFalse(tuple0a.equals(null));
        
        // Test equals with different class
        assertFalse(Tuple.of("a", 1).equals("not a tuple"));
    }
    
    @Test
    public void testToArrayForAllSizes() {
        // Test Tuple0
        assertEquals(0, Tuple.of().toArray().length);
        
        // Test Tuple1
        assertEquals(1, Tuple.of("a").toArray().length);
        
        // Test Tuple5
        assertEquals(5, Tuple.of(1, 2, 3, 4, 5).toArray().length);
        
        // Test Tuple10
        assertEquals(10, Tuple.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10).toArray().length);
    }
}
