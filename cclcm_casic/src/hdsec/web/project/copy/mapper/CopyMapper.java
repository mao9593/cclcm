package hdsec.web.project.copy.mapper;

import hdsec.web.project.copy.model.CopyEvent;
import hdsec.web.project.ledger.model.EntityPaper;

import java.util.List;
import java.util.Map;

public interface CopyMapper {

	/**
	 * 复印作业管理
	 */
	void addCopyEvent(CopyEvent event);

	List<CopyEvent> getCopyEventList(Map<String, Object> map);

	CopyEvent getCopyEventByCopyCode(String event_code);

	void delCopyEventByCopyCode(String event_code);

	String getJobCodeByEventCode(String event_code);

	void updateCopyEvent(CopyEvent event);

	void addPaperledger(EntityPaper paper);

	void updateIsbarcode(Map<String, Object> map);

	void updateCopystatus(Map<String, Object> map);

	List<CopyEvent> getCopyEventListByJobCode(String job_code);

	void cancelCopyEventByEventCode(String event_code);

	int getCopyEventCountByJobCode(String job_code);

	void updateCopyEventJobRel(Map<String, Object> map);

	void addCopyEventByEnter(CopyEvent event);

	void addCopyEventByMerge(CopyEvent event);

	void addCopyEventJobRel(Map<String, Object> map);

	void cancelCopyEventByPrintEventCode(String print_eventcode);

}
