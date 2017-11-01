package hdsec.web.project.secmanage.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

/**
 * 对外交流材料
 * 
 * @author guojiao
 */
public class ExchangeMaterialEvent extends BaseEvent {

	private String contact_num = ""; // 联系电话
	private String exc_object = "";// 交流对象
	private String exc_location = "";// 交流地点
	private Integer exc_type = null;// 交流材料类型
	private String file_list = "";// 附件名称

	public String getExc_object() {
		return exc_object;
	}

	public void setExc_object(String exc_object) {
		this.exc_object = exc_object;
	}

	public String getExc_location() {
		return exc_location;
	}

	public void setExc_location(String exc_location) {
		this.exc_location = exc_location;
	}

	public String getExc_type_name() {
		String name = "";
		if (exc_type != null) {
			switch (exc_type) {
			case 1:
				name = "纸介质";
				break;
			case 2:
				name = "电子载体";
				break;
			case 3:
				name = "其他";
				break;
			default:
				break;
			}
		}
		return name;
	}

	public Integer getExc_type() {
		return exc_type;
	}

	public void setExc_type(Integer exc_type) {
		this.exc_type = exc_type;
	}

	public String getContact_num() {
		return contact_num;
	}

	public void setContact_num(String contact_num) {
		this.contact_num = contact_num;
	}

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public ExchangeMaterialEvent() {
		super(JobTypeEnum.MATERIAL);
	}

	public ExchangeMaterialEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code,
			String usage_code, String project_code, String summ, String contact_num, String file_list,
			String exc_object, String exc_location, Integer exc_type) {
		super(JobTypeEnum.MATERIAL, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.contact_num = contact_num;
		this.exc_location = exc_location;
		this.exc_object = exc_object;
		this.exc_type = exc_type;
		this.file_list = file_list;
	}
}
