package hdsec.web.project.securityuser.mapper;

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
import hdsec.web.project.user.model.SecUser;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface SecurityUserMapper {

	void addUserSeclvChangeEvent(UserSeclvChangeEvent event);

	int getSeclvcodeByUid(String userId);

	List<UserSeclvChangeEvent> getUSeclvChangeEventList(Map<String, Object> map);

	void updateUSeclvChangeEventJobCode(Map<String, String> map);

	UserSeclvChangeEvent getUSeclvChangeEventByEventCode(String event_code);

	String getJobCodeByEventCode(String event_code);

	void delUSecChangeEventByEventCode(String event_code);

	List<UserSeclvChangeEvent> getUSeclvChangeEventListByJobCode(String job_code);

	void UpdateUSeclvByEventCode(Map<String, Object> map);

	void UpdateSecUSeclvByUserId(Map<String, Object> map);

	void addResignEvent(ResignEvent event);

	int getResigncodeByUid(String userId);

	List<ResignEvent> getUResignEventList(Map<String, Object> map);

	void updateUResignEventJobCode(Map<String, String> map);

	ResignEvent getUResignEventByEventCode(String event_code);

	String getResignJobCodeByEventCode(String event_code);

	void delUResignEventByEventCode(String event_code);

	List<ResignEvent> getUResignEventListByJobCode(String job_code);

	void UpdateUResignByJobCode(String job_code);

	void DeadUResignByJobCode(String job_code);

	void UpdateSecUResignByUserId(Map<String, Object> map);

	void updateResignEventByJobCode(Map<String, Object> map);

	/** 涉密人员因私出国模块 */
	void addUserAbroadEvent(UserAbroadEvent event);

	void updateUAbroadEventJobCode(Map<String, String> map);

	List<UserAbroadEvent> getUAbroadEventList(Map<String, Object> map);

	UserAbroadEvent getUAbroadEventByEventCode(String event_code);

	String getUAbroadJobCodeByEventCode(String event_code);

	void delUAbroadEventByEventCode(String event_code);

	List<UserAbroadEvent> getUAbroadEventListByJobCode(String job_code);

	void updateUAbroadStatusByJobCode(String job_code);

	void addUserEntrustEvent(UserEntrustEvent event);

	void updateUserEntrustEventJobCode(Map<String, String> map);

	List<UserEntrustEvent> getUserEntrustEventList(Map<String, Object> map);

	UserEntrustEvent getUserEntrustEventByEventCode(String event_code);

	String getUserEntrustJobCodeByEventCode(String event_code);

	void delUserEntrustEventByEventCode(String event_code);

	List<UserEntrustEvent> getUserEntrustEventListByJobCode(String job_code);

	void updateUEntrustStatusByJobCode(String job_code);

	void addApproveAboradFile(Map<String, Object> map);

	/** 用户个人信息完善模块 **/
	void addUserInfoToTemp(BmRealUser tempinfo);

	void addUserInfoEvent(BmUserInfoEvent event);

	void updateUserInfoEventJobCode(Map<String, String> map);

	List<BmUserInfoEvent> getUserInfoEventList(Map<String, Object> map);

	BmUserInfoEvent getUserInfoEventByEventCode(String event_code);

	BmRealUser getUserInfoByEventCode(Map<String, Object> map);

	String getUserInfoJobCodeByEventCode(String event_code);

	void delUserInfoEventByEventCode(String event_code);

	void delUserInfoByEventCode(String event_code);

	List<BmUserInfoEvent> getUserInfoEventListByJobCode(String job_code);

	void updateUserRealInfoByUserInfo(String event_code);

	void updateUserIdCardByUserInfo(String event_code);

	void updateUserInfoConfirmTime(Map<String, Object> map);

	String getUserInfoEventCodeByJobCode(String job_code);

	BmRealUser getBmRealInfoByUserId(String user_id);

	void updateBMSysConfigItem(BMSysConfigItem sysConfigItem);

	BMSysConfigItem getBMSysConfigItemValue(String item_key);

	List<SysUserPost> getPostList();

	List<SysUserPost> getDeptList();

	/**
	 * 岗位管理 yangjl
	 * 
	 * @param map
	 * @return
	 */
	List<SysUserPost> getSysUserPostList(Map<String, Object> map);

	void addSysUserPost(SysUserPost userpost);

	void setUserPostSealedByCode(String post_id);

	SysUserPost getUserPostByCode(String post_id);

	void updateUserPost(SysUserPost userpost);

	List<SysUserPost> getUserPostListByUserPostName(String post_name);

	void recoverUserPostByCode(String post_id);

	int getUserPostCount(Map<String, String> map);

	List<ClientTask> getTask(Map<String, Object> map);

	void addPostDept(Map<String, String> map);

	List<PostDeptConfig> getPostDeptList(Map<String, Object> map);

	void delPostDeptByPostid(SysUserPost userpost);

	void addCycleItem(BMCycleItem cycleitem);

	List<BMCycleItem> getCycleItemListByBarcode(String barcode);

	List<CompanyContact> getCompanyContact(Map<String, Object> map);

	void addCompanyContact(CompanyContact contact);

	void updateCompanyContact(CompanyContact contact);

	void delCompanyContact(Map<String, Object> map);

	void delUserInfoByUserIdAndInfoType(Map<String, Object> map);

	List<BmRealUser> getAllUserInfoList(Map<String, Object> map);

	List<BmRealUser> getUserInfoByUserIdAndInfoType(Map<String, Object> map);

	int getBmUserById(String user_id);

	String getUserHistoryAbroadInfo(Map<String, Object> map);

	List<ProcessJob> getJobList(Map<String, Object> map);

	List<SecUser> getBmSecUserList(Map<String, Object> map, RowBounds rbs) throws Exception;

	List<SecUser> getAllBmSecUserList(Map<String, Object> map);

	int getBmSecUserSize(Map<String, Object> map) throws Exception;

	String getUserEntityPaperTotal(Map<String, Object> map);

	String getUserEntityCdTotal(Map<String, Object> map);

	void updateUserAbroadConfirmInfo(Map<String, Object> map);

	void updateUserStatueByResignUserId(Map<String, String> map);

	void addUserExperiences(ExperienceInfo experiences);

	void addUserAbroad(AbroadInfo abroad);

	void addUserFamily(FamilyInfo family);

	List<ExperienceInfo> getUserExperience(Map<String, Object> map);

	List<AbroadInfo> getUserAbroad(Map<String, Object> map);

	List<FamilyInfo> getUserFamily(Map<String, Object> map);

	void delUserExperience(String event_code);

	void delUserAbroad(String event_code);

	void delUserFamily(String event_code);

	List<ProcessJob> getApprovedJobByInstanceIds(Map<String, Object> map);
}