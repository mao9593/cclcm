package hdsec.web.project.domain;

import hdsec.web.project.common.PropertyUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

/**
 * 域信息查询工具类
 * 
 * @author renmingfei 20150119
 * 
 */
public class LdapUtil implements Serializable {

	private static final long serialVersionUID = 1L;
	private final Logger logger = Logger.getLogger(this.getClass());
	private LdapContext ctx = null;

	public LdapUtil() throws Exception {
		try {
			ctx = initialCtx();
		} catch (Exception e) {
			logger.error("call " + this.getClass().getName() + ".LdapUtil() error! Detail information:" + e.getMessage());
			throw e;
		}
	}

	private LdapContext initialCtx() throws Exception {
		String ladpURL = PropertyUtil.getLdapURL();
		String principal = PropertyUtil.getLdapUsername();
		String credential = PropertyUtil.getLdapPassword();
		logger.debug("<ladpURL>" + ladpURL + ";<principal>" + principal + ";<credential>" + credential);
		Hashtable<String, String> hashEnv = new Hashtable<String, String>(); // 定义一个哈希表来存连接信息
		hashEnv.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory"); // 记录工JNDI工厂
		hashEnv.put(Context.PROVIDER_URL, ladpURL); // LDAP的地址，要根据LDAP服务器IP进行修改，389是LDAP的默认端口,636是安全端口.
		hashEnv.put(Context.SECURITY_AUTHENTICATION, "simple"); // 这个是默认授权类型，一般不用改
		hashEnv.put(Context.SECURITY_PRINCIPAL, principal); // LDAP的账户名，一般是这样的格式：dc=cs,dc=hunan,dc=com
		hashEnv.put(Context.SECURITY_CREDENTIALS, credential); // 对应上面账户的密码
		// env.put("com.sun.jndi.ldap.connect.pool", "true");
		hashEnv.put("java.naming.referral", "follow");
		hashEnv.put("java.naming.ldap.attributes.binary", "objectGUID");

		LdapContext ldapCtx = null;
		try {
			ldapCtx = new InitialLdapContext(hashEnv, null); // 初始化LDAP连接，连接成功后就可以用ctx来操作LDAP了
			logger.info("initialize ldap connection");
		} catch (NamingException e) {
			handleException("initialCtx()", e, true);
		}
		return ldapCtx;
	}

	/**
	 * 异常处理方法
	 * 
	 * @param method_name
	 * @param err
	 * @param if_throw
	 * @throws NamingException
	 */
	private void handleException(String method_name, Exception err, boolean if_throw) throws NamingException {
		String msg = "call " + this.getClass().getName() + "." + method_name + "() error! Detail information:" + err.getMessage();
		logger.error(msg);
		if (if_throw) {
			throw new NamingException(msg);
		}
	}

	/**
	 * 关闭ldap连接
	 * 
	 * @throws NamingException
	 */
	public void close() throws NamingException {
		if (ctx != null) {
			ctx.close();
			ctx = null;
		}
		logger.info("close ldap connection");
	}

	private String addLeading(int k) {
		return k <= 0xF ? "0" + Integer.toHexString(k) : Integer.toHexString(k);
	}

	public String getGuid(byte[] guid) {
		StringBuffer guidBuf = new StringBuffer();
		guidBuf.append(addLeading(guid[3] & 0xFF));
		guidBuf.append(addLeading(guid[3] & 0xFF));
		guidBuf.append(addLeading(guid[2] & 0xFF));
		guidBuf.append(addLeading(guid[1] & 0xFF));
		guidBuf.append(addLeading(guid[0] & 0xFF));
		guidBuf.append("-");
		guidBuf.append(addLeading(guid[5] & 0xFF));
		guidBuf.append(addLeading(guid[4] & 0xFF));
		guidBuf.append("-");
		guidBuf.append(addLeading(guid[7] & 0xFF));
		guidBuf.append(addLeading(guid[6] & 0xFF));
		guidBuf.append("-");
		guidBuf.append(addLeading(guid[8] & 0xFF));
		guidBuf.append(addLeading(guid[9] & 0xFF));
		guidBuf.append("-");
		guidBuf.append(addLeading(guid[10] & 0xFF));
		guidBuf.append(addLeading(guid[11] & 0xFF));
		guidBuf.append(addLeading(guid[12] & 0xFF));
		guidBuf.append(addLeading(guid[13] & 0xFF));
		guidBuf.append(addLeading(guid[14] & 0xFF));
		guidBuf.append(addLeading(guid[15] & 0xFF));
		return guidBuf.toString();
	}

	/**
	 * 按过滤条件来检索条目的下级条目 scope: 0(object),1(onelevel),2(subtree)
	 */
	public NamingEnumeration<SearchResult> searchByFilter(String ldap_path, String filter, int scope) throws NamingException {
		NamingEnumeration<SearchResult> ret = null;
		try {
			SearchControls cons = new SearchControls();
			cons.setSearchScope(scope);
			// 搜索符合条件的结果
			ret = ctx.search(ldap_path, filter, cons);
		} catch (NamingException e) {
			handleException("searchByFilter", e, true);
		}
		return ret;
	}

