package hdsec.web.project.domain;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * 用户部门查询及操作工具类
 * 
 * @author renmingfei 20150119
 * 
 */
public class UserUtil {
	private final Logger logger = Logger.getLogger(this.getClass());
	private static String url = null;
	private static String userName = null;
	private static String password = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private Connection conn = null;
	static {
		url = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "jdbc.url");
		userName = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "jdbc.username");
		password = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "jdbc.password");
	}

	public UserUtil() {
		super();
	}

	/**
	 * 同步之前先重置用户和部门的同步状态（ext_code）
	 */
	public void resetExtCode() {
		String sql = null;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			sql = "update sec_dept set ext_code='0' where dept_id != '01'";// 重置除‘九部’外的其他部门的同步状态
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			sql = "update sec_user set ext_code='0' where user_iidd not in('admin','sysadmin1','sysadmin2','secadmin1','secadmin2','audadmin1','audadmin2')";// 重置除管理员外的其他用户的同步状态
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
			close();
		}
	}

	/**
	 * 判断部门是否已经存在
	 * 
	 * @param deptId
	 *            部门ID（GUID）
	 * @return
	 */
	public boolean checkDeptIsExists(String deptId) {
		String sql = null;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			if (StringUtils.hasLength(deptId)) {
				sql = "select * from sec_dept where dept_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, deptId);
			}
			rs = ps.executeQuery();
			if (null != rs && rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
			close();
		}
		return false;
	}

	/**
	 * sql查询执行完成后，对相关连接进行关闭操作，防止资源泄漏 renmingfei 20150120
	 */
	private void close() {
		if (rs != null) {
			try {
				rs.close();
				rs = null;
			} catch (SQLException e) {
				logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
			}
		}
		if (ps != null) {
			try {
				ps.close();
				ps = null;
			} catch (SQLException e) {
				logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
			}
		}
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException e) {
				logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
			}
		}
	}

	/**
	 * 获取新建部门的部门编号
	 * 
	 * @return
	 */
	private String getNewDeptCs() {
		String sql = null;
		String oldCs = null;
		String temp1 = "";
		String temp2 = "";
		String temp3 = "";
		String temp4 = "";
		try {
			conn = DriverManager.getConnection(url, userName, password);
			sql = "select dept_cs from sec_dept where len(dept_cs)=4 order by dept_cs desc";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				oldCs = rs.getString("dept_cs");
				logger.debug("oldCs is " + oldCs);
			}
			if (StringUtils.hasLength(oldCs)) {
				temp1 = oldCs;
				temp2 = temp1.substring(temp1.length() - 2);
				temp3 = temp1.substring(0, temp1.length() - 2);
				int a = Integer.parseInt(temp2) + 1;
				if (a < 10)
					temp2 = "0" + a;
				else {
					temp2 = String.valueOf(a);
				}
				temp4 = temp3 + temp2;
			} else {// 如果没有长度为4的部门编号，说明系统中还没有建立任何部门，则直接设置第一个部门的编号为0101
				temp4 = "0101";
			}
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
			close();
		}
		return temp4;
	}

	/**
	 * 添加新部门
	 * 
	 * @param dept
	 * @throws Exception
	 */
	public void addNewDept(DeptInfo dept) throws Exception {
		logger.info("addNewDept with dept[" + dept + "]");
		String sql = null;
		String dept_cs = getNewDeptCs();
		String parent_dept_id = "01";// 九部域内只有一层机构，都挂在根机构下，故父部门直接取01即可。
		try {
			conn = DriverManager.getConnection(url, userName, password);
			sql = "insert into sec_dept(dept_id,dept_cs,dept_parent_id,dept_name,dept_level_code,dept_rank,ext_code) values(?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dept.getDeptId());
			ps.setString(2, dept_cs);
			ps.setString(3, parent_dept_id);
			ps.setString(4, dept.getDeptName());
			ps.setString(5, "1");
			ps.setString(6, dept_cs);
			ps.setString(7, "1");
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
			close();
		}
	}

	/**
	 * 通过部门ID查询部门名称
	 * 
	 * @param deptId
	 * @return
	 */
	public String getDeptNameById(String deptId) {
		String sql = null;
		String deptName = null;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			sql = "select dept_name from sec_dept where dept_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, deptId);
			rs = ps.executeQuery();
			if (rs.next()) {
				deptName = rs.getString("dept_name");
			}
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
			close();
		}
		return deptName;
	}

	/**
	 * 检查部门名称是否修改
	 * 
	 * @param deptInfo
	 * @return
	 */
	public boolean checkDeptNameChanged(DeptInfo deptInfo) {
		String sql = null;
		String deptName = null;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			sql = "select dept_name from sec_dept where dept_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, deptInfo.getDeptId());
			rs = ps.executeQuery();
			if (rs.next()) {
				deptName = rs.getString("dept_name");
			}
			return !deptName.equals(deptInfo.getDeptName());
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
			close();
		}
		return false;
	}

	/**
	 * 修改部门名称
	 * 
	 * @param dept
	 */
	public void updateDeptName(DeptInfo dept) {
		logger.info("updateDeptName with dept[" + dept + "]");
		String sql = null;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			sql = "update sec_dept set dept_name = ? where dept_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, dept.getDeptName());
			ps.setString(2, dept.getDeptId());
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
			close();
		}
	}

	/**
	 * 设置部门同步状态为已同步
	 * 
	 * @param deptId
	 */
	public void setDeptSyned(String deptId) {
		logger.info("setDeptSyned with deptId[" + deptId + "]");
		String sql = null;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			sql = "update sec_dept set ext_code = '1' where dept_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, deptId);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
			close();
		}
	}

	/**
	 * 判断用户是否已经存在
	 * 
	 * @param userId
	 * @return
	 */
	public boolean checkUserIsExists(String userId) {
		String sql = null;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			if (StringUtils.hasLength(userId)) {
				sql = "select * from sec_user where user_iidd = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, userId);
			}
			rs = ps.executeQuery();
			if (null != rs && rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
			close();
		}
		return false;
	}

	/**
	 * 检查用户信息是否更改
	 * 
	 * @param user
	 * @return
	 */
	public boolean checkUserInfoChanged(UserInfo user) {
		String sql = null;
		int cnt = 1;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			sql = "select count(1)  cnt from sec_user where user_iidd = ? and user_name = ? and idcard = ? and security_code = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getUserName());
			ps.setString(3, user.getPid());
			ps.setString(4, user.getSecurityCode());
			rs = ps.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
			return cnt < 1;
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
			close();
		}
		return false;
	}

	/**
	 * 修改用户信息
	 * 
	 * @param user
	 */
	public void updateUserInfo(UserInfo user) {
		logger.info("updateUserInfo with user[" + user + "]");
		String sql = null;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			sql = "update sec_user set user_name = ?, idcard = ?, security_code = ? where user_iidd = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPid());
			ps.setString(3, user.getSecurityCode());
			ps.setString(4, user.getUserId());
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
			close();
		}
	}

	/**
	 * 添加新用户
	 * 
	 * @param user
	 */
	public void addNewUser(UserInfo user) {
		logger.info("addNewUser with user[" + user + "]");
		String sql;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			if (!checkRealUserIsExists(user.getUserId(), conn)) {
				sql = "insert into real_user(real_user_id) values(?) ";
				ps = conn.prepareStatement(sql);
				ps.setString(1, user.getUserId());
				ps.executeUpdate();
			}
			sql = "insert into sec_user(user_iidd,real_user_id,user_name,user_ppww,dept_id,idcard,security_code,ext_code,status,user_type) values(?,?,?,?,?,?,?,?,?,?) ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getUserId());
			ps.setString(3, user.getUserName());
			ps.setString(4, "ed317f03cfd878109640cb78acf2ee00"); // '12345678a@'
			ps.setString(5, user.getDeptId());
			ps.setString(6, user.getPid());
			ps.setString(7, user.getSecurityCode());
			ps.setString(8, "1");
			ps.setInt(9, 0);
			ps.setInt(10, 1);
			ps.executeUpdate();
			sql = "insert into sec_role_user(role_id,user_iidd) values('11',?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserId());
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
			close();
		}
	}

	/**
	 * 检查员工信息是否存在
	 * 
	 * @param userId
	 * @return
	 */
	private boolean checkRealUserIsExists(String userId, Connection conn) {
		String sql = null;
		try {
			if (StringUtils.hasLength(userId)) {
				sql = "select * from real_user where real_user_id = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, userId);
			}
			rs = ps.executeQuery();
			if (null != rs && rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		}
		return false;
	}

	/**
	 * 通过部门名称查询部门ID
	 * 
	 * @param deptName
	 * @return
	 */
	public String getDeptIdByName(String deptName) {
		String sql = null;
		String deptId = null;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			sql = "select dept_id from sec_dept where dept_name = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, deptName);
			rs = ps.executeQuery();
			if (rs.next()) {
				deptId = rs.getString("dept_id");
			}
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
			close();
		}
		return deptId;
	}

	/**
	 * 检查用户是否更换了部门
	 * 
	 * @param user
	 * @return
	 */
	public boolean checkUserChangeDept(UserInfo user) {
		String sql = null;
		String deptId = null;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			sql = "select dept_id from sec_user where user_iidd = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserId());
			rs = ps.executeQuery();
			if (rs.next()) {
				deptId = rs.getString("dept_id");
			}
			return !deptId.equals(user.getDeptId());
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
			close();
		}
		return false;
	}

	/**
	 * 更改用户部门
	 * 
	 * @param user
	 */
	public void changeUserDept(UserInfo user) {
		logger.info("changeUserDept with user[" + user + "]");
		String sql = null;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			sql = "update sec_user set dept_id = ? where user_iidd = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getDeptId());
			ps.setString(2, user.getUserId());
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
			close();
		}
	}

	/**
	 * 设置用户同步状态为已同步
	 * 
	 * @param userId
	 */
	public void setUserSyned(String userId) {
		logger.info("setUserSyned with userId[" + userId + "]");
		String sql = null;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			sql = "update sec_user set ext_code = '1' where user_iidd = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
			close();
		}
	}

	/**
	 * 将同步状态为0的用户标记为删除
	 */
	public void markUserDeleted() {
		logger.info("---markUserDeleted");
		int cnt = 31;
		String sql = null;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			// 判断如果一次删除的用户数小于30，则执行标记删除的操作，防止程序异常导致大量用户被误删，影响用户使用
			sql = "select count(1) from sec_user where ext_code = '0' and is_sealed = 'N'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
			if (cnt <= 30) {
				logger.info("waiting deleted user amount is " + cnt);
				sql = "update sec_user set is_sealed = 'Y' where ext_code = '0' and is_sealed = 'N'";
				ps = conn.prepareStatement(sql);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
			close();
		}
	}

	/**
	 * 将同步状态为0并且部门内已无用户的部门标记为删除
	 */
	public void markDeptDeleted() {
		logger.info("---markDeptDeleted");
		String sql = null;
		int cnt = 6;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			// 判断如果一次删除的部门数小于5，则执行标记删除的操作，防止程序异常导致大量部门被误删，影响用户使用
			sql = "select count(1) from sec_dept where ext_code = '0' and is_sealed='N' and dept_id != '01' and dept_id not in (select dept_id from sec_user where is_sealed='N')";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				cnt = rs.getInt(1);
			}
			if (cnt <= 5) {
				logger.info("waiting deleted dept amount is " + cnt);
				sql = "update sec_dept set is_sealed = 'Y' where ext_code = '0' and is_sealed='N' and dept_id != '01' and dept_id not in (select dept_id from sec_user where is_sealed='N')";
				ps = conn.prepareStatement(sql);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
			close();
		}
	}
}
