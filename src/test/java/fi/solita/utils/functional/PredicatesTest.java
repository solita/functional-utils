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
}
