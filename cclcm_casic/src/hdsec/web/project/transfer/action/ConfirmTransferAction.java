package hdsec.web.project.transfer.action;

/**
 * 确认流转处理
 * 
 * @author lixiang
 * 
 */

public class ConfirmTransferAction extends TransferBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code;
	private String type;
	private String barcode;
	private String result;

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Override
	public String executeFunction() throws Exception {
		// if(basicService.isConfirmOpen("TRANSFER_CONFIRM")){//流转交接确认开启
		// transferService.addConfirmRecord(event_code, type, barcode);
		// result = "confirm";
		// }else{//流转交接确认关闭
		transferService.confirmTransfer(event_code, type, barcode);
		insertCommonLog("确认流转载体[" + barcode + "]");
		result = "done";
		// }
		return SUCCESS;
	}
}
