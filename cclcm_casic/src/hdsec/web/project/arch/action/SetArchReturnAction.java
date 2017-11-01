package hdsec.web.project.arch.action;

import java.util.HashMap;
import java.util.Map;

/**
 * 档案归还操作
 * 
 * @author handouwang
 * 
 *         2015-7-28/
 */
public class SetArchReturnAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
	private int id = 0;// event_id
	private String barcode = "";// 档案条码
	private String user_iidd;
	private String job_code;
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

	public String getUser_iidd() {
		return user_iidd;
	}

	public void setUser_iidd(String user_iidd) {
		this.user_iidd = user_iidd;
	}

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	@Override
	public String executeFunction() throws Exception {
		if (id != 0) {
			archService.setArchReturn(id);
			insertCommonLog("归还档案[" + barcode + "].");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("accept_user_iidd", user_iidd);
			map.put("job_code", job_code);
			archService.delAskToReturnArchClientMsg(map);
			setMsg("档案归档！");
			return SUCCESS;
		} else {
			throw new Exception("No valid id input");
		}
	}

	public String getMsg() {
		return msg;
	}
}
