package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConfigItem;

/**
 * 回收送销开关配置
 * 
 * @author fyp
 * 
 */
public class ConfigRecoverDestroyAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer recover_on_off = 0;
	private Integer senddestroy_on_off = 0;
	private String update = "N";
	private String type = "";
	private String typ = "";

	public Integer getRecover_on_off() {
		return recover_on_off;
	}

	public void setRecover_on_off(Integer recover_on_off) {
		this.recover_on_off = recover_on_off;
	}

	public Integer getSenddestroy_on_off() {
		return senddestroy_on_off;
	}

	public void setSenddestroy_on_off(Integer senddestroy_on_off) {
		this.senddestroy_on_off = senddestroy_on_off;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
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

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {
			if (typ.equals("recover")) {
				if (type.equalsIgnoreCase("open")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_Recover_On_Off,
							SysConfigItem.NAME_Recover_On_Off, null, SysConfigItem.TYPE_Recover_On_Off, 1));
				}
				if (type.equalsIgnoreCase("close")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_Recover_On_Off,
							SysConfigItem.NAME_Recover_On_Off, null, SysConfigItem.TYPE_Recover_On_Off, 0));
				}
			} else if (typ.equals("destroy")) {
				if (type.equalsIgnoreCase("open")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_SendDestroy_On_Off,
							SysConfigItem.NAME_SendDestroy_On_Off, null, SysConfigItem.TYPE_SendDestroy_On_Off, 1));
				}
				if (type.equalsIgnoreCase("close")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_SendDestroy_On_Off,
							SysConfigItem.NAME_SendDestroy_On_Off, null, SysConfigItem.TYPE_SendDestroy_On_Off, 0));
				}
			}

		} else {
			recover_on_off = basicService.getSysConfigItemValue(SysConfigItem.KEY_Recover_On_Off).getStartuse();
			senddestroy_on_off = basicService.getSysConfigItemValue(SysConfigItem.KEY_SendDestroy_On_Off).getStartuse();
		}
		return SUCCESS;
	}
}
