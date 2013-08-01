package fi.solita.utils.codegen;

import static fi.solita.utils.codegen.Helpers.element2NestedClasses;
import static fi.solita.utils.codegen.Helpers.getPackageName;
import static fi.solita.utils.codegen.Helpers.nonGeneratedElements;
import static fi.solita.utils.codegen.Helpers.qualifiedName;
import static fi.solita.utils.codegen.Helpers.simpleName;
import static fi.solita.utils.codegen.Helpers.withAnnotation;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.cons;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.find;
import static fi.solita.utils.functional.Functional.flatMap;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.reduce;
import static fi.solita.utils.functional.Functional.repeat;
import static fi.solita.utils.functional.Functional.sequence;
import static fi.solita.utils.functional.Functional.transpose;
import static fi.solita.utils.functional.Functional.zip;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Predicates.equalTo;
import static fi.solita.utils.functional.Predicates.matches;
import static fi.solita.utils.functional.Predicates.not;
import static fi.solita.utils.functional.Transformers.prepend;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic.Kind;

import fi.solita.utils.codegen.generators.ConstructorsAsFunctions;
import fi.solita.utils.codegen.generators.Content;
import fi.solita.utils.codegen.generators.Generator;
import fi.solita.utils.codegen.generators.InstanceFieldsAsEnum;
import fi.solita.utils.codegen.generators.InstanceFieldsAsFunctions;
import fi.solita.utils.codegen.generators.MethodsAsFunctions;
import fi.solita.utils.functional.Apply;
import fi.solita.utils.functional.Function1;
import fi.solita.utils.functional.Monoids;
import fi.solita.utils.functional.Option;
import fi.solita.utils.functional.Pair;
import fi.solita.utils.functional.Predicate;
import fi.solita.utils.functional.Predicates;
import fi.solita.utils.functional.Transformer;
import fi.solita.utils.functional.Tuple2;

@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedOptions({"CommonMetadataProcessor." + CommonMetadataProcessor.Options.enabled,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.generatedClassNamePattern,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.includesRegex,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.excludesRegex,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.onlyPublicMembers,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.includesAnnotation,
                   "CommonMetadataProcessor." + CommonMetadataProcessor.Options.excludesAnnotation})
public class CommonMetadataProcessor<OPTIONS extends CommonMetadataProcessor.CombinedGeneratorOptions> extends AbstractProcessor {

    public static class Options {
        public static final String enabled = "enabled";
        public static final String generatedClassNamePattern = "generatedClassNamePattern";
        public static final String includesRegex = "includesRegex";
        public static final String excludesRegex = "excludesRegex";
        public static final String onlyPublicMembers = "onlyPublicMembers";
        public static final String includesAnnotation = "includesAnnotation";
        public static final String excludesAnnotation = "excludesAnnotation";
    }
    
    public Map<String, String> options()      { return processingEnv.getOptions(); }
    
    protected boolean enabledByDefault() {
        return true;
    }
    
    public boolean enabled()                  { return Boolean.parseBoolean(findOption(Options.enabled, Boolean.toString(enabledByDefault()))); }
    public Pattern includesRegex()            { return Pattern.compile(findOption(Options.includesRegex, ".*")); }
    public Pattern excludesRegex()            { return Pattern.compile(findOption(Options.excludesRegex, "")); }
    public boolean onlyPublicMembers()        { return Boolean.parseBoolean(findOption(Options.onlyPublicMembers, "false")); }
    public String generatedClassNamePattern() { return findOption(Options.generatedClassNamePattern, "{}_"); }
    public String includesAnnotation()        { return findOption(Options.includesAnnotation, ""); }
    public String excludesAnnotation()        { return findOption(Options.excludesAnnotation, NoMetadataGeneration.class.getName()); }
    public Pattern extendClassNamePattern()   { return Pattern.compile("<not enabled>"); }

    public String findOption(String option, String defaultIfNotFound) {
        return find(options(), getClass().getSimpleName() + "." + option).getOrElse(defaultIfNotFound);
    }
    
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
    
    @SuppressWarnings("unchecked")
    public OPTIONS generatorOptions() {
        return (OPTIONS) new CombinedGeneratorOptions() {
          public boolean onlyPublicMembers() {
                return CommonMetadataProcessor.this.onlyPublicMembers();
          }
        };
    }
    
    @SuppressWarnings("unchecked")
    public List<Generator<? super OPTIONS>> generators() {
        return Arrays.<Generator<? super OPTIONS>>asList(InstanceFieldsAsEnum.instance,
                       InstanceFieldsAsFunctions.instance,
                       ConstructorsAsFunctions.instance,
                       MethodsAsFunctions.instance);
    }
    
