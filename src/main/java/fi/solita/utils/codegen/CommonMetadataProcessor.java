package fi.solita.utils.codegen;

import static fi.solita.utils.codegen.Helpers.element2NestedClasses;
import static fi.solita.utils.codegen.Helpers.getPackageName;
import static fi.solita.utils.codegen.Helpers.nonGeneratedElements;
import static fi.solita.utils.codegen.Helpers.padding;
import static fi.solita.utils.codegen.Helpers.qualifiedName;
import static fi.solita.utils.codegen.Helpers.simpleName;
import static fi.solita.utils.codegen.Helpers.typeArgs;
import static fi.solita.utils.codegen.Helpers.withAnnotation;
import static fi.solita.utils.functional.Collections.emptyList;
import static fi.solita.utils.functional.Collections.newArray;
import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.cons;
import static fi.solita.utils.functional.Functional.filter;
import static fi.solita.utils.functional.Functional.find;
import static fi.solita.utils.functional.Functional.flatMap;
import static fi.solita.utils.functional.Functional.map;
import static fi.solita.utils.functional.Functional.sequence;
import static fi.solita.utils.functional.Functional.transpose;
import static fi.solita.utils.functional.Option.Some;
import static fi.solita.utils.functional.Predicates.equalTo;
import static fi.solita.utils.functional.Predicates.matches;
import static fi.solita.utils.functional.Predicates.not;

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
import fi.solita.utils.functional.Option;
import fi.solita.utils.functional.Pair;
import fi.solita.utils.functional.Predicate;
import fi.solita.utils.functional.Predicates;
import fi.solita.utils.functional.Transformer;

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
        final boolean onlyPublicMembers = CommonMetadataProcessor.this.onlyPublicMembers();
        return (OPTIONS) new CombinedGeneratorOptions() {
          public boolean onlyPublicMembers() {
                return onlyPublicMembers;
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
    
    static final Transformer<Apply<TypeElement,Iterable<String>>, Apply<TypeElement,Pair<Long,List<String>>>> timed = new Transformer<Apply<TypeElement,Iterable<String>>, Apply<TypeElement,Pair<Long,List<String>>>>() {
        @Override
        public Apply<TypeElement, Pair<Long, List<String>>> transform(final Apply<TypeElement, Iterable<String>> source) {
            return new Function1<TypeElement, Pair<Long,List<String>>>() {
                @Override
                public Pair<Long, List<String>> apply(TypeElement t) {
                    long start = System.nanoTime();
                    List<String> result = newList(map(source.apply(t), padding));
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
        Pattern extendClassNamePattern = extendClassNamePattern();
        Filer filer = processingEnv.getFiler();
        Class<?> clazz = getClass();
        
        long started = System.nanoTime();
        long generation = 0;
        long nestedGeneration = 0;
        long rest = 0;
        long fileWriting = 0;
        long[] cumulativeGeneratorTimes = new long[generators.size()];
        Predicate<Element> acceptedElement = elementsToProcess();
        @SuppressWarnings("unchecked")
        List<TypeElement> elementsToProcess = newList((Iterable<TypeElement>)filter(roundEnv.getRootElements(), acceptedElement));
        Apply<TypeElement, Pair<List<Long>, List<String>>> nestedDataProducer = Content.withNestedClasses.ap(genClassNamePat, acceptedElement, generatorsWithTiming);
        for (TypeElement element: elementsToProcess) {
            try {
                long time = System.nanoTime();
                List<Pair<Long, List<String>>> elemData = newList(sequence(generatorsWithTiming, element));
                long time2 = System.nanoTime();
                List<Pair<List<Long>, List<String>>> nestedData = newList(map(filter(element2NestedClasses.apply(element), acceptedElement), nestedDataProducer));
                long time3 = System.nanoTime();
                Iterable<String> content = map(concat(flatMap(elemData, Helpers.<List<String>>right()), flatMap(nestedData, Helpers.<List<String>>right())), padding);
                
                String genClassName = genClassNamePat.replace("{}", element.getSimpleName().toString());
                String superclassName = typeArgs.matcher(element.getSuperclass().toString()).replaceFirst("");
                Option<String> extendedClassName = extendClassNamePattern.matcher(superclassName).matches() ? Some(genClassNamePat.replace("{}", superclassName)) : Option.<String>None();
                long time4 = System.nanoTime();
                ClassFileWriter.writeClassFile(getPackageName(element), genClassName, extendedClassName, content, clazz, filer);
                
                generation += time2 - time;
                nestedGeneration += time3 - time2;
                rest += time4 - time3;
                fileWriting += System.nanoTime() - time4;
    
                Iterable<Long> generatorTimesForContent = map(elemData, Helpers.<Long>left());
                Iterable<List<Long>> generatorTimesForNestedClasses = map(nestedData, Helpers.<List<Long>>left());
                Long[] totalTimesPerGenerator = newArray(Long.class, map(transpose(cons(generatorTimesForContent, generatorTimesForNestedClasses)), Helpers.iterableSum));
                
                for (int i = 0; i < cumulativeGeneratorTimes.length; ++i) {
                    cumulativeGeneratorTimes[i] += totalTimesPerGenerator[i];
                }
            } catch (RuntimeException e) {
                processingEnv.getMessager().printMessage(Kind.ERROR, "Exception while handling: " + element.getQualifiedName());
                throw e;
            }
        }
        if (!elementsToProcess.isEmpty()) {
            processingEnv.getMessager().printMessage(Kind.WARNING, getClass().getName() + " processed " + elementsToProcess.size() + " elements in " + (System.nanoTime()-started)/1000/1000 + " ms (" + generation/1000/1000 + "/" + nestedGeneration/1000/1000 + "/" + rest/1000/1000 + "/" + fileWriting/1000/1000 + " ms) (" + newList(map(newArray(cumulativeGeneratorTimes), nanosToMillis)) + " ms)");
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
        public Class<? extends Apply> getClassForInstanceFields(boolean isFinal) {
            return isFinal ? MetaField.class : MetaField.Property.class;
        }
        @SuppressWarnings("rawtypes")
        @Override
        public Class<? extends Apply> getPredicateClassForInstanceFields(boolean isFinal) {
            return isFinal ? MetaField.Predicate.class : MetaField.PredicateProperty.class; 
        }
        @Override
        public List<String> getAdditionalBodyLinesForInstanceFields() {
            return emptyList();
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
            switch (argCount) {
                case 0: return MetaMethod.F0.class;
                case 1: return MetaMethod.F1.class;
                case 2: return MetaMethod.F2.class;
                case 3: return MetaMethod.F3.class;
                case 4: return MetaMethod.F4.class;
                case 5: return MetaMethod.F5.class;
                case 6: return MetaMethod.F6.class;
                case 7: return MetaMethod.F7.class;
                case 8: return MetaMethod.F8.class;
                case 9: return MetaMethod.F9.class;
                case 10: return MetaMethod.F10.class;
                case 11: return MetaMethod.F11.class;
                case 12: return MetaMethod.F12.class;
                case 13: return MetaMethod.F13.class;
                case 14: return MetaMethod.F14.class;
                case 15: return MetaMethod.F15.class;
                case 16: return MetaMethod.F16.class;
                case 17: return MetaMethod.F17.class;
                case 18: return MetaMethod.F18.class;
                case 19: return MetaMethod.F19.class;
                case 20: return MetaMethod.F20.class;
                case 21: return MetaMethod.F21.class;
                case 22: return MetaMethod.F22.class;
            }
            throw new RuntimeException("Not implemented: F" + argCount);
        }
        @SuppressWarnings("rawtypes")
        @Override
        public Class<? extends Apply> getPredicateClassForMethods() {
            return (Class<? extends Apply>) MetaMethod.Predicate.class; 
        }
        @SuppressWarnings("rawtypes")
        @Override
        public Class<? extends Apply> getClassForConstructors(int argCount) {
            switch (argCount) {
                case 0: return MetaConstructor.F0.class;
                case 1: return MetaConstructor.F1.class;
                case 2: return MetaConstructor.F2.class;
                case 3: return MetaConstructor.F3.class;
                case 4: return MetaConstructor.F4.class;
                case 5: return MetaConstructor.F5.class;
                case 6: return MetaConstructor.F6.class;
                case 7: return MetaConstructor.F7.class;
                case 8: return MetaConstructor.F8.class;
                case 9: return MetaConstructor.F9.class;
                case 10: return MetaConstructor.F10.class;
                case 11: return MetaConstructor.F11.class;
                case 12: return MetaConstructor.F12.class;
                case 13: return MetaConstructor.F13.class;
                case 14: return MetaConstructor.F14.class;
                case 15: return MetaConstructor.F15.class;
                case 16: return MetaConstructor.F16.class;
                case 17: return MetaConstructor.F17.class;
                case 18: return MetaConstructor.F18.class;
                case 19: return MetaConstructor.F19.class;
                case 20: return MetaConstructor.F20.class;
                case 21: return MetaConstructor.F21.class;
                case 22: return MetaConstructor.F22.class;
            }
            throw new RuntimeException("Not implemented: F" + argCount);
        }
        @Override
        public List<String> getAdditionalBodyLinesForConstructors(ExecutableElement element) {
            return emptyList();
        }
        @Override
        public List<String> getAdditionalBodyLinesForMethods(ExecutableElement element) {
            return emptyList();
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
