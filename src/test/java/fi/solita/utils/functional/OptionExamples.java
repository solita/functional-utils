package fi.solita.utils.functional;
import static fi.solita.utils.functional.Option.None;
import static fi.solita.utils.functional.Option.Some;

import org.junit.Test;

public class OptionExamples {
    
    @SuppressWarnings("unused")
    @Test
    public void examples() {
        Option<String> optionContainingFoo = Some("foo");
        Option<String> emptyOption = None();
        
        Option<String> valueResultsInSome = Option.of("foo");
        Option<Object> nullResultsInNone = Option.of(null);
        
        String string = optionContainingFoo.get();
        try { 
            emptyOption.get();
        } catch (UnsupportedOperationException e) {}
        
        String foo = optionContainingFoo.getOrElse("bar");
        String bar = emptyOption.getOrElse("bar");
        
        boolean someIsDefined = Some("foo").isDefined();
        boolean noneIsNotDefined = None().isDefined();
        
        for (String str: optionContainingFoo) {
            // executed once
        }
        
        for (String str: emptyOption) {
            // never executed
        }
    }
}
