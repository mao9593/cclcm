package hdsec.web.project.securityuser.model;

public class AbroadInfo {
	private String abroad_time = "";
	private String back_time = "";
	private String abroad_place = "";
	private String abroad_reason = "";
	private String out_customs = "";
	private String in_customs = "";
	private String abroad_content = "";
	private String row_num = "";
	private String abroad_file = "";
	private String event_code = "";

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getAbroad_file() {
		return abroad_file;
	}

	public void setAbroad_file(String abroad_file) {
		this.abroad_file = abroad_file;
	}

	public String getBack_time() {
		return back_time;
	}

	public void setBack_time(String back_time) {
		this.back_time = back_time;
	}

	public String getRow_num() {
		return row_num;
	}

	public void setRow_num(String row_num) {
		this.row_num = row_num;
	}

	public String getAbroad_time() {
		return abroad_time;
	}

	public void setAbroad_time(String abroad_time) {
		this.abroad_time = abroad_time;
	}

	public String getAbroad_place() {
		return abroad_place;
	}

	public void setAbroad_place(String abroad_place) {
		this.abroad_place = abroad_place;
	}

	public String getAbroad_reason() {
		return abroad_reason;
	}

	public void setAbroad_reason(String abroad_reason) {
		this.abroad_reason = abroad_reason;
	}

	public String getOut_customs() {
		return out_customs;
	}

	public void setOut_customs(String out_customs) {
		this.out_customs = out_customs;
	}

	public String getIn_customs() {
		return in_customs;
	}

	public void setIn_customs(String in_customs) {
		this.in_customs = in_customs;
	}

	public String getAbroad_content() {
		return abroad_content;
	}

	public void setAbroad_content(String abroad_content) {
		this.abroad_content = abroad_content;
	}

	public AbroadInfo() {

	}

	public AbroadInfo(String abroad_time, String back_time, String abroad_place, String abroad_reason,
			String out_customs, String in_customs, String abroad_content, String abroad_file, String row_num,
			String event_code) {
		this.abroad_time = abroad_time;
		this.abroad_place = abroad_place;
		this.abroad_reason = abroad_reason;
		this.out_customs = out_customs;
		this.in_customs = in_customs;
		this.abroad_content = abroad_content;
		this.row_num = row_num;
		this.back_time = back_time;
		this.abroad_file = abroad_file;
		this.event_code = event_code;
	}
}