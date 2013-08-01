package fi.solita.utils.codegen.generators;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.TypeElement;

import fi.solita.utils.functional.Function3;

public abstract class Generator<O extends GeneratorOptions> extends Function3<ProcessingEnvironment, O, TypeElement, Iterable<String>> {
}
