# A more-or-less functional util library for general use. Now with added code generation.

Code generation is highly recommended since it provides kind of poor-mans-first-class-functions in regular java code, thus greatly lessening the pain of using functional constructs. See examples (todo) for more info.

## Installing

> git clone git://github.com/solita/functional-utils.git

and use as a regular java project. Eclipse project/classpath files are included.

...or include as a jar file in the classpath. Bundled pom.xml can be used to package the jar, or just use [the latest ready-made file](functional-utils.jar)

## Code generation

### Eclipse

Create (or open an existing) Java project.

![Add functional-utils as a project dependency (as a jar file or a project)][JavaBuildPath.png]

![Project properties -> Java Compiler -> Annotation Processing][AnnotationProcessing.png]
<ul>
  <li>Enable project specific settings</li>
	<li>Enable annotation processing</li>
</ul>

![Project properties -> Java Compiler -> Annotation Processing -> Factory Path][FactoryPath.png]
<ul>
	<li>Enable project specific settings</li>
	<li>Add JARs... and select functiona-utils.jar</li>
</ul>

Now whenever you save a file the metadata classes are automatically generated and immediately ready for use.

### IntelliJ Idea

TODO: Anyone know how Idea supports Annotation Processors?

### Maven/Gradle/...

Please consult the documentation of your build tool on how to enable and control annotation processing.


## Examples

TODO


## Word of warning

This package comes with no warranty what-so-ever. It's higly experimental, might contain loads of bugs and needs more testing.
Packages and classes may get renamed or moved, and things may suddenly break.
Use at your own risk!

Bug reports, feature requests and opinionated recommendations are highly welcome ;)


## License

Copyright © 2012 Solita Oy

Distributed under the MIT License.