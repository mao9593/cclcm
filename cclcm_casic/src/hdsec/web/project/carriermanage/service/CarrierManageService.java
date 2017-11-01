package hdsec.web.project.carriermanage.service;

import hdsec.web.project.carriermanage.model.PersonalFileEvent;
import hdsec.web.project.carriermanage.model.PersonalFileInfo;

import java.util.List;
import java.util.Map;

public interface CarrierManageService {

	/** 查询涉密人员个人涉密资料信息 **/
	List<PersonalFileInfo> getPersonalFileInfo(Map<String, Object> map);

	/** 新增涉密人员个人涉密资料信息 **/
	void addPersonalFileInfo(PersonalFileInfo fileinfo);

	/** 添加人员资料台帐申请作业 **/
	void addPersonalFileEvent(PersonalFileEvent event, String next_approver) throws Exception;

	/** 根据任务作业号查询用户涉密资料台帐作业信息* */
	List<PersonalFileEvent> getPersonalFileEventListByJobCode(String job_code);

	/** 查询涉密资料信息列表 **/
	List<PersonalFileInfo> getFileInfo(Map<String, Object> map);

	/** 查询用户申请审批信息 */
	List<PersonalFileEvent> getPersonalFileEventList(Map<String, Object> map);

	/** 审批通过更新个人资料台账审批状态字段为1已通过 **/
	void updateStatueEntityPersonalFile(String event_code);

	/** 查询涉密资料信息个数 **/
	int getFileInfoSize(Map<String, Object> map) throws Exception;
}
