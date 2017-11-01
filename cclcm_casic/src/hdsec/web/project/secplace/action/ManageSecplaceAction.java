package hdsec.web.project.secplace.action;

import hdsec.web.project.secplace.model.EntitySecplace;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看涉密场所台账
 * 
 * @author liuyaling 2015-5-13
 */
public class ManageSecplaceAction extends SecplaceBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String secplace_barcode = "";
	private String secplace_name = "";
	private String seclv_code = "";
	private String secplace_code = "";
	private String secplace_type = "";
	private String secplace_status = "";
	private String duty_dept_id = "";
	private String duty_user_id = "";
	private List<EntitySecplace> secplaceList = null;

	public String getSecplace_name() {
		return secplace_name;
	}

	public void setSecplace_name(String secplace_name) {
		this.secplace_name = secplace_name;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getSecplace_barcode() {
		return secplace_barcode;
	}

	public void setSecplace_barcode(String secplace_barcode) {
		this.secplace_barcode = secplace_barcode;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getSecplace_code() {
		return secplace_code;
	}

	public void setSecplace_code(String secplace_code) {
		this.secplace_code = secplace_code;
	}

	public String getSecplace_type() {
		return secplace_type;
	}

	public void setSecplace_type(String secplace_type) {
		this.secplace_type = secplace_type;
	}

	public String getSecplace_status() {
		return secplace_status;
	}

	public void setSecplace_status(String secplace_status) {
		this.secplace_status = secplace_status;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getDuty_user_id() {
		return duty_user_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
	}

	public List<EntitySecplace> getSecplaceList() {
		return secplaceList;
	}

	public void setSecplaceList(List<EntitySecplace> secplaceList) {
		this.secplaceList = secplaceList;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("secplace_barcode", secplace_barcode);
		map.put("secplace_name", secplace_name);
		map.put("seclv_code", seclv_code);
		map.put("secplace_code", secplace_code);
		map.put("secplace_type", secplace_type);
		map.put("duty_dept_id", duty_dept_id);
		map.put("duty_user_id", duty_user_id);
		map.put("secplace_status", secplace_status);

		secplaceList = secplaceService.getAllSecplaceList(map);
		System.out.println("secplaceList.size:" + secplaceList.size());
		return SUCCESS;
	}

}