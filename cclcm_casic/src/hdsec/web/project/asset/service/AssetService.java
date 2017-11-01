package hdsec.web.project.asset.service;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
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

public interface AssetService {

	/**
	 * 添加资产采购申请作业
	 * 
	 * @param event
	 */
	void addPurchaseEvent(PurchaseEvent event);

	/**
	 * 获取资产采购作业列表
	 * 
	 * @param map
	 * @return
	 */
	List<PurchaseEvent> getPurchaseEventList(Map<String, Object> map);

	/**
	 * 添加资产采购审批流程
	 * 
	 * @param user_iidd
	 * @param dept_id
	 * @param seclv_code
	 * @param jobType
	 * @param next_approver
	 * @param output_dept_name
	 * @param output_user_name
	 * @param comment
	 * @param eventIdList
	 * @param usage_code
	 * @param project_code
	 * @throws Exception
	 */
	void addProcessJob(String user_iidd, String dept_id, Integer seclv_code,
			JobTypeEnum jobType, String next_approver, String comment,
			List<String> eventIdList, String usage_code, String project_code)
			throws Exception;

	/**
	 * 通过流水号查找作业
	 * 
	 * @param event_code
	 * @return
	 */
	PurchaseEvent getPurchaseEventByCode(String event_code);

	/**
	 * 整合资产入库操作，更新状态、入台帐、生命周期记录
	 * 
	 * @param map
	 * @param property
	 * @param cycleitem
	 */
	void addPropertyledger(Map<String, Object> map, Map<String, Object> mapP,
			EntityProperty property, CycleItem cycleitem);

	/**
	 * 获取资产台账列表（目前简单完成，后续待修改优化台账）
	 * 
	 * @param map
	 * @return
	 */
	int getSelfPropertyLedgerSize(Map<String, Object> map);

	/*
	 * 获取个人台账大小
	 */
	// List<EntityProperty> getSelfPropertyLedgerList(Map<String, Object> map,
	// RowBounds rbs);

	List<EntityProperty> getPropertyList(Map<String, Object> map);

	/**
	 * 获取资产报废作业列表
	 * 
	 * @param map
	 * @return
	 */
	List<WasteEvent> getWasteEventList(Map<String, Object> map);

	/**
	 * 根据任务作业号查询资产采购作业信息
	 * 
	 * @param job_code
	 * @return
	 */
	List<PurchaseEvent> getPurchaseEventListByJobCode(String job_code);

	/**
	 * 通过作业编号查找任务号
	 * 
	 * @param event_code
	 */
	String getJobCodeByEventCode(String event_code);

	/**
	 * 通过作业编号删除采购作业
	 * 
	 * @param event_code
	 */
	void delPurchaseEventByEnterCode(String event_code, String eventtype);

	/**
	 * 通过ID或条码号查询资产
	 * 
	 * @param barcode
	 * @return
	 */
	EntityProperty getPropertyByIDBarcode(Map<String, String> map);

	/**
	 * 通过条码号查询全生命周期记录
	 * 
	 * @param barcode
	 * @return
	 */
	List<CycleItem> getCycleItemListByBarcode(String barcode);

	/**
	 * 提交资产处理申请
	 * 
	 * @param user_iidd
	 * @param dept_id
	 * @param seclv_code
	 * @param jobType
	 * @param next_approver
	 * @param device_code
	 * @param usage_code
	 * @throws Exception
	 */
	void handlePropertyJob(String user_iidd, String dept_id,
			Integer seclv_code, JobTypeEnum jobType, String next_approver,
			String property_barcode, String usage_code) throws Exception;

	/**
	 * 取消资产变动申请
	 * 
	 * @param device_code
	 */
	void cancelHandlePropertyByCode(String property_barcode);

	/**
	 * 添加全生命周期记录
	 * 
	 * @param cycleitem
	 */
	void addCycleItem(CycleItem cycleitem);

	/**
	 * 更新资产状态
	 * 
	 * @param property_barcode
	 * @param property_status
	 */
	void updatePropertyStatus(String property_barcode, Integer property_status);

	/**
	 * 通过载体类型查询申请处理的审批列表
	 * 
	 * @param type
	 *            (PAPER/CD/PROPERTY)
	 * @return
	 */
	List<ProcessJob> getHandleJobListByEntity(String user_iidd, String type);

	List<ProcessJob> getHandleInJobListByEntity(String user_iidd, String type);

