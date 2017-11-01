package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecDeptLevel;
import hdsec.web.project.user.model.SecDeptType;
import hdsec.web.project.user.model.SecSubsys;
import hdsec.web.project.user.model.SecTerritory;

import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 查看并配置部门信息
 * 
 * @author renmingfei
 * 
 */
public class ConfigDeptAction extends UserBaseAction {

	private static final long serialVersionUID = 1L;
	private String dept_id = "";
	private String upDeptName = "";
	private String upDeptId = "";
	private String dept_subsys_code = "";
	private String dept_subsys_name = "";
	private String hasUser = "N";
	private SecDept secDept = null;
	private SecTerritory secTerr = null;
	private SecDeptLevel secDeptLevel = null;
	private SecDeptType secDeptType = null;

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getUpDeptName() {
		return upDeptName;
	}

	public void setUpDeptName(String upDeptName) {
		this.upDeptName = upDeptName;
	}

	public String getUpDeptId() {
		return upDeptId;
	}

	public void setUpDeptId(String upDeptId) {
		this.upDeptId = upDeptId;
	}

	public SecDept getSecDept() {
		return secDept;
	}

	public SecTerritory getSecTerr() {
		return secTerr;
	}

	public SecDeptLevel getSecDeptLevel() {
		return secDeptLevel;
	}

	public SecDeptType getSecDeptType() {
		return secDeptType;
	}

	public String getDept_subsys_code() {
		return dept_subsys_code;
	}

	public String getDept_subsys_name() {
		return dept_subsys_name;
	}

	public String getHasUser() {
		return hasUser;
	}

	@Override
	public String executeFunction() throws Exception {
		// request.setAttribute("fun", "query");
		if (StringUtils.hasLength(dept_id)) {
			// 部门信息
			secDept = userService.getSecDeptById(dept_id);
			// 地市信息
			/*
			 * if (StringUtils.hasLength(secDept.getTerr_code())) { secTerr =
			 * userService.getSecTerrByCode(secDept.getTerr_code()); }
			 */
			// 部门级别信息
			// secDeptLevel = userService.getSecDeptLevelByCode(secDept.getDept_level_code());
			// 部门类型信息,等级为1的部门没有类型信息
			/*
			 * if (StringUtils.hasLength(secDept.getDept_type_code())) { secDeptType =
			 * userService.getSecDeptTypeByCode(secDept.getDept_type_code()); } else
			 * {//防止页面调用名称的get方法的时候，出现NullPointerException secDeptType = new SecDeptType(); }
			 */
			// 查询父部门名称和代码
			if (!StringUtils.hasLength(secDept.getDept_parent_id())) {
				setUpDeptName("已是最高级");
			} else {
				String parentDeptId = secDept.getDept_parent_id();
				setUpDeptId(parentDeptId);
				setUpDeptName(userService.getDeptNameByDeptId(parentDeptId));
			}

			// 构造相关子系统及其显示,形式为'XXX,XXX,XXX'
			List<SecSubsys> subsysList = userService.getSubsysListByDeptId(dept_id);
			for (SecSubsys item : subsysList) {
				dept_subsys_code = dept_subsys_code + item.getSubsys_code() + ",";
				dept_subsys_name = dept_subsys_name + item.getSubsys_name() + ",";
			}
			if (dept_subsys_code.indexOf(",") != -1) {// 去掉最后的逗号
				dept_subsys_code = dept_subsys_code.substring(0, dept_subsys_code.length() - 1);
				dept_subsys_name = dept_subsys_name.substring(0, dept_subsys_name.length() - 1);
			}
			// 查看该部门及其子部门是否已经关联用户
			hasUser = userService.getUserCountByDeptWithSub(secDept.getDept_cs()) > 0 ? "Y" : "N";
		} else {
			return "introduce";
		}
		return SUCCESS;
	}

}