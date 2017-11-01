package hdsec.web.project.user.service;

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
import ht304.hdsec.framework.user.service.FrameworkUserService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;

/**
 * 
 * 2014-4-11 下午6:38:39
 * 
 * @author renmingfei
 */
/**
 * 
 * 2014-4-11 下午6:38:40
 * 
 * @author renmingfei
 */
public interface UserService extends FrameworkUserService {

	/**
	 * 获取可分页展示的用户列表
	 * 
	 * @param secUser
	 * @param ps
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	List<SecUser> getSecUser(SecUser secUser);

	/**
	 * 通过用户ID查询用户
	 * 
	 * @param userId
	 * @return
	 */
	SecUser getSecUserByUid(String userId);

	/**
	 * 通过身份证号查询用户
	 * 
	 * @param userId
	 * @return
	 * @throws Exception
	 */
	SecUser getSecUserByIdcard(String idcard) throws Exception;

	/**
	 * 通过用户ID，查询用户有权限操作的操作代码列表
	 * 
	 * @param userId
	 * @return
	 */
	Collection<String> getAllOperCodeByUserId(String userId);

	/**
	 * 获取所有岗位信息
	 * 
	 * @return
	 */
	List<Post> getAllPostAsCon();

	/**
	 * 通过用户ID查询常用联系人/组
	 * 
	 * @param user_iidd
	 * @return
	 */
	List<SecCommonGroup> getSecCommonGroupByUserId(String userId);

	/**
	 * 获得常用联系部门
	 * 
	 * @param map
	 * @return
	 */
	List<SecCommonContact> getCommonContactForDept(Map<String, Object> map);

	/**
	 * 获得常用联系人
	 * 
	 * @param map
	 * @return
	 */
	List<SecCommonContact> getCommonContactForUser(Map<String, Object> map);

	/**
	 * 获得部门树
	 * 
	 * @return
	 */
	List<DeptTree> getDeptTree();

	/**
	 * 用户管理用户总数(用于分页)
	 * 
	 * @param map
	 * @return
	 */
	int getUserByDeptSize(Map<String, Object> map);

	/**
	 * 用户管理页面查询用户
	 * 
	 * @return
	 */
	List<SecUser> getUserByDept(Map<String, Object> map, RowBounds rbs);

	/**
	 * 通过部门ID查询部门名称
	 * 
	 * @param dept_id
	 * @return
	 */
	String getDeptNameByDeptId(String dept_id);

	/**
	 * 查询角色列表
	 * 
	 * @param map
	 * @return
	 */
	List<SecRole> getSecRoleBySubsys(Map<String, String> map);

	/**
	 * 获取所有子系统的列表
	 * 
	 * @return
	 */
	List<SecSubsys> getAllSubsysAsCon();

	/**
	 * 查询角色名称是否已经存在
	 * 
	 * @param role_name
	 * @return
	 */
	int getRoleNameCount(String role_name);

	/**
	 * 添加用户角色
	 * 
	 * @param map
	 */
	void addSecRole(SecRole secRole);

	/**
	 * 删除用户角色
	 * 
	 * @param role_id
	 */
	void delSecRole(String role_id);

	/**
	 * 根据角色ID查询角色对象
	 * 
	 * @param intValue
	 * @return
	 */
	SecRole getSecRoleByRoleId(String role_id);

	/**
	 * 修改角色信息
	 * 
	 * @param map
	 */
	void updateSecRole(Map<String, String> map);

	/**
	 * 根据子系统代码查询子系统名称
	 * 
	 * @param subsys_code
	 * @return
	 */
	SecSubsys getSubsysBySubsysCode(String subsys_code);

	/**
	 * 根据角色ID查询关联的用户
	 * 
	 * @param role_id
	 * @return
	 */
	List<SecUser> getUserByRole(String role_id);

	/**
	 * 通过角色ID查询子系统编码
	 * 
	 * @param intValue
	 * @return
	 */
	String getSubsysCodeByRoleId(String intValue);

	/**
	 * 查询某一个子系统内所有的操作
	 * 
	 * @param subsys_code
	 * @return
	 */
	List<SecOperation> getSecOperBySubsys(String subsys_code);

	/**
	 * 通过子系统编码和角色ID查询某角色已经配置的操作列表
	 * 
	 * @param map
	 * @return
	 */
	List<String> getConfigedOperByRoleAndSubsys(Map<String, String> map);

	/**
	 * 更新角色对应的操作权限到数据库
	 * 
	 * @param subSysCode
	 * @param roleID
	 * @param modifiedOperCodeList
	 * @param aclList
	 * @return
	 */
	int updateRoleOper(String subsys_code, String role_id, List<String> modifiedOperCodeList, List<Acl> aclList);

