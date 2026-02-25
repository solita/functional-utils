package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newMap;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Collections.newSortedMap;
import static fi.solita.utils.functional.FunctionalM.filter;
import static fi.solita.utils.functional.FunctionalM.find;
import static fi.solita.utils.functional.FunctionalM.findBy;
import static fi.solita.utils.functional.FunctionalM.flatten;
import static fi.solita.utils.functional.FunctionalM.flatten2;
import static fi.solita.utils.functional.FunctionalM.flatten3;
import static fi.solita.utils.functional.FunctionalM.groupBy;
import static fi.solita.utils.functional.FunctionalM.groupByFirst;
import static fi.solita.utils.functional.FunctionalM.map;
import static fi.solita.utils.functional.FunctionalM.mapKey;
import static fi.solita.utils.functional.FunctionalM.mapValue;
import static fi.solita.utils.functional.FunctionalM.mapValueList;
import static fi.solita.utils.functional.FunctionalM.mapValueSet;
import static fi.solita.utils.functional.FunctionalM.mapValues;
import static fi.solita.utils.functional.FunctionalM.with;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

import org.junit.Test;

public class FunctionalMTest {

    @Test
    public void testFind_existingKey() {
        Map<String, Integer> map = newMap(Pair.of("a", 1), Pair.of("b", 2), Pair.of("c", 3));
        
        Option<Integer> result = find("b", map);
        
        assertTrue(result.isDefined());
        assertThat(result.get(), equalTo(2));
    }
    
    @Test
    public void testFind_nonExistingKey() {
        Map<String, Integer> map = newMap(Pair.of("a", 1), Pair.of("b", 2));
        
        Option<Integer> result = find("z", map);
        
        assertFalse(result.isDefined());
    }
    
    @Test
    public void testFind_emptyMap() {
        Map<String, Integer> map = newMap(SemiGroups.<Integer>fail(), Collections.<Map.Entry<String, Integer>>emptyList());
        
        Option<Integer> result = find("a", map);
        
        assertFalse(result.isDefined());
    }
    
    @Test
    public void testFindBy() {
        Map<String, Integer> map = newMap(
            Pair.of("apple", 1),
            Pair.of("banana", 2),
            Pair.of("apricot", 3),
            Pair.of("cherry", 4)
        );
        
        Iterable<Integer> result = findBy(new Apply<String, Boolean>() {
            @Override
            public Boolean apply(String key) {
                return key.startsWith("a");
            }
        }, map);
        
        // Since newMap doesn't guarantee order, we check contains instead
        List<Integer> resultList = newList(result);
        assertTrue(resultList.contains(1));
        assertTrue(resultList.contains(3));
        assertThat(resultList.size(), equalTo(2));
    }
    
    @Test
    public void testFindBy_noMatch() {
        Map<String, Integer> map = newMap(Pair.of("a", 1), Pair.of("b", 2));
        
        Iterable<Integer> result = findBy(new Apply<String, Boolean>() {
            @Override
            public Boolean apply(String key) {
                return key.startsWith("z");
            }
        }, map);
        
        assertTrue(newList(result).isEmpty());
    }
    
    @Test
    public void testFilter() {
        Map<String, Integer> map = newMap(
            Pair.of("a", 1),
            Pair.of("b", 2),
            Pair.of("c", 3),
            Pair.of("d", 4)
        );
        
        Map<String, Integer> result = filter(new Apply<Map.Entry<String, Integer>, Boolean>() {
            @Override
            public Boolean apply(Map.Entry<String, Integer> entry) {
                return entry.getValue() % 2 == 0;
            }
        }, map);
        
        assertThat(result.size(), equalTo(2));
        assertThat(result.get("b"), equalTo(2));
        assertThat(result.get("d"), equalTo(4));
    }
    
    @Test
    public void testMap_transformEntries() {
        Map<String, Integer> map = newMap(Pair.of("a", 1), Pair.of("b", 2));
        
        Map<String, Integer> result = map(SemiGroups.<Integer>fail(), new Apply<Map.Entry<String, Integer>, Map.Entry<String, Integer>>() {
            @Override
            public Map.Entry<String, Integer> apply(Map.Entry<String, Integer> entry) {
                return Pair.of(entry.getKey().toUpperCase(), entry.getValue() * 10);
            }
        }, map);
        
        assertThat(result.get("A"), equalTo(10));
        assertThat(result.get("B"), equalTo(20));
    }
    
