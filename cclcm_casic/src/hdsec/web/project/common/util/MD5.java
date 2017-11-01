package hdsec.web.project.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;

import org.springframework.util.StringUtils;

public class MD5 {
	public static String getMD5Str(String source) throws Exception {
		if ((source == null) || "".equals(source)) {
			return null;
		}

		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.reset();
			messageDigest.update(source.getBytes("UTF-8"));
		} catch (Exception e) {
			throw new Exception("Exception at getMD5Str: " + e.toString());
		}

		byte[] byteArray = messageDigest.digest();
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				md5StrBuff.append("0").append(
						Integer.toHexString(0xFF & byteArray[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}

		return md5StrBuff.toString();
	}
	
	public static String getStringMD5(String str) throws Exception{
		if(!StringUtils.hasLength(str)){
			return str;
		}
		MessageDigest messageDigest = null;
		messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(str.getBytes());
		return bufferToHex(messageDigest.digest(), 0,
				messageDigest.digest().length);
	}

	public static String getFileMd5(File file) throws Exception {
		FileInputStream fis = null;
		MessageDigest messageDigest = null;
//		FileChannel ch = null;
//		MappedByteBuffer mbb = null;
		messageDigest = MessageDigest.getInstance("MD5");
		fis = new FileInputStream(file);
		ByteArrayOutputStream out = new ByteArrayOutputStream((int) file.length());
		byte buffer[] = new byte[1024 * 1024];
		int len = 0;
		while((len = fis.read(buffer))!= -1){
			out.write(buffer, 0, len);
		}
		fis.close();
		out.close();
//			ch = fis.getChannel();
//			mbb = ch.map(FileChannel.MapMode.READ_ONLY, 0,
//					file.length());
		messageDigest.update(out.toByteArray());
		return bufferToHex(messageDigest.digest(), 0,
				messageDigest.digest().length);
		
	}

	static String bufferToHex(byte bytes[], int from, int len) {
		StringBuffer sb = new StringBuffer();
		for (int i = from; i < len; i++) {
			char c1 = hexDigits[(bytes[i] & 0xf0) >> 4];
			char c2 = hexDigits[bytes[i] & 0xf];
			sb.append(c1);
			sb.append(c2);
		}
		return sb.toString();

	}

	static final char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

}