	/**
	 * 查询部门内的岗位列表
	 * 
	 * @param dept_id
	 * @return
	 */
	List<Post> getPostByDeptAsCon(String dept_id);

	/**
	 * 增加安全用户
	 * 
	 * @param map
	 */
	void addSecUser(SecUser secUser, boolean real_blank, String role_id_list) throws Exception;

	/**
	 * 设置用户状态
	 * 
	 * @param map
	 */
	void updateUserStatus(Map<String, String> map);

	/**
	 * 删除安全用户
	 * 
	 * @param user_iidd
	 */
	void delSecUserById(String user_iidd);

	/**
	 * 根据条件查询安全用户ID列表
	 * 
	 * @param map
	 * @return
	 */
	List<String> getUserIdList(Map<String, String> map);

	/**
	 * 根据用户ID查询实际用户信息
	 * 
	 * @param user_iidd
	 * @return
	 */
	RealUser getRealUserByUid(String user_iidd);

	/**
	 * 修改安全用户信息
	 * 
	 * @param map
	 */
	void updateSecUser(Map<String, Object> map, boolean real_blank);

	/**
	 * 更新安全用户名字
	 * 
	 * @param String
	 */
	void updateSecUserName(Map<String, Object> map);

	/**
	 * 重置用户密码为默认密码
	 * 
	 * @param map
	 */
	void resetUserPwd(Map<String, String> map);

	/**
	 * 根据用户ID查询用户主部门
	 * 
	 * @param user_iidd
	 * @return
	 */
	SecUserDept getUserMainDeptByUser(String user_iidd);

	/**
	 * 根据用户ID查询用户拥有的角色列表
	 * 
	 * @param map
	 * @return
	 */
	List<SecRole> getRoleListByUser(Map<String, String> map);

	/**
	 * 查询某个部门是否配置了某个岗位
	 * 
	 * @param map
	 * @return
	 */
	int getDeptPostCount(Map<String, String> map);

	/**
	 * 修改用户部门
	 * 
	 * @param map
	 */
	void updateUserDept(Map<String, String> map);

	/**
	 * 更新用户和普通角色的关联
	 * 
	 * @param user_iidd
	 * @param newCodes
	 */
	void updateUserRole(String user_iidd, String subsys_code, String newCodes);

	/**
	 * 根据子系统编码查询该子系统的操作树
	 * 
	 * @param subsys_code
	 * @return
	 */
	List<OperTree> getOperTreeBySubsys(String subsys_code);

	/**
	 * 通过操作编号和子系统代号查询操作对象
	 * 
	 * @param oper_code
	 * @param subsys_code
	 * @return
	 */
	SecOperation getSecOperByCodeAndSubsys(String oper_code, String subsys_code);

	/**
	 * 查询某个操作的子操作数量，用以判断该操作是否可以直接删除
	 * 
	 * @param string
	 * @param subsys_code
	 * @return
	 */
	int getSubOperCountByCode(String oper_code, String subsys_code);

	/**
	 * 查询某个操作的子操作列表
	 * 
	 * @param string
	 * @param subsys_code
	 * @return
	 */
	List<SecOperation> getSubOperListByCode(String oper_code, String subsys_code);

	/**
	 * 根据操作编码查询操作对象个数
	 * 
	 * @param oper_code
	 * @return
	 */
	int getOperCountByCode(String oper_code);

	/**
	 * 添加操作节点
	 * 
	 * @param secOper
	 * @return
	 */
	int addSecOper(SecOperation secOper);

	/**
	 * 根据ID删除操作节点
	 * 
	 * @param map
	 */
	void delSecOperByCode(Map<String, String> map);

	/**
	 * 根据编号级联删除操作节点
	 * 
	 * @param map
	 */
	void delSecOperByCodeWithSub(String oper_code, String subsys_code);

	/**
	 * 更新操作节点信息
	 * 
	 * @param secOper
	 */
	void updateSecOper(SecOperation secOper);

	/**
	 * 根据部门编码查询部门信息
	 * 
	 * @param dept_id
	 * @return
	 */
	SecDept getSecDeptById(String dept_id);

	/**
	 * 根据地市编码查询地市信息
	 * 
	 * @param terr_code
	 * @return
	 */
	SecTerritory getSecTerrByCode(String terr_code);

	/**
	 * 根据部门等级编码查询部门等级信息
	 * 
	 * @param dept_level_code
	 * @return
	 */
	SecDeptLevel getSecDeptLevelByCode(String dept_level_code);

