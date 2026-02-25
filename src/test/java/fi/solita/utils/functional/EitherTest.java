package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Either.asList;
import static fi.solita.utils.functional.Either.get;
import static fi.solita.utils.functional.Either.left;
import static fi.solita.utils.functional.Either.lefts;
import static fi.solita.utils.functional.Either.right;
import static fi.solita.utils.functional.Either.rights;
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

public class EitherTest {

    @Test
    public void left_createsLeftEither() {
        Either<String, Integer> either = left("foo");
        assertTrue(either.isLeft());
        assertFalse(either.isRight());
        assertTrue(either.left.isDefined());
        assertFalse(either.right.isDefined());
        assertEquals("foo", either.left.get());
    }

    @Test
    public void right_createsRightEither() {
        Either<String, Integer> either = right(42);
        assertFalse(either.isLeft());
        assertTrue(either.isRight());
        assertFalse(either.left.isDefined());
        assertTrue(either.right.isDefined());
        assertEquals(Integer.valueOf(42), either.right.get());
    }

    @Test
    public void get_onLeft_returnsLeftValue() {
        Either<String, String> either = left("foo");
        assertEquals("foo", get(either));
    }

    @Test
    public void get_onRight_returnsRightValue() {
        Either<String, String> either = right("bar");
        assertEquals("bar", get(either));
    }

    @Test
    public void asList_onLeft_returnsListWithLeftValue() {
        Either<String, String> either = left("foo");
        List<String> list = asList(either);
        assertEquals(1, list.size());
        assertEquals("foo", list.get(0));
    }

    @Test
    public void asList_onRight_returnsListWithRightValue() {
        Either<String, String> either = right("bar");
        List<String> list = asList(either);
        assertEquals(1, list.size());
        assertEquals("bar", list.get(0));
    }

    @Test
    public void lefts_extractsAllLeftValues() {
        List<Either<String, Integer>> eithers = newList(
            Either.<String, Integer>left("a"),
            Either.<String, Integer>right(1),
            Either.<String, Integer>left("b"),
            Either.<String, Integer>right(2),
            Either.<String, Integer>left("c")
        );
        
        List<String> leftValues = newList(lefts(eithers));
        assertEquals(3, leftValues.size());
        assertEquals("a", leftValues.get(0));
        assertEquals("b", leftValues.get(1));
        assertEquals("c", leftValues.get(2));
    }

    @Test
    public void rights_extractsAllRightValues() {
        List<Either<String, Integer>> eithers = newList(
            Either.<String, Integer>left("a"),
            Either.<String, Integer>right(1),
            Either.<String, Integer>left("b"),
            Either.<String, Integer>right(2),
            Either.<String, Integer>left("c")
        );
        
        List<Integer> rightValues = newList(rights(eithers));
        assertEquals(2, rightValues.size());
        assertEquals(Integer.valueOf(1), rightValues.get(0));
        assertEquals(Integer.valueOf(2), rightValues.get(1));
    }

    @Test
    public void bimap_onLeft_appliesLeftFunction() {
        Either<String, Integer> either = left("foo");
        Either<Integer, String> result = either.bimap(strlen, intToString);
        
        assertTrue(result.isLeft());
        assertEquals(Integer.valueOf(3), result.left.get());
    }

    @Test
    public void bimap_onRight_appliesRightFunction() {
        Either<String, Integer> either = right(42);
        Either<Integer, String> result = either.bimap(strlen, intToString);
        
        assertTrue(result.isRight());
        assertEquals("42", result.right.get());
    }

