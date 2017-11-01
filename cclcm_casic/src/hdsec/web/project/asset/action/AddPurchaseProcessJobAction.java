package hdsec.web.project.asset.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.asset.model.PurchaseEvent;
import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;
import hdsec.web.project.common.util.StringUtil;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 添加资产采购流程任务申请
 * 
 * @author gaoximin 2015-6-29
 */
public class AddPurchaseProcessJobAction extends AssetBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_ids = "";
	private Integer seclv_code = null;
	private String submitFlag = "N";
	private String comment = "";
	private String next_approver = "";
	private JobTypeEnum jobType = null;
	private SecLevel seclv = null;
	private ApproveProcess process = null;
	private List<ApproverUser> userList = null;
	private String chkResult = "";
	private String actionContext = "";
	private String project_code = "";
	private String usage_code = "";
	private List<PurchaseEvent> eventList = null;
	private Integer highest_seclv = 10000;

	public void setSubmitFlag(String submitFlag) {
		this.submitFlag = submitFlag;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getActionContext() {
		return actionContext;
	}

	public void setActionContext(String actionContext) {
		this.actionContext = actionContext;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public void setEvent_ids(String event_ids) {
		this.event_ids = event_ids.replaceAll(" ", "");
	}

	public String getEvent_ids() {
		return event_ids;
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

	public String getChkResult() {
		return chkResult;
	}

	public String getProject_code() {
		return project_code;
	}

	public void setProject_code(String project_code) {
		this.project_code = project_code;
	}

	public String getUsage_code() {
		return usage_code;
	}

	public void setUsage_code(String usage_code) {
		this.usage_code = usage_code;
	}

	public void setChkResult(String chkResult) {
		this.chkResult = chkResult;
	}

	public int getHighest_seclv() {
		return highest_seclv;
	}

	public List<ApproverUser> getUserList() {
		return userList;
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageList();
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	public List<PurchaseEvent> getEventList() {
		return eventList;
	}

	// 获取所有密级
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		if (submitFlag.equalsIgnoreCase("Y")) {
			try {
				assetService.addProcessJob(getCurUser().getUser_iidd(),
						getCurUser().getDept_id(), seclv_code, jobType,
						next_approver, comment,
						StringUtil.stringArrayToList(event_ids.split(":")),
						usage_code, project_code);
				insertCommonLog("提交" + jobType.getJobTypeName() + "审批申请"
						+ event_ids);
			} catch (Exception e) {
				logger.error(e.getMessage());
				setChkResult("添加资产采购审批流程出现异常");
			}
			return "ok";
		} else {
			if (StringUtils.hasLength(event_ids)) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("submitable", true);
				map.put("event_ids",
						StringUtil.stringArrayToList(event_ids.split(":")));
				eventList = assetService.getPurchaseEventList(map);
				for (PurchaseEvent item : eventList) {
					SecLevel seclevel = userService.getSecLevelByCode(item
							.getSeclv_code());
					if (highest_seclv > seclevel.getSeclv_rank()) {
						highest_seclv = seclevel.getSeclv_rank();
					}
				}
				Double max_price = 0.0;
				Double min_price = 0.0;
				PurchaseEvent itemEvent = eventList.get(0);
				if (itemEvent != null) {
					max_price = Double.parseDouble(itemEvent.getUnit_price());
					min_price = Double.parseDouble(itemEvent.getUnit_price());
				}

				for (PurchaseEvent item : eventList) {
					SecLevel seclevel = userService.getSecLevelByCode(item
							.getSeclv_code());
					if (highest_seclv > seclevel.getSeclv_rank()) {
						highest_seclv = seclevel.getSeclv_rank();
					}
					if (max_price < Double.parseDouble(item.getUnit_price())) {

						max_price = Double.parseDouble(item.getUnit_price());
					}
					Double d = Double.parseDouble(item.getUnit_price());
					if (min_price > Double.parseDouble(item.getUnit_price())) {

						min_price = Double.parseDouble(item.getUnit_price());
					}

				}
				String price = PropertiesFileUtil.getProperty(
						Constants.PROJECT_CONFIG_FILE, "asset.price");
				if (price == null) {
					throw new Exception("没有配置大额金额！请联系管理进行配置。");
				}
				Double dprice = Double.parseDouble(price);
				if (min_price < dprice && max_price >= dprice) {
					throw new Exception("申请单中，金额大于" + price + "元 采购另行申请！");
				}
				if (min_price >= dprice) {

					setJobType(JobTypeEnum.PURCHASES.getJobTypeCode());
				}
				if (max_price < dprice) {
					setJobType(JobTypeEnum.PURCHASE.getJobTypeCode());
				}

			} else {
				throw new Exception("参数错误，没有资产采购申请作业编号");
			}
		}
		seclv = userService.getSecLevelByCode(seclv_code);
		return SUCCESS;
	}

}
