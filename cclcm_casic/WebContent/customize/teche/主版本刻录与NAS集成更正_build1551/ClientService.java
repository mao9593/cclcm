package hdsec.web.project.client.service;

import hdsec.web.project.client.model.PendingWorkItem;
import hdsec.web.project.client.model.SysCVS;
import hdsec.web.project.client.model.SysModule;
import hdsec.web.project.user.model.SecUser;

import java.util.List;
import java.util.Map;

public interface ClientService {
	/**
	 * 功能模块管理
	 */
	List<SysModule> getSysModuleList();

	/**
	 * 客户端版本管理
	 */
	List<SysCVS> getAllSysCVSList();

	List<SysCVS> getSysCVSList();

	SysCVS getCVSByCode(String cvs_code);

	void updateCVS(String cvs_code, String dept_id, String set_version);

	List<SysCVS> getCVSListByCondition(Map<String, Object> map);

	/**
	 * 查询审批完成的申请列表 2014-4-14 下午9:55:56
	 * 
	 * @author renmingfei
	 * @param user_iidd
	 * @return
	 */
	List<PendingWorkItem> getApprovedWorkList(String user_iidd);

	/**
	 * 查询领导待审批列表 2014-4-15 下午2:50:25
	 * 
	 * @author renmingfei
	 * @param user_iidd
	 * @return
	 */
	List<PendingWorkItem> getWaitingApproveList(String user_iidd);

	/**
	 * 查询普通用户待查看任务列表 2014-5-8 下午4:10:32
	 * 
	 * @author renmingfei
	 * @param user_iidd
	 * @return
	 */
	List<PendingWorkItem> getWorkerWorkList(String user_iidd, boolean nasFlag);

	/**
	 * 查询领导待查看任务列表 2014-5-8 下午7:00:27
	 * 
	 * @author renmingfei
	 * @param user_iidd
	 * @return
	 */
	List<PendingWorkItem> getLeaderWorkList(String user_iidd, boolean nasFlag);

	/**
	 * 通过审批单任务流水号，删除客户端提示信息（用于用户取消了原申请任务） 2014-5-17 下午5:00:13
	 * 
	 * @author renmingfei
	 * @param job_code
	 */
	void delClientMsgByJobCode(String job_code);

	/**
	 * 删除客户端提醒文件留用过期消息
	 * 
	 * @param map
	 */
	void delRetrieveMsgByUser(Map<String, Object> map);

	/**
	 * 根据身份证号查询用户信息
	 * 
	 * @param identity
	 * @return
	 */
	SecUser getUserByIdentity(String identity);
}
