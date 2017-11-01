package basic;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WSTest {

	public static void main(String[] args) {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("./applicationContext.xml");
		appContext.getBean("proxyworkservice");
	}

}
