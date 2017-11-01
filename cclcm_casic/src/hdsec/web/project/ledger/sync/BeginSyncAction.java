package hdsec.web.project.ledger.sync;

import hdsec.web.project.ledger.action.LedgerBaseAction;

public class BeginSyncAction extends LedgerBaseAction {
	private static final long serialVersionUID = 1L;
	private String sqlUrl = "";
	private String sqlUserName = "";
	private String sqlPassword = "";
	private String detail = "";
	private String readyDetail = "";
	private String retrieve = "";
	private String seclv = "";
	private Integer buttonFlag = 0;

	public String getSqlUrl() {
		return sqlUrl;
	}

	public void setSqlUrl(String sqlUrl) {
		this.sqlUrl = sqlUrl;
	}

	public String getSqlUserName() {
		return sqlUserName;
	}

	public void setSqlUserName(String sqlUserName) {
		this.sqlUserName = sqlUserName;
	}

	public String getSqlPassword() {
		return sqlPassword;
	}

	public void setSqlPassword(String sqlPassword) {
		this.sqlPassword = sqlPassword;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getReadyDetail() {
		return readyDetail;
	}

	public void setReadyDetail(String readyDetail) {
		this.readyDetail = readyDetail;
	}

	public String getRetrieve() {
		return retrieve;
	}

	public void setRetrieve(String retrieve) {
		this.retrieve = retrieve;
	}

	public String getSeclv() {
		return seclv;
	}

	public void setSeclv(String seclv) {
		this.seclv = seclv;
	}

	public Integer getButtonFlag() {
		return buttonFlag;
	}

	public void setButtonFlag(Integer buttonFlag) {
		this.buttonFlag = buttonFlag;
	}

	@Override
	public String executeFunction() throws Exception {
		SyncDataImpl impl = new SyncDataImpl();
		// 台账同步前准备
		if (buttonFlag > 10) {
			readyDetail = "准备工作：";
			// 增加标志位字段
			if (buttonFlag.equals(11)) {
				String returnReady11 = impl.alterOldTable(sqlUrl, sqlUserName, sqlPassword);
				readyDetail += returnReady11;
			}
			// 用户名查重
			if (buttonFlag.equals(12)) {
				String returnReady12 = impl.testUserName(sqlUrl, sqlUserName, sqlPassword);
				readyDetail += returnReady12;
			}
			// 检测密级一致性
			if (buttonFlag.equals(13)) {
				String returnReady13 = impl.testSeclv(sqlUrl, sqlUserName, sqlPassword);
				readyDetail += returnReady13;
			}
			// 条码查重
			if (buttonFlag.equals(14)) {
				String returnReady14 = impl.testBarcode(sqlUrl, sqlUserName, sqlPassword);
				readyDetail += returnReady14;
			}
		}
		if (buttonFlag.equals(1)) {
			// 在新系统cclcm数据库中创建临时台账表ENTITY_PAPER_TMP
			detail = impl.createTmpTable();
			// 把smartprint数据库未同步（syncflag='N'）的旧台账插入临时表ENTITY_PAPER_TMP
			String returnDetail1 = impl.insertTmpTableDate(sqlUrl, sqlUserName, sqlPassword, retrieve, seclv);
			detail += returnDetail1;
		}
		// 更新临时表ENTITY_PAPER_TMP台账信息（密级、部门、责任部门）
		if (buttonFlag.equals(2)) {
			String returnDetail2 = impl.updateTmpTableSeclvCodeAndDeptId();
			detail += returnDetail2;
		}
		// 把临时表台账插入正式台账表entity_paper
		if (buttonFlag.equals(3)) {
			String returnDetail3 = impl.inertDataIntoEntityTableFromTmpTable();
			detail += returnDetail3;
		}
		// 检测旧系统中未同步成功的台账
		if (buttonFlag.equals(4)) {
			String returnDetail4 = impl.checkNoSyncFromOldSystem(sqlUrl, sqlUserName, sqlPassword, retrieve, seclv);
			detail += returnDetail4;
		}
		return SUCCESS;
	}
}
