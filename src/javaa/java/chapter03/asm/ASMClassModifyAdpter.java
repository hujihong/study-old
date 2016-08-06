//package chapter03.asm;
//
//import org.objectweb.asm.ClassAdapter;
//import org.objectweb.asm.ClassVisitor;
//import org.objectweb.asm.MethodVisitor;
//import org.objectweb.asm.Opcodes;
//
//public class ASMClassModifyAdpter extends ClassAdapter {
//	
//	public ASMClassModifyAdpter(ClassVisitor classVisitor) {
//		super(classVisitor);
//	}
//	
//	public MethodVisitor visitMethod(final int access, final String methodName,
//			final String desc, final String signature, final String[] exceptions) {
//		if("display2".equals(methodName)) {
//			return null;//�����������������
//		}
//		if("display1".equals(methodName)) {
//			MethodVisitor methodVisitor = cv.visitMethod(access, methodName, desc, signature, exceptions);
//			methodVisitor.visitCode();
//			//���ӵ����ȼ������Ӵ��룺name = "����name"
//			methodVisitor.visitVarInsn(Opcodes.ALOAD , 0);
//			methodVisitor.visitLdcInsn("����name");
//			methodVisitor.visitFieldInsn(Opcodes.PUTFIELD , "chapter03/asm/ForASMTestClass" , "name" , "Ljava/lang/String;");
//			
//			//�������ȼ������Ӵ��룺value = "����value";
//			methodVisitor.visitVarInsn(Opcodes.ALOAD , 0);
//			methodVisitor.visitLdcInsn("����value");
//			methodVisitor.visitFieldInsn(Opcodes.PUTFIELD , "chapter03/asm/ForASMTestClass" , "value" , "Ljava/lang/String;");
//			
//			//�ٽ�һ�����Ի�ȡ������ӡ����
//			methodVisitor.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out" , "Ljava/io/PrintStream;");
//			methodVisitor.visitVarInsn(Opcodes.ALOAD, 0);
//			methodVisitor.visitFieldInsn(Opcodes.GETFIELD, "chapter03/asm/ForASMTestClass", "name", "Ljava/lang/String;");
//			methodVisitor.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V");
//			
//			methodVisitor.visitEnd();
//			return methodVisitor;//����visitor
//		}else {
//			return cv.visitMethod(access, methodName, desc, signature, exceptions);
//		}
//	}
//
//}
