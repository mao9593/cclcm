package hdsec.web.project.device.service;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.device.model.DeviceEvent;
import hdsec.web.project.device.model.DeviceStatusEnum;
import hdsec.web.project.device.model.DeviceType;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.user.model.SecUser;

import java.util.List;
import java.util.Map;

public interface DeviceService {
	// 磁介质管理
	/**
	 * 添加磁介质
	 */
	void addEntityDevice(EntityDevice device, CycleItem cycleitem);

	/**
	 * 销毁磁介质
	 * 
	 * @param cycleitem
	 */
	void delDeviceByCode(String device_code, CycleItem cycleitem);

	/**
	 * 通过流水号查找磁介质
	 */
	EntityDevice getDeviceByCode(String device_code);

	/**
	 * 修改磁介质
	 */
	void updateDevice(EntityDevice device);

	/**
	 * 修改磁介质状态
	 */
	void updateDeviceStatus(String device_code, DeviceStatusEnum status);

	/**
	 * 取消磁介质销毁处理申请
	 */
	void cancelHandleDeviceByCode(String device_code);

	// 磁介质借入借出作业管理
	/**
	 * 添加作业
	 */
	void addDeviceEvent(DeviceEvent event);

	/**
	 * 查看作业列表
	 */
	List<DeviceEvent> getDeviceEventList(Map<String, Object> map);

	/**
	 * 通过流水号查找作业
	 */
	DeviceEvent getDeviceEventByDeviceCode(String event_code);

	/**
	 * 通过流水号删除作业
	 */
	void delDeviceEventByDeviceCode(String event_code);

	/**
	 * 通过作业流水号查询任务流水号
	 */
	String getJobCodeByEventCode(String event_code);

	/**
	 * 修改作业信息
	 */
	void updateDeviceEvent(DeviceEvent event);

	// 磁介质交接确认
	/**
	 * 磁介质借出交接确认
	 * 
	 * @param user
	 */
	void confirmDeviceBR(String event_code, String device_code, SecUser user);

	/**
	 * 根据磁介质barcode查找对应的event_code
	 */
	String getEventCodeByBarcode(String device_barcode, String borrow_user_iidd);

	/**
	 * 磁介质归还交接确认
	 */
	void confirmDeviceRT(String device_code, SecUser user);

	/**
	 * 添加磁介质借用任务
	 * 
	 * 2014-5-15 上午2:48:43
	 * 
	 * @author lixiang
	 * @param user_iidd
	 * @param dept_id
	 * @param seclv_code
	 * @param jobType
	 * @param next_approver
	 * @param comment
	 * @param hiddens
	 * @param usage_code
	 * @param project_code
	 * @throws Exception
	 */
	void addProcessJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType, String next_approver,
			String comment, String hiddens, String usage_code, String project_code) throws Exception;

	/**
	 * 通过event_id得到作业密级
	 * 
	 * 2014-5-15 上午3:16:35
	 * 
	 * @author lixiang
	 * @param event_id
	 * @return
	 */
	Integer getSeclvCodeByEventId(String event_id);

	/**
	 * 通过任务号查找借用作业列表
	 * 
	 * 2014-5-15 上午6:13:04
	 * 
	 * @author lixiang
	 * @param job_code
	 * @return
	 */
	List<DeviceEvent> getDeviceEventListByJobCode(String job_code);

	/**
	 * 取消磁介质借用申请
	 * 
	 * @author lixiang
	 * @param event_code
	 * @return
	 */
	int cancelDeviceEventByEventCode(String event_code);

	/**
	 * 通过条码号查找磁介质
	 */
	EntityDevice getDeviceByBarcode(String device_barcode);

	/**
	 * 获得磁介质类型列表
	 * 
	 * @return
	 */
	List<DeviceType> getDeviceTypeList();

	/**
	 * 添加磁介质类型
	 * 
	 * @param type
	 */
	void addDeviceType(DeviceType type);

	/**
	 * 判断磁介质类型的id是否存在
	 * 
	 * @param id
	 * @return
	 */
	boolean isTypeExistByID(Integer id);

	/**
	 * 判断磁介质类型的name是否存在
	 * 
	 * @param typename
	 * @return
	 */
	boolean isTypeExistByName(String typename);

	/**
	 * 通过ID获得磁介质类型
	 * 
	 * @param id
	 * @return
	 */
	DeviceType getDeviceTypeByID(Integer id);

	/**
	 * 修改磁介质类型
	 * 
	 * @param type
	 */
	void updateDeviceType(DeviceType type);

	/**
	 * 通过类型名获得类型ID
	 * 
	 * @param typename
	 * @return
	 */
	Integer getTypeIDByName(String typename);

	/**
	 * 判断该磁介质类型是否在用
	 * 
	 * @param id
	 * @return
	 */
	boolean isTypeInUse(Integer id);

	/**
	 * 删除磁介质类型
	 * 
	 * @param id
	 */
	void delDeviceTypeByID(Integer id);

}
