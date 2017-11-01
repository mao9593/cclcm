package hdsec.web.project.ledger.action;

public class SetRemarkAction extends LedgerBaseAction{
	private static final long serialVersionUID = 1L;
	private String paper_barcode = "";//
	private String send_id = "";//外发号
	private String send_mode = "";//外发方式
	private String box_num = "";//盒号
	private String file_order_num = "";//文件顺序号
	private String manage_opinion = "";//处理意见
	private String receive_id = "";//集团收文编号
	private String remark = "";//备注1
	private String type = "";
	private String ledger_type = "";
	public String getPaper_barcode() {
		return paper_barcode;
	}
	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode;
	}
	public String getSend_id() {
		return send_id;
	}
	public void setSend_id(String send_id) {
		this.send_id = send_id;
	}
	public String getSend_mode() {
		return send_mode;
	}
	public void setSend_mode(String send_mode) {
		this.send_mode = send_mode;
	}
	public String getBox_num() {
		return box_num;
	}
	public void setBox_num(String box_num) {
		this.box_num = box_num;
	}
	public String getFile_order_num() {
		return file_order_num;
	}
	public void setFile_order_num(String file_order_num) {
		this.file_order_num = file_order_num;
	}
	public String getManage_opinion() {
		return manage_opinion;
	}
	public void setManage_opinion(String manage_opinion) {
		this.manage_opinion = manage_opinion;
	}
	public String getReceive_id() {
		return receive_id;
	}
	public void setReceive_id(String receive_id) {
		this.receive_id = receive_id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLedger_type() {
		return ledger_type;
	}
	public void setLedger_type(String ledger_type) {
		this.ledger_type = ledger_type;
	}
	@Override
	public String executeFunction() throws Exception {
		ledgerService.setPaperRemark(paper_barcode, send_id, send_mode, box_num ,
				file_order_num, manage_opinion, receive_id, remark);
		insertCommonLog("设置台账备注[" + paper_barcode + "][" + send_id + "][" + send_mode + "]" +
				".");
		return SUCCESS;
	}
}
