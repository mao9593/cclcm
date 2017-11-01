package hdsec.web.project.user.action;

import hdsec.web.project.common.util.Constants;
import hdsec.web.project.user.model.SecOperation;

import org.springframework.util.StringUtils;

/**
 * 修改操作节点
 * @author renmingfei
 *
 */
public class UpdateSecOperAction extends UserBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String subsys_code = "";
	private String update = "N";
	private String oper_name = "";
	private String en_directory = "";
	private String oper_desc = "";
	private String oper_code = "";
	private String web_url = "";
	private String icon_path = "";
	private String web_mark = "";
	private String server_mark = "";
	private SecOperation secOper = null;
	
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
	
	public void setUpdate(String update) {
		this.update = update;
	}
	
	public SecOperation getSecOper() {
		return secOper;
	}
	
	public void setOper_name(String oper_name) {
		this.oper_name = oper_name;
	}
	
	public void setEn_directory(String en_directory) {
		this.en_directory = en_directory;
	}
	
	public void setOper_desc(String oper_desc) {
		this.oper_desc = oper_desc;
	}
	
	public void setWeb_url(String web_url) {
		this.web_url = web_url.trim();
	}
	
	public void setIcon_path(String icon_path) {
		this.icon_path = icon_path;
	}
	
	public void setWeb_mark(String web_mark) {
		this.web_mark = web_mark;
	}
	
	public void setServer_mark(String server_mark) {
		this.server_mark = server_mark;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {//修改操作
			//操作编码不允许修改
			if (!StringUtils.hasLength(icon_path)) {
				icon_path = Constants.DEFAULT_ICON_PATH;
			}
			secOper = new SecOperation(oper_code, oper_name, oper_desc, subsys_code, web_url, icon_path, web_mark,
					server_mark, "N", en_directory);
			userService.updateSecOper(secOper);
			return "update";
		} else {//进入修改页面
			secOper = userService.getSecOperByCodeAndSubsys(oper_code, subsys_code);
		}
		return SUCCESS;
	}
}
