package hdsec.web.project.domain;

import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class LDAPGetDomain {
	private final Logger logger = Logger.getLogger(this.getClass());
	private static String url = null;
	private static String userName = null;
	private static String password = null;
	static {
		url = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "jdbc.url");
		userName = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "jdbc.username");
		password = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "jdbc.password");
	}

	public LDAPGetDomain() {
	}

	public void getDomainInfo() throws Exception {
		Hashtable<String, String> hashEnv = new Hashtable<String, String>();
		Map<String, UserInfo> accountMap = new HashMap<String, UserInfo>();
		String depts = null;
		// String accountValue = null;
		String LDAP_URL = PropertyUtil.getLdapURL(); // LDAP访问地址 //
		// User@domain.com
		logger.debug("-----LDAP_URL is:" + LDAP_URL);
		String adminName = PropertyUtil.getLdapUsername(); // 注意用户名的写法：domain/User
		// 或
		logger.debug("-----adminName is:" + adminName);
		String adminPassword = PropertyUtil.getLdapPassword();// 密码
		logger.debug("-----adminPassword is:" + adminPassword);
		hashEnv.put(Context.SECURITY_AUTHENTICATION, "simple"); // LDAP访问安全级别
		hashEnv.put(Context.SECURITY_PRINCIPAL, adminName); // AD User
		hashEnv.put(Context.SECURITY_CREDENTIALS, adminPassword); // AD
		// Password
		hashEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory"); // LDAP工厂类
		hashEnv.put(Context.PROVIDER_URL, LDAP_URL);
		try {
			LdapContext ctx = new InitialLdapContext(hashEnv, null);
			SearchControls searchCtls = new SearchControls();
			searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String searchFilter = "objectClass=*";
			String searchBase = PropertyUtil.getLdapSearchBase();
			logger.info("-----searchBase is:" + searchBase);
			// String returnedAtts[] = {"memberOf"};//定制返回属性
			String returnedAtts[] = { "sAMAccountName", "distinguishedName", "description", "uid" }; // 定制返回属性
			searchCtls.setReturningAttributes(returnedAtts); // 设置返回属性集
			NamingEnumeration<?> answer = ctx.search(searchBase, searchFilter, searchCtls);
			int totalResults = 0;
			UserInfo user = null;
			while (answer.hasMoreElements()) {
				logger.debug("answer.hasMoreElements is true");
				SearchResult sr = (SearchResult) answer.next();
				logger.info("Returned value of searchResult.getName() is:" + sr.getName());
				if (sr.getName().startsWith("CN")) {
					logger.info("CN 用户部门组织列" + sr.getName());
					depts = sr.getName();
					// String[] userDepts = sr.getName().split(",");
					// for(int i = userDepts.length - 1; i >= 0 ; i--){
					// System.out.println(userDepts[i]);
					// }
				}
				Attributes attrs = sr.getAttributes();
				if (attrs != null) {
					logger.debug("searchResult.getAttributes returns some value[attrs]");
					try {
						logger.debug("ready to step into the loop of NamingEnumeration<?> ne[attrs.getAll()]");
						user = new UserInfo();
						for (NamingEnumeration<?> ne = attrs.getAll(); ne.hasMore();) {
							Attribute specAttr = (Attribute) ne.next();
							logger.debug("cast ne.next() to an Attribute[specAttr]");
							// System.out.println(" AttributeID="
							// + Attr.getID().toString());
							logger.info("specAttr.getID() returns :" + specAttr.getID());
							logger.debug("ready to step into the loop of NamingEnumeration<?> e[specAttr.getAll()]");
							for (NamingEnumeration<?> e = specAttr.getAll(); e.hasMore(); totalResults++) {
								if (specAttr.getID().toString().equals("sAMAccountName")) {
									String userId = e.next().toString();
									logger.info("userId is:" + userId);
									user.setUserId(userId);
									// accountValue = e.next().toString();
									// accountMap.put(depts, accountValue);
								} else if (specAttr.getID().toString().equals("description")) {
									String seclvName = e.next().toString();
									// System.out.println("用户密级 " + seclvName);
									logger.info("seclvName is:" + seclvName);
									if ("机密".equals(seclvName)) {
										user.setSecurityCode("2");
										// user.setSeclvCode("2");
									} else if ("秘密".equals(seclvName)) {
										user.setSecurityCode("2");
										// user.setSeclvCode("2");
									} else if ("内部".equals(seclvName)) {
										user.setSecurityCode("3");
										// user.setSeclvCode("3");
									} else if ("非密".equals(seclvName)) {
										user.setSecurityCode("4");
										// user.setSeclvCode("4");
									}
								} else if (specAttr.getID().toString().equals("uid")) {
									String uid = e.next().toString();
									logger.info("uid[idcard] is:" + uid);
									String[] idac = uid.split(",");
									// System.out.println("accout" + idac[1]);
									// System.out.println("身份证号2" +
									// uid.split(",")[0]);
									if (!StringUtils.hasLength(user.getPid())) {
										user.setPid(idac[0]);
									}
								} else if (specAttr.getID().toString().equals("CN")) {
									String username = e.next().toString();
									logger.info("username is:" + username);
									// System.out.println("用户名" + username);
									user.setUserName(username);
								} else {
									String unknown = e.next().toString();
									logger.info("some value with unknown specAttr is :" + unknown);
								}
							}

							Enumeration<?> values = specAttr.getAll();
							if (values != null) { // 迭代
								while (values.hasMoreElements()) {
									// System.out.println(" AttributeValues="
									// + values.nextElement());
									values.nextElement();
								}
							}
							if (StringUtils.hasLength(depts)) {
								accountMap.put(depts, user);
								logger.info("pub element into accoutnMap with key[" + depts + "],user[" + user + "]");
								user = null;
							}
						}
					} catch (NamingException e) {
						logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
						System.err.println("Throw Exception : " + e);
					}
				}
			}
			List<String> deptList = new ArrayList<String>();
			logger.debug("ready to step into the loop of accountMap<String,UserInfo>.");
			for (Entry<String, UserInfo> entry : accountMap.entrySet()) {
				// System.out.println(entry.getKey() +" " +
				// entry.getValue());
				UserInfo userInfo = entry.getValue();
				String[] userDepts = entry.getKey().split(",");
				logger.info("-----key[" + entry.getKey() + "],value[" + userInfo + "]");
				for (int i = userDepts.length - 1; i >= 0; i--) {
					String deptName = userDepts[i].split("=")[1];
					logger.info("deptName is:" + deptName);
					// deptName = deptName.substring(3,deptName.length());
					// if(i == userDepts.length - 1){
					// if(checkDeptIsExists(deptName,"九部")){
					// deptList.add(deptName);
					// System.out.println("部门已经存在");
					// }else{
					// addNewDept(userDepts[i].split("=")[1],null);
					// }
					// }else{
					if (i - 1 >= 0) {
						// System.out.println("部门 " + deptName );
						if (deptName.length() <= 3) {
							logger.debug("length of deptName less than 3,skip it");
							continue;
						}
						deptName = deptName.substring(3, deptName.length());
						logger.debug("cutting first 3 letters");
						String parentDeptName = null;
						if (userDepts.length != i + 1) {
							parentDeptName = userDepts[i + 1].split("=")[1];
						} else {
							parentDeptName = "九部";
						}
						logger.info("deptName[" + deptName + "],parentDeptName[" + parentDeptName + "]");
						// System.out.println("部门 " + deptName +
						// " 父部门 "+parentDeptName);
						if (checkDeptIsExists(deptName, parentDeptName)) {
							deptList.add(deptName);
						} else {
							// addNewDept(deptName,parentDeptName);
						}
					}
					// }
				}
				if (userDepts.length < 2) {
					logger.debug("size of userDepts less than 2,skip it");
					continue;
				}
				String userId = userInfo.getUserId();
				String userName = userDepts[0].split("=")[1];
				String userDeptName = userDepts[1].split("=")[1];
				if (userDeptName.length() <= 3) {
					continue;
				}
				userDeptName = userDeptName.substring(3, userDeptName.length());
				if (checkUserIsExists(userId)) {
					if (!checkUserAndDeptIsExists(userId, userDeptName) && deptList.contains(userDeptName)) {
						updateUserDept(userId, userDeptName);
					} else {
						logger.debug("Nothing changed");
						// System.out.println("用户已经存在");
					}
				} else {
					if (deptList.contains(userDeptName)) {
						logger.info("deal with userId[" + userId + "],userName[" + userName + "],userDeptName[" + userDeptName + "]");
						if (StringUtils.hasLength(userId)) {
							addNewUser(userInfo, userName, userDeptName);
						} else {
							logger.info("No userId matches userName[" + userName + "]");
						}
					}
				}
			}
			ctx.close();
		} catch (NamingException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
			e.printStackTrace();
		}
	}

	private void updateUserDept(String userId, String deptName) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			String deptId = getDeptIdByName(deptName, conn);
			logger.info("getDeptIdByName with param deptName[" + deptName + "] returns:" + deptId);
			sql = "update sec_user set dept_id = ? where user_iidd = ? ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, deptId);
			ps.setString(2, userId);
			ps.execute();
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
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

	}

	private void addNewUser(UserInfo user, String userName, String deptName) throws Exception {
		logger.info("addNewUser with user[" + user + "],userName[" + userName + "],deptName[" + deptName + "]");
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			if (!checkRealUserIsExists(user.getUserId())) {
				sql = "insert into real_user(real_user_id) values(?) ";
				ps = conn.prepareStatement(sql);
				ps.setString(1, user.getUserId());
				ps.execute();
			}

			sql = "insert into sec_user(user_iidd,real_user_id,user_name,user_ppww,dept_id,idcard,security_code) values(?,?,?,?,?,?,?) ";
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserId());
			ps.setString(2, user.getUserId());
			ps.setString(3, userName);
			ps.setString(4, "12345678a@");
			String deptId = getDeptIdByName(deptName, conn);
			ps.setString(5, deptId);
			ps.setString(6, user.getPid());
			ps.setString(7, user.getSecurityCode());
			ps.execute();
		} finally {
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

	}

	private boolean checkDeptIsExists(String deptName, String parentDeptName) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		String sql;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			if (StringUtils.hasLength(parentDeptName)) {
				sql = "select * from sec_dept d,sec_dept p where d.dept_parent_id = p.dept_id and d.dept_name = ? and p.dept_name = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, deptName);
				ps.setString(2, parentDeptName);
			} else {
				sql = "select * from sec_dept d where d.dept_name = ? and d.dept_cs = ?";
				ps = conn.prepareStatement(sql);
				ps.setString(1, deptName);
				ps.setString(2, "01");
			}
			rs = ps.executeQuery();
			if (null != rs && rs.next()) {
				logger.debug("checkDeptIsExists with deptName[" + deptName + "],parentDeptName[" + parentDeptName + "],returns true");
				return true;
			} else {
				logger.debug("checkDeptIsExists with deptName[" + deptName + "],parentDeptName[" + parentDeptName + "],returns false");
				return false;
			}
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
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
		return false;
	}

	private boolean checkRealUserIsExists(String userId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			sql = "select * from real_user where real_user_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			if (null != rs && rs.next()) {
				logger.debug("checkRealUserIsExists with userId[" + userId + "],returns true");
				return true;
			} else {
				logger.debug("checkRealUserIsExists with userId[" + userId + "],returns false");
				return false;
			}
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
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
		return false;
	}

	private boolean checkUserIsExists(String userId) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			sql = "select * from sec_user where user_iidd = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			rs = ps.executeQuery();
			if (null != rs && rs.next()) {
				logger.debug("checkUserIsExists with userId[" + userId + "],returns true");
				return true;
			} else {
				logger.debug("checkUserIsExists with userId[" + userId + "],returns false");
				return false;
			}
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
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
		return false;
	}

	private boolean checkUserAndDeptIsExists(String userId, String deptName) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql;
		try {
			conn = DriverManager.getConnection(url, userName, password);
			sql = "select * from sec_user u,sec_dept d where u.dept_id = d.dept_id and u.user_iidd = ? and d.dept_name = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, deptName);
			rs = ps.executeQuery();
			if (null != rs && rs.next()) {
				logger.debug("checkUserAndDeptIsExists with userId[" + userId + "],deptName[" + deptName + "],returns true");
				return true;
			} else {
				logger.debug("checkUserAndDeptIsExists with userId[" + userId + "],deptName[" + deptName + "],returns false");
				return false;
			}
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
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
		return false;
	}

	private String getDeptIdByName(String deptName, Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String sql = null;
		try {
			sql = "select dept_id from sec_dept where dept_name = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, deptName);
			rs = ps.executeQuery();
			while (rs.next()) {
				return rs.getString("dept_id");
			}
		} catch (SQLException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
		} finally {
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

		}
		return null;
	}

	public static void main(String args[]) throws Exception {
		LDAPGetDomain ad = new LDAPGetDomain();
		ad.getDomainInfo();
	}
}
