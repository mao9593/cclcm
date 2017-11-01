package basic.service;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * 获取待办提醒webservice（主动方式）
 * 
 * @author renmingfei
 * 
 */
@WebService(serviceName = "HDBurnService")
public class HDBurnServiceImpl {
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@WebMethod
	public String pendingWork(@WebParam(targetNamespace = "http://webservice.burn.project.web.hdsec/") String name)
			throws Exception {
		return createReturnXmlStr(name);
	}

	private String createReturnXmlStr(String name) throws UnsupportedEncodingException {
		return "Hello " + name;
	}

}
