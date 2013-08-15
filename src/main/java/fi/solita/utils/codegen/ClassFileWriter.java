package fi.solita.utils.codegen;

import static fi.solita.utils.functional.Collections.newMap;

import java.io.Serializable;
import java.io.Writer;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Generated;
import javax.annotation.processing.Filer;
import javax.tools.FileObject;

import fi.solita.utils.functional.Option;

public class ClassFileWriter {
    
    private static final Pattern IMPORTS = Pattern.compile(Pattern.quote("{${") + "(([a-zA-Z0-9_$]+\\.)*([a-zA-Z0-9_$]+))" + Pattern.quote("}$}"));
    private static final String SERIALIZABLE = Serializable.class.getSimpleName();
    private static final String GENERATED = Generated.class.getSimpleName();
    private static final String LINE_SEP = System.getProperty("line.separator");
    
    public static void writeClassFile(String packageName, String classSimpleName, Option<String> extendedClassName, Iterable<String> contentLines, Class<?> generator, Filer filer) {
        Map<String,String> toImport = newMap();
        toImport.put(GENERATED, "javax.annotation.Generated");
        toImport.put(SERIALIZABLE, "java.io.Serializable");
        
        StringBuffer content = new StringBuffer();
        for (String line: contentLines) {
            Matcher m = IMPORTS.matcher(line);
            while (m.find()) {
                String qualifiedName = m.group(1);
                String simpleName = m.group(3);
                String alreadyImported = toImport.get(simpleName);
                if (alreadyImported == null || alreadyImported.equals(qualifiedName)) {
                    toImport.put(simpleName, qualifiedName);
                    m.appendReplacement(content, "$3");
                } else {
                    m.appendReplacement(content, "$1");
                }
            }
            m.appendTail(content);
            content.append(LINE_SEP);
        }
        
        try {
            String extend = extendedClassName.isDefined() ? " extends " + extendedClassName.get() : "";
            FileObject fo = filer.createSourceFile((packageName.isEmpty() ? "" : packageName + ".") + classSimpleName);
            Writer pw = fo.openWriter();
            
            if (!packageName.isEmpty()) {
                pw.append("package ")
                  .append(packageName)
                  .append(';')
                  .append(LINE_SEP);
            }
            for (String qualifiedName: toImport.values()) {
                pw.append("import ")
                  .append(qualifiedName)
                  .append(";")
                  .append(LINE_SEP);
            }
            pw.append(LINE_SEP)
              .append('@')
              .append(GENERATED)
              .append("(\"")
              .append(generator.getName())
              .append("\")")
              .append(LINE_SEP)
              .append("public class ")
              .append(classSimpleName)
              .append(extend)
              .append(" implements ")
              .append(SERIALIZABLE)
              .append(" {")
              .append(LINE_SEP)
              .append(LINE_SEP)
              .append(content)
              .append('}');
            
            pw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
