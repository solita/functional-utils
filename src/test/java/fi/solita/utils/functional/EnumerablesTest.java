package fi.solita.utils.functional;

import static fi.solita.utils.functional.Functional.head;
import static fi.solita.utils.functional.Functional.last;
import static fi.solita.utils.functional.Functional.range;
import static fi.solita.utils.functional.Functional.tail;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EnumerablesTest {

	@Test
	public void testName() {
		  Iterable<Character> asciiAlpha = range(Enumerables.asciiAlpha);
		  
		  assertEquals(Character.valueOf('a'), head(asciiAlpha));
		  assertEquals(Character.valueOf('z'), last(asciiAlpha));
		  assertEquals(Character.valueOf('b'), head(tail(asciiAlpha)));
	}
}
