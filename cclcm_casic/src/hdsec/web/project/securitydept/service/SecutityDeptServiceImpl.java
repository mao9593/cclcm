package hdsec.web.project.securitydept.service;

import java.util.List;
import java.util.Map;

import hdsec.web.project.activiti.service.ActivitiService;
import hdsec.web.project.basic.mapper.BasicMapper;
import hdsec.web.project.basic.service.BasicPrcManage;
import hdsec.web.project.basic.service.BasicService;
import hdsec.web.project.client.service.ClientService;
import hdsec.web.project.common.bm.BMBarcodeGeneratorImpl;
import hdsec.web.project.computer.service.ComputerService;
import hdsec.web.project.secplace.service.SecplaceService;
import hdsec.web.project.securitydept.mapper.SecurityDeptMapper;
import hdsec.web.project.securitydept.model.SecurityDept;
import hdsec.web.project.securityuser.mapper.SecurityUserMapper;
import hdsec.web.project.user.service.UserService;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

public class SecutityDeptServiceImpl implements SecurityDeptService {
	private final Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private SecurityUserMapper securityUserMapper;
	@Resource
	private BasicMapper basicMapper;
	@Resource
	private BasicPrcManage basicPrcManage;
	@Resource
	private UserService userService;
	@Resource
	private ActivitiService activitiService;
	@Resource
	private BasicService basicService;
	@Resource
	protected ClientService clientService;
	@Resource
	protected BMBarcodeGeneratorImpl bMBarcodeGenerator;
	@Resource
	protected SecplaceService secplaceService;
	@Resource
	protected ComputerService computerService;
	@Resource
	private SecurityDeptMapper securityDeptMapper;
	@Override
	public List<SecurityDept> getSecurityUsers(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return securityDeptMapper.getSecurityUsers(map);
	}
	@Override
	public void updatesecurityUser(SecurityDept user) {
		// TODO Auto-generated method stub
		securityDeptMapper.updatesecurityUser(user);
	}
	@Override
	public void addSecurityUser(SecurityDept user) {
		securityDeptMapper.addSecurityUser(user);
	}
	@Override
	public void delSecurityUser(Map<String,Object>map){
		securityDeptMapper.delSecurityUser(map);
	}
	@Override
	public List<SecurityDept> getAllSecurityUsers(Map<String,Object>map) {
		// TODO Auto-generated method stub
		return securityDeptMapper.getAllSecurityUsers(map);
	}
	@Override
	public List<SecurityDept> getFixSecertUsers(Map<String, Object> map) {
		
		return securityDeptMapper.getFixSecertUsers(map);
	}
	
	
}
