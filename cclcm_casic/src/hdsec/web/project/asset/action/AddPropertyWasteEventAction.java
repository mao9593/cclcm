package hdsec.web.project.asset.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.asset.model.EntityProperty;
import hdsec.web.project.asset.model.WasteEvent;
import hdsec.web.project.user.model.SecDept;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 报废申请及变更申请
 * 
 * @author zhangzw 2015-7-29
 */
public class AddPropertyWasteEventAction extends AssetBaseAction {

	private String event_code = "";// 作业流水号
	private Integer seclv_code = null;// 作业密级
	private String project_code = "";// 所属项目编号
	private String usage_code = ""; // 用途编号
	private String summ = ""; // 备注
	private String deper_life = ""; // 折旧年限
	private String userd_life = ""; // 使用年限
	private String suggestion = ""; // 鉴定意见及负责鉴定人
	private String suggestion_waste = "";// 设备报废后处理意见
	private String property_kind = ""; // 采购资产种类
	private String property_name = ""; // 资产名称
	private String property_standard = ""; // 资产规格
	private String supplier = ""; // 供应厂商
	private String reason = "";// 报废原因
	private String property_type = ""; // 资产型号
	private String property_barcode = "";
	private String next_approver = "";
	private String type = "";
	private String change_user_iidd = "";// 变更账号
	private String cd_id = "";// id
	private String id = "";
	private String property_place = "";// 固定资产存放单位
	private EntityProperty property = null;
	private final String jobType = JobTypeEnum.WASTE.getJobTypeCode();
	private final String jobTypeChange = JobTypeEnum.PROPERTYCHANGE
			.getJobTypeCode();
	private String handleType = "";

	public String getCd_id() {
		return cd_id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setChange_user_iidd(String change_user_iidd) {
		this.change_user_iidd = change_user_iidd;
	}

	public String getJobType() {
		return jobType;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

	public String getHandleType() {
		return handleType;
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

	public String getDeper_life() {
		return deper_life;
	}

	public void setDeper_life(String deper_life) {
		this.deper_life = deper_life;
	}

	public String getUserd_life() {
		return userd_life;
	}

	public void setUserd_life(String userd_life) {
		this.userd_life = userd_life;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public String getSuggestion_waste() {
		return suggestion_waste;
	}

	public void setSuggestion_waste(String suggestion_waste) {
		this.suggestion_waste = suggestion_waste;
	}

	public String getProperty_kind() {
		return property_kind;
	}

	public void setProperty_kind(String property_kind) {
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

	public String getProperty_barcode() {
		return property_barcode;
	}

	public void setProperty_barcode(String property_barcode) {
		this.property_barcode = property_barcode;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public String getType() {
		return type;
	}

	public EntityProperty getProperty() {
		return property;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProperty_place() {
		return property_place;
	}

	public void setProperty_place(String property_place) {
		this.property_place = property_place;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {

		if (StringUtils.hasLength(event_code)) {
			WasteEvent event = new WasteEvent(getCurUser().getUser_iidd(),
					getCurUser().getDept_id(), event_code, seclv_code,
					usage_code, "", "", "", "", id, "", "", reason);
			if ("WASTE".endsWith(handleType)) {
				event.setJobType(JobTypeEnum.valueOf(jobType));
				event.setUserd_life(userd_life);
			} else {
				event.setJobType(JobTypeEnum.valueOf(jobTypeChange));
				event.setUser_iidd_after(change_user_iidd);
				SecDept secDept = userService
						.getSecDeptByUserId(change_user_iidd);
				if (secDept != null) {
					event.setDept_id_after(secDept.getDept_id());
				} else {
					event.setDept_id_after("");
				}

			}
			event.setEvent_type(handleType);
			event.setDepre_life(deper_life);
			event.setSuggestion(suggestion);
			event.setSuggestion_waste(suggestion_waste);
			event.setProperty_place(property_place);

			assetService.addWasteEvent(event, next_approver, handleType);
			insertCommonLog("添加报废申请作业[" + event_code + "]");
			if (!type.equals("")) {
				return type;
			} else {
				return "ok";
			}
			//
		} else {
			event_code = getCurUser().getUser_iidd()
					+ System.currentTimeMillis();
			Map<String, String> map = new HashMap<String, String>();
			map.put("id", id);
			property = assetService.getPropertyByIDBarcode(map);
		}
		return SUCCESS;
	}
}
