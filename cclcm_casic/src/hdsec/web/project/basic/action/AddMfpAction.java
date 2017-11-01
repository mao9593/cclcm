package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConsole;
import hdsec.web.project.basic.model.SysMfp;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 添加一体机
 * 
 * @author renmingfei
 * 
 */
public class AddMfpAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String mfp_name = "";// 名称
	private String mfp_brand = "";// 品牌
	private String mfp_model = "";// 型号
	private String mfp_location = "";// 位置
	private String dept_id = "";// 部门
	private String console_code = "";// 控制台编号
	private Integer seclv_code = null;// 密级
	
	public void setMfp_name(String mfp_name) {
		this.mfp_name = mfp_name;
	}
	
	public void setMfp_brand(String mfp_brand) {
		this.mfp_brand = mfp_brand;
	}
	
	public void setMfp_model(String mfp_model) {
		this.mfp_model = mfp_model;
	}
	
	public void setMfp_location(String mfp_location) {
		this.mfp_location = mfp_location;
	}
	
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	public void setConsole_code(String console_code) {
		this.console_code = console_code;
	}
	
	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}
	
	public List<SysConsole> getConsoleList() {
		return basicService.getSysConsoleList();
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (mfp_name.isEmpty()) {
			return SUCCESS;
		} else {
			String mfp_code = "MFP-" + String.valueOf(UUID.randomUUID().toString());
			SysMfp mfp = new SysMfp(mfp_code, mfp_name, mfp_brand, mfp_model, mfp_location, dept_id, new Date(),
					console_code, seclv_code);
			basicService.addSysMfp(mfp);
			insertAdminLog("添加一体机[" + mfp_name + "]");
			return "ok";
		}
	}
}
