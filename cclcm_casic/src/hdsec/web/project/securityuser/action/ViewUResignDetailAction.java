package hdsec.web.project.securityuser.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.common.bm.model.BmRealUser;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.securityuser.model.ResignEvent;
import hdsec.web.project.user.model.SecUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 查看用户离职作业详情
 * 
 * @author yangjl 2015-6-10
 */
public class ViewUResignDetailAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String op = "";
	private ResignEvent event = null;
	private ApproveProcess process = null;
	private ProcessJob job = null;
	private List<ProcessRecord> recordList = null;
	// add by liuyaling
	private Integer listSize = null;
	private String opinion_all = "";
	private BmRealUser bmUser = null;
	private SecUser resignUser = null;

	// add ending

	public Integer getListSize() {
		return listSize;
	}

	public String getOpinion_all() {
		return opinion_all;
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

	public ResignEvent getEvent() {
		return event;
	}
	public BmRealUser getBmUser() {
		return bmUser;
	}

	public SecUser getResignUser() {
		return resignUser;
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
		event = securityUserService.getUResignEventByEventCode(event_code);

		// 从real_user、sec_user表中获取用户信息
				Map<String, Object> mm = new HashMap<String, Object>();
				mm.put("real_user_id", event.getUser_iidd());
				mm.put("info_type", 2);
				List<BmRealUser> usertemp = securityUserService.getUserInfoByUserIdAndInfoType(mm);
				if (usertemp.size() > 0) {
					bmUser = usertemp.get(0);
				} else {
					bmUser = null;
				}
				resignUser = userService.getSecUserByUid(event.getUser_iidd());
		
		if (event == null) {
			throw new Exception("查询的作业已经被删除");
		} else {
			String job_code = securityUserService.getResignJobCodeByEventCode(event_code);
			if (StringUtils.hasLength(job_code)) {
				job = basicService.getProcessJobByCode(job_code);
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);

				// add by yangjl
				listSize = recordList.size() - 1;
				for (int i = 1; i <= listSize; i++) {
					opinion_all = opinion_all + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion()
							+ "<br>审批人：" + recordList.get(i).getUser_name() + "<br>审批时间："
							+ recordList.get(i).getOp_time_str();
				}
				// add ending
			}
			return SUCCESS;
		}
	}
}
