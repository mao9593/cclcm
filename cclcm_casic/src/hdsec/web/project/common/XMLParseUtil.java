package hdsec.web.project.common;

import java.io.File;
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
		//System.out.println("dom4j parserXml");
	}
	
	public static void beanToXML(Object o,OutputStream os) throws Exception {  
           JAXBContext context = JAXBContext.newInstance(o.getClass());  
           Marshaller marshaller = context.createMarshaller();  
           marshaller.marshal(o, os);
   } 
	
	public static String beanToXMLString(Object o) throws Exception {  
		StringWriter writer = new StringWriter();
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
}