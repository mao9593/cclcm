package hdsec.web.project.input.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.common.util.MD5;
import hdsec.web.project.common.util.PrefixFilenameFilter;
import hdsec.web.project.input.model.InputEvent;
import hdsec.web.project.user.model.SecLevel;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 添加电子文件导入作业
 * 
 * @author guoxh 2016-9-24
 */
public class AddInputEventAction extends InputBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 电子文件导入作业流水号
	private String user_iidd = "";// 申请用户ID
	private String dept_id = "";// 申请用户部门
	private Integer seclv_code = null;// 作业密级
	private String file_seclevel = "";// 文件密级
	private String file_list = "";// 文件列表
	private Integer file_num = null;// 文件数量
	private Date apply_time = null;// 作业添加时间
	private String summ = "";// 备注
	private String user_name = "";// 申请用户名
	private String dept_name = "";// 申请用户部门名称
	private String seclv_name = "";// 密级名称
	private String message_usage = "";// 信息用途
	private String personal = "";// 单位个人
	private String address = "";// 网上/邮件地址
	private String med_type = "";// 输入介质类型（1、U盘2、光盘3、存储卡、4其他）
	private Date operate_time = null;// 操作时间
	private String cd_num = "";// 中转盘号
	private String internet_num = "";// 互联网盘号

	public String getCd_num() {
		return cd_num;
	}

	public void setCd_num(String cd_num) {
		this.cd_num = cd_num;
	}

	public String getInternet_num() {
		return internet_num;
	}

	public void setInternet_num(String internet_num) {
		this.internet_num = internet_num;
	}

	// private Integer input_state = null;// 输入状态
	// private List<SysMsgUsage> msgUsageList = null;
	private String update = "N";
	private String flag = "N";
	private String messages = "";
	private JobTypeEnum jobType = null;// 任务类型
	private String usage_code = "";// 用途编号
	private String project_code = "";// 所属项目编号
	private String next_approver = "";// 下级审批人
	private String info = ""; // 上传文件信息（文件名和密级）
	private List<InputEvent> eventList = null;

	public List<InputEvent> getEventList() {
		return eventList;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getChkResult() {
		return chkResult;
	}

	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}

	private String chkResult = "";

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getImportSecLevelByUser(getCurUser().getUser_iidd());
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageListByModule("INPUT");
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
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

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getFile_seclevel() {
		return file_seclevel;
	}

	public void setFile_seclevel(String file_seclevel) {
		this.file_seclevel = file_seclevel;
	}

	public Integer getFile_num() {
		return file_num;
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public Date getApply_time() {
		return apply_time;
	}

	public void setApply_time(Date apply_time) {
		this.apply_time = apply_time;
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

	public String getSeclv_name() {
		return seclv_name;
	}

	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}

	public String getMessage_usage() {
		return message_usage;
	}

	public void setMessage_usage(String message_usage) {
		this.message_usage = message_usage;
	}

	public String getPersonal() {
		return personal;
	}

	public void setPersonal(String personal) {
		this.personal = personal;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMed_type() {
		return med_type;
	}

	public void setMed_type(String med_type) {
		this.med_type = med_type;
	}

	public Date getOperate_time() {
		return operate_time;
	}

	public void setOperate_time(Date operate_time) {
		this.operate_time = operate_time;
	}

	/*
	 * public Integer getInput_state() { return input_state; }
	 * 
	 * public void setInput_state(Integer input_state) { this.input_state = input_state; }
	 */

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	/*
	 * public List<SysMsgUsage> getMsgUsageList() { return msgUsageList; }
	 */

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public void setJobType(JobTypeEnum jobType) {
		this.jobType = jobType;
	}

	public String getUsage_code() {
		return usage_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	private void handleFileList() throws Exception {

		String tempPath = PropertyUtil.getInputFileTempPath();
		String storePath = PropertyUtil.getInputFileStorePath();
		// 在临时路径后加上用户ID
		tempPath = tempPath + File.separator + getCurUser().getUser_iidd();
		File path = new File(tempPath);
		PrefixFilenameFilter filenameFilter = new PrefixFilenameFilter(event_code + "-");
		if (path.isDirectory()) {
			File[] files = path.listFiles(filenameFilter);
			file_num = files.length;
			for (File file : files) {
				String fileName = file.getName();
				fileName = fileName.substring(fileName.indexOf("-") + 1);
				String[] filesInfo = info.split("::");
				String oneFileName;
				String seclv_file = "";
				for (String oneFileInfo : filesInfo) {
					oneFileName = oneFileInfo.split(" __ ")[0];
					if (fileName.equals(oneFileName)) {
						// fileName = oneFileName;
						seclv_file = oneFileInfo.split(" __ ")[1];
					}
				}
				try {
					// copyFile(file, storePath + File.separator + event_code,
					// fileName);
					moveFile(file, storePath + File.separator + event_code, MD5.getStringMD5(fileName));
					file_list = file_list + fileName + CCLCMConstants.DEVIDE_SYMBOL;
					file_seclevel = file_seclevel + seclv_file + CCLCMConstants.DEVIDE_SYMBOL;
					logger.debug("copy file:" + fileName);
				} finally {
					file.delete();
				}
			}
			if (file_list.endsWith(CCLCMConstants.DEVIDE_SYMBOL)) {
				file_list = file_list.substring(0, file_list.length() - 1);
			}
			if (file_seclevel.endsWith(CCLCMConstants.DEVIDE_SYMBOL)) {
				file_seclevel = file_seclevel.substring(0, file_seclevel.length() - 1);
			}
		}
	}

	@Override
	public String executeFunction() throws Exception {

		if (StringUtils.hasLength(event_code)) {
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			handleFileList();
			InputEvent event = new InputEvent(event_code, personal, file_seclevel, address, file_num,
					med_type.substring(med_type.indexOf(",") + 1), file_list, jobType, user_iidd, dept_id, seclv_code,
					usage_code, project_code, summ.substring(2), cd_num.substring(2), internet_num.substring(2));
			String jobType_code = "MSG_INPUT";
			JobTypeEnum jobType = JobTypeEnum.valueOf(jobType_code);
			event.setJobType(jobType);
			event.setInput_state(1);
			inputService.addInputEvent(event, next_approver);

			insertCommonLog("添加电子文件导入申请，文件列表[" + file_list + "]");

			return "true";
		} else {
			event_code = getCurUser().getUser_iidd() + "_INPUT_" + System.currentTimeMillis();

			return SUCCESS;
		}
	}

}
