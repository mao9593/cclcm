package hdsec.web.project.client.model;

/**
 * 待办事项展现类
 * 
 * 2014-4-14 下午8:37:03
 * 
 * @author renmingfei
 */
public class PendingWorkItem {
	private String item_type = "";
	private String item_url = "";
	private Integer pass_num = 0;
	private String pass_intro = "通过：";
	private Integer rej_num = 0;
	private String rej_intro = "驳回：";
	private Integer wait_num = 0;
	private String wait_intro = "待审批：";

	public String getItem_type() {
		return item_type;
	}

	public void setItem_type(String item_type) {
		this.item_type = item_type;
	}

	public String getItem_name() {
		switch (this.item_type) {
		case "PRINT":
			return "打印申请";
		case "BURN":
			return "刻录申请";
		case "COPY":
			return "复印申请";
		case "OUTCOPY":
			return "外来文件复印申请";
		case "LEADIN":
			return "录入申请";
		case "TRANSFER":
			return "流转申请";
		case "DEVICE":
			return "磁介质借用申请";
		case "BORROW":
			return "借阅申请";
		case "FILE_PAPER":
			return "文件归档申请";
		case "SEND_PAPER":
			return "文件外发申请";
		case "DESTROY_PAPER":
			return "文件销毁申请";
		case "DELAY_PAPER":
			return "文件延期回收申请";
		case "FILE_CD":
			return "光盘归档申请";
		case "SEND_CD":
			return "光盘外发申请";
		case "DESTROY_CD":
			return "光盘销毁申请";
		case "DELAY_CD":
			return "光盘延期回收申请";
		case "DESTROY_DEVICE":
			return "磁介质销毁申请";
		case "CHANGE":
			return "载体归属变换申请";
		case "SENDES_PAPER":
			return "文件送销申请";
		case "SENDES_CD":
			return "光盘送销申请";
		case "CARRYOUT_PAPER":
			return "文件外带申请";
		case "CARRYOUT_CD":
			return "光盘外带申请";
		case "MODIFY_SECLV":
			return "密级变更申请";
		case "MSG_INPUT":
			return "电子文件导入申请";
		case "TRANSFER_CONFIRM":
			return "流转确认";
		case "BORROW_EXPIRE":
			return "借用到期";
		default:
			return "";
		}
	}

	public String getItem_url() {
		return item_url;
	}

	public void setItem_url(String item_url) {
		this.item_url = item_url;
	}

	public Integer getPass_num() {
		return pass_num;
	}

	public void setPass_num(Integer pass_num) {
		this.pass_num = pass_num;
	}

	public String getPass_intro() {
		return pass_intro;
	}

	public void setPass_intro(String pass_intro) {
		this.pass_intro = pass_intro;
	}

	public Integer getRej_num() {
		return rej_num;
	}

	public void setRej_num(Integer rej_num) {
		this.rej_num = rej_num;
	}

	public String getRej_intro() {
		return rej_intro;
	}

	public void setRej_intro(String rej_intro) {
		this.rej_intro = rej_intro;
	}

	public Integer getWait_num() {
		return wait_num;
	}

	public void setWait_num(Integer wait_num) {
		this.wait_num = wait_num;
	}

	public String getWait_intro() {
		return wait_intro;
	}

	public void setWait_intro(String wait_intro) {
		this.wait_intro = wait_intro;
	}

	public PendingWorkItem() {
		super();
	}

	public PendingWorkItem(String item_type, Integer pass_num, Integer rej_num, String item_url) {
		super();
		this.item_type = item_type;
		this.pass_num = pass_num;
		this.rej_num = rej_num;
		this.item_url = item_url;
	}

	public PendingWorkItem(String item_type, Integer wait_num, String item_url) {
		super();
		this.item_type = item_type;
		this.wait_num = wait_num;
		this.item_url = item_url;
	}
}
