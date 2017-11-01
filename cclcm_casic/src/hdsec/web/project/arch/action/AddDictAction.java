package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.ArchDict;
import hdsec.web.project.common.util.Constants;

/**
 * 添加字典类
 * 
 * @author handouwang
 * 
 *         2015-7-26/
 */
public class AddDictAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
	private String dict_name = "";
	private String dict_value = "";
	private String add = "N";

	public void setDict_name(String dict_name) {
		this.dict_name = dict_name.trim();
	}

	public void setDict_value(String dict_value) {
		this.dict_value = dict_value.replaceAll(" ", "").replaceAll(",",
				Constants.COMMON_SEPARATOR);
	}

	public void setAdd(String add) {
		this.add = add;
	}

	@Override
	public String executeFunction() throws Exception {
		if (add.equalsIgnoreCase("Y")) {// 处理添加操作
			if (archService.isDictExistByName(dict_name)) {
				throw new Exception("密级名称已经存在，不能重复添加。");
			} else {
				ArchDict dict = new ArchDict(dict_name, dict_value);
				archService.addDict(dict);
				insertCommonLog("添加字典[" + dict_name + "][" + dict_value + "].");
				return "insert";
			}
		} else {
			return SUCCESS;
		}
	}
}
