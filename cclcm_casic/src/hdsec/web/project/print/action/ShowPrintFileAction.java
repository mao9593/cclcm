package hdsec.web.project.print.action;

import hdsec.web.project.print.model.PrintEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 预览打印文件
 * 
 * @author lixiang
 * 
 */
public class ShowPrintFileAction extends PrintBaseAction {
	private static final long serialVersionUID = 1L;
	private String secpass = "";
	private String unzipdirname = "";
	private String pagecount = "";
	private String job_code = "";
	private String next_approver = "";
	private String st_file_name = "";
	private List<PrintEvent> eventList_temp = null;
	private Integer nCount = null;

	public Integer getnCount() {
		return nCount;
	}

	public void setnCount(Integer nCount) {
		this.nCount = nCount;
	}

	public List<PrintEvent> getEventList_temp() {
		return eventList_temp;
	}

	public void setEventList_temp(List<PrintEvent> eventList_temp) {
		this.eventList_temp = eventList_temp;
	}

	public String getSt_file_name() {
		return st_file_name;
	}

	public void setSt_file_name(String st_file_name) {
		this.st_file_name = st_file_name;
	}

	public String getNext_approver() {
		return next_approver;
	}

	public void setNext_approver(String next_approver) {
		this.next_approver = next_approver;
	}

	public String getJob_code() {
		return job_code;
	}

	public void setJob_code(String job_code) {
		this.job_code = job_code;
	}

	public String getSecpass() {
		return secpass;
	}

	public void setSecpass(String secpass) {
		this.secpass = secpass;
	}

	public String getUnzipdirname() {
		return unzipdirname;
	}

	public void setUnzipdirname(String unzipdirname) {
		this.unzipdirname = unzipdirname;
	}

	public String getPagecount() {
		return pagecount;
	}

	public void setPagecount(String pagecount) {
		this.pagecount = pagecount;
	}

	public String getRemoteAddr() {
		return getRequest().getScheme() + "://" + getRequest().getServerName() + ":" + getRequest().getServerPort();
	}

	@Override
	public String executeFunction() throws Exception {

		if (st_file_name != null && !st_file_name.equals("")) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("st_filename", st_file_name);
			printService.updateEventFileReadByStFileName(map);
			eventList_temp = printService.getPrintEventList(map);
			String temp_job_code = "";
			for (int i = 0; i < eventList_temp.size(); i++) {
				temp_job_code = eventList_temp.get(i).getJob_code();
			}
			int ret = 0;

			if (temp_job_code != null && !temp_job_code.equals("")) {
				ret = printService.getIsAllEventIsRead(temp_job_code);

				if (ret == 0) {
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("job_code", temp_job_code);
					map1.put("file_read_status", 1);
					basicService.updateJobProcessFileRead(map1);
				}
			}
		}
		return SUCCESS;
	}
}
