package hdsec.web.project.client.action;

import hdsec.web.project.client.model.SysCVS;

public class ViewCVSDetailAction extends ClientBaseAction {
	private static final long serialVersionUID = 1L;
	private String cvs_code = "";// 编号
	private SysCVS sysCVS = null;
	
	public void setCvs_code(String cvs_code) {
		this.cvs_code = cvs_code;
	}
	
	public SysCVS getSysCVS() {
		return sysCVS;
	}
	
	@Override
	public String executeFunction() throws Exception {
		sysCVS = clientService.getCVSByCode(cvs_code);
		return SUCCESS;
	}
}
