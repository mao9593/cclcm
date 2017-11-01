package hdsec.web.project.ledger.model;

/**
 * 生命周期操作枚举
 * 
 * @author renmingfei
 * 
 */
public enum CycleOperEnum {
	PRINT("PRINT", "打印"), LEADIN("LEADIN", "录入"), COPY("COPY", "复印"), BURN("BURN", "刻录"), REPRINT("REPRINT", "补打"), REBURN(
			"REBURN", "补刻"), TRANSIN("TRANSIN", "流转入"), TRANSOUT("TRANSOUT", "流转出"), SEND("SEND", "外发"), FILE("FILE",
			"归档"), RETRIEVE("RETRIEVE", "回收"), DESTORY("DESTORY", "销毁"), RECV("RECV", "接收"), BORROW("BORROW", "借入"), RETURN(
			"RETURN", "归还"), REJECT("REJECT", "拒收"), RESET("RESET", "重置"), REPAIR("REPAIR", "送修"), CHANGETODEPT(
			"CHANGETODEPT", "个人载体变更为部门载体"), CHANGETOPERSON("CHANGETOPERSON", "部门载体变更为个人载体"), SENDREJECT("SENDREJECT",
			"外发拒收"), FILEREJECT("FILEREJECT", "归档拒收"), RETRIEVEREJECT("RETRIEVEREJECT", "回收拒收"), CARRYOUT("CARRYOUT",
			"带出"), CARRYIN("CARRYIN", "带回"), SPECIAL("SPECIAL", "特殊打印"), PURCHASEIN("PURCHASEIN", "采购入账"), PROPERTYIN(
			"PROPERTYIN", "入账"), PROPERTYOUT("PROPERTYOUT", "出账"), WASTE("WASTE", "报废"), CHANGE("CHANGE", "变更"), ERRORINFO(
			"ERRORINFO", "录入台账错误"), PAGEMODIFY("PAGEMODIFY", "录入文件页数错误"), DESTROY_PAPER_BYSELF("DESTROY_PAPER_BYSELF",
			"文件监销"), DESTROY_CD_BYSELF("DESTROY_CD_BYSELF", "光盘监销"), DESTROY_DEVICE_BYSELF("DESTROY_DEVICE_BYSELF",
			"磁介质监销"), TRANSIN_REJECT("TRANSIN_REJECT", "流转拒收"), MERGE_ENTITY("MERGE_ENTITY", "台账合并");
	private String key = "";
	private String name = "";

	private CycleOperEnum(String key, String name) {
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
