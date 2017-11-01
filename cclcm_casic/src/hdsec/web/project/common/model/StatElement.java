package hdsec.web.project.common.model;

/**
 * 饼图元素
 * @author renmingfei
 *
 */
public class StatElement {
	private String name = "";
	private int num = 0;
	private String percentage = "";
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getNum() {
		return num;
	}
	
	public void setNum(int num) {
		this.num = num;
	}
	
	public String getPercentage() {
		return percentage;
	}
	
	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}
	
	public StatElement() {
		super();
	}
	
	public StatElement(String name, int num, String percentage) {
		super();
		this.name = name;
		this.num = num;
		this.percentage = percentage;
	}
	
}
