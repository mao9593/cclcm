package hdsec.web.project.basic.action;

/**
 * 删除智能回收柜
 * @author gaoxm
 *
 */

public class DelRecycleBoxAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String box_code = "";
	
	public void setBox_code(String box_code) {
		this.box_code = box_code;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (box_code.isEmpty()) {
			throw new Exception("参数错误，没有回收柜编号");
		} else {
			basicService.delSysRecycleBoxByCode(box_code);
			insertAdminLog("删除智能回收柜：编号[" + box_code + "]");
		}
		return SUCCESS;
	}
	
}
