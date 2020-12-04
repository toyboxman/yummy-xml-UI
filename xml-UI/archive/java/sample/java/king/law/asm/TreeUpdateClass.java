package king.law.asm;

import king.law.asm.src.ClassA;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.InsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;
import org.objectweb.asm.tree.TypeInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

import java.lang.reflect.Constructor;
import java.util.Iterator;

public class TreeUpdateClass {
    public static void main(String[] args) throws Exception {
        System.out.println("original ClassA(5) is : " + new ClassA(5));
        System.out.println("has index() method? : " + ClassA.class.getDeclaredMethod("index"));
        System.out.println("--------------------------");
        ClassReader classReader = new ClassReader("king.law.asm.src.ClassA");
        ClassNode cn = new ClassNode();
        classReader.accept(cn, 0);
        Iterator<MethodNode> methodNodeIterator = cn.methods.iterator();
        while (methodNodeIterator.hasNext()) {
            MethodNode mn = methodNodeIterator.next();
            if ("index".equals(mn.name) && mn.access == Opcodes.ACC_PRIVATE
                    && "()I".equals(mn.desc)) {
                methodNodeIterator.remove();
            }

            if ("toString".equals(mn.name) && "()Ljava/lang/String;".equals(mn.desc)) {
                final AbstractInsnNode[] cNode = {null};
                mn.instructions.forEach(node -> {
                    if (node.getOpcode() == Opcodes.GETFIELD) {
                        cNode[0] = node;
                    }
                });
                InsnList randomIl = new InsnList();
                randomIl.add(new TypeInsnNode(Opcodes.NEW, "java/util/Random"));
                randomIl.add(new InsnNode(Opcodes.DUP));
                randomIl.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "java/util/Random",
                        "<init>", "()V", false));
                randomIl.add(new InsnNode(Opcodes.ICONST_5));
                randomIl.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/util/Random",
                        "nextInt", "(I)I", false));
                randomIl.add(new InsnNode(Opcodes.IMUL));
                mn.instructions.insert(cNode[0], randomIl);

                InsnList il = new InsnList();
                il.add(new FieldInsnNode(Opcodes.GETSTATIC, "java/lang/System",
                        "out", "Ljava/io/PrintStream;"));
                il.add(new VarInsnNode(Opcodes.ALOAD, 0));
                il.add(new FieldInsnNode(Opcodes.GETFIELD, "king/law/asm/src/ClassA",
                        "number", "I"));
                il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                        "println", "(I)V", false));
                mn.instructions.insert(il);

                mn.maxStack += 2;
            }
        }

        ClassWriter cw = new ClassWriter(0);
        cn.accept(cw);
        byte[] bytes = cw.toByteArray();

        Class updatedClassA = new UpdateClassLoader()
                .defineClass("king.law.asm.src.ClassA", bytes);
        Constructor constructor = updatedClassA.getConstructor(Integer.TYPE);//等于 int.class
        Object updatedObj = constructor.newInstance(5);
        System.out.println("updated ClassA(5) is : " + updatedObj);
        System.out.println("has index() method? : " + updatedClassA.getDeclaredMethod("index"));
    }
}
