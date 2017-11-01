package hdsec.web.project.storage.service;

import hdsec.web.project.storage.model.EntityStorage;
import hdsec.web.project.user.model.SecUser;

import java.util.List;
import java.util.Map;

/**
 * 存储模块service接口
 * 2014-4-22 上午12:15:00
 * 
 * @author renmingfei
 */
public interface StorageService {
	
	List<EntityStorage> getStorageList(Map<String, Object> map) throws Exception ;
	
	void addStorage(EntityStorage storage);
	
	EntityStorage getStorageByCode(String storage_code);
	
	void updateStorage(EntityStorage storage);
	
	void delStorageByCode(String storage_code);
	
	void updateStorageStatus(Map<String, Object> map);
	
	EntityStorage getStorageByBaecode(String storage_barcode);

	/**
	 * 为用户分配存储介质并交接
	 * @param storage_code
	 * @param use_user
	 * @param admin
	 */
	void distributeStorage(String storage_code, SecUser use_user,
			SecUser admin);

	/**
	 * 收回存储介质
	 * @param storage
	 * @param admin
	 */
	void returnStorage(EntityStorage storage, SecUser admin);
	
}
