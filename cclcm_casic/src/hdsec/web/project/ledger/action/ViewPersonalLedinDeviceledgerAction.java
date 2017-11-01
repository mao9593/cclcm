package hdsec.web.project.ledger.action;

import hdsec.web.project.device.model.DeviceStatusEnum;
import hdsec.web.project.device.model.DeviceType;
import hdsec.web.project.device.model.EntityDevice;
import hdsec.web.project.user.model.SecLevel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 个人录入磁介质台账管理列表
 * 
 * @author haojia
 * 
 */
public class ViewPersonalLedinDeviceledgerAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private List<EntityDevice> deviceLedgerList;
	private String device_barcode = "";
	private String seclv_code = "";
	private Integer med_type;
	private String device_status = "";
	private String device_series = "";
	private String device_name = "";
	private String secError = "N";

	public String getSecError() {
		return secError;
	}

	public void setSecError(String secError) {
		this.secError = secError;
	}

	public String getDevice_name() {
		return device_name;
	}

	public void setDevice_name(String device_name) {
		this.device_name = device_name;
	}

	public String getDevice_series() {
		return device_series;
	}

	public void setDevice_series(String device_series) {
		this.device_series = device_series;
	}

	public List<EntityDevice> getDeviceLedgerList() {
		return deviceLedgerList;
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

	public Integer getMed_type() {
		return med_type;
	}

	public void setMed_type(Integer med_type) {
		this.med_type = med_type;
	}

	public String getDevice_status() {
		return device_status;
	}

	public void setDevice_status(String device_status) {
		this.device_status = device_status;
	}

	public List<DeviceStatusEnum> getDsList() {
		List<DeviceStatusEnum> tmpList = new ArrayList<DeviceStatusEnum>();
		tmpList.add(DeviceStatusEnum.DS0);
		tmpList.add(DeviceStatusEnum.DS1);
		tmpList.add(DeviceStatusEnum.DS2);
		tmpList.add(DeviceStatusEnum.DS3);
		tmpList.add(DeviceStatusEnum.DS4);
		tmpList.add(DeviceStatusEnum.DS5);
		tmpList.add(DeviceStatusEnum.DS6);
		tmpList.add(DeviceStatusEnum.DS7);
		return tmpList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	public List<DeviceType> getDeviceTypeList() {
		return deviceService.getDeviceTypeList();
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("device_barcode", device_barcode);
		map.put("seclv_code", seclv_code);
		map.put("device_series", device_series);
		map.put("device_name", device_name);
		map.put("scope", "PERSON");

		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = ledgerService.getAllDeviceLedgerSize(map);
		deviceLedgerList = ledgerService.getAllDeviceLedgerList(map, rbs);

		return SUCCESS;
	}
}
