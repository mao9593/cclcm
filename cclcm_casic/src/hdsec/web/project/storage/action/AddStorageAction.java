package hdsec.web.project.storage.action;

import hdsec.web.project.storage.model.EntityStorage;
import hdsec.web.project.user.model.SecLevel;

import java.util.List;
import java.util.UUID;

/**
 * 录入存储介质 2014-4-22 下午3:56:55
 * 
 * @author renmingfei
 */
public class AddStorageAction extends StorageBaseAction {
	private static final long serialVersionUID = 1L;
	private String storage_name = "";// 名称
	private String dept_id = "";// 设备所属部门ID
	private String dept_name = "";// 设备所属部门名称
	private String storage_series = "";// 设备编号
	private Integer med_type = null;// 介质类型(1硬盘,2存储卡,3其他)
	private Integer seclv_code = null;// 密级
	private String duty_user_iidd = "";// 责任人ID
	private String storage_model = "";// 型号 (例如PC机　DELL OPTIPLEX 360)
	private String storage_detail = "";// 设备配置：比如U盘 1G\2G\4G 是否加密PC CPU/内存/硬盘

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public void setStorage_name(String storage_name) {
		this.storage_name = storage_name;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public void setStorage_series(String storage_series) {
		this.storage_series = storage_series;
	}

	public void setMed_type(Integer med_type) {
		this.med_type = med_type;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setDuty_user_iidd(String duty_user_iidd) {
		this.duty_user_iidd = duty_user_iidd;
	}

	public void setStorage_model(String storage_model) {
		this.storage_model = storage_model;
	}

	public void setStorage_detail(String storage_detail) {
		this.storage_detail = storage_detail;
	}

	@Override
	public String executeFunction() throws Exception {
		if (storage_name.isEmpty()) {
			return SUCCESS;
		} else {
			String storage_code = "STR-" + String.valueOf(UUID.randomUUID().toString());
			// String storage_barcode = storage_code;
			String storage_barcode = basicService.createEntityBarcodeCAEP("STORAGE", seclv_code, dept_id); // createEntityBarcode
			String seclv_name = userService.getSecLevelByCode(seclv_code).getSeclv_name();
			String duty_user_name = userService.getUserNameByUserId(duty_user_iidd);
			EntityStorage storage = new EntityStorage(storage_code, storage_name, getCurUser().getUser_iidd(),
					getCurUser().getUser_name(), dept_id, dept_name, storage_series, storage_barcode, med_type,
					seclv_code, seclv_name, duty_user_iidd, duty_user_name, storage_model, storage_detail);
			storageService.addStorage(storage);
			insertCommonLog("添加存储介质：名称[" + storage_name + "]，介质编号[" + storage_code + "]");
			return "ok";
		}
	}
}
