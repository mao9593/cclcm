package hdsec.web.project.computer.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.computer.model.BorrowBookEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

public class AddBorrowBookEventAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
	private String contact_num = "";
	private Integer book_selv = null;
	private Date start_time = null;
	private Date end_time = null;
	private String place = "";
	private String judge = "";
	private String devieinfo = "";
	private String other_info = "";
	private String next_approver = "";// 下级审批人
	private String jobType = JobTypeEnum.BORROW_BOOK.getJobTypeCode();
	private Date sign_time = null;
	private String signname = "";
	private String job_other = JobTypeEnum.BORROW_BOOKOUT.getJobTypeCode();

	public void setSign_time(Date sign_time) {
		this.sign_time = sign_time;
	}

	public void setSignname(String signname) {
		this.signname = signname;
	}

	public void setDevieinfo(String devieinfo) {
		this.devieinfo = devieinfo;
	}

	public void setJudge(String judge) {
		this.judge = judge;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public void setContact_num(String contact_num) {
		this.contact_num = contact_num;
	}

	public void setBook_selv(Integer book_selv) {
		this.book_selv = book_selv;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public void setOther_info(String other_info) {
		this.other_info = other_info;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getJobType() {
		return jobType;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			if (place.equals("")) {
				throw new Exception("请填写出差地点!");
			}
			// 查询用户信息
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();

			BorrowBookEvent event = new BorrowBookEvent(event_code, user_iidd, dept_id, seclv_code, usage_code,
					project_code, summ, contact_num, start_time, end_time, book_selv, place, other_info, judge,
					devieinfo, sign_time, signname);

			if (place.equals("公司内")) {
				event.setJobType(JobTypeEnum.valueOf(jobType));
			} else {
				event.setJobType(JobTypeEnum.valueOf(job_other));
			}
			computerService.addBorrowBookEvent(event, next_approver);
			insertCommonLog("添加用户申请笔记本借用[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_BORROWBOOK_" + System.currentTimeMillis();
			return SUCCESS;
		}
	}
}