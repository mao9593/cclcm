package hdsec.web.project.basic.model;

import hdsec.web.project.common.BaseDomain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 关键字类
 * 
 * @author lixiang
 * 
 */

public class SysKeyword extends BaseDomain {
	private String keyword_code = "";// 主键
	private String keyword_content = "";// 关键字内容
	private String keyword_detail = "";// 具体说明
	private Date update_time = null;// 更新时间

	public String getKeyword_code() {
		return keyword_code;
	}

	public void setKeyword_code(String keyword_code) {
		this.keyword_code = keyword_code;
	}

	public String getKeyword_content() {
		return keyword_content;
	}

	public void setKeyword_content(String keyword_content) {
		this.keyword_content = keyword_content;
	}

	public String getKeyword_detail() {
		return keyword_detail;
	}

	public void setKeyword_detail(String keyword_detail) {
		this.keyword_detail = keyword_detail;
	}

	public Date getUpdate_time() {
		return update_time;
	}

	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}

	public String getUpdate_time_str() {
		return update_time == null ? "" : getSdf().format(update_time);
	}

	public SysKeyword() {
		super();
	}

	public SysKeyword(SimpleDateFormat sdf) {
		super(sdf);
	}

	public SysKeyword(String keyword_code, String keyword_content, String keyword_detail, Date update_time) {
		super();
		this.keyword_code = keyword_code;
		this.keyword_content = keyword_content;
		this.keyword_detail = keyword_detail;
		this.update_time = update_time;
	}

}
