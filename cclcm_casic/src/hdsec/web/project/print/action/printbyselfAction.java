package hdsec.web.project.print.action;

public class printbyselfAction extends PrintBaseAction {

	private static final long serialVersionUID = 1L;
	
	public String getUser_iidd() {
		return getCurUser().getUser_iidd();
	}

	@Override
	public String executeFunction() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

}
