package basic;

import hdsec.web.project.basic.model.Message;
import hdsec.web.project.basic.model.Messages;
import hdsec.web.project.basic.model.TransferMessage;
import hdsec.web.project.common.XMLParseUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import basic.mapper.Mappers;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yy
 * 
 */
public class TestXML {

	@Resource
	public static Mappers mappers;

	public static void main(String[] args) throws Exception, Exception {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("./applicationContext.xml");
		TestXML test = (TestXML) appContext.getBean("testXML");
		mappers = (Mappers)appContext.getBean("mappers");
		Messages ms = new Messages();
		ms.setMessages(test.getMessages());
		OutputStream os = new FileOutputStream(new File("c:\\test.xml"));
		XMLParseUtil.beanToXML(ms, os);
		os.flush();
		os.close();
	}

	public List<Message> getMessages() {
		List<Message> messages = new ArrayList<Message>();
		List<TransferMessage> mess = mappers.getTransferMessage();
		for (TransferMessage tMessage : mess) {
			Message message = new Message();
			message.setMgsFromDept(tMessage.getFromDept());
			message.setMgsFromSys(tMessage.getSysName());
			// message.setMgsType("");
			message.setMgsLevel("1");
			// message.setMgsFunc("");
			message.setMgsWord("");
			// message.setMgsFromName("");
			message.setSentTime(tMessage.getFromDate());
			message.setMgsUrgent("特急");
			// message.setMgsOperateType("");
			message.setMgsStatus(tMessage.getReaded());
			message.setMgsAccessory("0");
			message.setTitle(tMessage.getTitle());
			message.setUrl(tMessage.getLinkUrl());
			messages.add(message);
		}
		return messages;
	}

	public Mappers getMappers() {
		return mappers;
	}

	public void setMappers(Mappers mappers) {
		this.mappers = mappers;
	}

}
