package fi.solita.utils.functional;
import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Functional.*;
import static fi.solita.utils.functional.Functional.group;
import static fi.solita.utils.functional.FunctionalS.range;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import org.junit.Test;

public class FunctionalTest {
    
    @Test
    public void testTake() {
        List<Integer> list = newList(1, 2, 3);
        
        assertTrue(newList(take(-1, list)).isEmpty());
        assertTrue(newList(take(0, list)).isEmpty());
        assertThat(newList(take(1, list)), equalTo(newList(1)));
        assertThat(newList(take(3, list)), equalTo(list));
        assertThat(newList(take(4, list)), equalTo(list));
        
        assertThat(newList(take(3, range(42))), equalTo(newList(42, 43, 44)));
    }
    
    @Test
    public void testDrop() {
        List<Integer> list = newList(1, 2, 3);
        
        assertThat(newList(drop(-1, list)), equalTo(list));
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
        Iterable<Iterable<String>> b = map(new Transformer<Tuple2<Integer,String>,Iterable<String>>() {
            @Override
            public Iterable<String> transform(Tuple2<Integer,String> source) {
                return newList(source._2);
            }
        }, a);
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
        assertEquals(newList("a"), newList(map(Transformers.toString, group("a"))));
        assertEquals(newList("aaa"), newList(map(Transformers.toString, group("aaa"))));
        assertEquals(newList("M", "i", "ss", "i", "ss", "i", "pp", "i"),
                     newList(map(Transformers.toString, group("Mississippi"))));
    }
    
