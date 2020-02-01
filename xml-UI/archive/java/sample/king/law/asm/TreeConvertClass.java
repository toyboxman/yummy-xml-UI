package king.law.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.ClassNode;

public class TreeConvertClass {
    public static void main(String[] args) throws Exception {
        ClassNode cn = new ClassNode();
        ClassReader classReader = new ClassReader("king.law.asm.src.ClassA");
        classReader.accept(cn, ClassReader.SKIP_DEBUG);
        cn.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,
                "king/law/asm/dest/ClassA0", null, "java/lang/Object",
                null);
        ClassWriter cw = new ClassWriter(0);
        cn.accept(cw);
        byte[] bytes = cw.toByteArray();

        Class updatedClassA = new UpdateClassLoader()
                .defineClass("king.law.asm.dest.ClassA0", bytes);
        System.out.println("class is : " + updatedClassA.getName());
    }
}
