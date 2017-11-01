package hdsec.web.project.user.model;

import hdsec.web.project.common.model.UserBaseDomain;

/**
 * 说明: 操作权限
 */
public class SecOperation extends UserBaseDomain {

	private String oper_code = "";
	private String oper_name = "";
	private String oper_desc = "";
	private String parent_code = "";
	private String subsys_code = "";
	private String web_url = "";
	private String icon_path = "";
	private String web_mark = "";
	private String server_mark = "";
	private String en_prvt_oper = "";
	private String en_directory = "";
	private String dir_rank = ""; // 目录排序

	public String getSubsys_code() {
		return subsys_code;
	}

	public void setSubsys_code(String subsys_code) {
		this.subsys_code = subsys_code;
	}

	public String getOper_code() {
		return oper_code;
	}

	public void setOper_code(String oper_code) {
		this.oper_code = oper_code;
	}

	public String getOper_name() {
		return oper_name;
	}

	public void setOper_name(String oper_name) {
		this.oper_name = oper_name;
	}

	public String getEn_directory() {
		return en_directory;
	}

	public void setEn_directory(String en_directory) {
		this.en_directory = en_directory;
	}

	public String getOper_desc() {
		return oper_desc;
	}

	public void setOper_desc(String oper_desc) {
		this.oper_desc = oper_desc;
	}

	public String getParent_code() {
		return parent_code;
	}

	public void setParent_code(String parent_code) {
		this.parent_code = parent_code;
	}

	public String getEn_prvt_oper() {
		return en_prvt_oper;
	}

	public void setEn_prvt_oper(String en_prvt_oper) {
		this.en_prvt_oper = en_prvt_oper;
	}

	public String getWeb_url() {
		return web_url;
	}

	public void setWeb_url(String web_url) {
		this.web_url = web_url;
	}

	public String getIcon_path() {
		return icon_path;
	}

	public void setIcon_path(String icon_path) {
		this.icon_path = icon_path;
	}

	public String getWeb_mark() {
		return web_mark;
	}

	public void setWeb_mark(String web_mark) {
		this.web_mark = web_mark;
	}

	public String getServer_mark() {
		return server_mark;
	}

	public void setServer_mark(String server_mark) {
		this.server_mark = server_mark;
	}

	public String getDir_rank() {
		return dir_rank;
	}

	public void setDir_rank(String dir_rank) {
		this.dir_rank = dir_rank;
	}

	public SecOperation() {
		super();
	}

	public SecOperation(String oper_code, String oper_name, String oper_desc, String en_directory, String en_prvt_oper,
			String subsys_code) {
		super();
		this.oper_code = oper_code;
		this.oper_name = oper_name;
		this.oper_desc = oper_desc;
		this.en_directory = en_directory;
		this.en_prvt_oper = en_prvt_oper;
		this.subsys_code = subsys_code;
	}

	public SecOperation(String oper_code, String oper_name, String oper_desc, String subsys_code, String web_url,
			String icon_path, String web_mark, String server_mark, String en_prvt_oper, String en_directory) {
		super();
		this.oper_code = oper_code;
		this.oper_name = oper_name;
		this.oper_desc = oper_desc;
		this.subsys_code = subsys_code;
		this.web_url = web_url;
		this.icon_path = icon_path;
		this.web_mark = web_mark;
		this.server_mark = server_mark;
		this.en_prvt_oper = en_prvt_oper;
		this.en_directory = en_directory;
	}

	public String getOperType() {
		if (en_directory.equalsIgnoreCase("Y")) {
			if (oper_code.length() == 2) {
				return "子系统根节点";
			} else if (oper_code.length() == 4) {
				return "一级目录节点";
			} else if (oper_code.length() == 6) {
				return "二级目录节点";
			}
		} else if (en_directory.equalsIgnoreCase("N")) {
			if (oper_code.length() == 8) {
				return "一级功能节点";
			} else if (oper_code.length() == 10) {
				return "二级目录节点";
			} else if (oper_code.length() >= 12) {
				return "次级目录节点";
			}
		}
		return "非法节点";
	}

	public static String getOperTypeByCode(String oper_code) {
		String name = "";
		switch (oper_code.length()) {
		case 2:
			name = "子系统根节点";
			break;
		case 4:
			name = "一级目录节点";
			break;
		case 6:
			name = "二级目录节点";
			break;
		case 8:
			name = "一级功能节点";
			break;
		case 10:
			name = "二级功能节点";
			break;
		default:
			name = "次级功能节点";
			break;
		}
		return name;
	}
}