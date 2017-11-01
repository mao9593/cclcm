package hdsec.web.project.client.mapper;

import hdsec.web.project.basic.model.ClientMsg;
import hdsec.web.project.basic.model.SysConsole;
import hdsec.web.project.client.model.ClientTask;
import hdsec.web.project.client.model.SysCVS;
import hdsec.web.project.client.model.SysModule;
import hdsec.web.project.user.model.SecUser;

import java.util.List;
import java.util.Map;

public interface ClientMapper {
	/**
	 * 功能模块管理
	 * 
	 * @param map
	 * @return
	 */
	List<SysModule> getSysModuleList();

	/**
	 * 客户端版本管理
	 * 
	 * @param map
	 * @return
	 */
	List<SysCVS> getSysCVSList(Map<String, Object> map);

	SysCVS getCVSByCode(String cvs_code);

	void updateCVS(Map<String, Object> map);

	List<SysCVS> getCVSListByCondition(Map<String, Object> map);

	int getPrintEventNum(Map<String, Object> map);

	int getBurnEventNum(Map<String, Object> map);

	int getCopyEventNum(Map<String, Object> map);

	int getEnterEventNum(Map<String, Object> map);

	int getTransferEventNum(Map<String, Object> map);

	int getDeviceEventNum(Map<String, Object> map);

	int getHandlePaperNum(Map<String, Object> map);

	int getHandleCDNum(Map<String, Object> map);

	int getHandleJobCountByEntityInstanceId(Map<String, Object> map);

	int getPendingWorkCount(Map<String, Object> map);

	void delClientMsgByJobCode(String job_code);

	int getHandleDeviceEventNum(Map<String, Object> map);

	void delRetrieveMsgByUser(Map<String, Object> map);

	SecUser getUserByIdentity(String identity);

	List<String> getOperTypeByJobCode(String job_code);

	Integer getDirRankByOperCode(String oper_code);

	/**
	 * 待办
	 * 
	 * @param map
	 * @return
	 */

	List<ClientMsg> getClientMessage(Map<String, Object> map);

	List<ClientMsg> getClientMessage1(Map<String, Object> map);

	List<ClientMsg> getClientMessage2(Map<String, Object> map);

	String getDeptName(String dept_id);

	String getUserName(String user_iidd);

	int getPendingWorkCount1(Map<String, Object> map);

	SysConsole getConsoleByCode(String console_code);
	List<ClientTask> getTask(Map<String, Object> map);
}
