package hdsec.web.project.carriermanage.mapper;

import hdsec.web.project.carriermanage.model.PersonalFileEvent;
import hdsec.web.project.carriermanage.model.PersonalFileInfo;

import java.util.List;
import java.util.Map;

public interface CarrierManageMapper {

	List<PersonalFileInfo> getPersonalFileInfo(Map<String, Object> map);

	void addPersonalFileInfo(PersonalFileInfo fileinfo);

	void addPersonalFileEvent(PersonalFileEvent event) throws Exception;

	void updatePersonalFileEventJobCode(Map<String, String> map);

	List<PersonalFileEvent> getPersonalFileEventListByJobCode(String job_code);

	List<PersonalFileInfo> getFileInfo(Map<String, Object> map);

	List<PersonalFileEvent> getPersonalFileEventList(Map<String, Object> map);

	void updateStatueEntityPersonalFile(String event_code);

	int getFileInfoSize(Map<String, Object> map);
}
