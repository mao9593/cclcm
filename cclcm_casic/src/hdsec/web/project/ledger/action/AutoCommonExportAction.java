package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.activiti.service.ActivitiService;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.borrow.service.BorrowService;
import hdsec.web.project.burn.service.BurnService;
import hdsec.web.project.client.model.SysCVS;
import hdsec.web.project.client.service.ClientService;
import hdsec.web.project.common.BaseEvent;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.copy.service.CopyService;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.enter.service.EnterService;
import hdsec.web.project.input.model.InputEvent;
import hdsec.web.project.input.service.InputService;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.model.ExportCDEnum;
import hdsec.web.project.ledger.model.ExportPaperEnum;
import hdsec.web.project.ledger.service.LedgerService;
import hdsec.web.project.log.action.LogExportXlsAction;
import hdsec.web.project.print.service.PrintService;
import hdsec.web.project.storage.model.EntityStorage;
import hdsec.web.project.storage.service.StorageService;
import hdsec.web.project.user.service.UserService;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.StringUtils;

/**
 * @author yy
 * 
 */
public class AutoCommonExportAction extends LogExportXlsAction {

	private static final long serialVersionUID = 1L;
	@Resource
	protected UserService userService;
	@Resource
	protected LedgerService ledgerService;
	@Resource
	protected BorrowService borrowService;
	@Resource
	protected StorageService storageService;
	@Resource
	protected ClientService clientService;
	@Resource
	protected CopyService copyService;
	@Resource
	protected PrintService printService;
	@Resource
	protected EnterService enterService;
	@Resource
	protected BasicService basicService;
	@Resource
	protected ActivitiService activitiService;
	@Resource
	protected BurnService burnService;
	@Resource
	protected InputService inputService;

	private HSSFWorkbook wb = new HSSFWorkbook();
	private final String ENTITY_PAPER = "paper";
	private final String PAPER_CYCLE = "paper_recycle";
	private final String ENTITY_CD = "cd";
	private final String ENTITY_DEVICE = "device";
	private final String CD_BORROW = "cdBorrow";
	private final String Paper_BORROW = "paperBorrow";
	private final String STORAGE = "storage";
	private final String CVS = "cvs";
	private String job_code = "";// 制作审批流程作业编号
	private ProcessJob job = null;// 制作审批流程作业
	private String job_code1 = "";// 闭环审批信息流程作业编号
	private ProcessJob job1 = null;// 闭环审批信息流程作业
	private List<ProcessRecord> recordList = null;// 制作审批流程作业结果列表
	private List<ProcessRecord> recordList1 = null;// 闭环审批信息流程作业结果列表
	private final String SEC_ENTITY_PAPER = "secpaper";
	private final String EFILEINPUT = "efileinput";
	private final String SEC_ENTITY_CD = "seccd";

	@Override
	protected void fillData(HSSFSheet arg0, int arg1) {

	}

	public ProcessJob getJob() {
		return job;
	}

	public void setJob(ProcessJob job) {
		this.job = job;
	}

	public ProcessJob getJob1() {
		return job1;
	}

	public void setJob1(ProcessJob job1) {
		this.job1 = job1;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<ProcessRecord> recordList) {
		this.recordList = recordList;
	}

	public List<ProcessRecord> getRecordList1() {
		return recordList1;
	}

	public void setRecordList1(List<ProcessRecord> recordList1) {
		this.recordList1 = recordList1;
	}

	List<?> ledgers;

	@Override
	public String executeFunction() throws Exception {
		return null;
	}

