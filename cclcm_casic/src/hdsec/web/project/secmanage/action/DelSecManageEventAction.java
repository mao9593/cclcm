package hdsec.web.project.secmanage.action;

import hdsec.web.project.publicity.model.ReportEvent;
import hdsec.web.project.secactivity.model.SecOutExchangeEvent;
import hdsec.web.project.secactivity.model.UserSecActiEvent;
import hdsec.web.project.secmanage.model.ExchangeMaterialEvent;
import hdsec.web.project.secmanage.model.ExhibitionEvent;
import hdsec.web.project.secmanage.model.FileOutMakeEvent;
import hdsec.web.project.secmanage.model.InternetEmailEvent;
import hdsec.web.project.secmanage.model.PaperPatentEvent;
import hdsec.web.project.secmanage.model.PunishRectifyEvent;
import hdsec.web.project.secmanage.model.ResearchFieldInEvent;
import hdsec.web.project.secmanage.model.SecCheckEvent;
import hdsec.web.project.secplace.model.EnterSecplaceEvent;

import java.util.List;

/**
 * 根据job_code撤销保密管理业务
 * 
 * @author gaoximin 2015-10-13
 */
public class DelSecManageEventAction extends SecManageBaseAction {
	private static final long serialVersionUID = 1L;
	private String job_code = "";
	private String type = "";
	private String style = "";
	private String apply_type = "";
	private String module = "";

