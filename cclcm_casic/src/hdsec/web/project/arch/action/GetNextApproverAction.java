package hdsec.web.project.arch.action;

import hdsec.web.project.activiti.model.ProcessJob;
import hdsec.web.project.basic.action.BasicBaseAction;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.exceptions.TooManyResultsException;

/**
 * 查询下级审批人
 * 
 * @author renmingfei
 * 
 */
public class GetNextApproverAction extends BasicBaseAction {

	private static final long serialVersionUID = 1L;
	private String dept_id = "";
	private Integer seclv_code = null;
	private String jobType = "";
	private String job_code = "";
	private List<ApproverUser> userList = null;
	private String done = "N";
	private String usage_code = "";

	public List<ApproverUser> getUserList() {
		return userList;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public String getDone() {
		return done;
	}

	public void setDone(String done) {
		this.done = done;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

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
		try {
			List<ApproverUser> oriList = basicService.getNextApprover(job_code,
					dept_id, seclv_code, jobType, usage_code);
			userList = removeDuplicateList(oriList);
			if (!basicService.isSelfApprove()) {// 不允许自审批
				ProcessJob job = basicService.getProcessJobByCode(job_code);
				String user_iidd = getCurUser().getUser_iidd();
				if (job != null) {
					user_iidd = job.getUser_iidd();
				}
				if ((userList != null) && (userList.size() == 1)
						&& userList.get(0).getUser_iidd().equals(user_iidd)) {
					throw new Exception(
							"唯一的下级审批人与申请用户相同！由于系统不支持自审批，请先联系管理员添加可审批用户！");
				} else {
					for (ApproverUser user : userList) {
						if (user.getUser_iidd().equals(user_iidd)) {
							userList.remove(user);
							logger.debug("审批列表中去掉申请用户");
							break;
						}
					}
				}
			}
			List<ApproverUser> tempList = new ArrayList<ApproverUser>();
			for (ApproverUser user : userList) {
				List<SecLevel> seclvList = new ArrayList<SecLevel>();
				seclvList = userService.getDeviceSecLevelByUser(user
						.getUser_iidd());
				for (SecLevel seclv : seclvList) {
					if (seclv.getSeclv_code() == seclv_code) {
						tempList.add(user);
						break;
					}
				}
			}
			if (userList.size() > 0 && tempList.size() == 0) {
				throw new Exception("下级审批人涉密级别低于审批单密级，请联系管理员");
			}
			userList = tempList;
			setDone("Y");
		} catch (Exception e) {
			logger.error("Exception:" + e.getMessage());
			if (e.getCause() instanceof TooManyResultsException) {
				logger.error("基于该部门、密级和操作的流程定义重复，请提醒管理员修改");
				setDone("基于该部门、密级和操作的流程定义重复，请提醒管理员修改");
			} else {
				logger.error(e.getMessage());
				setDone(e.getMessage());
			}
		}
		return SUCCESS;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDept_id() {
		return dept_id;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public String getJobType() {
		return jobType;
	}

	public String getJob_code() {
		return job_code;
	}

	public String getUsage_code() {
		return usage_code;
	}

	public void setUserList(List<ApproverUser> userList) {
		this.userList = userList;
	}
}
