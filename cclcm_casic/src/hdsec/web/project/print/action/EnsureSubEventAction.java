package hdsec.web.project.print.action;

import hdsec.web.project.basic.model.SysProject;
import hdsec.web.project.basic.model.SysUsage;
import hdsec.web.project.common.util.StringUtil;
import hdsec.web.project.print.model.PrintEvent;
import hdsec.web.project.user.model.SecLevel;

import java.net.URLDecoder;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 作业提交确认
 * 
 * @author haojia
 * 
 */
public class EnsureSubEventAction extends PrintBaseAction {

	private static final long serialVersionUID = 1L;
	private String event_ids = "";
	private String log_file_name = "";
	private String FileTempList = null;
	private String SeclvTempList = null;
	private String file_seclvs = null;
	private String file_titles = "";
	private String file_title = "";
	private String file_seclv_code = "";
	private List<PrintEvent> eventList = null;
	private String keywords = "";

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public List<PrintEvent> getEventList() {
		return eventList;
	}

	public String getFile_title() {
		return file_title;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title;
	}

	public String getFile_seclv_code() {
		return file_seclv_code;
	}

	public void setFile_seclv_code(String file_seclv_code) {
		this.file_seclv_code = file_seclv_code;
	}

	public String getFile_titles() {
		return file_titles;
	}

	public void setFile_titles(String file_titles) {
		this.file_titles = file_titles;
	}

	public void setFile_seclvs(String file_seclvs) {
		this.file_seclvs = file_seclvs;
	}

	public String getSeclvTempList() {
		return SeclvTempList;
	}

	public void setSeclvTempList(String seclvTempList) {
		SeclvTempList = seclvTempList;
	}

	public String getFileTempList() {
		return FileTempList;
	}

	public void setFileTempList(String fileTempList) {
		FileTempList = fileTempList;
	}

	public List<SecLevel> getSeclvList() {
		return userService.getPrintSecLevelByUser(getCurUser().getUser_iidd());
	}

	public void setEvent_ids(String event_ids) {
		this.event_ids = event_ids.replaceAll(" ", "");
	}

	public String getEvent_ids() {
		return event_ids;
	}

	public boolean getIsKeywordEnable() {
		return basicService.isKeywordEnable();
	}

	public List<SysUsage> getUsageList() {
		return basicService.getSysUsageListByModule("PRINT");
	}

	public List<SysProject> getProjectList() {
		return basicService.getSysProjectList();
	}

	private Map<String, String> getFileSeclvList() {
		if (StringUtils.hasLength(file_seclvs)) {
			Map<String, String> map = new HashMap<String, String>();
			for (String item : file_seclvs.split(",")) {
				if (item.indexOf(":") != -1) {
					map.put(item.substring(0, item.indexOf(":")), item.substring(item.indexOf(":") + 1));
				}
			}
			return map;
		}
		return Collections.emptyMap();
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(event_ids)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("submitable", true);
			map.put("event_ids", StringUtil.stringArrayToList(event_ids.split(":")));
			eventList = printService.getPrintEventList(map);

			if (eventList != null) {
				for (int i = 0; i < eventList.size(); i++) {
					if (StringUtils.hasLength(file_seclvs)) {
						String seclv_name = "";
						for (String seclv_codes : file_seclvs.split(",")) {
							String seclv_code = seclv_codes.substring(seclv_codes.indexOf(":") + 1);
							seclv_name = basicService.getSeclvNameByCode(Integer.parseInt(seclv_code));
							eventList.get(i).setSeclv_name(seclv_name);
							i++;
						}
					}
				}

				file_titles = URLDecoder.decode(getRequest().getParameter("file_titles"), "UTF-8");

				for (int j = 0; j < eventList.size(); j++) {
					if (StringUtils.hasLength(file_titles)) {
						String dl_filename = "";
						for (String dl_filenames : file_titles.split(",")) {
							dl_filename = dl_filenames.substring(dl_filenames.indexOf(":") + 1);
							eventList.get(j).setDl_filename(dl_filename);
							j++;
						}
					}
				}
			}
		}
		return SUCCESS;
	}
}
