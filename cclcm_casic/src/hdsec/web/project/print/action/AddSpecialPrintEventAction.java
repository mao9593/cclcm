package hdsec.web.project.print.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.common.util.MD5;
import hdsec.web.project.common.util.PrefixFilenameFilter;
import hdsec.web.project.print.model.OaPrintEvent;
import hdsec.web.project.user.model.SecLevel;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 添加OA特殊打印功能
 * 
 * @author guojiao
 * 
 */
public class AddSpecialPrintEventAction extends PrintBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String comment = "";// 文件备注
	private String summ = "";// 审批单备注
	// private String paper_name = "";// 文件名称
	private Integer page_num = null; // 页数
	private Integer paper_num = null; // 份数
	private String make_type = null; // 制作类别（1、上下订装2、左右订装3、其他）
	private Integer file_selv = null;// 文件密级
	private Integer print_direct = null;// 纵横向
	private Integer print_double = null;// 单双面
	private Integer paper_color = null;// 色彩
	private String paper_kind = "";// 纸张类型
	private Integer file_num = 0;// 文件数量
	private String file_list = "";// 文件列表
	private final String jobType = JobTypeEnum.SPECIAL_PRINT.getJobTypeCode();
	private List<OaPrintEvent> eventList = null;

	// private List<SysUsage> typeList = null;

	public List<OaPrintEvent> getEventList() {
		return eventList;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public void setPage_num(Integer page_num) {
		this.page_num = page_num;
	}

	public void setPaper_num(Integer paper_num) {
		this.paper_num = paper_num;
	}

	public void setMake_type(String make_type) {
		this.make_type = make_type;
	}

	public void setFile_selv(Integer file_selv) {
		this.file_selv = file_selv;
	}

	public void setPrint_direct(Integer print_direct) {
		this.print_direct = print_direct;
	}

	public void setPrint_double(Integer print_double) {
		this.print_double = print_double;
	}

	public void setPaper_color(Integer paper_color) {
		this.paper_color = paper_color;
	}

	public void setPaper_kind(String paper_kind) {
		this.paper_kind = paper_kind;
	}

	public String getJobType() {
		return jobType;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getEvent_code() {
		return event_code;
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

	public void setComment(String comment) {
		this.comment = comment;
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getPrintSecLevelByUser(getCurUser().getUser_iidd());
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageListByModule("PRINT");
	}

	public List<SysUsage> getTypeList() {
		return basicService.getSpecialPaperTypeList();
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	private void handleFileList() throws Exception {
		// 从配置文件中读取刻录文件的临时存储路径和正式存储路径
		String tempPath = PropertyUtil.getBurnFileTempPath();
		String storePath = PropertyUtil.getOaFileStorePath();

		tempPath = tempPath + File.separator + getCurUser().getUser_iidd();
		File path = new File(tempPath);
		PrefixFilenameFilter filenameFilter = new PrefixFilenameFilter(event_code + "-");
		if (path.isDirectory()) {
			File[] files = path.listFiles(filenameFilter);
			file_num = files.length;
			for (File file : files) {
				String fileName = file.getName();
				fileName = fileName.substring(fileName.indexOf("-") + 1);

				try {
					moveFile(file, storePath + File.separator + event_code, MD5.getStringMD5(fileName));
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
			OaPrintEvent event = new OaPrintEvent(user_iidd, dept_id, event_code, seclv_code, usage_code, project_code,
					summ, file_list, page_num, paper_num, make_type, file_selv, print_direct, print_double,
					paper_color, paper_kind, file_num, file_list, comment);
			event.setJob_type(jobType);
			printService.addOaPrintEvent(event);
			insertCommonLog("添加特殊打印申请，文件列表[" + event_code + "]");
		}
		event_code = getCurUser().getUser_iidd() + System.currentTimeMillis();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("submitable", true);
		eventList = printService.getSpecialEventList(map);
		return SUCCESS;
	}
}
