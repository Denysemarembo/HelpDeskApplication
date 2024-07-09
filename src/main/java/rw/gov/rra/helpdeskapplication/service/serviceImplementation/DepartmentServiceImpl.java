package rw.gov.rra.helpdeskapplication.service.serviceImplementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rw.gov.rra.helpdeskapplication.model.Department;
import rw.gov.rra.helpdeskapplication.repository.DepartmentRepository;
import rw.gov.rra.helpdeskapplication.service.DepartmentService;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository repo;

    @Override
    public Department createDepartment(Department department) {
        return repo.save(department);
    }

    @Override
    public Department getDepartmentById(Long departmentId) {
        return repo.findById(departmentId).orElse(null);
    }

    @Override
    public List<Department> getAllDepartments() {
        return repo.findAll();
    }



}
