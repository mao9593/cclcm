package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConfigItem;

/**
 * 消息提醒开关设置
 * 
 * @author fyp
 * 
 */
public class ConfigRemindAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private Integer scan_on_off = 0;
	private Integer transfer_on_off = 0;
	private Integer approve_on_off = 0;
	private Integer borrow_on_off = 0;
	private Integer retrieve_on_off = 0;
	private String update = "N";
	private String type = "";
	private String typ = "";

	public Integer getScan_on_off() {
		return scan_on_off;
	}

	public void setScan_on_off(Integer scan_on_off) {
		this.scan_on_off = scan_on_off;
	}

	public Integer getTransfer_on_off() {
		return transfer_on_off;
	}

	public void setTransfer_on_off(Integer transfer_on_off) {
		this.transfer_on_off = transfer_on_off;
	}

	public Integer getApprove_on_off() {
		return approve_on_off;
	}

	public void setApprove_on_off(Integer approve_on_off) {
		this.approve_on_off = approve_on_off;
	}

	public Integer getBorrow_on_off() {
		return borrow_on_off;
	}

	public void setBorrow_on_off(Integer borrow_on_off) {
		this.borrow_on_off = borrow_on_off;
	}

	public Integer getRetrieve_on_off() {
		return retrieve_on_off;
	}

	public void setRetrieve_on_off(Integer retrieve_on_off) {
		this.retrieve_on_off = retrieve_on_off;
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

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {
			if (typ.equals("approve")) {
				if (type.equalsIgnoreCase("open")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_APPROVE_ON_OFF,
							SysConfigItem.NAME_APPROVE_ON_OFF, null, SysConfigItem.TYPE_APPROVE_ON_OFF, 1));
				}
				if (type.equalsIgnoreCase("close")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_APPROVE_ON_OFF,
							SysConfigItem.NAME_APPROVE_ON_OFF, null, SysConfigItem.TYPE_APPROVE_ON_OFF, 0));
				}
			} else if (typ.equals("retrieve")) {
				if (type.equalsIgnoreCase("open")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_RETRIEVE_ON_OFF,
							SysConfigItem.NAME_RETRIEVE_ON_OFF, null, SysConfigItem.TYPE_RETRIEVE_ON_OFF, 1));
				}
				if (type.equalsIgnoreCase("close")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_RETRIEVE_ON_OFF,
							SysConfigItem.NAME_RETRIEVE_ON_OFF, null, SysConfigItem.TYPE_RETRIEVE_ON_OFF, 0));
				}
			} else if (typ.equals("transfer")) {
				if (type.equalsIgnoreCase("open")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_TRANSFER_ON_OFF,
							SysConfigItem.NAME_TRANSFER_ON_OFF, null, SysConfigItem.TYPE_TRANSFER_ON_OFF, 1));
				}
				if (type.equalsIgnoreCase("close")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_TRANSFER_ON_OFF,
							SysConfigItem.NAME_TRANSFER_ON_OFF, null, SysConfigItem.TYPE_TRANSFER_ON_OFF, 0));
				}
			} else if (typ.equals("borrow")) {
				if (type.equalsIgnoreCase("open")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_BORROW_ON_OFF,
							SysConfigItem.NAME_BORROW_ON_OFF, null, SysConfigItem.TYPE_BORROW_ON_OFF, 1));
				}
				if (type.equalsIgnoreCase("close")) {
					basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_BORROW_ON_OFF,
							SysConfigItem.NAME_BORROW_ON_OFF, null, SysConfigItem.TYPE_BORROW_ON_OFF, 0));
				}

			}
		} else {

			approve_on_off = basicService.getSysConfigItemValue(SysConfigItem.KEY_APPROVE_ON_OFF).getStartuse();
			retrieve_on_off = basicService.getSysConfigItemValue(SysConfigItem.KEY_RETRIEVE_ON_OFF).getStartuse();
			transfer_on_off = basicService.getSysConfigItemValue(SysConfigItem.KEY_TRANSFER_ON_OFF).getStartuse();
			borrow_on_off = basicService.getSysConfigItemValue(SysConfigItem.KEY_BORROW_ON_OFF).getStartuse();
		}
		return SUCCESS;
	}
}
