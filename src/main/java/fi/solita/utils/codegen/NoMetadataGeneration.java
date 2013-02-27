package fi.solita.utils.codegen;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * No metadata is generated for the annotated class .
 * This annotation can also be applied to another annotation, thus skipping
 * generation from classes annotated with the other annotation.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface NoMetadataGeneration {
    String value() default "";
}
