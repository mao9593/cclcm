package hdsec.web.project.copy.action;

/**
 * 更新打印条码状态
 * 
 * @author lixiang
 * 
 */
public class UpdateIsbarcodeAction extends CopyBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code;// 作业号
	private Integer is_barcode;// 是否已打印条码
	
	public String getEvent_code() {
		return event_code;
	}
	
	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}
	
	public Integer getIs_barcode() {
		return is_barcode;
	}
	
	public void setIs_barcode(Integer is_barcode) {
		this.is_barcode = is_barcode;
	}
	
	@Override
	public String executeFunction() throws Exception {
		
		copyService.updateIsbarcode(event_code, is_barcode);
		insertCommonLog("event_code[" + event_code + "]的复印申请已打印条码");
		return SUCCESS;
		
	}
	
}
