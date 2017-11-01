package hdsec.web.project.computer.model;

import hdsec.web.project.common.BaseDomain;

/**
 * 信息设备类型类
 * 
 * @author guojiao
 * 
 */
public class InfoType extends BaseDomain {
	private String info_id = "";// 主键，唯一标示子类型ID
	private String info_type = "";// 子类型名称
	private Integer device_type = null;// 设备类型（1：计算机 2：网络设备 3：外部设备 4：办公自动化设备 5：安全产品 6：介质）
	private String summ = "";// 子类型备注说明
	private Integer statue = null;// 该类型是否启用（0：默认启用；1、已停用）
	private Integer product_type = null;// 该字段仅标示 5.安全产品类 中区分软硬件类型（1软件2硬件，其他默认均为0）

	public Integer getStatue() {
		return statue;
	}

	public void setStatue(Integer statue) {
		this.statue = statue;
	}

	public String getInfo_id() {
		return info_id;
	}

	public void setInfo_id(String info_id) {
		this.info_id = info_id;
	}

	public String getInfo_type() {
		return info_type;
	}

	public void setInfo_type(String info_type) {
		this.info_type = info_type;
	}

	public Integer getDevice_type() {
		return device_type;
	}

	public void setDevice_type(Integer device_type) {
		this.device_type = device_type;
	}

	public String getSumm() {
		return summ;
	}

	public void setSumm(String summ) {
		this.summ = summ;
	}

	public Integer getProduct_type() {
		return product_type;
	}

	public String getProduct_type_name() {
		if (product_type == null) {
			return "";
		} else {
			switch (product_type) {
			case 0:
				return "";
			case 1:
				return "软件";
			case 2:
				return "硬件";
			default:
				return "";
			}
		}
	}

	public void setProduct_type(Integer product_type) {
		this.product_type = product_type;
	}

	public InfoType() {

	}
}
