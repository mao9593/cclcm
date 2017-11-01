package hdsec.web.project.common.action;

import hdsec.web.project.common.util.TimeUtil;

import java.sql.Timestamp;

public abstract class DateTimePagingSortingAction extends PagingSortingAction {

	private static final long serialVersionUID = 1L;

	protected String timeType = "0";

	protected String beginTime = "";

	protected String endTime = "";

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTimeType() {
		return timeType;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public Timestamp getTimestampBeginTime() {
		if (timeType.equals("-1"))
			return null;
		else if (timeType == null || timeType.length() == 0) {
			return TimeUtil.getTime(beginTime);
		} else {
			try {
				return TimeUtil.getBeforeXDay(Integer.parseInt(timeType));
			} catch (Exception e) {
				return TimeUtil.getBeforeXDay(0);
			}
		}
	}

	public Timestamp getTimestampEndTime() {
		if (timeType.equals("-1"))
			return null;
		else if (timeType == null || timeType.length() == 0) {
			return TimeUtil.getTime(endTime);
		} else {
			return TimeUtil.getAfterXDay(0);
		}
	}

}