	public void setStyle(String style) {
		this.style = style;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public String getApply_type() {
		return apply_type;
	}

	public void setApply_type(String apply_type) {
		this.apply_type = apply_type;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	@Override
	public String executeFunction() throws Exception {
		if (style.equals("FIELDIN") && job_code != null) {
			List<ResearchFieldInEvent> researchFieldInEvent = secManageService
					.getResearchFieldInEventListByJobCode(job_code);
			for (ResearchFieldInEvent event : researchFieldInEvent) {
				secManageService.delDelResearchFieldInEventByEventCode(event.getEvent_code());
				insertCommonLog("删除进入重要科研场地申请[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("INTER_EMAIL") && job_code != null) {
			List<InternetEmailEvent> internetEmailEvent = secManageService.getInternetEmailListByJobCode(job_code);
			for (InternetEmailEvent event : internetEmailEvent) {
				secManageService.delDelInternetEmailEventByEventCode(event.getEvent_code());
				insertCommonLog("删除外网电子邮件申请[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("FILEOUTMAKE") && job_code != null) {
			List<FileOutMakeEvent> fileOutMakeEvent = secManageService.getFileOutMakeEventListByJobCode(job_code);
			for (FileOutMakeEvent event : fileOutMakeEvent) {
				secManageService.delDelFileOutMakeEventByEventCode(event.getEvent_code());
				insertCommonLog("删除涉密文件资料外出制作申请[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("SEC_CHECK") && job_code != null) {
			List<SecCheckEvent> secCheckEvent = secManageService.getSecCheckListByJobCode(job_code);
			for (SecCheckEvent event : secCheckEvent) {
				secManageService.delDelSecCheckEventByEventCode(event.getEvent_code());
				insertCommonLog("删除部门专项保密检查申请[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("MATERIAL") && job_code != null) {
			List<ExchangeMaterialEvent> exchangeMaterialEvent = secManageService
					.getExchangeMaterialListByJobCode(job_code);
			for (ExchangeMaterialEvent event : exchangeMaterialEvent) {
				secManageService.delDelExchangeMaterialEventByEventCode(event.getEvent_code());
				insertCommonLog("删除对外交流材料申请[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("EXHIBITION") && job_code != null) {
			List<ExhibitionEvent> exhibitionEvent = secManageService.getExhibitionListByJobCode(job_code);
			for (ExhibitionEvent event : exhibitionEvent) {
				secManageService.delDelExhibitionEventByEventCode(event.getEvent_code());
				insertCommonLog("删除展示展览保密审查申请[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("USERSEC_ACTIVITY") && job_code != null) {
			List<UserSecActiEvent> userSecActiEvent = secActivityService.getUSecActiEventListByJobCode(job_code);
			for (UserSecActiEvent event : userSecActiEvent) {
				secActivityService.delUSecActiEventByEventCode(event.getEvent_code());
				insertCommonLog("删除重大涉密活动申请[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("ENTER_SECPLACE") && job_code != null) {
			EnterSecplaceEvent enterSecplaceEvent = secplaceService.getEnterSecplaceEventByJobCode(job_code);
			if (enterSecplaceEvent != null) {
				secplaceService.deleteEnterSecplaceEvent(enterSecplaceEvent.getEvent_code());
				insertCommonLog("删除进出要害部门部位申请[" + enterSecplaceEvent.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("PUNISH_DEPT") && job_code != null) {
			List<PunishRectifyEvent> punishRectifyEvent = secManageService.getPunishEventListByJobCode(job_code);
			for (PunishRectifyEvent event : punishRectifyEvent) {
				secManageService.delDelPunishEventByEventCode(event.getEvent_code(), "0");
				insertCommonLog("删除部门自查违规处罚申请[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("PUNISH_SECCHECK") && job_code != null) {
			List<PunishRectifyEvent> punishRectifyEvent = secManageService.getPunishEventListByJobCode(job_code);
			for (PunishRectifyEvent event : punishRectifyEvent) {
				secManageService.delDelPunishEventByEventCode(event.getEvent_code(), "1");
				insertCommonLog("删除保密检查违规处罚申请[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("PUNISH_RECTIFY") && job_code != null) {
			List<PunishRectifyEvent> punishRectifyEvent = secManageService.getPunishEventListByJobCode(job_code);
			for (PunishRectifyEvent event : punishRectifyEvent) {
				secManageService.delDelPunishEventByEventCode(event.getEvent_code(), "2");
				insertCommonLog("删除保密整改督查申请[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("PAPERPATENT") && job_code != null) {
			List<PaperPatentEvent> paperPatentEvent = secManageService.getPaperPatentListByJobCode(job_code);
			for (PaperPatentEvent event : paperPatentEvent) {
				secManageService.delPaperPatentEventByEventCode(event.getEvent_code(), event.getFile_type());
				insertCommonLog("删除专利发表申请[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("PAPER_RESEARCH") && job_code != null) {
			List<PaperPatentEvent> paperPatentEvent = secManageService.getPaperPatentListByJobCode(job_code);
			for (PaperPatentEvent event : paperPatentEvent) {
				secManageService.delPaperPatentEventByEventCode(event.getEvent_code(), event.getFile_type());
				insertCommonLog("删除政研管理论文发表申请[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("PAPER_OTHERS") && job_code != null) {
			List<PaperPatentEvent> paperPatentEvent = secManageService.getPaperPatentListByJobCode(job_code);
			for (PaperPatentEvent event : paperPatentEvent) {
				secManageService.delPaperPatentEventByEventCode(event.getEvent_code(), event.getFile_type());
				insertCommonLog("删除科研论文发表申请[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("OUT_EXCHANGE") && job_code != null) {
			List<SecOutExchangeEvent> secOutExchangeEvent = secActivityService
					.getOutExchangeEventListByJobCode(job_code);
			for (SecOutExchangeEvent event : secOutExchangeEvent) {
				secActivityService.delSecOutExchangeEventByEventCode(event.getEvent_code());
				insertCommonLog("删除涉外交流保密审查作业[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("EVENT_REPORT") && job_code != null) {
			List<ReportEvent> reportEvent = publicityService.getPublReportEventListByJobCode(job_code);
			for (ReportEvent event : reportEvent) {
				publicityService.delUPublReportEventByEventCode(event.getEvent_code(), "1");
				insertCommonLog("删除宣传报道保密审查作业[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("EVENT_DEPTREPORT") && job_code != null) {
			List<ReportEvent> reportEvent = publicityService.getPublReportEventListByJobCode(job_code);
			for (ReportEvent event : reportEvent) {
				publicityService.delUPublReportEventByEventCode(event.getEvent_code(), "2");
				insertCommonLog("删除部门投稿保密审查作业[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("EVENT_INTRAPUBL") && job_code != null) {
			List<ReportEvent> reportEvent = publicityService.getPublReportEventListByJobCode(job_code);
			for (ReportEvent event : reportEvent) {
				publicityService.delUPublReportEventByEventCode(event.getEvent_code(), "3");
				insertCommonLog("删除内网信息发布保密审查作业[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		} else if (style.equals("EVENT_INTERPUBL") && job_code != null) {
			List<ReportEvent> reportEvent = publicityService.getPublReportEventListByJobCode(job_code);
			for (ReportEvent event : reportEvent) {
				publicityService.delUPublReportEventByEventCode(event.getEvent_code(), "4");
				insertCommonLog("删除外网信息发布保密审查作业[" + event.getEvent_code());
			}
			return type.equalsIgnoreCase("ajax") ? null : SUCCESS;
		}
		return null;
	}
}
