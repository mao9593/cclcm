package hdsec.web.project.secactivity.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.common.util.PrefixFilenameFilter;
import hdsec.web.project.secactivity.model.SecOutExchangeEvent;
import hdsec.web.project.secplace.model.EntityVisitor;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.UserSecurity;

import java.io.File;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 添加涉外交流保密工作
 * 
 * @author gj
 * 
 */
public class AddSecOutExchangeEventAction extends SecuActiBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
	private String company_info = "";// 来访单位
	private String reason = "";// 事由
	private String recept_user_iidd = "";// 接待人员ID
	private String receptionist = ""; // 接待人员
	private String exchange_info = "";// 业务交流范围
	private String reception = "";// 接待地点
	private Integer reception_sec = null;// 接待地点是否涉密
	private String visite_place = "";// 参观地点
	private Integer visite_sec = null;// 参观地点是否涉密
	private String material = "";// 提供资料
	private Integer material_sec = null;// 提供资料是否涉密
	private String sec_measure = "";// 保密错误
	private Date go_time = null; // 抵达时间
	private Date back_time = null; // 离所时间
	private Integer file_num = 0;// 附件数量
	private String file_list = ""; // 上传附件
	private String his_job_code = ""; // 包含改作业的历史任务列表
	private String next_approver = "";// 下级审批人
	private String storepath = "";// 文档存储路径
	private List<SecOutExchangeEvent> eventList = null;
	private String jobType = JobTypeEnum.OUT_EXCHANGE.getJobTypeCode();
	private List<BurnFile> fileList = null;
	private int visitor_num = 0;
	private String v_name = "";
	private String v_sex = "";
	private String v_nation = "";
	private String v_post = "";
	private String v_cercode = "";

	public String getV_name() {
		return v_name;
	}

	public void setV_name(String v_name) {
		this.v_name = v_name;
	}

	public String getV_sex() {
		return v_sex;
	}

	public void setV_sex(String v_sex) {
		this.v_sex = v_sex;
	}

	public String getV_nation() {
		return v_nation;
	}

	public void setV_nation(String v_nation) {
		this.v_nation = v_nation;
	}

	public String getV_post() {
		return v_post;
	}

	public void setV_post(String v_post) {
		this.v_post = v_post;
	}

	public String getV_cercode() {
		return v_cercode;
	}

	public void setV_cercode(String v_cercode) {
		this.v_cercode = v_cercode;
	}

	public int getVisitor_num() {
		return visitor_num;
	}

	public void setVisitor_num(int visitor_num) {
		this.visitor_num = visitor_num;
	}

	public String getHis_job_code() {
		return his_job_code;
	}

	public String getStorepath() {
		return storepath;
	}

	public List<BurnFile> getFileList() {
		return fileList;
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
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

	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
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

	public String getCompany_info() {
		return company_info;
	}

	public void setCompany_info(String company_info) {
		this.company_info = company_info;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRecept_user_iidd() {
		return recept_user_iidd;
	}

	public void setRecept_user_iidd(String recept_user_iidd) {
		this.recept_user_iidd = recept_user_iidd;
	}

	public String getReceptionist() {
		return receptionist;
	}

	public void setReceptionist(String receptionist) {
		this.receptionist = receptionist;
	}

	public String getExchange_info() {
		return exchange_info;
	}

	public void setExchange_info(String exchange_info) {
		this.exchange_info = exchange_info;
	}

	public String getReception() {
		return reception;
	}

	public void setReception(String reception) {
		this.reception = reception;
	}

	public Integer getReception_sec() {
		return reception_sec;
	}

	public void setReception_sec(Integer reception_sec) {
		this.reception_sec = reception_sec;
	}

	public String getVisite_place() {
		return visite_place;
	}

	public void setVisite_place(String visite_place) {
		this.visite_place = visite_place;
	}

	public Integer getVisite_sec() {
		return visite_sec;
	}

	public void setVisite_sec(Integer visite_sec) {
		this.visite_sec = visite_sec;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Integer getMaterial_sec() {
		return material_sec;
	}

	public void setMaterial_sec(Integer material_sec) {
		this.material_sec = material_sec;
	}

	public String getSec_measure() {
		return sec_measure;
	}

	public void setSec_measure(String sec_measure) {
		this.sec_measure = sec_measure;
	}

	public Date getGo_time() {
		return go_time;
	}

	public void setGo_time(Date go_time) {
		this.go_time = go_time;
	}

	public Date getBack_time() {
		return back_time;
	}

	public void setBack_time(Date back_time) {
		this.back_time = back_time;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public List<SecOutExchangeEvent> getEventList() {
		return eventList;
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
			SecOutExchangeEvent event = new SecOutExchangeEvent(user_iidd, dept_id, event_code, seclv_code, usage_code,
					project_code, summ, file_num, file_list, his_job_code, company_info, reason, recept_user_iidd,
					receptionist, exchange_info, reception, reception_sec, visite_place, visite_sec, material,
					material_sec, sec_measure, go_time, back_time);
			event.setJobType(JobTypeEnum.valueOf(jobType));
			secactivityservice.AddSecOutExchangeEvent(event, next_approver);

			String[] visitor_name = v_name.split(",");
			String[] visitor_sex = v_sex.split(",");
			String[] nationalist = v_nation.split(",");
			String[] visitor_post = v_post.split(",");
			String[] certificate_code = v_cercode.split(",");

			for (int i = 0; i < visitor_num; i++) {
				EntityVisitor visitor = new EntityVisitor(event_code, visitor_name[i], certificate_code[i],
						visitor_sex[i], nationalist[i], visitor_post[i]);

				secplaceService.addEntityVisitor(visitor);
			}
			insertCommonLog("添加涉外交流保密申请作业[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_OUTEXCHANGE_" + System.currentTimeMillis();
			return SUCCESS;
		}
	}
}