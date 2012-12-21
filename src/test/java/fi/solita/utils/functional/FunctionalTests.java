package fi.solita.utils.functional;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.*;
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
}
