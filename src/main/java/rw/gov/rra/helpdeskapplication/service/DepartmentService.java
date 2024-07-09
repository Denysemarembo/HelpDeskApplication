package rw.gov.rra.helpdeskapplication.service;



import rw.gov.rra.helpdeskapplication.model.Department;

import java.util.List;

public interface DepartmentService {
    Department createDepartment(Department department);
    Department getDepartmentById(Long departmentId);
    List<Department> getAllDepartments();

    
}

