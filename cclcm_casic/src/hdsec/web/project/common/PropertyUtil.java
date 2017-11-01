package hdsec.web.project.common;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * 获取属性及路径的工具类 2014-4-29 下午4:13:11
 * 
 * @author renmingfei
 */
public class PropertyUtil {
	private static Logger logger = Logger.getLogger(PropertyUtil.class);
	/**
	 * 本地存储路径
	 */
	public static final String BURN_FILE_TEMP_PATH = "burn.file.temp.path";
	public static final String BURN_FILE_STORE_PATH = "burn.file.store.path";
	public static final String IMPORT_FILE_STORE_PATH = "import.file.store.path";
	public static final String PRINT_FILE_STORE_PATH = "print.file.store.path";
	public static final String UPLOAD_VERSION_FILE_TEMP_PATH = "upload.version.file.temp.path";
	public static final String UPLOAD_VERSION_FILE_STORE_PATH = "upload.version.file.store.path";
	public static final String UPLOAD_SETUP_FILE_STORE_PATH = "upload.setup.file.store.path";
	public static final String UPLOAD_PERMISSION_FILE_TEMP_PATH = "upload.permission.file.temp.path";
	public static final String UPLOAD_PERMISSION_FILE_STORE_PATH = "upload.permission.file.store.path";
	public static final String NAS_BURN_FILE_STORE_URL = "nas.burn.file.store.url";
	public static final String NAS_BURN_FILE_STORE_USERNAME = "nas.burn.file.store.username";
	public static final String NAS_BURN_FILE_STORE_PASSWORD = "nas.burn.file.store.password";
	public static final String LDAP_URL = "ldap.url";
	public static final String LDAP_USERNAME = "ldap.username";
	public static final String LDAP_PASSWORD = "ldap.password";
	public static final String LDAP_SEARCH_BASE = "ldap.search.base";
	public static final String SCHEDULER_TIME = "scehduler.time";
	public static final String WS_URL = "ws.url";
	public static final String SCAN_FILE_TEMP_PATH = "scan.file.temp.path";
	public static final String SCAN_FILE_STORE_PATH = "scan.file.store.path";
	public static final String UPLOAD_SEND_FILE_PATH = "upload.send.file.path";
	public static final String INPUT_EFILE_TEMP_PATH = "input.efile.temp.path";
	public static final String INPUT_EFILE_STORE_PATH = "input.efile.store.path";
	// 邦辰DLP平台提交检索请求与查询请求url配置项
	public static final String DLP_COMMIT_URL = "dlp.commiturl";
	public static final String DLP_QUERY_URL = "dlp.queryurl";
	public static final String DLP_IS_OPEN = "dlp.isopen";
	public static final String DLP_BURN_DOWNLOAD_URL = "dlp.burndownloadurl";

	private static String getLocalPath() throws Exception {
		String localPath = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath();
		if (localPath.indexOf("WEB-INF") == -1) {
			logger.error("WEB工程本地部署路径中不包含WEB-INF");
			throw new Exception("WEB工程本地部署路径中不包含WEB-INF");
		} else {
			return localPath;
		}
	}

