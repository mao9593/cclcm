package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.ArchKey;
import hdsec.web.project.arch.model.ArchValue;

/**
 * 查看档案详细信息
 * 
 * @author handouwang
 * 
 *         2015-7-27/
 */
public class ViewArchDetailAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
	private String id;
	private ArchValue av = null;
	private ArchKey ak = null;
	private int record_count = 0;

	public ArchValue getAv() {
		return av;
	}

	public ArchKey getAk() {
		return ak;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getRecord_count() {
		return record_count;
	}

	@Override
	public String executeFunction() throws Exception {
		// 取值
		av = archService.getArchValueById(id);
		if (av != null) {
			// 取模板
			String template_id = av.getTemplate_id();
			ak = archService.getTemplateBySystemCode(template_id);
			// 查看字段个数
			record_count = ak.getRecord_count();
		} else {
			throw new Exception("Can't get arch value by id:" + id);
		}
		return SUCCESS;
	}
}
