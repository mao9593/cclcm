package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.Item;

/**
 * 添加项目
 * 
 * @author handouwang
 * 
 *         2015-7-27/
 */
public class AddItemAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
	private int type_id;
	private String item_code = "";
	private String summ = "";
	private String add = "N";

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	@Override
	public String executeFunction() throws Exception {
		if (add.equalsIgnoreCase("Y")) {// 处理添加操作
			if (archService.isItemExistByCode(item_code)) {
				throw new Exception("子项代号已经存在，不能重复添加。");
			} else {
				Item item = new Item(item_code, summ, type_id);
				archService.addItem(item);
				insertCommonLog("添加项目[" + item_code + "][" + summ + "].");
				return "insert";
			}
		} else {
			return SUCCESS;
		}
	}
}
