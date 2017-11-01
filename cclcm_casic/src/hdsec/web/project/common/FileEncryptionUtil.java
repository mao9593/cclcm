package hdsec.web.project.common;

import org.apache.log4j.Logger;
import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;

/**
 * 文件加解密工具类 使用时注意要先将HDCryption.dll和JNativeCpp.dll文件放入tomcat/bin目录下
 * 
 * @author renmingfei
 * 
 */
public class FileEncryptionUtil {
	private static Logger logger = Logger.getLogger(FileEncryptionUtil.class);
	private static JNative jNative = null;

	public static int decryptFile(String sourcePath, String destPath) throws NativeException, IllegalAccessException {
		int result = -1;
		sourcePath = transferPathStr(sourcePath);
		destPath = transferPathStr(destPath);
		try {
			if (jNative == null) {
				jNative = new JNative("HDCryption.dll", "HangDunCryptographyFile");
				jNative.setRetVal(Type.INT);
			}
			int i = 0;
			jNative.setParameter(i++, 1);
			jNative.setParameter(i++, 16);
			jNative.setParameter(i++, sourcePath);
			jNative.setParameter(i++, destPath);
			jNative.invoke();// 调用方法
			result = jNative.getRetValAsInt();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if (jNative != null) {
				jNative.dispose();// 释放
				jNative = null;
			}
		}
		return result;
	}

	public static int encryptFile(String sourcePath, String destPath) throws NativeException, IllegalAccessException {
		int result = -1;
		sourcePath = transferPathStr(sourcePath);
		destPath = transferPathStr(destPath);
		try {
			if (jNative == null) {
				jNative = new JNative("HDCryption.dll", "HangDunCryptographyFile");
				jNative.setRetVal(Type.INT);
			}
			int i = 0;
			jNative.setParameter(i++, 2);
			jNative.setParameter(i++, 16);
			jNative.setParameter(i++, sourcePath);
			jNative.setParameter(i++, destPath);
			jNative.invoke();// 调用方法
			result = jNative.getRetValAsInt();
		} finally {
			if (jNative != null) {
				jNative.dispose();// 释放
				jNative = null;
			}
		}
		return result;
	}

	private static String transferPathStr(String path) {
		if (path.startsWith("/")) {
			path = path.substring(1);
		}
		// 将/换成\\
		path = path.replaceAll("\\/", "\\\\\\\\");
		return path;
	}
}
