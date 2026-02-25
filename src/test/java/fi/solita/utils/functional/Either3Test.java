package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Either3.asList;
import static fi.solita.utils.functional.Either3.get;
import static fi.solita.utils.functional.Either3.left;
import static fi.solita.utils.functional.Either3.lefts;
import static fi.solita.utils.functional.Either3.middle;
import static fi.solita.utils.functional.Either3.middles;
import static fi.solita.utils.functional.Either3.right;
import static fi.solita.utils.functional.Either3.rights;
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
import java.util.List;

import org.junit.Test;

public class Either3Test {

    @Test
    public void left_createsLeftEither3() {
        Either3<String, Integer, Boolean> either = left("foo");
        assertTrue(either.isLeft());
        assertFalse(either.isMiddle());
        assertFalse(either.isRight());
        assertTrue(either.left.isDefined());
        assertFalse(either.middle.isDefined());
        assertFalse(either.right.isDefined());
        assertEquals("foo", either.left.get());
    }

    @Test
    public void middle_createsMiddleEither3() {
        Either3<String, Integer, Boolean> either = middle(42);
        assertFalse(either.isLeft());
        assertTrue(either.isMiddle());
        assertFalse(either.isRight());
        assertFalse(either.left.isDefined());
        assertTrue(either.middle.isDefined());
        assertFalse(either.right.isDefined());
        assertEquals(Integer.valueOf(42), either.middle.get());
    }

    @Test
    public void right_createsRightEither3() {
        Either3<String, Integer, Boolean> either = right(true);
        assertFalse(either.isLeft());
        assertFalse(either.isMiddle());
        assertTrue(either.isRight());
        assertFalse(either.left.isDefined());
        assertFalse(either.middle.isDefined());
        assertTrue(either.right.isDefined());
        assertEquals(Boolean.TRUE, either.right.get());
    }

    @Test
    public void get_onLeft_returnsLeftValue() {
        Either3<String, String, String> either = left("foo");
        assertEquals("foo", get(either));
    }

    @Test
    public void get_onMiddle_returnsMiddleValue() {
        Either3<String, String, String> either = middle("bar");
        assertEquals("bar", get(either));
    }

    @Test
    public void get_onRight_returnsRightValue() {
        Either3<String, String, String> either = right("baz");
        assertEquals("baz", get(either));
    }

    @Test
    public void asList_onLeft_returnsListWithLeftValue() {
        Either3<String, String, String> either = left("foo");
        List<String> list = asList(either);
        assertEquals(1, list.size());
        assertEquals("foo", list.get(0));
    }

    @Test
    public void asList_onMiddle_returnsListWithMiddleValue() {
        Either3<String, String, String> either = middle("bar");
        List<String> list = asList(either);
        assertEquals(1, list.size());
        assertEquals("bar", list.get(0));
    }

    @Test
    public void asList_onRight_returnsListWithRightValue() {
        Either3<String, String, String> either = right("baz");
        List<String> list = asList(either);
        assertEquals(1, list.size());
        assertEquals("baz", list.get(0));
    }

    @Test
    public void lefts_extractsAllLeftValues() {
        List<Either3<String, Integer, Boolean>> eithers = newList(
            Either3.<String, Integer, Boolean>left("a"),
            Either3.<String, Integer, Boolean>middle(1),
            Either3.<String, Integer, Boolean>left("b"),
            Either3.<String, Integer, Boolean>right(true),
            Either3.<String, Integer, Boolean>left("c"),
            Either3.<String, Integer, Boolean>middle(2)
        );
        
        List<String> leftValues = newList(lefts(eithers));
        assertEquals(3, leftValues.size());
        assertEquals("a", leftValues.get(0));
        assertEquals("b", leftValues.get(1));
        assertEquals("c", leftValues.get(2));
    }

    @Test
    public void middles_extractsAllMiddleValues() {
        List<Either3<String, Integer, Boolean>> eithers = newList(
            Either3.<String, Integer, Boolean>left("a"),
            Either3.<String, Integer, Boolean>middle(1),
            Either3.<String, Integer, Boolean>left("b"),
            Either3.<String, Integer, Boolean>right(true),
            Either3.<String, Integer, Boolean>middle(2),
            Either3.<String, Integer, Boolean>middle(3)
        );
        
        List<Integer> middleValues = newList(middles(eithers));
        assertEquals(3, middleValues.size());
        assertEquals(Integer.valueOf(1), middleValues.get(0));
        assertEquals(Integer.valueOf(2), middleValues.get(1));
        assertEquals(Integer.valueOf(3), middleValues.get(2));
    }

    @Test
    public void rights_extractsAllRightValues() {
        List<Either3<String, Integer, Boolean>> eithers = newList(
            Either3.<String, Integer, Boolean>left("a"),
            Either3.<String, Integer, Boolean>middle(1),
            Either3.<String, Integer, Boolean>right(true),
            Either3.<String, Integer, Boolean>left("b"),
            Either3.<String, Integer, Boolean>right(false),
            Either3.<String, Integer, Boolean>middle(2)
        );
        
        List<Boolean> rightValues = newList(rights(eithers));
        assertEquals(2, rightValues.size());
        assertEquals(Boolean.TRUE, rightValues.get(0));
        assertEquals(Boolean.FALSE, rightValues.get(1));
    }