    @Test
    public void fold_onLeft_appliesLeftFunction() {
        Either<String, Integer> either = left("foo");
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
                    return "right:" + t;
                }
            }
        );
        assertEquals("left:foo", result);
    }

    @Test
    public void fold_onRight_appliesRightFunction() {
        Either<String, Integer> either = right(42);
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
                    return "right:" + t;
                }
            }
        );
        assertEquals("right:42", result);
    }

    @Test
    public void map_onLeft_returnsLeftUnchanged() {
        Either<String, Integer> either = left("foo");
        Either<String, String> result = either.map(intToString);
        
        assertTrue(result.isLeft());
        assertEquals("foo", result.left.get());
    }

    @Test
    public void map_onRight_transformsRightValue() {
        Either<String, Integer> either = right(42);
        Either<String, String> result = either.map(intToString);
        
        assertTrue(result.isRight());
        assertEquals("42", result.right.get());
    }

    @Test
    public void flatMap_onLeft_returnsLeftUnchanged() {
        Either<String, Integer> either = left("foo");
        Apply<Integer, Either<String, ? extends String>> f = toStringEither;
        Either<String, String> result = either.<String>flatMap(f);
        
        assertTrue(result.isLeft());
        assertEquals("foo", result.left.get());
    }

    @Test
    public void flatMap_onRight_appliesFunction() {
        Either<String, Integer> either = right(42);
        Apply<Integer, Either<String, ? extends String>> f = toValueEither;
        Either<String, String> result = either.<String>flatMap(f);
        
        assertTrue(result.isRight());
        assertEquals("value:42", result.right.get());
    }

    @Test
    public void flatMap_onRight_canReturnLeft() {
        Either<String, Integer> either = right(42);
        Apply<Integer, Either<String, ? extends String>> f = toErrorEither;
        Either<String, String> result = either.<String>flatMap(f);
        
        assertTrue(result.isLeft());
        assertEquals("error", result.left.get());
    }

    @Test
    public void first_onLeft_transformsLeftValue() {
        Either<String, Integer> either = left("foo");
        Either<Integer, Integer> result = either.first(strlen);
        
        assertTrue(result.isLeft());
        assertEquals(Integer.valueOf(3), result.left.get());
    }

    @Test
    public void first_onRight_returnsRightUnchanged() {
        Either<String, Integer> either = right(42);
        Either<Integer, Integer> result = either.first(strlen);
        
        assertTrue(result.isRight());
        assertEquals(Integer.valueOf(42), result.right.get());
    }

    @Test
    public void second_onLeft_returnsLeftUnchanged() {
        Either<String, Integer> either = left("foo");
        Either<String, String> result = either.second(intToString);
        
        assertTrue(result.isLeft());
        assertEquals("foo", result.left.get());
    }

    @Test
    public void second_onRight_transformsRightValue() {
        Either<String, Integer> either = right(42);
        Either<String, String> result = either.second(intToString);
        
        assertTrue(result.isRight());
        assertEquals("42", result.right.get());
    }

    @Test
    public void equals_sameLeftValues_areEqual() {
        Either<String, Integer> either1 = left("foo");
        Either<String, Integer> either2 = left("foo");
        
        assertEquals(either1, either2);
        assertEquals(either1.hashCode(), either2.hashCode());
    }

    @Test
    public void equals_sameRightValues_areEqual() {
        Either<String, Integer> either1 = right(42);
        Either<String, Integer> either2 = right(42);
        
        assertEquals(either1, either2);
        assertEquals(either1.hashCode(), either2.hashCode());
    }

    @Test
    public void equals_differentSides_areNotEqual() {
        Either<String, String> either1 = left("foo");
        Either<String, String> either2 = right("foo");
        
        assertFalse(either1.equals(either2));
    }

    @Test
    public void equals_differentValues_areNotEqual() {
        Either<String, Integer> either1 = left("foo");
        Either<String, Integer> either2 = left("bar");
        
        assertFalse(either1.equals(either2));
    }

    @Test
    public void equals_sameInstance_isEqual() {
        Either<String, Integer> either = left("foo");
        assertTrue(either.equals(either));
    }

    @Test
    public void toString_onLeft_showsLeft() {
        Either<String, Integer> either = left("foo");
        String result = either.toString();
        assertTrue(result.contains("left"));
        assertTrue(result.contains("foo"));
    }

    @Test
    public void toString_onRight_showsRight() {
        Either<String, Integer> either = right(42);
        String result = either.toString();
        assertTrue(result.contains("right"));
        assertTrue(result.contains("42"));
    }

    @Test
    public void serialization_left() throws Exception {
        Either<String, Integer> either = left("foo");
        assertThat(either, instanceOf(Serializable.class));
        
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bao);
        out.writeObject(either);
        out.close();
        
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
        @SuppressWarnings("unchecked")
        Either<String, Integer> deserialized = (Either<String, Integer>) in.readObject();
        in.close();
        
        assertThat(deserialized, instanceOf(Serializable.class));
        assertTrue(deserialized.isLeft());
        assertEquals("foo", deserialized.left.get());
    }

    @Test
    public void serialization_right() throws Exception {
        Either<String, Integer> either = right(42);
        assertThat(either, instanceOf(Serializable.class));
        
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bao);
        out.writeObject(either);
        out.close();
        
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
        @SuppressWarnings("unchecked")
        Either<String, Integer> deserialized = (Either<String, Integer>) in.readObject();
        in.close();
        
        assertThat(deserialized, instanceOf(Serializable.class));
        assertTrue(deserialized.isRight());
        assertEquals(Integer.valueOf(42), deserialized.right.get());
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

    static Apply<Integer, Either<String, ? extends String>> toStringEither = new Apply<Integer, Either<String, ? extends String>>() {
        @Override
        public Either<String, ? extends String> apply(Integer t) {
            return right(t.toString());
        }
    };

    static Apply<Integer, Either<String, ? extends String>> toValueEither = new Apply<Integer, Either<String, ? extends String>>() {
        @Override
        public Either<String, ? extends String> apply(Integer t) {
            return right("value:" + t);
        }
    };

    static Apply<Integer, Either<String, ? extends String>> toErrorEither = new Apply<Integer, Either<String, ? extends String>>() {
        @Override
        public Either<String, ? extends String> apply(Integer t) {
            return left("error");
        }
    };
}
