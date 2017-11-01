package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.ArchDict;

import java.util.List;

public class GetDictionaryAction extends ArchBaseAction {
	private List<ArchDict> dictList;

	private String archDictInfo = "";

	public String getArchDictInfo() {
		return archDictInfo;
	}

	public void setArchDictInfo(String archDictInfo) {
		this.archDictInfo = archDictInfo;
	}

	public List<ArchDict> getDictList() {
		return dictList;
	}

	public void setDictList(List<ArchDict> dictList) {
		this.dictList = dictList;
	}

	@Override
	public String executeFunction() throws Exception {
		dictList = archService.getAllArchDictList();
		for (ArchDict item : dictList) {
			archDictInfo += item.getId() + "|" + item.getDict_name() + "^";
		}
		return SUCCESS;
	}

}
