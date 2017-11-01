package hdsec.web.project.user.session;

import hdsec.web.project.user.model.SecOperation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 说明: 只在session中有效的临时操作对象。仅在角色配置过程中使用。
 * @author renmingfei
 *
 */
public class SessionOper {
	public static final String OPER_SEPARATOR = ","; //操作ID列表分隔符，用于生成SQL语句中的IN子名的被选值列表以及分割从客户端接收的操作ID列表字符串
	public static final String YES = "Y"; //对应数据库中的char(1)类型数据的'Y'值。
	
	private Integer operID; //操作ID
	private String operCode; //操作编码
	private String operName; //操作名称
	private String operDesc; //操作描述
	private String enDirectory; //是否为目录类型操作
	private String enPrvtOper;
	private List correlativeResTypes; //关联的资源类型集合 List<SessionResType>
	private Set authoritedReses; //已授权的资源集合Set<SessionAbstractRes>
	private boolean authorited; //当前操作是否被授权
	private boolean newOper; //新增或被修改过的操作此属性为true
	
	public SessionOper() {
		correlativeResTypes = new ArrayList();
		authoritedReses = new TreeSet();
	}
	
	public SessionOper(SecOperation func) {
		this();
		setOperCode(func.getOper_code());
		setOperName(func.getOper_name());
		setOperDesc(func.getOper_desc());
		setEnDirectory(func.getEn_directory());
		setEnPrvtOper(func.getEn_prvt_oper());
	}
	
	public Integer getOperID() {
		return operID;
	}
	
	public void setOperID(Integer operID) {
		this.operID = operID;
	}
	
	public String getOperCode() {
		return operCode;
	}
	
	public void setOperCode(String operCode) {
		this.operCode = operCode;
	}
	
	public String getOperName() {
		return operName;
	}
	
	public void setOperName(String operName) {
		this.operName = operName;
	}
	
	public String getOperDesc() {
		return operDesc;
	}
	
	public void setOperDesc(String operDesc) {
		this.operDesc = operDesc;
	}
	
	public boolean isEnDirectory() {
		return enDirectory != null && enDirectory.equals(YES);
	}
	
	public boolean isEnPrvtOper() {
		return enPrvtOper != null && enPrvtOper.equals(YES);
	}
	
	public String getEnDirectory() {
		return enDirectory;
	}
	
	public void setEnDirectory(String enDirectory) {
		this.enDirectory = enDirectory;
	}
	
	public String getEnPrvtOper() {
		return enPrvtOper;
	}
	
	public void setEnPrvtOper(String enPrvtOper) {
		this.enPrvtOper = enPrvtOper;
	}
	
	public boolean isNewOper() {
		return newOper;
	}
	
	/**
	 * 将当前操作标记为新操作
	 */
	public void changeStatusToNew() {
		this.newOper = true;
	}
	
	public List getCorrelativeResTypes() {
		return correlativeResTypes;
	}
	
	public Set getAuthoritedReses() {
		return authoritedReses;
	}
	
	/**
	 * 将参数中的资源集合做为当前操作的已授权资源集合，本方法与
	 * public void setAuthoritedReses(Collection authoritedReses)方法功能含义相同，
	 * 但本方法只在初始化当前操作对象时使用。
	 * @param authoritedReses 资源集合，格式为：Collection<SessionAbstractRes>
	 */
	public void setAuthoritedResesInit(Collection authoritedReses) {
		this.authoritedReses.clear();
		this.authoritedReses.addAll(authoritedReses);
	}
	
	/**
	 * 将参数中的资源集合做为当前操作的已授权资源集合，本方法与
	 * public void setAuthoritedResesInit(Collection authoritedReses)方法功能含义相同，
	 * 但本方法用于除初始化当前操作对象以外的情况。
	 * @param authoritedReses 资源集合，格式为：Collection<SessionAbstractRes>
	 */
	public void setAuthoritedReses(Collection authoritedReses) {
		this.authoritedReses.clear();
		this.authoritedReses.addAll(authoritedReses);
	}
	
	public boolean isAuthorited() {
		return authorited;
	}
	
	/**
	 * 设置当前操作的授权状态，本方法与public void setAuthorited(boolean authorited)方法
	 * 功能含义相同，但本方法只在初始化当前操作对象时使用。
	 * @param authorited 授权状态
	 */
	public void setAuthoritedAtInit(boolean authorited) {
		this.authorited = authorited;
	}
	
	/**
	 * 设置当前操作的授权状态，本方法与public void setAuthoritedAtInit(boolean authorited)方法功能含义相同，
	 * 但本方法用于除初始化当前操作对象以外的情况。
	 * @param authorited 授权状态
	 */
	public void setAuthorited(boolean authorited) {
		if (!this.newOper)
			this.newOper = true; //被授权的资源被修改则将当前操作标记为新操作
		this.authorited = authorited;
	}
}
