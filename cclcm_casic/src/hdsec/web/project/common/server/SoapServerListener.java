package hdsec.web.project.common.server;

import hdsec.web.project.common.PropertyUtil;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.xml.ws.Endpoint;

public class SoapServerListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			Endpoint.publish(PropertyUtil.getWSURL(),new CommonImpl());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