	/**
	 * 上传刻录文件本地缓存路径 2014-4-29 下午12:44:23
	 * 
	 * @author renmingfei
	 * @return
	 * @throws Exception
	 */
	public static String getBurnFileTempPath() throws Exception {
		String suffix = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, BURN_FILE_TEMP_PATH);
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + suffix;
		logger.info("BurnFileTempPath[" + path + "]");
		return path;
	}

	/**
	 * 刻录文件本地存储路径 2014-4-29 下午12:45:07
	 * 
	 * @author renmingfei
	 * @return
	 * @throws Exception
	 */
	public static String getBurnFileStorePath() throws Exception {
		String suffix = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, BURN_FILE_STORE_PATH);
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + suffix;
		logger.info("BurnFileStorePath[" + path + "]");
		return path;
	}

	/**
	 * 导入文件本地存储路径 2014-4-29 下午12:45:26
	 * 
	 * @author renmingfei
	 * @return
	 * @throws Exception
	 */
	public static String getImportFileStorePath() throws Exception {
		String suffix = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, IMPORT_FILE_STORE_PATH);
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + suffix;
		logger.info("ImportFileStorePath[" + path + "]");
		return path;
	}

	/**
	 * 打印文件本地存储路径 2014-4-29 下午12:45:50
	 * 
	 * @author renmingfei
	 * @return
	 * @throws Exception
	 */
	public static String getPrintFileStorePath() throws Exception {
		String suffix = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, PRINT_FILE_STORE_PATH);
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + suffix;
		logger.info("PrintFileStorePath[" + path + "]");
		return path;
	}

	/**
	 * 常用文档下载本地存储路径
	 * 
	 * @author renmingfei
	 * @return
	 * @throws Exception
	 */
	public static String getDownloadFileStorePath() throws Exception {
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + "download";
		logger.info("DownloadFileStorePath[" + path + "]");
		return path;
	}

	/**
	 * 本单位的单位编码，在配置文件中使用unit.code指定 2014-4-29 下午12:46:38
	 * 
	 * @author renmingfei
	 * @return
	 * @throws Exception
	 */
	public static String getUnitCode() throws Exception {
		String unitCode = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, CCLCMConstants.UNIT_CODE);
		if (StringUtils.hasLength(unitCode) && unitCode.trim().length() == 4) {
			return unitCode.trim();
		} else {
			throw new Exception("配置文件中未设置单位编码或者单位编码长度不是4位");
		}
	}

	/**
	 * 打印文件:PRINTPAPER 涉密图纸:PRINTGRAPH 复印文件:COPY 外来文件:ENTERPAPER 科研工作手册:SECBOOK 刻录光盘:BURN 外来光盘:ENTERCD 涉密移动载体:DEVICE
	 * 涉密存储介质:STORAGE 涉密活动中使用的涉密载体:ACTIVITY 2014-4-29 下午12:48:46
	 * 
	 * @author renmingfei
	 * @param entityType
	 *            如果传入null或者空字符串或者不在上述枚举中的值，则返回空字符串
	 * @return
	 */
	public static String getEntityTypeKey(String entityType) {
		if (StringUtils.hasLength(entityType)) {
			switch (entityType.toUpperCase()) {
			case "PRINTPAPER":
				return "D";
			case "PRINTGRAPH":
				return "T";
			case "COPY":
				return "F";
			case "ENTERPAPER":
				return "W";
			case "SECBOOK":
				return "B";
			case "BURN":
				return "G";
			case "ENTERCD":
				return "Z";
			case "SPACECD":
				return "G";
			case "DEVICE":
				return "X";
			case "STORAGE":
				return "Y";
			case "ACTIVITY":
				return "C";
			default:
				return "";
			}
		} else {
			return "";
		}
	}

	/**
	 * 上传版本文件本地缓存路径
	 * 
	 * @author yy
	 * @return
	 * @throws Exception
	 */
	public static String getUploadVersionFileTempPath() throws Exception {
		String suffix = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, UPLOAD_VERSION_FILE_TEMP_PATH);
		String localPath = getLocalPath();
		String path = localPath.substring(1, localPath.indexOf("WEB-INF")) + suffix;
		logger.info("UploadVersionFileTempPath[" + path + "]");
		return path;
	}

	/**
	 * 上传外发回执单文件路径
	 * 
	 * @author congxue
	 * @return
	 * @throws Exception
	 */
	public static String getUploadSendFilePath() throws Exception {
		String suffix = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, UPLOAD_SEND_FILE_PATH);
		String localPath = getLocalPath();
		String path = localPath.substring(1, localPath.indexOf("WEB-INF")) + suffix;
		logger.info("UploadSendFilePath[" + path + "]");
		return path;
	}

	/**
	 * 版本文件本地存储路径
	 * 
	 * @author yy
	 * @return
	 * @throws Exception
	 */
	public static String getUploadVersionFileStorePath() throws Exception {
		String suffix = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, UPLOAD_VERSION_FILE_STORE_PATH);
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + suffix;
		logger.info("UploadVersionFileStorePath[" + path + "]");
		return path;
	}

	/**
	 * 版本文件相对存储路径
	 * 
	 * @author yy
	 * @return
	 * @throws Exception
	 */
	public static String getRelUploadVersionFileStorePath() throws Exception {
		String suffix = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, UPLOAD_VERSION_FILE_STORE_PATH);
		logger.info("RelUploadVersionFileStorePath[" + suffix + "]");
		return suffix;
	}

	/**
	 * 安装包本地存储路径
	 * 
	 * @author gaoxm
	 * @return
	 * @throws Exception
	 */
	public static String getUploadSetupFileStorePath() throws Exception {
		String suffix = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, UPLOAD_SETUP_FILE_STORE_PATH);
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + suffix;
		logger.info("UploadSetupFileStorePath[" + path + "]");
		return path;
	}

	/**
	 * 安装包相对存储路径
	 * 
	 * @author gaoxm
	 * @return
	 * @throws Exception
	 */
	public static String getRelUploadSetupFileStorePath() throws Exception {
		String suffix = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, UPLOAD_SETUP_FILE_STORE_PATH);
		logger.info("RelUploadSetupFileStorePath[" + suffix + "]");
		return suffix;
	}

	/**
	 * 上传版本文件本地缓存路径
	 * 
	 * @author yy
	 * @return
	 * @throws Exception
	 */
	public static String getUploadPermissionFileTempPath() throws Exception {
		String suffix = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, UPLOAD_PERMISSION_FILE_TEMP_PATH);
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + suffix;
		logger.info("UploadVersionFileTempPath[" + path + "]");
		return path;
	}

	/**
	 * 上传版本文件本地缓存路径
	 * 
	 * @author yy
	 * @return
	 * @throws Exception
	 */
	public static String getUploadPermissionFilePath() throws Exception {
		String suffix = PropertiesFileUtil
				.getProperty(Constants.PROJECT_CONFIG_FILE, UPLOAD_PERMISSION_FILE_STORE_PATH);
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + suffix;
		return path;
	}

	/**
	 * 扫描文件本地存储路径
	 * 
	 * @author gaoxm
	 * @return
	 * @throws Exception
	 */
	public static String getScanFileStorePath(String event_code) throws Exception {
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + "files" + "/" + "scan" + "/" + event_code;
		logger.info("ScanFileStorePath[" + path + "]");
		return path;
	}

	/**
	 * 导入电子文件本地缓存(临时)路径 2016-9-25
	 * 
	 * @author guoxh
	 * @return
	 * @throws Exception
	 */
	public static String getInputFileTempPath() throws Exception {
		String suffix = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, INPUT_EFILE_TEMP_PATH);
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + suffix;
		logger.info("InputEFileTempPath[" + path + "]");
		return path;
	}

	/**
	 * 导入电子文件本地存储路径 2016-9-25
	 * 
	 * @author guoxh
	 * @return
	 * @throws Exception
	 */
	public static String getInputFileStorePath() throws Exception {
		String suffix = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, INPUT_EFILE_STORE_PATH);
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + suffix;
		logger.info("InputEFileStorePath[" + path + "]");
		return path;
	}

	/**
	 * 输入文件存储路径
	 * 
	 * @param event_code
	 * @return
	 * @throws Exception
	 */
	public static String getInputFileStorePath(String event_code) throws Exception {
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + "files" + "/" + "input" + "/" + event_code;
		logger.info("InputFileStorePath[" + path + "]");
		return path;
	}

	public static String getNasBurnFileStoreUrl() throws Exception {
		return PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, NAS_BURN_FILE_STORE_URL);
	}

	public static String getNasBurnFileStoreUsername() throws Exception {
		return PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, NAS_BURN_FILE_STORE_USERNAME);
	}

	public static String getNasBurnFileStorePassword() throws Exception {
		return PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, NAS_BURN_FILE_STORE_PASSWORD);
	}

	public static String getLdapURL() throws Exception {
		return PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, LDAP_URL);
	}

	public static String getLdapUsername() throws Exception {
		return PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, LDAP_USERNAME);
	}

	public static String getLdapPassword() throws Exception {
		return PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, LDAP_PASSWORD);
	}

	public static String getLdapSearchBase() throws Exception {
		return PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, LDAP_SEARCH_BASE);
	}

	public static String getSchedulerTime() throws Exception {
		return PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, SCHEDULER_TIME);
	}

	public static String getWSURL() throws Exception {
		return PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, WS_URL);
	}

	/**
	 * 本单位的条码版本标识
	 * 
	 * @author yy
	 * @return
	 * @throws Exception
	 */
	public static String getUnitBarcode() throws Exception {
		String unitCode = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, CCLCMConstants.UNIT_BARCODE);
		if (StringUtils.hasLength(unitCode)) {
			return unitCode.trim();
		} else {
			throw new Exception("配置文件中未设置单位编码");
		}
	}

	/**
	 * 本单位的名称
	 * 
	 * @author yy
	 * @return
	 * @throws Exception
	 */
	public static String getUnitName() throws Exception {
		String unitName = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, CCLCMConstants.UNIT_NAME);
		if (StringUtils.hasLength(unitName)) {
			return unitName.trim();
		} else {
			throw new Exception("配置文件中未设置单位名称");
		}
	}

	/**
	 * OA特殊打印文件本地存储路径
	 * 
	 * @author guojiao
	 * @return
	 * @throws Exception
	 */
	public static String getOaFileStorePath() throws Exception {
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + "files" + "/" + "Sepcial";
		logger.info("OaFileStorePath[" + path + "]");
		return path;
	}

	/**
	 * 邦辰dlp平台提交检索请求url
	 * 
	 * @author haojia
	 * @return
	 * @throws Exception
	 */
	public static String getDlpCommitUrl() throws Exception {
		String commiturl = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, DLP_COMMIT_URL);
		if (StringUtils.hasLength(commiturl)) {
			return commiturl.trim();
		} else {
			throw new Exception("配置文件中未设置DLP平台提交检索请求url地址");
		}
	}

	/**
	 * 邦辰dlp平台提交查询请求url
	 * 
	 * @author haojia
	 * @return
	 * @throws Exception
	 */
	public static String getDlpQueryUrl() throws Exception {
		String queryurl = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, DLP_QUERY_URL);
		if (StringUtils.hasLength(queryurl)) {
			return queryurl.trim();
		} else {
			throw new Exception("配置文件中未设置DLP平台查询结果请求url地址");
		}
	}

	/**
	 * 邦辰dlp集成标志
	 * 
	 * @author haojia
	 * @return
	 * @throws Exception
	 */
	public static String getDlpIsOpenUrl() throws Exception {
		String isopenpath = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, DLP_IS_OPEN);
		if (StringUtils.hasLength(isopenpath)) {
			return isopenpath.trim();
		} else {
			throw new Exception("配置文件中未设置DLP集成标志");
		}
	}

	/**
	 * 航盾服务IP 刻录文件获取URL
	 * 
	 * @author haojia
	 * @return
	 * @throws Exception
	 */
	public static String getDlpBurnDownloadIPUrl() throws Exception {
		String queryurl = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, DLP_BURN_DOWNLOAD_URL);
		if (StringUtils.hasLength(queryurl)) {
			return queryurl.trim();
		} else {
			throw new Exception("配置文件中未设置航盾刻录文件获取URL");
		}
	}

}
