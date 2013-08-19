package fi.solita.utils.codegen;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;

@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedOptions({"CommonMetadataProcessor3." + CommonMetadataProcessor.Options.enabled,
                   "CommonMetadataProcessor3." + CommonMetadataProcessor.Options.generatedClassNamePattern,
                   "CommonMetadataProcessor3." + CommonMetadataProcessor.Options.generatedPackagePattern,
                   "CommonMetadataProcessor3." + CommonMetadataProcessor.Options.includesRegex,
                   "CommonMetadataProcessor3." + CommonMetadataProcessor.Options.excludesRegex,
                   "CommonMetadataProcessor3." + CommonMetadataProcessor.Options.onlyPublicMembers,
                   "CommonMetadataProcessor3." + CommonMetadataProcessor.Options.includesAnnotation,
                   "CommonMetadataProcessor3." + CommonMetadataProcessor.Options.excludesAnnotation})
public class CommonMetadataProcessor3 extends CommonMetadataProcessor<CommonMetadataProcessor.CombinedGeneratorOptions> {
    @Override
    protected boolean enabledByDefault() {
        return false;
    }
}
