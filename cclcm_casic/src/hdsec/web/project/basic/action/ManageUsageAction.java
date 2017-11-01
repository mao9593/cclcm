package hdsec.web.project.basic.action;

import hdsec.web.project.basic.model.SysUsage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 用途管理列表
 * 
 * @author renmingfei
 * 
 */
public class ManageUsageAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private List<SysUsage> usageList = new ArrayList<SysUsage>();
	private String is_sealed = "N";

	public List<SysUsage> getUsageList() {
		return usageList;
	}

	public String getIs_sealed() {
		return is_sealed;
	}

	public void setIs_sealed(String is_sealed) {
		this.is_sealed = is_sealed;
	}

	private String getModuleNames(String module_code) {
		String names = "";
		if (StringUtils.hasLength(module_code)) {
			StringBuffer sb = new StringBuffer();
			String codes[] = module_code.split(",");
			for (String code : codes) {
				if (!code.isEmpty()) {
					if ("PRINT".equals(code)) {
						sb.append("打印").append(",");
					} else if ("BURN".equals(code)) {
						sb.append("刻录").append(",");
					} else if ("COPY".equals(code)) {
						sb.append("复印").append(",");
					} else if ("LEADIN".equals(code)) {
						sb.append("录入").append(",");
					} else if ("TRANSFER".equals(code)) {
						sb.append("流转").append(",");
					} else if ("DEVICE".equals(code)) {
						sb.append("磁介质").append(",");
					} else if ("STORAGE".equals(code)) {
						sb.append("存储介质").append(",");
					} else if ("BORROW".equals(code)) {
						sb.append("部门载体借用").append(",");
					} else if ("CHANGE".equals(code)) {
						sb.append("载体归属转换").append(",");
					} else if ("INPUT".equals(code)) {
						sb.append("电子文件导入").append(",");
					}
				}
			}
			names = sb.toString();
			names = names.substring(0, names.length() - 1);
		}
		return names;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_sealed", is_sealed);
		List<SysUsage> tempList = basicService.getSysUsageList(map);
		for (SysUsage usage : tempList) {
			String name = getModuleNames(usage.getModule_code());
			usage.setModule_name(name);
			usageList.add(usage);
		}
		return SUCCESS;
	}
}
