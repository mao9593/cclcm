package hdsec.web.project.arch.action;

public class SetArchRenewAction extends ArchBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMsg() {
		return msg;
	}

	private int id = 0;// event表的id
	private String barcode = "";// 借阅档案的条码
	private String msg;

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	@Override
	public String executeFunction() throws Exception {
		if (id != 0) {
			archService.setArchRenew(id, getCurUser().getUser_iidd());
			insertCommonLog("续借档案[" + barcode + "].");
			setMsg("续借成功!");
			return SUCCESS;
		} else {
			throw new Exception("No valid id input");
		}
	}
}