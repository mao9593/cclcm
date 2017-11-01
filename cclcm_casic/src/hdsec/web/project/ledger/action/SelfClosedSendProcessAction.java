package hdsec.web.project.ledger.action;

import hdsec.web.project.ledger.model.EntityCD;
import hdsec.web.project.ledger.model.EntityPaper;
import hdsec.web.project.ledger.model.EntityStateEnum;

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
 * 个人闭环申请外发状态任务
 * 
 * guojiao 20160302
 */
public class SelfClosedSendProcessAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String barcode = "";
	private String media_state = "";
	private String media = "";
	private Integer seclv_code = null;
	private Date startTime = null;
	private Date endTime = null;
	private String researchFlag = "N";
	private List<EntityPaper> paperLedgerList = null;
	private List<EntityCD> cdLedgerList = null;

	public List<EntityPaper> getPaperLedgerList() {
		return paperLedgerList;
	}

	public List<EntityCD> getCdLedgerList() {
		return cdLedgerList;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getMedia_state() {
		return media_state;
	}

	public void setMedia_state(String media_state) {
		this.media_state = media_state;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public Integer getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(Integer seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getStartTime() {
		return sdf.format(startTime);
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return sdf.format(endTime);
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getResearchFlag() {
		return researchFlag;
	}

	public void setResearchFlag(String researchFlag) {
		this.researchFlag = researchFlag;
	}

	public List<EntityStateEnum> getStateList() {
		List<EntityStateEnum> list = EntityStateEnum.getEntityStateList();
		return list;
	}

	@Override
	public String executeFunction() throws Exception {
		if (researchFlag.equals("Y")) {
			if (media.equals("paper")) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("duty_user_iidd", getCurUser().getUser_iidd());
				map.put("seclv_code", seclv_code);
				map.put("start_time", startTime);
				map.put("end_time", endTime);
				map.put("paper_state", 8);// 申请外发
				map.put("job_status", "true");// 审批通过的paper
				map.put("start_time_sortable", true);
				String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
				if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
					page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
				}
				beginIndex = page * pageSize;
				RowBounds rbs = new RowBounds(beginIndex, pageSize);
				totalSize = ledgerService.getAllPaperLedgerSize(map);
				paperLedgerList = ledgerService.getAllPaperLedgerList(map, rbs);
			} else {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("duty_user_iidd", getCurUser().getUser_iidd());
				map.put("seclv_code", seclv_code);
				map.put("start_time", startTime);
				map.put("end_time", endTime);
				map.put("cd_state", 8);// 申请外发
				map.put("job_status", "true");// 审批通过的paper
				map.put("start_time_sortable", true);
				String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
				if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
					page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
				}
				beginIndex = page * pageSize;
				RowBounds rbs = new RowBounds(beginIndex, pageSize);
				totalSize = ledgerService.getAllCDLedgerSize(map);
				List<EntityCD> cds = ledgerService.getAllCDLedgerList(map, rbs);
				List<EntityCD> entityList = new ArrayList<EntityCD>();
				if ((null != cds && totalSize != 0) || totalSize > cds.size()) {
					cds = ledgerService.getAllCDLedgerList(map);
				}
				for (EntityCD entity : cds) {
					entityList.add(entity);
				}
				cdLedgerList = entityList;
			}
		} else {
			researchFlag = "WARING";
		}
		return SUCCESS;
	}

}