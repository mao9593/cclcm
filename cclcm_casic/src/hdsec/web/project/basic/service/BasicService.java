package hdsec.web.project.basic.service;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.basic.model.CDStatic;
import hdsec.web.project.basic.model.ClientMsg;
import hdsec.web.project.basic.model.DeptAdminConfig;
import hdsec.web.project.basic.model.FileInfo;
import hdsec.web.project.basic.model.NewSecRole;
import hdsec.web.project.basic.model.PaperStatic;
import hdsec.web.project.basic.model.SysBarcode;
import hdsec.web.project.basic.model.SysBurner;
import hdsec.web.project.basic.model.SysConfigItem;
import hdsec.web.project.basic.model.SysConsole;
import hdsec.web.project.basic.model.SysExchangeBox;
import hdsec.web.project.basic.model.SysKeyword;
import hdsec.web.project.basic.model.SysMfp;
import hdsec.web.project.basic.model.SysPlace;
import hdsec.web.project.basic.model.SysPrinter;
import hdsec.web.project.basic.model.SysPrinterGroup;
import hdsec.web.project.basic.model.SysPrinterUser;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysProxy;
import hdsec.web.project.basic.model.SysRecycleBox;
import hdsec.web.project.basic.model.SysSeclevel;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.common.BaseEvent;
import hdsec.web.project.enter.model.SubSecDeptAdmin;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecRoleUser;
import hdsec.web.project.user.model.SecUser;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface BasicService {
	public final String INIT_STR = "00000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000";
	/**
	 * 用途管理
	 * 
	 * @return
	 */
	// List<SysUsage> getAllSysUsageList();
	public final static String SEP_STR = "_";

	void addSysUsage(SysUsage usage);

	void addSpecialPaperType(SysUsage usage);

	void delUsageByCode(String usage_code);

	SysUsage getUsageByCode(String usage_code);

	void updateUsage(SysUsage usage);

	List<SysUsage> getSysUsageList();

	List<SysUsage> getSpecialPaperTypeList(Map<String, Object> map);

	List<SysUsage> getSpecialPaperTypeList();

	/**
	 * 项目管理
	 * 
	 * @return
	 */
	// List<SysProject> getAllSysProjectList();

	void addSysProject(SysProject project);

	void delProjectByCode(String project_code);

	SysProject getProjectByCode(String project_code);

	void updateProject(SysProject project);

	List<SysProject> getSysProjectList();

	/**
	 * 控制台管理
	 * 
	 * @return
	 */
	// List<SysConsole> getAllSysConsoleList();

	SysConsole getConsoleByCode(String console_code);

	void updateConsole(SysConsole console);

	void delConsoleByCode(String console_code);

	void addConsole(SysConsole console) throws Exception;

	List<SysConsole> getSysConsoleList();

	List<SysConsole> getSysConsoleList(Map<String, Object> map);

	/**
	 * 一体机管理
	 */
	// List<SysMfp> getAllSysMfpList();

	void addSysMfp(SysMfp mfp);

	void delMfpByCode(String mfp_code);

	SysMfp getMfpByCode(String mfp_code);

	void updateMfp(SysMfp mfp);

	List<SysMfp> getSysMfpList();

	/**
	 * 刻录机管理
	 */
	// List<SysBurner> getAllSysBurnerList();

	void addSysBurner(SysBurner burner);

	void delBurnerByCode(String burner_code);

	SysBurner getBurnerByCode(String burner_code);

	void updateBurner(SysBurner burner);

	List<SysBurner> getSysBurnerList();

	/**
	 * 打印机管理
	 */
	// List<SysPrinter> getAllSysPrinterList();

	void addSysPrinter(SysPrinter printer);

	void delPrinterByCode(String printer_code);

	SysPrinter getPrinterByCode(String printer_code);

	void updatePrinter(SysPrinter printer);

	List<SysPrinter> getSysPrinterList();

	List<SysPrinterGroup> getPrinterGroupList(String printer_code, String dept_id);

	void updatePrinterGroup(String printer_code, String dept_ids);

	void addPrinterGroup(SysPrinterGroup printerGroup);

	List<SysPrinter> getSysPrinterList(Map<String, Object> map);

	/**
	 * 通过作业号查询作业列表
	 * 
	 * @param jobType
	 * @param event_codes
	 * @return
	 * @throws Exception
	 */
	List<BaseEvent> getEventListByEventCodes(JobTypeEnum jobType, String event_codes) throws Exception;

	/**
	 * 通过密级code列表查询其中的最高密级
	 * 
	 * @param seclvCodeList
	 */
	SecLevel getHighestSeclvByCodeList(List<Integer> seclvCodeList);

	/**
	 * 通过用户部门、作业密级、流程类型、用途查询流程定义
	 * 
	 * @param dept_id
	 * @param seclv_code
	 * @param jobTypeCode
	 * @param usage_code
	 * @return
	 * @throws Exception
	 */
	ApproveProcess getApproveProcessByKey(String dept_id, Integer seclv_code, String jobTypeCode, String usage_code)
			throws Exception;

	/**
	 * 查询下级审批人
	 * 
	 * @param job_code
	 * @param dept_id
	 * @param seclv_code
	 * @param jobType_code
	 * @param usage_code
	 * @return
	 * @throws Exception
	 */
	List<ApproverUser> getNextApprover(String job_code, String dept_id, Integer seclv_code, String jobType_code,
			String usage_code) throws Exception;

	/**
	 * 查询任务列表
	 * 
	 * @param map
	 * @return
	 */
	List<ProcessJob> getJobList(Map<String, Object> map, RowBounds rbs);

	/**
	 * 通过任务流水号查询任务信息
	 * 
	 * @param job_code
	 * @return
	 */
	ProcessJob getProcessJobByCode(String job_code);

	/**
	 * 根据下级审批人ID查询用户姓名
	 * 
	 * @param next_approver
	 * @return
	 */
	String getApproverName(String next_approver);

	/**
	 * 添加任务流程申请(多条任务)
	 * 
	 * @param job
	 * @throws Exception
	 */
	void addMultiProcessJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, String output_dept_name, String output_user_name, String comment, String event_codes,
			String usage_code, String project_code) throws Exception;

	/**
	 * 取消任务流程
	 * 
	 * @param job_code
	 */
	void cancelJob(String job_code, String jobType_code);

	/**
	 * 通过任务流水号查询作业列表
	 * 
	 * @param job_code
	 * @return
	 * @throws Exception
	 */
	List<BaseEvent> getEventListByJobCode(String job_code) throws Exception;

	/**
	 * 通过任务流水号查询任务类型代号
	 * 
	 * @param job_code
	 * @return
	 */
	String getJobTypeCodeByJobCode(String job_code);

	/**
	 * 通过任务流水号查询任务类型枚举对象
	 * 
	 * @param job_code
	 * @return
	 */
	JobTypeEnum getJobTypeEnumByJobCode(String job_code);

	/**
	 * 根据用户ID查询候选审批任务列表
	 * 
	 * @param user_iidd
	 * @param jobType
	 * @return
	 */
	List<ProcessJob> getCandidateListByUserId(String user_iidd, JobTypeEnum jobType);

	/**
	 * 根据用户ID查询已分配审批任务列表
	 * 
	 * @param user_iidd
	 * @param jobType
	 * @return
	 */
	List<ProcessJob> getAssignedListByUserId(String user_iidd, JobTypeEnum jobType);

	/**
	 * 审批流程申请任务
	 * 
	 * @param job_code
	 * @param user
	 * @param approver
	 * @param approved
	 * @param opinion
	 * @throws Exception
	 */
	void approveJob(String job_code, ApproverUser user, ApproverUser approver, String approved, String opinion,
			String entitytype) throws Exception;

	/**
	 * 查询用户自己的审批历史记录
	 * 
	 * @param user_iidd
	 * @param jobType
	 * @param user_name
	 * @param seclv_code
	 * @return
	 */
	List<ProcessJob> getApprovedJobByUserId(String user_iidd, JobTypeEnum jobType, String user_name, Integer seclv_code);

	/**
	 * 关闭任务流程
	 * 
	 * @param job_code
	 * @param jobType_code
	 * @throws Exception
	 */
	void closeJob(String job_code, String jobType_code) throws Exception;

	/**
	 * 查询比参数密级高的密级列表
	 * 
	 * @param seclv_code
	 * @return
	 */
	List<SecLevel> getHigherSeclvList(SecLevel seclv);

	/**
	 * 添加任务流程申请(单条任务)
	 * 
	 * @param user_iidd
	 * @param dept_id
	 * @param seclv_code
	 * @param jobType
	 * @param next_approver
	 * @param output_dept_name
	 * @param output_user_name
	 * @param comment
	 * @param event_codes
	 * @param project_code
	 * @param usage_code
	 * @throws Exception
	 */
	void addProcessJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType, String next_approver,
			String output_dept_name, String output_user_name, String comment, String event_codes, String usage_code,
			String project_code) throws Exception;

	/**
	 * 通过密级编号查询密级信息
	 * 
	 * @param seclv_code
	 * @return
	 */
	SysSeclevel getSysSecLevelByCode(Integer seclv_code);

	/**
	 * 配置密级信息
	 * 
	 * @param seclv
	 */
	void configSeclv(SysSeclevel seclv);

	/**
	 * 根据条码列表查询纸质台帐列表
	 * 
	 * @param _chk
	 * @return
	 */
	List<EntityPaper> getPaperListByBarcodes(String barcodes);

	/**
	 * 提交纸质载体处理申请
	 * 
	 * @param user_iidd
	 * @param dept_id
	 * @param seclv_code
	 * @param jobType
	 * @param next_approver
	 * @param barcodes
	 * @param output_user_name
	 * @param output_dept_name
	 * @param usage_code
	 * @throws Exception
	 */
	void handlePaperJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, String barcodes, String output_dept_name, String output_user_name, String usage_code,
			String supervise_user_iidd) throws Exception;

	/**
	 * 提交纸质载体"全部提交"处理申请
	 * 
	 * @param user_iidd
	 * @param dept_id
	 * @param seclv_code
	 * @param jobType
	 * @param next_approver
	 * @param paperList
	 * @param usage_code
	 * @throws Exception
	 */
	void handleSubmitAllPaperJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, List<EntityPaper> paperList, String usage_code) throws Exception;

	/**
	 * 提交光盘载体"全部提交"处理申请
	 * 
	 * @param user_iidd
	 * @param dept_id
	 * @param seclv_code
	 * @param jobType
	 * @param next_approver
	 * @param cdList
	 * @param usage_code
	 * @throws Exception
	 */
	void handleSubmitAllCDJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, List<EntityCD> cdList, String usage_code) throws Exception;

	/**
	 * 通过载体类型查询申请处理的审批列表
	 * 
	 * @param type
	 *            (PAPER/CD)
	 * @return
	 */
	List<ProcessJob> getHandleJobListByEntity(String user_iidd, String type);

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
	List<ProcessJob> getHandledJobByUserId(String user_iidd, String type, String user_name, Integer seclv_code,
			String jobType_code);

	/**
	 * 通过任务流水号删除任务
	 * 
	 * @param job_code
	 */
	void delJob(String job_code);

	List<SysProxy> getUserProxys(String user_iidd);

	void addUserProxy(Map<String, Object> map);

	void deleteUserProxy(Map<String, Object> map);

	/**
	 * 智能回收柜管理
	 */
	// List<SysRecycleBox> getAllRecycleBoxList();

	void addSysRecycleBox(SysRecycleBox recycleBox);

	void delSysRecycleBoxByCode(String box_code);

	SysRecycleBox getRecycleBoxByCode(String box_code);

	void updateRecycleBox(SysRecycleBox recycleBox);

	List<SysRecycleBox> getSysRecycleBoxList();

	List<SysPrinterUser> getPrintUserList(String printer_code, String user_iidd);

	void addPrinterUsers(String user_iidds, String printer_code);

	void delPrinterUser(String printer_code, String user_iidd);

	/**
	 * 智能交换柜管理
	 */
	// List<SysExchangeBox> getAllExchangeBoxList();

	void addSysExchangeBox(SysExchangeBox exchangeBox);

	void delSysExchangeBoxByCode(String box_code);

	SysExchangeBox getExchangeBoxByCode(String box_code);

	void updateExchangeBox(SysExchangeBox exchangeBox);

	List<SysExchangeBox> getSysExchangeBoxList();

	/**
	 * 获取（打印刻录复印录入）配置参数
	 * 
	 * @param string
	 * @return
	 */
	SysConfigItem getSysConfigItemValue(String item_key);

	/**
	 * 更改（打印刻录复印录入）配置参数
	 * 
	 * @param configItem
	 */
	void updateSysConfigItem(SysConfigItem sysConfigItem);

	/**
	 * 增添（打印刻录复印录入）配置参数
	 * 
	 * @param configItem
	 */
	void addSysConfigItem(SysConfigItem sysConfigItem);

	List<SysProxy> getUserAprvProxys(String user_iidd);

	void addUserAprvProxy(Map<String, Object> map);

	void deleteUserAprvProxy(Map<String, Object> map);

	String getParentDeptIdByCurrentId(String curr_user_dept);

	List<SysPlace> getAllPlaces();

	void addPlace(Map<String, Object> map);

	void delPlace(String place_code);

	void updatePlace(Map<String, Object> map);

	SysPlace getPlaceByCode(String place_code);

	/**
	 * 根据条码列表查询光盘台帐列表
	 * 
	 * @param _chk
	 * @return
	 */
	List<EntityCD> getCDListByBarcodes(String barcodes);

	/**
	 * 提交光盘载体处理申请
	 * 
	 * @param user_iidd
	 * @param dept_id
	 * @param seclv_code
	 * @param jobType
	 * @param next_approver
	 * @param barcodes
	 * @param output_user_name
	 * @param output_dept_name
	 * @param usage_code
	 * @throws Exception
	 */
	void handleCDJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType, String next_approver,
			String barcodes, String output_dept_name, String output_user_name, String usage_code,
			String supervise_user_iidd) throws Exception;

	/**
	 * 提交磁介质处理申请
	 * 
	 * @param user_iidd
	 * @param dept_id
	 * @param seclv_code
	 * @param jobType
	 * @param next_approver
	 * @param device_code
	 * @param usage_code
	 */
	void handleDeviceJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, String device_code, String usage_code, String supervise_user_iidd) throws Exception;

	/**
	 * 提交归档申请
	 * 
	 * @param item_id
	 * @param type
	 * @param user
	 */
	void submitApplyFile(String item_id, String type, SecUser user) throws Exception;

	/**
	 * 添加借阅流程 2014-4-22 上午3:10:02
	 * 
	 * @author gaoximin
	 */
	void addBorrowProcessJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, String output_dept_name, String output_user_name, String comment, String event_codes,
			String usage_code, String project_code, String entity_type, String barcodemedia, String entity_name,
			Date limitTime) throws Exception;

	/**
	 * 根据部门管理员ID查询管理的部门 2014-4-22 上午8:58:59
	 * 
	 * @author gaoximin
	 * @param user_iidd
	 * @return
	 */
	List<SubSecDeptAdmin> getAdminGroupList(String user_iidd);

	/**
	 * 获取条码列表
	 * 
	 * @param map
	 * 
	 */
	List<SysBarcode> getAllSysBarcodes();

	/**
	 * 获取单个条码
	 * 
	 * @param barcode_code
	 */
	SysBarcode getOneBarcodeByCode(String barcode_code);

	/**
	 * 保存条码
	 * 
	 * @param barcode
	 */
	void saveOneBarcode(SysBarcode barcode);

	/**
	 * 密级codes找name
	 * 
	 * @param map
	 */
	List<String> getSeclvNameByCodes(Map<String, Object> map);

	/**
	 * 控制台codes找name
	 * 
	 * @param map
	 */
	List<String> getConsoleNameByCodes(Map<String, Object> map);

	/**
	 * 项目codes找name
	 * 
	 * @param map
	 */
	List<String> getProjectNameByCodes(Map<String, Object> map);

	/**
	 * 用途codes找name
	 * 
	 * @param map
	 */
	List<String> getUsageNameByCodes(Map<String, Object> map);

	/**
	 * 删除条码
	 * 
	 * @param barcode_code
	 */
	void delBarcodeByCode(String barcode_code);

	/**
	 * 更新条码
	 * 
	 * @param barcode
	 */
	void updateBarcode(SysBarcode barcode);

	/**
	 * 获取单个条码
	 * 
	 * @param barcode_code
	 */
	SysBarcode getBarcodeByCode(String barcode_code);

	/**
	 * 根据传入的载体类型，生成载体条码号 打印文件:PRINTPAPER 涉密图纸:PRINTGRAPH 复印文件:COPY 外来文件:ENTERPAPER 保密手册:SECBOOK 刻录光盘:BURN 外来光盘:ENTERCD
	 * 涉密移动载体:DEVICE 涉密存储介质:STORAGE 涉密活动中使用的涉密载体:ACTIVITY 2014-4-29 下午4:15:52
	 * 
	 * @author renmingfei
	 * @param entityType
	 * @return
	 * @throws Exception
	 */
	String createEntityBarcode(String entityType) throws Exception;

	/**
	 * 将已归档载体还原为留用状态
	 * 
	 * @author lixiang
	 * @param item_id
	 * @param type
	 * @param user
	 */
	void fileToUse(String item_id, String type, SecUser user);

	/**
	 * 保存文件信息
	 * 
	 * @author yy
	 * @param fileName
	 * @param relstorePath
	 * @param user_iidd
	 * @param comment
	 * @param type
	 */
	void saveFileInfo(String fileName, String relstorePath, String user_iidd, String comment, String type);

	/**
	 * 纸张统计列表
	 * 
	 * @author lixiang
	 * @param map
	 * @return
	 */
	List<PaperStatic> getPaperStaticList(Map<String, Object> map);

	/**
	 * 添加客户端提醒消息 2014-5-5 上午2:26:46
	 * 
	 * @author gaoximin
	 * @param burner
	 */
	void addClientMsg(ClientMsg clientMsg);

	/**
	 * 按照用户ID或任务号JOB_CODE，更新当前用户消息提醒为已读，更新已读时间 2014-5-5 上午2:35:18
	 * 
	 * @author gaoximin
	 * @param map
	 */
	void updateClientMsg(String accept_user_iidd, String job_code, Integer is_read, Date read_time);

	/**
	 * 判断用户特征值或者名称是否已经存在 2014-5-6 上午9:38:34
	 * 
	 * @author renmingfei
	 * @param usage_code
	 * @param usage_name
	 * @return
	 */
	boolean isUsageExist(String usage_code, String usage_name);

	/**
	 * @author yy
	 * @param project_code
	 * @param project_name
	 */
	boolean checkProject(String project_code, String project_name);

	/**
	 * @author yy
	 * @param console_code
	 * @param console_name
	 */
	boolean checkConsole(String console_code, String console_name);

	/**
	 * @author yy
	 * @param type
	 */
	List<FileInfo> getFileInfosByType(String type);

	/**
	 * @author yy
	 * @param console_code
	 * @param hidSet_version
	 */
	void updateConsoleVersion(String console_code, String hidSet_version);

	/**
	 * 置指定类型的客户端提示消息为已读状态 2014-5-15 下午5:20:15
	 * 
	 * @author renmingfei
	 * @param user_iidd
	 * @param string
	 * @param i
	 */
	void setClientMsgRead(String user_iidd, String oper_type, int i);

	/**
	 * 生产二维码 参数值可以为空（除了entityType）,为空将按位数补"0"; 如 seclv_id = null, 结果"00" 参数过长,将按位数取前几位; 如 seclv_id = "1234", 结果"12"
	 * 参数长度不够,将在前面补对应个数的"0"; 如 seclv_id = "1", 结果"01" 参数中含有中文字符,每个中文字符将按两个字符计算.如果需要截断单个中文字符,将会截断到最后一个中文,然后往前补"0"; 如
	 * user_name="1司马相如"， 结果"01司马相"
	 * 
	 * @author yy
	 * @param 加密标识
	 *            seclv_id 2位
	 * @param 二维码标准
	 *            version 2位
	 * @param 员工姓名
	 *            user_name 8位
	 * @param 密级
	 *            seclv_code 1位
	 * @param 介质
	 *            medium 1位
	 * @param 来源
	 *            from_src 1位
	 * @param 页数
	 *            papge_count 4位
	 * @param 页码
	 *            page_index 4位
	 * @param 文件名称
	 *            file_name 128位
	 * @param 制作部门
	 *            dept_id 4位
	 * @param 载体类型
	 *            entityType String createTwoDimenosionalBarcode(String seclv_id, String version, String user_name,
	 *            String seclv_code, String medium, String from_src, String papge_count, String page_index, String
	 *            file_name, String dept_id, String entityType) throws Exception;
	 */
	/**
	 * 根据已存在的一维码生成二维码
	 * 
	 * @param 加密标识
	 *            seclv_id 2位
	 * @param 二维码标准
	 *            version 2位
	 * @param 员工姓名
	 *            user_name 8位
	 * @param 密级
	 *            seclv_code 1位
	 * @param 介质
	 *            medium 1位
	 * @param 来源
	 *            from_src 1位
	 * @param 页数
	 *            papge_count 4位
	 * @param 页码
	 *            page_index 4位
	 * @param 文件名称
	 *            file_name 128位
	 * @param 制作部门
	 *            dept_id 4位
	 * @param 载体条码
	 *            （一维） entity_barcode
	 */
	String buildTwoDimenosionalBarcode(String seclv_id, String version, String user_name, String seclv_code,
			String medium, String from_src, String papge_count, String page_index, String file_name, String dept_id,
			String entity_barcode) throws Exception;

	/**
	 * 根据密级、用途、项目得到对应的条码规则
	 * 
	 * 2014-5-21 上午1:46:00
	 * 
	 * @author lixiang
	 * @param seclv_code
	 * @param usage_code
	 * @param project_code
	 * @return
	 */
	SysBarcode getSysBarcode(Integer seclv_code, String usage_code, String project_code);

	List<CDStatic> getCDStaticList(Map<String, Object> map);

	List<SysUsage> getAllSysUsageList();

	/**
	 * 根据用途名得到用途
	 * 
	 * @param usage_name
	 * @return
	 */
	List<SysUsage> getUsageByUsageName(String usage_name);

	/**
	 * 得到默认条码规则
	 * 
	 * @return
	 */
	SysBarcode getDefaultSysBarcode();

	/**
	 * 判断模块的交接确认是否开启
	 * 
	 * @param module
	 * @return
	 */
	boolean isConfirmOpen(String module);

	String changeBarcode(String string) throws Exception;

	/**
	 * 启用已停用的控制台
	 * 
	 * @param console_code
	 */
	void recoverConsoleByCode(String console_code);

	/**
	 * 显示所有控制台列表
	 * 
	 * @param map
	 * @return
	 */
	List<SysConsole> getAllSysConsoleList(Map<String, Object> map);

	/**
	 * 启用已停用的用途
	 * 
	 * @param usage_code
	 */
	void recoverUsageByCode(String usage_code);

	/**
	 * 根据查询条件显示用途列表
	 * 
	 * @param map
	 * @return
	 */
	List<SysUsage> getSysUsageList(Map<String, Object> map);

	/**
	 * 强制关闭错误流程
	 * 
	 * @param job_code
	 */
	void closeWrongJob(String job_code);

	/**
	 * 判断是否自审批
	 * 
	 * @return
	 */
	boolean isSelfApprove();

	/**
	 * 根据用户和角色查询部门配置项
	 * 
	 * @param user_iidd
	 * @param role_id
	 * @return
	 */
	DeptAdminConfig getDeptAdminConfig(String user_iidd, String role_id);

	/**
	 * 查询用户拥有的角色
	 * 
	 * @param map
	 * @return
	 */
	List<NewSecRole> getRoleListByUser(Map<String, String> map);

	/**
	 * 配置用户、角色、部门的关联
	 * 
	 * @param adminConfig
	 */
	void configDeptAdmin(DeptAdminConfig adminConfig);

	/**
	 * 查询用户在当前页面权限内所管理的部门列表
	 * 
	 * @param user_iidd
	 * @param web_url
	 * @return
	 */
	List<SecDept> getDeptAdminList(String user_iidd, String web_url);

	/**
	 * 查询用户在当前页面权限内所管理的部门ID列表
	 * 
	 * @param user_iidd
	 * @param web_url
	 * @return
	 */
	List<String> getAdminDeptIdList(String user_iidd, String web_url);

	/**
	 * 按密级纸张统计
	 * 
	 * @param map
	 * @return
	 */
	List<PaperStatic> getPaperStaticListBySeclv(Map<String, Object> map);

	/**
	 * 按颜色纸张统计
	 * 
	 * @param map
	 * @return
	 */
	List<PaperStatic> getPaperStaticListByColor(Map<String, Object> map);

	/**
	 * 按单双面纸张统计
	 * 
	 * @param map
	 * @return
	 */
	List<PaperStatic> getPaperStaticListByDouble(Map<String, Object> map);

	/**
	 * 按大小纸张统计
	 * 
	 * @param map
	 * @return
	 */
	List<PaperStatic> getPaperStaticListBySize(Map<String, Object> map);

	/**
	 * 纸张统计
	 * 
	 * @param map
	 * @return
	 */
	List<PaperStatic> getPaperStaticListByAll(Map<String, Object> map);

	/**
	 * 获得一级子部门dept_id
	 * 
	 * @param dept_id
	 * @return
	 */
	List<String> getFirstChildList(String dept_id);

	/**
	 * 获得当前所有纸张类型
	 * 
	 * @return
	 */
	List<String> getPageSizeList();

	/**
	 * 获得关键字列表
	 * 
	 * @param map
	 * @return
	 */
	List<SysKeyword> getSysKeywordList(Map<String, Object> map);

	/**
	 * 添加关键字
	 * 
	 * @param keyword
	 */
	void addSysKeyword(SysKeyword keyword);

	/**
	 * 关键字查重
	 * 
	 * @param keyword_content
	 * @return
	 */
	boolean isKeywordExist(String keyword_content);

	/**
	 * 通过关键字号查询关键字
	 * 
	 * @param keyword_code
	 * @return
	 */
	SysKeyword getKeywordByCode(String keyword_code);

	/**
	 * 修改关键字
	 * 
	 * @param keyword
	 */
	void updateKeyword(SysKeyword keyword);

	/**
	 * 通过关键字内容查找序号
	 * 
	 * @param keyword_content
	 * @return
	 */
	String getKeywordCodeByContent(String keyword_content);

	/**
	 * 删除关键字
	 * 
	 * @param keyword_code
	 */
	void delKeywordByCode(String keyword_code);

	/**
	 * 是否开启关键字检测
	 * 
	 * @return
	 */
	boolean isKeywordEnable();

	/**
	 * 根据控制台代号查询挂载打印机个数 gaoxm
	 * 
	 * @param console_code
	 * @return
	 */
	int getPrinterNumByConsole(String console_code);

	/**
	 * 库中关键字总个数
	 * 
	 * @return
	 */
	Integer getKeywordCount();

	/**
	 * 更新纸质载体表的回收提醒时间
	 * 
	 * @param keyword
	 */
	void updatePaperExpireTime(Map<String, Object> map);

	/**
	 * 更新光盘载体表的回收提醒时间
	 * 
	 * @param keyword
	 */
	void updateCDExpireTime(Map<String, Object> map);

	/**
	 * 根据打印机编号查询独立模式用户数量
	 * 
	 * @param printer_code
	 * @return
	 */
	int getUserNumByPrinter(String printer_code);

	/**
	 * 获得适用于该模块的用途列表
	 * 
	 * @param module_code
	 * @return
	 */
	List<SysUsage> getSysUsageListByModule(String module_code);

	/**
	 * 根据打印机编号、用户ID查询是否为当前打印机配置过当前用户
	 * 
	 * @param map
	 * @return
	 */
	int getNumByUserPrinter(Map<String, Object> map);

	/**
	 * 通过三部的用户编号获得user_id
	 * 
	 * @param user_code
	 * @return
	 */
	String getUserIDByExtCode(String user_code);

	/**
	 * 启动送销流程
	 * 
	 * @param user_iidd
	 * @param dept_id
	 * @param seclv_code
	 * @param jobType
	 * @param next_approver
	 * @param _chk
	 * @throws Exception
	 */
	void handlePaperSendDestroy(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, List<String> barcodeList) throws Exception;

	/**
	 * 启动送销流程
	 * 
	 * @param user_iidd
	 * @param dept_id
	 * @param seclv_code
	 * @param jobType
	 * @param next_approver
	 * @param _chk
	 * @throws Exception
	 */
	void handleCDSendDestroy(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, List<String> barcodeList) throws Exception;

	/**
	 * 已有一维码时生成中电二维码
	 * 
	 * @param 成员单位
	 *            parent_dept_name
	 * @param 载体条码
	 *            （一维） entity_barcode
	 * @param 下属单位名称
	 *            （当前部门名称） curr_dept_name
	 * @param 登记人
	 *            duty_name
	 * @param 载体类型
	 *            type
	 * @param 文件名称
	 *            file_name
	 * @param 密级
	 *            seclv_name
	 * @param 来源
	 *            src_from
	 * @param 申请时间
	 *            apply_time
	 * @throws Exception
	 */
	String buildCETCTwoDimenosionalBarcode(String parent_dept_name, String entity_barcode, String curr_dept_name,
			String duty_name, String type, String file_name, String seclv_name, String src_from, String apply_time)
			throws Exception;

	int getCountbyApproveProxy(Map<String, Object> map);

	List<SysProxy> getApproveProxy(Map<String, Object> map);

	List<SysConfigItem> getSysConfigItems(String item_type);

	void addUserRoleRel(SecRoleUser secroleuser);

	int checkRole(Map<String, Object> map);

	/**
	 * 由于保密系统基于中电条码规则开发，用的中电生成条码规则，现为兼容保留中电条码规则，实际方法返回科工条码规则
	 */
	String createCETCEntityBarcode(String child_dept_id) throws Exception;

	/**
	 * 根据用户ID查询兼职审批部门 201511
	 * 
	 * @param user_iidd
	 * @return
	 */
	DeptAdminConfig getPartTimeDeptListByUserId(String user_iidd);

	/**
	 * 配置兼职审批部门201511
	 * 
	 * @param adminConfig
	 */
	void configPartTimeDept(DeptAdminConfig adminConfig);

	/**
	 * 优化Joblist查询
	 * 
	 * 
	 */
	int getJobSize(Map<String, Object> map);

	String getSeclvNameByCode(int seclv_code);

	/**
	 * 获取二维码
	 * 
	 * 
	 */
	String getBarcodeCompare(String barcode);

	/**
	 * 根据中电新条码规则生成条码201509 。dept_id为存在多个单位时用（如声光电），其他情况为空
	 * 
	 * @param dept_id
	 * @param copies
	 * @param transferUnitcode
	 * @return
	 * @throws Exception
	 */
	String createNewCETCBarcode(String dept_id, Integer copies) throws Exception;

	/**
	 * 获取自增的barcode
	 * 
	 */
	String getCreateBarcode();

	/**
	 * 根据传入的载体类型，生成载体条码号 打印文件:PRINTPAPER 涉密图纸:PRINTGRAPH 复印文件:COPY 外来文件:ENTERPAPER 保密手册:SECBOOK 刻录光盘:BURN 外来光盘:ENTERCD
	 * 涉密移动载体:DEVICE 涉密存储介质:STORAGE 涉密活动中使用的涉密载体:ACTIVITY (中物5所使用)
	 * 
	 * @author gaoyiming
	 * @param entityType
	 * @return
	 * @throws Exception
	 */
	String createEntityBarcodeCAEP(String entityType, Integer seclv_code, String dept_id) throws Exception;

	/**
	 * 修改流程表中文件阅读状态
	 * 
	 * @param map
	 */
	void updateJobProcessFileRead(Map<String, Object> map);

	int getHandledJobByUserIdSize(String user_iidd, String type, String user_name, Integer seclv_code,
			String jobType_code);

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
	List<ProcessJob> getHandledJobByUserId(String user_iidd, String type, String user_name, Integer seclv_code,
			String jobType_code, RowBounds rbs);

	String getPrintProxyUserIdByJobcode(String job_code);

	String getBurnProxyUserIdByJobcode(String job_code);

	/**
	 * 提交纸质载体处理申请(仅用于替换页监销)
	 * 
	 * @param user_iidd
	 * @param dept_id
	 * @param seclv_code
	 * @param jobType
	 * @param next_approver
	 * @param barcodes
	 * @param output_user_name
	 * @param output_dept_name
	 * @param usage_code
	 * @throws Exception
	 */
	void handleReplacePaperJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, String barcodes, String output_dept_name, String output_user_name, String usage_code,
			String supervise_user_iidd) throws Exception;

	/**
	 * 获取系统内配置了监销人角色的用户列表
	 */
	List<SecUser> getSuperviseUserList();

	/**
	 * 根据部门ID及其父部门ID获取外发承办人(部门文件管理员角色)列表
	 * 
	 * @param map
	 */
	List<SecUser> getOutputUndertakersByDeptId(Map<String, Object> map);

	/**
	 * 提交纸质载体外发处理申请
	 * 
	 * @param user_iidd
	 * @param dept_id
	 * @param seclv_code
	 * @param jobType
	 * @param next_approver
	 * @param barcodes
	 * @param output_user_name
	 * @param output_dept_name
	 * @param reason
	 * @param send_way
	 * @param carryout_user_iidd
	 * @param carryout_dept_id
	 * @param output_undertaker
	 * @throws Exception
	 */
	void handleSendPaperJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, String barcodes, String output_dept_name, String output_user_name, String reason,
			String send_way, String carryout_user_iidds, String carryout_user_names, String output_undertaker)
			throws Exception;

	/**
	 * 提交光盘载体外发处理申请
	 * 
	 * @param user_iidd
	 * @param dept_id
	 * @param seclv_code
	 * @param jobType
	 * @param next_approver
	 * @param barcodes
	 * @param output_user_name
	 * @param output_dept_name
	 * @param reason
	 * @param send_way
	 * @param carryout_user_iidd
	 * @param carryout_dept_id
	 * @param output_undertaker
	 * @throws Exception
	 */
	void handleSendCDJob(String user_iidd, String dept_id, Integer seclv_code, JobTypeEnum jobType,
			String next_approver, String barcodes, String output_dept_name, String output_user_name, String reason,
			String send_way, String carryout_user_iidd, String carryout_dept_id, String output_undertaker)
			throws Exception;
	
	/**
	 * 
	 * @param user_iidd
	 * @param jobType
	 * @param user_name
	 * @param seclv_code
	 * @param file_scope
	 * @param seclv_accord
	 * @param secret_time
	 * @throws Exception
	 */
	List<ProcessJob> getApprovedJobByUserIdPrint(String user_iidd,
			JobTypeEnum jobType, String user_name, Integer seclv_code,
			String file_scope, String seclv_accord, String secret_time)
			throws Exception;

}