	/**
	 * 根据部门类型编码查询部门类型
	 * 
	 * @param dept_type_code
	 * @return
	 */
	SecDeptType getSecDeptTypeByCode(String dept_type_code);

	/**
	 * 根据部门编码查询与其关联的子系统列表
	 * 
	 * @param dept_id
	 * @return
	 */
	List<SecSubsys> getSubsysListByDeptId(String dept_id);

	/**
	 * 根据部门编码级联查询部门及其子部门
	 * 
	 * @param string
	 * @return
	 */
	List<SecDept> getSecDeptByIdWithSub(String dept_id);

	/**
	 * 查看该部门及其子部门已经关联的用户数量
	 * 
	 * @param string
	 * @return
	 */
	int getUserCountByDeptWithSub(String dept_cs);

	/**
	 * 获取全部地点列表
	 * 
	 * @return
	 */
	List<SecTerritory> getAllSecTerritory();

	/**
	 * 获取全部部门等级列表
	 * 
	 * @return
	 */
	List<SecDeptLevel> getAllSecDeptLevel();

	/**
	 * 获取全部部门类型列表
	 * 
	 * @return
	 */
	List<SecDeptType> getAllSecDeptType();

	/**
	 * 根据不机构模糊查询所有子机构
	 * 
	 * @param string
	 * @return
	 */
	List<SecDept> getSubDeptListByDeptCs(String dept_cs);

	/**
	 * 根据部门编码查询部门数量，判断指定编码的部门是否已经存在
	 * 
	 * @param dept_id
	 * @return
	 */
	int getSecDeptCountByDeptCs(String dept_cs);

	/**
	 * 添加机构，及机构子系统的关联
	 * 
	 * @param map
	 */
	void addSecDept(Map<String, String> map);

	/**
	 * 修改机构，及机构子系统的关联
	 * 
	 * @param map
	 */
	void updateSecDept(Map<String, String> map);

	/**
	 * 根据编码删除部门及子部门及各部门与岗位和子系统的关联
	 * 
	 * @param dept_id
	 */
	void delSecDeptByIdWithSub(String dept_id);

	/**
	 * 根据部门编码查看该部门已经配置的岗位列表
	 * 
	 * @param dept_id
	 * @return
	 */
	List<SecUserPost> getInDeptPostListByDept(String dept_id);

	/**
	 * 根据部门编码查看该部门还没有配置的岗位列表
	 * 
	 * @param dept_id
	 * @return
	 */
	List<SecUserPost> getOutDeptPostListByDept(String dept_id);

	/**
	 * 配置部门拥有的岗位
	 * 
	 * @param dept_id
	 * @param selectedPostIdList
	 */
	void addSecDeptPostRel(String dept_id, List<String> selectedPostIdList);

	/**
	 * 根据用户登录ID查询用户岗位信息
	 * 
	 * @param user_iidd
	 * @return
	 */
	SecUserPost getSecUserPostByUser(String user_iidd);

	/**
	 * 获取全部岗位的列表，用于展示和查询
	 * 
	 * @return
	 */
	List<SecUserPost> getAllUserPostList();

	/**
	 * 添加用户岗位
	 * 
	 * @param post
	 */
	void addSecUserPost(SecUserPost post);

	/**
	 * 根据ID删除岗位
	 * 
	 * @param post_id
	 */
	void delPostById(String post_id);

	/**
	 * 根据岗位ID查询岗位信息
	 * 
	 * @param post_id
	 * @return
	 */
	SecUserPost getSecUserPostById(String post_id);

	/**
	 * 修改岗位信息
	 * 
	 * @param post
	 */
	void updateSecUserPost(SecUserPost post);

	/**
	 * 根据岗位ID查询该岗位上的用户列表
	 * 
	 * @param post_id
	 * @return
	 */
	List<SecUser> getUserByPost(String post_id);

	/**
	 * 根据岗位ID查询配置了该岗位的部门列表
	 * 
	 * @param post_id
	 * @return
	 */
	List<String> getDeptByPost(String post_id);

	/**
	 * 更改岗位排序
	 * 
	 * @param post_id
	 * @param post_level
	 */
	void updatePostLevel(List<String> post_id, List<Integer> post_level);

	/**
	 * 更改岗位等级
	 * 
	 * @param post_id
	 * @param post_class
	 */
	void updatePostClass(List<String> post_id, List<Integer> post_class);

	/**
	 * 查询机构类型类表
	 * 
	 * @return
	 */
	List<SecDeptType> getAllDeptType();

	/**
	 * 添加机构类型
	 * 
	 * @param type
	 */
	void addDeptType(SecDeptType type);

