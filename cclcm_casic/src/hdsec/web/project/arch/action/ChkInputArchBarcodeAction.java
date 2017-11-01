package hdsec.web.project.arch.action;

import hdsec.web.project.arch.service.ArchService;

import javax.annotation.Resource;

public class ChkInputArchBarcodeAction {

	@Resource
	private ArchService archService;
	private String arch_barcode = "";

	private String chkResult = "";

	public String getArch_barcode() {
		return arch_barcode;
	}

	public void setArch_barcode(String arch_barcode) {
		this.arch_barcode = arch_barcode;
	}

	public String getChkResult() {
		return chkResult;
	}

	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}

	public String execute() {
		int count = archService.getCountArchBarcodeByArchbarcode(arch_barcode);
		if (count == 0) {
			setChkResult("false");
		} else {
			setChkResult("true");
		}
		return "success";
	}

}
