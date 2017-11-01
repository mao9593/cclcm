package hdsec.web.project.basic.action;

import hdsec.web.project.activiti.model.JobTypeEnum;

/**
 * 查看作业详情，根据作业类型进行跳转
 * 
 * @author renmingfei
 * 
 */
public class ViewEventDetailAction extends BasicBaseAction {
	private static final long serialVersionUID = 1L;
	private String op = "noprc";
	private String event_code = "";
	private JobTypeEnum jobType = null;
	private String actionContext = "";
	private String barcode;
	private String file_src;

	public void setJobType_code(String jobType_code) {
		this.jobType = JobTypeEnum.valueOf(jobType_code);
	}

	public void setOp(String op) {
		this.op = op;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getActionContext() {
		return actionContext;
	}

	/**
	 * 鉴别当前处理是什么类型的作业，添加任务之后，返回相应的列表，所以要求模块命名、作业命名和数据库表格命名要保持一致
	 * 
	 * @return
	 */
	private String getEventName() {
		String tableName = jobType.getEventTableName();
		return tableName.substring(6);
	}

	@Override
	public String executeFunction() throws Exception {
		String eventName = getEventName();
		if ("transfer".equals(eventName)) {
			actionContext = "/transfer/viewtransferingeventdetail.action?op=" + op + "&event_code=" + event_code
					+ "&barcode=" + barcode;
		} else if ("import".equals(eventName)) {
			actionContext = "/enter/viewentereventdetail.action?op=" + op + "&event_code=" + event_code;
		} else {
			if (("nas").equals(file_src)) {
				actionContext = "/" + eventName + "/" + "viewnas" + eventName + "eventdetail.action?op=" + op
						+ "&event_code=" + event_code + "&file_src=" + file_src;
			} else {
				actionContext = "/" + eventName + "/" + "view" + eventName + "eventdetail.action?op=" + op
						+ "&event_code=" + event_code;
			}
		}
		logger.info("actionContext is :" + actionContext);
		return SUCCESS;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getFile_src() {
		return file_src;
	}

	public void setFile_src(String file_src) {
		this.file_src = file_src;
	}
}
