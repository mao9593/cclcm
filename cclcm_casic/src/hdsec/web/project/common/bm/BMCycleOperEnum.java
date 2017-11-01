package hdsec.web.project.common.bm;

/**
 * BM全生命周期记录
 * 
 * @author gaoximin 2015-7-16
 */
public enum BMCycleOperEnum {
	PRINT("PRINT", "打印"), LEADIN("LEADIN", "录入"), COPY("COPY", "复印"), BURN("BURN", "刻录"), REPRINT("REPRINT", "补打"), REBURN(
			"REBURN", "补刻"), TRANSIN("TRANSIN", "流转入"), TRANSOUT("TRANSOUT", "流转出"), SEND("SEND", "外发"), FILE("FILE",
			"归档"), RETRIEVE("RETRIEVE", "回收"), DESTORY("DESTORY", "销毁"), RECV("RECV", "接收"), BORROW("BORROW", "借入"), RETURN(
			"RETURN", "归还"), REJECT("REJECT", "拒收"), RESET("RESET", "重置"), REPAIR("REPAIR", "送修"), CHANGETODEPT(
			"CHANGETODEPT", "个人载体变更为部门载体"), CHANGETOPERSON("CHANGETOPERSON", "部门载体变更为个人载体"), SENDREJECT("SENDREJECT",
			"外发拒收"), FILEREJECT("FILEREJECT", "归档拒收"), RETRIEVEREJECT("RETRIEVEREJECT", "回收拒收"), ADD("ADD", "新增"), CHANGE(
			"CHANGE", "变更"), DESTROY("DESTROY", "报废"), REINSTALL("REINSTALL", "重装系统"), USBKEY("USBKEY", "USBKEY申请/变更"), QUITINTERNET(
			"QUITINTERNET", "退网"), OPENPORT("OPENPORT", "开通端口"), LOCALPRINTER("LOCALPRINTER", "保留本地打印机"), MODIFY(
			"MODIFY", "修改");
	private String key = "";
	private String name = "";

	private BMCycleOperEnum(String key, String name) {
		this.key = key;
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public String getName() {
		return name;
	}
}
