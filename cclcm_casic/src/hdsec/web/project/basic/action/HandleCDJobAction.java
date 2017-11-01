package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.basic.model.SysSeclevel;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;
import hdsec.web.project.user.model.SecUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

/**
 * 光盘台帐处理申请
 * 
 * @author lixiang
 * 
 */
public class HandleCDJobAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private String _chk = "";
	private String addjob = "N";
	private String isDept = "N";
	private String next_approver = "";
	private JobTypeEnum jobType = null;
	private SecLevel seclv = null;
	private Integer seclv_code = null;
	private ApproveProcess process = null;
	private List<ApproverUser> userList = null;
	private List<EntityCD> cdList = null;
	private String output_dept_name = "";
	private String output_user_name = "";
	private String cd_barcodes = "";
	private Integer delay_days = null;
	private String checkResult = "";
	private String supervise_user_iidd = "";
	private String supervise_user_name = "";
	private List<SecUser> output_undertakerList = null;
	private String send_way = "";
	private String carryout_user_iidds = "";
	private String carryout_user_names = "";
	private String output_undertaker = "";

	public String getSend_way() {
		return send_way;
	}

	public void setSend_way(String send_way) {
		this.send_way = send_way;
	}

	public String getCarryout_user_iidds() {
		return carryout_user_iidds;
	}

	public void setCarryout_user_iidds(String carryout_user_iidds) {
		this.carryout_user_iidds = carryout_user_iidds;
	}

	public String getCarryout_user_names() {
		return carryout_user_names;
	}

	public void setCarryout_user_names(String carryout_user_names) {
		this.carryout_user_names = carryout_user_names;
	}

	public String getOutput_undertaker() {
		return output_undertaker;
	}

	public void setOutput_undertaker(String output_undertaker) {
		this.output_undertaker = output_undertaker;
	}

	// 获取外发承办人列表(仅部门文件管理员)
	public List<SecUser> getOutput_undertakerList() {
		String parent_dept_id = basicService.getParentDeptIdByCurrentId(getCurUser().getDept_id());
		Map<String, Object> map0 = new HashMap<String, Object>();
		map0.put("dept_id", getCurUser().getDept_id());
		map0.put("parent_dept_id", parent_dept_id);
		output_undertakerList = basicService.getOutputUndertakersByDeptId(map0);
		// 选择自己，若为自己，则外发确认或接收在 个人台账->外发申请记录中
		// output_undertakerList.add(getCurUser());

		Set<String> set = new HashSet<String>();
		List<SecUser> sulist = new ArrayList<SecUser>();
		for (SecUser item : output_undertakerList) {
			if (set.add(item.getUser_iidd())) {
				sulist.add(item);
			}
		}
		return sulist;
	}

	// 获取监销人列表
	public List<SecUser> getSuperviseUserList() {
		return basicService.getSuperviseUserList();
	}

	public List<EntityCD> getCdList() {
		return cdList;
	}

	public void setCdList(List<EntityCD> cdList) {
		this.cdList = cdList;
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

	public void set_chk(String _chk) {
		this._chk = _chk.replaceAll(" ", "");
	}

	public String get_chk() {
		return _chk;
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

	public void setOutput_dept_name(String output_dept_name) {
		this.output_dept_name = output_dept_name;
	}

	public void setOutput_user_name(String output_user_name) {
		this.output_user_name = output_user_name;
	}

	public String getIsDept() {
		return isDept;
	}

	public void setIsDept(String isDept) {
		this.isDept = isDept;
	}

	public String getCd_barcodes() {
		return cd_barcodes;
	}

	public void setCd_barcodes(String cd_barcodes) {
		this.cd_barcodes = cd_barcodes;
	}

	public Integer getDelay_days() {
		return delay_days;
	}

	public void setDelay_days(Integer delay_days) {
		this.delay_days = delay_days;
	}

	public String getCheckResult() {
		return checkResult;
	}

	public void setCheckResult(String checkResult) {
		this.checkResult = checkResult;
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
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	@Override
	public String executeFunction() throws Exception {
		isDept = getRequest().getParameter("isDept");
		if (addjob.equalsIgnoreCase("Y")) {
			try {
				SysSeclevel sysSeclevel = basicService.getSysSecLevelByCode(seclv_code);
				if (sysSeclevel.getArchive_time() > 0) {
					// 延期留用期限不为空时（即设置的值）
					if (delay_days != null) {
						String resultInfo = "";
						// 延期留用期限与回收时间期限比较
						if (delay_days > sysSeclevel.getArchive_time()) {
							resultInfo = "延期留用期限不能大于回收时间期限,请重新输入";
							setCheckResult(resultInfo);
							return "ok2";
						} else {
							// 延期留用期限小于回收时间期限，则更新数据库
							String[] barcodes = cd_barcodes.split(",");
							for (String barcode : barcodes) {
								Map<String, Object> map = new HashMap<String, Object>();
								map.put("cd_barcode", barcode);
								map.put("delay_days", delay_days);
								ledgerService.updateCDDelayDays(map);
							}
						}
					}
				}
				if (jobType.getJobTypeCode().equals("SEND_CD")) { // 外发
					if (cd_barcodes != "") {
						_chk = cd_barcodes;
					}
					// String carryout_dept_id = transferService.getDeptIdByUserId(carryout_user_iidd);
					carryout_user_iidds = carryout_user_iidds.isEmpty() ? "" : carryout_user_iidds.substring(0,
							carryout_user_iidds.length() - 1);
					carryout_user_names = carryout_user_names.isEmpty() ? "" : carryout_user_names.substring(0,
							carryout_user_names.length() - 1);
					basicService.handleSendCDJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(), seclv_code,
							jobType, next_approver, _chk, output_dept_name, output_user_name, "", send_way,
							carryout_user_iidds, carryout_user_names, output_undertaker);
				} else {
					basicService.handleCDJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(), seclv_code,
							jobType, next_approver, _chk, output_dept_name, output_user_name, "", supervise_user_iidd);
				}

				insertCommonLog("提交光盘载体" + jobType.getJobTypeName() + "审批申请：条码号[" + _chk + "].");
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			if (isDept.equalsIgnoreCase("Y")) {
				return "ok1";
			} else {
				return "ok";
			}
		} else {
			if (StringUtils.hasLength(_chk)) {
				cdList = basicService.getCDListByBarcodes(_chk);
				Integer tempSeclv = -1;
				Integer highest_seclv = 10000;
				for (EntityCD item : cdList) {
					SecLevel seclevel = userService.getSecLevelByCode(item.getSeclv_code());
					if (highest_seclv > seclevel.getSeclv_rank()) {
						highest_seclv = seclevel.getSeclv_rank();
						tempSeclv = seclevel.getSeclv_code();
					}
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
					List<SecLevel> seclvList = userService.getBurnSecLevelByUser(user.getUser_iidd());
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
			return SUCCESS;
		}
	}
}