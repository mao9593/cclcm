package hdsec.web.project.secplace.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PrefixFilenameFilter;
import hdsec.web.project.secplace.model.SecplaceEvent;
import hdsec.web.project.user.model.SecLevel;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 涉密场所申请
 * 
 * @author liuyaling 2015-6-11
 */
public class AddSecplaceEventAction extends SecplaceBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String event_code = "";// 事件号（唯一）
	private String secplace_name = "";// 场所名称
	private String secplace_code = "";// 场所编号（唯一）
	private String secplace_location = "";// 物理位置
	private String duty_user_id = "";// 责任人id
	private String duty_user_name = "";// 责任人姓名
	private String duty_dept_id = "";// 责任部门id
	private String duty_dept_name = "";// 责任人名称
	private int secplace_type;// 要害类别（1：部门，2：部位）
	private String conf_code = "";// 保密编号
	private int seclv_code;// 要害密级
	private String user_number = "";// 涉密人员数量
	private Date found_time = null;// 成立时间
	private String secplace_application = "";// 用途
	private String people_protect = "";// 人防措施
	private String physical_protect = "";// 物防措施
	private String technology_protect = "";// 技防措施
	private String leader_id = "";// 主管领导
	private int file_num;// 文件数量
	private String file_list = "";// 文件列表
	private String user_iidd = "";// 申请人ID
	private String dept_id = "";// 申请部门ID
	private Date apply_time = null;// 录入时间
	private String summ = "";// 备注
	private Integer user_number1 = null;// 重要涉密人员数量
	private Integer user_number2 = null;// 一般涉密人员数量
	private Integer user_number_sum = null;//涉密人员总数
	private String jobType = "";
	private String next_approver = "";// 下级审批人

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getSecplace_name() {
		return secplace_name;
	}

	public void setSecplace_name(String secplace_name) {
		this.secplace_name = secplace_name;
	}

	public String getSecplace_code() {
		return secplace_code;
	}

	public void setSecplace_code(String secplace_code) {
		this.secplace_code = secplace_code;
	}

	public String getSecplace_location() {
		return secplace_location;
	}

	public void setSecplace_location(String secplace_location) {
		this.secplace_location = secplace_location;
	}

	public String getDuty_user_id() {
		return duty_user_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getDuty_dept_name() {
		return duty_dept_name;
	}

	public void setDuty_dept_name(String duty_dept_name) {
		this.duty_dept_name = duty_dept_name;
	}

	public int getSecplace_type() {
		return secplace_type;
	}

	public void setSecplace_type(int secplace_type) {
		this.secplace_type = secplace_type;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public int getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(int seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getUser_number() {
		return user_number;
	}

	public void setUser_number(String user_number) {
		user_number = Constants.COMMON_SEPARATOR + user_number1.toString() + Constants.COMMON_SEPARATOR 
				+ user_number2.toString() + Constants.COMMON_SEPARATOR + user_number_sum.toString()
				+ Constants.COMMON_SEPARATOR + "end" + Constants.COMMON_SEPARATOR;
		this.user_number = user_number;
	}
	
	public Date getFound_time() {
		return found_time;
	}

	public void setFound_time(Date found_time) {
		this.found_time = found_time;
	}

	public String getSecplace_application() {
		return secplace_application;
	}

	public void setSecplace_application(String secplace_application) {
		this.secplace_application = secplace_application;
	}

	public String getPeople_protect() {
		return people_protect;
	}

	public void setPeople_protect(String people_protect) {
		this.people_protect = people_protect;
	}

	public String getPhysical_protect() {
		return physical_protect;
	}

	public void setPhysical_protect(String physical_protect) {
		this.physical_protect = physical_protect;
	}

	public String getTechnology_protect() {
		return technology_protect;
	}

	public void setTechnology_protect(String technology_protect) {
		this.technology_protect = technology_protect;
	}

	public String getLeader_id() {
		return leader_id;
	}

	public void setLeader_id(String leader_id) {
		this.leader_id = leader_id;
	}

	public int getFile_num() {
		return file_num;
	}

	public void setFile_num(int file_num) {
		this.file_num = file_num;
	}

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
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

	public Date getApply_time() {
		return apply_time;
	}

	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
	}
	
	public Integer getUser_number1() {
		String[] user_numbers = user_number.split("\\|");
		user_number1 = Integer.parseInt(user_numbers[0]);
		return user_number1;
	}

	public void setUser_number1(Integer user_number1) {
		this.user_number1 = user_number1;
	}

	public Integer getUser_number2() {
		String[] user_numbers = user_number.split("\\|");
		user_number2 = Integer.parseInt(user_numbers[1]);
		return user_number2;
	}

	public void setUser_number2(Integer user_number2) {
		this.user_number2 = user_number2;
	}

	public Integer getUser_number_sum() {
		user_number_sum = user_number1 + user_number2;
		return user_number_sum;
	}

	public void setUser_number_sum(Integer user_number_sum) {
		user_number_sum = user_number1 + user_number2;
		this.user_number_sum = user_number_sum;
	}
	
	private void handleFileList() throws Exception {
		// 从配置文件中读取上传的安全保密措施文件的临时存储路径和正式存储路径
		String storePath = BMPropertyUtil.getSecplaceFileStorePath();
		// 在临时路径后加上用户ID
		String tempPath = storePath + File.separator + getCurUser().getUser_iidd();
		File path = new File(tempPath);
		PrefixFilenameFilter filenameFilter = new PrefixFilenameFilter(event_code + "-");
		if (path.isDirectory()) {
			File[] files = path.listFiles(filenameFilter);
			file_num = files.length;
			for (File file : files) {
				String fileName = file.getName();
				// 去掉前面的event_code
				// fileName = fileName.substring(fileName.indexOf("-") + 1);
				// 文件名
				fileName = fileName.substring(fileName.indexOf("-") + 1);

				try {
					moveFile(file, storePath + File.separator + event_code, fileName);
					file_list = file_list + fileName + CCLCMConstants.DEVIDE_SYMBOL;
					logger.debug("copy file:" + fileName);
				} finally {
					file.delete();
				}
			}
			if (file_list.endsWith(CCLCMConstants.DEVIDE_SYMBOL)) {
				file_list = file_list.substring(0, file_list.length() - 1);
			}
		}
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			// 通过secplace_code查重
			int count = 0;
			count = secplaceService.getSecplaceCountBySecplaceCode(secplace_code);
			if (count > 0) {
				String errorLog = "要害部门部位编号[" + secplace_code + "]已存在，请修改后重新申请！";
				throw new Exception(errorLog);
			}

			String user_iidd = getCurUser().getUser_iidd();
			String user_name = getCurUser().getUser_name();
			String dept_id = getCurUser().getDept_id();
			handleFileList();
			apply_time = new Date();
			SecplaceEvent event = new SecplaceEvent(event_code, seclv_code, user_iidd, dept_id, apply_time,
					secplace_name, secplace_code, secplace_location, duty_user_id, duty_user_name, duty_dept_id,
					duty_dept_name, secplace_type, conf_code, user_number, found_time, secplace_application,
					people_protect, physical_protect, technology_protect, leader_id, file_num, file_list, summ, "");
			String jobType_code = "EVENT_SECPLACE";
			JobTypeEnum jobType = JobTypeEnum.valueOf(jobType_code);
			event.setJobType(jobType);
			secplaceService.addSecplaceEvent(event, next_approver);// 向数据库添加event

			insertCommonLog("添加外来人员进入涉密场所申请[" + event_code + "]");

			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_EVENTSECPLACE_" + System.currentTimeMillis();
			return SUCCESS;
		}
	}
}