package hdsec.web.project.secactivity.mapper;

import hdsec.web.project.secactivity.model.SecOutExchangeEvent;
import hdsec.web.project.secactivity.model.UserSecActiEvent;

import java.util.List;
import java.util.Map;

public interface SecActivityMapper {

	void AddUserSecActiEvent(UserSecActiEvent event);

	void updateSecActiEventJobCode(Map<String, String> map);

	UserSecActiEvent getSecActiEventByEventCode(String event_code);

	String getJCodeByECode(String event_code);

	List<UserSecActiEvent> getUSecActiEventList(Map<String, Object> map);

	List<UserSecActiEvent> getUSecActiEventListByJobCode(String job_code);

	void delUSecActiEventByEventCode(String event_code);

	/** 涉外交流保密工作 **/

	void AddSecOutExchangeEvent(SecOutExchangeEvent event);

	void updateSecOutExchangeEventJobCode(Map<String, String> map);

	List<SecOutExchangeEvent> getSecOutExchangeEventList(Map<String, Object> map);

	SecOutExchangeEvent getSecOutExchangeByEventCode(String event_code);

	String getOutExchangeJobCodeByEventCode(String event_code);

	void delSecOutExchangeEventByEventCode(String event_code);

	List<SecOutExchangeEvent> getOutExchangeEventListByJobCode(String job_code);
}
