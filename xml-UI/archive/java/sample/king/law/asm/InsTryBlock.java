package king.law.asm;

import org.objectweb.asm.*;
import org.objectweb.asm.util.CheckClassAdapter;

import java.lang.reflect.Constructor;
import java.nio.file.*;

public class InsTryBlock {
    public static void main(String[] args) throws Exception {
        ClassReader classReader = new ClassReader("king.law.asm.src.ClassA");
        // tryblock植入class文件后会导致FRAMES改变，手动计算困难，因此选择自动处理
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        // 使用定制ClassWriter配合ClassVisitor来处理读出class字节流
        TryBlockClassWriter mcw = new TryBlockClassWriter(Opcodes.ASM7, cw);
        ClassVisitor cv = new CheckClassAdapter(mcw);
        classReader.accept(cv, 0);
        // 把转换过的class字节流写入ClassA0.class文件
        byte[] bytes = cw.toByteArray();
        Path dir = Paths.get("./target/classes/king/law/asm", "src");
        Path file = dir.resolve("ClassA0.class");
        Files.write(file, bytes,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE);
        // 通过定制classloader来加载修改后的class文件
        Class updatedClassA = new UpdateClassLoader()
                .defineClass("king.law.asm.src.ClassA", bytes);
        Constructor constructor = updatedClassA.getConstructor(Integer.TYPE);//等于 int.class
        Object updatedObj = constructor.newInstance(5);
        System.out.println("updated ClassA(5) is : " + updatedObj);
    }

    public static class TryBlockMethodWriter extends MethodVisitor {
        // 待增加try block的methodName
        private String methodName;

        // try block的相关label，对应asm代码 TRYCATCHBLOCK L0 L1 L2 null
        private Label lTryBlockStart; // L0
        private Label lTryBlockEnd;   // L1
        private Label lFinalBlock;    // L2

        // flag用来表示原始asm代码中IRETURN是否已经做过替换 IRETURN->ISTORE 1
        private boolean replace = false;

        public TryBlockMethodWriter(int api, MethodVisitor mv, String methodName) {
            super(api, mv);
            this.methodName = methodName;
        }

        // 为了给整个方法都放到try/finally，需要在方法体最开始加入visitTryCatchBlock
        //   visitAnnotationDefault?
        //   ( visitAnnotation | visitParameterAnnotation | visitAttribute )*
        //   ( visitCode
        //   ( visitTryCatchBlock | visitLabel | visitFrame | visitXxxInsn |
        //        visitLocalVariable | visitLineNumber )*
        //   visitMaxs )?
        //   visitEnd

        // 原始index()完整asm代码
        //private index()I
        //L0
        //LINENUMBER 13 L0
        //ALOAD 0
        //GETFIELD king/law/asm/src/ClassA.number : I
        //IRETURN
        //
        //L1
        //LOCALVARIABLE this Lking/law/asm/src/ClassA; L0 L1 0
        //MAXSTACK = 1
        //MAXLOCALS = 1
        //

        //---> 增加try block后index()完整asm代码
        //
        //private index()I
        //TRYCATCHBLOCK L0 L1 L2 null
        //L0
        //LINENUMBER 14 L0
        //ALOAD 0
        //GETFIELD king/law/asm/src/ClassA.number : I
        //ISTORE 1
        //
        //L1
        //LINENUMBER 16 L1
        //GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
        //LDC "index() method returns"
        //INVOKEVIRTUAL java/io/ PrintStream.println (Ljava/lang/String;)V
        //ILOAD 1
        //IRETURN
        //
        //L2
        //FRAME SAME1 java/lang/Throwable
        //ASTORE 2
        //GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
        //LDC "index() method returns"
        //INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/String;)V
        //ALOAD 2
        //ATHROW
        //
        //L3
        //LOCALVARIABLE this Lking/law/asm/src/ClassA; L0 L3 0
        //MAXSTACK = 2
        //MAXLOCALS = 3
        //
        @Override
        public void visitCode() {
            super.visitCode();

            // 仅对 index()方法增加try block
            if (methodName.equals("index")) {
                lTryBlockStart = new Label();
                lTryBlockEnd = new Label();
                lFinalBlock = new Label();

                // 在原始asm代码中新增 TRYCATCHBLOCK L0 L1 L2 null
                // null表示捕获any exceptions，也可以用名称指定具体捕获类型如"java/lang/System"
                visitTryCatchBlock(lTryBlockStart, lTryBlockEnd,
                        lFinalBlock, null);
                // 在原始asm代码中新增 L0
                visitLabel(lTryBlockStart);
            }
        }

        @Override
        public void visitInsn(int opcode) {
            // 如果是原始asm代码中IRETURN指令则替换成ISTORE 1指令
            if (opcode == Opcodes.IRETURN && !replace) {
                //System.out.println("do instrument: Opcodes.IRETURN");
                //调试中通过打印发现IRETURN被执行两次导致修改class失败
                //因为修改后的class L1中也有IRETURN指令，那个地方不能替换，因此增加replace flag判断
                visitVarInsn(Opcodes.ISTORE, 1);
                replace = true;
                return;
            }
            super.visitInsn(opcode);
        }

        @Override
        public void visitMaxs(int maxStack, int maxLocals) {
            // 原始asm代码max段之前就是IRETURN指令，需要增加try block相关代码
            if (methodName.equals("index")) {
                // 增加L1段的代码
                // L1
                visitLabel(lTryBlockEnd);
                //LINENUMBER 16 L1 行码会自动计算不需要显式处理
                //GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
                visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System",
                        "out", "Ljava/io/PrintStream;");
                //LDC "index() method returns"
                visitLdcInsn("index() method returns");
                //INVOKEVIRTUAL java/io/ PrintStream.println (Ljava/lang/String;)V
                visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                        "println", "(Ljava/lang/String;)V", false);
                //ILOAD 1
                visitVarInsn(Opcodes.ILOAD, 1);
                //IRETURN
                //此处指令通过replace flag识别就不会再替换掉
                visitInsn(Opcodes.IRETURN);

                // 增加L2段的代码
                // L2
                visitLabel(lFinalBlock);
                //FRAME SAME1 java/lang/Throwable 选择ClassWriter.COMPUTE_FRAMES自动计算，不需要显式处理
                //ASTORE 2
                visitVarInsn(Opcodes.ASTORE, 2);
                //GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
                visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System",
                        "out", "Ljava/io/PrintStream;");
                //LDC "index() method returns"
                visitLdcInsn("index() method returns");
                //INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/String;)V
                visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                        "println", "(Ljava/lang/String;)V", false);
                //ALOAD 2
                visitVarInsn(Opcodes.ALOAD, 2);
                //ATHROW
                visitInsn(Opcodes.ATHROW);
            }

            super.visitMaxs(maxStack, maxLocals);
        }
    }

    public static class TryBlockClassWriter extends ClassVisitor {
        private int api;

        public TryBlockClassWriter(int api, ClassWriter cv) {
            super(api, cv);
            this.api = api;
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc,
                                         String signature, String[] exceptions) {

            MethodVisitor mv = super.visitMethod(access, name, desc, signature,
                    exceptions);
            // 封装系统MethodVisitor，通过TryBlockMethodWriter代理来实现代码修改
            TryBlockMethodWriter mvw = new TryBlockMethodWriter(api, mv, name);
            return mvw;
        }
    }
}
