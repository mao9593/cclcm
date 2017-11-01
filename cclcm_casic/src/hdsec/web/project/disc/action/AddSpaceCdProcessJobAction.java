package hdsec.web.project.disc.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.common.util.StringUtil;
import hdsec.web.project.disc.model.EntitySpaceCD;
import hdsec.web.project.disc.model.SpaceCDEvent;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 添加空白盘流程任务申请
 * 
 * @author zp
 * 
 */
public class AddSpaceCdProcessJobAction extends DiscBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_ids = "";
	private String submit = "N";
	private String comment = "";
	private JobTypeEnum jobType = null;
	private SecLevel seclv = null;
	private final ApproveProcess process = null;
	private final List<ApproverUser> userList = null;
	private List<EntitySpaceCD> items = null;
	private String chkResult = "";
	private String usage_code = "";
	private String file_seclvs = "";
	private Integer seclv_code = null;
	private String next_approver = "";
	private List<SpaceCDEvent> eventList = null;
	private Integer highest_seclv = 10000;

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public String getSubmit() {
		return submit;
	}

	public String getComment() {
		return comment;
	}

	public String getFile_seclvs() {
		return file_seclvs;
	}

	public void setSeclv(SecLevel seclv) {
		this.seclv = seclv;
	}

	public String getEvent_ids() {
		return event_ids;
	}

	public void setEvent_ids(String event_ids) {
		this.event_ids = event_ids;
	}

	public List<EntitySpaceCD> getItems() {
		return items;
	}

	public void setItems(List<EntitySpaceCD> items) {
		this.items = items;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getBurnSecLevelByUser(getCurUser().getUser_iidd());
	}

	public void setFile_seclvs(String file_seclvs) {
		this.file_seclvs = file_seclvs;
	}

	public void setSubmit(String submit) {
		this.submit = submit;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public List<SpaceCDEvent> getEventList() {
		return eventList;
	}

	public void setEventList(List<SpaceCDEvent> eventList) {
		this.eventList = eventList;
	}

	public Integer getHighest_seclv() {
		return highest_seclv;
	}

	public void setHighest_seclv(Integer highest_seclv) {
		this.highest_seclv = highest_seclv;
	}

	public boolean getIsKeywordEnable() {
		return basicService.isKeywordEnable();
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageListByModule("BURN");
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	/*
	 * private Map<String, String> getFileSeclvList() { if (StringUtils.hasLength(file_seclvs)) { Map<String, String>
	 * map = new HashMap<String, String>(); for (String item : file_seclvs.split(",")) { if (item.indexOf(":") != -1) {
	 * map.put(item.substring(0, item.indexOf(":")), item.substring(item.indexOf(":") + 1)); } } return map; } return
	 * Collections.emptyMap(); }
	 */
	@Override
	public String executeFunction() throws Exception {
		System.out.print("jobtype" + jobType);
		if (submit.equalsIgnoreCase("Y")) {
			try {
				discService.addSpaceCdProcessJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(), seclv_code,
						jobType, next_approver, comment, StringUtil.stringArrayToList(event_ids.split(":")),
						usage_code, "");
				insertCommonLog("提交" + jobType.getJobTypeName());
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
				eventList = discService.getSpaceCDEventList(map);
				for (SpaceCDEvent item : eventList) {
					SecLevel seclevel = userService.getSecLevelByCode(item.getSeclv_code());
					if (highest_seclv > seclevel.getSeclv_rank()) {
						highest_seclv = seclevel.getSeclv_rank();
					}
				}

				/* items = discService.getDeptEntitySpaceCdList(map); */
			} else {
				throw new Exception("参数错误，没有业务作业ID");
			}
			return SUCCESS;
		}
	}

}