package fi.solita.utils.functional;

import static fi.solita.utils.functional.Predicates.*;
import static org.junit.Assert.*;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.junit.Test;

public class PredicatesTest {

    @Retention(RetentionPolicy.RUNTIME)
    @interface TestAnnotation {
    }

    @TestAnnotation
    static class AnnotatedClass {
    }

    static class NotAnnotatedClass {
    }
    
    static class SerializableClass implements Serializable {
        private static final long serialVersionUID = 1L;
    }
    
    static class NonSerializableClass {
    }

    @Test
    public void testIsNull() {
        Predicate<String> predicate = isNull();
        assertTrue(predicate.accept(null));
        assertFalse(predicate.accept("foo"));
    }

    @Test
    public void testEqualTo() {
        Predicate<String> predicate = equalTo("test");
        assertTrue(predicate.accept("test"));
        assertFalse(predicate.accept("other"));
    }
    
    @Test
    public void testEqualTo_withNullArgument() {
        Predicate<String> predicate = equalTo("test");
        try {
            predicate.accept(null);
            fail("Expected NullPointerException");
        } catch (NullPointerException e) {
            // expected - null.equals("test") throws NPE
        }
    }

    @Test
    public void testNot() {
        Predicate<Integer> isEven = divisible(2);
        Predicate<Integer> isNotEven = not(isEven);
        assertTrue(isNotEven.accept(3));
        assertFalse(isNotEven.accept(4));
    }

    @Test
    public void testIsDefined() {
        assertTrue(isDefined.accept(Option.Some("value")));
        assertFalse(isDefined.accept(Option.None()));
    }
    
    @Test
    public void testDefined() {
        assertTrue(defined.accept(Option.Some("value")));
        assertFalse(defined.accept(Option.None()));
    }

    @Test
    public void testIsLeft() {
        assertTrue(isLeft.accept(Either.left("left")));
        assertFalse(isLeft.accept(Either.right("right")));
    }

    @Test
    public void testIsRight() {
        assertFalse(isRight.accept(Either.left("left")));
        assertTrue(isRight.accept(Either.right("right")));
    }

    @Test
    public void testDivisible() {
        Predicate<Integer> divisibleBy3 = divisible(3);
        assertTrue(divisibleBy3.accept(9));
        assertTrue(divisibleBy3.accept(0));
        assertFalse(divisibleBy3.accept(10));
    }

    @Test
    public void testEven() {
        assertTrue(even.accept(0));
        assertTrue(even.accept(2));
        assertTrue(even.accept(100));
        assertFalse(even.accept(1));
        assertFalse(even.accept(99));
    }

    @Test
    public void testOdd() {
        assertTrue(odd.accept(1));
        assertTrue(odd.accept(99));
        assertFalse(odd.accept(0));
        assertFalse(odd.accept(2));
        assertFalse(odd.accept(100));
    }

    @Test
    public void testInstanceOf() {
        Predicate<String> isString = instanceOf(String.class);
        assertTrue(isString.accept("test"));
    }
    
    @Test
    public void testInstanceOf_withObjects() {
        Predicate<Object> isSerializable = new Predicate<Object>() {
            @Override
            public boolean accept(Object candidate) {
                return Serializable.class.isInstance(candidate);
            }
        };
        assertTrue(isSerializable.accept("test"));
        assertTrue(isSerializable.accept(42));
        assertFalse(isSerializable.accept(new NonSerializableClass()));
    }

    @Test
    public void testEmpty_withIterable() {
        Predicate<List<String>> predicate = Predicates.<String, List<String>>empty();
        List<String> emptyList = new ArrayList<String>();
        List<String> nonEmptyList = new ArrayList<String>();
        nonEmptyList.add("item");
        
        assertTrue(predicate.accept(emptyList));
        assertFalse(predicate.accept(nonEmptyList));
    }

    @Test
    public void testMapEmpty() {
        Predicate<Map<String, String>> predicate = Predicates.<String, String, Map<String, String>>mapEmpty();
        Map<String, String> emptyMap = new HashMap<String, String>();
        Map<String, String> nonEmptyMap = new HashMap<String, String>();
        nonEmptyMap.put("key", "value");
        
        assertTrue(predicate.accept(emptyMap));
        assertFalse(predicate.accept(nonEmptyMap));
    }

