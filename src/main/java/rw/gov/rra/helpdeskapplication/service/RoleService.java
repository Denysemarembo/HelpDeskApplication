package rw.gov.rra.helpdeskapplication.service;



import rw.gov.rra.helpdeskapplication.model.Role;

import java.util.List;

public interface RoleService {
    Role createRole(Role role);
    Role getRoleById(Long roleId);
    List<Role> getAllRoles();
    
}

