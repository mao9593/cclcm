package hdsec.web.project.ledger.action;

public class DeletePaperConversionRateAction extends LedgerBaseAction {

	private String type_name;

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	@Override
	public String executeFunction() throws Exception {
		ledgerService.deletePaperConversionRateByTypeName(type_name);
		insertCommonLog("删除[" + type_name + "]的折合率");
		return SUCCESS;
	}

}
