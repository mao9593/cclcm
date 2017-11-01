package hdsec.web.project.print.mapper;

import hdsec.web.project.print.model.FixAccording;
import hdsec.web.project.print.model.OaPrintEvent;
import hdsec.web.project.print.model.PrintEvent;
import hdsec.web.project.print.model.RefFixAccordingDept;
import hdsec.web.project.print.model.RiskKeywordsPrint;
import hdsec.web.project.print.model.SysPrinter;

import java.util.List;
import java.util.Map;

public interface PrintMapper {

	List<SysPrinter> getPrinterList(Map<String, Object> map);

	List<PrintEvent> getPrintEventList(Map<String, Object> map);

	PrintEvent getPrintEventByPrintId(String id);

	PrintEvent getPrintEventByPrintCode(String event_code);

	String getJobCodeByEventCode(String event_code);

	void delPrintEventByPringId(String id);

	void delPrintEventByPrintCode(String event_code);

	void updatePrintEvent(PrintEvent event);

	void updatePrintEvent1(PrintEvent event);

	void cancelPrintEventByEventCode(String event_code);

	int getPrintEventCountByJobCode(String job_code);

	void delDeptPrinter(String dept_id);

	void addDeptPrinter(Map<String, Object> map);

	String getDeptPrinter(String dept_id);

	void addPrintEventJobRel(Map<String, Object> map);

	List<PrintEvent> getPrintEventListByJobCode(String job_code);

	void cancelPrintEventByJobCode(String job_code);

	int getPrintEventEnterCancel(String job_code);

	void addOaPrintEvent(OaPrintEvent event);

	List<OaPrintEvent> getSpecialEventList(Map<String, Object> map);

	void addOaPrintEventJobRel(Map<String, Object> map);

	List<OaPrintEvent> getSpecialPrintEventListByJobCode(String job_code);

	OaPrintEvent getSpecialPrintEventByPrintId(String id);

	OaPrintEvent getSpecialPrintEventByEventCode(String event_code);

	OaPrintEvent getSpecialPrintEventById(String id);

	void delSpecialPrintEventByPringId(String id);

	void updateSpecialEventState(Map<String, Object> map);

	void updateSpecialPrintEventByEventCode(Map<String, Object> map);

	void updateEventFileReadByStFileName(Map<String, Object> map);

	int getIsAllEventIsRead(String job_code);

	void updateEventFileReadByJobCode(Map<String, Object> map);

	void updatePrintProxyUseridByEventCode(Map<String, Object> map);

	void AddKeywordPrintEvent(Map<String, Object> map);

	// 根据事务编号TID更新打印调用邦辰关键字引擎接口返回的接口调用情况（成功或失败）。
	void updateKeywordPrintCallresultByTid(Map<String, Object> map);

	void updateKeywordPrintEvent(Map<String, Object> map);

	// 将解析出来的json数据中的risklist信息插入数据
	void addRisklistPrint(RiskKeywordsPrint temp);

	List<RiskKeywordsPrint> getRisklistPrint(Map<String, Object> map);

	String getPrintTransID(Map<String, Object> map);

	void addRisklistPrintList(List<RiskKeywordsPrint> list);

	String getTransIDfromPrint(Map<String, Object> map);

	void cancelOaPrintEventByJobCode(String job_code);

	int getOaPrintEventEnterCancel(String job_code);

	void remarkPrintEvent(Map<String, Object> map);

	int getPrintEventSize(Map<String, Object> map);

	List<FixAccording> getFixAccordingByType(Map<String, Object> map);

	void updateFixedContent(Map<String, Object> map);

	void addFixedContent(Map<String, Object> map);

	void delFixedAccordingById(String id);

	void updateKeywordPrintCheckresultByTid(Map<String, Object> map);

	String getCheckresultByTid(String tid);

	Integer getFixedId(String content);

	void addRefFixedAccordingDept(RefFixAccordingDept refFixAccordingDept);
}
