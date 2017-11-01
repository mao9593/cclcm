package hdsec.web.project.computer.model;

import hdsec.web.project.common.BaseDomain;

/**
 * 笔记本台账
 * 
 * @author guojiao
 */
public class EntityBook extends BaseDomain {

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
	private Integer book_status = null; // 笔记本状态（1在用 2停用 3维修 4报废 5销毁6已借出 7申请变更 8申请维修 9申请报废 10申请重装）
	private String useinfo = ""; // 使用情况
	private String outsideinfo = ""; // 外出情况
	private String output_point = "";// 输出情况
	private String storage_secinfo = "";// 存储涉密信息情况
	private String detail = "";// 备注
	private String med_type = "";// 设备类型
	private String conf_code = "";// 保密编号
	private String is_sealed = "";// 是否启用
	private String duty_dept_name = ""; // 责任部门
	private String duty_user_name = ""; // 责任人
	private String med_type_name = "";// 设备类型
	private String seclv_name = "";// 密级名称
	private String oldconf_code = ""; // 原保密编号

	public String getOldconf_code() {
		return oldconf_code;
	}

	public void setOldconf_code(String oldconf_code) {
		this.oldconf_code = oldconf_code;
	}

	public String getBook_status_name() {
		String name = "";
		if (book_status != null) {
			switch (book_status) {
			case 1:
				name = "在用";
				break;
			case 2:
				name = "停用";
				break;
			case 3:
				name = "维修";
				break;
			case 4:
				name = "报废";
				break;
			case 5:
				name = "销毁";
				break;
			case 6:
				name = "已借出";
				break;
			case 7:
				name = "申请变更";
				break;
			case 8:
				name = "申请维修";
				break;
			case 9:
				name = "申请报废";
				break;
			case 10:
				name = "申请重装";
				break;
			default:
				name = "未输入";
				break;
			}
		}
		return name;
	}

	public String getUseinfo() {
		return useinfo;
	}

	public void setUseinfo(String useinfo) {
		this.useinfo = useinfo;
	}

	public String getSeclv_name() {
		return seclv_name;
	}

	public void setSeclv_name(String seclv_name) {
		this.seclv_name = seclv_name;
	}

	public String getMed_type_name() {
		return med_type_name;
	}

	public void setMed_type_name(String med_type_name) {
		this.med_type_name = med_type_name;
	}

	public String getHdisk_no() {
		return hdisk_no;
	}

	public void setHdisk_no(String hdisk_no) {
		this.hdisk_no = hdisk_no;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	public String getBook_barcode() {
		return book_barcode;
	}

	public void setBook_barcode(String book_barcode) {
		this.book_barcode = book_barcode;
	}

	public String getBook_code() {
		return book_code;
	}

	public void setBook_code(String book_code) {
		this.book_code = book_code;
	}

	public String getDuty_entp() {
		return duty_entp;
	}

	public void setDuty_entp(String duty_entp) {
		this.duty_entp = duty_entp;
	}

	public String getDuty_dept_id() {
		return duty_dept_id;
	}

	public void setDuty_dept_id(String duty_dept_id) {
		this.duty_dept_id = duty_dept_id;
	}

	public String getDuty_dept_name() {
		return duty_dept_name;
	}

	public void setDuty_dept_name(String duty_dept_name) {
		this.duty_dept_name = duty_dept_name;
	}

	public String getDuty_user_id() {
		return duty_user_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getBook_os() {
		return book_os;
	}

	public void setBook_os(String book_os) {
		this.book_os = book_os;
	}

	public String getBook_mac() {
		return book_mac;
	}

	public void setBook_mac(String book_mac) {
		this.book_mac = book_mac;
	}

	public String getInternet_type() {
		return internet_type;
	}

	public void setInternet_type(String internet_type) {
		this.internet_type = internet_type;
	}

	public String getBook_model() {
		return book_model;
	}

	public void setBook_model(String book_model) {
		this.book_model = book_model;
	}

	public String getStorage_location() {
		return storage_location;
	}

	public void setStorage_location(String storage_location) {
		this.storage_location = storage_location;
	}

	public Integer getBook_status() {
		return book_status;
	}

	public void setBook_status(Integer book_status) {
		this.book_status = book_status;
	}

	public String getOutsideinfo() {
		return outsideinfo;
	}

	public void setOutsideinfo(String outsideinfo) {
		this.outsideinfo = outsideinfo;
	}

	public String getOutput_point() {
		return output_point;
	}

	public void setOutput_point(String output_point) {
		this.output_point = output_point;
	}

	public String getStorage_secinfo() {
		return storage_secinfo;
	}

	public void setStorage_secinfo(String storage_secinfo) {
		this.storage_secinfo = storage_secinfo;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getMed_type() {
		return med_type;
	}

	public void setMed_type(String med_type) {
		this.med_type = med_type;
	}

	@Override
	public String getIs_sealed() {
		return is_sealed;
	}

	@Override
	public void setIs_sealed(String is_sealed) {
		this.is_sealed = is_sealed;
	}

	public EntityBook() {

	}

	public EntityBook(String book_barcode, String book_code, String duty_entp, String duty_dept_id,
			String duty_user_id, Integer seclv_code, String book_os, String book_mac, String hdisk_no,
			String internet_type, String book_model, String storage_location, Integer book_status, String outsideinfo,
			String output_point, String storage_secinfo, String detail, String med_type, String conf_code,
			String useinfo, String oldconf_code) {

		this.book_barcode = book_barcode;
		this.book_code = book_code;
		this.duty_entp = duty_entp;
		this.duty_dept_id = duty_dept_id;
		this.duty_user_id = duty_user_id;
		this.seclv_code = seclv_code;
		this.book_os = book_os;
		this.book_mac = book_mac;
		this.hdisk_no = hdisk_no;
		this.internet_type = internet_type;
		this.book_model = book_model;
		this.storage_location = storage_location;
		this.book_status = book_status;
		this.outsideinfo = outsideinfo;
		this.output_point = output_point;
		this.storage_secinfo = storage_secinfo;
		this.detail = detail;
		this.med_type = med_type;
		this.conf_code = conf_code;
		this.useinfo = useinfo;
		this.oldconf_code = oldconf_code;
	}
}