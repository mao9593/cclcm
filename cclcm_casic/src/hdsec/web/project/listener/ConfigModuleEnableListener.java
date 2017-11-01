package hdsec.web.project.listener;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;
import hdsec.web.project.ledger.model.EntityStateEnum;
import hdsec.web.project.user.model.ModuleEnum;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TimeZone;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * 启动tomcat时，加载模块启动信息 2014-5-7 下午6:16:37
 * 
 * @author renmingfei
 */
public final class ConfigModuleEnableListener implements ServletContextListener {
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Connection conn = null;
		TimeZone time = TimeZone.getTimeZone("GMT+8");// 设置为东八区
		TimeZone.setDefault(time);// 设置时区
		try {
			String driver = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "jdbc.driver");
			String url = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "jdbc.url");
			String userName = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "jdbc.username");
			String password = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "jdbc.password");
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userName, password);
			if (conn != null) {
				logger.info("JDBC Connection Successful");
				PreparedStatement ps = conn
						.prepareStatement("select count(1) as count from sys_module where module_enable='Y' and module_code= ? ");
				ResultSet rs = null;
				int count = 0;
				// 循环所有模块，配置开启状态
				for (ModuleEnum item : ModuleEnum.values()) {
					ps.setString(1, item.getModuleCode());
					rs = ps.executeQuery();
					while (rs.next()) {
						count = rs.getInt(1);
					}
					// 配置是否开启
					item.switchModuleEnable(count > 0);
					// 如果该模块开启了，则配置相应的流程类型为可用
					if (count > 0) {
						if (item.getModuleCode().equalsIgnoreCase("BURN")) {
							JobTypeEnum.BURN_FILE.enableUsed();
							JobTypeEnum.BURN_SEND.enableUsed();
							JobTypeEnum.BURN_REMAIN.enableUsed();
							// 有刻录就会有光盘所以要开启光盘模块
							JobTypeEnum.DELAY_CD.enableUsed();
							JobTypeEnum.DESTROY_CD.enableUsed();
							JobTypeEnum.FILE_CD.enableUsed();
							JobTypeEnum.SEND_CD.enableUsed();
							// JobTypeEnum.SENDES_CD.enableUsed();
						} else if (item.getModuleCode().equalsIgnoreCase("PRINT")) {
							JobTypeEnum.PRINT_FILE.enableUsed();
							JobTypeEnum.PRINT_SEND.enableUsed();
							JobTypeEnum.PRINT_REMAIN.enableUsed();
							// 有打印就会有文件处理模块
							JobTypeEnum.DESTROY_PAPER.enableUsed();
							JobTypeEnum.SEND_PAPER.enableUsed();
							JobTypeEnum.DELAY_PAPER.enableUsed();
							JobTypeEnum.FILE_PAPER.enableUsed();
							// JobTypeEnum.SENDES_PAPER.enableUsed();
						} else if (item.getModuleCode().equalsIgnoreCase("COPY")) {
							JobTypeEnum.COPY_FILE.enableUsed();
							JobTypeEnum.COPY_SEND.enableUsed();
							JobTypeEnum.COPY.enableUsed();
						} else if (item.getModuleCode().equalsIgnoreCase("OUTCOPY")) {
							JobTypeEnum.OUTCOPY_FILE.enableUsed();
							JobTypeEnum.OUTCOPY_SEND.enableUsed();
							JobTypeEnum.OUTCOPY_REMAIN.enableUsed();
						} else if (item.getModuleCode().equalsIgnoreCase("LEADIN")) {
							JobTypeEnum.LEADIN.enableUsed();
							JobTypeEnum.SCAN_LEADIN.enableUsed();
						} else if (item.getModuleCode().equalsIgnoreCase("DEVICE")) {
							// 磁介质
							JobTypeEnum.DEVICE.enableUsed();
							JobTypeEnum.DESTROY_DEVICE.enableUsed();
						} else if (item.getModuleCode().equalsIgnoreCase("MODIFY_SECLV")) {
							// 密级变更
							JobTypeEnum.MODIFY_SECLV.enableUsed();
						} else if (item.getModuleCode().equalsIgnoreCase("INPUT")) {
							// 电子文件导入
							JobTypeEnum.MSG_INPUT.enableUsed();
						} else {
							try {
								JobTypeEnum.valueOf(item.getModuleCode()).enableUsed();
							} catch (Exception e) {
								logger.error(e.getMessage());
							}
						}
					}
					rs.close();
					if (item.isModuleEnable()) {
						if (item.getModuleCode().equalsIgnoreCase("TRANSFER")) {
							EntityStateEnum.STATE5.enableUsed();
							logger.info("Entity state 5 for TRANSFER enabled");
						} else if (item.getModuleCode().equalsIgnoreCase("BORROW")) {
							EntityStateEnum.STATE6.enableUsed();
							logger.info("Entity state 6 for BORROW enabled");
						} else if (item.getModuleCode().equalsIgnoreCase("CONFIRM")) {
							EntityStateEnum.STATE10.enableUsed();
							logger.info("Entity state 10 for CONFIRM enabled");
						}
					}
				}
				if (rs != null) {
					rs.close();
				}
			}
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		} catch (SQLException e) {
			logger.error(e.getMessage());
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
