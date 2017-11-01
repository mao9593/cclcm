package hdsec.web.project.ledger.model;

import hdsec.web.project.activiti.model.ProcessJob;

public class EventCarryOutProcessJob extends ProcessJob {

	private String entity_type;

	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public String getEntity_typeName() {
		String entity_typeName = "";
		if (entity_type.equals("Paper")) {
			entity_typeName = "文件";
		} else {
			entity_typeName = "光盘";
		}
		return entity_typeName;
	}

}
