package hdsec.web.project.client.action;

import hdsec.web.project.basic.model.SysConfigItem;
import hdsec.web.project.client.model.SysCVS;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户端版本管理列表
 * 
 * @author gaoxm
 * 
 */
public class ManageCVSAction extends ClientBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SysCVS> cVSList = null;
	private String user_iidd = "";
	private String user_name = "";
	private String dept_id = "";
	private String dept_name = "";
	private Integer message_tips_start = 1;
	private String update = "N";
	private String type = "";
	private String module = "";

	public Integer getMessage_tips_start() {
		return message_tips_start;
	}

	public void setMessage_tips_start(Integer message_tips_start) {
		this.message_tips_start = message_tips_start;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

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

	public List<SysCVS> getCVSList() {
		return cVSList;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_name", user_name);
		map.put("dept_id", dept_id);
		map.put("is_sealed", "N");
		cVSList = clientService.getCVSListByCondition(map);
		if (update.equalsIgnoreCase("Y")) {
			if (module.equalsIgnoreCase("CLIENT_MSG")) {
				if (type.equalsIgnoreCase("open")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_CLIENT_MSG,
							SysConfigItem.NAME_CLIENT_MSG, null, SysConfigItem.TYPE_MESSAGE, 1));
					insertAdminLog("开启消息提醒");
				}
				if (type.equalsIgnoreCase("close")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_CLIENT_MSG,
							SysConfigItem.NAME_CLIENT_MSG, null, SysConfigItem.TYPE_MESSAGE, 0));
					insertAdminLog("关闭消息提醒");
				}
			}
		} else {
			message_tips_start = basicService.getSysConfigItemValue(SysConfigItem.KEY_CLIENT_MSG).getStartuse();
		}
		return SUCCESS;
	}
}
