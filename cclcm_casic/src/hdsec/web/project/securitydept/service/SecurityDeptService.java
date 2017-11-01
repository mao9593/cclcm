package hdsec.web.project.securitydept.service;

import java.util.List;
import java.util.Map;
import hdsec.web.project.securitydept.model.SecurityDept;

public interface SecurityDeptService {

	List<SecurityDept> getSecurityUsers(Map<String,Object>map);
	void updatesecurityUser(SecurityDept user);
	void delSecurityUser(Map<String,Object>map);
	void addSecurityUser(SecurityDept user);
	List<SecurityDept> getAllSecurityUsers(Map<String,Object>map);
	List<SecurityDept> getFixSecertUsers(Map<String,Object>map);
}
