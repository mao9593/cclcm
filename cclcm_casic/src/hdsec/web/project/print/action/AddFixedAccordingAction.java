package hdsec.web.project.print.action;

import hdsec.web.project.print.model.RefFixAccordingDept;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 添加定密依据
 * 
 * @author guojiao
 * 
 */
public class AddFixedAccordingAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	private String type = "";
	private String content = "";;
	private Integer fix_id=null;//每个依据的id
	private String dept_id="";
	
	
	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public Integer getFix_id() {
		return fix_id;
	}

	public void setFix_id(Integer fix_id) {
		this.fix_id = fix_id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String executeFunction() throws Exception {
		if (!type.equals("")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("type", type);
			map.put("content", content);
			printService.addFixedContent(map);
			fix_id=printService.getFixedId(content);//获取部门和定密依据绑定的id
			for (String dept_id1 : dept_id.split(",")) {
				if (StringUtils.hasLength(dept_id1.trim())) {
					printService.addRefFixedAccordingDept(new RefFixAccordingDept(dept_id1, fix_id));
				}
			}
			return "ok";
		} else {
			return SUCCESS;
		}
	}

}
