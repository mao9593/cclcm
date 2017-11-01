package hdsec.web.project.computer.action;

import hdsec.web.project.common.bm.BMCycleItem;
import hdsec.web.project.computer.model.EntityComputer;
import hdsec.web.project.computer.model.InfoType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class ViewComputerDetailAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;

	EntityComputer computer = null;
	private String computer_barcode = ""; // 条码号
	private List<BMCycleItem> itemList = null;

	public EntityComputer getComputer() {
		return computer;
	}

	public void setComputer(EntityComputer computer) {
		this.computer = computer;
	}

	public String getComputer_barcode() {
		return computer_barcode;
	}

	public void setComputer_barcode(String computer_barcode) {
		this.computer_barcode = computer_barcode;
	}

	public List<BMCycleItem> getItemList() {
		return itemList;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(computer_barcode)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("computer_barcode", computer_barcode);
			computer = computerService.getComputerByMap(map);
			if (!computer.getSoftware_type().equals("")) {
				InfoType medname = null;
				String names = "";
				String[] med = computer.getSoftware_type().split(",");
				for (int i = 0; i < med.length; i++) {
					map.put("info_id", med[i].trim());
					medname = computerService.getInfoTypeByID(map);
					names += medname.getInfo_type() + ",";
				}
				computer.setSoftware_type(names);
			}
			if (!computer.getKey_code().equals("")) {
				String[] code = computer.getKey_code().split(",");
				computer.setKey_code(code[0]);
			}
			itemList = securityUserService.getCycleItemListByBarcode(computer_barcode);
			// 添加全生命周期审批任务链接按钮。
			for (BMCycleItem item : itemList) {
				if (!item.getJob_code().equals("")) {
					if (item.getJob_code().contains("EVENT_REPCOM") || item.getJob_code().contains("EVENT_REINSTALL")
							|| item.getJob_code().contains("EVENT_QUITINT")
							|| item.getJob_code().contains("EVENT_USBKEY")
							|| item.getJob_code().contains("EVENT_OPENPORT")
							|| item.getJob_code().contains("EVENT_LOCALPRINTER")) {// 维修,重装,退网,USBKEY,开通端口,保留本地打印机
						item.setType("infosystem");
					} else {
						item.setType("computer");
					}
				}
			}
			return SUCCESS;
		} else {
			throw new Exception("无法查询条码号，请重新尝试。");
		}
	}
}