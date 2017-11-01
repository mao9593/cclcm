package hdsec.web.project.basic.action;

public class DelExchangeBoxAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String box_code = "";
	
	public void setBox_code(String box_code) {
		this.box_code = box_code;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (box_code.isEmpty()) {
			throw new Exception("参数错误，没有交换柜编号");
		} else {
			basicService.delSysExchangeBoxByCode(box_code);
			insertAdminLog("删除智能交换柜：编号[" + box_code + "]");
		}
		return SUCCESS;
	}
	
}
