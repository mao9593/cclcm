package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.user.model.SecLevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 *  已拒绝外发列表（光盘外发）
 * @author Mr
 * 
 */
public class ViewRejectSendCDAction extends LedgerBaseAction{

	private static final long serialVersionUID = 1L;
	private List<EntityCD> cdLedgerList;
	private String cd_barcode;
	private List<String> dept_ids;
	private String duty_user_name;
	private String file_list;
	
	
	public String getFile_list() {
		return file_list;
	}

	public void setFile_list(String file_list) {
		this.file_list = file_list;
	}

	public List<EntityCD> getCdLedgerList() {
		return cdLedgerList;
	}

	public void setCdLedgerList(List<EntityCD> cdLedgerList) {
		this.cdLedgerList = cdLedgerList;
	}

	public String getCd_barcode() {
		return cd_barcode;
	}

	public void setCd_barcode(String cd_barcode) {
		this.cd_barcode = cd_barcode;
	}

	public String getDuty_user_name() {
		return duty_user_name;
	}

	public void setDuty_user_name(String duty_user_name) {
		this.duty_user_name = duty_user_name;
	}

	public void setDept_ids(List<String> dept_ids) {
		this.dept_ids = dept_ids;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public String getDept_ids() {
		String ret = "";
		for (String d_item : dept_ids) {
			ret += d_item + ",";
		}
		if (ret.endsWith(",")) {
			ret = ret.substring(0, ret.length() - 1);
		}
		return ret;
	}

	
	@Override
	public String executeFunction() throws Exception {
		String web_url = getModuleName().toLowerCase() + "/" + getTitleName().toLowerCase() + ".action";
		dept_ids = basicService.getAdminDeptIdList(getCurUser().getUser_iidd(), web_url);
		if (dept_ids == null || dept_ids.size() == 0) {
			throw new Exception("没有配置管理部门,请联系系统管理员进行配置");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("job_status", "true");// 审批通过的paper
		map.put("admin_dept_ids", dept_ids);
		map.put("file_list", file_list);
		map.put("entity_type", "CD");
		map.put("cd_barcode", cd_barcode);
		map.put("duty_user_name", duty_user_name);
		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = ledgerService.getAllRejectCDSize(map);
		cdLedgerList = ledgerService.getAllRejectCDList(map, rbs);
		return SUCCESS;
	}

}