    private static final Iterable<String> onceIterable = new Iterable<String>() {
        private boolean iterated = false;
        public Iterator<String> iterator() {
            if (iterated) {
                throw new IllegalStateException("trying to iterate again!");
            }
            iterated = true;
            return new Iterator<String>() {
                boolean nextCalled = false;
                public boolean hasNext() {
                    return !nextCalled;
                }

                public String next() {
                    if (nextCalled) {
                        throw new NoSuchElementException();
                    }
                    nextCalled = true;
                    return "foo";
                }

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
    
    @Test
    public void traversalFusionByComposingMonoids() {
        // lista, joka varmistaa että sen voi iteroida vain kerran
        List<Double> list = listOf(1.0, 2.0, 3.0, 4.0);
        
        // tavallisesti lukujen summaamista varten löytyy kirjastoista jokin
        // sum-funktio tähän tyyliin:
        //
        // int summa = sum(list);
        //
        // Jos abstraktiotaso olisi hieman korkeampi, niin tuossa tehtäisiin
        // oikeasti listan redusointi summa-monoidilla.
        
        // näin laskettaisiin siis esim summa ja tulo normaalisti:
        double summa = reduce(Monoids.doubleSum,     newList(list));
        double tulo  = reduce(Monoids.doubleProduct, newList(list));
        
        // lukujen maksimi ei ole monoidi (koska ei järkevää nolla-arvoa)
        // mutta mistä tahansa semigroupista voidaan tehdä monoidi tarjoamalla
        // sopiva nolla-arvo:
        Monoid<Double> maxMonoid = Monoids.of(SemiGroups.<Double>max(), Function.of(Double.MIN_VALUE));
        double maximi = reduce(maxMonoid, newList(list));
        
        // listan pituus ei ole itsessään monoidi, mutta se voidaan laskea
        // monoidina kuvaamalla lista ykkösiksi ja käyttämällä summa-monoidia:
        int pituus = reduce(Monoids.intSum, map(Function.constant(1), newList(list)));
        
        // ja oikein siis menee:
        assertEquals(10.0, summa, 0.1);
        assertEquals(24.0, tulo, 0.1);
        assertEquals(4, pituus);
        assertEquals(4.0, maximi, 0.1);
        
        // entä jos lista on vaikka 2mrd numeroa, ja halutaan siis iteroida
        // vain kerran?
        
        // muunnetaan lista numeroita listaksi Tupleja. Eli siis kukin
        // listan alkio jaetaan moneen osaan kutakin haluttua tapausta varten:
        Iterable<Tuple4<Double, Double, Integer, Double>> jaettu = map(toSuitableTuple /* d -> Tuple.of(d, d, 1, d) */, list);
        
        // nyt tarvitaan monoidi, joka osaa käsitellä nuo tuplet.
        // Monoidien kompositio on myös monoidi (nimeltään product-monoid):
        Monoid<Tuple4<Double,Double,Integer,Double>> productMonoid = Monoids.product(Monoids.doubleSum, Monoids.doubleProduct, Monoids.intSum, maxMonoid);
        
        // tehdään laskenta yhdessä iteraatiossa:
        Tuple4<Double,Double,Integer,Double> sumProductLengthMax = reduce(productMonoid, jaettu);
        
        // ja tarkistus:
        assertEquals(Double.valueOf(10.0), sumProductLengthMax._1);
        assertEquals(Double.valueOf(24.0), sumProductLengthMax._2);
        assertEquals(Integer.valueOf(4),   sumProductLengthMax._3);
        assertEquals(Double.valueOf(4.0),  sumProductLengthMax._4);
    }

    private static final Apply<Double, Tuple4<Double, Double, Integer, Double>> toSuitableTuple = new Apply<Double,Tuple4<Double,Double,Integer,Double>>() {
        public Tuple4<Double, Double, Integer, Double> apply(Double d) {
            return Tuple.of(d, d, 1, d);
        }
    };
    
    private static <T> ArrayList<T> listOf(T... ts) {
        return new ArrayList<T>(Arrays.asList(ts)) {
            private boolean iterated = false;
            public Iterator<T> iterator() {
                if (iterated) {
                    throw new UnsupportedOperationException("Already iterated!");
                }
                iterated = true;
                return super.iterator();
            }
        };
    }
    
    @Test
    public void testConcat() {
        assertEquals(newList(1,2,3), newList(concat(newList(1,2,3), newList())));
        assertEquals(newList(1,2,3), newList(concat(newList(1,2), newList(3))));
        assertEquals(newList(1,2,3), newList(concat(newList(1), newList(2,3))));
        assertEquals(newList(1,2,3), newList(concat(newList(), newList(1,2,3))));
        
        assertEquals(newList(1,2,3,4), newList(concat(concat(newList(1), newList(2)), concat(newList(3), newList(4)))));
    }
    
    @Test
    public void testConcatPerformance() {
        // should be fast, otherwise somethings screwed up
        Iterable<Integer> it = emptyList();
        for (@SuppressWarnings("unused") Integer i: range(0, 100)) {
            it = concat(it, newList(range(0, 5000)));
        }
        newSet(it);
    }
    
    @Test
    public void testSpan() {
        assertEquals(newList(1,2,3), newList(takeWhile(Predicates.not(Predicates.equalTo(5)), newList(1,2,3,5))));
        assertEquals(newList(5), newList(dropWhile(Predicates.not(Predicates.equalTo(5)), newList(1,2,3,5))));
        
        Pair<Iterable<Integer>,Iterable<Integer>> pair = span(Predicates.not(Predicates.equalTo(5)), newList(1,2,3,5));
        assertEquals(newList(1,2,3), newList(pair.left));
        assertEquals(newList(5), newList(pair.right));
    }
    
    @Test
    public void testRangify1() {
        assertEquals(newList(newList(1,3), newList(5)), newList(rangify(Enumerables.ints, newList(1,2,3,5))));
    }
    
    @Test
    public void testRangify2() {
        assertEquals(newList((Object)newList(1,4)), newList(rangify(Enumerables.ints, newList(1,2,3,4))));
    }
    
    @Test
    public void testRangify3() {
        assertEquals(emptyList(), newList(rangify(Enumerables.ints, Collections.<Integer>emptyList())));
    }
    
    @Test
    public void testRangify4() {
        assertEquals(newList(newList(1),newList(1)), newList(rangify(Enumerables.ints, newList(1,1))));
    }
    
    @Test
    public void testRangify5() {
        assertEquals(newList(newList(4,5),newList(1)), newList(rangify(Enumerables.ints, newList(4,5,1))));
    }
    
    @Test
    public void flattenIgnoresNulls() {
        assertEquals(newList("foo"), newList(flatten(newList((List<String>)null, newList("foo")))));
    }
}
