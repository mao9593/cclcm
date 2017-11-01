package hdsec.web.project.burn.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecRole;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 添加刻录作业
 * 
 * @author yy
 * 
 */
public class AddNASBurnEventAction extends BurnBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_code = "";// 刻录作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String comment = "";// 备注
	private Integer data_type = null;// 数据类型
	private Integer cd_num = 1;// 刻录份数
	private Integer file_num = 0;// 文件数量
	private String file_list = "";// 文件列表
	private String file_seclevel = "";// 文件密级列表
	private String cd_serial = "";// 光盘编号
	private Integer is_proxy = 0;// 是否代理刻录 1代理 0不代理
	private String cycle_type = "";// 刻录状态
	private String period = "";// 短期/长期
	private String next_approver = "";// 下级审批人
	private String output_dept_name = "";// 外发接收人部门
	private String output_user_name = "";// 外发接收人
	private String conf_code = "";// 保密编号
	private String info;// 上传文件信息（文件名和密级）
	private List<SecRole> userRoleList = null;
	private String is_special = "N";

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
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

	public void setData_type(Integer data_type) {
		this.data_type = data_type;
	}

	public void setCd_num(Integer cd_num) {
		this.cd_num = cd_num;
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public void setFile_seclevel(String file_seclevel) {
		this.file_seclevel = file_seclevel;
	}

	public void setCd_serial(String cd_serial) {
		this.cd_serial = cd_serial;
	}

	public void setIs_proxy(Integer is_proxy) {
		this.is_proxy = is_proxy;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getBurnSecLevelByUser(getCurUser().getUser_iidd());
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageListByModule("BURN");
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	public List<SecRole> getUserRoleList() {
		return userRoleList;
	}

	public String getIs_special() {
		return is_special;
	}

	private void handleFileList() throws Exception {
		for (String onefile : info.split("::")) {
			file_num++;
			String fileName = onefile.split(" __ ")[0];
			String seclv_file = onefile.split(" __ ")[1];
			file_list = file_list + fileName + CCLCMConstants.DEVIDE_SYMBOL;
			file_seclevel = file_seclevel + seclv_file + CCLCMConstants.DEVIDE_SYMBOL;
			logger.debug("copy file:" + fileName);
		}
		if (file_list.endsWith(CCLCMConstants.DEVIDE_SYMBOL)) {
			file_list = file_list.substring(0, file_list.length() - 1);
		}
		if (file_seclevel.endsWith(CCLCMConstants.DEVIDE_SYMBOL)) {
			file_seclevel = file_seclevel.substring(0, file_seclevel.length() - 1);
		}
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			handleFileList();
			BurnEvent event = new BurnEvent(user_iidd, dept_id, event_code, seclv_code, usage_code, project_code,
					cd_serial, cd_num, is_proxy, comment, data_type, file_num, file_list, file_seclevel, cycle_type,
					period);
			String jobType_code = "BURN_" + cycle_type;
			JobTypeEnum jobType = JobTypeEnum.valueOf(jobType_code);
			event.setJobType(jobType);
			event.setConf_code(conf_code);
			burnService.addBurnEvent(event, next_approver, output_dept_name, output_user_name, comment);
			insertCommonLog("添加刻录作业[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_BURN_" + System.currentTimeMillis();
			// 判断是否是刻录专员（31所专用）
			userRoleList = getCurUser().getUserRoleList();
			if (userRoleList != null && userRoleList.size() > 0) {
				for (SecRole role : userRoleList) {
					if (role.getRole_spec_key().equalsIgnoreCase("SPECIALCDMANAGER")) {
						is_special = "Y";
					}
				}
			}
			if (is_special.equals("N")) {
				throw new Exception("只有刻录专员才具有刻录权限！");
			}
			return SUCCESS;
		}
	}

	public String getCycle_type() {
		return cycle_type;
	}

	public void setCycle_type(String cycle_type) {
		this.cycle_type = cycle_type;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getOutput_dept_name() {
		return output_dept_name;
	}

	public void setOutput_dept_name(String output_dept_name) {
		this.output_dept_name = output_dept_name;
	}

	public String getOutput_user_name() {
		return output_user_name;
	}

	public void setOutput_user_name(String output_user_name) {
		this.output_user_name = output_user_name;
	}

}
