package hdsec.web.project.print.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.basic.model.SysProxy;
import hdsec.web.project.print.model.OaPrintEvent;
import hdsec.web.project.print.model.PrintEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 待审批打印任务列表
 * 
 * 添加特殊打印审批任务（2015-11-10-guojiao）
 * 
 * @author renmingfei
 * 
 */
public class ManagePrintAprvJobAction extends PrintBaseAction {

	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("proxy_user_iidd", getCurUser().getUser_iidd());
		map.put("proxy_type", "APPROVE");
		List<SysProxy> approvers = basicService.getApproveProxy(map);
		if (approvers != null && approvers.size() > 0) { // 代理审批
			for (SysProxy sysProxy : approvers) {
				if (!sysProxy.getIsExpire()) {
					candidateList = basicService.getCandidateListByUserId(sysProxy.getUser_iidd(),
							JobTypeEnum.PRINT_FILE);
					applyList.addAll(candidateList);
					candidateList = basicService.getCandidateListByUserId(sysProxy.getUser_iidd(),
							JobTypeEnum.PRINT_REMAIN);
					applyList.addAll(candidateList);
					candidateList = basicService.getCandidateListByUserId(sysProxy.getUser_iidd(),
							JobTypeEnum.PRINT_SEND);
					applyList.addAll(candidateList);
					candidateList = basicService.getCandidateListByUserId(sysProxy.getUser_iidd(),
							JobTypeEnum.SPECIAL_PRINT);
					applyList.addAll(candidateList);
					candidateList = basicService.getCandidateListByUserId(sysProxy.getUser_iidd(),
							JobTypeEnum.SPECIAL_PRINT_ZWYJG);
					applyList.addAll(candidateList);
				}
			}
		}
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PRINT_FILE);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PRINT_REMAIN);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PRINT_SEND);
		applyList.addAll(candidateList);
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.SPECIAL_PRINT);
		applyList.addAll(candidateList);

		// 增加中物院机关特殊打印流程
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(),
				JobTypeEnum.SPECIAL_PRINT_ZWYJG);
		applyList.addAll(candidateList);

		for (ProcessJob job : applyList) {
			String event_names = "";
			if (job.getJobType().getJobTypeCode().equals("SPECIAL_PRINT")) {
				List<OaPrintEvent> events = printService.getSpecialPrintEventListByJobCode(job.getJob_code());
				for (OaPrintEvent event : events) {
					event_names += event.getPaper_name() + "  ";
				}
			} else {
				List<PrintEvent> events = printService.getPrintEventListByJobCode(job.getJob_code());
				for (PrintEvent event : events) {
					event_names += event.getFile_title() + "  ";
				}
			}
			job.setEvent_names(event_names);
		}

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "PRINT", 1);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "SPECIAL_PRINT", 1);
		return SUCCESS;
	}

}
