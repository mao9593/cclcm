package hdsec.web.project.computer.service;

import hdsec.web.project.common.bm.BMCycleItem;
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

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

/**
 * 计算机台账相关功能
 * 
 * @author liuyaling 2015-5-5
 */
public interface ComputerService {

	/**
	 * 添加计算机
	 * 
	 * @param computer
	 * @param cycleitem
	 */
	void addEntityComputer(EntityComputer computer, BMCycleItem cycleitem);

	/**
	 * 更新计算机台账
	 * 
	 * @param computer
	 */
	void updateComputer(EntityComputer computer);

	/**
	 * 根据Jobcode更新计算机台账
	 * 
	 * @param computer
	 */

	void updateComputerByJobCode(Map<String, Object> map);

	/**
	 * 查看设备类型
	 * 
	 * @return
	 */
	List<DeviceType> getDeviceTypeList();

	/**
	 * 通过类型名获得类型ID
	 * 
	 * @param typename
	 * @return
	 */
	Integer getTypeIDByName(String typename);

	/**
	 * 根据查询条件查看计算机台账
	 * 
	 * @param map
	 * @return list：entitycomputer
	 * @throws Exception
	 */
	List<EntityComputer> getAllComputerList(Map<String, Object> map) throws Exception;

	/** 查询检索的计算机数目 **/
	Integer getAllComputerSize(Map<String, Object> map) throws Exception;

	/**
	 * 根据条码查询计算机
	 * 
	 * @param computer_barcode
	 * @return
	 */
	EntityComputer getComputerByMap(Map<String, Object> map);

	/** 将申请信息添加到申请表 **/
	void addComputerEvent(ChangeDeviceEvent event, String next_approver) throws Exception;

	/**
	 * 查询计算机申请列表
	 * 
	 * @return
	 */
	List<ChangeDeviceEvent> getComputerEventList(Map<String, Object> map);

	/** 根据job_code获取event_code **/
	String getChangeDeviceEventCodeByJobCode(String job_code);

	/** 根据event_code获取申请表信息 **/
	ChangeDeviceEvent getChangeDeviceEventByEventCode(String event_code);

	/** 根据event_code更新关联表信息 **/
	void addDeviceOperationByEventCode(Map<String, Object> map);

	/** 根据job_code获取申请表信息 **/
	ChangeDeviceEvent getChangeDeviceEventByJobCode(String job_code);

	/** 根据job_code获取申请列表 **/
	List<ChangeDeviceEvent> getComputerEventListByJobCode(String job_code);

	/** 撤销申请 **/
	void deleteEvent(String event_code);

	/** 根据向申请表中添加设备barcode进行关联 **/
	void addBarcodeInEvent(String event_code, String barcode);

	/** 根据申请表添加计算机台账信息 **/
	void addComputerByEvent(ChangeDeviceEvent event, String computer_barcode, String user_name, String user_id)
			throws ParseException;

	/** 根据申请表更新计算机台账信息 **/
	String changeComputerByEvent(ChangeDeviceEvent event, String user_name, String user_id) throws Exception;

	/** 根据申请表内容添加到关联表中 **/
	void addDeviceOperation(ChangeDeviceEvent event, String user_name, String user_id);

	/** 根据event_code查询关联表中内容 **/
	EntityDeviceOperation getDeviceOperationDataByEventCode(String event_code);

	/** 信息设备模块 **/

	/**
	 * 信息设备类型接口方法
	 * 
	 * 参数说明:
	 * 
	 * device_type： 设备类型(1:计算机 2:网络设备 3:外部设备 4:办公自动化设备 5:安全产品 6:介质)
	 * 
	 * statue ：是否启用状态（0启用；1停用）
	 * 
	 * product_type:安全产品类型中区分：1、软件；2、硬件(其他类型默认均为0)
	 * 
	 **/
	List<InfoType> getInfoTypeList(Map<String, Object> map);

	/** 新增办公设备台账 **/
	void addInfoDevice(EntityInfoDevice device, BMCycleItem cycleitem);

	/** 根据子类型名称获取子类型ID **/
	Integer getInfoIDByName(Map<String, Object> map);

	/** 获取信息设备台账表 **/
	List<EntityInfoDevice> getInfoDeviceList(Map<String, Object> map);

	/** 根据信息设备条码查找台账表 **/
	EntityInfoDevice getInfoDeviceByBarcode(String device_barcode);

	/** 增加信息设备申请 **/
	void addInfoDeviceEvent(InfoDeviceEvent event, String next_approver) throws Exception;

	/** 查询信息设备作业信息 **/
	List<InfoDeviceEvent> getInfoDeviceEventList(Map<String, Object> map);

	/** 删除信息设备任务 **/
	void delInfoDeviceEvent(String event_code);

	/** 根据event_code查询信息设备任务 **/
	InfoDeviceEvent getInfoDeviceEventByEventCode(String event_code);

	/** 根据event_code查询信息设备任务的job_code **/
	String getInfoDeviceJobCodeByEventCode(String event_code);

	/** 根据job_code查询信息设备任务 **/
	List<InfoDeviceEvent> getInfoDeviceEventListByJobCode(String job_code);

	/** 根据job_code查询信息设备任务并添加到台账中 **/
	void addInfoDeviceByEvent(InfoDeviceEvent event);

	/** 根据job_code查询信息设备变更任务并更新设备台账表 **/
	void updateInfoDeviceChangeByEvent(String job_code);

	/** 修改信息设备中台账表更新台账表 **/
	void updateInfoDeviceByEntity(EntityInfoDevice info);

	/** 信息设备报废更新台账中的报废时间 **/
	void updateInfoDeviceDestroyByEvent(String job_code);

	/** 根据job_code更新event表中的event——content **/
	// void updateChangeDeviceEventByJobCode(Map<String, String> map);

