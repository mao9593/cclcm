package hdsec.web.project.disc.service;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.disc.model.EntitySpaceCD;
import hdsec.web.project.disc.model.SpaceCDEvent;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityCD;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author lidepeng 2015-8-11
 */
public interface DiscService {

	List<String> createEntityBarcode_1(String string, int spacecd_count) throws Exception;

	/*
	 * List<String> createEntityBarcode_spacecd(String string, String dept_id, int seclv_code, int spacecd_count) throws
	 * Exception;
	 */

	void addEnterSpaceCD(EntitySpaceCD entity);

	void addEnterSpaceCDEvent(SpaceCDEvent event);

	List<SpaceCDEvent> getEventSpaceCdList(Map<String, Object> map);

	List<EntitySpaceCD> getDeptEntitySpaceCdList(Map<String, Object> map);

	List<EntitySpaceCD> getSelfEntitySpaceCdList(Map<String, Object> map);

	List<EntitySpaceCD> getDeptSpaceCdStock(Map<String, Object> map);

	void updateSpaceCdById(Map<String, Object> map);

	void updateSpaceCDEventByEventCode(Map<String, Object> map);

	EntitySpaceCD getEntitySpaceCdById(String id);

	void addSpaceCdProcessJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, String comment, List<String> stringArrayToList, String usage_code, String project_code)
			throws Exception;

	List<EntitySpaceCD> getSendEntitySpaceCdList(Map<String, Object> map);

	void cancelHandleSpaceCDById(String id);

	List<EntitySpaceCD> getEntitySpaceCdListByJobCode(String job_code);

	void updateSpaceCdState(Map<String, Object> map);

	void updateSpaceCdChangeSelf(Map<String, Object> map);

	void addCycleItem(CycleItem cycleitem);

	void addCDledger(EntityCD entityCD);

	void updateSpaceCdReturn(Map<String, Object> map);

	/**
	 * 查看空白盘作业列表
	 * 
	 * @author lishu
	 * @param job_code
	 * @return
	 */
	List<SpaceCDEvent> getSpaceCDEventList(Map<String, Object> map);

	void addSpaceCDEvent(SpaceCDEvent event);

	List<SpaceCDEvent> getSpaceCDEventListByJobCode(String job_code);

	/**
	 * 通过作业编号查找作业
	 * 
	 * @param event_code
	 */
	SpaceCDEvent getSpaceCDEventByEventCode(String event_code);

	/**
	 * 通过作业编号删除作业
	 * 
	 * @param event_code
	 */
	void delSpaceCDEventByEventCode(String event_code);

	/**
	 * 通过作业id删除作业
	 * 
	 * @param id
	 */
	void delSpaceCDEventById(String id);

	/**
	 * 未分配未喷绘未使用空白盘盘台账退回
	 * 
	 * @param id
	 */
	void deleteSpaceCdEntityById(String id);

	// 更新空白盘作业表中数据
	void updateSpaceCdEvent(Map<String, Object> map);

	void cancelSpaceEventByJobCode(String job_code);

	int getSpaceCDEventEnterCancel(String job_code);
}
