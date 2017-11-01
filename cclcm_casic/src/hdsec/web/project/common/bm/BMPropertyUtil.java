package hdsec.web.project.common.bm;

import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * 保密平台新增，获取属性及路径的工具类
 * 
 * @author gaoximin 2015-7-6
 */
public class BMPropertyUtil extends PropertyUtil {
	private static Logger logger = Logger.getLogger(BMPropertyUtil.class);
	/**
	 * 本地存储路径
	 */
	public static final String UPLOAD_Learning_FILE_STORE_PATH = "upload.learning.file.store.path";
	public static final String UPLOAD_ACTIVITY_FILE_STORE_PATH = "upload.activity.file.store.path";
	public static final String SECPLACE_FILE_TEMP_PATH = "secplace.file.temp.path";
	public static final String SECPLACE_FILE_STORE_PATH = "secplace.file.store.path";

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
	 * 上传涉密场所文件本地缓存路径 2015-6-29 下午15:03:03
	 * 
	 * @author liuyaling
	 * @return
	 * @throws Exception
	 */
	public static String getSecplaceFileTempPath() throws Exception {
		String suffix = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, SECPLACE_FILE_TEMP_PATH);
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + suffix;
		logger.info("SecplaceFileTempPath[" + path + "]");
		return path;
	}

	/**
	 * 涉密场所文件本地存储路径 2015-6-29 下午15:03:03
	 * 
	 * @author liuyaling
	 * @return
	 * @throws Exception
	 */
	public static String getSecplaceFileStorePath() throws Exception {
		// String suffix = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, SECPLACE_FILE_STORE_PATH);
		// String localPath = getLocalPath();
		// String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + suffix;
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + "files/secplace";
		logger.info("SecplaceFileStorePath[" + path + "]");
		return path;
	}

	/**
	 * 保密学习材料本地存储路径
	 * 
	 * @author lishu
	 * @return
	 * @throws Exception
	 */
	public static String getLearningFileStorePath() throws Exception {
		String suffix = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, UPLOAD_Learning_FILE_STORE_PATH);
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + suffix;
		logger.info("LearningFileStorePath[" + path + "]");
		return path;
	}

	/**
	 * 上传涉密活动附件文件路径
	 * 
	 * @author guojiao
	 * @return
	 * @throws Exception
	 */
	public static String getSecActivityStrorePath() throws Exception {
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + "files" + "/" + "activity";
		logger.info("ActivityFileStorePath[" + path + "]");
		return path;
	}

	/**
	 * 上传保密管理附件文件路径
	 * 
	 * @author guojiao
	 * @return
	 * @throws Exception
	 */
	public static String getSecManageStrorePath() throws Exception {
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + "files" + "/" + "secmanage";
		logger.info("SecmanageFileStorePath[" + path + "]");
		return path;
	}

	/** 上传涉密人员中相关附件 **/
	public static String getSecUserInfoSepcialFilePath() throws Exception {
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + "files" + "/" + "securityuser";
		logger.info("SecurityuserFileStorePath[" + path + "]");
		return path;
	}

	/**
	 * 上传涉密活动附件文件路径
	 * 
	 * @author lishu
	 * @return
	 * @throws Exception
	 */
	public static String getReportStrorePath() throws Exception {
		String localPath = getLocalPath();
		String path = localPath.substring(0, localPath.indexOf("WEB-INF")) + "files" + "/" + "report";
		logger.info("ReportFileStorePath[" + path + "]");
		return path;
	}

	/**
	 * 打印文件:PRINTPAPER 涉密图纸:PRINTGRAPH 复印文件:COPY 外来文件:ENTERPAPER 保密手册:SECBOOK 刻录光盘:BURN 外来光盘:ENTERCD 涉密移动载体:DEVICE
	 * 涉密存储介质:STORAGE 涉密活动中使用的涉密载体:ACTIVITY 2014-4-29 下午12:48:46
	 * 
	 * @author renmingfei
	 * 
	 * @author gxm 201506 ：资产：PROPERTY
	 * @author gxm 201507 ：计算机：COMPUTER，涉密场所：SECPLACE
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
			case "DEVICE":
				return "X";
			case "STORAGE":
				return "Y";
			case "ACTIVITY":
				return "C";
			case "PROPERTY":
				return "P";
			case "COMPUTER":
				return "J";
			case "SECPLACE":
				return "S";
			default:
				return "";
			}
		} else {
			return "";
		}
	}
}
