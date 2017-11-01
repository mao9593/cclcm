package hdsec.web.project.ledger.action;

import hdsec.web.project.user.model.SecUser;

/**
 * 确认外发纸质载体
 * 
 * @author lixiang
 */
public class ConfirmSendPaperAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	private String paper_barcode = "";// 主键ID
	private Integer paper_state = null;
	private String result = "";
	private String comment = "";
	private String updateFlag = "N";
	private String type = "N";// 判断确认入口是否个人闭环界面-郭姣-20160302
	private String output_confidential_num = ""; // 外发机要号
	private String send_way = "";

	public String getSend_way() {
		return send_way;
	}

	public void setSend_way(String send_way) {
		this.send_way = send_way;
	}

	public String getOutput_confidential_num() {
		return output_confidential_num;
	}

	public void setOutput_confidential_num(String output_confidential_num) {
		this.output_confidential_num = output_confidential_num;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPaper_barcode() {
		return paper_barcode;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode.trim();
	}

	public Integer getPaper_state() {
		return paper_state;
	}

	public void setPaper_state(Integer paper_state) {
		this.paper_state = paper_state;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}

	@Override
	public String executeFunction() throws Exception {
		if (updateFlag.equals("Y")) {
			try {
				if (paper_barcode.isEmpty()) {
					setResult("缺少参数[纸质载体编号],状态修改失败");
				} else {
					SecUser user = getCurUser();
					ledgerService.confirmSendPaper(paper_barcode, user, comment, output_confidential_num);
					setResult("外发已确认");
					insertCommonLog("管理员[" + user.getUser_name() + "]确认纸质载体[" + paper_barcode + "]已外发");
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
				setResult("出现异常，外发确认失败");
			}
			if (type.equals("Y")) {
				return "self";
			} else {
				return "ok";
			}
		} else {
			return SUCCESS;
		}
	}
}
