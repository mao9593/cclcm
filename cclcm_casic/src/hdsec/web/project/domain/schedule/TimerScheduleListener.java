package hdsec.web.project.domain.schedule;

import hdsec.web.project.common.PropertyUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * 监听器：启动域同步定时器
 * 
 * @author renmingfei 2015-1-9
 */
public class TimerScheduleListener implements ServletContextListener {
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		int time;
		try {
			time = Integer.parseInt(PropertyUtil.getSchedulerTime());
			new MyTask(time);
		} catch (NumberFormatException e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
			e.printStackTrace();
		}
	}
}
