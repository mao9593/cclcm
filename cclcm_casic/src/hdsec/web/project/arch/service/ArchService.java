package hdsec.web.project.arch.service;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.arch.model.ArchDict;
import hdsec.web.project.arch.model.ArchKey;
import hdsec.web.project.arch.model.ArchTypeName;
import hdsec.web.project.arch.model.ArchValue;
import hdsec.web.project.arch.model.BorrowLen;
import hdsec.web.project.arch.model.Dossier;
import hdsec.web.project.arch.model.EventArchBrw;
import hdsec.web.project.arch.model.Item;
import hdsec.web.project.user.model.ApproverUser;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

/**
 * @author handouwang
 * 
 *         2015-7-26/
 */
public interface ArchService {

	/**
	 * 添加档案模板
	 * 
	 * @param map
	 */
	void addArchKey(Map<String, Object> map);

	/**
	 * 查询档案类别管理列表
	 * 
	 * @param map
	 * @return
	 */
	List<ArchTypeName> getTypeList(Map<String, String> map);

	/**
	 * 添加类别
	 * 
	 * @param map
	 */
	void addType(Map<String, Object> map);

	/**
	 * 将档案管理表与模板表关联system_code 将system_code插入档案管理表
	 * 
	 * @param map
	 */
	void updateArchType(Map<String, Object> map);

	/**
	 * 通过system_code取出档案模板
	 * 
	 * @param template_id
	 * @return
	 */
	ArchKey getTemplateBySystemCode(String template_id);

	/**
	 * 查询字典列表
	 * 
	 * @return
	 */
	List<ArchDict> getAllArchDictList();

	/**
	 * 通过名称判断字典是否已经存在
	 * 
	 * @param dict_name
	 * @return
	 */
	boolean isDictExistByName(String dict_name);

	/**
	 * 添加新的字典
	 * 
	 * @param dict
	 */
	void addDict(ArchDict dict);

	/**
	 * 修改字典信息
	 * 
	 * @param dict
	 */
	void updateDict(ArchDict dict);

	/**
	 * 通过ID查询字典信息
	 * 
	 * @param id
	 * @return
	 */
	ArchDict getDictById(int id);

	/**
	 * 修改字典启动状态
	 * 
	 * @param id
	 * @param val
	 */
	void setDictUsed(String id, int is_used);

	/**
	 * 查询所有在用的密级
	 * 
	 * @return
	 */
	List<BorrowLen> getUsedSeclvList();

	/**
	 * 设置借用期限
	 * 
	 * @param seclv_code
	 * @param length
	 */
	void setBorrowLen(int seclv_code, int length);

	/**
	 * 更新属性
	 * 
	 * @param map
	 */
	void updateArchPropertyValue(Map<String, Object> map);

	/**
	 * 查询档案列表条数，用于分页
	 * 
	 * @param map
	 * @return
	 */
	/* 上次同步20150726 */
	int getArchListSize(Map<String, Object> map);

	/**
	 * 查询档案列表（分页后的数据）
	 * 
	 * @param map
	 * @param rbs
	 * @return
	 */
	List<ArchValue> getArchList(Map<String, Object> map, RowBounds rbs);

	/**
	 * 通过ID查询单条档案信息
	 * 
	 * @param id
	 * @return
	 */
	ArchValue getArchValueById(String id);

	/**
	 * 获取案卷展示树
	 * 
	 * @return
	 */
	List<ArchTypeName> getArchTypeTree();

	/**
	 * 通过ID查询档案类别信息
	 * 
	 * @param id
	 * @return
	 */
	ArchTypeName getArchTypeNameById(int id);

	/**
	 * 判断项目代号是否已经存在
	 * 
	 * @param item_code
	 * @return
	 */
	boolean isItemExistByCode(String item_code);

	/**
	 * 添加子项
	 * 
	 * @param item
	 */
	void addItem(Item item);

	/**
	 * 通过ID查询子项信息
	 * 
	 * @param id
	 * @return
	 */
	Item getItemById(int id);

	/**
	 * 判断案卷号是否已经存在
	 * 
	 * @param dos_code
	 * @return
	 */
	boolean isDosExistByCode(String dos_code);

	/**
	 * 添加案卷
	 * 
	 * @param dos
	 */
	void addDossier(Dossier dos);

	/**
	 * 通过ID查询案卷详细信息
	 * 
	 * @param id
	 * @return
	 */
	Dossier getDosById(int id);

	/**
	 * 通过案卷号查处档案模板号
	 * 
	 * @param id
	 * @return
	 */
	String getTemplateIdByDosId(int dos_id);

	/**
	 * 保存档案记录
	 * 
	 * @param archValueMap
	 */
	void saveArchProperty(Map<String, Object> archValueMap);

	/**
	 * 修改案卷信息
	 * 
	 * @param dos
	 */
	void updateDossier(Dossier dos);

	/**
	 * 通过案卷ID查询该案卷内的档案数目
	 * 
	 * @param id
	 * @return
	 */
	int getArchNumByDos(int id);

	/**
	 * 删除案卷
	 * 
	 * @param id
	 */
	void deleteDosById(int id);

	/**
	 * 查看已经定义了模板的档案类别
	 * 
	 * @param object
	 * @return
	 */
	List<ArchTypeName> getValidTypeList();

	/**
	 * 添加借阅流程
	 * 
	 * @author
	 */
	void addBorrowProcessJob(String user_iidd, String dept_id,
			Integer seclv_code, JobTypeEnum jobType, String next_approver,
			String comment, String event_codes, String usage_code,
			String entity_type, String barcodemedia, String entity_name)
			throws Exception;

