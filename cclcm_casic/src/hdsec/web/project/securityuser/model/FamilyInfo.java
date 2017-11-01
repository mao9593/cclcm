package hdsec.web.project.securityuser.model;

public class FamilyInfo {
	private String family_relationship = "";
	private String family_name = "";
	private String family_nationality = "";
	private String family_borndate = "";
	private String family_politicalstatus = "";
	private String family_workplace = "";
	private String family_lifeplace = "";
	private String family_greencard = "";
	private String row_num = "";
	private String event_code = "";

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getRow_num() {
		return row_num;
	}

	public void setRow_num(String row_num) {
		this.row_num = row_num;
	}

	public String getFamily_relationship() {
		return family_relationship;
	}

	public void setFamily_relationship(String family_relationship) {
		this.family_relationship = family_relationship;
	}

	public String getFamily_name() {
		return family_name;
	}

	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	public String getFamily_nationality() {
		return family_nationality;
	}

	public void setFamily_nationality(String family_nationality) {
		this.family_nationality = family_nationality;
	}

	public String getFamily_borndate() {
		return family_borndate;
	}

	public void setFamily_borndate(String family_borndate) {
		this.family_borndate = family_borndate;
	}

	public String getFamily_politicalstatus() {
		return family_politicalstatus;
	}

	public void setFamily_politicalstatus(String family_politicalstatus) {
		this.family_politicalstatus = family_politicalstatus;
	}

	public String getFamily_workplace() {
		return family_workplace;
	}

	public void setFamily_workplace(String family_workplace) {
		this.family_workplace = family_workplace;
	}

	public String getFamily_lifeplace() {
		return family_lifeplace;
	}

	public void setFamily_lifeplace(String family_lifeplace) {
		this.family_lifeplace = family_lifeplace;
	}

	public String getFamily_greencard() {
		return family_greencard;
	}

	public void setFamily_greencard(String family_greencard) {
		this.family_greencard = family_greencard;
	}

	public FamilyInfo() {

	}

	public FamilyInfo(String family_relationship, String family_name, String family_nationality,
			String family_borndate, String family_politicalstatus, String family_workplace, String family_lifeplace,
			String family_greencard, String row_num, String event_code) {
		this.family_relationship = family_relationship;
		this.family_name = family_name;
		this.family_nationality = family_nationality;
		this.family_borndate = family_borndate;
		this.family_politicalstatus = family_politicalstatus;
		this.family_workplace = family_workplace;
		this.family_lifeplace = family_lifeplace;
		this.family_greencard = family_greencard;
		this.row_num = row_num;
		this.event_code = event_code;
	}
}