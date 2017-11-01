package hdsec.web.project.common.server;

import hdsec.web.project.basic.model.Message;
import hdsec.web.project.basic.model.Messages;
import hdsec.web.project.basic.model.TransferMessage;
import hdsec.web.project.common.XMLParseUtil;
import hdsec.web.project.common.model.Organization;
import hdsec.web.project.common.model.Organizations;
import hdsec.web.project.common.model.UserInfo;
import hdsec.web.project.common.model.Users;
import hdsec.web.project.common.util.Constants;
import hdsec.web.project.common.util.PropertiesFileUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.jws.WebService;

import org.springframework.util.StringUtils;

@WebService(serviceName = "CommonService", portName = "CommonServicePort", targetNamespace = "http://service.common.project.web.hdsec/", endpointInterface = "hdsec.web.project.common.server.Common")
public class CommonImpl implements Common {
	

	private static String url = null;
	private static String userName = null;
	private static String password = null;
	static{
		 url = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "jdbc.url");
		 userName = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "jdbc.username");
		 password = PropertiesFileUtil.getProperty(Constants.PROJECT_CONFIG_FILE, "jdbc.password");
	}

	@Override
	public String getMessage(String userId) {
		Messages ms = new Messages();
		try {
			if (null != getMessagesByPID(userId) && getMessagesByPID(userId).size() > 0) {
				ms.setMessages(getMessagesByPID(userId));
				return XMLParseUtil.beanToXMLString(ms);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	List<Message> getMessagesByPID(String pid) throws Exception {
		List<Message> messages = new ArrayList<Message>();
		for (TransferMessage tMessage : getTransferMessageByPID(pid)) {
			messages.add(changeTransferMessage(tMessage));
		}
		return messages;
	}

	
	List<TransferMessage> getTransferMessageByPID(String pid) throws Exception {
		List<TransferMessage> list = new ArrayList<TransferMessage>();
		
		Connection conn = DriverManager.getConnection(url, userName, password);
		if (conn != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
//				ps = conn.prepareStatement("select * from transfer_message m,sec_user u where m.pid = u.user_name and u.idcard= \'"+ pid +"\'");
				ps = conn.prepareStatement("select * from transfer_message m,sec_user u where m.pid = u.user_name and u.idcard= ?");
				ps.setString(1, pid);
				rs = ps.executeQuery();
				while (rs.next()) {
					TransferMessage tm = new TransferMessage();
					tm.setSesquenceno((String) rs.getString("sesquenceno"));
					tm.setPid((String) rs.getString("pid"));
					tm.setSysId((String) rs.getString("sysId"));
					tm.setSysName((String) rs.getString("sysName"));
					tm.setDealLevel((String) rs.getString("dealLevel"));
//					tm.setWordFunc((String) rs.getString("wordFunc"));
					tm.setUrgent((String) rs.getString("urgent"));
					tm.setLeaveword((String) rs.getString("leaveword"));
					tm.setFromMan((String) rs.getString("fromMan"));
					tm.setFromDate((Date) rs.getDate("fromDate"));
					tm.setTitle((String) rs.getString("title"));
					tm.setLinkUrl((String) rs.getString("linkUrl"));
					tm.setReaded((String) rs.getString("readed"));
					tm.setFromDept((String) rs.getString("fromDept"));
					list.add(tm);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				if (rs != null) {
					try {
						rs.close();
						rs = null;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (ps != null) {
					try {
						ps.close();
						ps = null;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				if (conn != null) {
					try {
						conn.close();
						conn = null;
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}

			}
		}

		return list;
	}

	private Message changeTransferMessage(TransferMessage tMessage) {
		Message message = new Message();
		message.setMgsFromDept(tMessage.getFromDept());
		message.setMgsFromSys(tMessage.getSysName());
		// message.setMgsType("");
		message.setMgsLevel("1");
		// message.setMgsFunc("");
		message.setMgsWord("");
		// message.setMgsFromName("");
		message.setSentTime(tMessage.getFromDate());
		message.setMgsUrgent("特急");
		// message.setMgsOperateType("");
		message.setMgsStatus(tMessage.getReaded());
		message.setMgsAccessory("0");
		message.setTitle(tMessage.getTitle());
		message.setUrl(tMessage.getLinkUrl());
		return message;
	}

	@Override
	public String sayHello(String name) {
		return "Hello " + name;
	}

	@Override
	public String syncUser(String usersInfoStr) {
		Users users = new Users();
		StringBuffer sb = new StringBuffer();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			users = (Users) XMLParseUtil.XMLStringToBean(usersInfoStr, users);
			conn = DriverManager.getConnection(url, userName, password);
			if (conn != null) {
				String sql = null;
				for(UserInfo user :users.getUser()){
					if("DEL".equals(user.getDealFlag())){
						sql = "delete from sec_user where idcard = ?";
						ps = conn.prepareStatement(sql);
						ps.setString(1, user.getPID());
						if(!ps.execute()){
							sb.append("删除用户出错， 身份证号为 ：" + user.getPID()+ "  ,用户姓名为:" + user.getOperatorName());
						}
					}else{
						sql = "select count(1) from sec_user u, sec_dept d where u.dept_id = d.dept_id and u.idcard = ? and d.other_dept_code = ?";
						ps = conn.prepareStatement(sql);
						ps.setString(1, user.getPID());
						ps.setString(2, user.getOrgCode());
						rs = ps.executeQuery();
						while(rs.next()){
							if(((int)rs.getInt(1)) > 0){
								sql = "update sec_user set user_iidd = ?,user_name = ? where u.idcard = ?";
								ps = conn.prepareStatement(sql);
								ps.setString(1, user.getUserID());
								ps.setString(2, user.getOperatorName());
								ps.setString(3, user.getPID());
								if(ps.execute()){
									sql = "update u set u.dept_id = d.dept_id from sec_user u, sec_dept d where u.idcard = ? and d.other_dept_code = ?";
									ps = conn.prepareStatement(sql);
									ps.setString(1, user.getPID());
									ps.setString(2, user.getOrgCode());
									if(!ps.execute()){
										sb.append("更新用户出错， 身份证号为 ：" + user.getPID()+ "  ,用户姓名为:" + user.getOperatorName());
									}
								}else{
									sb.append("更新用户出错， 身份证号为 ：" + user.getPID()+ "  ,用户姓名为:" + user.getOperatorName());
								}
							}else{
								sql = "select dept_id from sec_dept d where d.other_dept_code = ?";
								ps = conn.prepareStatement(sql);
								ps.setString(1, user.getOrgCode());
								rs = ps.executeQuery();
								while(rs.next()){
									if(((int)rs.getInt(1)) == 1){
										String dept_id = (String) rs.getString("dept_id");
										sql = "insert into sec_user(user_iidd,real_user_id,user_name,dept_id,idcard,security_code) values(?,?,?,?,?,?)";
										ps = conn.prepareStatement(sql);
										ps.setString(1, user.getUserID());
										ps.setString(2, user.getUserID());
										ps.setString(3, user.getOperatorName());
										ps.setString(4, dept_id);
										ps.setString(5, user.getPID());
										ps.setString(6, user.getSecret());
										if(!ps.execute()){
											sb.append("添加用户出错， 身份证号为 ：" + user.getPID()+ "  ,用户姓名为:" + user.getOperatorName());
										}
									}else if(((int)rs.getInt(1)) == 1){
										sb.append("添加用户出错， 身份证号为 ：" + user.getPID()+ "  ,用户姓名为:" + user.getOperatorName() + "  原因为用户部门org_code不存在 :" + user.getOrgCode());
									}
								}
							}
						}
					}
				}
			}
		
		} catch (Exception e) {
			sb.append("系统同步用户出错！");
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
		return sb.toString();
	}

	@Override
	public String syncDept(String deptsInfoStr) {
		Organizations orgs = new Organizations();
		StringBuffer sb = new StringBuffer();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			orgs = (Organizations)XMLParseUtil.XMLStringToBean(deptsInfoStr, orgs);
			conn = DriverManager.getConnection(url, userName, password);
			if (conn != null) {
				String sql = null;
				for(Organization org : orgs.getOrganization()){
					if("DEL".equals(org.getDealFlag())){
						sql = "delete from sec_dept where other_dept_code =?";
						ps = conn.prepareStatement(sql);
						ps.setString(1, org.getOrgCode());
						if(!ps.execute()){
							sb.append("删除部门出错， orgCode ：" + org.getOrgCode());
						}
					}else{
						sql = "select count(1) from sec_dept where other_dept_code = ?";
						ps = conn.prepareStatement(sql);
						ps.setString(1, org.getOrgCode());
						rs = ps.executeQuery();
						while(rs.next()){
							if(((int)rs.getInt(1)) > 0){
								if(StringUtils.hasLength(org.getOrgPCode())){
									sql = "select other_p_dept_code from sec_dept where other_dept_code = ?";
									ps = conn.prepareStatement(sql);
									ps.setString(1, org.getOrgPCode());
									rs = ps.executeQuery();
									if(rs.next()){
										if(!org.getOrgPCode().equals((String) rs.getString("other_p_dept_code"))){
											String dept_cs = getDeptcsByParentDeptCode(org.getOrgPCode(),conn);
											String dept_parent_id = getParentIdByParentDeptCode(org.getOrgPCode(), conn);
											sql = "update sec_dept set dept_name = ?,dept_cs = ?,other_p_dept_code = ?,dept_parent_id=? where other_dept_code = ?";
											ps = conn.prepareStatement(sql);
											ps.setString(1, org.getOrgName());
											ps.setString(2, dept_cs);
											ps.setString(3, org.getOrgPCode());
											ps.setString(4, dept_parent_id);
											ps.setString(5, org.getOrgCode());
											if(!ps.execute()){
												sb.append("更新部门出错， orgCode ：" + org.getOrgCode());
											}
										}else{
											sql = "update sec_dept set dept_name = ? where other_dept_code = ?";
											ps = conn.prepareStatement(sql);
											ps.setString(1, org.getOrgName());
											ps.setString(2, org.getOrgCode());
											if(!ps.execute()){
												sb.append("更新部门出错， orgCode ：" + org.getOrgCode());
											}
										}
									}else{
										sql = "update sec_dept set dept_name = ? where other_dept_code = ?";
										ps = conn.prepareStatement(sql);
										ps.setString(1, org.getOrgName());
										ps.setString(2, org.getOrgCode());
										if(!ps.execute()){
											sb.append("更新部门出错， orgCode ：" + org.getOrgCode());
										}
									}
								}else{
									sql = "update sec_dept set dept_name = ? where other_dept_code = ?";
									ps = conn.prepareStatement(sql);
									ps.setString(1, org.getOrgName());
									ps.setString(2, org.getOrgCode());
									if(!ps.execute()){
										sb.append("更新部门出错， orgCode ：" + org.getOrgCode());
									}
								}
							}else{
								String deptcs = getDeptcsByParentDeptCode(org.getOrgPCode(),conn);
								String parentID = getParentIdByParentDeptCode( org.getOrgPCode(), conn);
								sql = "insert into sec_dept(dept_id,dept_name,dept_cs,dept_parent_id,other_dept_code,other_p_dept_code) values(?,?,?,?,?,?)";
								ps = conn.prepareStatement(sql);
								ps.setString(1, UUID.randomUUID().toString());
								ps.setString(2, org.getOrgName());
								ps.setString(3, deptcs);
								ps.setString(4, parentID);
								ps.setString(3, org.getOrgCode());
								ps.setString(3, org.getOrgPCode());
								if(!ps.execute()){
									sb.append("插入部门出错， orgCode ：" + org.getOrgCode());
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
					conn = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return sb.toString();
	}
	
	private String getDeptcsByParentDeptCode(String orgPCode,Connection conn) throws Exception{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			String sql = null;
			sql = "select max(dept_cs) p_cs from sec_dept where other_P_dept_code = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orgPCode);
			rs = ps.executeQuery();
			while(rs.next()){
				String p_cs = (String) rs.getString("p_cs");
				String pre = p_cs.substring(0,p_cs.length() - 2);
				int l = Integer.parseInt(String.valueOf(p_cs.charAt(p_cs.length() -1)));
				int p = Integer.parseInt(String.valueOf(p_cs.charAt(p_cs.length() -2)));
				if(l == 9){
					return pre + (++p) +"0";
				}else{
					return pre + p + (++l);
				}
			}
		}finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return null;
	}
	
	private String getParentIdByParentDeptCode(String orgPCode,Connection conn) throws Exception{
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			String sql = null;
			sql = "select dept_id from sec_dept where other_dept_code = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, orgPCode);
			rs = ps.executeQuery();
			while(rs.next()){
				return (String) rs.getString("dept_id");
			}
		}finally {
			if (rs != null) {
				try {
					rs.close();
					rs = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
					ps = null;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
		}
		return null;
	}

}
