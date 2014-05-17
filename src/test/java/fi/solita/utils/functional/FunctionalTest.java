package fi.solita.utils.functional;
import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.distinct;
import static fi.solita.utils.functional.Functional.drop;
import static fi.solita.utils.functional.Functional.flatMap;
import static fi.solita.utils.functional.Functional.flatten;
import static fi.solita.utils.functional.Functional.group;
import static fi.solita.utils.functional.Functional.head;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.Functional.reverse;
import static fi.solita.utils.functional.Functional.size;
import static fi.solita.utils.functional.Functional.take;
import static fi.solita.utils.functional.Functional.transpose;
import static fi.solita.utils.functional.FunctionalC.group;
import static fi.solita.utils.functional.FunctionalS.range;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

public class FunctionalTest {
    
    @Test
    public void testTake() {
        List<Integer> list = newList(1, 2, 3);
        
        assertTrue(newList(take(0, list)).isEmpty());
        assertThat(newList(take(1, list)), equalTo(newList(1)));
        assertThat(newList(take(3, list)), equalTo(list));
        assertThat(newList(take(4, list)), equalTo(list));
        
        assertThat(newList(take(3, range(42))), equalTo(newList(42, 43, 44)));
    }
    
    @Test
    public void testDrop() {
        List<Integer> list = newList(1, 2, 3);
        
        assertThat(newList(drop(0, list)), equalTo(list));
        assertThat(newList(drop(1, list)), equalTo(newList(2, 3)));
        assertTrue(newList(drop(3, list)).isEmpty());
        assertTrue(newList(drop(4, list)).isEmpty());
        
        assertThat(head(drop(3, range(42))), equalTo(45));
    }

    @Test
    public void testReverse() {
        List<Integer> original = newList(1, 2, 3);
        List<Integer> reversed = newList(reverse(original));
        
        assertThat(reversed, equalTo(newList(3, 2, 1)));
        assertThat(reversed, not(equalTo(original)));
    }
    
    @Test
    public void testTranspose() {
         List<String> row1 = newList("1","2");
         List<String> row2 = newList("3","4");
         List<List<String>> m = newList(row1, row2);
         
         Iterable<Iterable<String>> t = transpose(m);
         
         assertThat(mkString("", map(toString, m)), equalTo("[1, 2][3, 4]"));
         assertThat(mkString("", map(toString, t)), equalTo("[1, 3][2, 4]"));
    }
    
    @Test
    public void testTranspose2() {
        List<String> row1 = newList("1","2");
        @SuppressWarnings("unchecked")
        List<List<String>> m = newList((List<String>[])new List[]{row1});
        
        Iterable<Iterable<String>> t = transpose(m);
        
        assertThat(mkString("", map(toString, m)), equalTo("[1, 2]"));
        assertThat(mkString("", map(toString, t)), equalTo("[1][2]"));
    }
    
    private static final Transformer<Object,String> toString = new Transformer<Object,String>() {
        @Override
        public String transform(Object source) {
            return source.toString();
        }
    };
    
    @Test
    public void testRange() {
        assertThat(size(range(1, 2)), equalTo(2l));
    }
    
    @Test
    public void testName() {
        @SuppressWarnings("unchecked")
        Iterable<Tuple2<Integer, String>> a = flatMap(zipWithIndex, Arrays.asList(onceIterable));
        Iterable<Iterable<String>> b = map(a, new Transformer<Tuple2<Integer,String>,Iterable<String>>() {
            @Override
            public Iterable<String> transform(Tuple2<Integer,String> source) {
                return newList(source._2);
            }
        });
        Iterable<String> c = flatten(b);
        newList(c);
    }
    
    public static final Function1<Iterable<String>, Iterable<Tuple2<Integer,String>>> zipWithIndex = new Function1<Iterable<String>, Iterable<Tuple2<Integer,String>>>() {
        @Override
        public Iterable<Tuple2<Integer, String>> apply(Iterable<String> t) {
            return Functional.zipWithIndex(t);
        }
    };
    
    @Test
    public void testGrouped() {
        assertEquals(emptyList(), newList(group(emptyList())));
        assertEquals(newList("a"), newList(map(group("a"), Transformers.toString)));
        assertEquals(newList("aaa"), newList(map(group("aaa"), Transformers.toString)));
        assertEquals(newList("M", "i", "ss", "i", "ss", "i", "pp", "i"),
                     newList(map(group("Mississippi"), Transformers.toString)));
    }
    
    private static final Iterable<String> onceIterable = new Iterable<String>() {
        private boolean iterated = false;
        @Override
        public Iterator<String> iterator() {
            if (iterated) {
                throw new IllegalStateException("trying to iterate again!");
            }
            iterated = true;
            return new Iterator<String>() {
                boolean nextCalled = false;
                @Override
                public boolean hasNext() {
                    return !nextCalled;
                }

                @Override
                public String next() {
                    if (nextCalled) {
                        throw new NoSuchElementException();
                    }
                    nextCalled = true;
                    return "foo";
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
                
            };
        }
    };
    
    @Test
    public void testDistinct() {
        assertEquals(newList(1,2,3,4), newList(distinct(newList(1,2,3,3,2,1,4))));
    }
}