    @Test
    public void testMap_withKeyAndValueTransformers() {
        Map<String, Integer> map = newMap(Pair.of("a", 1), Pair.of("b", 2));
        
        Map<String, String> result = map(SemiGroups.<String>fail(), 
            new Apply<String, String>() {
                @Override
                public String apply(String key) {
                    return key.toUpperCase();
                }
            },
            new Apply<Integer, String>() {
                @Override
                public String apply(Integer value) {
                    return String.valueOf(value * 10);
                }
            }, 
            map);
        
        assertThat(result.get("A"), equalTo("10"));
        assertThat(result.get("B"), equalTo("20"));
    }
    
    @Test
    public void testMap_withDuplicateKeys() {
        Map<String, Integer> map = newMap(Pair.of("a", 1), Pair.of("b", 2), Pair.of("c", 3));
        
        // Transform keys so "b" and "c" both become "X"
        Map<String, Integer> result = map(SemiGroups.intSum, 
            new Apply<String, String>() {
                @Override
                public String apply(String key) {
                    return key.equals("a") ? "A" : "X";
                }
            },
            new Apply<Integer, Integer>() {
                @Override
                public Integer apply(Integer value) {
                    return value;
                }
            }, 
            map);
        
        assertThat(result.get("A"), equalTo(1));
        assertThat(result.get("X"), equalTo(5)); // 2 + 3
    }
    
    @Test
    public void testMapSortedMap() {
        SortedMap<String, List<Integer>> map = newSortedMap(SemiGroups.<List<Integer>>fail(), 
            String.CASE_INSENSITIVE_ORDER,
            newList(
                Pair.of("a", newList(1, 2, 3)),
                Pair.of("b", newList(4, 5, 6))
            )
        );
        
        SortedMap<String, Iterable<Integer>> result = map(new Apply<Integer, Integer>() {
            @Override
            public Integer apply(Integer val) {
                return val * 10;
            }
        }, map);
        
        assertThat(newList(result.get("a")), equalTo(newList(10, 20, 30)));
        assertThat(newList(result.get("b")), equalTo(newList(40, 50, 60)));
    }
    
    @Test
    public void testMapValues() {
        Map<String, List<Integer>> map = newMap(
            Pair.of("a", newList(1, 2, 3)),
            Pair.of("b", newList(4, 5, 6))
        );
        
        Map<String, Iterable<Integer>> result = mapValues(new Apply<Integer, Integer>() {
            @Override
            public Integer apply(Integer val) {
                return val * 2;
            }
        }, map);
        
        assertThat(newList(result.get("a")), equalTo(newList(2, 4, 6)));
        assertThat(newList(result.get("b")), equalTo(newList(8, 10, 12)));
    }
    
    @Test
    public void testMapValues_null() {
        Map<String, Iterable<Integer>> result = mapValues(new Apply<Integer, Integer>() {
            @Override
            public Integer apply(Integer val) {
                return val * 2;
            }
        }, null);
        
        assertThat(result, equalTo(null));
    }
    
    @Test
    public void testMapValueList() {
        Map<String, List<Integer>> map = newMap(
            Pair.of("a", newList(1, 2, 3)),
            Pair.of("b", newList(4, 5))
        );
        
        Map<String, List<String>> result = mapValueList(new Apply<Integer, String>() {
            @Override
            public String apply(Integer val) {
                return "v" + val;
            }
        }, map);
        
        assertThat(result.get("a"), equalTo(newList("v1", "v2", "v3")));
        assertThat(result.get("b"), equalTo(newList("v4", "v5")));
    }
    
    @Test
    public void testMapValueSet() {
        Map<String, List<Integer>> map = newMap(
            Pair.of("a", newList(1, 2, 1, 2)),
            Pair.of("b", newList(3, 4, 3))
        );
        
        Map<String, Set<String>> result = mapValueSet(new Apply<Integer, String>() {
            @Override
            public String apply(Integer val) {
                return "v" + val;
            }
        }, map);
        
        assertThat(result.get("a"), equalTo(newSet("v1", "v2")));
        assertThat(result.get("b"), equalTo(newSet("v3", "v4")));
    }
    
