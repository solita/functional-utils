package fi.solita.utils.functional;
import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.emptySet;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newMutableList;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Functional.clamp;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.distinct;
import static fi.solita.utils.functional.Functional.drop;
import static fi.solita.utils.functional.Functional.dropWhile;
import static fi.solita.utils.functional.Functional.flatMap;
import static fi.solita.utils.functional.Functional.flatten;
import static fi.solita.utils.functional.Functional.foreach;
import static fi.solita.utils.functional.Functional.group;
import static fi.solita.utils.functional.Functional.head;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.Functional.rangify;
import static fi.solita.utils.functional.Functional.reduce;
import static fi.solita.utils.functional.Functional.reverse;
import static fi.solita.utils.functional.Functional.size;
import static fi.solita.utils.functional.Functional.span;
import static fi.solita.utils.functional.Functional.split;
import static fi.solita.utils.functional.Functional.take;
import static fi.solita.utils.functional.Functional.takeWhile;
import static fi.solita.utils.functional.Functional.transpose;
import static fi.solita.utils.functional.FunctionalS.range;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

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
    
    @Test
    public void testTranspose3() {
         List<String> row1 = newList("1","2");
         List<String> row2 = newList("3");
         List<List<String>> m = newList(row1, row2);
         
         Iterable<Iterable<String>> t = transpose(m);
         
         assertThat(mkString("", map(toString, m)), equalTo("[1, 2][3]"));
         assertThat(mkString("", map(toString, t)), equalTo("[1, 3]"));
    }
    
    @Test
    public void testTranspose4() {
         List<String> row1 = newList("1");
         List<String> row2 = newList("2", "3");
         List<List<String>> m = newList(row1, row2);
         
         Iterable<Iterable<String>> t = transpose(m);
         
         assertThat(mkString("", map(toString, m)), equalTo("[1][2, 3]"));
         assertThat(mkString("", map(toString, t)), equalTo("[1, 2]"));
    }
    
    
    @Test
    public void testTranspose5() {
         List<String> row1 = newList("1","2");
         List<String> row2 = newList("3", "4", "5");
         List<List<String>> m = newList(row1, row2);
         
         Iterable<Iterable<String>> t = transpose(m);
         
         assertThat(mkString("", map(toString, m)), equalTo("[1, 2][3, 4, 5]"));
         assertThat(mkString("", map(toString, t)), equalTo("[1, 3][2, 4]"));
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
        Iterable<Pair<Integer, String>> a = flatMap(zipWithIndex, Arrays.asList(onceIterable));
        Iterable<Iterable<String>> b = map(new Transformer<Tuple2<Integer,String>,Iterable<String>>() {
            @Override
            public Iterable<String> transform(Tuple2<Integer,String> source) {
                return newList(source._2);
            }
        }, a);
        Iterable<String> c = flatten(b);
        newList(c);
    }
    
    public static final Function1<Iterable<String>, Iterable<Pair<Integer,String>>> zipWithIndex = new Function1<Iterable<String>, Iterable<Pair<Integer,String>>>() {
        @Override
        public Iterable<Pair<Integer, String>> apply(Iterable<String> t) {
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
    public void distinctWorksTwice() {
        Iterable<Integer> xs = distinct(newList(1,2,3,3,2,1,4));
        assertEquals(newList(1,2,3,4), newList(xs));
        assertEquals(newList(1,2,3,4), newList(xs));
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
        assertEquals(newList(1,2,3), newList(concat(newList(1,2,3), newMutableList())));
        assertEquals(newList(1,2,3), newList(concat(newList(1,2), newList(3))));
        assertEquals(newList(1,2,3), newList(concat(newList(1), newList(2,3))));
        assertEquals(newList(1,2,3), newList(concat(newMutableList(), newList(1,2,3))));
        
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
        assertEquals(newList(1,2,3), newList(pair.left()));
        assertEquals(newList(5), newList(pair.right()));
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
    
    @Test
    public void testForeach_apply() {
        foreach(new Apply<Integer, Void>() {
            public Void apply(Integer t) {
                return null;
            }
        }, newList(42));
    }
    
    @Test
    public void testForeach_applyVoid() {
        foreach(new ApplyVoid<Integer>() {
            public void accept(Integer t) {
                return;
            }
        }, newList(42));
    }
    
    @Test
    public void testSplit() {
        assertEquals(Pair.of(1, newMutableList())   , force(split(newList(1))));
        assertEquals(Pair.of(1, newList(2))  , force(split(newList(1,2))));
        assertEquals(Pair.of(1, newList(2,3)), force(split(newList(1,2,3))));
        
        assertEquals(Pair.of(newList(1)  , newMutableList()) , force2(split(1, newList(1))));
        assertEquals(Pair.of(newList(1)  , newList(2)), force2(split(1, newList(1,2))));
        assertEquals(Pair.of(newList(1,2), newList(3)), force2(split(2, newList(1,2,3))));
    }
    
    private static <T> Pair<T,List<T>> force(Pair<T,Iterable<T>> p) {
        return Pair.of(p.left(), newList(p.right()));
    }
    private static <T> Pair<List<T>,List<T>> force2(Pair<Iterable<T>,Iterable<T>> p) {
        return Pair.of(newList(p.left()), newList(p.right()));
    }
    
    @Test
    public void testClamp() {
        assertEquals(Integer.valueOf(1), clamp(1, 1, 1));
        assertEquals(Integer.valueOf(1), clamp(1, 1, 0));
        assertEquals(Integer.valueOf(1), clamp(1, 1, 2));
        assertEquals(Integer.valueOf(10), clamp(10, 20, 1));
        assertEquals(Integer.valueOf(20), clamp(10, 20, 30));
        
        assertEquals(Character.valueOf('c'), clamp('c', 'z', 'a'));
    }
    
    @Test
    public void padLeft() {
        assertEquals("00", Functional.padLeft(2, '0', "").toString());
        assertEquals("0x", Functional.padLeft(2, '0', "x").toString());
        assertEquals("xx", Functional.padLeft(2, '0', "xx").toString());
        assertEquals("xxx", Functional.padLeft(2, '0', "xxx").toString());
    }
    
    @Test
    public void padRight() {
        assertEquals("00", Functional.padRight(2, '0', "").toString());
        assertEquals("x0", Functional.padRight(2, '0', "x").toString());
        assertEquals("xx", Functional.padRight(2, '0', "xx").toString());
        assertEquals("xxx", Functional.padRight(2, '0', "xxx").toString());
    }
    
    @Test
    public void testIntersection() {
        assertEquals(emptySet(), Functional.intersection(Collections.<Integer>emptySet(), newSet(2,3)));
        assertEquals(emptySet(), Functional.intersection(newSet(2,3), Collections.<Integer>emptySet()));
        assertEquals(emptySet(), Functional.intersection(Arrays.asList(Collections.<Integer>emptySet(), newSet(2,3))));
        
        assertEquals(newSet(2), Functional.intersection(newSet(1,2), newSet(2,3)));
        assertEquals(newSet(2), Functional.intersection(Arrays.asList(newSet(1,2), newSet(2,3))));
    }
    
    @Test
    public void testEvery() {
        assertEquals(emptyList(), newList(Functional.every(-1, newList(0,1,2,3,4,5))));
        assertEquals(newList(0), newList(Functional.every(0, newList(0,1,2,3,4,5))));
        assertEquals(newList(0,1,2,3,4,5), newList(Functional.every(1, newList(0,1,2,3,4,5))));
        assertEquals(newList(0,2,4), newList(Functional.every(2, newList(0,1,2,3,4,5))));
        assertEquals(newList(0,3), newList(Functional.every(3, newList(0,1,2,3,4,5))));
        assertEquals(newList(0), newList(Functional.every(6, newList(0,1,2,3,4,5))));
    }
    
    @Test
    public void testOrdered() {
        assertTrue(Functional.ordered(Collections.<Integer>emptyList()));
        assertTrue(Functional.ordered(Collections.<Integer>newList(1)));
        assertTrue(Functional.ordered(Collections.<Integer>newList(1,2,3)));
        
        assertTrue(Functional.ordered(Compare.byNatural(), Collections.<Integer>emptyList()));
        assertTrue(Functional.ordered(Compare.byNatural(), Collections.<Integer>newList(1)));
        assertTrue(Functional.ordered(Compare.byNatural(), Collections.<Integer>newList(1,2,3)));
        
        assertFalse(Functional.ordered(Collections.<Integer>newList(2,1)));
        
        assertFalse(Functional.ordered(Compare.byNatural(), Collections.<Integer>newList(2,1)));
    }
    
    @Test
    public void testSubtract() {
        assertEquals(emptyList(), newList(Functional.subtract(emptyList(), newList(1,2,3))));
        assertEquals(emptyList(), newList(Functional.subtract(newList(1,2,3), newList(1,2,3))));
        assertEquals(newList(1), newList(Functional.subtract(newList(1,2,3), newList(2,3))));
        assertEquals(newList(1,3), newList(Functional.subtract(newList(1,2,3), newList(2))));
        assertEquals(newList(1,2,3), newList(Functional.subtract(newList(1,2,3), emptyList())));
        assertEquals(newList(1,2,3), newList(Functional.subtract(newList(1,2,3), newList(4,5,6))));
    }
    
    @Test
    public void testRemove() {
        assertEquals(emptyList(), newList(Functional.remove(1, emptyList())));
        assertEquals(newList(2,3), newList(Functional.remove(1, newList(1,2,3))));
        assertEquals(newList(1,3), newList(Functional.remove(2, newList(1,2,3))));
        assertEquals(newList(1,2), newList(Functional.remove(3, newList(1,2,3))));
        assertEquals(newList(2,3), newList(Functional.remove(1, newList(1,1,2,3))));
        assertEquals(newList(1,2,3), newList(Functional.remove(4, newList(1,2,3))));
    }
    
    @Test
    public void testFind() {
        assertEquals(Option.None(), Functional.<Integer>find(Predicates.equalTo(1), Collections.<Integer>emptyList()));
        assertEquals(Option.Some(1), Functional.<Integer>find(Predicates.equalTo(1), newList(1,2,3)));
        assertEquals(Option.Some(2), Functional.<Integer>find(Predicates.equalTo(2), newList(1,2,3)));
        assertEquals(Option.Some(3), Functional.<Integer>find(Predicates.equalTo(3), newList(1,2,3)));
        assertEquals(Option.None(), Functional.<Integer>find(Predicates.equalTo(4), newList(1,2,3)));
    }
    
    @Test
    public void testFilter() {
        assertEquals(emptyList(), newList(Functional.<Integer>filter(Predicates.equalTo(1), Collections.<Integer>emptyList())));
        assertEquals(newList(1), newList(Functional.<Integer>filter(Predicates.equalTo(1), newList(1,2,3))));
        assertEquals(newList(2), newList(Functional.<Integer>filter(Predicates.equalTo(2), newList(1,2,3))));
        assertEquals(newList(2,2), newList(Functional.<Integer>filter(Predicates.equalTo(2), newList(1,2,2,3))));
        assertEquals(emptyList(), newList(Functional.<Integer>filter(Predicates.equalTo(4), newList(1,2,3))));
    }
    
    @Test
    public void testHead() {
        assertEquals(Integer.valueOf(1), Functional.head(newList(1,2,3)));
        assertEquals(Integer.valueOf(42), Functional.head(newList(42)));
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testHeadEmpty() {
        Functional.head(emptyList());
    }
    
    @Test
    public void testHeadOption() {
        assertEquals(Option.Some(1), Functional.headOption(newList(1,2,3)));
        assertEquals(Option.Some(42), Functional.headOption(newList(42)));
        assertEquals(Option.None(), Functional.headOption(emptyList()));
    }
    
    @Test
    public void testTail() {
        assertEquals(newList(2,3), newList(Functional.tail(newList(1,2,3))));
        assertEquals(emptyList(), newList(Functional.tail(newList(1))));
    }
    
    @Test
    public void testTailEmpty() {
        assertEquals(emptyList(), newList(Functional.tail(emptyList())));
    }
    
    @Test
    public void testLast() {
        assertEquals(Integer.valueOf(3), Functional.last(newList(1,2,3)));
        assertEquals(Integer.valueOf(42), Functional.last(newList(42)));
    }
    
    @Test(expected = UnsupportedOperationException.class)
    public void testLastEmpty() {
        Functional.last(emptyList());
    }
    
    @Test
    public void testLastOption() {
        assertEquals(Option.Some(3), Functional.lastOption(newList(1,2,3)));
        assertEquals(Option.Some(42), Functional.lastOption(newList(42)));
        assertEquals(Option.None(), Functional.lastOption(emptyList()));
    }
    
    @Test
    public void testInit() {
        assertEquals(newList(1,2), newList(Functional.init(newList(1,2,3))));
        assertEquals(emptyList(), newList(Functional.init(newList(1))));
    }
    
    @Test
    public void testInitEmpty() {
        // init uses take(size-1), which for empty list is take(-1) which returns empty
        assertEquals(emptyList(), newList(Functional.init(emptyList())));
    }
    
    @Test
    public void testTakeLast() {
        assertEquals(emptyList(), newList(Functional.takeLast(0, newList(1,2,3))));
        assertEquals(newList(3), newList(Functional.takeLast(1, newList(1,2,3))));
        assertEquals(newList(2,3), newList(Functional.takeLast(2, newList(1,2,3))));
        assertEquals(newList(1,2,3), newList(Functional.takeLast(3, newList(1,2,3))));
        assertEquals(newList(1,2,3), newList(Functional.takeLast(4, newList(1,2,3))));
    }
    
    @Test
    public void testDropLast() {
        assertEquals(newList(1,2,3), newList(Functional.dropLast(0, newList(1,2,3))));
        assertEquals(newList(1,2), newList(Functional.dropLast(1, newList(1,2,3))));
        assertEquals(newList(1), newList(Functional.dropLast(2, newList(1,2,3))));
        assertEquals(emptyList(), newList(Functional.dropLast(3, newList(1,2,3))));
        assertEquals(emptyList(), newList(Functional.dropLast(4, newList(1,2,3))));
    }
    
    @Test
    public void testPartition() {
        Pair<Iterable<String>, Iterable<Integer>> result = Functional.partition(
            new Apply<Integer, Either<String, Integer>>() {
                public Either<String, Integer> apply(Integer i) {
                    return i % 2 == 0 ? Either.<String,Integer>right(i) : Either.<String,Integer>left(String.valueOf(i));
                }
            },
            newList(1,2,3,4,5)
        );
        assertEquals(newList("1","3","5"), newList(result.left()));
        assertEquals(newList(2,4), newList(result.right()));
    }
    
    @Test
    public void testIsEmpty() {
        assertTrue(Functional.isEmpty(emptyList()));
        assertFalse(Functional.isEmpty(newList(1)));
        assertFalse(Functional.isEmpty(newList(1,2,3)));
    }
    
    @Test
    public void testSize() {
        assertEquals(0, Functional.size(emptyList()));
        assertEquals(1, Functional.size(newList(1)));
        assertEquals(3, Functional.size(newList(1,2,3)));
    }
    
    @Test
    public void testContains() {
        assertFalse(Functional.contains(1, emptyList()));
        assertTrue(Functional.contains(1, newList(1,2,3)));
        assertTrue(Functional.contains(2, newList(1,2,3)));
        assertTrue(Functional.contains(3, newList(1,2,3)));
        assertFalse(Functional.contains(4, newList(1,2,3)));
    }
    
    @Test
    public void testExists() {
        assertFalse(Functional.<Integer>exists(Predicates.equalTo(1), Collections.<Integer>emptyList()));
        assertTrue(Functional.<Integer>exists(Predicates.equalTo(1), newList(1,2,3)));
        assertTrue(Functional.<Integer>exists(Predicates.equalTo(2), newList(1,2,3)));
        assertFalse(Functional.<Integer>exists(Predicates.equalTo(4), newList(1,2,3)));
    }
    
    @Test
    public void testForall() {
        assertTrue(Functional.<Integer>forall(Predicates.equalTo(1), Collections.<Integer>emptyList()));
        assertTrue(Functional.<Integer>forall(Predicates.equalTo(1), newList(1)));
        assertTrue(Functional.<Integer>forall(Predicates.equalTo(1), newList(1,1,1)));
        assertFalse(Functional.<Integer>forall(Predicates.equalTo(1), newList(1,2,3)));
        assertFalse(Functional.<Integer>forall(Predicates.equalTo(2), newList(1,2,3)));
    }
    
    @Test
    public void testCons() {
        assertEquals(newList(0,1,2,3), newList(Functional.cons(0, newList(1,2,3))));
        assertEquals(newList(1), newList(Functional.cons(1, emptyList())));
    }
    
    @Test
    public void testAppend() {
        assertEquals(newList(1,2,3,4), newList(Functional.append(4, newList(1,2,3))));
        assertEquals(newList(1), newList(Functional.append(1, emptyList())));
    }
    
    @Test
    public void testSort() {
        assertTrue(newList(Functional.sort(Collections.<Integer>emptyList())).isEmpty());
        assertEquals(newList(1), newList(Functional.sort(newList(1))));
        assertEquals(newList(1,2,3), newList(Functional.sort(newList(3,1,2))));
        assertEquals(newList(1,2,3), newList(Functional.sort(newList(1,2,3))));
    }
    
    @Test
    public void testSortWithComparator() {
        Comparator<Integer> reverseOrder = new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b.compareTo(a);
            }
        };
        assertEquals(emptyList(), newList(Functional.sort(reverseOrder, Collections.<Integer>emptyList())));
        assertEquals(newList(1), newList(Functional.sort(reverseOrder, newList(1))));
        assertEquals(newList(3,2,1), newList(Functional.sort(reverseOrder, newList(1,2,3))));
    }
    
    @Test
    public void testReduceSemigroup() {
        // Create a simple test class that implements SemiGroup
        class Sum implements SemiGroup<Sum> {
            final int value;
            Sum(int value) { this.value = value; }
            public Sum apply(Map.Entry<? extends Sum, ? extends Sum> t) {
                return new Sum(t.getKey().value + t.getValue().value);
            }
            @Override
            public boolean equals(Object obj) {
                return obj instanceof Sum && ((Sum)obj).value == this.value;
            }
            @Override
            public int hashCode() {
                return value;
            }
        }
        
        assertEquals(Option.None(), Functional.reduce(Collections.<Sum>emptyList()));
        assertEquals(Option.Some(new Sum(1)), Functional.reduce(newList(new Sum(1))));
        assertEquals(Option.Some(new Sum(6)), Functional.reduce(newList(new Sum(1), new Sum(2), new Sum(3))));
    }
    
    @Test
    public void testReduceMonoid() {
        assertEquals(Integer.valueOf(0), Functional.reduce(Monoids.intSum, Collections.<Integer>emptyList()));
        assertEquals(Integer.valueOf(1), Functional.reduce(Monoids.intSum, newList(1)));
        assertEquals(Integer.valueOf(6), Functional.reduce(Monoids.intSum, newList(1,2,3)));
    }
    
    @Test
    public void testFold() {
        ApplyBi<Integer,Integer,Integer> sum = new ApplyBi<Integer,Integer,Integer>() {
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        };
        assertEquals(Option.None(), Functional.fold(Function.of(sum), Collections.<Integer>emptyList()));
        assertEquals(Option.Some(1), Functional.fold(Function.of(sum), newList(1)));
        assertEquals(Option.Some(6), Functional.fold(Function.of(sum), newList(1,2,3)));
    }
    
    @Test
    public void testFoldWithZero() {
        ApplyBi<Integer,Integer,Integer> sum = new ApplyBi<Integer,Integer,Integer>() {
            public Integer apply(Integer a, Integer b) {
                return a + b;
            }
        };
        assertEquals(Integer.valueOf(0), Functional.fold(0, Function.of(sum), Collections.<Integer>emptyList()));
        assertEquals(Integer.valueOf(1), Functional.fold(0, Function.of(sum), newList(1)));
        assertEquals(Integer.valueOf(6), Functional.fold(0, Function.of(sum), newList(1,2,3)));
    }
    
    @Test
    public void testMin() {
        assertEquals(Option.None(), Functional.min(Collections.<Integer>emptyList()));
        assertEquals(Option.Some(1), Functional.min(newList(1)));
        assertEquals(Option.Some(1), Functional.min(newList(3,1,2)));
        assertEquals(Option.Some(1), Functional.min(newList(1,2,3)));
    }
    
    @Test
    public void testMinTwoValues() {
        assertEquals(Integer.valueOf(1), Functional.min(1, 2));
        assertEquals(Integer.valueOf(1), Functional.min(2, 1));
        assertEquals(Integer.valueOf(1), Functional.min(1, 1));
    }
    
    @Test
    public void testMinBy() {
        Comparator<Integer> reverseOrder = new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b.compareTo(a);
            }
        };
        assertEquals(Option.None(), Functional.minBy(reverseOrder, Collections.<Integer>emptyList()));
        assertEquals(Option.Some(1), Functional.minBy(reverseOrder, newList(1)));
        assertEquals(Option.Some(3), Functional.minBy(reverseOrder, newList(3,1,2)));
    }
    
    @Test
    public void testMinByTwoValues() {
        Comparator<Integer> reverseOrder = new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b.compareTo(a);
            }
        };
        assertEquals(Integer.valueOf(2), Functional.minBy(reverseOrder, 1, 2));
        assertEquals(Integer.valueOf(2), Functional.minBy(reverseOrder, 2, 1));
    }
    
    @Test
    public void testMax() {
        assertEquals(Option.None(), Functional.max(Collections.<Integer>emptyList()));
        assertEquals(Option.Some(1), Functional.max(newList(1)));
        assertEquals(Option.Some(3), Functional.max(newList(3,1,2)));
        assertEquals(Option.Some(3), Functional.max(newList(1,2,3)));
    }
    
    @Test
    public void testMaxTwoValues() {
        assertEquals(Integer.valueOf(2), Functional.max(1, 2));
        assertEquals(Integer.valueOf(2), Functional.max(2, 1));
        assertEquals(Integer.valueOf(1), Functional.max(1, 1));
    }
    
    @Test
    public void testMaxBy() {
        Comparator<Integer> reverseOrder = new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b.compareTo(a);
            }
        };
        assertEquals(Option.None(), Functional.maxBy(reverseOrder, Collections.<Integer>emptyList()));
        assertEquals(Option.Some(1), Functional.maxBy(reverseOrder, newList(1)));
        assertEquals(Option.Some(1), Functional.maxBy(reverseOrder, newList(3,1,2)));
    }
    
    @Test
    public void testMaxByTwoValues() {
        Comparator<Integer> reverseOrder = new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                return b.compareTo(a);
            }
        };
        assertEquals(Integer.valueOf(1), Functional.maxBy(reverseOrder, 1, 2));
        assertEquals(Integer.valueOf(1), Functional.maxBy(reverseOrder, 2, 1));
    }
    
    @Test
    public void testZip() {
        assertEquals(emptyList(), newList(Functional.zip(emptyList(), Collections.<Integer>emptyList())));
        assertEquals(newList(Pair.of(1, "a")), newList(Functional.zip(newList(1), newList("a"))));
        assertEquals(newList(Pair.of(1, "a"), Pair.of(2, "b")), newList(Functional.zip(newList(1,2), newList("a","b"))));
        assertEquals(newList(Pair.of(1, "a")), newList(Functional.zip(newList(1), newList("a","b"))));
        assertEquals(newList(Pair.of(1, "a")), newList(Functional.zip(newList(1,2), newList("a"))));
    }
    
    @Test
    public void testZip3() {
        assertEquals(emptyList(), newList(Functional.zip(emptyList(), emptyList(), emptyList())));
        assertEquals(newList(Tuple.of(1, "a", true)), 
                     newList(Functional.zip(newList(1), newList("a"), newList(true))));
        assertEquals(newList(Tuple.of(1, "a", true), Tuple.of(2, "b", false)), 
                     newList(Functional.zip(newList(1,2), newList("a","b"), newList(true,false))));
    }
    
    @Test
    public void testZip4() {
        assertEquals(emptyList(), newList(Functional.zip(emptyList(), emptyList(), emptyList(), emptyList())));
        assertEquals(newList(Tuple.of(1, "a", true, 'x')), 
                     newList(Functional.zip(newList(1), newList("a"), newList(true), newList('x'))));
    }
    
    @Test
    public void testZipWithIndex() {
        assertEquals(emptyList(), newList(Functional.zipWithIndex(emptyList())));
        assertEquals(newList(Pair.of(0, "a")), newList(Functional.zipWithIndex(newList("a"))));
        assertEquals(newList(Pair.of(0, "a"), Pair.of(1, "b"), Pair.of(2, "c")), 
                     newList(Functional.zipWithIndex(newList("a","b","c"))));
    }
    
    @Test
    public void testZipTo() {
        assertEquals(emptyList(), newList(Functional.zipTo(emptyList(), "x")));
        assertEquals(newList(Pair.of(1, "x"), Pair.of(2, "x")), 
                     newList(Functional.zipTo(newList(1,2), "x")));
    }
    
    @Test
    public void testZipToPair() {
        assertEquals(emptyList(), newList(Functional.zipToPair(Collections.<Map.Entry<String,Integer>>emptyList(), true)));
        assertEquals(newList(Tuple.of("a", 1, true), Tuple.of("b", 2, true)), 
                     newList(Functional.zipToPair(newList(Pair.of("a",1), Pair.of("b",2)), true)));
    }
    
    @Test
    public void testUnzip() {
        Pair<Iterable<Integer>, Iterable<String>> result = Functional.unzip(Collections.<Pair<Integer, String>>emptyList());
        assertEquals(emptyList(), newList(result.left()));
        assertEquals(emptyList(), newList(result.right()));
        
        result = Functional.unzip(newList(Pair.of(1, "a"), Pair.of(2, "b")));
        assertEquals(newList(1,2), newList(result.left()));
        assertEquals(newList("a","b"), newList(result.right()));
    }
    
    @Test
    public void testUnzip3() {
        Tuple3<Iterable<Integer>, Iterable<String>, Iterable<Boolean>> result = Functional.unzip3(Collections.<Tuple3<Integer, String, Boolean>>emptyList());
        assertEquals(emptyList(), newList(result._1));
        assertEquals(emptyList(), newList(result._2));
        assertEquals(emptyList(), newList(result._3));
        
        result = Functional.unzip3(newList(Tuple.of(1, "a", true), Tuple.of(2, "b", false)));
        assertEquals(newList(1,2), newList(result._1));
        assertEquals(newList("a","b"), newList(result._2));
        assertEquals(newList(true,false), newList(result._3));
    }
    
    @Test
    public void testSequence() {
        Apply<Integer,String> toString = new Apply<Integer,String>() {
            public String apply(Integer i) {
                return String.valueOf(i);
            }
        };
        Apply<Integer,Integer> double_ = new Apply<Integer,Integer>() {
            public Integer apply(Integer i) {
                return i * 2;
            }
        };
        
        assertEquals(emptyList(), newList(Functional.sequence(42, Collections.<Apply<Integer,?>>emptyList())));
        assertEquals(newList("5"), newList(Functional.sequence(5, newList(toString))));
        assertEquals(newList("5", 10), newList(Functional.sequence(5, newList(toString, double_))));
    }
    
    @Test
    public void testMap2() {
        ApplyBi<Integer,String,String> combine = new ApplyBi<Integer,String,String>() {
            public String apply(Integer i, String s) {
                return s + i;
            }
        };
        assertEquals(Option.None(), Functional.map2(Option.<Integer>None(), Option.Some("a"), combine));
        assertEquals(Option.None(), Functional.map2(Option.Some(1), Option.<String>None(), combine));
        assertEquals(Option.Some("a1"), Functional.map2(Option.Some(1), Option.Some("a"), combine));
    }
    
    @Test
    public void testMap3() {
        Apply3<Integer,String,Boolean,String> combine = new Apply3<Integer,String,Boolean,String>() {
            public String apply(Integer i, String s, Boolean b) {
                return s + i + b;
            }
        };
        assertEquals(Option.None(), Functional.map3(Option.<Integer>None(), Option.Some("a"), Option.Some(true), combine));
        assertEquals(Option.None(), Functional.map3(Option.Some(1), Option.<String>None(), Option.Some(true), combine));
        assertEquals(Option.None(), Functional.map3(Option.Some(1), Option.Some("a"), Option.<Boolean>None(), combine));
        assertEquals(Option.Some("a1true"), Functional.map3(Option.Some(1), Option.Some("a"), Option.Some(true), combine));
    }
    
    @Test
    public void testMap4() {
        Apply4<Integer,String,Boolean,Character,String> combine = new Apply4<Integer,String,Boolean,Character,String>() {
            public String apply(Integer i, String s, Boolean b, Character c) {
                return s + i + b + c;
            }
        };
        assertEquals(Option.None(), Functional.map4(Option.<Integer>None(), Option.Some("a"), Option.Some(true), Option.Some('x'), combine));
        assertEquals(Option.None(), Functional.map4(Option.Some(1), Option.<String>None(), Option.Some(true), Option.Some('x'), combine));
        assertEquals(Option.None(), Functional.map4(Option.Some(1), Option.Some("a"), Option.<Boolean>None(), Option.Some('x'), combine));
        assertEquals(Option.None(), Functional.map4(Option.Some(1), Option.Some("a"), Option.Some(true), Option.<Character>None(), combine));
        assertEquals(Option.Some("a1truex"), Functional.map4(Option.Some(1), Option.Some("a"), Option.Some(true), Option.Some('x'), combine));
    }
}
