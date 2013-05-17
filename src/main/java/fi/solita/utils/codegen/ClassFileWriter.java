package fi.solita.utils.codegen;

import static fi.solita.utils.functional.Collections.newList;
import static fi.solita.utils.functional.Functional.concat;
import static fi.solita.utils.functional.Functional.unlines;

import java.io.Writer;

import javax.annotation.processing.Filer;
import javax.tools.FileObject;

public class ClassFileWriter {

    public static void writeClassFile(String packageName, String classSimpleName, Iterable<String> content, Class<?> generator, Filer filer) {
        try {
            FileObject fo = filer.createSourceFile((packageName.isEmpty() ? "" : packageName + ".") + classSimpleName);
            Writer pw = fo.openWriter();
            
            if (!packageName.isEmpty()) {
                pw.append("package " + packageName + ";" + System.getProperty("line.separator"));
            }
            pw.append(unlines(concat(
                newList(
                    "",
                    "",
                    "@" + javax.annotation.Generated.class.getName() + "(\"" + generator.getName() + "\")",
                    "public final class " + classSimpleName + " implements " + java.io.Serializable.class.getName() + " {",
                    ""
                ),
                content,
                newList(
                    "",
                    "}"
                )
            )));
            
            pw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
