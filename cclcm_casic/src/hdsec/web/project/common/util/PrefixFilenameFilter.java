package hdsec.web.project.common.util;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 文件名过滤器
 * @author renmingfei
 *
 */
public class PrefixFilenameFilter implements FilenameFilter {
	private String prefix = "";
	
	public PrefixFilenameFilter(String prefix) {
		super();
		this.prefix = prefix;
	}
	
	@Override
	public boolean accept(File dir, String name) {
		return name.startsWith(prefix);
	}
	
}
