package hdsec.web.project.arch.model;

import java.util.List;

/**
 * @author hd304 档案类别管理表 2015-7-26/
 */
public class ArchTypeName {
	private int ID;
	private String type_num;
	private String type_name;
	private String template_id;
	private int is_used;
	private List<Item> itemList;
	private String context = "arch/configarchtype.action";

	public ArchTypeName() {
		super();
	}

	public ArchTypeName(String type_num, String type_name, String template_id,
			int is_used) {
		super();
		this.type_num = type_num;
		this.type_name = type_name;
		this.template_id = template_id;
		this.is_used = is_used;
	}

	public void setItemList(List<Item> itemList) {
		this.itemList = itemList;
	}

	public List<Item> getItemList() {
		return itemList;
	}

	public String getContext() {
		return context;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getType_num() {
		return type_num;
	}

	public void setType_num(String type_num) {
		this.type_num = type_num;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public int getIs_used() {
		return is_used;
	}

	public void setIs_used(int is_used) {
		this.is_used = is_used;
	}
}
