package fi.solita.utils.functional;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Function._;
import static fi.solita.utils.functional.Function.__;
import static fi.solita.utils.functional.FunctionalImpl.map;

import java.util.Map;

import org.junit.Test;

public class FunctionExamples {
    
    static Transformer<String,Integer> length = new Transformer<String,Integer>() {
        @Override
        public Integer transform(String source) {
            return source.length();
        }
    };
    
    static Function2<Integer,Integer,Integer> mod = new Function2<Integer,Integer,Integer>() {
        @Override
        public Integer apply(Integer modulus, Integer i) {
            return i % modulus;
        }
    };
    
    @SuppressWarnings("unused")
    @Test
    public void examples() {
        // Apply<T,R> is a function from T to R
        Apply<String,Integer> f = length;
        
        // Function0 ia a 0-arg function, Function1 1-arg, Function2 2-arg, etc.
        Function0<Integer> zeroArg = Function.of(42);
        Function1<String,Integer> oneArg = length;
        Function2<Integer, Integer, Integer> twoArg = mod;
        
        int applied = length.apply("foo");
        Iterable<Integer> mappedOverFunction = map(f, newList("a", "aa"));
        
        Function0<Integer> partiallyApplied = length.ap("foo");
        int result = partiallyApplied.apply();
        Function1<Integer, Integer> modulo42 = mod.ap(42);
        
        Function1<Integer, Integer> partiallyApplyFirstParam = mod.apply(42, _);
        Function1<Integer, Integer> partiallyApplySecondParam = mod.apply(_, 84);
        // a function can also be split to two functions, dividing some params
        // to the first one and others to the second one:
        Function1<Integer, Function1<Integer, Integer>> split = mod.apply(__, _);
        
        // function composition, one way or the other
        Function1<String, String> func = Function.id();
        Function1<String, Integer> composed = func.andThen(length);
        Function1<String, Integer> composed2 = length.compose(func);
        
        // multi-param function can be views as a 1-arg function of a Tuple:
        Function2<Integer,Integer,Integer> m = mod;
        Function1<Map.Entry<Integer,Integer>,Integer> tuppled = m.tuppled();
        
        Function2<Integer,Integer,Integer> twoArgFunction = mod;
        Function1<Integer,Function1<Integer,Integer>> curried = twoArgFunction.curried();
    }
}
