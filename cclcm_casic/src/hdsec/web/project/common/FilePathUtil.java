package hdsec.web.project.common;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;

import org.apache.log4j.Logger;

public class FilePathUtil {
	private static Logger logger = Logger.getLogger(FilePathUtil.class);
	public static final String BURN_FILE_TEMP_PATH = "burn.file.temp.path";
	public static final String BURN_FILE_STORE_PATH = "burn.file.store.path";
	public static final String IMPORT_FILE_STORE_PATH = "import.file.store.path";
	public static final String PRINT_FILE_STORE_PATH = "print.file.store.path";
	
	private static String getLocalPath() throws Exception {
		String localPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		if (localPath.indexOf("WEB-INF") == -1) {
			logger.error("WEB工程本地部署路径中不包含WEB-INF");
			throw new Exception("WEB工程本地部署路径中不包含WEB-INF");
		} else {
			return localPath;
		}
	}
	
	public static String getBurnFileTempPath() throws Exception {
		String suffix = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, BURN_FILE_TEMP_PATH);
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + suffix;
		logger.info("BurnFileTempPath[" + path + "]");
		return path;
	}
	
	public static String getBurnFileStorePath() throws Exception {
		String suffix = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, BURN_FILE_STORE_PATH);
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + suffix;
		logger.info("BurnFileStorePath[" + path + "]");
		return path;
	}
	
	public static String getImportFileStorePath() throws Exception {
		String suffix = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, IMPORT_FILE_STORE_PATH);
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + suffix;
		logger.info("ImportFileStorePath[" + path + "]");
		return path;
	}
	
	public static String getPrintFileStorePath() throws Exception {
		String suffix = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, PRINT_FILE_STORE_PATH);
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + suffix;
		logger.info("PrintFileStorePath[" + path + "]");
		return path;
	}
}
