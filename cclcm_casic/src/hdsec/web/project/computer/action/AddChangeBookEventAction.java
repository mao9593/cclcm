package hdsec.web.project.computer.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.computer.model.ChangeDeviceEvent;
import hdsec.web.project.computer.model.EntityBook;
import hdsec.web.project.computer.model.EntityEventDevice;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/*
 * 笔记本变更申请
 * 
 * @author guojiao
 * 
 */
public class AddChangeBookEventAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_code = "";// 事件编号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String user_phone = "";// 联系方式
	private String book_barcode = "";
	private EntityBook book = null;
	private Integer com_seclv_code = null;// 计算机密级
	private String other_s = "";// 其他变更内容
	private String duty_dept_id = "";
	private String duty_user_id = "";
	private String storage_location = "";
	private String summ = "";// 备注
	private String next_approver = "";// 下级审批人
	private String jobType = JobTypeEnum.BOOK_CHANGE.getJobTypeCode();

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getJobType() {
		return jobType;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public EntityBook getBook() {
		return book;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

	public String getBook_barcode() {
		return book_barcode;
	}

	public void setBook_barcode(String book_barcode) {
		this.book_barcode = book_barcode;
	}

	public void setCom_seclv_code(Integer com_seclv_code) {
		this.com_seclv_code = com_seclv_code;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
	}

	public void setStorage_location(String storage_location) {
		this.storage_location = storage_location;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("book_barcode", book_barcode);
			List<EntityBook> blist = computerService.getBookList(map);
			if (blist == null) {
				throw new Exception("请重新选择笔记本");
			}
			book = blist.get(0);
			EntityEventDevice event_device_content = new EntityEventDevice(event_code, book_barcode, com_seclv_code,
					book.getSeclv_code(), duty_user_id, book.getDuty_user_id(), duty_dept_id, book.getDuty_dept_id(),
					storage_location, book.getStorage_location(), other_s);
			ChangeDeviceEvent event = new ChangeDeviceEvent(event_code, user_iidd, dept_id, seclv_code, usage_code,
					project_code, summ, user_phone, book_barcode, 11);// 笔记本变更申请表

			event.setJobType(JobTypeEnum.valueOf(jobType));
			computerService.addComputerEvent(event, next_approver);
			computerService.addEntityEventDevice(event_device_content);
			insertCommonLog("添加笔记本变更申请[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_CHANGEBOOK_" + System.currentTimeMillis();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("book_barcode", book_barcode);
			List<EntityBook> blist = computerService.getBookList(map);
			if (blist != null) {
				book = blist.get(0);
			}
			return SUCCESS;
		}
	}
}