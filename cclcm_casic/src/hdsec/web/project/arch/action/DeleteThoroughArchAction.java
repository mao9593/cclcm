package hdsec.web.project.arch.action;

public class DeleteThoroughArchAction extends ArchBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String msg;

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String executeFunction() throws Exception {
		archService.deleteThoroughArchById(id);
		setMsg("彻底删除档案！");
		return SUCCESS;
	}

}
