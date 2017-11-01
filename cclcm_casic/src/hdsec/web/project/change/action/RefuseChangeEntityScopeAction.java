package hdsec.web.project.change.action;

/**
 * 个人拒绝变换载体归属
 * 
 * @author guojiao
 * 
 */

public class RefuseChangeEntityScopeAction extends ChangeBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String barcode = "";

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Override
	public String executeFunction() throws Exception {
		if (!event_code.equals("") && !barcode.equals("")) {
			changeService.refuseChangeEntityScope(event_code, barcode);
		}
		insertCommonLog("拒绝变更载体[" + barcode + "]归属");
		return null;
	}
}
