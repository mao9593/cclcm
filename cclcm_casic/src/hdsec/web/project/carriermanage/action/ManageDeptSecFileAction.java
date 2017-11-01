package hdsec.web.project.carriermanage.action;

import hdsec.web.project.carriermanage.model.PersonalFileInfo;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 部门涉密资料统计台账查询
 * 
 * @author guojiao
 * 
 */
public class ManageDeptSecFileAction extends CarrierManageBaseAction {
	private static final long serialVersionUID = 1L;
	private String file_name = "";// 资料名称
	private Integer file_seclv_code = null;// 资料密级
	private String file_type = null;// 资料类型
	private String file_year = "";
	private String file_quarter = "";
	private String duty_user_id = ""; // 责任人编号
	private String duty_user_name = ""; // 责任人编号
	private String duty_dept_id = ""; // 责任部门编号
	private String duty_dept_name = ""; // 责任部门编号
	private String duty_entp_id = ""; // 责任单位编号
	private List<PersonalFileInfo> fileList = null;
	private List<String> dept_ids = null;
	private List<SecDept> secAdminDeptList = null;
	private Integer total_file = 0;

	public Integer getTotal_file() {
		return total_file;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public String getDuty_dept_name() {
		return duty_dept_name;
	}

	public void setDuty_dept_name(String duty_dept_name) {
		this.duty_dept_name = duty_dept_name;
	}

	public List<SecDept> getSecAdminDeptList() {
		return secAdminDeptList;
	}

	public String getDept_ids() {
		String ret = "";
		for (String d_item : dept_ids) {
			ret += d_item + ",";
		}
		if (ret.endsWith(",")) {
			ret = ret.substring(0, ret.length() - 1);
		}
		return ret;
	}

	public List<PersonalFileInfo> getFileList() {
		return fileList;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public Integer getFile_seclv_code() {
		return file_seclv_code;
	}

	public void setFile_seclv_code(Integer file_seclv_code) {
		this.file_seclv_code = file_seclv_code;
	}

	public String getFile_type() {
		return file_type;
	}

	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}

	public String getFile_year() {
		return file_year;
	}

	public void setFile_year(String file_year) {
		this.file_year = file_year;
	}

	public String getFile_quarter() {
		return file_quarter;
	}

	public void setFile_quarter(String file_quarter) {
		this.file_quarter = file_quarter;
	}

	public String getDuty_user_id() {
		return duty_user_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getDuty_entp_id() {
		return duty_entp_id;
	}

	public void setDuty_entp_id(String duty_entp_id) {
		this.duty_entp_id = duty_entp_id;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action";
		dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
		if (dept_ids == null || dept_ids.size() == 0) {
			throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
		}
		secAdminDeptList = basicService.getDeptAdminList(getCurUser().getUser_iidd(), web_url);

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("file_name", file_name);
		map.put("file_seclv_code", file_seclv_code);
		map.put("file_type", file_type);
		map.put("file_year", file_year);
		map.put("file_quarter", file_quarter);
		map.put("duty_user_id", duty_user_id);
		map.put("duty_dept_id", duty_dept_id);
		map.put("duty_entp_id", duty_entp_id);
		map.put("approve_status", "1");
		map.put("scope_dept_ids", dept_ids);
		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = carrierManageService.getFileInfoSize(map);
		fileList = carrierManageService.getFileInfo(map);

		for (PersonalFileInfo temp : fileList) {
			if (temp != null) {
				total_file += temp.getFile_quantity();
			}
		}

		return SUCCESS;
	}
}
