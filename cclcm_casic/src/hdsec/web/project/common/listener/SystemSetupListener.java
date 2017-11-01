package hdsec.web.project.common.listener;

import hdsec.web.project.common.extension.ServletResourcePatternResovlerAndLoader;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;

/**
 * 说明:系统启动事件的监听器, 主要是在Server启动时初始化一些和servlet有关的全局性内容.
 * 
 * @author renmingfei
 * 
 */
public final class SystemSetupListener implements ServletContextListener {
	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void contextInitialized(ServletContextEvent event) {
		// 初始化Servlet资源搜索器
		ServletResourcePatternResovlerAndLoader.init(event.getServletContext());
		logger.info("SystemSetupListener, app_started_at "
				+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
	}
}