	/**
	 * 根据机构类型编号查询机构类型信息
	 * 
	 * @param dept_type_code
	 * @return
	 */
	SecDeptType getDeptTypeByCode(String dept_type_code);

	/**
	 * 根据机构类型编号删除机构类型
	 * 
	 * @param dept_type_code
	 */
	void delDeptTypeByCode(String dept_type_code);

	/**
	 * 修改机构类型信息
	 * 
	 * @param secDeptType
	 */
	void updateDeptType(SecDeptType secDeptType);

	/**
	 * 查询机构等级列表
	 * 
	 * @return
	 */
	List<SecDeptLevel> getDeptLevel();

	/**
	 * 添加子系统
	 * 
	 * @param secSubsys
	 */
	void addSubsys(SecSubsys secSubsys);

	/**
	 * 修改子系统信息
	 * 
	 * @param secSubsys
	 */
	void updateSubsys(SecSubsys secSubsys);

	/**
	 * 根据子系统代号删除子系统
	 * 
	 * @param subsys_code
	 */
	void delSubsysByCode(String subsys_code);

	/**
	 * 获取用户密级列表
	 * 
	 * @return
	 */
	List<SecLevel> getSecLevel();

	/**
	 * 根据用户和子系统查询角色列表
	 * 
	 * @param map
	 * @return
	 */
	List<SecRole> getRoleListByUserAndSubsys(Map<String, String> map);

	/**
	 * 添加部门地区
	 * 
	 * @param secterr
	 */
	void addTerritory(SecTerritory secTerr);

	/**
	 * 通过地区编号删除地区
	 * 
	 * @param terr_code
	 */
	void delTerrByCode(String terr_code);

	/**
	 * 修改地区信息
	 * 
	 * @param secTerr
	 */
	void updateTerritory(SecTerritory secTerr);

	/**
	 * 根据编号查询密级信息
	 * 
	 * @param seclv_code
	 * @return
	 */
	SecLevel getSecLevelByCode(Integer seclv_code);

	/**
	 * 添加密级
	 * 
	 * @param seclv
	 */
	void addSeclv(SecLevel seclv);

	/**
	 * 根据编号删除密级
	 * 
	 * @param seclv_code
	 */
	void delSecLvByCode(Integer seclv_code);

	/**
	 * 修改密级信息
	 * 
	 * @param seclv
	 */
	void updateSeclv(SecLevel seclv);

	/**
	 * 通过机构类型查询机构列表
	 * 
	 * @param dept_type_code
	 * @return
	 */
	List<SecDept> getSecDeptByType(String dept_type_code);

	/**
	 * 通过机构等级查询机构列表
	 * 
	 * @param dept_type_code
	 * @return
	 */
	List<SecDept> getSecDeptByLevel(Integer dept_level_code);

	/**
	 * 通过地区查询机构列表
	 * 
	 * @param dept_type_code
	 * @return
	 */
	List<SecDept> getSecDeptByTerr(String terr_code);

	/**
	 * 查询实际用户信息
	 * 
	 * @return
	 */
	List<RealUser> getRealUser(Map<String, String> map);

	/**
	 * 通过实际员工ID删除员工信息
	 * 
	 * @param real_user_id
	 */
	void delRealUserById(String real_user_id);

	/**
	 * 通过实际员工ID查询员工信息
	 * 
	 * @param real_user_id
	 * @return
	 */
	RealUser getRealUserById(String real_user_id);

	/**
	 * 通过实际员工ID查询用户列表
	 * 
	 * @param real_user_id
	 * @return
	 */
	List<SecUser> getSimpleSecUserByReal(String real_user_id);

	/**
	 * 更改员工信息
	 * 
	 * @param realUser
	 */
	void updateRealUserByReal(RealUser realUser);

	/**
	 * 查询所有的一级目录节点（oper_code长度为4）
	 * 
	 * @return
	 */
	List<SecOperation> getAllMenuOper();

	/**
	 * 根据操作节点编号查询下一级节点列表
	 * 
	 * @param oper_code
	 * @return
	 */
	List<SecOperation> getSubOperByCode(String oper_code);

	/**
	 * 查询全部机构列表
	 * 
	 * @return
	 */
	List<SecDept> getAllSecDept();

	/**
	 * 用户登录静态密码认证
	 * 
	 * @param user_iidd
	 * @param user_ppww
	 * @return
	 */
	boolean authenticate(String user_iidd, String user_ppww);

	/**
	 * 根据条件查询角色列表
	 * 
	 * @param map
	 * @return
	 */
	List<SecRole> getSecRole(Map<String, String> map);