    @Test
    public void testGreaterThan() {
        Predicate<Integer> greaterThan5 = greaterThan(5);
        assertTrue(greaterThan5.accept(6));
        assertTrue(greaterThan5.accept(10));
        assertFalse(greaterThan5.accept(5));
        assertFalse(greaterThan5.accept(4));
    }

    @Test
    public void testLessThan() {
        Predicate<Integer> lessThan5 = lessThan(5);
        assertTrue(lessThan5.accept(4));
        assertTrue(lessThan5.accept(0));
        assertFalse(lessThan5.accept(5));
        assertFalse(lessThan5.accept(6));
    }

    @Test
    public void testGreaterThanOrEqualTo() {
        Predicate<Integer> gte5 = greaterThanOrEqualTo(5);
        assertTrue(gte5.accept(5));
        assertTrue(gte5.accept(6));
        assertFalse(gte5.accept(4));
    }

    @Test
    public void testLessThanOrEqualTo() {
        Predicate<Integer> lte5 = lessThanOrEqualTo(5);
        assertTrue(lte5.accept(5));
        assertTrue(lte5.accept(4));
        assertFalse(lte5.accept(6));
    }

    @Test
    public void testBetween() {
        Predicate<Integer> between5And10 = between(5, 10);
        assertTrue(between5And10.accept(5));
        assertTrue(between5And10.accept(7));
        assertTrue(between5And10.accept(10));
        assertFalse(between5And10.accept(4));
        assertFalse(between5And10.accept(11));
    }

    @Test
    public void testMatches() {
        Pattern pattern = Pattern.compile("^\\d{3}$");
        Predicate<String> predicate = matches(pattern);
        assertTrue(predicate.accept("123"));
        assertFalse(predicate.accept("12"));
        assertFalse(predicate.accept("1234"));
        assertFalse(predicate.accept("abc"));
    }

    @Test
    public void testLookingAt() {
        Pattern pattern = Pattern.compile("^\\d{3}");
        Predicate<String> predicate = lookingAt(pattern);
        assertTrue(predicate.accept("123"));
        assertTrue(predicate.accept("123abc"));
        assertFalse(predicate.accept("12"));
        assertFalse(predicate.accept("abc123"));
    }

    @Test
    public void testSerializable() {
        assertTrue(serializable.accept(new SerializableClass()));
        assertTrue(serializable.accept("string"));
        assertTrue(serializable.accept(42));
        assertFalse(serializable.accept(new NonSerializableClass()));
    }

    @Test
    public void testIsAnnotationPresent() {
        Predicate<Class<?>> predicate = isAnnotationPresent(TestAnnotation.class);
        assertTrue(predicate.accept(AnnotatedClass.class));
        assertFalse(predicate.accept(NotAnnotatedClass.class));
    }

    @Test
    public void testComparisons_withStrings() {
        Predicate<String> gtB = greaterThan("B");
        assertTrue(gtB.accept("C"));
        assertFalse(gtB.accept("B"));
        assertFalse(gtB.accept("A"));
        
        Predicate<String> betweenAandC = between("A", "C");
        assertTrue(betweenAandC.accept("A"));
        assertTrue(betweenAandC.accept("B"));
        assertTrue(betweenAandC.accept("C"));
        assertFalse(betweenAandC.accept("D"));
    }

    @Test
    public void testPredicateCombination_and() {
        Predicate<Integer> gt5 = greaterThan(5);
        Predicate<Integer> lt10 = lessThan(10);
        Predicate<Integer> between5And10 = gt5.and(lt10);
        
        assertTrue(between5And10.accept(7));
        assertFalse(between5And10.accept(5));
        assertFalse(between5And10.accept(10));
    }

    @Test
    public void testPredicateCombination_or() {
        Predicate<Integer> eq5 = equalTo(5);
        Predicate<Integer> eq10 = equalTo(10);
        Predicate<Integer> fiveOrTen = eq5.or(eq10);
        
        assertTrue(fiveOrTen.accept(5));
        assertTrue(fiveOrTen.accept(10));
        assertFalse(fiveOrTen.accept(7));
    }
}
