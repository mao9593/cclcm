package hdsec.web.project.disc.action;

import hdsec.web.project.disc.model.SpaceCDEvent;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecUser;

import java.util.Date;
import java.util.List;

/**
 * 添加空白盘
 * 
 * @author zp
 * @author lidepeng(20150811)
 * @author lishu 2015-12-25
 */

public class AddDeptSpaceCDAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer seclv_code = null;// 作业密级
	private String scope_dept_id = ""; // 归属部门
	private int spacecd_count;
	private String cd_type;
	private String update = "N";
	private String enter_code;
	private List<SecDept> deptAdminList;
	private String scope_dept_name = "";
	private Integer enter_num = null;
	private String spacecd_type = "";

	public String getEnter_code() {
		return enter_code;
	}

	public void setEnter_code(String enter_code) {
		this.enter_code = enter_code;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public List<SecDept> getDeptAdminList() {
		return deptAdminList;
	}

	public void setDeptAdminList(List<SecDept> deptAdminList) {
		this.deptAdminList = deptAdminList;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getScope_dept_id() {
		return scope_dept_id;
	}

	public void setScope_dept_id(String scope_dept_id) {
		this.scope_dept_id = scope_dept_id;
	}

	public int getSpacecd_count() {
		return spacecd_count;
	}

	public void setSpacecd_count(int spacecd_count) {
		this.spacecd_count = spacecd_count;
	}

	public String getCd_type() {
		return cd_type;
	}

	public void setCd_type(String cd_type) {
		this.cd_type = cd_type;
	}

	public String getScope_dept_name() {
		return scope_dept_name;
	}

	public void setScope_dept_name(String scope_dept_name) {
		this.scope_dept_name = scope_dept_name;
	}

	public Integer getEnter_num() {
		return enter_num;
	}

	public void setEnter_num(Integer enter_num) {
		this.enter_num = enter_num;
	}

	@Override
	public String executeFunction() throws Exception {
		String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action";
		deptAdminList = basicService.getDeptAdminList(getCurUser().getUser_iidd(), web_url);
		if (deptAdminList == null || deptAdminList.size() == 0) {
			throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
		}

		if (update.equals("Y")) {
			// 得到录入的空白盘所有条码
			// List<String> barcodess = discService.createEntityBarcode_1("ENTERCD", spacecd_count);
			/*
			 * List<String> barcodess = discService.createEntityBarcode_spacecd("ENTERCD", scope_dept_id, seclv_code,
			 * spacecd_count);
			 */
			SecUser user = getCurUser();
			SecDept dept = userService.getSecDeptById(scope_dept_id);
			String event_code = user.getUser_iidd() + "-LEADIN-" + System.currentTimeMillis();
			/* String barcodeLast = barcodess.get(barcodess.size() - 1); */
			// enter_code = barcodeLast.substring(9, barcodeLast.lastIndexOf("00"));
			/*
			 * if (barcodeLast.length() == 13) { enter_code = barcodeLast.substring(4, barcodeLast.lastIndexOf("-")); }
			 * else if (barcodeLast.length() == 14) { enter_code = barcodeLast.substring(5,
			 * barcodeLast.lastIndexOf("-")); }
			 */

			SpaceCDEvent event = new SpaceCDEvent(event_code, user.getUser_iidd(), user.getDept_id(), "DEPT",
					scope_dept_id, scope_dept_name, seclv_code, "", new Date(), cd_type, enter_num, enter_code,
					spacecd_type);

			/*
			 * for (String string : barcodess) { EntitySpaceCD entity = new EntitySpaceCD(string, user.getDept_id(),
			 * user.getDept_name(), user.getUser_iidd(), user.getUser_name(), null, null, null, null, new Date(),
			 * seclv_code, cd_type, null, null, null, 0, "LEADIN", "DEPT", null, scope_dept_id, dept.getDept_name(),
			 * null, null); entity.setEvent_code(event_code); discService.addEnterSpaceCD(entity); }
			 */
			discService.addEnterSpaceCDEvent(event);

			return "ok";
		}

		return SUCCESS;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getBurnSecLevelByUser(getCurUser().getUser_iidd());
	}
}
