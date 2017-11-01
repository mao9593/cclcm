package hdsec.web.project.ledger.action;

import hdsec.web.project.user.model.SecUser;

/**
 * 光盘确认外发
 * 
 * @author lixiang
 */
public class ConfirmSendCDAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	private String cd_barcode = "";// 主键ID
	private Integer cd_state = null;
	private String result = "";
	private String comment = "";
	private String updateFlag = "N";
	private String type = "N";// 判断确认入口是否个人闭环界面-郭姣-20160302
	private String output_confidential_num = ""; // 外发机要号
	private String send_way = "";
	private String update_user_name = ""; // 更新接收人
	private String update_dept_name = ""; // 更新接收单位

	public String getUpdate_user_name() {
		return update_user_name;
	}

	public void setUpdate_user_name(String update_user_name) {
		this.update_user_name = update_user_name;
	}

	public String getUpdate_dept_name() {
		return update_dept_name;
	}

	public void setUpdate_dept_name(String update_dept_name) {
		this.update_dept_name = update_dept_name;
	}

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

	public String getCd_barcode() {
		return cd_barcode;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode.trim();
	}

	public Integer getCd_state() {
		return cd_state;
	}

	public void setCd_state(Integer cd_state) {
		this.cd_state = cd_state;
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
				if (cd_barcode.isEmpty()) {
					setResult("缺少参数[光盘编号],状态修改失败");
				} else {
					SecUser user = getCurUser();
					ledgerService.confirmSendCD(cd_barcode, user, comment, output_confidential_num, update_user_name, update_dept_name);
					setResult("外发已确认");
					insertCommonLog("管理员[" + user.getUser_name() + "]确认光盘[" + cd_barcode + "]已外发");
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
