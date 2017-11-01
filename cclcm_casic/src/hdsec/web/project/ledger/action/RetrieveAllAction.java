package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.user.model.SecUser;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

/**
 * 回收全部检索结果
 * 
 * @author liuyaling 2015-5-29
 */
public class RetrieveAllAction extends LedgerBaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String hidpaper_barcode = "";
	private String hidduty_user_name = "";
	private String hiduser_name = "";
	private String hiddept_name = "";
	private String hidseclv_code = "";
	private Date hidstart_time = null;
	private Date hidend_time = null;
	private String hidretrieve_type = "";
	private String hiddept_ids;
	private String hidcd_barcode = "";
	private String hidconf_code = "";
	private String hidcomment = "";
	private List<EntityPaper> paperLedgerList;
	private List<EntityCD> cdLedgerList;

	public String getHidstart_time() {
		return sdf.format(hidstart_time);
	}

	public void setHidstart_time(Date hidstart_time) {
		this.hidstart_time = hidstart_time;
	}

	public String getHidend_time() {
		return sdf.format(hidend_time);
	}

	public void setHidend_time(Date hidend_time) {
		this.hidend_time = hidend_time;
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

	public List<EntityPaper> getPaperLedgerList() {
		return paperLedgerList;
	}

	public void setPaperLedgerList(List<EntityPaper> paperLedgerList) {
		this.paperLedgerList = paperLedgerList;
	}

	public String getHidpaper_barcode() {
		return hidpaper_barcode;
	}

	public void setHidpaper_barcode(String hidpaper_barcode) {
		this.hidpaper_barcode = hidpaper_barcode;
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

	public String getHiddept_name() {
		return hiddept_name;
	}

	public void setHiddept_name(String hiddept_name) {
		this.hiddept_name = hiddept_name;
	}

	public String getHidseclv_code() {
		return hidseclv_code;
	}

	public void setHidseclv_code(String hidseclv_code) {
		this.hidseclv_code = hidseclv_code;
	}

	public String getHidretrieve_type() {
		return hidretrieve_type;
	}

	public void setHidretrieve_type(String hidretrieve_type) {
		this.hidretrieve_type = hidretrieve_type;
	}

	public String getHidcomment() {
		return hidcomment;
	}

	public void setHidcomment(String hidcomment) {
		this.hidcomment = hidcomment;
	}

	@Override
	public String executeFunction() throws Exception {
		String web_url = hiddept_ids;
		List<String> dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
		if (dept_ids == null || dept_ids.size() == 0) {
			throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
		}

		// List<String> dept_ids = new ArrayList<String>();
		// if (hiddept_ids == null || hiddept_ids == "") {
		// throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
		// } else {
		// for (String ids : hiddept_ids.split(",")) {
		// dept_ids.add(ids);
		// }
		// }
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("duty_user_name", hidduty_user_name);
		map.put("seclv_code", hidseclv_code);
		map.put("start_time", hidstart_time);
		map.put("end_time", hidend_time);
		map.put("job_status", "true");// 审批通过的paper
		map.put("admin_dept_ids", dept_ids);
		map.put("user_name", hiduser_name);
		map.put("dept_name", hiddept_name);

		SecUser user = getCurUser();// 获取当前用户

		if (hidretrieve_type.equals("paper")) {
			map.put("paper_barcode", hidpaper_barcode);
			map.put("paper_state", 7);// 申请销毁

			totalSize = ledgerService.getAllPaperLedgerSize(map);
			RowBounds rbs = new RowBounds(0, totalSize);
			paperLedgerList = ledgerService.getAllPaperLedgerList(map, rbs);

			for (int i = 0; i < totalSize; i++) {
				ledgerService.submitRetrievePaper(paperLedgerList.get(i).getPaper_id().toString(), user);
				ledgerService.updateRetrieveComment(paperLedgerList.get(i).getPaper_id().toString(), hidcomment);
				insertCommonLog("回收文件：条码[" + paperLedgerList.get(i).getPaper_barcode() + "]");
			}
			return "paper_success";
		} else {// 光盘
			map.put("cd_barcode", hidcd_barcode);
			map.put("cd_state", 7);// 申请销毁
			map.put("conf_code", hidconf_code);

			totalSize = ledgerService.getAllCDLedgerSize(map);
			RowBounds rbs = new RowBounds(0, totalSize);
			cdLedgerList = ledgerService.getAllCDLedgerList(map, rbs);
			for (int i = 0; i < totalSize; i++) {
				ledgerService.submitRetrieveCD(cdLedgerList.get(i).getCd_id().toString(), user);
				insertCommonLog("回收光盘：条码[" + cdLedgerList.get(i).getCd_barcode() + "]");
			}

			return "cd_success";
		}

	}
}