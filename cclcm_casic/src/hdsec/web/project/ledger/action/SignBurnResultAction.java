package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.user.model.SecUser;

/**
 * 标记光盘
 * 
 * @author yueying
 * 
 */
public class SignBurnResultAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String cd_id = "";
	private String fail_remark = "";
	private String sign_ok = "N";
	private String cd_barcode = "";
	private String fail_remark_tips = "";
	private String type = "CD";
	private String file_num = "";
	private String file_list = "";
	private String isRetrieveCD = "N";

	public String getIsRetrieveCD() {
		return isRetrieveCD;
	}

	public void setIsRetrieveCD(String isRetrieveCD) {
		this.isRetrieveCD = isRetrieveCD;
	}

	public String getFile_num() {
		return file_num;
	}

	public void setFile_num(String file_num) {
		this.file_num = file_num;
	}

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode;
	}

	public String getCd_id() {
		return cd_id;
	}

	public void setCd_id(String cd_id) {
		this.cd_id = cd_id;
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

	@Override
	public String executeFunction() throws Exception {
		if (sign_ok.equals("Y")) {
			if (isRetrieveCD.equals("Y")) {// 如果选择了“已现场回收”则执行回收操作
				EntityCD cd = ledgerService.getCDByBarcode(cd_barcode);
				SecUser user = getCurUser();
				ledgerService.submitRetrieveCD(cd.getCd_id().toString(), user);
				insertCommonLog("回收光盘：条码[" + cd.getCd_barcode() + "]");

			}
			ledgerService.signCD(cd_id, file_num, file_list, fail_remark);
			insertCommonLog("标记光盘[" + cd_barcode + "]刻录失败");
			return "ok";
		} else {
			EntityCD cd = ledgerService.getCDById(cd_id);
			if (cd.getFail_remark() != null && !cd.getFail_remark().equals("")) {
				fail_remark_tips = cd.getFail_remark();
			}
			if (cd.getFile_list() != null && !cd.getFile_list().equals("")) {
				file_list = cd.getFile_list();
			}
			if (cd.getFile_num() != null && !cd.getFile_num().equals("")) {
				file_num = cd.getFile_num().toString();
			}
			return SUCCESS;
		}
	}
}
