package hdsec.web.project.common.action;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public abstract class ExportXMLBaseAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	protected static final int BATCH_CACHE_SIZE = 10000;
	protected SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
	protected OutputStream os = null;
	protected int batchNum = 0;
	protected boolean prestart = true;
	
	protected OutputStream createFile(String fileName) throws IOException {
		HttpServletResponse response = getResponse();
		response.reset();
		response.setHeader("Content-disposition", "attachment;filename="
				+ new String(fileName.getBytes(), "iso-8859-1"));//导出日志的名称中汉字的转换
		response.setContentType("text/xml");
		return response.getOutputStream();
	}
	
	protected abstract void handleLogWriting(XMLStreamWriter xmlw) throws XMLStreamException;
}
