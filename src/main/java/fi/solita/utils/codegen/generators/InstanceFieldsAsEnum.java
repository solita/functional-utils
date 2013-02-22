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

import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import fi.solita.utils.functional.Transformer;

public class InstanceFieldsAsEnum extends Transformer<TypeElement, Iterable<String>> {
    
    public static final InstanceFieldsAsEnum instance = new InstanceFieldsAsEnum();
    
    @Override
    public Iterable<String> transform(TypeElement source) {
        Iterable<VariableElement> fieldsToInclude = filter(element2Fields.apply(source), not(staticElements));
        return concat(
            Some("public enum Fields {"),
            map(fieldsToInclude, simpleName.andThen(prepend("    ")).andThen(append(","))),
            Some("}"),
            Some("")
        );
    }
}