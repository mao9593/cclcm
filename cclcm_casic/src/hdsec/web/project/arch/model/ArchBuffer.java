package hdsec.web.project.arch.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 档案录入缓存类
 * 
 * @author handouwang
 * 
 *         2015-8-2/
 */
public class ArchBuffer {
	private static Map<String, ArchValue> archMap = null;

	private static Map<String, ArchValue> getArchMapInstance() {
		if (archMap == null) {
			archMap = new HashMap<String, ArchValue>();
		}
		return archMap;
	}

	public static synchronized void setArchBuffer(String template_id,
			ArchValue arch) {
		getArchMapInstance().put(template_id, arch);
	}

	public static synchronized ArchValue getArchBuffer(String template_id) {
		return getArchMapInstance().get(template_id);
	}

	public static synchronized void setArchBuffer(String template_id,
			Map<String, Object> map) {
		getArchMapInstance().put(template_id, transMapToArch(map));
	}

	private static ArchValue transMapToArch(Map<String, Object> map) {
		ArchValue arch = new ArchValue();
		arch.setBarcode((String) map.get("BARCODE"));
		arch.setDos_num((String) map.get("DOS_NUM"));
		arch.setArch_num((String) map.get("ARCH_NUM"));
		arch.setType_code((String) map.get("TYPE_CODE"));
		arch.setFile_title((String) map.get("FILE_TITLE"));
		arch.setFile_num((String) map.get("FILE_NUM"));
		arch.setPage_num((String) map.get("PAGE_NUM"));
		arch.setCount((String) map.get("COUNT"));
		arch.setTotal_page((String) map.get("TOTAL_PAGE"));
		arch.setSeclv_code((String) map.get("SECLV_CODE"));
		arch.setFile_date((String) map.get("FILE_DATE"));
		arch.setFile_carr((String) map.get("FILE_CARR"));
		arch.setKeep_limit((String) map.get("KEEP_LIMIT"));
		arch.setStatus((int) map.get("STATUS"));
		arch.setSumm((String) map.get("SUMM"));
		arch.setDos_id((int) map.get("DOS_ID"));
		arch.setT01((String) map.get("T01"));
		arch.setT02((String) map.get("T02"));
		arch.setT03((String) map.get("T03"));
		arch.setT04((String) map.get("T04"));
		arch.setT05((String) map.get("T05"));
		arch.setT06((String) map.get("T06"));
		arch.setT07((String) map.get("T07"));
		arch.setT08((String) map.get("T08"));
		arch.setT09((String) map.get("T09"));
		arch.setT10((String) map.get("T10"));
		arch.setT11((String) map.get("T11"));
		arch.setT12((String) map.get("T12"));
		arch.setT13((String) map.get("T13"));
		arch.setT14((String) map.get("T14"));
		arch.setT15((String) map.get("T15"));
		arch.setT16((String) map.get("T16"));
		arch.setT17((String) map.get("T17"));
		arch.setT18((String) map.get("T18"));
		arch.setT19((String) map.get("T19"));
		arch.setT20((String) map.get("T20"));
		arch.setT21((String) map.get("T21"));
		arch.setT22((String) map.get("T22"));
		arch.setT23((String) map.get("T23"));
		arch.setT24((String) map.get("T24"));
		arch.setT25((String) map.get("T25"));
		return arch;
	}
}
