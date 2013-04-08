package fi.solita.utils.codegen.generators;

import static fi.solita.utils.codegen.Helpers.element2Fields;
import static fi.solita.utils.codegen.Helpers.simpleName;
import static fi.solita.utils.codegen.Helpers.staticElements;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Predicates.not;
import static fi.solita.utils.functional.Transformers.append;
import static fi.solita.utils.functional.Transformers.prepend;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Function1;
import fi.solita.utils.functional.Function2;
import fi.solita.utils.functional.Function3;
import fi.solita.utils.functional.Predicate;
import fi.solita.utils.functional.Transformer;

public class InstanceFieldsAsEnum extends Function2<InstanceFieldsAsEnum.Options, TypeElement, Iterable<String>> {
    
    public static final Function1<Options, Function1<TypeElement, Iterable<String>>> instance = new InstanceFieldsAsEnum().curried();
    
    public static interface Options {
        boolean onlyPublicMembers();
    }
    
    @Override
    public Iterable<String> apply(Options options, TypeElement source) {
        Iterable<VariableElement> elements = element2Fields.apply(source);
        if (options.onlyPublicMembers()) {
            elements = filter(elements, new Predicate<Element>() {
                @Override
                public boolean accept(Element candidate) {
                    return candidate.getModifiers().contains(Modifier.PUBLIC);
                }
            });
        }
      
        Iterable<VariableElement> fieldsToInclude = filter(elements, not(staticElements));
        return concat(
            Some("public enum Fields {"),
            map(fieldsToInclude, simpleName.andThen(prepend("    ")).andThen(append(","))),
            Some("}"),
            Some("")
        );
    }
}