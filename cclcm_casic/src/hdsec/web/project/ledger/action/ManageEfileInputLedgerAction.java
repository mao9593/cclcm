package hdsec.web.project.ledger.action;

public class ManageEfileInputLedgerAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;

	public String getContext() {
		return "ledger/viewefileinputledger.action";
	}

	@Override
	public String executeFunction() {
		// TODO:鉴权
		return SUCCESS;
	}
}
