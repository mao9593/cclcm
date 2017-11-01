package hdsec.web.project.ledger.action;

import hdsec.web.project.basic.model.SysConfigItem;
import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 光盘刻录结果列表
 * 
 * @author yueying
 * 
 */
public class ViewCDBurnResultAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EntityCD> cdLedgerList = null;
	private String cd_barcode = "";
	private String duty_user_name = "";
	private String seclv_code = "";
	private Date start_time = null;
	private Date end_time = null;
	private String cd_state;
	private String user_name;
	private String dept_name;
	private Integer maxChangeResultDays = 200;

	public Integer getMaxChangeResultDays() {
		return maxChangeResultDays;
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public List<EntityCD> getCDLedgerList() {
		return cdLedgerList;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode.trim();
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	@Override
	public String executeFunction() throws Exception {
		String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action";
		List<String> dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
		if (dept_ids == null || dept_ids.size() == 0) {
			throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
		}
		

		SysConfigItem maxChangeResult = basicService.getSysConfigItemValue(SysConfigItem.KEY_MAX_CHANGE_RESULT_DAYS);
		if(maxChangeResult!=null){
			maxChangeResultDays = Integer.parseInt(maxChangeResult.getItem_value());			
		}
		
		/*yaochuanqi 首次进入页面不进行查询，没有起始时间不进行查询*/
		if(start_time==null){
			return SUCCESS;
		}
		
		List<EntityCD> entityList = new ArrayList<EntityCD>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_burn_result", true);
		map.put("cd_barcode", cd_barcode);
		map.put("duty_user_name", duty_user_name);
		map.put("seclv_code", seclv_code);
		map.put("start_time", start_time);
		map.put("end_time", end_time);
		map.put("cd_state", cd_state);
		map.put("create_type", "BURN");
		map.put("admin_dept_ids_made", dept_ids);
		map.put("user_name", user_name);
		map.put("dept_name", dept_name);
		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = ledgerService.getAllCDLedgerSize(map);
		List<EntityCD> cds = ledgerService.getAllCDLedgerList(map, rbs);
		if ((null != cds && totalSize != 0) || totalSize > cds.size()) {
			cds = ledgerService.getAllCDLedgerList(map);
		}
		// List<EntityCD> cds = ledgerService.getAllCDLedgerList(map);
		for (EntityCD entity : cds) {
			entityList.add(entity);
		}
		cdLedgerList = entityList;
		return SUCCESS;
	}

	public String getCd_barcode() {
		return cd_barcode;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public String getStart_time() {

		return sdf.format(start_time);
	}

	public String getEnd_time() {
		return sdf.format(end_time);
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name.trim();
	}

	public String getCd_state() {
		return cd_state;
	}

	public void setCd_state(String cd_state) {
		this.cd_state = cd_state;
	}

}
