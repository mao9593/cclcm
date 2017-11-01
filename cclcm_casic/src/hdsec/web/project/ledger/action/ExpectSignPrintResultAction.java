package hdsec.web.project.ledger.action;

import java.util.HashMap;
import java.util.Map;


/**
 * 预台账打印标记
 * 
 * @author zp
 * 
 */
public class ExpectSignPrintResultAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String paper_id = "";
	private String paper_barcode = "";
	private String fail_remark = "";
	private String sign_ok = "N";
	private String fail_remark_tips = "";
	private String real_page_count = null;

	public String getPaper_id() {
		return paper_id;
	}

	public void setPaper_id(String paper_id) {
		this.paper_id = paper_id;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode;
	}

	public String getFail_remark() {
		return fail_remark;
	}

	public void setFail_remark(String fail_remark) {
		this.fail_remark = fail_remark;
	}

	public String getSign_ok() {
		return sign_ok;
	}

	public void setSign_ok(String sign_ok) {
		this.sign_ok = sign_ok;
	}

	public String getFail_remark_tips() {
		return fail_remark_tips;
	}

	public void setFail_remark_tips(String fail_remark_tips) {
		this.fail_remark_tips = fail_remark_tips;
	}

	public String getReal_page_count() {
		return real_page_count;
	}

	public void setReal_page_count(String real_page_count) {
		this.real_page_count = real_page_count;
	}

	@Override
	public String executeFunction() throws Exception {
		if (sign_ok.equals("Y")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("paper_id", paper_id);
			map.put("fail_remark", fail_remark);
			map.put("real_page_count", real_page_count);
			ledgerService.ExpectsignPaperSuccess(map);
			insertCommonLog("标记文件[" + paper_barcode + "]打印成功");
			return "ok";
		} else {
			return SUCCESS;
		}
	}

}
