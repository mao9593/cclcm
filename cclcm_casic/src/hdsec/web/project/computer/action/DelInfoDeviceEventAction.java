package hdsec.web.project.computer.action;

/**
 * 撤销信息设备申请记录
 * 
 * @author guojiao
 */
public class DelInfoDeviceEventAction extends ComputerBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String type = "";
	private String device_barcode = "";
	private String style = "";

	public void setStyle(String style) {
		this.style = style;
	}

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	@Override
	public String executeFunction() throws Exception {
		if (!device_barcode.equals("")) {
			if (type.equals("NETWORK")) {
				computerService.deleteNetworkDevice(device_barcode);
				insertCommonLog("删除网络信息设备[" + device_barcode + "]");
				return "NETWORK";
			} else {
				computerService.deleteEntityInfoDevice(device_barcode);
				insertCommonLog("删除信息设备[" + device_barcode + "]");
				return "ok_device";
			}
		}
		if (!event_code.equals("")) {
			if (style.equals("book")) {
				computerService.deleteBorrowBookEventByEventCode(event_code);
				insertCommonLog("删除借用笔记本申请[" + event_code);
				return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
			} else {
				computerService.delInfoDeviceEvent(event_code);
				insertCommonLog("删除信息设备申请作业[" + event_code);
				return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
			}

		}
		return "";
	}
}
