package hdsec.web.project.copy.action;

/**
 * 删除复印作业
 * 
 * @author lixiang
 * 
 */
public class DelCopyEventAction extends CopyBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	@Override
	public String executeFunction() throws Exception {
		copyService.delCopyEventByCopyCode(event_code);
		insertCommonLog("删除复印作业[" + event_code + "]");
		return null;
	}
}
