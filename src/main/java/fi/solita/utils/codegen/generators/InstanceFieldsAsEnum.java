package fi.solita.utils.codegen.generators;

import static fi.solita.utils.codegen.Helpers.element2Fields;
import static fi.solita.utils.codegen.Helpers.padding;
import static fi.solita.utils.codegen.Helpers.simpleName;
import static fi.solita.utils.codegen.Helpers.staticElements;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Predicates.not;
import static fi.solita.utils.functional.Transformers.append;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import fi.solita.utils.functional.Predicate;

public class InstanceFieldsAsEnum extends Generator<InstanceFieldsAsEnum.Options>{
    
    public static final InstanceFieldsAsEnum instance = new InstanceFieldsAsEnum();
    
    public static interface Options extends GeneratorOptions {
        boolean onlyPublicMembers();
    }
    
    @Override
    public Iterable<String> apply(ProcessingEnvironment processingEnv, Options options, TypeElement source) {
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
            map(fieldsToInclude, simpleName.andThen(padding).andThen(append(","))),
            Some("}"),
            Some("")
        );
    }
}