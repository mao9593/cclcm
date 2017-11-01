package hdsec.web.project.change.mapper;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.change.model.EventChange;

import java.util.List;
import java.util.Map;

public interface ChangeMapper {

	void addChangeEvent(EventChange event);

	List<ProcessJob> getJobList(Map<String, Object> map);

	List<EventChange> getChangeEventListByJobCode(String job_code);

	EventChange getChangeEventByCode(String event_code);

	String getJobCodeByEventCode(String event_code);

	void cancelChangeEvent(Map<String, Object> map);

	List<EventChange> getChangeEventList(Map<String, Object> map);

	void updateChangeEvent(EventChange event);

	void updateEntityPaperScope(Map<String, Object> map);

	void updateEntityCDScope(Map<String, Object> map);

	ProcessJob getProcessJobByJobCode(String job_code);

	void updatePaperStateByBarcode(Map<String, Object> stateMap);

	void updateCDStateByBarcode(Map<String, Object> stateMap);

}
