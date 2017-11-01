package hdsec.web.project.listener;

import hdsec.web.project.common.FileEncryptionUtil;
import hdsec.web.project.common.model.CopyrightLicense;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;
import hdsec.web.project.jdbc.ScriptRunner;
import hdsec.web.project.user.action.ServerValidation;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * 授权文件解析监听器
 * 
 * @author renmingfei 2014-8-26
 */
public class ReadLicenseListener implements ServletContextListener {
	private Logger logger = Logger.getLogger(this.getClass());
	private String driver = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "jdbc.driver");
	private String url = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "jdbc.url");
	private String userName = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "jdbc.username");
	private String password = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "jdbc.password");

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	/**
	 * 先判断授权文件是否存在，如果存在则进行授权操作。并把授权文件转换为old状态 如果不存在，则跳过授权操作。
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		boolean isLicFileExist = false;
		try {
			CopyrightLicense.skipLicense = isSkipLicense();
			logger.info("Lic:Skip " + CopyrightLicense.skipLicense);
			CopyrightLicense.registered = isSystemActivated();
			logger.info("Lic:Reg " + CopyrightLicense.registered);
			CopyrightLicense.pNum = getUserNum();
			logger.info("Lic:Users " + CopyrightLicense.pNum);
			CopyrightLicense.limitDate = getLimitDate();
			logger.info("Lic:Date " + CopyrightLicense.limitDate);
			isLicFileExist = ServerValidation.isLicFileExist();
			CopyrightLicense.hsNum = getHsNum();
			logger.info("Lic:HSNum" + CopyrightLicense.hsNum);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		if (isLicFileExist) {
			try {
				handleModuleUsage();
			} catch (Exception e) {
				logger.error("解析授权文件时出错：" + e.getMessage());
			}
		} else {
			logger.info("Lic:File not exists. Starting tomcat normally.");
		}
	}

	private boolean isSkipLicense() {
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userName, password);
			String nnvlPwd = "";
			if (conn != null) {
				logger.info("Create JDBC Connection Successful");
				PreparedStatement ps = conn
						.prepareStatement("select item_value from sec_config where item_key='SKIP_LIC'");
				ResultSet rs = null;
				rs = ps.executeQuery();
				while (rs.next()) {
					nnvlPwd = rs.getString(1);
				}
			}
			if (nnvlPwd.equals("HaNgDUnSafE")) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		return false;
	}

	/**
	 * 如果已经激活，则记录激活状态为true
	 * 
	 * @return
	 * @throws Exception
	 */
	private boolean isSystemActivated() throws Exception {
		return ServerValidation.getLicenseResult().equals("1");
	}

	/**
	 * 获取用户点数
	 * 
	 * @return
	 * @throws Exception
	 */
	private Integer getUserNum() {
		Integer userNum = 0;
		try {
			userNum = ServerValidation.getAvailablePNum();
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		return userNum;
	}

	/**
	 * 获取系统到期日期
	 * 
	 * @return
	 * @throws Exception
	 */
	private String getLimitDate() throws Exception {
		String limitDate = "";
		try {
			limitDate = ServerValidation.getLimitDate();
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		return limitDate;
	}

	/**
	 * 处理模块的开启
	 * 
	 * @throws Exception
	 */
	private void handleModuleUsage() throws Exception {
		CopyrightLicense.moduleStr = ServerValidation.getModuleStr();
		logger.info("Lic:Mstr " + CopyrightLicense.moduleStr);
		if (CopyrightLicense.moduleStr.length() < 10) {
			throw new Exception("授权文件模块信息异常：[" + CopyrightLicense.moduleStr + "]");
		} else {
			String oldModuleStr = ServerValidation.getOldModuleStr();
			// oldModuleStr为0说明不存在原许可文件，可能是第一次部署或者原许可文件人为删除了,
			// oldModuleStr的长度小于新的moduleStr的长度，说明可能增加了新的模块
			// 把oldModuleStr的长度跟moduleStr补齐
			if (oldModuleStr.length() < CopyrightLicense.moduleStr.length()) {
				for (int i = oldModuleStr.length(); i < CopyrightLicense.moduleStr.length(); i++) {
					oldModuleStr += "0";
				}
			}
			CopyrightLicense.oldModuleStr = oldModuleStr;
			logger.info("Lic:OMstr " + CopyrightLicense.oldModuleStr);
			// 比较新老许可文件，判断是否需要开关模块
			Connection conn = null;
			Savepoint sp = null;
			try {
				Class.forName(driver).newInstance();
				conn = DriverManager.getConnection(url, userName, password);
				conn.setAutoCommit(false);
				sp = conn.setSavepoint("startLic");
				ScriptRunner runner = new ScriptRunner(conn);
				runner.setErrorLogWriter(null);
				runner.setLogWriter(null);
				runner.setAutoCommit(false);
				// runner.setStopOnError(true);
				runner.setStopOnError(false);
				FileInputStream fis = null;
				InputStreamReader isr = null;
				for (int mIndex = 0; mIndex < CopyrightLicense.moduleStr.length(); mIndex++) {
					if (CopyrightLicense.moduleStr.charAt(mIndex) - CopyrightLicense.oldModuleStr.charAt(mIndex) == 1) {
						// 解密对应的sql文件
						try {
							logger.info("execute " + mIndex + "_module.sql");
							FileEncryptionUtil.decryptFile(getLocalPath() + "hdsec/web/project/listener/sql/" + mIndex
									+ "_module.sql", getLocalPath() + "hdsec/web/project/listener/initialize/" + mIndex
									+ "_module.sql");
							// 执行对应的sql脚本
							fis = new FileInputStream(getLocalPath() + "hdsec/web/project/listener/initialize/"
									+ mIndex + "_module.sql");
							isr = new InputStreamReader(fis, "UTF-8");
							runner.runScript(isr);
						} catch (Exception e) {
							logger.error(e.getMessage());
						} finally {
							// 删除临时sql
							fis.close();
							isr.close();
							File tempSql = new File(getLocalPath() + "hdsec/web/project/listener/initialize/" + mIndex
									+ "_module.sql");
							if (tempSql != null && tempSql.delete()) {
								logger.info("delete " + mIndex + "_module.sql");
							}
						}
					}
				}
				conn.commit();
				runner.closeConnection();
				ServerValidation.transmitLicFile();
				logger.error("授权文件加载完成。");
			} catch (Exception e) {
				logger.error(e.getMessage());
				conn.rollback(sp);
				logger.error("授权文件加载出错，数据库回滚。");
				// e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			}
		}
	}

	private String getLocalPath() throws Exception {
		String localPath = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath();
		return localPath;
	}

	/**
	 * 获取控制台个数
	 * 
	 * @return
	 * @throws Exception
	 */
	private Integer getHsNum() {
		Integer hsNum = 0;
		try {
			hsNum = ServerValidation.getAvailableHsNum();
		} catch (Exception e) {
			logger.warn(e.getMessage());
		}
		return hsNum;
	}
}
