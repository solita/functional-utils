package fi.solita.utils.codegen.generators;

import static fi.solita.utils.codegen.Helpers.getPackageName;
import static fi.solita.utils.codegen.Helpers.padding;
import static fi.solita.utils.codegen.Helpers.publicElement;
import static fi.solita.utils.codegen.Helpers.qualifiedName;
import static fi.solita.utils.codegen.Helpers.simpleName;
import static fi.solita.utils.codegen.Helpers.staticElements;
import static fi.solita.utils.codegen.Helpers.typeParameter2String;
import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.isEmpty;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.FunctionalA.concat;
import static fi.solita.utils.functional.FunctionalImpl.filter;
import static fi.solita.utils.functional.FunctionalImpl.map;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Predicates.not;

import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import fi.solita.utils.codegen.Helpers;
import fi.solita.utils.codegen.Helpers.EnvDependent;
import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Transformer;
import fi.solita.utils.functional.Tuple;
import fi.solita.utils.functional.Tuple3;

public class InstanceFieldsAsTuple extends Generator<InstanceFieldsAsTuple.Options>{
    
    public static final InstanceFieldsAsTuple instance = new InstanceFieldsAsTuple();
    
    public static interface Options extends GeneratorOptions {
        boolean onlyPublicMembers();
        @SuppressWarnings("rawtypes")
        Class<? extends Apply> getClassForInstanceFields(boolean isFinal);
        @SuppressWarnings("rawtypes")
        Class<? extends Apply> getPredicateClassForInstanceFields(boolean isFinal);
    }
    
    @Override
    public Iterable<String> apply(ProcessingEnvironment processingEnv, final Options options, final TypeElement enclosingElement) {
        @SuppressWarnings("unchecked")
        Iterable<VariableElement> elements = (Iterable<VariableElement>) filter(enclosingElement.getEnclosedElements(), Helpers.fields);
        if (options.onlyPublicMembers()) {
            elements = filter(elements, publicElement);
        }
        
        List<VariableElement> fieldsToInclude = newList(filter(elements, not(staticElements)));
        if (fieldsToInclude.isEmpty() || fieldsToInclude.size() > Tuple.class.getDeclaredClasses().length) {
            return emptyList();
        }
        
        final InstanceFieldsAsFunctions.F f = InstanceFieldsAsFunctions.variableElementGen;
        
        final String pack = getPackageName(enclosingElement);
        final String metaClassQualifiedName = pack + "." + qualifiedName.apply(enclosingElement).replaceFirst(Pattern.quote(pack + "."), "").replace(".", "_.") + "_";
        List<String> allTypeParams = newList(map(enclosingElement.getTypeParameters(), typeParameter2String));
        String typeParamsString = isEmpty(allTypeParams) ? "" : "<" + mkString(", ", allTypeParams) + ">";
        String tupleClass = Helpers.importTypes(Tuple.class.getName() + fieldsToInclude.size());
        
        final EnvDependent helper = new Helpers.EnvDependent(processingEnv);
        
        List<Tuple3<String, String, Boolean>> fieldData = newList(map(fieldsToInclude, new Transformer<VariableElement,Tuple3<String,String,Boolean>>() {
            @Override
            public Tuple3<String,String,Boolean> transform(VariableElement field) {
                f.apply((fi.solita.utils.codegen.generators.InstanceFieldsAsFunctions.Options) options, helper, field);
                String relevantTypeParamsString = isEmpty(f.relevantTypeParamsWithoutConstraints) ? "" : "<" + mkString(", ", f.relevantTypeParamsWithoutConstraints) + ">";
                
                return Tuple.of(metaClassQualifiedName + "." + relevantTypeParamsString + simpleName.apply(field), f.fundef, f.needsToBeFunction);
            }
        }));
      
        return concat(
            Some("public static final " + typeParamsString + " " + tupleClass + "<" + mkString(",", map(fieldData, getType)) + ">" + " $Fields() { return " + Helpers.importType(Tuple.class) + ".of("),
            Some(mkString(", ", map(fieldData, getInvokation.andThen(padding)))),
            Some("); }"),
            Some("")
        );
    }
    
    static Transformer<Tuple3<String,String,Boolean>,String> getInvokation = new Transformer<Tuple3<String,String,Boolean>,String>() {
        @Override
        public String transform(Tuple3<String, String, Boolean> source) {
            return source._1 + (source._3 ? "()" : "");
        }
    };
            
    static Transformer<Tuple3<String,String,Boolean>,String> getType = new Transformer<Tuple3<String,String,Boolean>,String>() {
        @Override
        public String transform(Tuple3<String, String, Boolean> source) {
            return source._2;
        }
    };
}