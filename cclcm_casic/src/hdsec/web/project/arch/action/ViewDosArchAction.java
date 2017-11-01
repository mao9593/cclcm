package hdsec.web.project.arch.action;

import hdsec.web.project.arch.model.ArchValue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.displaytag.tags.TableTagParameters;
import org.displaytag.util.ParamEncoder;
import org.springframework.util.StringUtils;

public class ViewDosArchAction extends ArchBaseAction {
	private static final long serialVersionUID = 1L;
	private int id;
	private int type_id;
	private int item_id;
	private String barcode;

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType_id() {
		return type_id;
	}

	public void setType_id(int type_id) {
		this.type_id = type_id;
	}

	public int getItem_id() {
		return item_id;
	}

	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}

	public List<ArchValue> getArchValueList() {
		return archValueList;
	}

	public void setArchValueList(List<ArchValue> archValueList) {
		this.archValueList = archValueList;
	}

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

	private List<ArchValue> archValueList;
	private String file_title;
	private String template_id;

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("file_title", file_title);
		map.put("template_id", template_id);
		map.put("dos_id", id);
		map.put("item_id", item_id);
		map.put("type_id", type_id);
		map.put("barcode", barcode);
		String pageIndexName = new ParamEncoder("item")
				.encodeParameterName(TableTagParameters.PARAMETER_PAGE);
		if (StringUtils.hasLength(getRequest().getParameter(pageIndexName))) {
			page = Integer.parseInt(getRequest().getParameter(pageIndexName)) - 1;
		}
		beginIndex = page * pageSize;
		RowBounds rbs = new RowBounds(beginIndex, pageSize);
		totalSize = archService.getArchListSize(map);
		archValueList = archService.getArchList(map, rbs);
		for (ArchValue item : archValueList) {
			template_id = item.getTemplate_id();
			break;
		}
		return SUCCESS;
	}

}
