package hdsec.web.project.ledger.sync;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Connections {
	public static Connection getSpConnection(String sqlUrl, String sqlUserName, String sqlPassword) throws Exception {
		String driver = getProperty("hdsec.web.project.config.properties", "jdbc.driver");
		Class.forName(driver);
		return DriverManager.getConnection(sqlUrl, sqlUserName, sqlPassword);
	}

	public static Connection getCclcmConnection() throws Exception {
		String driver = getProperty("hdsec.web.project.config.properties", "jdbc.driver");
		String url = getProperty("hdsec.web.project.config.properties", "jdbc.url");
		String userName = getProperty("hdsec.web.project.config.properties", "jdbc.username");
		String password = getProperty("hdsec.web.project.config.properties", "jdbc.password");
		Class.forName(driver);
		return DriverManager.getConnection(url, userName, password);
	}

	public static String getProperty(String fileName, String key) {
		Properties p = new Properties();
		try {
			p.load(SyncDataImpl.class.getResourceAsStream("/" + fileName));
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
		return p.getProperty(key);
	}
}
