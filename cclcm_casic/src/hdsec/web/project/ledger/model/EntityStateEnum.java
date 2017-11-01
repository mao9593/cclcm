package hdsec.web.project.ledger.model;

import java.util.ArrayList;
import java.util.List;

/**
 * 涉密载体状态枚举
 * 
 * @author lixiang
 */
public enum EntityStateEnum {
	STATE0(0, "留用", true), STATE1(1, "已回收", true), STATE2(2, "已外发", true), STATE3(3, "已归档", false), STATE4(4, "已销毁",
			false), STATE5(5, "流转中", false), STATE6(6, "借阅中", false), STATE7(7, "申请销毁", true), STATE8(8, "申请外发", true), STATE9(
			9, "申请归档", true), STATE10(10, "待交接", false), STATE11(11, "送销", true), STATE12(12, "归属转换中", true), STATE13(
			13, "密级变更中", true), STATE14(14, "申请修改", true), STATE15(15, "待销毁", true), STATE16(16, "外发中", true), STATE17(
			17, "申请外带", true), STATE18(18, "外带中", true), STATE19(19, "已外带", true), STATE20(20, "申请合并", true);
	private Integer key;
	private String name = "";
	private boolean is_used;

	private EntityStateEnum(Integer key, String name, boolean is_used) {
		this.key = key;
		this.name = name;
		this.is_used = is_used;
	}

	public static boolean isStateAvailabel(int key) {
		for (EntityStateEnum item : EntityStateEnum.values()) {
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

	// 普通用户状态列表
	public static List<EntityStateEnum> getUserEntityStateList() {
		List<EntityStateEnum> list = new ArrayList<EntityStateEnum>();
		for (EntityStateEnum item : EntityStateEnum.values()) {
			if (item.isEntityStateUsed()) {
				list.add(item);
			}
		}
		return list;
	}

	// 闭环管理员状态列表
	public static List<EntityStateEnum> getCycleAdminEntityStateList() {
		List<EntityStateEnum> list = new ArrayList<EntityStateEnum>();
		for (EntityStateEnum item : EntityStateEnum.values()) {
			if (item.isEntityStateUsed()) {
				list.add(item);
			}
		}
		list.add(EntityStateEnum.STATE4);
		return list;
	}

	// 档案管理员状态列表
	public static List<EntityStateEnum> getFileAdminEntityStateList() {
		List<EntityStateEnum> list = new ArrayList<EntityStateEnum>();
		for (EntityStateEnum item : EntityStateEnum.values()) {
			if (item.isEntityStateUsed()) {
				list.add(item);
			}
		}
		list.add(EntityStateEnum.STATE3);
		return list;
	}

	// 所有可用状态（如安全管理员）
	public static List<EntityStateEnum> getEntityStateList() {
		List<EntityStateEnum> list = new ArrayList<EntityStateEnum>();
		for (EntityStateEnum item : EntityStateEnum.values()) {
			if (item.isEntityStateUsed()) {
				list.add(item);
			}
		}
		list.add(EntityStateEnum.STATE4);
		list.add(EntityStateEnum.STATE3);
		return list;
	}
}
