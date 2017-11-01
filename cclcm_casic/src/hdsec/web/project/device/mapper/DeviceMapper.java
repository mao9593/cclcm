package hdsec.web.project.device.mapper;

import hdsec.web.project.device.model.DeviceEvent;
import hdsec.web.project.device.model.DeviceType;
import hdsec.web.project.device.model.EntityDevice;

import java.util.List;
import java.util.Map;

public interface DeviceMapper {

	/**
	 * 磁介质管理
	 */

	void addEntityDevice(EntityDevice device);

	void setDeviceSealedByCode(String device_code);

	EntityDevice getDeviceByCode(String device_code);

	void updateDevice(EntityDevice device);

	void updateDeviceStatus(Map<String, Object> map);

	void cancelHandleDeviceByCode(String device_code);

	int getHandleDeviceCountByJobCode(String job_code);

	/**
	 * 磁介质借入借出作业管理
	 */
	void addDeviceEvent(DeviceEvent event);

	List<DeviceEvent> getDeviceEventList(Map<String, Object> map);

	DeviceEvent getDeviceEventByDeviceCode(String event_code);

	void delDeviceEventByDeviceCode(String event_code);

	String getJobCodeByEventCode(String event_code);

	void updateDeviceEvent(DeviceEvent event);

	void updateDeviceEventBarcode(Map<String, Object> map);

	String getEventCodeByBarcode(Map<String, Object> map);

	void updateConfirmDeviceEvent(DeviceEvent event);

	void updateConfirmEntityDevice(EntityDevice device);

	void addDeviceEventJobRel(Map<String, Object> map);

	Integer getSeclvCodeByEventId(String event_id);

	List<DeviceEvent> getDeviceEventListByJobCode(String job_code);

	void cancelDeviceEventByEventCode(String event_code);

	int getDeviceEventCountByJobCode(String job_code);

	EntityDevice getDeviceByBarcode(String device_barcode);

	List<DeviceType> getDeviceTypeList();

	void addDeviceType(DeviceType type);

	int getTypeCountByID(Integer id);

	int getTypeCountByName(String typename);

	DeviceType getDeviceTypeByID(Integer id);

	void updateDeviceType(DeviceType type);

	Integer getTypeIDByName(String typename);

	int getUseTypeCountByDevice(Integer id);

	void delDeviceTypeByID(Integer id);

	void updatePersonalEntityDevice(Map<String, Object> map);

}
