package hdsec.web.project.secmanage.mapper;

import hdsec.web.project.secmanage.model.ExchangeMaterialEvent;
import hdsec.web.project.secmanage.model.ExhibitionEvent;
import hdsec.web.project.secmanage.model.FileOutMakeEvent;
import hdsec.web.project.secmanage.model.InternetEmailEvent;
import hdsec.web.project.secmanage.model.PaperPatentEvent;
import hdsec.web.project.secmanage.model.PunishRectifyEvent;
import hdsec.web.project.secmanage.model.ResearchFieldInEvent;
import hdsec.web.project.secmanage.model.SecCheckEvent;

import java.util.List;
import java.util.Map;

public interface SecManageMapper {

	void addResearchFieldInEvent(ResearchFieldInEvent event) throws Exception;

	void updateResearchFieldInEventJobCode(Map<String, String> map);

	List<ResearchFieldInEvent> getResearchFieldInEventList(Map<String, Object> map);

	ResearchFieldInEvent getResearchFieldInEventByEventCode(String event_code);

	String getJobCodeByEventCode(String event_code);

	void delDelResearchFieldInEventByEventCode(String event_code);

	List<ResearchFieldInEvent> getResearchFieldInEventListByJobCode(String job_code);

	void addInternetEmailEvent(InternetEmailEvent event) throws Exception;

	void updateInternetEmailEventJobCode(Map<String, String> map);

	List<InternetEmailEvent> getInternetEmailEventList(Map<String, Object> map);

	String getEmailJobCodeByEventCode(String event_code);

	void delDelInternetEmailEventByEventCode(String event_code);

	InternetEmailEvent getInternetEmailEventByEventCode(String event_code);

	List<InternetEmailEvent> getInternetEmailListByJobCode(String job_code);

	void addSecCheckEvent(SecCheckEvent event) throws Exception;

	void updateSecCheckEventJobCode(Map<String, String> map);

	List<SecCheckEvent> getSecCheckEventList(Map<String, Object> map);

	String getSecCheckJobCodeByEventCode(String event_code);

	void delDelSecCheckEventByEventCode(String event_code);

	SecCheckEvent getSecCheckEventByEventCode(String event_code);

	List<SecCheckEvent> getSecCheckListByJobCode(String job_code);

	void addExchangeMaterialEvent(ExchangeMaterialEvent event) throws Exception;

	void updateExchangeMaterialEventJobCode(Map<String, String> map);

	List<ExchangeMaterialEvent> getExchangeMaterialEventList(Map<String, Object> map);

	String getExchangeMaterialJobCodeByEventCode(String event_code);

	void delDelExchangeMaterialEventByEventCode(String event_code);

	ExchangeMaterialEvent getExchangeMaterialEventByEventCode(String event_code);

	List<ExchangeMaterialEvent> getExchangeMaterialListByJobCode(String job_code);

	void addFileOutMakeEvent(FileOutMakeEvent event) throws Exception;

	void updateFileOutMakeEventJobCode(Map<String, String> map);

	List<FileOutMakeEvent> getFileOutMakeEventList(Map<String, Object> map);

	FileOutMakeEvent getFileOutMakeEventByEventCode(String event_code);

	String getJobCodeByFileOutMakeEventCode(String event_code);

	void delDelFileOutMakeEventByEventCode(String event_code);

	List<FileOutMakeEvent> getFileOutMakeEventListByJobCode(String job_code);

	void addExhibitionEvent(ExhibitionEvent event) throws Exception;

	List<ExhibitionEvent> getExhibitionEventList(Map<String, Object> map);

	ExhibitionEvent getExhibitionEventByEventCode(String event_code);

	String getExhibitionJobCodeByEventCode(String event_code);

	List<ExhibitionEvent> getExhibitionListByJobCode(String job_code);

	void delDelExhibitionEventByEventCode(String event_code);

	void updateExhibitionEventJobCode(Map<String, String> map);

	void addPunishEvent(PunishRectifyEvent event) throws Exception;

	void updatePunishEventJobCode(Map<String, String> map);

	List<PunishRectifyEvent> getPunishEventList(Map<String, Object> map);

	String getPunishJobCodeByEventCode(String event_code);

	void delDelPunishEventByEventCode(String event_code);

	PunishRectifyEvent getPunishEventByEventCode(String event_code);

	List<PunishRectifyEvent> getPunishEventListByJobCode(String job_code);

	void addApproveFile(Map<String, Object> map);

	void updateSecCheckEventFileInfo(Map<String, Object> map);

	void updateSecCheckEvent(Map<String, Object> map);

	void addPaperPatentEvent(PaperPatentEvent event) throws Exception;

	void updatePaperPatentEventJobCode(Map<String, String> map);

	String getPaperPatentJobCodeByEventCode(String event_code);

	List<PaperPatentEvent> getPaperPatentListByJobCode(String job_code);

	List<PaperPatentEvent> getPaperPatentEventList(Map<String, Object> map);

	PaperPatentEvent getPaperPatentEventByEventCode(String event_code);

	void delPaperPatentEventByEventCode(String event_code);
}