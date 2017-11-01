package hdsec.web.project.activiti.action;


/**
 * 批量删除流程
 * @author renmingfei
 *
 */
public class DelProcessAction extends ActivitiBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String process_id = "";
	
	public void setProcess_id(String process_id) {
		this.process_id = process_id;
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (process_id.isEmpty()) {
			throw new Exception("参数错误，没有流程ID");
		} else {
			String process_name = activitiService.getProcessNameById(process_id);
			activitiService.delProcessByProcessId(process_id);
			insertAdminLog("删除流程：" + process_name);
		}
		return SUCCESS;
	}
}
