package hdsec.web.project.common.util;

import java.io.File;
import java.io.FilenameFilter;

/**
 * 文件名过滤器
 * @author renmingfei
 *
 */
public class ExactFilenameFilter implements FilenameFilter {
	private String filename = "";
	
	public ExactFilenameFilter(String filename) {
		super();
		this.filename = filename;
	}
	
	@Override
	public boolean accept(File dir, String name) {
		return name.equals(filename);
	}
	
}
