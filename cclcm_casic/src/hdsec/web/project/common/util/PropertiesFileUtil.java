package hdsec.web.project.common.util;

import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtil {

	public PropertiesFileUtil() {
	}

	public static String getProperty(String fileName, String key) {
		Properties p = new Properties();
		try {
			p.load(PropertiesFileUtil.class.getResourceAsStream("/" + fileName));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return p.getProperty(key);
	}

	public static Properties getProperties(String fileName) {
		Properties p = new Properties();
		try {
			p.load(PropertiesFileUtil.class.getResourceAsStream("/" + fileName));
			return p;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
