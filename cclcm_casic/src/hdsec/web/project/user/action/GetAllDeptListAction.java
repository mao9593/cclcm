package hdsec.web.project.user.action;

import hdsec.web.project.common.util.DeptListUtil;

import javax.annotation.Resource;

import org.w3c.dom.Document;

import com.casic304.util.xml.XmlUtil;

/**
 * 用户和部门树的服务端程序，查询所有部门的树形结构
 * @author renmingfei
 *
 */
public class GetAllDeptListAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	@Resource
	private DeptListUtil deptListUtil;
	private String selected = "";
	private String treeType = "self";
	private boolean reload = false;
	private Document doc;
	
	public String getSelected() {
		return selected;
	}
	
	public void setSelected(String selected) {
		this.selected = selected;
	}
	
	public String getTreeType() {
		return treeType;
	}
	
	public void setTreeType(String treeType) {
		this.treeType = treeType;
	}
	
	public boolean isReload() {
		return reload;
	}
	
	public void setReload(String reload) {
		this.reload = reload.equalsIgnoreCase("true");
	}
	
	@Override
	public String executeFunction() throws Exception {
		if (selected.equalsIgnoreCase(""))
			selected = null;
		if ((!treeType.equalsIgnoreCase("self")) && (!treeType.equalsIgnoreCase("child")))
			treeType = "self";
		doc = XmlUtil.getNewDoc(deptListUtil.getDeptListTreeXML(selected, treeType, reload).toString());
		return SUCCESS;
	}
	
	public String getDeptListDoc() {
		return XmlUtil.toString(doc);
	}
}
