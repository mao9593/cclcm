package hdsec.web.project.borrow.action;

import org.springframework.util.StringUtils;

/**
 * 修改借阅状态，是否归还
 * 2014-4-22 上午12:58:46
 * 
 * @author gaoximin
 */
public class UpdateBorrowStatusAction extends BorrowBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code;
	private String entity_type;
	private String barcode;
	
	public String getEvent_code() {
		return event_code;
	}
	
	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}
	
	public String getEntity_type() {
		return entity_type;
	}
	
	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}
	
	public String getBarcode() {
		return barcode;
	}
	
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			borrowService.updateBorrowStatus(event_code, entity_type, barcode);
		}
		insertCommonLog("归还借阅文件[" + event_code + "]");
		return SUCCESS;
	}
}
