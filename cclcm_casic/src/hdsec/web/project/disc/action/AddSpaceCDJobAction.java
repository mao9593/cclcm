package hdsec.web.project.disc.action;

import hdsec.web.project.basic.model.SysBarcode;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;
import hdsec.web.project.disc.model.EntitySpaceCD;
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
 * 
 */

/**
 * 
 * @author lishu 2015-12-10
 */
public class AddSpaceCDJobAction extends DiscBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer seclv_code = null;// 作业密级
	private String scope_dept_id = ""; // 归属部门
	private String scope_dept_name = "";
	private Integer enter_num = null;
	private String cd_type;
	private String update = "N";
	private String enter_code;
	private List<SecDept> deptAdminList;
	private String spacecd_type = "";// 空白盘类型，0空白盘，1中转盘
	private String summ = "";
	private String scope = "";
	private String barcode = "";// 载体条码
	private String pdf417code = "";
	private String usage_code = "";

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

	public Integer getEnter_num() {
		return enter_num;
	}

	public void setEnter_num(Integer enter_num) {
		this.enter_num = enter_num;
	}

	public String getCd_type() {
		return cd_type;
	}

	public void setCd_type(String cd_type) {
		this.cd_type = cd_type;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getPdf417code() {
		return pdf417code;
	}

	public void setPdf417code(String pdf417code) {
		this.pdf417code = pdf417code;
	}

	public String getSpacecd_type() {
		return spacecd_type;
	}

	public void setSpacecd_type(String spacecd_type) {
		this.spacecd_type = spacecd_type;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getScope_dept_name() {
		return scope_dept_name;
	}

	public void setScope_dept_name(String scope_dept_name) {
		this.scope_dept_name = scope_dept_name;
	}

	@Override
	public String executeFunction() throws Exception {
		String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action";
		deptAdminList = basicService.getDeptAdminList(getCurUser().getUser_iidd(), web_url);
		if (deptAdminList == null || deptAdminList.size() == 0) {
			throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
		}

		if (update.equals("Y")) {
			// 得到录入的空白盘所有条码(条码动态库）
			SecUser user = getCurUser();
			SecDept dept = userService.getSecDeptById(scope_dept_id);
			String event_code = user.getUser_iidd() + "-SPACECD-" + System.currentTimeMillis();
			SpaceCDEvent event = new SpaceCDEvent(event_code, user.getUser_iidd(), user.getDept_id(), scope,
					scope_dept_id, scope_dept_name, seclv_code, summ, new Date(), cd_type, enter_num, enter_code,
					spacecd_type);
			discService.addEnterSpaceCDEvent(event);
			String usage_code = event.getUsage_code();
			String enter_num_str = Integer.toString(enter_num);
			String project_code = event.getProject_code();
			SysBarcode sysBarcode = basicService.getSysBarcode(seclv_code, usage_code, project_code);
			String unitCode = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.code");// 5S
			// 获取unit.CompanyType haojia 20160629
			String strCompayType = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.CompanyType");

			seclv_code = event.getSeclv_code();
			Integer page_count = 1;

			for (int i = 0; i < enter_num; i++) {
				StringBuffer sb = new StringBuffer();
				if(unitCode != "" && strCompayType.equalsIgnoreCase("CAEP")){
					barcode = basicService.createEntityBarcodeCAEP("SPACECD", seclv_code, scope_dept_id);
				}else{
					barcode = basicService.createEntityBarcode("SPACECD");	
				}

				if (sysBarcode != null) {
					Integer form = sysBarcode.getForm();
					if (unitCode != "" && strCompayType.equalsIgnoreCase("CAEP")) {
						if (form == 1) {// 一维码
							sb.append("1" + "#" + barcode + "#" + barcode);
						} else if (form == 2) {// 二维码(QR)
							sb.append("2" + "#" + barcode + "#" + barcode);
						} else if (form == 3) {// 二维码(PDF417)
							sb.append("3" + "#" + barcode + "#" + barcode);
						}						
					}else{
						if (form == 1) {// 一维码
							sb.append("1" + "#" + barcode + "#" + barcode);
						} else if (form == 2) {// 二维码(QR)
							sb.append("2"
									+ "#"
									+ barcode
									+ "#"
									+ basicService.buildTwoDimenosionalBarcode("00", "00", user.getUser_name(),
											String.valueOf(seclv_code), "Z", "W", String.valueOf(page_count),
											"0000", "", scope_dept_id, barcode));
							pdf417code = basicService.buildTwoDimenosionalBarcode("00", "00", user.getUser_name(),
									String.valueOf(seclv_code), "Z", "W", String.valueOf(page_count), "0000", "",
									scope_dept_id, barcode);
						} else if (form == 3) {// 二维码(PDF417)
							sb.append("3"
									+ "#"
									+ barcode
									+ "#"
									+ basicService.buildTwoDimenosionalBarcode("00", "00", user.getUser_name(),
											String.valueOf(seclv_code), "Z", "W", String.valueOf(page_count),
											"0000", "", scope_dept_id, barcode));
							pdf417code = basicService.buildTwoDimenosionalBarcode("00", "00", user.getUser_name(),
									String.valueOf(seclv_code), "Z", "W", String.valueOf(page_count), "0000", "",
									scope_dept_id, barcode);
						}
					}
				}

				sb.append("#");
				// 往台帐表插入空白盘数据
				EntitySpaceCD entity = new EntitySpaceCD(event_code, barcode, pdf417code, user.getDept_id(),
						user.getDept_name(), user.getUser_iidd(), user.getUser_name(), null, null, null, null,
						new Date(), seclv_code, cd_type, null, null, null, 0, "LEADIN", "DEPT", null, scope_dept_id,
						dept.getDept_name(), null, null, 0);
				discService.addEnterSpaceCD(entity);

			}

			return "ok";
		}

		return SUCCESS;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getBurnSecLevelByUser(getCurUser().getUser_iidd());
	}
}
