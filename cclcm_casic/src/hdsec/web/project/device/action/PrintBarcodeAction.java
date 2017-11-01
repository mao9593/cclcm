package hdsec.web.project.device.action;

import hdsec.web.project.basic.model.SysBarcode;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;
import hdsec.web.project.device.model.EntityDevice;

import java.util.List;

/**
 * 打印磁介质条码
 * 
 * @author lixiang
 */
public class PrintBarcodeAction extends DeviceBaseAction {
	private static final long serialVersionUID = 1L;
	private String device_barcode = "";// 载体一维码
	private String device_barcodes = "";// 载体条码结果

	public String getDevice_barcode() {
		return device_barcode;
	}

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
	}

	public String getDevice_barcodes() {
		return device_barcodes;
	}

	public void setDevice_barcodes(String device_barcodes) {
		this.device_barcodes = device_barcodes;
	}

	// 获取unit.CompanyType haojia 20160629
	String strCompayType = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.CompanyType");

	@Override
	public String executeFunction() throws Exception {
		SysBarcode sysBarcode = null;
		EntityDevice device = deviceService.getDeviceByBarcode(device_barcode);
		Integer seclv_code = device.getSeclv_code();
		String user_name = device.getUser_name();
		String file_title = device.getDevice_name();
		String dept_id = device.getDept_id();
		StringBuffer sb = new StringBuffer();
		String unitCode = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "unit.code");// 5S
		List<SysUsage> usageList = basicService.getUsageByUsageName("磁介质");
		if (usageList != null && usageList.size() > 0) {
			if (usageList.size() > 1) {
				throw new Exception("存在多个用途[磁介质]");
			} else {
				String usage_code = usageList.get(0).getUsage_code();
				sysBarcode = basicService.getSysBarcode(seclv_code, usage_code, "");
			}
		} else {
			sysBarcode = basicService.getDefaultSysBarcode();
		}
		if (sysBarcode != null) {
			Integer form = sysBarcode.getForm();
			if (unitCode != "" && strCompayType.equalsIgnoreCase("CAEP")) {
				// if (unitCode != "" && unitCode.equalsIgnoreCase("5S")) {
				if (form == 1) {// 一维码
					sb.append("1" + "#" + device_barcode + "#" + device_barcode);
				} else if (form == 2) {// 二维码(QR)
					sb.append("2" + "#" + device_barcode + "#" + device_barcode);
				} else if (form == 3) {// 二维码(PDF417)
					sb.append("3" + "#" + device_barcode + "#" + device_barcode);
				}
			} else {
				if (form == 1) {// 一维码
					sb.append("1" + "#" + device_barcode + "#" + device_barcode);
				} else if (form == 2) {// 二维码(QR)
					sb.append("2"
							+ "#"
							+ device_barcode
							+ "#"
							+ basicService.buildTwoDimenosionalBarcode("00", "00", user_name,
									String.valueOf(seclv_code), "D", "C", "0000", "0000", file_title, dept_id,
									device_barcode));
				} else if (form == 3) {// 二维码(PDF417)
					sb.append("3"
							+ "#"
							+ device_barcode
							+ "#"
							+ basicService.buildTwoDimenosionalBarcode("00", "00", user_name,
									String.valueOf(seclv_code), "D", "C", "0000", "0000", file_title, dept_id,
									device_barcode));
				}
			}
			device_barcodes = sb.toString();

		}

		return SUCCESS;
	}
}
