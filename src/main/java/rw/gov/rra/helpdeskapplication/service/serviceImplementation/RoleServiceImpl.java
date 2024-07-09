package rw.gov.rra.helpdeskapplication.service.serviceImplementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.gov.rra.helpdeskapplication.model.Role;
import rw.gov.rra.helpdeskapplication.repository.RoleRepository;
import rw.gov.rra.helpdeskapplication.service.RoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository repo;

    @Override
    public Role createRole(Role role) {
        return repo.save(role);
    }

    @Override
    public Role getRoleById(Long roleId) {
        return repo.findById(roleId).orElse(null);
    }

    @Override
    public List<Role> getAllRoles() {
        return repo.findAll();
    }
}
