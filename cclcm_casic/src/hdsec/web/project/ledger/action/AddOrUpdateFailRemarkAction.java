package hdsec.web.project.ledger.action;

import java.text.SimpleDateFormat;

public class AddOrUpdateFailRemarkAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	private String barcode = "";
	private String comment = "";
	private String tag = "";

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String executeFunction() throws Exception {
		comment = "销毁文件备注：" + comment;
		if ("Y".equals(tag)) {
			SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			ledgerService.addOrUpdateFailRemarkByBarcodeAndTime(barcode, comment);
			insertCommonLog("销毁管理员将条码为：" + barcode + "的文件再次销毁时间是" + date);
		} else {
			ledgerService.addOrUpdateFailRemarkByBarcode(barcode, comment);
		}
		return SUCCESS;
	}

}
