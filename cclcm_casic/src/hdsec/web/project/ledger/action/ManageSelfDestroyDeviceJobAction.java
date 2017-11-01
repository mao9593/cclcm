package hdsec.web.project.ledger.action;

import hdsec.web.project.basic.model.SysPrinter;
import hdsec.web.project.device.model.EntityDevice;
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
 * 查看自主销毁申请记录
 * 
 * @author haojia
 */
public class ManageSelfDestroyDeviceJobAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EntityDevice> deviceLedgerList = null;
	private String seclv_code = "";
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private String device_barcode = "";

	public List<EntityDevice> getDeviceLedgerList() {
		return deviceLedgerList;
	}

	public void setDeviceLedgerList(List<EntityDevice> deviceLedgerList) {
		this.deviceLedgerList = deviceLedgerList;
	}

	public String getDevice_barcode() {
		return device_barcode;
	}

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getStartTime() {
		return startTime == null ? "" : sdf.format(startTime);
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime == null ? "" : sdf.format(endTime);
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<SysPrinter> getPrinterList() {
		return basicService.getSysPrinterList();
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("seclv_code", seclv_code);
		map.put("start_time", startTime);
		map.put("end_time", endTime);
		map.put("device_barcode", device_barcode);
		map.put("job_status", job_status);
		map.put("scope", "PERSON");
		map.put("has_job_code", true);

		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		totalSize = ledgerService.getSelfDestroyDeviceSize(map);
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		deviceLedgerList = ledgerService.getSelfDestroyDevcieList(map, rbs);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "DESTROY_DEVICE_BYSELF", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "DESTROY_DEVICE_BYSELF", 3);

		return SUCCESS;
	}
}
