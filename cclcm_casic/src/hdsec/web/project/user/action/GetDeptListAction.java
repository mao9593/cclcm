package hdsec.web.project.user.action;

import hdsec.web.project.user.model.SecUser;

/**
 * 按部门查询时使用的中转action,只传参，没有操作
 * @author renmingfei
 *
 */
public class GetDeptListAction extends UserBaseAction {
	private static final long serialVersionUID = 1L;
	String objName = "";
	String objId = "";
	String inputType = "";
	String dept_id = "";
	
	public String getDept_id() {
		SecUser user = getSecUserFromSession();
		return user.getDept_id();//返回用户的主要部门的部门代码
	}
	
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	
	public String getObjName() {
		return objName;
	}
	
	public void setObjName(String objName) {
		this.objName = objName;
	}
	
	public String getObjId() {
		return objId;
	}
	
	public void setObjId(String objId) {
		this.objId = objId;
	}
	
	public String getInputType() {
		return inputType;
	}
	
	public void setInputType(String inputType) {
		this.inputType = inputType;
	}
	
	@Override
	public String executeFunction() {
		// TODO Auto-generated method stub
		return SUCCESS;
	}
	
}
