package hdsec.web.project.common.util;

/**
 * 字符串的中文封装,以一个汉字算两个字符,一个英文字母或数字算一个字符,进行相应的length()和substring()运算.
 * 2006-3-24 15:45:13
 * @author WangBei
 */
public class StringZhCN {
	
	private String string;
	
	public StringZhCN(String s) {
		this.string = s;
	}
	
	public int length() {
		return string.getBytes().length;
	}
	
	/**
	 * 取开始和结尾的子串,以单字节为计算单位.
	 * @param start 以0开始的首字节位置.如果该字节位置正好是汉字的后半个字节,则向后推1位
	 * @param end 以0开始计算的末字节位置(含).如果该字节位置正好是汉字的后半个字节,则向前推1位
	 * @return
	 */
	public String substring(int start, int end) {
		int len = this.length();
		
		if (start < 0 || start >= len)
			start = 0;
		if (end > len)
			end = this.length();
		
		byte[] bs = string.getBytes();
		
		//判断start位置是否位于某个汉字的后半个字节上.
		if (bs[start] < 0) {
			
			int count = 0; //某位置之前的小于0的字节数
			
			for (int i = 0; i < start; i++) {
				if (bs[i] < 0)
					count++;
			}
			if (count % 2 == 1) {
				start++;
			}
		}
		
		//判断end位置是否位于某个汉字的前半个字节上
		if (bs[end] < 0) {
			
			int count = 0; //某位置之前的小于0的字节数
			
			for (int i = 0; i < end; i++) {
				if (bs[i] < 0)
					count++;
			}
			
			if (count % 2 == 0) {
				end--;
			}
		}
		
		String str = new String(bs, start, end - start + 1);
		return str;
	}
	
	/**
	 * 前几个汉字的长度的字符串.
	 * @param numOnChineseCh 汉字长度,也就是字母长度*2
	 * @param padding 长度超过后在后面增加的字符串,例如…
	 * @return 最多numOnChineseCh个汉字长度的字符串.
	 */
	public String first(int numOnChineseCh, String padding) {
		if (this.length() > numOnChineseCh * 2) {
			String trimed = substring(0, numOnChineseCh * 2 - 1 - new StringZhCN(padding).length());
			if (new StringZhCN(trimed + padding).length() == numOnChineseCh * 2) {
				return trimed + padding;
			} else {
				return trimed + " " + padding;
			}
		}
		return this.string;
	}
	
	@Override
	public String toString() {
		return string;
	}
	
	public String concat(String str) {
		return string += str;
	}
	
}
