package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.common.bm.model.BMSysConfigItem;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.secmanage.model.ExchangeMaterialEvent;
import hdsec.web.project.secmanage.model.MaterialInfo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 查看对外交流材料任务详情
 * 
 * @author guojiao
 */
public class ViewExchangeMaterialDetailAction extends SecManageBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String op = "";
	private ExchangeMaterialEvent event = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	private List<MaterialInfo> material = null;
	private String type = "";
	private Integer listSize = null;
	private String opinion_all = "";

	public Integer getListSize() {
		return listSize;
	}

	public String getOpinion_all() {
		return opinion_all;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<MaterialInfo> getMaterial() {
		return material;
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

	public ExchangeMaterialEvent getEvent() {
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

	@Override
	public String executeFunction() throws Exception {
		event = secManageService.getExchangeMaterialEventByEventCode(event_code);
		if (event == null) {
			throw new Exception("查询的作业已经被删除");
		} else {
			if (!event.getFile_list().equals("")) {
				material = new ArrayList<MaterialInfo>();
				String[] temp = event.getFile_list().split(BMSysConfigItem.COMMON_SEPARATOR_END);
				for (int i = 0; i < temp.length; i++) {
					String[] temp_info = temp[i].split("\\|");
					material.add(new MaterialInfo(temp_info[1], temp_info[2]));
				}
			}
			String job_code = secManageService.getExchangeMaterialJobCodeByEventCode(event_code);
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