	/**
	 * 查看审批记录
	 * 
	 * @param user_iidd
	 * @param type
	 * @param user_name
	 * @param seclv_code
	 * @param jobType_code
	 * @return
	 */
	List<ProcessJob> getHandledJobByUserId(String user_iidd, String type,
			String user_name, Integer seclv_code, String jobType_code);

	List<ProcessJob> getHandledInJobByUserId(String user_iidd, String type,
			String user_name, Integer seclv_code, String jobType_code);

	/**
	 * 根据作业号查询资产
	 * 
	 * @param job_code
	 * @return
	 */
	List<EntityProperty> getPropertyListByJobCode(String job_code);

	/**
	 * 获得资产类型列表
	 * 
	 * @return
	 */
	List<PropertyKind> getKindList();

	/**
	 * 添加资产类型
	 * 
	 * @param type
	 */
	void addKindType(PropertyKind type);

	/**
	 * 通过ID获得资产类型
	 * 
	 * @param id
	 * @return
	 */
	PropertyKind getKindTypeByID(Integer id);

	/**
	 * 删除资产类型
	 * 
	 * @param id
	 */
	void delKindTypeByID(Integer id);

	/**
	 * 修改资产类型
	 * 
	 * @param type
	 */
	void updateKindType(PropertyKind type);

	/**
	 * 添加资产入库申请作业
	 * 
	 * @param event
	 */
	void addStorageEvent(StorageEvent event, String next_approver)
			throws Exception;

	/**
	 * 获取资产入库作业列表
	 * 
	 * @param map
	 * @return
	 */
	List<StorageEvent> getStorageEventList(Map<String, Object> map);

	/**
	 * 通过作业编号删除入库作业
	 * 
	 * @param event_code
	 */
	void delStorageEventByEnterCode(String event_code);

	/**
	 * 通过作业编号查询入库任务号
	 * 
	 * @param event_code
	 * @return
	 */
	String getStorageJobCodeByEventCode(String event_code);

	/**
	 * 通过流水号查找入库作业
	 * 
	 * @param event_code
	 * @return
	 */
	StorageEvent getStorageEventByCode(String event_code);

	/**
	 * 根据任务作业号查询资产入库作业信息
	 * 
	 * @param job_code
	 * @return
	 */
	List<StorageEvent> getStorageEventListByJobCode(String job_code);

	/**
	 * 添加wasteevent
	 * 
	 * @param
	 * @return
	 */
	void addWasteEvent(WasteEvent waste_event, String next_approver,
			String handleType) throws Exception;;

	/**
	 * 通过作业编号更新资产编号
	 * 
	 * @param event_code
	 */
	void updatePropertyNoByCode(Map<String, Object> map);

	/**
	 * 通过作业编号更新凭证号
	 * 
	 * @param event_code
	 */
	void updateVoucherNoByCode(Map<String, Object> map);

	/**
	 * 根据任务作业号查询资产报废变更作业信息
	 * 
	 * @param job_code
	 * @return
	 */
	List<WasteEvent> getWasteEventListByJobCode(String job_code);

	/**
	 * 通过作业编号查找变更报废任务号
	 * 
	 * @param event_code
	 */
	String getWasteJobCodeByEventCode(String event_code);

	/**
	 * 通过流水号查找变更报废作业
	 * 
	 * @param event_code
	 * @return
	 */
	WasteEvent getWasteEventByCode(String event_code);

	/**
	 * 获取资产采购作业列表
	 * 
	 * @param map
	 * @return
	 */
	List<WasteEvent> getWasteChangeEventList(Map<String, Object> map);

	/**
	 * 通过作业编号删除报废变更作业
	 * 
	 * @param event_code
	 */
	void delWasteChangeEventByEnterCode(String event_code);

	/**
	 * 通过id更新资产状态
	 * 
	 * @param property_barcode
	 * @param property_status
	 */
	void updatePropertyStatusByID(Map<String, Object> map);

	/**
	 * 更新报废变更作业
	 * 
	 * @param property_barcode
	 * @param property_status
	 */
	void updateWasteStatus(String event_code, Integer waste_status);

	/**
	 * 根据资产种类查询对应流水号
	 * 
	 * @param kind_id
	 * @return
	 */
	PropertyKind getSerialNoByKindId(int kind_id);

	/**
	 * 紧急采购申请
	 * 
	 * @param purchaseevent
	 * @param next_approver
	 * @return
	 */
	void addUrgentPropertyEvent(PurchaseEvent purchaseevent,
			String next_approver) throws Exception;
}