	/**
	 * 根据子系统代号查询三员角色列表
	 * 
	 * @param map
	 */
	List<SecRole> getAdminRoleBySubsys(Map<String, Object> map);

	/**
	 * 根据用户ID和子系统代号查询该用户已经分配的三员角色ID
	 * 
	 * @param map
	 * @return
	 */
	Integer getAdminRoleIdByUser(Map<String, Object> map);

	/**
	 * 根据子系统代号查询特殊角色列表
	 * 
	 * @param map
	 * @return
	 */
	List<SecRole> getSpecialRoleBySubsys(Map<String, Object> map);

	/**
	 * 根据用户ID和子系统代号查询该用户已经分配的特殊角色ID
	 * 
	 * @param map
	 * @return
	 */
	Integer getSpecialRoleIdByUser(Map<String, Object> map);

	/**
	 * 根据角色查询角色所属子系统的名称
	 * 
	 * @return
	 */
	String getSubsysNameByRoleId(String role_id);

	/**
	 * 通过用户ID和角色ID查询Domain
	 * 
	 * @param user_iidd
	 * @param role_id
	 * @return
	 */
	String getDomainByUserAndRole(String user_iidd, String role_id);

	/**
	 * 更新拥有特殊角色的用户的资源代号配置
	 * 
	 * @param domain
	 * @param terrCodes
	 */
	void updateScopeMemberCode(String domain, String codes);

	/**
	 * 查询特殊角色的用户已经配置的代号列表
	 * 
	 * @param map
	 * @return
	 */
	List<String> getScopeMemberCode(String domain);

	/**
	 * 更改用户的特殊角色
	 * 
	 * @param user_iidd
	 * @param subsys_code
	 * @param role_id
	 * @param role_spec_key
	 */
	void updateSpecialRole(String user_iidd, String subsys_code, String role_id, String role_spec_key);

	/**
	 * 查询用户拥有的特殊角色列表
	 * 
	 * @param user_iidd
	 * @return
	 */
	List<SecRole> getSpecialRoleByUserOnly(Map<String, Object> map);

	/**
	 * 查询用户拥有的特殊角色的操作列表
	 * 
	 * @param user_iidd
	 * @return
	 */
	List<String> getSpecialOperByUserOnly(String user_iidd);

	/**
	 * 查询用户拥有的所有操作的列表
	 * 
	 * @param user_iidd
	 * @return
	 */
	List<String> getAllOperByUserOnly(String user_iidd);

	/**
	 * 查询用户没有的所有操作的列表
	 * 
	 * @param user_iidd
	 * @return
	 */
	List<String> getNonOperByUserOnly(String user_iidd);

	/**
	 * 根据用户登录ID和用户登录名模糊查询用户列表
	 * 
	 * @param map
	 * @return
	 */
	List<SecUser> getSecUserList(Map<String, String> map);

	/**
	 * 根据岗位Id查询岗位信息
	 * 
	 * @param post_id
	 * @return
	 */
	Post getPostByPostId(String post_id);

	/**
	 * 获取系统配置参数
	 * 
	 * @param string
	 * @return
	 */
	ConfigItem getConfigItemValue(String item_key);

	/**
	 * 更改系统配置参数
	 * 
	 * @param configItem
	 */
	void updateConfigItem(ConfigItem configItem);

	/**
	 * 获得用户上次修改密码到现在的天数
	 * 
	 * @param user_iidd
	 * @return
	 */
	Integer getPwdUpdateDays(String user_iidd);

	/**
	 * 修改用户登录密码
	 * 
	 * @param map
	 */
	void changeUserPwd(Map<String, String> map);

	/**
	 * 输错密码次数加1
	 * 
	 * @param user_iidd
	 */
	void logfailTimesPlus(String user_iidd, HttpServletRequest request);

	/**
	 * 通过用户ID查询用户名
	 * 
	 * @param proposer
	 * @return
	 */
	String getUserNameByUserId(String proposer);

	/**
	 * 通过用户ID查询部门名称
	 * 
	 * @param proposer
	 * @return
	 */
	String getDeptNameByUserId(String proposer);

	/**
	 * 通过部门编号查询
	 * 
	 * @param dept_id
	 */
	Integer getMaxPostClassByDept(String dept_id);

	/**
	 * 查询指定部门的岗位列表
	 * 
	 * @param dept_id
	 * @return
	 */
	List<SecUserPost> getSecUserPostByDeptId(String dept_id);

	/**
	 * 查询指定部门的岗位等级列表
	 * 
	 * @param dept_id
	 * @return
	 */
	List<String> getPostClassByDeptId(String dept_id);

