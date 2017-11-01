package hdsec.web.project.basic.action;

import hdsec.web.project.user.model.SecUser;

public class GetApplyFileAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String item_id;
	private String type;
	private String ajax_type = "";

	public void setAjax_type(String ajax_type) {
		this.ajax_type = ajax_type;
	}

	public String getUser_name() {
		return getCurUser().getUser_name();
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String executeFunction() throws Exception {
		SecUser user = getCurUser();
		basicService.submitApplyFile(item_id, type, user);// item_id是载体的id
		insertCommonLog("确认接收归档文件[" + item_id + "]");
		return ajax_type.equalsIgnoreCase("ajax") ? null : type;
	}
}
