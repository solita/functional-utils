package fi.solita.utils.functional;

import static fi.solita.utils.functional.Predicates.between;
import static fi.solita.utils.functional.Predicates.greaterThan;
import static fi.solita.utils.functional.Predicates.greaterThanOrEqualTo;
import static fi.solita.utils.functional.Predicates.lessThan;
import static fi.solita.utils.functional.Predicates.lessThanOrEqualTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

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
    public void testGreaterThan() {
        java.util.function.Predicate<Integer> greaterThan5 = greaterThan(5);
        assertTrue(greaterThan5.test(6));
        assertTrue(greaterThan5.test(10));
        assertFalse(greaterThan5.test(5));
        assertFalse(greaterThan5.test(4));
    }

    @Test
    public void testLessThan() {
        java.util.function.Predicate<Integer> lessThan5 = lessThan(5);
        assertTrue(lessThan5.test(4));
        assertTrue(lessThan5.test(0));
        assertFalse(lessThan5.test(5));
        assertFalse(lessThan5.test(6));
    }

    @Test
    public void testGreaterThanOrEqualTo() {
        java.util.function.Predicate<Integer> gte5 = greaterThanOrEqualTo(5);
        assertTrue(gte5.test(5));
        assertTrue(gte5.test(6));
        assertFalse(gte5.test(4));
    }

    @Test
    public void testLessThanOrEqualTo() {
        java.util.function.Predicate<Integer> lte5 = lessThanOrEqualTo(5);
        assertTrue(lte5.test(5));
        assertTrue(lte5.test(4));
        assertFalse(lte5.test(6));
    }

    @Test
    public void testBetween() {
        java.util.function.Predicate<Integer> between5And10 = between(5, 10);
        assertTrue(between5And10.test(5));
        assertTrue(between5And10.test(7));
        assertTrue(between5And10.test(10));
        assertFalse(between5And10.test(4));
        assertFalse(between5And10.test(11));
    }
}
