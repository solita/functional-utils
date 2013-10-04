package fi.solita.utils.functional;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.sequence;
import static fi.solita.utils.functional.Option.Some;

import org.junit.Test;

public class OptionTest {
    
    @Test
    public void fold() {
         Option<String> foo = Some("foo");
         Option<Integer> bar = Some(42);
         
         int slen = foo.fold(OptionTest_.length, 42);
         int slen2 = (foo.isDefined() ? length(foo.get()) : 42);
         
         int i = bar.fold(Function.<Integer>id(), 42);
         int i2 = bar.getOrElse(42);
         
         Iterable<Integer> intvals = newList(slen, i);
         Iterable<Integer> intvals2 = sequence(newList(Option_.<String,Integer>fold().apply(foo).ap(OptionTest_.length),
                                                       Option_.<Integer,Integer>fold().apply(bar).ap(Function.<Integer>id())), // == Option_.<Integer>getOrElse().apply(bar))
                                               42);
    }
    
    static int length(String s) {
        return s.length();
    }
}