	public void exportFile(OutputStream os, Map<String, Object> map, String sheetName, String[] titles, String type,
			String cols) throws Exception {
		BufferedOutputStream bos = new BufferedOutputStream(os);
		cellstyle = wb.createCellStyle();
		cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);
		int total_size = 0;
		if (ENTITY_PAPER.equals(type)) {
			total_size = ledgerService.getAllPaperLedgerSize(map);
		} else if (ENTITY_CD.equals(type)) {
			total_size = ledgerService.getAllCDLedgerSize(map);
		} else if (ENTITY_DEVICE.equals(type)) {
			total_size = ledgerService.getAllDeviceLedgerSize(map);
		} else if (CD_BORROW.equals(type)) {
			total_size = borrowService.getPersonCDBorrowLedgerSize(map);
		} else if (Paper_BORROW.equals(type)) {
			total_size = borrowService.getPersonPaperBorrowLedgerSize(map);
		} else if (STORAGE.equals(type)) {
			if (null != storageService.getStorageList(map)) {
				total_size = storageService.getStorageList(map).size();
			}
		} else if (CVS.equals(type)) {
			if (null != clientService.getCVSListByCondition(map)) {
				total_size = clientService.getCVSListByCondition(map).size();
			}
		} else if (PAPER_CYCLE.equals(type)) {
			total_size = ledgerService.getAllPaperRecycleLedgerSize(map);
		} else if (SEC_ENTITY_PAPER.equals(type)) {
			total_size = ledgerService.getSecAllPaperLedgerSize(map);
		} else if (EFILEINPUT.equals(type)) {
			total_size = inputService.getEfileInputEventListSize(map);
		} else if (SEC_ENTITY_CD.equals(type)) {
			total_size = ledgerService.getSecAllCDLedgerSize(map);
		}

		int count = total_size / CCLCMConstants.SHEET_SIZE + 1;
		int begin = 0;
		for (int i = 0; i < count; i++) {
			begin = i * CCLCMConstants.SHEET_SIZE;
			RowBounds rbs = new RowBounds(begin, CCLCMConstants.SHEET_SIZE);
			// long start_time = new Date().getTime();
			if (ENTITY_PAPER.equals(type)) {
				ledgers = ledgerService.getAllPaperLedgerList(map, rbs);
			} else if (ENTITY_CD.equals(type)) {
				ledgers = ledgerService.getAllCDLedgerList(map, rbs);
			} else if (ENTITY_DEVICE.equals(type)) {
				ledgers = ledgerService.getAllDeviceLedgerList(map, rbs);
			} else if (CD_BORROW.equals(type)) {
				ledgers = borrowService.getPersonCDBorrowLedgerList(map, rbs);
			} else if (Paper_BORROW.equals(type)) {
				ledgers = borrowService.getPersonPaperBorrowLedgerList(map, rbs);
			} else if (STORAGE.equals(type)) {
				ledgers = storageService.getStorageList(map);
			} else if (CVS.equals(type)) {
				ledgers = clientService.getCVSListByCondition(map);
			} else if (PAPER_CYCLE.equals(type)) {
				ledgers = ledgerService.getAllPaperRecycleLedgerList(map);
				type = "paper";
			} else if (SEC_ENTITY_PAPER.equals(type)) {
				ledgers = ledgerService.getSecAllPaperLedgerList(map, rbs);
			} else if (EFILEINPUT.equals(type)) {
				ledgers = inputService.getEfileInputEventList(map);
			} else if (SEC_ENTITY_CD.equals(type)) {
				ledgers = ledgerService.getSecAllCDLedgerList(map, rbs);
			}

			// long end_time = new Date().getTime();
			// System.out.println("total time : " + (end_time - start_time) + "  total size:" + ledgers.size());
			if (count - 1 != i) {
				insertIntoOneSheet(i, i * CCLCMConstants.SHEET_SIZE - begin, (i + 1) * CCLCMConstants.SHEET_SIZE - 1
						- begin, sheetName, titles, type, cols);
			} else {
				insertIntoOneSheet(i, i * CCLCMConstants.SHEET_SIZE - begin, total_size - 1 - begin, sheetName, titles,
						type, cols);
			}
		}