    @Test
    public void testMapKey() {
        Map<String, Integer> map = newMap(Pair.of("a", 1), Pair.of("b", 2));
        
        Map<String, Integer> result = mapKey(SemiGroups.<Integer>fail(), new Apply<String, String>() {
            @Override
            public String apply(String key) {
                return key.toUpperCase();
            }
        }, map);
        
        assertThat(result.get("A"), equalTo(1));
        assertThat(result.get("B"), equalTo(2));
    }
    
    @Test
    public void testMapKey_withCombiner() {
        Map<String, Integer> map = newMap(Pair.of("a", 1), Pair.of("b", 2), Pair.of("c", 3));
        
        Map<String, Integer> result = mapKey(SemiGroups.intSum, new Apply<String, String>() {
            @Override
            public String apply(String key) {
                return "KEY";  // All keys map to same value
            }
        }, map);
        
        // All keys map to "KEY", so values should be combined
        assertThat(result.get("KEY"), equalTo(6)); // 1 + 2 + 3
    }
    
    @Test
    public void testMapValue() {
        Map<String, Integer> map = newMap(Pair.of("a", 1), Pair.of("b", 2));
        
        Map<String, String> result = mapValue(new Apply<Integer, String>() {
            @Override
            public String apply(Integer val) {
                return "value-" + val;
            }
        }, map);
        
        assertThat(result.get("a"), equalTo("value-1"));
        assertThat(result.get("b"), equalTo("value-2"));
    }
    
    @Test
    public void testFlatten() {
        Map<String, List<Integer>> map = newMap(
            Pair.of("a", newList(1, 2)),
            Pair.of("b", newList(3, 4, 5))
        );
        
        Iterable<Pair<String, Integer>> result = flatten(map);
        
        List<Pair<String, Integer>> list = newList(result);
        assertThat(list.size(), equalTo(5));
        // Check that all expected pairs are present (order may vary)
        assertTrue(list.contains(Pair.of("a", 1)));
        assertTrue(list.contains(Pair.of("a", 2)));
        assertTrue(list.contains(Pair.of("b", 3)));
        assertTrue(list.contains(Pair.of("b", 4)));
        assertTrue(list.contains(Pair.of("b", 5)));
    }
    
    @Test
    public void testFlatten_null() {
        Iterable<Pair<String, Integer>> result = flatten(null);
        
        assertThat(result, equalTo(null));
    }
    
    @Test
    public void testFlatten2() {
        Map<String, List<Pair<Integer, String>>> map = newMap(
            Pair.of("a", newList(Pair.of(1, "x"), Pair.of(2, "y"))),
            Pair.of("b", newList(Pair.of(3, "z")))
        );
        
        Iterable<Tuple3<String, Integer, String>> result = flatten2(map);
        
        List<Tuple3<String, Integer, String>> list = newList(result);
        assertThat(list.size(), equalTo(3));
        assertTrue(list.contains(Tuple.of("a", 1, "x")));
        assertTrue(list.contains(Tuple.of("a", 2, "y")));
        assertTrue(list.contains(Tuple.of("b", 3, "z")));
    }
    
    @Test
    public void testFlatten3() {
        Map<String, List<Tuple3<Integer, String, Boolean>>> map = newMap(
            Pair.of("a", newList(Tuple.of(1, "x", true), Tuple.of(2, "y", false))),
            Pair.of("b", newList(Tuple.of(3, "z", true)))
        );
        
        Iterable<Tuple4<String, Integer, String, Boolean>> result = flatten3(map);
        
        List<Tuple4<String, Integer, String, Boolean>> list = newList(result);
        assertThat(list.size(), equalTo(3));
        assertTrue(list.contains(Tuple.of("a", 1, "x", true)));
        assertTrue(list.contains(Tuple.of("a", 2, "y", false)));
        assertTrue(list.contains(Tuple.of("b", 3, "z", true)));
    }
    
    @Test
    public void testGroupBy() {
        List<String> items = newList("apple", "apricot", "banana", "blueberry", "cherry");
        
        Map<Character, List<String>> result = groupBy(new Apply<String, Character>() {
            @Override
            public Character apply(String str) {
                return str.charAt(0);
            }
        }, items);
        
        assertThat(result.get('a'), equalTo(newList("apple", "apricot")));
        assertThat(result.get('b'), equalTo(newList("banana", "blueberry")));
        assertThat(result.get('c'), equalTo(newList("cherry")));
    }
    
