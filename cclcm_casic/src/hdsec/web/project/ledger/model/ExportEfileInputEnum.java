package hdsec.web.project.ledger.model;

import java.util.ArrayList;
import java.util.List;

public enum ExportEfileInputEnum {
	TITLE2(2, "责任人", false), TITLE3(3, "责任人部门", false), TITLE4(4, "文件列表", false), TITLE5(5, "作业密级", false), TITLE6(6,
			"导入时间", false), TITLE7(7, "用途", false), TITLE8(8, "介质类型", false), TITLE9(9, "申请状态", false);

	private Integer key;
	private String name = "";
	private boolean is_used;

	private ExportEfileInputEnum(Integer key, String name, boolean is_used) {
		this.key = key;
		this.name = name;
		this.is_used = is_used;
	}

	public static boolean isStateAvailabel(int key) {
		for (ExportEfileInputEnum item : ExportEfileInputEnum.values()) {
			if (item.getKey().intValue() == key) {
				return true;
			}
		}
		return false;
	}

	public Integer getKey() {
		return this.key;
	}

	public String getName() {
		return this.name;
	}

	public boolean isEntityStateUsed() {
		return this.is_used;
	}

	public void enableUsed() {

		this.is_used = true;
	}

	public void disableUsed() {

		this.is_used = false;
	}

	public void switchUsed() {
		this.is_used = !is_used;

	}

	public static List<ExportEfileInputEnum> getAllExportTypeList() {
		List<ExportEfileInputEnum> list = new ArrayList<ExportEfileInputEnum>();
		for (ExportEfileInputEnum item : ExportEfileInputEnum.values()) {
			list.add(item);
		}
		return list;
	}

}
