package hdsec.web.project.securityuser.model;

public class ExperienceInfo {
	private String experience_time = "";
	private String end_time = "";
	private String experience_content = "";
	private String row_num = "";
	private String event_code = "";

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getRow_num() {
		return row_num;
	}

	public void setRow_num(String row_num) {
		this.row_num = row_num;
	}

	public String getExperience_time() {
		return experience_time;
	}

	public void setExperience_time(String experience_time) {
		this.experience_time = experience_time;
	}

	public String getExperience_content() {
		return experience_content;
	}

	public void setExperience_content(String experience_content) {
		this.experience_content = experience_content;
	}

	public ExperienceInfo() {

	}

	public ExperienceInfo(String experience_time, String end_time, String experience_content, String row_num,
			String event_code) {
		this.experience_time = experience_time;
		this.end_time = end_time;
		this.experience_content = experience_content;
		this.row_num = row_num;
		this.event_code = event_code;
	}
}
