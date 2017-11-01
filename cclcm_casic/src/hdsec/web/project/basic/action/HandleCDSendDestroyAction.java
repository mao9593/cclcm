package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.common.util.StringUtil;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.util.StringUtils;

/**
 * 光盘台帐处理申请
 * 
 * @author renmingfei 2014-10-23
 */
public class HandleCDSendDestroyAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private String _chk = "";
	private String addjob = "N";
	private String next_approver = "";
	private JobTypeEnum jobType = null;
	private SecLevel seclv = null;
	private Integer seclv_code = null;
	private ApproveProcess process = null;
	private List<ApproverUser> userList = null;
	private List<EntityCD> cdList = null;

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
				basicService.handleCDSendDestroy(getCurUser().getUser_iidd(), getCurUser().getDept_id(), seclv_code,
						jobType, next_approver, StringUtil.stringArrayToList(_chk.split(",")));
				insertCommonLog("提交光盘载体" + jobType.getJobTypeName() + "审批申请：条码号[" + _chk + "].");
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			return "ok";
		} else {
			if (StringUtils.hasLength(_chk)) {
				cdList = basicService.getCDListByBarcodes(_chk);
				Integer tempSeclv = -1;
				for (EntityCD item : cdList) {
					if (tempSeclv == -1 || tempSeclv == item.getSeclv_code()) {
						tempSeclv = item.getSeclv_code();
					} else {
						return "secError";
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