    static Transformer<Apply<TypeElement,Iterable<String>>, Apply<TypeElement,Pair<Long,List<String>>>> timed = new Transformer<Apply<TypeElement,Iterable<String>>, Apply<TypeElement,Pair<Long,List<String>>>>() {
        @Override
        public Apply<TypeElement, Pair<Long, List<String>>> transform(final Apply<TypeElement, Iterable<String>> source) {
            return new Function1<TypeElement, Pair<Long,List<String>>>() {
                @Override
                public Pair<Long, List<String>> apply(TypeElement t) {
                    long start = System.nanoTime();
                    List<String> result = newList(map(source.apply(t), prepend("    ")));
                    return Pair.of(System.nanoTime() - start, result);
                }
            };
        }
    };
    
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        if (!enabled()) {
            return false;
        }
        
        try {
            return doProcess(annotations, roundEnv);
        } catch (RuntimeException e) {
            processingEnv.getMessager().printMessage(Kind.ERROR, e.toString());
            throw e;
        }
    }
    
    public boolean doProcess(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        List<Apply<TypeElement,Iterable<String>>> generators = newList();
        for (Generator<? super OPTIONS> g: generators()) {
            generators.add(g.ap(processingEnv, generatorOptions()));
        }
        List<Apply<TypeElement, Pair<Long, List<String>>>> generatorsWithTiming = newList(map(generators, timed));
        
        String genClassNamePat = generatedClassNamePattern();
        Filer filer = processingEnv.getFiler();
        
        long started = System.nanoTime();
        long generation = 0;
        long nestedGeneration = 0;
        long fileWriting = 0;
        List<Long> gens = newList(repeat(0l, generators.size()));
        Predicate<Element> predicate = elementsToProcess();
        @SuppressWarnings("unchecked")
        List<TypeElement> elements = newList((Iterable<TypeElement>)filter(roundEnv.getRootElements(), predicate));
        Apply<TypeElement, Pair<List<Long>, List<String>>> nestedDataProducer = Content.withNestedClasses.ap(genClassNamePat, predicate).ap(generatorsWithTiming);
        for (TypeElement element: elements) {
            long time = System.nanoTime();
            List<Pair<Long, List<String>>> elemData = newList(sequence(generatorsWithTiming, element));
            long time2 = System.nanoTime();
            List<Pair<List<Long>, List<String>>> nestedData = newList(map(filter(element2NestedClasses.apply(element), predicate), nestedDataProducer));
            
            Iterable<String> content = map(concat(flatMap(elemData, Helpers.<List<String>>right()), flatMap(nestedData, Helpers.<List<String>>right())), prepend("    "));
            long time3 = System.nanoTime();
            
            String genClassName = genClassNamePat.replace("{}", element.getSimpleName().toString());
            String superclassName = element.getSuperclass().toString().replaceFirst("<.*", "");
            Option<String> extendedClassName = extendClassNamePattern().matcher(superclassName).matches() ? Some(genClassNamePat.replace("{}", superclassName)) : Option.<String>None();
            ClassFileWriter.writeClassFile(getPackageName(element), genClassName, extendedClassName, content, getClass(), filer);
            
            generation += time2 - time;
            nestedGeneration += time3 - time2;
            fileWriting += System.nanoTime() - time3;

            Iterable<Long> contentTimes = map(elemData, Helpers.<Long>left());
            Iterable<List<Long>> nestedTimes = map(nestedData, Helpers.<List<Long>>left());
            List<Long> totalTimesPerGenerator = newList(map(transpose(cons(contentTimes, nestedTimes)), new Transformer<Iterable<Long>,Long>() {
                @Override
                public Long transform(Iterable<Long> source) {
                    return reduce(source, Monoids.longSum);
                }
            }));
            gens = newList(map(zip(gens, totalTimesPerGenerator), new Transformer<Tuple2<Long,Long>,Long>() {
                @Override
                public Long transform(Tuple2<Long, Long> source) {
                    return source._1 + source._2;
                }
            }));
        }
        if (!elements.isEmpty()) {
            processingEnv.getMessager().printMessage(Kind.NOTE, getClass().getName() + " processed " + elements.size() + " elements in " + (System.nanoTime()-started)/1000/1000 + " ms (" + generation/1000/1000 + "/" + nestedGeneration/1000/1000 + "/" + fileWriting/1000/1000 + " ms) (" + newList(map(gens, nanosToMillis)) + " ms)");
        }
        return false;
    }
    
    private static Transformer<Long,Long> nanosToMillis = new Transformer<Long,Long>() {
        @Override
        public Long transform(Long source) {
            return source/1000/1000;
        }
    };

    public static class CombinedGeneratorOptions implements InstanceFieldsAsFunctions.Options, MethodsAsFunctions.Options, ConstructorsAsFunctions.Options, InstanceFieldsAsEnum.Options {
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
