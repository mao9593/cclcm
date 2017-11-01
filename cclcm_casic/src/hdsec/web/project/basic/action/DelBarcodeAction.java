package hdsec.web.project.basic.action;

/**
 * 删除条码
 * 
 * @author yy
 * 
 */
public class DelBarcodeAction extends BasicBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String barcode_code;
	
	@Override
	public String executeFunction() throws Exception {
		if (barcode_code.isEmpty()) {
			throw new Exception("参数错误，没有条码规则编号");
		} else {
			basicService.delBarcodeByCode(barcode_code);
			insertAdminLog("删除条码：编号[" + barcode_code + "]");
		}
		return "ok";
	}
	
	public void setBarcode_code(String barcode_code) {
		this.barcode_code = barcode_code;
	}
}
