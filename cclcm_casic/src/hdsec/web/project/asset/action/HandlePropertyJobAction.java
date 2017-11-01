package hdsec.web.project.asset.action;

import hdsec.web.project.activiti.model.ApproveProcess;
import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.asset.model.EntityProperty;
import hdsec.web.project.user.model.ApproverUser;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.StringUtils;

/**
 * 资产报废
 * 
 * @author gaoximin 2015-7-16
 */
public class HandlePropertyJobAction extends AssetBaseAction {

	private static final long serialVersionUID = 1L;
	private String property_barcode = "";
	private String addjob = "N";
	private String next_approver = "";
	private JobTypeEnum jobType = null;
	private SecLevel seclv = null;
	private Integer seclv_code = null;
	private ApproveProcess process = null;
	private List<ApproverUser> userList = null;
	private EntityProperty property = null;
	private String handletype = "";// 默认为报废

	public String getProperty_barcode() {
		return property_barcode;
	}

	public void setProperty_barcode(String property_barcode) {
		this.property_barcode = property_barcode;
	}

	public EntityProperty getProperty() {
		return property;
	}

	public void setProperty(EntityProperty property) {
		this.property = property;
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

	public String getHandletype() {
		return handletype;
	}

	public void setHandletype(String handletype) {
		this.handletype = handletype;
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
				if (handletype.equals("PROPERTYOUT")) {// 出库
					jobType = JobTypeEnum.PROPERTYOUT;
				} else if (handletype.equals("WASTE")) {// 报废
					jobType = JobTypeEnum.WASTE;
				} else if (handletype.equals("STORE")) {// 入库
					jobType = JobTypeEnum.STORE;
				}
				Map<String, String> map = new HashMap<String, String>();
				map.put("property_barcode", property_barcode);
				property = assetService.getPropertyByIDBarcode(map);
				assetService.handlePropertyJob(getCurUser().getUser_iidd(),
						getCurUser().getDept_id(), seclv_code, jobType,
						next_approver, property_barcode, "");
				insertCommonLog("提交" + jobType.getJobTypeName() + "审批申请：["
						+ property_barcode + "].");
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
			if (jobType.getJobTypeCode().equals("STORE")) {
				return "store";
			} else {
				return "ok";
			}
		} else {
			if (handletype.equals("PROPERTYOUT")) {// 出库
				jobType = JobTypeEnum.PROPERTYOUT;
			} else if (handletype.equals("WASTE")) {// 报废
				jobType = JobTypeEnum.WASTE;
			} else if (handletype.equals("STORE")) {// 入库
				jobType = JobTypeEnum.STORE;
			}
			if (StringUtils.hasLength(property_barcode)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("property_barcode", property_barcode);
				property = assetService.getPropertyByIDBarcode(map);
				Integer tempSeclv = -1;
				if (tempSeclv == -1 || tempSeclv == property.getSeclv_code()) {
					tempSeclv = property.getSeclv_code();
				} else {
					return "error";
				}

				// 查询流程定义，用于展示流程信息
				process = basicService.getApproveProcessByKey(getCurUser()
						.getDept_id(), tempSeclv, jobType.getJobTypeCode(), "");
				List<ApproverUser> oriList = basicService.getNextApprover(null,
						getCurUser().getDept_id(), tempSeclv,
						jobType.getJobTypeCode(), "");
				userList = removeDuplicateList(oriList);
				if (!basicService.isSelfApprove()) {// 不允许自审批
					if ((userList != null)
							&& (userList.size() == 1)
							&& userList.get(0).getUser_iidd()
									.equals(getCurUser().getUser_iidd())) {
						throw new Exception(
								"唯一的下级审批人与申请用户相同！由于系统不支持自审批，请先联系管理员重设此流程步骤或添加可审批用户！");
					} else {
						for (ApproverUser user : userList) {
							if (user.getUser_iidd().equals(
									getCurUser().getUser_iidd())) {
								userList.remove(user);
								logger.debug("审批列表中去掉申请用户");
								break;
							}
						}
					}
				}
				// 取所有密级
				List<ApproverUser> tempList = new ArrayList<ApproverUser>();
				for (ApproverUser user : userList) {
					List<SecLevel> seclvList = userService.getSecLevel();
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