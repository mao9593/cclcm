package hdsec.web.project.client.service;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.util.ActivitiCons;
import hdsec.web.project.basic.model.ClientMsg;
import hdsec.web.project.basic.model.SysConsole;
import hdsec.web.project.basic.service.BasicPrcManage;
import hdsec.web.project.client.mapper.ClientMapper;
import hdsec.web.project.client.model.ClientTask;
import hdsec.web.project.client.model.PendingWorkItem;
import hdsec.web.project.client.model.SysCVS;
import hdsec.web.project.client.model.SysModule;
import hdsec.web.project.user.model.ModuleEnum;
import hdsec.web.project.user.model.SecUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

public class ClientServiceImpl implements ClientService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private ClientMapper clientMapper;
	@Resource
	private BasicPrcManage basicPrcManage;

	@Override
	public List<SysModule> getSysModuleList() {
		logger.debug("getSysModuleList");
		return clientMapper.getSysModuleList();
	}

	@Override
	public List<SysCVS> getAllSysCVSList() {
		logger.debug("getAllSysCVSList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_all", true);
		return clientMapper.getSysCVSList(map);
	}

	@Override
	public List<SysCVS> getSysCVSList() {
		logger.debug("getSysPrinterList");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_all", false);
		return clientMapper.getSysCVSList(map);
	}

	@Override
	public SysCVS getCVSByCode(String cvs_code) {
		logger.debug("getCVSByCode:" + cvs_code);
		return clientMapper.getCVSByCode(cvs_code);
	}

	@Override
	public void updateCVS(String cvs_code, String dept_id, String set_version) {
		logger.debug("updateCVS:" + "code[" + cvs_code + "]deptid[" + dept_id + "]");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cvs_code", cvs_code);
		map.put("set_version", set_version);
		map.put("dept_id", dept_id);
		clientMapper.updateCVS(map);
	}

	@Override
	public List<SysCVS> getCVSListByCondition(Map<String, Object> map) {
		logger.debug("getCVSListByCondition:" + "MapCondition");
		return clientMapper.getCVSListByCondition(map);
	}

	@Override
	public List<PendingWorkItem> getApprovedWorkList(String user_iidd) {
		int passed = 0;
		int rejected = 0;
		List<PendingWorkItem> approvedList = new ArrayList<PendingWorkItem>();
		// 打印申请
		passed = getPrintEventNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS);
		rejected = getPrintEventNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT);
		if (passed + rejected > 0) {
			approvedList.add(new PendingWorkItem("PRINT", passed, rejected, "print/manageprintevent.action"));
		}
		// 刻录申请
		passed = getBurnEventNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS);
		rejected = getBurnEventNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT);
		if (passed + rejected > 0) {
			approvedList.add(new PendingWorkItem("BURN", passed, rejected, "burn/manageburnevent.action"));
		}
		// 复印申请
		passed = getCopyEventNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS);
		rejected = getCopyEventNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT);
		if (passed + rejected > 0) {
			approvedList.add(new PendingWorkItem("COPY", passed, rejected, "copy/managecopyevent.action"));
		}
		// 录入申请
		passed = getEnterEventNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS);
		rejected = getEnterEventNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT);
		if (passed + rejected > 0) {
			approvedList.add(new PendingWorkItem("LEADIN", passed, rejected, "enter/manageenterevent.action"));
		}
		// 流转申请
		passed = getTransferEventNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS);
		rejected = getTransferEventNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT);
		if (passed + rejected > 0) {
			approvedList.add(new PendingWorkItem("TRANSFER", passed, rejected, "ledger/managetransferevent.action"));
		}
		// 借用申请
		passed = getDeviceEventNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS);
		rejected = getDeviceEventNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT);
		if (passed + rejected > 0) {
			approvedList.add(new PendingWorkItem("DEVICE", passed, rejected, "device/managedeviceevent.action"));
		}
		// 磁介质销毁申请
		passed = getHandleDeviceEventNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS, JobTypeEnum.DESTROY_DEVICE)
				+ getHandleDeviceEventNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS, JobTypeEnum.DESTROY);
		rejected = getHandleDeviceEventNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT, JobTypeEnum.DESTROY_DEVICE)
				+ getHandleDeviceEventNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT, JobTypeEnum.DESTROY);
		if (passed + rejected > 0) {
			approvedList.add(new PendingWorkItem("DESTROY_DEVICE", passed, rejected,
					"basic/managehandlejob.action?type=DEVICE"));
		}
		// 借阅申请
		// TODO
		// 文件归档申请
		passed = getHandlePaperNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS, JobTypeEnum.FILE_PAPER)
				+ getHandlePaperNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS, JobTypeEnum.FILE);
		rejected = getHandlePaperNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT, JobTypeEnum.FILE_PAPER)
				+ getHandlePaperNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT, JobTypeEnum.FILE);
		if (passed + rejected > 0) {
			approvedList.add(new PendingWorkItem("FILE_PAPER", passed, rejected, "ledger/filepaperapply.action"));
		}
		// 文件外发申请
		passed = getHandlePaperNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS, JobTypeEnum.SEND_PAPER)
				+ getHandlePaperNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS, JobTypeEnum.SEND);
		rejected = getHandlePaperNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT, JobTypeEnum.SEND_PAPER)
				+ getHandlePaperNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT, JobTypeEnum.SEND);
		if (passed + rejected > 0) {
			approvedList.add(new PendingWorkItem("SEND_PAPER", passed, rejected, "ledger/sendpaperapply.action"));
		}
		// 文件销毁申请
		passed = getHandlePaperNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS, JobTypeEnum.DESTROY_PAPER)
				+ getHandlePaperNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS, JobTypeEnum.DESTROY);
		rejected = getHandlePaperNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT, JobTypeEnum.DESTROY_PAPER)
				+ getHandlePaperNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT, JobTypeEnum.DESTROY);
		if (passed + rejected > 0) {
			approvedList.add(new PendingWorkItem("DESTROY_PAPER", passed, rejected, "ledger/destroypaperapply.action"));
		}
		// 文件留用申请
		passed = getHandlePaperNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS, JobTypeEnum.DELAY_PAPER)
				+ getHandlePaperNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS, JobTypeEnum.DELAY);
		rejected = getHandlePaperNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT, JobTypeEnum.DELAY_PAPER)
				+ getHandlePaperNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT, JobTypeEnum.DELAY);
		if (passed + rejected > 0) {
			approvedList.add(new PendingWorkItem("DELAY_PAPER", passed, rejected, "ledger/delaypaperapply.action"));
		}
		// 光盘归档申请
		passed = getHandleCDNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS, JobTypeEnum.FILE)
				+ getHandleCDNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS, JobTypeEnum.FILE_CD);
		rejected = getHandleCDNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT, JobTypeEnum.FILE)
				+ getHandleCDNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT, JobTypeEnum.FILE_CD);
		if (passed + rejected > 0) {
			approvedList.add(new PendingWorkItem("FILE_CD", passed, rejected, "ledger/filecdapply.action"));
		}
		// 光盘外发申请
		passed = getHandleCDNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS, JobTypeEnum.SEND)
				+ getHandleCDNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS, JobTypeEnum.SEND_CD);
		rejected = getHandleCDNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT, JobTypeEnum.SEND)
				+ getHandleCDNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT, JobTypeEnum.SEND_CD);
		if (passed + rejected > 0) {
			approvedList.add(new PendingWorkItem("SEND_CD", passed, rejected, "ledger/sendcdapply.action"));
		}
		// 光盘销毁申请
		passed = getHandleCDNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS, JobTypeEnum.DESTROY)
				+ getHandleCDNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS, JobTypeEnum.DESTROY_CD);
		rejected = getHandleCDNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT, JobTypeEnum.DESTROY)
				+ getHandleCDNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT, JobTypeEnum.DESTROY_CD);
		if (passed + rejected > 0) {
			approvedList.add(new PendingWorkItem("DESTROY_CD", passed, rejected, "ledger/destroycdapply.action"));
		}
		// 光盘留用申请
		passed = getHandleCDNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS, JobTypeEnum.DELAY)
				+ getHandleCDNum(user_iidd, ActivitiCons.APPLY_APPROVED_PASS, JobTypeEnum.DELAY_CD);
		rejected = getHandleCDNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT, JobTypeEnum.DELAY)
				+ getHandleCDNum(user_iidd, ActivitiCons.APPLY_APPROVED_REJECT, JobTypeEnum.DELAY_CD);
		if (passed + rejected > 0) {
			approvedList.add(new PendingWorkItem("DELAY_CD", passed, rejected, "ledger/delaycdapply.action"));
		}
		return approvedList;
	}

	private int getPrintEventNum(String user_iidd, String job_status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		map.put("job_status", job_status);
		return clientMapper.getPrintEventNum(map);
	}

	private int getBurnEventNum(String user_iidd, String job_status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		map.put("job_status", job_status);
		return clientMapper.getBurnEventNum(map);
	}

	private int getCopyEventNum(String user_iidd, String job_status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		map.put("job_status", job_status);
		return clientMapper.getCopyEventNum(map);
	}

	private int getEnterEventNum(String user_iidd, String job_status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		map.put("job_status", job_status);
		return clientMapper.getEnterEventNum(map);
	}

	private int getTransferEventNum(String user_iidd, String job_status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		map.put("job_status", job_status);
		return clientMapper.getTransferEventNum(map);
	}

	private int getDeviceEventNum(String user_iidd, String job_status) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		map.put("job_status", job_status);
		return clientMapper.getDeviceEventNum(map);
	}

	private int getHandleDeviceEventNum(String user_iidd, String job_status, JobTypeEnum jobType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		map.put("job_status", job_status);
		map.put("jobType", jobType);
		return clientMapper.getHandleDeviceEventNum(map);
	}

	private int getHandlePaperNum(String user_iidd, String job_status, JobTypeEnum jobType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		map.put("job_status", job_status);
		map.put("jobType", jobType);
		return clientMapper.getHandlePaperNum(map);
	}

	private int getHandleCDNum(String user_iidd, String job_status, JobTypeEnum jobType) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		map.put("job_status", job_status);
		map.put("jobType", jobType);
		return clientMapper.getHandleCDNum(map);
	}

	@Override
	public List<PendingWorkItem> getWaitingApproveList(String user_iidd) {
		int total_num = 0;
		int num_1 = 0;
		int num_2 = 0;
		int num_3 = 0;
		List<String> instanceIdList = new ArrayList<String>();
		Map<String, Object> map = new HashMap<String, Object>();
		List<PendingWorkItem> list = new ArrayList<PendingWorkItem>();
		// 电子文件导入管理
		total_num = basicPrcManage.getCandidateInstanceCount(user_iidd, JobTypeEnum.MSG_INPUT.getJobTypeCode());
		if (total_num > 0) {
			list.add(new PendingWorkItem("MSG_INPUT", total_num, "input/manageinputaprvjob.action"));
		}
		// 打印申请
		num_1 = basicPrcManage.getCandidateInstanceCount(user_iidd, JobTypeEnum.PRINT_REMAIN.getJobTypeCode());
		num_2 = basicPrcManage.getCandidateInstanceCount(user_iidd, JobTypeEnum.PRINT_FILE.getJobTypeCode());
		num_3 = basicPrcManage.getCandidateInstanceCount(user_iidd, JobTypeEnum.PRINT_SEND.getJobTypeCode());
		total_num = num_1 + num_2 + num_3;
		if (total_num > 0) {
			list.add(new PendingWorkItem("PRINT", total_num, "basic/manageaprvjob.action?type=PRINT"));
		}
		// 刻录申请
		num_1 = basicPrcManage.getCandidateInstanceCount(user_iidd, JobTypeEnum.BURN_REMAIN.getJobTypeCode());
		num_2 = basicPrcManage.getCandidateInstanceCount(user_iidd, JobTypeEnum.BURN_FILE.getJobTypeCode());
		num_3 = basicPrcManage.getCandidateInstanceCount(user_iidd, JobTypeEnum.BURN_SEND.getJobTypeCode());
		total_num = num_1 + num_2 + num_3;
		if (total_num > 0) {
			list.add(new PendingWorkItem("BURN", total_num, "basic/manageaprvjob.action?type=BURN"));
		}
		// 复印申请
		num_1 = basicPrcManage.getCandidateInstanceCount(user_iidd, JobTypeEnum.COPY.getJobTypeCode());
		num_2 = basicPrcManage.getCandidateInstanceCount(user_iidd, JobTypeEnum.COPY_FILE.getJobTypeCode());
		num_3 = basicPrcManage.getCandidateInstanceCount(user_iidd, JobTypeEnum.COPY_SEND.getJobTypeCode());
		total_num = num_1 + num_2 + num_3;
		if (total_num > 0) {
			list.add(new PendingWorkItem("COPY", total_num, "basic/manageaprvjob.action?type=COPY"));
		}
		// 录入申请
		total_num = basicPrcManage.getCandidateInstanceCount(user_iidd, JobTypeEnum.LEADIN.getJobTypeCode());
		if (total_num > 0) {
			list.add(new PendingWorkItem("LEADIN", total_num, "basic/manageaprvjob.action?type=LEADIN"));
		}
		// 流转申请
		total_num = basicPrcManage.getCandidateInstanceCount(user_iidd, JobTypeEnum.TRANSFER.getJobTypeCode());
		if (total_num > 0) {
			list.add(new PendingWorkItem("TRANSFER", total_num, "basic/manageaprvjob.action?type=TRANSFER"));
		}
		// 借用申请
		total_num = basicPrcManage.getCandidateInstanceCount(user_iidd, JobTypeEnum.DEVICE.getJobTypeCode());
		if (total_num > 0) {
			list.add(new PendingWorkItem("DEVICE", total_num, "basic/manageaprvjob.action?type=DEVICE"));
		}
		// 借阅申请
		// TODO
		// 归档申请
		instanceIdList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.FILE.getJobTypeCode()));
		instanceIdList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd,
				JobTypeEnum.FILE_PAPER.getJobTypeCode()));
		instanceIdList
				.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.FILE_CD.getJobTypeCode()));
		if (instanceIdList != null && instanceIdList.size() > 0) {
			map.put("instanceIdList", instanceIdList);
			map.put("type", "PAPER");
			total_num = clientMapper.getHandleJobCountByEntityInstanceId(map);
			if (total_num > 0) {
				list.add(new PendingWorkItem("FILE_PAPER", total_num, "basic/managehandlejob.action?type=PAPER"));
			}
			map.put("type", "CD");
			total_num = clientMapper.getHandleJobCountByEntityInstanceId(map);
			if (total_num > 0) {
				list.add(new PendingWorkItem("FILE_CD", total_num, "basic/managehandlejob.action?type=CD"));
			}
		}
		// 外发申请
		instanceIdList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.SEND.getJobTypeCode()));
		instanceIdList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd,
				JobTypeEnum.SEND_PAPER.getJobTypeCode()));
		instanceIdList
				.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.SEND_CD.getJobTypeCode()));
		if (instanceIdList != null && instanceIdList.size() > 0) {
			map.put("instanceIdList", instanceIdList);
			map.put("type", "PAPER");
			total_num = clientMapper.getHandleJobCountByEntityInstanceId(map);
			if (total_num > 0) {
				list.add(new PendingWorkItem("SEND_PAPER", total_num, "basic/managehandlejob.action?type=PAPER"));
			}
			map.put("type", "CD");
			total_num = clientMapper.getHandleJobCountByEntityInstanceId(map);
			if (total_num > 0) {
				list.add(new PendingWorkItem("SEND_CD", total_num, "basic/managehandlejob.action?type=CD"));
			}
		}
		// 销毁申请
		instanceIdList
				.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.DESTROY.getJobTypeCode()));
		instanceIdList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd,
				JobTypeEnum.DESTROY_PAPER.getJobTypeCode()));
		instanceIdList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd,
				JobTypeEnum.DESTROY_CD.getJobTypeCode()));
		instanceIdList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd,
				JobTypeEnum.DESTROY_DEVICE.getJobTypeCode()));
		if (instanceIdList != null && instanceIdList.size() > 0) {
			map.put("instanceIdList", instanceIdList);
			map.put("type", "PAPER");
			total_num = clientMapper.getHandleJobCountByEntityInstanceId(map);
			if (total_num > 0) {
				list.add(new PendingWorkItem("DESTROY_PAPER", total_num, "basic/managehandlejob.action?type=PAPER"));
			}
			map.put("type", "CD");
			total_num = clientMapper.getHandleJobCountByEntityInstanceId(map);
			if (total_num > 0) {
				list.add(new PendingWorkItem("DESTROY_CD", total_num, "basic/managehandlejob.action?type=CD"));
			}
			map.put("type", "DEVICE");
			total_num = clientMapper.getHandleJobCountByEntityInstanceId(map);
			if (total_num > 0) {
				list.add(new PendingWorkItem("DESTROY_DEVICE", total_num, "basic/managehandlejob.action?type=DEVICE"));
			}
		}
		// 文件延期留用申请
		instanceIdList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd, JobTypeEnum.DELAY.getJobTypeCode()));
		instanceIdList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd,
				JobTypeEnum.DELAY_PAPER.getJobTypeCode()));
		instanceIdList.addAll(basicPrcManage.getCandidateInstanceIdList(user_iidd,
				JobTypeEnum.DELAY_CD.getJobTypeCode()));
		if (instanceIdList != null && instanceIdList.size() > 0) {
			map.put("instanceIdList", instanceIdList);
			map.put("type", "PAPER");
			total_num = clientMapper.getHandleJobCountByEntityInstanceId(map);
			if (total_num > 0) {
				list.add(new PendingWorkItem("DELAY_PAPER", total_num, "basic/managehandlejob.action?type=PAPER"));
			}
			map.put("type", "CD");
			total_num = clientMapper.getHandleJobCountByEntityInstanceId(map);
			if (total_num > 0) {
				list.add(new PendingWorkItem("DELAY_CD", total_num, "basic/managehandlejob.action?type=CD"));
			}
		}
		return list;
	}

	@Override
	public List<PendingWorkItem> getWorkerWorkList(String user_iidd, boolean nasFlag) {
		final int PASSED = 2;
		final int REJECTED = 3;
		int passed = 0;
		int rejected = 0;
		List<PendingWorkItem> approvedList = new ArrayList<PendingWorkItem>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		// 载体密级变更
		map.put("oper_type", "MODIFY_SECLV");
		map.put("message_type", PASSED);
		passed = clientMapper.getPendingWorkCount(map);
		map.put("message_type", REJECTED);
		rejected = clientMapper.getPendingWorkCount(map);
		if (passed + rejected > 0) {
			approvedList.add(new PendingWorkItem("MODIFY_SECLV", passed, rejected, "ledger/managemodifyledger.action"));
		}
		// 电子文件导入管理
		map.put("oper_type", "MSG_INPUT");
		map.put("message_type", PASSED);
		passed = clientMapper.getPendingWorkCount(map);
		map.put("message_type", REJECTED);
		rejected = clientMapper.getPendingWorkCount(map);
		if (passed + rejected > 0) {
			approvedList.add(new PendingWorkItem("MSG_INPUT", passed, rejected, "input/viewinputlistjob.action"));
		}
		// 如果打印模块开启，则统计待查看的打印审批结果
		if (ModuleEnum.PRINT.isModuleEnable()) {
			map.put("oper_type", "PRINT");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				approvedList.add(new PendingWorkItem("PRINT", passed, rejected, "print/manageprintjob.action"));
			}
			// 文件归档
			map.put("oper_type", "FILE_PAPER");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				approvedList.add(new PendingWorkItem("FILE_PAPER", passed, rejected, "ledger/filepaperapply.action"));
			}
			// 文件外发
			map.put("oper_type", "SEND_PAPER");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				approvedList.add(new PendingWorkItem("SEND_PAPER", passed, rejected, "ledger/sendpaperapply.action"));
			}
			// 文件销毁
			map.put("oper_type", "DESTROY_PAPER");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				approvedList.add(new PendingWorkItem("DESTROY_PAPER", passed, rejected,
						"ledger/destroypaperapply.action"));
			}
			// 文件延期留用
			map.put("oper_type", "DELAY_PAPER");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				approvedList.add(new PendingWorkItem("DELAY_PAPER", passed, rejected, "ledger/delaypaperapply.action"));
			}
			// 文件送销
			map.put("oper_type", "SENDES_PAPER");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				approvedList.add(new PendingWorkItem("SENDES_PAPER", passed, rejected,
						"ledger/viewsenddestroypaper.action"));

			}

			// 文件外带
			map.put("oper_type", "CARRYOUT_PAPER");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				approvedList.add(new PendingWorkItem("CARRYOUT_PAPER", passed, rejected,
						"ledger/managecarryoutpaperjob.action"));
			}

		}

		// 刻录
		if (ModuleEnum.BURN.isModuleEnable()) {
			map.put("oper_type", "BURN");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				if (nasFlag == true) {
					approvedList.add(new PendingWorkItem("BURN", passed, rejected, "burn/managenasburnevent.action"));
				} else {
					approvedList.add(new PendingWorkItem("BURN", passed, rejected, "burn/manageburnevent.action"));
				}
			}
			// 光盘归档
			map.put("oper_type", "FILE_CD");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				approvedList.add(new PendingWorkItem("FILE_CD", passed, rejected, "ledger/filecdapply.action"));
			}
			// 光盘外发
			map.put("oper_type", "SEND_CD");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				approvedList.add(new PendingWorkItem("SEND_CD", passed, rejected, "ledger/sendcdapply.action"));
			}
			// 光盘销毁
			map.put("oper_type", "DESTROY_CD");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				approvedList.add(new PendingWorkItem("DESTROY_CD", passed, rejected, "ledger/destroycdapply.action"));
			}
			// 光盘延期留用
			map.put("oper_type", "DELAY_CD");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				approvedList.add(new PendingWorkItem("DELAY_CD", passed, rejected, "ledger/delaycdapply.action"));
			}
			// 光盘送销
			map.put("oper_type", "SENDES_CD");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				approvedList.add(new PendingWorkItem("SENDES_CD", passed, rejected, "ledger/viewsenddestroycd.action"));
			}

			// 光盘外带
			map.put("oper_type", "CARRYOUT_CD");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				approvedList.add(new PendingWorkItem("CARRYOUT_CD", passed, rejected,
						"ledger/managecarryoutcdjob.action"));
			}

		}
		// 复印
		if (ModuleEnum.COPY.isModuleEnable()) {
			map.put("oper_type", "COPY");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				approvedList.add(new PendingWorkItem("COPY", passed, rejected, "copy/managecopyjob.action"));
			}
		}
		// 外来文件复印
		if (ModuleEnum.OUTCOPY.isModuleEnable()) {
			map.put("oper_type", "OUTCOPY");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				approvedList.add(new PendingWorkItem("OUTCOPY", passed, rejected, "copy/managecopyjobbyenter.action"));
			}
		}
		// 录入
		if (ModuleEnum.LEADIN.isModuleEnable()) {
			map.put("oper_type", "LEADIN");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				approvedList.add(new PendingWorkItem("LEADIN", passed, rejected, "enter/manageenterlistjob.action"));
			}
		}
		// 流转
		if (ModuleEnum.TRANSFER.isModuleEnable()) {
			map.put("oper_type", "TRANSFER");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				approvedList.add(new PendingWorkItem("TRANSFER", passed, rejected,
						"transfer/managepapertransferevent.action"));
			}
		}
		// 磁介质借用
		if (ModuleEnum.DEVICE.isModuleEnable()) {
			map.put("oper_type", "DEVICE");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				approvedList.add(new PendingWorkItem("DEVICE", passed, rejected, "device/managedevicejob.action"));
			}
		}
		// 部门载体借用
		if (ModuleEnum.BORROW.isModuleEnable()) {
			map.put("oper_type", "BORROW");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				approvedList.add(new PendingWorkItem("BORROW", passed, rejected,
						"borrow/viewpersonalborrowledger.action"));
			}
		}

		// 磁介质销毁
		if (ModuleEnum.DEVICE.isModuleEnable()) {
			map.put("oper_type", "DESTROY_DEVICE");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				approvedList.add(new PendingWorkItem("DESTROY_DEVICE", passed, rejected,
						"basic/managehandlejob.action?type=DEVICE"));
			}
		}

		// 载体归属转换
		if (ModuleEnum.CHANGE.isModuleEnable()) {
			map.put("oper_type", "CHANGE");
			map.put("message_type", PASSED);
			passed = clientMapper.getPendingWorkCount(map);
			map.put("message_type", REJECTED);
			rejected = clientMapper.getPendingWorkCount(map);
			if (passed + rejected > 0) {
				approvedList.add(new PendingWorkItem("CHANGE", passed, rejected, "change/managechangejob.action"));
			}
		}
		return approvedList;
	}

	@Override
	public List<PendingWorkItem> getLeaderWorkList(String user_iidd, boolean nasFlag) {
		final int WAIT = 1;
		int num = 0;
		List<PendingWorkItem> waitingList = new ArrayList<PendingWorkItem>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		// 文件密级变更
		map.put("oper_type", "MODIFY_SECLV");
		map.put("message_type", WAIT);
		num = clientMapper.getPendingWorkCount(map);
		if (num > 0) {
			waitingList.add(new PendingWorkItem("MODIFY_SECLV", num, "ledger/managemodifyjob.action"));
		}
		// 电子文件导入管理
		map.put("oper_type", "MSG_INPUT");
		map.put("message_type", WAIT);
		num = clientMapper.getPendingWorkCount(map);
		if (num > 0) {
			waitingList.add(new PendingWorkItem("MSG_INPUT", num, "input/manageinputaprvjob.action"));
		}
		// 如果打印模块开启，则统计代查看的打印审批结果
		if (ModuleEnum.PRINT.isModuleEnable()) {
			map.put("oper_type", "PRINT");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("PRINT", num, "print/manageprintaprvjob.action"));
			}
			// 文件归档
			map.put("oper_type", "FILE_PAPER");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("FILE_PAPER", num, "basic/managehandlejob.action?type=PAPER"));
			}
			// 文件外发
			map.put("oper_type", "SEND_PAPER");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("SEND_PAPER", num, "basic/managehandlejob.action?type=PAPER"));
			}

			// 文件外带
			map.put("oper_type", "CARRYOUT_PAPER");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("CARRYOUT_PAPER", num, "ledger/managecarryoutaprvjob.action"));
			}

			// 文件销毁
			map.put("oper_type", "DESTROY_PAPER");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("DESTROY_PAPER", num, "basic/managehandlejob.action?type=PAPER"));
			}
			// 文件延期留用
			map.put("oper_type", "DELAY_PAPER");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("DELAY_PAPER", num, "basic/managehandlejob.action?type=PAPER"));
			}
			// 文件送销
			map.put("oper_type", "SENDES_PAPER");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				waitingList
						.add(new PendingWorkItem("SENDES_PAPER", num, "ledger/managesenddestroypaperaprvjob.action"));
			}
		}

		// 刻录
		if (ModuleEnum.BURN.isModuleEnable()) {
			map.put("oper_type", "BURN");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				if (nasFlag == true) {
					waitingList.add(new PendingWorkItem("BURN", num,
							"basic/manageaprvjob.action?type=BURN&file_src=nas"));
				} else {
					waitingList.add(new PendingWorkItem("BURN", num, "basic/manageaprvjob.action?type=BURN"));
				}
			}
			// 光盘归档
			map.put("oper_type", "FILE_CD");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("FILE_CD", num, "basic/managehandlejob.action?type=CD"));
			}
			// 光盘外发
			map.put("oper_type", "SEND_CD");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("SEND_CD", num, "basic/managehandlejob.action?type=CD"));
			}

			// 光盘外带
			map.put("oper_type", "CARRYOUT_CD");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("CARRYOUT_CD", num, "ledger/managecarryoutaprvjob.action"));
			}

			// 光盘销毁
			map.put("oper_type", "DESTROY_CD");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("DESTROY_CD", num, "basic/managehandlejob.action?type=CD"));
			}
			// 光盘延期留用
			map.put("oper_type", "DELAY_CD");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("DELAY_CD", num, "basic/managehandlejob.action?type=CD"));
			}
			// 光盘送销
			map.put("oper_type", "SENDES_CD");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("SENDES_CD", num, "ledger/managesenddestroycdaprvjob.action"));
			}
		}
		// 复印
		if (ModuleEnum.COPY.isModuleEnable()) {
			map.put("oper_type", "COPY");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("COPY", num, "copy/managecopyaprvjob.action"));
			}
		}
		// 外来文件复印
		if (ModuleEnum.OUTCOPY.isModuleEnable()) {
			map.put("oper_type", "OUTCOPY");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("OUTCOPY", num, "copy/managecopyaprvjobbyenter.action"));
			}
		}
		// 录入
		if (ModuleEnum.LEADIN.isModuleEnable()) {
			map.put("oper_type", "LEADIN");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("LEADIN", num, "enter/manageenteraprvjob.action?type=LEADIN"));
			}
		}
		// 流转
		if (ModuleEnum.TRANSFER.isModuleEnable()) {
			map.put("oper_type", "TRANSFER");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("TRANSFER", num, "basic/manageaprvjob.action?type=TRANSFER"));
			}
		}
		// 磁介质借用
		if (ModuleEnum.DEVICE.isModuleEnable()) {
			map.put("oper_type", "DEVICE");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("DEVICE", num, "device/managedeviceaprvjob.action"));
			}
		}
		// 部门载体借用
		if (ModuleEnum.BORROW.isModuleEnable()) {
			map.put("oper_type", "BORROW");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("BORROW", num, "basic/manageaprvjob.action?type=BORROW"));
			}
		}
		// 磁介质销毁
		if (ModuleEnum.DEVICE.isModuleEnable()) {
			map.put("oper_type", "DESTROY_DEVICE");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("DESTROY_DEVICE", num, "basic/managehandlejob.action?type=DEVICE"));
			}
		}
		// 载体归属变换
		if (ModuleEnum.CHANGE.isModuleEnable()) {
			map.put("oper_type", "CHANGE");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("CHANGE", num, "change/managechangeaprvjob.action"));
			}
		}
		return waitingList;
	}

	@Override
	public void delClientMsgByJobCode(String job_code) {
		clientMapper.delClientMsgByJobCode(job_code);
	}

	@Override
	public void delRetrieveMsgByUser(Map<String, Object> map) {
		clientMapper.delRetrieveMsgByUser(map);
	}

	@Override
	public SecUser getUserByIdentity(String identity) {
		logger.debug("getUserByIdentity:" + identity);
		return clientMapper.getUserByIdentity(identity);
	}

	@Override
	public List<String> getOperTypeByJobCode(String job_code) {
		logger.debug("getOperTypeByJobCode");
		return clientMapper.getOperTypeByJobCode(job_code);
	}

	@Override
	public Integer getPrintMenuId() {
		logger.debug("getPrintMenuId");
		return clientMapper.getDirRankByOperCode("0103");// 打印管理模块
	}

	/**
	 * 待办
	 */
	@Override
	public List<ClientMsg> getClientMessage(Map<String, Object> map) {
		String oper_type = (String) map.get("oper_type");
		List<ClientMsg> cmg = null;
		if (oper_type.contains("_PAPER") || oper_type.contains("_CD")) {
			logger.info("!!!!!!!!!!!paper||cd!!!!!!!!!!!!!!!");
			cmg = clientMapper.getClientMessage1(map);
		} else if ("DESTROY_DEVICE".equals(oper_type)) {
			logger.info("!!!!!!!!!!!DESTROY_DEVICE!!!!!!!!!!!!!!!");
			cmg = clientMapper.getClientMessage2(map);
		} else {
			logger.info("!!!!!!!!!!!!!!!!!!!!!!!!!!");
			cmg = clientMapper.getClientMessage(map);
		}

		return cmg;
	}

	@Override
	public List<PendingWorkItem> getLeaderWorkList1(String user_iidd, boolean nasFlag) {
		final int WAIT = 1;
		int num = 0;
		List<PendingWorkItem> waitingList = new ArrayList<PendingWorkItem>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		// 如果打印模块开启，则统计代查看的打印审批结果
		if (ModuleEnum.PRINT.isModuleEnable()) {
			map.put("oper_type", "PRINT");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("PRINT", num, "print/manageprintaprvjob.action"));
			}
			// 文件归档
			map.put("oper_type", "FILE_PAPER");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("FILE_PAPER", num, "basic/managehandlejob.action?type=PAPER"));
			}
			// 文件外发
			map.put("oper_type", "SEND_PAPER");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("SEND_PAPER", num, "basic/managehandlejob.action?type=PAPER"));
			}
			// 文件销毁
			map.put("oper_type", "DESTROY_PAPER");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("DESTROY_PAPER", num, "basic/managehandlejob.action?type=PAPER"));
			}
			// 文件延期留用
			map.put("oper_type", "DELAY_PAPER");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("DELAY_PAPER", num, "basic/managehandlejob.action?type=PAPER"));
			}
		}
		// 刻录
		if (ModuleEnum.BURN.isModuleEnable()) {
			map.put("oper_type", "BURN");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				if (nasFlag == true) {
					waitingList.add(new PendingWorkItem("BURN", num,
							"basic/manageaprvjob.action?type=BURN&file_src=nas"));
				} else {
					waitingList.add(new PendingWorkItem("BURN", num, "basic/manageaprvjob.action?type=BURN"));
				}
			}
			// 光盘归档
			map.put("oper_type", "FILE_CD");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("FILE_CD", num, "basic/managehandlejob.action?type=CD"));
			}
			// 光盘外发
			map.put("oper_type", "SEND_CD");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("SEND_CD", num, "basic/managehandlejob.action?type=CD"));
			}
			// 光盘销毁
			map.put("oper_type", "DESTROY_CD");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("DESTROY_CD", num, "basic/managehandlejob.action?type=CD"));
			}
			// 光盘延期留用
			map.put("oper_type", "DELAY_CD");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("DELAY_CD", num, "basic/managehandlejob.action?type=CD"));
			}
		}
		// 复印
		if (ModuleEnum.COPY.isModuleEnable()) {
			map.put("oper_type", "COPY");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("COPY", num, "copy/managecopyaprvjob.action"));
			}
		}
		// 外来文件复印
		if (ModuleEnum.OUTCOPY.isModuleEnable()) {
			map.put("oper_type", "OUTCOPY");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("OUTCOPY", num, "copy/managecopyaprvjobbyenter.action"));
			}
		}
		// 录入
		if (ModuleEnum.LEADIN.isModuleEnable()) {
			map.put("oper_type", "LEADIN");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("LEADIN", num, "enter/manageenteraprvjob.action?type=LEADIN"));
			}
		}
		// 流转
		if (ModuleEnum.TRANSFER.isModuleEnable()) {
			map.put("oper_type", "TRANSFER");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("TRANSFER", num, "basic/manageaprvjob.action?type=TRANSFER"));
			}
		}
		// 磁介质借用
		if (ModuleEnum.DEVICE.isModuleEnable()) {
			map.put("oper_type", "DEVICE");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("DEVICE", num, "device/managedeviceaprvjob.action"));
			}
		}
		// 部门载体借用
		if (ModuleEnum.BORROW.isModuleEnable()) {
			map.put("oper_type", "BORROW");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("BORROW", num, "basic/manageaprvjob.action?type=BORROW"));
			}
		}
		// 磁介质销毁
		if (ModuleEnum.DEVICE.isModuleEnable()) {
			map.put("oper_type", "DESTROY_DEVICE");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("DESTROY_DEVICE", num, "basic/managehandlejob.action?type=DEVICE"));
			}
		}
		// 载体归属变换
		if (ModuleEnum.CHANGE.isModuleEnable()) {
			map.put("oper_type", "CHANGE");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("CHANGE", num, "change/managechangeaprvjob.action"));
			}
		}
		return waitingList;
	}

	@Override
	public List<PendingWorkItem> getLeaderWorkList2(String user_iidd, boolean nasFlag, String operType) {
		final int WAIT = 1;
		int num = 0;
		List<PendingWorkItem> waitingList = new ArrayList<PendingWorkItem>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		logger.info("@@@@@@@@@@@@@@operType=" + operType);
		// 如果打印模块开启，则统计代查看的打印审批结果
		if (operType.equals("PRINT")) {
			map.put("oper_type", "PRINT");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("PRINT", num, "print/manageprintaprvjob.action"));
			}
		}
		if (operType.equals("FILE_PAPER")) {
			// 文件归档
			map.put("oper_type", "FILE_PAPER");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("FILE_PAPER", num, "basic/managehandlejob.action?type=PAPER"));
			}
		}
		if (operType.equals("SEND_PAPER")) {
			// 文件外发
			map.put("oper_type", "SEND_PAPER");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("SEND_PAPER", num, "basic/managehandlejob.action?type=PAPER"));
			}
		}
		if (operType.equals("DESTROY_PAPER")) {
			// 文件销毁
			map.put("oper_type", "DESTROY_PAPER");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("DESTROY_PAPER", num, "basic/managehandlejob.action?type=PAPER"));
			}
		}
		if (operType.equals("DELAY_PAPER")) {
			// 文件延期留用
			map.put("oper_type", "DELAY_PAPER");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("DELAY_PAPER", num, "basic/managehandlejob.action?type=PAPER"));
			}
		}
		// 刻录
		if (operType.equals("BURN")) {
			map.put("oper_type", "BURN");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				if (nasFlag == true) {
					waitingList.add(new PendingWorkItem("BURN", num,
							"basic/manageaprvjob.action?type=BURN&file_src=nas"));
				} else {
					waitingList.add(new PendingWorkItem("BURN", num, "basic/manageaprvjob.action?type=BURN"));
				}
			}
		}
		if (operType.equals("FILE_CD")) {
			// 光盘归档
			map.put("oper_type", "FILE_CD");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("FILE_CD", num, "basic/managehandlejob.action?type=CD"));
			}
		}
		// 光盘外发
		if (operType.equals("SEND_CD")) {
			map.put("oper_type", "SEND_CD");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("SEND_CD", num, "basic/managehandlejob.action?type=CD"));
			}
		}
		if (operType.equals("DESTROY_CD")) {
			// 光盘销毁
			map.put("oper_type", "DESTROY_CD");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("DESTROY_CD", num, "basic/managehandlejob.action?type=CD"));
			}
		}
		// 光盘延期留用
		if (operType.equals("DELAY_CD")) {
			map.put("oper_type", "DELAY_CD");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("DELAY_CD", num, "basic/managehandlejob.action?type=CD"));
			}
		}
		// 复印
		if (operType.equals("COPY")) {
			map.put("oper_type", "COPY");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("COPY", num, "copy/managecopyaprvjob.action"));
			}
		}
		// 外来文件复印
		if (operType.equals("OUTCOPY")) {
			map.put("oper_type", "OUTCOPY");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("OUTCOPY", num, "copy/managecopyaprvjobbyenter.action"));
			}
		}
		// 录入
		if (operType.equals("LEADIN")) {
			map.put("oper_type", "LEADIN");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("LEADIN", num, "enter/manageenteraprvjob.action?type=LEADIN"));
			}
		}
		// 流转
		if (operType.equals("TRANSFER")) {
			map.put("oper_type", "TRANSFER");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("TRANSFER", num, "basic/manageaprvjob.action?type=TRANSFER"));
			}
		}
		// 磁介质借用
		if (operType.equals("DEVICE")) {
			map.put("oper_type", "DEVICE");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("DEVICE", num, "device/managedeviceaprvjob.action"));
			}
		}
		// 部门载体借用
		if (operType.equals("BORROW")) {
			map.put("oper_type", "BORROW");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("BORROW", num, "basic/manageaprvjob.action?type=BORROW"));
			}
		}
		// 磁介质销毁
		if (operType.equals("DESTROY_DEVICE")) {
			map.put("oper_type", "DESTROY_DEVICE");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("DESTROY_DEVICE", num, "basic/managehandlejob.action?type=DEVICE"));
			}
		}
		// 载体归属变换
		if (operType.equals("CHANGE")) {
			map.put("oper_type", "CHANGE");
			map.put("message_type", WAIT);
			num = clientMapper.getPendingWorkCount1(map);
			if (num > 0) {
				waitingList.add(new PendingWorkItem("CHANGE", num, "change/managechangeaprvjob.action"));
			}
		}
		return waitingList;
	}

	@Override
	public String getDeptName(String dept_id) {
		return clientMapper.getDeptName(dept_id);
	}

	@Override
	public String getUserName(String user_iidd) {
		return clientMapper.getUserName(user_iidd);
	}

	@Override
	public SysConsole getConsoleByCode(String console_code) {
		return clientMapper.getConsoleByCode(console_code);
	}

	@Override
	public List<ClientTask> getTask(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return clientMapper.getTask(map);
	}

	@Override
	public List<PendingWorkItem> getTransferClient(String user_iidd) {
		int num = 0;
		List<PendingWorkItem> transfers = new ArrayList<PendingWorkItem>();
		// 流转提醒
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", user_iidd);
		map.put("oper_type", "TRANSFER_CONFIRM");
		map.put("message_type", 8);
		num = clientMapper.getPendingWorkCount(map);
		if (num > 0) {
			transfers.add(new PendingWorkItem("TRANSFER_CONFIRM", num, "transfer/viewconfirmtransfer.action"));
		}
		// 借用到期
		map.put("user_iidd", user_iidd);
		map.put("oper_type", "BORROW");
		map.put("message_type", 7);
		num = clientMapper.getPendingWorkCount(map);
		if (num > 0) {
			transfers.add(new PendingWorkItem("BORROW_EXPIRE", num, "borrow/viewpersonalborrowledger.action"));
		}
		return transfers;
	}
}
