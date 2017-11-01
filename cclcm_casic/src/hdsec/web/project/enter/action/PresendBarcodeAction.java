package hdsec.web.project.enter.action;

public class PresendBarcodeAction extends EnterBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String barcode_type = "";
	private Integer barcode_num = null;

	private String medium_barcode = "";// 通用载体条码，打印条码传值使用

	public String getMedium_barcode() {
		return medium_barcode;
	}

	public String getBarcode_type() {
		return barcode_type;
	}

	public void setBarcode_type(String barcode_type) {
		this.barcode_type = barcode_type;
	}

	public Integer getBarcode_num() {
		return barcode_num;
	}

	public void setBarcode_num(Integer barcode_num) {
		this.barcode_num = barcode_num;
	}

	@Override
	public String executeFunction() throws Exception {
		if (barcode_type.equals("")) {
			return "ok";
		} else {
			try {
				String barcode = "";// 载体条码
				for (int i = 0; i < barcode_num; i++) {
					if (barcode_type.equals("file")) {
						barcode = basicService.createEntityBarcode("ENTERPAPER");
					} else if (barcode_type.equals("cd")) {
						barcode = basicService.createEntityBarcode("ENTERCD");
					}
					medium_barcode = medium_barcode + "1#" + barcode + "#" + barcode + "#" + barcode + ":";
				}
				insertCommonLog("预发条码[" + medium_barcode + "]");
				return SUCCESS;
			} catch (Exception e) {
				logger.error("Exception" + e.getMessage());
				throw new Exception("执行预发条码出现异常");
			}
		}
	}

}