		wb.write(bos);
		if (null != bos) {
			bos.flush();
			bos.close();
		}
		if (null != os) {
			os.close();
		}
	}

	public void insertIntoOneSheet(int pageIndex, int from, int to, String sheetName, String[] titles, String type,
			String cols) throws IOException {
		HSSFSheet sh = wb.createSheet(sheetName + pageIndex);
		addTitile(sh, titles);
		BaseEvent event = null;
		String usage_name = "";
		String approveInfo = "";// 制作审批信息
		String cycleApproveInfo = "";// 闭环审批信息

		int i = from;
		try {
			for (i = from; i <= to; i++) {
				if (ENTITY_PAPER.equals(type) || SEC_ENTITY_PAPER.equals(type)) {
					// 获取cols的列信息
					String[] tempcols = cols.split(",");
					for (String string : tempcols) {
						for (ExportPaperEnum item : ExportPaperEnum.values()) {
							if (item.getKey().intValue() == Integer.parseInt(string.trim())) {
								item.enableUsed();
								break;
							}
						}
					}
					// end
					EntityPaper entity = (EntityPaper) ledgers.get(i);
					usage_name = "";// 用途
					approveInfo = "";// 制作审批信息
					cycleApproveInfo = "";// 闭环审批信息
					if (ExportPaperEnum.TITLE9.isEntityStateUsed() && entity != null
							&& StringUtils.hasLength(entity.getEvent_code())) {
						approveInfo = getApproveInfo(entity.getCreate_type(), entity.getEvent_code());
					}
					if (ExportPaperEnum.TITLE34.isEntityStateUsed() && entity != null
							&& StringUtils.hasLength(entity.getEvent_code())) {
						usage_name = getUsageName(entity.getCreate_type(), entity.getEvent_code());
					}
					if (ExportPaperEnum.TITLE27.isEntityStateUsed() && StringUtils.hasLength(entity.getJob_code())) {
						cycleApproveInfo = getCycleApproveInfo(entity.getJob_code());
					}

					insertPaperRow(sh, entity, usage_name, approveInfo, cycleApproveInfo, i - from + 1, cols);
				} else if (ENTITY_CD.equals(type) || SEC_ENTITY_CD.equals(type)) {
					// 获取cols的列信息
					String[] tempcols = cols.split(",");
					for (String string : tempcols) {
						for (ExportCDEnum item : ExportCDEnum.values()) {
							if (item.getKey().intValue() == Integer.parseInt(string.trim())) {
								item.enableUsed();
								break;
							}
						}
					}
					// end
					EntityCD entity = (EntityCD) ledgers.get(i);
					usage_name = "";
					approveInfo = "";
					cycleApproveInfo = "";
					if (ExportCDEnum.TITLE9.isEntityStateUsed() && entity != null
							&& StringUtils.hasLength(entity.getEvent_code())) {
						approveInfo = getApproveInfo(entity.getCreate_type(), entity.getEvent_code());
					}
					if (ExportCDEnum.TITLE36.isEntityStateUsed() && entity != null
							&& StringUtils.hasLength(entity.getEvent_code())) {
						usage_name = getUsageName(entity.getCreate_type(), entity.getEvent_code());
					}
					if (ExportCDEnum.TITLE27.isEntityStateUsed() && StringUtils.hasLength(entity.getJob_code())) {
						cycleApproveInfo = getCycleApproveInfo(entity.getJob_code());
					}
					insertCDRow(sh, entity, usage_name, approveInfo, cycleApproveInfo, i - from + 1, cols);
				} else if (ENTITY_DEVICE.equals(type)) {
					EntityDevice entity = (EntityDevice) ledgers.get(i);
					insertDeviceRow(sh, entity, i - from + 1);
				} else if (CD_BORROW.equals(type)) {
					// 获取cols的列信息
					String[] tempcols = cols.split(",");
					for (String string : tempcols) {
						for (ExportCDEnum item : ExportCDEnum.values()) {
							if (item.getKey().intValue() == Integer.parseInt(string.trim())) {
								item.enableUsed();
								break;
							}
						}
					}
					// end
					EntityCD entity = (EntityCD) ledgers.get(i);
					usage_name = "";
					approveInfo = "";
					cycleApproveInfo = "";
					if (ExportCDEnum.TITLE9.isEntityStateUsed() && entity != null
							&& StringUtils.hasLength(entity.getEvent_code())) {
						approveInfo = getApproveInfo(entity.getCreate_type(), entity.getEvent_code());
					}
					if (ExportCDEnum.TITLE36.isEntityStateUsed() && entity != null
							&& StringUtils.hasLength(entity.getEvent_code())) {
						usage_name = getUsageName(entity.getCreate_type(), entity.getEvent_code());
					}
					if (ExportCDEnum.TITLE27.isEntityStateUsed() && StringUtils.hasLength(entity.getJob_code())) {
						cycleApproveInfo = getCycleApproveInfo(entity.getJob_code());
					}
					insertCDRow(sh, entity, usage_name, approveInfo, cycleApproveInfo, i - from + 1, cols);
				} else if (Paper_BORROW.equals(type)) {
					// 获取cols的列信息
					String[] tempcols = cols.split(",");
					for (String string : tempcols) {
						for (ExportCDEnum item : ExportCDEnum.values()) {
							if (item.getKey().intValue() == Integer.parseInt(string.trim())) {
								item.enableUsed();
								break;
							}
						}
					}
					// end
					EntityPaper entity = (EntityPaper) ledgers.get(i);
					usage_name = "";
					approveInfo = "";
					cycleApproveInfo = "";
					if (ExportPaperEnum.TITLE9.isEntityStateUsed() && entity != null
							&& StringUtils.hasLength(entity.getEvent_code())) {
						approveInfo = getApproveInfo(entity.getCreate_type(), entity.getEvent_code());
					}
					if (ExportPaperEnum.TITLE34.isEntityStateUsed() && entity != null
							&& StringUtils.hasLength(entity.getEvent_code())) {
						usage_name = getUsageName(entity.getCreate_type(), entity.getEvent_code());
					}
					if (ExportPaperEnum.TITLE27.isEntityStateUsed() && StringUtils.hasLength(entity.getJob_code())) {
						cycleApproveInfo = getCycleApproveInfo(entity.getJob_code());
					}
					insertPaperRow(sh, entity, usage_name, approveInfo, cycleApproveInfo, i - from + 1, cols);
				} else if (STORAGE.equals(type)) {
					EntityStorage entity = (EntityStorage) ledgers.get(i);
					insertStorageRow(sh, entity, i - from + 1);
				} else if (CVS.equals(type)) {
					SysCVS cvs = (SysCVS) ledgers.get(i);
					insertSysCVSRow(sh, cvs, i - from + 1);
				} else if (EFILEINPUT.equals(type)) {
					InputEvent efileinput = (InputEvent) ledgers.get(i);
					insertEfileInputRow(sh, efileinput, i - from + 1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void insertStorageRow(HSSFSheet sh, EntityStorage entity, int rownum) {
		int index = 0;
		HSSFRow row = sh.createRow(rownum);
		cteateCell(row, index++, rownum + "");
		cteateCell(row, index++, changeNullOrEmpty(entity.getStorage_barcode()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getStorage_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getInput_user_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getDept_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getStorage_series()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getInput_time_str()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getMed_type_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getSeclv_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getDuty_user_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getUse_user_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getStorage_status_name()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getStorage_model()));
		cteateCell(row, index++, changeNullOrEmpty(entity.getStorage_detail()));

	}

	private void insertDeviceRow(HSSFSheet sh, EntityDevice entity, int rownum) {
		int index = 0;
		HSSFRow row = sh.createRow(rownum);
		cteateCell(row, index++, rownum + "");
		cteateCell(row, index++, changeNullOrEmpty(entity.getDevice_barcode()));// 条码号
		cteateCell(row, index++, changeNullOrEmpty(entity.getDevice_name()));// 名称
		cteateCell(row, index++, changeNullOrEmpty(entity.getBorrow_user_name()));// 借用人
		cteateCell(row, index++, changeNullOrEmpty(entity.getBorrow_dept_name()));// 借用人部门
		cteateCell(row, index++, changeNullOrEmpty(entity.getEnter_time_str()));// 登记时间
		cteateCell(row, index++, changeNullOrEmpty(entity.getMed_type_name()));// 介质类型(1,U盘，2硬盘3笔记本
		cteateCell(row, index++, changeNullOrEmpty(entity.getSeclv_name()));// 密级
		cteateCell(row, index++, changeNullOrEmpty(entity.getDuty_user_name()));// 部门
		cteateCell(row, index++, changeNullOrEmpty(entity.getMed_content()));// 说明
		cteateCell(row, index++, changeNullOrEmpty(entity.getDevice_status_name()));// 状态
		cteateCell(row, index++, changeNullOrEmpty(entity.getDevice_model()));// 设备编码
		cteateCell(row, index++, changeNullOrEmpty(entity.getDevice_detail()));// 设备配置
		cteateCell(row, index++, changeNullOrEmpty(entity.getDept_name()));// 所属部门
		cteateCell(row, index++, changeNullOrEmpty(entity.getDuty_user_name()));// 责任人
		cteateCell(row, index++, changeNullOrEmpty(entity.getUser_name()));// 录入员
	}

	public void addTitile(HSSFSheet sh, String[] titles) {
		HSSFRow row = sh.createRow(0);
		int index = 0;
		for (String str : titles) {
			cteateCell(row, index++, str);
		}
	}

	private void insertCDRow(HSSFSheet sh, EntityCD cd, String usage_name, String approveInfo, String cycleApproveInfo,
			int rownum, String cols) {
		for (ExportCDEnum item : ExportCDEnum.values()) {
			item.disableUsed();
		}
		String[] tempcols = cols.split(",");
		for (String string : tempcols) {
			for (ExportCDEnum item : ExportCDEnum.values()) {
				if (item.getKey().intValue() == Integer.parseInt(string.trim())) {
					item.enableUsed();
					break;
				}
			}
		}
		int index = 0;
		HSSFRow row = sh.createRow(rownum);
		cteateCell(row, index++, rownum + "");// 序号
		if (ExportCDEnum.TITLE2.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getCd_barcode()));// 光盘编号
		}
		if (ExportCDEnum.TITLE3.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getCd_state_name()));// 当前状态
		}
		if (ExportCDEnum.TITLE4.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getDuty_user_name()));// 责任人
		}
		if (ExportCDEnum.TITLE5.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getDuty_dept_name()));// 责任人部门
		}
		if (ExportCDEnum.TITLE6.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getBurn_result_name()));// 刻录结果
		}
		if (ExportCDEnum.TITLE7.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getSeclv_name()));// 作业密级
		}
		if (ExportCDEnum.TITLE8.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getCreate_type_name()));// 制作方式
		}
		if (ExportCDEnum.TITLE9.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(approveInfo));// 制作审批信息
		}
		if (ExportCDEnum.TITLE10.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getBurn_time()));// 刻录时间
		}
		if (ExportCDEnum.TITLE11.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getBurn_machine()));// 刻录机器
		}
		if (ExportCDEnum.TITLE12.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getBurn_ipaddress()));// 刻录IP
		}
		if (ExportCDEnum.TITLE13.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getCd_type()));// 光盘类型
		}
		if (ExportCDEnum.TITLE14.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getProject_code()));// 所属项目
		}
		if (ExportCDEnum.TITLE15.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getCd_volume()));// 卷标名称
		}
		if (ExportCDEnum.TITLE16.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getEvent_code()));// 作业代号
		}
		if (ExportCDEnum.TITLE17.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getData_type_name()));// 数据类型
		}
		if (ExportCDEnum.TITLE18.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getFile_list()));// 文件列表
		}
		if (ExportCDEnum.TITLE19.isEntityStateUsed()) {
			cteateCell(row, index++, (cd.getFile_num() == null ? 0 : cd.getFile_num()) + "");// 文件数量
		}
		if (ExportCDEnum.TITLE20.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getConf_code()));// 保密编号
		}
		if (ExportCDEnum.TITLE21.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getUser_name()));// 申请人
		}
		if (ExportCDEnum.TITLE22.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getDept_name()));// 申请人部门
		}
		if (ExportCDEnum.TITLE23.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getBurn_type_name()));// 刻录类型
		}
		if (ExportCDEnum.TITLE24.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getIs_reburn_name()));// 是否补刻
		}
		if (ExportCDEnum.TITLE25.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getJob_code()));// 流转号
		}
		if (ExportCDEnum.TITLE26.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getRetrieve_user_name()));// 闭环处理人(回收、外发、归档)
		}
		if (ExportCDEnum.TITLE27.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cycleApproveInfo));// 闭环审批信息
		}
		if (ExportCDEnum.TITLE28.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getRetrieve_type_name()));// 回收方式
		}
		if (ExportCDEnum.TITLE29.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getRetrieve_box_code()));// 回收柜标识
		}
		if (ExportCDEnum.TITLE30.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getRetrieve_time()));// 回收时间
		}

		if (ExportCDEnum.TITLE32.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getDestroy_user_name()));// 销毁人
		}
		if (ExportCDEnum.TITLE33.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getDestroy_time()));// 销毁时间
		}
		if (ExportCDEnum.TITLE34.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getExpire_time()));// 到期时间
		}
		if (ExportCDEnum.TITLE35.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getExpire_status_name()));// 到期状态
		}
		if (ExportCDEnum.TITLE36.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(usage_name));// 用途
		}
		if (ExportCDEnum.TITLE37.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getUser_name()));// 制作人
		}
		if (ExportCDEnum.TITLE38.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getDept_name()));// 制作人部门
		}
		if (ExportCDEnum.TITLE39.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getOutput_dept_name()));// 外发接收部门
		}
		if (ExportCDEnum.TITLE40.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cd.getConfidential_num()));// 光盘机要号（录入）
		}
	}

	private void insertPaperRow(HSSFSheet sh, EntityPaper paper, String usage_name, String approveInfo,
			String cycleApproveInfo, int rownum, String cols) {
		for (ExportPaperEnum item : ExportPaperEnum.values()) {
			item.disableUsed();
		}
		String[] tempcols = cols.split(",");
		for (String string : tempcols) {
			for (ExportPaperEnum item : ExportPaperEnum.values()) {
				if (item.getKey().intValue() == Integer.parseInt(string.trim())) {
					item.enableUsed();
					break;
				}
			}
		}
		int index = 0;
		HSSFRow row = sh.createRow(rownum);
		cteateCell(row, index++, rownum + "");// 序号
		if (ExportPaperEnum.TITLE2.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getPaper_barcode()));// 载体条码
		}
		if (ExportPaperEnum.TITLE3.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getUser_name()));// 产生人姓名
		}
		if (ExportPaperEnum.TITLE4.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getDept_name()));// 产生人部门
		}
		if (ExportPaperEnum.TITLE5.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getDuty_user_name()));// 责任人姓名
		}
		if (ExportPaperEnum.TITLE6.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getDuty_dept_name()));// 责任人部门
		}
		if (ExportPaperEnum.TITLE7.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getSeclv_name()));// 密级
		}
		if (ExportPaperEnum.TITLE8.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getCreate_type_name()));// 制作方式
		}
		if (ExportPaperEnum.TITLE9.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(approveInfo));// 制作审批信息
		}
		if (ExportPaperEnum.TITLE10.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getPrint_time()));// 产生时间
		}
		if (ExportPaperEnum.TITLE11.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getIs_reprint_name()));// 是否补打
		}
		if (ExportPaperEnum.TITLE12.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getPrint_result_name()));// 打印结果
		}
		if (ExportPaperEnum.TITLE13.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getFile_title()));// 原文件名
		}
		if (ExportPaperEnum.TITLE14.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getProject_code()));// 所属项目代号
		}
		if (ExportPaperEnum.TITLE15.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getFileID()));// 文号
		}
		if (ExportPaperEnum.TITLE16.isEntityStateUsed()) {
			cteateCell(row, index++, (paper.getPage_count() == null ? 0 : paper.getPage_count()) + "");// 页数
		}
		if (ExportPaperEnum.TITLE17.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getPage_size()));// 纸张大小
		}
		if (ExportPaperEnum.TITLE18.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getColor_name()));// 横纵向
		}
		if (ExportPaperEnum.TITLE19.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getPrint_direct_name()));// 横纵向
		}
		if (ExportPaperEnum.TITLE20.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getPrint_double_name()));// 单双面
		}
		if (ExportPaperEnum.TITLE21.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getPaper_state_name()));// 介质状态
		}
		if (ExportPaperEnum.TITLE22.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getPrinter_name()));// 打印机名称
		}
		if (ExportPaperEnum.TITLE23.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getKeyword_content()));// 关键字
		}
		if (ExportPaperEnum.TITLE24.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getRetrieve_time()));// 回收时间
		}
		if (ExportPaperEnum.TITLE25.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getRetrieve_type_name()));// 回收方式
		}
		if (ExportPaperEnum.TITLE26.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getRetrieve_user_name()));// 闭环处理人(回收、外发、归档)
		}
		if (ExportPaperEnum.TITLE27.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(cycleApproveInfo));// 闭环审批信息
		}
		if (ExportPaperEnum.TITLE28.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getRetrieve_box_code()));// 回收柜标识
		}
		if (ExportPaperEnum.TITLE29.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getDestroy_time()));// 销毁时间
		}
		if (ExportPaperEnum.TITLE30.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getDestroy_user_name()));// 销毁人
		}
		if (ExportPaperEnum.TITLE31.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getJob_code()));// 流转号
		}
		if (ExportPaperEnum.TITLE32.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getExpire_time()));// 到期时间
		}
		if (ExportPaperEnum.TITLE33.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getExpire_status_name()));// 到期状态
		}
		if (ExportPaperEnum.TITLE34.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(usage_name));// 用途
		}
		if (ExportPaperEnum.TITLE35.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getOutput_dept_name()));// 外发接收部门
		}
		if (ExportPaperEnum.TITLE36.isEntityStateUsed()) {
			cteateCell(row, index++, changeNullOrEmpty(paper.getFail_remark()));// 备注
		}
	}

	private void insertSysCVSRow(HSSFSheet sh, SysCVS cvs, int rownum) {
		int index = 0;
		HSSFRow row = sh.createRow(rownum);
		cteateCell(row, index++, rownum + "");
		cteateCell(row, index++, changeNullOrEmpty(cvs.getUser_name()));
		cteateCell(row, index++, changeNullOrEmpty(cvs.getDept_name()));
		cteateCell(row, index++, changeNullOrEmpty(cvs.getComputer_name()));
		cteateCell(row, index++, changeNullOrEmpty(cvs.getIp_address()));
		cteateCell(row, index++, changeNullOrEmpty(cvs.getCurr_version()));
		cteateCell(row, index++, changeNullOrEmpty(cvs.getInstall_version()));
		cteateCell(row, index++, changeNullOrEmpty(cvs.getInstall_time_str()));
		cteateCell(row, index++, changeNullOrEmpty(cvs.getUpdate_time_str()));
		cteateCell(row, index++, changeNullOrEmpty(cvs.getOs_version()));
		cteateCell(row, index++, changeNullOrEmpty(cvs.getLast_time_str()));
		cteateCell(row, index++, changeNullOrEmpty(cvs.getUninstall_str()));
		cteateCell(row, index++, changeNullOrEmpty(cvs.getSet_version()));
	}

	private void insertEfileInputRow(HSSFSheet sh, InputEvent efileinput, int rownum) {
		int index = 0;
		HSSFRow row = sh.createRow(rownum);
		cteateCell(row, index++, rownum + "");
		cteateCell(row, index++, changeNullOrEmpty(efileinput.getUser_name())); // 责任人
		cteateCell(row, index++, changeNullOrEmpty(efileinput.getDept_name())); // 责任人部门
		cteateCell(row, index++, changeNullOrEmpty(efileinput.getFile_list())); // 文件列表
		cteateCell(row, index++, changeNullOrEmpty(efileinput.getSeclv_name())); // 作业密级
		cteateCell(row, index++, changeNullOrEmpty(efileinput.getApply_time_str())); // 导入时间
		cteateCell(row, index++, changeNullOrEmpty(efileinput.getUsage_code())); // 用途
		cteateCell(row, index++, changeNullOrEmpty(efileinput.getMed_type())); // 介质类型
		// cteateCell(row, index++, changeNullOrEmpty(efileinput.getFile_num()));
	}

	private String changeNullOrEmpty(String str) {
		if (null == str || str.length() == 0) {
			return " ";
		} else {
			return str;
		}
	}

	private String getApproveInfo(String create_type, String event_code) {
		String approveInfo = "";
		BaseEvent event = null;
		if (create_type.equals("COPY")) {
			event = copyService.getCopyEventByCopyCode(event_code);
		} else if (create_type.equals("OUTCOPY")) {
			event = copyService.getCopyEventByCopyCode(event_code);
		} else if (create_type.equals("LEADIN")) {
			event = enterService.getEnterEventByEnterCode(event_code);
		} else if (create_type.equals("PRINT")) {
			event = printService.getPrintEventByPrintCode(event_code);
		} else if (create_type.equals("BURN")) {
			event = burnService.getBurnEventByBurnCode(event_code);
		} else if (create_type.equals("LEADIN")) {
			event = enterService.getEnterEventByEnterCode(event_code);
		} else if (create_type.equals("BURN")) {
			event = burnService.getBurnEventByBurnCode(event_code);
		} else if (create_type.equals("LEADIN")) {
			event = enterService.getEnterEventByEnterCode(event_code);
		}
		if (event != null) {
			job_code = event.getJob_code();
			job = basicService.getProcessJobByCode(job_code);
			if (job != null) {
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
				for (ProcessRecord processRecord : recordList) {
					approveInfo += processRecord.getUser_name() + "," + processRecord.getDept_name() + ","
							+ sdf.format(processRecord.getOp_time()) + "," + processRecord.getOpinion() + ",\n";
				}
				approveInfo = approveInfo.substring(0, approveInfo.length() - 1);
			}
			// usage_name = event.getUsage_name();
		}

		return approveInfo;
	}

	private String getUsageName(String create_type, String event_code) {
		String usage_name = "";
		BaseEvent event = null;
		if (create_type.equals("COPY")) {
			event = copyService.getCopyEventByCopyCode(event_code);
		} else if (create_type.equals("OUTCOPY")) {
			event = copyService.getCopyEventByCopyCode(event_code);
		} else if (create_type.equals("LEADIN")) {
			event = enterService.getEnterEventByEnterCode(event_code);
		} else if (create_type.equals("PRINT")) {
			event = printService.getPrintEventByPrintCode(event_code);
		} else if (create_type.equals("BURN")) {
			event = burnService.getBurnEventByBurnCode(event_code);
		} else if (create_type.equals("LEADIN")) {
			event = enterService.getEnterEventByEnterCode(event_code);
		} else if (create_type.equals("BURN")) {
			event = burnService.getBurnEventByBurnCode(event_code);
		} else if (create_type.equals("LEADIN")) {
			event = enterService.getEnterEventByEnterCode(event_code);
		}
		if (event != null) {
			usage_name = event.getUsage_name();
		}
		return usage_name;
	}

	private String getCycleApproveInfo(String job_code) {
		String cycleApproveInfo = "";
		job1 = basicService.getProcessJobByCode(job_code);
		if (job1 != null) {
			ProcessRecord record1 = new ProcessRecord();
			record1.setJob_code(job_code);
			recordList1 = activitiService.getProcessRecordList(record1);
			for (ProcessRecord processRecord1 : recordList1) {
				cycleApproveInfo += processRecord1.getUser_name() + "," + processRecord1.getDept_name() + ","
						+ sdf.format(processRecord1.getOp_time()) + "," + processRecord1.getOpinion() + ",\n";
			}
			cycleApproveInfo = cycleApproveInfo.substring(0, cycleApproveInfo.length() - 1);
		}
		return cycleApproveInfo;
	}

}
