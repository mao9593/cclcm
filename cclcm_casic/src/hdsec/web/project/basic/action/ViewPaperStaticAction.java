package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.PaperStatic;
import hdsec.web.project.basic.model.SysPrinter;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 查看纸张统计
 * 
 * @author lixiang
 * 
 */
public class ViewPaperStaticAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private List<PaperStatic> paperStaticList;
	private String dept_id = "";
	private String dept_name = "";
	private String seclv_code = "";
	private String printer_code = "";
	private Date start_time = null;
	private Date end_time = null;
	private Integer page_sum = 0;// 总页数
	private String query = "N";
	
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
	
	public String getSeclv_code() {
		return seclv_code;
	}
	
	public String getStart_time() {
		return sdf.format(start_time);
	}
	
	public String getEnd_time() {
		return sdf.format(end_time);
	}
	
	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}
	
	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
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
	
	public String getPrinter_code() {
		return printer_code;
	}
	
	public void setPrinter_code(String printer_code) {
		this.printer_code = printer_code;
	}
	
	public List<SysPrinter> getPrinterList() {
		return basicService.getSysPrinterList();
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
			map.put("seclv_code", seclv_code);
			map.put("printer_code", printer_code);
			map.put("start_time", start_time);
			map.put("end_time", end_time);
			map.put("create_type", "PRINT");
			
			paperStaticList = basicService.getPaperStaticList(map);
			
			Iterator<PaperStatic> paper_staitic_iter = paperStaticList.iterator();
			while (paper_staitic_iter.hasNext()) {
				PaperStatic paper_staitic = paper_staitic_iter.next();
				if (!(paper_staitic.getPage_count() == null))
					page_sum += paper_staitic.getPage_count();
			}
		}
		return SUCCESS;
	}
	
}
