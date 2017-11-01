package hdsec.web.project.burn.model;

/**
 * 院机关集成：刻录宣传报道文件所需参数
 * 
 * @author chenrongrong 2015-4-15
 */
public class BurnPublicity {
	private Integer id = null;
	private String idCard = "";// 用户身份证ID
	private String event_code = "";// 作业号
	private String cd_serial = "";// 光盘编号
	private String seclv_code = null;// 作业密级
	private String file_name = "";// 文件名
	private String file_seclevel = "";// 文件密级
	private byte[] file_contents = null;// 文件内容
	private String burn_states = "";// 是否已处理
	private Integer fsequenceno = null;
	private String file_id = "";
	private String reqdept = "";
	private String reqperson = "";

	public BurnPublicity() {
		super();
	}

	public BurnPublicity(Integer id, String idCard, String event_code, String cd_serial, String seclv_code,
			String file_name, String file_seclevel, byte[] file_contents, String burn_states, Integer fsequenceno,
			String file_id, String reqdept, String reqperson) {
		super();
		this.id = id;
		this.idCard = idCard;
		this.event_code = event_code;
		this.cd_serial = cd_serial;
		this.seclv_code = seclv_code;
		this.file_name = file_name;
		this.file_seclevel = file_seclevel;
		this.file_contents = file_contents;
		this.burn_states = burn_states;
		this.fsequenceno = fsequenceno;
		this.file_id = file_id;
		this.reqdept = reqdept;
		this.reqperson = reqperson;
	}

	public String getFile_seclevel() {
		return file_seclevel;
	}

	public void setFile_seclevel(String file_seclevel) {
		this.file_seclevel = file_seclevel;
	}

	public String getBurn_states() {
		return burn_states;
	}

	public void setBurn_states(String burn_states) {
		this.burn_states = burn_states;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getSeclv_code() {
		return seclv_code;
	}

	public void setSeclv_code(String seclv_code) {
		this.seclv_code = seclv_code;
	}

	public String getCd_serial() {
		return cd_serial;
	}

	public void setCd_serial(String cd_serial) {
		this.cd_serial = cd_serial;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public byte[] getFile_contents() {
		return file_contents;
	}

	public void setFile_contents(byte[] file_contents) {
		this.file_contents = file_contents;
	}

	public Integer getFsequenceno() {
		return fsequenceno;
	}

	public void setFsequenceno(Integer fsequenceno) {
		this.fsequenceno = fsequenceno;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getReqdept() {
		return reqdept;
	}

	public void setReqdept(String reqdept) {
		this.reqdept = reqdept;
	}

	public String getReqperson() {
		return reqperson;
	}

	public void setReqperson(String reqperson) {
		this.reqperson = reqperson;
	}
}
