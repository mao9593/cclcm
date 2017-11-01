package hdsec.web.project.arch.action;

/**
 * 删除案卷
 * 
 * @author handouwang
 * 
 *         2015-7-28/
 */
public class DeleteDosAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
	private int id = 0;

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	@Override
	public String executeFunction() throws Exception {
		if (id != 0) {
			if (archService.getArchNumByDos(id) > 0) {
				// return "forbid";
				throw new Exception("该案卷下尚有档案，请在删除案卷之前先将案卷内的档案全部删除。");
			} else {
				archService.deleteDosById(id);
			}
		} else {
			throw new Exception("No valid id input");
		}
		return SUCCESS;
	}
}
