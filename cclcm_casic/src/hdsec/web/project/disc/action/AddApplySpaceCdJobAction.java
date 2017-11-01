package hdsec.web.project.disc.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.disc.model.EntitySpaceCD;
import hdsec.web.project.disc.model.SpaceCDEvent;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.Collections;
import java.util.Date;
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
public class AddApplySpaceCdJobAction extends DiscBaseAction {

	private static final long serialVersionUID = 1L;
	private String ids = "";
	private String submit = "N";
	private String comment = "";
	private SecLevel seclv = null;
	private final ApproveProcess process = null;
	private final List<ApproverUser> userList = null;
	private List<EntitySpaceCD> items = null;
	private String chkResult = "";
	private String usage_code = "";
	private String file_seclvs = "";
	private String next_approver = "";
	private String event_code = "";
	private Integer seclv_code = null;// 作业密级
	private String summ = "";// 备注
	private Date apply_time = null;// 申请时间
	private String cd_type = "";// 光盘类型（CD、DVD）
	private String spacecd_type = "";// 空白盘类型，0空白盘，1中转盘
	private int enter_num = 0;// 份数
	private List<SpaceCDEvent> eventList = null;
	private final String jobType = JobTypeEnum.SPACECD_BORROW.getJobTypeCode();

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

	public String getIds() {
		return ids;
	}

	public String getJobType() {
		return jobType;
	}

	public void setIds(String ids) {
		this.ids = ids;
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

	public boolean getIsKeywordEnable() {
		return basicService.isKeywordEnable();
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageListByModule("BURN");
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public Date getApply_time() {
		return apply_time;
	}

	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}

	public String getCd_type() {
		return cd_type;
	}

	public void setCd_type(String cd_type) {
		this.cd_type = cd_type;
	}

	public String getSpacecd_type() {
		return spacecd_type;
	}

	public void setSpacecd_type(String spacecd_type) {
		this.spacecd_type = spacecd_type;
	}

	public int getEnter_num() {
		return enter_num;
	}

	public void setEnter_num(int enter_num) {
		this.enter_num = enter_num;
	}

	public List<SpaceCDEvent> getEventList() {
		return eventList;
	}

	public void setEventList(List<SpaceCDEvent> eventList) {
		this.eventList = eventList;
	}

	private Map<String, String> getFileSeclvList() {
		if (StringUtils.hasLength(file_seclvs)) {
			Map<String, String> map = new HashMap<String, String>();
			for (String item : file_seclvs.split(",")) {
				if (item.indexOf(":") != -1) {
					map.put(item.substring(0, item.indexOf(":")), item.substring(item.indexOf(":") + 1));
				}
			}
			return map;
		}
		return Collections.emptyMap();
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			String apply_user_iidd = getCurUser().getUser_iidd();
			String apply_dept_id = getCurUser().getDept_id();
			String scope_dept_id = getCurUser().getDept_id();
			String scope_dept_name = getCurUser().getDept_name();
			apply_time = new Date();
			SpaceCDEvent event = new SpaceCDEvent(event_code, apply_user_iidd, apply_dept_id, scope_dept_id,
					scope_dept_name, seclv_code, summ, apply_time, cd_type, spacecd_type, enter_num, 0);
			discService.addSpaceCDEvent(event);
			insertCommonLog("添加空白盘领用作业[" + event_code + "]");

		}
		event_code = getCurUser().getUser_iidd() + System.currentTimeMillis();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("apply_user_iidd", getCurUser().getUser_iidd());
		map.put("submitable", true);
		eventList = discService.getSpaceCDEventList(map);
		return SUCCESS;
	}
}