	/**
	 * 通过角色ID查询角色名称
	 * 
	 * @param string
	 * @return
	 */
	String getRoleNameByRoleId(String role_id);

	/**
	 * 查询用户允许使用的密级
	 * 
	 * @param user_iidd
	 * @return
	 */
	List<SecLevel> getAllowSecLevelByUserId(String user_iidd);

	/**
	 * 在指定的部门（含子部门）内，模糊查询用户列表（包含用户登录ID和用户姓名两个模糊）
	 * 
	 * @param dept_id
	 *            如果用户部门为null,则表示不限制部门
	 * @param fuzzy
	 * @return
	 */
	List<ApproverUser> getFuzzyUser(String dept_id, String fuzzy);

	/**
	 * 根据用户ID模糊查询用户
	 * 
	 * @param dept_id
	 * @param fuzzy
	 * @return
	 */
	List<ApproverUser> getFuzzyUserByUserId(String dept_id, String user_iidd);

	/**
	 * 根据用户姓名模糊查询用户
	 * 
	 * @param dept_id
	 * @param fuzzy
	 * @return
	 */
	List<ApproverUser> getFuzzyUserByUserName(String dept_id, String user_name);

	/**
	 * 查询所有子系统列表，包含已封存的
	 * 
	 * @return
	 */
	List<SecSubsys> getAllSubsys();

	/**
	 * 查询所有密级列表，包含已封存的
	 * 
	 * @return
	 */
	List<SecLevel> getAllSecLevel();

	/**
	 * 查询所有角色列表，包含已封存的
	 * 
	 * @return
	 */
	List<SecRole> getAllSecRole(Map<String, String> map);

	/**
	 * 模糊查询用户
	 * 
	 * @param dept_id
	 * @param fuzzy
	 * @return
	 */
	List<User> getFuzzyUserDept(String dept_id, String fuzzy);

	/**
	 * 通过部门级联编号查询部门信息
	 * 
	 * @param dept_cs
	 * @throws Exception
	 */
	SecDept getSecDeptByDeptCs(String dept_cs) throws Exception;

	/**
	 * 查看所有涉密人员类别列表
	 * 
	 * @return
	 */
	List<UserSecurity> getAllSecurityList();

	/**
	 * 添加涉密人员类别
	 * 
	 * @param security
	 */
	void addSecurity(UserSecurity security);

	/**
	 * 通过编号查找涉密人员类别信息
	 * 
	 * @param security_code
	 * @return
	 */
	UserSecurity getSecurityByCode(String security_code);

	/**
	 * 通过编号删除涉密人员类别
	 * 
	 * @param security_code
	 */
	void delSecurityByCode(String security_code);

	/**
	 * 更新涉密人员类别信息
	 * 
	 * 2014-4-8 下午3:33:01
	 * 
	 * @author renmingfei
	 * @param security
	 */
	void updateSecurity(UserSecurity security);

	/**
	 * 查看可用的涉密人员类别列表
	 * 
	 * @return
	 */
	List<UserSecurity> getSecurityList();

	/**
	 * 查询用户可以打印的密级列表
	 * 
	 * 2014-4-9 下午6:51:06
	 * 
	 * @author renmingfei
	 * @param user_iidd
	 * @return
	 */
	List<SecLevel> getPrintSecLevelByUser(String user_iidd);

	/**
	 * 查询用户可以复印的密级列表
	 * 
	 * 2014-4-9 下午6:51:06
	 * 
	 * @author renmingfei
	 * @param user_iidd
	 * @return
	 */
	List<SecLevel> getCopySecLevelByUser(String user_iidd);

	/**
	 * 查询用户可以导入的密级列表
	 * 
	 * 2014-4-9 下午6:51:06
	 * 
	 * @author renmingfei
	 * @param user_iidd
	 * @return
	 */
	List<SecLevel> getImportSecLevelByUser(String user_iidd);

	/**
	 * 查询用户可以刻录的密级列表
	 * 
	 * 2014-4-9 下午6:51:06
	 * 
	 * @author renmingfei
	 * @param user_iidd
	 * @return
	 */
	List<SecLevel> getBurnSecLevelByUser(String user_iidd);

	/**
	 * 查询用户可以复刻的密级列表
	 * 
	 * 2014-4-9 下午6:51:06
	 * 
	 * @author renmingfei
	 * @param user_iidd
	 * @return
	 */
	List<SecLevel> getCopyBurnSecLevelByUser(String user_iidd);

