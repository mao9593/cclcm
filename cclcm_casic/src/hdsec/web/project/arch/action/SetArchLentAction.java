package hdsec.web.project.arch.action;

/**
 * 档案借出操作
 * 
 * @author handouwang
 * 
 *         2015-7-28/
 */
public class SetArchLentAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
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
			archService.setArchLent(id, getCurUser().getUser_iidd());
			insertCommonLog("借出档案[" + barcode + "].");
			setMsg("档案借出!");
			return SUCCESS;
		} else {
			throw new Exception("No valid id input");
		}
	}

	public String getMsg() {
		return msg;
	}
}
