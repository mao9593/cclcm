package hdsec.web.project.computer.mapper;

import hdsec.web.project.computer.model.BorrowBookEvent;
import hdsec.web.project.computer.model.ChangeDeviceEvent;
import hdsec.web.project.computer.model.EntityBook;
import hdsec.web.project.computer.model.EntityComputer;
import hdsec.web.project.computer.model.EntityDeviceOperation;
import hdsec.web.project.computer.model.EntityDeviceTemp;
import hdsec.web.project.computer.model.EntityEventDevice;
import hdsec.web.project.computer.model.EntityHardDisk;
import hdsec.web.project.computer.model.EntityInfoDevice;
import hdsec.web.project.computer.model.InfoDeviceEvent;
import hdsec.web.project.computer.model.InfoType;
import hdsec.web.project.device.model.DeviceType;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

/**
 * 
 * @author liuyaling 2015-4-29
 */
public interface ComputerMapper {

	void addEntityComputer(EntityComputer computer);

	void updateComputer(EntityComputer computer);

	List<DeviceType> getComputerTypeList();

	Integer getTypeIDByName(String typename);

	List<EntityComputer> getAllComputerList(Map<String, Object> map);

	EntityComputer getComputerByMap(Map<String, Object> map);

	void addChangeDeviceEvent(ChangeDeviceEvent event);

	void updateChangeDeviceEventJobCode(Map<String, String> map);

	List<ChangeDeviceEvent> getComputerEventList(Map<String, Object> map);

	String getChangeDeviceEventCodeByJobCode(String job_code);

	ChangeDeviceEvent getChangeDeviceEventByEventCode(String event_code);

	ChangeDeviceEvent getChangeDeviceEventByJobCode(String job_code);

	String getChangeDeviceEventJobCodeByEventCode(String event_code);

	void deleteChangeDeviceEvent(String event_code);

	void addBarcodeInEvent(Map<String, String> map);

	void updateComputerByEvent(Map<String, Object> map);

	Integer getAllComputerSize(Map<String, Object> map);

	void addDeviceOperation(EntityDeviceOperation deviceOperation);

	List<ChangeDeviceEvent> getComputerEventListByJobCode(String job_code);

	void updateComputerByJobCode(Map<String, Object> map);

	// add by LS
	void updateChangeDeviceEventByJobCode(Map<String, String> map);

	/** 信息设备模块 **/

	List<InfoType> getInfoTypeList(Map<String, Object> map);

	void addInfoDevice(EntityInfoDevice device);

	Integer getInfoIDByName(Map<String, Object> map);

	List<EntityInfoDevice> getInfoDeviceList(Map<String, Object> map);

	EntityInfoDevice getInfoDeviceByBarcode(String device_barcode);

	void addInfoDeviceEvent(InfoDeviceEvent event);

	void updateInfoDeviceEventJobCode(Map<String, String> map);

	List<InfoDeviceEvent> getInfoDeviceEventList(Map<String, Object> map);

	String getInfoDeviceJobCodeByEventCode(String event_code);

	void delInfoDeviceEvent(String event_code);

	InfoDeviceEvent getInfoDeviceEventByEventCode(String event_code);

	List<InfoDeviceEvent> getInfoDeviceEventListByJobCode(String job_code);

	void addInfoDeviceByEvent(InfoDeviceEvent event);

	void updateInfoDeviceByBarcode(Map<String, Object> map);

	void updateInfoDeviceChangeByEvent(InfoDeviceEvent event);

	void updateInfoDeviceByEntity(EntityInfoDevice info);

	void updateInfoDeviceDestroyByEvent(String device_barcode);

	String getSerialNum(String device_type);

	void updateSerialNum(String device_type);

	int getInfoTypeCountByName(String typename);

	String getNextDeviceTypeId(String id);

	void addInfoDeviceType(InfoType type);

	void updateInfoType(InfoType type);

	InfoType getInfoTypeByID(Map<String, Object> map);

	String getInfoIdByInfoType(String info_type);

	void deleteEntityComputer(String computer_barcode);

	void addDeviceOperationByEventCode(Map<String, Object> map);

	EntityDeviceOperation getDeviceOperationDataByEventCode(String event_code);

	void addInfoDeviceTemp(EntityDeviceTemp temp);

	EntityDeviceTemp getDeviceTempByEventCode(String event_code);

	void updateDeviceEntityByTemp(String event_code);

	void updateDeviceTemp(EntityDeviceTemp temp);

	void updateChangeDeviceEvent(Map<String, String> map);

	void addBorrowBookEvent(BorrowBookEvent event);

	void updateBorrowBookEventJobCode(Map<String, String> map);

	List<BorrowBookEvent> getBorrowBookEventList(Map<String, Object> map);

	List<BorrowBookEvent> getBorrowBookEventListByJobCode(String job_code);

	void deleteNetworkDevice(String device_barcode);

	void addEntityEventDevice(EntityEventDevice event_device_content);

	void addBookEntity(EntityBook book);

	List<EntityBook> getBookList(Map<String, Object> map);

	void updateEntityBook(EntityBook book);

	void deleteEntityBook(String book_barcode);

	Integer getAllBookSize(Map<String, Object> map);

	Integer getAllInfoDeviceSize(Map<String, Object> map);

	void updateBorrowBookAssociate(Map<String, Object> map);

	EntityEventDevice getEntityEventDeviceByEventCode(String event_code);

	void updateEntityEventDevice(EntityEventDevice event_device_content);

	void deleteEntityInfoDevice(String device_barcode);

	void deleteBorrowBookEventByEventCode(String event_code);

	String getBorrowBookJobCodeByEventCode(String event_code);

	Integer getAllHardDiskSize(Map<String, Object> map) throws Exception;

	List<EntityHardDisk> getAllHardDiskList(Map<String, Object> map);

	EntityHardDisk getHardDiskByMap(Map<String, Object> map);

	void updateHardDisk(EntityHardDisk hdisk);

	void deleteEntityHDisk(String hdisk_no);

	List<EntityHardDisk> getHardDiskList(Map<String, Object> map);

	void addEntityHardDisk(EntityHardDisk disk);

	String getComputerNumByMap(Map<String, Object> map);

	String getInfoDeviceNumByMap(Map<String, Object> map);

	String getMediaNumByMap(Map<String, Object> map);

	void updateEntityBookByEvent(Map<String, Object> map);

	List<EntityBook> getALLBookList(Map<String, Object> map, RowBounds rbs) throws Exception;
}