	/**
	 * 查询用户可以借入的密级列表
	 * 
	 * 2014-4-9 下午6:51:06
	 * 
	 * @author renmingfei
	 * @param user_iidd
	 * @return
	 */
	List<SecLevel> getDeviceSecLevelByUser(String user_iidd);

	/**
	 * 查询用户可以默认允许操作的密级列表
	 * 
	 * 2014-4-9 下午6:51:06
	 * 
	 * @author renmingfei
	 * @param user_iidd
	 * @return
	 */
	List<SecLevel> getDefaultSecLevelByUser(String user_iidd);

	/**
	 * 根据用户登录ID查询用户的涉密人员类别
	 * 
	 * 2014-4-9 下午8:29:11
	 * 
	 * @author renmingfei
	 * @param user_iidd
	 * @return
	 */
	UserSecurity getSecurityByUser(String user_iidd);

	/**
	 * 通过用户登录ID查询部门信息 2014-4-11 下午6:38:43
	 * 
	 * @author renmingfei
	 * @param user_iidd
	 * @return
	 */
	SecDept getSecDeptByUserId(String user_iidd);

	/**
	 * 更新用户和三员角色的关联
	 * 
	 * 2014-4-11 下午7:46:30
	 * 
	 * @author renmingfei
	 * @param user_iidd
	 * @param subsys_code
	 * @param role_id
	 * @param roleTypeReadonly
	 */
	void updateAdminRole(String user_iidd, String subsys_code, String role_id);

	/**
	 * 查看部门管理员配置信息 2014-4-12 上午11:04:02
	 * 
	 * @author renmingfei
	 * @param user_iidd
	 * @return
	 */
	DeptAdminConfig getDeptAdminConfig(String user_iidd);

	/**
	 * 配置部门管理员 2014-4-12 下午1:54:14
	 * 
	 * @author renmingfei
	 * @param adminConfig
	 */
	void configDeptAdmin(DeptAdminConfig adminConfig);

	/**
	 * 通过部门ID列表查询部门名称，用逗号分隔
	 * 
	 * 2014-4-16 下午11:45:15
	 * 
	 * @author renmingfei
	 * @param deptIdList
	 * @return
	 */
	String getDeptNamesByIdList(List<String> deptIdList);

	/**
	 * 通过涉密人员类别名称反查编号 2014-5-5 上午11:00:21
	 * 
	 * @author renmingfei
	 * @param security_name
	 * @return
	 */
	String getSecurityCodeByName(String security_name);

	/**
	 * 通过名称名称反查部门ID 2014-5-5 上午11:04:53
	 * 
	 * @author renmingfei
	 * @param dept_name
	 * @return
	 */
	String getDeptIdByName(String dept_name);

	/**
	 * 根据用户登录ID判断用户是否已经存在 2014-5-5 上午11:43:56
	 * 
	 * @author renmingfei
	 * @param user_iidd
	 */
	boolean isUserExist(String user_iidd);

	/**
	 * 判断该编号或者名称的密级是否已经存在 2014-5-7 上午9:07:00
	 * 
	 * @author renmingfei
	 * @param seclv_code
	 * @param seclv_name
	 * @return
	 */
	boolean isSeclvExist(Integer seclv_code, String seclv_name);

	/**
	 * 根据模块编号查询该模块是否开启 2014-5-7 下午5:32:01
	 * 
	 * @author renmingfei
	 * @param module_code
	 * @return
	 */
	boolean isModuleEnable(String module_code);

	/**
	 * 查询普通用户角色ROLE_ID 2014-5-14 上午9:06:17
	 * 
	 * @author renmingfei
	 * @return
	 */
	String getWorkerRoleId();

	/**
	 * 查询普通用户角色ROLE_ID 2014-5-14 上午9:06:17
	 * 
	 * @author yy
	 * @param accept_user_iidd
	 * @param url
	 */
	void updateViewStatus(String accept_user_iidd, String url);

	/**
	 * 检测用户卡号是否存在
	 * 
	 * @author lixiang
	 */
	boolean checkPassNumIsUsed(String pass_num);

	/**
	 * 检测用户卡号是否被其他用户使用
	 * 
	 * @param pass_num
	 * @return
	 */
	String getSecUserIdByPassNum(String pass_num);

	/**
	 * 恢复已删除的用户
	 * 
	 * @param user_iidd
	 */
	void recoverSecUserById(String user_iidd);

	/**
	 * 启用已停用的密级
	 * 
	 * @param seclv_code
	 */
	void recoverSecLvByCode(Integer seclv_code);

	/**
	 * 检测密级名是否存在
	 * 
	 * @param seclv_name
	 * @return
	 */
	boolean isSeclvExistByName(String seclv_name);

