package hdsec.web.project.device.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecUser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.StringUtils;

/**
 * 磁介质台帐处理申请
 * 
 * @author lixiang
 * 
 */
public class HandleDeviceJobAction extends DeviceBaseAction {

	private static final long serialVersionUID = 1L;
	private String device_code = "";
	private String addjob = "N";
	private String next_approver = "";
	private JobTypeEnum jobType = null;
	private SecLevel seclv = null;
	private Integer seclv_code = null;
	private ApproveProcess process = null;
	private List<ApproverUser> userList = null;
	private EntityDevice device = null;
	private String device_barcodes = "";
	private List<EntityDevice> deviceList = null;
	private String supervise_user_iidd = "";
	private String supervise_user_name = "";
	private String temp_supervise_user_iidd = "";

	// 获取监销人列表
	public List<SecUser> getSuperviseUserList() {
		return basicService.getSuperviseUserList();
	}

	public String getTemp_supervise_user_iidd() {
		return temp_supervise_user_iidd;
	}

	public void setTemp_supervise_user_iidd(String temp_supervise_user_iidd) {
		this.temp_supervise_user_iidd = temp_supervise_user_iidd;
	}

	public String getSupervise_user_iidd() {
		return supervise_user_iidd;
	}

	public void setSupervise_user_iidd(String supervise_user_iidd) {
		this.supervise_user_iidd = supervise_user_iidd;
	}

	public String getSupervise_user_name() {
		return supervise_user_name;
	}

	public void setSupervise_user_name(String supervise_user_name) {
		this.supervise_user_name = supervise_user_name;
	}

	public List<EntityDevice> getDeviceList() {
		return deviceList;
	}

	public void setDeviceList(List<EntityDevice> deviceList) {
		this.deviceList = deviceList;
	}

	public String getDevice_barcodes() {
		return device_barcodes;
	}

	public void setDevice_barcodes(String device_barcodes) {
		this.device_barcodes = device_barcodes;
	}

