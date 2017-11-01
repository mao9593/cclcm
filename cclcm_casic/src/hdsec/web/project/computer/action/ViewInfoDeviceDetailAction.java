package hdsec.web.project.computer.action;

import hdsec.web.project.common.bm.BMCycleItem;
import hdsec.web.project.computer.model.EntityInfoDevice;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;

import org.springframework.util.StringUtils;

public class ViewInfoDeviceDetailAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	EntityInfoDevice device = null;
	private String device_barcode = "";
	private List<BMCycleItem> itemList = null;

	public String getDevice_barcode() {
		return device_barcode;
	}

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
	}

	public List<BMCycleItem> getItemList() {
		return itemList;
	}

	public EntityInfoDevice getDevice() {
		return device;
	}

	public void setDevice(EntityInfoDevice device) {
		this.device = device;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(device_barcode)) {
			device = computerService.getInfoDeviceByBarcode(device_barcode);
			itemList = securityUserService.getCycleItemListByBarcode(device_barcode);
			return SUCCESS;
		} else {
			throw new Exception("无法查询条码号，请重新尝试。");
		}
	}
}