package hdsec.web.project.arch.listener;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * 档案借阅催还定时器
 * 
 * @author lidepeng 2015-9-9
 */
public class AskingToReturnListener implements ServletContextListener {
	private static Logger logger = Logger
			.getLogger(AskingToReturnListener.class);

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Timer timer = new Timer(true);
		logger.info("档案借阅催还定时器已启动");
		Calendar calender = Calendar.getInstance();
		int year = calender.get(Calendar.YEAR);
		int month = calender.get(Calendar.MONTH);
		int day = calender.get(Calendar.DAY_OF_MONTH);
		calender.set(year, month, day, 23, 59, 00);
		Date date = calender.getTime();
		if (date.before(new Date())) {
			Calendar startDT = Calendar.getInstance();
			startDT.setTime(date);
			startDT.add(Calendar.DAY_OF_MONTH, 1);
			date = startDT.getTime();
		}
		timer.schedule(new AskingToReturnTask(), 1000 * 50);
		// timer.schedule(new AskingToReturnTask(), date);
		logger.info("已添加任务列表");
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}
}
