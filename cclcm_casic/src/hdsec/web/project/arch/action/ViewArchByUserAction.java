package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.ArchTypeName;
import hdsec.web.project.arch.model.ArchValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

/**
 * @author handouwang
 * 
 *         2015-7-27/
 */
public class ViewArchByUserAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
	private List<ArchValue> archList = null;
	private String file_title = "";
	private String template_id = "";

	public String getFile_title() {
		return file_title;
	}

	public void setFile_title(String file_title) {
		this.file_title = file_title;
	}

	public String getTemplate_id() {
		return template_id;
	}

	public void setTemplate_id(String template_id) {
		this.template_id = template_id;
	}

	public List<ArchValue> getArchList() {
		return archList;
	}

	public List<ArchTypeName> getArchTypeList() {
		return archService.getValidTypeList();
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("file_title", file_title);
		map.put("template_id", template_id);
		String pageIndexName = new ParamEncoder("item")
				.encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = archService.getArchListSize(map);
		archList = archService.getArchList(map, rbs);
		return SUCCESS;
	}
}
