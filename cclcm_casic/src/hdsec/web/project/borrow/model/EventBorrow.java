package hdsec.web.project.borrow.model;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.BaseEvent;
import hdsec.web.project.common.util.TimeUtil;

import java.util.Date;

public class EventBorrow extends BaseEvent {
	private String entity_type = "";
	private String entity_name = "";
	private String barcode = "";
	private String lend_dept_id = "";
	private String lend_user_iidd = "";
	private Integer borrow_status = null;
	private String his_job_code = "";
	private String job_code = "";
	private Date finish_time = null;// 完成时间
	private Date return_time = null;// 归还时间
	private String lend_user_name = "";// 借出人
	private String lend_dept_name = "";// 借出部门
	private String is_sure = "N";
	private Date limit_Time = null;// 借用期限
	private Integer expire_status = null;

	public Integer getExpire_status() {
		if (limit_Time == null) {// 未到期
			expire_status = 0;
		} else if (limit_Time.before(new Date())) {// 已到期
			expire_status = 1;
		} else if (limit_Time.before(TimeUtil.getAfterXDay(2))) {// 即将到期
			expire_status = 2;
		} else {// 未到期
			expire_status = 0;
		}
		return expire_status;
	}

	public String getExpire_status_name() {
		if (limit_Time == null) {
			return "未到期";
		} else if (limit_Time.before(new Date())) {
			return "已到期";
		} else if (limit_Time.before(TimeUtil.getAfterXDay(2))) {
			return "即将到期";
		} else {
			return "未到期";
		}
	}

	public void setExpire_status(Integer expire_status) {
		this.expire_status = expire_status;
	}

	public String getLimit_time_str() {
		return limit_Time == null ? "" : getSdf().format(limit_Time);
	}

	public Date getLimit_Time() {
		return limit_Time;
	}

	public void setLimit_Time(Date limit_Time) {
		this.limit_Time = limit_Time;
	}

	public String getEntity_type() {
		return entity_type;
	}

	public String getEntity_type_name() {
		String name = "未知";
		switch (this.entity_type) {
		case "PAPER":
			name = "纸质";
			break;
		case "CD":
			name = "光盘";
			break;
		case "DEVICE":
			name = "磁介质";
			break;
		}
		return name;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public String getEntity_name() {
		return entity_name;
	}

	public void setEntity_name(String entity_name) {
		this.entity_name = entity_name;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getLend_dept_id() {
		return lend_dept_id;
	}

	public void setLend_dept_id(String lend_dept_id) {
		this.lend_dept_id = lend_dept_id;
	}

	public String getLend_user_iidd() {
		return lend_user_iidd;
	}

	public void setLend_user_iidd(String lend_user_iidd) {
		this.lend_user_iidd = lend_user_iidd;
	}

	public int getBorrow_status() {
		return borrow_status;
	}

	public String getBorrow_status_name() {
		String name = "未知";
		switch (this.borrow_status) {
		case 0:
			name = "未借入";
			break;
		case 1:
			name = "已借入";
			break;
		case 2:
			name = "已归还";
			break;
		}
		return name;
	}

	public String getIs_sure() {
		return is_sure;
	}

	public void setIs_sure(String is_sure) {
		this.is_sure = is_sure;
	}

	public void setBorrow_status(int borrow_status) {
		this.borrow_status = borrow_status;
	}

	public String getHis_job_code() {
		return his_job_code;
	}

	public void setHis_job_code(String his_job_code) {
		this.his_job_code = his_job_code;
	}

	@Override
	public String getJob_code() {
		return job_code;
	}

	@Override
	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public Date getFinish_time() {
		return finish_time;
	}

	public String getFinish_time_str() {
		return finish_time == null ? "" : getSdf().format(finish_time);
	}

	public void setFinish_time(Date finish_time) {
		this.finish_time = finish_time;
	}

	public Date getReturn_time() {
		return return_time;
	}

	public String getReturn_time_str() {
		return return_time == null ? "" : getSdf().format(return_time);
	}

	public void setReturn_time(Date return_time) {
		this.return_time = return_time;
	}

	public String getLend_user_name() {
		return lend_user_name;
	}

	public String getLend_dept_name() {
		return lend_dept_name;
	}

	public EventBorrow() {
		super(JobTypeEnum.BORROW);
	}

	public EventBorrow(String event_code, String user_iidd, String dept_id, Integer seclv_code, String usage_code,
			String project_code, String summ, String entity_type, String barcode, String lend_dept_id,
			String lend_user_iidd, Integer borrow_status, String job_code, String entity_name) {
		super(JobTypeEnum.BORROW, event_code, user_iidd, dept_id, seclv_code, usage_code, project_code, summ);
		this.entity_type = entity_type;
		this.barcode = barcode;
		this.lend_dept_id = lend_dept_id;
		this.lend_user_iidd = lend_user_iidd;
		this.borrow_status = borrow_status;
		this.job_code = job_code;
		this.entity_name = entity_name;
	}

}
