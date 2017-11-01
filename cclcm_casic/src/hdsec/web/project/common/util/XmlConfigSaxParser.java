package hdsec.web.project.common.util;

import java.util.Properties;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 说明: 解析XML配置文件的类.
 * <p>配置文件的格式为<br>
 * <code>
 * &ltconfig&gt<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&lta value="下面的不会冲掉这个值"/&gt<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&lta.x&gt这个的key是a.x&lt/a.x&gt<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&lta&gt<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lty&gt这个的key是a.y&lt/y&gt<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&ltz&gt&lt!--a.z会被设置为""--&gt<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lti value="这个是a.z.j"/&gt<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&ltj&gt这个是a.z.j&lt/j&gt<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&ltk value="这样"&gt会合并起来&lt/k&gt<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt/z&gt<br>
 * &nbsp;&nbsp;&nbsp;&nbsp;&lt/a&gt<br>
 * &lt/config&gt
 * </code>
 *
 * @author: renmingfei
 */
public class XmlConfigSaxParser extends DefaultHandler {

	private Properties props;
	private StringBuffer currentValue = new StringBuffer();
	private StringBuffer currentKey = new StringBuffer();

	public XmlConfigSaxParser() {
		props = new Properties();
	}

	public Properties getProps() {
		return props;
	}

	//定义开始解析元素的方法. 这里是将<xxx>中的名称xxx提取出来.
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		currentValue.delete(0, currentValue.length());
		if (currentKey.length() > 0)
			currentKey.append(".");
		currentKey.append(qName);

		if (attributes.getLength() > 0 && attributes.getIndex("value") >= 0) {
			currentValue.append(attributes.getValue("value"));
		}
	}

	//这里是将<xxx></xxx>之间的值加入到currentValue
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		currentValue.append(ch, start, length);
	}

	//在遇到</xxx>结束后,将之前的名称和值一一对应保存在props中
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		int keyLen = currentKey.length();
		if (keyLen == qName.length())
			return; //keyLen等于qName.length()说明是根元素

		String value = currentValue.toString().trim();
		String key = currentKey.toString();
		key = key.substring(key.indexOf(".") + 1);

		//如果props中没有key的值, 或者value赋了新的有意义的值, 则加入到props中
		if (value.length() > 0 || !props.containsKey(key)) {
			props.put(key, currentValue.toString().trim());
		}

		//清空value与key
		currentValue.delete(0, currentValue.length());
		currentKey.delete(keyLen - qName.length() - 1, keyLen);
	}
}
