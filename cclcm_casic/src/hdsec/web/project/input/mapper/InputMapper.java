package hdsec.web.project.input.mapper;

import hdsec.web.project.input.model.InputEvent;

import java.util.List;
import java.util.Map;

/**
 * 
 * 电子文件导入管理
 * 
 * @author guoxh 2016-10-8 18:31:06
 */
public interface InputMapper {

	void addInputEvent(InputEvent event);

	List<InputEvent> getInputEventList(Map<String, Object> map);

	String getJobCodeByEventCode(String event_code);

	String getEventCodeByJobCode(String job_code);

	InputEvent getInputEventByCode(String event_code);

	void cancelInputEventByCode(String event_code);

	List<InputEvent> getInputEventListByJobCode(String job_code);

	void updateInputEventState(Map<String, Object> map);

	List<InputEvent> getEfileInputEventList(Map<String, Object> map);

	int getEfileInputEventListSize(Map<String, Object> map);
}