    @Test
    public void testGroupBy_withKeyAndValue() {
        List<String> items = newList("apple", "apricot", "banana");
        
        Map<Character, List<Integer>> result = groupBy(
            new Apply<String, Character>() {
                @Override
                public Character apply(String str) {
                    return str.charAt(0);
                }
            },
            new Apply<String, Integer>() {
                @Override
                public Integer apply(String str) {
                    return str.length();
                }
            },
            items
        );
        
        assertThat(result.get('a'), equalTo(newList(5, 7))); // "apple".length(), "apricot".length()
        assertThat(result.get('b'), equalTo(newList(6))); // "banana".length()
    }
    
    @Test
    public void testGroupByFirst() {
        List<Pair<String, Integer>> items = newList(
            Pair.of("a", 1),
            Pair.of("b", 2),
            Pair.of("a", 3)
        );
        
        Map<String, List<Tuple1<Integer>>> result = groupByFirst(items);
        
        assertThat(result.get("a").size(), equalTo(2));
        assertThat(result.get("a").get(0)._1, equalTo(1));
        assertThat(result.get("a").get(1)._1, equalTo(3));
        assertThat(result.get("b").size(), equalTo(1));
        assertThat(result.get("b").get(0)._1, equalTo(2));
    }
    
    @Test
    public void testGroupByFirst_withCombiner() {
        List<Pair<String, Integer>> items = newList(
            Pair.of("a", 1),
            Pair.of("b", 2),
            Pair.of("a", 3),
            Pair.of("b", 4)
        );
        
        // Create a SemiGroup for Tuple1<Integer> that sums the wrapped values
        SemiGroup<Tuple1<Integer>> tuple1Sum = new SemiGroup<Tuple1<Integer>>() {
            @Override
            public Tuple1<Integer> apply(Map.Entry<? extends Tuple1<Integer>, ? extends Tuple1<Integer>> t) {
                return Tuple.of(t.getKey()._1 + t.getValue()._1);
            }
        };
        
        Map<String, Tuple1<Integer>> result = groupByFirst(tuple1Sum, items);
        
        assertThat(result.get("a")._1, equalTo(4)); // 1 + 3
        assertThat(result.get("b")._1, equalTo(6)); // 2 + 4
    }
    
    @Test
    public void testGroupByFirst_withTuple3() {
        List<Tuple3<String, Integer, Boolean>> items = newList(
            Tuple.of("a", 1, true),
            Tuple.of("b", 2, false),
            Tuple.of("a", 3, true)
        );
        
        Map<String, List<Pair<Integer, Boolean>>> result = groupByFirst(items);
        
        assertThat(result.get("a").size(), equalTo(2));
        assertThat(result.get("a").get(0), equalTo(Pair.of(1, true)));
        assertThat(result.get("a").get(1), equalTo(Pair.of(3, true)));
        assertThat(result.get("b").get(0), equalTo(Pair.of(2, false)));
    }
    
    @Test
    public void testWith() {
        Map<String, Integer> map = newMap(Pair.of("a", 1), Pair.of("b", 2));
        
        Map<String, Integer> result = with(SemiGroups.<Integer>fail(), "c", 3, map);
        
        assertThat(result.size(), equalTo(3));
        assertThat(result.get("a"), equalTo(1));
        assertThat(result.get("b"), equalTo(2));
        assertThat(result.get("c"), equalTo(3));
    }
    
    @Test
    public void testWith_existingKey() {
        Map<String, Integer> map = newMap(Pair.of("a", 1), Pair.of("b", 2));
        
        Map<String, Integer> result = with(SemiGroups.intSum, "a", 10, map);
        
        assertThat(result.size(), equalTo(2));
        assertThat(result.get("a"), equalTo(11)); // 1 + 10
        assertThat(result.get("b"), equalTo(2));
    }
    
    @Test
    public void testWith_null() {
        Map<String, Integer> result = with(SemiGroups.<Integer>fail(), "a", 1, null);
        
        assertThat(result, equalTo(null));
    }
}
