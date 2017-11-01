package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecDeptLevel;
import hdsec.web.project.user.model.SecDeptType;
import hdsec.web.project.user.model.SecSubsys;
import hdsec.web.project.user.model.SecTerritory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 修改安全部门
 * 
 * @author renmingfei
 * 
 */
public class UpdateSecDeptAction extends UserBaseAction {

	private static final long serialVersionUID = 1L;
	private String dept_id = "";
	private String dept_name = "";
	private String dept_rank = ""; // 部门排序值
	private String dept_parent_cs = ""; // 父部门cs值
	private String temp_rank = ""; // 用户实际可以输入的排序值
	private String dept_desc = "";
	private String upDeptName = "";
	private String dept_subsys_name = "";
	private String secTerr_code = "";
	private String dept_level_code = "";
	private String dept_type_code = "";
	private String dept_subsys = "";
	private String hasUser = "N";
	private String update = "N";
	private String updateSubsys = "N";
	private SecDept secDept = null;
	private List<SecTerritory> secTerrList = null;
	private List<SecDeptLevel> deptLevelList = null;
	private List<SecDeptType> deptTypeList = null;
	private String ext_code = ""; // 组织机构代码

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_rank() {
		return dept_rank;
	}

	public String getDept_parent_cs() {
		return dept_parent_cs;
	}

	public void setDept_parent_cs(String dept_parent_cs) {
		this.dept_parent_cs = dept_parent_cs;
	}

	public void setDept_rank(String dept_rank) {
		this.dept_rank = dept_rank;
	}

	public String getDept_name() {
		return dept_name;
	}

	public String getTemp_rank() {
		return temp_rank;
	}

	public void setTemp_rank(String temp_rank) {
		this.temp_rank = temp_rank;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getUpDeptName() {
		return upDeptName;
	}

	public void setUpDeptName(String upDeptName) {
		this.upDeptName = upDeptName;
	}

	public String getDept_subsys_name() {
		return dept_subsys_name;
	}

	public String getHasUser() {
		return hasUser;
	}

	public SecDept getSecDept() {
		return secDept;
	}

	public List<SecTerritory> getSecTerrList() {
		return secTerrList;
	}

	public List<SecDeptLevel> getDeptLevelList() {
		return deptLevelList;
	}

	public List<SecDeptType> getDeptTypeList() {
		return deptTypeList;
	}

	public void setDept_desc(String dept_desc) {
		this.dept_desc = dept_desc;
	}

	public void setSecTerr_code(String secTerr_code) {
		this.secTerr_code = secTerr_code;
	}

	public void setDept_level_code(String dept_level_code) {
		this.dept_level_code = dept_level_code;
	}

	public void setDept_type_code(String dept_type_code) {
		this.dept_type_code = dept_type_code;
	}

	public void setDept_subsys(String dept_subsys) {
		this.dept_subsys = dept_subsys;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public void setUpdateSubsys(String updateSubsys) {
		this.updateSubsys = updateSubsys;
	}
	
	public String getExt_code() {
		return ext_code;
	}

	public void setExt_code(String ext_code) {
		this.ext_code = ext_code;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {// 修改机构操作
			if (!StringUtils.hasLength(dept_id)) {// 部门代码已经存在，不能重复添加
				logger.error("机构修改失败，机构编码:" + dept_id + " 已经不存在。");
			} else {
				if (dept_level_code.equalsIgnoreCase("1")) {// 如果部门等级是单位，则没有部门类型，部门和班组才有
					dept_type_code = "";
				}
				setSecTerr_code("");
				setDept_level_code("1");
				setDept_type_code("");
				Map<String, String> map = new HashMap<String, String>();
				map.put("dept_id", dept_id);
				map.put("dept_rank", dept_rank);
				map.put("dept_name", dept_name);
				map.put("dept_desc", dept_desc);
				map.put("secTerr_code", secTerr_code);
				map.put("dept_level_code", dept_level_code);
				map.put("dept_type_code", dept_type_code);
				map.put("dept_subsys", dept_subsys);
				map.put("updateSubsys", updateSubsys);
				map.put("ext_code", ext_code);
				userService.updateSecDept(map);
				insertAdminLog("修改机构信息:" + dept_id + "," + dept_name);
				return "update";
			}
		} else {// 返回修改页面
			// secTerrList = userService.getAllSecTerritory();
			// deptLevelList = userService.getAllSecDeptLevel();
			// deptTypeList = userService.getAllSecDeptType();
			if (StringUtils.hasLength(dept_id)) {
				// 查询机构信息
				secDept = userService.getSecDeptById(dept_id);
				// 查询父机构名称
				if (dept_id.length() <= 2) {
					setUpDeptName("已是最高级");
					setDept_parent_cs("");
				} else {
					setUpDeptName(userService.getDeptNameByDeptId(secDept.getDept_parent_id()));
					// 设置父部门cs值
					setDept_parent_cs(userService.getSecDeptById(secDept.getDept_parent_id()).getDept_cs());
					setTemp_rank(secDept.getDept_rank().substring(dept_parent_cs.length()));
				}
				// 构造相关子系统及其显示,形式为'XXX,XXX,XXX'
				List<SecSubsys> subsysList = userService.getSubsysListByDeptId(dept_id);
				for (SecSubsys item : subsysList) {
					dept_subsys_name = dept_subsys_name + item.getSubsys_name() + ",";
				}
				if (dept_subsys_name.indexOf(",") != -1) {// 去掉最后的逗号
					dept_subsys_name = dept_subsys_name.substring(0, dept_subsys_name.length() - 1);
				}
				hasUser = userService.getUserCountByDeptWithSub(dept_id) > 0 ? "Y" : "N";
			}
		}
		return SUCCESS;
	}
}
