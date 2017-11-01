package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.ArchTypeName;

/**
 * 配置档案类别(添加项目等)
 * 
 * @author handouwang
 * 
 *         2015-7-28/
 */
public class ConfigArchTypeAction extends ArchBaseAction {

	private static final long serialVersionUID = 1L;
	private int id = 0;
	private ArchTypeName type = null;

	public ArchTypeName getType() {
		return type;
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
			type = archService.getArchTypeNameById(id);
			return SUCCESS;
		} else {
			throw new Exception("No valid Id input");
		}
	}

}