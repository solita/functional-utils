package fi.solita.utils.functional;
import static org.junit.Assert.assertEquals;

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
}
