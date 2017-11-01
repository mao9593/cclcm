package hdsec.web.project.asset.mapper;

import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.asset.model.EntityProperty;
import hdsec.web.project.asset.model.PropertyKind;
import hdsec.web.project.asset.model.PurchaseEvent;
import hdsec.web.project.asset.model.StorageEvent;
import hdsec.web.project.asset.model.WasteEvent;
import hdsec.web.project.ledger.model.EntityPaper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface AssetMapper {

	void addPurchaseEvent(PurchaseEvent event);

	List<PurchaseEvent> getPurchaseEventList(Map<String, Object> map);

	void addPurchaseEventJobRel(Map<String, Object> map);

	PurchaseEvent getPurchaseEventByCode(String event_code);

	void addPropertyledger(Map<String, Object> map, EntityProperty property,
			CycleItem cycleitem);

	void updatePurchasestatus(Map<String, Object> map);

	void addPropertyledger(EntityProperty property);

	void addCycleItem(CycleItem cycleitem);

	int getSelfPropertyLedgerSize(Map<String, Object> map);

	List<EntityProperty> getPropertyList(Map<String, Object> map);

	// List<EntityProperty> getSelfPropertyLedgerList(Map<String, Object> map,
	// RowBounds rbs);

	List<WasteEvent> getWasteEventList(Map<String, Object> map);

	List<PurchaseEvent> getPurchaseEventListByJobCode(String job_code);

	String getJobCodeByEventCode(String event_code);

	void delPurchaseEventByEnterCode(String event_code);

	EntityProperty getPropertyByIDBarcode(Map<String, String> map);

	List<CycleItem> getCycleItemListByBarcode(String barcode);

	void updateEntityPropertyStatus(Map<String, Object> map);

	void cancelHandlePropertyByCode(String property_barcode);

	int getHandlePropertyCountByJobCode(String job_code);

	void updatePropertyStatus(Map<String, Object> map);

	List<EntityProperty> getPropertyListByJobCode(String job_code);

	List<PropertyKind> getKindList();

	void addKindType(PropertyKind type);

	PropertyKind getKindTypeByID(Integer id);

	void delKindTypeByID(Integer id);

	void updateKindType(PropertyKind type);

	void addStorageEvent(StorageEvent event);

	void updateStorageEventJobCode(Map<String, String> map);

	List<StorageEvent> getStorageEventList(Map<String, Object> map);

	void delStorageEventByEnterCode(String event_code);

	String getStorageJobCodeByEventCode(String event_code);

	void delStorageEventByEventCode(String event_code);

	void delJob(String job_code);

	StorageEvent getStorageEventByCode(String event_code);

	void updateStoragestatus(Map<String, Object> map);

	List<StorageEvent> getStorageEventListByJobCode(String job_code);

	void addWasteEvent(WasteEvent event);

	void updatePropertyNoByCode(Map<String, Object> map);

	void updateVoucherNoByCode(Map<String, Object> map);

	List<WasteEvent> getWasteEventListByJobCode(String job_code);

	String getWasteJobCodeByEventCode(String event_code);

	WasteEvent getWasteEventByCode(String event_code);

	List<WasteEvent> getWasteChangeEventList(Map<String, Object> map);

	void delWasteChangeEventByEnterCode(String event_code);

	void updatePropertyStatusByID(Map<String, Object> map);

	void updateWasteStatus(Map<String, Object> map);

	void updateWasteEventStatus(Map<String, String> map);

	PropertyKind getSerialNoByKindId(int kind_id);

	void updateKindSerialNo(Map<String, Object> map);

}
