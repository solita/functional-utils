package fi.solita.utils.codegen;

import java.io.OutputStream;
import java.io.PrintWriter;

import javax.annotation.processing.Filer;
import javax.tools.FileObject;

public class ClassFileWriter {

    public static void writeClassFile(String packageName, String classSimpleName, Iterable<String> content, Class<?> generator, Filer filer) {
        try {
            FileObject fo = filer.createSourceFile((packageName.isEmpty() ? "" : packageName + ".") + classSimpleName);
    
            OutputStream os = fo.openOutputStream();
            PrintWriter pw = new PrintWriter(os);
    
            if (!packageName.isEmpty()) {
                pw.println("package " + packageName + ";");
            }
            pw.println();
            pw.println();
    
            pw.println("@" + javax.annotation.Generated.class.getName() + "(\"" + generator.getName() + "\")");
            pw.println("public final class " + classSimpleName + " implements " + java.io.Serializable.class.getName() + " {");
            pw.println();
            for (String line: content) {
                pw.println(line);
            }
            pw.println();
            pw.println("}");
    
            pw.flush();
            pw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
