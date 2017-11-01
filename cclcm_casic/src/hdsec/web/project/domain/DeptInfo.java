package hdsec.web.project.domain;

/**
 * 域同步，部门类
 * 
 * @author renmingfei 20150119
 */
public class DeptInfo {
	private String deptId;
	private String deptName;

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public DeptInfo(String deptId, String deptName) {
		super();
		this.deptId = deptId;
		this.deptName = deptName;
	}

	public DeptInfo() {
		super();
	}

	@Override
	public String toString() {
		return "deptId<" + deptId + ">,deptName<" + deptName + ">";
	}
}
