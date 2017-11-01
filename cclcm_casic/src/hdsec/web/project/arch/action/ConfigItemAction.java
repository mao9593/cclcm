package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.Item;

/**
 * 配置项目(添加案卷等)
 * 
 * @author handouwang
 * 
 *         2015-7-28/
 */
public class ConfigItemAction extends ArchBaseAction {

	private static final long serialVersionUID = 1L;
	private int id = 0;
	private Item item = null;

	public Item getItem() {
		return item;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String executeFunction() throws Exception {
		if (id != 0) {
			item = archService.getItemById(id);
			return SUCCESS;
		} else {
			throw new Exception("No valid Id input");
		}
	}

}