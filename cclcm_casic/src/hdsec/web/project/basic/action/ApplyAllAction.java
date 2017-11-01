package hdsec.web.project.basic.action;

import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.user.model.SecUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

/**
 * 全部归档处理
 * 
 * @author liuyaling 2015-5-31
 */
public class ApplyAllAction extends BasicBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<EntityPaper> paperLedgerList;
	private List<EntityCD> cdLedgerList;

	private String hidduty_user_name = "";
	private String hiduser_name = "";
	private String hidseclv_code = "";
	private String hiddept_id = "";
	private String hidstart_time = "";
	private String hidend_time = "";
	private String hiddept_ids = "";
	private String hidapply_type = "";

	private String hidpaper_barcode = "";

	private String hidcd_barcode = "";
	private String hidconf_code = "";

	private String ajax_type = "";

	public void setAjax_type(String ajax_type) {
		this.ajax_type = ajax_type;
	}

	public String getHidduty_user_name() {
		return hidduty_user_name;
	}

	public void setHidduty_user_name(String hidduty_user_name) {
		this.hidduty_user_name = hidduty_user_name;
	}

	public String getHiduser_name() {
		return hiduser_name;
	}

	public void setHiduser_name(String hiduser_name) {
		this.hiduser_name = hiduser_name;
	}

	public String getHidseclv_code() {
		return hidseclv_code;
	}

	public void setHidseclv_code(String hidseclv_code) {
		this.hidseclv_code = hidseclv_code;
	}

	public String getHiddept_id() {
		return hiddept_id;
	}

	public void setHiddept_id(String hiddept_id) {
		this.hiddept_id = hiddept_id;
	}

	public String getHidstart_time() {
		return sdf.format(hidstart_time);
	}

	public void setHidstart_time(String hidstart_time) {
		this.hidstart_time = hidstart_time;
	}

	public String getHidend_time() {
		return sdf.format(hidend_time);
	}

	public void setHidend_time(String hidend_time) {
		this.hidend_time = hidend_time;
	}

	public String getHidpaper_barcode() {
		return hidpaper_barcode;
	}

	public void setHidpaper_barcode(String hidpaper_barcode) {
		this.hidpaper_barcode = hidpaper_barcode;
	}

	public String getHidcd_barcode() {
		return hidcd_barcode;
	}

	public void setHidcd_barcode(String hidcd_barcode) {
		this.hidcd_barcode = hidcd_barcode;
	}

	public String getHidconf_code() {
		return hidconf_code;
	}

	public void setHidconf_code(String hidconf_code) {
		this.hidconf_code = hidconf_code;
	}

	public String getHiddept_ids() {
		return hiddept_ids;
	}

	public void setHiddept_ids(String hiddept_ids) {
		this.hiddept_ids = hiddept_ids;
	}

	public String getHidapply_type() {
		return hidapply_type;
	}

	public void setHidapply_type(String hidapply_type) {
		this.hidapply_type = hidapply_type;
	}

	@Override
	public String executeFunction() throws Exception {
		List<String> dept_ids = new ArrayList<String>();
		if (hiddept_ids == null || hiddept_ids == "") {
			throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
		} else {
			for (String ids : hiddept_ids.split(",")) {
				dept_ids.add(ids);
			}
		}

		SecUser user = getCurUser();

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("duty_user_name", hidduty_user_name);
		map.put("seclv_code", hidseclv_code);
		map.put("start_time", hidstart_time);
		map.put("end_time", hidend_time);
		map.put("job_status", "true");// 审批通过的paper
		map.put("admin_dept_ids", dept_ids);
		map.put("duty_dept_id", hiddept_id);
		map.put("user_name", hiduser_name);

		if (hidapply_type.equalsIgnoreCase("paper")) {// 文件归档
			map.put("paper_barcode", hidpaper_barcode);
			map.put("paper_state", 9);// 申请归档

			totalSize = ledgerService.getAllPaperLedgerSize(map);
			RowBounds rbs = new RowBounds(0, totalSize);
			paperLedgerList = ledgerService.getAllPaperLedgerList(map, rbs);
			for (int i = 0; i < totalSize; i++) {
				basicService.submitApplyFile(paperLedgerList.get(i).getPaper_id().toString(), "PAPER", user);// item_id是载体的id
				insertCommonLog("确认接收归档文件[" + paperLedgerList.get(i).getPaper_id() + "]");
			}
			return ajax_type.equalsIgnoreCase("ajax") ? null : "paper_success";
		} else {// 光盘归档
			map.put("cd_barcode", hidcd_barcode);
			map.put("cd_state", 9);// 申请归档
			map.put("conf_code", hidconf_code);

			totalSize = ledgerService.getAllCDLedgerSize(map);
			RowBounds rbs = new RowBounds(0, totalSize);
			cdLedgerList = ledgerService.getAllCDLedgerList(map, rbs);
			for (int i = 0; i < totalSize; i++) {
				basicService.submitApplyFile(cdLedgerList.get(i).getCd_id().toString(), "CD", user);// item_id是载体的id
				insertCommonLog("确认接收归档光盘[" + cdLedgerList.get(i).getCd_id() + "]");
			}
			return ajax_type.equalsIgnoreCase("ajax") ? null : "cd_success";
		}

	}
}