	public String getDevice_code() {
		return device_code;
	}

	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}

	public EntityDevice getDevice() {
		return device;
	}

	public void setDevice(EntityDevice device) {
		this.device = device;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setAddjob(String addjob) {
		this.addjob = addjob;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public JobTypeEnum getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = JobTypeEnum.valueOf(jobType);
	}

	public SecLevel getSeclv() {
		return seclv;
	}

	public ApproveProcess getProcess() {
		return process;
	}

	public List<ApproverUser> getUserList() {
		return userList;
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
		if (addjob.equalsIgnoreCase("Y")) {
			try {
				if (StringUtils.hasLength(device_code)) {
					EntityDevice entityDevice = deviceService.getDeviceByCode(device_code);
					String device_barcode = "";
					if (entityDevice != null) {
						device_barcode = entityDevice.getDevice_barcode();
					}
					basicService.handleDeviceJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(), seclv_code,
							jobType, next_approver, device_code, "", temp_supervise_user_iidd);
					insertCommonLog("提交磁介质" + jobType.getJobTypeName() + "审批申请：device_barcode[" + device_barcode + "].");
				} else {
					if (device_barcodes != null && (!device_barcodes.equals(""))) {
						List<EntityDevice> tempdeviceList = ledgerService.getDeviceListByBarcodes(device_barcodes);
						for (EntityDevice item : tempdeviceList) {
							basicService.handleDeviceJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(),
									seclv_code, jobType, next_approver, item.getDevice_code(), "",
									temp_supervise_user_iidd);
							insertCommonLog("提交磁介质" + jobType.getJobTypeName() + "审批申请：device_barcode["
									+ item.getDevice_barcode() + "].");
						}
					}
					return "personal";
				}
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			return "ok";
		} else {
			if (StringUtils.hasLength(device_code)) {
				device = deviceService.getDeviceByCode(device_code);
				Integer tempSeclv = -1;
				if (tempSeclv == -1 || tempSeclv == device.getSeclv_code()) {
					tempSeclv = device.getSeclv_code();
				} else {
					return "secError";
				}

				// 查询流程定义，用于展示流程信息
				process = basicService.getApproveProcessByKey(getCurUser().getDept_id(), tempSeclv,
						jobType.getJobTypeCode(), "");
				List<ApproverUser> oriList = basicService.getNextApprover(null, getCurUser().getDept_id(), tempSeclv,
						jobType.getJobTypeCode(), "");
				userList = removeDuplicateList(oriList);
				if (!basicService.isSelfApprove()) {// 不允许自审批
					if ((userList != null) && (userList.size() == 1)
							&& userList.get(0).getUser_iidd().equals(getCurUser().getUser_iidd())) {
						throw new Exception("唯一的下级审批人与申请用户相同！由于系统不支持自审批，请先联系管理员重设此流程步骤或添加可审批用户！");
					} else {
						for (ApproverUser user : userList) {
							if (user.getUser_iidd().equals(getCurUser().getUser_iidd())) {
								userList.remove(user);
								logger.debug("审批列表中去掉申请用户");
								break;
							}
						}
					}
				}
				List<ApproverUser> tempList = new ArrayList<ApproverUser>();
				for (ApproverUser user : userList) {
					List<SecLevel> seclvList = userService.getDeviceSecLevelByUser(user.getUser_iidd());
					for (SecLevel seclv : seclvList) {
						if (seclv.getSeclv_code() == tempSeclv) {
							tempList.add(user);
							break;
						}
					}
				}
				if (userList.size() > 0 && tempList.size() == 0) {
					throw new Exception("下级审批人涉密级别低于审批单密级，请联系管理员");
				}
				userList = tempList;
				seclv = userService.getSecLevelByCode(tempSeclv);
			} else {
				if (device_barcodes != null && (!device_barcodes.equals(""))) {
					deviceList = ledgerService.getDeviceListByBarcodes(device_barcodes);

					Integer tempSeclv = -1;
					for (EntityDevice item : deviceList) {
						if (tempSeclv == -1 || tempSeclv == item.getSeclv_code()) {
							tempSeclv = item.getSeclv_code();
						} else {
							return "secError";
						}
					}

					// 查询流程定义，用于展示流程信息
					process = basicService.getApproveProcessByKey(getCurUser().getDept_id(), tempSeclv,
							jobType.getJobTypeCode(), "");
					List<ApproverUser> oriList = basicService.getNextApprover(null, getCurUser().getDept_id(),
							tempSeclv, jobType.getJobTypeCode(), "");
					userList = removeDuplicateList(oriList);
					if (!basicService.isSelfApprove()) {// 不允许自审批
						if ((userList != null) && (userList.size() == 1)
								&& userList.get(0).getUser_iidd().equals(getCurUser().getUser_iidd())) {
							throw new Exception("唯一的下级审批人与申请用户相同！由于系统不支持自审批，请先联系管理员重设此流程步骤或添加可审批用户！");
						} else {
							for (ApproverUser user : userList) {
								if (user.getUser_iidd().equals(getCurUser().getUser_iidd())) {
									userList.remove(user);
									logger.debug("审批列表中去掉申请用户");
									break;
								}
							}
						}
					}
					List<ApproverUser> tempList = new ArrayList<ApproverUser>();
					for (ApproverUser user : userList) {
						List<SecLevel> seclvList = userService.getDeviceSecLevelByUser(user.getUser_iidd());
						for (SecLevel seclv : seclvList) {
							if (seclv.getSeclv_code() == tempSeclv) {
								tempList.add(user);
								break;
							}
						}
					}
					if (userList.size() > 0 && tempList.size() == 0) {
						throw new Exception("下级审批人涉密级别低于审批单密级，请联系管理员");
					}
					userList = tempList;
					seclv = userService.getSecLevelByCode(tempSeclv);

				} else {
					throw new Exception("参数错误，没有载体条码号");
				}
			}
			return SUCCESS;
		}
	}
}