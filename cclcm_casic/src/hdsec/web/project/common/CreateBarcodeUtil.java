package hdsec.web.project.common;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xvolks.jnative.JNative;
import org.xvolks.jnative.Type;
import org.xvolks.jnative.exceptions.NativeException;
import org.xvolks.jnative.pointers.NullPointer;
import org.xvolks.jnative.pointers.Pointer;
import org.xvolks.jnative.pointers.memory.HeapMemoryBlock;
import org.xvolks.jnative.pointers.memory.MemoryBlockFactory;

public class CreateBarcodeUtil {

	/**
	 * 第一个参数是传入的二维 数组 ① nCompanyType 代表条码分支 ； （1 : CAISC；2 : CETC；3 : CAEP；4 : CASIC_31；5 : CETC-NEW；） ② nBarcodeType
	 * 代表条码类型； （1 : 一维码数值；2 : 二维码数值；） ③ cUserID 代表用户USER_ID； ④ nEventType 代表作业类型； （1 : 打印；2 : 刻录；3 : 复印；4 : 录入；5 : 磁介质；）
	 * ⑤ cEventCode 代表作业编号 ； （打印刻录复印录入：EVENT_CODE，磁介质：DEVICE_CODE） ⑥ cConsoleID 代表控制台号； （载体无控制台号属性时，传默认值0） ⑦
	 * nCopiesCount 代表份号； （载体无份数属性时，传默认值0） 、第二个参数为第一个参数的个数 老版六个 新版七个
	 * 
	 * @param cParas
	 * @param nNumOfParas
	 * @param oneOrTwo
	 * @return
	 * @throws NativeException
	 * @throws IllegalAccessException
	 */
	

	public static Map<String, String> CreateBarcode(Map<String, String> Paras, int nNumOfParas) throws IllegalAccessException,
			NativeException, URISyntaxException {
		// 获取当前运行路径
		JNative jNative = null;
		String localPath = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath();
		// 去掉最前面的/
		if (localPath.startsWith("/")) {
			localPath = localPath.substring(1);
		}
		// 将/换成\\
		localPath = localPath.replaceAll("\\/", "\\\\\\\\");
		String path = localPath.substring(0, localPath.lastIndexOf("\\\\WEB-INF"));
		//List<String> list = new ArrayList<String>();
		// System.setProperty("jnative.debug", "true");
		Map<String, String> map = new HashMap(); 
		System.load(path + "\\GenBarCodeDll.dll");
		jNative = null;
		try {
			jNative = new JNative(path + "\\GenBarCodeDll.dll", "DllGetBarcodeValueFun");
			jNative.setRetVal(Type.INT);
			//MemoryBlockFactory.setPreferredMemoryType(HeapMemoryBlock.class);
			Pointer cParas = new Pointer(MemoryBlockFactory.createMemoryBlock(32));// 二维指针
			cParas.zeroMemory();

			Pointer nCompanyType = new Pointer(MemoryBlockFactory.createMemoryBlock(256));
			nCompanyType.zeroMemory();
			Pointer nBarcodeType = new Pointer(MemoryBlockFactory.createMemoryBlock(256));
			nBarcodeType.zeroMemory();
			Pointer cUserID = new Pointer(MemoryBlockFactory.createMemoryBlock(256));
			cUserID.zeroMemory();
			Pointer nEventType = new Pointer(MemoryBlockFactory.createMemoryBlock(256));
			nEventType.zeroMemory();
			Pointer cEventCode = new Pointer(MemoryBlockFactory.createMemoryBlock(256));
			cEventCode.zeroMemory();
			Pointer cConsoleID = new Pointer(MemoryBlockFactory.createMemoryBlock(256));
			cConsoleID.zeroMemory();
			Pointer nCopiesCount = new Pointer(MemoryBlockFactory.createMemoryBlock(256));
			nCopiesCount.zeroMemory();
			Pointer strOutBarcode = new Pointer(MemoryBlockFactory.createMemoryBlock(512));
			strOutBarcode.zeroMemory();
			Pointer strOut2DCode = new Pointer(MemoryBlockFactory.createMemoryBlock(512));
			strOut2DCode.zeroMemory();
			Pointer ptrDB = NullPointer.NULL;
			String strCompayType = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.CompanyType");
			nCompanyType.setStringAt(0, strCompayType);
			//条码规则 正常根据条码类型1维码，2维码，这里直接用调用二维码，同时生成1维码，二维码
			nBarcodeType.setStringAt(0, "2");
			cUserID.setStringAt(0, (String) Paras.get("USERID"));
			nEventType.setStringAt(0, (String) Paras.get("EVENTTYPE"));
			cEventCode.setStringAt(0, (String) Paras.get("EVENTCODE"));
			cConsoleID.setStringAt(0, "0");
			nCopiesCount.setStringAt(0, (String) Paras.get("COUNT"));

			// 为二维指针赋值，每赋值一次，偏移4字节也就是一个指针的大小
			cParas.setIntAt(0, nCompanyType.getPointer());
			cParas.setIntAt(4, nBarcodeType.getPointer());
			cParas.setIntAt(8, cUserID.getPointer());
			cParas.setIntAt(12, nEventType.getPointer());
			cParas.setIntAt(16, cEventCode.getPointer());
			cParas.setIntAt(20, cConsoleID.getPointer());
			cParas.setIntAt(24, nCopiesCount.getPointer());

			// 设置函数参数
			jNative.setParameter(0, cParas);
			jNative.setParameter(1, 7);
			jNative.setParameter(2, strOutBarcode);
			jNative.setParameter(3, strOut2DCode);
			jNative.setParameter(4, ptrDB);

			// 调用方法
			jNative.invoke();
			jNative.getRetVal();
			String barCode = strOutBarcode.getAsString();
			//String barCode2 = new String(strOut2DCode.getMemory(), "GBK");
			String barCode2 =  strOut2DCode.getAsString();
			map.put("Barcode", barCode);
			map.put("barCode2", barCode2);
			
		} catch (NativeException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
        } finally {
			if (jNative != null) {
				jNative.dispose();
			}
		}
		return map;
	}
}
