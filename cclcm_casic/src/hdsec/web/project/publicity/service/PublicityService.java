package hdsec.web.project.publicity.service;

import hdsec.web.project.publicity.model.ReportEvent;

import java.util.List;
import java.util.Map;

public interface PublicityService {

	/** 添加宣传报道审批作业 **/
	void addReportEvent(ReportEvent event, String next_approver) throws Exception;

	/** 通过流水号查找宣传报道详情 **/
	ReportEvent getReportEvent(String event_code);

	/** 通过作业流水号查询任务流水号 */
	String getJobCodeByEventCode(String event_code);

	/** 查询用户宣传报道审批信息 */
	List<ReportEvent> getPublReportEventList(Map<String, Object> map);

	/** 根据任务作业号查询用户宣传报道作业信息 */
	List<ReportEvent> getPublReportEventListByJobCode(String job_code);

	/** 通过作业流水号删除用户宣传报道作业 */
	void delUPublReportEventByEventCode(String event_code, String apply_type);

}
