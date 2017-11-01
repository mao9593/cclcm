package hdsec.web.project.user.session;

import hdsec.web.project.user.model.Acl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 说明:只在session中有效的临时访问控制对象。仅在角色配置过程中使用。
 * @author renmingfei
 *
 */
public class SessionACL {
	private String subSysCode; //子系统编码
	private String roleID; //角色ID
	private Integer currentOperID; //当前操作ID
	private String roleName; //角色名称
	private Map<String, SessionOper> operations; //所有操作集合
	
	/**
	 * 格式为：Map<resTypeCode, resList>
	 * 使用Map并用resTypeCode做为关键字将所有资源分类意在配合程序实现
	 * 所有资源的延迟加载，并在为一个操作配置资源时实现快速的分类检索所有资源。
	 */
	private Map resources;
	
	public SessionACL() {
		operations = new TreeMap();
		resources = new TreeMap();
	}
	
	public SessionACL(String subSysCode, String roleID, String roleName) {
		this();
		this.subSysCode = subSysCode;
		this.roleID = roleID;
		this.roleName = roleName;
	}
	
	public String getRoleID() {
		return roleID;
	}
	
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	
	public Integer getCurrentOperID() {
		return currentOperID;
	}
	
	public void setCurrentOperID(Integer currentOperID) {
		this.currentOperID = currentOperID;
	}
	
	public String getSubSysCode() {
		return subSysCode;
	}
	
	public void setSubSysCode(String subSysCode) {
		this.subSysCode = subSysCode;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public Map getOperations() {
		return operations;
	}
	
	public void setOperations(Map operations) {
		this.operations = operations;
	}
	
	public void addOperation(SessionOper operation) {
		this.operations.put(operation.getOperCode(), operation);
	}
	
	/**
	 * 授权一个操作，通常是资源无关的操作 added by renmingfei 20130727
	 * @param operCode 操作编号
	 */
	public void authorityOper(String operCode) {
		Object obj = this.operations.get(operCode);
		if (obj != null) {
			((SessionOper) obj).setAuthorited(true);
		}
	}
	
	/**
	 * 取消一个操作的授权  added by renmingfei 20130727
	 * @param operCode 操作编号
	 */
	public void unAuthorityOper(String operCode) {
		Object obj = this.operations.get(operCode);
		if (obj != null) {
			((SessionOper) obj).setAuthorited(false);
		}
	}
	
	/**
	 * 授权一个操作，通常是资源无关的操作
	 * @param operID 操作ID
	 */
	public void authorityOper(Integer operCode) {
		Object obj = this.operations.get(operCode);
		if (obj != null) {
			((SessionOper) obj).setAuthorited(true);
		}
	}
	
	/**
	 * 取消一个操作的授权
	 * @param operCode 操作编号
	 */
	public void unAuthorityOper(Integer operID) {
		Object obj = this.operations.get(operID);
		if (obj != null) {
			((SessionOper) obj).setAuthorited(false);
		}
	}
	
	public SessionOper getCurrentOper() {
		return this.operations.get(this.currentOperID);
	}
	
	public SessionOper getOperation(Integer operID) {
		return this.operations.get(operID);
	}
	
	public Map getResources() {
		return resources;
	}
	
	public List getResources(Integer resTypeCode) {
		return (List) resources.get(resTypeCode);
	}
	
	/**
	 * 判断当前资源集合中是否包含参数中给出的资源类型编码对应的资源类型
	 * @param resTypeCode 资源类型编码
	 * @return 否包为true，否则为false
	 */
	public boolean containsResType(Integer resTypeCode) {
		return this.resources.containsKey(resTypeCode);
	}
	
	/**
	 * 取得当前操作相关的所有资源（包含规则），按资源类型分类。
	 * @param operID 操作ID
	 * @return 当前操作相关的所有资源（包含规则）
	 */
	public Map allResesByOperID(Integer operID) {
		Map returnMap = new TreeMap();
		List resTypes = this.operations.get(operID).getCorrelativeResTypes();
		for (int i = 0, n = resTypes.size(); i < n; i++) {
			returnMap.put(resTypes.get(i), this.resources.get(resTypes.get(i)));
		}
		
		return returnMap;
	}
	
	/**
	 * 返回所有新的或被修改过的操作的ID列表
	 * @return 所有新的或被修改过的操作的ID列表，格式为：xxx,xxx,xxx,...
	 */
	public String getModifiedOperIDs() {
		StringBuffer sb = new StringBuffer();
		Iterator<SessionOper> ite = this.operations.values().iterator();
		SessionOper oper;
		while (ite.hasNext()) {
			oper = ite.next();
			if (oper.isNewOper()) { //未被授权或是新授权或是被修改过授权状态，不是新的或没有被修改过的操作不处理
				sb.append(oper.getOperID());
				sb.append(SessionOper.OPER_SEPARATOR);
			}
		}
		String IDs = sb.length() > 0 ? sb.substring(0, sb.length() - 1).toString() : "";
		
		return IDs;
	}
	
	/**
	 * 获得修改过的操作编号列表 add by renmingfei 20130727
	 * @return
	 */
	public List<String> getModifiedOperCodeList() {
		List<String> list = new ArrayList<String>();
		for (SessionOper item : this.operations.values()) {
			if (item.isNewOper()) {
				list.add(item.getOperCode());
			}
		}
		return list;
	}
	
	public List<Integer> getModifiedOperIdList() {
		List<Integer> list = new ArrayList<Integer>();
		for (SessionOper item : this.operations.values()) {
			if (item.isNewOper()) {
				list.add(item.getOperID());
			}
		}
		return list;
	}
	
	/**
	 * 将当前配置数据转成一个集合，集合的每一个元素都是与数据库中当前子系统的访问控制表（sec_acl_xxx）
	 * 的记录对应的{@link eoms.sysmodel.security.model.Acl}类的实例，用于
	 * 向数据库中插入相应记录。
	 * @return 结构为List<Acl>的集合
	 */
	public List<Acl> getAclList() {
		List<Acl> acls = new ArrayList<Acl>();
		SessionOper oper;
		Acl acl;
		Iterator operIte = operations.values().iterator(); //得到所有操作
		while (operIte.hasNext()) { //遍历所有操作集合
			oper = (SessionOper) operIte.next();
			if (!oper.isNewOper()) { //不是新的或没有被修改过的操作不处理
				continue;
			}
			if (oper.isAuthorited()) { //如果当前操作资源有无且已授权
				//构造资源无关操作的Acl对象
				acl = new Acl(this.roleID, oper.getOperCode());
				acls.add(acl); //每个已授权的资源无关操作形成一条记录
			}
		}
		
		return acls;
	}
}
