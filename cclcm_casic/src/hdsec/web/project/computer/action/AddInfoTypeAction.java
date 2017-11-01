package hdsec.web.project.computer.action;

import hdsec.web.project.common.bm.model.BMSysConfigItem;
import hdsec.web.project.computer.model.InfoType;

public class AddInfoTypeAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private String add = "N";
	private String typename = "";
	private String summ = "";
	private String device_id = "";
	private String id = null;
	private Integer product_type = 0;

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public void setAdd(String add) {
		this.add = add;
	}

	public void setProduct_type(Integer product_type) {
		this.product_type = product_type;
	}

	@Override
	public String executeFunction() throws Exception {
		if (add.equalsIgnoreCase("Y")) {
			if (computerService.isTypeExistByName(typename)) {
				throw new Exception("该类型名称已经存在，不能重复添加。");
			} else {
				id = computerService.getNextDeviceTypeId(device_id);
				if (id == null || id.length() != 2) {
					throw new Exception("设备类型获取错误，请重新选择");
				}
				InfoType type = new InfoType();
				String num1 = id.substring(0, 1);
				String num2 = id.substring(1, 2);
				String num3 = "";
				if (num2.equals("Z")) {
					throw new Exception("该子设备类型数添加超过35个限制，禁止再添加");
				} else {
					int len = BMSysConfigItem.SERIAL_NUM.indexOf(num2);
					num3 = BMSysConfigItem.SERIAL_NUM.substring(len + 1, len + 2);
				}
				String num = num1 + num3;
				type.setInfo_id(num);
				type.setInfo_type(typename);
				type.setSumm(summ);
				type.setDevice_type(Integer.valueOf(device_id).intValue());
				type.setProduct_type(product_type);
				computerService.addInfoDeviceType(type);
				insertCommonLog("添加信息设备类型:" + typename);
			}
			return "insert";
		} else
			return SUCCESS;
	}
}