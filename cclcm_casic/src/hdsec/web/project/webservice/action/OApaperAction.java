package hdsec.web.project.webservice.action;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.codehaus.xfire.client.Client;

import com.opensymphony.xwork2.ActionSupport;

public class OApaperAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());

	private String xmlStr = "";

	@Override
	public String execute() throws Exception {
		// 拼接传过去的xml字符串
		// 测试文件台账
		xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "<root><data><code>0000F2015000000000038800</code>"
				+ "<dutyuserid>test2</dutyuserid>" + "<dutyusername>test2</dutyusername>"
				+ "<operationname>3</operationname>" + "<operationtype></operationtype>"
				+ "<operationtime>2015-02-03 09:10:10</operationtime></data></root>";
		// 测试光盘台账
		// xmlStr =
		// "<?xml version=\"1.0\" encoding=\"utf-8\"?><list><ENTITYCD><IDCARD>123456789</IDCARD><CD_BARCODE>20141105001</CD_BARCODE>"
		// + "<SECLV_CODE>1</SECLV_CODE><BURN_TIME>2014-11-5 00:00:00</BURN_TIME><FILE_LIST>文件名</FILE_LIST>"
		// + "</ENTITYCD><ENTITYCD><IDCARD>123456789</IDCARD><CD_BARCODE>20141105001</CD_BARCODE>"
		// + "<SECLV_CODE>2</SECLV_CODE><BURN_TIME>2014-11-5 00:00:00</BURN_TIME><FILE_LIST>文件名</FILE_LIST>"
		// + "</ENTITYCD></list>";

		logger.debug("success to create xmlStr");
		logger.debug("start to call PDMPaperService");
		String urlStr = "http://localhost/service/PDMPaperService?wsdl";
		try {
			Client client = new Client(new URL(urlStr));
			Object[] result = client.invoke("sendRequestPrintFile", new Object[] { xmlStr });
			// Object[] result = client.invoke("postEntityCD", new Object[] { xmlStr });
			System.out.println(result[0].toString());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("success to call UpdatePDMPaperService");
		return "success";
	}
}
