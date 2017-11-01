package hdsec.web.project.common.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义字符串工具类
 * 
 * @author renmingfei
 * 
 */
public class StringUtil {
	// 判断输入字符串是不是整数，如果是整数，是不是在给定范围内
	public static boolean isLegalInteger(String base, Integer minimum, Integer maximum) {
		Integer target;
		try {
			target = Integer.parseInt(base);
			if (minimum != null && target < minimum) {
				return false;
			}
			if (maximum != null && target > maximum) {
				return false;
			}
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static List<String> stringArrayToList(String[] strArray) {
		List<String> strList = new ArrayList<String>();
		for (String item : strArray) {
			if (!item.isEmpty()) {
				strList.add(item);
			}
		}
		return strList;
	}
}
