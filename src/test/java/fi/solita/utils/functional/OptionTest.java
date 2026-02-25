package fi.solita.utils.functional;

import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;
import static org.hamcrest.CoreMatchers.equalTo;
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
import java.util.Iterator;

import org.junit.Test;

@SuppressWarnings("unused")
public class OptionTest {
    
    @Test
    public void fold() {
         Option<String> foo = Some("foo");
         Option<Integer> bar = Some(42);
         
         int slen = foo.fold(strlen, 42);
         int slen2 = (foo.isDefined() ? strlen.apply(foo.get()) : 42);
         
         int i = bar.fold(Function.<Integer>id(), 42);
         int i2 = bar.getOrElse(42);
    }
    
    @Test
    public void of_withValue() {
        Option<String> option = Option.of("foo");
        assertTrue(option.isDefined());
        assertEquals("foo", option.get());
    }
    
    @Test
    public void of_withNull() {
        Option<String> option = Option.of(null);
        assertFalse(option.isDefined());
    }
    
    @Test
    public void Some_containsValue() {
        Option<Integer> option = Some(42);
        assertTrue(option.isDefined());
        assertEquals(Integer.valueOf(42), option.get());
    }
    
    @Test
    public void None_isEmpty() {
        Option<String> option = None();
        assertFalse(option.isDefined());
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void get_onNone_throwsException() {
        None().get();
    }
    
    @Test
    public void get_onSome_returnsValue() {
        assertEquals("foo", Some("foo").get());
    }
    
    @Test
    public void getOrElse_onSome_returnsValue() {
        assertEquals("foo", Some("foo").getOrElse("bar"));
    }
    
    @Test
    public void getOrElse_onNone_returnsDefault() {
        assertEquals("bar", None().getOrElse("bar"));
    }
    
    @Test
    public void map_onSome_transformsValue() {
        Option<Integer> option = Some("foo").map(strlen);
        assertTrue(option.isDefined());
        assertEquals(Integer.valueOf(3), option.get());
    }
    
    @Test
    public void map_onNone_returnsNone() {
        Option<Integer> option = Option.<String>None().map(strlen);
        assertFalse(option.isDefined());
    }
    
    @Test
    public void map_returningNull_createsNone() {
        Option<String> option = Some("foo").map(new Apply<String, String>() {
            @Override
            public String apply(String t) {
                return null;
            }
        });
        assertFalse(option.isDefined());
    }
    
    @Test
    public void filter_onSome_withMatchingPredicate_returnsSome() {
        Option<String> option = Some("foo").filter(new Predicate<String>() {
            @Override
            public boolean accept(String candidate) {
                return candidate.length() == 3;
            }
        });
        assertTrue(option.isDefined());
        assertEquals("foo", option.get());
    }
    
    @Test
    public void filter_onSome_withNonMatchingPredicate_returnsNone() {
        Option<String> option = Some("foo").filter(new Predicate<String>() {
            @Override
            public boolean accept(String candidate) {
                return candidate.length() > 10;
            }
        });
        assertFalse(option.isDefined());
    }
    
    @Test
    public void filter_onNone_returnsNone() {
        Option<String> option = Option.<String>None().filter(new Predicate<String>() {
            @Override
            public boolean accept(String candidate) {
                return true;
            }
        });
        assertFalse(option.isDefined());
    }
    
    @Test
    public void flatMap_onSome_returnsTransformedOption() {
        Option<Integer> option = Some("foo").flatMap(new Apply<String, Option<Integer>>() {
            @Override
            public Option<Integer> apply(String t) {
                return Some(t.length());
            }
        });
        assertTrue(option.isDefined());
        assertEquals(Integer.valueOf(3), option.get());
    }
    
    @Test
    public void flatMap_onSome_returningNone() {
        Option<Integer> option = Some("foo").flatMap(new Apply<String, Option<Integer>>() {
            @Override
            public Option<Integer> apply(String t) {
                return None();
            }
        });
        assertFalse(option.isDefined());
    }
    
    @Test
    public void flatMap_onNone_returnsNone() {
        Option<Integer> option = Option.<String>None().flatMap(new Apply<String, Option<Integer>>() {
            @Override
            public Option<Integer> apply(String t) {
                return Some(t.length());
            }
        });
        assertFalse(option.isDefined());
    }
    
    @Test
    public void cata_onSome_callsIfSome() {
        String result = Some("foo").cata(
            new Apply<String, String>() {
                @Override
                public String apply(String t) {
                    return "some:" + t;
                }
            },
            new ApplyZero<String>() {
                @Override
                public String get() {
                    return "none";
                }
            }
        );
        assertEquals("some:foo", result);
    }
    
    @Test
    public void cata_onNone_callsIfNone() {
        String result = Option.<String>None().cata(
            new Apply<String, String>() {
                @Override
                public String apply(String t) {
                    return "some:" + t;
                }
            },
            new ApplyZero<String>() {
                @Override
                public String get() {
                    return "none";
                }
            }
        );
        assertEquals("none", result);
    }
    
    @Test
    public void fold_onSome_returnsTransformedValue() {
        Integer result = Some("foo").fold(strlen, 0);
        assertEquals(Integer.valueOf(3), result);
    }
    
    @Test
    public void fold_onNone_returnsDefaultValue() {
        Integer result = Option.<String>None().fold(strlen, 0);
        assertEquals(Integer.valueOf(0), result);
    }
    
    @Test
    public void orElse_onSome_returnsValue() {
        String result = Some("foo").orElse(new ApplyZero<String>() {
            @Override
            public String get() {
                return "bar";
            }
        });
        assertEquals("foo", result);
    }
    
    @Test
    public void orElse_onNone_callsSupplier() {
        String result = Option.<String>None().orElse(new ApplyZero<String>() {
            @Override
            public String get() {
                return "bar";
            }
        });
        assertEquals("bar", result);
    }
    
    @Test
    public void isDefined_onSome_returnsTrue() {
        assertTrue(Some("foo").isDefined());
    }
    
    @Test
    public void isDefined_onNone_returnsFalse() {
        assertFalse(None().isDefined());
    }
    
    @Test
    public void iterator_onSome_iteratesOnce() {
        Iterator<String> iter = Some("foo").iterator();
        assertTrue(iter.hasNext());
        assertEquals("foo", iter.next());
        assertFalse(iter.hasNext());
    }
    
    @Test
    public void iterator_onNone_isEmpty() {
        Iterator<String> iter = Option.<String>None().iterator();
        assertFalse(iter.hasNext());
    }
    
    @Test
    public void equals_sameSomeValues() {
        assertEquals(Some("foo"), Some("foo"));
        assertEquals(Some(42), Some(42));
    }
    
    @Test
    public void equals_differentSomeValues() {
        assertFalse(Some("foo").equals(Some("bar")));
        assertFalse(Some(42).equals(Some(43)));
    }
    
    @Test
    public void equals_sameInstance() {
        Option<String> option = Some("foo");
        assertEquals(option, option);
    }
    
    @Test
    public void equals_withNull() {
        assertFalse(Some("foo").equals(null));
    }
    
    @Test
    public void equals_someAndNone() {
        assertFalse(Some("foo").equals(None()));
        assertFalse(None().equals(Some("foo")));
    }
    
    @Test
    public void hashCode_sameSomeValues() {
        assertEquals(Some("foo").hashCode(), Some("foo").hashCode());
        assertEquals(Some(42).hashCode(), Some(42).hashCode());
    }
    
    @Test
    public void toString_onSome() {
        assertEquals("Some(foo)", Some("foo").toString());
        assertEquals("Some(42)", Some(42).toString());
    }
    
    @Test
    public void toString_onNone() {
        assertEquals("None", None().toString());
    }
    
    @Test
    public void serialization_Some() throws Exception {
        Option<String> option = Some("foo");
        assertThat(option, instanceOf(Serializable.class));
        
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bao);
        out.writeObject(option);
        out.close();
        
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
        @SuppressWarnings("unchecked")
        Option<String> deserializedOption = (Option<String>) in.readObject();
        in.close();
        
        assertThat(deserializedOption, instanceOf(Serializable.class));
        assertTrue(deserializedOption.isDefined());
        assertEquals("foo", deserializedOption.get());
        assertEquals(option, deserializedOption);
    }
    
    @Test
    public void serialization_None() throws Exception {
        Option<String> option = None();
        assertThat(option, instanceOf(Serializable.class));
        
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(bao);
        out.writeObject(option);
        out.close();
        
        ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bao.toByteArray()));
        @SuppressWarnings("unchecked")
        Option<String> deserializedOption = (Option<String>) in.readObject();
        in.close();
        
        assertThat(deserializedOption, instanceOf(Serializable.class));
        assertFalse(deserializedOption.isDefined());
    }
    
    static Apply<String,Integer> strlen = new Transformer<String,Integer>() {
        @Override
        public Integer transform(String source) {
            return source.length();
        }
    };
}
