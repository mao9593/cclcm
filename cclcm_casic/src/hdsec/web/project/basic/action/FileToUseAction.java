package hdsec.web.project.basic.action;

import hdsec.web.project.user.model.SecUser;

/**
 * 将已归档文件/光盘还原为留用状态
 * 
 * @author lixiang
 */
public class FileToUseAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String item_id;
	private String type;
	
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
	
	public String getUser_name() {
		return getCurUser().getUser_name();
	}
	
	@Override
	public String executeFunction() throws Exception {
		SecUser user = getCurUser();
		basicService.fileToUse(item_id, type, user);
		insertCommonLog("将归档文件[" + item_id + "]还原为留用");
		return type;
	}
}
