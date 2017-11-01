package hdsec.web.project.secplace.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.secplace.model.EnterSecplaceEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * 查看进入涉密场所待审批列表
 * 
 * @author liuyaling 2015-6-12
 */
public class ManageEnterSecplaceAprvJobAction extends SecplaceBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ProcessJob> candidateList = null;
	// private List<ProcessJob> assignedList = null;
	private List<ProcessJob> applyList = null;
	private String file_src;

	public List<ProcessJob> getCandidateList() {
		return candidateList;
	}

	public void setCandidateList(List<ProcessJob> candidateList) {
		this.candidateList = candidateList;
	}

	public List<ProcessJob> getApplyList() {
		return applyList;
	}

	public void setApplyList(List<ProcessJob> applyList) {
		this.applyList = applyList;
	}

	public String getFile_src() {
		return file_src;
	}

	public void setFile_src(String file_src) {
		this.file_src = file_src;
	}

	@Override
	public String executeFunction() throws Exception {
		applyList = new ArrayList<ProcessJob>();
		candidateList = basicService.getCandidateListByUserId(getCurUser().getUser_iidd(), JobTypeEnum.ENTER_SECPLACE);
		applyList.addAll(candidateList);
		for (ProcessJob job : applyList) {
			String event_names = "";
			EnterSecplaceEvent event = secplaceService.getEnterSecplaceEventByJobCode(job.getJob_code());
			event_names += event.getFile_list() + "  ";
			job.setEvent_names(event_names);
		}
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "ENTER_SECPLACE", 1);

		return SUCCESS;
	}

}