	/**
	 * 信息设备类型自动生成保密编号接口
	 * 
	 * 参数说明：
	 * 
	 * device_type：设备类型：1：计算机 2：网络设备 3：外部设备 4：办公自动化设备 5：安全产品 6：介质
	 * 
	 * type：子类型(设备分类编号;可查看InfoType.info_id)；
	 * 
	 * dept_id：责任人部门ID
	 **/
	String createSecretSerial(String device_type, String type, String dept_id) throws Exception;

	/** 根据设备类型名称判断是否存在 **/
	boolean isTypeExistByName(String typename);

	/** 根据最大info_id获取下一个新增设备类型id **/
	String getNextDeviceTypeId(String id);

	/** 增加信息设备类型 **/
	void addInfoDeviceType(InfoType type);

	/** 修改信息设备类型名称、描述 **/
	void updateInfoType(InfoType type);

	/** 根据信息设备类型编号查询设备详细内容 **/
	InfoType getInfoTypeByID(Map<String, Object> map);

	/** 根据条码号删除计算机台账 **/
	void deleteEntityComputer(String computer_barcode);

	/**
	 * 向操作关联表中添加数据
	 * 
	 * @param operation
	 */
	void addRefDeviceOperation(EntityDeviceOperation operation);

	/**
	 * 更新ChangeDeviceEvent
	 * 
	 * @param map
	 */
	void updateChangeDeviceEvent(Map<String, String> map);

	/**
	 * 通过event_code获取job_code
	 * 
	 * @param event_code
	 * @return
	 */
	String getChangeDeviceEventJobCodeByEventCode(String event_code);

	/** 新增信息设备临时信息表 **/
	void addInfoDeviceTemp(EntityDeviceTemp temp);

	/** 根据event_code获取信息台账临时表 **/
	EntityDeviceTemp getDeviceTempByEventCode(String event_code);

	void updateDeviceEntityByTemp(String event_code);

	void updateDeviceTemp(EntityDeviceTemp temp);

	/** 笔记本借用模块 **/

	/** 增加笔记本借用申请 **/
	void addBorrowBookEvent(BorrowBookEvent event, String next_approver) throws Exception;

	/** 获取笔记本借用任务列表 **/
	List<BorrowBookEvent> getBorrowBookEventList(Map<String, Object> map);

	/** 根据job_code获取笔记本借用任务列表 **/
	List<BorrowBookEvent> getBorrowBookEventListByJobCode(String job_code);

	/**
	 * 根据设备条码号删除设备（假删）
	 * 
	 * @param device_barcode
	 */
	void deleteNetworkDevice(String device_barcode);

	/** 笔记本台账 **/

	/** 新增笔记本台账 **/
	void addBookEntity(EntityBook book, BMCycleItem cycleitem);

	/** 获取笔记本台账列表 **/
	List<EntityBook> getBookList(Map<String, Object> map);

	/** 获取笔记本台账列表 （分页查询） **/
	List<EntityBook> getALLBookList(Map<String, Object> map, RowBounds rbs) throws Exception;

	/** 更新笔记本台账 **/
	void updateEntityBook(EntityBook book);

	/** 删除笔记本台账 (更新是否启用状态，假删) **/
	void deleteEntityBook(String book_barcode);

	/** 获取笔记本台账列表个数 **/
	Integer getAllBookSize(Map<String, Object> map) throws Exception;

	/** 添加 **/
	void addEntityEventDevice(EntityEventDevice event_device_content);

	/** 获取信息设备查询列表个数 **/
	Integer getAllInfoDeviceSize(Map<String, Object> map) throws Exception;

	/** 更新笔记本借用作业表中审批绑定笔记本台账条码ID **/
	void updateBorrowBookAssociate(Map<String, Object> map);

	/** 根据event_code获取 **/
	EntityEventDevice getEntityEventDeviceByEventCode(String event_code);

	/** 更新 **/
	void updateEntityEventDevice(EntityEventDevice event_device_content);

	/** 信息设备台账删除（假删） **/
	void deleteEntityInfoDevice(String device_barcode);

	/** 根据计算机审批表数据更新到计算机台账中 **/
	void updateComputerByEvent(Map<String, Object> map);

	/** 根据设备条码更新信息设备台账信息 **/
	void updateInfoDeviceByBarcode(Map<String, Object> map);

	void deleteBorrowBookEventByEventCode(String event_code);

	/** 获取硬盘台账列表个数 **/
	Integer getAllHardDiskSize(Map<String, Object> map) throws Exception;

	/** 获取硬盘台帐列表 **/
	List<EntityHardDisk> getAllHardDiskList(Map<String, Object> map);

	/**
	 * 根据硬盘序列号查询硬盘信息
	 * 
	 * @param hdisk_no
	 * @return
	 */
	EntityHardDisk getHardDiskByMap(Map<String, Object> map);

	void updateHardDisk(EntityHardDisk hdisk);

	void deleteEntityHDisk(String hdisk_no);

	List<EntityHardDisk> getHardDiskList(Map<String, Object> map);

	/** 新增硬盘台账 **/
	void addEntityHardDisk(EntityHardDisk disk);

	/** 获取责任人为个人并且状态为留用的计算机台账数 **/
	String getComputerNumByMap(Map<String, Object> map);

	/** 获取责任人为个人并且状态为留用的信息设备（非介质类）台账数 **/
	String getInfoDeviceNumByMap(Map<String, Object> map);

	/** 获取责任人为个人并且状态为留用的信息设备中介质类台账数 **/
	String getMediaNumByMap(Map<String, Object> map);

	/** 笔记本申请表数据更新到台账中 **/
	void updateEntityBookByEvent(Map<String, Object> map);
}