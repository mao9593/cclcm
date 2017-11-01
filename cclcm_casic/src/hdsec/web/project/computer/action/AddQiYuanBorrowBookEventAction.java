package hdsec.web.project.computer.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.computer.model.BorrowBookEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

public class AddQiYuanBorrowBookEventAction extends ComputerBaseAction {

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
	private String jobType = JobTypeEnum.QIYUAN_BORROW_BOOK.getJobTypeCode();
	private Date sign_time = null;
	private String signname = "";
	private String mobilephone_num = ""; // 七院-手机号
	private String disc_num = ""; // 七院-光盘编号
	private String using_place = ""; // 七院-使用地点
	private String disc_filename = ""; // 七院-刻录文件名1
	private String disc_filename_2 = ""; // 七院-刻录文件名2
	private String disc_filename_3 = ""; // 七院-刻录文件名3
	private String disc_filename_4 = ""; // 七院-刻录文件名4
	private Integer discfile1_seclv_code = null; // 七院-刻录文件名1-密级
	private Integer discfile2_seclv_code = null; // 七院-刻录文件名2-密级
	private Integer discfile3_seclv_code = null; // 七院-刻录文件名3-密级
	private Integer discfile4_seclv_code = null; // 七院-刻录文件名4-密级
	private String security_methord = ""; // 七院保密防护措施

	public String getSecurity_methord() {
		return security_methord;
	}

	public void setSecurity_methord(String security_methord) {
		this.security_methord = security_methord;
	}

	public Integer getDiscfile4_seclv_code() {
		return discfile4_seclv_code;
	}

	public void setDiscfile4_seclv_code(Integer discfile4_seclv_code) {
		this.discfile4_seclv_code = discfile4_seclv_code;
	}

	public Integer getDiscfile3_seclv_code() {
		return discfile3_seclv_code;
	}

	public void setDiscfile3_seclv_code(Integer discfile3_seclv_code) {
		this.discfile3_seclv_code = discfile3_seclv_code;
	}

	public Integer getDiscfile2_seclv_code() {
		return discfile2_seclv_code;
	}

	public void setDiscfile2_seclv_code(Integer discfile2_seclv_code) {
		this.discfile2_seclv_code = discfile2_seclv_code;
	}

	public Integer getDiscfile1_seclv_code() {
		return discfile1_seclv_code;
	}

	public void setDiscfile1_seclv_code(Integer discfile1_seclv_code) {
		this.discfile1_seclv_code = discfile1_seclv_code;
	}

	public String getDisc_filename_4() {
		return disc_filename_4;
	}

	public void setDisc_filename_4(String disc_filename_4) {
		this.disc_filename_4 = disc_filename_4;
	}

	public String getDisc_filename_3() {
		return disc_filename_3;
	}

	public void setDisc_filename_3(String disc_filename_3) {
		this.disc_filename_3 = disc_filename_3;
	}

	public String getDisc_filename_2() {
		return disc_filename_2;
	}

	public void setDisc_filename_2(String disc_filename_2) {
		this.disc_filename_2 = disc_filename_2;
	}

	public void setDisc_filename(String disc_filename) {
		this.disc_filename = disc_filename;
	}

	public String getUsing_place() {
		return using_place;
	}

	public void setUsing_place(String using_place) {
		this.using_place = using_place;
	}

	public String getDisc_num() {
		return disc_num;
	}

	public void setDisc_num(String disc_num) {
		this.disc_num = disc_num;
	}

	public String getMobilephone_num() {
		return mobilephone_num;
	}

	public void setMobilephone_num(String mobilephone_num) {
		this.mobilephone_num = mobilephone_num;
	}

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

			// 查询用户信息
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();

			BorrowBookEvent event = new BorrowBookEvent(event_code, user_iidd, dept_id, seclv_code, usage_code,
					project_code, summ, contact_num, start_time, end_time, book_selv, place, other_info, judge,
					devieinfo, sign_time, signname);

			// 七院笔记本借用表单添加：保密措施、光盘编号、刻录文件名、手机号等信息
			event.setSecurity_methord(security_methord);
			event.setMobilephone_num(mobilephone_num);
			event.setDisc_num(disc_num);
			String Temp_Disc_file = "";
			if (!disc_filename.equals("") && discfile1_seclv_code != null) {
				Temp_Disc_file = disc_filename + ","
						+ userService.getSecLevelByCode(discfile1_seclv_code).getSeclv_name();
			}
			if (!disc_filename_2.equals("") && discfile2_seclv_code != null) {
				Temp_Disc_file = Temp_Disc_file + "," + disc_filename_2 + ","
						+ userService.getSecLevelByCode(discfile2_seclv_code).getSeclv_name();
			}
			if (!disc_filename_3.equals("") && discfile3_seclv_code != null) {
				Temp_Disc_file = Temp_Disc_file + "," + disc_filename_3 + ","
						+ userService.getSecLevelByCode(discfile3_seclv_code).getSeclv_name();
			}
			if (!disc_filename_4.equals("") && discfile4_seclv_code != null) {
				Temp_Disc_file = Temp_Disc_file + "," + disc_filename_4 + ","
						+ userService.getSecLevelByCode(discfile4_seclv_code).getSeclv_name();
			}
			event.setDisc_file(Temp_Disc_file);
			event.setJobType(JobTypeEnum.valueOf(jobType));

			computerService.addBorrowBookEvent(event, next_approver);
			insertCommonLog("添加用户申请笔记本借用[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_QIYUANBORROWBOOK_" + System.currentTimeMillis();
			return SUCCESS;
		}
	}
}