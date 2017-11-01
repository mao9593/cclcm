package hdsec.web.project.ledger.model;

import hdsec.web.project.activiti.model.JobTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 涉密载体状态枚举
 * 
 * @author lixiang
 */
public enum ExportPaperEnum {
	TITLE2(2, "载体条码", false),TITLE3(3, "制作人", false),TITLE4(4, "制作部门", false),TITLE5(5, "责任人姓名", false),TITLE6(6, "责任人部门", false)
	,TITLE7(7, "密级", false),TITLE8(8, "制作方式", false),TITLE9(9, "制作审批信息", false),TITLE10(10, "产生时间", false),TITLE11(11, "是否补打", false),TITLE12(12, "打印结果", false)
	,TITLE13(13, "文件名", false),TITLE14(14, "所属项目代号", false),TITLE15(15, "文号", false),TITLE16(16, "页数", false),TITLE17(17, "纸张大小", false),TITLE18(18, "颜色", false)
	,TITLE19(19, "横纵向", false),TITLE20(20, "单双面", false),TITLE21(21, "介质状态", false),TITLE22(22, "打印机名称", false),TITLE23(23, "关键字", false),TITLE24(24, "闭环时间", false)
	,TITLE25(25, "回收方式", false),TITLE26(26, "闭环处理人(回收、外发、归档)", false),TITLE27(27, "闭环审批信息", false),TITLE28(28, "回收柜标识", false),TITLE29(29, "销毁时间", false),TITLE30(30, "销毁处理人", false)
	,TITLE31(31, "流转号", false),TITLE32(32, "到期时间", false),TITLE33(33, "到期状态", false),TITLE34(34, "用途", false),TITLE35(35, "外发接收部门", false),TITLE36(36, "备注", false),TITLE37(37, "机要号", false);
	
	private Integer key;
	private String name = "";
	private boolean is_used;

	private ExportPaperEnum(Integer key, String name, boolean is_used) {
		this.key = key;
		this.name = name;
		this.is_used = is_used;
	}

	public static boolean isStateAvailabel(int key) {
		for (ExportPaperEnum item : ExportPaperEnum.values()) {
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
	
	public static List<ExportPaperEnum> getAllExportTypeList() {
		List<ExportPaperEnum> list = new ArrayList<ExportPaperEnum>();
		for (ExportPaperEnum item : ExportPaperEnum.values()) {
			list.add(item);
		}
		return list;
	}
	
}
