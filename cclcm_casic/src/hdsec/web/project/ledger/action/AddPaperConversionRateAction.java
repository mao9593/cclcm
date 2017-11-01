package hdsec.web.project.ledger.action;

public class AddPaperConversionRateAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	private String type_name;
	private double conversion_rate;

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public double getConversion_rate() {
		return conversion_rate;
	}

	public void setConversion_rate(double conversion_rate) {
		this.conversion_rate = conversion_rate;
	}

	@Override
	public String executeFunction() throws Exception {
		ledgerService.addPaperConversionRateByTypeName(type_name, conversion_rate);
		return SUCCESS;
	}

}
