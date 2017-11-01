package hdsec.web.project.domain.schedule;

import hdsec.web.project.domain.SynchronizeFromLDAP;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

public class MyTask {
	private final Logger logger = Logger.getLogger(this.getClass());
	private final Timer timer;

	public MyTask(int time) {
		timer = new Timer();
		timer.schedule(new RemindTask(), 0, time * 1000); // subsequent rate
		// System.out.println("第" + i++ + "次运行，每次隔" + time + "秒");
	}

	class RemindTask extends TimerTask {
		@Override
		public void run() {
			logger.info("-------start synchronizing users and depts from domain(LDAP)");
			try {
				SynchronizeFromLDAP sfl = new SynchronizeFromLDAP();
				sfl.startSynUser();
			} catch (Exception e) {
				logger.error("Exception cause:" + e.getCause().getClass().getSimpleName() + ".Message:" + e.getMessage());
				e.printStackTrace();
			}
			logger.info("-------end synchronizing users and depts from domain(LDAP)");
		}
	}

	public static void main(String args[]) {
		new MyTask(10);
	}
}
