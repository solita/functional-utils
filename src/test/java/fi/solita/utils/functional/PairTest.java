package fi.solita.utils.functional;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;

import org.junit.Test;

public class PairTest {
    
    class NonSerializable {}

    @Test
    public void isMapEntry() throws Exception {
        Pair<Integer,String> pair = Pair.of(42, "foo");
        
        assertThat(pair, instanceOf(Map.Entry.class));
        assertThat(pair.left(), equalTo(pair._1));
        assertThat(pair.right(), equalTo(pair._2));
    }
    
    @Test
    public void serialization() throws Exception {
        Pair<Integer,String> pair = Pair.of(42, "foo");
        assertThat(pair, instanceOf(Serializable.class));
        
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bao);
        out.writeObject(pair);
        out.close();
        
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
        Pair<?,?> deserializedPair = (Pair<?, ?>) in.readObject();
        in.close();
        
        assertThat(deserializedPair, instanceOf(Serializable.class));
        assertEquals(pair, deserializedPair);
        
        assertEquals(pair.left(), deserializedPair.left());
        assertEquals(pair.right(), deserializedPair.right());
        assertEquals(pair._1, deserializedPair._1);
        assertEquals(pair._2, deserializedPair._2);
    }
    
    @Test
    public void of_createsCorrectPair() {
        Pair<String, Integer> pair = Pair.of("hello", 42);
        
        assertEquals("hello", pair.left());
        assertEquals((Integer) 42, pair.right());
        assertEquals("hello", pair._1);
        assertEquals((Integer) 42, pair._2);
    }
    
    @Test
    public void of_handlesNullValues() {
        Pair<String, Integer> pairWithNullLeft = Pair.of(null, 42);
        Pair<String, Integer> pairWithNullRight = Pair.of("hello", null);
        Pair<String, Integer> pairWithBothNull = Pair.of(null, null);
        
        assertEquals(null, pairWithNullLeft.left());
        assertEquals((Integer) 42, pairWithNullLeft.right());
        
        assertEquals("hello", pairWithNullRight.left());
        assertEquals(null, pairWithNullRight.right());
        
        assertEquals(null, pairWithBothNull.left());
        assertEquals(null, pairWithBothNull.right());
    }
    
    @Test
    public void fanout_appliesBothFunctionsToSameInput() {
        Apply<Integer, String> toString = new Function1<Integer, String>() {
            @Override
            public String apply(Integer t) {
                return t.toString();
            }
        };
        
        Apply<Integer, Integer> doubleIt = new Function1<Integer, Integer>() {
            @Override
            public Integer apply(Integer t) {
                return t * 2;
            }
        };
        
        Apply<Integer, Pair<String, Integer>> fanoutFunction = Pair.fanout(toString, doubleIt);
        Pair<String, Integer> result = fanoutFunction.apply(42);
        
        assertEquals("42", result.left());
        assertEquals((Integer) 84, result.right());
    }
    
    @Test
    public void bimap_transformsBothSides() {
        Pair<Integer, String> pair = Pair.of(42, "hello");
        
        Apply<Integer, String> intToString = new Function1<Integer, String>() {
            @Override
            public String apply(Integer t) {
                return "num:" + t;
            }
        };
        
        Apply<String, Integer> stringToLength = new Function1<String, Integer>() {
            @Override
            public Integer apply(String t) {
                return t.length();
            }
        };
        
        Pair<String, Integer> result = pair.bimap(intToString, stringToLength);
        
        assertEquals("num:42", result.left());
        assertEquals((Integer) 5, result.right());
    }
    
    @Test
    public void first_transformsLeftSideOnly() {
        Pair<Integer, String> pair = Pair.of(42, "hello");
        
        Apply<Integer, String> intToString = new Function1<Integer, String>() {
            @Override
            public String apply(Integer t) {
                return "num:" + t;
            }
        };
        
        Pair<String, String> result = pair.first(intToString);
        
        assertEquals("num:42", result.left());
        assertEquals("hello", result.right());
    }
    
    @Test
    public void second_transformsRightSideOnly() {
        Pair<Integer, String> pair = Pair.of(42, "hello");
        
        Apply<String, Integer> stringToLength = new Function1<String, Integer>() {
            @Override
            public Integer apply(String t) {
                return t.length();
            }
        };
        
        Pair<Integer, Integer> result = pair.second(stringToLength);
        
        assertEquals((Integer) 42, result.left());
        assertEquals((Integer) 5, result.right());
    }
    
    @Test
    public void mapEntryInterface_getKey() {
        Pair<String, Integer> pair = Pair.of("key", 42);
        Map.Entry<String, Integer> entry = pair;
        
        assertEquals("key", entry.getKey());
    }
    
    @Test
    public void mapEntryInterface_getValue() {
        Pair<String, Integer> pair = Pair.of("key", 42);
        Map.Entry<String, Integer> entry = pair;
        
        assertEquals((Integer) 42, entry.getValue());
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void mapEntryInterface_setValueThrowsException() {
        Pair<String, Integer> pair = Pair.of("key", 42);
        Map.Entry<String, Integer> entry = pair;
        
        entry.setValue(100);
    }
    
    @Test
    public void equals_sameValues() {
        Pair<String, Integer> pair1 = Pair.of("hello", 42);
        Pair<String, Integer> pair2 = Pair.of("hello", 42);
        
        assertEquals(pair1, pair2);
        assertEquals(pair1.hashCode(), pair2.hashCode());
    }
    
    @Test
    public void equals_differentValues() {
        Pair<String, Integer> pair1 = Pair.of("hello", 42);
        Pair<String, Integer> pair2 = Pair.of("world", 42);
        Pair<String, Integer> pair3 = Pair.of("hello", 100);
        
        assertFalse(pair1.equals(pair2));
        assertFalse(pair1.equals(pair3));
    }
    
    @Test
    public void equals_withNullValues() {
        Pair<String, Integer> pair1 = Pair.of(null, 42);
        Pair<String, Integer> pair2 = Pair.of(null, 42);
        Pair<String, Integer> pair3 = Pair.of("hello", 42);
        
        assertEquals(pair1, pair2);
        assertFalse(pair1.equals(pair3));
    }
    
    @Test
    public void equals_reflexive() {
        Pair<String, Integer> pair = Pair.of("hello", 42);
        
        assertTrue(pair.equals(pair));
    }
    
    @Test
    public void equals_null() {
        Pair<String, Integer> pair = Pair.of("hello", 42);
        
        assertFalse(pair.equals(null));
    }
    
    @Test
    public void equals_differentType() {
        Pair<String, Integer> pair = Pair.of("hello", 42);
        
        assertFalse(pair.equals("hello"));
    }
    
    @Test
    public void hashCode_consistent() {
        Pair<String, Integer> pair = Pair.of("hello", 42);
        
        assertEquals(pair.hashCode(), pair.hashCode());
    }
    
    @Test
    public void toString_format() {
        Pair<String, Integer> pair = Pair.of("hello", 42);
        
        String result = pair.toString();
        
        assertTrue(result.contains("hello"));
        assertTrue(result.contains("42"));
    }
    
    @Test
    public void toArray_containsValues() {
        Pair<String, Integer> pair = Pair.of("hello", 42);
        
        Object[] array = pair.toArray();
        
        assertEquals(2, array.length);
        assertEquals("hello", array[0]);
        assertEquals(42, array[1]);
    }
    
    @Test
    public void prepend_addsElementToLeft() {
        Pair<String, Integer> pair = Pair.of("hello", 42);
        
        Tuple3<Boolean, String, Integer> result = pair.prepend(true);
        
        assertEquals(true, result._1);
        assertEquals("hello", result._2);
        assertEquals((Integer) 42, result._3);
    }
    
    @Test
    public void append_addsElementToRight() {
        Pair<String, Integer> pair = Pair.of("hello", 42);
        
        Tuple3<String, Integer, Double> result = pair.append(3.14);
        
        assertEquals("hello", result._1);
        assertEquals((Integer) 42, result._2);
        assertEquals((Double) 3.14, result._3);
    }
    
    @Test
    public void join_withTuple1() {
        Pair<String, Integer> pair = Pair.of("hello", 42);
        Tuple1<Boolean> tuple1 = Tuple.of(true);
        
        Tuple3<String, Integer, Boolean> result = pair.join(tuple1);
        
        assertEquals("hello", result._1);
        assertEquals((Integer) 42, result._2);
        assertEquals(true, result._3);
    }
    
    @Test
    public void join_withTuple2() {
        Pair<String, Integer> pair = Pair.of("hello", 42);
        Tuple2<Boolean, Double> tuple2 = Tuple.of(true, 3.14);
        
        Tuple4<String, Integer, Boolean, Double> result = pair.join(tuple2);
        
        assertEquals("hello", result._1);
        assertEquals((Integer) 42, result._2);
        assertEquals(true, result._3);
        assertEquals((Double) 3.14, result._4);
    }
    
    @Test
    public void get_1_returnLeftValue() {
        Pair<String, Integer> pair = Pair.of("hello", 42);
        
        assertEquals("hello", pair.get_1());
    }
    
    @Test
    public void get_2_returnRightValue() {
        Pair<String, Integer> pair = Pair.of("hello", 42);
        
        assertEquals((Integer) 42, pair.get_2());
    }
    
    @Test
    public void take1_returnsFirstElement() {
        Pair<String, Integer> pair = Pair.of("hello", 42);
        
        Tuple1<String> result = pair.take1();
        
        assertEquals("hello", result._1);
    }
    
    @Test
    public void drop1_returnsSecondElement() {
        Pair<String, Integer> pair = Pair.of("hello", 42);
        
        Tuple1<Integer> result = pair.drop1();
        
        assertEquals((Integer) 42, result._1);
    }
    
    @Test
    public void fanout_withNullResult() {
        Apply<Integer, String> nullReturner = new Function1<Integer, String>() {
            @Override
            public String apply(Integer t) {
                return null;
            }
        };
        
        Apply<Integer, Integer> identity = new Function1<Integer, Integer>() {
            @Override
            public Integer apply(Integer t) {
                return t;
            }
        };
        
        Apply<Integer, Pair<String, Integer>> fanoutFunction = Pair.fanout(nullReturner, identity);
        Pair<String, Integer> result = fanoutFunction.apply(42);
        
        assertEquals(null, result.left());
        assertEquals((Integer) 42, result.right());
    }
    
    @Test
    public void bimap_withNullInput() {
        Pair<String, Integer> pair = Pair.of(null, null);
        
        Apply<String, Integer> stringToLength = new Function1<String, Integer>() {
            @Override
            public Integer apply(String t) {
                return t == null ? 0 : t.length();
            }
        };
        
        Apply<Integer, String> intToString = new Function1<Integer, String>() {
            @Override
            public String apply(Integer t) {
                return t == null ? "null" : t.toString();
            }
        };
        
        Pair<Integer, String> result = pair.bimap(stringToLength, intToString);
        
        assertEquals((Integer) 0, result.left());
        assertEquals("null", result.right());
    }
    
    @Test
    public void first_withNullValue() {
        Pair<String, Integer> pair = Pair.of(null, 42);
        
        Apply<String, Integer> stringToLength = new Function1<String, Integer>() {
            @Override
            public Integer apply(String t) {
                return t == null ? 0 : t.length();
            }
        };
        
        Pair<Integer, Integer> result = pair.first(stringToLength);
        
        assertEquals((Integer) 0, result.left());
        assertEquals((Integer) 42, result.right());
    }
    
    @Test
    public void second_withNullValue() {
        Pair<String, Integer> pair = Pair.of("hello", null);
        
        Apply<Integer, String> intToString = new Function1<Integer, String>() {
            @Override
            public String apply(Integer t) {
                return t == null ? "null" : t.toString();
            }
        };
        
        Pair<String, String> result = pair.second(intToString);
        
        assertEquals("hello", result.left());
        assertEquals("null", result.right());
    }
}
