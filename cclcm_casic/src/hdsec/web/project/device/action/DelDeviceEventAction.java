package hdsec.web.project.device.action;

/**
 * 删除磁介质借入作业
 * 
 * @author lixiang
 * 
 */
public class DelDeviceEventAction extends DeviceBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String result = "";
	
	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}
	
	public String getResult() {
		return result;
	}
	
	@Override
	public String executeFunction() throws Exception {
		deviceService.delDeviceEventByDeviceCode(event_code);
		insertCommonLog("删除磁介质借入作业[" + event_code + "]");
		result = "删除成功";
		return SUCCESS;
	}
}