	/**
	 * 按过滤条件来检索条目的下级条目 scope: 0(object),1(onelevel),2(subtree)
	 * 
	 * @param name
	 * @param filter
	 * @param searchCtls
	 * @return
	 * @throws NamingException
	 */
	public List<Attributes> search2List(String name, String filter, SearchControls searchCtls) throws NamingException {
		if (!StringUtils.hasLength(name)) {
			return null;
		}
		List<Attributes> retList = new ArrayList<Attributes>();
		try {
			NamingEnumeration<SearchResult> sret = null;
			SearchResult sr = null;
			Attributes atts = null;
			if (!StringUtils.hasLength(filter)) {
				filter = "(objectClass=*)";
			}
			// 搜索符合条件的结果
			sret = ctx.search(name, filter, searchCtls);
			while (sret != null && sret.hasMore()) {
				sr = sret.next();
				atts = sr.getAttributes();
				retList.add(atts);
			}
		} catch (Exception e) {
			handleException("search2List()", e, true);
		}
		return retList;
	}

	/**
	 * 按过滤条件来检索条目的下级条目 scope: 0(object),1(onelevel),2(subtree)
	 * 
	 * @param ldap_path
	 * @param filter
	 * @param scope
	 * @return
	 * @throws NamingException
	 */
	public List<Attributes> search2List(String name, String filter, int scope) throws NamingException {
		if (!StringUtils.hasLength(name)) {
			return null;
		}
		List<Attributes> retList = new ArrayList<Attributes>();
		try {
			NamingEnumeration<SearchResult> sret = null;
			SearchResult sr = null;
			Attributes atts = null;
			if (!StringUtils.hasLength(filter)) {
				filter = "(objectClass=*)";
			}
			SearchControls cons = new SearchControls();
			cons.setSearchScope(scope);
			// 搜索符合条件的结果
			sret = ctx.search(name, filter, cons);
			while (sret != null && sret.hasMore()) {
				sr = sret.next();
				atts = sr.getAttributes();
				retList.add(atts);
			}
		} catch (Exception e) {
			handleException("search2List()", e, true);
		}
		return retList;
	}

	/**
	 * 按过滤条件来检索条目的下级条目 scope: 0(object),1(onelevel),2(subtree)
	 * 
	 * @param ldap_path
	 * @param filter
	 * @param scope
	 * @return
	 * @throws NamingException
	 */
	public List<Attributes> search2List(String name, String filter, int scope, String[] attrs, int size_limit) throws NamingException {
		if (!StringUtils.hasLength(name)) {
			return null;
		}
		List<Attributes> retList = new ArrayList<Attributes>();
		try {
			NamingEnumeration<SearchResult> sret = null;
			SearchResult sr = null;
			Attributes atts = null;
			if (filter == null || filter.equals(""))
				filter = "(objectClass=*)";
			SearchControls cons = new SearchControls();
			cons.setSearchScope(scope);
			if (size_limit > 0) {
				cons.setCountLimit(size_limit);
			}
			if (attrs != null && attrs.length > 0) {
				cons.setReturningAttributes(attrs);
			}
			// 搜索符合条件的结果
			sret = ctx.search(name, filter, cons);
			while (sret != null && sret.hasMore()) {
				sr = sret.next();
				atts = sr.getAttributes();
				retList.add(atts);
			}
		} catch (Exception e) {
			handleException("search2List()", e, true);
		}
		return retList;
	}

	/**
	 * 按属性来检索条目的下级条目
	 * 
	 * @param ldap_path
	 * @param attrs
	 * @return
	 * @throws NamingException
	 */
	public List<Attributes> search2List(String name, Attributes attrs) throws NamingException {
		if (!StringUtils.hasLength(name)) {
			return null;
		}
		List<Attributes> retList = new ArrayList<Attributes>();
		try {
			NamingEnumeration<SearchResult> sret = null;
			SearchResult sr = null;
			Attributes atts = null;
			sret = ctx.search(name, attrs);
			while (sret != null && sret.hasMore()) {
				sr = sret.next();
				atts = sr.getAttributes();
				retList.add(atts);
			}
		} catch (Exception e) {
			handleException("search2List", e, true);
		}
		return retList;
	}

	/**
	 * 测试
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LdapUtil ldap_service = new LdapUtil();
			System.out.println(ldap_service.search2List(PropertyUtil.getLdapSearchBase(), "(&(objectClass=organizationalUnit)(name~=B01))",
					SearchControls.SUBTREE_SCOPE).size());
			ldap_service.close();
		} catch (Exception e) {
			System.out.println("error:" + e.getMessage());
		}
	}

}
