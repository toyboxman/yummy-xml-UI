package king.law.asm;

import king.law.asm.src.ClassA;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.lang.reflect.Constructor;

class ClsWr extends ClassVisitor {
    public ClsWr(int flags) {
        super(/* latest api = */ Opcodes.ASM7, new ClassWriter(flags));
    }

    @Override
    public MethodVisitor visitMethod(int access, String name,
                                     String descriptor, String signature, String[] exceptions) {
        if (access == Opcodes.ACC_PRIVATE
                && name.equals("index")
                && descriptor.equals("()I")) {
            return null;
        }
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (name.equals("toString")) {
            mv = new MtWr(mv);
        }
        return mv;
    }

    public byte[] toByteArray() {
        return ((ClassWriter) cv).toByteArray();
    }
}

class MtWr extends MethodVisitor {
    private MethodVisitor mv = null;

    public MtWr(MethodVisitor impl) {
        super(/* latest api = */ Opcodes.ASM7, impl);
        mv = impl;
    }

    @Override
    public void visitCode() {
        super.visitCode();
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System",
                "out", "Ljava/io/PrintStream;");
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitFieldInsn(Opcodes.GETFIELD, "king/law/asm/src/ClassA",
                "number", "I");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(I)V", false);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        mv.visitFieldInsn(opcode, owner, name, descriptor);
        if (opcode == Opcodes.GETFIELD) {
            mv.visitTypeInsn(Opcodes.NEW, "java/util/Random");
            mv.visitInsn(Opcodes.DUP);
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Random",
                    "<init>", "()V", false);
            mv.visitInsn(Opcodes.ICONST_5);
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Random",
                    "nextInt", "(I)I", false);
            mv.visitInsn(Opcodes.IMUL);
        }
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        mv.visitMaxs(maxStack + 2, maxLocals);
    }
}

class UpdateClassLoader extends ClassLoader {
    public Class defineClass(String name, byte[] b) {
        return super.defineClass(name, b, 0, b.length);
    }

    @Override
    protected Class<?> findClass(String name)
            throws ClassNotFoundException {
        if (name.equals("king.law.asm.src.ClassA")) {
            ClassWriter cw = new ClassWriter(0);
            byte[] b = cw.toByteArray();
            return defineClass(name, b, 0, b.length);
        }
        return super.findClass(name);
    }
}

public class UpdateClass {
    public static void main(String[] args) throws Exception {
        System.out.println("original ClassA(5) is : " + new ClassA(5));
        System.out.println("has index() method? : " + ClassA.class.getDeclaredMethod("index"));
        System.out.println("--------------------------");
        ClassReader classReader = new ClassReader("king.law.asm.src.ClassA");
        ClassVisitor cw = new ClsWr(0);
        classReader.accept(cw, ClassReader.SKIP_DEBUG);

        byte[] bytes = ((ClsWr) cw).toByteArray();
        Class updatedClassA = new UpdateClassLoader()
                .defineClass("king.law.asm.src.ClassA", bytes);
        Constructor constructor = updatedClassA.getConstructor(Integer.TYPE);//等于 int.class
        Object updatedObj = constructor.newInstance(5);
        System.out.println("updated ClassA(5) is : " + updatedObj);
        System.out.println("has index() method? : " + updatedClassA.getDeclaredMethod("index"));
    }
}
