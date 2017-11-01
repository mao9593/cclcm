package hdsec.web.project.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by IntelliJ IDEA.
 *
 * @author mzwei
 *         User: mzwei
 *         Date: 2006-7-25
 *         Time: 18:49:12
 */
public class TestRunTimeUtil {
	private Calendar cal = Calendar.getInstance();
	
	public TestRunTimeUtil() {
	}
	
	public long getElapseTime() {
		Calendar cCal = Calendar.getInstance();
		
		return cCal.getTimeInMillis() - this.cal.getTimeInMillis();
	}
	
	public String getElapseTimeStandardFormat() {
		return getElapseTime("mm:ss.SSS");
	}
	
	public String getElapseTime(String timePattern) {
		Calendar cCal = Calendar.getInstance();
		cCal.setTimeInMillis(getElapseTime());
		SimpleDateFormat sdf = new SimpleDateFormat(timePattern);
		return sdf.format(cCal.getTime());
	}
	
}
