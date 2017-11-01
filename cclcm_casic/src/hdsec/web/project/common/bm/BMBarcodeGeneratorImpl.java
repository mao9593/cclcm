package hdsec.web.project.common.bm;

import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.basic.service.BarcodeGeneratorImpl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

/**
 * BM生成条码拓展类
 * 
 * @author gaoximin 2015-7-7
 */
public class BMBarcodeGeneratorImpl extends BarcodeGeneratorImpl {
	@Resource
	private BasicMapper basicMapper;

	private String createBarcode() {
		Map<String, Object> map = new HashMap<String, Object>();
		basicMapper.getCreateBarcode(map);
		String barcode = String.valueOf(map.get("outValue"));
		String prefix = "0000000000000";
		return prefix.substring(barcode.length()).concat(barcode);
	}

	public String createBMEntityBarcode(String entityType) throws Exception {
		String unitCode = BMPropertyUtil.getUnitCode();
		String barcodeType = BMPropertyUtil.getEntityTypeKey(entityType);
		String curYear = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		String barcode = createBarcode();
		return unitCode + barcodeType + curYear + barcode + "00";
	}
}
