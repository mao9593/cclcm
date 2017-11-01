package hdsec.web.project.publicity.mapper;

import hdsec.web.project.publicity.model.ReportEvent;

import java.util.List;
import java.util.Map;

public interface PublicityMapper {

	void addReportEvent(ReportEvent event) throws Exception;

	void updateReportEventJobCode(Map<String, String> map);

	ReportEvent getReportEventByEventCode(String event_code);

	String getJCodeByECode(String event_code);

	List<ReportEvent> getPublReportEventList(Map<String, Object> map);

	List<ReportEvent> getPublReportEventListByJobCode(String job_code);

	void delUPublReportEventByEventCode(String event_code);


}
