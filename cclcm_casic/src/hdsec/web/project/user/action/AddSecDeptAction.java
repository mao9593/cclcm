package hdsec.web.project.user.action;

import hdsec.web.project.user.model.DeptAdminConfig;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecDeptAdmin;
import hdsec.web.project.user.model.SecDeptLevel;
import hdsec.web.project.user.model.SecDeptType;
import hdsec.web.project.user.model.SecTerritory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 添加组织机构
 * 
 * @author renmingfei
 * 
 */
public class AddSecDeptAction extends UserBaseAction {

	private static final long serialVersionUID = 1L;
	private String insert = "N";
	private String dept_id = "";
	private String dept_cs = "";
	private String dept_parent_id = "";
	private String dept_parent_name = "";
	private String newDept_cs = "";
	private String dept_name = "";
	private String dept_desc = "";
	private String secTerr_code = "";
	private String dept_level_code = "";
	private String dept_type_code = "";
	private String dept_subsys = "";
	private SecDept secDept = null;
	private List<SecTerritory> secTerrList = null;
	private List<SecDeptLevel> deptLevelList = null;
	private List<SecDeptType> deptTypeList = null;
	private String dept_rank = ""; // 部门排序值
	private String dept_parent_cs = ""; // 父部门cs值
	private String temp_rank = ""; // 用户实际可以输入的排序值
	private String autoDept="N";
	private String ext_code = ""; // 组织机构代码

	public String getAutoDept() {
		return autoDept;
	}

	public void setAutoDept(String autoDept) {
		this.autoDept = autoDept;
	}

	public String getDept_rank() {
		return dept_rank;
	}

	public void setDept_rank(String dept_rank) {
		this.dept_rank = dept_rank;
	}

	public String getDept_parent_cs() {
		return dept_parent_cs;
	}

	public void setDept_parent_cs(String dept_parent_cs) {
		this.dept_parent_cs = dept_parent_cs;
	}

	public String getTemp_rank() {
		return temp_rank;
	}

	public void setTemp_rank(String temp_rank) {
		this.temp_rank = temp_rank;
	}

	public void setInsert(String insert) {
		this.insert = insert;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_parent_id() {
		return dept_parent_id;
	}

	public void setDept_parent_id(String dept_parent_id) {
		this.dept_parent_id = dept_parent_id;
	}

	public void setDept_cs(String dept_cs) {
		this.dept_cs = dept_cs;
	}

	public String getDept_parent_name() {
		return dept_parent_name;
	}

	public void setDept_parent_name(String dept_parent_name) {
		this.dept_parent_name = dept_parent_name;
	}

	public String getNewDept_cs() {
		return newDept_cs;
	}

	public void setNewDept_cs(String newDept_cs) {
		this.newDept_cs = newDept_cs;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
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
	
	public String getExt_code() {
		return ext_code;
	}

	public void setExt_code(String ext_code) {
		this.ext_code = ext_code;
	}

	@Override
	public String executeFunction() throws Exception {
		if (insert.equalsIgnoreCase("Y")) {// 添加机构操作
			if (userService.getSecDeptCountByDeptCs(dept_cs) > 0) {// 机构代码已经存在，不能重复添加
				// 添加失败是否需要给页面提示?
				logger.error("机构添加失败，机构编号:" + dept_cs + " 已经存在，不能重复添加。");
			} else {
				if (dept_level_code.equalsIgnoreCase("1")) {// 如果机构等级是单位，则没有机构类型，机构和班组才有
					dept_type_code = "";
				}
				setSecTerr_code("");
				setDept_level_code("1");
				setDept_type_code("");
				UUID uuid = UUID.randomUUID();
				Map<String, String> map = new HashMap<String, String>();
				map.put("dept_id", uuid.toString());
				map.put("dept_rank", dept_rank);
				map.put("dept_cs", dept_cs);
				map.put("dept_parent_id", dept_parent_id);
				map.put("dept_name", dept_name);
				map.put("dept_desc", dept_desc);
				map.put("secTerr_code", secTerr_code);
				map.put("dept_level_code", dept_level_code);
				map.put("dept_type_code", dept_type_code);
				map.put("dept_subsys", dept_subsys);
				map.put("ext_code", ext_code);
				userService.addSecDept(map);
				insertAdminLog("添加部门：" + dept_name);
				if (autoDept.equals("Y")) {
					List<String> user_iidds=userService.getUser_iiddsGroup();//所有用户id
					List<String> dept_ids=userService.getDept_idsIsY();//所有部门id
					dept_ids.remove(map.get("dept_id"));//去除新增的部门id
					for (String item : user_iidds) {
						List<String> role_ids=userService.getRole_idsGroup(item);
						for (String string : role_ids) {
							Map<String, String> mapAuto = new HashMap<String, String>();
							mapAuto.put("user_iidd", item);
							mapAuto.put("role_id", string);
							List<String> adminDept_ids=userService.getDept_idsByTwo(mapAuto);
							if (adminDept_ids.containsAll(dept_ids)) {
								DeptAdminConfig adminConfig = new DeptAdminConfig(item, map.get("dept_id"), "","");
								userService.configDeptAdmin1(adminConfig,string);
							}
						}
					}
				}
				return "insert";
			}
		} else {// 返回添加页面
			secDept = userService.getSecDeptById(dept_id);
			dept_cs = secDept.getDept_cs();
			List<SecDept> subDeptList = userService.getSubDeptListByDeptCs(dept_cs);
			String temp1 = "";
			String temp2 = "";
			String temp3 = "";
			String temp4 = "";// 新节点的编码
			if (subDeptList.size() > 0) {
				// 其实就是变成大整数再加1，这样计算是为防止以后机构编码可能以字母开头的情况
				// 子机构列表是按Dept_id倒序查询出来的，所以第一个的code最大
				temp1 = subDeptList.get(0).getDept_cs();// 子机构中最大的编码
				temp2 = temp1.substring(temp1.length() - 2);// 子编码的最后两位
				temp3 = temp1.substring(0, temp1.length() - 2);// 排除最后两位的编码
				int a = Integer.parseInt(temp2) + 1;
				if (a < 10) {
					temp2 = "0" + a;
				} else {
					temp2 = String.valueOf(a);
				}
				temp4 = temp3 + temp2;
			} else {
				temp4 = dept_cs + "01";
			}
			setNewDept_cs(temp4);
			setDept_parent_id(dept_id);
			setDept_parent_name(secDept.getDept_name());
			setDept_parent_cs(dept_cs);// 设置父部门cs值
			// 设置可供用户输入的排序值的默认值
			setTemp_rank(getNewDept_cs().substring(dept_parent_cs.length()));
			// secTerrList = userService.getAllSecTerritory();
			// deptLevelList = userService.getAllSecDeptLevel();
			// deptTypeList = userService.getAllSecDeptType();
		}
		return SUCCESS;
	}
}
