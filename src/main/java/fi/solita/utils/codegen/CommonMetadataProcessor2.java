package fi.solita.utils.codegen;

import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedOptions;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;

@SupportedAnnotationTypes("*")
@SupportedSourceVersion(SourceVersion.RELEASE_6)
@SupportedOptions({"CommonMetadataProcessor2." + CommonMetadataProcessor.Options.enabled,
                   "CommonMetadataProcessor2." + CommonMetadataProcessor.Options.generatedClassNamePattern,
                   "CommonMetadataProcessor2." + CommonMetadataProcessor.Options.generatedPackagePattern,
                   "CommonMetadataProcessor2." + CommonMetadataProcessor.Options.includesRegex,
                   "CommonMetadataProcessor2." + CommonMetadataProcessor.Options.excludesRegex,
                   "CommonMetadataProcessor2." + CommonMetadataProcessor.Options.onlyPublicMembers,
                   "CommonMetadataProcessor2." + CommonMetadataProcessor.Options.includesAnnotation,
                   "CommonMetadataProcessor2." + CommonMetadataProcessor.Options.excludesAnnotation})
public class CommonMetadataProcessor2 extends CommonMetadataProcessor<CommonMetadataProcessor.CombinedGeneratorOptions> {
    @Override
    protected boolean enabledByDefault() {
        return false;
    }
}
