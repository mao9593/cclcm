package hdsec.web.project.ledger.action;

import hdsec.web.project.basic.model.RejectRecord;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 *  已拒绝外发列表（文件外发）
 * @author Mr
 * 
 */
public class ViewRejectSendPaperAction extends LedgerBaseAction{

	private static final long serialVersionUID = 1L;
	private List<EntityPaper> paperLedgerList;
	private String paper_barcode;
	private String duty_user_name;
	private List<String> dept_ids;
	private String file_title;
	private String entity_type="paper";
	private List<RejectRecord> rejectRecordList;
	
	public String getEntity_type() {
		return entity_type;
	}

	public void setEntity_type(String entity_type) {
		this.entity_type = entity_type;
	}

	public String getFile_title() {
		return file_title;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title;
	}

	public String getPaper_barcode() {
		return paper_barcode;
	}

	public void setPaper_barcode(String paper_barcode) {
		this.paper_barcode = paper_barcode;
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

	public List<RejectRecord> getRejectRecordList() {
		return rejectRecordList;
	}

	public void setRejectRecordList(List<RejectRecord> rejectRecordList) {
		this.rejectRecordList = rejectRecordList;
	}

	public List<EntityPaper> getPaperLedgerList() {
		return paperLedgerList;
	}

	public void setPaperLedgerList(List<EntityPaper> paperLedgerList) {
		this.paperLedgerList = paperLedgerList;
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
		map.put("paper_barcode",paper_barcode);//文件条码
		map.put("job_status", "true");// 审批通过的paper
		map.put("entity_type", entity_type);// 拒绝表中为类型为paper
		map.put("admin_dept_ids", dept_ids);
		map.put("duty_user_name", duty_user_name);//责任人
		map.put("file_title", file_title);
		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = ledgerService.getAllRejectPaperSize(map);
		rejectRecordList = ledgerService.getAllRejectPaperList(map, rbs);
		return SUCCESS;
	}

}
