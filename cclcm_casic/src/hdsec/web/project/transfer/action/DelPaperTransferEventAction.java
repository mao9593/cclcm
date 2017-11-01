package hdsec.web.project.transfer.action;

import hdsec.web.project.basic.service.BasicService;

import javax.annotation.Resource;

public class DelPaperTransferEventAction extends TransferBaseAction {
	private static final long serialVersionUID = 1L;
	
	@Resource
	protected BasicService basicService;
	
	private String event_code;
	private String barcode;
	
	@Override
	public String executeFunction() throws Exception {
		
		transferService.deleteEventTransfer(event_code, barcode);
		return "ok";
	}
	
	public String getEvent_code() {
		return event_code;
	}
	
	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}
	
	public String getBarcode() {
		return barcode;
	}
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
}
