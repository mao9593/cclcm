package hdsec.web.project.securityuser.service;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.common.bm.BMCycleItem;
import hdsec.web.project.common.bm.model.BMSysConfigItem;
import hdsec.web.project.common.bm.model.BmRealUser;
import hdsec.web.project.common.bm.model.ClientTask;
import hdsec.web.project.securityuser.model.AbroadInfo;
import hdsec.web.project.securityuser.model.BmUserInfoEvent;
import hdsec.web.project.securityuser.model.CompanyContact;
import hdsec.web.project.securityuser.model.ExperienceInfo;
import hdsec.web.project.securityuser.model.FamilyInfo;
import hdsec.web.project.securityuser.model.PostDeptConfig;
import hdsec.web.project.securityuser.model.ResignEvent;
import hdsec.web.project.securityuser.model.SysUserPost;
import hdsec.web.project.securityuser.model.UserAbroadEvent;
import hdsec.web.project.securityuser.model.UserEntrustEvent;
import hdsec.web.project.securityuser.model.UserSeclvChangeEvent;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecUser;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface SecurityUserService {

	/**
	 * 添加用户密级变更作业
	 * 
	 * @param event
	 * @throws Exception
	 */
	void addUserSeclvChangeEvent(UserSeclvChangeEvent event, String next_approver) throws Exception;

	/**
	 * 查询密级变更作业信息
	 * 
	 * @param map
	 * @return
	 */
	List<UserSeclvChangeEvent> getUSeclvChangeEventList(Map<String, Object> map);

	/**
	 * 通过流水号查找用户密级变更详情
	 * 
	 * @param event_code
	 * @return
	 */
	UserSeclvChangeEvent getUSeclvChangeEventByEventCode(String event_code);

	/**
	 * 通过作业流水号查询任务流水号
	 * 
	 * @param event_code
	 * @return
	 */
	String getJobCodeByEventCode(String event_code);

	/**
	 * 通过作业流水号删除用户密级变更作业
	 * 
	 * @param event_code
	 */
	void delUSecChangeEventByEventCode(String event_code);

	/**
	 * 通过作业流水号删除新增涉密人员作业
	 * 
	 * @param event_code
	 */
	void delUSecAddEventByEventCode(String event_code);

	/**
	 * 根据任务作业号查询用户密级变更作业信息
	 * 
	 * @param job_code
	 * @return
	 */
	List<UserSeclvChangeEvent> getUSeclvChangeEventListByJobCode(String job_code);

	/**
	 * 通过作业流水号变更密级操作
	 * 
	 * @param event_code
	 */
	void UpdateUSeclvByEventCode(Map<String, Object> map, String change_user_iidd, String seclv_code_after,
			String dept_id_after, String post_id_after);

	/**
	 * 添加用户离职作业yangjl
	 * 
	 * @author yangjl 2015-6-15
	 */
	void addResignEvent(ResignEvent event, String next_approver) throws Exception;

	/**
	 * 查询离职作业信息 yangjl
	 * 
	 * @param map
	 * @return
	 */
	List<ResignEvent> getUResignEventList(Map<String, Object> map);

	/**
	 * 通过流水号查找用户离职详情 yangjl
	 * 
	 * @param event_code
	 * @return
	 */
	ResignEvent getUResignEventByEventCode(String event_code);

	/**
	 * 通过离职作业流水号查询离职任务流水号 yangjl
	 * 
	 * @param event_code
	 * @return
	 */
	String getResignJobCodeByEventCode(String event_code);

	/**
	 * 通过作业流水号删除用户离职作业 yangjl
	 * 
	 * @param event_code
	 */
	void delUResignEventByEventCode(String event_code);

	/**
	 * 根据任务作业号查询用户离职作业信息 yangjl
	 * 
	 * @param job_code
	 * @return
	 */
	List<ResignEvent> getUResignEventListByJobCode(String job_code);

	/**
	 * 更新用户离职作业脱密期字段lishu
	 * 
	 * @param job_code
	 * @return
	 */
	void updateResignEventByJobCode(Map<String, Object> map);

	/**
	 * 因私出国模块
	 * 
	 * @author gj
	 * */

	/** 添加用户因私出国作业 */
	void addUserAbroadEvent(UserAbroadEvent event, String next_approver) throws Exception;

	/** 查询用户因私出国作业信息 */
	List<UserAbroadEvent> getUAbroadEventList(Map<String, Object> map);

	/** 通过流水号查找用户因私出国详情 */
	UserAbroadEvent getUAbroadEventByEventCode(String event_code);

	/** 通过作业流水号删除用户因私出国作业 */
	void delUAbroadEventByEventCode(String event_code);

	/** 通过作业流水号查询任务流水号 */
	String getUAbroadJobCodeByEventCode(String event_code);

	/** 根据任务作业号查询用户因私出国作业信息 */
	List<UserAbroadEvent> getUAbroadEventListByJobCode(String job_code);

	/** 更新用户审批过程中上传附件 **/
	void addApproveAboradFile(Map<String, Object> map);

	/**
	 * 用户委托保密管理
	 * 
	 * @author gj
	 * */

	/** 添加用户委托保密管理作业 */
	void addUserEntrustEvent(UserEntrustEvent event, String next_approver) throws Exception;

	/** 查询用户委托保密管理作业信息 */
	List<UserEntrustEvent> getUserEntrustEventList(Map<String, Object> map);

	/** 通过流水号查找用户委托保密管理详情 */
	UserEntrustEvent getUserEntrustEventByEventCode(String event_code);

	/** 通过委托保密作业流水号查询任务流水号 */
	String getUserEntrustJobCodeByEventCode(String event_code);

	/** 通过作业流水号删除用户委托保密作业 */
	void delUserEntrustEventByEventCode(String event_code);

	/** 根据任务作业号查询用户委托保密作业信息 */
	List<UserEntrustEvent> getUserEntrustEventListByJobCode(String job_code);

	/**
	 * 扩展-审批流程申请任务
	 * 
	 * @param event_name
	 * @param job_code
	 * @param user
	 * @param approver
	 * @param approved
	 * @param opinion
	 * @throws Exception
	 */
	void extendApproveJob(String job_code, ApproverUser user, ApproverUser approver, String approved, String opinion,
			String entitytype) throws Exception;

	/** 用户个人信息完善模块 **/

	/** 用户信息加入临时信息表 **/
	void addUserInfoToTemp(BmRealUser tempinfo);

	/** 添加用户信息完善作业 */
	void addUserInfoEvent(BmUserInfoEvent event, String next_approver) throws Exception;

	/** 查询用户信息完善作业信息 **/
	List<BmUserInfoEvent> getUserInfoEventList(Map<String, Object> map);

	/** 通过流水号查找用户信息完善详情 **/
	BmUserInfoEvent getUserInfoEventByEventCode(String event_code);

	/** 通过流水号查找用户个人信息更改详情 **/
	BmRealUser getUserInfoByEventCode(Map<String, Object> map);

	/** 通过用户信息作业流水号查询任务流水号 **/
	String getUserInfoJobCodeByEventCode(String event_code);

	/** 通过作业流水号删除用户信息完善作业* */
	void delUserInfoEventByEventCode(String event_code);

	/** 根据任务作业号查询用户信息完善作业信息* */
	List<BmUserInfoEvent> getUserInfoEventListByJobCode(String job_code);

	/** 根据用户名查找扩展新增用户部分信息 **/
	BmRealUser getBmRealInfoByUserId(String user_id);

	/** 根据用户和infotype类型删除用户信息表信息（①更新info_type=2已通过信息②删除已保存info_type=3信息） **/
	void delUserInfoByUserIdAndInfoType(Map<String, Object> map);

	/**
	 * 更改配置参数
	 * 
	 * @param configItem
	 */
	void updateBMSysConfigItem(BMSysConfigItem sysConfigItem);

	/**
	 * 获取配置参数
	 * 
	 * @param string
	 * @return
	 */
	BMSysConfigItem getBMSysConfigItemValue(String item_key);

	/**
	 * 根据传入的载体类型，生成载体条码号 打印文件:PRINTPAPER 涉密图纸:PRINTGRAPH 复印文件:COPY 外来文件:ENTERPAPER 保密手册:SECBOOK 刻录光盘:BURN 外来光盘:ENTERCD
	 * 涉密移动载体:DEVICE 涉密存储介质:STORAGE 涉密活动中使用的涉密载体:ACTIVITY
	 * 
	 * 资产：PROPERTY，计算机：COMPUTER，涉密场所：SECPLACE
	 * 
	 * @param entityType
	 * @return
	 * @throws Exception
	 */
	String createBMEntityBarcode(String entityType) throws Exception;

	/**
	 * 获取岗位信息
	 * 
	 * @return
	 */
	List<SysUserPost> getPostList();

	/**
	 * 获取部门信息
	 * 
	 * @return
	 */
	List<SysUserPost> getDeptList();

	/**
	 * 根据查询条件显示岗位列表
	 * 
	 * @param map
	 * @return
	 */
	List<SysUserPost> getSysUserPostList(Map<String, Object> map);

	List<SysUserPost> getAllSysUserPostList();

	/**
	 * 根据岗位名得到岗位
	 * 
	 * @param post_name
	 * @return
	 */
	List<SysUserPost> getUserPostByUserPostName(String post_name);

	/**
	 * 岗位管理
	 * 
	 * @return
	 */
	// public final static String SEP_STR = "_";

	void addSysUserPost(SysUserPost userpost, String dept_ids);

	void delUserPostByCode(String post_id);

	SysUserPost getUserPostByCode(String post_id);

	void updateUserPost(SysUserPost userpost, String dept_ids);

	List<SysUserPost> getSysUserPostList();

	/**
	 * 启用已停用的岗位
	 * 
	 * @param post_id
	 */
	void recoverUserPostByCode(String post_id);

	/**
	 * 判断用户特征值或者名称是否已经存在
	 * 
	 * @author yangjl 2015-7-1
	 * @param post_id
	 * @param post_name
	 * @return
	 */
	boolean isUserPostExist(String post_id, String post_name);

	/**
	 * 获取消息提醒
	 * 
	 * @param map
	 */
	List<ClientTask> getTask(Map<String, Object> map);

	List<PostDeptConfig> getPostDeptList(String post_id, String dept_id);

	/**
	 * 添加全生命周期记录
	 * 
	 * @param cycleitem
	 */
	void addCycleItem(BMCycleItem cycleitem);

	/**
	 * 通过条码号查询全生命周期记录
	 * 
	 * @param barcode
	 * @return
	 */
	List<BMCycleItem> getCycleItemListByBarcode(String barcode);

	List<CompanyContact> getCompanycontact(Map<String, Object> map);

	void addCompanyContact(CompanyContact contact);

	void updateCompanyContact(CompanyContact contact);

	void delCompanyContact(Map<String, Object> map);

	/** 查询BM中用户完善资料列表 **/
	List<BmRealUser> getAllUserInfoList(Map<String, Object> map);

	/** 根据用户ID查询BM用户信息表中是否存在 **/
	int getBmUserById(String user_id);

	List<BmRealUser> getUserInfoByUserIdAndInfoType(Map<String, Object> map);

	String getUserHistoryAbroadInfo(Map<String, Object> map);

	/**
	 * 查询任务列表
	 * 
	 * @param map
	 * @return
	 */
	List<ProcessJob> getJobList(Map<String, Object> map);

	/** 查询sec_user中所有相关信息user_personal<查询界面按页检索> **/
	List<SecUser> getBmSecUserList(Map<String, Object> map, RowBounds rbs) throws Exception;

	/** 查询sec_user中所有相关信息user_personal<导出台账使用> **/
	List<SecUser> getAllBmSecUserList(Map<String, Object> map);

	/**
	 * 查询人员信息台账总数
	 * 
	 * @param map
	 * @return
	 * @throws Exception
	 */
	int getBmSecUserSize(Map<String, Object> map) throws Exception;

	/** 查询人员的涉密（机密和秘密）文件台账总数 **/
	String getUserEntityPaperTotal(Map<String, Object> map);

	/** 查询人员的涉密（机密和秘密）光盘台账总数 **/
	String getUserEntityCdTotal(Map<String, Object> map);

	/** 更新人员因私出境中申请人回国确认信息更新数据库 **/
	void updateUserAbroadConfirmInfo(Map<String, Object> map);

	void resubmitApplyJob(String job_code, ApproverUser user, ApproverUser approver) throws Exception;

	/** 新增人员信息中个人简历表中 **/
	void addUserExperiences(ExperienceInfo experiences);

	/** 新增人员信息中出国经历表中 **/
	void addUserAbroad(AbroadInfo abroad);

	/** 新增人员信息中家庭成员表中 **/
	void addUserFamily(FamilyInfo family);

	List<ExperienceInfo> getUserExperience(Map<String, Object> map);

	List<AbroadInfo> getUserAbroad(Map<String, Object> map);

	List<FamilyInfo> getUserFamily(Map<String, Object> map);

	/**
	 * 查询用户自己的审批历史记录
	 * 
	 * @param user_iidd
	 * @param jobType
	 * @param user_name
	 * @param seclv_code
	 * @return
	 */
	List<ProcessJob> getApprovedJobByUserId(String user_iidd, JobTypeEnum jobType, Map<String, Object> map);
}