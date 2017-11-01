package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.secmanage.model.FileOutMakeEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 涉密文件（资料）外出制作审批任务
 * 
 * @author gaoximin 2015-7-23
 */
public class ManageFileOutMakeAprvJobAction extends SecManageBaseAction {
	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	private List<ProcessJob> applyList = null;

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.FILEOUTMAKE);
		applyList.addAll(candidateList);
		for (ProcessJob job : applyList) {
			String event_names = "";
			List<FileOutMakeEvent> events = secManageService.getFileOutMakeEventListByJobCode(job.getJob_code());
			for (FileOutMakeEvent event : events) {
				event_names += event.getFile_name() + "  ";
			}
			job.setEvent_names(event_names);
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "FILEOUTMAKE", 1);
		return SUCCESS;
	}

}
