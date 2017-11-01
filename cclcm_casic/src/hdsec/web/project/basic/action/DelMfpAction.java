package hdsec.web.project.basic.action;

/**
 * 删除一体机
 * @author renmingfei
 *
 */
public class DelMfpAction extends BasicBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String mfp_code = "";
	
	public void setMfp_code(String mfp_code) {
		this.mfp_code = mfp_code;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (mfp_code.isEmpty()) {
			throw new Exception("参数错误，没有一体机编号");
		} else {
			basicService.delMfpByCode(mfp_code);
			insertAdminLog("删除一体机：编号[" + mfp_code + "]");
		}
		return SUCCESS;
	}
}
