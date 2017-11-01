package hdsec.web.project.computer.action;

import hdsec.web.project.computer.model.BorrowBookEvent;
import hdsec.web.project.user.model.SecLevel;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查看笔记本借用申请记录
 * 
 * @author guojiao
 */
public class ManageBorrowBookEventAction extends ComputerBaseAction {
	private static final long serialVersionUID = 1L;
	private Date startTime = null;
	private Date endTime = null;
	private String job_status = "";
	private String book_selv = "";
	private List<BorrowBookEvent> eventList = null;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getJob_status() {
		return job_status;
	}

	public void setJob_status(String job_status) {
		this.job_status = job_status;
	}

	public String getBook_selv() {
		return book_selv;
	}

	public void setBook_selv(String book_selv) {
		this.book_selv = book_selv;
	}

	public List<BorrowBookEvent> getEventList() {
		return eventList;
	}

	public void setEventList(List<BorrowBookEvent> eventList) {
		this.eventList = eventList;
	}

	public String getStartTime_str() {
		return startTime == null ? "" : sdf.format(startTime);
	}

	public String getEndTime_str() {
		return endTime == null ? "" : sdf.format(endTime);
	}

	public List<SecLevel> getSeclvList() {
		return userService.getSecLevel();
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_iidd", getCurUser().getUser_iidd());
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("book_selv", book_selv);
		map.put("job_status", job_status);

		eventList = computerService.getBorrowBookEventList(map);

		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BORROW_BOOK", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BORROW_BOOKOUT", 2);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BORROW_BOOKOUT", 3);
		basicService.setClientMsgRead(getCurUser().getUser_iidd(), "BORROW_BOOK", 3);

		return SUCCESS;
	}
}
