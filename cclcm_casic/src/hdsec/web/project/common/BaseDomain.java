package hdsec.web.project.common;

import java.text.SimpleDateFormat;

/**
 * POJO的基类
 * @author renmingfei
 *
 */
public class BaseDomain {
	private String is_sealed = "N";// N 未封存，Y 封存
	protected SimpleDateFormat sdf = null;//格式化时间输出格式
	
	public String getIs_sealed() {
		return is_sealed;
	}
	
	public void setIs_sealed(String is_sealed) {
		this.is_sealed = is_sealed;
	}
	
	public String getDeleteStatus() {
		return is_sealed.equalsIgnoreCase("Y") ? "<font color='red'>已封存</font>" : "留用";
	}
	
	public Boolean getIs_delete() {
		return is_sealed.equalsIgnoreCase("Y") ? true : false;
	}
	
	public SimpleDateFormat getSdf() {
		return sdf == null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss") : sdf;
	}
	
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	
	public BaseDomain() {
		super();
	}
	
	public BaseDomain(SimpleDateFormat sdf) {
		super();
		this.sdf = sdf;
	}
}
