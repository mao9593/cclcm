package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.PaperStatic;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看纸张统计
 * 
 * @author lixiang
 * 
 */
public class ViewPaperStaticByAllAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private List<PaperStatic> paperStaticList;
	private String dept_id = "";
	private String dept_name = "";
	private Date start_time = null;
	private Date end_time = null;
	private Integer page_sum = 0;// 总页数
	private String query = "N";
	private String create_type = "PRINT";// 制作方式

	public String getCreate_type() {
		return create_type;
	}

	public void setCreate_type(String create_type) {
		this.create_type = create_type;
	}

	public List<PaperStatic> getPaperStaticList() {
		return paperStaticList;
	}

	public void setPaperStaticList(List<PaperStatic> paperStaticList) {
		this.paperStaticList = paperStaticList;
	}

	public String getDept_id() {
		return dept_id;
	}

	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}

	public String getDept_name() {
		return dept_name;
	}

	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String getStart_time() {
		return sdf.format(start_time);
	}

	public String getEnd_time() {
		return sdf.format(end_time);
	}

	public void setStart_time(Date start_time) {
		this.start_time = start_time;
	}

	public void setEnd_time(Date end_time) {
		this.end_time = end_time;
	}

	public Integer getPage_sum() {
		return page_sum;
	}

	public void setPage_sum(Integer page_sum) {
		this.page_sum = page_sum;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	@Override
	public String executeFunction() throws Exception {
		if (query.equalsIgnoreCase("Y")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dept_id", dept_id);
			map.put("start_time", start_time);
			map.put("end_time", end_time);
			map.put("create_type", create_type);
			paperStaticList = basicService.getPaperStaticListByAll(map);
			for (PaperStatic paper_staitic : paperStaticList) {
				if (paper_staitic.getPage_count() != null)
					page_sum += paper_staitic.getPage_count();
			}
		}
		return SUCCESS;
	}

}
