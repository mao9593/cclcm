package hdsec.web.project.ledger.action;

/**
 * 文件总台账部门组织机构树
 * 
 * @author chenrongrong
 * 
 */
public class ManagePaperledgerAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;

	public String getContext() {
		return "ledger/viewpaperledger.action";
	}

	@Override
	public String executeFunction() {
		// TODO:鉴权
		return SUCCESS;
	}
}
