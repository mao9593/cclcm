package hdsec.web.project.secmanage.action;

/**
 * 撤销保密管理业务申请
 * 
 * @author gaoximin 2015-7-22
 */
public class DelResearchFieldInEventAction extends SecManageBaseAction {
	private static final long serialVersionUID = 1L;
	private String event_code = "";
	private String type = "";
	private String style = "";
	private String apply_type = "";
	private String file_type = "";

	public void setStyle(String style) {
		this.style = style;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getApply_type() {
		return apply_type;
	}

	public void setApply_type(String apply_type) {
		this.apply_type = apply_type;
	}

	@Override
	public String executeFunction() throws Exception {
		if (style.equals("researchfield")) {
			secManageService.delDelResearchFieldInEventByEventCode(event_code);
			insertCommonLog("删除进入重要科研场地申请[" + event_code);
			return type.equalsIgnoreCase("ajax") ? "researchfield" : SUCCESS;
		} else if (style.equals("EMAIL")) {
			secManageService.delDelInternetEmailEventByEventCode(event_code);
			insertCommonLog("删除外网电子邮件申请[" + event_code);
			return type.equalsIgnoreCase("ajax") ? "ok_email" : SUCCESS;
		} else if (style.equals("fileoutmake")) {
			secManageService.delDelFileOutMakeEventByEventCode(event_code);
			insertCommonLog("删除涉密文件资料外出制作申请[" + event_code);
			return type.equalsIgnoreCase("ajax") ? "fileoutmake" : SUCCESS;
		} else if (style.equals("CHECK")) {
			secManageService.delDelSecCheckEventByEventCode(event_code);
			insertCommonLog("删除部门专项保密检查申请作业[" + event_code);
			return type.equalsIgnoreCase("ajax") ? "ok_check" : SUCCESS;
		} else if (style.equals("MATERIAL")) {
			secManageService.delDelExchangeMaterialEventByEventCode(event_code);
			insertCommonLog("删除对外交流材料申请作业[" + event_code);
			return type.equalsIgnoreCase("ajax") ? "ok_material" : SUCCESS;
		} else if (style.equals("EXHIBITION")) {
			secManageService.delDelExhibitionEventByEventCode(event_code);
			insertCommonLog("删除展示展览保密审查申请作业[" + event_code);
			return type.equalsIgnoreCase("ajax") ? "ok_exhibition" : SUCCESS;
		} else if (style.equals("punish")) {
			secManageService.delDelPunishEventByEventCode(event_code, apply_type);
			insertCommonLog("删除保密工作违规处罚整改督查申请作业[" + event_code);
			return type.equalsIgnoreCase("ajax") ? "punish" : SUCCESS;
		} else if (style.equals("PAPER_RESEARCH")) {
			secManageService.delPaperPatentEventByEventCode(event_code, file_type);
			insertCommonLog("删除专利申请论文发表申请作业[" + event_code);
			return type.equalsIgnoreCase("ajax") ? "paper_research" : SUCCESS;
		} else if (style.equals("PAPER_OTHERS")) {
			secManageService.delPaperPatentEventByEventCode(event_code, file_type);
			insertCommonLog("删除专利申请论文发表申请作业[" + event_code);
			return type.equalsIgnoreCase("ajax") ? "paper_others" : SUCCESS;
		} else if (style.equals("PAPER_PATENT")) {
			secManageService.delPaperPatentEventByEventCode(event_code, file_type);
			insertCommonLog("删除专利申请论文发表申请作业[" + event_code);
			return type.equalsIgnoreCase("ajax") ? "paper_patent" : SUCCESS;
		}

		return null;
	}
}
