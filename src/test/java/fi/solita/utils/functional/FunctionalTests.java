package fi.solita.utils.functional;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.drop;
import static fi.solita.utils.functional.Functional.head;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.Functional.range;
import static fi.solita.utils.functional.Functional.reverse;
import static fi.solita.utils.functional.Functional.take;
import static fi.solita.utils.functional.Functional.transpose;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class FunctionalTests {
    
    @Test
    public void testTake() {
        List<Integer> list = newList(1, 2, 3);
        
        assertTrue(newList(take(list, 0)).isEmpty());
        assertThat(newList(take(list, 1)), equalTo(newList(1)));
        assertThat(newList(take(list, 3)), equalTo(list));
        assertThat(newList(take(list, 4)), equalTo(list));
        
        assertThat(newList(take(range(42), 3)), equalTo(newList(42, 43, 44)));
    }
    
    @Test
    public void testDrop() {
        List<Integer> list = newList(1, 2, 3);
        
        assertThat(newList(drop(list, 0)), equalTo(list));
        assertThat(newList(drop(list, 1)), equalTo(newList(2, 3)));
        assertTrue(newList(drop(list, 3)).isEmpty());
        assertTrue(newList(drop(list, 4)).isEmpty());
        
        assertThat(head(drop(range(42), 3)), equalTo(45));
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
         
         assertThat(mkString("", map(m, Transformers.toString)), equalTo("[1, 2][3, 4]"));
         assertThat(mkString("", map(t, Transformers.toString)), equalTo("[1, 3][2, 4]"));
    }
    
    @Test
    public void testTranspose2() {
        List<String> row1 = newList("1","2");
        @SuppressWarnings("unchecked")
        List<List<String>> m = newList((List<String>[])new List[]{row1});
        
        Iterable<Iterable<String>> t = transpose(m);
        
        assertThat(mkString("", map(m, Transformers.toString)), equalTo("[1, 2]"));
        assertThat(mkString("", map(t, Transformers.toString)), equalTo("[1][2]"));
    }
}
