package hdsec.web.project.user.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

/**
 * 验证授权
 * 
 * @author yy
 * 
 */
public class ServerValidation {
	private static Logger logger = Logger.getLogger(ServerValidation.class);
	private static Runtime rt = Runtime.getRuntime();

	private static String getPermissionPath() throws Exception {
		String localPath = getLocalPath();
		return localPath.substring(0, localPath.indexOf("WEB-INF")) + "permission";
	}

	private static String getLocalPath() throws Exception {
		String localPath = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath();
		if (localPath.indexOf("WEB-INF") == -1) {
			logger.error("WEB工程本地部署路径中不包含WEB-INF");
			throw new Exception("WEB工程本地部署路径中不包含WEB-INF");
		} else {
			return localPath;
		}
	}

	private static String getLicFilePath() throws Exception {
		return getPermissionPath() + File.separator + "License.copyright";
	}

	private static String getOldLicFilePath() throws Exception {
		return getPermissionPath() + File.separator + "License.copyright.old";
	}

	public static boolean isLicFileExist() throws Exception {
		File licenseFile = new File(getLicFilePath());
		return licenseFile.exists();
	}

	public static boolean isOldLicFileExist() throws Exception {
		File licenseFile = new File(getOldLicFilePath());
		return licenseFile.exists();
	}

	public static boolean transmitLicFile() throws Exception {
		if (isLicFileExist()) {
			if (isOldLicFileExist()) {// 若存在old授权文件，则先备份之
				File oldLicFile = new File(getOldLicFilePath());
				oldLicFile.renameTo(new File(getOldLicFilePath()
						+ new SimpleDateFormat("yyyy-MM-dd_HH_mm").format(new Date()) + ".bk"));
			}
			File licFile = new File(getLicFilePath());
			return licFile.renameTo(new File(getOldLicFilePath()));
		}
		return false;
	}

	/**
	 * 读取注册码，如果已经激活完毕，则返回1
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getLicenseResult() throws Exception {
		StringBuilder builder = new StringBuilder();
		String line;
		ServerValidation.class.getResource("/");
		Process pro = rt.exec(getPermissionPath() + "\\VerifyActivateInfo.exe");
		BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
		while ((line = br.readLine()) != null) {
			builder.append(line);
		}
		return builder.toString();
	}

	/**
	 * 读取license文件，格式为PNUM|DATE|HSNUM
	 * 
	 * @return
	 * @throws Exception
	 */
	private static String readLicenseFile() throws Exception {
		StringBuilder builder = new StringBuilder();
		String line;
		Process pro = rt.exec(getPermissionPath() + "\\ReadLicenseFile.exe");
		BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
		while ((line = br.readLine()) != null) {
			builder.append(line);
		}
		return builder.toString();
	}

	private static String readOldLicenseFile() throws Exception {
		StringBuilder builder = new StringBuilder();
		String line;
		Process pro = rt.exec(getPermissionPath() + "\\ReadOldLicenseFile.exe");
		BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
		while ((line = br.readLine()) != null) {
			builder.append(line);
		}
		return builder.toString();
	}

	/**
	 * 判断注册码和激活码是否匹配
	 * 
	 * @param register_code
	 * @param activation_code
	 * @return
	 * @throws Exception
	 */
	public static boolean verifyActivateCode(String register_code, String activation_code) throws Exception {
		StringBuilder builder = new StringBuilder();
		String line;
		Process pro = rt
				.exec(getPermissionPath() + "\\VerifyActivateCode.exe " + register_code + " " + activation_code);
		BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
		while ((line = br.readLine()) != null) {
			builder.append(line);
		}
		if (builder.toString().equals("1")) {
			return true;
		}
		return false;
	}

	/**
	 * 获取可用用户数
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Integer getAvailablePNum() throws Exception {
		String res = readLicenseFile();
		if (res != null && !res.equals("0")) {
			return Integer.valueOf(res.substring(0, res.indexOf("|")));
		} else {
			res = readOldLicenseFile();
			if (res != null && !res.equals("0")) {
				return Integer.valueOf(res.substring(0, res.indexOf("|")));
			} else {
				logger.error("解析可用用户数时发生异常:Can't get available number of users.License File ERROR or wrong returned info["
						+ res + "]");
				throw new Exception("解析可用用户数时发生异常");
			}
		}
	}

	/**
	 * 获取到期日期
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getLimitDate() throws Exception {
		String res = readLicenseFile();
		if (res != null && !res.equals("0")) {
			String[] resArr = res.split("|");
			if (resArr.length == 3) {
				return res.substring(res.indexOf("|") + 1, res.lastIndexOf("|"));
			} else {
				return res.substring(res.lastIndexOf("|") + 1);
			}
		} else {
			res = readOldLicenseFile();
			if (res != null && !res.equals("0")) {
				String[] resArr = res.split("|");
				if (resArr.length == 3) {
					return res.substring(res.indexOf("|") + 1, res.lastIndexOf("|"));
				} else {
					return res.substring(res.lastIndexOf("|") + 1);
				}
			} else {
				logger.error("解析到期日期时发生异常:Can't get limit date permitted. License File ERROR or wrong returned info["
						+ res + "]");
				throw new Exception("解析到期日期时发生异常");
			}
		}
	}

	/**
	 * 获取模块开启信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getModuleStr() throws Exception {
		StringBuilder builder = new StringBuilder();
		String line;
		try {
			Process pro = rt.exec(getPermissionPath() + "\\ReadLicenseModule.exe");
			BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			while ((line = br.readLine()) != null) {
				builder.append(line);
			}
			return builder.toString();
		} catch (Exception e) {
			logger.error("解析模块信息时发生异常:Can't get module info. License File ERROR or wrong returned info["
					+ builder.toString() + "]");
			throw new Exception("解析模块信息时发生异常");
		}
	}

	/**
	 * 获取原模块开启信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getOldModuleStr() throws Exception {
		StringBuilder builder = new StringBuilder();
		String line;
		try {
			Process pro = rt.exec(getPermissionPath() + "\\ReadOldLicenseModule.exe");
			BufferedReader br = new BufferedReader(new InputStreamReader(pro.getInputStream()));
			while ((line = br.readLine()) != null) {
				builder.append(line);
			}
			return builder.toString();
		} catch (Exception e) {
			logger.error("解析原模块信息时发生异常:Can't get old module info. License File ERROR or wrong returned info["
					+ builder.toString() + "]");
			throw new Exception("解析原模块信息时发生异常");
		}
	}

	/**
	 * 获取控制台个数
	 * 
	 * @return
	 * @throws Exception
	 */
	public static Integer getAvailableHsNum() throws Exception {
		String res = readLicenseFile();
		if (res != null && !res.equals("0")) {
			String[] resArr = res.split("|");
			if (resArr.length == 3) {
				return Integer.valueOf(res.substring(res.lastIndexOf("|") + 1));
			} else {
				return null;
			}
		} else {
			res = readOldLicenseFile();
			if (res != null && !res.equals("0")) {
				String[] resArr = res.split("|");
				if (resArr.length == 3) {
					return Integer.valueOf(res.substring(res.lastIndexOf("|") + 1));
				} else {
					return null;
				}
			} else {
				logger.error("解析控制台个数时发生异常:Can't get available number of console. License File ERROR or wrong returned info["
						+ res + "]");
				throw new Exception("解析控制台个数时发生异常");
			}
		}
	}
}
