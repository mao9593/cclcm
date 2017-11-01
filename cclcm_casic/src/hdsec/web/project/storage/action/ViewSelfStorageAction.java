package hdsec.web.project.storage.action;

import hdsec.web.project.storage.model.EntityStorage;
import hdsec.web.project.storage.model.StorageStatusEnum;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看个人保管存储载体列表 2014-4-22 上午12:16:53
 * 
 * @author renmingfei
 */
public class ViewSelfStorageAction extends StorageBaseAction {

	private static final long serialVersionUID = 1L;
	private String storage_barcode = "";
	private String seclv_code;
	private Integer med_type;
	private String storage_name = "";
	private List<EntityStorage> storageList = null;

	public String getStorage_barcode() {
		return storage_barcode;
	}

	public void setStorage_barcode(String storage_barcode) {
		this.storage_barcode = storage_barcode;
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

	public String getStorage_name() {
		return storage_name;
	}

	public void setStorage_name(String storage_name) {
		this.storage_name = storage_name;
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
		map.put("use_user_iidd", getCurUser().getUser_iidd());
		map.put("storage_barcode", storage_barcode);
		map.put("seclv_code", seclv_code);
		map.put("med_type", med_type);
		map.put("storage_name", storage_name);
		storageList = storageService.getStorageList(map);
		return SUCCESS;
	}
}
