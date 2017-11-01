package hdsec.web.project.user.service;

import hdsec.web.project.common.model.CopyrightLicense;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.MatchStringVector;
import hdsec.web.project.log.service.LogService;
import hdsec.web.project.user.mapper.UserMapper;
import hdsec.web.project.user.model.Acl;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.ConfigItem;
import hdsec.web.project.user.model.DeptAdminConfig;
import hdsec.web.project.user.model.DeptTree;
import hdsec.web.project.user.model.OperTree;
import hdsec.web.project.user.model.Post;
import hdsec.web.project.user.model.RealUser;
import hdsec.web.project.user.model.SecCommonContact;
import hdsec.web.project.user.model.SecCommonGroup;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecDeptAdmin;
import hdsec.web.project.user.model.SecDeptLevel;
import hdsec.web.project.user.model.SecDeptType;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecOperation;
import hdsec.web.project.user.model.SecRole;
import hdsec.web.project.user.model.SecRoleUser;
import hdsec.web.project.user.model.SecSubsys;
import hdsec.web.project.user.model.SecTerritory;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.model.SecUserDept;
import hdsec.web.project.user.model.SecUserPost;
import hdsec.web.project.user.model.User;
import hdsec.web.project.user.model.UserSecurity;
import ht304.hdsec.framework.common.model.FrameworkActionUser;
import ht304.hdsec.framework.role.model.FrameworkPermission;
import ht304.hdsec.framework.role.model.FrameworkRole;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Resource
	LogService logService;
	@Resource
	private UserMapper userMapper;

	// public static int numbers = 0;

	// public static int l_numbers = 0;

	// @Resource
	// private SqlSessionFactory sqlSessionFactory;

	@Override
	public List<SecUser> getSecUser(SecUser secUser) {
		return userMapper.getSecUser(secUser);
	}

	@Override
	public SecUser getSecUserByUid(String userId) {
		SecUser user = userMapper.getSecUserByUid(userId);
		if (user != null && StringUtils.hasLength(user.getSecurity_code())) {
			Map<String, String> userMap = new HashMap<String, String>();
			userMap.put("user_iidd", user.getUser_iidd());
			List<SecRole> roleList = userMapper.getRoleListByUser(userMap);
			user.setUserRoleList(roleList);
			user.setSecurity(getSecurityByCode(user.getSecurity_code()));
		}
		return user;
	}

	@Override
	public SecUser getSecUserByIdcard(String idcard) throws Exception {
		List<SecUser> userList = userMapper.getSecUserByIdcard(idcard);
		if (userList == null || userList.size() == 0) {
			throw new Exception("身份证号[" + idcard + "]没有绑定任何用户，请管理员检查确认");
		} else if (userList.size() > 1) {
			throw new Exception("身份证号[" + idcard + "]绑定了多个用户，请管理员检查确认");
		} else {
			SecUser user = userList.get(0);
			if (user != null && StringUtils.hasLength(user.getSecurity_code())) {
				Map<String, String> userMap = new HashMap<String, String>();
				userMap.put("user_iidd", user.getUser_iidd());
				List<SecRole> roleList = userMapper.getRoleListByUser(userMap);
				user.setUserRoleList(roleList);
				user.setSecurity(getSecurityByCode(user.getSecurity_code()));
			}
			return user;
		}
	}

	@Override
	public Collection<String> getAllOperCodeByUserId(String userId) {
		List<String> list = null;
		// 如果是超级用户，则返回全部oper
		/*
		 * if (userMapper.isPowerUser(userId) > 0) { //return userMapper.getAllOperCode(); list =
		 * userMapper.getAllOperCode(); } else { //如果不是超级用户，根据userid查询oper //return
		 * userMapper.getAllOperCodeByUserId(userId); list = userMapper.getAllOperCodeByUserId(userId); }
		 */
		list = userMapper.getAllOperCodeByUserId(userId);
		MatchStringVector<String> msv = new MatchStringVector<String>(MatchStringVector.FRONT_MATCH_MODE, list.size());
		msv.addAll(list);
		return msv;
	}

	@Override
	public <ActionUser extends FrameworkActionUser> ActionUser getActionUserByUid(String arg0) {
		return null;
	}

	@Override
	public <Permission extends FrameworkPermission> List<Permission> getAssignedPermissions(String arg0) {
		return null;
	}

	@Override
	public <Role extends FrameworkRole> List<Role> getAssignedRoles(String arg0) {
		return null;
	}

	@Override
	public List<Post> getAllPostAsCon() {
		return userMapper.getAllPostAsCon();
	}

	@Override
	public List<SecCommonGroup> getSecCommonGroupByUserId(String userId) {
		return userMapper.getSecCommonGroupByUserId(userId);
	}

	@Override
	public List<SecCommonContact> getCommonContactForDept(Map<String, Object> map) {
		return userMapper.getCommonContactForDept(map);
	}

	@Override
	public List<SecCommonContact> getCommonContactForUser(Map<String, Object> map) {
		return userMapper.getCommonContactForUser(map);
	}

	@Override
	public List<DeptTree> getDeptTree() {
		return userMapper.getDeptTree();
	}

	@Override
	public int getUserByDeptSize(Map<String, Object> map) {
		return userMapper.getUserByDeptSize(map);
	}

	@Override
	public List<SecUser> getUserByDept(Map<String, Object> map, RowBounds rbs) {
		List<SecUser> userList = userMapper.getUserByDept(map, rbs);
		if (userList != null && userList.size() > 0) {
			Map<String, String> userMap = new HashMap<String, String>();
			for (SecUser user : userList) {
				userMap.put("user_iidd", user.getUser_iidd());
				List<SecRole> roleList = userMapper.getRoleListByUser(userMap);
				user.setUserRoleList(roleList);
			}
		}
		return userList;
	}

	@Override
	public String getDeptNameByDeptId(String dept_id) {
		if (dept_id.equalsIgnoreCase("self")) {
			return "本部门";
		} else if (dept_id.equalsIgnoreCase("super")) {
			return "上级部门";
		} else if (dept_id.equalsIgnoreCase("default")) {
			return "默认部门";
		}
		return userMapper.getDeptNameByDeptId(dept_id);
	}

	@Override
	public List<SecRole> getSecRoleBySubsys(final Map<String, String> map) {
		logger.debug("----enter UserServiceImpl.getSecRoleBySubsys()----");
		List<SecRole> roleList = userMapper.getSecRoleBySubsys(map);
		for (SecRole item : roleList) {
			if (userMapper.getRoleHasUserCount(item.getRole_id()) > 0) {
				item.setHasUser(true);
			}
		}
		return roleList;
	}

	@Override
	public List<SecSubsys> getAllSubsysAsCon() {
		return userMapper.getAllSubsysAsCon();
	}

	@Override
	public int getRoleNameCount(String role_name) {
		return userMapper.getRoleNameCount(role_name);
	}

	@Override
	public void addSecRole(SecRole secRole) {
		userMapper.addSecRole(secRole);
	}

	@Override
	public void delSecRole(String role_id) {
		userMapper.delSecRoleByRoleId(role_id);
	}

	@Override
	public SecRole getSecRoleByRoleId(String role_id) {
		return userMapper.getSecRoleByRoleId(role_id);
	}

	@Override
	public void updateSecRole(Map<String, String> map) {
		userMapper.updateSecRole(map);
	}

	@Override
	public SecSubsys getSubsysBySubsysCode(String subsys_code) {
		return userMapper.getSubsysBySubsysCode(subsys_code);
	}

	@Override
	public List<SecUser> getUserByRole(String role_id) {
		return userMapper.getUserByRole(role_id);
	}

	@Override
	public String getSubsysCodeByRoleId(String role_id) {
		return userMapper.getSubsysCodeByRoleId(role_id);
	}

	@Override
	public List<SecOperation> getSecOperBySubsys(String subsys_code) {
		return userMapper.getSecOperBySubsys(subsys_code);
	}

	@Override
	public List<String> getConfigedOperByRoleAndSubsys(Map<String, String> map) {
		return userMapper.getConfigedOperByRoleAndSubsys(map);
	}

	@Override
	public int updateRoleOper(String subsys_code, String role_id, List<String> modifiedOperCodeList, List<Acl> aclList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("role_id", role_id);
		map.put("operCodes", modifiedOperCodeList);
		userMapper.deleteModifiedOperByRole(map);
		logger.debug("---deleteModifiedOperByRole called----");
		for (Acl item : aclList) {
			userMapper.addRoleOper(item);
		}
		return 1;
	}

	@Override
	public List<Post> getPostByDeptAsCon(String dept_id) {
		return userMapper.getPostByDeptAsCon(dept_id);
	}

	@Override
	public void addSecUser(SecUser secUser, boolean real_blank, String role_id_list) throws Exception {

		/**
		 * 生成real_user_id 默认实际用户ID跟用户登录ID相同，如user_iidd为renmingfei，则real_user_id也为renmingfei
		 * 实际用户ID已经被使用的时候，则在后缀加序号，如renmingfei_1,renmingfei_2以此类推。 List<String> realUserId =
		 * userMapper.getRealUserIdWithSub(secUser.getUser_iidd()); if (realUserId.size() == 0) {
		 * secUser.setReal_user_id(secUser.getUser_iidd()); } else { String lastRealUserId =
		 * realUserId.get(realUserId.size() - 1); int beginIndex = lastRealUserId.lastIndexOf("_"); int index =
		 * Integer.parseInt(lastRealUserId.substring(beginIndex + 1, lastRealUserId.length()));
		 * secUser.setReal_user_id(secUser.getUser_iidd() + "_" + String.valueOf(index + 1)); }
		 */
		// }
		if (getLastNumberCount() <= 0) {
			throw new Exception("用户添加已达到上限，如需添加用户，请继续购买人数！");
		}
		// 打开批处理
		if (!real_blank) {
			userMapper.insertRealUser(secUser);
		}
		userMapper.addSecUser(secUser);
		// if(secUser.is_worker() || secUser.is_leader()){
		// l_numbers --;
		// }
		addUserRole(secUser.getUser_iidd(), Constants.LOG_SUBSYS_ADMIN_CODE, role_id_list, Constants.ROLE_TYPE_COMMON);
	}

	@Override
	public int getLastNumberCount() throws Exception {
		/*
		 * if (0 == numbers) { String numberDays = ServerValidation.hasPermissionFile(); if (!"0".equals(numberDays)) {
		 * numbers = Integer.parseInt(numberDays.split("\\|")[0]); } }
		 */
		int curUserCount = userMapper.getAllCommonUserCount();
		int lastNumber = CopyrightLicense.pNum - curUserCount;
		if (lastNumber > 0) {
			return lastNumber;
		} else {
			logger.warn("当前用户数[" + curUserCount + "]已经超出可用用户数[" + CopyrightLicense.pNum + "]");
			throw new Exception("当前用户数[" + curUserCount + "]已经超出可用用户数[" + CopyrightLicense.pNum + "]");
		}
	}

	public void addUserRole(String user_iidd, String subsys_code, String newCodes, Integer role_type) {
		SecRoleUser secRoleUser = null;
		SecRole role = null;
		for (String item : newCodes.split(",")) {
			if (StringUtils.hasLength(item.trim())) {
				secRoleUser = new SecRoleUser(user_iidd, item.trim());
				userMapper.addUserRoleRel(secRoleUser);
				// 如果角色是部门管理员
				role = getSecRoleByRoleId(item);
				if (role != null && role.getRole_type() == Constants.ROLE_TYPE_COMMON
						&& role.getRole_spec_key().equals("DEPT")) {
					// 添加默认部门管理员配置
					SecDept dp = getSecDeptByUserId(user_iidd);
					SecDeptAdmin deptAdmin = new SecDeptAdmin(user_iidd, dp.getDept_id(), dp.getDept_cs());
					userMapper.addSecDeptAdmin(deptAdmin);
				}
			}
		}
	}

	@Override
	public void updateUserStatus(Map<String, String> map) {
		userMapper.updateUserStatus(map);
	}

	@Override
	public void delSecUserById(String user_iidd) {
		userMapper.delSecUserById(user_iidd);
	}

	@Override
	public List<String> getUserIdList(Map<String, String> map) {
		return userMapper.getUserIdList(map);
	}

	@Override
	public RealUser getRealUserByUid(String user_iidd) {
		return userMapper.getRealUserByUid(user_iidd);
	}

	@Override
	public void updateSecUser(Map<String, Object> map, boolean real_blank) {
		/*
		 * SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH); session.update("updateSecUser", map);
		 * if (!real_blank) { session.update("updateRealUser", map); } session.commit(true); session.close();
		 */
		userMapper.updateSecUser(map);
		if (!real_blank) {
			userMapper.updateRealUser(map);
		}
	}

	@Override
	public void updateSecUserName(Map<String, Object> map) {
		userMapper.updateSecUserName(map);
	}

	@Override
	public void resetUserPwd(Map<String, String> map) {
		userMapper.resetUserPwd(map);
	}

	@Override
	public SecUserDept getUserMainDeptByUser(String user_iidd) {
		return userMapper.getUserDeptByUser(user_iidd);
	}

	@Override
	public List<SecRole> getRoleListByUser(Map<String, String> map) {
		return userMapper.getRoleListByUser(map);
	}

	@Override
	public int getDeptPostCount(Map<String, String> map) {
		return userMapper.getDeptPostCount(map);
	}

	@Override
	public void updateUserDept(Map<String, String> map) {
		userMapper.updateUserDept(map);
		if (map.get("clearPost").equalsIgnoreCase("Y")) {
			userMapper.clearUserPostByUser(map.get("user_iidd"));
		}
	}

	@Override
	public void updateUserRole(String user_iidd, String subsys_code, String newCodes) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		map.put("subsys_code", subsys_code);
		map.put("role_type", Constants.ROLE_TYPE_COMMON);
		// 查询原来的普通角色ID列表
		List<String> roleList = userMapper.getRoleIdListByUser(map);
		// 删除原来的普通角色
		userMapper.deleteUserRoleByUser(map);
		// 添加新普通角色
		SecRoleUser secRoleUser = null;
		List<String> newRoleList = new ArrayList<String>();
		for (String item : newCodes.split(",")) {
			if (StringUtils.hasLength(item.trim())) {
				secRoleUser = new SecRoleUser(user_iidd, item.trim());
				userMapper.addUserRoleRel(secRoleUser);
				newRoleList.add(item.trim());
			}
		}
		// 如果有被删除的角色，则删除该角色的部门关联配置
		for (String role_id : roleList) {
			if (!newRoleList.contains(role_id)) {
				map.put("role_id", role_id);
				userMapper.delSecDeptAdminByUserRole(map);
				if (role_id.equals("110")) {
					SecUser tempuser = getSecUserByUid(user_iidd);
					String tempusername = tempuser.getUser_name();
					if (tempusername.contains("DMA")) {
						HashMap<String, Object> tempmap = new HashMap<String, Object>();
						tempmap.put("user_name", tempusername.substring(0, tempusername.indexOf("_DMA")));
						tempmap.put("user_iidd", user_iidd);
						updateSecUserName(tempmap);
					}
				}
			}
		}
		/*
		 * // 如果角色包含部门管理员 if (newCodes.equals("8") || newCodes.startsWith("8,") || newCodes.endsWith(",8") ||
		 * newCodes.indexOf(",8,") != -1) { // 如果原来不包含部门管理员角色 if (roleList.indexOf("8") == -1) { // 添加默认部门管理员配置 SecDept
		 * dp = getSecDeptByUserId(user_iidd); SecDeptAdmin deptAdmin = new SecDeptAdmin(user_iidd, dp.getDept_id(),
		 * dp.getDept_cs()); userMapper.addSecDeptAdmin(deptAdmin); } } else {// 如果不包含部门管理员角色,删除部门管理员配置
		 * userMapper.delSecDeptAdminByUser(user_iidd); }
		 */

	}

	@Override
	public void updateAdminRole(String user_iidd, String subsys_code, String role_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		map.put("subsys_code", subsys_code);
		map.put("role_type", Constants.ROLE_TYPE_READONLY);
		userMapper.deleteUserRoleByUser(map);
		SecRoleUser secRoleUser = null;
		if (StringUtils.hasLength(role_id.trim())) {
			secRoleUser = new SecRoleUser(user_iidd, role_id.trim());
			userMapper.addUserRoleRel(secRoleUser);
		}
	}

	@Override
	public SecDept getSecDeptByUserId(String user_iidd) {
		return userMapper.getSecDeptByUserId(user_iidd);
	}

	@Override
	public List<OperTree> getOperTreeBySubsys(String subsys_code) {
		return userMapper.getOperTreeBySubsys(subsys_code);
	}

	@Override
	public SecOperation getSecOperByCodeAndSubsys(String oper_code, String subsys_code) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("oper_code", oper_code);
		map.put("subsys_code", subsys_code);
		return userMapper.getSecOperByCodeAndSubsys(map);
	}

	@Override
	public int getSubOperCountByCode(String oper_code, String subsys_code) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("oper_code", oper_code);
		map.put("subsys_code", subsys_code);
		return userMapper.getSubOperCountByCode(map);
	}

	@Override
	public List<SecOperation> getSubOperListByCode(String oper_code, String subsys_code) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("oper_code", oper_code);
		map.put("subsys_code", subsys_code);
		return userMapper.getSubOperListByCode(map);
	}

	@Override
	public int getOperCountByCode(String oper_code) {
		return userMapper.getOperCountByCode(oper_code);
	}

	@Override
	public int addSecOper(SecOperation secOper) {
		return userMapper.addSecOper(secOper);
	}

	@Override
	public void delSecOperByCode(Map<String, String> map) {
		userMapper.delRoleOperByOper(map);
		userMapper.delSecOperByCode(map);
	}

	@Override
	public void delSecOperByCodeWithSub(String oper_code, String subsys_code) {
		// 通过模糊查询查找出该节点所有的子孙节点，然后遍历删除
		// SecOperation secOper = getSecOperByCodeAndSubsys(oper_code,
		// subsys_code);
		Map<String, String> map = new HashMap<String, String>();
		// map.put("oper_code", secOper.getOper_code());
		map.put("oper_code", oper_code);
		map.put("subsys_code", subsys_code);
		List<String> subList = userMapper.getSecOperCodeWithSub(map);
		for (String item : subList) {
			map = new HashMap<String, String>();
			map.put("oper_code", item);
			map.put("subsys_code", subsys_code);
			delSecOperByCode(map);
		}
	}

	@Override
	public void updateSecOper(SecOperation secOper) {
		userMapper.updateSecOper(secOper);
	}

	@Override
	public SecDept getSecDeptById(String dept_id) {
		return userMapper.getSecDeptById(dept_id);
	}

	@Override
	public SecTerritory getSecTerrByCode(String terr_code) {
		return userMapper.getSecTerrByCode(terr_code);
	}

	@Override
	public SecDeptLevel getSecDeptLevelByCode(String dept_level_code) {
		return userMapper.getSecDeptLevelByCode(dept_level_code);
	}

	@Override
	public SecDeptType getSecDeptTypeByCode(String dept_type_code) {
		return userMapper.getSecDeptTypeByCode(dept_type_code);
	}

	@Override
	public List<SecSubsys> getSubsysListByDeptId(String dept_id) {
		return userMapper.getSubsysListByDeptId(dept_id);
	}

	@Override
	public List<SecDept> getSecDeptByIdWithSub(String dept_id) {
		return userMapper.getSecDeptByIdWithSub(dept_id);
	}

	@Override
	public int getUserCountByDeptWithSub(String dept_cs) {
		return userMapper.getUserCountByDeptCsWithSub(dept_cs);
	}

	@Override
	public List<SecTerritory> getAllSecTerritory() {
		return userMapper.getAllSecTerritory();
	}

	@Override
	public List<SecDeptLevel> getAllSecDeptLevel() {
		return userMapper.getAllSecDeptLevel();
	}

	@Override
	public List<SecDeptType> getAllSecDeptType() {
		return userMapper.getAllSecDeptType();
	}

	@Override
	public List<SecDept> getSubDeptListByDeptCs(String dept_cs) {
		return userMapper.getSubDeptListByDeptCs(dept_cs);
	}

	@Override
	public int getSecDeptCountByDeptCs(String dept_cs) {
		return userMapper.getSecDeptCountByDeptCs(dept_cs);
	}

	@Override
	public void addSecDept(Map<String, String> map) {
		userMapper.addSecDept(map);
		String dept_subsys = map.get("dept_subsys");
		if (StringUtils.hasLength(dept_subsys)) {
			String dept_id = map.get("dept_id");
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("dept_id", dept_id);
			for (String item : dept_subsys.split(",")) {
				map1.put("subsys_code", item);
				userMapper.addSecDeptSubsys(map1);
			}
		}
	}

	@Override
	public void updateSecDept(Map<String, String> map) {
		userMapper.updateSecDept(map);
		String updateSubsys = map.get("updateSubsys");
		String dept_subsys = map.get("dept_subsys");
		if (updateSubsys.equalsIgnoreCase("Y")) {
			String dept_id = map.get("dept_id");
			userMapper.deleteDeptSubsysByDept(dept_id);// 先删除原部门和子系统的关联，再添加
			Map<String, String> map1 = new HashMap<String, String>();
			map1.put("dept_id", dept_id);
			for (String item : dept_subsys.split(",")) {
				if (StringUtils.hasLength(item.trim())) {
					map1.put("subsys_code", item.trim());
					userMapper.addSecDeptSubsys(map1);
				}
			}
		}
	}

	@Override
	public void delSecDeptByIdWithSub(String dept_id) {
		// 先根据部门编码查询其所有的子部门编码列表，然后逐个删除
		List<String> deptIdList = userMapper.getSecDeptIdWithSub(dept_id);
		for (String item : deptIdList) {
			delSecDeptById(item);
		}
	}

	public void delSecDeptById(String dept_id) {
		userMapper.delSecDeptById(dept_id);
	}

	@Override
	public List<SecUserPost> getInDeptPostListByDept(String dept_id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("dept_id", dept_id);
		map.put("rel", "IN");
		return userMapper.getDeptPostListByDept(map);
	}

	@Override
	public List<SecUserPost> getOutDeptPostListByDept(String dept_id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("dept_id", dept_id);
		map.put("rel", "OUT");
		return userMapper.getDeptPostListByDept(map);
	}

	@Override
	public void addSecDeptPostRel(String dept_id, List<String> selectedPostIdList) {
		userMapper.deleteDeptPostByDept(dept_id);
		Map<String, String> map = new HashMap<String, String>();
		map.put("dept_id", dept_id);
		for (String post_id : selectedPostIdList) {
			map.put("post_id", post_id);
			userMapper.addDeptPostRel(map);
		}
	}

	@Override
	public SecUserPost getSecUserPostByUser(String user_iidd) {
		return userMapper.getSecUserPostByUser(user_iidd);
	}

	@Override
	public List<SecUserPost> getAllUserPostList() {
		return userMapper.getAllUserPostList();
	}

	@Override
	public void addSecUserPost(SecUserPost post) {
		userMapper.addSecUserPost(post);
	}

	@Override
	public void delPostById(String post_id) {
		userMapper.delSecUserPostById(post_id);
	}

	@Override
	public SecUserPost getSecUserPostById(String post_id) {
		return userMapper.getSecUserPostById(post_id);
	}

	@Override
	public void updateSecUserPost(SecUserPost post) {
		userMapper.updateSecUserPost(post);
	}

	@Override
	public List<SecUser> getUserByPost(String post_id) {
		return userMapper.getUserByPost(post_id);
	}

	@Override
	public List<String> getDeptByPost(String post_id) {
		return userMapper.getDeptByPost(post_id);
	}

	@Override
	public void updatePostLevel(List<String> post_id, List<Integer> post_level) {
		String postId = null;
		Integer postLevel = null;
		Map<String, Object> map = new HashMap<String, Object>();
		for (int index = 0; index < post_id.size(); index++) {
			postId = post_id.get(index);
			postLevel = post_level.get(index);
			map.put("post_id", postId);
			map.put("post_level", postLevel);
			userMapper.updatePostLevel(map);
		}
	}

	@Override
	public void updatePostClass(List<String> post_id, List<Integer> post_class) {
		String postId = null;
		Integer postClass = null;
		Map<String, Object> map = new HashMap<String, Object>();
		for (int index = 0; index < post_id.size(); index++) {
			postId = post_id.get(index);
			postClass = post_class.get(index);
			map.put("post_id", postId);
			map.put("post_class", postClass);
			userMapper.updatePostClass(map);
		}
	}

	@Override
	public List<SecDeptType> getAllDeptType() {
		return userMapper.getAllDeptType();
	}

	@Override
	public void addDeptType(SecDeptType type) {
		userMapper.addDeptType(type);
	}

	@Override
	public SecDeptType getDeptTypeByCode(String dept_type_code) {
		return userMapper.getDeptTypeByCode(dept_type_code);
	}

	@Override
	public void delDeptTypeByCode(String dept_type_code) {
		userMapper.delDeptTypeByCode(dept_type_code);
	}

	@Override
	public void updateDeptType(SecDeptType secDeptType) {
		userMapper.updateDeptType(secDeptType);
	}

	@Override
	public List<SecDeptLevel> getDeptLevel() {
		return userMapper.getDeptLevel();
	}

	@Override
	public void addSubsys(SecSubsys secSubsys) {
		userMapper.addSubsys(secSubsys);
		userMapper.addSubsysRootOper(secSubsys);
		// 添加三员角色
		/*
		 * SecRole secRole = new SecRole("系统管理员", secSubsys.getSubsys_name() + "-系统管理员", secSubsys.getSubsys_code(),
		 * Constants.ROLE_TYPE_READONLY, null); session.insert("addSubsysAdminRole", secRole); secRole = new
		 * SecRole("安全管理员", secSubsys.getSubsys_name() + "-安全管理员", secSubsys.getSubsys_code(),
		 * Constants.ROLE_TYPE_READONLY, null); session.insert("addSubsysAdminRole", secRole); secRole = new
		 * SecRole("审计管理员", secSubsys.getSubsys_name() + "-审计管理员", secSubsys.getSubsys_code(),
		 * Constants.ROLE_TYPE_READONLY, null); session.insert("addSubsysAdminRole", secRole); //添加特殊角色 secRole = new
		 * SecRole("部门秘书", secSubsys.getSubsys_name() + "-部门秘书", secSubsys.getSubsys_code(),
		 * Constants.ROLE_TYPE_SPECIAL, Constants.ROLE_KEY_DEPT); session.insert("addSubsysSpecialRole", secRole);
		 * secRole = new SecRole("地区秘书", secSubsys.getSubsys_name() + "-地区秘书", secSubsys.getSubsys_code(),
		 * Constants.ROLE_TYPE_SPECIAL, Constants.ROLE_KEY_TERR); session.insert("addSubsysSpecialRole", secRole);
		 */
		// userMapper.addSubsys(secSubsys);
	}

	@Override
	public void updateSubsys(SecSubsys secSubsys) {
		userMapper.updateSubsys(secSubsys);
	}

	@Override
	public void delSubsysByCode(String subsys_code) {
		userMapper.delSubsysRootOper(subsys_code);
		userMapper.delSubsysByCode(subsys_code);
	}

	@Override
	public List<SecLevel> getSecLevel() {
		return userMapper.getSecLevel();
	}

	@Override
	public List<SecRole> getRoleListByUserAndSubsys(Map<String, String> map) {
		return userMapper.getRoleListByUserAndSubsys(map);
	}

	@Override
	public void addTerritory(SecTerritory secTerr) {
		userMapper.addTerritory(secTerr);
	}

	@Override
	public void delTerrByCode(String terr_code) {
		userMapper.delTerrByCode(terr_code);
	}

	@Override
	public void updateTerritory(SecTerritory secTerr) {
		userMapper.updateTerritory(secTerr);
	}

	@Override
	public SecLevel getSecLevelByCode(Integer seclv_code) {
		return userMapper.getSecLevelByCode(seclv_code);
	}

	@Override
	public void addSeclv(SecLevel seclv) {
		userMapper.addSeclv(seclv);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seclv_code", seclv.getSeclv_code());
		map.put("leak_time", 20);
		map.put("archive_time", 0);
		map.put("allow_reprint", 0);
		map.put("is_paper_audit", "N");
		map.put("is_cd_audit", "N");
		userMapper.addSysSeclevel(map);
	}

	@Override
	public void delSecLvByCode(Integer seclv_code) {
		userMapper.delSecLvByCode(seclv_code);
	}

	@Override
	public void updateSeclv(SecLevel seclv) {
		userMapper.updateSeclv(seclv);
	}

	@Override
	public List<SecDept> getSecDeptByType(String dept_type_code) {
		return userMapper.getSecDeptByType(dept_type_code);
	}

	@Override
	public List<SecDept> getSecDeptByLevel(Integer dept_level_code) {
		return userMapper.getSecDeptByLevel(dept_level_code);
	}

	@Override
	public List<SecDept> getSecDeptByTerr(String terr_code) {
		return userMapper.getSecDeptByTerr(terr_code);
	}

	@Override
	public List<RealUser> getRealUser(Map<String, String> map) {
		return userMapper.getRealUser(map);
	}

	@Override
	public void delRealUserById(String real_user_id) {
		userMapper.delRealUserById(real_user_id);
	}

	@Override
	public RealUser getRealUserById(String real_user_id) {
		return userMapper.getRealUserById(real_user_id);
	}

	@Override
	public List<SecUser> getSimpleSecUserByReal(String real_user_id) {
		return userMapper.getSimpleSecUserByReal(real_user_id);
	}

	@Override
	public void updateRealUserByReal(RealUser realUser) {
		userMapper.updateRealUserByReal(realUser);
	}

	@Override
	public List<SecOperation> getAllMenuOper() {
		return userMapper.getAllMenuOper();
	}

	@Override
	public List<SecOperation> getSubOperByCode(String oper_code) {
		return userMapper.getSubOperByCode(oper_code + "__");
	}

	@Override
	public List<SecDept> getAllSecDept() {
		return userMapper.getAllSecDept();
	}

	@Override
	public boolean authenticate(String user_iidd, String user_ppww) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_iidd", user_iidd);
		map.put("user_ppww", user_ppww);
		return userMapper.authenticate(map) > 0;
	}

	@Override
	public List<SecRole> getSecRole(Map<String, String> map) {
		List<SecRole> roleList = userMapper.getSecRole(map);
		for (SecRole item : roleList) {
			if (userMapper.getRoleHasUserCount(item.getRole_id()) > 0) {
				item.setHasUser(true);
			}
		}
		return roleList;
	}

	@Override
	public List<SecRole> getAdminRoleBySubsys(Map<String, Object> map) {
		return userMapper.getAdminRoleBySubsys(map);
	}

	@Override
	public Integer getAdminRoleIdByUser(Map<String, Object> map) {
		return userMapper.getAdminRoleIdByUser(map);
	}

	@Override
	public List<SecRole> getSpecialRoleBySubsys(Map<String, Object> map) {
		return userMapper.getSpecialRoleBySubsys(map);
	}

	@Override
	public Integer getSpecialRoleIdByUser(Map<String, Object> map) {
		return userMapper.getSpecialRoleIdByUser(map);
	}

	@Override
	public String getSubsysNameByRoleId(String role_id) {
		return userMapper.getSubsysNameByRoleId(role_id);
	}

	@Override
	public List<String> getScopeMemberCode(String domain) {
		return userMapper.getScopeMemberCode(domain);
	}

	@Override
	public String getDomainByUserAndRole(String user_iidd, String role_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		map.put("role_id", role_id);
		return userMapper.getDomainByUserAndRole(map);
	}

	@Override
	public void updateScopeMemberCode(String domain, String codes) {
		userMapper.delScopeMemberCode(domain);
		Map<String, String> map = new HashMap<String, String>();
		map.put("domain", domain);
		for (String item : codes.split(",")) {
			if (StringUtils.hasLength(item.trim())) {
				map.put("member_code", item.trim());
				userMapper.addScopeMemberCode(map);
			}
		}
	}

	@Override
	public void updateSpecialRole(String user_iidd, String subsys_code, String role_id, String role_spec_key) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		map.put("subsys_code", subsys_code);
		map.put("role_type", Constants.ROLE_TYPE_SPECIAL);
		// 删除该特殊角色的用户原来配置的资源Code
		userMapper.delScopeMemberCodeByUser(user_iidd);
		userMapper.deleteUserRoleByUser(map);
		if (role_id.equals("-1")) {// -1为清除该类型角色
			SecRoleUser secRoleUser = null;
			String domain = "";
			String domain_table = "";
			if (StringUtils.hasLength(role_spec_key)) {
				domain = user_iidd.toUpperCase() + "_" + role_spec_key;
				if (role_spec_key.equalsIgnoreCase(Constants.ROLE_KEY_DEPT)) {
					domain_table = Constants.ROLE_DOMAIN_TABLE_DEPT;
				} else if (role_spec_key.equalsIgnoreCase(Constants.ROLE_KEY_TERR)) {
					domain_table = Constants.ROLE_DOMAIN_TABLE_TERR;
				}
			}
			// secRoleUser = new SecRoleUser(user_iidd,
			// Integer.parseInt(item.trim()));
			secRoleUser = new SecRoleUser(user_iidd, role_id, domain, domain_table);
			userMapper.addUserRoleRel(secRoleUser);
		}
	}

	@Override
	public List<SecRole> getSpecialRoleByUserOnly(Map<String, Object> map) {
		return userMapper.getSpecialRoleByUserOnly(map);
	}

	@Override
	public List<String> getSpecialOperByUserOnly(String user_iidd) {
		return userMapper.getSpecialOperByUserOnly(user_iidd);
	}

	@Override
	public List<String> getAllOperByUserOnly(String user_iidd) {
		return userMapper.getAllOperByUserOnly(user_iidd);
	}

	@Override
	public List<String> getNonOperByUserOnly(String user_iidd) {
		return userMapper.getNonOperByUserOnly(user_iidd);
	}

	@Override
	public List<SecUser> getSecUserList(Map<String, String> map) {
		return userMapper.getSecUserList(map);
	}

	@Override
	public Post getPostByPostId(String post_id) {
		return userMapper.getPostByPostId(post_id);
	}

	@Override
	public ConfigItem getConfigItemValue(String item_key) {
		return userMapper.getConfigItemValue(item_key);
	}

	@Override
	public void updateConfigItem(ConfigItem configItem) {
		userMapper.updateConfigItem(configItem);
	}

	@Override
	public Integer getPwdUpdateDays(String user_iidd) {
		return userMapper.getPwdUpdateDays(user_iidd);
	}

	@Override
	public void changeUserPwd(Map<String, String> map) {
		userMapper.changeUserPwd(map);
	}

	@Override
	public void logfailTimesPlus(String user_iidd, HttpServletRequest request) {
		Integer times = userMapper.getLogfailTimes(user_iidd);
		if (times == null) {
			times = 1;
		}
		times++;
		Map<String, String> map = new HashMap<String, String>();
		map.put("user_iidd", user_iidd);
		ConfigItem fail_times = getConfigItemValue(ConfigItem.KEY_FAIL_TIMES);
		if (fail_times.getStartuse() == 1 && times >= Integer.parseInt(fail_times.getItem_value())) {
			map.put("status", "1");
			// 锁定用户
			updateUserStatus(map);
			// SecUser curUser = getSecUserByUid(user_iidd);
			// TODO:记录到登录日志表里
			map.put("times", "0");
		} else {
			map.put("times", String.valueOf(times));
		}
		userMapper.logfailTimesPlus(map);
	}

	@Override
	public String getUserNameByUserId(String user_iidd) {
		return userMapper.getUserNameByUserId(user_iidd);
	}

	@Override
	public String getDeptNameByUserId(String user_iidd) {
		return userMapper.getDeptNameByUserId(user_iidd);
	}

	@Override
	public Integer getMaxPostClassByDept(String dept_id) {
		return userMapper.getMaxPostClassByDept(dept_id);
	}

	@Override
	public List<SecUserPost> getSecUserPostByDeptId(String dept_id) {
		return userMapper.getSecUserPostByDeptId(dept_id);
	}

	@Override
	public List<String> getPostClassByDeptId(String dept_id) {
		return userMapper.getPostClassByDeptId(dept_id);
	}

	@Override
	public String getRoleNameByRoleId(String role_id) {
		return userMapper.getRoleNameByRoleId(role_id);
	}

	@Override
	public List<SecLevel> getAllowSecLevelByUserId(String user_iidd) {
		return userMapper.getAllowSecLevelByUserId(user_iidd);
	}

	@Override
	public List<ApproverUser> getFuzzyUser(String dept_id, String fuzzy) {
		if (dept_id == null) {
			dept_id = "";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dept_id", dept_id);
		map.put("fuzzy", fuzzy);
		return userMapper.getFuzzyUser(map);
	}

	@Override
	public List<ApproverUser> getFuzzyUserByUserId(String dept_id, String user_iidd) {
		if (dept_id == null) {
			dept_id = "";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dept_id", dept_id);
		map.put("user_iidd", user_iidd);
		return userMapper.getFuzzyUserByUserId(map);
	}

	@Override
	public List<ApproverUser> getFuzzyUserByUserName(String dept_id, String user_name) {
		if (dept_id == null) {
			dept_id = "";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dept_id", dept_id);
		map.put("user_name", user_name);
		return userMapper.getFuzzyUserByUserName(map);
	}

	@Override
	public List<SecSubsys> getAllSubsys() {
		return userMapper.getAllSubsys();
	}

	@Override
	public List<SecLevel> getAllSecLevel() {
		return userMapper.getAllSecLevel();
	}

	@Override
	public List<SecRole> getAllSecRole(Map<String, String> map) {
		return userMapper.getAllSecRole(map);
	}

	@Override
	public List<User> getFuzzyUserDept(String dept_id, String fuzzy) {
		if (dept_id == null) {
			dept_id = "";
		}
		List<User> users = new ArrayList<User>();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dept_id", dept_id);
		map.put("fuzzy", fuzzy);
		String sep = "/";
		for (User user : userMapper.getFuzzyUserDept(map)) {
			StringBuffer sb = new StringBuffer();
			sb.append(user.getDept_name());
			if (isNotNullOrEmpty(user.getParent1())) {
				sb.append(sep).append(user.getParent1());
			}
			if (isNotNullOrEmpty(user.getParent2())) {
				sb.append(sep).append(user.getParent2());
			}
			if (isNotNullOrEmpty(user.getParent3())) {
				sb.append(sep).append(user.getParent3());
			}
			if (isNotNullOrEmpty(user.getParent4())) {
				sb.append(sep).append(user.getParent4());
			}
			if (isNotNullOrEmpty(user.getParent5())) {
				sb.append(sep).append(user.getParent5());
			}
			user.setDept_info(sb.toString());
			users.add(user);
		}
		return users;
	}

	private boolean isNotNullOrEmpty(String str) {
		if (null == str || str.equals("")) {
			return false;
		}
		return true;
	}

	@Override
	public SecDept getSecDeptByDeptCs(String dept_cs) throws Exception {
		List<SecDept> deptList = userMapper.getSecDeptByDeptCs(dept_cs);
		if (deptList == null || deptList.size() == 0) {
			throw new Exception("没有部门使用该级联编号[" + dept_cs + "]");
		} else if (deptList.size() > 1) {
			throw new Exception("级联编号[" + dept_cs + "]被多个部门使用");
		} else {
			return deptList.get(0);
		}
	}

	@Override
	public List<UserSecurity> getAllSecurityList() {
		return userMapper.getAllSecurityList();
	}

	@Override
	public void addSecurity(UserSecurity security) {
		userMapper.addSecurity(security);
	}

	@Override
	public UserSecurity getSecurityByCode(String security_code) {
		UserSecurity security = userMapper.getSecurityByCode(security_code);
		security.setPrint_name(getSeclvNamesByCodes(security.getPrintList()));
		security.setCopy_name(getSeclvNamesByCodes(security.getCopyList()));
		security.setImport_name(getSeclvNamesByCodes(security.getImportList()));
		security.setBurn_name(getSeclvNamesByCodes(security.getBurnList()));
		security.setCopy_burn_name(getSeclvNamesByCodes(security.getCopyBurnList()));
		security.setDevice_name(getSeclvNamesByCodes(security.getDeviceList()));
		security.setDefault_name(getSeclvNamesByCodes(security.getDefaultList()));
		return security;
	}

	private String getSeclvNamesByCodes(List<String> codeList) {
		if (codeList != null && codeList.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("codeList", codeList);
			List<String> nameList = userMapper.getSeclvNamesByCodes(map);
			return nameList == null ? "" : nameList.toString();
		} else {
			return "";
		}
	}

	@Override
	public void delSecurityByCode(String security_code) {
		userMapper.delSecurityByCode(security_code);
	}

	@Override
	public void updateSecurity(UserSecurity security) {
		userMapper.updateSecurity(security);
	}

	@Override
	public List<UserSecurity> getSecurityList() {
		return userMapper.getSecurityList();
	}

	@Override
	public UserSecurity getSecurityByUser(String user_iidd) {
		UserSecurity security = userMapper.getSecurityByUser(user_iidd);
		security.setPrint_name(getSeclvNamesByCodes(security.getPrintList()));
		security.setCopy_name(getSeclvNamesByCodes(security.getCopyList()));
		security.setImport_name(getSeclvNamesByCodes(security.getImportList()));
		security.setBurn_name(getSeclvNamesByCodes(security.getBurnList()));
		security.setCopy_burn_name(getSeclvNamesByCodes(security.getCopyBurnList()));
		security.setDevice_name(getSeclvNamesByCodes(security.getDeviceList()));
		security.setDefault_name(getSeclvNamesByCodes(security.getDefaultList()));
		return security;
	}

	@Override
	public List<SecLevel> getPrintSecLevelByUser(String user_iidd) {
		List<String> seclvCodeList = getSecurityByUser(user_iidd).getPrintList();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codeList", seclvCodeList);
		List<SecLevel> seclvList = userMapper.getSeclvListByCodes(map);
		return seclvList;
	}

	@Override
	public List<SecLevel> getCopySecLevelByUser(String user_iidd) {
		List<String> seclvCodeList = getSecurityByUser(user_iidd).getCopyList();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codeList", seclvCodeList);
		List<SecLevel> seclvList = userMapper.getSeclvListByCodes(map);
		return seclvList;
	}

	@Override
	public List<SecLevel> getImportSecLevelByUser(String user_iidd) {
		List<String> seclvCodeList = getSecurityByUser(user_iidd).getImportList();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codeList", seclvCodeList);
		List<SecLevel> seclvList = userMapper.getSeclvListByCodes(map);
		return seclvList;
	}

	@Override
	public List<SecLevel> getBurnSecLevelByUser(String user_iidd) {
		List<String> seclvCodeList = getSecurityByUser(user_iidd).getBurnList();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codeList", seclvCodeList);
		List<SecLevel> seclvList = userMapper.getSeclvListByCodes(map);
		return seclvList;
	}

	@Override
	public List<SecLevel> getCopyBurnSecLevelByUser(String user_iidd) {
		List<String> seclvCodeList = getSecurityByUser(user_iidd).getCopyBurnList();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codeList", seclvCodeList);
		List<SecLevel> seclvList = userMapper.getSeclvListByCodes(map);
		return seclvList;
	}

	@Override
	public List<SecLevel> getDeviceSecLevelByUser(String user_iidd) {
		List<String> seclvCodeList = getSecurityByUser(user_iidd).getDeviceList();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codeList", seclvCodeList);
		List<SecLevel> seclvList = userMapper.getSeclvListByCodes(map);
		return seclvList;
	}

	@Override
	public List<SecLevel> getDefaultSecLevelByUser(String user_iidd) {
		List<String> seclvCodeList = getSecurityByUser(user_iidd).getDefaultList();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("codeList", seclvCodeList);
		List<SecLevel> seclvList = userMapper.getSeclvListByCodes(map);
		return seclvList;
	}

	@Override
	public DeptAdminConfig getDeptAdminConfig(String user_iidd) {
		List<String> dept_id_list = getAdminDeptIdListByUser(user_iidd);
		if (dept_id_list != null && dept_id_list.size() > 0) {
			String dept_ids = dept_id_list.toString().replace("[", "").replace("]", "").replace(" ", "");
			String dept_names = getDeptNamesByIdList(dept_id_list);
			String is_inherit = userMapper.getAdminInheritByUser(user_iidd);
			return new DeptAdminConfig(user_iidd, dept_ids, dept_names, is_inherit);
		} else {
			return new DeptAdminConfig(user_iidd, "", "", "N");
		}
	}

	@Override
	public String getDeptNamesByIdList(List<String> deptIdList) {
		if (deptIdList != null && deptIdList.size() > 0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("deptIdList", deptIdList);
			List<String> deptNameList = userMapper.getDeptNameListByIdList(map);
			String dept_names = deptNameList.toString().replace("[", "").replace("]", "");
			return dept_names;
		} else {
			return "";
		}
	}

	public List<String> getAdminDeptIdListByUser(String user_iidd) {
		return userMapper.getAdminDeptIdListByUser(user_iidd);
	}

	@Override
	public void configDeptAdmin(DeptAdminConfig adminConfig) {
		userMapper.delSecDeptAdminByUser(adminConfig.getUser_iidd());
		SecDept dp = null;
		SecDeptAdmin deptAdmin = null;
		for (String dept_id : adminConfig.getDept_ids().split(",")) {
			dp = getSecDeptById(dept_id);
			deptAdmin = new SecDeptAdmin(adminConfig.getUser_iidd(), dept_id, dp.getDept_cs(),
					adminConfig.getIs_inherit());
			userMapper.addSecDeptAdmin(deptAdmin);
		}
	}

	@Override
	public String getSecurityCodeByName(String security_name) {
		List<String> list = userMapper.getSecurityCodeByName(security_name);
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public String getDeptIdByName(String dept_name) {
		List<String> list = userMapper.getDeptIdByName(dept_name);
		if (list != null && list.size() == 1) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public boolean isUserExist(String user_iidd) {
		return userMapper.getUserCountByUserId(user_iidd) > 0;
	}

	@Override
	public boolean isSeclvExist(Integer seclv_code, String seclv_name) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("seclv_code", seclv_code);
		map.put("seclv_name", seclv_name);
		return userMapper.getSeclvCountByCodeName(map) > 0;
	}

	@Override
	public boolean isModuleEnable(String module_code) {
		return userMapper.getModuleEnableCount(module_code) > 0;
	}

	@Override
	public String getWorkerRoleId() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("role_type", 3);
		map.put("role_spec_key", "WORKER");
		return userMapper.getRoleIdByTypeAndKey(map);
	}

	@Override
	public void updateViewStatus(String accept_user_iidd, String url) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accept_user_iidd", accept_user_iidd);
		map.put("url", url);
		userMapper.updateViewStatus(map);
	}

	@Override
	public boolean checkPassNumIsUsed(String pass_num) {
		if (!StringUtils.hasLength(pass_num)) {
			return false;
		}
		Integer user = 0;
		user = userMapper.getSecUserCountByPassNum(pass_num);
		if (user != 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getSecUserIdByPassNum(String pass_num) {
		return userMapper.getSecUserIdByPassNum(pass_num);
	}

	@Override
	public void recoverSecUserById(String user_iidd) {
		userMapper.recoverSecUserById(user_iidd);
	}

	@Override
	public void recoverSecLvByCode(Integer seclv_code) {
		userMapper.recoverSecLvByCode(seclv_code);
	}

	@Override
	public boolean isSeclvExistByName(String seclv_name) {
		return userMapper.getSeclvCountByName(seclv_name) > 0;
	}

	@Override
	public boolean isSeclvExistByRank(Integer seclv_rank) {
		return userMapper.getSeclvCountByRank(seclv_rank) > 0;
	}

	@Override
	public boolean isSeclvExistByCode(Integer seclv_code) {
		return userMapper.getSeclvCountByCode(seclv_code) > 0;
	}

	@Override
	public Integer getSeclvCodeByName(String seclv_name) {
		return userMapper.getSeclvCodeByName(seclv_name);
	}

	@Override
	public Integer getSeclvCodeByRank(Integer seclv_rank) {
		return userMapper.getSeclvCodeByRank(seclv_rank);
	}

	@Override
	public List<SecLevel> getSeclvList(Map<String, Object> map) {
		return userMapper.getSeclvList(map);
	}

	@Override
	public int getAllCommonUserCount() {
		return userMapper.getAllCommonUserCount();
	}

	@Override
	public boolean isUserLocked(String user_iidd) {
		return userMapper.getUserLocked(user_iidd) > 0;
	}

	@Override
	public int getSeclvCount() {
		return userMapper.getSeclvCount();
	}

	@Override
	public boolean isSeclvExistByOthername(String othername) {
		return userMapper.getSeclvCountByOthername(othername) > 0;
	}

	@Override
	public Integer getSeclvCodeByOthername(String othername) {
		return userMapper.getSeclvCodeByOthername(othername);
	}

	@Override
	public String getSysConfigItemValue(String item_key) {

		return userMapper.getSysConfigItemValue(item_key);
	}

	@Override
	public List<SecOperation> getSecOperBySubsysAll(String subsys_code) {

		return userMapper.getSecOperBySubsysAll(subsys_code);
	}

	@Override
	public Integer getSysConfigAdminVersion() {
		return userMapper.getSysConfigAdminVersion();
	}

	@Override
	public void delSysConfigAdminVersion() {
		userMapper.delSysConfigAdminVersion();
		userMapper.delSysManageUserMenu();
		userMapper.updateSysManageUserMenu();
	}

	@Override
	public void insertSysConfigAdminVersion() {
		userMapper.insertSysConfigAdminVersion();
		userMapper.insertSysManageUserMenu();
		userMapper.updateSysManageUserMenuDept();
	}

	@Override
	public int getIpConfigIsExist(String userType) {
		return userMapper.getIpConfigIsExist(userType);
	}

	@Override
	public int getIpConfigStartuse(String userType) {
		return userMapper.getIpConfigStartuse(userType);
	}

	@Override
	public String getLimitIpConfig(String userType) {
		return userMapper.getLimitIpConfig(userType);
	}

	@Override
	public List<SecUser> getAdminUser(Map<String, String> map, RowBounds rbs) {
		List<SecUser> userList = new ArrayList<SecUser>();
		List<String> userId = userMapper.getUserIdByRole(map);
		for (int i = 0; i < userId.size(); i++) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("user_iidd", userId.get(i));
			List<SecUser> secuserList = userMapper.getUserByDept(map1, rbs);
			for (int j = 0; j < secuserList.size(); j++) {
				userList.add(secuserList.get(j));
			}
		}
		if (userList != null && userList.size() > 0) {
			Map<String, String> userMap = new HashMap<String, String>();
			for (SecUser user : userList) {
				userMap.put("user_iidd", user.getUser_iidd());
				List<SecRole> roleList = userMapper.getRoleListByUser(userMap);
				user.setUserRoleList(roleList);
			}
		}
		return userList;
	}

	@Override
	public String getDefaultPwd(String item_key) {
		return userMapper.getDefaultPwd(item_key);
	}

	@Override
	public String getCurUserPwd(String user_iidd) {
		return userMapper.getCurUserPwd(user_iidd);
	}

	@Override
	public List<String> getUser_iiddsGroup() {
		return userMapper.getUser_iiddsGroup();
	}

	@Override
	public List<String> getRole_idsGroup(String user_iidd) {
		return userMapper.getRole_idsGroup(user_iidd);
	}

	@Override
	public List<String> getDept_idsByTwo(Map<String, String> map) {
		return userMapper.getDept_idsByTwo(map);
	}

	@Override
	public List<String> getDept_idsIsY() {
		return userMapper.getDept_idsIsY();
	}

	@Override
	public void configDeptAdmin1(DeptAdminConfig adminConfig, String role_id) {
		String user_iidd = adminConfig.getUser_iidd();
		// String role_id = adminConfig.getRole_id();

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		map.put("role_id", role_id);
		SecDept dp = null;
		for (String dept_id : adminConfig.getDept_ids().split(",")) {
			dp = userMapper.getSecDeptById(dept_id);
			map.put("dept_id", dept_id);
			map.put("dept_cs", dp.getDept_cs());
			userMapper.addSecDeptAdmin1(map);
		}
	}

	@Override
	public void insertProxyInfo(Map<String, String> map) {
		userMapper.insertProxyInfo(map);
	}

	@Override
	public void deleteProxyInfo(Map<String, String> map) {
		userMapper.deleteProxyInfo(map);
	}

	/**
	 * 根据user_id查询被代理人
	 * 
	 * @param user_iidd
	 */
	@Override
	public List<SecRoleUser> getAgentByUser_iidd(Map<String, String> map) {
		return userMapper.getAgentByUser_iidd(map);
	}
}
