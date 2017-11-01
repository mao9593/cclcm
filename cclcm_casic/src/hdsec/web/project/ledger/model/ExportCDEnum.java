package hdsec.web.project.ledger.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 涉密载体状态枚举
 * 
 * @author lixiang
 */
public enum ExportCDEnum {
	TITLE2(2, "光盘编号", false),TITLE3(3, "当前状态", false),TITLE4(4, "责任人", false),TITLE5(5, "责任人部门", false),TITLE6(6, "刻录结果", false)
	,TITLE7(7, "作业密级", false),TITLE8(8, "制作方式", false),TITLE9(9, "制作审批信息", false),TITLE10(10, "刻录时间", false),TITLE11(11, "刻录机器", false),TITLE12(12, "刻录IP", false)
	,TITLE13(13, "光盘类型", false),TITLE14(14, "所属项目", false),TITLE15(15, "卷标名称", false),TITLE16(16, "作业代号", false),TITLE17(17, "数据类型", false),TITLE18(18, "文件列表", false)
	,TITLE19(19, "文件数量", false),TITLE20(20, "保密编号", false),TITLE21(21, "申请人", false),TITLE22(22, "申请人部门", false),TITLE23(23, "刻录类型", false),TITLE24(24, "是否补刻", false)
	,TITLE25(25, "流转号", false),TITLE26(26, "闭环处理人(回收、外发、归档)", false),TITLE27(27, "闭环审批信息", false),TITLE28(28, "回收方式", false),TITLE29(29, "回收柜标识", false),TITLE30(30, "回收时间", false)
	,TITLE32(32, "销毁人", false),TITLE33(33, "销毁时间", false),TITLE34(34, "到期时间", false),TITLE35(35, "到期状态", false),TITLE36(36, "用途", false)
	,TITLE37(37, "制作人", false),TITLE38(38, "制作部门", false),TITLE39(39, "外发接收部门", false),TITLE40(40, "机要号", false);
	
	private Integer key;
	private String name = "";
	private boolean is_used;

	private ExportCDEnum(Integer key, String name, boolean is_used) {
		this.key = key;
		this.name = name;
		this.is_used = is_used;
	}

	public static boolean isStateAvailabel(int key) {
		for (ExportCDEnum item : ExportCDEnum.values()) {
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
	
	public static List<ExportCDEnum> getAllExportTypeList() {
		List<ExportCDEnum> list = new ArrayList<ExportCDEnum>();
		for (ExportCDEnum item : ExportCDEnum.values()) {
			list.add(item);
		}
		return list;
	}
	
}
