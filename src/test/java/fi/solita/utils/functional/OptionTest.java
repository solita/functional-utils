package fi.solita.utils.functional;

import static fi.solita.utils.functional.Option.Some;

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
    
    static Apply<String,Integer> strlen = new Transformer<String,Integer>() {
        @Override
        public Integer transform(String source) {
            return source.length();
        }
    };
}
