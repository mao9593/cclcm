package hdsec.web.project.computer.action;

import hdsec.web.project.computer.model.InfoType;

import java.util.HashMap;
import java.util.Map;

/**
 * 修改信息设备类型
 * 
 * @author guojiao
 * 
 */
public class UpdateInfoTypeAction extends ComputerBaseAction {
	private static final long serialVersionUID = 1L;
	private InfoType type = null;
	private String id = null;
	private String typename = "";
	private String content = "";
	private String update = "N";

	public InfoType getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {
			if (computerService.isTypeExistByName(typename)) {
				throw new Exception("修改的类型名称已存在，请重新修改。");
			}
			type = new InfoType();
			type.setInfo_id(id);
			type.setInfo_type(typename);
			type.setSumm(content);
			computerService.updateInfoType(type);
			insertCommonLog("修改信息设备类型:" + typename);
			return "update";
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("info_id", id);
			type = computerService.getInfoTypeByID(map);
		}
		return SUCCESS;
	}
}
