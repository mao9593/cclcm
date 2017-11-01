package hdsec.web.project.arch.action;

public class DeleteArchAction extends ArchBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String msg;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String executeFunction() throws Exception {
		archService.deleteArchById(id);
		setMsg("ok");
		return SUCCESS;
	}

}
