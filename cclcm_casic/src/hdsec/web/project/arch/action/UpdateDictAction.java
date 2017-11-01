package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.ArchDict;
import hdsec.web.project.common.util.Constants;

/**
 * 修改字典信息
 * 
 * @author handouwang
 * 
 *         2015-7-26/
 */
public class UpdateDictAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
	private String id = "";
	private String dict_name = "";
	private String o_dict_name = "";
	private String dict_value = null;
	private String update = "N";
	private ArchDict dict = null;

	public ArchDict getDict() {
		return dict;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setDict_name(String dict_name) {
		this.dict_name = dict_name;
	}

	public void setO_dict_name(String o_dict_name) {
		this.o_dict_name = o_dict_name;
	}

	public void setDict_value(String dict_value) {
		this.dict_value = dict_value.replaceAll(" ", "").replaceAll(",",
				Constants.COMMON_SEPARATOR);
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {// 处理修改操作
			if (!dict_name.equals(o_dict_name)
					&& archService.isDictExistByName(dict_name)) {
				throw new Exception("字典名称已经存在。");
			}
			dict = new ArchDict(id, dict_name, dict_value);
			archService.updateDict(dict);
			insertCommonLog("修改字典配置id[" + id + "],name[" + dict_name
					+ "],value[" + dict_value + "]");
			return "update";
		} else {
			dict = archService.getDictById(Integer.parseInt(id));
		}
		return SUCCESS;
	}
}
