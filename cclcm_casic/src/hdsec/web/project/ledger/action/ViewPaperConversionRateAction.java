package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.PaperType;

import java.util.ArrayList;
import java.util.List;

public class ViewPaperConversionRateAction extends LedgerBaseAction {
	private List<PaperType> paperTypeList = new ArrayList();

	public List<PaperType> getPaperTypeList() {
		return paperTypeList;
	}

	public void setPaperTypeList(List<PaperType> paperTypeList) {
		this.paperTypeList = paperTypeList;
	}

	@Override
	public String executeFunction() throws Exception {
		paperTypeList = ledgerService.getPaperConversionRate();
		return SUCCESS;
	}

}
