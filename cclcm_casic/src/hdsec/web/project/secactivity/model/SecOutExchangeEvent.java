package hdsec.web.project.secactivity.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;

import java.util.Date;

/**
 * 添加涉外交流保密工作申请
 * 
 * @author gj
 * 
 */
public class SecOutExchangeEvent extends BaseEvent {
	private String company_info = "";// 来访单位
	private String reason = "";// 事由
	private String recept_user_iidd = "";// 接待人员ID
	private String receptionist = ""; // 接待人员
	private String exchange_info = "";// 业务交流范围
	private String reception = "";// 接待地点
	private Integer reception_sec = null;// 接待地点是否涉密
	private String visite_place = "";// 参观地点
	private Integer visite_sec = null;// 参观地点是否涉密
	private String material = "";// 提供资料
	private Integer material_sec = null;// 提供资料是否涉密
	private String sec_measure = "";// 保密错误
	private Date go_time = null; // 抵达时间
	private Date back_time = null; // 离所时间
	private Integer file_num = 0;// 附件数量
	private String file_list = ""; // 上传附件
	private String his_job_code = ""; // 包含改作业的历史任务列表

	public String getCompany_info() {
		return company_info;
	}

	public void setCompany_info(String company_info) {
		this.company_info = company_info;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getRecept_user_iidd() {
		return recept_user_iidd;
	}

	public void setRecept_user_iidd(String recept_user_iidd) {
		this.recept_user_iidd = recept_user_iidd;
	}

	public String getReceptionist() {
		return receptionist;
	}

	public void setReceptionist(String receptionist) {
		this.receptionist = receptionist;
	}

	public String getExchange_info() {
		return exchange_info;
	}

	public void setExchange_info(String exchange_info) {
		this.exchange_info = exchange_info;
	}

	public String getReception() {
		return reception;
	}

	public void setReception(String reception) {
		this.reception = reception;
	}

	public Integer getReception_sec() {
		return reception_sec;
	}

	public void setReception_sec(Integer reception_sec) {
		this.reception_sec = reception_sec;
	}

	public String getVisite_place() {
		return visite_place;
	}

	public void setVisite_place(String visite_place) {
		this.visite_place = visite_place;
	}

	public Integer getVisite_sec() {
		return visite_sec;
	}

	public void setVisite_sec(Integer visite_sec) {
		this.visite_sec = visite_sec;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Integer getMaterial_sec() {
		return material_sec;
	}

	public void setMaterial_sec(Integer material_sec) {
		this.material_sec = material_sec;
	}

	public String getSec_measure() {
		return sec_measure;
	}

	public void setSec_measure(String sec_measure) {
		this.sec_measure = sec_measure;
	}

	public Date getGo_time() {
		return go_time;
	}

	public void setGo_time(Date go_time) {
		this.go_time = go_time;
	}

	public Date getBack_time() {
		return back_time;
	}

	public void setBack_time(Date back_time) {
		this.back_time = back_time;
	}

	public String getGo_time_str() {
		return go_time == null ? "" : getSdf().format(go_time);
	}

	public String getBack_time_str() {
		return back_time == null ? "" : getSdf().format(back_time);
	}

	public Integer getFile_num() {
		return file_num;
	}

	public void setFile_num(Integer file_num) {
		this.file_num = file_num;
	}

	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public String getHis_job_code() {
		return his_job_code;
	}

	public void setHis_job_code(String his_job_code) {
		this.his_job_code = his_job_code;
	}

	public SecOutExchangeEvent() {
		super(JobTypeEnum.OUT_EXCHANGE);
	}

	public SecOutExchangeEvent(String user_iidd, String dept_id, String event_code, Integer seclv_code,
			String usage_code, String project_code, String summ, Integer file_num, String file_list,
			String his_job_code, String company_info, String reason, String recept_user_iidd, String receptionist,
			String exchange_info, String reception, Integer reception_sec, String visite_place, Integer visite_sec,
			String material, Integer material_sec, String sec_measure, Date go_time, Date back_time) {
		super(JobTypeEnum.USERSEC_ACTIVITY, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);

		this.file_list = file_list;
		this.his_job_code = his_job_code;
		this.file_num = file_num;
		this.file_num = file_num;
		this.file_list = file_list;
		this.company_info = company_info;
		this.reason = reason;
		this.recept_user_iidd = recept_user_iidd;
		this.receptionist = receptionist;
		this.exchange_info = exchange_info;
		this.reception = reception;
		this.reception_sec = reception_sec;
		this.visite_place = visite_place;
		this.visite_sec = visite_sec;
		this.material = material;
		this.material_sec = material_sec;
		this.sec_measure = sec_measure;
		this.go_time = go_time;
		this.back_time = back_time;
	}
}