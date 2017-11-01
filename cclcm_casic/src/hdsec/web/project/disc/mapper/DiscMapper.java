package hdsec.web.project.disc.mapper;

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
public interface DiscMapper {

	void getCreateBarcode_1(Map<String, Object> map);

	void getCreateBarcode_spacecd(Map<String, Object> map);

	void addEnterSpaceCD(EntitySpaceCD item);

	void addEnterSpaceCDEvent(SpaceCDEvent item);

	List<SpaceCDEvent> getEventSpaceCdList(Map<String, Object> map);

	List<EntitySpaceCD> getDeptEntitySpaceCdList(Map<String, Object> map);

	List<EntitySpaceCD> getDeptSpaceCdStock(Map<String, Object> map);

	void updateSpaceCdById(Map<String, Object> map);

	void updateSpaceCDEventByEventCode(Map<String, Object> map);

	EntitySpaceCD getEntitySpaceCdById(String id);

	void updateSpaceCdJobById(Map<String, Object> map);

	void updateSpaceCdJobByIdPass(Map<String, Object> map);

	List<EntitySpaceCD> getSendEntitySpaceCdList(Map<String, Object> map);

	void cancelHandleSpaceCDById(String id);

	int getHandleSpaceCDCountByJobCode(String job_code);

	List<EntitySpaceCD> getEntitySpaceCdListByJobCode(String job_code);

	void updateSpaceCdState(Map<String, Object> map);

	void updateSpaceCdChangeSelf(Map<String, Object> map);

	void addCycleItem(CycleItem cycleitem);

	void addCDledger(EntityCD cd);

	void addSpaceCDEvent(SpaceCDEvent event);

	void addSpaceCDEventJobRel(Map<String, Object> map);

	List<SpaceCDEvent> getSpaceCDEventListByJobCode(String job_code);

	SpaceCDEvent getSpaceCDEventByEventCode(String event_code);

	void updateSpaceCdReturn(Map<String, Object> map);

	List<SpaceCDEvent> getSpaceCDEventList(Map<String, Object> map);

	void cancelSpaceEventByJobCode(String job_code);

	int getSpaceCDEventEnterCancel(String job_code);

	void delSpaceCDEventByEventCode(String event_code);

	void delSpaceCDEventById(String id);

	List<EntitySpaceCD> getSelfEntitySpaceCdList(Map<String, Object> map);

	// 更新空白盘作业表中数据
	void updateSpaceCdEvent(Map<String, Object> map);

	/*
	 * 未分配未喷绘未使用空白盘盘台账退回
	 * 
	 * @param event_code
	 */
	void deleteSpaceCdEntityById(String id);

}
