package hdsec.web.project.arch.model;

/**
 * @author hd304 每一个模板字段的分解 2015-7-27/
 */
public class TemplateProperty {
	private String property_name;
	private String name;
	private String type;
	private String dirc;
	private String type_name;
	private String dirc_name;
	private String[] dict_list;

	public String[] getDict_list() {
		return dict_list;
	}

	public void setDict_list(String[] dict_list) {
		this.dict_list = dict_list;
	}

	public String getDirc_name() {
		return dirc_name;
	}

	public void setDirc_name(String dirc_name) {
		this.dirc_name = dirc_name;
	}

	public String getProperty_name() {
		return property_name;
	}

	public void setProperty_name(String property_name) {
		this.property_name = property_name;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDirc() {
		return dirc;
	}

	public void setDirc(String dirc) {
		this.dirc = dirc;
	}

}
