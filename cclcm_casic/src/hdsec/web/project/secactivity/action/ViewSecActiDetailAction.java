package hdsec.web.project.secactivity.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.secactivity.model.UserSecActiEvent;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 查看涉密活动作业详情
 * 
 * @author gj
 */
public class ViewSecActiDetailAction extends SecuActiBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String op = "";
	private UserSecActiEvent event = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	private List<BurnFile> burnFileList = null;
	private String leader[] = null;
	private Integer listSize = null;
	private String opinion_all = "";

	public void setListSize(Integer listSize) {
		this.listSize = listSize;
	}

	public Integer getListSize() {
		return listSize;
	}

	public String getOpinion_all() {
		return opinion_all;
	}

	public String[] getLeader() {
		return leader;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public UserSecActiEvent getEvent() {
		return event;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public ProcessJob getJob() {
		return job;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public List<BurnFile> getBurnFileList() {
		return burnFileList;
	}

	@Override
	public String executeFunction() throws Exception {
		event = secactivityservice.getSecActiEvent(event_code);
		if (event == null) {
			throw new Exception("查询的作业已经被删除");
		} else {
			if (!event.getSecret_leader().equals("")) {
				leader = event.getSecret_leader().split(",");
			}
			String[] filelist = event.getFile_list().split(CCLCMConstants.DEVIDE_SYMBOL);
			if (filelist.length > 0 && StringUtils.hasLength(filelist[0])) {
				String storePath = BMPropertyUtil.getSecActivityStrorePath();
				burnFileList = new ArrayList<BurnFile>();
				String file_path = "";
				for (int i = 0; i < filelist.length; i++) {
					file_path = storePath + "/" + event_code + "/" + filelist[i];
					burnFileList.add(new BurnFile(filelist[i], file_path));
				}
			}
			String job_code = secactivityservice.getJobCodeByEventCode(event_code);
			if (StringUtils.hasLength(job_code)) {
				job = basicService.getProcessJobByCode(job_code);
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
			}
			listSize = recordList.size() - 1;
			for (int i = 1; i <= listSize; i++) {
				opinion_all = opinion_all + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion() + "<br>审批人："
						+ recordList.get(i).getUser_name() + "<br>审批时间：" + recordList.get(i).getOp_time_str();
			}
			return SUCCESS;
		}
	}
}
