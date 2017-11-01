package hdsec.web.project.device.action;

import hdsec.web.project.device.model.DeviceStatusEnum;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.ledger.model.CycleItem;

import java.util.Date;

/**
 * 更新磁介质状态：重置、送修、销毁处理
 * 
 * @author lixiang
 * 
 */
public class UpdateDeviceStatusAction extends DeviceBaseAction {
	private static final long serialVersionUID = 1L;
	private String device_code;
	private Integer device_status;
	private String result = "";
	private String type = "";

	public void setType(String type) {
		this.type = type;
	}

	public String getDevice_code() {
		return device_code;
	}

	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}

	public Integer getDevice_status() {
		return device_status;
	}

	public void setDevice_status(Integer device_status) {
		this.device_status = device_status;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String executeFunction() throws Exception {
		if (device_code.isEmpty()) {
			setResult("参数错误，没有磁介质编号");
		} else {
			// 更新介质状态
			DeviceStatusEnum status = DeviceStatusEnum.valueOf("DS" + device_status);
			deviceService.updateDeviceStatus(device_code, status);
			EntityDevice device = deviceService.getDeviceByCode(device_code);
			// 生成载体生命周期记录
			CycleItem cycleitem = new CycleItem();
			cycleitem.setBarcode(device.getDevice_barcode());
			cycleitem.setEntity_type("DEVICE");
			cycleitem.setOper_time(new Date());
			cycleitem.setUser_name(getCurUser().getUser_name());
			cycleitem.setDept_name(getCurUser().getDept_name());
			// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
			// 此处不用启动流程，job_code设置为default
			cycleitem.setJob_code("default");

			EntityDevice entityDevice = deviceService.getDeviceByCode(device_code);
			String device_barcode = "";
			if (entityDevice != null) {
				device_barcode = entityDevice.getDevice_barcode();
			}
			if (device_status == DeviceStatusEnum.DS0.getKey()) {// 重置
				cycleitem.setOper("RESET");
				ledgerService.addCycleItem(cycleitem);
				setResult("已还原为在档可借");
				insertCommonLog("重置磁介质[" + device_barcode + "]的状态为在档可借");
			} else if (device_status == DeviceStatusEnum.DS6.getKey()) {// 送修
				cycleitem.setOper("REPAIR");
				ledgerService.addCycleItem(cycleitem);
				setResult("已送修");
				insertCommonLog("送修磁介质[" + device_barcode + "]");
			} else {// 销毁
				cycleitem.setOper("DESTORY");
				deviceService.delDeviceByCode(device_code, cycleitem);
				insertCommonLog("封存磁介质[" + device_barcode + "]");
				return type.equalsIgnoreCase("ajax") ? null : "destroy";
			}
		}
		return SUCCESS;
	}

}
