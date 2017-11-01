package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.PaperStatic;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看纸张统计(按大小)
 * 
 * @author lixiang
 * 
 */
public class ViewPaperStaticBySizeAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private List<PaperStatic> paperStaticList;
	private String dept_id = "";
	private String dept_name = "";
	private Date start_time = null;
	private Date end_time = null;
	private Integer page_sum = 0;// 总页数
	private String query = "N";

	private String dept_names = "";
	private String page_counts = "";
	private String dept_page_counts = "";
	private String size_names = "";

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

	public String getDept_names() {
		return dept_names;
	}

	public String getPage_counts() {
		return page_counts;
	}

	public String getDept_page_counts() {
		return dept_page_counts;
	}

	public String getSize_names() {
		return size_names;
	}

	@Override
	public String executeFunction() throws Exception {

		if (query.equalsIgnoreCase("Y")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dept_id", dept_id);
			map.put("start_time", start_time);
			map.put("end_time", end_time);
			map.put("create_type", "PRINT");
			paperStaticList = basicService.getPaperStaticListBySize(map);

			for (PaperStatic paper_staitic : paperStaticList) {
				page_sum += paper_staitic.getPage_count();
				page_counts += paper_staitic.getPage_count() + "#";
			}

			List<String> sizeList = basicService.getPageSizeList();
			for (String size : sizeList) {
				size_names += size + "#";
			}

			int sizeNum = sizeList.size();
			for (int i = 0; i < paperStaticList.size() / sizeNum; i++) {
				int dept_pages = 0;
				for (int j = 0; j < sizeNum; j++) {
					dept_pages += paperStaticList.get(sizeNum * i + j).getPage_count();
				}
				dept_page_counts += dept_pages + "#";
			}

			dept_names = userService.getDeptNameByDeptId(dept_id);
			List<String> firstChildList = basicService.getFirstChildList(dept_id);
			for (String first_child_id : firstChildList) {
				dept_names += "#" + userService.getDeptNameByDeptId(first_child_id);
			}
		}
		return SUCCESS;
	}

}
