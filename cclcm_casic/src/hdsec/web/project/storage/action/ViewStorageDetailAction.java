package hdsec.web.project.storage.action;

import hdsec.web.project.storage.model.EntityStorage;

/**
 * 查看存储介质详细信息
 * 2014-4-23 下午8:56:36
 * 
 * @author renmingfei
 */
public class ViewStorageDetailAction extends StorageBaseAction {
	private static final long serialVersionUID = 1L;
	private String storage_code = "";
	private EntityStorage storage = null;
	
	public EntityStorage getStorage() {
		return storage;
	}
	
	public void setStorage_code(String storage_code) {
		this.storage_code = storage_code;
	}
	
	@Override
	public String executeFunction() throws Exception {
		storage = storageService.getStorageByCode(storage_code);
		return SUCCESS;
	}
	
}
