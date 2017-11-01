package hdsec.web.project.listener;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;

/**
 * 测试使用JNative调用本地dll解密文件
 * 
 * @author renmingfei
 * 
 */
public class EncryptSqlFile {
	/**
	 * return 转换成功的字节数
	 */
	static JNative jNative = null;

	public void callEncryptFile(int encryptOrDecrypt, int editionAlgorithm, String sourcePath, String destPath)
			throws NativeException, IllegalAccessException {
		if (jNative == null) {
			jNative = new JNative("HDCryption.dll", "HangDunCryptographyFile");
			jNative.setRetVal(Type.INT);
		}
		int i = 0;
		jNative.setParameter(i++, encryptOrDecrypt);
		jNative.setParameter(i++, editionAlgorithm);
		jNative.setParameter(i++, sourcePath);
		jNative.setParameter(i++, destPath);
		jNative.invoke();// 调用方法
		Integer result = jNative.getRetValAsInt();
		System.out.println("result is :" + (result == 0));
	}

	public static void main(String[] args) throws IllegalAccessException, NativeException {
		try {
			EncryptSqlFile tjn = new EncryptSqlFile();
			// 获取当前运行路径(test/bin)
			String localPath = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath();
			// 去掉最前面的/
			if (localPath.startsWith("/")) {
				localPath = localPath.substring(1);
			}
			System.out.println(localPath);
			// 将/换成\\
			localPath = localPath.replaceAll("\\/", "\\\\\\\\");
			// 加密
			tjn.callEncryptFile(1, 16, localPath + "hdsec\\web\\project\\listener\\sqlmodule\\0_module_print.sql",
					localPath + "hdsec\\web\\project\\listener\\sql\\0_module.sql");
			tjn.callEncryptFile(1, 16, localPath + "hdsec\\web\\project\\listener\\sqlmodule\\1_module_burn.sql",
					localPath + "hdsec\\web\\project\\listener\\sql\\1_module.sql");
			tjn.callEncryptFile(1, 16, localPath + "hdsec\\web\\project\\listener\\sqlmodule\\2_module_copy.sql",
					localPath + "hdsec\\web\\project\\listener\\sql\\2_module.sql");
			tjn.callEncryptFile(1, 16, localPath + "hdsec\\web\\project\\listener\\sqlmodule\\3_module_device.sql",
					localPath + "hdsec\\web\\project\\listener\\sql\\3_module.sql");
			tjn.callEncryptFile(1, 16, localPath + "hdsec\\web\\project\\listener\\sqlmodule\\4_module_enter.sql",
					localPath + "hdsec\\web\\project\\listener\\sql\\4_module.sql");
			tjn.callEncryptFile(1, 16, localPath + "hdsec\\web\\project\\listener\\sqlmodule\\5_module_transfer.sql",
					localPath + "hdsec\\web\\project\\listener\\sql\\5_module.sql");
			tjn.callEncryptFile(1, 16, localPath + "hdsec\\web\\project\\listener\\sqlmodule\\6_module_borrow.sql",
					localPath + "hdsec\\web\\project\\listener\\sql\\6_module.sql");
			tjn.callEncryptFile(1, 16, localPath + "hdsec\\web\\project\\listener\\sqlmodule\\7_module_confirm.sql",
					localPath + "hdsec\\web\\project\\listener\\sql\\7_module.sql");
			tjn.callEncryptFile(1, 16, localPath + "hdsec\\web\\project\\listener\\sqlmodule\\8_module_delay.sql",
					localPath + "hdsec\\web\\project\\listener\\sql\\8_module.sql");
			tjn.callEncryptFile(1, 16, localPath + "hdsec\\web\\project\\listener\\sqlmodule\\9_module_storage.sql",
					localPath + "hdsec\\web\\project\\listener\\sql\\9_module.sql");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jNative != null) {
				jNative.dispose();// 释放
			}
		}
		System.out.println("Encryption Completed");
	}
}
