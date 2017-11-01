package hdsec.web.project.arch.mapper;

import hdsec.web.project.arch.model.ArchDict;
import hdsec.web.project.arch.model.ArchKey;
import hdsec.web.project.arch.model.ArchTypeName;
import hdsec.web.project.arch.model.ArchValue;
import hdsec.web.project.arch.model.BorrowLen;
import hdsec.web.project.arch.model.Dossier;
import hdsec.web.project.arch.model.EventArchBrw;
import hdsec.web.project.arch.model.Item;

import java.util.List;
import java.util.Map;

/**
 * @author handouwang
 * 
 *         2015-7-26/
 */
public interface ArchMapper {

	List<ArchDict> getAllArchDictList();

	int getDictCountByName(String dict_name);

	void addDict(ArchDict dict);

	void updateDict(ArchDict dict);

	ArchDict getDictById(int id);

	void setDictUsed(ArchDict dict);

	List<BorrowLen> getUsedSeclvList();

	int getBorrowLenCount(int seclv_code);

	void insertBorrowLen(BorrowLen len);

	void updateBorrowLen(BorrowLen len);

	void addArchKey(Map<String, Object> map);

	List<ArchTypeName> getTypeList(Map<String, String> map);

	void addType(Map<String, Object> map);

	void updateArchType(Map<String, Object> map);

	ArchKey getTemplateBySystemCode(String template_id);

	void updateArchPropertyValue(Map<String, Object> map);

	int getArchListSize(Map<String, Object> map);

	List<ArchValue> getArchList(Map<String, Object> map);

	ArchValue getArchValueById(int id);

	List<Dossier> getDosListByItemId(int itemId);

	List<Item> getItemListByTypeId(int typeId);

	ArchTypeName getArchTypeNameById(int id);

	int getItemCountByCode(String item_code);

	void addItem(Item item);

	Item getItemById(int id);

	int getDosCountByCode(String dos_code);

	void addDossier(Dossier dos);

	Dossier getDosById(int id);

	String getTemplateIdByDosId(int dos_id);

	void saveArchProperty(Map<String, Object> archValueMap);

	void updateDossier(Dossier dos);

	int getArchNumByDos(int id);

	void deleteDosById(int id);

	List<ArchTypeName> getValidTypeList();

	String getTemplateIdByBarcode(String barcode);

	int trgetTypeNameIdByTemplateId(String templateId);

	int getItemIdByTypeId(int typeId);

	int getDosIdByItemId(int itemId);

	void addEnventArch(Map<String, Object> map);

	void updateArchValuePropertyValue(Map<String, Object> map);

	List<EventArchBrw> getEventArchListByJobCode(String job_code);

	int getRecordIdByBarcode(String barcode);

	List<EventArchBrw> getArchBrwEventList(Map<String, Object> map);

	void setEventStatusLent(Map<String, Object> map);

	EventArchBrw getArchBrwEventById(int id);

	void setArchStatusLent(EventArchBrw event);

	void addArchBrwRecord(EventArchBrw event);

	void setEventStatusReturn(int id);

	void setArchStatusReturn(EventArchBrw event);

	void addArchReturnRecord(EventArchBrw event);

	Integer getBorrowLenBySeclvName(String seclv_name);

	List<Integer> getDosIdByCode(String dos_code);

	String getBarcodeByJobCodeFromEventArchBrw(String job_code);

	int getItemIdByDosId(int dos_id);

	int getDosIdByBarcode(String barcode);

	ArchValue getLastInsertArchByTemplate(String template_id);

	int getCountArchBarcodeByArchbarcode(String arch_barcode);

	EventArchBrw getArchEventByEnterCode(String event_code);

	EventArchBrw getArchEventByjobCode(String job_code);

	void cancelArchEventByEventCode(String event_code);

	int getArchEventCountByJobCode(String job_code);

	void resetArchStatus(Integer at_record_id);

	void delArchEventByJobCode(String job_code);

	void setEventStatusRenew(Map<String, Object> map);

	void addArchBrwRecordRenew(EventArchBrw event);

	void setEventDateRenew(Map<String, Object> map);

	void deleteArchById(String id);

	int getArchDeleteListSize(Map<String, Object> map);

	List<ArchValue> getArchDeleteList(Map<String, Object> map);

	void deleteThoroughArchById(String id);

	void updateArchProperty(Map<String, Object> archValueMap);

	void addArcAskingToReturnClientMsg(Map<String, Object> map);

	void delAskToReturnArchClientMsg(Map<String, Object> map);

}