	void addBorrowProcessJob(String user_iidd, String dept_id,
			Integer seclv_code, JobTypeEnum jobType, String next_approver,
			String comment, String event_codes, String usage_code,
			String entity_type, String barcodemedia, String entity_name,
			String arche_type) throws Exception;

	/**
	 * 通过barcode取出templateId
	 * 
	 * @param barcode
	 * @return
	 */
	String getTemplateIdByBarcode(String barcode);

	/**
	 * 通过templateId取出type_id
	 * 
	 * @param templateId
	 * @return
	 */
	int getTypeNameIdByTemplateId(String templateId);

	/**
	 * 通过type_id取出itemId
	 */
	int getItemIdByTypeId(int typeId);

	/**
	 * 
	 * @param itemId
	 * @return
	 */
	int getDosIdByItemId(int itemId);

	/**
	 * 添加借用记录
	 * 
	 * @param map
	 */
	void addEnventArch(Map<String, Object> map);

	/**
	 * 更新档案记录的某一个属性
	 * 
	 * @param map
	 */
	void updateArchValuePropertyValue(Map<String, Object> map);

	/**
	 * 根据job_code 取出待审批的档案
	 * 
	 * @param job_code
	 * @return
	 */
	List<EventArchBrw> getEventArchListByJobCode(String job_code);

	/**
	 * 通过barcode取出记录的Id
	 * 
	 * @param barcode
	 * @return
	 */
	int getRecordIdByBarcode(String barcode);

	/**
	 * 借出档案
	 * 
	 * @param id
	 * @throws Exception
	 */
	void setArchLent(int event_id, String user_iidd) throws Exception;

	/**
	 * 查看档案借阅申请列表
	 * 
	 * @param map
	 * @return
	 */
	List<EventArchBrw> getArchBrwEventList(Map<String, Object> map);

	/**
	 * 归还档案
	 * 
	 * @param id
	 */
	void setArchReturn(int event_id);

	/**
	 * 通过案卷号查询案卷ID
	 * 
	 * @param dos_code
	 * @return
	 * @throws Exception
	 */
	String getDosIdByCode(String dos_code) throws Exception;

	/**
	 * 通过job_code查出barcode
	 * 
	 * @param job_code
	 * @return
	 */
	String getBarcodeByJobCodeFromEventArchBrw(String job_code);

	/**
	 * 审批
	 * 
	 * @param job_code
	 * @param user
	 * @param approver
	 * @param approved
	 * @param opinion
	 * @param entitytype
	 * @throws Exception
	 */
	void approveJob(String job_code, ApproverUser user, ApproverUser approver,
			String approved, String opinion, String entitytype)
			throws Exception;

	/**
	 * 查找指定模板的上次插入的档案记录
	 * 
	 * @param template_id
	 * @return
	 */
	ArchValue getLastInsertArchByTemplate(String template_id);

	/**
	 * 查看有没有重复的barcode
	 * 
	 * @param arch_barcode
	 * @return
	 */
	int getCountArchBarcodeByArchbarcode(String arch_barcode);

	/**
	 * 通过event_code取消借用任务
	 * 
	 * @param event_code
	 * @return
	 */
	int cancelArchEventByEventCode(String event_code);

	/**
	 * 
	 * @param user_iidd
	 * @param dept_id
	 * @param seclv_code
	 * @param jobType
	 * @param next_approver
	 * @param comment
	 * @param event_codes
	 * @param usage_code
	 * @param entity_type
	 * @param barcodemedia
	 * @param filename
	 */
	void addRenewProcessJob(String user_iidd, String dept_id,
			Integer seclv_code, JobTypeEnum jobType, String next_approver,
			String comment, String event_codes, String usage_code,
			String entity_type, String barcodemedia, String filename)
			throws Exception;

	/**
	 * sava archRenew record
	 * 
	 * @param event_id
	 * @param user_iidd
	 * @throws Exception
	 */
	void setArchRenew(int event_id, String user_iidd) throws Exception;

	/**
	 * 续借审批
	 * 
	 * @param job_code
	 * @param user
	 * @param approver
	 * @param approved
	 * @param opinion
	 * @param entitytype
	 * @throws Exception
	 */
	void approveRenewJob(String job_code, ApproverUser user,
			ApproverUser approver, String approved, String opinion,
			String entitytype) throws Exception;

	/**
	 * 通过id删除档案
	 * 
	 * @param id
	 */
	void deleteArchById(String id);

	/**
	 * 查询被删除的列表的数量
	 * 
	 * @param map
	 * @return
	 */
	int getArchDeleteListSize(Map<String, Object> map);

	/**
	 * 查询被删除的列表
	 * 
	 * @param map
	 * @param rbs
	 * @return
	 */
	List<ArchValue> getArchDeleteList(Map<String, Object> map, RowBounds rbs);

	/**
	 * 彻底删除档案记录
	 * 
	 * @param id
	 */
	void deleteThoroughArchById(String id);

	/**
	 * 修改档案
	 * 
	 * @param archValueMap
	 */
	void updateArchProperty(Map<String, Object> archValueMap);

	/**
	 * 插入档案借阅催还消息
	 * 
	 * @param map
	 */
	void addArcAskingToReturnClientMsg(Map<String, Object> map);

	/**
	 * 删除借阅档案消息
	 * 
	 * @param map
	 */
	void delAskToReturnArchClientMsg(Map<String, Object> map);
}
