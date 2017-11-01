package hdsec.web.project.storage.action;

import hdsec.web.project.basic.model.SysBarcode;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.storage.model.EntityStorage;

import java.util.List;

/**
 * 打印存储介质条码
 * 
 * @author lixiang
 */
public class PrintBarcodeAction extends StorageBaseAction {
	private static final long serialVersionUID = 1L;
	private String storage_barcode = "";// 载体一维码
	private String storage_barcodes = "";// 载体条码结果

	public String getStorage_barcode() {
		return storage_barcode;
	}

	public void setStorage_barcode(String storage_barcode) {
		this.storage_barcode = storage_barcode;
	}

	public String getStorage_barcodes() {
		return storage_barcodes;
	}

	public void setStorage_barcodes(String storage_barcodes) {
		this.storage_barcodes = storage_barcodes;
	}

	@Override
	public String executeFunction() throws Exception {
		SysBarcode sysBarcode = null;
		EntityStorage storage = storageService.getStorageByBaecode(storage_barcode);
		Integer seclv_code = storage.getSeclv_code();
		String user_name = storage.getInput_user_name();
		String file_title = storage.getStorage_name();
		String dept_id = storage.getDept_id();
		StringBuffer sb = new StringBuffer();
		List<SysUsage> usageList = basicService.getUsageByUsageName("存储介质");
		if (usageList != null && usageList.size() > 0) {
			if (usageList.size() > 1) {
				throw new Exception("存在多个用途[存储介质]");
			} else {
				String usage_code = usageList.get(0).getUsage_code();
				sysBarcode = basicService.getSysBarcode(seclv_code, usage_code, "");
			}
		} else {
			sysBarcode = basicService.getDefaultSysBarcode();
		}
		if (sysBarcode != null) {
			Integer form = sysBarcode.getForm();
			if (form == 1) {// 一维码
				sb.append("1" + "#" + storage_barcode + "#" + storage_barcode);
			} else if (form == 2) {// 二维码(QR)
				sb.append("2"
						+ "#"
						+ storage_barcode
						+ "#"
						+ basicService.buildTwoDimenosionalBarcode("00", "00", user_name, String.valueOf(seclv_code),
								"D", "Y", "0000", "0000", file_title, dept_id, storage_barcode));
			} else if (form == 3) {// 二维码(PDF417)
				sb.append("3"
						+ "#"
						+ storage_barcode
						+ "#"
						+ basicService.buildTwoDimenosionalBarcode("00", "00", user_name, String.valueOf(seclv_code),
								"D", "Y", "0000", "0000", file_title, dept_id, storage_barcode));
			}
			storage_barcodes = sb.toString();
		}

		return SUCCESS;
	}

}
