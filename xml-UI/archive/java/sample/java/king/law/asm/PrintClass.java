package king.law.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

public class PrintClass {
    public static void main(String[] args) throws IOException {
        System.out.println("********************************************");
        System.out.println();
//        ClassReader classReader = new ClassReader("java.lang.String");
        ClassReader classReader = new ClassReader("king.law.asm.dest.ClassA");
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(new PrintWriter(System.out));
        classReader.accept(traceClassVisitor,0);
    }
}
