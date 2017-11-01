package hdsec.web.project.arch.action;

/**
 * 设置字典启用状态法
 * 
 * @author handouwang
 * 
 *         2015-7-26/
 */
public class SetDictUsedAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
	private String id;
	private int val;
	private String name = "";

	public void setId(String id) {
		this.id = id;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String executeFunction() throws Exception {
		archService.setDictUsed(id, val);
		insertCommonLog("修改字典启用状态[" + name + "][" + val + "].");
		return SUCCESS;
	}
}
