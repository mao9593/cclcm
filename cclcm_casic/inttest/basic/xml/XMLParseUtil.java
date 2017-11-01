package basic.xml;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * xml 生成解析类
 * @author yy
 *
 */
public class XMLParseUtil {
//	public static void createXml(String filePath,String rootName,List<SecUser>elements) throws Exception {
//		Document document = DocumentHelper.createDocument();
//		Element root = document.addElement(rootName);
//		for(SecUser user : elements){
//			Element userNode = root.addElement("user");
//			Element deptName = userNode.addElement("dept_name");
//			deptName.setText(user.getDept_name());
//			Element deptId = userNode.addElement("dept_id");
//			deptId.setText(user.getDept_id());
//			Element userName = userNode.addElement("user_name");
//			userName.setText(user.getUser_name());
//			Element userId = userNode.addElement("user_id");
//			userId.setText(user.getUser_iidd());
//		}
//		try {
//			Writer fileWriter = new FileWriter(filePath);
//			XMLWriter xmlWriter = new XMLWriter(fileWriter);
//			xmlWriter.write(document);
//			xmlWriter.close();
//		} catch (IOException e) {
//			System.out.println(e.getMessage());
//		}
//	}

	public static void parserXml(String filePath) {
		File inputXml = new File(filePath);
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(inputXml);
			Element root = document.getRootElement();
			for (Iterator<?> i = root.elementIterator(); i.hasNext();) {
				Element element = (Element) i.next();
				System.out.println(element.getName());
				for (Iterator<?> j = element.elementIterator(); j.hasNext();) {
					Element node = (Element) j.next();
					System.out.println(node.getName() + ":" + node.getText());
				}
				System.out.println(element.getName());
			}
		} catch (DocumentException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("dom4j parserXml");
	}
	
	public static void beanToXML(Object o,OutputStream os) throws Exception {  
           JAXBContext context = JAXBContext.newInstance(o.getClass());  
           Marshaller marshaller = context.createMarshaller();  
           marshaller.marshal(o, os);
    } 
	
	public static String beanToXMLString(Object o,StringWriter writer) throws Exception {  
		JAXBContext context = JAXBContext.newInstance(o.getClass());  
		Marshaller marshaller = context.createMarshaller();  
		marshaller.marshal(o, writer);
		return writer.toString();
	}  
	
	public static Object XMLFileToBean(String filePath,Object obj) throws Exception{  
       JAXBContext context = JAXBContext.newInstance(obj.getClass());  
       Unmarshaller unmarshaller = context.createUnmarshaller();
       return(Object)unmarshaller.unmarshal(new File(filePath));
	} 
	
	public static Object XMLStringToBean(String str,Object obj) throws Exception{  
		JAXBContext context = JAXBContext.newInstance(obj.getClass());  
		Unmarshaller unmarshaller = context.createUnmarshaller();
		return(Object)unmarshaller.unmarshal(new StringReader(str));
	} 
	
	
	public static void main(String args[]) throws Exception{
		UserInfo info = getUserInfo();
		OutputStream os = new FileOutputStream(new File("c:\\uuu.xml"));
		beanToXML(info,os);
		os.flush();
		os.close();
	}
	
	public static String getXMLString(){
		return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
				"<userInfo><depts><dept_id>d11111</dept_id><dept_name>部门1</dept_name>" +
				"</depts><depts><dept_id>编号2</dept_id><dept_name>网信</dept_name>" +
				"</depts><users><phone>13888888888</phone><user_id>2345</user_id>" +
				"<user_name>岳莹1</user_name></users><users><phone>8888-8888</phone>" +
				"<user_id>5678</user_id><user_name>李四</user_name></users></userInfo>";
	}
	
	public static UserInfo getUserInfo(){
		UserInfo info = new UserInfo();
		Organization d1 = new Organization();
		d1.setDealFlag("ADD");
		d1.setOrgCode("001");
		d1.setOrgName("部门1");
		d1.setOrgPCode("0");
		
		Organization d2 = new Organization();
		d2.setDealFlag("ADD");
		d2.setOrgCode("002");
		d2.setOrgName("部门2");
		d2.setOrgPCode("0");
		
		
		User u1 = new User();
		u1.setDealFlag("ADD");
		u1.setUserID("user1");
		u1.setOperatorName("张上哪");
		u1.setPID("88888888");
		u1.setSecret("1");
		u1.setOrgID("部门1");
		u1.setOrgCode("001");
		
		User u2 = new User();
		u2.setDealFlag("ADD");
		u2.setUserID("user2");
		u2.setOperatorName("l浏览");
		u2.setPID("666666");
		u2.setSecret("1");
		u2.setOrgID("部门2");
		u2.setOrgCode("002");
		
		Depts depts = new Depts();
		depts.getOrganization().add(d1);
		depts.getOrganization().add(d2);
		
		Users users = new Users();
		users.getUser().add(u1);
		users.getUser().add(u2);
		
		info.setDepts(depts);
		info.setUsers(users);
		return info;
	}
	
}