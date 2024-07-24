package org.example;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class SimpleJavaIDE {
    public static void main(String[] args) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        boolean compiled = compileJavaSource("YourSourceFile.java", compiler);
        if (compiled) {
            System.out.println("Source code compiled successfully.");
        } else {
            System.out.println("Compilation failed.");
        }
    }

    private static boolean compileJavaSource(String sourceFilePath, JavaCompiler compiler) {
        if (compiler != null) {
            int result = compiler.run(null, null, null, sourceFilePath);
            return result == 0;
        }
        return false;
    }
}
