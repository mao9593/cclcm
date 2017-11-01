package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.basic.model.RejectRecord;
import hdsec.web.project.burn.model.BurnEvent;
import hdsec.web.project.burn.model.BurnFile;
import hdsec.web.project.common.CCLCMConstants;
import hdsec.web.project.common.PropertyUtil;
import hdsec.web.project.common.util.MD5;
import hdsec.web.project.copy.model.CopyEvent;
import hdsec.web.project.copy.service.CopyService;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.enter.model.EnterEvent;
import hdsec.web.project.enter.service.EnterService;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.print.model.PrintEvent;
import hdsec.web.project.print.service.PrintService;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

/**
 * 查看载体全生命周期详细信息
 * 
 * @author renmingfei
 * 
 */
public class ViewCycleDetailAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String type = "";
	private String barcode = "";
	private EntityPaper paper = null;
	private EntityCD cd = null;
	private EntityDevice device = null;
	private List<CycleItem> itemList = null;
	private PrintEvent event = null;
	private String ledger_type = "";
	private BurnEvent burn_event = null;
	private List<BurnFile> burnFileList = null;
	private boolean is_cd_audit = false;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private BurnEvent burnEvent = null;
	private CopyEvent copyEvent = null;
	private EnterEvent enterEvent = null;
	private List<ProcessRecord> recordList = null;
	private List<RejectRecord> rejectRecordList = null;
	private List<BurnEvent> burnEventList = null;
	@Resource
	protected PrintService printService;
	@Resource
	protected CopyService copyService;
	@Resource
	protected EnterService enterService;

	public void setType(String type) {
		this.type = type;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public List<CycleItem> getItemList() {
		return itemList;
	}

	public EntityPaper getPaper() {
		return paper;
	}

	public EntityCD getCd() {
		return cd;
	}

	public EntityDevice getDevice() {
		return device;
	}

	public String getLedger_type() {
		return ledger_type;
	}

	public void setLedger_type(String ledger_type) {
		this.ledger_type = ledger_type;
	}

	public List<BurnFile> getBurnFileList() {
		return burnFileList;
	}

	public boolean isIs_cd_audit() {
		return is_cd_audit;
	}

	public void setIs_cd_audit(boolean is_cd_audit) {
		this.is_cd_audit = is_cd_audit;
	}

	public ProcessJob getJob() {
		return job;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public List<RejectRecord> getRejectRecordList() {
		return rejectRecordList;
	}

	@Override
	public String executeFunction() throws Exception {
		if (type.equalsIgnoreCase("PAPER")) {
			paper = ledgerService.getPaperByBarcode(barcode);
			if (paper != null) {
				if (paper.getCreate_type().contains("COPY")) {
					copyEvent = copyService.getCopyEventByCopyCode(paper.getEvent_code());
					if (copyEvent != null) {
						job_code = copyEvent.getJob_code();
					}
				} else if (paper.getCreate_type().equals("LEADIN")) {
					enterEvent = enterService.getEnterEventByEnterCode(paper.getEvent_code());
					if (enterEvent != null) {
						job_code = enterEvent.getJob_code();
					}
				} else {
					event = printService.getPrintEventByPrintCode(paper.getEvent_code());
					if (event != null) {
						job_code = event.getJob_code();
					}
				}
				// 展示文件载体拒收记录
				rejectRecordList = ledgerService.getRejectRecordByBarcode(barcode);
			}
		} else if (type.equalsIgnoreCase("DISC")) {
			cd = ledgerService.getCDByBarcode(barcode);
			if (cd != null) {
				if (cd.getCreate_type().equals("LEADIN")) {
					enterEvent = enterService.getEnterEventByEnterCode(cd.getEvent_code());
					if (enterEvent != null) {
						job_code = enterEvent.getJob_code();
					}
				} else {
					burnEventList = ledgerService.getBurnEventByCdBarcode(barcode);
					burn_event = burnService.getBurnEventByBurnCode(cd.getEvent_code());
					// if (burn_event != null) {
					// job_code = burn_event.getJob_code();
					// }
				}
				is_cd_audit = ledgerService.checkShowBurnFile(cd.getSeclv_code().toString());
				// 展示光盘载体拒收记录
				rejectRecordList = ledgerService.getRejectRecordByBarcode(barcode);
			}
			if (burn_event != null) {
				cd.setConf_code(burn_event.getConf_code());
				String[] filelist = burn_event.getFile_list().split(CCLCMConstants.DEVIDE_SYMBOL);
				String[] fileseclevel = burn_event.getFile_seclevel().split(CCLCMConstants.DEVIDE_SYMBOL);
				if (filelist.length > 0 && StringUtils.hasLength(filelist[0])) {
					String storePath = PropertyUtil.getBurnFileStorePath();
					burnFileList = new ArrayList<BurnFile>();
					Integer seclv_code;
					String seclv_name = "";
					String file_path = "";
					for (int i = 0; i < filelist.length; i++) {
						seclv_code = Integer.parseInt(fileseclevel[i].trim());
						seclv_name = userService.getSecLevelByCode(seclv_code).getSeclv_name();
						file_path = storePath + "/" + cd.getEvent_code() + "/" + MD5.getStringMD5(filelist[i]);
						burnFileList.add(new BurnFile(filelist[i], seclv_code, seclv_name, file_path));
					}
				}
			}
		} else if (type.equalsIgnoreCase("DEVICE")) {
			device = ledgerService.getDeviceByBarcode(barcode);
		} else {
			throw new Exception("未知载体类型[" + type + "]");
		}
		itemList = ledgerService.getCycleItemListByBarcode(barcode);
		// 流程展示
		job = basicService.getProcessJobByCode(job_code);
		if (job != null) {
			ProcessRecord record = new ProcessRecord();
			record.setJob_code(job_code);
			recordList = activitiService.getProcessRecordList(record);
			process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
		}
		return type;
	}

	public PrintEvent getEvent() {
		return event;
	}

	public List<BurnEvent> getBurnEventList() {
		return burnEventList;
	}
}
