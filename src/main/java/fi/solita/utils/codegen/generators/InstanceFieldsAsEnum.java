package fi.solita.utils.codegen.generators;

import static fi.solita.utils.codegen.Helpers.element2Fields;
import static fi.solita.utils.codegen.Helpers.padding;
import static fi.solita.utils.codegen.Helpers.publicElement;
import static fi.solita.utils.codegen.Helpers.simpleName;
import static fi.solita.utils.codegen.Helpers.staticElements;
import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.FunctionalA.concat;
import static fi.solita.utils.functional.FunctionalImpl.filter;
import static fi.solita.utils.functional.FunctionalImpl.map;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Predicates.not;
import static fi.solita.utils.functional.Transformers.append;

import java.util.List;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

public class InstanceFieldsAsEnum extends Generator<InstanceFieldsAsEnum.Options>{
    
    public static final InstanceFieldsAsEnum instance = new InstanceFieldsAsEnum();
    
    public static interface Options extends GeneratorOptions {
        boolean onlyPublicMembers();
    }
    
    @Override
    public Iterable<String> apply(ProcessingEnvironment processingEnv, Options options, TypeElement source) {
        Iterable<VariableElement> elements = element2Fields.apply(source);
        if (options.onlyPublicMembers()) {
            elements = filter(elements, publicElement);
        }
      
        List<VariableElement> fieldsToInclude = newList(filter(elements, not(staticElements)));
        if (fieldsToInclude.isEmpty()) {
            return emptyList();
        }
        return concat(
            Some("public enum $Fields {"),
            map(fieldsToInclude, simpleName.andThen(padding).andThen(append(","))),
            Some("}"),
            Some("")
        );
    }
}