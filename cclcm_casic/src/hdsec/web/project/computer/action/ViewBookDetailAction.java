package hdsec.web.project.computer.action;

import hdsec.web.project.common.bm.BMCycleItem;
import hdsec.web.project.computer.model.EntityBook;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class ViewBookDetailAction extends ComputerBaseAction {

	private static final long serialVersionUID = 1L;
	private List<EntityBook> book = null;
	private EntityBook device = null;
	private String device_barcode = "";
	private List<BMCycleItem> itemList = null;

	public EntityBook getDevice() {
		return device;
	}

	public String getDevice_barcode() {
		return device_barcode;
	}

	public void setDevice_barcode(String device_barcode) {
		this.device_barcode = device_barcode;
	}

	public List<BMCycleItem> getItemList() {
		return itemList;
	}

	@Override
	public String executeFunction() throws Exception {
		if (StringUtils.hasLength(device_barcode)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("book_barcode", device_barcode);
			itemList = securityUserService.getCycleItemListByBarcode(device_barcode);
			// 添加全生命周期审批任务链接按钮。
			for (BMCycleItem item : itemList) {
				if (!item.getJob_code().equals("")) {
					if (item.getJob_code().contains("BOOK_CHANGE") || item.getJob_code().contains("BOOK_REPAIR")
							|| item.getJob_code().contains("BOOK_DES") || item.getJob_code().contains("BOOK_REINSTALL")) {// 变更,维修,重装,报废
						item.setType("event");
					}
				}
			}
			book = computerService.getBookList(map);
			if (book != null)
				device = book.get(0);
			else
				throw new Exception("无法查询条码号，请重新尝试。");

			return SUCCESS;
		} else {
			throw new Exception("无法查询条码号，请重新尝试。");
		}
	}
}