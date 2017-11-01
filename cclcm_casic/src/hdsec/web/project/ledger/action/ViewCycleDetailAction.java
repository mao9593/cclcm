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
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.enter.model.EnterEvent;
import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.model.EventModify;
import hdsec.web.project.print.model.OaPrintEvent;
import hdsec.web.project.print.model.PrintEvent;
import hdsec.web.project.user.model.SecUser;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private List<BurnFile> burnFileList1 = null;
	private boolean is_cd_audit = false;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private BurnEvent burnEvent = null;
	private CopyEvent copyEvent = null;
	private EnterEvent enterEvent = null;
	// private EventModify eventModify = null;
	private List<EventModify> eventModify = null;
	private List<ProcessRecord> recordList = null;
	private List<RejectRecord> rejectRecordList = null;
	protected List<String> allOper = null;
	protected List<String> nonOper = null;
	private String nasFlag = "N";
	private String sendPath;
	private String printType = "";
	private List<String> paper_barcode = null;
	private int pre_seclv;
	private int trg_seclv;
	private String pre_seclv_name;
	private String trg_seclv_name;
	private int modify_status;
	private String supervise_user_name = "";
	private String file_kind = "";
	private List<EntityPaper> mergeList = null;// 展示台账合并中被合并台账列表

	public List<EntityPaper> getMergeList() {
		return mergeList;
	}

	public String getFile_kind() {
		return file_kind;
	}

	public void setFile_kind(String file_kind) {
		this.file_kind = file_kind;
	}

	public String getPre_seclv_name() {
		return pre_seclv_name;
	}

	public void setPre_seclv_name(String pre_seclv_name) {
		this.pre_seclv_name = pre_seclv_name;
	}

	public String getTrg_seclv_name() {
		return trg_seclv_name;
	}

	public void setTrg_seclv_name(String trg_seclv_name) {
		this.trg_seclv_name = trg_seclv_name;
	}

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

	public String isNasFlag() {
		return nasFlag;
	}

	public void setNasFlag(String nasFlag) {
		this.nasFlag = nasFlag;
	}

	public BurnEvent getBurn_event() {
		return burn_event;
	}

	public List<String> getAllOper() {
		return allOper;
	}

	public void setAllOper(List<String> allOper) {
		this.allOper = allOper;
	}

	public List<String> getNonOper() {
		return nonOper;
	}

	public void setNonOper(List<String> nonOper) {
		this.nonOper = nonOper;
	}

	public String getSendPath() {
		return sendPath;
	}

	public void setSendPath(String sendPath) {
		this.sendPath = sendPath;
	}

	public List<BurnFile> getBurnFileList1() {
		return burnFileList1;
	}

	public void setBurnFileList1(List<BurnFile> burnFileList1) {
		this.burnFileList1 = burnFileList1;
	}

	public void setPrintType(String printType) {
		this.printType = printType;
	}

	public String getPrintType() {
		return printType;
	}

	public List<String> getPaper_barcode() {
		return paper_barcode;
	}

	public List<EventModify> getEventModify() {
		return eventModify;
	}

	public void setEventModify(List<EventModify> eventModify) {
		this.eventModify = eventModify;
	}

	public int getPre_seclv() {
		return pre_seclv;
	}

	public void setPre_seclv(int pre_seclv) {
		this.pre_seclv = pre_seclv;
	}

	public int getTrg_seclv() {
		return trg_seclv;
	}

	public void setTrg_seclv(int trg_seclv) {
		this.trg_seclv = trg_seclv;
	}

	public int getModify_status() {
		return modify_status;
	}

	public void setModify_status(int modify_status) {
		this.modify_status = modify_status;
	}

	public String getSupervise_user_name() {
		return supervise_user_name;
	}

	public void setSupervise_user_name(String supervise_user_name) {
		this.supervise_user_name = supervise_user_name;
	}

	public boolean hasPermission(String permission) {
		// 如果用户的权限列表中包含该权限字符串，则返回true
		if (allOper != null && allOper.contains(permission)) {
			return true;
		} else if (allOper != null && allOper.contains("/" + permission)) {
			return true;
		} else {
			// 数据库操作表中没有此操作记录，默认返回true;
			return false;
		}
	}

	private String handleFileList() throws Exception {
		sendPath = PropertyUtil.getUploadSendFilePath();
		sendPath = sendPath + File.separator + barcode;
		File path = new File(sendPath);
		burnFileList1 = new ArrayList<BurnFile>();
		if (path.isDirectory()) {
			File[] files = path.listFiles();
			String file_path = "";
			for (File file : files) {
				file_path = sendPath;
				file_path = file_path.substring(file_path.indexOf("files"));
				burnFileList1.add(new BurnFile(file.getName(), file_path));
			}
		}
		return SUCCESS;
	}

	@Override
	public String executeFunction() throws Exception {
		if (type.equalsIgnoreCase("PAPER")) {
			handleFileList();
			paper = ledgerService.getPaperByBarcode(barcode);
			supervise_user_name = userService.getUserNameByUserId(paper.getSupervise_user_iidd());
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
						if (enterEvent.getFile_kind().equals("BOOK") && enterEvent.getScope().equals("DEPT")) {
							file_kind = "BOOK";
						}
					}
				} else if (paper.getCreate_type().equals("SPECIAL")) {
					OaPrintEvent oaEvent = printService.getSpecialPrintEventByEventCode(paper.getEvent_code());
					if (oaEvent != null) {
						job_code = oaEvent.getJob_code();
					}
				} else if (paper.getCreate_type().equals("MERGE_ENTITY")) {
					job_code = paper.getJob_code();
					Map<String, Object> mapm = new HashMap<String, Object>();
					mapm.put("merge_state", "1");
					mapm.put("merge_code", paper.getPaper_barcode());

					mergeList = ledgerService.getMergePaperList(mapm);
				} else {
					event = printService.getPrintEventByPrintCode(paper.getEvent_code());
					if (event != null) {
						job_code = event.getJob_code();
					}
				}
				// 展示文件载体拒收记录
				rejectRecordList = ledgerService.getRejectRecordByBarcode(barcode);
				// 根据PID_barcode获得paper_barcode
				if (printType.equals("replacePage")) {
					paper_barcode = ledgerService.getPaperBarcode(barcode);
				}
				// 变更密级记录的数量
				int modify_num = ledgerService.getCountModifyEvent();
				if (modify_num > 0) {
					// 通过条码查询变更列表
					eventModify = ledgerService.getModifyEventListByBarcode(paper.getPaper_barcode());
					if (eventModify != null) {
						for (EventModify modify : eventModify) {
							if (modify.getModify_status() == 1) {
								// 变更状态为已变更
								modify_status = 1;
								modify.setPre_seclv_name(ledgerService.getSeclvNameByCode(modify.getPre_seclv()));
								modify.setTrg_seclv_name(ledgerService.getSeclvNameByCode(modify.getTrg_seclv()));
							}

						}

					}
				}
			}
		} else if (type.equalsIgnoreCase("DISC")) {
			cd = ledgerService.getCDByBarcode(barcode);
			supervise_user_name = userService.getUserNameByUserId(cd.getSupervise_user_iidd());
			if (cd != null) {
				if (cd.getCreate_type().equals("LEADIN")) {
					enterEvent = enterService.getEnterEventByEnterCode(cd.getEvent_code());
					if (enterEvent != null) {
						job_code = enterEvent.getJob_code();
					}
				} else {
					burn_event = burnService.getBurnEventByBurnCode(cd.getEvent_code());
					if (burn_event != null) {
						job_code = burn_event.getJob_code();
						// 判断刻录是否与NAS集成，便于台账查看详细时下载刻录文件
						SecUser secUser = userService.getSecUserByUid(burn_event.getUser_iidd());
						if (secUser != null) {
							secUser.setNeed_checkPwd(false);
							// 把用户的所有操作列表写进user
							List<String> allOperUrl = userService.getAllOperByUserOnly(secUser.getUser_iidd());
							secUser.setAllOper(allOperUrl);
							setAllOper(allOperUrl);
							// 把用户没有的操作列表写进user
							List<String> nonOperUrl = userService.getNonOperByUserOnly(secUser.getUser_iidd());
							secUser.setNonOper(nonOperUrl);
							setNonOper(nonOperUrl);
							// 判断刻录是否与NAS集成
							String permission = "burn/managenasburnevent.action";
							if (hasPermission(permission) == true) {
								nasFlag = "Y";
							}
						}
					}
				}
				is_cd_audit = ledgerService.checkShowBurnFile(cd.getSeclv_code().toString());
				// 展示光盘载体拒收记录
				rejectRecordList = ledgerService.getRejectRecordByBarcode(barcode);
				// 变更密级记录的数量
				int modify_num = ledgerService.getCountModifyEvent();
				if (modify_num > 0) {
					// 通过条码查询变更列表
					eventModify = ledgerService.getModifyEventListByBarcode(cd.getCd_barcode());
					if (eventModify != null) {
						for (EventModify modify : eventModify) {
							if (modify.getModify_status() == 1) {
								// 变更状态为已变更
								modify_status = 1;
								modify.setPre_seclv_name(ledgerService.getSeclvNameByCode(modify.getPre_seclv()));
								modify.setTrg_seclv_name(ledgerService.getSeclvNameByCode(modify.getTrg_seclv()));
							}
						}

					}
				}
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
			if (device.getScope().equals("PERSON")) {
				enterEvent = enterService.getEnterEventByMediumSerial(device.getDevice_barcode());
				if (enterEvent != null) {
					job_code = enterEvent.getJob_code();
				}
			} else {
				job_code = "default";
			}
		} else {
			throw new Exception("未知载体类型[" + type + "]");
		}
		itemList = ledgerService.getCycleItemListByBarcode(barcode);
		boolean first_item = true;
		// 将生命周期中第一次记录的job_code设置为初次产生的event表中的job_code，即载体产生时的job_code
		for (CycleItem item : itemList) {
			if (item.getOper().getKey().equals("MERGE_ENTITY")) {
				continue;
			}
			if (first_item) {
				item.setJob_code(job_code);
				first_item = false;
			} else if (item.getJob_code() == null || item.getJob_code() == "") {
				item.setJob_code("default");
			}
		}

		// 在全生命周期部分都做了展示，此部分可以去掉
		// 流程展示
		/*
		 * job = basicService.getProcessJobByCode(job_code); if (job != null) { ProcessRecord record = new
		 * ProcessRecord(); record.setJob_code(job_code); recordList = activitiService.getProcessRecordList(record);
		 * process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id()); }
		 */
		return type;
	}

	public PrintEvent getEvent() {
		return event;
	}

	public String getNas_url() throws Exception {
		return PropertyUtil.getNasBurnFileStoreUrl();
	}

	public String getNas_username() throws Exception {
		return PropertyUtil.getNasBurnFileStoreUsername();
	}

	public String getNas_password() throws Exception {
		return PropertyUtil.getNasBurnFileStorePassword();
	}

}
