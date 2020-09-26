package fi.solita.utils.functional;
import static fi.solita.utils.functional.Collections.it;
import static fi.solita.utils.functional.Collections.newArray;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Collections.newMap;
import static fi.solita.utils.functional.Collections.newMutableList;
import static fi.solita.utils.functional.Collections.newMutableMap;
import static fi.solita.utils.functional.Collections.newMutableSet;
import static fi.solita.utils.functional.Collections.newSet;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class CollectionsExamples {
    
    @SuppressWarnings("unused")
    @Test
    public void examples() {
        List<Integer> emptyList = newMutableList();
        Set<String> emptySet = newMutableSet();
        Map<Long, Integer> emptyMap = newMutableMap();
        
        int[] primitiveArray = new int[]{1, 2, 3};
        List<Integer> listFromPrimitives = newList(primitiveArray);
        Set<Integer> setFromPrimitives = newSet(primitiveArray);
        
        Iterable<Integer> iterable = newList(1, 2, 3);
        List<Integer> listFromIterable = newList(iterable);
        Set<Integer> setFromIterable = newSet(iterable);
        
        List<? extends Map.Entry<String, Integer>> entries = newList(Pair.of("foo", 1), Pair.of("bar", 2));
        Map<String, Integer> mapFromIterableEntries = newMap(entries);
        Map<String, Integer> mapFromEntries = newMap(Pair.of("foo", 1), Pair.of("bar", 2));
        
        Character[] arrayFromPrimitives = newArray('a', 'b', 'c');
        char[] primitiveArrayFromObjectArray = newArray(arrayFromPrimitives);
        Character[] objectArrayFromPrimitiveArray = newArray(primitiveArrayFromObjectArray);
        
        Iterable<Character> charSequenceToIterable = it("foo");
    }
}
