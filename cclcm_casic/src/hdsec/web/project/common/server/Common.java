package hdsec.web.project.common.server;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.soap.MTOM;


@WebService(name="Common")
@SOAPBinding(style=SOAPBinding.Style.RPC)
@MTOM
public interface Common {
	public String getMessage(@WebParam String userId);
	public String sayHello(@WebParam String name);
	public String syncUser(@WebParam String usersInfoStr);
	public String syncDept(@WebParam String deptsInfoStr);
}
