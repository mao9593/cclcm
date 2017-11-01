package hdsec.web.project.domain;

import hdsec.web.project.common.PropertyUtil;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * 用户、部门域同步
 * 
 * @author renmingfei 20150119
 * 
 */
public class SynchronizeFromLDAP extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private LdapUtil ldapUtil = null;
	private String searchBase = "";
	private UserUtil userUtil = null;
	private SearchControls searchCtls = null;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SynchronizeFromLDAP sfl = new SynchronizeFromLDAP();
		sfl.startSynUser();
	}

	public SynchronizeFromLDAP() {
		try {
			ldapUtil = new LdapUtil();
			userUtil = new UserUtil();
			searchCtls = new SearchControls();
			searchBase = PropertyUtil.getLdapSearchBase();
			logger.debug("searchBase is:" + searchBase);
		} catch (Exception e) {
			logger.error("call " + this.getClass().getName() + ".SynchronizeFromLDAP() error! Detail information:" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 用户及部门同步主程序
	 * 
	 * @throws Exception
	 */
	public void startSynUser() {
		try {
			// 0.修改所有部门ext_code为0；修改所有用户ext_code为0
			userUtil.resetExtCode();
			logger.info("reset ext_code of dept and user to 0");
			// 1.读组列表并跌代处理
			handleDeptSyn();
			// 2.读用户列表并跌代处理
			handleUserSyn();
			// 3.读ext_code不为1的用户，标记删除
			markUserDeleted();
			// 4.读ext_code不为1的部门，若该部门下用户数为0，标记删除
			markDeptDeleted();
		} catch (Exception e) {
			logger.error("call " + this.getClass().getName() + ".startSynUser() error! Detail information:" + e.getMessage());
			e.printStackTrace();
		} finally {
			if (ldapUtil != null) {
				try {
					ldapUtil.close();
				} catch (NamingException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * 将同步状态为0并且部门内已无用户的部门标记为删除
	 */
	private void markDeptDeleted() {
		userUtil.markDeptDeleted();
	}

	/**
	 * 将同步状态为0的用户标记为删除
	 */
	private void markUserDeleted() {
		userUtil.markUserDeleted();
	}

	/**
	 * 处理部门同步方法
	 * 
	 * @throws Exception
	 */
	private void handleDeptSyn() throws Exception {
		logger.info("-------start to synchronize dept info");
		String returnedAtts[] = { "objectGUID", "distinguishedName", "name" }; // 定制返回属性
		searchCtls.setReturningAttributes(returnedAtts); // 设置返回属性集
		searchCtls.setSearchScope(SearchControls.ONELEVEL_SCOPE);
		List<Attributes> attrList = ldapUtil.search2List(searchBase, "(objectClass=organizationalUnit)", searchCtls);
		// logger.debug(UUID.randomUUID().toString());//UUID 36位
		String guid = null;
		String deptName = null;
		DeptInfo dept = null;
		for (Attributes attrs : attrList) {
			guid = getGuid(attrs.get("objectGUID"));
			deptName = getDeptName(attrs.get("name"));
			dept = new DeptInfo(guid, deptName);
			logger.info("dept " + dept);
			if (StringUtils.hasLength(guid) && StringUtils.hasLength(deptName)) {
				if (userUtil.checkDeptIsExists(guid)) {// 部门已经存在
					if (userUtil.checkDeptNameChanged(dept)) { // 若名称有变
						userUtil.updateDeptName(dept);
					}
					userUtil.setDeptSyned(guid);// ext_code -> 1
				} else {// 若为新增部门 goto @addDept
					userUtil.addNewDept(dept);
				}
			}
			dept = null;
		}
		logger.info("-------end to synchronize dept info");
	}

	/**
	 * 处理用户同步方法
	 * 
	 * @throws Exception
	 */
	private void handleUserSyn() throws Exception {
		logger.info("-------start to synchronize user info");
		String returnedAtts[] = { "uid", "distinguishedName", "description", "sAMAccountname", "displayName", "cn" }; // 定制返回属性
		searchCtls.setReturningAttributes(returnedAtts); // 设置返回属性集
		searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		List<Attributes> attrList = ldapUtil.search2List(searchBase, "(objectClass=user)", searchCtls);
		String userDeptName = null;
		UserInfo user = null;
		for (Attributes attrs : attrList) {
			// 过滤用户，只有A开头和B开头的部门的用户才进行同步
			userDeptName = getUserDeptName(attrs.get("distinguishedName"));
			if (StringUtils.hasLength(userDeptName)) {
				// 解析用户信息
				user = new UserInfo();
				user.setUserName(getUserName(attrs.get("cn")));
				user.setDeptId(userUtil.getDeptIdByName(userDeptName));
				user.setDeptName(userDeptName);
				user.setSecurityCode(getUserSeclvCode(attrs.get("description")));
				user.setUserId(getUserId(attrs.get("sAMAccountname")));
				user.setPid(getUserIdcard(attrs.get("uid")));
				logger.info("parsed user is " + user);
				if (StringUtils.hasLength(user.getUserId()) && StringUtils.hasLength(user.getDeptId())) {
					if (userUtil.checkUserIsExists(user.getUserId())) {// 用户已经存在
						if (userUtil.checkUserChangeDept(user)) {
							userUtil.changeUserDept(user);
						}
						if (userUtil.checkUserInfoChanged(user)) { // 若用户属性有变化
							userUtil.updateUserInfo(user);
						}
						userUtil.setUserSyned(user.getUserId());// ext_code -> 1
					} else {// 若为新增用户 goto @addUser
						userUtil.addNewUser(user);
					}
				}
			}
			user = null;
		}
		logger.info("-------end to synchronize user info");
	}

	/**
	 * 从uid属性中解析出用户身份证号
	 * 
	 * @param attr
	 * @return
	 * @throws NamingException
	 */
	private String getUserIdcard(Attribute attr) throws NamingException {
		// uid属性有的是身份证号在前，有的是用户名拼音在前，此处选择通过长度判断要传哪个。
		if (attr != null) {
			if (attr.get(0).toString().length() >= 18) {
				return attr.get(0).toString().trim();
			} else if (attr.get(1).toString().length() >= 18) {
				return attr.get(1).toString().trim();
			}
		}
		return "";
	}

	/**
	 * 获取用户名
	 * 
	 * @param attr
	 * @return
	 * @throws NamingException
	 */
	private String getUserName(Attribute attr) throws NamingException {
		if (attr != null) {
			return attr.get(0).toString();
		}
		return "";
	}

	/**
	 * 获取用户ID
	 * 
	 * @param attr
	 * @return
	 * @throws NamingException
	 */
	private String getUserId(Attribute attr) throws NamingException {
		if (attr != null) {
			return attr.get(0).toString();
		}
		return null;
	}

	/**
	 * 获取用户密级－涉密人员类别；绝密对应核心涉密人员，机密对应重要涉密人员，秘密对应一般涉密人员，其他对应非涉密人员
	 * 
	 * @param attr
	 * @return
	 * @throws NamingException
	 */
	private String getUserSeclvCode(Attribute attr) throws NamingException {
		if (attr != null) {
			String seclvName = attr.get(0).toString();
			if (seclvName.equals("绝密")) {
				return "1";
			} else if (seclvName.equals("机密")) {
				return "2";
			} else if (seclvName.equals("秘密")) {
				return "3";
			} else {
				return "4";
			}
		} else {
			// 如果没有读取到用户密级，则默认为非涉密人员
			return "4";
		}
	}

	/**
	 * 解析出用户所属部门
	 * 
	 * @param attr
	 * @return
	 * @throws NamingException
	 */
	private String getUserDeptName(Attribute attr) throws NamingException {
		if (attr != null) {
			return parseDeptName(attr.get(0).toString());
		} else {
			return null;
		}
	}

	/**
	 * 从用户的distinguishedName属性中解析出用户所属部门
	 * 
	 * @param disName
	 * @return
	 */
	private String parseDeptName(String disName) {
		if (disName.length() > 25) {
			// 去掉后面的“,OU=People,DC=pmrc,DC=net”
			disName = disName.substring(0, disName.length() - 25);
			int lastIndex = disName.lastIndexOf(",");
			if (disName.length() > 4) {
				// 去掉前面的“,OU=”,取出部门名称
				disName = disName.substring(lastIndex + 4);
				if (disName.length() > 3 && (disName.startsWith("A") || disName.startsWith("B"))) {
					return disName.substring(3);
				}
			}
		}
		return null;
	}

	/**
	 * 获取部门ID－GUID
	 * 
	 * @param attr
	 * @return
	 * @throws NamingException
	 */
	private String getGuid(Attribute attr) throws NamingException {
		if (attr != null) {
			return ldapUtil.getGuid((byte[]) attr.get(0));
		} else {
			return null;
		}
	}

	/**
	 * 获取部门名称
	 * 
	 * @param attr
	 * @return
	 * @throws NamingException
	 */
	private String getDeptName(Attribute attr) throws NamingException {
		String deptName = null;
		if (attr != null) {
			deptName = attr.get(0).toString();
			logger.debug("original dept name is " + deptName);
			// 如果部门名称长度大于3，并且部门名称以A或者B开头
			if (deptName.length() > 3 && (deptName.startsWith("A") || deptName.startsWith("B"))) {
				deptName = deptName.substring(3, deptName.length());// 去掉部门名称的前缀A01等
				return deptName;
			}
			return null;
		} else {
			return null;
		}
	}

	public static void main(String args[]) throws Exception {
		SynchronizeFromLDAP sfl = new SynchronizeFromLDAP();
		sfl.startSynUser();
		// sfl.handleUserSyn();
		// sfl.handleDeptSyn();
	}
}
