package hdsec.web.project.basic.mapper;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.basic.model.CDStatic;
import hdsec.web.project.basic.model.ClientMsg;
import hdsec.web.project.basic.model.ConfirmRecord;
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
import hdsec.web.project.user.model.SecRoleUser;
import hdsec.web.project.user.model.SecUser;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface BasicMapper {
	/**
	 * 用途管理
	 * 
	 * @param map
	 * @return
	 */
	List<SysUsage> getSysUsageList(Map<String, Object> map);

	List<SysUsage> getSpecialPaperTypeList(Map<String, Object> map);

	void addSysUsage(SysUsage usage);

	void addSpecialPaperType(SysUsage usage);

	void setUsageSealedByCode(String usage_code);

	SysUsage getUsageByCode(String usage_code);

	void updateUsage(SysUsage usage);

	/**
	 * 项目管理
	 * 
	 * @param map
	 * @return
	 */
	List<SysProject> getSysProjectList(Map<String, Object> map);

	void addSysProject(SysProject project);

	void setProjectSealedByCode(String project_code);

	SysProject getProjectByCode(String project_code);

	void updateProject(SysProject project);

	/**
	 * 一体机管理
	 * 
	 * @param map
	 * @return
	 */
	List<SysMfp> getSysMfpList(Map<String, Object> map);

	void addSysMfp(SysMfp mfp);

	void setMfpSealedByCode(String mfp_code);

	SysMfp getMfpByCode(String mfp_code);

	void updateMfp(SysMfp mfp);

	/**
	 * 刻录机管理
	 * 
	 * @param map
	 * @return
	 */
	List<SysBurner> getSysBurnerList(Map<String, Object> map);

	void addSysBurner(SysBurner burner);

	void setBurnerSealedByCode(String burner_code);

	SysBurner getBurnerByCode(String burner_code);

	void updateBurner(SysBurner burner);

	/**
	 * 打印机管理
	 * 
	 * @param map
	 * @return
	 */
	List<SysPrinter> getSysPrinterList(Map<String, Object> map);

	void addSysPrinter(SysPrinter printer);

	void setPrinterSealedByCode(String printer_code);

	SysPrinter getPrinterByCode(String printer_code);

	void updatePrinter(SysPrinter printer);

	void updatePrinterGroup(SysPrinterGroup printerGroup);

	void addPrinterGroup(SysPrinterGroup printerGroup);

	/**
	 * 控制台管理
	 * 
	 * @param map
	 * @return
	 */
	List<SysConsole> getAllSysConsoleList(Map<String, Object> map);

	SysConsole getConsoleByCode(String console_code);

	void updateConsole(SysConsole console);

	void setConsoleSealedByCode(String console_code);

	void addConsole(SysConsole console);

	/**
	 * 申请作业管理
	 * 
	 * @param map
	 * @return
	 */
	List<BaseEvent> getEventListByEventCodes(Map<String, Object> map);

	List<ProcessJob> getJobList(Map<String, Object> map, RowBounds rbs);

	ProcessJob getProcessJobByCode(String job_code);

	void addProcessJob(ProcessJob job);

	void updateEventStatus(Map<String, Object> map);

	void delEventJobRelByJobCode(Map<String, Object> map);

	void delJob(String job_code);

	String getJobTypeCodeByJobCode(String job_code);

	List<BaseEvent> getEventListByJobCode(Map<String, Object> map);

	List<ProcessJob> getProcessJobListByInstanceId(List<String> instanceIdList);

	void claimJobTask(ProcessJob job);

	void approveJobTask(ProcessJob job);

	List<ProcessJob> getApprovedJobByInstanceIds(Map<String, Object> map);

	void closeJobTask(ProcessJob job);

	List<SysPrinterGroup> getPrinterGroupList(Map<String, Object> map);

	void delPrinterGroupByPrinterCode(String printer_code);

	SysSeclevel getSysSecLevelByCode(Integer seclv_code);

	void delSysSeclevelByCode(Integer seclv_code);

	void addSysSeclevel(SysSeclevel seclv);

	List<EntityPaper> getPaperListByBarcodes(Map<String, Object> map);

	void updateEntityStatus(Map<String, Object> map);

	List<ProcessJob> getHandleJobListByEntityInstanceId(Map<String, Object> map);

	int getHandleJobListByEntityInstanceIdSize(Map<String, Object> map);

	List<ProcessJob> getHandleJobListByEntityInstanceId(Map<String, Object> map, RowBounds rbs);

	List<EntityPaper> getEntityListByJobCode(Map<String, Object> map);

	List<SysProxy> getUserProxys(String user_iidd);

	void addUserProxy(Map<String, Object> map);

	void deleteUserProxy(Map<String, Object> map);

	List<EntityCD> getCDListByBarcodes(Map<String, Object> map);

	void updateEntityCDStatus(Map<String, Object> map);

	void updateEntityDeviceStatus(Map<String, Object> map);

	/**
	 * 智能回收柜管理
	 * 
	 * @param map
	 * @return
	 */
	List<SysRecycleBox> getSysRecycleBoxList(Map<String, Object> map);

	void addSysRecycleBox(SysRecycleBox recycleBox);

	void setRecycleBoxSealedByCode(String box_code);

	SysRecycleBox getRecycleBoxByCode(String box_code);

	void updateRecycleBox(SysRecycleBox recycleBox);

	List<SysPrinterUser> getPrintUserList(Map<String, Object> map);

	void addPrinterUser(Map<String, Object> map);

	void delPrinterUser(Map<String, Object> map);

	/**
	 * 智能交换柜管理
	 */

	List<SysExchangeBox> getSysExchangeBoxList(Map<String, Object> map);

	void addSysExchangeBox(SysExchangeBox exchangeBox);

	void setExchangeBoxSealedByCode(String box_code);

	SysExchangeBox getExchangeBoxByCode(String box_code);

	void updateExchangeBox(SysExchangeBox exchangeBox);

	/**
	 * （打印刻录复印录入）参数设置
	 */
	SysConfigItem getSysConfigItemValue(String item_key);

	void updateSysConfigItem(SysConfigItem sysConfigItem);

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

	void saveConfirmRecord(ConfirmRecord record);

	List<SubSecDeptAdmin> getAdminGroupList(String user_iidd);

	List<SysBarcode> getAllSysBarcodes();

	SysBarcode getOneBarcodeByCode(String barcode_code);

	void saveOneBarcode(SysBarcode barcode);

	List<String> getSeclvNameByCodes(Map<String, Object> map);

	List<String> getConsoleNameByCodes(Map<String, Object> map);

	List<String> getProjectNameByCodes(Map<String, Object> map);

	List<String> getUsageNameByCodes(Map<String, Object> map);

	void delBarcodeByCode(String barcode_code);

	void updateBarcode(SysBarcode barcode);

	SysBarcode getBarcodeByCode(String barcode_code);

	String getCreateBarcode(Map<String, Object> map);

	List<PaperStatic> getPaperStaticList(Map<String, Object> map);

	List<String> getFirstChildList(String dept_id);

	List<PaperStatic> getAllChildPaperStaticList(Map<String, Object> map);

	void updateClientMsg(Map<String, Object> map);

	void addClientMsg(ClientMsg clientMsg);

	void saveFileInfo(FileInfo fileInfo);

	FileInfo getOneFileInfoByName(String fileName);

	void updateFileInfo(FileInfo fileInfo);

	int getUsageCount(Map<String, String> map);

	int checkProject(Map<String, Object> map);

	int checkConsole(Map<String, Object> map);

	List<FileInfo> getFileInfosByType(String type);

	void updateConsoleVersion(Map<String, Object> map);

	void setClientMsgRead(Map<String, Object> map);

	List<SysBarcode> getSysBarcodeList();

	SysBarcode getDefaultSysBarcode();

	List<CDStatic> getCDStaticList(Map<String, Object> map);

	List<CDStatic> getAllChildCDStaticList(Map<String, Object> map);

	void resetEntityPaperStatus(Map<String, Object> map);

	void resetEntityCDStatus(Map<String, Object> map);

	void resetEntityDeviceStatus(Map<String, Object> map);

	List<SysUsage> getUsageListByUsageName(String usage_name);

	Integer getConfirmStartUse(String item_key);

	void recoverConsoleByCode(String console_code);

	void recoverUsageByCode(String usage_code);

	Integer getSelfApproveStartUse();

	List<NewSecRole> getRoleListByUser(Map<String, String> map);

	void delSecDeptAdminByUserRole(Map<String, Object> map);

	void addSecDeptAdmin(Map<String, Object> map);

	List<String> getDeptIdListByUserRole(Map<String, Object> map);

	String getOperCodeByURL(String web_url);

	List<String> getRoleIdByOperCode(String oper_code);

	List<PaperStatic> getPaperStaticListBySeclv(Map<String, Object> map);

	List<PaperStatic> getAllChildPaperStaticListBySeclv(Map<String, Object> map);

	List<PaperStatic> getPaperStaticListByColor(Map<String, Object> map);

	List<PaperStatic> getAllChildPaperStaticListByColor(Map<String, Object> map);

	List<PaperStatic> getPaperStaticListByDouble(Map<String, Object> map);

	List<PaperStatic> getAllChildPaperStaticListByDouble(Map<String, Object> map);

	List<String> getPageSizeList();

	List<PaperStatic> getPaperStaticListBySize(Map<String, Object> map);

	List<PaperStatic> getAllChildPaperStaticListBySize(Map<String, Object> map);

	List<PaperStatic> getPaperStaticListByAll(Map<String, Object> map);

	List<PaperStatic> getAllChildPaperStaticListByAll(Map<String, Object> map);

	void setPaperJobCodeNull(Map<String, Object> resetmap);

	void setCDJobCodeNull(Map<String, Object> resetmap);

	List<SysKeyword> getSysKeywordList(Map<String, Object> map);

	void addSysKeyword(SysKeyword keyword);

	int getKeywordCount(String keyword_content);

	SysKeyword getKeywordByCode(String keyword_code);

	void updateKeyword(SysKeyword keyword);

	String getKeywordCodeByContent(String keyword_content);

	void delKeywordByCode(String keyword_code);

	int getPrinterNumByConsole(String console_code);

	Integer getAllKeywordCount();

	void updatePaperExpireTime(Map<String, Object> map);

	public void updateCDExpireTime(Map<String, Object> map);

	int getUserNumByPrinter(String printer_code);

	int getNumByUserPrinter(Map<String, Object> map);

	String getUserIDByExtCode(String user_code);

	void updateDefaultPassword(SysConfigItem sysConfigItem);

	int getAllConsoleCount();

	int getCountbyApproveProxy(Map<String, Object> map);

	List<SysProxy> getApproveProxy(Map<String, Object> map);

	List<SysConfigItem> getSysConfigItems(String item_type);

	void addUserRoleRel(SecRoleUser secroleuser);

	int checkRole(Map<String, Object> map);

	List<String> getPartTimeDeptListByUserId(String user_iidd);

	void delPartTimeDeptByUserId(String user_iidd);

	void addPartTimeDept(Map<String, Object> map);

	int getJobSize(Map<String, Object> map);

	String getSeclvNameByCode(int seclv_code);

	// 获取二维码
	String getBarcodeCompare(String barcode);

	String getDeptExtCodeByDeptId(String dept_id);

	String getCreateBarcodeCAEP(Map<String, Object> map);

	void updateJobProcessFileRead(Map<String, Object> map);

	String getPrintProxyUserIdByJobcode(String job_code);

	String getBurnProxyUserIdByJobcode(String job_code);

	void updateReplacePageEntityStatus(Map<String, Object> map);

	List<SecUser> getSuperviseUserList();

	List<SecUser> getOutputUndertakersByDeptId(Map<String, Object> map);
}
