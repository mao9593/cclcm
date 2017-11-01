package hdsec.web.project.ledger.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

/**
 * 纸质台帐处理申请
 * 
 * @author renmingfei
 * 
 */
public class HandlePaperCarryOutJobAction extends LedgerBaseAction {

	private static final long serialVersionUID = 1L;
	private String _chk = "";
	private String addjob = "N";
	private String isDept = "N";
	private String next_approver = "";
	private JobTypeEnum jobType = null;
	private SecLevel seclv = null;
	private Date startTime = null;
	private Date endTime = null;
	private String carryoutInfo = "";
	private Integer seclv_code = null;
	private ApproveProcess process = null;
	private List<ApproverUser> userList = null;
	private List<EntityPaper> paperList = null;
	private String send_way = "";
	private String carryout_user_iidds = "";
	private String carryout_user_names = "";

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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getCarryoutInfo() {
		return carryoutInfo;
	}

	public void setCarryoutInfo(String carryoutInfo) {
		this.carryoutInfo = carryoutInfo;
	}

	@Override
	protected HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
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

	public String getIsDept() {
		return isDept;
	}

	public void setIsDept(String isDept) {
		this.isDept = isDept;
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
		isDept = getRequest().getParameter("isDept");
		if (addjob.equalsIgnoreCase("Y")) {

			try {
				// 审批
				ledgerService.addPaperCarryOutProcessJob(getCurUser().getUser_iidd(), getCurUser().getDept_id(),
						seclv_code, jobType, next_approver, startTime, endTime, carryoutInfo, _chk.split(","), "",
						send_way, carryout_user_iidds, carryout_user_names);

				insertCommonLog("提交外带" + jobType.getJobTypeName() + "审批申请：条码号[" + _chk + "].");
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
				paperList = basicService.getPaperListByBarcodes(_chk);
				Integer tempSeclv = -1;
				Integer highest_seclv = 10000;
				for (EntityPaper item : paperList) {
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
					List<SecLevel> seclvList = userService.getPrintSecLevelByUser(user.getUser_iidd());
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