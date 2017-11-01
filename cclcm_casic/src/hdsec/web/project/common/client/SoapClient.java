package hdsec.web.project.common.client;

import java.net.URL;

import javax.xml.namespace.QName;

public class SoapClient {

	/**
	 *
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		syaHello();
	}
	public static void syaHello() throws Exception {
		QName qName = new QName("http://service.common.project.web.hdsec/","CommonService");
		CommonService commonService = new CommonService(new URL("http://127.0.0.1:8080/commonService?wsdl"),qName);
		Common common = (Common) commonService.getPort(Common.class);
		System.out.println(common.getMessage("123456789"));
	}

}
