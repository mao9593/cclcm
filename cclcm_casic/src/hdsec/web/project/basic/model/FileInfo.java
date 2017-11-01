package hdsec.web.project.basic.model;

import hdsec.web.project.common.BaseDomain;

import java.util.Date;

/**
 * @author yy
 *
 */
public class FileInfo extends BaseDomain {
	
	private String file_name;
	private String prod_num;
	private String version;
	private String store_location;
	private Date create_time;
	private String create_user_iidd;
	private String create_user_name;
	private Date update_time;
	private String update_user_iidd;
	private String update_user_name;
	private String comment;
	private String type;
	
	public FileInfo() {
		
	}
	
	public FileInfo(String file_name, String prod_num, String version, String store_location, Date create_time,
			String create_user_iidd, String comment, String type) {
		this.file_name = file_name;
		this.prod_num = prod_num;
		this.version = version;
		this.store_location = store_location;
		this.create_time = create_time;
		this.create_user_iidd = create_user_iidd;
		this.comment = comment;
		this.type = type;
	}
	
	public String getFile_name() {
		return file_name;
	}
	
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	
	public String getProd_num() {
		return prod_num;
	}
	
	public void setProd_num(String prod_num) {
		this.prod_num = prod_num;
	}
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getStore_location() {
		return store_location;
	}
	
	public void setStore_location(String store_location) {
		this.store_location = store_location;
	}
	
	public String getCreate_user_iidd() {
		return create_user_iidd;
	}
	
	public void setCreate_user_iidd(String create_user_iidd) {
		this.create_user_iidd = create_user_iidd;
	}
	
	public String getUpdate_time() {
		if (null == update_time) {
			return "";
		}
		return getSdf().format(update_time);
	}
	
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	
	public String getUpdate_user_iidd() {
		return update_user_iidd;
	}
	
	public void setUpdate_user_iidd(String update_user_iidd) {
		this.update_user_iidd = update_user_iidd;
	}
	
	public String getCreate_time() {
		return getSdf().format(create_time);
	}
	
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
	public String getProd_num_name() {
		if ("print".equalsIgnoreCase(prod_num)) {
			return "打印";
		} else {
			return "其他";
		}
	}
	
	public String getCreate_user_name() {
		return create_user_name;
	}
	
	public void setCreate_user_name(String create_user_name) {
		this.create_user_name = create_user_name;
	}
	
	public String getUpdate_user_name() {
		return update_user_name;
	}
	
	public void setUpdate_user_name(String update_user_name) {
		this.update_user_name = update_user_name;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
}
