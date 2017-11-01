package hdsec.web.project.carriermanage.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.carriermanage.model.PersonalFileEvent;
import hdsec.web.project.carriermanage.model.PersonalFileInfo;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 增加 涉密人员个人涉密资料台帐
 * 
 * @author lishu guojiao
 * 
 */
public class AddPersonalFileEventAction extends CarrierManageBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = ""; // 备注
	private String next_approver = "";// 下级审批人
	private final String jobType = JobTypeEnum.PERSONAL_FILE.getJobTypeCode();

	private String duty_entp_id = "";// 责任单位编号
	private String file_year = "";// 年份
	private String file_quarter = "";// 季度
	private String file_name = "";// 资料名称
	private String file_seclv_code = "";// 资料密级
	private String file_type = "";// 资料类型
	private String file_category = "";// 资料格式
	private Integer file_num = null;// 上传份数
	private String file_quantity = "";// 每份的数量
	private String other_type = "";// 其他类型（用户手填）

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public String getJobType() {
		return jobType;
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

	public void setDuty_entp_id(String duty_entp_id) {
		this.duty_entp_id = duty_entp_id;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public void setFile_seclv_code(String file_seclv_code) {
		this.file_seclv_code = file_seclv_code;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public void setFile_category(String file_category) {
		this.file_category = file_category;
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public void setFile_quantity(String file_quantity) {
		this.file_quantity = file_quantity;
	}

	public void setFile_year(String file_year) {
		this.file_year = file_year;
	}

	public void setFile_quarter(String file_quarter) {
		this.file_quarter = file_quarter;
	}

	public void setOther_type(String other_type) {
		this.other_type = other_type;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			if (file_num == null) {
				throw new Exception("请添加资料涉密信息详情");
			}
			// 先版本只支持各自上传
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			String duty_user_id = getCurUser().getUser_iidd();
			String duty_dept_id = getCurUser().getDept_id();

			// 添加资料行进行拆分
			String[] file_name_str = file_name.split(",");
			String[] file_seclv_str = file_seclv_code.split(",");
			String[] file_type_str = file_type.split(",");
			String[] file_category_str = file_category.split(",");
			String[] file_quantity_str = file_quantity.split(",");
			String[] other_type_str = other_type.split(",");

			// 添加进event表中
			PersonalFileEvent event = new PersonalFileEvent(user_iidd, dept_id, event_code, seclv_code, usage_code,
					project_code, summ, duty_user_id, file_num, duty_dept_id, duty_entp_id, file_year, file_quarter);

			event.setJobType(JobTypeEnum.valueOf(jobType));

			// 根据添加资料行数分别添加仅资料台账中
			for (int i = 0; i < file_num; i++) {
				PersonalFileInfo fileinfo = new PersonalFileInfo(file_name_str[i].trim(),
						Integer.parseInt(file_seclv_str[i].trim()), file_type_str[i].trim(),
						file_category_str[i].trim(), other_type_str[i].trim(), Integer.parseInt(file_quantity_str[i]
								.trim()), event_code, duty_user_id, duty_dept_id, duty_entp_id, file_year,
						file_quarter, 0);
				carrierManageService.addPersonalFileInfo(fileinfo);
			}
			// 启动流程添加完成
			carrierManageService.addPersonalFileEvent(event, next_approver);
			insertCommonLog("添加个人涉密资料申请[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_PERSONAL_FILE_" + System.currentTimeMillis();
			return SUCCESS;
		}
	}
}
