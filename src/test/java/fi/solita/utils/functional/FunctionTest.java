package fi.solita.utils.functional;

import static fi.solita.utils.functional.Function._;
import static fi.solita.utils.functional.Function.__;
import static org.junit.Assert.*;

import org.junit.Test;

public class FunctionTest {

    @Test
    public void testName() {
         Function3<String, Character, Integer, Long> f = new Function3<String,Character,Integer,Long>() {
            @Override
            public Long apply(String t1, Character t2, Integer t3) {
                return (long) (t1.length() + Integer.parseInt(t2.toString()) + t3);
            }
         };
         
         Function1<String, Function2<Character, Integer, Long>> ff = f.apply(_, __, __);
         Long res = ff.apply("foo").apply('4', 5);
         assertEquals(12l, res.longValue());
         
         Function5<String, String, String, String, Integer, Long> g = new Function5<String,String,String,String,Integer,Long>() {
             @Override
             public Long apply(String t1, String t2, String t3, String t4, Integer t5) {
                 return (long) (t1.length() + t2.length() + t3.length() + t4.length() + t5);
             }
         };
          
         Function2<String, Integer, Function3<String, String, String, Long>> ggg = g.apply(_, __, __, __, _);
         Long res2 = ggg.apply("foo", 2).apply("bar", "baz", "quux");
         assertEquals(15l, res2.longValue());
    }
}
