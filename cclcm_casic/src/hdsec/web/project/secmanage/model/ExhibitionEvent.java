package hdsec.web.project.secmanage.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

public class ExhibitionEvent extends BaseEvent {

	private String contact_num = ""; // 联系电话
	private String exh_reason = "";// 展览原因
	private String exh_location = "";// 展览地点
	private String exh_type = "";// 展览类型
	private String exh_others = "";// 展览其他类型
	private String file_list = "";// 附件名称
	private Integer file_num = null;// 附件数目

	public String getContact_num() {
		return contact_num;
	}

	public void setContact_num(String contact_num) {
		this.contact_num = contact_num;
	}

	public String getExh_reason() {
		return exh_reason;
	}

	public void setExh_reason(String exh_reason) {
		this.exh_reason = exh_reason;
	}

	public String getExh_location() {
		return exh_location;
	}

	public void setExh_location(String exh_location) {
		this.exh_location = exh_location;
	}

	public String getExh_type_name() {
		String name = "";
		String[] type = exh_type.split(",");
		for (int i = 0; i < type.length; i++) {
			if (type[i].contains("1")) {
				name = name + "产品,";
			} else if (type[i].contains("2")) {
				name = name + "模型,";
			} else if (type[i].contains("3")) {
				name = name + "展板,";
			} else if (type[i].contains("4")) {
				name = name + "宣传画册,";
			} else if (type[i].contains("5")) {
				name = name + "音像,";
			} else if (type[i].contains("6")) {
				name = name + exh_others;
			}
		}
		return name;
	}

	public void setExh_type(String exh_type) {
		this.exh_type = exh_type;
	}

	public String getExh_others() {
		return exh_others;
	}

	public void setExh_others(String exh_others) {
		this.exh_others = exh_others;
	}

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public Integer getFile_num() {
		return file_num;
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public ExhibitionEvent() {
		super(JobTypeEnum.EXHIBITION);
	}

	public ExhibitionEvent(String event_code, String user_iidd, String dept_id, Integer seclv_code, String usage_code,
			String project_code, String summ, String contact_num, String exh_location, String exh_type,
			String exh_others, String exh_reason, String file_list, Integer file_num) {
		super(JobTypeEnum.EXHIBITION, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);
		this.contact_num = contact_num;
		this.exh_location = exh_location;
		this.exh_type = exh_type;
		this.exh_others = exh_others;
		this.exh_reason = exh_reason;
		this.file_list = file_list;
		this.file_num = file_num;
	}

}