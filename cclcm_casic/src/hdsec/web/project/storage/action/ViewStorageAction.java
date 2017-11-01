package hdsec.web.project.storage.action;

import hdsec.web.project.storage.model.EntityStorage;
import hdsec.web.project.storage.model.StorageStatusEnum;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看所有存储载体列表 2014-4-22 上午12:16:53
 * 
 * @author renmingfei
 */
public class ViewStorageAction extends StorageBaseAction {

	private static final long serialVersionUID = 1L;
	private String dept_id = "";
	private String duty_user_name = "";
	private String use_user_name = "";
	private String storage_barcode = "";
	private String seclv_code = "";
	private Integer med_type;
	private StorageStatusEnum storage_status = null;
	private String storage_name = "";
	private List<EntityStorage> storageList = null;

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name.trim();
	}

	public String getUse_user_name() {
		return use_user_name;
	}

	public void setUse_user_name(String use_user_name) {
		this.use_user_name = use_user_name.trim();
	}

	public String getStorage_barcode() {
		return storage_barcode;
	}

	public void setStorage_barcode(String storage_barcode) {
		this.storage_barcode = storage_barcode.trim();
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public Integer getMed_type() {
		return med_type;
	}

	public void setMed_type(Integer med_type) {
		this.med_type = med_type;
	}

	public StorageStatusEnum getStorage_status() {
		return storage_status;
	}

	public void setStorage_status(Integer storage_status) {
		this.storage_status = StorageStatusEnum.valueOf("SS" + storage_status);
	}

	public String getStorage_name() {
		return storage_name;
	}

	public void setStorage_name(String storage_name) {
		this.storage_name = storage_name.trim();
	}

	public List<EntityStorage> getStorageList() {
		return storageList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<StorageStatusEnum> getStatusList() {
		List<StorageStatusEnum> tmpList = new ArrayList<StorageStatusEnum>();
		for (StorageStatusEnum item : StorageStatusEnum.values()) {
			tmpList.add(item);
		}
		return tmpList;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// map.put("dept_id", dept_id);
		map.put("duty_user_name", duty_user_name);
		map.put("use_user_name", use_user_name);
		map.put("storage_barcode", storage_barcode);
		map.put("seclv_code", seclv_code);
		map.put("med_type", med_type);
		map.put("storage_status", storage_status);
		map.put("storage_name", storage_name);
		storageList = storageService.getStorageList(map);
		return SUCCESS;
	}
}
