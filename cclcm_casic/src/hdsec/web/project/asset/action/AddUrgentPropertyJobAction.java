package hdsec.web.project.asset.action;

import java.util.List;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.asset.model.PropertyKind;
import hdsec.web.project.asset.model.PurchaseEvent;
import hdsec.web.project.user.model.SecLevel;

import org.springframework.util.StringUtils;

public class AddUrgentPropertyJobAction extends AssetBaseAction {

	private String event_code = "";
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = "";// 用途编号
	private String summ = "";// 备注
	private String purchase_title = ""; // 工单标题
	private String purchase_code = "";// 工单编号
	private String telephone = ""; // 申请人联系电话
	private Integer property_seclvcode = null; // 资产密级
	private Integer property_kind = null; // 采购资产种类ID
	private String property_name = ""; // 资产名称
	private String property_standard = ""; // 资产规格
	private String supplier = ""; // 供应厂商
	private Integer amount = 0;// 数量
	private String unit_price = ""; // 单价
	private String total_price = "";// 总价
	private String reason = "";// 采购原因
	private String property_type = ""; // 资产型号
	private String details = ""; // 主要技术指标和配套附件主要技术指标和配套附件
	private String budget_year = ""; // 年度预算指标（元）
	private String property_kind_name = null; // 资产种类
	private String next_approver = "";// 下级审批人
	private String jobType = JobTypeEnum.URGENTPURCHASE.getJobTypeCode();

	public String getJobType() {
		return jobType;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public List<PropertyKind> getTypeList() {
		return assetService.getKindList();
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
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

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public String getPurchase_title() {
		return purchase_title;
	}

	public void setPurchase_title(String purchase_title) {
		this.purchase_title = purchase_title;
	}

	public String getPurchase_code() {
		return purchase_code;
	}

	public void setPurchase_code(String purchase_code) {
		this.purchase_code = purchase_code;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Integer getProperty_seclvcode() {
		return property_seclvcode;
	}

	public void setProperty_seclvcode(Integer property_seclvcode) {
		this.property_seclvcode = property_seclvcode;
	}

	public Integer getProperty_kind() {
		return property_kind;
	}

	public void setProperty_kind(Integer property_kind) {
		this.property_kind = property_kind;
	}

	public String getProperty_name() {
		return property_name;
	}

	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}

	public String getProperty_standard() {
		return property_standard;
	}

	public void setProperty_standard(String property_standard) {
		this.property_standard = property_standard;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}

	public String getTotal_price() {
		return total_price;
	}

	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getProperty_type() {
		return property_type;
	}

	public void setProperty_type(String property_type) {
		this.property_type = property_type;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getBudget_year() {
		return budget_year;
	}

	public void setBudget_year(String budget_year) {
		this.budget_year = budget_year;
	}

	public String getProperty_kind_name() {
		return property_kind_name;
	}

	public void setProperty_kind_name(String property_kind_name) {
		this.property_kind_name = property_kind_name;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	// 获取所有密级
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	// 资产密级
	public List<SecLevel> getPropertySeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_code)) {
			String user_iidd = getCurUser().getUser_iidd();
			String dept_id = getCurUser().getDept_id();
			PurchaseEvent event = new PurchaseEvent(user_iidd, dept_id,
					event_code, seclv_code, usage_code, project_code, summ,
					purchase_title, purchase_code, telephone,
					property_seclvcode, property_kind_name, property_name,
					property_standard, supplier, amount, unit_price,
					total_price, reason, property_type, details, budget_year,
					property_kind);
			event.setJob_type(jobType);
			assetService.addUrgentPropertyEvent(event, next_approver);
			insertCommonLog("添加紧急资产采购申请作业[" + event_code + "]");
			return "ok";
		} else {
			event_code = getCurUser().getUser_iidd() + "_" + jobType + "_"
					+ System.currentTimeMillis();
			return SUCCESS;
		}
	}

}
