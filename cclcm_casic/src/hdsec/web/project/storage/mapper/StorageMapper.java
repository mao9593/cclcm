package hdsec.web.project.storage.mapper;

import hdsec.web.project.storage.model.EntityStorage;

import java.util.List;
import java.util.Map;

public interface StorageMapper {
	
	List<EntityStorage> getStorageList(Map<String, Object> map);
	
	void addStorage(EntityStorage storage);
	
	EntityStorage getStorageByCode(String storage_code);
	
	void updateStorage(EntityStorage storage);
	
	void delStorageByCode(String storage_code);
	
	void updateStorageStatus(Map<String, Object> map);
	
	void distributeStorage(Map<String, Object> map);
	
	void clearStorageUser(String storage_code);

	EntityStorage getStorageByBarcode(String storage_barcode);
	
}
