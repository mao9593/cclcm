package hdsec.web.project.securityuser.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人审批流程追踪
 * 
 * @author gaoximin 2015-9-24
 */
public class ManageApproveListAction extends SecurityUserBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private String user_iidd = "";// 发起人ID
	private String user_name = "";// 发起人
	private String dept_id = "";// 发起部门ID
	private String jobType_code = "";
	private Integer seclv_code = null;
	private String dept_name = "";
	private List<ProcessJob> jobList = null;
	List<JobTypeEnum> useredJobType = null;// 在用流程类型
	private String file_src = "";
	private List<String> allOper = null;
	private String researchFlag = "N";

	public String getStartTime_str() {
		return startTime == null ? "" : sdf.format(startTime);
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getEndTime_str() {
		return endTime == null ? "" : sdf.format(endTime);
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getJobType_code() {
		return jobType_code;
	}

	public void setJobType_code(String jobType_code) {
		this.jobType_code = jobType_code;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public List<ProcessJob> getJobList() {
		return jobList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<JobTypeEnum> getUseredJobType() {
		return JobTypeEnum.getUsedJobTypeList();
	}

	public boolean hasPermission(String permission) {
		// 如果用户的权限列表中包含该权限字符串，则返回true
		if (allOper != null && allOper.contains(permission)) {
			return true;
		} else if (allOper != null && allOper.contains("/" + permission)) {
			return true;
		} else {
			// 数据库操作表中没有此操作记录，默认返回true;
			return false;
		}
	}

	public String getFile_src() {
		return file_src;
	}

	public void setFile_src(String file_src) {
		this.file_src = file_src;
	}

	public String getResearchFlag() {
		return researchFlag;
	}

	public void setResearchFlag(String researchFlag) {
		this.researchFlag = researchFlag;
	}

	@Override
	public String executeFunction() throws Exception {
		if (researchFlag.equals("Y")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user_iidd", user_iidd);
			map.put("dept_id", dept_id);
			map.put("seclv_code", seclv_code);
			map.put("startTime", startTime);
			map.put("endTime", endTime);
			map.put("job_status", job_status);
			map.put("jobType_code", jobType_code);

			List<ProcessJob> tempList = null;
			jobList = new ArrayList<ProcessJob>();
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BURN_REMAIN,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BURN_SEND,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BURN_FILE,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.PRINT_REMAIN, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PRINT_SEND,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PRINT_FILE,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.COPY, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.COPY_SEND,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.COPY_FILE,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.OUTCOPY_REMAIN, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.OUTCOPY_SEND, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.OUTCOPY_FILE, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.LEADIN, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.DEVICE, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.TRANSFER,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BORROW, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.DELAY, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.SEND, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.FILE, map);
			jobList.addAll(tempList);
			tempList = securityUserService
					.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.DESTROY, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.DELAY_CD,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService
					.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.SEND_CD, map);
			jobList.addAll(tempList);
			tempList = securityUserService
					.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.FILE_CD, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.DESTROY_CD,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.DELAY_PAPER,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.SEND_PAPER,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.FILE_PAPER,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.DESTROY_PAPER, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.DESTROY_DEVICE, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.CHANGE, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.SCAN_LEADIN,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.SENDES_PAPER, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.SENDES_CD,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.CARRYOUT,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.CARRYOUT_PAPER, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.CARRYOUT_CD,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.MODIFY_SECLV, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.SPACECD_BORROW, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PURCHASE,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.WASTE, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PROPERTYOUT,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.PROPERTYCHANGE, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.USERSECLV_ADD, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.USERSECLV_CHANGE, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.USERSEC_ACTIVITY, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.SECUSER_ABROAD, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.SECUSER_ENTRUST, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.RESIGN, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.ENTER_SECPLACE, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_SECPLACE, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.OUT_EXCHANGE, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_INTCOM, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_SINCOM, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_CHGCOM, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_DESCOM, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.USER_INFO,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_REPORT, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_REPORT2, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_REPORT3, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_DEPTREPORT, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_INTRAPUBL, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_INTERPUBL, map);
			jobList.addAll(tempList);
			tempList = securityUserService
					.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.FIELDIN, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.INFO_DEVICE,
					map);
			jobList.addAll(tempList);

			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.INFO_OTHER,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.DEVICE_CHANGE, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.CHANGE_OTHER, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.DEVICE_DES,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_REPCOM, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_REINSTALL, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_QUITINT, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_USBKEY, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.INTER_EMAIL,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.SEC_CHECK,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.FILEOUTMAKE,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_OPENPORT, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.EVENT_LOCALPRINTER, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.MATERIAL,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.EXHIBITION,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PUNISH_DEPT,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.PUNISH_SECCHECK, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.PUNISH_RECTIFY, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.PAPER_RESEARCH, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.PAPER_OTHERS, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.PAPERPATENT,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BORROW_BOOK,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.BORROW_BOOKOUT, map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BOOK_CHANGE,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BOOK_REPAIR,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(), JobTypeEnum.BOOK_DES,
					map);
			jobList.addAll(tempList);
			tempList = securityUserService.getApprovedJobByUserId(getCurUser().getUser_iidd(),
					JobTypeEnum.BOOK_REINSTALL, map);
			jobList.addAll(tempList);

			// 判断刻录是否与NAS集成
			String permission = "burn/managenasburnevent.action";
			if (hasPermission(permission)) {
				file_src = "nas";
			}
			researchFlag = "Y";
		}
		return SUCCESS;
	}
}