    @Test
    public void trimap_onLeft_appliesLeftFunction() {
        Either3<String, Integer, Boolean> either = left("foo");
        Either3<Integer, String, String> result = either.trimap(strlen, intToString, boolToString);
        
        assertTrue(result.isLeft());
        assertEquals(Integer.valueOf(3), result.left.get());
    }

    @Test
    public void trimap_onMiddle_appliesMiddleFunction() {
        Either3<String, Integer, Boolean> either = middle(42);
        Either3<Integer, String, String> result = either.trimap(strlen, intToString, boolToString);
        
        assertTrue(result.isMiddle());
        assertEquals("42", result.middle.get());
    }

    @Test
    public void trimap_onRight_appliesRightFunction() {
        Either3<String, Integer, Boolean> either = right(true);
        Either3<Integer, String, String> result = either.trimap(strlen, intToString, boolToString);
        
        assertTrue(result.isRight());
        assertEquals("true", result.right.get());
    }

    @Test
    public void fold_onLeft_appliesLeftFunction() {
        Either3<String, Integer, Boolean> either = left("foo");
        String result = either.fold(
            new Apply<String, String>() {
                @Override
                public String apply(String t) {
                    return "left:" + t;
                }
            },
            new Apply<Integer, String>() {
                @Override
                public String apply(Integer t) {
                    return "middle:" + t;
                }
            },
            new Apply<Boolean, String>() {
                @Override
                public String apply(Boolean t) {
                    return "right:" + t;
                }
            }
        );
        assertEquals("left:foo", result);
    }

    @Test
    public void fold_onMiddle_appliesMiddleFunction() {
        Either3<String, Integer, Boolean> either = middle(42);
        String result = either.fold(
            new Apply<String, String>() {
                @Override
                public String apply(String t) {
                    return "left:" + t;
                }
            },
            new Apply<Integer, String>() {
                @Override
                public String apply(Integer t) {
                    return "middle:" + t;
                }
            },
            new Apply<Boolean, String>() {
                @Override
                public String apply(Boolean t) {
                    return "right:" + t;
                }
            }
        );
        assertEquals("middle:42", result);
    }

    @Test
    public void fold_onRight_appliesRightFunction() {
        Either3<String, Integer, Boolean> either = right(true);
        String result = either.fold(
            new Apply<String, String>() {
                @Override
                public String apply(String t) {
                    return "left:" + t;
                }
            },
            new Apply<Integer, String>() {
                @Override
                public String apply(Integer t) {
                    return "middle:" + t;
                }
            },
            new Apply<Boolean, String>() {
                @Override
                public String apply(Boolean t) {
                    return "right:" + t;
                }
            }
        );
        assertEquals("right:true", result);
    }

    @Test
    public void first_onLeft_transformsLeftValue() {
        Either3<String, Integer, Boolean> either = left("foo");
        Either3<Integer, Integer, Boolean> result = either.first(strlen);
        
        assertTrue(result.isLeft());
        assertEquals(Integer.valueOf(3), result.left.get());
    }

    @Test
    public void first_onMiddle_returnsMiddleUnchanged() {
        Either3<String, Integer, Boolean> either = middle(42);
        Either3<Integer, Integer, Boolean> result = either.first(strlen);
        
        assertTrue(result.isMiddle());
        assertEquals(Integer.valueOf(42), result.middle.get());
    }

    @Test
    public void first_onRight_returnsRightUnchanged() {
        Either3<String, Integer, Boolean> either = right(true);
        Either3<Integer, Integer, Boolean> result = either.first(strlen);
        
        assertTrue(result.isRight());
        assertEquals(Boolean.TRUE, result.right.get());
    }

    @Test
    public void second_onLeft_returnsLeftUnchanged() {
        Either3<String, Integer, Boolean> either = left("foo");
        Either3<String, String, Boolean> result = either.second(intToString);
        
        assertTrue(result.isLeft());
        assertEquals("foo", result.left.get());
    }

    @Test
    public void second_onMiddle_transformsMiddleValue() {
        Either3<String, Integer, Boolean> either = middle(42);
        Either3<String, String, Boolean> result = either.second(intToString);
        
        assertTrue(result.isMiddle());
        assertEquals("42", result.middle.get());
    }

    @Test
    public void second_onRight_returnsRightUnchanged() {
        Either3<String, Integer, Boolean> either = right(true);
        Either3<String, String, Boolean> result = either.second(intToString);
        
        assertTrue(result.isRight());
        assertEquals(Boolean.TRUE, result.right.get());
    }

    @Test
    public void third_onLeft_returnsLeftUnchanged() {
        Either3<String, Integer, Boolean> either = left("foo");
        Either3<String, Integer, String> result = either.third(boolToString);
        
        assertTrue(result.isLeft());
        assertEquals("foo", result.left.get());
    }

