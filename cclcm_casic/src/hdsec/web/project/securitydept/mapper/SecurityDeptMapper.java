package hdsec.web.project.securitydept.mapper;

import hdsec.web.project.securitydept.model.SecurityDept;

import java.util.List;
import java.util.Map;

public interface SecurityDeptMapper {
    List<SecurityDept> getSecurityUsers(Map<String, Object> map);
    void updatesecurityUser(SecurityDept user);
    void delSecurityUser(Map<String, Object>map);
    void addSecurityUser(SecurityDept user);
    List<SecurityDept> getAllSecurityUsers(Map<String,Object>map);
    List<SecurityDept> getFixSecertUsers(Map<String,Object>map);
    
}
