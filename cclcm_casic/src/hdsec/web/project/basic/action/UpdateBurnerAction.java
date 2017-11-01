package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysBurner;
import hdsec.web.project.basic.model.SysConsole;
import hdsec.web.project.basic.model.SysMfp;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.List;

/**
 * 修改刻录机信息
 * 
 * @author renmingfei
 * 
 */
public class UpdateBurnerAction extends BasicBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String burner_code = "";// 编号
	private String burner_name = "";// 名称
	private String burner_path = "";// 路径
	private String burner_type = "";// 类型 DVD/CD/BLUERAY
	private String burner_brand = "";// 品牌
	private String burner_model = "";// 型号
	private String burner_location = "";// 位置
	private String dept_id = "";// 部门
	private String console_code = "";// 控制台编号
	private Integer seclv_code = null;// 密级
	private Integer burner_usefor = null;// 用途 0输出 1输入
	private String mfp_code = "";// 关联的一体机code
	private SysBurner burner = null;
	private String update = "N";
	
	public SysBurner getBurner() {
		return burner;
	}
	
	public void setBurner_code(String burner_code) {
		this.burner_code = burner_code;
	}
	
	public void setBurner_name(String burner_name) {
		this.burner_name = burner_name;
	}
	
	public void setBurner_path(String burner_path) {
		this.burner_path = burner_path;
	}
	
	public void setBurner_type(String burner_type) {
		this.burner_type = burner_type;
	}
	
	public void setBurner_brand(String burner_brand) {
		this.burner_brand = burner_brand;
	}
	
	public void setBurner_model(String burner_model) {
		this.burner_model = burner_model;
	}
	
	public void setBurner_location(String burner_location) {
		this.burner_location = burner_location;
	}
	
	public void setBurner_usefor(Integer burner_usefor) {
		this.burner_usefor = burner_usefor;
	}
	
	public void setMfp_code(String mfp_code) {
		this.mfp_code = mfp_code;
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
	
	public void setUpdate(String update) {
		this.update = update;
	}
	
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}
	
	public List<SysConsole> getConsoleList() {
		return basicService.getSysConsoleList();
	}
	
	public List<SysMfp> getMfpList() {
		return basicService.getSysMfpList();
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("N")) {
			burner = basicService.getBurnerByCode(burner_code);
			return SUCCESS;
		} else {
			burner = new SysBurner(burner_code, burner_name, burner_path, burner_type, burner_brand, burner_model,
					burner_location, dept_id, new Date(), console_code, seclv_code, burner_usefor, mfp_code);
			basicService.updateBurner(burner);
			insertAdminLog("修改刻录机：编号[" + mfp_code + "]");
			return "ok";
		}
	}
	
}
