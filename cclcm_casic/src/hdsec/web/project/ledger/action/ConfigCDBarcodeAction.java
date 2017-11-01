package hdsec.web.project.ledger.action;
/**
 * 
 *
 * @author lidepeng
 * 2015-4-9
 */
public class ConfigCDBarcodeAction extends LedgerBaseAction {

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