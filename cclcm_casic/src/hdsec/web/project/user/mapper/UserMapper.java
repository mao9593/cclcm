package hdsec.web.project.user.mapper;

import hdsec.web.project.user.model.Acl;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.ConfigItem;
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

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

	List<SecUser> getSecUser(SecUser ups);

	SecUser getSecUserByUid(String userid);

	List<String> getAllOperCodeByUserId(String userId);

	int isPowerUser(String userId);

	List<String> getAllOperCode();

	List<Post> getAllPostAsCon();

	List<SecCommonGroup> getSecCommonGroupByUserId(String userId);

	List<SecCommonContact> getCommonContactForDept(Map<String, Object> map);

	List<SecCommonContact> getCommonContactForUser(Map<String, Object> map);

	List<DeptTree> getDeptTree();

	int getUserByDeptSize(Map<String, Object> map);

	List<SecUser> getUserByDept(Map<String, Object> map, RowBounds rbs);

	String getDeptNameByDeptId(String dept_id);

	List<SecRole> getSecRoleBySubsys(Map<String, String> map);

	List<SecSubsys> getAllSubsysAsCon();

	int getRoleHasUserCount(String role_id);

	int getRoleNameCount(String role_name);

	void addSecRole(SecRole secRole);

	void delSecRoleByRoleId(String role_id);

	SecRole getSecRoleByRoleId(String role_id);

	void updateSecRole(Map<String, String> map);

	SecSubsys getSubsysBySubsysCode(String subsys_code);

	List<SecUser> getUserByRole(String role_id);

	String getSubsysCodeByRoleId(String role_id);

	List<SecOperation> getSecOperBySubsys(String subsys_code);

	List<String> getConfigedOperByRoleAndSubsys(Map<String, String> map);

	List<Post> getPostByDeptAsCon(String dept_id);

	Object addSecUser(SecUser secUser);

	void updateUserStatus(Map<String, String> map);

	List<String> getUserIdList(Map<String, String> map);

	RealUser getRealUserByUid(String user_iidd);

	void resetUserPwd(Map<String, String> map);

	SecUserDept getUserDeptByUser(String user_iidd);

	List<SecRole> getRoleListByUser(Map<String, String> map);

	int getDeptPostCount(Map<String, String> map);

	List<OperTree> getOperTreeBySubsys(String subsys_code);

	SecOperation getSecOperByCodeAndSubsys(Map<String, String> map);

	int getSubOperCountByCode(Map<String, String> map);

	List<SecOperation> getSubOperListByCode(Map<String, String> map);

	int getOperCountByCode(String oper_code);

	int addSecOper(SecOperation secOper);

	List<String> getSecOperCodeWithSub(Map<String, String> map);

	void updateSecOper(SecOperation secOper);

	SecDept getSecDeptById(String dept_id);

	SecTerritory getSecTerrByCode(String terr_code);

	SecDeptLevel getSecDeptLevelByCode(String dept_level_code);

	SecDeptType getSecDeptTypeByCode(String dept_type_code);

	List<SecSubsys> getSubsysListByDeptId(String dept_id);

	List<SecDept> getSecDeptByIdWithSub(String dept_id);

	int getUserCountByDeptCsWithSub(String dept_cs);

	List<SecTerritory> getAllSecTerritory();

	List<SecDeptLevel> getAllSecDeptLevel();

	List<SecDeptType> getAllSecDeptType();

	List<SecDept> getSubDeptListByDeptCs(String dept_id);

	int getSecDeptCountByDeptCs(String dept_cs);

	void deleteDeptSubsysByDept(String dept_id);

	List<String> getSecDeptIdWithSub(String dept_id);

	List<SecUserPost> getDeptPostListByDept(Map<String, String> map);

	SecUserPost getSecUserPostByUser(String user_iidd);

	List<SecUserPost> getAllUserPostList();

	void addSecUserPost(SecUserPost post);

	SecUserPost getSecUserPostById(String post_id);

	void updateSecUserPost(SecUserPost post);

	List<SecUser> getUserByPost(String post_id);

	List<String> getDeptByPost(String post_id);

	void updatePostLevel(Map<String, Object> map);

	void updatePostClass(Map<String, Object> map);

	List<SecDeptType> getAllDeptType();

	void addDeptType(SecDeptType type);

	SecDeptType getDeptTypeByCode(String dept_type_code);

	void delDeptTypeByCode(String dept_type_code);

	void updateDeptType(SecDeptType secDeptType);

	List<SecDeptLevel> getDeptLevel();

	void addSubsys(SecSubsys secSubsys);

	void updateSubsys(SecSubsys secSubsys);

	void delSubsysByCode(String subsys_code);

	List<SecLevel> getSecLevel();

	List<String> getRealUserIdWithSub(String user_iidd);

	List<SecRole> getRoleListByUserAndSubsys(Map<String, String> map);

	void addTerritory(SecTerritory secTerr);

	void delTerrByCode(String terr_code);

	void updateTerritory(SecTerritory secTerr);

	SecLevel getSecLevelByCode(Integer seclv_code);

	void addSeclv(SecLevel seclv);

	void delSecLvByCode(Integer seclv_code);

	void updateSeclv(SecLevel seclv);

	List<SecDept> getSecDeptByType(String dept_type_code);

	List<SecDept> getSecDeptByLevel(Integer dept_level_code);

	List<SecDept> getSecDeptByTerr(String terr_code);

	List<RealUser> getRealUser(Map<String, String> map);

	RealUser getRealUserById(String real_user_id);

	void delRealUserById(String real_user_id);

	List<SecUser> getSimpleSecUserByReal(String real_user_id);

	void updateRealUserByReal(RealUser realUser);

	List<SecOperation> getAllMenuOper();

	List<SecOperation> getSubOperByCode(String string);

	List<SecDept> getAllSecDept();

	int authenticate(Map<String, String> map);

	List<SecRole> getSecRole(Map<String, String> map);

	List<SecRole> getAdminRoleBySubsys(Map<String, Object> map);

	Integer getAdminRoleIdByUser(Map<String, Object> map);

	List<SecRole> getSpecialRoleBySubsys(Map<String, Object> map);

	Integer getSpecialRoleIdByUser(Map<String, Object> map);

	String getSubsysNameByRoleId(String role_id);

	String getDomainByUserAndRole(Map<String, Object> map);

	List<String> getScopeMemberCode(String domain);

	List<SecRole> getSpecialRoleByUserOnly(Map<String, Object> map);

	List<String> getSpecialOperByUserOnly(String user_iidd);

	List<String> getAllOperByUserOnly(String user_iidd);

	List<String> getNonOperByUserOnly(String user_iidd);

	List<SecUser> getSecUserList(Map<String, String> map);

	Post getPostByPostId(String post_id);

	ConfigItem getConfigItemValue(String item_key);

	void updateConfigItem(ConfigItem configItem);

	Integer getPwdUpdateDays(String user_iidd);

	void changeUserPwd(Map<String, String> map);

	Integer getLogfailTimes(String user_iidd);

	void logfailTimesPlus(Map<String, String> map);

	String getUserNameByUserId(String user_iidd);

	String getDeptNameByUserId(String user_iidd);

	Integer getMaxPostClassByDept(String dept_id);

	List<SecUserPost> getSecUserPostByDeptId(String dept_id);

	List<String> getPostClassByDeptId(String dept_id);

	String getRoleNameByRoleId(String role_id);

	List<SecLevel> getAllowSecLevelByUserId(String user_iidd);

	List<ApproverUser> getFuzzyUser(Map<String, Object> map);

	List<ApproverUser> getFuzzyUserByUserId(Map<String, Object> map);

	List<ApproverUser> getFuzzyUserByUserName(Map<String, Object> map);

	void addSecDept(Map<String, String> map);

	void addSecDeptSubsys(Map<String, String> map1);

	void delSecUserById(String user_iidd);

	void deleteModifiedOperByRole(Map<String, Object> map);

	void addRoleOper(Acl acl);

	void insertRealUser(SecUser secUser);

	void updateSecUser(Map<String, Object> map);

	void updateSecUserName(Map<String, Object> map);

	void updateRealUser(Map<String, Object> map);

	void updateUserDept(Map<String, String> map);

	void clearUserPostByUser(String string);

	void deleteUserRoleByUser(Map<String, Object> map);

	void addUserRoleRel(SecRoleUser secRoleUser);

	void delRoleOperByOper(Map<String, String> map);

	void delSecOperByCode(Map<String, String> map);

	void updateSecDept(Map<String, String> map);

	void delSecDeptById(String dept_id);

	void deleteDeptPostByDept(String dept_id);

	void addDeptPostRel(Map<String, String> map);

	void delSecUserPostById(String post_id);

	void addSubsysRootOper(SecSubsys secSubsys);

	void delSubsysRootOper(String subsys_code);

	void delScopeMemberCode(String domain);

	void addScopeMemberCode(Map<String, String> map);

	void delScopeMemberCodeByUser(String user_iidd);

	List<SecSubsys> getAllSubsys();

	List<SecLevel> getAllSecLevel();

	List<SecRole> getAllSecRole(Map<String, String> map);

	List<User> getFuzzyUserDept(Map<String, Object> map);

	List<SecDept> getSecDeptByDeptCs(String dept_cs);

	List<UserSecurity> getAllSecurityList();

	void addSecurity(UserSecurity security);

	UserSecurity getSecurityByCode(String security_code);

	void delSecurityByCode(String security_code);

	List<String> getSeclvNamesByCodes(Map<String, Object> map);

	void updateSecurity(UserSecurity security);

	List<UserSecurity> getSecurityList();

	UserSecurity getSecurityByUser(String user_iidd);

	List<SecLevel> getSeclvListByCodes(Map<String, Object> map);

	SecDept getSecDeptByUserId(String user_iidd);

	void addSecDeptAdmin(SecDeptAdmin deptAdmin);

	List<String> getRoleIdListByUser(Map<String, Object> map);

	void delSecDeptAdminByUser(String user_iidd);

	List<String> getAdminDeptIdListByUser(String user_iidd);

	List<String> getDeptNameListByIdList(Map<String, Object> map);

	String getAdminInheritByUser(String user_iidd);

	List<String> getSecurityCodeByName(String security_name);

	List<String> getDeptIdByName(String dept_name);

	int getUserCountByUserId(String user_iidd);

	int getSeclvCountByCodeName(Map<String, Object> map);

	int getModuleEnableCount(String module_code);

	String getRoleIdByTypeAndKey(Map<String, Object> map);

	Integer getSecUserCountByPassNum(String pass_num);

	void updateViewStatus(Map<String, Object> map);

	List<SecUser> getSecUserByIdcard(String idcard);

	String getSecUserIdByPassNum(String pass_num);

	void recoverSecUserById(String user_iidd);

	void recoverSecLvByCode(Integer seclv_code);

	void addSysSeclevel(Map<String, Object> map);

	int getSeclvCountByName(String seclv_name);

	int getSeclvCountByRank(Integer seclv_rank);

	int getSeclvCountByCode(Integer seclv_code);

	Integer getSeclvCodeByName(String seclv_name);

	Integer getSeclvCodeByRank(Integer seclv_rank);

	List<SecLevel> getSeclvList(Map<String, Object> map);

	int getAllCommonUserCount();

	void delSecDeptAdminByUserRole(Map<String, Object> map);

	int getUserLocked(String user_iidd);

	int getSeclvCount();

	int getSeclvCountByOthername(String othername);

	Integer getSeclvCodeByOthername(String othername);

	String getSysConfigItemValue(String item_key);

	List<SecOperation> getSecOperBySubsysAll(String subsys_code);

	Integer getSysConfigAdminVersion();

	void delSysConfigAdminVersion();

	void delSysManageUserMenu();

	void updateSysManageUserMenu();

	void insertSysConfigAdminVersion();

	void insertSysManageUserMenu();

	void updateSysManageUserMenuDept();

	int getIpConfigIsExist(String userType);

	int getIpConfigStartuse(String userType);

	String getLimitIpConfig(String userType);

	List<String> getUserIdByRole(Map<String, String> map);

	// add by wx 20150501 获取用户默认密码
	String getDefaultPwd(String item_key);

	// above

	// add by wx 20150501 获取当前用户密码MD5加密值
	String getCurUserPwd(String user_iidd);

	// above
	List<String> getUser_iiddsGroup();

	List<String> getRole_idsGroup(String user_iidd);

	List<String> getDept_idsByTwo(Map<String, String> map);

	List<String> getDept_idsIsY();

	void addSecDeptAdmin1(Map<String, Object> map);

	// 增加角色代理信息
	void insertProxyInfo(Map<String, String> map);

	// 删除角色代理信息
	void deleteProxyInfo(Map<String, String> map);

	List<SecRoleUser> getAgentByUser_iidd(Map<String, String> map);

}
