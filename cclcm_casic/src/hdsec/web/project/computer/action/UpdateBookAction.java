package hdsec.web.project.computer.action;

import hdsec.web.project.computer.model.EntityBook;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class UpdateBookAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private List<EntityBook> book = null;
	private EntityBook device = null;
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
	private String useinfo = ""; // 使用情况
	private String book_code = ""; // 统一编号
	private String device_barcode = "";
	private String type = "";
	private String oldconf_code = ""; // 原保密编号

	public void setOldconf_code(String oldconf_code) {
		this.oldconf_code = oldconf_code;
	}

	public String getBook_code() {
		return book_code;
	}

	public void setBook_code(String book_code) {
		this.book_code = book_code;
	}

	public EntityBook getDevice() {
		return device;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
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

	public String getDuty_user_id() {
		return duty_user_id;
	}

	public void setDuty_user_id(String duty_user_id) {
		this.duty_user_id = duty_user_id;
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

	public String getHdisk_no() {
		return hdisk_no;
	}

	public void setHdisk_no(String hdisk_no) {
		this.hdisk_no = hdisk_no;
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

	public String getUseinfo() {
		return useinfo;
	}

	public void setUseinfo(String useinfo) {
		this.useinfo = useinfo;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(device_barcode)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("book_barcode", device_barcode);
			book = computerService.getBookList(map);
			if (book != null)
				device = book.get(0);
			else
				throw new Exception("无法查询条码号，请重新尝试。");

			if (type.equals("UPDATE")) {
				EntityBook info = new EntityBook(device_barcode, book_code, duty_entp, duty_dept_id, duty_user_id,
						seclv_code, book_os, book_mac, hdisk_no, internet_type, book_model, storage_location,
						book_status, outsideinfo, output_point, storage_secinfo, detail, device.getMed_type(),
						device.getConf_code(), useinfo, oldconf_code);

				computerService.updateEntityBook(info);
				return "ok";
			}
			return SUCCESS;
		} else {
			throw new Exception("无法查询条码号，请重新尝试。");
		}
	}
}