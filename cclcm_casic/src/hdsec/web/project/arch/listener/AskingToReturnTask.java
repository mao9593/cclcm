package hdsec.web.project.arch.listener;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TimerTask;
import org.apache.log4j.Logger;

/**
 * 添加所有到期档案的催还消息提醒
 * 
 * @author lidepeng 2015-9-9
 */
public class AskingToReturnTask extends TimerTask {
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void run() {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			String driver = PropertiesFileUtil.getProperty(
					Constants.PROJECT_CONFIG_FILE, "jdbc.driver");
			String url = PropertiesFileUtil.getProperty(
					Constants.PROJECT_CONFIG_FILE, "jdbc.url");
			String userName = PropertiesFileUtil.getProperty(
					Constants.PROJECT_CONFIG_FILE, "jdbc.username");
			String password = PropertiesFileUtil.getProperty(
					Constants.PROJECT_CONFIG_FILE, "jdbc.password");
			Class.forName(driver);
			conn = DriverManager.getConnection(url, userName, password);
			if (conn != null) {
				logger.info("JDBC Connection Successful");
				// 删除消息通知表中所有借阅催还通知消息
				String sql = "delete from client_msg where oper_type = 'ASKRETURN'";
				ps = conn.prepareStatement(sql);
				ps.executeUpdate();
				// 查询出所有当前到期的档案借阅记录
				sql = "";
				sql = "select ea.user_iidd ,ea.job_code ,su.user_name from event_archbrw as ea ,sec_user as su where limit_time < getdate() and borrow_status = 1and ea.user_iidd = su.user_iidd ";
				ps = null;
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				while (rs.next()) {
					String user_iidd = rs.getString("user_iidd");
					String job_code = rs.getString("job_code");
					String user_name = rs.getString("user_name");
					sql = "";
					sql = "insert into client_msg(accept_user_iidd,accept_user_name,oper_type,message_type,job_code,message,insert_time,is_read)values('"
							+ user_iidd
							+ "','"
							+ user_name
							+ "','"
							+ "ASKRETURN',"
							+ "2,'"
							+ job_code
							+ "','"
							+ "来自档案管理员：您借阅的档案到期请及时归还！',"
							+ "getdate(),"
							+ "0"
							+ ")";
					ps = null;
					ps = conn.prepareStatement(sql);
					ps.executeUpdate();
				}
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			}
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}

}
