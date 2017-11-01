package hdsec.web.project.common.util;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.log4j.Logger;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * 说明: XML工具类
 * @author renmingfei
 *
 */
public class XMLUtils implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private static Logger log = org.apache.log4j.Logger.getLogger(XMLUtils.class);
	
	/** 用于得到系统默认的DocumentBuilder */
	private static DocumentBuilder getBuilder() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		return builder;
	}
	
	/** 解析path所指定的XML文件. */
	public static Document getDocument(String path) throws Exception {
		File f = new File(path);
		f.getAbsolutePath();
		Document doc = getBuilder().parse(f);
		return doc;
	}
	
	/** 解析流 */
	public static Document getDocument(InputStream in) throws Exception {
		Document doc = getBuilder().parse(in);
		in.close();
		return doc;
	}
	
	/** 得到空文档对象 */
	public static Document getDocument() throws Exception {
		Document doc = getBuilder().newDocument();
		return doc;
	}
	
	/** 解析字符串得到文档对象 */
	public static Document getDocument(StringBuffer xmlContent) throws Exception {
		StringReader sr = new StringReader(xmlContent.toString());
		InputSource iSrc = new InputSource(sr);
		
		Document doc = getBuilder().parse(iSrc);
		return doc;
	}
	
	/** 将Document对象保存为指定文件名 */
	public static void saveDocumentToFile(Document doc, String filePath) {
		saveStringToFile(getXmlString(doc), filePath);
	}
	
	/** 将文本字符串保存到文件中 */
	public static void saveStringToFile(String str, String filePath) {
		try {
			File f = new File(filePath);
			PrintWriter out = new PrintWriter(new FileWriter(f));
			out.print(str);
			out.close();
		} catch (Exception e) {
			log.error("Can't write to file : " + filePath + "[[" + e.getLocalizedMessage());
		}
	}
	
	/** 将XML节点解析为字符串 */
	@SuppressWarnings("finally")
	public static String getXmlString(Node node) {
		StringWriter buffer = new StringWriter();
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			
			Source source = new DOMSource(node);
			
			StreamResult result = new StreamResult(buffer);
			
			Properties props = new Properties();
			//props.setProperty("encoding", "GB2312");
			//modified by renmingfei 2013-07-08
			props.setProperty("encoding", "UTF-8");
			props.setProperty("method", "xml");
			props.setProperty("omit-xml-declaration", node instanceof Document ? "no" : "yes");
			
			transformer.setOutputProperties(props);
			transformer.transform(source, result);
		} catch (Exception e) {
			log.error("转换失败:" + e.getLocalizedMessage());
		} finally {
			String result = buffer.toString();
			return result;
		}
	}
	
	/** 将Dom对象转化为XSLTranformer对象 */
	public static Transformer getTransformer(Document xslDoc) {
		Transformer transformer = null;
		
		TransformerFactory tFactory = TransformerFactory.newInstance();
		if (tFactory.getFeature(DOMSource.FEATURE) && tFactory.getFeature(DOMResult.FEATURE)) {
			DOMSource source = new DOMSource(xslDoc);
			
			try {
				transformer = tFactory.newTransformer(source);
			} catch (Exception ex) {
				log.error(ex.getLocalizedMessage());
			}
		}
		return transformer;
	}
	
	/** 用xslDom格式化xmlDom并返回根节点对象 **/
	public static Node transform(Document xmlDoc, Document xslDoc) {
		return transform(xmlDoc, getTransformer(xslDoc));
	}
	
	/** 格式化xmlDom并返回根节点对象 **/
	public static Node transform(Document xmlDoc, Transformer transformer) {
		try {
			DOMSource xmlSouce = new DOMSource(xmlDoc);
			DOMResult domResult = new DOMResult();
			
			transformer.transform(xmlSouce, domResult);
			return domResult.getNode();
			
		} catch (Exception ex) {
			log.error(ex.getLocalizedMessage());
		}
		return null;
	}
	
	/** 用xslDom格式化xmlDom并返回结果字符串 **/
	public static String transToString(Document xmlDoc, Document xslDoc) {
		return transToString(xmlDoc, getTransformer(xslDoc));
	}
	
	/** 格式化xmlDom并返回结果字符串 **/
	public static String transToString(Document xmlDoc, Transformer transformer) {
		try {
			StringWriter stringWriter = new StringWriter();
			StreamResult result = new StreamResult(stringWriter);
			
			DOMSource xmlSouce = new DOMSource(xmlDoc);
			transformer.transform(xmlSouce, result);
			return stringWriter.toString();
			
		} catch (Exception ex) {
			log.error(ex.getLocalizedMessage());
			return ex.getMessage();
		}
	}
	
	/** 得到xpath的值 */
	public static String getXPathValue(Node node, String path) {
		try {
			if (node instanceof Document)
				return XPathAPI.selectSingleNode(((Document) node).getDocumentElement(), path).getNodeValue();
			
			return XPathAPI.selectSingleNode(node, path).getNodeValue();
		} catch (Exception e) {
			log.warn("Can't find the XPath Node : " + path);
			return "";
		}
	}
	
	public static Node selectSingleNode(Node node, String path) {
		try {
			if (node instanceof Document)
				return XPathAPI.selectSingleNode(((Document) node).getDocumentElement(), path);
			
			return XPathAPI.selectSingleNode(node, path);
			
		} catch (Exception e) {
			log.warn("Can't find the XPath Node : " + path);
			return null;
		}
	}
	
	public static NodeList selectNodeList(Node node, String path) {
		try {
			if (node instanceof Document)
				return XPathAPI.selectNodeList(((Document) node).getDocumentElement(), path);
			
			return XPathAPI.selectNodeList(node, path);
			
		} catch (Exception e) {
			log.warn("Can't find the XPath Node : " + path);
			return null;
		}
	}
	
	/** 得到节点的属性, 没有相应属性时将返回null */
	public static String getNodeAttribute(Node node, String attrName) {
		try {
			return node.getAttributes().getNamedItem(attrName).getNodeValue();
		} catch (Exception e) {
			return null;
		}
	}
	
}
