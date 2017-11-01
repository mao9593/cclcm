package hdsec.web.project.copy.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.common.util.StringUtil;
import hdsec.web.project.copy.model.CopyEvent;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 添加外来文件复印任务申请
 * 
 * @author lixiang
 * 
 */
public class AddCopyProcessJobByEnterAction extends CopyBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_ids = "";
	private Integer seclv_code = null;
	private String submit = "N";
	private String comment = "";
	private String next_approver = "";
	private JobTypeEnum jobType = null;
	private final SecLevel seclv = null;
	private final ApproveProcess process = null;
	private final List<ApproverUser> userList = null;
	private String chkResult = "";
	private List<CopyEvent> eventList;
	private String usage_code = "";
	private String _chk = "";
	private Integer highest_seclv = null;
	private String cycle_type = "";
	private String output_dept_name = "";
	private String output_user_name = "";

	public String get_chk() {
		return _chk;
	}

	public void set_chk(String _chk) {
		this._chk = _chk.replaceAll(" ", "");
	}

	public List<SecLevel> getSeclvList() {
		return userService.getCopySecLevelByUser(getCurUser().getUser_iidd());
	}

	public List<CopyEvent> getEventList() {
		return eventList;
	}

	public void setEvent_ids(String event_ids) {
		this.event_ids = event_ids.replaceAll(" ", "");
	}

	public String getEvent_ids() {
		return event_ids;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = JobTypeEnum.valueOf(jobType);
	}

	public SecLevel getSeclv() {
		return seclv;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public String getChkResult() {
		return chkResult;
	}

	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}

	public List<ApproverUser> getUserList() {
		return userList;
	}

	public String getUsage_code() {
		return usage_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public void setCycle_type(String cycle_type) {
		this.cycle_type = cycle_type;
	}

	public String getOutput_dept_name() {
		return output_dept_name;
	}

	public void setOutput_dept_name(String output_dept_name) {
		this.output_dept_name = output_dept_name;
	}

	public String getOutput_user_name() {
		return output_user_name;
	}

	public void setOutput_user_name(String output_user_name) {
		this.output_user_name = output_user_name;
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageListByModule("COPY");
	}

	public int getHighest_seclv() {
		return highest_seclv;
	}

	@Override
	public String executeFunction() throws Exception {

		if (submit.equalsIgnoreCase("Y")) {
			try {
				copyService.addProcessJobByEnter(StringUtil.stringArrayToList(event_ids.split(":")), getCurUser()
						.getUser_iidd(), getCurUser().getDept_id(), seclv_code, cycle_type, jobType, next_approver,
						output_dept_name, output_user_name, comment, usage_code);
				insertCommonLog("提交" + jobType.getJobTypeName() + "申请" + event_ids);
			} catch (Exception e) {
				logger.error(e.getMessage());
				setChkResult("添加审批流程出现异常");
			}
			setChkResult("ok");
			return "ok";
		} else {
			if (StringUtils.hasLength(event_ids)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("submitable", true);
				map.put("event_ids", StringUtil.stringArrayToList(event_ids.split(":")));
				eventList = copyService.getCopyEventList(map);

				List<Integer> seclvCodeList = new ArrayList<Integer>();
				for (CopyEvent event : eventList) {
					seclvCodeList.add(event.getSeclv_code());
				}
				highest_seclv = basicService.getHighestSeclvByCodeList(seclvCodeList).getSeclv_rank();
			} else {
				throw new Exception("参数错误，没有文件编号");
			}
			return SUCCESS;
		}
	}

}