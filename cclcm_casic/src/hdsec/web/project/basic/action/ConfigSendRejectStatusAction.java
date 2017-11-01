package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConfigItem;

/**
 * 配置载体拒收后状态:“留用”与“已回收”互换
 * 
 * @author lixiang
 * 
 */
public class ConfigSendRejectStatusAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String state = "";

	public String getState() {
		return state;
	}

	@Override
	public String executeFunction() throws Exception {
		state = basicService.getSysConfigItemValue(SysConfigItem.KEY_SEND_REJECT_STATUS).getItem_value();
		if ("0".equals(state)) {
			basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_SEND_REJECT_STATUS,
					SysConfigItem.NAME_SEND_REJECT_STATUS, "1", SysConfigItem.TYPE_SEND, 1));
		} else if ("1".equals(state)) {
			basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_SEND_REJECT_STATUS,
					SysConfigItem.NAME_SEND_REJECT_STATUS, "0", SysConfigItem.TYPE_SEND, 1));
		}
		return SUCCESS;
	}
}
