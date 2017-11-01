package hdsec.web.project.secplace.action;

import hdsec.web.project.secplace.action.SecplaceBaseAction;
import hdsec.web.project.secplace.model.EntitySecplace;

import java.util.ArrayList;
import java.util.List;

/**
 * 获得匹配的涉密场所
 * 
 * @author liuyaling 2015-6-11
 */
public class GetFuzzySecplaceAction extends SecplaceBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fuzzy = "";
	private List<EntitySecplace> secplaceList = new ArrayList<EntitySecplace>();

	public List<EntitySecplace> getSecplaceList() {
		return secplaceList;
	}

	public void setSecplaceList(List<EntitySecplace> secplaceList) {
		this.secplaceList = secplaceList;
	}

	public String getFuzzy() {
		return fuzzy;
	}

	public void setFuzzy(String fuzzy) {
		logger.warn("fuzzy is :" + fuzzy);
		this.fuzzy = fuzzy;
	}

	@Override
	public String executeFunction() throws Exception {
		try {
			secplaceList = secplaceService.getFuzzySecplaceList(fuzzy);
		} catch (Exception e) {
			logger.error("Exception occurs in GetFuzzySecplaceAction");
			logger.error(e.getMessage());
		}
		return SUCCESS;
	}
}