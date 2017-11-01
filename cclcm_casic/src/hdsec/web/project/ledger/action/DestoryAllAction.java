package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.CycleItem;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

/**
 * 销毁检索出的所有记录
 * 
 * @author liuyaling 2015-5-31
 */
public class DestoryAllAction extends LedgerBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 公共检索条件
	private String hidduty_user_name = "";
	private String hiduser_name = "";
	private String hiddept_name = "";
	private String hidseclv_code = "";
	private Date hidstart_time = null;
	private Date hidend_time = null;
	private String hiddestory_type = "";
	private String hiddept_ids = "";
	private String hidretrieve_box_code = "";
	// paper
	private String hidpaper_barcode = "";
	private List<EntityPaper> paperLedgerList;
	// cd
	private String hidcd_barcode = "";
	private String hidconf_code = "";
	private String cd_state = "";
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

	public String getCd_state() {
		return cd_state;
	}

	public void setCd_state(String cd_state) {
		this.cd_state = cd_state;
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

	public String getHiddestory_type() {
		return hiddestory_type;
	}

	public void setHiddestory_type(String hiddestory_type) {
		this.hiddestory_type = hiddestory_type;
	}

	public String getHiddept_ids() {
		return hiddept_ids;
	}

	public void setHiddept_ids(String hiddept_ids) {
		this.hiddept_ids = hiddept_ids;
	}

	public String getHidretrieve_box_code() {
		return hidretrieve_box_code;
	}

	public void setHidretrieve_box_code(String hidretrieve_box_code) {
		this.hidretrieve_box_code = hidretrieve_box_code;
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

	@Override
	public String executeFunction() throws Exception {
		String web_url = hiddept_ids;
		List<String> dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
		if (dept_ids == null || dept_ids.size() == 0) {
			throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
		}

		// SecUser user = getCurUser();// 获取当前用户

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_sendes", true);
		map.put("duty_user_name", hidduty_user_name);
		map.put("seclv_code", hidseclv_code);
		map.put("start_time", hidstart_time);
		map.put("end_time", hidend_time);
		map.put("retrieve_box_code", hidretrieve_box_code);
		map.put("admin_dept_ids", dept_ids);
		map.put("retrieve_time_sortable", true);
		map.put("user_name", hiduser_name);
		map.put("time_type", "retrieve");
		map.put("dept_name", hiddept_name);

		if (hiddestory_type.equals("paper")) {// 文件
			map.put("paper_barcode", hidpaper_barcode);

			totalSize = ledgerService.getAllPaperLedgerSize(map);
			RowBounds rbs = new RowBounds(0, totalSize);
			paperLedgerList = ledgerService.getAllPaperLedgerList(map, rbs);
			Date destroy_time = new Date();
			for (int i = 0; i < totalSize; i++) {
				String p_id = paperLedgerList.get(i).getPaper_id().toString() + ",";
				String[] ids = p_id.split(",");
				Map<String, Object> map1 = new HashMap<String, Object>();
				map1.put("paper_ids", ids);
				map1.put("destroy_time", destroy_time);
				map1.put("duty_user_iidd", getCurUser().getUser_iidd());
				map1.put("duty_user_name", getCurUser().getUser_name());
				map1.put("duty_dept_id", getCurUser().getDept_id());
				map1.put("duty_dept_name", getCurUser().getDept_name());
				ledgerService.destroyRetrievedPapers(map1);

				CycleItem cycleitem = new CycleItem();
				cycleitem.setBarcode(paperLedgerList.get(i).getPaper_barcode());
				cycleitem.setEntity_type("PAPER");
				cycleitem.setOper_time(destroy_time);
				cycleitem.setUser_name(getCurUser().getUser_name());
				cycleitem.setDept_name(getCurUser().getDept_name());
				cycleitem.setOper("DESTORY");
				// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
				// 此处不用启动流程，job_code设置为default
				cycleitem.setJob_code("default");
				ledgerService.addCycleItem(cycleitem);
				insertCommonLog("销毁文件：条码[" + paperLedgerList.get(i).getPaper_barcode() + "]");
			}
			return "paper_success";

		} else {// 光盘

			map.put("cd_barcode", hidcd_barcode);
			map.put("cd_state", cd_state);
			map.put("conf_code", hidconf_code);

			totalSize = ledgerService.getAllCDLedgerSize(map);
			RowBounds rbs = new RowBounds(0, totalSize);
			cdLedgerList = ledgerService.getAllCDLedgerList(map, rbs);
			Date destroy_time = new Date();
			for (int i = 0; i < totalSize; i++) {
				String cd_id = cdLedgerList.get(i).getCd_id().toString() + ",";
				String[] ids = cd_id.split(",");

				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("cd_ids", ids);
				map2.put("destroy_time", destroy_time);
				map2.put("duty_user_iidd", getCurUser().getUser_iidd());
				map2.put("duty_user_name", getCurUser().getUser_name());
				map2.put("duty_dept_id", getCurUser().getDept_id());
				map2.put("duty_dept_name", getCurUser().getDept_name());
				ledgerService.destroyRetrievedCDs(map2);

				CycleItem cycleitem = new CycleItem();
				cycleitem.setBarcode(cdLedgerList.get(i).getCd_barcode());
				cycleitem.setEntity_type("CD");
				cycleitem.setOper_time(destroy_time);
				cycleitem.setUser_name(getCurUser().getUser_name());
				cycleitem.setDept_name(getCurUser().getDept_name());
				cycleitem.setOper("DESTORY");
				// 每次生命周期都记录job_code,以便查询审批记录 add by liuyaling 2015-05-21
				// 此处不用启动流程，job_code设置为default
				cycleitem.setJob_code("default");
				ledgerService.addCycleItem(cycleitem);
				insertCommonLog("销毁文件：条码[" + cdLedgerList.get(i).getCd_barcode() + "]");

			}
			return "cd_success";

		}
	}
}