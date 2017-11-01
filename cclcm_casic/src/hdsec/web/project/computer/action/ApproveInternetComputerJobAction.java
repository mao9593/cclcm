package hdsec.web.project.computer.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.computer.model.ChangeDeviceEvent;
import hdsec.web.project.computer.model.EntityComputer;
import hdsec.web.project.computer.model.EntityEventDevice;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.util.StringUtils;

public class ApproveInternetComputerJobAction extends ComputerBaseAction {

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
	// private EntityComputer computer_old = null;
	private String history = "";
	private String hdisk_no = "";
	private String computer_mac = "";
	private String computer_os = "";
	private Date install_time = null;
	private String key_code = "";
	private String computer_ip = "";
	private String switch_num = "";
	private String switch_point = "";
	private String software_type = "";// 软件安装
	// private String software_summ = "";
	private String mark_code = "";
	private String vlan_num = "";
	private String computer_gateway = "";
	// private String med_type = "";
	// private String event_content = "";
	// private String event_code = "";
	private Integer listSize = null;
	private String opinion_all = "";
	private ChangeDeviceEvent event = null;

	// private String isrecycle = "";
	// private String content = "";
	// private String isnewdisk = "";
	// private String newdisk = "";

	protected void getComputerInfoSpecial(ChangeDeviceEvent event1) {

		listSize = recordList.size() - 1;
		for (int i = 1; i <= listSize; i++) {
			opinion_all = opinion_all + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion() + "<br>审批人："
					+ recordList.get(i).getUser_name() + "<br>审批时间：" + recordList.get(i).getOp_time_str();
		}

		EntityEventDevice event_device_content = computerService
				.getEntityEventDeviceByEventCode(event1.getEvent_code());

		// 新增计算机网络机

		String duty_user_name = "";
		String duty_dept_name = "";
		List<String> seclv_name = null;

		if (event_device_content.getSeclv_code() != null) {
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

		// event_content组成：|计算机名称|密级代码|责任人ID|责任部门ID|责任单位ID|保密编号|网络类型代码|存放区域|存放位置|硬盘序列号|操作系统|安装时间|MAC地址|end|
		EntityComputer computer_temp = new EntityComputer("", event_device_content.getComputer_name(),
				event_device_content.getSeclv_code(), seclv_name.get(0), event_device_content.getDuty_user_id(),
				duty_user_name, event_device_content.getDuty_dept_id(), duty_dept_name,
				event_device_content.getDuty_entp_id(), event_device_content.getComputer_code(),
				event_device_content.getInternet_type(), event_device_content.getHdisk_no(),
				event_device_content.getComputer_os(), event_device_content.getInstall_time(), "",
				event_device_content.getComputer_mac(), event_device_content.getStorage_area(),
				event_device_content.getStorage_location(), event_device_content.getSumm(), "", "",
				event_device_content.getMed_type(), event_device_content.getOutput_point(),
				event_device_content.getOldconf_code(), event_device_content.getMed_type_name());

		computer = computer_temp;
		// computerService.updateComputer(computer);
		// event1 = null;
		// return "ok_internet";
	}

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

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public String getHdisk_no() {
		return hdisk_no;
	}

	public void setHdisk_no(String hdisk_no) {
		this.hdisk_no = hdisk_no;
	}

	public String getComputer_mac() {
		return computer_mac;
	}

	public void setComputer_mac(String computer_mac) {
		this.computer_mac = computer_mac;
	}

	public String getComputer_os() {
		return computer_os;
	}

	public void setComputer_os(String computer_os) {
		this.computer_os = computer_os;
	}

	public Date getInstall_time() {
		return install_time;
	}

	public void setInstall_time(Date install_time) {
		this.install_time = install_time;
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
				// 新增网络版计算机
				if (listSize == 5) {
					String other_opinion = "";
					String time1 = "";
					if (install_time != null) {
						SimpleDateFormat t1 = new SimpleDateFormat("yyyy-MM-dd");
						time1 = t1.format(install_time);
					}
					String softName = getSoftName(software_type);

					other_opinion = other_opinion + "Key号:  " + key_code + "<br>" + "计算机IP:  " + computer_ip + "<br>"
							+ "交换机IP:  " + switch_num + "<br>" + "交换机端口:  " + switch_point + "<br>" + "掩码:  "
							+ mark_code + "<br>" + "硬盘序列号:  " + hdisk_no + "<br>" + "MAC地址:  " + computer_mac + "<br>"
							+ "操作系统版本:  " + computer_os + "<br>" + "操作系统安装时间:  " + time1 + "<br>" + "已安装软件:  "
							+ softName;

					opinion = opinion + "<br>" + other_opinion;
					Map<String, Object> mmp = new HashMap<String, Object>();
					mmp.put("event_code", event.getEvent_code());
					mmp.put("key_code", key_code);
					mmp.put("computer_ip", computer_ip);
					mmp.put("switch_num", switch_num);
					mmp.put("switch_point", switch_point);
					mmp.put("vlan_num", vlan_num);
					mmp.put("mark_code", mark_code);
					mmp.put("computer_gateway", computer_gateway);
					mmp.put("software_type", software_type);
					// mmp.put("software_summ", software_summ);
					mmp.put("hdisk_no", hdisk_no);
					mmp.put("computer_mac", computer_mac);
					mmp.put("computer_os", computer_os);
					mmp.put("install_time", install_time);

					computerService.addDeviceOperationByEventCode(mmp);
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
