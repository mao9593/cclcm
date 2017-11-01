package hdsec.web.project.computer.action;

import hdsec.web.project.computer.model.EntityComputer;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过保密编号查询计算机基本信息，通过ajax实现
 * 
 * @author liuyaling 2015-7-13
 */
public class GetComputerInfoAction extends ComputerBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String conf_code = "";
	private EntityComputer computer = null;

	public EntityComputer getComputer() {
		return computer;
	}

	public void setComputer(EntityComputer computer) {
		this.computer = computer;
	}

	public String getConf_code() {
		return conf_code;
	}

	public void setConf_code(String conf_code) {
		this.conf_code = conf_code;
	}

	@Override
	public String executeFunction() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("conf_code", conf_code);
		EntityComputer computer_tmp = computerService.getComputerByMap(map);
		computer = computer_tmp;
		return SUCCESS;
	}

}