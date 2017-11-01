package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysConfigItem;
import hdsec.web.project.basic.model.SysKeyword;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 关键字管理列表
 * 
 * @author lixiang
 * 
 */

public class ManageKeywordAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String keyword_content = "";
	private List<SysKeyword> keywordList = null;
	private Integer keyword_start = 0;
	private String update = "N";
	private String type = "";

	public String getKeyword_content() {
		return keyword_content;
	}

	public void setKeyword_content(String keyword_content) {
		this.keyword_content = keyword_content.trim();
	}

	public List<SysKeyword> getKeywordList() {
		return keywordList;
	}

	public Integer getKeyword_start() {
		return keyword_start;
	}

	public void setKeyword_start(Integer keyword_start) {
		this.keyword_start = keyword_start;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getKeyword_count() {
		return basicService.getKeywordCount();
	}

	@Override
	public String executeFunction() throws Exception {
		if (update.equalsIgnoreCase("Y")) {
			if (type.equalsIgnoreCase("open")) {
				basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_KEYWORD,
						SysConfigItem.NAME_KEYWORD, null, SysConfigItem.TYPE_KEYWORD, 1));
				insertAdminLog("开启关键字检测");
			}
			if (type.equalsIgnoreCase("close")) {
				basicService.updateSysConfigItem(new SysConfigItem(SysConfigItem.KEY_KEYWORD,
						SysConfigItem.NAME_KEYWORD, null, SysConfigItem.TYPE_KEYWORD, 0));
				insertAdminLog("关闭关键字检测");
			}
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("keyword_content", keyword_content);
			keywordList = basicService.getSysKeywordList(map);
			keyword_start = basicService.getSysConfigItemValue(SysConfigItem.KEY_KEYWORD).getStartuse();
		}
		return SUCCESS;
	}
}
