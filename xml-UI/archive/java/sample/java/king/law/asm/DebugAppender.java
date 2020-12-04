package king.law.asm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import org.objectweb.asm.util.TraceClassVisitor;

public class DebugAppender implements Opcodes {

    public static final String CLASS_FILE_NAME = "./target/classes/king/law/asm/DebugAppender$Target.class";

    public static class Target {

        private String object = null;

        public Target(String name) {
            this.object = name;

        }

        public void hitTarget(String target) {
            if (target.equals(object)) {
                System.out.println("Target is hit by you, bingo!");
            } else {
                System.out.println("Target is missed, please try again!");
            }
        }
    }

    public DebugAppender() {
    }

    public static void main(String... args) throws Exception {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("run original hitTarget method with hit&miss cases");
        System.out.println("--------------------------------------------------------------------------------");
        new Target("target1").hitTarget("target2");
        new Target("target2").hitTarget("target2");

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("read out vm instructions from class file");
        System.out.println("--------------------------------------------------------------------------------");
        ClassReader cr = new ClassReader(new FileInputStream(CLASS_FILE_NAME));
        TraceClassVisitor tcv = new TraceClassVisitor(new PrintWriter(System.out));
        cr.accept(tcv, 0);

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("modify those vm instructions");
        System.out.println("--------------------------------------------------------------------------------");

        ClassWriter cw = new ClassWriter(cr, 0);
        TargetClassVisitor mcv = new TargetClassVisitor(ASM5, cw);
        cr.accept(mcv, 0);
        byte[] codes = cw.toByteArray();
        FileOutputStream fos = new FileOutputStream(CLASS_FILE_NAME);
        fos.write(codes);
        fos.close();

        URL[] urls = new URL[1];
        urls[0] = new File("./target/classes").toURI().toURL();
        TargetClassLoader targetCL = new TargetClassLoader(urls);
        //forced to reload modified Target class, otherwise the old class object will be used to construct new instance
        Class targetClass = targetCL.loadClass("king.law.asm.DebugAppender$Target");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("run modified hitTarget method with hit&miss cases");
        System.out.println("--------------------------------------------------------------------------------");

        //have to use reflection to construct Target instances and call hitTarget method
        //I cannot cast targetClass loaded by TargetClassLoader in previous steps to current Class type loaded by another classloader
        //otherwise, it will throw casting exception
        Constructor constructor = targetClass.getConstructor(String.class);
        Method hitTarget = targetClass.getMethod("hitTarget", String.class);
        Object instance1 = constructor.newInstance("target1");
        hitTarget.invoke(instance1, "target2");
        Object instance2 = constructor.newInstance("target2");
        hitTarget.invoke(instance2, "target2");
    }
}

class TargetClassVisitor extends ClassVisitor implements Opcodes {

    public TargetClassVisitor(int api, ClassVisitor cv) {
        super(api, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
        final MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);
        if (name.equals("hitTarget")) {
            return new HitTargetMethodVisitor(ASM5, mv);
        } else {
            return mv;
        }
    }
}

class HitTargetMethodVisitor extends MethodVisitor implements Opcodes {

    public HitTargetMethodVisitor(int api, MethodVisitor mv) {
        super(api, mv);
    }

    @Override
    public void visitCode() {
        System.out.println("start to visit hitTarget method...");
        super.visitCode();
    }

    @Override
    public void visitEnd() {
        System.out.println("end to visit hitTarget method.");
        super.visitEnd();
    }

    @Override
    public void visitLineNumber(int line, Label start) {
        super.visitLineNumber(line, start);
        if (line == 36) {//insert a new system.out.println
            this.mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
            this.mv.visitLdcInsn("add debug when target is hit");
            this.mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        }
    }
}

class TargetClassLoader extends URLClassLoader {

    public TargetClassLoader(URL[] urls) {
        super(urls);
    }

    @Override
    public Class loadClass(String name) throws ClassNotFoundException {
        if (name.contains("Target")) {
            Class fc = findClass(name);//forced to load again
            return fc;
        }
        return super.loadClass(name);//parent classloader will load all jdk class files
    }
}