    @Test
    public void third_onMiddle_returnsMiddleUnchanged() {
        Either3<String, Integer, Boolean> either = middle(42);
        Either3<String, Integer, String> result = either.third(boolToString);
        
        assertTrue(result.isMiddle());
        assertEquals(Integer.valueOf(42), result.middle.get());
    }

    @Test
    public void third_onRight_transformsRightValue() {
        Either3<String, Integer, Boolean> either = right(true);
        Either3<String, Integer, String> result = either.third(boolToString);
        
        assertTrue(result.isRight());
        assertEquals("true", result.right.get());
    }

    @Test
    public void equals_sameLeftValues_areEqual() {
        Either3<String, Integer, Boolean> either1 = left("foo");
        Either3<String, Integer, Boolean> either2 = left("foo");
        
        assertEquals(either1, either2);
        assertEquals(either1.hashCode(), either2.hashCode());
    }

    @Test
    public void equals_sameMiddleValues_areEqual() {
        Either3<String, Integer, Boolean> either1 = middle(42);
        Either3<String, Integer, Boolean> either2 = middle(42);
        
        assertEquals(either1, either2);
        assertEquals(either1.hashCode(), either2.hashCode());
    }

    @Test
    public void equals_sameRightValues_areEqual() {
        Either3<String, Integer, Boolean> either1 = right(true);
        Either3<String, Integer, Boolean> either2 = right(true);
        
        assertEquals(either1, either2);
        assertEquals(either1.hashCode(), either2.hashCode());
    }

    @Test
    public void equals_differentSides_areNotEqual() {
        Either3<String, String, String> either1 = left("foo");
        Either3<String, String, String> either2 = middle("foo");
        Either3<String, String, String> either3 = right("foo");
        
        assertFalse(either1.equals(either2));
        assertFalse(either1.equals(either3));
        assertFalse(either2.equals(either3));
    }

    @Test
    public void equals_differentValues_areNotEqual() {
        Either3<String, Integer, Boolean> either1 = left("foo");
        Either3<String, Integer, Boolean> either2 = left("bar");
        
        assertFalse(either1.equals(either2));
    }

    @Test
    public void equals_sameInstance_isEqual() {
        Either3<String, Integer, Boolean> either = left("foo");
        assertTrue(either.equals(either));
    }

    @Test
    public void toString_onLeft_showsLeft() {
        Either3<String, Integer, Boolean> either = left("foo");
        String result = either.toString();
        assertTrue(result.contains("left"));
        assertTrue(result.contains("foo"));
    }

    @Test
    public void toString_onMiddle_showsMiddle() {
        Either3<String, Integer, Boolean> either = middle(42);
        String result = either.toString();
        assertTrue(result.contains("middle"));
        assertTrue(result.contains("42"));
    }

    @Test
    public void toString_onRight_showsRight() {
        Either3<String, Integer, Boolean> either = right(true);
        String result = either.toString();
        assertTrue(result.contains("right"));
        assertTrue(result.contains("true"));
    }

    @Test
    public void serialization_left() throws Exception {
        Either3<String, Integer, Boolean> either = left("foo");
        assertThat(either, instanceOf(Serializable.class));
        
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bao);
        out.writeObject(either);
        out.close();
        
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
        @SuppressWarnings("unchecked")
        Either3<String, Integer, Boolean> deserialized = (Either3<String, Integer, Boolean>) in.readObject();
        in.close();
        
        assertThat(deserialized, instanceOf(Serializable.class));
        assertTrue(deserialized.isLeft());
        assertEquals("foo", deserialized.left.get());
    }

    @Test
    public void serialization_middle() throws Exception {
        Either3<String, Integer, Boolean> either = middle(42);
        assertThat(either, instanceOf(Serializable.class));
        
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bao);
        out.writeObject(either);
        out.close();
        
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
        @SuppressWarnings("unchecked")
        Either3<String, Integer, Boolean> deserialized = (Either3<String, Integer, Boolean>) in.readObject();
        in.close();
        
        assertThat(deserialized, instanceOf(Serializable.class));
        assertTrue(deserialized.isMiddle());
        assertEquals(Integer.valueOf(42), deserialized.middle.get());
    }

    @Test
    public void serialization_right() throws Exception {
        Either3<String, Integer, Boolean> either = right(true);
        assertThat(either, instanceOf(Serializable.class));
        
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bao);
        out.writeObject(either);
        out.close();
        
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
        @SuppressWarnings("unchecked")
        Either3<String, Integer, Boolean> deserialized = (Either3<String, Integer, Boolean>) in.readObject();
        in.close();
        
        assertThat(deserialized, instanceOf(Serializable.class));
        assertTrue(deserialized.isRight());
        assertEquals(Boolean.TRUE, deserialized.right.get());
    }

    // Helper functions for tests
    static Apply<String, Integer> strlen = new Transformer<String, Integer>() {
        @Override
        public Integer transform(String source) {
            return source.length();
        }
    };

    static Apply<Integer, String> intToString = new Transformer<Integer, String>() {
        @Override
        public String transform(Integer source) {
            return source.toString();
        }
    };

    static Apply<Boolean, String> boolToString = new Transformer<Boolean, String>() {
        @Override
        public String transform(Boolean source) {
            return source.toString();
        }
    };
}
