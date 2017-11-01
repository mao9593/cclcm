package hdsec.web.project.common.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {

	private final static long ONE_DAY_TIMEMILLS = 86400000L;

	public static Timestamp getBeforeXDay(int day) {
		if (day < 0) {
			throw new IllegalArgumentException(String.valueOf(day));
		}
		Calendar c = Calendar.getInstance(Locale.CHINESE);
		c.setTimeInMillis(System.currentTimeMillis() - day * ONE_DAY_TIMEMILLS);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return new Timestamp(c.getTimeInMillis());
	}

	public static Timestamp getAfterXDay(int day) {
		if (day < 0) {
			return getBeforeXDay(-day);
			// throw new IllegalArgumentException(String.valueOf(day));
		}
		Calendar c = Calendar.getInstance(Locale.CHINESE);
		c.setTimeInMillis(System.currentTimeMillis() + day * ONE_DAY_TIMEMILLS);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return new Timestamp(c.getTimeInMillis());
	}

	public static Timestamp getCurrentTimestamp() {
		return new Timestamp(System.currentTimeMillis());
	}

	public static Timestamp getBeforeTimestamp(Timestamp current, int beforeXMin) {
		return new Timestamp(current.getTime() - beforeXMin * 60000L);
	}

	public static Timestamp getBeforeTimestamp(long timeInMillis, int beforeXMin) {
		return new Timestamp(timeInMillis - timeInMillis % (beforeXMin * 60000L) - beforeXMin * 60000L);
	}

	public static Timestamp getCurrentTimestamp(long timeInMillis, int beforeXMin) {
		return new Timestamp(timeInMillis - timeInMillis % (beforeXMin * 60000L));
	}

	public static Timestamp getTime(String yyyyMMddhhmmss) {
		if (yyyyMMddhhmmss == null || yyyyMMddhhmmss.trim().length() == 0) {
			return getCurrentTimestamp();
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		Date date;
		try {
			date = sdf.parse(yyyyMMddhhmmss);
		} catch (ParseException e) {
			e.printStackTrace();
			return new Timestamp(0);
		}
		return new Timestamp(date.getTime());
	}

	/**
	 * 把指定时间往后推迟指定天数
	 * 
	 * @param time
	 * @param day
	 * @return
	 */
	public static Timestamp getAfterXDayByTransferDay(Date time, int day) {
		if (day < 0) {
			return getBeforeXDay(-day);
			// throw new IllegalArgumentException(String.valueOf(day));
		}
		long time_long = time.getTime();
		Calendar c = Calendar.getInstance(Locale.CHINESE);
		c.setTimeInMillis(time_long + day * ONE_DAY_TIMEMILLS);
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return new Timestamp(c.getTimeInMillis());
	}

}
