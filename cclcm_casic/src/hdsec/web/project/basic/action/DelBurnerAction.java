package hdsec.web.project.basic.action;

/**
 * 删除刻录机
 * @author renmingfei
 *
 */
public class DelBurnerAction extends BasicBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String burner_code = "";
	
	public void setBurner_code(String burner_code) {
		this.burner_code = burner_code;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (burner_code.isEmpty()) {
			throw new Exception("参数错误，没有刻录机编号");
		} else {
			basicService.delBurnerByCode(burner_code);
			insertAdminLog("删除刻录机：编号[" + burner_code + "]");
		}
		return SUCCESS;
	}
}
