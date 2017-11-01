package hdsec.web.project.computer.service;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.activiti.service.ActivitiService;
import hdsec.web.project.activiti.util.ActivitiCons;
import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.basic.model.ClientMsg;
import hdsec.web.project.basic.service.BasicPrcManage;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.common.bm.BMCycleItem;
import hdsec.web.project.common.bm.model.BMSysConfigItem;
import hdsec.web.project.computer.mapper.ComputerMapper;
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
import hdsec.web.project.securityuser.mapper.SecurityUserMapper;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.service.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

public class ComputerServiceImpl implements ComputerService {

	private final Logger logger = Logger.getLogger(this.getClass());
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Resource
	private ComputerMapper ComputerMapper;
	@Resource
	private BasicService basicService;
	@Resource
	private BasicPrcManage basicPrcManage;
	@Resource
	private BasicMapper basicMapper;
	@Resource
	private UserService userService;
	@Resource
	private ActivitiService activitiService;
	@Resource
	private SecurityUserMapper securityUserMapper;

	@Override
	public void addEntityComputer(EntityComputer computer, BMCycleItem cycleitem) {
		logger.debug("addEntityComputer:" + computer.getComputer_code());
		ComputerMapper.addEntityComputer(computer);
		securityUserMapper.addCycleItem(cycleitem);
	}

	@Override
	public void updateComputer(EntityComputer computer) {
		logger.debug("updateComputer:" + computer.getComputer_code());
		ComputerMapper.updateComputer(computer);

	}

	@Override
	public List<DeviceType> getDeviceTypeList() {
		logger.debug("getComputerTypeList");
		return ComputerMapper.getComputerTypeList();
	}

	@Override
	public List<ChangeDeviceEvent> getComputerEventListByJobCode(String job_code) {
		logger.debug("getComputerEventListByJobCode");
		return ComputerMapper.getComputerEventListByJobCode(job_code);
	}

	@Override
	public Integer getTypeIDByName(String typename) {
		logger.debug("getTypeIDByName");
		return ComputerMapper.getTypeIDByName(typename);
	}

	@Override
	public List<EntityComputer> getAllComputerList(Map<String, Object> map) throws Exception {
		logger.debug("getAllComputerList");
		String changedBarcode = basicService.changeBarcode((String) map.get("computer_barcode"));
		map.put("computer_barcode", changedBarcode);
		return ComputerMapper.getAllComputerList(map);
	}

	@Override
	public EntityComputer getComputerByMap(Map<String, Object> map) {
		logger.debug("getComputerByMap:");
		return ComputerMapper.getComputerByMap(map);
	}

	@Override
	public void updateComputerByJobCode(Map<String, Object> map) {
		logger.debug("updateComputerByJobCode");
		ComputerMapper.updateComputerByJobCode(map);

	}

	@Override
	public void addDeviceOperationByEventCode(Map<String, Object> map) {
		logger.debug("addDeviceOperationByEventCode");
		ComputerMapper.addDeviceOperationByEventCode(map);

	}

