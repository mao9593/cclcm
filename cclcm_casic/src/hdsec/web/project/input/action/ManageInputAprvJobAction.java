package hdsec.web.project.input.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.input.model.InputEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 电子文件导入待审批
 * 
 * @author guoxh 2016-9-30 16:43:35
 */
public class ManageInputAprvJobAction extends InputBaseAction {
	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.MSG_INPUT);
		applyList.addAll(candidateList);
		for (ProcessJob job : applyList) {
			String event_names = "";
			List<InputEvent> events = inputService.getInputEventListByJobCode(job.getJob_code());
			for (InputEvent event : events) {
				event_names += event.getFile_list() + "  ";
			}
			job.setEvent_names(event_names);
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "MSG_INPUT", 1);
		return SUCCESS;
	}

}
