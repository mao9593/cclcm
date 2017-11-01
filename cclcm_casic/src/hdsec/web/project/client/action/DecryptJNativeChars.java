package hdsec.web.project.client.action;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.MemoryBlockFactory;

/**
 * 使用JNative调用本地dll解密文件
 * 
 * @author xuzhaofeng
 * 
 */

public class DecryptJNativeChars {
	/**
	 * return 转换成功的字节数
	 */
	public static String decryptChars(int encryptOrDecrypt, int editionAlgorithm, int length, Pointer input,
			Pointer pTar) throws NativeException, IllegalAccessException {
		JNative jNative = null;
		try {
			if (jNative == null) {
				jNative = new JNative("HDCryption.dll", "HangDunCryptographyChars");
				pTar = new Pointer(MemoryBlockFactory.createMemoryBlock(256));
				// 利用org.xvolks.jnative.JNative来装载
				// SCReader.dll,并利用其SCHelp_HexStringToBytes方法
				jNative.setRetVal(Type.VOID);
				// 指定返回参数的类型
			}
			int i = 0;
			jNative.setParameter(i++, encryptOrDecrypt);
			jNative.setParameter(i++, editionAlgorithm);
			jNative.setParameter(i++, length);
			jNative.setParameter(i++, input);
			jNative.setParameter(i++, pTar);
			System.out.println("调用的DLL文件名为：" + jNative.getDLLName());
			System.out.println("调用的方法名为：" + jNative.getFunctionName());
			// 传值
			jNative.invoke();// 调用方法
			jNative.getRetVal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jNative != null) {
				jNative.dispose();// 释放
			}
		}
		byte[] bytes = pTar.getMemory();
		byte[] bytes2 = new byte[16];
		for (int i = 0; i < 16; i++) {
			bytes2[i] = bytes[i];
		}
		// output = new String(new sun.misc.BASE64Encoder().encode(bytes2));
		return new String(bytes2);
	}

	public static Pointer createPointer() throws NativeException {
		Pointer pointer = null;
		pointer = new Pointer(MemoryBlockFactory.createMemoryBlock(256));
		pointer.setIntAt(0, 256);
		return pointer;
	}
}
