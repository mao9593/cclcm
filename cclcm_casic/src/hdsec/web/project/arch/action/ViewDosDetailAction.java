package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.Dossier;

/**
 * 查询案卷详细信息
 * 
 * @author handouwang
 * 
 *         2015-7-28/
 */
public class ViewDosDetailAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
	private int id = 0;
	private Dossier dos = null;

	public Dossier getDos() {
		return dos;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String executeFunction() throws Exception {
		if (id != 0) {
			dos = archService.getDosById(id);
		} else {
			throw new Exception("No valid id input");
		}
		return SUCCESS;
	}
}
