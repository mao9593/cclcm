package hdsec.web.project.secactivity.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.common.util.PrefixFilenameFilter;
import hdsec.web.project.secactivity.model.UserSecActiEvent;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.UserSecurity;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 添加涉密活动申请
 * 
 * @author gj
 * 
 */
public class AddUserSecActiEventAction extends SecuActiBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
	private String apply_type = ""; // 申请类型
	private String contact = ""; // 联系方式
	private Integer secret_type = 0; // 活动类型
	private String name = ""; // 活动名称
	private String place = ""; // 活动地点
	private String secret_leader = ""; // 活动保密主管领导
	private String secret_director = ""; // 活动保密负责人
	private Integer file_num = null;// 上传方案个数
	private String file_list = ""; // 上传方案文件
	private String his_job_code = ""; // 包含改作业的历史任务列表
	private Date start_time = null; // 活动开始时间
	private Date end_time = null; // 活动结束时间
	private String next_approver = "";// 下级审批人
	private String storepath = "";// 文档存储路径
	private Integer act_selv = null;// 会议密级
	private String sponsor = "";// 主办单位
	private String organizer = "";// 承办单位
	private String act_dept_id = "";// 会务承担单位或部门ID
	private String act_dept_name = "";// 会务承担单位或部门
	private String act_user = "";// 参会人员
	private List<UserSecActiEvent> eventList = null;
	private final String jobType = JobTypeEnum.USERSEC_ACTIVITY.getJobTypeCode();
	private List<BurnFile> fileList = null;

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public List<BurnFile> getFileList() {
		return fileList;
	}

	public String getStorepath() {
		return storepath;
	}

	public Date getStart_time() {
		return start_time;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public Date getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getUsage_code() {
		return usage_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getApply_type() {
		return apply_type;
	}

	public void setApply_type(String apply_type) {
		this.apply_type = apply_type;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getJobType() {
		return jobType;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public Integer getSecret_type() {
		return secret_type;
	}

	public void setSecret_type(Integer secret_type) {
		this.secret_type = secret_type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getSecret_leader() {
		return secret_leader;
	}

	public void setSecret_leader(String secret_leader) {
		this.secret_leader = secret_leader;
	}

	public String getSecret_director() {
		return secret_director;
	}

	public void setSecret_director(String secret_director) {
		this.secret_director = secret_director;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public String getProject_code() {
		return project_code;
	}

	public String getHis_job_code() {
		return his_job_code;
	}

	public List<UserSecActiEvent> getEventList() {
		return eventList;
	}

	public Integer getAct_selv() {
		return act_selv;
	}

	public void setAct_selv(Integer act_selv) {
		this.act_selv = act_selv;
	}

	public String getSponsor() {
		return sponsor;
	}

	public void setSponsor(String sponsor) {
		this.sponsor = sponsor;
	}

	public String getOrganizer() {
		return organizer;
	}

	public void setOrganizer(String organizer) {
		this.organizer = organizer;
	}

	public String getAct_dept_id() {
		return act_dept_id;
	}

	public void setAct_dept_id(String act_dept_id) {
		this.act_dept_id = act_dept_id;
	}

	public String getAct_dept_name() {
		return act_dept_name;
	}

	public void setAct_dept_name(String act_dept_name) {
		this.act_dept_name = act_dept_name;
	}

	public String getAct_user() {
		return act_user;
	}

	public void setAct_user(String act_user) {
		this.act_user = act_user;
	}

	// 获取所有密级
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageList();
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	public List<UserSecurity> getSecurityList() {
		return userService.getSecurityList();
	}

	private void diskWritenCompleted(File source) throws InterruptedException {
		long size = source.length();
		Thread.sleep(2000);
		long size1 = source.length();

		while (size < size1) {
			Thread.sleep(2000);
			size = size1;
			size1 = source.length();
		}
	}

	protected void moveFile(File source, String destPath, String fileName) throws Exception {
		if (!source.isFile()) {
			throw new Exception("Source File[" + source.getName() + "] is not a file.");
		}
		if (!StringUtils.hasLength(destPath.trim())) {
			throw new Exception("The target path of copy action is blank.");
		}
		try {
			File path = new File(destPath);
			if (!path.exists()) {
				logger.debug("path[" + path + "] does not exsit, create it.");
				path.mkdirs();
			}
			if (path.canWrite()) {
				// 等待文件完成写入磁盘
				diskWritenCompleted(source);
				if (source.renameTo(new File(destPath + File.separator + fileName))) {
					logger.info("remove file[" + source.getName() + "] successfully.");
				} else {
					throw new Exception("remove file[" + source.getName() + "] fail.");
				}
			} else {
				throw new Exception("The target path of remove action is not writable");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	private void handleFileList() throws Exception {
		// 从配置文件中读取涉密活动文件的存储路径
		String storePath = BMPropertyUtil.getSecActivityStrorePath();
		// 在临时路径后加上用户ID
		String tempPath = storePath + File.separator + getCurUser().getUser_iidd();
		File path = new File(tempPath);
		PrefixFilenameFilter filenameFilter = new PrefixFilenameFilter(event_code + "-");
		if (path.isDirectory()) {
			File[] files = path.listFiles(filenameFilter);
			file_num = files.length;
			for (File file : files) {
				String fileName = file.getName();
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
			// 查询变更用户信息
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			handleFileList();
			UserSecActiEvent event = new UserSecActiEvent(user_iidd, dept_id, event_code, seclv_code, usage_code,
					project_code, summ, apply_type, contact, secret_type, name, place, secret_leader, secret_director,
					file_num, file_list, his_job_code, start_time, end_time, act_selv, sponsor, organizer, act_dept_id,
					act_dept_name, act_user);
			event.setJobType(JobTypeEnum.valueOf(jobType));
			secactivityservice.AddUserSecActiEvent(event, next_approver);
			insertCommonLog("添加用户涉密活动申请作业[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_SECACTIVITY_" + System.currentTimeMillis();
			return SUCCESS;
		}
	}
}
