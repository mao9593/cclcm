package hdsec.web.project.print.action;

import hdsec.web.project.print.model.FixAccording;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class UpdateFixedAccordingAction extends PrintBaseAction {

	private static final long serialVersionUID = 1L;
	private FixAccording list = null;
	private String content = "";
	private String id = "";
	private String update = "N";

	public FixAccording getList() {
		return list;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(id)) {
			if (update.equals("Y")) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", id);
				map.put("content", content);
				printService.updateFixedContent(map);
				return "ok";
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			List<FixAccording> flist = printService.getFixAccordingByType(map);
			if (flist == null) {
				throw new Exception("类型号查询错误，请重试！");
			}
			list = flist.get(0);

			return SUCCESS;
		} else {
			throw new Exception("类型号获取错误，请重试！");
		}
	}
}