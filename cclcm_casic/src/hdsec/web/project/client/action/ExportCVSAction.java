package hdsec.web.project.client.action;

import hdsec.web.project.ledger.action.CommonExportAction;

import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 导出客户端列表
 * 
 * @author lixiang
 * 
 */
public class ExportCVSAction extends CommonExportAction {
	private static final long serialVersionUID = 1L;

	private String user_iidd = "";
	private String user_name = "";
	private String dept_id = "";
	private String dept_name = "";
	private final String filename = "客户端列表-" + sdf1.format(new Date()) + ".xls";
	private final String sheetName = "客户端列表";
	private final String[] titles = { "序号", "用户名", "部门", "计算机名", "IP地址", "当前版本", "初始版本", "安装时间", "上次更新时间", "操作系统版本",
			"上次在线时间", "卸载时间", "设定客户端版本" };

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_name", user_name);
		map.put("dept_id", dept_id);
		map.put("is_sealed", "N");
		OutputStream os = createFile(filename);
		exportFile(os, map, sheetName, titles, "cvs");
		return null;
	}
}
