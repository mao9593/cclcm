package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysExchangeBox;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;
import java.util.UUID;

public class AddExchangeBoxAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String box_name = "";//柜子名称
	private String box_location = "";//所在地点
	private Integer seclv_code = null;//密级
	private Integer box_status = null;//回收柜状态（0空、1已存、2损坏、3打开）
	
	public void setBox_name(String box_name) {
		this.box_name = box_name;
	}
	
	public void setBox_location(String box_location) {
		this.box_location = box_location;
	}
	
	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}
	
	public void setBox_status(Integer box_status) {
		this.box_status = box_status;
	}
	
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (box_name.isEmpty()) {
			return SUCCESS;
		} else {
			String box_code = "ECBOX-" + String.valueOf(UUID.randomUUID().toString());
			SysExchangeBox exchangeBox = new SysExchangeBox(box_code, box_name, box_location, seclv_code, box_status);
			basicService.addSysExchangeBox(exchangeBox);
			insertAdminLog("添加智能交换柜[" + box_name + "]");
			return "ok";
		}
	}
}
