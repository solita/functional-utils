package fi.solita.utils.codegen;

import static fi.solita.utils.codegen.Helpers.element2NestedClasses;
import static fi.solita.utils.codegen.Helpers.getPackageName;
import static fi.solita.utils.codegen.Helpers.nonGeneratedElements;
import static fi.solita.utils.codegen.Helpers.qualifiedName;
import static fi.solita.utils.codegen.Helpers.simpleName;
import static fi.solita.utils.codegen.Helpers.withAnnotation;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.find;
import static fi.solita.utils.functional.Functional.flatMap;
import static fi.solita.utils.functional.Functional.flatten;
import static fi.solita.utils.functional.Functional.isEmpty;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.sequence;
import static fi.solita.utils.functional.Predicates.equalTo;
import static fi.solita.utils.functional.Predicates.matches;
import static fi.solita.utils.functional.Predicates.not;
import static fi.solita.utils.functional.Transformers.prepend;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.FilerException;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import fi.solita.utils.codegen.generators.ConstructorsAsFunctions;
import fi.solita.utils.codegen.generators.Content;
import fi.solita.utils.codegen.generators.InstanceFieldsAsEnum;
import fi.solita.utils.codegen.generators.InstanceFieldsAsFunctions;
import fi.solita.utils.codegen.generators.MethodsAsFunctions;
import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Function1;
import fi.solita.utils.functional.Predicate;
import fi.solita.utils.functional.Predicates;

@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedOptions({"CommonMetadataProcessor." + CommonMetadataProcessor.Options.generatedClassNamePattern,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.includesRegex,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.excludesRegex,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.onlyPublicMembers,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.includesAnnotation,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.excludesAnnotation})
public class CommonMetadataProcessor extends AbstractProcessor {

    public static class Options {
        public static final String generatedClassNamePattern = "generatedClassNamePattern";
        public static final String includesRegex = "includesRegex";
        public static final String excludesRegex = "excludesRegex";
        public static final String onlyPublicMembers = "onlyPublicMembers";
        public static final String includesAnnotation = "includesAnnotation";
        public static final String excludesAnnotation = "excludesAnnotation";
    }
    
    public Map<String, String> options() { return processingEnv.getOptions(); }
    public Pattern includesRegex() { return Pattern.compile(find(options(), getClass().getSimpleName() + "." + Options.includesRegex).getOrElse(".*")); }
    public Pattern excludesRegex() { return Pattern.compile(find(options(), getClass().getSimpleName() + "." + Options.excludesRegex).getOrElse("")); }
    public boolean onlyPublicMembers() { return Boolean.parseBoolean(find(options(), getClass().getSimpleName() + "." + Options.onlyPublicMembers).getOrElse("false")); }
    public String generatedClassNamePattern() { return find(options(), getClass().getSimpleName() + "." + Options.generatedClassNamePattern).getOrElse("{}_"); }
    public String includesAnnotation() { return find(options(), getClass().getSimpleName() + "." + Options.includesAnnotation).getOrElse(""); }
    public String excludesAnnotation() { return find(options(), getClass().getSimpleName() + "." + Options.excludesAnnotation).getOrElse(NoMetadataGeneration.class.getName()); }
    
    public Predicate<Element> elementsToProcess() { return Predicates.<Element>instanceOf(TypeElement.class).and(
                                                           nonGeneratedElements).and(
                                                           (includeAllByAnnotation.or(withAnnotation(includesAnnotation())))).and(
                                                           not(withAnnotation(excludesAnnotation()))).and(
                                                           qualifiedName.andThen(matches(includesRegex()).and(
                                                                                 not(matches(excludesRegex()))))).and(
                                                           simpleName.andThen(not(equalTo("package-info")))); }

    private final Predicate<Element> includeAllByAnnotation = new Predicate<Element>() {
        public boolean accept(Element candidate) {
            return includesAnnotation().isEmpty();
        };
    };
    
    public GeneratorOptions generatorOptions() {
        return new GeneratorOptions() {
          public boolean onlyPublicMembers() {
                return CommonMetadataProcessor.this.onlyPublicMembers();
          }
        };
    }
    
