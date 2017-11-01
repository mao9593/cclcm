package hdsec.web.project.arch.action;

/**
 * 分密级设置借用期限
 * 
 * @author handouwang
 * 
 *         2015-7-26/
 */
public class SetBorrowLenAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
	private int seclv_code;
	private int length;

	public void setSeclv_code(int seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public String executeFunction() throws Exception {
		archService.setBorrowLen(seclv_code, length);
		insertCommonLog("设置借用期限[" + seclv_code + "][" + length + "].");
		return SUCCESS;
	}
}
