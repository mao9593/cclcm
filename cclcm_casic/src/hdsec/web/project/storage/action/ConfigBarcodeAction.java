package hdsec.web.project.storage.action;

/**
 * 管理员设置条码横纵向、是否加密
 * 
 * @author lixiang
 * 
 */
public class ConfigBarcodeAction extends StorageBaseAction {
	
	private static final long serialVersionUID = 1L;
	private String n_dum = "";
	private String is_encrypt = "";
	
	public String getN_dum() {
		return n_dum;
	}


	public void setN_dum(String n_dum) {
		this.n_dum = n_dum;
	}


	public String getIs_encrypt() {
		return is_encrypt;
	}


	public void setIs_encrypt(String is_encrypt) {
		this.is_encrypt = is_encrypt;
	}


	@Override
	public String executeFunction() throws Exception {
		return SUCCESS;
	}
}