	@Override
	public void addComputerEvent(ChangeDeviceEvent event, String next_approver) throws Exception {
		logger.debug("addComputerEvent" + event.getEvent_code());
		ComputerMapper.addChangeDeviceEvent(event);// 插入event事件
		// 维修、报废为独占，更改计算机台账状态
		if (event != null
				&& (event.getEvent_type() == 4 || event.getEvent_type() == 5 || event.getEvent_type() == 3
						|| event.getEvent_type() == 7 || event.getEvent_type() == 6)) {
			int computer_status = 0;
			if (event.getEvent_type() == 4) {
				// 申请报废
				computer_status = 7;
			} else if (event.getEvent_type() == 5) {
				// 申请维修
				computer_status = 6;
			} else if (event.getEvent_type() == 3) {
				// 申请变更
				computer_status = 8;
			} else if (event.getEvent_type() == 7) {
				// 申请退网
				computer_status = 9;
			} else if (event.getEvent_type() == 6) {
				// 申请重装
				computer_status = 10;
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("computer_barcode", event.getBarcode());
			map.put("computer_status", computer_status);
			ComputerMapper.updateComputerByEvent(map);
		}
		if (event.getEvent_type() == 11 || event.getEvent_type() == 12 || event.getEvent_type() == 13
				|| event.getEvent_type() == 14) {
			int statue = 0;
			if (event.getEvent_type() == 11) {
				// 申请变更
				statue = 7;
			} else if (event.getEvent_type() == 12) {
				// 申请维修
				statue = 8;
			} else if (event.getEvent_type() == 13) {
				// 申请报废
				statue = 9;
			} else if (event.getEvent_type() == 14) {
				// 申请重装
				statue = 10;
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("book_barcode", event.getBarcode());
			map.put("book_status", statue);
			ComputerMapper.updateEntityBookByEvent(map);
		}

		ApproveProcess process = basicPrcManage
				.getApproveProcessByKey(event.getDept_id(), String.valueOf(event.getSeclv_code()), event.getJobType()
						.getJobTypeCode(), event.getUsage_code(), true);// 获取审批流程
		String job_status = null;// 流程状态
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;// 审批流程已通过
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;// 待审批
		}

		String next_approver_name = basicPrcManage.getApproverName(next_approver);// 通过审批人ID查询审批人姓名
		String job_code = event.getUser_iidd() + "-" + event.getJobType().getJobTypeCode() + "-"
				+ System.currentTimeMillis();// 生成job_code

		ProcessJob job = new ProcessJob(job_code, event.getUser_iidd(), event.getDept_id(), event.getSeclv_code(),
				event.getJobType(), new Date(), job_status, next_approver, next_approver_name, process.getProcess_id());
		// 新的任务
		// job.setComment(comment);
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		// 向event_secplace的event中添加job_code信息
		Map<String, String> map = new HashMap<String, String>();
		map.put("event_code", event.getEvent_code());
		map.put("job_code", job_code);
		ComputerMapper.updateChangeDeviceEventJobCode(map);

		// 更新作业信息
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + event.getJobType().getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);// 记录流程

		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有" + event.getJobType().getJobTypeName() + "作业需要您审批";
			String oper_type = "";
			if (event.getEvent_type() == 1) {
				oper_type = "EVENT_INTCOM";
			} else if (event.getEvent_type() == 2) {
				oper_type = "EVENT_SINCOM";
			} else if (event.getEvent_type() == 3) {
				oper_type = "EVENT_CHGCOM";
			} else if (event.getEvent_type() == 4) {
				oper_type = "EVENT_DESCOM";
			} else if (event.getEvent_type() == 5) {
				oper_type = "EVENT_REPCOM";
			} else if (event.getEvent_type() == 6) {
				oper_type = "EVENT_REINSTALL";
			} else if (event.getEvent_type() == 7) {
				oper_type = "EVENT_QUITINT";
			} else if (event.getEvent_type() == 8) {
				oper_type = "EVENT_USBKEY";
			} else if (event.getEvent_type() == 9) {
				oper_type = "EVENT_OPENPORT";
			} else if (event.getEvent_type() == 10) {
				oper_type = "EVENT_LOCALPRINTER";
			} else if (event.getEvent_type() == 11) {
				oper_type = "BOOK_CHANGE";
			} else if (event.getEvent_type() == 12) {
				oper_type = "BOOK_REPAIR";
			} else if (event.getEvent_type() == 13) {
				oper_type = "BOOK_DES";
			} else if (event.getEvent_type() == 14) {
				oper_type = "BOOK_REINSTALL";
			}

			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, oper_type, 1, job.getJob_code(), message,
						new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		} else {// 没有审批流程，则直接添加的entity中
			if (event.getEvent_type() == 1 || event.getEvent_type() == 2) {
				// String computer_barcode = basicService.createEntityBarcode("COMPUTER");
				String computer_barcode = "";
				String dept_id = event.getDept_id();
				if (StringUtils.hasLength(dept_id)) {
					// computer_barcode = basicService.createCETCEntityBarcode(dept_id);
					// 不区分份数，默认为0
					computer_barcode = basicService.createNewCETCBarcode(dept_id, 0);
				} else {
					// computer_barcode = basicService.createCETCEntityBarcode("00");
					// 不区分份数，默认为0
					computer_barcode = basicService.createNewCETCBarcode("", 0);
				}
				addComputerByEvent(event, computer_barcode, user_name, event.getUser_iidd());
				// 在event中添加对应的设备条码号
				addBarcodeInEvent(event.getEvent_code(), computer_barcode);
			} else if (event.getEvent_type() == 3 || event.getEvent_type() == 4 || event.getEvent_type() == 5
					|| event.getEvent_type() == 6 || event.getEvent_type() == 7 || event.getEvent_type() == 9) {// 直接改变台账中对应的信息
				changeComputerByEvent(event, user_name, event.getUser_iidd());
			} else if (event.getEvent_type() == 8 || event.getEvent_type() == 10) {
				addDeviceOperation(event, user_name, event.getUser_iidd());
			}
		}

	}

	@Override
	public void addDeviceOperation(ChangeDeviceEvent event, String user_name, String user_id) {
		String dept_name = userService.getDeptNameByUserId(user_id);

		// 生成载体生命周期记录
		BMCycleItem cycleitem = new BMCycleItem();
		cycleitem.setBarcode(event.getBarcode());
		cycleitem.setEntity_type("COMPUTER");
		cycleitem.setOper_time(new Date());
		cycleitem.setUser_name(user_name);
		cycleitem.setDept_name(dept_name);
		if (event.getEvent_type() == 10) {
			cycleitem.setOper("LOCALPRINTER");
		}
		cycleitem.setJob_code(event.getJob_code());
		securityUserMapper.addCycleItem(cycleitem);
	}

	@Override
	public String changeComputerByEvent(ChangeDeviceEvent event, String user_name, String user_id) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		EntityComputer computer = null;
		int types = 0;
		if (event.getEvent_type() == 11 || event.getEvent_type() == 12 || event.getEvent_type() == 13
				|| event.getEvent_type() == 14) {
			map.put("book_barcode", event.getBarcode());
		} else {
			map.put("computer_barcode", event.getBarcode());
			computer = ComputerMapper.getComputerByMap(map);
		}
		EntityEventDevice event_device_content = getEntityEventDeviceByEventCode(event.getEvent_code());

		if (event.getEvent_type() == 3) {// 计算机变更
			// 计算机变更
			if (event_device_content != null) {
				map.put("seclv_code", event_device_content.getSeclv_code());
				map.put("duty_user_id", event_device_content.getDuty_user_id());
				map.put("duty_dept_id", event_device_content.getDuty_dept_id());
				// map.put("duty_entp_id", event_device_content.getDuty_entp_id());
				// map.put("conf_code", event_device_content.getConf_code());
				map.put("hdisk_no", event_device_content.getHdisk_no());
				// map.put("computer_ip", event_device_content.getComputer_ip());
				map.put("computer_mac", event_device_content.getComputer_mac());
				map.put("storage_area", event_device_content.getStorage_area());
				map.put("storage_location", event_device_content.getStorage_location());
				map.put("summ", event_device_content.getSumm());
			}
			// 信息中心处理意见获取，并更新到台账中-gj
			EntityDeviceOperation edo = getDeviceOperationDataByEventCode(event.getEvent_code());
			if (edo != null) {
				map.put("software_summ", edo.getSoftware_summ());
				map.put("software_type", edo.getSoftware_type());
				if (!edo.getKey_code().equals("")) {
					String key = edo.getKey_code() + "," + computer.getKey_code();
					map.put("key_code", key);
				}
				map.put("computer_ip", edo.getComputer_ip());
				map.put("computer_gateway", edo.getComputer_gateway());
				map.put("mark_code", edo.getMark_code());
				map.put("vlan_num", edo.getVlan_num());
				map.put("switch_num", edo.getSwitch_num());
				map.put("switch_point", edo.getSwitch_point());
				// 更换硬盘新硬盘序列号更新台账
				if (!edo.getOperation_content().equals("")) {
					map.put("hdisk_no", edo.getOperation_content());
				}
			}
			map.put("computer_status", "1");
			ComputerMapper.updateComputerByEvent(map);
		} else if (event.getEvent_type() == 4) {// 计算机报废
			// 计算机状态改为报废
			map.put("computer_status", "4");
			ComputerMapper.updateComputerByEvent(map);
		} else if (event.getEvent_type() == 5) {// 计算机维修
			EntityDeviceOperation edo = getDeviceOperationDataByEventCode(event.getEvent_code());
			if (edo != null) {
				map.put("software_type", edo.getSoftware_type());
				map.put("install_time", edo.getStart_time());
				String[] splOperation_content = edo.getOperation_content().split(",");
				if (splOperation_content[6].equals("是")) {
					map.put("computer_mac", splOperation_content[7]);
				}
			}
			// 计算机维修流程完成，则状态改为“在用”
			map.put("computer_status", "1");
			ComputerMapper.updateComputerByEvent(map);
		} else if (event.getEvent_type() == 6) {// //计算机重装
			EntityDeviceOperation edo = getDeviceOperationDataByEventCode(event.getEvent_code());
			if (edo != null) {
				map.put("software_summ", edo.getSoftware_summ());
				map.put("software_type", edo.getSoftware_type());
				map.put("install_time", edo.getOperation_time());
			}
			map.put("computer_status", "1");
			ComputerMapper.updateComputerByEvent(map);
		} else if (event.getEvent_type() == 7) {// 计算机退网
			map.put("computer_status", "2");// 退网通过，将状态更改为“停用”--20151023-gj
			ComputerMapper.updateComputerByEvent(map);
		} else if (event.getEvent_type() == 8) {// USBKEY变更
			// String[] content = event.getEvent_content().split("\\|");
			Integer apply_type = event_device_content.getApply_type();

			EntityDeviceOperation operation = getDeviceOperationDataByEventCode(event.getEvent_code());

			String key_code = computer.getKey_code();
			if (apply_type == 1) {
				// 1.如果是首次申请，直接将新key号添加到计算机台账中
				key_code = operation.getNew_content() + "," + key_code;
			} else if (apply_type == 2 || apply_type == 5) {
				// 2.如果是依旧换新或丢失遗失，将计算机台账中的旧key号更换为新key号，由于台账中可能有多个key，需要查找和拼接
				if (key_code.contains(operation.getOld_content())) {
					Integer startIndex = key_code.indexOf(operation.getOld_content());
					Integer endIndex = startIndex + operation.getOld_content().length() - 1;
					String first = key_code.substring(0, startIndex);
					String last = key_code.substring(endIndex + 1);
					key_code = first + operation.getNew_content() + last;
				} else {
					return "旧key号不存在";
				}
			} else if (apply_type == 3) {
				// 3.如果是退回，从台账的key中查到到此key编号，去掉就可
				if (key_code.contains(operation.getOld_content())) {
					Integer startIndex = key_code.indexOf(operation.getOld_content().charAt(0));
					Integer endIndex = startIndex + operation.getOld_content().length() - 1;
					String first = key_code.substring(0, startIndex);
					String last = key_code.substring(endIndex + 1);
					key_code = first + last;
				} else {
					return "旧key号不存在";
				}
			}
			map.put("key_code", key_code);
			ComputerMapper.updateComputerByEvent(map);
		} else if (event.getEvent_type() == 9) {// 开通端口
			EntityDeviceOperation edo = getDeviceOperationDataByEventCode(event.getEvent_code());
			if (edo != null) {
				map.put("output_point", edo.getOperation_content());
			}

			ComputerMapper.updateComputerByEvent(map);
		} else if (event.getEvent_type() == 11) {// 笔记本变更
			types = 1;
			if (event_device_content != null) {
				map.put("seclv_code", event_device_content.getSeclv_code());
				map.put("duty_user_id", event_device_content.getDuty_user_id());
				map.put("duty_dept_id", event_device_content.getDuty_dept_id());
				map.put("storage_location", event_device_content.getStorage_location());
				map.put("detail", event_device_content.getSumm());
			}
			map.put("book_status", "1");
			ComputerMapper.updateEntityBookByEvent(map);
		} else if (event.getEvent_type() == 12) {// 笔记本维修
			types = 1;
			// EntityDeviceOperation edo = getDeviceOperationDataByEventCode(event.getEvent_code());
			map.put("book_status", "1");
			ComputerMapper.updateEntityBookByEvent(map);
		} else if (event.getEvent_type() == 13) {// 笔记本报废
			types = 1;
			map.put("book_status", "4");
			ComputerMapper.updateEntityBookByEvent(map);
		} else if (event.getEvent_type() == 14) {// 笔记本重装系统
			types = 1;
			// EntityDeviceOperation edo = getDeviceOperationDataByEventCode(event.getEvent_code());
			map.put("book_status", "1");
			ComputerMapper.updateEntityBookByEvent(map);
		}

		String dept_name = userService.getDeptNameByUserId(user_id);

		// 生成载体生命周期记录
		BMCycleItem cycleitem = new BMCycleItem();
		cycleitem.setBarcode(event.getBarcode());
		if (types == 1) {
			cycleitem.setEntity_type("BOOK");
		} else {
			cycleitem.setEntity_type("COMPUTER");
		}
		cycleitem.setOper_time(new Date());
		cycleitem.setUser_name(user_name);
		cycleitem.setDept_name(dept_name);
		if (event.getEvent_type() == 3 || event.getEvent_type() == 11) {
			cycleitem.setOper("CHANGE");
		} else if (event.getEvent_type() == 4 || event.getEvent_type() == 13) {
			cycleitem.setOper("DESTROY");
		} else if (event.getEvent_type() == 5 || event.getEvent_type() == 12) {
			cycleitem.setOper("REPAIR");
		} else if (event.getEvent_type() == 6 || event.getEvent_type() == 14) {
			cycleitem.setOper("REINSTALL");
		} else if (event.getEvent_type() == 7) {
			cycleitem.setOper("QUITINTERNET");
		} else if (event.getEvent_type() == 8) {
			cycleitem.setOper("USBKEY");
		} else if (event.getEvent_type() == 9) {
			cycleitem.setOper("OPENPORT");
		} else if (event.getEvent_type() == 10) {
			cycleitem.setOper("LOCALPRINTER");
		}
		cycleitem.setJob_code(event.getJob_code());
		securityUserMapper.addCycleItem(cycleitem);

		return null;

	}

	@Override
	public void addBarcodeInEvent(String event_code, String barcode) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("event_code", event_code);
		map.put("barcode", barcode);
		ComputerMapper.addBarcodeInEvent(map);
	}

	@Override
	public void addComputerByEvent(ChangeDeviceEvent event, String computer_barcode, String user_name, String user_id)
			throws ParseException {
		EntityComputer computer = null;
		EntityEventDevice event_device_content = getEntityEventDeviceByEventCode(event.getEvent_code());
		// SimpleDateFormat transfer = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		/* 生成保密编号--郭姣 */
		String confcode = "";
		try {
			confcode = createSecretSerial("1", event_device_content.getMed_type(), "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (event.getEvent_type() == 1) {// 新增计算机网络机

			EntityDeviceOperation edo = getDeviceOperationDataByEventCode(event.getEvent_code());
			EntityComputer computer_temp = new EntityComputer(computer_barcode,
					event_device_content.getComputer_name(), "", "", "", edo.getHdisk_no(),
					event_device_content.getComputer_code(), confcode, event_device_content.getSeclv_code(),
					event_device_content.getDuty_user_id(), event_device_content.getDuty_dept_id(),
					event_device_content.getDuty_entp_id(), edo.getComputer_os(), edo.getInstall_time(),
					edo.getKey_code(), edo.getComputer_ip(), edo.getComputer_mac(),
					event_device_content.getOutput_point(), edo.getSwitch_num(), edo.getSwitch_point(),
					event_device_content.getInternet_type(), event_device_content.getStorage_area(),
					event_device_content.getStorage_location(), 1, "", null, event_device_content.getSumm(), "", "",
					edo.getMark_code(), edo.getVlan_num(), edo.getComputer_gateway(),
					event_device_content.getMed_type(), edo.getSoftware_type(), edo.getSoftware_summ(),
					event_device_content.getOldconf_code());
			computer = computer_temp;

		} else if (event.getEvent_type() == 2) {// 新增计算机单机

			EntityComputer computer_temp = new EntityComputer(computer_barcode,
					event_device_content.getComputer_name(), "", "", "", event_device_content.getHdisk_no(),
					event_device_content.getComputer_code(), confcode, event_device_content.getSeclv_code(),
					event_device_content.getDuty_user_id(), event_device_content.getDuty_dept_id(),
					event_device_content.getDuty_entp_id(), event_device_content.getComputer_os(),
					event_device_content.getInstall_time(), "", "", "", null, "", "", 6,
					event_device_content.getStorage_area(), event_device_content.getStorage_location(), 1, "", null,
					event_device_content.getSumm(), "", "", "", "", "", event_device_content.getMed_type(), "", "",
					event_device_content.getOldconf_code());

			computer = computer_temp;
		}

		String dept_name = userService.getDeptNameByUserId(user_id);

		// 生成载体生命周期记录
		BMCycleItem cycleitem = new BMCycleItem();
		cycleitem.setBarcode(computer_barcode);
		cycleitem.setEntity_type("SECPLACE");
		cycleitem.setOper_time(new Date());
		cycleitem.setUser_name(user_name);
		cycleitem.setDept_name(dept_name);
		cycleitem.setOper("ADD");
		cycleitem.setJob_code(event.getJob_code());

		addEntityComputer(computer, cycleitem);

	}

	@Override
	public List<ChangeDeviceEvent> getComputerEventList(Map<String, Object> map) {
		logger.debug("getComputerEventList:");
		return ComputerMapper.getComputerEventList(map);
	}

	@Override
	public String getChangeDeviceEventCodeByJobCode(String job_code) {
		logger.debug("getChangeDeviceEventCodeByJobCode" + job_code);
		return ComputerMapper.getChangeDeviceEventCodeByJobCode(job_code);
	}

	@Override
	public ChangeDeviceEvent getChangeDeviceEventByEventCode(String event_code) {
		logger.debug("getChangeDeviceEventByEventCode:" + event_code);
		return ComputerMapper.getChangeDeviceEventByEventCode(event_code);
	}

	@Override
	public ChangeDeviceEvent getChangeDeviceEventByJobCode(String job_code) {
		logger.debug("getChangeDeviceEventByJobCode:" + job_code);
		return ComputerMapper.getChangeDeviceEventByJobCode(job_code);
	}

	@Override
	public void deleteEvent(String event_code) {
		logger.debug("deleteEvent" + event_code);
		String job_code = ComputerMapper.getChangeDeviceEventJobCodeByEventCode(event_code);
		int type = 0;
		ChangeDeviceEvent event = ComputerMapper.getChangeDeviceEventByEventCode(event_code);
		if (event.getEvent_type() == 1) {
			basicService.cancelJob(job_code, JobTypeEnum.EVENT_INTCOM.getJobTypeCode());
		} else if (event.getEvent_type() == 2) {
			basicService.cancelJob(job_code, JobTypeEnum.EVENT_SINCOM.getJobTypeCode());
		} else if (event.getEvent_type() == 3) {
			basicService.cancelJob(job_code, JobTypeEnum.EVENT_CHGCOM.getJobTypeCode());
		} else if (event.getEvent_type() == 4) {
			basicService.cancelJob(job_code, JobTypeEnum.EVENT_DESCOM.getJobTypeCode());
		} else if (event.getEvent_type() == 5) {
			basicService.cancelJob(job_code, JobTypeEnum.EVENT_REPCOM.getJobTypeCode());
		} else if (event.getEvent_type() == 6) {
			basicService.cancelJob(job_code, JobTypeEnum.EVENT_REINSTALL.getJobTypeCode());
		} else if (event.getEvent_type() == 7) {
			basicService.cancelJob(job_code, JobTypeEnum.EVENT_QUITINT.getJobTypeCode());
		} else if (event.getEvent_type() == 8) {
			basicService.cancelJob(job_code, JobTypeEnum.EVENT_USBKEY.getJobTypeCode());
		} else if (event.getEvent_type() == 9) {
			basicService.cancelJob(job_code, JobTypeEnum.EVENT_OPENPORT.getJobTypeCode());
		} else if (event.getEvent_type() == 10) {
			basicService.cancelJob(job_code, JobTypeEnum.EVENT_LOCALPRINTER.getJobTypeCode());
		} else if (event.getEvent_type() == 11) {
			type = 1;
			basicService.cancelJob(job_code, JobTypeEnum.BOOK_CHANGE.getJobTypeCode());
		} else if (event.getEvent_type() == 12) {
			type = 1;
			basicService.cancelJob(job_code, JobTypeEnum.BOOK_REPAIR.getJobTypeCode());
		} else if (event.getEvent_type() == 13) {
			type = 1;
			basicService.cancelJob(job_code, JobTypeEnum.BOOK_DES.getJobTypeCode());
		} else if (event.getEvent_type() == 14) {
			type = 1;
			basicService.cancelJob(job_code, JobTypeEnum.BOOK_REINSTALL.getJobTypeCode());
		}
		ComputerMapper.deleteChangeDeviceEvent(event_code);

		Map<String, Object> map = new HashMap<String, Object>();
		if (type == 1) {
			// 笔记本状态更新为“在用”
			map.put("book_barcode", event.getBarcode());
			map.put("book_status", "1");
			ComputerMapper.updateEntityBookByEvent(map);
		} else {
			// 计算机台账状态均置为“在用”
			if (!event.getBarcode().equals("")) {
				map.put("computer_barcode", event.getBarcode());
				map.put("computer_status", "1");
				ComputerMapper.updateComputerByEvent(map);
			}
		}
	}

	@Override
	public Integer getAllComputerSize(Map<String, Object> map) throws Exception {
		logger.debug("getAllComputerSize:");
		return ComputerMapper.getAllComputerSize(map);
	}

	@Override
	public List<InfoType> getInfoTypeList(Map<String, Object> map) {
		logger.debug("getInfoTypeList");
		return ComputerMapper.getInfoTypeList(map);
	}

	@Override
	public void addInfoDevice(EntityInfoDevice device, BMCycleItem cycleitem) {
		logger.debug("addInfoDevice");
		ComputerMapper.addInfoDevice(device);
		securityUserMapper.addCycleItem(cycleitem);
	}

	@Override
	public Integer getInfoIDByName(Map<String, Object> map) {
		logger.debug("getInfoIDByName");
		return ComputerMapper.getInfoIDByName(map);
	}

	/** 信息设备模块 **/

	@Override
	public List<EntityInfoDevice> getInfoDeviceList(Map<String, Object> map) {
		logger.debug("getInfoDeviceList");
		return ComputerMapper.getInfoDeviceList(map);
	}

	@Override
	public EntityInfoDevice getInfoDeviceByBarcode(String device_barcode) {
		logger.debug("getInfoDeviceByBarcode:");
		return ComputerMapper.getInfoDeviceByBarcode(device_barcode);
	}

	@Override
	public void addInfoDeviceEvent(InfoDeviceEvent event, String next_approver) throws Exception {
		logger.debug("addInfoDeviceEvent" + event.getEvent_code());
		ComputerMapper.addInfoDeviceEvent(event);
		// 变更、报废，更改信息设备台账状态
		if (event != null && (event.getEvent_type() == 3 || event.getEvent_type() == 2)) {
			int status = 0;
			if (event.getEvent_type() == 3) {
				// 申请报废
				status = 8;
			} else if (event.getEvent_type() == 2) {
				// 申请变更
				status = 7;
			}

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("device_barcode", event.getDevice_barcode());
			map.put("device_statues", status);
			ComputerMapper.updateInfoDeviceByBarcode(map);
		}

		ApproveProcess process = basicPrcManage
				.getApproveProcessByKey(event.getDept_id(), String.valueOf(event.getSeclv_code()), event.getJobType()
						.getJobTypeCode(), event.getUsage_code(), true);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = basicPrcManage.getApproverName(next_approver);
		String job_code = event.getUser_iidd() + "-" + event.getJobType() + "-" + System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, event.getUser_iidd(), event.getDept_id(), event.getSeclv_code(),
				event.getJobType(), new Date(), job_status, next_approver, next_approver_name, process.getProcess_id());
		job.setComment(event.getSumm());
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		Map<String, String> map = new HashMap<String, String>();
		map.put("event_code", event.getEvent_code());
		map.put("job_code", job_code);
		// 更新job_code字段
		ComputerMapper.updateInfoDeviceEventJobCode(map);

		if (process.getTotal_steps() == 0) {
			// 若该流程没有审批，直接结束。暂时空缺
		}
		// 更新作业信息
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + event.getJobType().getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有" + event.getJobType().getJobTypeName() + "作业需要您审批";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, job.getJobType().getJobTypeCode(), 1,
						job.getJob_code(), message, new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	@Override
	public List<InfoDeviceEvent> getInfoDeviceEventList(Map<String, Object> map) {
		logger.debug("getInfoDeviceEventList");
		return ComputerMapper.getInfoDeviceEventList(map);
	}

	@Override
	public void delInfoDeviceEvent(String event_code) {
		logger.debug("delInfoDeviceEvent " + event_code);

		InfoDeviceEvent event = getInfoDeviceEventByEventCode(event_code);
		// 信息设备台账状态均置为“在用”
		if (!event.getDevice_barcode().equals("")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("device_barcode", event.getDevice_barcode());
			map.put("device_statues", "1");
			ComputerMapper.updateInfoDeviceByBarcode(map);
		}

		// 判断流程类型撤销流程任务
		if (event.getJob_code().contains("INFO_DEVICE")) {
			basicService.cancelJob(event.getJob_code(), JobTypeEnum.INFO_DEVICE.getJobTypeCode());
		} else if (event.getJob_code().contains("INFO_OTHER")) {
			basicService.cancelJob(event.getJob_code(), JobTypeEnum.INFO_OTHER.getJobTypeCode());
		} else if (event.getJob_code().contains("DEVICE_CHANGE")) {
			basicService.cancelJob(event.getJob_code(), JobTypeEnum.DEVICE_CHANGE.getJobTypeCode());
		} else if (event.getJob_code().contains("CHANGE_OTHER")) {
			basicService.cancelJob(event.getJob_code(), JobTypeEnum.CHANGE_OTHER.getJobTypeCode());
		} else if (event.getJob_code().contains("DEVICE_DES")) {
			basicService.cancelJob(event.getJob_code(), JobTypeEnum.DEVICE_DES.getJobTypeCode());
		}
		ComputerMapper.delInfoDeviceEvent(event_code);
	}

	@Override
	public InfoDeviceEvent getInfoDeviceEventByEventCode(String event_code) {
		logger.debug("getInfoDeviceEventByEventCode:" + event_code);
		return ComputerMapper.getInfoDeviceEventByEventCode(event_code);
	}

	@Override
	public String getInfoDeviceJobCodeByEventCode(String event_code) {
		logger.debug("getInfoDeviceJobCodeByEventCode:" + event_code);
		return ComputerMapper.getInfoDeviceJobCodeByEventCode(event_code);
	}

	@Override
	public List<InfoDeviceEvent> getInfoDeviceEventListByJobCode(String job_code) {
		logger.debug("getInfoDeviceEventListByJobCode:" + job_code);
		return ComputerMapper.getInfoDeviceEventListByJobCode(job_code);
	}

	@Override
	public void addInfoDeviceByEvent(InfoDeviceEvent event) {
		logger.debug("addInfoDeviceByEvent:" + event);
		ComputerMapper.addInfoDeviceByEvent(event);

		BMCycleItem cycleitem = new BMCycleItem();
		cycleitem.setBarcode(event.getDevice_barcode());
		cycleitem.setEntity_type("DEVICE");
		cycleitem.setOper_time(new Date());
		cycleitem.setUser_name(event.getUser_name());
		cycleitem.setDept_name(event.getDept_name());
		cycleitem.setOper("ADD");
		cycleitem.setJob_code(event.getJob_code());
		securityUserMapper.addCycleItem(cycleitem);

	}

	@Override
	public void updateInfoDeviceChangeByEvent(String job_code) {
		logger.debug("updateInfoDeviceChangeByEvent:" + job_code);
		List<InfoDeviceEvent> eventlist = ComputerMapper.getInfoDeviceEventListByJobCode(job_code);
		InfoDeviceEvent event = eventlist.get(0);
		ComputerMapper.updateInfoDeviceChangeByEvent(event);

		BMCycleItem cycleitem = new BMCycleItem();
		cycleitem.setBarcode(event.getDevice_barcode());
		cycleitem.setEntity_type("DEVICE");
		cycleitem.setOper_time(new Date());
		cycleitem.setUser_name(event.getUser_name());
		cycleitem.setDept_name(event.getDept_name());
		cycleitem.setOper("CHANGE");
		cycleitem.setJob_code(event.getJob_code());
		securityUserMapper.addCycleItem(cycleitem);

		// 信息设备台账状态均置为“在用”
		if (!event.getDevice_barcode().equals("")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("device_barcode", event.getDevice_barcode());
			map.put("device_statues", "1");
			ComputerMapper.updateInfoDeviceByBarcode(map);
		}
	}

	@Override
	public void updateInfoDeviceByEntity(EntityInfoDevice info) {
		logger.debug("updateInfoDeviceByEntity");
		ComputerMapper.updateInfoDeviceByEntity(info);
	}

	@Override
	public void updateInfoDeviceDestroyByEvent(String job_code) {
		logger.debug("updateInfoDeviceDestroyByEvent:" + job_code);
		List<InfoDeviceEvent> eventlist = ComputerMapper.getInfoDeviceEventListByJobCode(job_code);
		InfoDeviceEvent event = eventlist.get(0);
		ComputerMapper.updateInfoDeviceDestroyByEvent(event.getDevice_barcode());
		BMCycleItem cycleitem = new BMCycleItem();
		cycleitem.setBarcode(event.getDevice_barcode());
		cycleitem.setEntity_type("DEVICE");
		cycleitem.setOper_time(new Date());
		cycleitem.setUser_name(event.getUser_name());
		cycleitem.setDept_name(event.getDept_name());
		cycleitem.setOper("DESTROY");
		cycleitem.setJob_code(event.getJob_code());
		securityUserMapper.addCycleItem(cycleitem);
	}

	public String getSerialNum(String device_type) {
		String num = ComputerMapper.getSerialNum(device_type);
		if (!num.equals("")) {
			ComputerMapper.updateSerialNum(device_type);
		}
		String prefix = "0000";
		return prefix.substring(num.length()).concat(num);
	}

	@Override
	public String createSecretSerial(String device_type, String type, String dept_id) throws Exception {
		logger.debug("createSecretSerial");
		String curYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		String num = getSerialNum(device_type);

		if (device_type.equals("6")) {
			String dept = "";
			String type1 = "";
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("info_id", type);
			InfoType typename = ComputerMapper.getInfoTypeByID(map);
			if (typename != null) {
				if (typename.getInfo_type().equals("优盘")) {
					type1 = "U";
				} else if (typename.getInfo_type().equals("光盘")) {
					type1 = "G";
				} else if (typename.getInfo_type().equals("移动硬盘")) {
					type1 = "Y";
				} else {
					type1 = "Q";
				}
			}

			// 根据dept_id获取部门表中的dept_cs与声光电公司dept_cs进行匹配
			SecDept temp = userService.getSecDeptById(dept_id);
			String deptnew = temp.getDept_cs();
			// 介质类编号：单位+类型+年份+流水号
			if (deptnew.startsWith(BMSysConfigItem.SGDGC_CODE)) {
				dept = "A";
			} else if (deptnew.startsWith(BMSysConfigItem.ERSI_CODE)) {
				dept = "B";
			} else if (deptnew.startsWith(BMSysConfigItem.ERLIU_CODE)) {
				dept = "C";
			} else if (deptnew.startsWith(BMSysConfigItem.SISI_CODE)) {
				dept = "D";
			}

			return dept + type1 + curYear.substring(2) + num;
		} else {
			// 信息设备编号：年份+类型+小类型+流水号
			return curYear.substring(2) + device_type + type.substring(1) + num;
		}
	}

	@Override
	public boolean isTypeExistByName(String typename) {
		logger.debug("isTypeExistByName");
		return ComputerMapper.getInfoTypeCountByName(typename) > 0;
	}

	@Override
	public String getNextDeviceTypeId(String id) {
		logger.debug("getNextDeviceTypeId");
		return ComputerMapper.getNextDeviceTypeId(id);
	}

	@Override
	public void addInfoDeviceType(InfoType type) {
		logger.debug("addInfoDeviceType");
		ComputerMapper.addInfoDeviceType(type);
	}

	@Override
	public void updateInfoType(InfoType type) {
		logger.debug("updateInfoType");
		ComputerMapper.updateInfoType(type);
	}

	@Override
	public InfoType getInfoTypeByID(Map<String, Object> map) {
		logger.debug("getInfoTypeByID");
		return ComputerMapper.getInfoTypeByID(map);
	}

	@Override
	public void deleteEntityComputer(String computer_barcode) {
		logger.debug("deleteEntityComputer:" + computer_barcode);
		ComputerMapper.deleteEntityComputer(computer_barcode);
	}

	@Override
	public EntityDeviceOperation getDeviceOperationDataByEventCode(String event_code) {
		logger.debug("getDeviceOperationDataByEventCode:" + event_code);
		return ComputerMapper.getDeviceOperationDataByEventCode(event_code);
	}

	@Override
	public void addRefDeviceOperation(EntityDeviceOperation operation) {
		logger.debug("addRefDeviceOperation:");
		ComputerMapper.addDeviceOperation(operation);
	}

	@Override
	public void updateChangeDeviceEvent(Map<String, String> map) {
		logger.debug("updateChangeDeviceEvent:");
		ComputerMapper.updateChangeDeviceEvent(map);
	}

	@Override
	public String getChangeDeviceEventJobCodeByEventCode(String event_code) {
		logger.debug("getChangeDeviceEventJobCodeByEventCode:" + event_code);
		return ComputerMapper.getChangeDeviceEventJobCodeByEventCode(event_code);
	}

	@Override
	public void addInfoDeviceTemp(EntityDeviceTemp temp) {
		logger.debug("addInfoDeviceTemp");
		ComputerMapper.addInfoDeviceTemp(temp);
	}

	@Override
	public EntityDeviceTemp getDeviceTempByEventCode(String event_code) {
		logger.debug("getDeviceTempByEventCode");
		return ComputerMapper.getDeviceTempByEventCode(event_code);
	}

	@Override
	public void updateDeviceEntityByTemp(String event_code) {
		logger.debug("updateDeviceEntityByTemp");
		ComputerMapper.updateDeviceEntityByTemp(event_code);
	}

	@Override
	public void updateDeviceTemp(EntityDeviceTemp temp) {
		logger.debug("updateDeviceTemp");
		ComputerMapper.updateDeviceTemp(temp);
	}

	@Override
	public void addBorrowBookEvent(BorrowBookEvent event, String next_approver) throws Exception {
		logger.debug("addBorrowBookEvent" + event.getEvent_code());
		ComputerMapper.addBorrowBookEvent(event);
		ApproveProcess process = basicPrcManage
				.getApproveProcessByKey(event.getDept_id(), String.valueOf(event.getSeclv_code()), event.getJobType()
						.getJobTypeCode(), event.getUsage_code(), true);
		String job_status = null;
		if (process.getTotal_steps() == 0) {
			job_status = ActivitiCons.APPLY_APPROVED_PASS;
		} else {
			job_status = ActivitiCons.APPLY_APPROVED_DEFAULT;
		}
		String next_approver_name = basicPrcManage.getApproverName(next_approver);
		String job_code = event.getUser_iidd() + "-" + event.getJobType() + "-" + System.currentTimeMillis();
		ProcessJob job = new ProcessJob(job_code, event.getUser_iidd(), event.getDept_id(), event.getSeclv_code(),
				event.getJobType(), new Date(), job_status, next_approver, next_approver_name, process.getProcess_id());
		job.setComment(event.getSumm());
		// 开启流程
		basicPrcManage.addActivitiApply(job, process);
		// 把任务信息插入数据库
		basicMapper.addProcessJob(job);
		Map<String, String> map = new HashMap<String, String>();
		map.put("event_code", event.getEvent_code());
		map.put("job_code", job_code);
		// 更新job_code字段
		ComputerMapper.updateBorrowBookEventJobCode(map);

		// 更新作业信息
		String user_name = userService.getUserNameByUserId(job.getUser_iidd());
		String dept_name = userService.getDeptNameByDeptId(job.getDept_id());
		String detail = "提交" + event.getJobType().getJobTypeName() + "申请";
		ProcessRecord record = new ProcessRecord(job.getJob_code(), job.getJobType(), job.getUser_iidd(), user_name,
				dept_name, detail, "请审批", new Date());
		activitiService.addProcessRecord(record);
		// 如果有审批流程，在消息表中添加审批消息
		if (process.getTotal_steps() != 0) {
			String message = dept_name + user_name + "有" + event.getJobType().getJobTypeName() + "作业需要您审批";
			for (String item : next_approver.split(",")) {
				String nextApproverName = basicPrcManage.getApproverName(item);
				ClientMsg clientMsg = new ClientMsg(item, nextApproverName, job.getJobType().getJobTypeCode(), 1,
						job.getJob_code(), message, new Date(), 0);
				basicMapper.addClientMsg(clientMsg);
			}
		}
	}

	@Override
	public List<BorrowBookEvent> getBorrowBookEventList(Map<String, Object> map) {
		logger.debug("getBorrowBookEventList");
		return ComputerMapper.getBorrowBookEventList(map);
	}

	@Override
	public List<BorrowBookEvent> getBorrowBookEventListByJobCode(String job_code) {
		logger.debug("getBorrowBookEventListByJobCode");
		return ComputerMapper.getBorrowBookEventListByJobCode(job_code);
	}

	@Override
	public void deleteNetworkDevice(String device_barcode) {
		logger.debug("deleteNetworkDevice");
		ComputerMapper.deleteNetworkDevice(device_barcode);
	}

	@Override
	public void addEntityEventDevice(EntityEventDevice event_device_content) {
		logger.debug("addEntityEventDevice");
		ComputerMapper.addEntityEventDevice(event_device_content);
	}

	@Override
	public void addBookEntity(EntityBook book, BMCycleItem cycleitem) {
		logger.debug("addBookEntity:" + book.getBook_barcode());
		ComputerMapper.addBookEntity(book);
		securityUserMapper.addCycleItem(cycleitem);
	}

	@Override
	public List<EntityBook> getBookList(Map<String, Object> map) {
		logger.debug("getBookList");
		return ComputerMapper.getBookList(map);
	}

	@Override
	public List<EntityBook> getALLBookList(Map<String, Object> map, RowBounds rbs) throws Exception {
		logger.debug("getALLBookList");
		return ComputerMapper.getALLBookList(map, rbs);
	}

	@Override
	public void updateEntityBook(EntityBook book) {
		logger.debug("updateEntityBook");
		ComputerMapper.updateEntityBook(book);
	}

	@Override
	public void deleteEntityBook(String book_barcode) {
		logger.debug("deleteEntityBook");
		ComputerMapper.deleteEntityBook(book_barcode);
	}

	@Override
	public Integer getAllBookSize(Map<String, Object> map) throws Exception {
		logger.debug("getAllBookSize:");
		return ComputerMapper.getAllBookSize(map);
	}

	@Override
	public Integer getAllInfoDeviceSize(Map<String, Object> map) throws Exception {
		logger.debug("getAllInfoDeviceSize:");
		return ComputerMapper.getAllInfoDeviceSize(map);
	}

	@Override
	public void updateBorrowBookAssociate(Map<String, Object> map) {
		logger.debug("updateBorrowBookAssociate:");
		ComputerMapper.updateBorrowBookAssociate(map);
	}

	@Override
	public EntityEventDevice getEntityEventDeviceByEventCode(String event_code) {
		logger.debug("getEntityEventDeviceByEventCode:");
		return ComputerMapper.getEntityEventDeviceByEventCode(event_code);
	}

	@Override
	public void updateEntityEventDevice(EntityEventDevice event_device_content) {
		logger.debug("updateEntityEventDevice:");
		ComputerMapper.updateEntityEventDevice(event_device_content);

	}

	@Override
	public void deleteEntityInfoDevice(String device_barcode) {
		logger.debug("deleteEntityInfoDevice");
		ComputerMapper.deleteEntityInfoDevice(device_barcode);
	}

	@Override
	public void updateComputerByEvent(Map<String, Object> map) {
		logger.debug("updateComputerByEvent");
		ComputerMapper.updateComputerByEvent(map);
	}

	@Override
	public void updateInfoDeviceByBarcode(Map<String, Object> map) {
		logger.debug("updateInfoDeviceByBarcode");
		ComputerMapper.updateInfoDeviceByBarcode(map);
	}

	@Override
	public Integer getAllHardDiskSize(Map<String, Object> map) throws Exception {
		logger.debug("getAllHardDiskSize:");
		return ComputerMapper.getAllHardDiskSize(map);
	}

	@Override
	public List<EntityHardDisk> getAllHardDiskList(Map<String, Object> map) {
		logger.debug("getBookList");
		return ComputerMapper.getAllHardDiskList(map);
	}

	@Override
	public void deleteBorrowBookEventByEventCode(String event_code) {
		logger.debug("deleteBorrowBookEventByEventCode" + event_code);
		String job_code = ComputerMapper.getBorrowBookJobCodeByEventCode(event_code);
		if (job_code.contains("BORROW_BOOKOUT")) {
			basicService.cancelJob(job_code, JobTypeEnum.BORROW_BOOKOUT.getJobTypeCode());
		} else {
			basicService.cancelJob(job_code, JobTypeEnum.BORROW_BOOK.getJobTypeCode());
		}

		ComputerMapper.deleteBorrowBookEventByEventCode(event_code);
	}

	@Override
	public EntityHardDisk getHardDiskByMap(Map<String, Object> map) {
		logger.debug("getHardDiskByMap:");
		return ComputerMapper.getHardDiskByMap(map);
	}

	@Override
	public void updateHardDisk(EntityHardDisk hdisk) {
		logger.debug("updateComputer:" + hdisk.getHdisk_no());
		ComputerMapper.updateHardDisk(hdisk);
	}

	@Override
	public void deleteEntityHDisk(String hdisk_no) {
		logger.debug("deleteEntityHDisk:" + hdisk_no);
		ComputerMapper.deleteEntityHDisk(hdisk_no);
	}

	@Override
	public List<EntityHardDisk> getHardDiskList(Map<String, Object> map) {
		logger.debug("getAllHardDiskList");
		return ComputerMapper.getHardDiskList(map);
	}

	@Override
	public void addEntityHardDisk(EntityHardDisk disk) {
		logger.debug("addEntityHardDisk");
		ComputerMapper.addEntityHardDisk(disk);
	}

	@Override
	public String getComputerNumByMap(Map<String, Object> map) {
		logger.debug("getComputerNumByMap");
		return ComputerMapper.getComputerNumByMap(map);
	}

	@Override
	public String getInfoDeviceNumByMap(Map<String, Object> map) {
		logger.debug("getInfoDeviceNumByMap");
		return ComputerMapper.getInfoDeviceNumByMap(map);
	}

	@Override
	public String getMediaNumByMap(Map<String, Object> map) {
		logger.debug("getMediaNumByMap");
		return ComputerMapper.getMediaNumByMap(map);
	}

	@Override
	public void updateEntityBookByEvent(Map<String, Object> map) {
		logger.debug("updateEntityBookByEvent");
		ComputerMapper.updateEntityBookByEvent(map);
	}
}