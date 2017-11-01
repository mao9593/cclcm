package hdsec.web.project.client.action;

import hdsec.web.project.basic.model.FileInfo;

import java.util.List;

/**
 * 设定客户端版本
 * 
 * @author gaoxm
 * 
 */
public class UpdateCVSAction extends ClientBaseAction {
	private static final long serialVersionUID = 1L;
	private String hidCvs_code = "";// 隐藏编号
	private String hidSet_version = "";// 设定版本（强制升级或降级）
	private String hidScope = "";// 设定范围
	private String hidDept_id = "";
	private String hidDept_name = "";
	private boolean is_set = false;

	public void setHidCvs_code(String hidCvs_code) {
		this.hidCvs_code = hidCvs_code;
	}

	public void setHidSet_version(String hidSet_version) {
		this.hidSet_version = hidSet_version;
	}

	public void setHidScope(String hidScope) {
		this.hidScope = hidScope;
	}

	public void setHidDept_id(String hidDept_id) {
		this.hidDept_id = hidDept_id;
	}

	public void setHidDept_name(String hidDept_name) {
		this.hidDept_name = hidDept_name;
	}

	public List<FileInfo> getVersions() {
		return basicService.getFileInfosByType("CLIENT");
	}

	@Override
	public String executeFunction() throws Exception {
		if (is_set) {
			if (hidScope.equals("single")) {
				clientService.updateCVS(hidCvs_code, "", hidSet_version);
				insertAdminLog("设定客户端版本：编号[" + hidCvs_code + "]版本[" + hidSet_version + "]");
			} else if (hidScope.equals("all")) {
				clientService.updateCVS("", "", hidSet_version);
				insertAdminLog("全局设定客户端版本：版本[" + hidSet_version + "]");
			} else if (hidScope.equals("dept")) {
				clientService.updateCVS("", hidDept_id, hidSet_version);
				insertAdminLog("批量设定客户端版本：部门[" + hidDept_name + "]版本[" + hidSet_version + "]");
			}
			return "ok";
		}
		return SUCCESS;
	}

	public void setIs_set(boolean is_set) {
		this.is_set = is_set;
	}
}
