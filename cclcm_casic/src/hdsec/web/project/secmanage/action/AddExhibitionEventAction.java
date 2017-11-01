package hdsec.web.project.secmanage.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.secmanage.model.ExhibitionEvent;
import hdsec.web.project.secmanage.model.FileListEvent;

import org.springframework.util.StringUtils;

public class AddExhibitionEventAction extends SecManageBaseAction {

	private static final long serialVersionUID = 1L;
	private String contact_num = "";
	private String exh_location = "";
	private String exh_type = "";
	private String exh_others = "";
	private String exh_reason = "";
	private String event_code = "";
	private String next_approver = "";
	private Integer seclv_code = null;
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
	private final String jobType = JobTypeEnum.EXHIBITION.getJobTypeCode();
	private FileListEvent file = null;

	public void setExh_others(String exh_others) {
		this.exh_others = exh_others;
	}

	public void setExh_type(String exh_type) {
		this.exh_type = exh_type;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public void setContact_num(String contact_num) {
		this.contact_num = contact_num;
	}

	public void setExh_location(String exh_location) {
		this.exh_location = exh_location;
	}

	public void setExh_reason(String exh_reason) {
		this.exh_reason = exh_reason;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();

			file = new FileListEvent();
			handleFileList(event_code, file, "");
			ExhibitionEvent event = new ExhibitionEvent(event_code, user_iidd, dept_id, seclv_code, usage_code,
					project_code, summ, contact_num, exh_location, exh_type, exh_others, exh_reason,
					file.getFile_list(), file.getFile_num());
			event.setJobType(JobTypeEnum.valueOf(jobType));
			secManageService.addExhibitionEvent(event, next_approver);
			insertCommonLog("添加展示/展览保密审查申请[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "EXHIBITION" + System.currentTimeMillis();
			return SUCCESS;
		}
	}

}