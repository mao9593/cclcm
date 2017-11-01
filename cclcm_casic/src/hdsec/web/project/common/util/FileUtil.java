package hdsec.web.project.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.thoughtworks.xstream.XStream;

public class FileUtil {

	public static byte[] fileToBytes(File file) {
		if (file == null)
			return null;
		try {
			FileInputStream fis = new FileInputStream(file);
			byte[] bytes = new byte[fis.available()];
			fis.read(bytes);
			fis.close();
			return bytes;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void saveToStream(Object obj, OutputStream os) {
		XStream xs = new XStream();
		xs.toXML(obj, os);
	}

	public static Object loadFromStream(InputStream is) {
		XStream xs = new XStream();
		return xs.fromXML(is);
	}

	public static Object loadFromFile(String fullPath) {
		File file = new File(fullPath);
		try {
			return loadFromStream(new FileInputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void saveToFile(Object obj, String fullPath) {
		File file = new File(fullPath);
		try {
			saveToStream(obj, new FileOutputStream(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
