package hdsec.web.project.print.service;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.print.model.FixAccording;
import hdsec.web.project.print.model.OaPrintEvent;
import hdsec.web.project.print.model.PrintEvent;
import hdsec.web.project.print.model.RefFixAccordingDept;
import hdsec.web.project.print.model.RiskKeywordsPrint;
import hdsec.web.project.print.model.SysPrinter;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PrintService {

	List<SysPrinter> getPrinterList(Map<String, Object> map);

	List<PrintEvent> getPrintEventList(Map<String, Object> map);

	PrintEvent getPrintEventByPrintId(String id);

	PrintEvent getPrintEventByPrintCode(String event_code);

	String getJobCodeByEventCode(String event_code);

	void delPrintEventByPringId(String id);

	void delPrintEventByPrintCode(String event_code);

	void updatePrintEvent(PrintEvent event);

	void updatePrintEvent1(PrintEvent event);

	int cancelPrintEventByEventCode(String event_code);

	void configDeptPrinter(String dept_id, String printer_code);

	String getDeptPrinter(String dept_id);

	/**
	 * 添加打印申请 2014-5-3 下午9:31:38
	 * 
	 * @author renmingfei
	 * @param user_iidd
	 *            用户登录Id
	 * @param dept_id
	 *            用户部门ID
	 * @param seclv_code
	 *            审批单密级
	 * @param jobType
	 *            审批任务类型
	 * @param next_approver
	 *            下级审批人
	 * @param output_dept_name
	 *            接收人部门
	 * @param output_user_name
	 *            接收人姓名
	 * @param comment
	 *            打印说明
	 * @param eventIdList
	 *            打印作业ID列表
	 * @param usage_code
	 *            用途编号
	 * @param project_code
	 *            项目编号
	 * @param file_seclvs
	 *            文件密级列表
	 * @throws Exception
	 */
	void addProcessJob(String user_iidd, String dept_id, Integer seclv_code, String cycle_type, String period,
			JobTypeEnum jobType, String next_approver, String output_dept_name, String output_user_name,
			String comment, List<String> eventIdList, String usage_code, String project_code,
			Map<String, String> fileSeclvMap, Map<String, String> filePrintCountMap, Map<String, String> fileColorMap,
			Map<String, String> filePrintDoubleMap, Map<String, String> fileTitleMap, Date start_time) throws Exception;

	/**
	 * 通过任务编号查询打印作业列表 2014-5-4 下午10:06:38
	 * 
	 * @author renmingfei
	 * @param job_code
	 * @return
	 */
	List<PrintEvent> getPrintEventListByJobCode(String job_code);

	/**
	 * 批量删除打印作业
	 * 
	 * @param event_id_list
	 */
	String delMultiPrintEvents(List<String> event_id_list);

	/**
	 * .查询出未打印的event,并把其job_code置为null
	 * 
	 * @param job_code
	 */
	void cancelPrintEventByJobCode(String job_code);

	/**
	 * 查询已打印的event的数目
	 * 
	 * @param job_code
	 * @return
	 */
	int getPrintEventEnterCancel(String job_code);

	/** 特殊打印作业添加 **/
	void addOaPrintEvent(OaPrintEvent event);

	/** 已添加特殊打印作业列表 **/
	List<OaPrintEvent> getSpecialEventList(Map<String, Object> map);

	/** 根据job_code获取特殊打印作业列表 **/
	List<OaPrintEvent> getSpecialPrintEventListByJobCode(String job_code);

	OaPrintEvent getSpecialPrintEventByEventCode(String event_code);

	OaPrintEvent getSpecialPrintEventById(String id);

	void querrySpecialOpers(Map<String, Object> map, CycleItem cycleitem, EntityPaper paper);

	void delSpecialPrintEventByPringId(String id);

	void updateSpecialPrintEventByEventCode(Map<String, Object> map);

	/** 根据st_file_name更新打印作业变的文件阅读状态字段 **/
	void updateEventFileReadByStFileName(Map<String, Object> map);

	int getIsAllEventIsRead(String job_code);

	/** 根据st_file_name更新打印作业变的文件阅读状态字段 **/
	void updateEventFileReadByJobCode(Map<String, Object> map);

	void updatePrintProxyUseridByEventCode(Map<String, Object> map);

	void AddKeywordPrintEvent(Map<String, Object> map);

	// 更新邦辰关键字检索结果
	void updateKeywordPrintEvent(Map<String, Object> map);

	// 获取邦辰关键字json数据解析表RISKLIST_PRINT表中的数据
	List<RiskKeywordsPrint> getRisklistPrint(Map<String, Object> map);

	// 获取作业的关键字检查唯一性编号
	String getPrintTransID(Map<String, Object> map);

	void addRisklistPrint(RiskKeywordsPrint temp);

	void addRisklistPrintList(List<RiskKeywordsPrint> list);

	String getTransIDfromPrint(Map<String, Object> map);

	/**
	 * .查询出(特殊打印)未打印的event,并把其job_code置为null
	 * 
	 * @param job_code
	 */
	void cancelOaPrintEventByJobCode(String job_code);

	/**
	 * 查询(特殊打印)已打印的event的数目
	 * 
	 * @param job_code
	 * @return
	 */
	int getOaPrintEventEnterCancel(String job_code);

	void remarkPrintEvent(Map<String, Object> map);

	int getPrintEventSize(Map<String, Object> map);

	/**
	 * 添加打印申请 扩展合并打印 2017-6-7
	 * 
	 * @author nv1j2
	 * @param user_iidd
	 *            用户登录Id
	 * @param dept_id
	 *            用户部门ID
	 * @param seclv_code
	 *            审批单密级
	 * @param jobType
	 *            审批任务类型
	 * @param next_approver
	 *            下级审批人
	 * @param output_dept_name
	 *            接收人部门
	 * @param output_user_name
	 *            接收人姓名
	 * @param comment
	 *            打印说明
	 * @param eventIdList
	 *            打印作业ID列表
	 * @param usage_code
	 *            用途编号
	 * @param project_code
	 *            项目编号
	 * @param file_seclvs
	 *            文件密级列表
	 * @throws Exception
	 */
	void addProcessJob(String user_iidd, String dept_id, Integer seclv_code, String cycle_type, String period,
			JobTypeEnum jobType, String next_approver, String output_dept_name, String output_user_name,
			String comment, List<String> eventIdList, String usage_code, String project_code,
			Map<String, String> fileSeclvMap, Map<String, String> filePrintCountMap, Map<String, String> fileColorMap,
			Map<String, String> filePrintDoubleMap, Map<String, String> fileTitleMap, Date start_time, Integer copy_num)
			throws Exception;

	List<FixAccording> getFixAccordingByType(Map<String, Object> map);

	void updateFixedContent(Map<String, Object> map);

	void addFixedContent(Map<String, Object> map);

	void delFixedAccordingById(String id);

	/**
	 * 将本次成功查询是否命中的结果(命中Hit,未命中NoHit)更新至数据库表keyword_print的字段CHECK_RESULT中 2017-5-25
	 * 
	 * @param tid
	 * @param check_result
	 */
	void updateKeywordPrintCheckresultByTid(String tid, String check_result);

	Integer getFixedId(String content);

	void addRefFixedAccordingDept(RefFixAccordingDept refFixAccordingDept);
}
