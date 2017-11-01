package hdsec.web.project.secplace.mapper;

import hdsec.web.project.secplace.model.EnterSecplaceEvent;
import hdsec.web.project.secplace.model.EntitySecplace;
import hdsec.web.project.secplace.model.EntityVisitor;
import hdsec.web.project.secplace.model.SecplaceEvent;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author liuyaling 2015-5-11
 */
public interface SecplaceMapper {

	void addEntitySecplace(EntitySecplace place);

	List<EntitySecplace> getAllSecplaceList(Map<String, Object> map);

	EntitySecplace getSecplaceByBarcode(String secplace_barcode);

	void updateSecplace(EntitySecplace secplace);

	List<EntitySecplace> getFuzzySecplaceList(String fuzzy);

	void addEnterSecplaceEvent(EnterSecplaceEvent event);

	void updateEnterSecplaceEventJobCode(Map<String, String> map);

	void addEntityVisitor(EntityVisitor visitor);

	List<EnterSecplaceEvent> getEnterSecplaceEventList(Map<String, Object> map);

	EnterSecplaceEvent getEnterSecplaceEventByEventCode(String event_code);

	String getEnterSecplaceEventJobCodeByEventCode(String event_code);

	String getSecplaceEventJobCodeByEventCode(String event_code);

	List<EntityVisitor> getVisitorListByEventCode(String event_code);

	EnterSecplaceEvent getEnterSecplaceEventListByJobCode(String job_code);

	String getEnterSecplaceEventCodeByJobCode(String job_code);

	void updateEnterSecplaceEventFileInfo(Map<String, Object> map);

	void addSecplaceEvent(SecplaceEvent event);

	void updateSecplaceEventJobCode(Map<String, String> map);

	List<SecplaceEvent> getSecplaceEventList(Map<String, Object> map);

	SecplaceEvent getSecplaceEventByEventCode(String event_code);

	SecplaceEvent getSecplaceEventByJobCode(String job_code);

	String getSecplaceEventCodeByJobCode(String job_code);

	void deleteEnterSecplaceEvent(String event_code);

	void deleteSecplaceEvent(String event_code);

	int getSecplaceCountBySecplaceCode(String secplace_code);

}