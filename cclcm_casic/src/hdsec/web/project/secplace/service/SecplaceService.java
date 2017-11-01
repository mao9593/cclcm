package hdsec.web.project.secplace.service;

import hdsec.web.project.common.bm.BMCycleItem;
import hdsec.web.project.secplace.model.EnterSecplaceEvent;
import hdsec.web.project.secplace.model.EntitySecplace;
import hdsec.web.project.secplace.model.EntityVisitor;
import hdsec.web.project.secplace.model.SecplaceEvent;

import java.util.List;
import java.util.Map;

public interface SecplaceService {

	void addEntitySecplace(EntitySecplace place, BMCycleItem cycleitem);

	List<EntitySecplace> getAllSecplaceList(Map<String, Object> map) throws Exception;

	EntitySecplace getSecplaceByBarcode(String secplace_barcode);

	void updateSecplace(EntitySecplace place);

	List<EntitySecplace> getFuzzySecplaceList(String fuzzy);

	void addEnterSecplaceEvent(EnterSecplaceEvent event, String next_approver) throws Exception;

	void addEntityVisitor(EntityVisitor visitor);

	List<EnterSecplaceEvent> getEnterSecplaceEventList(Map<String, Object> map) throws Exception;

	EnterSecplaceEvent getEnterSecplaceEventByEventCode(String event_code);

	String getEnterSecplaceEventJobCodeByEventCode(String event_code);

	List<EntityVisitor> getVisitorListByEventCode(String event_code);

	EnterSecplaceEvent getEnterSecplaceEventByJobCode(String job_code);

	String getEnterSecplaceEventCodeByJobCode(String job_code);

	void updateEnterSecplaceEventFileInfo(String event_code, int file_num, String file_list);

	void addSecplaceEvent(SecplaceEvent event, String next_approver) throws Exception;

	List<SecplaceEvent> getSecplaceEventList(Map<String, Object> map) throws Exception;

	SecplaceEvent getSecplaceEventByEventCode(String event_code);

	SecplaceEvent getSecplaceEventByJobCode(String job_code);

	String getSecplaceEventCodeByJobCode(String job_code);

	void addSecplaceByEvent(SecplaceEvent event, String secplace_barcode, String user_name, String user_id);

	void deleteEnterSecplaceEvent(String event_code);

	void deleteSecplaceEvent(String event_code);

	String getSecplaceEventJobCodeByEventCode(String event_code);

	int getSecplaceCountBySecplaceCode(String secplace_code);

}