    public List<Function1<TypeElement,Iterable<String>>> generators() {
        return newList(InstanceFieldsAsEnum.instance.apply(generatorOptions()),
                       InstanceFieldsAsFunctions.instance.apply(generatorOptions()),
                       ConstructorsAsFunctions.instance.apply(processingEnv).apply(generatorOptions()),
                       MethodsAsFunctions.instance.apply(processingEnv).apply(generatorOptions()));
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        final List<Function1<TypeElement,Iterable<String>>> generators = generators();
        Predicate<Element> predicate = elementsToProcess();
        for (TypeElement element: (Iterable<TypeElement>)filter(roundEnv.getRootElements(), predicate)) {
            Iterable<String> elemData = flatten(sequence(generators, element));
            Iterable<String> nestedData = flatMap(filter(element2NestedClasses.apply(element), predicate), Content.withNestedClasses.curried().apply(generatedClassNamePattern()).apply(predicate).apply(generators));
            List<String> content = newList(map(concat(elemData, map(nestedData, prepend("    "))), prepend("    ")));
            if (!isEmpty(content)) {
                String genClassName = generatedClassNamePattern().replace("{}", element.getSimpleName().toString());
                try {
                    ClassFileWriter.writeClassFile(getPackageName(element), genClassName, content, getClass(), processingEnv.getFiler());
                } catch (RuntimeException e) {
                    // if file already exists, try appending an underscore
                    if (e.getCause() instanceof FilerException) {
                        ClassFileWriter.writeClassFile(getPackageName(element), genClassName + "_", content, getClass(), processingEnv.getFiler());
                    } else {
                        throw e;
                    }
                }
            }
        }
        return false;
    }

    public static class GeneratorOptions implements InstanceFieldsAsFunctions.Options, MethodsAsFunctions.Options, ConstructorsAsFunctions.Options, InstanceFieldsAsEnum.Options {
        @Override
        public boolean generateMemberNameAccessorForMethods() {
            return true;
        }
        @SuppressWarnings("rawtypes")
        @Override
        public Class<? extends Apply> getClassForInstanceFields() {
            return (Class<? extends Apply>) DefaultMeta.DefaultMeta_.class;
        }
        @SuppressWarnings("rawtypes")
        @Override
        public Class<? extends Apply> getPredicateClassForInstanceFields() {
            return (Class<? extends Apply>) DefaultMeta.DefaultPredicateMeta_.class; 
        }
        @Override
        public List<String> getAdditionalBodyLinesForInstanceFields() {
            return newList();
        }
        @Override
        public boolean generateMemberAccessorForFields() {
            return true;
        }
        @Override
        public boolean generateMemberInitializerForFields() {
            return true;
        }
        @Override
        public boolean generateMemberNameAccessorForFields() {
            return true;
        }
        @SuppressWarnings("rawtypes")
        @Override
        public Class<? extends Apply> getClassForMethods(int argCount) {
            return (Class<? extends Apply>) DefaultMeta.DefaultMethodMeta_.class; 
        }
        @SuppressWarnings("rawtypes")
        @Override
        public Class<? extends Apply> getPredicateClassForMethods() {
            return (Class<? extends Apply>) DefaultMeta.DefaultMethodPredicateMeta_.class; 
        }
        @SuppressWarnings({ "rawtypes", "unchecked" })
        @Override
        public Class<? extends Apply> getClassForConstructors(int argCount) {
            try {
                return (Class<? extends Apply>) Class.forName(DefaultMeta.DefaultConstructorMeta_.class.getName() + "$F" + argCount);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        @Override
        public List<String> getAdditionalBodyLinesForConstructors(ExecutableElement element) {
            return newList();
        }
        @Override
        public List<String> getAdditionalBodyLinesForMethods(ExecutableElement element) {
            return newList();
        }
        @Override
        public boolean makeFieldsPublic() {
            return false;
        }
        @Override
        public boolean generateMemberInitializerForMethods() {
            return true;
        }
        @Override
        public boolean generateMemberAccessorForMethods() {
            return true;
        }
        @Override
        public boolean generateMemberInitializerForConstructors() {
            return true;
        }
        @Override
        public boolean generateMemberAccessorForConstructors() {
            return true;
        }
        @Override
        public boolean onlyPublicMembers() {
              return false;
        }
    }
}
