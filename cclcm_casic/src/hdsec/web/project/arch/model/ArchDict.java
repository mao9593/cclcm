package hdsec.web.project.arch.model;

import hdsec.web.project.common.util.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典类
 * 
 * @author handouwang
 * 
 *         2015-7-26/
 */
public class ArchDict {
	private String id = "";
	private String dict_name = "";
	private String dict_value = "";
	private int is_used = 1;

	/**
	 * @return 字典取值列表
	 */
	public List<String> getDictValues() {
		List<String> dictList = new ArrayList<String>();
		String[] vs = dict_value.split(Constants.COMMON_SEPARATOR_REGEX);
		for (String item : vs) {
			if (!item.trim().isEmpty()) {
				dictList.add(item);
			}
		}
		return dictList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDict_name() {
		return dict_name;
	}

	public void setDict_name(String dict_name) {
		this.dict_name = dict_name;
	}

	public String getDict_value() {
		return dict_value;
	}

	public void setDict_value(String dict_value) {
		this.dict_value = dict_value;
	}

	public int getIs_used() {
		return is_used;
	}

	public void setIs_used(int is_used) {
		this.is_used = is_used;
	}

	public boolean is_used() {
		return is_used == 1;
	}

	public ArchDict() {
		super();
	}

	public ArchDict(String dict_name, String dict_value) {
		super();
		this.dict_name = dict_name;
		this.dict_value = dict_value;
	}

	public ArchDict(String id, String dict_name, String dict_value) {
		super();
		this.id = id;
		this.dict_name = dict_name;
		this.dict_value = dict_value;
	}

}