	/**
	 * 检测密级排序号是否存在
	 * 
	 * @param seclv_rank
	 * @return
	 */
	boolean isSeclvExistByRank(Integer seclv_rank);

	/**
	 * 检测密级号是否存在
	 * 
	 * @param seclv_code
	 * @return
	 */
	boolean isSeclvExistByCode(Integer seclv_code);

	/**
	 * 由密级名得到密级号
	 * 
	 * @param seclv_name
	 * @return
	 */
	Integer getSeclvCodeByName(String seclv_name);

	/**
	 * 由密集排序得到密级号
	 * 
	 * @param seclv_rank
	 * @return
	 */
	Integer getSeclvCodeByRank(Integer seclv_rank);

	/**
	 * 根据查询条件返回密级列表
	 * 
	 * @param map
	 * @return
	 */
	List<SecLevel> getSeclvList(Map<String, Object> map);

	int getAllCommonUserCount();

	int getLastNumberCount() throws Exception;

	/**
	 * 判断用户是否处于锁定状态（判断条件为status为1，且锁定时间小于十分钟）
	 * 
	 * @param user_iidd
	 * @return
	 */
	boolean isUserLocked(String user_iidd);

	/**
	 * 获取当前在用密级个数
	 * 
	 * @return
	 */
	int getSeclvCount();

	/**
	 * 检测密级别名是否存在
	 * 
	 * @param seclv_name
	 * @return
	 */
	boolean isSeclvExistByOthername(String othername);

	/**
	 * 由密级名得到密级号
	 * 
	 * @param seclv_name
	 * @return
	 */
	Integer getSeclvCodeByOthername(String othername);

	/**
	 * 通过item_key查询默认密码值
	 * 
	 * @param item_key
	 * @return
	 */
	String getSysConfigItemValue(String item_key);

	/**
	 * 查找子系统的所有操作
	 * 
	 * @param subsys_code
	 * @return
	 */
	List<SecOperation> getSecOperBySubsysAll(String subsys_code);

	/**
	 * 查询当前管理员用户管理权限划分版本
	 * 
	 * @return
	 */
	Integer getSysConfigAdminVersion();

	/**
	 * 删除科工集团管理员用户管理权限
	 * 
	 * @return
	 */
	void delSysConfigAdminVersion();

	/**
	 * 增加科工集团管理员用户管理权限
	 * 
	 * @return
	 */
	void insertSysConfigAdminVersion();

	/**
	 * 判断是否有ip限制字段
	 * 
	 * @param userType
	 * @return
	 */
	int getIpConfigIsExist(String userType);

	/**
	 * 判断是否有ip限制开启
	 * 
	 * @param userType
	 * @return
	 */
	int getIpConfigStartuse(String userType);

	/**
	 * 取限制IP
	 * 
	 * @param userType
	 * @return
	 */
	String getLimitIpConfig(String userType);

	List<SecUser> getAdminUser(Map<String, String> map, RowBounds rbs);

	// add by wx 20150501 获取默认密码
	/**
	 * 获取默认密码
	 * 
	 * @param item_key
	 * @return
	 */
	String getDefaultPwd(String item_key);

	// above

	// add by wx 20150501 获取当前用户密码MD5加密值
	/**
	 * 获取默认密码
	 * 
	 * @param user_iidd
	 * @return
	 */
	String getCurUserPwd(String user_iidd);

	// above
	/**
	 * 分组得到配置过部门的用户id集合
	 * 
	 * @return
	 */
	List<String> getUser_iiddsGroup();

	/**
	 * 通过用户id得到配置过部门的角色集合
	 * 
	 * @param user_iidd
	 * @return
	 */
	List<String> getRole_idsGroup(String user_iidd);

	/**
	 * 通过用户id和角色id得到配置的部门集合
	 * 
	 * @param map
	 * @return
	 */
	List<String> getDept_idsByTwo(Map<String, String> map);

	/**
	 * 所有可用的部门
	 * 
	 * @return
	 */
	List<String> getDept_idsIsY();

	public void configDeptAdmin1(DeptAdminConfig adminConfig, String role_id);

	/**
	 * 角色表添加代理信息
	 * 
	 * @param is_proxy
	 */
	void insertProxyInfo(Map<String, String> map);

	/**
	 * 角色表删除代理审批权限
	 * 
	 * @param is_proxy
	 */
	void deleteProxyInfo(Map<String, String> map);

	/**
	 * 根据user_id查询被代理人
	 * 
	 * @param user_iidd
	 */
	List<SecRoleUser> getAgentByUser_iidd(Map<String, String> map);
}
