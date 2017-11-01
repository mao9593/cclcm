package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.CDStatic;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 光盘统计
 * 2014-5-21 上午1:02:37
 * 
 * @author gaoximin
 */
public class ViewCDStaticAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private List<CDStatic> cdStaticList;
	private String dept_id = "";
	private String dept_name = "";
	private String seclv_code = "";
	
	private Date start_time = null;
	private Date end_time = null;
	private Integer disc_sum = 0;// 总个数
	private String query = "N";
	
	
	
	public List<CDStatic> getCdStaticList() {
		return cdStaticList;
	}

	public void setCdStaticList(List<CDStatic> cdStaticList) {
		this.cdStaticList = cdStaticList;
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
	
	public Integer getDisc_sum() {
		return disc_sum;
	}
	
	public void setdisc_sum(Integer disc_sum) {
		this.disc_sum = disc_sum;
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
			map.put("start_time", start_time);
			map.put("end_time", end_time);
			map.put("create_type", "BURN");
			
			cdStaticList = basicService.getCDStaticList(map);
			Iterator<CDStatic> cd_static_iter = cdStaticList.iterator();
			while (cd_static_iter.hasNext()) {
				CDStatic cd_static = cd_static_iter.next();
				if (!(cd_static.getDisc_count() == null))
					disc_sum += cd_static.getDisc_count();
			}
		}
		return SUCCESS;
	}
}
