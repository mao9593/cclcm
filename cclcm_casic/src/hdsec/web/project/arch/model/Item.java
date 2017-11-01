package hdsec.web.project.arch.model;

import java.util.List;

/**
 * 项目类
 * 
 * @author handouwang
 * 
 *         2015-7-27/
 */
public class Item {
	private int id;
	private String item_code;
	private String summ;
	private int type_id;
	private List<Dossier> dosList;
	private String context = "arch/configitem.action";

	public void setDosList(List<Dossier> dosList) {
		this.dosList = dosList;
	}

	public List<Dossier> getDosList() {
		return dosList;
	}

	public String getContext() {
		return context;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public Item() {
		super();
	}

	public Item(String item_code, String summ, int type_id) {
		super();
		this.item_code = item_code;
		this.summ = summ;
		this.type_id = type_id;
	}

}
