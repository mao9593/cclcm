package hdsec.web.project.ledger.model;

import hdsec.web.project.common.BaseDomain;

/**
 * 一维码，二维码
 * 
 * @author gaoximin 2015-9-7
 */
public class BarcodeCompare extends BaseDomain {
	private String barcode = "";// 一维码，条码号
	private String pdfcode = "";// PDF417码内容

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getPdfcode() {
		return pdfcode;
	}

	public void setPdfcode(String pdfcode) {
		this.pdfcode = pdfcode;
	}

	public BarcodeCompare() {
		super();
	}

	public BarcodeCompare(String barcode, String pdfcode) {
		this.barcode = barcode;
		this.pdfcode = pdfcode;
	}
}
