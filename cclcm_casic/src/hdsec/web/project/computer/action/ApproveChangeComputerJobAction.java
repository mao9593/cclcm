package hdsec.web.project.computer.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.computer.model.ChangeDeviceEvent;
import hdsec.web.project.computer.model.EntityComputer;
import hdsec.web.project.computer.model.EntityEventDevice;
import hdsec.web.project.computer.model.EntityHardDisk;
import hdsec.web.project.computer.model.InfoType;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.util.StringUtils;

public class ApproveChangeComputerJobAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	// private List<ChangeDeviceEvent> eventList = null;
	private String approved = "";
	private String opinion = "";
	private String next_approver = "";
	private List<ApproverUser> userList = null;
	// private String jobType = "";
	private EntityComputer computer = null;
	private EntityComputer computer_old = null;
	private String history = "";
	// private String hdisk_no = "";
	// private String computer_mac = "";
	// private String computer_os = "";
	// private Date install_time = null;
	private String key_code = "";
	private String computer_ip = "";
	private String switch_num = "";
	private String switch_point = "";
	private String software_type = "";// 软件安装
	private String software_summ = "";
	private String mark_code = "";
	private String vlan_num = "";
	private String computer_gateway = "";
	// private String med_type = "";
	// private String event_content = "";
	// private String event_code = "";
	private Integer listSize = null;
	private String opinion_all = "";
	private ChangeDeviceEvent event = null;
	private String isrecycle = "";
	private String content = "";
	private String isnewdisk = "";
	private String newdisk = "";

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public ProcessJob getJob() {
		return job;
	}

	public void setJob(ProcessJob job) {
		this.job = job;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public void setProcess(ApproveProcess process) {
		this.process = process;
	}

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<ProcessRecord> recordList) {
		this.recordList = recordList;
	}

	public String getApproved() {
		return approved;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public List<ApproverUser> getUserList() {
		return userList;
	}

	public void setUserList(List<ApproverUser> userList) {
		this.userList = userList;
	}

	public EntityComputer getComputer() {
		return computer;
	}

	public void setComputer(EntityComputer computer) {
		this.computer = computer;
	}

	public EntityComputer getComputer_old() {
		return computer_old;
	}

	public void setComputer_old(EntityComputer computer_old) {
		this.computer_old = computer_old;
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getKey_code() {
		return key_code;
	}

	public void setKey_code(String key_code) {
		this.key_code = key_code;
	}

	public String getComputer_ip() {
		return computer_ip;
	}

	public void setComputer_ip(String computer_ip) {
		this.computer_ip = computer_ip;
	}

	public String getSwitch_num() {
		return switch_num;
	}

	public void setSwitch_num(String switch_num) {
		this.switch_num = switch_num;
	}

	public String getSwitch_point() {
		return switch_point;
	}

	public void setSwitch_point(String switch_point) {
		this.switch_point = switch_point;
	}

	public String getSoftware_type() {
		return software_type;
	}

	public void setSoftware_type(String software_type) {
		this.software_type = software_type;
	}

	public String getSoftware_summ() {
		return software_summ;
	}

	public void setSoftware_summ(String software_summ) {
		this.software_summ = software_summ;
	}

	public String getMark_code() {
		return mark_code;
	}

	public void setMark_code(String mark_code) {
		this.mark_code = mark_code;
	}

	public String getVlan_num() {
		return vlan_num;
	}

	public void setVlan_num(String vlan_num) {
		this.vlan_num = vlan_num;
	}

	public String getComputer_gateway() {
		return computer_gateway;
	}

	public void setComputer_gateway(String computer_gateway) {
		this.computer_gateway = computer_gateway;
	}

	public Integer getListSize() {
		return listSize;
	}

	public void setListSize(Integer listSize) {
		this.listSize = listSize;
	}

	public String getOpinion_all() {
		return opinion_all;
	}

	public void setOpinion_all(String opinion_all) {
		this.opinion_all = opinion_all;
	}

	public ChangeDeviceEvent getEvent() {
		return event;
	}

	public void setEvent(ChangeDeviceEvent event) {
		this.event = event;
	}

	public String getIsrecycle() {
		return isrecycle;
	}

	public void setIsrecycle(String isrecycle) {
		this.isrecycle = isrecycle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIsnewdisk() {
		return isnewdisk;
	}

	public void setIsnewdisk(String isnewdisk) {
		this.isnewdisk = isnewdisk;
	}

	public String getNewdisk() {
		return newdisk;
	}

	public void setNewdisk(String newdisk) {
		this.newdisk = newdisk;
	}

	protected void getComputerInfoSpecial(ChangeDeviceEvent event1) {

		listSize = recordList.size() - 1;
		for (int i = 1; i <= listSize; i++) {
			opinion_all = opinion_all + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion() + "<br>审批人："
					+ recordList.get(i).getUser_name() + "<br>审批时间：" + recordList.get(i).getOp_time_str();
		}

		EntityEventDevice event_device_content = computerService
				.getEntityEventDeviceByEventCode(event1.getEvent_code());

		// 计算机变更

		// computer_change组成：|计算机条码|密级代码|责任人ID|责任部门ID|责任单位ID|保密编号|硬盘序列号|IP|MAC|存放区域|存放位置|others|end|
		// String computer_barcode = "";
		Integer seclv_code = null;
		String duty_user_name = "";
		String duty_dept_name = "";
		List<String> seclv_name = null;

		if (event_device_content.getSeclv_code() != null) {
			seclv_code = event_device_content.getSeclv_code();
			String seclv_codes = Constants.COMMON_SEPARATOR + event_device_content.getSeclv_code()
					+ Constants.COMMON_SEPARATOR;
			String[] codes = seclv_codes.split("\\|");
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("codes", codes);
			seclv_name = basicService.getSeclvNameByCodes(map);
		}

		duty_user_name = userService.getUserNameByUserId(event_device_content.getDuty_user_id());
		duty_dept_name = userService.getDeptNameByDeptId(event_device_content.getDuty_dept_id());
		// duty_entp_name = userService.getDeptNameByDeptId(event_device_content.getDuty_entp_id());

		if (seclv_code == null) {
			EntityComputer computer_temp = new EntityComputer(event_device_content.getComputer_barcode(), "", null, "",
					event_device_content.getDuty_user_id(), duty_user_name, event_device_content.getDuty_dept_id(),
					duty_dept_name, event_device_content.getDuty_entp_id(), event_device_content.getComputer_code(), 0,
					event_device_content.getHdisk_no(), "", null, event_device_content.getComputer_ip(),
					event_device_content.getComputer_mac(), event_device_content.getStorage_area(),
					event_device_content.getStorage_location(), event_device_content.getSumm(), "", "", null, null,
					event_device_content.getOldconf_code(), event_device_content.getMed_type_name());
			computer = computer_temp;
		} else {
			EntityComputer computer_temp = new EntityComputer(event_device_content.getComputer_barcode(), "",
					event_device_content.getSeclv_code(), seclv_name.get(0), event_device_content.getDuty_user_id(),
					duty_user_name, event_device_content.getDuty_dept_id(), duty_dept_name,
					event_device_content.getDuty_entp_id(), event_device_content.getComputer_code(), 0,
					event_device_content.getHdisk_no(), "", null, event_device_content.getComputer_ip(),
					event_device_content.getComputer_mac(), event_device_content.getStorage_area(),
					event_device_content.getStorage_location(), event_device_content.getSumm(), "", "", null, null,
					event_device_content.getOldconf_code(), event_device_content.getMed_type_name());
			computer = computer_temp;
		}

		Integer old_seclv_code = null;
		String old_duty_user_name = "";
		String old_duty_dept_name = "";
		// String old_duty_entp_name = "";
		List<String> old_seclv_name = null;

		if (event_device_content.getBef_seclv_code() != null) {
			old_seclv_code = event_device_content.getBef_seclv_code();
			String seclv_codess = Constants.COMMON_SEPARATOR + event_device_content.getBef_seclv_code()
					+ Constants.COMMON_SEPARATOR;
			String[] codess = seclv_codess.split("\\|");
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("codes", codess);
			old_seclv_name = basicService.getSeclvNameByCodes(maps);
		}

		old_duty_user_name = userService.getUserNameByUserId(event_device_content.getBef_duty_user_id());
		old_duty_dept_name = userService.getDeptNameByDeptId(event_device_content.getBef_duty_dept_id());
		// old_duty_entp_name = userService.getDeptNameByDeptId(event_device_content.getBef_duty_entp_id());

		Map<String, Object> mapss = new HashMap<String, Object>();
		mapss.put("computer_barcode", event_device_content.getComputer_barcode());
		computer_old = computerService.getComputerByMap(mapss);

		if (old_seclv_code == null) {
			computer_old.setSeclv_code(null);
			computer_old.setSeclv_name("");
		} else {
			computer_old.setSeclv_code(event_device_content.getBef_seclv_code());
			computer_old.setSeclv_name(old_seclv_name.get(0));
		}
		computer_old.setDuty_user_id(event_device_content.getDuty_user_id());
		computer_old.setDuty_user_name(old_duty_user_name);
		computer_old.setDuty_dept_id(event_device_content.getDuty_dept_id());
		computer_old.setDuty_dept_name(old_duty_dept_name);
		computer_old.setDuty_entp_id(event_device_content.getDuty_entp_id());
		computer_old.setConf_code(event_device_content.getBef_conf_code());
		computer_old.setHdisk_no(event_device_content.getBef_hdisk_no());
		computer_old.setComputer_ip(event_device_content.getBef_computer_ip());
		computer_old.setComputer_mac(event_device_content.getBef_computer_mac());
		computer_old.setStorage_area(event_device_content.getBef_storage_area());
		computer_old.setStorage_location(event_device_content.getBef_storage_location());

		if (!computer_old.getKey_code().equals("")) {
			String[] code = computer_old.getKey_code().split(",");
			computer_old.setKey_code(code[0]);
		}
		// return "ok_change";
	}

	/**
	 * 去重
	 * 
	 * @param oriList
	 * @return
	 */
	private List<ApproverUser> removeDuplicateList(List<ApproverUser> oriList) {
		Set<String> set = new HashSet<String>();
		List<ApproverUser> newList = new ArrayList<ApproverUser>();
		for (ApproverUser item : oriList) {
			if (set.add(item.getUser_iidd())) {
				newList.add(item);
			}
		}
		return newList;
	}

	@Override
	public String executeFunction() throws Exception {
		if (history.equals("Y")) {
			job = basicService.getProcessJobByCode(job_code);
			process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
			ProcessRecord record = new ProcessRecord();
			record.setJob_code(job_code);
			recordList = activitiService.getProcessRecordList(record);
			event = computerService.getChangeDeviceEventByJobCode(job_code);
			getComputerInfoSpecial(event);

			return SUCCESS;
		} else {
			if (StringUtils.hasLength(approved)) {// 审批结果

				event = computerService.getChangeDeviceEventByJobCode(job_code);
				// 计算机变更
				if (listSize == 4) {
					String other_opinion = "";
					Map<String, Object> mmp1 = new HashMap<String, Object>();
					if (isnewdisk.equals("是")) {
						other_opinion = "更换硬盘，新硬盘序列号：" + newdisk.trim();
						mmp1.put("operation_content", newdisk.trim());
					} else {
						other_opinion = "不更换硬盘";
					}
					if (!computer_ip.equals("")) {
						other_opinion = other_opinion + "<br>" + "IP地址:" + computer_ip;
					}
					if (!computer_gateway.equals("")) {
						other_opinion = other_opinion + "<br>" + "网关:" + computer_gateway;
					}
					if (!mark_code.equals("")) {
						other_opinion = other_opinion + "<br>" + "掩码:" + mark_code;
					}
					if (!vlan_num.equals("")) {
						other_opinion = other_opinion + "<br>" + "VLAN:" + vlan_num;
					}
					if (!switch_num.equals("")) {
						other_opinion = other_opinion + "<br>" + "交换机IP:" + switch_num;
					}
					if (!switch_point.equals("")) {
						other_opinion = other_opinion + "<br>" + "交换机端口:" + switch_point;
					}
					if (!key_code.equals("")) {
						other_opinion = other_opinion + "<br>" + "KEY号:" + key_code;
					}
					if (!software_type.equals("")) {
						InfoType medname = null;
						String names = "";
						Map<String, Object> mapt = new HashMap<String, Object>();
						String[] med = software_type.split(",");
						for (int i = 0; i < med.length; i++) {
							mapt.put("info_id", med[i].trim());
							medname = computerService.getInfoTypeByID(mapt);
							names += medname.getInfo_type() + ",";
						}

						other_opinion = other_opinion + "<br>" + "已安装软件:" + names;
					}
					if (!software_summ.equals("")) {
						other_opinion = other_opinion + "<br>" + "软件安装备注:" + software_summ;
					}
					opinion = opinion + "<br>" + other_opinion;

					mmp1.put("computer_ip", computer_ip);
					mmp1.put("computer_gateway", computer_gateway);
					mmp1.put("mark_code", mark_code);
					mmp1.put("vlan_num", vlan_num);
					mmp1.put("switch_num", switch_num);
					mmp1.put("switch_point", switch_point);
					mmp1.put("key_code", key_code);
					mmp1.put("software_type", software_type);
					mmp1.put("software_summ", software_summ);
					mmp1.put("event_code", event.getEvent_code());
					computerService.addDeviceOperationByEventCode(mmp1);
				} else if (listSize == 5) {
					String other_opinion = "";
					if (isrecycle.equals("是")) {
						Map<String, Object> map1 = new HashMap<String, Object>();
						String newhdisk = "";
						map1.put("computer_barcode", event.getBarcode());
						computer = computerService.getComputerByMap(map1);
						if (computer.getHdisk_no().trim().compareTo(content.trim()) != 0) {
							// 修改硬盘序列号则将该字段更新到硬盘表的备注中，添加到硬盘表中
							newhdisk = content.trim();
						}
						EntityHardDisk disk = new EntityHardDisk("computer", computer.getComputer_barcode(),
								computer.getConf_code(), computer.getHdisk_no(), computer.getDuty_user_id(),
								computer.getDuty_dept_id(), computer.getDuty_entp_id(), getCurUser().getUser_iidd(),
								new Date(), newhdisk, event.getJob_code(), 1);
						computerService.addEntityHardDisk(disk);// 将硬盘相关信息更新到硬盘台账中

						other_opinion = "回收硬盘：硬盘序列号为：" + content.trim();
					} else {
						// 不回收硬盘，则仅作为展示即可
						other_opinion = "不回收硬盘";
					}
					opinion = opinion + "<br>" + other_opinion;
				}
				String next_approver_name = basicService.getApproverName(next_approver);
				ApproverUser user = new ApproverUser(getCurUser().getUser_iidd(), getCurUser().getUser_name());
				ApproverUser approver = new ApproverUser(next_approver, next_approver_name);
				securityUserService.extendApproveJob(job_code, user, approver, approved, opinion, "");
				insertCommonLog("审批流程申请任务[" + job_code + "]");
				return "ok";
			} else {

				// 流程及审批信息
				job = basicService.getProcessJobByCode(job_code);
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
				event = computerService.getChangeDeviceEventByJobCode(job_code);
				getComputerInfoSpecial(event);
				try {
					List<ApproverUser> oriList = basicService.getNextApprover(job_code, job.getDept_id(),
							job.getSeclv_code(), job.getJobType().getJobTypeCode(), "");
					userList = removeDuplicateList(oriList);
					// 流程需要申请人（普通用户角色）确认,声光电BM，G
					Map<String, Object> variables = basicPrcManage.getProcessVariables(job.getInstance_id());
					Integer cur_approval = (Integer) variables.get("cur_approval");
					if (process.getTotal_steps() > cur_approval) {
						String[] roles = process.getStep_role().split("#");
						String role_ids = roles[cur_approval];
						// 如果审批人角色有且只有普通用户，则视为申请用户确认
						if (role_ids.equals("11")) {
							String user_iidd = getCurUser().getUser_iidd();
							String user_name = getCurUser().getUser_name();
							if (job != null) {
								user_iidd = job.getUser_iidd();
								user_name = job.getUser_name();
							}
							ApproverUser applyUser = new ApproverUser(user_iidd, user_name);
							// applyUser.setUser_iidd(user_iidd);
							// applyUser.setUser_name(user_name);
							userList.removeAll(userList);
							userList.add(applyUser);
							return SUCCESS;
						}
					}
					if (!basicService.isSelfApprove()) {// 不允许自审批
						if ((userList != null) && (userList.size() == 1)
								&& userList.get(0).getUser_iidd().equals(job.getUser_iidd())) {
							throw new Exception("唯一的下级审批人与申请用户相同！由于系统不支持自审批，请联系管理员添加可审批用户！");
						} else {
							for (ApproverUser user : userList) {
								if (user.getUser_iidd().equals(job.getUser_iidd())) {
									userList.remove(user);
									logger.debug("审批列表中去掉申请用户");
									break;
								}
							}
							for (ApproverUser user : userList) {
								if (user.getUser_iidd().equals(getCurUser().getUser_iidd())) {
									userList.remove(user);
									logger.debug("审批列表中去掉当前审批人");
									break;
								}
							}
						}
					}

					List<ApproverUser> tempList = new ArrayList<ApproverUser>();
					for (ApproverUser user : userList) {
						List<SecLevel> seclvList = new ArrayList<SecLevel>();
						seclvList = userService.getSecLevel();
						for (SecLevel seclv : seclvList) {
							if (seclv.getSeclv_code() == job.getSeclv_code()) {
								tempList.add(user);
								break;
							}
						}
					}
					if (userList.size() > 0 && tempList.size() == 0) {
						throw new Exception("下级审批人涉密级别低于审批单密级，请联系管理员");
					}
					userList = tempList;
				} catch (Exception e) {
					logger.error("Exception:" + e.getMessage());
					if (e.getCause() instanceof TooManyResultsException) {
						logger.error("基于该部门、密级和操作的流程定义重复，请提醒管理员修改");
						throw new Exception("基于该部门、密级和操作的流程定义重复，请提醒管理员修改");
					} else {
						throw e;
					}
				}

				return SUCCESS;
			}
		}
	}

}
