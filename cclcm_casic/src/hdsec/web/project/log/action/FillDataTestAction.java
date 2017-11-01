package hdsec.web.project.log.action;

import hdsec.web.project.log.service.LogService;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionSupport;

/**
 * 填充数据，测试大数据量的性能
 * @author renmingfei
 *
 */
public class FillDataTestAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	@Resource
	private LogService logService;
	
	@Override
	public String execute() {
		logService.fillDataTest();
		return SUCCESS;
	}
	
}
