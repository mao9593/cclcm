package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.EventOut;

public class ViewCarryOutInfoAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private int id;
	private EventOut eventOut;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public EventOut getEventOut() {
		return eventOut;
	}

	public void setEventOut(EventOut eventOut) {
		this.eventOut = eventOut;
	}

	@Override
	public String executeFunction() throws Exception {
		eventOut = ledgerService.getEventOutById(id);
		return SUCCESS;
	}
}
