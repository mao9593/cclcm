package hdsec.web.project.enter.mapper;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.enter.model.EnterEvent;
import hdsec.web.project.enter.model.EnterProcessJob;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.model.ReprintCD;

import java.util.List;
import java.util.Map;

public interface EnterMapper {
	/**
	 * 录入作业管理
	 */
	void addEnterEvent(EnterEvent event);

	List<EnterEvent> getEnterEventList(Map<String, Object> map);

	List<EnterEvent> getSelfEnterEventList(Map<String, Object> map);

	EnterEvent getEnterEventByEnterCode(String event_code);

	void delEnterEventByEnterCode(String event_code);

	String getJobCodeByEventCode(String event_code);

	void updateEnterEvent(EnterEvent event);

	void updateEnterEventState(Map<String, Object> map);

	void addPaperledger(EntityPaper paper);

	void addCDledger(EntityCD cd);

	int getEnterBarcodeSize(String barcode);

	void addProcessJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType, String next_approver,
			String output_dept_name, String output_user_name, String comment, List<String> eventIdList,
			String usage_code, String project_code, Map<String, String> fileSeclvMap) throws Exception;

	void addEnterEventJobRel(Map<String, Object> map);

	List<EnterEvent> getEnterEventListByJobCode(String job_code);

	void cancelEnterEventByEventCode(String event_code);

	int getEnterEventCountByJobCode(String job_code);

	Integer getEnterEventCountByMediumSerial(String barcode);

	String getEventCodeByBarcode(String barcode);

	List<EnterProcessJob> getEnterProcessJobListByInstanceId(List<String> instanceIdList);

	List<EnterProcessJob> getEnterApprovedJobByInstanceIds(Map<String, Object> map);

	void deleteEnterJob(String id);

	ReprintCD getEnterEventJoinImportByCDBarcode(String cd_barcode);

	ReprintCD getEnterEventJoinBurnByCDBarcode(String cd_barcode);

	ReprintCD getEnterEventCDBarcode(String cd_barcode);

	// 更新录入时填写的原文件/光盘/磁介质编号
	void updateCDSrccode(Map<String, Object> map);

	void updatePaperSrccode(Map<String, Object> map);

	void updateDeviceSrccode(Map<String, Object> map);

	EnterEvent getEnterEventByMediumSerial(String medium_serial);

	/**
	 * 查询已录入的event的数目
	 * 
	 * @param job_code
	 * @return
	 */
	int getEnterEventEnterCancel(String job_code);

	// 更新录入时填写的文件/光盘/磁介质 机要号
	void updateCDConfidentialnum(Map<String, Object> map);

	void updateDeviceConfidentialnum(Map<String, Object> map);

	void updatePaperConfidentialnum(Map<String, Object> map);
}
