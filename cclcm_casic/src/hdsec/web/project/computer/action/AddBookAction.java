package hdsec.web.project.computer.action;

import hdsec.web.project.common.bm.BMCycleItem;
import hdsec.web.project.computer.model.EntityBook;
import hdsec.web.project.computer.model.InfoType;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class AddBookAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private String book_barcode = ""; // 条码号
	private String book_code = ""; // 统一编号
	private String duty_entp = ""; // 单位
	private String duty_dept_id = ""; // 责任部门ID
	private String duty_user_id = ""; // 责任人ID
	private Integer seclv_code = null; // 设备密级
	private String book_os = ""; // 计算机操作系统
	private String hdisk_no = "";// 硬盘序列号
	private String book_mac = ""; // mac地址
	private String internet_type = ""; // 联网情况
	private String book_model = ""; // 名称型号
	private String storage_location = ""; // 存放地点
	private Integer book_status = null; // 计算机状态（1在用 2停用 3维修 4报废 5销毁）
	private String outsideinfo = ""; // 外出情况
	private String output_point = "";// 输出情况
	private String storage_secinfo = "";// 存储涉密信息情况
	private String detail = "";// 备注
	private String med_type = "";// 设备类型
	private String useinfo = ""; // 使用情况
	private String oldconf_code = ""; // 原保密编号

	public void setOldconf_code(String oldconf_code) {
		this.oldconf_code = oldconf_code;
	}

	public String getMed_type() {
		return med_type;
	}

	public void setUseinfo(String useinfo) {
		this.useinfo = useinfo;
	}

	public void setHdisk_no(String hdisk_no) {
		this.hdisk_no = hdisk_no;
	}

	public void setBook_code(String book_code) {
		this.book_code = book_code;
	}

	public void setBook_os(String book_os) {
		this.book_os = book_os;
	}

	public void setBook_mac(String book_mac) {
		this.book_mac = book_mac;
	}

	public void setBook_model(String book_model) {
		this.book_model = book_model;
	}

	public void setBook_status(Integer book_status) {
		this.book_status = book_status;
	}

	public void setMed_type(String med_type) {
		this.med_type = med_type;
	}

	public void setDuty_entp(String duty_entp) {
		this.duty_entp = duty_entp;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setInternet_type(String internet_type) {
		this.internet_type = internet_type;
	}

	public void setStorage_location(String storage_location) {
		this.storage_location = storage_location;
	}

	public void setOutsideinfo(String outsideinfo) {
		this.outsideinfo = outsideinfo;
	}

	public void setOutput_point(String output_point) {
		this.output_point = output_point;
	}

	public void setStorage_secinfo(String storage_secinfo) {
		this.storage_secinfo = storage_secinfo;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		if (med_type.equals("")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("info_type", "笔记本");
			InfoType type = computerService.getInfoTypeByID(map);
			if (type != null)
				med_type = type.getInfo_id();
			return SUCCESS;
		}
		// 生成barcode
		if (StringUtils.hasLength(duty_dept_id)) {
			// book_barcode = basicService.createCETCEntityBarcode(duty_dept_id);
			// 不区分份数，默认为0
			book_barcode = basicService.createNewCETCBarcode(duty_dept_id, 0);
		} else {
			// book_barcode = basicService.createCETCEntityBarcode("00");
			// 不区分份数，默认为0
			book_barcode = basicService.createNewCETCBarcode("", 0);
		}
		// 生成保密编号流水号
		String conf_code = computerService.createSecretSerial("1", med_type, duty_dept_id);

		EntityBook book = new EntityBook(book_barcode, book_code, duty_entp, duty_dept_id, duty_user_id, seclv_code,
				book_os, book_mac, hdisk_no, internet_type, book_model, storage_location, book_status, outsideinfo,
				output_point, storage_secinfo, detail, med_type, conf_code, useinfo, oldconf_code);

		// 生成载体生命周期记录
		BMCycleItem cycleitem = new BMCycleItem();
		cycleitem.setBarcode(book_barcode);
		cycleitem.setEntity_type("COMPUTER");
		cycleitem.setOper_time(new Date());
		cycleitem.setUser_name(getCurUser().getUser_name());
		cycleitem.setDept_name(getCurUser().getDept_name());
		cycleitem.setOper("ADD");
		computerService.addBookEntity(book, cycleitem);
		insertCommonLog("添加计算机[" + book_barcode + "]");

		return "ok";
	}
}