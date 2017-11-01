package hdsec.web.project.computer.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.activiti.model.ProcessRecord;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.computer.model.EntityDeviceTemp;
import hdsec.web.project.computer.model.EntityInfoDevice;
import hdsec.web.project.computer.model.InfoDeviceEvent;
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

public class ApproveInfoDeviceJobAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private ProcessJob job = null;
	private ApproveProcess process = null;
	private List<ProcessRecord> recordList = null;
	private List<InfoDeviceEvent> eventList = null;
	private String approved = "";
	private String opinion = "";
	private String next_approver = "";
	private List<ApproverUser> userList = null;
	private String history = "";
	private EntityDeviceTemp temp = null;
	private InfoDeviceEvent event = null;
	private Integer listSize = null;
	private String opinion_all = "";

	private String device_series = "";// 设备编号
	private String device_version = "";// 型号
	private String brand_type = "";// 品牌类型
	private Integer device_seclv = null;// 设备密级
	private String device_usage = "";// 设备用途
	private Date purchase_time = null;// 采购时间
	private Date use_time = null;// 领用/启用时间
	private String location = "";// 安装地点
	private String serial_num = "";// 序列号
	private String cert_name = "";// 检测证书名称
	private String cert_num = "";// 证书编号
	private String memory = "";// 内存/容量
	private Integer device_statues = null;// 状态
	private String detail = "";// 设备详情
	private String oldconf_code = "";// 原保密编号
	private String company = "";// 单位
	private List<EntityInfoDevice> deviceList = null;
	private EntityInfoDevice device = null;
	private String check = "";// 审批中是否

	public void setCheck(String check) {
		this.check = check;
	}

	public EntityInfoDevice getDevice() {
		return device;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public void setDevice_series(String device_series) {
		this.device_series = device_series;
	}

	public void setDevice_version(String device_version) {
		this.device_version = device_version;
	}

	public void setBrand_type(String brand_type) {
		this.brand_type = brand_type;
	}

	public void setDevice_seclv(Integer device_seclv) {
		this.device_seclv = device_seclv;
	}

	public void setDevice_usage(String device_usage) {
		this.device_usage = device_usage;
	}

	public void setPurchase_time(Date purchase_time) {
		this.purchase_time = purchase_time;
	}

	public void setUse_time(Date use_time) {
		this.use_time = use_time;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setSerial_num(String serial_num) {
		this.serial_num = serial_num;
	}

	public void setCert_name(String cert_name) {
		this.cert_name = cert_name;
	}

	public void setCert_num(String cert_num) {
		this.cert_num = cert_num;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public void setDevice_statues(Integer device_statues) {
		this.device_statues = device_statues;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public void setOldconf_code(String oldconf_code) {
		this.oldconf_code = oldconf_code;
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

	public EntityDeviceTemp getTemp() {
		return temp;
	}

	public InfoDeviceEvent getEvent() {
		return event;
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

	public List<ProcessRecord> getRecordList() {
		return recordList;
	}

	public List<ApproverUser> getUserList() {
		return userList;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public void setApproved(String approved) {
		this.approved = approved;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion + " ";
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver.replaceAll(" ", "");
	}

	public String getType() {
		return basicService.getJobTypeCodeByJobCode(job_code);
	}

	public String getHistory() {
		return history;
	}

	public void setHistory(String history) {
		this.history = history;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
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

	private void getNextStep(InfoDeviceEvent event) {
		temp = computerService.getDeviceTempByEventCode(event.getEvent_code());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("device_barcode", event.getDevice_barcode());
		deviceList = computerService.getInfoDeviceList(map);
		if (deviceList.size() != 0) {
			device = deviceList.get(0);
		}

		listSize = recordList.size() - 1;
		for (int i = 1; i <= listSize; i++) {
			opinion_all = opinion_all + Constants.COMMON_SEPARATOR + recordList.get(i).getOpinion() + "<br>审批人："
					+ recordList.get(i).getUser_name() + "<br>审批时间：" + recordList.get(i).getOp_time_str();
		}
	}

	@Override
	public String executeFunction() throws Exception {
		if (history.equals("Y")) {
			job = basicService.getProcessJobByCode(job_code);
			process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
			ProcessRecord record = new ProcessRecord();
			record.setJob_code(job_code);
			recordList = activitiService.getProcessRecordList(record);
			eventList = computerService.getInfoDeviceEventListByJobCode(job_code);
			event = eventList.get(0);
			getNextStep(event);
			if (job.getJobType().getJobTypeCode().equals("DEVICE_CHANGE")
					|| job.getJobType().getJobTypeCode().equals("CHANGE_OTHER")) {
				return "change";
			} else if (job.getJobType().getJobTypeCode().equals("DEVICE_DES")) {
				return "des";
			}
			return SUCCESS;
		} else {
			if (StringUtils.hasLength(approved)) {// 审批结果
				job = basicService.getProcessJobByCode(job_code);
				eventList = computerService.getInfoDeviceEventListByJobCode(job_code);
				event = eventList.get(0);
				if (job.getJobType().getJobTypeCode().equals("INFO_DEVICE")
						|| job.getJobType().getJobTypeCode().equals("INFO_OTHER")) {
					if (listSize == 1) {
						EntityDeviceTemp appro = new EntityDeviceTemp(event.getEvent_code(), "", device_series,
								device_version, brand_type, device_seclv, device_usage, purchase_time, use_time,
								location, serial_num, cert_name, cert_num, memory, null, "", detail, oldconf_code,
								company, device_statues);
						computerService.updateDeviceTemp(appro);
					}
				} else if (job.getJobType().getJobTypeCode().equals("DEVICE_DES")) {
					if (listSize == 1) {
						String other_opinion = "";
						if (check.equalsIgnoreCase("yes")) {
							other_opinion = "是否拆除存储部件：是";
						} else {
							other_opinion = "是否拆除存储部件：否";
						}
						opinion = opinion + "<br>" + other_opinion;
					}
				}
				String next_approver_name = basicService.getApproverName(next_approver);
				ApproverUser user = new ApproverUser(getCurUser().getUser_iidd(), getCurUser().getUser_name());
				ApproverUser approver = new ApproverUser(next_approver, next_approver_name);
				// 判断审批最后一步已通过更改信息
				securityUserService.extendApproveJob(job_code, user, approver, approved, opinion, "");
				insertCommonLog("审批用户涉密活动任务，审批单编号[" + job_code + "]");
				return "ok";
			} else {
				job = basicService.getProcessJobByCode(job_code);
				ProcessRecord record = new ProcessRecord();
				record.setJob_code(job_code);
				recordList = activitiService.getProcessRecordList(record);
				eventList = computerService.getInfoDeviceEventListByJobCode(job_code);
				event = eventList.get(0);
				getNextStep(event);

				String usage_code = event.getUsage_code();
				process = basicPrcManage.getApproveProcessByInstanceId(job.getInstance_id());
				try {
					List<ApproverUser> oriList = basicService.getNextApprover(job_code, job.getDept_id(),
							job.getSeclv_code(), job.getJobType().getJobTypeCode(), usage_code);
					userList = removeDuplicateList(oriList);

					// 流程需要申请人（普通用户角色）确认,声光电BM，
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
							userList.removeAll(userList);
							userList.add(applyUser);
							if (job.getJobType().getJobTypeCode().equals("DEVICE_CHANGE")
									|| job.getJobType().getJobTypeCode().equals("CHANGE_OTHER")) {
								return "change";
							} else if (job.getJobType().getJobTypeCode().equals("DEVICE_DES")) {
								return "des";
							}
							return SUCCESS;
						}
					}

					if (!basicService.isSelfApprove()) {// 不允许自审批
						if ((userList != null) && (userList.size() == 1)
								&& userList.get(0).getUser_iidd().equals(job.getUser_iidd())) {
							throw new Exception("唯一的下级审批人与申请用户相同！由于系统不支持自审批，请先联系管理员添加可审批用户！");
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
					/*
					 * List<ApproverUser> tempList = new ArrayList<ApproverUser>(); for (ApproverUser user : userList) {
					 * List<SecLevel> seclvList = userService.getCopySecLevelByUser(user.getUser_iidd()); for (SecLevel
					 * seclv : seclvList) { if (seclv.getSeclv_code() == job.getSeclv_code()) { tempList.add(user);
					 * break; } } } if (userList.size() > 0 && tempList.size() == 0) { throw new
					 * Exception("下级审批人涉密级别低于审批单密级，请联系管理员"); } userList = tempList;
					 */
				} catch (Exception e) {
					logger.error("Exception:" + e.getMessage());
					if (e.getCause() instanceof TooManyResultsException) {
						logger.error("基于该部门、密级和操作的流程定义重复，请提醒管理员修改");
						throw new Exception("基于该部门、密级和操作的流程定义重复，请提醒管理员修改");
					} else {
						throw e;
					}
				}
				if (job.getJobType().getJobTypeCode().equals("DEVICE_CHANGE")
						|| job.getJobType().getJobTypeCode().equals("CHANGE_OTHER")) {
					return "change";
				} else if (job.getJobType().getJobTypeCode().equals("DEVICE_DES")) {
					return "des";
				}
				return SUCCESS;
			}
		}

	}
}