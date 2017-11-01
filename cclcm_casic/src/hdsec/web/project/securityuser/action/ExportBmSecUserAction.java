package hdsec.web.project.securityuser.action;

import hdsec.web.project.common.bm.BMCommonExportAction;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 人员信息台账导出（保密平台）
 * 
 * @author guojiao 2015-11-5
 */
public class ExportBmSecUserAction extends BMCommonExportAction {

	private static final long serialVersionUID = 1L;
	private String user_id = "";
	private String base_name = "";
	private String dept_id = "";
	private String post_id = "";
	private String security_code = "";
	private Integer user_statue = null;
	private Integer secret_statue = null;
	private String dept_ids = "";
	private List<String> depts = null;
	private String type = "";

	private final String filename = "人员信息台账-" + sdf1.format(new Date()) + ".xls";
	private final String sheetName = "人员信息台账";
	private final String[] titles = { "序号", "部门", "姓名", "性别", "国籍", "民族", "籍贯", "出生日期", "政治面貌", "学历", "身份证号", "电子邮箱",
			"联系电话", "户籍所在地", "现住址", "职务", "职称", "参加工作时间", "涉密等级", "婚姻状况", "绿卡或永久居留证信息", "岗位类别", "是否有海外经历",
			"配偶子女是否有海外经历", "人员状态", "脱密期", "涉密文件总数", "涉密光盘总数" };

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public void setBase_name(String base_name) {
		this.base_name = base_name;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}

	public void setSecurity_code(String security_code) {
		this.security_code = security_code;
	}

	public void setUser_statue(Integer user_statue) {
		this.user_statue = user_statue;
	}

	public void setSecret_statue(Integer secret_statue) {
		this.secret_statue = secret_statue;
	}

	public void setDept_ids(String dept_ids) {
		this.dept_ids = dept_ids;
	}

	// 前台部门选择框为多选，将多选id存到list类型中在数据库进行查询
	private void getAllDept(String dept) {
		depts = new ArrayList<String>();
		String temp[] = dept.split(",");
		for (int i = 0; i < temp.length; i++) {
			depts.add(temp[i]);
		}
	}

	@Override
	public String executeFunction() throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_id);
		map.put("user_name", base_name);
		if (!dept_id.equals("")) {
			getAllDept(dept_id);
			map.put("dept_id", depts);
		}
		map.put("post_id", post_id);
		map.put("security_code", security_code);
		if (type.equals("DEPT")) {
			map.put("scope_dept_ids", dept_ids.split(","));
		}
		map.put("user_statue", user_statue);
		map.put("secret_statue", secret_statue);

		OutputStream os = createFile(filename);
		exportFileBM(os, map, sheetName, titles, "secuser");
		return SUCCESS;
	}

}