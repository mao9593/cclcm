package hdsec.web.project.webservice.action;

import hdsec.web.project.basic.model.ClientMsg;
import hdsec.web.project.client.model.PendingWorkItem;
import hdsec.web.project.client.service.ClientService;
import hdsec.web.project.user.model.SecUser;
import hdsec.web.project.user.service.UserService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 协同平台待办文件列表 2015-1-6 下午1:37:00
 * 
 * @author chenrongrong
 */
public class WaitTodoAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private UserService userService;
	@Resource
	protected ClientService clientService;
	private SecUser user = null;
	private List<PendingWorkItem> waitTodoList = new ArrayList<PendingWorkItem>();
	private String PID = "";
	private StringBuilder xmlStr = new StringBuilder();
	private StringBuilder xmlStr1 = new StringBuilder();
	private StringBuilder xmlStr2 = new StringBuilder();
	private String strXml = "";
	private List<ClientMsg> cmsList = new ArrayList<ClientMsg>();
	private String htp = "";

	@Override
	public String execute() throws Exception {
		try {
			user = userService.getSecUserByIdcard(PID);
			logger.info("**********userName=" + user.getUser_name());
			String permission = "burn/managenasburnevent.action";
			boolean nasFlag = user.hasPermission(permission);
			waitTodoList = clientService.getLeaderWorkList1(user.getUser_iidd(), nasFlag);
			xmlStr.append("<?xml version=\"1.0\" encoding=\"GBK\"?>");
			int num = 0;
			if (waitTodoList.size() > 0) {
				for (int i = 0; i < waitTodoList.size(); i++) {
					PendingWorkItem pwi = waitTodoList.get(i);
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("accept_user_iidd", user.getUser_iidd());
					map.put("oper_type", pwi.getItem_type());
					cmsList = clientService.getClientMessage(map);
					num += cmsList.size();
					String operType = pwi.getItem_type();
					if (cmsList.size() > 0) {
						for (int j = 0; j < cmsList.size(); j++) {
							ClientMsg cms = cmsList.get(j);
							Date Insert_time = cms.getInsert_time();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String send_time = sdf.format(Insert_time);
							String message = cms.getMessage();
							String user_iidd = cms.getUser_iidd();
							String dept_id = cms.getDept_id();
							String dept_name = clientService.getDeptName(dept_id);
							String user_name = clientService.getUserName(user_iidd);
							xmlStr2.append("<Mgs><MgsFromDept>"
									+ dept_name
									+ "</MgsFromDept><MgsFromSys>"
									+ "航盾涉密载体全生命周期电子化闭环管理系统"
									+ "</MgsFromSys><MgsType> "
									+ "待办文件"
									+ "</MgsType><MgsLevel> "
									+ "优先待办"
									+ "</MgsLevel><MgsFunc>"
									+ "审批"
									+ "</MgsFunc><MgsWord></MgsWord><MgsFromName>"
									+ user_name
									+ "</MgsFromName><SentTime>"
									+ send_time
									+ "</SentTime><MgsUrgent>"
									+ "紧急"
									+ "</MgsUrgent><OperateType>"
									+ "OpWin"
									+ "</OperateType><MgsStatus>1</MgsStatus><MgsAccessory></MgsAccessory><Title>涉密文件待审批:"
									+ message + "</Title><Url>" + htp + "webservice/checkurl.action?operType="
									+ operType + "</Url><WfUrl></WfUrl><DEALFLAG>" + "INS" + "</DEALFLAG></Mgs>");
						}
					}
				}
			} else {
				logger.info("需要待办的内容为空。。。。。");
			}
			xmlStr1.append("<list type=\"Mgs\" rowNum=\"" + num + "\">");
			xmlStr.append(xmlStr1.toString());
			xmlStr.append(xmlStr2.toString());
			xmlStr.append("</list>");
			strXml = xmlStr.toString();
			logger.info("***strXml=" + strXml);
			return SUCCESS;
		} catch (Exception e1) {
			return "fail";
		}

	}

	public void setPID(String PID) {
		logger.info("**********PID=" + PID);
		this.PID = PID;
	}

	public String getPID() {
		return PID;
	}

	public String getStrXml() {
		return strXml;
	}

	public void setHtp(String htp) {
		this.htp = htp;
	}
}
