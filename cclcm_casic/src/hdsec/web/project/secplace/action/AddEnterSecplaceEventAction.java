package hdsec.web.project.secplace.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.bm.BMPropertyUtil;
import hdsec.web.project.common.util.PrefixFilenameFilter;
import hdsec.web.project.secplace.model.EnterSecplaceEvent;
import hdsec.web.project.secplace.model.EntitySecplace;
import hdsec.web.project.secplace.model.EntityVisitor;
import hdsec.web.project.user.model.SecLevel;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 外来人员进入涉密场所
 * 
 * @author liuyaling 2015-6-8
 */
public class AddEnterSecplaceEventAction extends SecplaceBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 事件编号
	private Date apply_time = null; // 申请时间
	private String secplace_code = "";// 场所编号
	private String visit_reason = "";// 事由
	private String accompany_id = "";// 陪同人员id
	private Date enter_time = null;// 进入时间
	private Date leave_time = null;// 离开时间
	private int seclv_code;// 审批单密级
	private EnterSecplaceEvent event = null;
	private EntityVisitor person = null;
	private Integer file_num = 0;// 文件数量
	private String file_list = "";// 文件列表
	private String info;// 上传文件信息（文件名和密级）
	private String next_approver = "";// 下级审批人
	private int visitor_num = 0;
	private String visitor_names = "";// 外来人员名称
	private String visitor_companys = "";
	private String certificate_types = "";
	private String certificate_codes = "";
	private String visitor_belongingss = "";
	private String security_arrangements = "";
	private final String jobType = JobTypeEnum.ENTER_SECPLACE.getJobTypeCode();
	private List<EntitySecplace> secplaceList = null;

	public void setVisitor_num(int visitor_num) {
		this.visitor_num = visitor_num;
	}

	public String getVisitor_companys() {
		return visitor_companys;
	}

	public void setVisitor_companys(String visitor_companys) {
		this.visitor_companys = visitor_companys;
	}

	public String getCertificate_types() {
		return certificate_types;
	}

	public void setCertificate_types(String certificate_types) {
		this.certificate_types = certificate_types;
	}

	public String getCertificate_codes() {
		return certificate_codes;
	}

	public void setCertificate_codes(String certificate_codes) {
		this.certificate_codes = certificate_codes;
	}

	public String getVisitor_belongingss() {
		return visitor_belongingss;
	}

	public void setVisitor_belongingss(String visitor_belongingss) {
		this.visitor_belongingss = visitor_belongingss;
	}

	public String getSecurity_arrangements() {
		return security_arrangements;
	}

	public void setSecurity_arrangements(String security_arrangements) {
		this.security_arrangements = security_arrangements;
	}

	public String getVisitor_names() {
		return visitor_names;
	}

	public void setVisitor_names(String visitor_names) {
		this.visitor_names = visitor_names;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public String getAccompany_id() {
		return accompany_id;
	}

	public void setAccompany_id(String accompany_id) {
		this.accompany_id = accompany_id;
	}

	public Integer getFile_num() {
		return file_num;
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public EntityVisitor getPerson() {
		return person;
	}

	public void setPerson(EntityVisitor person) {
		this.person = person;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getSecplace_code() {
		return secplace_code;
	}

	public void setSecplace_code(String secplace_code) {
		this.secplace_code = secplace_code;
	}

	public String getVisit_reason() {
		return visit_reason;
	}

	public void setVisit_reason(String visit_reason) {
		this.visit_reason = visit_reason;
	}

	public Date getEnter_time() {
		return enter_time;
	}

	public void setEnter_time(Date enter_time) {
		this.enter_time = enter_time;
	}

	public Date getLeave_time() {
		return leave_time;
	}

	public void setLeave_time(Date leave_time) {
		this.leave_time = leave_time;
	}

	public int getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(int seclv_code) {
		this.seclv_code = seclv_code;
	}

	public EnterSecplaceEvent getEvent() {
		return event;
	}

	public void setEvent(EnterSecplaceEvent event) {
		this.event = event;
	}

	public String getJobType() {
		return jobType;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<EntitySecplace> getSecplaceList() {
		return secplaceList;
	}

	private void handleFileList() throws Exception {
		// 从配置文件中读取上传的安全保密措施文件的临时存储路径和正式存储路径
		// String tempPath = PropertyUtil.getSecplaceFileTempPath();
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
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			handleFileList();
			apply_time = new Date();
			EnterSecplaceEvent event = new EnterSecplaceEvent(event_code, user_iidd, dept_id, apply_time, seclv_code,
					secplace_code, visit_reason, accompany_id, enter_time, leave_time, file_num, file_list, "gaosss");
			event.setJobType(JobTypeEnum.valueOf(jobType));
			secplaceService.addEnterSecplaceEvent(event, next_approver);// 向数据库添加event

			String[] visitor_name = visitor_names.split(",");
			String[] visitor_company = visitor_companys.split(",");
			String[] certificate_type = certificate_types.split(",");
			String[] certificate_code = certificate_codes.split(",");
			String[] visitor_belongings = visitor_belongingss.split(",");
			String[] security_arrangement = security_arrangements.split(",");
			for (int i = 0; i < visitor_num; i++) {
				EntityVisitor visitor = new EntityVisitor(event_code, visitor_name[i], visitor_company[i],
						certificate_type[i], certificate_code[i], "", 0, "", visitor_belongings[i],
						security_arrangement[i], "");
				secplaceService.addEntityVisitor(visitor);
			}
			insertCommonLog("添加外来人员进入涉密场所申请[" + event_code + "]");
			return "ok";
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			secplaceList = secplaceService.getAllSecplaceList(map);
			event_code = getCurUser().getUser_iidd() + "_ENTERSECPLACE_" + System.currentTimeMillis();
			return SUCCESS;
		}
	}
}
