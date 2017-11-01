package hdsec.web.project.print.action;

import hdsec.web.project.activiti.model.JobTypeEnum;
import hdsec.web.project.print.model.PrintEvent;
import hdsec.web.project.print.model.RiskKeywordsPrint;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.decorator.CheckboxTableDecorator;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * 待提交打印作业列表
 * 
 * @author renmingfei
 * 
 */

public class SubmitPrintEventAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	private String file_title = "";
	private String color = "";
	private Date startTime = null;
	private Date endTime = null;
	private Integer print_type = null;
	private List<PrintEvent> eventList = null;
	private final String jobType = JobTypeEnum.PRINT_REMAIN.getJobTypeCode();
	private CheckboxTableDecorator decorator = null;
	private List<RiskKeywordsPrint> dlpList = null;

	public CheckboxTableDecorator getDecorator() {
		return decorator;
	}

	public String getJobType() {
		return jobType;
	}

	public String getFile_title() {
		return file_title;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title.trim();
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Integer getPrint_type() {
		return print_type;
	}

	public void setPrint_type(Integer print_type) {
		this.print_type = print_type;
	}

	public String getStartTime_str() {
		return startTime == null ? "" : sdf.format(startTime);
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getEndTime_str() {
		return endTime == null ? "" : sdf.format(endTime);
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public List<PrintEvent> getEventList() {
		return eventList;
	}

	/*
	 * public List<SecLevel> getSeclvList() { return userService.getPrintSecLevelByUser(getCurUser().getUser_iidd()); }
	 */

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("file_title", file_title);
		// map.put("color", color);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("submitable", true);
		// map.put("print_type", print_type);
		String pageIndexName = new ParamEncoder("item").encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = printService.getPrintEventSize(map);
		eventList = printService.getPrintEventList(map);

		if (eventList != null) {
			// 开始获取每个文件对应的dlp检索结果，是否已经通过检测
			for (PrintEvent event : eventList) {
				Map<String, Object> map2 = new HashMap<String, Object>();
				map2.put("st_filename", event.getSt_filename());
				String transIDTemp = printService.getPrintTransID(map2);

				Map<String, Object> map3 = new HashMap<String, Object>();
				map3.put("tid", transIDTemp);
				dlpList = printService.getRisklistPrint(map3);

				String checkresult = printMapper.getCheckresultByTid(transIDTemp);

				if (null == dlpList || dlpList.size() == 0) {
					if (checkresult != null && !checkresult.isEmpty()) {
						if (checkresult.equals("NoHit")) {
							event.setPolicy("NO");
						} else if (checkresult.equals("Failed")) {
							event.setPolicy("Failed");
						} else if (checkresult.equals("RequestFailed")) {
							event.setPolicy("RequestFailed");
						} else if (checkresult.equals("Checking")) {
							event.setPolicy("Checking");
						}
					} else {
						event.setPolicy("Checking");
					}
				} else {
					event.setPolicy("YES");
				}
			}
		}

		decorator = new org.displaytag.decorator.CheckboxTableDecorator();
		decorator.setId("id");
		decorator.setFieldName("_chk");

		return SUCCESS;
	}

}
